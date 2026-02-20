<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" 	  uri="http://www.springframework.org/security/tags" 	%>

<script>
	function fileDownLoadSession(){
		alert('<spring:message code="app.common.ou.filedown.alert.a1"/>');<!--로그인 후 다운로드 가능합니다.-->
	}
</script>
<table id="fileDownLoad" class="type7">
     <c:if test="${not empty attachFileList }">
		<c:forEach items="${attachFileList}" var="list" varStatus="index" >
			<tr>
				<td>
					<img src="<c:url value='/resources/images/pearl/common/'/><c:out value="${list.fileTypeNm}"/>.gif"/>
					<sec:authorize access="isAuthenticated()">
						<input type="hidden" name="atchFileId" value="<c:out value='${list.atchFileId}'/>"/>
						<a href="<c:url value='/app/common/'/><c:out value="${list.atchFileId}"/>/<c:out value="${list.seq}"/>/fileDownload">
							<c:out value="${list. orgFileNm}"/>
						</a>
					</sec:authorize>
					<%-- 로그인하지 않은(권한없는) 사용자
					<sec:authorize access="isAnonymous()">
						<a href="javascript:void(0);" onclick="javascript:fileDownLoadSession();">
							<c:out value="${list. orgFileNm}"/>
						</a>
					</sec:authorize>
					--%>
						&nbsp;[<c:out value="${list.fileSizeKb}"/>&nbsp;KB]
				</td>
			</tr>
	   </c:forEach>
    </c:if>
</table>