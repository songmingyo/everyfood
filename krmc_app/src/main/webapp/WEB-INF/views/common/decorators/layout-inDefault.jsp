<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">

<spring:eval expression="@config['Common.Decorator.Menu.Type']"		var="decoratorMenuType" />
<spring:eval expression="@config['Common.Decorator.Content.Fixed']"	var="decoratorContentFixed" />
<spring:eval expression="@config['Common.Decorator.Menu.Tabs']" var="decoratorMenuTabs" />

<sec:authorize access="isAuthenticated()">
	<spring:eval expression="userSession.userType"		var="userType" />
</sec:authorize>
<spring:eval expression="@config['system.url']"	var="webhome" />
<head>
	<link rel="shortcut icon" type="image/x-icon" href="<c:out value="${webhome}"/>/resources/favicon.ico" />
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<sec:csrfMetaTags/>

	<title><decorator:title default="KOREA R.M.C" /></title>

	<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jquery-3.6.0.min.js"/>"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/pearl/base.css"/>" /> 

	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/jsn/menu_layout_navy.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/jsn/menu_layout_custom.css"/>" />

	<link rel="stylesheet" href="<c:url value="/resources/css/pearl/business/menu.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/pearl/business/style.css"/>">
	<!-- css확장 -->
	<link rel="stylesheet" href="<c:url value="/resources/css/pearl/business/reset.css"/>">

	<script type="text/javascript" src="<c:url value="/resources/js/pearl/submain/content.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/pearl/submain/submain.js"/>"></script>

	<!-- left 메뉴 숨기기  -->
	<script type="text/javascript" src="<c:url value="/resources/js/pearl/common/content_fixed.js"/>"></script>
	
	<!--  jqGrid Utils -->
	<script type="text/javascript" src="<c:url value="/resources/js/pearl/common/commonGridFmtUtil.js"/>"></script>

	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/jQuery/css/1.10.1/jquery-ui-1.10.1.custom.min.css"/>" />

	<!-- Font Awesome -->
	<link rel="stylesheet" href="/resources/plugins/fontawesome-free/css/all.min.css"/>
	<link href="<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" />


	<c:choose>
		<c:when test="${decoratorMenuTabs eq 'Y'}">
			<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery.handler-tab.js"/>"></script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery.handler.js"/>"></script>
		</c:otherwise>
	</c:choose>

	<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery.blockUI.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jquery.tmpl.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/pearl/common/ui.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/pearl/common/commonUtil.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/pearl/common/treeMenu.js"/>"></script>

	<!--jqgrid  -->
	<link rel="stylesheet" type="text/css" media="screen" href="/resources/plugins/jqGrid/css/ui.jqgrid.min.css" />
	<script type="text/javascript" src="/resources/plugins/jqGrid/js/i18n/grid.locale-${pageContext.response.locale}.js" ></script>
	<script type="text/javascript" src="/resources/plugins/jqGrid/js/jquery.jqgrid.min.js"></script>
	<script type="text/javascript" src="/resources/plugins/jqGrid/js/grid.common.js"></script>
	
	
	<!-- jBox ToolTip -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/jBox/dist/jBox.all.min.js"/>"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/jBox/dist/jBox.all.min.css"/>"/>
	
	<!-- tooltipster -->
	<script src="/resources/js/tooltipster/tooltipster.bundle.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/resources/css/tooltipster/tooltipster.bundle.min.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/tooltipster/themes/tooltipster-sideTip-borderless.min.css" />
	

	<!-- jquery validate -->
	<script src="<c:url value="/resources/plugins/jQuery/validate/jquery.validate.min.js"/>" 		type="text/javascript"></script>
	<script src="<c:url value="/resources/plugins/jQuery/validate/additional-methods.min.js"/>" 	type="text/javascript"></script>
	<script src='<c:url value="/resources/plugins/jQuery/validate/localization/messages_${pageContext.response.locale}.js"/>' type="text/javascript"></script>
	<script src="<c:url value="/resources/plugins/jQuery/validate/common-validator-options.js"/>" 	type="text/javascript"></script>

	<script src="<c:url value="/resources/plugins/jQuery/jquery.ui.datepicker-${pageContext.response.locale}.js"/>" 	type="text/javascript"></script>

	<!-- jquery UI -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery-ui-1.12.1.js"/>"></script>

	<!-- validation -->
	<script src="/resources/js/numeral/numeral.min.js"></script>
	<script src="/resources/js/validation/jquery-validation-number-patch.js"></script>
	<script src="/resources/js/validation/jquery-validation-default-settings.js"></script>


	<!-- InputMask -->
	<script src="<c:url value="/resources/plugins/jQuery/input-mask/jquery.inputmask.js"/>" 				type="text/javascript"></script>
	<script src="<c:url value="/resources/plugins/jQuery/input-mask/jquery.inputmask.date.extensions.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/plugins/jQuery/input-mask/jquery.inputmask.extensions.js"/>" 		type="text/javascript"></script>

	<!-- jquery loadmask -->
	<link href="<c:url value="/resources/plugins/jQuery/css/jquery.loadmask.css"/>" rel="stylesheet" type="text/css" />
	<script type='text/javascript' src='<c:url value='/resources/plugins/jQuery/jquery.loadmask.js'/>'></script>
	
	<!-- charJS -->
	<script src="<c:url value="/resources/almasaeed/plugins/chartjs/Chart.bundle.js"/>" type="text/javascript"></script>

	<script>
		var __locale =  "<c:out value='${pageContext.response.locale}'/>";
		var decoratorMenuTabs = "<c:out value='${decoratorMenuTabs}'/>";
		var __context = "<c:out value='${pageContext.request.contextPath}'/>";

		//AJAX CSRF TOKEN INSERT
		$(function () {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$(document).ajaxSend(function(e, xhr, options) {
				xhr.setRequestHeader(header, token);
			});
		});

		$(document).ready(function($){
			$('title').text("KOREA R.M.C");
			
			$(".calendar").datepicker({ dateFormat: 'yy-mm-dd' });
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

			$('#top-logout').unbind().click(function()  {
				$('#logoutForm').submit();
				return false;
			});

			if(decoratorMenuTabs != 'N') {

				var element = document.getElementById('container');
				new ResizeSensor(element, function() {
					parent.tabResize(element.clientHeight);
				});

			}

			// Custom Validator ===================================
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

			$.validator.addMethod(	// 비밀번호 - 정규식 체크
					"exprChk",
					function(value,element){
						//특수문자, 영어, 숫자
						//영문,숫자,특수문자조합 {8,25}부분 최소,최대길이 설정
						var threechk_pattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;

						// 영어,숫자혼용
						var num = $(element).val().search(/[0-9]/g);
						var eng = $(element).val().search(/[a-z]/ig);

						//특수문자, 영어, 숫자 혼용 필수 //validation 사용하지 X
						//if(!threechk_pattern.test($(element).val())){return true;}
						//영어, 숫자 혼용 필수 (사용 안할 경우, true로 값 변경)
						if(num<0 || eng<0){
							return false;	//validation 사용
						}

						return true;
					},"<spring:message code='app.common.ou.login.alert.valid.pwd2'/>");

			// .Custom Validator ===================================

			$("#quickDic").draggable({
				handle: $("li")
			});


			/* $('input[type="radio"]').iCheck({
			      radioClass   : 'iradio_minimal-blue'
		    }); */

		});


		$(function(){
			/* gnb */
			$('.gnb .depth1').mouseenter(function(){
				if(!$(this).hasClass('this')){
					$('.gnb .depth1.this').find('.depth2').css('display','none');
				}
				$(this).siblings().removeClass('on');
				$(this).addClass('on');
			});
			$('.gnb .depth1').mouseleave(function(){
				$('.gnb .depth1.this').find('.depth2').css('display','flex');
				$(this).removeClass('on');
			});

			/* tab */
			$('.wrap_tab').each(function(){
				var btn = $(this).find('>.wrap_tab_btn .tab_btn');

				btn.click(function(){
					var cont = $(this).parent().siblings().find('.tab_cont'),
							i = $(this).index();

					$(this).siblings('.tab_btn').removeClass('on');
					$(this).addClass('on');
					cont.removeClass('on');
					cont.eq(i).addClass('on');
				});
			});

		});
	</script>

	<decorator:head />

</head>
	
<body style="min-width:1280px; background-color: #efefef; " >
<sec:authorize access="isAuthenticated()">
<form id="logoutForm" action="<c:url value="/security/securitySignout"/>" method="post" style="display:none">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</sec:authorize>
		<p id="opacityError" class="opacityError" style="display: none;"></p>
		<p id="opacity" class="opacity" style="display: none;"></p>
		<div id="wrapper" style="background-color: #fff;    border: 1px solid #e8e8e8; border-radius: 0.4rem;">
			<input type="hidden" id="decoratorMenuType" name="decoratorMenuType" value="${decoratorMenuType}">

			<page:apply-decorator name="layout-sub-inHeader" />

			<div id="container" style="height: 100vh;">
				<div id="aside" class="closed_aside" style="width:40px;">
					<page:apply-decorator 	name="layout-sub-inLeft" />
				</div>
				<div id="content" class="open_content" style="margin-left: 40px;">
					<decorator:body />
				</div>
			</div>
		</div>
		<div id="commonExceptionModalFade"   role="dialog" title="Exception"></div>

</body>
</html> 
