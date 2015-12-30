<%@ include file="../layout/taglib.jsp"%>

<form:form modelAttribute="user" class="form-horizontal" role="form">
	<div>
		<div
			style="background-color: rgba(192, 192, 192, 0.3); padding: 1px 10px 415px 10px;">
			<div class="register-form">
				<form action="/profile" method="post" class="form-horizontal">
					<form:input type="hidden" path="id" id="id" />
					<h2>
						<b>${username}'s Profile</b>
					</h2>
					<br />
					<div class="form-group">
						<label class="control-label col-sm-2" for="firstName">First
							Name:</label>
						<div class="col-sm-3">
							<form:input path="firstName" id="firstName" class="form-control"
								autocomplete="off" />
							<form:errors path="firstName" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="lastName">Last
							Name:</label>
						<div class="col-sm-3">
							<form:input path="lastName" id="lastName" class="form-control"
								autocomplete="off" />
							<form:errors path="lastName" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Email:</label>
						<div class="col-sm-3">
							<form:input path="email" id="email" class="form-control"
								autocomplete="off" type="email" />
							<form:errors path="email" cssClass="error" />
						</div>
					</div>
					<br /> <input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-5">
							<form:form action="/profile/" method="POST">
								<button type="submit" class="btn btn-primary">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									Edit Profile
								</button>
							</form:form>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-5">
							<form:form action="/profile/changePassword" method="GET">
								<button type="submit" class="btn btn-primary">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									Change Password
								</button>
							</form:form>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</form:form>