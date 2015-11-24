<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<title>SoftServe Library Login page</title>
</head>
<body>
	<div class="container">
		<div class="navbar navbar-default">

			<div class="navbar-header">
				<ul class="nav navbar-nav">
					<li><a href="/MyLibrary/login/">Login</a></li>
				</ul>
			</div>
		</div>

		<form:form modelAttribute="user" class="form-horizontal" role="form">
			<div>
				<div>
					<div class="register-form">
						<form action="/register" method="post" class="form-horizontal">
							<form:input type="hidden" path="id" id="id" />
							<div class="input-group input-sm">
								<div class="col-md-2">
									<label for="username" class="input-group-addon">Username:</label>
								</div>
								<div class="col-md-10">
									<form:input path="username" id="username" class="form-control"
										autocomplete="off" />
									<form:errors path="username" cssClass="error" />
								</div>
							</div>
							<div class="input-group input-sm">
								<div class="col-md-2">
									<label for="password" class="input-group-addon">Password:</label>
								</div>
								<div class="col-md-10">
									<form:input path="password" id="password" class="form-control"
										autocomplete="off" type="password" />
									<form:errors path="password" cssClass="error" />
								</div>
							</div>
							<div class="input-group input-sm">
								<div class="col-md-2">
									<label for="firstName" class="input-group-addon">First
										Name:</label>
								</div>
								<div class="col-md-10">
									<form:input path="firstName" id="firstName"
										class="form-control" autocomplete="off" />
									<form:errors path="firstName" cssClass="error" />
								</div>
							</div>
							<div class="input-group input-sm">
								<div class="col-md-2">
									<label for="lastName" class="input-group-addon">Last
										Name:</label>
								</div>
								<div class="col-md-10">
									<form:input path="lastName" id="lastName" class="form-control"
										autocomplete="off" />
									<form:errors path="lastName" cssClass="error" />
								</div>
							</div>
							<div class="input-group input-sm">
								<div class="col-md-2">
									<label for="email" class="input-group-addon">Email:</label>
								</div>
								<div class="col-md-10">
									<form:input path="email" id="email" class="form-control"
										autocomplete="off" type="email" />
									<form:errors path="email" cssClass="error" />
								</div>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class="form-actions">
								<input type="submit"
									class="btn btn-block btn-primary btn-default" value="Register">
							</div>
						</form>
					</div>
				</div>
			</div>
		</form:form>
		<br />
		<jsp:include page="../includes/footer.jsp" />
	</div>
</body>
</html>