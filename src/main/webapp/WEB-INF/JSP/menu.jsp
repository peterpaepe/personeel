<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<header>
	<nav>
		<ul>
			<li><a href="<c:url value='/werknemers'/>"><fmt:message key='werknemershiërarchie'/></a></li>
			<li><a href="<c:url value='/jobtitels'/>"><fmt:message key='jobtitels'/></a></li>
		</ul>
	</nav>
</header>