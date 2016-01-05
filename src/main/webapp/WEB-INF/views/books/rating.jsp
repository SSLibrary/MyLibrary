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
			
			<input id="input-6c" class="rating" data-min="0" data-max="5" data-step="1" data-size="md">
			<button type="submit" class="btn btn-primary">Add Rating</button>
		</form:form>
	</div>
</div>
<br/>
<div class="btn-toolbar">
        	<div class="btn-group">
        	<a href="/MyLibrary/authors/${book.author.id}/books/${book.id}/preview"
						class="btn btn-primary" role="button"> <span
						class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back
					</a>
				</div>
		</div >
<br />

<!-- rating script -->
<script src="../../../../resources/rating-plugin/js/star-rating.min.js"	type="text/javascript"></script>
<script type="text/javascript">
	$('#input-6c').on('rating.change', function(event, value) {
		document.getElementById("ratingValue").value = value;
	});
</script>
<!--end rating script -->	
	