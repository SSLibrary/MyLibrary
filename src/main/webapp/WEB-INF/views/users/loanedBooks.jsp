<%@ include file="../layout/taglib.jsp" %>

<sec:authorize access="hasAuthority('ADMIN')">
		<div class="jumbotron">
			<h1 class="text-center">Loaned Books by Users</h1>
		</div>
		<c:choose>
			<c:when test="${isEmpty}">
				<h2 class="text-center">No Books Are Loaned!</h2>
			</c:when>
			<c:otherwise>	
			 <table class="table">	
			 <tr>
			 <th>Title</th>
			 <th>Author</th>
			 <th>Get Date</th>
			 <th>Return Date</th>
			 <th>Loaned By</th>
				<c:forEach items="${loanedBooks}" var="loanedBook">
				<tr>
					<td>${loanedBook.book.title}</td>
					<td>${loanedBook.book.author.name}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd, hh:mm a" value="${loanedBook.getDate}" /></td>			
					<td><fmt:formatDate pattern="yyyy-MM-dd, hh:mm a" value="${loanedBook.returnDate}" /></td>	
					<td><a href="/MyLibrary/users/${loanedBook.user.id}/profile" >${loanedBook.user.username}</a></td>
					</tr>
					<br />	
				</c:forEach>
			</tr>
			</table>
			</c:otherwise>
		</c:choose>
	</sec:authorize>