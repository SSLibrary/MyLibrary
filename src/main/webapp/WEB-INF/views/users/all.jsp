<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<title>SoftServe Library Users List</title>
</head>
<body>
	<div class="container">
		<jsp:include page="../includes/header.jsp" />
		<div class="jumbotron">
			<h1 class="text-center">Users</h1>
		</div>
		<form:form action="search" method="GET">
			<div class="row">
				<div class="col-md-12">
					<div class="input-group">
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit">Go!</button>
						</span> <input type="text" class="form-control" name="username"
							placeholder="Search for User by Username">
					</div>
				</div>
			</div>
		</form:form>
		<div class="row">
			<div class="col-md-3 col-xs-4 text-center">
				<h2>Name</h2>
			</div>
			<div class="col-md-3 col-xs-4 text-center">
				<h2>Status</h2>
			</div>
			<div class="col-md-3 col-xs-4 text-center">
				<h2>Role</h2>
			</div>
		</div>
		<c:forEach items="${allUsers}" var="user">
			<div class="row">
				<div class="col-md-3 col-xs-4 text-center">
					<h4>${user.username}</h4>
				</div>
				<div class="col-md-3 col-xs-4 text-center">
					<h4>${user.userStatus}</h4>
				</div>
				<div class="col-md-3 col-xs-4 text-center">
					<h4>${user.userRole}</h4>
				</div>
				<c:choose>
				<c:when test="${ user.userRole == 'ADMIN'}">
					<div class="col-md-3">
					<div class="btn-group btn-group-justified">
						<div class="btn-group">
							<button type="submit" class="btn btn-primary disabled">Change Status</button>
						</div>
					</div>
				</div>
				</c:when>
				<c:otherwise>
					<div class="col-md-3">
					<div class="btn-group btn-group-justified">
						<sec:authorize access="hasAuthority('ADMIN')">
							<div class="btn-group">
								<form:form action="${user.id}" method="PUT">
									<button type="submit" class="btn btn-primary">Change Status</button>
								</form:form>
							</div>
						</sec:authorize>
					</div>
				</div>
				</c:otherwise>
			</c:choose>
			</div>
			<br />
		</c:forEach>
		<br /> 
			<jsp:include page="../includes/footer.jsp" />
	</div>
</body>
</html>