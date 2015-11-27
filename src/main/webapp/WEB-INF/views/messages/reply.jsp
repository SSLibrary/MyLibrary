<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<title>Send new Message</title>
</head>
<body>
	<div class="container">
		<jsp:include page="../includes/header.jsp" />
		<form:form modelAttribute="message" class="form-horizontal" role="form">
			<legend>Reply to <b>${receiver}</b> </legend>
			<form:input type="hidden" path="message_id" id="message_id" />
			
			<div class="form-group">
				<label for="body" class="control-label col-sm-1">Message:</label>
				<div class="col-sm-10">
					<form:input path="body" id="body" class="form-control" />
					<form:errors path="body" />
				</div>
				</div>
					<div class="form-group">
						<div class="col-sm-1"></div>
						<div class="col-sm-2">
							<form:form action="messages/${message_id}/reply" method="POST">
								<button type="submit" class="btn btn-primary form-control">Reply</button>
							</form:form>
						</div>
					</div>
					<table class="table table-striped">	
							<c:forEach items="${parents}" var="parent">
							<tr>
						
						<td>
						<p><b>From:</b> ${parent.sender.username}</p>
						<p><b>Date:</b> ${parent.date}</p>
						<p><b>From:</b> ${parent.header}</p>
						<p><b>Date:</b> ${parent.body}</p>
						
						</td> 	
						</tr>
						</c:forEach>	
						</table>
						<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
						</form:form>	
						</div>
		<br /> <a href="<c:url value='/authors/' />"> <img
			class="img-responsive"
			src="<c:url value='/resources/images/SoftServe-logo.jpg' />"
			alt="SoftServe Logo" width="100%">
		</a>
</body>
</html>