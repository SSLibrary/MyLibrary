<%@ include file="../layout/taglib.jsp"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>

	    
	    <div class="panel panel-default">
	 		<div class="panel-body text-center">Number of all books in the library</div>
			<div class="panel-footer text-center">${booksCount}</div>
		</div>
	

		
		<!--  sec:authorize access="hasAuthority('ADMIN')"-->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 ">
				<form:form action="choiceList" method="GET">
					<button type="submit" class="btn btn-primary btn-block">More statistics</button>
				</form:form>
			</div>
		</div>