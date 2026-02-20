<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">

<spring:eval expression="@config['Common.Decorator.Res.Menu.Type']" 	var="decoratorResMenuType"/>

<sec:authorize access="isAuthenticated()">
	<%--
	<spring:eval expression="userSession.userMenuType" var="userMenuType" />
	<spring:eval expression="userSession.userMenuTab"  var="userMenuTabs" />
	 --%>
	<spring:eval expression="userSession.userType"     var="userType" />
</sec:authorize>

<spring:eval expression="@config['system.url.edi']"      var="webhome" />
<head>

	<link rel="icon" href="<c:out value="${webhome}"/>/resources/favicon-lottemart.png" sizes="36x36" />
	 <sec:csrfMetaTags/>
     <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
     <title><decorator:title default="KOREA R.M.C" /></title>


     <!-- jQuery 2.1.4 -->
     <script src="<c:url value="/resources/almasaeed/plugins/jQuery/jQuery-2.1.4.min.js"/>"></script>


    <!-- jQuery UI 1.11.2 -->
    <script src="http://code.jquery.com/ui/1.11.2/jquery-ui.min.js" type="text/javascript"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>
      $.widget.bridge('uibutton', $.ui.button);
    </script>

    <script type="text/javascript" src="<c:url value="/resources/js/pearl/common/commonUtil.js"/>"></script>


<!-- jquery validate -->
	<script src="<c:url value="/resources/plugins/jQuery/validate/jquery.validate.min.js"/>" 		type="text/javascript"></script>
	<script src="<c:url value="/resources/plugins/jQuery/validate/additional-methods.min.js"/>" 	type="text/javascript"></script>
	<script src='<c:url value="/resources/plugins/jQuery/validate/localization/messages_${pageContext.response.locale}.js"/>' type="text/javascript"></script>
	<script src="<c:url value="/resources/plugins/jQuery/validate/common-validator-options.js"/>" 	type="text/javascript"></script>


	<!-- Ion Slider -->
	<link href="<c:url value="/resources/almasaeed/plugins/ionslider/ion.rangeSlider.css"/>" 			rel="stylesheet" type="text/css" />
	<!-- ion slider Nice -->
	<link href="<c:url value="/resources/almasaeed/plugins/ionslider/ion.rangeSlider.skinNice.css"/>" 	rel="stylesheet" type="text/css" />
	
    <!-- iCheck -->
    <link href="<c:url value="/resources/almasaeed/plugins/iCheck/flat/blue.css"/>" 		rel="stylesheet" type="text/css" />
    <link href="<c:url value="/resources/almasaeed/plugins/iCheck/all.css"/>" 	  		rel="stylesheet" type="text/css" />
    <link href="<c:url value="/resources/almasaeed/dist/css/skins/_all-skins.min.css"/>" rel="stylesheet" type="text/css" />

    <!-- Morris chart -->
    <link href="<c:url value="/resources/almasaeed/plugins/morris/morris.css"/>" rel="stylesheet" type="text/css" />
    <!-- Date Picker -->
    <script src="<c:url value='/resources/almasaeed/plugins/datepicker/bootstrap-datepicker.js'/>" type="text/javascript"></script>
    <link href="<c:url value="/resources/almasaeed/plugins/datepicker/datepicker3.css"/>" rel="stylesheet" type="text/css" />
    <script src="<c:url value='/resources/almasaeed/plugins/datepicker/locales/bootstrap-datepicker.${pageContext.response.locale}.js'/>"></script>
    <!-- Daterange picker -->
    <link href="<c:url value="/resources/almasaeed/plugins/daterangepicker/daterangepicker-bs3.css"/>" rel="stylesheet" type="text/css" />
    <!-- bootstrap wysihtml5 - text editor -->
    <link href="<c:url value="/resources/almasaeed/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"/>" rel="stylesheet" type="text/css" />


	<!-- date-range-picker -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js" type="text/javascript"></script>
    <script src="<c:url value="/resources/almasaeed/plugins/daterangepicker/daterangepicker.js"/>" type="text/javascript"></script>
    
    <!-- DATA TABES SCRIPT -->
    <script src="<c:url value="/resources/almasaeed/plugins/datatables/jquery.dataTables.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/almasaeed/plugins/datatables/dataTables.bootstrap.min.js"/>" type="text/javascript"></script>
    <link href="<c:url value="/resources/almasaeed/plugins/datatables/dataTables.bootstrap.css"/>" rel="stylesheet" type="text/css" />


    <!-- Bootstrap 3.3.4 -->
    <link href="<c:url value="/resources/almasaeed/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="<c:url value="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- jvectormap -->
    <link href="<c:url value="/resources/almasaeed/plugins/jvectormap/jquery-jvectormap-1.2.2.css"/>" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="<c:url value="/resources/almasaeed/dist/css/AdminLTE.css"/>" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link href="<c:url value="/resources/almasaeed/dist/css/skins/_all-skins.min.css"/>" rel="stylesheet" type="text/css" />


