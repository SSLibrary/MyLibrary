<%@ include file="../layout/taglib.jsp" %>

		<form:form class="form-horizontal" role="form" modelAttribute="book" enctype="multipart/form-data">
			<legend>Book Details</legend>
			<form:input type="hidden" path="id" id="id" />
			<div class="form-group">
				<label for="title" class="control-label col-sm-2">Title:</label>
				<div class="col-sm-3">
					<form:input path="title" id="title" class="form-control"/>
					<form:errors path="title" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label for="fileUpload" class="control-label col-sm-2">Pic:</label>
				<div class="col-sm-3">					
					<input type="file"  id="fileUpload" name="fileUpload" class="form-control"/>
					<form:errors path="image" cssClass="error" />				
				</div>
			</div>		
			<c:choose>
				<c:when test="${edit}">
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-5">
							<form:form action="${book.id}" method="PUT" >
								<button type="submit" class="btn btn-primary">Edit</button>
							</form:form>
						</div>
					</div>
				</c:when>
				<c:otherwise>				
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-5">
							<form:form action="new" method="POST" >									  	
								<button type="submit" class="btn btn-primary">Add</button>
							</form:form>
						</div>
					</div>				
				</c:otherwise>
			</c:choose>
		</form:form>
		