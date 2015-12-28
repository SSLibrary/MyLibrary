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
<div style="float:left;margin:30px;padding:0px">
	<div>
		<h3><strong>Title: ${book.title}</strong></h3>
	</div>
	<div class="col-sm-10">
		<dl class="dl-horizontal">
	  		<dt>Author:</dt>
		  	<dd>${book.author.name}</dd>
		  	<dt>Pages:</dt>
		  	<dd>${book.pages}</dd>
		  	 <dt>Description:</dt>
		  	<dd><textarea rows="4" cols="70" readonly>${book.bookDescription}</textarea></dd>
		  	<dt>Status:</dt>  	
		  	<dd>${book.status}</dd>
		</dl>
	</div>
</div>

	
		
		