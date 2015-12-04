<%@ include file="../layout/taglib.jsp"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>


		<div class="panel panel-default">
			<div class="panel-body text-center">Number Of Authors</div>
			<div class="panel-footer text-center">${authorsCount}</div>
		</div>
	
	<br></br>
	
	
	
		<div class="panel panel-default">
			<div class="panel-body text-center">Name and country of the authors</div>
			
				<c:forEach items="${authors}" var="authorVar">
					<tr>
						<div class="panel-footer text-center">${authorVar.name} from ${authorVar.country}</div>
					</tr><p>
				</c:forEach>
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
	