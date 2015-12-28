<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>	
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<head>
	<title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>
<body>			
	<tilesx:useAttribute name="current"/>
	<div class="container">
		<tiles:insertAttribute name="header"/>				
		<tiles:insertAttribute name="body"/>
		<tiles:insertAttribute name="footer"/>
	</div>
</body>
</html>



