<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>	

<!DOCTYPE html>
<html lang="en">
<head>
	<title><tiles:getAsString name="title"></tiles:getAsString></title>
	<link rel="stylesheet"
	href="../../../../resources/rating-plugin/css/star-rating.min.css"
	media="all" rel="stylesheet" type="text/css" />
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
</head>
<body>	
	<tilesx:useAttribute name="current"/>
  <!--  <h1 style="color: green; margin-top:50px" >${current}</h1> -->
<div class="container">
	<tiles:insertAttribute name="header"/>	
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="footer"/>
</div>
</body>
</html>