<!-- iCheck 1.0.1 -->
    <script src="<c:url value="/resources/almasaeed/plugins/iCheck/icheck.min.js"/>" type="text/javascript"></script>

    <spring:eval expression="@config['Web.SocketPath.Url']" 	var="websockethome"/>
    <input type="hidden" id="websockethome" name="websockethome" value="${websockethome}" />
    <script src="<c:url value="/resources/js/pearl/common/websocket-common.js"/>" type="text/javascript"></script>

	<script src="<c:url value="/resources/plugins/sockjs/sockjs-0.3.4.js"/>" type="text/javascript"></script>


    <script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jquery.tmpl.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery.handler.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery.blockUI.js"/>"></script>


	 <!-- Ion Slider -->
	<script src="<c:url value="/resources/almasaeed/plugins/ionslider/ion.rangeSlider.min.js"/>" type="text/javascript"></script>
	<!-- Bootstrap slider -->
	<script src="<c:url value="/resources/almasaeed/plugins/bootstrap-slider/bootstrap-slider.js"/>" type="text/javascript"></script>



	<decorator:head />

<style type="text/css">
.thead_dark{
	background-color: #6d798f;
	color: #fff;
	text-align: center !important;
}

.thead_gray{
	background-color: #efefef;
	color: #6d798f;
	text-align: center !important;
}

.no-sort::after { display: none!important; }
.no-sort { pointer-events: none!important; cursor: default!important; }

.skin-black .main-header>.logo {background-color:#1a2226; border-right: 1px solid #1a2226;}
.skin-black .main-header>.logo:hover{ background-color:#1a2226;}
</style>

</head>
<script type="text/javascript">

	$.fn.datepicker.defaults.language = "<c:out value='${pageContext.response.locale}'/>";

	//AJAX CSRF TOKEN INSERT
	$(function () {
		  var token = $("meta[name='_csrf']").attr("content");
		  var header = $("meta[name='_csrf_header']").attr("content");
		  $(document).ajaxSend(function(e, xhr, options) {
		    xhr.setRequestHeader(header, token);
		  });
		  
		  

		//iCheck for checkbox and radio inputs
	    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
	      checkboxClass: 'icheckbox_minimal-blue',
	      radioClass: 'iradio_minimal-blue'
	    });
	    //Red color scheme for iCheck
	    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
	      checkboxClass: 'icheckbox_minimal-red',
	      radioClass: 'iradio_minimal-red'
	    });
	    //Flat red color scheme for iCheck
	    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
	      checkboxClass: 'icheckbox_flat-green',
	      radioClass: 'iradio_flat-green'
	    });
		    
	});

	
	
</script>

<body class="sidebar-mini skin-black sidebar-collapse">

	<div class="wrapper">
		<page:apply-decorator 	name="incResHeader"/>
		<page:apply-decorator 	name="incResMenu"/> 
		<decorator:body />

		<page:apply-decorator 	name="incResFooter"	/>
	<%--	<page:apply-decorator 	name="incResSidebar"	/>   --%>

	</div><!-- ./wrapper -->

	<form id="logoutForm" action="<c:url value="/security/securitySignout"/>" method="post" style="display:none">
    	<sec:csrfInput/>
	</form>

    <!-- Bootstrap 3.3.2 JS -->
    <script src="<c:url value="/resources/almasaeed/bootstrap/js/bootstrap.min.js"/>" type="text/javascript"></script>

    <!-- AdminLTE App -->
    <script src="<c:url value="/resources/almasaeed/dist/js/app.min.js"/>" type="text/javascript"></script>


</body>

</html>





