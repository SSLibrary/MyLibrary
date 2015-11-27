<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<title>SoftServe Library</title>
</head>
<body>
	<div class="container">
		<jsp:include page="../includes/header.jsp" />
		<div class="jumbotron">
			<h1 class="text-center">Authors</h1>
		</div>
		<form:form action="search" method="GET">
			<div class="row">
				<div class="col-md-12">
					<div class="input-group">
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit">Go!</button>
						</span> <input type="text" class="form-control" name="author_name"
							placeholder="Search for Author by Author's Name">
					</div>
				</div>
			</div>
		</form:form>
		<div class="row">
			<div class="col-md-3 col-xs-6 text-center">
				<h2>Name</h2>
			</div>
			<div class="col-md-3 col-xs-6 text-center">
				<h2>Country</h2>
			</div>
		</div>
		<c:forEach items="${authors}" var="author">
			<div class="row">
				<div class="col-md-3 col-xs-6 text-center">
					<h4>${author.name}</h4>
				</div>
				<div class="col-md-3 col-xs-6 text-center">
					<h4>${author.country}</h4>
				</div>
				<div class="col-md-6">
					<div class="btn-group btn-group-justified">
						<sec:authorize access="hasAuthority('ADMIN')">
							<div class="btn-group">
								<form:form action="${author.id}" method="GET">
									<button type="submit" class="btn btn-primary">Edit</button>
								</form:form>
							</div>
						</sec:authorize>
						<sec:authorize access="hasAuthority('ADMIN')">
							<div class="btn-group">
								<form:form action="${author.id}" method="DELETE">
									<button type="submit" class="btn btn-default">Delete</button>
								</form:form>
							</div>
						</sec:authorize>
						<div class="btn-group">
							<form:form action="${author.id}/books/" method="GET">
								<button type="submit" class="btn btn-primary">Books</button>
							</form:form>
						</div>
					</div>
				</div>
			</div>
			<br />
		</c:forEach>
		<br />
			<!-- pagination -->		
					<tag:paginate max="15" offset="${offset}" count="${count}"
   						uri="../authors/" next="&raquo;" previous="&laquo;" /> 
   					<!-- end of pagination -->		
		<sec:authorize access="hasAuthority('ADMIN')">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8 ">
					<form:form action="/MyLibrary/authors/new/" method="GET">
						<button type="submit" class="btn btn-primary btn-block">Add
							New Author</button>
					</form:form>
				</div>
			</div>
		</sec:authorize>
		<br />
		<jsp:include page="../includes/footer.jsp" />
	</div>
</body>
</html>