package by.itacademy.matveenko.jd2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.itacademy.matveenko.jd2.bean.News;
import by.itacademy.matveenko.jd2.dao.INewsDao;
import by.itacademy.matveenko.jd2.dao.NewsDaoException;
import by.itacademy.matveenko.jd2.dao.connectionpool.ConnectionPool;
import by.itacademy.matveenko.jd2.dao.connectionpool.ConnectionPoolException;

public class NewsDao implements INewsDao {
	
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public List<News> getLatestList(int pageSize) throws NewsDaoException {
		List<News> newsLatestList = new ArrayList<>();
		int startSize = pageSize;
		String selectNewsLatestList = "SELECT * FROM news ORDER BY dateNews DESC LIMIT " + startSize;	 
	        try (Connection connection = ConnectionPool.getInstance().takeConnection();
	        	PreparedStatement ps = connection.prepareStatement(selectNewsLatestList)) {
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                	int idNews = rs.getInt("id");
	    				String titleNews = rs.getString("titleNews");
	    				String briefNews = rs.getString("briefNews");
	    				String contentNews = rs.getString("contentNews");
	    				//String dateNews = rs.getString("dateNews");
	    				//int idReporter = rs.getInt("idReporter");
	    				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    				Timestamp timestamp = rs.getTimestamp(5);
	    				String date = new SimpleDateFormat("yyyy-MM-dd").format(timestamp);
	    				News latestNews = new News(idNews, titleNews, briefNews, contentNews, date);
	    				newsLatestList.add(latestNews);
	    			}	    						
	        }	        
	    } catch (SQLException | ConnectionPoolException e) {
	    	log.error(e);
	    	throw new NewsDaoException(e);
	    	}
	        return newsLatestList;
	 }				

	@Override
	public List<News> getNewsList(Integer pageNumber, Integer pageSize) throws NewsDaoException {
		List<News> newsList = new ArrayList<>();
		int startSize = (pageNumber - 1) * pageSize;
		String selectNewsList = "SELECT * FROM news ORDER BY dateNews DESC LIMIT " + startSize + "," + pageSize;	 
	        try (Connection connection = ConnectionPool.getInstance().takeConnection();
	        	PreparedStatement ps = connection.prepareStatement(selectNewsList)) {
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                	int idNews = rs.getInt("id");
	    				String titleNews = rs.getString("titleNews");
	    				String briefNews = rs.getString("briefNews");
	    				String contentNews = rs.getString("contentNews");
	    				//String dateNews = rs.getString("dateNews");
	    				//int idReporter = rs.getInt("idReporter");
	    				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    				Timestamp timestamp = rs.getTimestamp(5);
	    				String date = new SimpleDateFormat("yyyy-MM-dd").format(timestamp);
	    				News news = new News(idNews, titleNews, briefNews, contentNews, date);
	    				newsList.add(news);
	    			}	    						
	        }	        
	    } catch (SQLException | ConnectionPoolException e) {
	    	log.error(e);
	    	throw new NewsDaoException(e);
	    	}
	        return newsList;
	 }				
	
	@Override
	public News fetchById(Integer id) throws NewsDaoException {
		News news = null;
		String selectNewsById = "SELECT * FROM news WHERE id = ?";
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
	        PreparedStatement ps = connection.prepareStatement(selectNewsById)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                	int idNews = rs.getInt("id");
                	String titleNews = rs.getString("titleNews");
    				String briefNews = rs.getString("briefNews");
    				String contentNews = rs.getString("contentNews");
    				//String dateNews = rs.getString("dateNews");
    				//int idReporter = rs.getInt("idReporter");
    				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    				Timestamp timestamp = rs.getTimestamp(5);
    				String date = new SimpleDateFormat("yyyy-MM-dd").format(timestamp);
    				news = new News(idNews, titleNews, briefNews, contentNews, date);
    				}
                }
			} catch (SQLException | ConnectionPoolException e) {
				log.error(e);
				throw new NewsDaoException(e);
			}
			return news;
	}

	@Override
	public int addNews(News news) throws NewsDaoException {
		int row = 0;
		String insertNews = "INSERT INTO news(titleNews, briefNews, contentNews, date, reporter) VALUES (?,?,?,?,?)";
		try (Connection connection = ConnectionPool.getInstance().takeConnection();
		    PreparedStatement ps = connection.prepareStatement(insertNews, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, news.getTitleNews());
            ps.setString(2, news.getBriefNews());
            ps.setString(3, news.getContentNews());
            ps.setString(4, getDateNews());
            //ps.setString(4, news.getReporter());
            row = ps.executeUpdate();
            if (row == 0) {
				throw new NewsDaoException("News not saved!");
			}
			//ResultSet generateKey = ps.getGeneratedKeys();
			//if (generateKey.next()) {
				//throw new NewsDaoException("News not saved!");
			//}
			//System.out.println("generateKey.getInt(DatabaseTableColumn.TABLE_NEWS_COLUMN_ID_NEWS)"
					//+ generateKey.getInt(DatabaseTableColumn.TABLE_NEWS_COLUMN_ID_NEWS));
			//return generateKey.getInt("id");
				} catch (SQLException | ConnectionPoolException e) {
					log.error(e);
					throw new NewsDaoException(e);
				}
				return row;
		}
	
	@Override
	public int updateNews(News news) throws NewsDaoException {
		int row = 0;
		// TODO Auto-generated method stub
		return row;
	}

	@Override
	public void deleteNewses(String[] idNewses) throws NewsDaoException {
		// TODO Auto-generated method stub		
	}
	
	private String getDateNews() {
	    ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("GMT+3"));
	    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	    String date = dateTimeFormatter.format(zonedDateTime);	    
	    return date;
	}
}