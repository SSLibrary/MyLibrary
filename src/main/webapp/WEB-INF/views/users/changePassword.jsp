<%@ include file="../layout/taglib.jsp"%>

<form:form modelAttribute="user" class="form-horizontal" role="form">
	<div>
		<div
			style="background-color: rgba(192, 192, 192, 0.3); padding: 1px 10px 415px 10px;">
			<div class="register-form">
				<form action="/profile" method="post" class="form-horizontal">
					<form:input type="hidden" path="id" id="id" />
					<h3>
						<b>Change Password</b>
					</h3>
					<div class="form-group">
						<label class="control-label col-sm-2" for="password">Current
							Password:</label>
						<div class="col-sm-3">
							<form:input path="password" id="currentPassword"
								class="form-control" autocomplete="off" type="password" />
							<form:errors path="password" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="password">New
							Password:</label>
						<div class="col-sm-3">
							<form:input path="newPassword" id="newPassword"
								class="form-control" autocomplete="off" type="password" />
							<form:errors path="newPassword" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="password">Enter
							Again New Password:</label>
						<div class="col-sm-3">
							<form:input path="newPassword2" id="repeatNewPassword"
								class="form-control" autocomplete="off" type="password" />
							<form:errors path="newPassword2" cssClass="error" />
						</div>
					</div>

					<br /> <input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<form:form action="/profile/changePassword" method="POST">
						<button type="submit" class="btn btn-primary">Change</button>
					</form:form>
				</form>
			</div>
		</div>
	</div>
</form:form>