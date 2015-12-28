<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	body {
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-position: center top;
} 
</style>
<title><tiles:getAsString name="title"></tiles:getAsString></title>
<link rel="shortcut icon" href="/MyLibrary/favicon.ico" />
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.css" />
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body style="background-color: rgba(245,245,245,0.5);">
	<tilesx:useAttribute name="current" />
	<div class="container">	
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />	
		<tiles:insertAttribute name="footer" />	
	</div>
</body>
</html>



