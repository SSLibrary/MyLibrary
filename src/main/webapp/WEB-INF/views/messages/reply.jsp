<%@ include file="../layout/taglib.jsp" %>

		<form:form modelAttribute="message" class="form-horizontal" role="form">
			<legend>Reply to <b>${receiver}</b> </legend>
			<form:input type="hidden" path="message_id" id="message_id" />
			
			<div class="form-group">
				<label for="body" class="control-label col-sm-1">Message:</label>
				<div class="col-sm-5">
					<form:textarea path="body" rows="5" cols="30" id="body" class="form-control" />
					<form:errors path="body" />
				</div>
				</div>
					<div class="form-group">
						<div class="col-sm-1"></div>
						<div class="col-sm-2">
							<form:form action="/${currUser}/messages/${message_id}/reply" method="POST">
								<button type="submit" class="btn btn-primary form-control">Reply</button>
							</form:form>
						</div>
					</div>
					<table class="table table-striped">	
							<c:forEach items="${parents}" var="parent">
							<tr>		
						<td>
						<p><b>From: </b>${parent.sender.username}</p>
						<p><b>To: </b>${parent.receiver.username}</p>
						<p><b>Date: </b><fmt:formatDate pattern="yyyy-MM-dd, hh:mm a" value="${parent.date}" /></p>
						<p><b>Subject: </b>${parent.header}</p>
						<p><b>Message: </b>${parent.body}</p>
						</td> 	
						</tr>
						</c:forEach>	
						</table>
						<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
						</form:form>