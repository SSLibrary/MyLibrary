<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>	
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<head>
	<title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>
<body>	
	<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>	
	<tilesx:useAttribute name="current"/>
  <!--  <h1 style="color: green; margin-top:50px" >${current}</h1> -->
<div class="container">
	<tiles:insertAttribute name="header"/>				
	<tiles:insertAttribute name="body"/>
	<tiles:insertAttribute name="footer"/>
</div>
</body>
</html>



