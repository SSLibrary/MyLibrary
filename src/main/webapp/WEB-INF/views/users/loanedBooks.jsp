<%@ include file="../layout/taglib.jsp"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>

<sec:authorize access="hasAuthority('ADMIN')">
	<div class="jumbotron">
		<h1 class="text-center">Loaned Books by Users</h1>
	</div>
	<c:choose>
		<c:when test="${isEmpty}">
			<h3 class="text-center">There are no books loaned!</h3>
		</c:when>
		<c:otherwise>
			<table class="table">
				<tr>
					<th>Title</th>
					<th>Author</th>
					<th>Get Date</th>
					<th>Return Date</th>
					<th>Due</th>
					<th>Loaned By</th>
					<c:forEach items="${loanedBooks}" var="loanedBook">
						<tr>
							<td>${loanedBook.book.title}</td>
							<td>${loanedBook.book.author.name}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd, hh:mm a"
									value="${loanedBook.getDate}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd, hh:mm a"
									value="${loanedBook.returnDate}" /></td>
							<c:choose>
								<c:when test="${loanedBook.returnDate < currDate}">
									<td>Yes</td>
								</c:when>
								<c:otherwise>
									<td>No</td>
								</c:otherwise>
							</c:choose>
							<td><a
								href="/MyLibrary/users/${loanedBook.user.id}/showProfile">${loanedBook.user.username}</a></td>
						</tr>
						<br />
					</c:forEach>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
</sec:authorize>
<!-- pagination -->
<c:choose>
	<c:when test="${count > 5}">
		<div class="text-center">
			<tag:paginate max="15" offset="${offset}" count="${count}"
				uri="../books/loaned" next="&raquo;" previous="&laquo;" />
		</div>
	</c:when>
</c:choose>
<!-- end of pagination -->