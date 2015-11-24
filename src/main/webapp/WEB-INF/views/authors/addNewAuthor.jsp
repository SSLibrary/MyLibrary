<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<title>Author Details</title>
</head>
<body>
	<div class="container">
		<jsp:include page="../includes/header.jsp" />
		<form:form modelAttribute="author" class="form-horizontal" role="form">
			<legend>Author Details</legend>
			<form:input type="hidden" path="id" id="id" />
			<div class="form-group">
				<label for="name" class="control-label col-sm-2">Name:</label>
				<div class="col-sm-10">
					<form:input path="name" id="name" class="form-control" />
					<form:errors path="name" />
				</div>
			</div>
			<div class="form-group">
				<label for="country" class="control-label col-sm-2">Country:</label>
				<div class="col-sm-10">
					<form:select path="country" id="country" class="form-control">
						<form:options items="${countries}" />
					</form:select>
				</div>
			</div>
			<c:choose>
				<c:when test="${edit}">
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-10">
							<form:form action="/${author.id}" method="PUT">
								<button type="submit" class="btn btn-primary form-control">Edit</button>
							</form:form>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-10">
							<form:form action="/new" method="GET">
								<button type="submit" class="btn btn-primary form-control">Add</button>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form:form>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</form:form>
		<br /> <a href="<c:url value='/authors/' />"> <img
			class="img-responsive"
			src="<c:url value='/resources/images/SoftServe-logo.jpg' />"
			alt="SoftServe Logo" width="100%">
		</a>
	</div>
</body>
</html>