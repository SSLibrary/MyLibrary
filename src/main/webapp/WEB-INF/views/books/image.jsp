<%@ include file="../layout/taglib.jsp" %>
		<c:choose>
			<c:when test="${emptyList}">
				<h2 class="text-center"></h2>
			</c:when>
			<c:otherwise>		
				<div class="text-center">
					<div class="col-md-3 col-xs-6 text-center">
						<img src="${image}" height="500" width="500">
					</div>
				</div>	
			</c:otherwise>
		</c:choose>		