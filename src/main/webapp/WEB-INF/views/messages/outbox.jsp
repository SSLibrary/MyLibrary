
<%@ include file="../layout/taglib.jsp" %>

		<div class="jumbotron">
			<h1 class="text-center">Message Outbox</h1>
		</div>
		<c:choose>
				<c:when test="${isEmpty}">
				
				<h3 class="text-center">There are no messages sent!</h3>
 	 </c:when>
			 <c:otherwise>
		
	<table class="table table-striped">	
		<c:forEach items="${messages}" var="message">
			<tr>
					<td>To: <i>${message.receiver.username}</i></td>
					<td>Subject: <i>${message.header}</i></td>
					<td>Date: <i>${message.date}</i></td>	
					</tr>	
			<br />
		