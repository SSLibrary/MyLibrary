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
			<h1 class="text-center">Message Inbox</h1>
		</div>
	
	<c:choose>
				<c:when test="${isEmpty}">
				
				<h3 class="text-center">There are no messages received!</h3>
 	 </c:when>
			 <c:otherwise>
			 <table class="table table-striped">	
		<c:forEach items="${messages}" var="message">
		<tr>
			<c:choose>
				<c:when test="${message.isNew=='0'}">
					<td>From: <i>${message.sender.username}</i></td>
					<td>Subject: <i><a href="<c:url value="/messages/${message.message_id}/reply" />">${message.header}</a></i></td>
					<td>Date: <i>${message.date}</i></td>
					<td>Status: <i>Read</i></td>
			</c:when>
			 <c:otherwise>		
					<td><b>From: <i>${message.sender.username}</i></b></td>
					<td><b>Subject: <i><a href="<c:url value="/messages/${message.message_id}/reply" />">${message.header}</a></i></b></td>
					<td><b>Date: <i>${message.date}</i></b></td>
					<td><b>Status: <i>Unread</i></b></td>
						</c:otherwise>		
				</c:choose>
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