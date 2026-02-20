<%@ page language="java" isELIgnored="false"	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/> 

<!DOCTYPE html>
<html>
<head>

<spring:eval expression="@config['Common.Decorator.Res.Menu.Type']" 	var="decoratorResMenuType"/>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript">
$(document).ready(function($){
	
	
	$('#left-logout').unbind().click(function()  { 
		$('#logoutForm').submit();
        return false;
	});
	
	
});	
</script>
</head>

<body>

<!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
          <div class="user-panel" style="height: 60px;">
            
            <div class="pull-left info" style="margin-left: -50px; margin-top: -5px;">
            	<p><sec:authentication property="principal.fullName"/></p>
            	<small><i class="fa fa-circle text-success"></i> <sec:authentication property="principal.username"/></small>
            </div>
            <div class="pull-right info" style="float: right; margin-left: 80px;">
            	<sec:authorize access="isAuthenticated()">
    				<button class="btn btn-block btn-danger" id="left-logout">Sign out</button>
				</sec:authorize>
			</div>
          </div>
          
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
 				<li class="header"><c:out value="${acessMenu.topMenuTitleLocale}" /></li>
 				<li class="active treeview">
            		<a href="<c:url value="/res/main/mainDashboard" />"> <i class="fa fa-dashboard"></i> <span>Dashboard</span></a>
           	</li>
      
            
        <c:set var="uplevel" value="0" />
		<c:forEach var="item" items="${myMenuList}">
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
				<c:set var="menuIconFmt" value="fa ${item.menuIcon}"/>
				<c:choose>
					<c:when test="${item.leaf lt 1}">
						<li <c:if test="${acessMenu.parentId eq item.menuId}">class="treeview"</c:if>>
						<a href="#" class="depth" >
							<i class="${menuIconFmt}"></i><c:out value="${titleLocale}"/>
							<i class="fa fa-angle-left pull-right"></i>
						</a>
						<c:if test="${acessMenu.parentId eq item.menuId}">
							<c:out value="<ul class='treeview-menu' style='display:block;'>" escapeXml="false" />
						</c:if>
						<c:if test="${acessMenu.parentId ne item.menuId}">
							<c:out value="<ul class='treeview-menu'>" escapeXml="false" />
						</c:if>
					</c:when>
					<c:otherwise>
						<li>
							<a href="#" onClick="_setNextUrl('<c:url value="${item.webPage}"/>','<c:out value="${item.menuId}"/>','<c:out value="${item.sysCode}"/>');">
								<i class="${menuIconFmt}"></i> <c:out value="${titleLocale}"/>
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
            
            
           <%--
            <c:choose>
		    	<c:when test="${(userSession.userType eq 'SA' or userSession.userType eq 'SU' )}">
		    		<li class="treeview">
		              <a href="#">
		                <i class="fa fa-table"></i> <span>Purchase Order</span>
		                <i class="fa fa-angle-left pull-right"></i>
		              </a>
		              <ul class="treeview-menu">
		                <li><a href="<c:url value="/app/res/ord/orderlist" />"><i class="fa fa-circle-o text-blue"></i> Order <small class="label pull-right bg-blue" id="ORD_TOTAL_CNT_M">0</small></a></li>
		                <li><a href="<c:url value="/app/res/ord/inPurchaseConfirm" />"><i class="fa fa-circle-o text-yellow"></i> Purchase confirm <small class="label pull-right bg-yellow" id="PURCON_TOTAL_CNT_M">0</small></a></li>
		                <li><a href="<c:url value="/app/res/ord/inSplySheet" />"><i class="fa fa-circle-o"></i> Sply Sheet List </a></li>
		              </ul>
		            </li>
		           
		    	</c:when>
		    	<c:otherwise>
		    		<li class="treeview">
		            	<a href="<c:url value="/app/res/po/splyord" />"><i class="fa fa-circle-o text-blue"></i> Order <small class="label pull-right bg-blue" id="ORD_TOTAL_CNT_M">0</small></a>
		            </li>
		    		<li>
		    		 	<a href="<c:url value="/app/res/po/ouPurchaseConfirm" />"><i class="fa fa-circle-o text-yellow"></i> Purchase confirm <small class="label pull-right bg-yellow" id="PURCON_TOTAL_CNT_M">0</small></a></li>
		            <li>
		             	<a href="<c:url value="/app/res/po/ouSplySheet" />"><i class="fa fa-circle-o"></i> Sply Sheet List </a>
		            </li>
		    	</c:otherwise>
		    </c:choose>
		     --%>
		  </ul>
          
        </section>
        <!-- /.sidebar -->
      </aside>
</body> 
</html>