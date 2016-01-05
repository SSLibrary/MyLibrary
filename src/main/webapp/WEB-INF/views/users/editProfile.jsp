<%@ include file="../layout/taglib.jsp"%>

<form:form modelAttribute="user" class="form-horizontal" role="form">
	<div>
		<div class="edit-profile-form">
			<form:input type="hidden" path="id" id="id" />
			<h2>
				<b>${username}'s Profile</b>
			</h2>
			<br />
			<div class="form-group">
				<label class="control-label col-sm-2" for="firstName">First
					Name:</label>
				<div class="col-sm-4">
					<form:input path="firstName" id="firstName" class="form-control"
						autocomplete="off" />
					<form:errors path="firstName" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="lastName">Last
					Name:</label>
				<div class="col-sm-4">
					<form:input path="lastName" id="lastName" class="form-control"
						autocomplete="off" />
					<form:errors path="lastName" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-4">
					<form:input path="email" id="email" class="form-control"
						autocomplete="off" type="email" />
					<form:errors path="email" cssClass="error" />
				</div>
			</div>
		</div>
	</div>
</form:form>
<div class="row">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4">
					<form:form action="/editProfile" method="POST">
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							Edit Profile
						</button>
					</form:form>
				</div>
			</div>
			</div>
			<div class="row">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-6">
					<form:form
						action="/MyLibrary/users/${currentUserID}/changePassword"
						method="GET">
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							Change Password
						</button>
					</form:form>
				</div>
			</div>
				</div>


