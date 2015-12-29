<%@ include file="../layout/taglib.jsp" %>

<c:choose>
	<c:when test="${emptyList}">			
		<div class="col-md-2 ">	
			<img class="img-responsive"	src="<c:url value='/resources/images/noCoverImage.gif' />"
			alt="SoftServe Logo"  width="300" height="300" alt="book cover">											
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
				class="btn btn-info" role="button">Show Comments</a>
			</div>	
		<sec:authorize access="hasAuthority('ADMIN')">
			  <div class="btn-group">
					<a href="/MyLibrary/authors/${book.author.id}/books/${book.id}/ratingCheck" 
						class="btn btn-info" role="button">Check Rating</a>
			  </div>
		</sec:authorize>
		<sec:authorize access="hasAuthority('USER')">
				<c:choose>		
				<c:when test="${book.isRated}">
						<div class="btn-group">
							<a href="/MyLibrary/authors/${book.author.id}/books/${book.id}/ratingCheck" 
									class="btn btn-info" role="button">Check Rating</a>
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