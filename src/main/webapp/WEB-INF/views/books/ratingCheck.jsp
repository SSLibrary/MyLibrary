<link rel="stylesheet"
	href="../../../../resources/rating-plugin/css/star-rating.min.css"
	media="all" rel="stylesheet" type="text/css" />
	
<%@ include file="../layout/taglib.jsp" %>

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
			<input id="input-21f" class="form-control rating" min='1' max='5'
				step='0.1' data-size="lg" type="number" />
		</div>
		
<!-- raiting script -->

	<script src="../../../../resources/rating-plugin/js/star-rating.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		$('#input-21f').rating('create', {
			disabled : true
		});
		$('#input-21f').rating('update', '${book.averageRating}');
	</script>	
<!--end raiting script -->	