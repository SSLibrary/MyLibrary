<%@ include file="../layout/taglib.jsp" %>

		<div class="navbar navbar-default">

			<div class="navbar-header">
				<ul class="nav navbar-nav">
					<li><a href="/MyLibrary/register/">Register</a></li>
				</ul>
			</div>
		</div>		
			<div>
				<div class="login-form">
					<div>
					<c:url var="loginUrl" value="/login" />
					<form action="${loginUrl}" method="post" class="form-horizontal">
						<c:if test="${param.error != null}">
							<div class="alert alert-danger">
								<p>Invalid username and password.</p>
							</div>
						</c:if>
						<div class="input-group input-sm">
							<div class="col-md-2" style="padding-top:3px">
								<label class="input-group-addon" for="username">Username:</label>
							</div>
							<div class="col-md-10" style="left:32px">
								<input type="text" class="form-control" id="username"
									name="username" placeholder="Enter Username" autocomplete="off">
							</div>
						</div>
						<div class="input-group input-sm">
							<div class="col-md-2" style="padding-top:3px">
								<label class="input-group-addon" for="password">Password:</label>
							</div>
							<div class="col-md-10" style="left:32px">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="Enter Password" autocomplete="off">
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<div class="form-actions">
							<input type="submit"
								class="btn btn-primary" value="Log in">
						</div>
					</form>
				</div>
			</div>
		</div>