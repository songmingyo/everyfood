<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<%--
	ClassName :  pwForgot.jsp
	Description : 비밀번호 찾기 (사용자 정보 확인후 비밀번호 초기화 url 메일 전송)
	Modification information
	수정일 			수정자					수정내용
	--------- 	----------------		-----------------
	2023.01.03		안석진				 최초 작성
	
	Author 	: asj
	Since 	: 2023.01
	
--%>
<html>
<head>
<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jquery.tmpl.js"/>"></script>
<link href="/resources/almasaeed/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.button.sublogin{position:relative;display:inline-block; line-height:30px; height:40px;min-width:100%;border:0px solid #000;-webkit- border-radius:4px;-moz- border-radius:4px; border-radius:4px; margin:0; background-color:#c70101;-webkit-transition: all 0.2s ease-in-out;-moz-transition: all 0.2s ease-in-out;-ms-transition: all 0.2s ease-in-out;-o-transition: all 0.2s ease-in-out; transition: all 0.2s ease-in-out; font-weight:400; text-align:center; vertical-align:middle;font-size:16px;color:#fff;}
	.button.sublogin:hover{ background-color: #a60000;-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none;}
	
	.sysNm{position:relative}
	.sysNm .sysSubNm{position: absolute;top:15px;}
	h3.title{font-weight:bold;}
	
	.login-logo{border-bottom: 1px solid #efefef;}
	.login-logo > img#main{margin-bottom: 20px; margin-top: 15px;}
	
	.co-ws{color:#a40c5e !important;}
	.co-bk-ws{background:#a40c5e !important;}
	.co-bk-ws:hover{background:#890d50 !important;}
	
	.co-a-ws{color:#a40c5e !important; font-weight:bold;}
	.co-a-ws:hover{color:#890d50 !important; border-bottom:1px solid;}
	
	.form-group > div.has-feedback+div.has-feedback{margin-top:5px;}
	
	.btn{border:none;}
	.mailList {list-style:none; padding-left: 8px; line-height:3px; }
	.mailList > li> label {font-weight:normal !important;}
	
</style>
<script type="text/javascript">
var isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ? true : false;
var srchType = null;

$(document).ready(function() {
	
	//버튼 이벤트--------------------------------------------------------
		$('#btnFindPw').unbind().click(function(){fncChkFindType()}); 		// 비밀번호 찾기
		$('#btnSendMail').unbind().click(function(){eventSendPwResetMail()}); 	// 인증요청
	//버튼 이벤트--------------------------------------------------------
	
	//라디오버튼 이벤트--------------------------------------------------------
		$("input[name='findType']").click(null, fncInitFindPw); 		// 찾기 유형 radio
	//라디오버튼 이벤트--------------------------------------------------------
	
	
	//input keydown event
		$("#bmanNo, #email").keydown(function(){
			if (event.keyCode === 13) {			//엔터키 이벤트 
				fncChkFindType();
		    }else if(event.keyCode === 32){		//스페이스바 입력 금지
		    	event.preventDefault();
		    }
		});
		
		
	fncInitFindPw();
	
});

<%--1. 비밀번호 찾기 유형에 따라 화면 노출--%>
function fncInitFindPw(){
	//alert 영역 show/hide--------------------
		$("#resultListDiv").hide();
		$("#sendMail-btn-area").hide();
		$("#resultMsg").empty();
	//alert 영역 show/hide--------------------	
	
	var findType = $.trim($(':radio[name="findType"]:checked').val());
	srchType = findType;
	
	if(srchType=="B"){
		
		$("#findBmanNoDiv").show();
		$("#findEmailDiv").hide();
		$("#bmanNo").val("");
		
	}else if(srchType=="E"){
		
		$("#findBmanNoDiv").hide();
		$("#findEmailDiv").show();
		$("#email").val("");
		
	}else{
		alert("비밀번호 찾기 유형을 선택하세요");
		$(':radio[name="findType"]').focus();
		return false;
	}
}

<%--2.비밀번호 찾기 클릭 --%>
function fncChkFindType(){
	
	if(srchType=="B"){
		eventSrchAccountList();	//사업자 번호로 찾기
		
	}else if(srchType=="E"){
		
		eventSrchAccountExistYn(); //이메일로 찾기
		
	}else{
		alert("비밀번호 찾기 유형을 선택하세요");
		$(':radio[name="findType"]').focus();
		return false;
	}
	
}

<%--2-1A. 사업자번호로 찾기: 사업자 번호 입력 > 기등록 계정 검증(조회) --%>
function eventSrchAccountList(){
	
	var bmanNo = $.trim($("#securityForm #bmanNo").val());
	
	if(bmanNo ==""){
		alert("사업자번호를 입력하세요.");
		$("#securityForm #bmanNo").focus();
		return false;
	}
	
	var srchInfo={};
	srchInfo.bmanNo = bmanNo;
	
	// 조회 이벤트 json 실행---------------------------
	$.ajax({
	      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	      url  : "<c:url value='/security/pwForgot_selAccountList.json'/>",
	      data : JSON.stringify(srchInfo),
 	     success : function(data){
 	    	if(data.msgCd =="success"){
 	    		fncSetAccountList(data); <%--계정검증 조회결과 세팅--%>
 	    		
 	    	}else if(data.msgCd =="busexception"){
 	    		$("#resultMsg").show();
 	    		$("#resultListDiv").hide();
 	    		$("#sendMail-btn-area").hide();
 	    		$("#resultMsg").empty();
 				$("#resultMsgTmpl").tmpl(data).appendTo("#resultMsg"); <%-- 검증 조회결과 오류메시지--%>
 				
 	    	}else{
 	    		alert("<spring:message code='message.error.process'/>");		//처리 중 오류가 발생하였습니다.
 	    	}
 	     }
	});
	//----------------------------------------------
	
}

<%--2-1A-a. 계정검증 조회결과 세팅 --%>
function fncSetAccountList(data){
	var verifyData = data.resultMap.accountList;
	
	if(verifyData !=null && verifyData.length>0){
		
		for(var i=0; i<verifyData.length; i++){
			verifyData[i].seq = i+1;
		}
		
		$("#resultMsg").hide();
		$("#resultListDiv").show();
		$("#sendMail-btn-area").show();
		$("#resultList").empty();
		$("#resultListTmpl").tmpl(verifyData).appendTo("#resultList"); <%-- 검증 조회결과--%>
		
	}else{
		alert("입점제안 시스템 내 가입정보가 없는 업체입니다.\r\n 가입 진행 후 이용가능합니다.");
		return false;
	}
}

<%--2-1B. 이메일로 찾기: 이메일 입력 > 계정 검증(회원정보가 존재하는 계정인지) --%>
function eventSrchAccountExistYn(){
	var email = $.trim($("#securityForm #email").val());
	
	if(email ==""){
		alert("이메일을 입력하세요.");
		$("#securityForm #email").focus();
		return false;
	}
	
	var srchInfo={};
	srchInfo.email = email;
	
	// 조회 이벤트 json 실행---------------------------
	$.ajax({
	      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	      url  : "<c:url value='/security/pwForgot_selAccountEmailExistYn.json'/>",
	      data : JSON.stringify(srchInfo),
 	     success : function(data){
 	    	if(data.msgCd =="success"){
 	    		eventSendPwResetMail(data); <%--비밀번호 변경(초기화) 페이지 메일 발송--%> 
 	    		
 	    	}else if(data.msgCd =="busexception"){
				$("#resultMsg").empty();
 				$("#resultMsgTmpl").tmpl(data).appendTo("#resultMsg"); <%-- 검증 조회결과 오류메시지--%>
 				
 	    	}else{
 	    		alert("<spring:message code='message.error.process'/>");		//처리 중 오류가 발생하였습니다.
 	    	}
 	     }
	});
	//----------------------------------------------
}


<%--3.비밀번호 변경(초기화) 페이지 메일 발송  --%>
function eventSendPwResetMail(data){
	var email ="";
	
	if(srchType=="B"){
		email = $.trim($(':radio[name="emailList"]:checked').val());
			
	}else if(srchType=="E"){
		var verifyData = data.resultMap.accountInfo;
		email = $.trim(verifyData.email);
		
	}else{
		alert("비밀번호 찾기 유형을 선택하세요");
		$(':radio[name="findType"]').focus();
		return false;
	}
	
	var srchInfo={};
	srchInfo.email = email;
	
	// 메일발송 이벤트 json 실행---------------------------
	$.ajax({
	      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	      url  : "<c:url value='/security/pwForgot_selSendForgotPwInfo.json'/>",
	      data : JSON.stringify(srchInfo),
 	     success : function(data){
 	    	if(data.msgCd =="success"){
 	    		alert("<spring:message code='app.common.ou.pwd.label.guideMsg10'/>");	//비밀번호 재설정 메일이 발송되었습니다.
 	    		
 	    	}else if(data.msgCd =="busexception"){
// 				var msg = data.message;
				$("#resultMsg").empty();
 				$("#resultMsgTmpl").tmpl(data).appendTo("#resultMsg"); <%--오류메시지--%>
 				
 	    	}else{
 	    		alert("<spring:message code='message.error.process'/>");		//처리 중 오류가 발생하였습니다.
 	    	}
 	     }
	});
	//----------------------------------------------
	
}
</script>

<script id="resultMsgTmpl"  type="text/x-jquery-tmpl">
	<span class="text-red">
		<i class="fa fa-warning"></i>&nbsp;<span><c:out value='\${message}'/><span>
	</span>	
</script>

<script id="resultListTmpl"  type="text/x-jquery-tmpl">
	<li>	
		<input type="radio" id="emailList_\${seq}" name="emailList" value="<c:out value='\${email}'/>"/>
		<label for="emailList_\${seq}"><c:out value='\${email}'/></label>
	</li>
</script>

</head>
<body class="login-page">
    <div class="login-box" >
      
      <div class="login-box-body" style=" border-radius: 0.5em;">
      	<!-- logo -->
			<div class="login-logo">
				<img src="/resources/images/pearl/main/logoW.png"  width="200" id="main" alt="KOREA R.M.C"/>
			</div>
		<!-- ./logo -->
      	<h3 class="sysNm">
			<strong><span class="co-ws">PLS</span> 입점상담 시스템</strong>
			<p class="sysSubNm"><span style="font-size: 0.5em; color: #757d87"><strong>P</strong>artner <strong>L</strong>aunching <strong>S</strong>ystem</span></p>
		</h3>
		<h3 class="title">비밀번호 찾기</h3>
		<div  style="font-size:11px;">
			<span>
				<input type="radio" id="findBmanNo" name="findType" value="B" checked="checked"/>          	   		
      	   		<label for ="findBmanNo" >사업자 번호로 찾기</label>	
			</span>
			<span>
				<input type="radio" id="findEmail" name="findType" value="E" />          	   		
      	   		<label for ="findEmail" >이메일로 찾기</label>	
			</span>
		</div> 
		<div class="row">
			<div class="col-xs-12">
				<form id="securityForm" method="post">
			          <sec:csrfInput />
			          <div id="findBmanNoDiv" class="has-feedback" style="margin-bottom: 5px;">
			            <span class="glyphicon glyphicon-envelope form-control-feedback fa fa-user" style="margin-top: 10px;"></span>
			            <input type="text" class="id form-control" placeholder="사업자번호 입력" name="bmanNo" id="bmanNo" autocomplete="off"  onkeyup="return isNumber(this); chkByte(this,30);"/>
			          </div>
			          <div id="findEmailDiv" class="has-feedback" style="margin-bottom: 5px;">
			            <span class="glyphicon glyphicon-envelope form-control-feedback fa fa-user" style="margin-top: 16px;"></span>
			            <input type="text" class="id form-control" placeholder="이메일 입력" name="email" id="email" autocomplete="off" onkeyup="chkByte(this,60);"/>
			          </div>
		        </form>
			</div>
		</div>
		<div class="btn-area">
			<div class="row">
				<div class="col-xs-12" style="margin-bottom:5px;">
					<button type="submit" id="btnFindPw" class="btn btn-primary btn-block btn-flat co-bk-ws">비밀번호 찾기</button>
				</div>
			</div>
		</div>
		<div style="text-align:center;">
			<span style="font-size:11px"><a href="<c:url value='/security/securityForm' />" style="margin-right:5px;"  class="co-a-ws" >로그인</a>|<a href="<c:url value='/security/securityJoinus' />" style="margin-left:5px;"  class="co-a-ws" >회원가입</a></span>
		</div>
        <div id="resultMsg" class="social-auth-links text-center" style="margin-top: 10px;"></div><!-- /.social-auth-links -->
        <div id="resultListDiv" class="social-auth-links" style="margin-top: 10px;" style="display:none;">
        	<ul id="resultList" class="mailList"></ul>
        </div>
        <div class="btn-area" id="sendMail-btn-area" style="display:none;">
        	<div class="row">
				<div class="col-xs-8" style="float:none !important; margin:0 auto;">
        			<button type="submit" id="btnSendMail" class="btn btn-primary btn-block btn-flat co-bk-ws">인증요청</button>
        		</div>
			</div>        		
		</div>
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->
  </body>
</html>