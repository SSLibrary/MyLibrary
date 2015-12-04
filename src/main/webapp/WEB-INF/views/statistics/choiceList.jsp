<%@ include file="../layout/taglib.jsp"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>


		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 ">
				<form:form action="authorCount" method="GET">
					<button type="submit" class="btn btn-primary btn-block">Number
						of all authors in the library and information about them</button>
				</form:form>
			</div>
		</div>

		<br></br>

		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 ">
				<form:form action="userCount" method="GET">
					<button type="submit" class="btn btn-primary btn-block">Number
						of all registered users in the library</button>
				</form:form>
			</div>
		</div>

		<br></br>

		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 ">
				<form:form action="booksCount" method="GET">
					<button type="submit" class="btn btn-primary btn-block">Number
						of all book in the library</button>
				</form:form>
			</div>
		</div>