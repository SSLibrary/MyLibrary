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
					<li><a href="/MyLibrary/register/">Register</a></li>
				</ul>
			</div>
		</div>

		<div>
			<div>
				<div class="login-form">
					<c:url var="loginUrl" value="/login" />
					<form action="${loginUrl}" method="post" class="form-horizontal">
						<c:if test="${param.error != null}">
							<div class="alert alert-danger">
								<p>Invalid username and password.</p>
							</div>
						</c:if>
						<div class="input-group input-sm">
							<div class="col-md-2">
								<label class="input-group-addon" for="username">Username:</label>
							</div>
							<div class="col-md-10">
								<input type="text" class="form-control" id="username"
									name="username" placeholder="Enter Username" autocomplete="off">
							</div>
						</div>
						<div class="input-group input-sm">
							<div class="col-md-2">
								<label class="input-group-addon" for="password">Password:</label>
							</div>
							<div class="col-md-10">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="Enter Password" autocomplete="off">
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<div class="form-actions">
							<input type="submit"
								class="btn btn-block btn-primary btn-default" value="Log in">
						</div>
					</form>
				</div>
			</div>
		</div>
		<br />
		<jsp:include page="../includes/footer.jsp" />
	</div>
</body>
</html>