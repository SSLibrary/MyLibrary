<%@ include file="../layout/taglib.jsp"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>

<div class="jumbotron">
	<h1 class="text-center">History</h1>
</div>
<div class="row">
	<div class="col-md-3 col-xs-6 text-center">
		<h2>Name</h2>
	</div>

</div>
<c:forEach items="${history}" var="book">
	<div class="row">
		<div class="col-md-3 col-xs-6 text-center">
			<h4>${book.title}</h4>
		</div>
		<div class="col-md-3 col-xs-6 text-center">
			<h4>${history.get_date}</h4>
		</div>
		<div class="col-md-6">
			<div class="btn-group btn-group-justified">
				<sec:authorize access="hasAuthority('ADMIN')">
					<div class="btn-group">
						<form:form action="${user.user_id}" method="GET">
							<button type="submit" class="btn btn-primary">Return</button>
						</form:form>
					</div>
				</sec:authorize>

			</div>
		</div>
	</div>
	<br />
</c:forEach>
<br />

<!-- pagination -->
<div class="text-center">
	<tag:paginate max="15" offset="${offset}" count="${count}"
		uri="../authors/" next="&raquo;" previous="&laquo;" />
</div>
<!-- end of pagination -->
