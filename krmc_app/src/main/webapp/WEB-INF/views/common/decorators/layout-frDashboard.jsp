<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<spring:eval expression="@config['system.url']"      var="webhome" />
<!DOCTYPE HTML>

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>

	<link rel="shortcut icon" type="image/x-icon" href="<c:out value="${webhome}"/>/resources/favicon.ico" />
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<sec:csrfMetaTags/>
	<title><decorator:title default="KOREA R.M.C" /></title>



	
	
	
	<!-- Font Awesome -->
	<link rel="stylesheet" href="/resources/plugins/fontawesome-free/css/all.min.css"/>
	<link href="<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" />
	
	<script src="<c:url value="/resources/plugins/jQuery/jquery-3.6.0.min.js"/>"></script><!-- jquery -->
	<script type="text/javascript" src="<c:url value="/resources/js/pearl/common/commonUtil.js"/>"></script><!-- main js -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery-ui-1.12.1.js"/>"></script><!-- jQuery-ui js -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/validate/jquery.validate.js"/>"></script><!-- jQuery-validate js -->
	<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jquery.tmpl.js"/>"></script>
	
	<!-- DATA TABES SCRIPT -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.css"/>" /> 
    <script src="<c:url value="/resources/plugins/datatables/jquery.dataTables.min.js"/>" 		type="text/javascript"></script>
    <script src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap.min.js"/>" 	type="text/javascript"></script>
   
    
	<script>
		var __locale =  "${pageContext.response.locale}";

		//AJAX CSRF TOKEN INSERT
		$(function () {
			  var token = $("meta[name='_csrf']").attr("content");
			  var header = $("meta[name='_csrf_header']").attr("content");
			  $(document).ajaxSend(function(e, xhr, options) {
			    xhr.setRequestHeader(header, token);
			  });
		});

		$(document).ready(function($){


			

		});

	
	</script>
	

