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
<div style="float:left;margin:50px;padding:0px">
	<div>
		<h1><strong>Title: ${book.title}</strong></h1>
	</div>	
	<div class="row">
	    <h3>Author: ${book.author.name}</h3>
	    <h3>Status:${book.status}</h3>				    
	</div>	
</div>

	
		
		