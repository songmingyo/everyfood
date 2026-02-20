<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<script type="text/javascript">
$(document).ready(function(){$('#logoutForm').submit();});
</script>
<sec:authorize access="isAuthenticated()">
<c:choose>
	<c:when test="${empty oldAuth}">
		<form id="logoutForm" action="<c:url value='/security/securitySignout'/>" method="post" style="display:none">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</c:when>
	<c:otherwise>
		<form id="logoutForm" action="<c:url value='/app/common/securitySignOut'/>" method="post" style="display:none">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</c:otherwise>
</c:choose>
</sec:authorize>