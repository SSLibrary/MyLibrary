<%@ include file="../layout/taglib.jsp"%>

<div class="form-group">
	<form:form modelAttribute="user" class="form-horizontal" role="form">
		<div>
			<div class="edit-profile-form">
				<form action="editProfile" method="POST" class="form-horizontal">
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
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-5">
							<button type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								Edit Profile
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</form:form>

	<form:form modelAttribute="user" class="form-horizontal" role="form">
		<div>
			<div class="change-password-form">
				<form action="/profile/changePassword" method="POST"
					class="form-horizontal">
					<form:input type="hidden" path="id" id="id" />
					<div class="form-group">
						<label class="control-label col-sm-2" for="password">Current
							Password: </label>
						<div class="col-sm-3">
							<form:input path="password" id="password" class="form-control"
								autocomplete="off" />
							<form:errors path="password" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="newPassword">New
							Password:</label>
						<div class="col-sm-3">
							<form:input path="newPassword" id="newPassword"
								class="form-control" autocomplete="off" />
							<form:errors path="newPassword" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="newPassword2">Confirm
							New Password:</label>
						<div class="col-sm-3">
							<form:input path="newPassword2" id="newPassword2"
								class="form-control" autocomplete="off" type="email" />
							<form:errors path="newPassword2" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-5">
							<button type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								Change Password
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</form:form>
</div>