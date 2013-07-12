<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page session="false" %>
<html>
	<head>
		<title>Spring Social Showcase</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/page.css"   type="text/css" media="screen" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/form.css"   type="text/css" media="screen" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/messages/messages.css"   type="text/css" media="screen" />
	</head>
	<body>
		<div id="header">
			<h1><a href="${pageContext.request.contextPath}/">Spring Social Showcase</a></h1>
		</div>
		
		<div id="leftNav">
			<tiles:insertTemplate template="menu.jsp" />
		</div>
		
		<div id="content">
			<tiles:insertAttribute name="content" />
		</div>		
	</body>
</html>