<style type="text/css">
		@charset "utf-8";
	
		.notices *{margin: 0; padding: 0; box-sizing: border-box;}
		a{text-decoration: none; color: inherit}
			
		#wrapper{ margin:10px;  background-color: #fff; border: 1px solid #e8e8e8; border-radius: 0.4rem;}
		
		/* #wrapper{ max-width:1500px;  margin:10px  auto; background-color: #fff; border: 1px solid #e8e8e8; border-radius: 0.4rem;}  */
		
			
		*:before, *:after{box-sizing: border-box}
		/* @import url('https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800'); */
		 
		html, body, div, applet, object, iframe,h1, h2, h3, h4, h5, h6, p, blockquote, pre,a, abbr, acronym, address, big, cite, code,del, dfn, em, img, ins, kbd, q, s, samp,small, strike, sub, sup, tt, var,b, u, i, center,dl, dt, dd, ol, ul, li,fieldset, form, label, legend,table, caption, tbody, tfoot, thead, tr, th, td,article, aside, canvas, details, embed, figure, figcaption, footer, header, hgroup, menu, nav, output, ruby, section, summary,time, mark, audio, video {
			border: 0;font-size: 100%;font: inherit;vertical-align: baseline;
		}
		
		legend, caption {overflow:hidden;width:1px;height:1px;color:transparent;}
		input, textarea{ font-family: "Nanum Gothic", "나눔 고딕" , Dotum, "돋움" , sans-serif;} /* only iOS */
		button{background:transparent; border:0; outline:none !important;}
		select{font-family: 'Noto Sans KR';}
		em{font-weight:500}
		strong{font-weight:700}
		
		
		*{margin: 0; padding: 0; box-sizing: border-box;}
		a{text-decoration: none; color: inherit}
		ol, ul{list-style: none}

		/* common style */
		.hide{font-size: 0 !important; visibility: hidden !important; color: transparent !important}
		
		/* footer */
		#footer{padding: 35px 31px 32px; border-top: 1px solid #e0e0e0; background: #fff;}
		#footer:after{content:''; display: block; clear: both}
		#footer :not(.copyright){letter-spacing: -1px;}
		#footer .copyright{float: right; margin-bottom: 0; letter-spacing: -0.5px;}
		#footer > *, #footer a{font-size: 13px; color: #999}
		#footer .f_menu{float: left; margin-bottom: 0}
		#footer .f_menu li{display: inline-block; line-height:1}
		#footer .f_menu li + li{border-left: 1px solid #999; margin-left: 10px; padding-left: 10px}
		
		

		
		
	/*	.visual { border-top: 1px solid #cecece; border-bottom: 1px solid #e4e4e4; clear:both; background:url('/resources/images/pearl/main/visual_img_01.jpg') center top no-repeat;  width:100%; height:300px; background-size : cover; position:relative;} */
	
		.visual { border-top: 1px solid #9c9c9a;   border-bottom: 1px solid #bebebe; clear:both; background:url('/resources/images/pearl/main/visual_img_05.jpeg') center top no-repeat;  width:100%; height:300px; background-size: 100%  100%;   position:relative;} 
		.visual .visual_con {width:100%;  display: flex; justify-content: left; margin:0 auto; height:100%; }
		.visual .visual_con .main_ing{justify-content: right; margin:0 auto; }
		.visual .visual_con p.visual_text { text-align:center; color:white; letter-spacing:-2px; font-family:'NGBold'; font-size:44px; padding-top:95px; }
		.visual .visual_con p.visual_text span { font-size:34px;font-family:'NGNormal'; display:block; margin-top:-10px; }
		.visual .visual_con .mypage_ing ul:after { display:block; content:""; clear:both; }
		.visual .visual_con .mypage_ing ul { background:white; letter-spacing:-1px; margin-top:42px;}
		.visual .visual_con .mypage_ing li {background:url('/images/app/mainImg/division_1.gif') right center no-repeat; float:left; color:#111111; font-size:15px; height:140px; width:15.666666%; text-align:center; padding-top:15px; box-sizing:border-box; }
		.visual .visual_con .mypage_ing li.width25 {background:url('/images/app/mainImg/division_1.gif') right center no-repeat; float:left; color:#111111; font-size:15px; height:140px; width:20.4559%; text-align:center; padding-top:15px; box-sizing:border-box; }
		.visual .visual_con .mypage_ing li.width20 {background:url('/images/app/mainImg/division_1.gif') right center no-repeat; float:left; color:#111111; font-size:15px; height:140px; width:16.3%; text-align:center; padding-top:15px; box-sizing:border-box; }
		.visual .visual_con .mypage_ing li:nth-child(1) { font-size:20px; color:#111111;font-family:'NGBold'; width:18%; text-align:left; padding:23px; box-sizing:border-box;}
		.visual .visual_con .mypage_ing li:nth-child(1) span { font-size: 15px;font-family:'NGNormal'; }
		.visual .visual_con .mypage_ing li.title { font-size:25px; color:#000080;font-family:'NGBold'; width:18%; text-align:center; padding:23px; box-sizing:border-box; margin-bottom: 20px;}
		.visual .visual_con .mypage_ing li span { font-size:34px; color:#666666; display:block;font-family:'NGBold';  }
		.visual .visual_con .mypage_ing li span.red { color:#e51937; }
		.visual .visual_con .mypage_ing li em { font-size:14px; color:#666666; display:block }
		.visual .visual_con .mypage_ing li em u { color:#e51937;text-decoration: none; }
		
		
		.visual .visual_con p.visual_text_member {  color:#f0f0f0; letter-spacing:0px;  font-size:14px;  margin-top: 50px;		}
		.visual .visual_con p.visual_text_member span {margin-left:5px; font-size:16px;  color: #efefef; font-weight: bold; }
		.visual .visual_con .main_ing .ul_main_ing { letter-spacing:-1px; margin-top:0px;   								}
		.visual .visual_con .main_ing .ul_main_ing li { list-style-type: none; float: left;    margin-bottom: -3px;     	}
		
		.visual .visual_con .main_ing .li_main_ing { width:23%; margin:0 3px 0 0; float:left; 								}
		.visual .visual_con .main_ing .li_main_ing ul{margin-top:0px; border-bottom:1px solid #d4d4d4;						}
		.visual .visual_con .main_ing .li_main_ing ul li{background:white; opacity: 0.7; padding:3px 5px 5px 13px;text-align:center;font-size:14px; color:#000000; }
		.visual .visual_con .main_ing .li_main_ing ul li.title {font-weight: bold; opacity:inherit;  padding: 8px; height: 55px;}
		
		.visual .visual_con .main_ing .li_main_ing ul li.title h1 { background: 0 5px no-repeat; background-size: 15px 15px; float:center; padding: 2px;}
		.visual .visual_con .main_ing .li_main_ing ul li.title span{color: #B22222; margin-left: 5px;   }
		.visual .visual_con .main_ing .li_main_ing ul li.title h1 span{ color: #666; font-weight: bold; }
		.visual .visual_con .main_ing .li_main_ing ul li span a{color:#e51937;}
		.visual .visual_con .main_ing .li_main_ing ul li span{font-size:1.2em; color:#666666; display:block;font-family:'NGBold'; color: #000000;}
		
		.visual .visual_con .main_ing .li_maint_ing { width:23%; margin:0 3px 0 0; float:left; 							}
		.visual .visual_con .main_ing .li_maint_ing ul{margin-top:0px; border-bottom:1px solid #d4d4d4;					}
		.visual .visual_con .main_ing .li_maint_ing ul li{background:white; opacity: 0.9; padding:3px 5px 5px 13px;text-align:center;font-size:14px; color:#000000;}
		.visual .visual_con .main_ing .li_maint_ing ul li.title {font-weight: bold; opacity:inherit;  padding: 8px; height: 55px;}
		.visual .visual_con .main_ing .li_maint_ing ul li.title h1 { background: 0 5px no-repeat; background-size: 15px 15px; float:center; padding: 2px;}
		.visual .visual_con .main_ing .li_maint_ing ul li.title span{color: #B22222; margin-left: 5px;   }
		.visual .visual_con .main_ing .li_maint_ing ul li.title h1 span{ color: #666; font-weight: bold; }
		.visual .visual_con .main_ing .li_maint_ing ul li span a{color:#e51937;}
		.visual .visual_con .main_ing .li_maint_ing ul li span{font-size:1.2em; color:#666666; display:block;font-family:'NGBold'; color: #000000;}
		
		
		.quick_menu_head{color:#d8edf8; font-size: 0.7em;  margin-bottom: 0px; }	
		.quick_menu_title{color:#ffffff; font-size: 18px; font-weight: bold;}
		.quick_menu_info{color:#ffffff; font-size: 0.7em;}
		
		.paging_simple_numbers{float: right;}
		.card {
		    position: relative;
		    display: -ms-flexbox;
		    display: flex;
		    -ms-flex-direction: column;
		    flex-direction: column;
		    min-width: 0;
		    word-wrap: break-word;
		    background-color: #fff;
		    background-clip: border-box;
		    border: 1px solid rgba(0,0,0,.125);
		    border-radius: 0.25rem;
		    opacity: 0.9;
		}
		
		
		
		
		.shadow-sm {
			margin-bottom: 0.5rem;
			box-shadow: 0 .225rem .25rem rgba(0,0,0,.275)!important;
		}
		
		.shadow-lg {
			 box-shadow: 0 .225rem .25rem rgba(0,0,0,.075)!important;
			 opacity: 1!important;
			 position: relative; 
			 background-color: #efefef; 
			 
			/* height:200px;*/
			 margin-bottom: 10px;
			 
		}
		
		.card h2{
			display: block;
		    font-size: 1.1em;
		  /* margin-block-start: 0.83em;*/
		  /* margin-block-end: 0.83em;*/
		    margin-inline-start: 0px;
		    margin-inline-end: 0px;
		    font-weight: bold;
		    color: #fff;
		    text-align: center;
		}	
		
		.card-body{
		
		    flex: 1 1 auto;
		    min-height: 1px;
		    padding: 0.5rem; 
		   
		    padding-top: 1.0rem; 
		    padding-right: 0.0rem;
		    padding-bottom: 0.30rem;;
		    padding-left: 0.0rem;
		    
		}
		
		.card-body span small{color:#d4d4d4; }
		.card-body span label {color:#fff; font-size: 1.5em; margin-right:5px; cursor:pointer;}
		.card-body span{margin-top:10px; margin-bottom:10px; font-size:1.2em; color:#666666; display:block;font-family:'NGBold'; color: #000000; text-align: center;}
		
		
		
		.biz_cont{ height:216px; overflow-y: scroll; }
		.biz_cont .pagination{padding-top: 2px;}
		/*
		.biz_cont table{width: 100%; border-collapse: collapse; background-color: #fff;   }
		.biz_cont table tbody tr{
			border-bottom: 1px solid #e3ebf2;
			font-size: 12px; 
			vertical-align:middle;
		}
		.biz_cont table tbody tr td,th {
			height: 35px;
			padding: 5px;
			vertical-align:middle;
		}
		.biz_cont table th {
			height:30px; font-size:0.8em; font-weight:bold; color:#fff; background-color: #bab9b9;
		}	
		*/
		.arrow_top {
		    position: absolute;
		    display: block;
		    width: 0px;
		    height: 0px;
		    border-color: transparent;
		    border-style: solid ;
		    top: -22px;
		    content: " ";
		    border-top-width: 0;
		    border-bottom-color: #fff;
		    border-width: 11px;
		    opacity: 0.9;
		  
		}


		.notices{width:900px; height:250px; float:left; margin:10px 0px 0px 10px;}
		
		.notices h1{border-bottom: 1px solid #d42d25; height:30px; font-size: 15px; font-weight: 700; color: #6a6561;margin-top: 20px;}
		.notices h1 a{float:right;}
		.btn-more {width: 60px;border-radius: 100px;color: white;text-align: center;line-height: 20px; background: #ea9a56;border: 2px solid white;font-size: 10px; display: block;}
		
		
		.bizlist{width:400px; height:250px; float:left; margin:10px 0px 0px 10px;}
		.bizlist h1{border-bottom: 1px solid #d42d25; height:30px; font-size: 15px; font-weight: 700; color: #6a6561;margin-top: 20px;}
		.bizlist h1 a{float:right;}
	
	/*
		.biz_notice .pagination{padding-top: 2px;}
		.biz_notice table{width: 100%; border-collapse: collapse; }
		.biz_notice table tbody tr{
		
			border-bottom: 1px solid #e3ebf2;
			font-size: 12px; 
		}
		.biz_notice table tbody tr td {
			height: 35px;
			padding-top: 7px;
		}
		*/
		
		.badge {display: inline-block;padding: 0.4em 0.5em 0.2em 0.5em; font-size: 80%;  font-weight: 700;line-height: 1; text-align: center; white-space: nowrap;vertical-align: baseline; border-radius: 0.25rem;}
	    
	    .badge-danger {color: #fff;background-color: #b02318;}
	    .badge-primary {color: #fff;background-color: #007bff; }
	    .badge-info {color: #fff; background-color: #17a2b8; }
	    .badge-secondary {color: #fff;background-color: #6c757d;}
	    .badge-warning {color: #fff;background-color: #ffc107;}
	    .badge-dark {color: #fff;background-color: #343a40; }
	    .badge-black {color: #fff;background-color: #2d2d2d; }
	    
	    
	    .summary{  padding: 0em 1em 0em 1em; border-radius: 1em; font-size: 0.9em; font-weight: bold; }
	    .summary-red{outline: 2px solid #8e2219;  background-color: #b02318; color: white;}
	    .summary-orange{outline: 2px solid #c07d45; background-color: #ea9a56;color: white;}
	    
	    .btn-more {width: 60px;border-radius: 100px;color: white;text-align: center;line-height: 20px; background: #ea9a56;border: 2px solid white;font-size: 10px; display: block;}
		
		.main_bgi_copy{position:absolute; bottom:10px; left:50px}
		.main_bgi_copy span{color: #efefef; margin-left: 10px; font-size: 0.8em;}
		
		.content_body{margin-bottom: 100px; margin-top: 10px;}
		.content_layout{width:100%; display: flex; height:100%; justify-content: center;}
		
		
		.service_banner{float: left; margin-right:15px; margin-top:30px;  height:250px; width:260px;  text-align: center; cursor: pointer;}
		.service_banner_title{font-size: 18px; margin-top:5px; color:#565656; font-weight: bold; cursor: pointer; color: #a40c5e;}
		.service_banner_title p{font-size: 0.7em; color:#6c6c6c; margin-top: 5px; margin-bottom:10px;  font-weight: normal;} 
		
		.service_banner a{
			display: inline-block;
		    min-width: 50px;
		    height: 30px;
		    padding: 0 23px;
		    border-radius: 20px;
		    border: 1px solid #e0e0e0;
		    font-size: 13px;
		    color: #000;
		    line-height: 30px;
		    box-sizing: border-box;
		    text-align: center;
		    background-color: #fff;
		    color: #333;
		    text-decoration: none;
		}

		.service_banner span img { max-width: 130px; transition:all 0.1s linear;}
		.a{width: 110px; margin: 0px auto;}
		.a:hover img{transform:scale(1.1);}

		.paging_simple_numbers{float: right;}

</style>
	
<decorator:head />
</head>

<body style="overflow-x:hidden; overflow-y:auto;background-color: #efefef;">


<sec:authorize access="isAuthenticated()">
<form id="logoutForm" action="<c:url value="/security/securitySignout"/>" method="post" style="display:none">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</sec:authorize>
<p id="opacityError"	class="opacityError" 	style="display: none;"></p>
<p id="opacity" 		class="opacity" 		style="display: none;"></p>


<div id="wrapper">
	<div id="wrap">
		<section id="container" style="min-height:95vh;">
		
		<!--Content Head -->
		<div class="header" id="header">
			<div class="container">
				<page:apply-decorator name="layout-main-inHeader"/>
			</div>
		</div>
		<!-- main 컨텐츠 내용 -->
		<decorator:body/>
			
		
		<!-- 풋터 -->
		<page:apply-decorator name="layout-main-inFooter"/>

		</section>

	</div>
	
</div>
	
</body>
</html>