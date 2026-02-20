<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<div id="lm_pc" class="lmOpen lmClose">
	<c:set var="topMenuTitleLocale" value="Untitled"/>
	<c:if test="${acessMenu.topMenuTitleLocale ne null and acessMenu.topMenuTitleLocale ne ''}">
		<c:set var="topMenuTitleLocale" value="${acessMenu.topMenuTitleLocale}"/>
	</c:if>
	<button class="bt-mn closed" id="hideLeft" >
		<span><c:out value="${topMenuTitleLocale}"/></span><em><i></i><i></i><i></i></em>
	</button>
	<ul id="menu_tree" class="snb">
		<c:set var="uplevel" value="0" />
		<c:forEach var="item" items="${leftMenuList}">
			<c:if test="${item.useYn eq 'Y' && item.hideYn eq 'N'}">
				<c:if test="${uplevel gt item.level}">
					<c:forEach begin="${item.level}" end="${uplevel-1}" step="1">
						<c:out value="</ul></li>" escapeXml="false" />
					</c:forEach>
				</c:if>
				<c:set var="titleLocale" value="Untitled"/>
				<c:if test="${item.titleLocale ne null and item.titleLocale ne ''}">
					<c:set var="titleLocale" value="${item.titleLocale}"/>
				</c:if>
				<c:set var="menuIconFmt" value="fas ${item.menuIcon}"/>
				<c:choose>
					<c:when test="${item.leaf lt 1}">
						<li <c:if test="${acessMenu.parentId eq item.menuId}">class="active"</c:if>>
						<a href="#" class="depth" >
							<span><i class="${menuIconFmt}"></i> <c:out value="${titleLocale}"/></span>
						</a>
						<c:if test="${acessMenu.parentId eq item.menuId}">
							<c:out value="<ul class='mdepth2' style='display:block;'>" escapeXml="false" />
						</c:if>
						<c:if test="${acessMenu.parentId ne item.menuId}">
							<c:out value="<ul class='mdepth2'>" escapeXml="false" />
						</c:if>
					</c:when>
					<c:otherwise>
						<li <c:if test="${acessMenu.menuId eq item.menuId}">class="active"</c:if>>
							<a href="#" onClick="_setNextUrl('<c:url value="${item.webPage}"/>','<c:out value="${item.menuId}"/>','<c:out value="${item.sysCode}"/>');">
								<span><i class="${menuIconFmt}"></i> <c:out value="${titleLocale}"/></span>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
				<c:set var="uplevel" value="${item.level}" />
			</c:if>
		</c:forEach>
		<c:if test="${uplevel gt 2}">
			<c:forEach begin="3" end="${uplevel}" step="1">
				<c:out value="</ul></li>" escapeXml="false" />
			</c:forEach>
		</c:if>
	</ul>

</div>
