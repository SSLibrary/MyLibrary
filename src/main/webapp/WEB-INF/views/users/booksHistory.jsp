<%@ include file="../layout/taglib.jsp" %>

		<div class="jumbotron">
			<h1 class="text-center">Books History</h1>
		</div>
		<c:choose>
			<c:when test="${isEmpty}">
				<h2 class="text-center">No Books Were Loaned yet!</h2>
			</c:when>
			<c:otherwise>
		<div class="row">
			<div class="col-md-3 col-xs-4 text-center">
				<h2>Title</h2>
			</div>
			<div class="col-md-3 col-xs-4 text-center">
				<h2>Author</h2>
			</div>
			<div class="col-md-3 col-xs-4 text-center">
				<h2>Get Date</h2>
			</div>
			<div class="col-md-3 col-xs-4 text-center">
				<h2>Return Date</h2>
			</div>
			
		</div>
		<c:forEach items="${booksHistory}" var="bookHistory">
			<div class="row">
				
				<div class="col-md-3 col-xs-6 text-center">
					<h4>${bookHistory.book.title}</h4>
				</div>
				<div class="col-md-3 col-xs-6 text-center">
					<h4>${bookHistory.book.author.name}</h4>
				</div>
				<div class="col-md-3 col-xs-6 text-center">
					<h4>${bookHistory.getDate}</h4>
				</div>
				<div class="col-md-3 col-xs-6 text-center">
					<h4>${bookHistory.returnDate}</h4>
				</div>
			</div>
			<br />
		</c:forEach> 
		</c:otherwise>
		</c:choose>