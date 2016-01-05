<link rel="stylesheet"
	href="../../../../resources/rating-plugin/css/star-rating.min.css"
	media="all" rel="stylesheet" type="text/css" />
<%@ include file="../layout/taglib.jsp"%>

<div class="row">
<c:if test="${largeSizeOfImage eq true}">
	<p class="btn btn-danger">Large SIZE</p>
</c:if>	
	<c:choose>
		<c:when test="${emptyListOfAuthorBooks}">
			<div class="col-sm-6 col-md-3">
				<div class="thumbnail">
					<img src="<c:url value='/resources/images/noCoverImage.jpg' />"
						alt="no cover" alt="book cover">
				</div>							
			</div>
		</c:when>		
		<c:otherwise>
			<div class="col-sm-6 col-md-3">
				<div class="thumbnail">
					<img src="${image}" alt="book cover" />
				</div>
			</div>
		</c:otherwise>
	</c:choose>		
	<div class="col-sm-6 col-md-9">
		<div>
			<h3>
				<strong>Book Title: ${book.title}</strong>
			</h3>
		</div>
		<dl class="dl-horizontal">
			<dt>Author:</dt>
			<dd>
				<a href="<c:url value='/authors/${book.author.id}/books/' />">
					${book.author.name}</a>
			</dd>
			<dt>Pages:</dt>
			<dd>${book.pages}</dd>
			<dt>Description:</dt>
			<dd>${book.bookDescription}</dd>
			<dt>Status:</dt>
			<c:choose>
				<c:when test="${isBookLoaned}">
					<dd>${book.status}
						by <a
							href="<c:url value='/users/${currentBookLoaner.id}/showProfile' />">
							${currentBookLoaner.username}</a>
					</dd>
				</c:when>
				<c:otherwise>
					<dd>${book.status}</dd>
				</c:otherwise>
			</c:choose>
		</dl>
	</div>
</div>
<div>
	<div class="col-md-offset-3">
		<div class="btn-group">
			<a
				href="/MyLibrary/authors/${book.author.id}/books/${book.id}/comments"
				class="btn btn-info" role="button"> <span
				class="glyphicon glyphicon-comment" aria-hidden="true"></span> Show
				Comments
			</a>
		</div>
		<sec:authorize access="hasAuthority('ADMIN')">
			<div class="btn-group">
				<a href="/MyLibrary/authors/${book.author.id}/books/${book.id}"
					class="btn btn-default" role="button"> <span
					class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit
				</a>
			</div>
			<div class="btn-group">
				<form:form method="DELETE"
					action="/MyLibrary/authors/${book.author.id}/books/${book.id}">
					<button
						onclick="if (confirm('Are you sure you want to delete this book?')) { return true; } else { return false; }"
						id="deleteForm" type="submit" class="btn btn-default">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
						Delete
					</button>
				</form:form>
			</div>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER')">
			<c:if test="${book.isRated == false}">
				<div class="btn-group">
					<a
						href="/MyLibrary/authors/${book.author.id}/books/${book.id}/rating"
						class="btn btn-info" role="button">Add Rating</a>
				</div>
			</c:if>
			<c:choose>
				<c:when test="${book.status =='Available'}">
					<div class="btn-group">
						<a
							href="/MyLibrary/books/${book.id}/${currentUserID}/addToHistory"
							class="btn btn-success" role="button">Get Book</a>
					</div>
				</c:when>
			</c:choose>
		</sec:authorize>
	</div>
</div>
<br />
<div class="row">
	<input id="input-21" class="form-control rating" min='1' max='5'
		step='0.1' type="number" />
</div>


<!-- rating script -->
<script src="../../../../resources/rating-plugin/js/star-rating.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$('#input-21').rating('create', {
		disabled : true
	});
	$('#input-21').rating('update', '${book.averageRating}');
</script>
<!--end rating script -->
