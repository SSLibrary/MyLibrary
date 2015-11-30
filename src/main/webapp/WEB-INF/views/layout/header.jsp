<%@ include file="../layout/taglib.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
	
<div class="navbar navbar-default">
	<ul class="nav navbar-nav">
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/authors/">Authors</a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/users/">Users</a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/messages/inbox">Inbox <span class="badge">${unread}</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/messages/outbox">Outbox</a></li>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
			<li class="${current == 'login' ? 'active' : '' }"><a href="/MyLibrary/login/">Login</a></li>
			<li class="${current == 'register' ? 'active' : '' }"><a href="/MyLibrary/register/">Register</a></li>			
		</sec:authorize>
	</ul>
	<sec:authorize access="isAuthenticated()">
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