<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<spring:eval expression="@config['system.url']"      var="webhome" />
<head>
	<link rel="shortcut icon" type="image/x-icon" href="<c:out value="${webhome}"/>/resources/favicon.ico" />
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<sec:csrfMetaTags/>

	<title><decorator:title default="KOREA R.M.C" /></title>
	<!-- Bootstrap 3.3.4 -->
    <link href="<c:url value="/resources/almasaeed/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="<c:url value="/resources/almasaeed/dist/css/AdminLTE.css"/>" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="<c:url value="/resources/almasaeed/plugins/iCheck/square/blue.css"/>" rel="stylesheet" type="text/css" />

	<!-- css -->
	<link rel="stylesheet" href="<c:url value="/resources/css/pearl/business/security.css"/>">
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

   <!-- jQuery 2.1.4 -->
    <script src="<c:url value="/resources/almasaeed/plugins/jQuery/jQuery-2.1.4.min.js"/>"></script>
	<!-- jquery UI -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery-ui-1.12.1.js"/>"></script>

    <!-- Bootstrap 3.3.2 JS -->
    <script src="<c:url value="/resources/almasaeed/bootstrap/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="<c:url value="/resources/almasaeed/plugins/iCheck/icheck.min.js"/>"  type="text/javascript"></script>
    <script src="<c:url value="/resources/js/pearl/common/commonUtil.js"/>" type="text/javascript" ></script>

    <script src="<c:url value='/resources/almasaeed/plugins/datepicker/bootstrap-datepicker.js'/>" type="text/javascript"></script>
 	<script src="<c:url value='/resources/almasaeed/plugins/datepicker/locales/bootstrap-datepicker.${pageContext.response.locale}.js'/>"></script>
    <link href="<c:url value="/resources/almasaeed/plugins/datepicker/datepicker3.css"/>" rel="stylesheet" type="text/css" />
    
    
	<script src="<c:url value="/resources/plugins/jQuery/validate/jquery.validate.min.js"/>" 		type="text/javascript"></script>
	<script src="<c:url value="/resources/plugins/jQuery/validate/additional-methods.min.js"/>" 	type="text/javascript"></script>
	<script src='<c:url value="/resources/plugins/jQuery/validate/localization/messages_${pageContext.response.locale}.js"/>' type="text/javascript"></script>
	<script src="<c:url value="/resources/plugins/jQuery/validate/common-validator-options.js"/>" 	type="text/javascript"></script>
    
    
	<script>
		var __locale =  "<c:out value='${pageContext.response.locale}'/>";
		var __context = "<c:out value='${pageContext.request.contextPath}'/>";
	
		$(function () {
			// AJAX CSRF TOKEN INSERT
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$(document).ajaxSend(function(e, xhr, options) {
				xhr.setRequestHeader(header, token);
			});
		});
		
		$(document).ready(function($){
			// .Custom Validator ===================================
			$.validator.addMethod(	 // 공백만 입력하는 것을 비허용.
					"noSpace",
					function(value,element){
						return $.trim($(element).val()) == "" ? false : true;
					},"<spring:message code='app.common.ou.login.alert.blank2'/>");
			
			$.validator.addMethod(	// 비밀번호 - 포함할 수 없는 문자
					"notInc",
					function(value,element,param){
						var pwd = $(element).val();
						var notIncId = "";

						//공백포함 불가
						if(pwd.indexOf(" ")>-1){
							return false;
						}

						//아이디 또는 이메일 포함 불가
						if(param.indexOf("@")>-1){
							notIncId = param.split("@")[0];
						}else{
							notIncId = $.trim(param);
						}

						if(notIncId != "" && notIncId != null){
							if(pwd.indexOf(notIncId)>-1){
								return false;
							}
						}
						return true;
					},"<spring:message code='app.common.ou.login.alert.valid.pwd'/>");
			
			$.validator.addMethod(	// 비밀번호 - [공통] 정규식 체크
					"exprChkNew",
					function(value,element){
						if(!fnValidRegExp($(element).val(),REG_PTRN.PW_CHK_01)){return false;}
						return true;
					},"<spring:message code='app.common.ou.login.alert.valid.pwd3'/>");
			// .Custom Validator ===================================


		});
		
    </script>

	<decorator:head />

</head>

<body class="login-page" >
	<p id="opacityError" class="opacityError" style="display: none;"></p>
	<p id="opacity" class="opacity" style="display: none;"></p>
	<decorator:body />
</body>

</html>