<%@ include file="../layout/taglib.jsp" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>

		<div class="jumbotron">
			<h1 class="text-center">Users</h1>
		</div>
		<form:form action="search" method="GET">
			<div class="row">
				<div class="col-md-12">
					<div class="input-group">
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit">Go!</button>
						</span> <input type="text" class="form-control" name="username"
							placeholder="Search for User by Username">
					</div>
				</div>
			</div>
		</form:form>
		<div class="row">
			<div class="col-md-3 col-xs-4 text-center">
				<h2>Name</h2>
				
			</div>
			<sec:authorize access="hasAuthority('ADMIN')">
			<div class="col-md-3 col-xs-4 text-center">
				<h2>Status</h2>
			</div>
			
			<div class="col-md-3 col-xs-4 text-center">
				<h2>Role</h2>
			</div>
			</sec:authorize>
		</div>
		<c:forEach items="${allUsers}" var="user">
			<div class="row">
				<div class="col-md-3 col-xs-4 text-center">
				<h4><a href="<c:url value='/${currUser}/messages/${user.id}/new' />">${user.username}</a>
					</h4>
				</div>
				<sec:authorize access="hasAuthority('ADMIN')">
				<div class="col-md-3 col-xs-4 text-center">
					<h4>${user.userStatus}</h4>
				</div>
				<div class="col-md-3 col-xs-4 text-center">
					<h4>${user.userRole}</h4>
				</div>
				
				<c:choose>
				<c:when test="${ user.userRole == 'ADMIN'}">
					<div class="col-md-3">
					<div class="btn-group btn-group-justified">
						<div class="btn-group">
							<button type="submit" class="btn btn-primary disabled">Change Status</button>
						</div>
					</div>
				</div>
				</c:when>
				<c:otherwise>
					<div class="col-md-3">
					<div class="btn-group btn-group-justified">
						<sec:authorize access="hasAuthority('ADMIN')">
							<div class="btn-group">
								<form:form action="${user.id}" method="PUT">
									<button type="submit" class="btn btn-primary">Change Status</button>
								</form:form>
							</div>
						</sec:authorize>
						
					</div>
				</div>
				</c:otherwise>
			</c:choose>
			</sec:authorize>
			</div>
			<br />
		</c:forEach>
			<!-- pagination -->
				<div class="text-center">	
					<tag:paginate max="15" offset="${offset}" count="${count}"
   						uri="../users/" next="&raquo;" previous="&laquo;" /> 
   				</div>	
   			<!-- end of pagination -->	
		