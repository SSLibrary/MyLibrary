<%@ include file="../layout/taglib.jsp"%>

<div style="float: left; margin-left: 50px; padding: 0px">
	<div>
		<h3>
			<strong>${username}'s Profile</strong>
		</h3>
	</div>
	<dl class="dl-horizontal" style="margin-left: -80px;">
		<dt>First Name:</dt>
		<dd>${user.firstName}</dd>
		<dt>Last Name</dt>
		<dd>${user.lastName}</dd>
		<dt>Email</dt>
		<dd>${user.email}</dd>
		<dt>Status</dt>
		<dd>${user.userStatus}</dd>
		<dt>Role</dt>
		<dd>${user.userRole}</dd>
	</dl>
</div>
<div style="float: left; margin-left: 130px;">
	<div class="btn-group">
		<a href="/MyLibrary/messages/${currentUserID}/new/${user.id}"
			class="btn btn-info" role="button"> <span
			class="glyphicon glyphicon-envelope" aria-hidden="true"></span> Send
			Message
		</a>
	</div>
	<div class="col-md-2">
		<div class="btn-group btn-group-justified">
			<sec:authorize access="hasAuthority('ADMIN')">
				<div class="btn-group">
					<form:form action="/MyLibrary/users/${user.id}" method="PUT">
						<button type="submit" class="btn btn-primary">Change
							Status</button>
					</form:form>
				</div>
			</sec:authorize>
		</div>
	</div>
</div >