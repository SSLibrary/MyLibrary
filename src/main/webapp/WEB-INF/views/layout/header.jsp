<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>

<div class="navbar navbar-default">
	<ul class="nav navbar-nav">			
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/">
			<span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/books">
			<span class="glyphicon glyphicon-book" aria-hidden="true"></span> Books</a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/authors/">Authors</a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/users/">
			<span class="glyphicon glyphicon-user" aria-hidden="true"></span> Users</a></li>
		</sec:authorize>	
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/${currUser}/messages/inbox">Inbox <span class="badge">${unread}</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER') OR hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/${currUser}/messages/outbox">Outbox</a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER')">
			<li><a href="/MyLibrary/books/${currUser}">Books History</a></li>
		</sec:authorize>
		<sec:authorize access="hasAuthority('ADMIN')">
			<li><a href="/MyLibrary/books/loaned">Loaned Books</a></li>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">	
			<li class="${current == 'login' ? 'active' : '' }"><a href="/MyLibrary/login/">Login</a></li>
			<li class="${current == 'register' ? 'active' : '' }"><a href="/MyLibrary/register/">Register</a></li>			
		</sec:authorize>
	</ul>
	<sec:authorize access="isAuthenticated()">
		<p class="text-right" style="margin-right:10px">
			Hello, <a href="/MyLibrary/users/${currUser}/profile/"><strong><sec:authentication property="principal.username" /></strong></a>
		</p>
		<form action="/MyLibrary/logout" method="post" class="navbar-form navbar-right">
			<button type="submit" class="btn btn-default btn-sm" style="margin-right:10px">
				<span class="glyphicon glyphicon-log-out"></span>Log out 
			</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</sec:authorize>
</div>