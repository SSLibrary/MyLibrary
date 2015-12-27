<%@ include file="../layout/taglib.jsp" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>

		<div class="jumbotron">
			<h2 class="text-center"><b>${author}/${book}/List of Comments</b></h2>
		</div>	
		<table class="table table-striped">
        	   <tr>
        	   <c:choose>
					<c:when test="${isEmpty}">
					<h3 class="text-center">There are no comments posted for this book yet !</h3>
						</c:when>
        		<c:otherwise>
		
		        <c:forEach items="${comments}" var="comment">
		           <tr>
					<td>"${comment.comment}"</td>
					<td>posted by <a href="<c:url value='/${currUser}/messages/${comment.user.id}/new' />"><b>${comment.user.username}</b></a></td>
						<sec:authorize access="hasAuthority('ADMIN')">
						<td><form:form action="comments/${comment.comment_id}" method="DELETE">
									<button type="submit" class="btn btn-default">Delete</button>
								</form:form>
							</td>
						</sec:authorize>
				   </tr>
			   <br />
		      </c:forEach>
		     <br />
			  </c:otherwise>
		   </c:choose>
            </tr>
        </table>
			<div class="row">
				<div class="col-md-2 " >
					<form:form action="comments/new" method="GET">
						<button type="submit" class="btn btn-primary btn-block">Add New Comment</button>
					</form:form>
				</div>
			</div>
		