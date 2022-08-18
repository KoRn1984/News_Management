<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="body-title">
	<a href="controller?command=go_to_news_list">News >> </a>News List
</div>
<form action="" method="post">
    <c:if test="${sessionScope.register_user eq 'not_registered'}">
		<c:import url="/WEB-INF/pages/tiles/registration.jsp" />		     
	<c:if test="${sessionScope.register_user eq 'registered'}">
		<center><font color="green">Registration completed successfully!</font></center>
	</c:if>
	</c:if><br />				     
	<c:if test="${sessionScope.addNews eq 'command_executed'}">
	    <center><font color="green">Data saved successfully!</font></center>
	</c:if><br />

	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">			
				<div class="news-title">
				<strong>				
					<c:out value="${news.titleNews}" />
				</strong>			
				</div>							
				<div class="news-date">
					<c:out value="${news.dateNews}" />
				</div>
				<div class="news-content">
					<c:out value="${news.briefNews}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'admin'}">
						      <a href="">Edit</a> 
						</c:if>						
						<a href="controller?command=go_to_view_news&id=${news.idNews}">View</a>   					    
   					    <c:if test="${sessionScope.role eq 'admin'}">
   					         <input type="checkbox" name="idNews" value="${news.idNews}" />
   					    </c:if>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<!-- <logic:notEmpty name="newsForm" property="newsList">
		<div class="delete-button-position">
			<html:submit>
				<bean:message key="locale.newslink.deletebutton" />
			</html:submit>
		</div>
	</logic:notEmpty> -->
	<c:if test="${sessionScope.showNews eq 'not_show'}">
	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
		<font color="red">
        No news for unregistered user!
        </font>
	    </c:if>
	</div>
	</c:if>	
</form>