<%@page contentType="text/html"	pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype>
<html lang='${pageContext.response.locale.language}'>
<head>
<title>Personeel - Spring 4.0 Test - Werknemershi&euml;rarchy</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel='stylesheet' href='styles/default.css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<a href="<c:url value='/'/>"><fmt:message key='menu'/></a>
	<c:choose>
	<c:when test="${not empty werknemer.naam}">
		<header>
			<h1>Werknemer ${werknemer.naam}</h1>
		</header>
		<section>
			<p>
				Voornaam<br /> <span class="bold">${werknemer.voornaam}</span>
			</p>
			<p>
				Familienaam<br /> <span class="bold">${werknemer.familienaam}</span>
			</p>
			<p>
				Email adres<br /> <span class="bold">${werknemer.email}</span>
			</p>
			<p>
				Salaris<br /> <span class="bold"><spring:eval expression="werknemer.salaris"/>&nbsp;&euro;</span>
			</p>
			<p>
				Jobtitel<br /> <span class="bold">${werknemer.jobtitel.naam}</span>
			</p>
			<c:if test="${not empty werknemer.chef}">
				<p>
					Chef<br />  
					<spring:url value='/werknemers/{id}' var='url'>
						<spring:param name='id' value='${werknemer.chef.id}' />
					</spring:url>
					<span class="bold">
						<a href="${url}"
							title="${werknemer.chef.naam}">${werknemer.chef.naam}
						</a>
					</span>
					
				</p>
			</c:if>
			<c:if test="${not empty werknemer.werknemers}">
				<p>
					Ondergeschikten<br />
					<c:forEach var="werknemer" items="${werknemer.werknemers}">
						<spring:url value='/werknemers/{id}' var='url'>
							<spring:param name='id' value='${werknemer.id}' />
						</spring:url>
						<span class="bold">
							<a href="${url}"
								title="${werknemer.naam}">${werknemer.naam}
							</a>
						</span>
						<br />
					</c:forEach>
				</p>
			</c:if>
			<p>
				Foto<br /> <img src="images/pasfotoswerknemers/${werknemer.id}.jpg"
					alt="${werknemer.naam}" title="${werknemer.naam}"/>
			</p>
			<spring:url value="/werknemers/{id}/opslag" var="opslagURL">
				<spring:param name="id" value="${werknemer.id}"/>
			</spring:url>
			<p><a href="${opslagURL}">Opslag</a></p>
		</section>
	</c:when> 
	<c:otherwise>
		<br>
		<div class="fouten">Werknemer niet gevonden!</div> 
	</c:otherwise>
	 </c:choose>
</body>
</html>