<%@page contentType="text/html"	pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib prefix='spring' uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<c:set var='contextPath' value='${pageContext.servletContext.contextPath}' />
<!doctype html>
<html lang='${pageContext.response.locale.language}'>
<head>
<title>Personeel - Spring 4.0 Test - Opslag</title>
<link rel='stylesheet' href='${contextPath}/styles/default.css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<a href="<c:url value='/'/>"><fmt:message key='menu' /></a>
	<header>
		<h1>Opslag voor ${werknemer.naam}</h1>
	</header>
	<section>
		<p>
			Huidig salaris<br />
			<span class="bold"><spring:eval expression="werknemer.salaris"/>&nbsp;&euro;</span>
		</p>
			<div>
				<spring:url value="/werknemers/{id}" var="opslagURL">
					<spring:param name="id" value="${werknemer.id}"/>
				</spring:url>
				<form:form method='put' action="${opslagURL}" commandName="opslagForm">
					<div><form:label path="salaris">Bedrag</form:label></div>
					<div><form:input path="salaris" autofocus="autofocus"/>&nbsp;<form:errors path="salaris"/></div>
					<input type="submit" value="Opslag"/>						
				</form:form>
			</div>
	</section>
</body>
</html>