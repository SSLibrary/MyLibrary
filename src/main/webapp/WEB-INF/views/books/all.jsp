<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<title>SoftServe Library</title>
</head>
<body>
	<div class="container">
		<jsp:include page="../includes/header.jsp" />
		<div class="jumbotron">
			<h1 class="text-center">${author.name}</h1>
		</div>
		<c:choose>
			<c:when test="${emptyList}">
				<h2 class="text-center">No Books For This Author Added Yet</h2>
			</c:when>

			<c:otherwise>
				<form:form action="search" method="GET">
					<div class="row">
						<div class="col-md-12">
							<div class="input-group">
								<span class="input-group-btn">
									<button class="btn btn-default" type="submit">Go!</button>
								</span> <input type="text" class="form-control" name="bookTitle"
									placeholder="Search for Book by Book Title">
							</div>
						</div>
					</div>
				</form:form>
				<div class="row">
					<div class="col-md-3 col-xs-6 text-center">
						<h2>Title</h2>
					</div>
					<div class="col-md-3 col-xs-6 text-center">
						<h2>Status</h2>
					</div>
				</div>
				<c:forEach items="${books}" var="book">
					<div class="row">
						<div class="col-md-3 col-xs-6 text-center">
							<h4>${book.title}</h4>
						</div>
						<div class="col-md-3 col-xs-6 text-center">
							<h4>${book.status}</h4>
						</div>
						<div class="col-md-6">
							<div class="btn-group btn-group-justified">
								<sec:authorize access="hasAuthority('ADMIN')">
									<div class="btn-group">
										<form:form action="${book.id}" method="GET">
											<button type="submit" class="btn btn-primary">Edit</button>
										</form:form>
									</div>
									<div class="btn-group">
										<form:form action="${book.id}" method="DELETE">
											<button type="submit" class="btn btn-default">Delete</button>
										</form:form>
									</div>
									<div class="btn-group">
										<form:form action="${book.id}/ratingCheck" method="GET">
											<button type="submit" class="btn btn-info">Check
												Rating</button>
										</form:form>
									</div>
								</sec:authorize>
								<sec:authorize access="hasAuthority('USER')">
									<c:choose>
										<c:when test="${book.isRated}">

											<div class="btn-group">
												<form:form action="${book.id}/ratingCheck" method="GET">
													<button type="submit" class="btn btn-info">Check
														Rating</button>
												</form:form>
											</div>

										</c:when>
										<c:otherwise>
											<div class="btn-group">
												<form:form action="${book.id}/rating" method="GET">
													<button type="submit" class="btn btn-info">Add
														Rating</button>
												</form:form>
											</div>
										</c:otherwise>
									</c:choose>
								</sec:authorize>
							</div>
						</div>
					</div>
				</c:forEach>
				<br />
			</c:otherwise>
		</c:choose>
		<br />
		<sec:authorize access="hasAuthority('ADMIN')">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8 ">
					<form:form action="new" method="GET">
						<button type="submit" class="btn btn-primary btn-block">Add
							New Book</button>
					</form:form>
				</div>
			</div>
		</sec:authorize>
		<br /> <a href="<c:url value='/authors/' />"> <img
			class="img-responsive"
			src="<c:url value='/resources/images/SoftServe-logo.jpg' />"
			alt="SoftServe Logo" width="100%">
		</a>
	</div>
</body>
</html>