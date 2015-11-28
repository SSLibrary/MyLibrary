<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title><tiles:getAsString name="title"></tiles:getAsString></title>
	<link rel="stylesheet"
	href="../../../../resources/rating-plugin/css/star-rating.min.css"
	media="all" rel="stylesheet" type="text/css" />
	
</head>
<body>	
	<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>	
	<tilesx:useAttribute name="current"/>
  <!--  <h1 style="color: green; margin-top:50px" >${current}</h1> -->

	<tiles:insertAttribute name="header"/>	
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="footer"/>
</body>
</html>



