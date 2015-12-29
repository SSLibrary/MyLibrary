<%@ include file="../layout/taglib.jsp" %>

<div class="navbar navbar-default">
	<div class="navbar-header">
		<ul class="nav navbar-nav">
			<li><a href="/MyLibrary/">
			<span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a></li>
			<li><a href="/MyLibrary/register/">
			<span class="glyphicon glyphicon-copy" aria-hidden="true"></span> Register</a></li>					
		</ul>
	</div>
</div>		
	<div style="background-color:rgba(192,192,192,0.3);padding: 1px 10px 530px 10px;">
		<div class="login-form" >
			<div>
				<c:url var="loginUrl" value="/login" />					
				<form action="${loginUrl}" method="post" class="form-horizontal">
					<c:if test="${param.error != null}">
						<div class="alert alert-danger">
							<p>Invalid username and password.</p>
						</div>
					</c:if>		
					<h2>
						<b>Please Login</b>
					</h2><br/>			
					<div class="form-group">
	  					<label class="control-label col-sm-2" for="username">Username:</label>
	  					<div class="col-sm-3">
	   						<input type="text" class="form-control" id="username"
	   						 name="username" placeholder="Enter Username">
	 					</div>
					</div>					
					<div class="form-group">
	  					<label class="control-label col-sm-2" for="password">Password:</label>
	  					<div class="col-sm-3">
	   					   <input type="password" class="form-control" id="password"
	   					    name="password" placeholder="Enter Password">
	 					</div>
					</div>				
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />			
				    <div class="form-group"> 
	  					<div class="col-sm-offset-2 col-sm-10">
	   						 <button type="submit" class="btn btn-primary">Log in</button>
	  					</div>
					</div>	
			</form>
		</div>
	</div>
</div>