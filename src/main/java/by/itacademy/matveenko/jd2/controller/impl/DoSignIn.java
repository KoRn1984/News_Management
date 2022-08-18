package by.itacademy.matveenko.jd2.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.itacademy.matveenko.jd2.service.IUserService;
import by.itacademy.matveenko.jd2.bean.ConnectorStatus;
import by.itacademy.matveenko.jd2.bean.UserRole;
import by.itacademy.matveenko.jd2.controller.AttributsName;
import by.itacademy.matveenko.jd2.controller.Command;
import by.itacademy.matveenko.jd2.controller.JspPageName;
import by.itacademy.matveenko.jd2.controller.UserParameterName;
import by.itacademy.matveenko.jd2.service.ServiceException;
import by.itacademy.matveenko.jd2.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignIn implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();
	private static final Logger log = LogManager.getRootLogger();


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		login = request.getParameter(UserParameterName.JSP_LOGIN_PARAM);
		password = request.getParameter(UserParameterName.JSP_PASSWORD_PARAM);	

		if (!dataValidation(login, password)) {
            response.sendRedirect(JspPageName.INDEX_PAGE);
            return;
        }
		try {
			UserRole role = service.signIn(login, password);
			if (!role.equals(UserRole.GUEST)) {
				request.getSession(true).setAttribute(AttributsName.USER, ConnectorStatus.ACTIVE);
				request.getSession(true).setAttribute(AttributsName.ROLE, role.getName());
				request.getSession(true).setAttribute(AttributsName.REGISTER_USER, ConnectorStatus.REGISTERED);
				response.sendRedirect("controller?command=go_to_news_list");
			} else {
				request.getSession(true).setAttribute(AttributsName.USER, ConnectorStatus.NOT_ACTIVE);
				response.sendRedirect("controller?command=go_to_base_page&AuthenticationError=Wrong login or password!");
			}			
		} catch (ServiceException e) {
			log.error(e);
			response.sendRedirect(JspPageName.INDEX_PAGE);
		}		
	}
	
	private boolean dataValidation(String login, String password) {
        if (login == null || password == null) {
            return false;
        }
        return true;
    }
}