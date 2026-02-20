<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<!-- header -->
<style>
</style>
<header id="header" class="them_01">
	<div id="gnbframeTop">
		<h1 class="logo white">
			<a href='<c:url value="/main/mainDashboard"/>'></a>
		</h1>
		<div class="h_util white">
			<sec:authorize access="isAuthenticated()">
				<c:choose>
					<c:when test="${userSession.userType == 'SA' || userSession.userType == 'SU'}">
						<p class="members" id="myInfo"><sec:authentication property="principal.fullName"/>(<sec:authentication property="principal.username"/>)</span></p>
					</c:when>
					<c:otherwise>
						<p class="members" id="myInfo"><sec:authentication property="principal.fullName"/>(<sec:authentication property="principal.username"/>)</span></p>
					</c:otherwise>
				</c:choose>
				<input type="button" id="top-logout" class="top-logout" value="로그아웃"/>
	<!-- 			<input type="button" class="all-menu-open"/> -->
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<input type="button" id="top-login" class="top-logout" value="로그인"/>
				<input type="button" id="top-joinus" class="top-logout" value="회원가입"/>
				<!-- <input type="button" class="all-menu-open"/> -->
			</sec:authorize>
		</div>
	</div>
	
	<!-- gnb -->
	<div id="gnbframe">
		<ul class="gnb main">
			<c:forEach var="item" items="${topMenuList}" varStatus="index">
				<c:set var="titleLocale" value="Untitled"/>
				<c:set var="sysMenuId" value="${item.menuId}"/>
				<c:if test="${item.menuId ne null and item.menuId ne ''}">
					<c:set var="titleLocale" value="${item.title}"/>
				</c:if>
				<!-- top menu -->
				<li class="menu<c:out value='${index.count}' /> depth1">
					<c:set var="chkTopMenu" value="0"/>
					<c:forEach var="fstmenu" items="${myMenuList }" varStatus="fstIdx">
						<c:if test='${chkTopMenu eq "0" }'>
							<c:if test="${sysMenuId eq fstmenu.parentId }">
								<c:set var="chkTopMenu" value="1"/>
								<a class="gnb_mtitle" data-menu="gnb_mtitle" href="javascript:;" onClick="_setNextUrl('<c:url value="${item.webPage}"/>','${item.childId}','');">
									<span><c:out value="${titleLocale}"/></span>
								</a>
							</c:if>
						</c:if>
					</c:forEach>
					<!-- sub menu : depth2 -->
					<div class="wrap_submenu">
						<ul class="depth2">
							<c:forEach var="data" items="${myMenuList}" varStatus="dataIndex">
								<c:if test="${sysMenuId eq data.parentId }">
						 			<c:if test='${data.useYn eq "Y" and data.hideYn ne "Y" }'>
						 				<c:set var="dataTitleLocale" value="${data.titleLocale}"/>
							 			<li>
							 				<c:choose>
							 					<c:when test='${data.leaf ne "0"}'>
							 						<a href="javascript:;" onClick="_setNextUrl('<c:url value="${data.webPage}"/>','${data.menuId}', '');">
									 					<c:out value='${dataTitleLocale }'/>
									 				</a>
							 					</c:when>
							 					<c:otherwise>
							 						<c:set var="chkSubChild" value="0"/>
							 						<c:forEach var="subChild" items="${myMenuList}" varStatus="subChildIdx">
							 							<c:if test='${chkSubChild eq "0" }'>
							 								<c:if test='${data.menuId eq subChild.parentId and subChild.level eq "3" and subChild.useYn eq "Y" and subChild.hideYn ne "Y"}'>
							 									<c:set var="chkSubChild" value="1"/>
							 									<a href="javascript:;" onClick="_setNextUrl('<c:url value="${subChild.webPage}"/>','${subChild.menuId}', '');">
							 										<c:out value='${dataTitleLocale }'/>
							 									</a>
								 							</c:if>
							 							</c:if>
							 						</c:forEach>
							 					</c:otherwise>
							 				</c:choose>
						 					<ul class="depth3">
						 						 <c:forEach var="dtlData" items="${myMenuList}" varStatus="dtlDataIdx">
						 						 	<c:if test='${data.menuId eq dtlData.parentId and dtlData.level eq "3"}'>
						 						 		<c:if test='${dtlData.useYn eq "Y" and dtlData.hideYn ne "Y" }'>
						 						 			<li>
						 						 				<a href="javascript:;" onClick="_setNextUrl('<c:url value="${dtlData.webPage}"/>','${dtlData.menuId}', '');">
						 						 					<c:out value='${dtlData.titleLocale }'/>
						 						 				</a>
						 						 			</li>
						 						 		</c:if>
						 						 	</c:if>
						 						 </c:forEach>
						 					</ul>
						 				</li>
						 			</c:if>
						 		</c:if>
							</c:forEach>
						</ul>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</header>