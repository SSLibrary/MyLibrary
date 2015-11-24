<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="../../../../resources/rating-plugin/css/star-rating.min.css"
	media="all" rel="stylesheet" type="text/css" />
<title>SoftServe Library</title>
</head>
<body>
	<div class="container">
		<jsp:include page="../includes/header.jsp" />
		<div class="row">
			<div class="col-md-3 col-xs-6 text-center">
				<h2>Title</h2>
			</div>
			<div class="col-md-3 col-xs-6 text-center">
				<h2>Status</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3 col-xs-6 text-center">
				<h4>${book.title}</h4>
			</div>
			<div class="col-md-3 col-xs-6 text-center">
				<h4>${book.status}</h4>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<form:form modelAttribute="rating" class="form-horizontal"
					role="form" action="rating" method="POST">
					<form:input type="hidden" path="ratingValue" id="ratingValue" />
					<form:errors path="ratingValue" cssClass="error" />
					<input id="input-21b" class="form-control rating" min='0' max='5'
						step='1' data-size="lg" type="number" />
					<button type="submit" class="btn btn-primary form-control">Add
						Rating</button>
				</form:form>
			</div>
		</div>
		<br />
		<jsp:include page="../includes/footer.jsp" />
	</div>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="../../../../resources/rating-plugin/js/star-rating.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		$('#input-21b').on('rating.change', function(event, value) {
			document.getElementById("ratingValue").value = value;
		});
	</script>
</body>
</html>