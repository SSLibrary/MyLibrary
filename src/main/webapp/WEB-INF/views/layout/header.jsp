<%@ include file="../layout/taglib.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
	
<div class="navbar navbar-default">
	<ul class="nav navbar-nav">		
		<li><a href="/MyLibrary/">Home</a></li>
	<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/books">Books</a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/authors/">Authors</a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/users/">Users</a></li>
		</sec:authorize>	
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/${currUser}/messages/inbox">Inbox <span class="badge">${unread}</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/${currUser}/messages/outbox">Outbox</a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/${currUser}/books">Books History</a></li>
		</sec:authorize>

		<sec:authorize access="!isAuthenticated()">
			<li class="${current == 'login' ? 'active' : '' }"><a href="/MyLibrary/login/">Login</a></li>
			<li class="${current == 'register' ? 'active' : '' }"><a href="/MyLibrary/register/">Register</a></li>			
		</sec:authorize>
	</ul>
	<sec:authorize access="isAuthenticated()">
		<p class="text-right" style="margin-right:10px">
			Hello, <strong><sec:authentication property="principal.username" /></strong>
		</p>
		<form action="/MyLibrary/logout" method="post"
			class="navbar-form navbar-right">
			<button type="submit" class="btn btn-default btn-sm" style="margin-right:10px">
				<span class="glyphicon glyphicon-log-out"></span> Log out
			</button>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</sec:authorize>
</div>