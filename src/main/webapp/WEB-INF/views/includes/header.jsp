<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="navbar navbar-default">
	<ul class="nav navbar-nav">
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/authors/">Authors</a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/users/">Users</a></li>
			</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/messages/inbox">Inbox</a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/messages/outbox">Outbox</a></li>
		</sec:authorize>
		<sec:authorize access="!isFullyAuthenticated()">
			<li><a href="/MyLibrary/login/">Login</a></li>
			<li><a href="/MyLibrary/register/">Register</a></li>
		</sec:authorize>
	</ul>
	<sec:authorize access="isFullyAuthenticated()">
		<p class="text-right">
			Hello,
			<sec:authentication property="principal.username" />
		</p>
		<form action="/MyLibrary/logout" method="post"
			class="navbar-form navbar-right">
			<button type="submit" class="btn btn-default btn-sm">
				<span class="glyphicon glyphicon-log-out"></span> Log out
			</button>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</sec:authorize>
</div>