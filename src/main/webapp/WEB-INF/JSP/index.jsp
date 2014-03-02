<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%> 
<!doctype html>
<html lang="${pageContext.response.locale.language}">
	<head>
		<title>Personeel - Spring 4.0 Test - Welkom</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel='stylesheet'   href='${pageContext.servletContext.contextPath}/styles/default.css'> 
	</head>
	<body>
		<h1><fmt:message key='menu'/></h1>
		<c:import url='/WEB-INF/JSP/menu.jsp'/> 
	</body>
</html>