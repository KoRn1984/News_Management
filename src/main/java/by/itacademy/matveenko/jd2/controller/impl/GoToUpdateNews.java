package by.itacademy.matveenko.jd2.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.itacademy.matveenko.jd2.bean.ConnectorStatus;
import by.itacademy.matveenko.jd2.bean.News;
import by.itacademy.matveenko.jd2.controller.AttributsName;
import by.itacademy.matveenko.jd2.controller.Command;
import by.itacademy.matveenko.jd2.controller.JspPageName;
import by.itacademy.matveenko.jd2.service.INewsService;
import by.itacademy.matveenko.jd2.service.ServiceException;
import by.itacademy.matveenko.jd2.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToUpdateNews implements Command {
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final Logger log = LogManager.getRootLogger();
	private static final String NEWS_ID = "id";	
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;		
		String id;
		id = request.getParameter(NEWS_ID);		
		try {
			news = newsService.findById(Integer.parseInt(id));
			request.setAttribute(AttributsName.NEWS, news);
			request.setAttribute("update_news", ConnectorStatus.ACTIVE);
			request.getRequestDispatcher(JspPageName.BASELAYOUT_PAGE).forward(request, response);
		} catch (ServiceException e) {		
			log.error(e);
        	response.sendRedirect(JspPageName.ERROR_PAGE);
		}		
	}
}