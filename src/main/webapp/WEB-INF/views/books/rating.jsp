<link rel="stylesheet"
	href="../../../../resources/rating-plugin/css/star-rating.min.css"
	media="all" rel="stylesheet" type="text/css" />
<%@ include file="../layout/taglib.jsp" %>

<div class="row">
	<div class="col-md-3 col-xs-6 text-center">
		<h3>Title: ${book.title}</h3>
	</div>
</div>
<div class="row">
	<div class="col-md-6">
		<form:form modelAttribute="rating" class="form-horizontal"
			role="form" action="rating" method="POST">
			<form:input type="hidden" path="ratingValue" id="ratingValue" />
			<form:errors path="ratingValue" cssClass="error" />
			<input id="input-21b" class="form-control rating" min='0' max='5' step='1' data-size="lg" type="number" />
			<button type="submit" class="btn btn-primary">Add Rating</button>
		</form:form>
	</div>
</div>
<br />

<!-- rating script -->
<script src="../../../../resources/rating-plugin/js/star-rating.min.js"	type="text/javascript"></script>
<script type="text/javascript">
	$('#input-21b').on('rating.change', function(event, value) {
		document.getElementById("ratingValue").value = value;
	});
</script>
<!--end rating script -->	
	