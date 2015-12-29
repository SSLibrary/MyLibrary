<%@ include file="../layout/taglib.jsp" %>

<c:choose>
	<c:when test="${emptyList}">			
		<div class="col-md-2 ">	
			<img class="img-responsive"	src="<c:url value='/resources/images/noCoverImage.gif' />"
			alt="no cover"  width="300" height="300" alt="book cover">											
		</div>						
	</c:when>
	<c:otherwise>		
		<div class="text-center">
			<div class="col-md-3 col-xs-6 text-center">						
				<img src="${image}"  width="300" height="300" alt="book cover"/>				
			</div>			
		</div>
	</c:otherwise>	
</c:choose>
<div style="float:left; margin-left:50px; padding:0px">
	<div>
		<h3><strong>Title: ${book.title}</strong></h3>
	</div>
		<dl class="dl-horizontal" style="margin-left:-80px;">
	  		<dt>Author:</dt>
		  	<dd>${book.author.name}</dd>
		  	<dt>Pages:</dt>
		  	<dd>${book.pages}</dd>
		  	<dt>Description:</dt>
		  	<dd style="width: 500px">${book.bookDescription}</dd>
		  	<dt>Status:</dt>  	
		  	<dd>${book.status}</dd>
		</dl>
	</div>
	<div style="float:left; margin-left:130px;">
	
	   <div class="btn-group">
			<a href="/MyLibrary/authors/${book.author.id}/books/${book.id}/comments" 
				class="btn btn-info" role="button">
				<span class="glyphicon glyphicon-comment" aria-hidden="true"></span> Show Comments</a>
			</div>	
		<sec:authorize access="hasAuthority('ADMIN')">
			  <div class="btn-group">
					<a href="/MyLibrary/authors/${book.author.id}/books/${book.id}/ratingCheck" 
						class="btn btn-info" role="button">
						<span class="glyphicon glyphicon-stats" aria-hidden="true"></span> Check Rating</a>
			  </div>
			  <div class="btn-group">
								<a href="/MyLibrary/authors/${book.author.id}/books/${book.id}" 
						class="btn btn-default" role="button">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit</a>
							</div>
							<div class="btn-group">
								<form:form action="${book.id}" method="DELETE" >
									<button
									onclick="if (confirm('Are you sure you want to delete this book?')) { form.action='${book.id}'; } else { return false; }"
									id="deleteForm" type="submit" class="btn btn-default">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete</button>
								</form:form>
							</div>	  
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER')">
				<c:choose>		
				<c:when test="${book.isRated}">
						<div class="btn-group">
							<a href="/MyLibrary/authors/${book.author.id}/books/${book.id}/ratingCheck" 
									class="btn btn-info" role="button">
									<span class="glyphicon glyphicon-stats" aria-hidden="true"></span> Check Rating</a>
						</div>
				    </c:when>						  
				   <c:otherwise>
				   <div class="btn-group">
							<a href="/MyLibrary/authors/${book.author.id}/books/${book.id}/rating" 
									class="btn btn-info" role="button">Add Rating</a>
						</div>							
				  </c:otherwise>
				</c:choose>
				<c:choose>
				    <c:when test="${book.status =='Available'}">
						<div class="btn-group">
							<a href="/MyLibrary/books/${book.id}/${currUser}/addToHistory" 
									class="btn btn-success" role="button">Get Book</a>
						</div>
				    </c:when>
				</c:choose>
		</sec:authorize>
	</div>