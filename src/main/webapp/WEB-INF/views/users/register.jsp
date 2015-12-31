<%@ include file="../layout/taglib.jsp"%>

<div class="navbar navbar-default">
	<div class="navbar-header">
		<ul class="nav navbar-nav">
			<li><a href="/MyLibrary/"> <span
					class="glyphicon glyphicon-home" aria-hidden="true"></span> Home
			</a></li>
			<li><a href="/MyLibrary/login/"> <span
					class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
					Login
			</a></li>
		</ul>
	</div>
</div>
<form:form modelAttribute="user" class="form-horizontal" role="form">
	<div class="register-form">
		<div>
			<form action="/register" method="post">
				<form:input type="hidden" path="id" id="id" />
				<h2>
					<b>Registration Form</b>
				</h2>
				<br />
				<div class="form-group">
					<label class="control-label col-sm-2" for="username">Username:</label>
					<div class="col-sm-6 col-md-4">
						<form:input path="username" id="username" class="form-control"
							autocomplete="off" />
						<form:errors path="username" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="password">Password:</label>
					<div class="col-sm-6 col-md-4">
						<form:input path="password" id="password" class="form-control"
							autocomplete="off" type="password" />
						<form:errors path="password" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="firstName">First
						Name:</label>
					<div class="col-sm-6 col-md-4">
						<form:input path="firstName" id="firstName" class="form-control"
							autocomplete="off" />
						<form:errors path="firstName" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="lastName">Last
						Name:</label>
					<div class="col-sm-6 col-md-4">
						<form:input path="lastName" id="lastName" class="form-control"
							autocomplete="off" />
						<form:errors path="lastName" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="email">Email:</label>
					<div class="col-sm-6 col-md-4">
						<form:input path="email" id="email" class="form-control"
							autocomplete="off" type="email" />
						<form:errors path="email" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">Register</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</form:form>