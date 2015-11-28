<%@ include file="../layout/taglib.jsp" %>

		<div class="jumbotron">
			<h1 class="text-center">Outbox</h1>
		</div>
	
		<c:forEach items="${messages}" var="message">
			<div class="row">
				<div class="col-md-3 col-xs-4 text-center">
					<h5>To: ${message.sender.username}</h5>
				</div>
				<div class="col-md-3 col-xs-4 text-center">
					<h5>Subject: ${message.header}</h5>
				</div>
				<div class="col-md-3 col-xs-4 text-center">
					<h5>Date: ${message.date}</h5>
				</div>
			</div>
		
			<br />
		</c:forEach>