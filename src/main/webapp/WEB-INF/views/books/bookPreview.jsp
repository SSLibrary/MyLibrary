<%@ include file="../layout/taglib.jsp" %>


	  <c:choose>
			<c:when test="${emptyList}">
				<div class="alert alert-danger">
					<h2 class="text-center">Empty book cover</h2>				
				</div>						
			</c:when>
			<c:otherwise>		
				<div class="text-center">
					<div class="col-md-3 col-xs-6 text-center">						
						<img src="${image}"  width="300" height="300" alt="book cover">
					</div>
				</div>	
			</c:otherwise>
		</c:choose>
		
		<dl class="dl-horizontal">
  		<dt>Title</dt>
 		 <dd>${book.title}</dd>
		</dl>
		
        <div class="row">
        <ul>
        <li>Author: ${book.author.name}</li></ul>
        </div>
        <div class="row">
        <div class="col-md-3 col-xs-6 text-center"><p align="right">Status:${book.status}</p></div>
	</div>
	
		
		