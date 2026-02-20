<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">

<spring:eval expression="@config['Common.Decorator.Menu.Type']"		var="decoratorMenuType" />
<spring:eval expression="@config['Common.Decorator.Content.Fixed']"	var="decoratorContentFixed" />
<spring:eval expression="@config['Common.Decorator.Menu.Tabs']"		var="decoratorMenuTabs" />
<spring:eval expression="@config['system.url']"      var="webhome" />

<sec:authorize access="isAuthenticated()">
	<spring:eval expression="userSession.userType"     var="userType" />
</sec:authorize>
<head>

	<%-- <link rel="icon" href="<c:out value="${webhome}"/>/resources/favicon-lottemart.png" sizes="36x36" /> --%>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<sec:csrfMetaTags/>
	<title><decorator:title default="KOREA R.M.C" /></title>

	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/pearl/base.css"/>" /> 
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/jsn/menu_layout_navy.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/jsn/menu_layout_custom.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/jQuery/css/1.10.1/jquery-ui-1.10.1.custom.min.css"/>" />


	<!-- Font Awesome Icons -->
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />

	<script src="<c:url value="/resources/almasaeed/plugins/jQuery/jQuery-2.1.4.min.js"/>"></script>
	
    <c:choose>
	   	<c:when test="${decoratorMenuTabs eq 'Y'}">
		 		<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery.handler.js"/>"></script><!-- 수정 -->
	   	</c:when>
	   	<c:otherwise>
   			<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery.handler.js"/>"></script>
	   	</c:otherwise>
	</c:choose>
	
	<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery.blockUI.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jquery.tmpl.js"/>"></script> 
	<script type="text/javascript" src="<c:url value="/resources/js/pearl/common/ui.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/pearl/common/commonUtil.js"/>"></script><!-- main js -->
	<script type="text/javascript" src="<c:url value="/resources/js/pearl/common/treeMenu.js"/>"></script>

	<!--jqgrid  -->
	<link rel="stylesheet" type="text/css" media="screen" href="/resources/plugins/jqGrid/css/ui.jqgrid.min.css" />
	<script type="text/javascript" src="/resources/plugins/jqGrid/js/i18n/grid.locale-${pageContext.response.locale}.js" ></script>
	<script type="text/javascript" src="/resources/plugins/jqGrid/js/jquery.jqgrid.min.js"></script>
	<script type="text/javascript" src="/resources/plugins/jqGrid/js/grid.common.js"></script>

    <!-- jquery validate -->
	<script src="<c:url value="/resources/plugins/jQuery/validate/jquery.validate.min.js"/>" 		type="text/javascript"></script>
	<script src="<c:url value="/resources/plugins/jQuery/validate/additional-methods.min.js"/>" 	type="text/javascript"></script>
	<script src='<c:url value="/resources/plugins/jQuery/validate/localization/messages_${pageContext.response.locale}.js"/>' type="text/javascript"></script>
	<script src="<c:url value="/resources/plugins/jQuery/validate/common-validator-options.js"/>" 	type="text/javascript"></script>

		<script src="<c:url value="/resources/plugins/jQuery/jquery.ui.datepicker-${pageContext.response.locale}.js"/>" 	type="text/javascript"></script>

    <!-- jquery UI -->
    <script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery-ui-1.12.1.js"/>"></script>

	<!-- jBox ToolTip -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/jBox/dist/jBox.all.min.js"/>"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/jBox/dist/jBox.all.min.css"/>"/>
	
	<!-- Font Awesome -->
	<link rel="stylesheet" href="/resources/plugins/fontawesome-free/css/all.min.css"/>
		
	<script type="text/javascript" src="<c:url value="/resources/js/pearl/submain/content.js"/>"></script>
	
	
	<!-- charJS -->
	<script src="<c:url value="/resources/almasaeed/plugins/chartjs/Chart.bundle.js"/>" type="text/javascript"></script>
	
	<!-- tooltipster -->
	<script src="/resources/js/tooltipster/tooltipster.bundle.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/resources/css/tooltipster/tooltipster.bundle.min.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/tooltipster/themes/tooltipster-sideTip-borderless.min.css" />
	<!-- validation -->
	<script src="/resources/js/numeral/numeral.min.js"></script>
	<script src="/resources/js/validation/jquery-validation-number-patch.js"></script>
	<script src="/resources/js/validation/jquery-validation-default-settings.js"></script>
	
	<!-- adminLte -->
	<!-- iCheck for checkboxes and radio inputs -->
	 <link href="<c:url value="/resources/almasaeed/plugins/iCheck/all.css"/>" 	  		rel="stylesheet" type="text/css" />
	<!-- Theme style -->
	 <%-- <link href="<c:url value="/resources/almasaeed/dist/css/AdminLTE.min.css"/>" rel="stylesheet" type="text/css" /> --%>
	<!-- AdminLTE Skins. Choose a skin from the css/skins
	     folder instead of downloading all of them to reduce the load. -->
	 <%-- <link href="<c:url value="/resources/almasaeed/dist/css/AdminLTE.min.css"/>" rel="stylesheet" type="text/css" /> --%>
	<!-- iCheck 1.0.1 -->
	<script src="<c:url value="/resources/almasaeed/plugins/iCheck/icheck.min.js"/>"  type="text/javascript"></script>
	<!-- AdminLTE for demo purposes -->
	 <script src="../../dist/js/demo.js" type="text/javascript"></script>
	<!-- css확장 -->
	<link rel="stylesheet" href="<c:url value="/resources/css/pearl/business/reset.css"/>">
	
	
		
	<!-- InputMask -->
	<script src="<c:url value="/resources/plugins/jQuery/input-mask/jquery.inputmask.js"/>" 				type="text/javascript"></script>
	<script src="<c:url value="/resources/plugins/jQuery/input-mask/jquery.inputmask.date.extensions.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/plugins/jQuery/input-mask/jquery.inputmask.extensions.js"/>" 		type="text/javascript"></script>

	
	<!-- jquery loadmask -->
	<link href="<c:url value="/resources/plugins/jQuery/css/jquery.loadmask.css"/>" rel="stylesheet" type="text/css" />
	<script type='text/javascript' src='<c:url value='/resources/plugins/jQuery/jquery.loadmask.js'/>'></script>
	
	
	<script>
		var __locale =  "<c:out value='${pageContext.response.locale}'/>";
		var __context = "<c:out value='${pageContext.request.contextPath}'/>";
		var decoratorMenuTabs = "<c:out value='${decoratorMenuTabs}'/>";
		
		//AJAX CSRF TOKEN INSERT
		$(function () {
				  var token = $("meta[name='_csrf']").attr("content");
				  var header = $("meta[name='_csrf_header']").attr("content");
				  $(document).ajaxSend(function(e, xhr, options) {
				    xhr.setRequestHeader(header, token);
				  });
			});

		$(document).ready(function($){
			
			  //마우스 클릭, 드래그 구분
			  var dragging = 0;
			  $(document).mousedown(function(){
				  dragging = 0;
				  $(document).mousemove(function(){
					  dragging = 1;
				  });
			  });
			  
			  $(".class").click(function(e){
				  e.preventDefault();
				  if(dragging == 0){
					  openStrDocPop();
				  }
				  else{
					  //드래그시
				  }
			  });

		});
	</script>
	<decorator:head />
</head>
<body>
	<sec:authorize access="isAuthenticated()">
			<c:set var="oldAuth" scope="page">
				<sec:authentication property="principal.oldAuth"/>
			</c:set>
	</sec:authorize>
			  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<p id="opacityError" class="opacityError" style="display: none;"></p>
	<p id="opacity" class="opacity" style="display: none;"></p>
	
	
<div id="wrapper">
	<div id="wrap">
		<section id="container" style="min-height:200vh;">
		
		<!-- main 컨텐츠 내용 -->
		<decorator:body/>
			
		
		<!-- 풋터 -->

		</section>

	</div>
	
</div>
	<div id="commonExceptionModalFade"   role="dialog"  title="Exception"></div>
</body>
</html> 
