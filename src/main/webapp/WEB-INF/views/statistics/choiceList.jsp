<%@ include file="../layout/taglib.jsp"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>

		Please select type of your search:
		<!--select onchange="stat" name="statistic">
		<optgroup label="Users"></optgroup>
			<option value="userCount">Number registered users</option>
		<optgroup label="Books"></optgroup>	
			<option value="bookCount">Number of all book in the library</option>
			<option value="listOfBooks">See the names of all book in the library</option>
			<option value="bookStatus">Show status of all books</option>
			<option value="bookCover">Number of all book with cover in the library</option>
		<optgroup label="Authors"></optgroup>	
			<option value="authorCount">Number of all authors in the library</option>
			<option value="listOfAuthors">See the names of all authors in the library</option>
		<optgroup label="Messages"></optgroup>
			<option value="bookCover">Number of all inbox messages</option>
			<option value="bookCover">Number of all outbox messages</option>
		</select-->

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