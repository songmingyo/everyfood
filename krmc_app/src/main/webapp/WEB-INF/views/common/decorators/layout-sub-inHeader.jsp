<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">

$(document).ready(function($){

	$('#top-logout').unbind().click(function()  {
		$('#logoutForm').submit();
        return false;
	});
	
	$('#top-login').unbind().click(function(){
		location.href = "<c:url value='/security/securityForm'/>";
	});
	
	$("#top-joinus").unbind().click(function(){
		location.href = "<c:url value='/security/securityJoinus'/>";
	});

	$("#btnHome").unbind().click(function(){
		location.href = "<c:url value='/main/mainDashboard'/>";
	});
	
	


});
</script>


<style type="text/css">
	

.headCon{ cursor:pointer; padding:0px; width: 150px;}	
/* .headCon h1{float: left;	margin-left: 30px; margin-right: 50px; margin-top: 10px;} */
.headCon h1{float: left;	 
margin-top: 10px; 
margin-left: 40px;
margin-right: 62px;
}
.gbn-content {
	border-bottom: 2px solid #e5e0e0;
	height:82px;
	margin-top: 10px;
}
	
	
.gbn-content .members:before{ content:''; display: inline-block; width: 19px; height: 19px; margin-right: 8px; background: url(/resources/images/pearl/main/main_icon_26.png) no-repeat center center; background-size: 70% auto; vertical-align: middle}
.gbn-content .gbn-content-btn{ text-align: right;   }
.gbn-content .gbn-content-btn span{color:#474550; font-size:0.9em; display: inline-flex; align-items: center; margin-top:10px; margin-right: 20px; margin-bottom: 0; letter-spacing: -0.5px;  }
.gbn-content .guest-join:before{content:'';   display: inline-block; width: 20px; height: 20px; margin-right: 2px; background: url(/resources/images/pearl/main/main_icon_23.png) no-repeat center center; background-size: 90%  auto; vertical-align: middle}
.gbn-content .guest-login:before{content:'';  display: inline-block; width: 20px; height: 20px; margin-right: 2px; background: url(/resources/images/pearl/main/main_icon_24.png) no-repeat center center; background-size: 70% auto;   vertical-align: middle}
.gbn-content .guest-logout:before{content:''; display: inline-block; width: 20px; height: 20px; margin-right: 2px; background: url(/resources/images/pearl/main/main_icon_25.png) no-repeat center center; background-size: 70% auto;   vertical-align: middle}


/* gnb */
#gnbframe { z-index:1000; background-color: #fff; }
#gnbframe .gnb{ margin: 0 auto;}
#gnbframe a{letter-spacing: -1px;  text-decoration: none; color: #333}
#gnbframe .depth3 a:hover{font-weight: 600; color: #b22222;}
#gnbframe .depth1{display: inline-block}
#gnbframe .depth1+.depth1{margin-left: 20px}
#gnbframe .depth1 > a{position: relative; display: inline-block; padding: 0px 13px 26px; font-size: 18px; font-weight: 600}
#gnbframe .depth1 > a:after{content:"";width: 100%;height:3px;position: absolute;left: 0;bottom:-1px;display: block;background-color:#b22222; -webkit-transform: scaleX(0);-moz-transform: scaleX(0);-ms-transform: scaleX(0);-o-transform: scaleX(0);transform: scaleX(0);-webkit-transition: transform .35s ease;-moz-transition: transform .35s ease; -o-transition: transform .35s ease;-ms-transition: transform .35s ease;z-index:10;}
#gnbframe .depth1 > a:hover:after, #gnbframe .depth1.this > a:after, #gnbframe .depth1.on > a:after{-webkit-transform: scaleX(1);-moz-transform: scaleX(1);-ms-transform: scaleX(1);-o-transform: scaleX(1);transform: scaleX(1)}
#gnbframe .depth2{display: flex; justify-content: center; align-items: center}
#gnbframe .depth2 > li{width: 220px; line-height: 53px; text-align: center}
#gnbframe .gnb:not(.main) .depth2 > li > a{color: #fff}
#gnbframe .depth2 > li > a{font-size: 16px; font-weight: 600}
#gnbframe .wrap_submenu{position: absolute; left: 0; width: 100%; margin-top: 1px; z-index: 1000}
#gnbframe .wrap_depth3{ position: relative; display: flex; justify-content: center; align-items: stretch; border-top: 1px solid #edebee; background: #fff}
#gnbframe .wrap_depth3:after{content: '';display: block;width: 100%;height: 15px;box-shadow: 0 6px 8px 0 #bbb;position: absolute;bottom: 0;}
#gnbframe .wrap_depth3 > *{border-right: 1px solid #edebee;}
#gnbframe .wrap_depth3 .tit_depth1{position: absolute; left: 50%; width: 330px; height: 100%; padding: 25px 22px; letter-spacing: -1px; font-size: 14px; font-weight: 600; transform: translateX(-770px);}
#gnbframe .depth3{ width: 220px; padding: 25px 27px; text-align: left; font-size: 14px;}
#gnbframe .depth3 li+li{margin-top: 15px; }
#gnbframe .depth1 .wrap_depth3 .tit_depth1{background-color: transparent; background-repeat: no-repeat; background-position: right bottom}
#gnbframe .wrap_submenu, #gnbframe :not(.main) .depth1 .wrap_submenu .wrap_depth3{display: none}
#gnbframe .depth1:hover .wrap_submenu, #gnbframe .gnb:not(.main) .depth1.this .wrap_submenu, #gnbframe .gnb:not(.main) .depth1:hover .wrap_submenu{display: block}
#gnbframe .gnb:not(.main) .depth1.on .wrap_submenu .depth2, #gnbframe .gnb:not(.main) .depth1:hover .wrap_submenu .depth2, #gnbframe :not(.main) .depth1:hover .wrap_submenu .wrap_depth3{display: flex}
#gnbframe .main .wrap_submenu .depth2{background: #fff}

</style>
	
<!--Content Head -->
<div class="main-gbn">
			<div class="headCon" id="btnHome"><h1><img src="/resources/images/pearl/main/logo.png" width="130px;"></h1></div>
			<nav class="gbn-content"> 
			
				<div class="gbn-content-btn"> 
					<sec:authorize access="isAuthenticated()">
						
						<c:choose>
							<c:when test="${userSession.userType == 'SA' || userSession.userType == 'SU'}">
								<span class="members" id="myInfo" style="cursor:pointer"><sec:authentication property="principal.fullName"/>(<sec:authentication property="principal.username"/>)</span>
							</c:when>
							<c:otherwise>
								<span class="members" id="myInfo" style="cursor:pointer"><sec:authentication property="principal.fullName"/>(<sec:authentication property="principal.username"/>)</span>
							</c:otherwise>
						</c:choose>
						
						<span class="guest-logout"  id="top-logout" style="cursor:pointer"> 로그아웃 </span>
					</sec:authorize>
					<sec:authorize access="isAnonymous()">
						<span class="guest-login"  id="top-login" style="cursor:pointer">로그인 </span>
						<span class="guest-join"   id="top-joinus" style="cursor:pointer">회원가입 </span>
					</sec:authorize>
				</div>
	
				
				<!-- gnb --><!-- 스크립트 필요 -->
		<div id="gnbframe">

			<ul class="gnb main"><!-- 메인 페이지에는 class="main"이 붙음 -->
			<c:forEach var="item" items="${topMenuList}" varStatus="index">
				<c:set var="titleLocale" value="Untitled"/>
				<c:set var="sysMenuId" value="${item.menuId}"/>
				<c:if test="${item.menuId ne null and item.menuId ne ''}">
					<c:set var="titleLocale" value="${item.title}"/>
				</c:if>
				<li class="menu<c:out value='${index.count}' /> depth1"><!-- 현재페이지에는 class="this"가 붙음 -->
					<c:choose>
						<c:when test="${decoratorMenuTabs eq 'Y'}">
							<a class="gnb_mtitle" href="#" onClick="_setMenuSettion('<c:url value="${item.webPage}"/>','${item.childId}','${item.childTitleLocale}','${item.menuId}','${item.sysCode}');">
						</c:when>
						<c:otherwise>
							<a class="gnb_mtitle" href="#" onClick="_setNextUrl('<c:url value="${item.webPage}"/>','${item.childId}','${item.sysCode}');">
						</c:otherwise>
					</c:choose>
						<span>
							<%-- data-menu="gnb_mtitle" --%>
							<c:out value="${titleLocale}"/>
						</span>
					</a>
					<div class="wrap_submenu">
				
					<ul class="depth2">
						<!-- 시스템코드가 동일한 것중에 1레벨만 -->
						<c:forEach var="data" items="${myMenuList}" varStatus="dataIndex">
							<c:if test="${sysMenuId eq data.parentId and data.level eq 2}" >
								<c:set var="dataTitleLocale" value="${data.title}"/>
								<c:if test="${data.useYn eq 'Y' && data.hideYn eq 'N'}">
									<li>
										<c:if test="${data.leaf ne '0'}">
											<a href="#" data-menu="depth2" onClick="_setNextUrl('<c:url value="${data.webPage}"/>','${data.menuId}','${data.sysCode}');">
												<c:out value="${dataTitleLocale}" />
											</a>
										</c:if>
										<c:if test="${data.leaf eq '0'}">
											<a href="#" data-menu="depth2">
												<c:out value="${dataTitleLocale}" />
											</a>
										</c:if>
									</li>
								</c:if>
							</c:if>
						</c:forEach>
					</ul>

					<div class="wrap_depth3">
						<c:forEach var="data" items="${myMenuList}" varStatus="dataIndex">
							<c:if test="${sysMenuId eq data.parentId and data.level eq 2}" >
								<c:if test="${data.useYn eq 'Y' && data.hideYn eq 'N'}">
									<ul class="depth3">
										 <c:if test="${data.leaf ne '0'}">
											<a href="#" data-menu="depth2" onClick="_setNextUrl('<c:url value="${data.webPage}"/>','${data.menuId}','${data.sysCode}');">
												<c:out value="${data.title}" />
											</a>
										</c:if> 
										<c:if test="${data.leaf eq '0'}">
											<c:set var="topSysCode" 		value="${data.menuId}"/>
											
											<c:forEach var="subData" items="${myMenuList}" varStatus="subDataIndex">
												<c:if test="${subData.leaf ne '0' and topSysCode eq subData.parentId }">
													<c:set var="subDataTitleLocale" value="${subData.title}"/>
													<c:if test="${subData.useYn eq 'Y' && subData.hideYn eq 'N'}">
														<li>
															<a href="#" data-menu="depth2" onClick="_setNextUrl('<c:url value="${subData.webPage}"/>','${subData.menuId}','${subData.sysCode}');">
																<c:out value="${subDataTitleLocale}" />
															</a>
														</li>
													</c:if>
												</c:if>
											</c:forEach>
										</c:if>
									</ul>
								</c:if>
							</c:if>
						</c:forEach>
					</div>
				</div>
				</li>
				</c:forEach>
				
			</ul>
		</div>
		<!-- // gnb -->
		</nav>
			
	</div>

	