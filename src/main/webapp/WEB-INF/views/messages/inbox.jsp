<%@ include file="../layout/taglib.jsp" %>

		<div class="jumbotron">
			<h1 class="text-center">Inbox</h1>
		</div>
	
		<c:forEach items="${messages}" var="message">
			<c:choose>
				<c:when test="${message.isNew=='0'}">
			<div class="row">
				<div class="col-md-3 col-xs-4 text-center">
					<h5>From: ${message.sender.username}</h5>
				</div>
				<div class="col-md-3 col-xs-4 text-center">
					<h5>Subject: <a href="<c:url value="/messages/${message.message_id}/reply" />">${message.header}</a></h5>
				</div>
				<div class="col-md-3 col-xs-4 text-center">
					<h5>Date: ${message.date}</h5>
				</div>
				<div class="col-md-3 col-xs-4 text-center">
					<h5>Status: Read</h5>
				</div>
			</div>
			</c:when>
			 <c:otherwise>
						<div class="row">
				<div class="col-md-3 col-xs-4 text-center">
					<h5><b>From: ${message.sender.username}</b></h5>
				</div>
				<div class="col-md-3 col-xs-4 text-center">
					<h5><b>Subject: <a href="<c:url value="/messages/${message.message_id}/reply" />">${message.header}</a></b></h5>
				</div>
				<div class="col-md-3 col-xs-4 text-center">
					<h5><b>Date: ${message.date}</b></h5>
				</div>
				<div class="col-md-3 col-xs-4 text-center">
					<h5><b>Status: Unread</b></h5>
				</div>
				</div>
						</c:otherwise>
				</c:choose>
			<br />
		</c:forEach>
		