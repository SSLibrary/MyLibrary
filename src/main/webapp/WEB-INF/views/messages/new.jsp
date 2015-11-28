<%@ include file="../layout/taglib.jsp" %>

		<form:form modelAttribute="message" class="form-horizontal" role="form">
			<legend>Send new Message to <b>${user}</b> </legend>
			<form:input type="hidden" path="message_id" id="message_id" />
			<div class="form-group">
				<label for="header" class="control-label col-sm-2">Subject:</label>
				<div class="col-sm-10">
					<form:input path="header" id="header" class="form-control" />
					<form:errors path="header" />
				</div>
			</div>
			<div class="form-group">
				<label for="body" class="control-label col-sm-2">Message:</label>
					<div class="col-sm-10">
						<form:input path="body" id="body" class="form-control" />
						<form:errors path="body" />
					</div>
				</div>
					<div class="form-group">
					<div class="col-sm-2"></div>
						<div class="col-sm-5">
							<form:form action="messages/new" method="POST">
								<button type="submit" class="btn btn-primary form-control">Send</button>
							</form:form>
						</div>
					</div>			
						<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
						</form:form>	
<<<<<<< .mine
					







=======
						</div>
		<br /> <a href="<c:url value='/authors/' />"> <img
			class="img-responsive"
			src="<c:url value='/resources/images/SoftServe-logo.jpg' />"
			alt="SoftServe Logo" width="100%">
		</a>
</body>
</html>
>>>>>>> .theirs
