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
		</c:forEach>
		
		</table>
		</c:otherwise>
		</c:choose>
		<br /> 
			<jsp:include page="../includes/footer.jsp" />
	</div>
</body>
</html>