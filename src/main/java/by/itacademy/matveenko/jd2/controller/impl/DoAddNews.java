package by.itacademy.matveenko.jd2.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.itacademy.matveenko.jd2.bean.ConnectorStatus;
import by.itacademy.matveenko.jd2.bean.News;
import by.itacademy.matveenko.jd2.controller.AttributsName;
import by.itacademy.matveenko.jd2.controller.Command;
import by.itacademy.matveenko.jd2.controller.JspPageName;
import by.itacademy.matveenko.jd2.controller.NewsParameterName;
import by.itacademy.matveenko.jd2.service.INewsService;
import by.itacademy.matveenko.jd2.service.ServiceException;
import by.itacademy.matveenko.jd2.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoAddNews implements Command{
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final Logger log = LogManager.getRootLogger();
	public static final String COMMAND_EXECUTED = "command_executed";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String titleNews = request.getParameter(NewsParameterName.JSP_TITLE_NEWS);
			String briefNews = request.getParameter(NewsParameterName.JSP_BRIEF_NEWS);
			String contentNews = request.getParameter(NewsParameterName.JSP_CONTENT_NEWS);
			String dateNews = request.getParameter(NewsParameterName.JSP_DATE_NEWS);

			HttpSession getSession = request.getSession(true);
			News news = new News(titleNews, briefNews, contentNews, dateNews);
			try {
				//System.out.println("newsService.save(news) " + newsService.save(news));
				if (newsService.save(news)) {				
					// getSession.setAttribute(AttributsKey.USER, ConnectionStatus.ACTIVE);
					// getSession.setAttribute(AttributsKey.ROLE, role);
					//getSession.removeAttribute(AttributsName.REGISTER_USER);
					getSession.setAttribute(AttributsName.USER, ConnectorStatus.ACTIVE);
					getSession.setAttribute(AttributsName.REGISTER_USER, ConnectorStatus.REGISTERED);
					getSession.setAttribute(AttributsName.ADD_NEWS, COMMAND_EXECUTED);
					response.sendRedirect("controller?command=go_to_news_list");
					//getSession.removeAttribute(AttributsName.ADD_NEWS);
				} else {
					response.sendRedirect(JspPageName.ERROR_PAGE);
				}
			} catch (ServiceException e) {
				log.error(e);
				response.sendRedirect(JspPageName.INDEX_PAGE);
			}
		}
	}