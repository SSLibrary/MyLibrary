<%@ include file="../layout/taglib.jsp" %>

		<form:form modelAttribute="book" class="form-horizontal" role="form">
			<legend>Book Details</legend>
			<form:input type="hidden" path="id" id="id" />
			<div class="form-group">
				<label for="name" class="control-label col-sm-2">Title:</label>
				<div class="col-sm-10">
					<form:input path="title" id="title" class="form-control" />
					<form:errors path="title" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label for="country" class="control-label col-sm-2">Status:</label>
				<div class="col-sm-10">
					<form:select path="status" id="status" class="form-control">
						<form:options items="${statuses}" />
					</form:select>
				</div>
			</div>
			<!--
			<div class="form-group">
				<label for="image" class="control-label col-sm-2">Image:</label>
				<div class="col-sm-10">
					<form:input type="file" path="image" id="image" class="form-control" name="image" />
					<form:errors path="image" cssClass="error" />
				</div>
			</div>
			-->
			<c:choose>
				<c:when test="${edit}">
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-10">
							<form:form action="${book.id}" method="PUT">
								<button type="submit" class="btn btn-primary form-control">Edit</button>
							</form:form>
						</div>
					</div>
				</c:when>
				<c:otherwise>				
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-10">
							<form:form action="new" method="POST" enctype="multipart/form-data">
							  	<input type="file"  class="form-control" placeholder="Image" name="image" > 
								<button type="submit" class="btn btn-primary form-control">Add</button>
							</form:form>
						</div>
					</div>				
				</c:otherwise>
			</c:choose>
		</form:form>
		