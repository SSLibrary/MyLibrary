<%@ include file="../layout/taglib.jsp" %>

		<form:form modelAttribute="comment" class="form-horizontal" role="form">
			<legend>Add New Comment for Book <b><i>"${book}"</i></b>:</legend>
			<form:input type="hidden" path="comment_id" id="comment_id" />
			<div class="form-group">
				<label for="name" class="control-label col-sm-2">Comment:</label>
				<div class="col-sm-5">
					<form:textarea path="comment" id="comment" rows="5" cols="30" class="form-control" />
					<form:errors path="comment" cssClass="error" />
				</div>
			</div>
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-2">
							<form:form action="comments/new" method="POST">
								<button type="submit" class="btn btn-primary form-control">Add</button>
							</form:form>
						</div>
					</div>
		</form:form>