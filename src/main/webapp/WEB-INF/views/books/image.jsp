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
						<img src="${image}"  width="500" height="500" alt="book cover">
					</div>
				</div>	
			</c:otherwise>
		</c:choose>
		