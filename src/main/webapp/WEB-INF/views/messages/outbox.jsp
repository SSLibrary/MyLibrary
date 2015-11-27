<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<title>SoftServe Library Users List</title>
</head>
<body>
	<div class="container">
		<jsp:include page="../includes/header.jsp" />
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
		<br /> 
			<jsp:include page="../includes/footer.jsp" />
	</div>
</body>
</html>