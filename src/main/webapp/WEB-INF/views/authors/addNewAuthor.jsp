<%@ include file="../layout/taglib.jsp" %>

		<form:form modelAttribute="author" class="form-horizontal" role="form">
			<legend>Author Details</legend>
			<form:input type="hidden" path="id" id="id" />
			<div class="form-group">
				<label for="name" class="control-label col-sm-2">Name:</label>
				<div class="col-sm-10">
					<form:input path="name" id="name" class="form-control" />
					<form:errors path="name" />
				</div>
			</div>
			<div class="form-group">
				<label for="country" class="control-label col-sm-2">Country:</label>
				<div class="col-sm-10">
					<form:select path="country" id="country" class="form-control">
						<form:options items="${countries}" />
					</form:select>
				</div>
			</div>
			<c:choose>
				<c:when test="${edit}">
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-10">
							<form:form action="/${author.id}" method="PUT">
								<button type="submit" class="btn btn-primary form-control">Edit</button>
							</form:form>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-10">
							<form:form action="/new" method="GET">
								<button type="submit" class="btn btn-primary form-control">Add</button>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form:form>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</form:form>
		