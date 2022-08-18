<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="body-title">
	<a href="controller?command=go_to_news_list">News >> </a>News View
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">Title</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<strong>
				<c:out value="${requestScope.news.titleNews}" />
			</strong>
			</div>
			</td>
		</tr>
		<tr>
			<td class="space_around_title_text">Date</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				<c:out value="${requestScope.news.dateNews}" />
			</div>
			</td>
		</tr>
		<tr>
			<td class="space_around_title_text">Brief</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				<c:out value="${requestScope.news.briefNews}" />
			</div>
			</td>
		</tr>
		<tr>
			<td class="space_around_title_text">Content</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
				<c:out value="${requestScope.news.contentNews}" />
			</div>
			</td>
		</tr>
	</table>
</div>

<c:if test="${sessionScope.role eq 'admin'}">
<div class="first-view-button">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="edit" />
		<input type="hidden" name="id" value="${news.idNews}" />
		<input type="submit" value="Edit" />
	</form>
</div>

<div class="second-view-button">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="delete" />
		<input type="hidden" name="id" value="${news.idNews}" />
		<input type="submit" value="Delete" />
	</form>
</div>
</c:if>