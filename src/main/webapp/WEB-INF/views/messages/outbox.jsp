<%@ include file="../layout/taglib.jsp" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>

		<div class="jumbotron">
			<h1 class="text-center">Message Outbox</h1>
		</div>
		<c:choose>
			<c:when test="${isEmpty}">				
				<h3 class="text-center">There are no messages sent!</h3>
 	 		</c:when>
			<c:otherwise>		
				<table class="table">	
					<c:forEach items="${messages}" var="message">
						<tr>
								<td>To: <i>${message.receiver.username}</i></td>
								<td>Subject: <i><a href="<c:url value="/${currUser}/messages/${message.message_id}/display" />">${message.header}</a></i></td>
								<td>Date: <i><fmt:formatDate pattern="yyyy-MM-dd, hh:mm a" value="${message.date}" /></i></td>	
								</tr>
						<br />
					</c:forEach>
				</table>
				</c:otherwise>				
		</c:choose>	
			<!-- pagination -->				
   			<c:choose>
				<c:when test="${count > 4}">
				<div class="text-center" style="top:773px;position:absolute;margin-left:520px;">		
					<tag:paginate max="15" offset="${offset}" count="${count}"
   						uri="../messages/outbox" next="&raquo;" previous="&laquo;" /> 
   				</div>
   				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>	
   			<!-- end of pagination -->	