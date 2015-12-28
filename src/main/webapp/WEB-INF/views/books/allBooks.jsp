<%@ include file="../layout/taglib.jsp" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>

<div class="jumbotron">
	<h1 class="text-center">Books</h1>
</div>
<form:form action="search" method="GET">
	<div class="row">
		<div class="col-md-12">
			<div class="input-group">
				<span class="input-group-btn">
					<button class="btn btn-default" type="submit">Go!</button>
				</span> <input type="text" class="form-control" name="title"
					placeholder="Search for Book by Title">
			</div>
		</div>
	</div>
</form:form>
<div class="row">
	<div class="col-md-3 col-xs-4 text-center">
		<h2>Title</h2>
	</div>
	<div class="col-md-3 col-xs-4 text-center">
		<h2>Author</h2>
	</div>
	<div class="col-md-3 col-xs-4 text-center">
		<h2>Status</h2>
	</div>			
</div>
<c:forEach items="${books}" var="book">
	<div class="row">
		<div class="col-md-3 col-xs-6 text-center">
			<h4>
				<a href="<c:url value='/authors/${book.author.id}/books/${book.id}/comments' />">${book.title}</a>
			</h4>
		</div>
		<div class="col-md-3 col-xs-6 text-center">
			<h4>${book.author.name}</h4>
		</div>
		<div class="col-md-3 col-xs-6 text-center">
			<h4>${book.status}</h4>
		</div>
		<c:choose>
			<c:when test="${book.status =='Available'}">
				<div class="btn-group">
				<a href="/MyLibrary/${currUser}/books/${book.id}/addToHistory" class="btn btn-default" role="button">Get</a>
				</div>
			</c:when>
		</c:choose>
	</div>
	<br />
</c:forEach>

<!-- pagination -->				
<c:choose>
	<c:when test="${count > 4}">
		<div class="text-center" style="top:773px;position:absolute;margin-left:520px;">	
			<tag:paginate max="15" offset="${offset}" count="${count}"
 						uri="./books" next="&raquo;" previous="&laquo;" /> 
		</div>
	</c:when>
</c:choose>	
<!-- end of pagination -->		