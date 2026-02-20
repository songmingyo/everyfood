<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"  %>
<%@ taglib prefix="page"      uri="http://www.opensymphony.com/sitemesh/page" 		%>

<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags" 			%>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form"	 	%>
<%@ taglib prefix="fmt"       uri="http://java.sun.com/jsp/jstl/fmt" 				%>
<%@ taglib prefix="c" 		  uri="http://java.sun.com/jsp/jstl/core"				%>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" 			%>
<%@ taglib prefix="html" 	  uri="/WEB-INF/tlds/custom-html.tld"  					%>

<%@ taglib prefix="sec" 	  uri="http://www.springframework.org/security/tags" 	%>
<%@ taglib prefix="util" 	  uri="/WEB-INF/tlds/util.tld"  %>
<%@ page import="java.util.*" 				%>
<%@ page import="org.apache.commons.lang.*"	%>

<jsp:useBean id="now" class="java.util.Date"/>
<sec:authorize access="isAuthenticated()">
	<sec:authentication var="userSession" property="principal" />
</sec:authorize>	


<c:choose>
	<c:when test="${pageContext.response.locale eq 'ko'}">
		<c:set var="localeDatePattern" value="yyyy-MM-dd" scope="page"/>
		<c:set var="localeDateTimePattern" value="yyyy-MM-dd HH:mm:ss" scope="page"/>
	</c:when>
	<c:otherwise>
		<c:set var="localeDatePattern" value="dd-MM-yyyy"  scope="page"/>
		<c:set var="localeDateTimePattern" value="HH:mm:ss dd-MM-yyyy" scope="page"/>
	</c:otherwise>
</c:choose>


<fmt:setLocale value="${pageContext.response.locale}" scope="page"/>
