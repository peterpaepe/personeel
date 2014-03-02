<%@page contentType="text/html"
	pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%> 
<c:set var='contextPath'
	value='${pageContext.servletContext.contextPath}' />
<!doctype html>
<html lang="${pageContext.response.locale.language}">
	<head>
		<title>Personeel - Spring 4.0 Test - Info</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel='stylesheet'
			href='${pageContext.servletContext.contextPath}/styles/default.css'>
	</head>
	<body>
		<a href="<c:url value='/'/>">Menu</a>
		<header>
			<h1>Pagina niet gevonden</h1>
		</header>		
		<p class="fouten">De pagina die u zocht bestaat helaas niet op	deze website.</p>
	</body>
</html>