<%@ include file="../layout/taglib.jsp" %>

		<div class="jumbotron">
			<h1 class="text-center">Books History</h1>
		</div>
		<c:choose>
			<c:when test="${isEmpty}">
				<h2 class="text-center">No Books Were Loaned yet!</h2>
			</c:when>
			<c:otherwise>	
			 <table class="table">	
			 <tr>
			 <th>Title</th>
			 <th>Author</th>
			 <th>Get Date</th>
			 <th>Return Date</th>
			 <th></th>
				<c:forEach items="${booksHistory}" var="bookHistory">
				<tr>
					<td>${bookHistory.book.title}</td>
					<td>${bookHistory.book.author.name}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd, hh:mm a" value="${bookHistory.getDate}" /></td>			
					<td><fmt:formatDate pattern="yyyy-MM-dd, hh:mm a" value="${bookHistory.returnDate}" /></td>		
					
					<c:choose>
				<c:when test="${bookHistory.isReturned=='0'}">
				<td>
				<a href="/MyLibrary/${currUser}/books/${bookHistory.id}/return" class="btn btn-default" role="button">Return</a>
				</td>
				</c:when>
					<c:otherwise>
					<td>Returned</td>
					</c:otherwise>
					</c:choose>
					</tr>
					<br />	
				</c:forEach>
			</tr>
			</table>
			</c:otherwise>
		</c:choose>