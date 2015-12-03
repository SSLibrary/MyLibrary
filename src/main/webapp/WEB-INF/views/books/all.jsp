<%@ include file="../layout/taglib.jsp" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>

		<div class="jumbotron">
			<h1 class="text-center">${author.name}</h1>
		</div>
		<c:choose>
			<c:when test="${emptyList}">
				<h2 class="text-center">No Books For This Author Added Yet</h2>
			</c:when>

			<c:otherwise>
				<form:form action="search" method="GET">
					<div class="row">
						<div class="col-md-12">
							<div class="input-group">
								<span class="input-group-btn">
									<button class="btn btn-default" type="submit">Go!</button>
								</span> <input type="text" class="form-control" name="bookTitle"
									placeholder="Search for Book by Book Title">
							</div>
						</div>
					</div>
				</form:form>
				<div class="row">	
				<!-- 
					<div class="col-md-3 col-xs-6 text-center">
						<h2>Picture</h2>
					</div>
					-->			
					<div class="col-md-3 col-xs-6 text-center">
						<h2>Title</h2>
					</div>
					<div class="col-md-3 col-xs-6 text-center">
						<h2>Status</h2>
					</div>
				</div>
				<c:forEach items="${books}" var="book">
					<div class="row">
				<!-- 	<div class="col-md-3 col-xs-6 text-center">
							<img src="data:image/jpeg;base64,${galleria.itemContent.encodeBase64()}"/>	
						</div>	
						 -->									
						<div class="col-md-3 col-xs-6 text-center">
							<h4>${book.title}</h4>
						</div>
						<div class="col-md-3 col-xs-6 text-center">
							<h4>${book.status}</h4>
						</div>
						<div class="col-md-6">
							<div class="btn-group btn-group-justified">
							<div class="btn-group">
										<form:form action="${book.id}/comments" method="GET">
											<button type="submit" class="btn btn-success">Comments</button>
										</form:form>
									</div>
								<sec:authorize access="hasAuthority('ADMIN')">
									<div class="btn-group">
										<form:form action="${book.id}" method="GET">
											<button type="submit" class="btn btn-primary">Edit</button>
										</form:form>
									</div>
									<div class="btn-group">
										<form:form action="${book.id}" method="DELETE">
											<button type="submit" class="btn btn-default">Delete</button>
										</form:form>
									</div>
									<div class="btn-group">
										<form:form action="${book.id}/ratingCheck" method="GET">
											<button type="submit" class="btn btn-info">Check
												Rating</button>
										</form:form>
									</div>
								</sec:authorize>
								<sec:authorize access="hasAuthority('USER')">
									<c:choose>
										<c:when test="${book.isRated}">

											<div class="btn-group">
												<form:form action="${book.id}/ratingCheck" method="GET">
													<button type="submit" class="btn btn-info">Check
														Rating</button>
												</form:form>
											</div>

										</c:when>
										<c:otherwise>
											<div class="btn-group">
												<form:form action="${book.id}/rating" method="GET">
													<button type="submit" class="btn btn-info">Add
														Rating</button>
												</form:form>
											</div>
										</c:otherwise>
									</c:choose>
								</sec:authorize>
							</div>
						</div>
					</div>
				</c:forEach>
				<br />
			</c:otherwise>
		</c:choose>			
		<br />
		<sec:authorize access="hasAuthority('ADMIN')">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8 ">
					<form:form action="new" method="GET">
						<button type="submit" class="btn btn-primary btn-block">Add
							New Book</button>
					</form:form>
				</div>
			</div>
		</sec:authorize>
			<!-- pagination -->
				<div class="centered" style="top:780px;position:absolute;">	
					<tag:paginate max="15" offset="${offset}" count="${count}"
   						uri="../books/" next="&raquo;" previous="&laquo;" /> 
   				</div>	
   			<!-- end of pagination -->	
