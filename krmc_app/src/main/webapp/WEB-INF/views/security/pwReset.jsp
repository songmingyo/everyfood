<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>


<html>
<head>
<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jquery.tmpl.js"/>"></script>
<style type="text/css">
	.button.sublogin{position:relative;display:inline-block; line-height:30px; height:40px;min-width:100%;border:0px solid #000;-webkit- border-radius:4px;-moz- border-radius:4px; border-radius:4px; margin:0; background-color:#c70101;-webkit-transition: all 0.2s ease-in-out;-moz-transition: all 0.2s ease-in-out;-ms-transition: all 0.2s ease-in-out;-o-transition: all 0.2s ease-in-out; transition: all 0.2s ease-in-out; font-weight:400; text-align:center; vertical-align:middle;font-size:16px;color:#fff;}
	.button.sublogin:hover{ background-color: #a60000;-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none;}
	
	.sysNm{position:relative}
	.sysNm .sysSubNm{position: absolute;top:15px;}
	h3.title{font-weight:bold;}
	
	.login-logo{border-bottom: 1px solid #efefef;}
	.login-logo > img#main{margin-bottom: 20px; margin-top: 15px;}
	
	.co-ws{color:#c21515 !important;}
	.co-bk-ws{background:#c21515 !important;}
	.co-bk-ws:hover{background:#c21515 !important;}
	
	.co-a-ws{color:#c21515 !important; font-weight:bold;}
	.co-a-ws:hover{color:#c21515 !important; border-bottom:1px solid;}
	
	.co-gray{color:#999 !important;}
	.co-bk-gray{background:#999 !important;}
	.co-bk-gray:hover{background:#ccc !important;}
	
	.form-group > div.has-feedback+div.has-feedback{margin-top:5px;}
	
	.btn{border:none;}
	.divCompInfo{border-top:1px solid #ccc; border-bottom:1px solid #ccc; padding:10px; font-size:13px; margin:15px 0px 10px 0px;}
	.spanInfo{font-weight:bold}
	
</style>
<script type="text/javascript">
var isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ? true : false;

// 비밀번호 변경(초기화) 구분 (FG:비밀번호 찾기, FR:임시비밀번호 발급(운영자가 생성)에 따른 접근, 3M: 비밀번호 3개월 변경)
var accessType = "<c:out value='${accessType}'/>";

$(document).ready(function() {
	
	//버튼 이벤트--------------------------------------------------------
	$('#btnSave').unbind().click(function(){eventPwReset()}); 		// 비밀번호 변경(저장)
	$('#btnLater').unbind().click(function(){eventPwChgLater()}); 	// 다음에 변경하기
	//버튼 이벤트--------------------------------------------------------
	
	//비밀번호 validation
	
	
	<%--
	$('#securityForm').validate({
		rules: {
        	userPw   	: { required: true, minlength : 8 , maxlength:25, notInc: $('#email').val(), exprChkNew: true},
        	userPwChk  	: { required: true, noSpace: true, equalTo: "#userPw", maxlength:20 },
        }
		,messages: {
    		userPw   	: {	 required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code='app.manager.memberviewlist._eventsave.a3' /><div>" <!-- 비밀번호를 입력하세요!  	-->
    					    ,notInc:  "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code='app.common.ou.login.alert.valid.pwd'/><div>" 		  <!-- 비밀번호에 아이디 및 공백은 포함할 수 없습니다. -->
    					    ,exprChkNew: "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code='app.common.ou.login.alert.valid.pwd3'/><div>" 	  <!-- 비밀번호는 숫자와 영문자,특수문자를 혼용하여 8~25글자를 입력해야 합니다. -->
    					  }
    		,userPwChk  : {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code='app.manager.memberviewlist._eventsave.a4'/><div>"	}<!-- 비밀번호를 입력하세요!  	-->
		}
		
	});
	--%>
	//input keydown event
	$("input").keydown(function(){
		if (event.keyCode === 13) {	//엔터키 이벤트 :: userPw,userPwChk  모두 입력 시 userPwChk 입력란에서만 실행
	    	if($(this).attr('type') == "password" && $('#userPw').val() && $('#userPwChk').val()){
	    		eventPwReset();
	    	}else{
	    		event.preventDefault();
	    	}
	    }else if(event.keyCode === 32){		//스페이스바 입력 금지
	    	event.preventDefault();
	    }
	});
	
});


//비밀번호 변경(저장)
function eventPwReset(){
	
	if($('#securityForm').valid()){
		var param ={};
		var userPw = $.trim($("#userPw").val());
		
		var email = $.trim($("#email").val());
		var memberCd = $.trim($("#memberCd").val());
		
		if( memberCd ==""){
			alert("계정정보가 존재하지 않습니다. 비밀번호 변경이 불가합니다.");
			return false;
		}	
	
		
		param.userPw = userPw;
		param.memberCd = memberCd;
		param.email = email;
		param.accessType = accessType;
		
		// 저장 이벤트 json 실행---------------------------
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : "<c:url value='/security/pwReset_updatePwdInfo.json'/>",
		      data : JSON.stringify(param),
	 	     success : function(data){
	 	    	if(data.msgCd =="success"){
	 	    		
	 	    		if(accessType=="FR"){
	 	    			//임시비밀번호로 로그인한 경우
	 	    			alert("<spring:message code='message.success.process'/>");	//정상적으로 처리되었습니다.
		 	    		location.href = "<c:url value='/main/mainDashboard'/>";
		 	    		
	 	    		}else if(accessType=="FG"){
	 	    			//비밀번호 찾기 진행한 경우
	 	    			alert("<spring:message code='app.common.pwd.reset.success'/>");	//새로운 비밀번호로 변경되었습니다. 로그인 후, 서비스를 이용하실 수 있습니다.
		 	    		location.href = "<c:url value='/security/securityForm'/>"; 		
	 	    		}
	 	    		
	 	    		
	 	    	}else if(data.msgCd =="busexception"){
					$("#resultMsg").empty();
	 				$("#resultMsgTmpl").tmpl(data).appendTo("#resultMsg"); <%--오류메시지--%>
	 				
	 	    	}else{
	 	    		alert("<spring:message code='message.error.process'/>");		//처리 중 오류가 발생하였습니다.
	 	    	}
	 	     }
		});
		//----------------------------------------------
	}

}

//다음에 변경하기
function eventPwChgLater(){
	
	var email = $.trim($("#email").val());
	var memberCd = $.trim($("#memberCd").val()); 
	
	if( memberCd ==""){
		alert("계정정보가 존재하지 않습니다.");
		return false;
	}
	
	var param= {};
	
	param.memberCd = memberCd;
	param.email = email;
	param.accessType = accessType; // 비밀번호 변경(초기화) 구분
	
	// 저장 이벤트 json 실행---------------------------
	$.ajax({
	      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	      url  : "<c:url value='/security/pwReset_updPwdLater.json'/>",
	      data : JSON.stringify(param),
 	     success : function(data){
 	    	 if(data.msgCd =="success"){
	    		alert("<spring:message code='message.success.process'/>");	//정상적으로 처리되었습니다.
 	    		location.href = "<c:url value='/main/mainDashboard'/>";
 	    		
 	    	}else if(data.msgCd =="busexception"){
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

</head>
<body class="login-page">
    <div class="login-box" >
      <div class="login-box-body" style=" border-radius: 0.5em;">
      <!-- logo -->
			<div class="login-logo">
				<img src="/resources/images/pearl/main/logo.png"  width="130" id="main" alt="KOREA R.M.C" onclick="fncMoveToMain();" style="cursor:pointer;"/>
			</div>
			<!-- ./logo -->
      	<h3 class="sysNm">
				<strong><span class="co-ws">KOREA R.M.C</span> 통합관리 시스템</strong>
				<p class="sysSubNm"><span style="font-size: 0.5em; color: #757d87"><strong>I</strong>ntergration<strong>M</strong>anagement <strong>S</strong>ystem</span></p>
		</h3>
		<!-- <h3 class="title">비밀번호 변경</h3> -->
		<div class="row" style="margin-bottom: -9px;">
			<h3 class="title col-xs-6" style="padding-right: 0;margin-top: 17px;">비밀번호 변경</h3>
			<c:if test="${accessType eq '3M' }">
				<div class="col-xs-6" style="margin-top: 23px; padding-left:7px; float: right;"><span class="spanInfo">최종변경일:&nbsp;${userVo.passLastDyFmt}</span></div>
			</c:if>
		</div>
		<div class="divCompInfo" id="compInfo">
			<input type="hidden" id="email" value="<c:out value='${userVo.email}'/>"/>
			<input type="hidden" id="memberCd" value="<c:out value='${userVo.memberCd}'/>"/>
			<c:choose>
				<c:when test="${accessType eq 'FR' }">
					<div>
						<i class='fa fa-info-circle' style="color:#c70101; vertical-align: -webkit-baseline-middle;"></i>
						<span style="font-size:16px; color:#c70101; margin-left:6px;">임시비밀번호로 로그인 시,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;비밀번호 변경후 이용가능합니다.</span>
					</div>
				</c:when>
				<c:when test="${accessType eq '3M' }">
					<div>
						<i class='fa fa-info-circle' style="color:#c70101; vertical-align: -webkit-baseline-middle;"></i>
						<span style="font-size:16px; color:#c70101; margin-left:6px;">비밀번호 변경후 3개월이 지났습니다.<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;비밀번호를 변경해주세요.</span>
					</div>
				</c:when>
				<c:otherwise>
					<div><span class="spanInfo">업체명:</span>&nbsp;<c:out value='${userVo.compNm}'/></div>
					<div><span class="spanInfo">사업자번호:</span>&nbsp;<c:out value='${userVo.bmanNo}'/></div>
					<div><span class="spanInfo">이메일 주소:</span>&nbsp;<c:out value='${userVo.emailMsk}'/></div>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<form id="securityForm" method="post">
			          <sec:csrfInput />
			          <div class="has-feedback" style="margin-bottom: 5px;">
			            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
						<input type="password" class="pwd form-control" placeholder="새 비밀번호 입력" id="userPw" name="userPw" autocomplete="new-password"/>
			          </div>
			          <div class="has-feedback" style="margin-bottom: 5px;">
			            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
						<input type="password" class="pwd form-control" placeholder="새 비밀번호 확인 입력" id="userPwChk" name="userPwChk" autocomplete="new-password"/>
			          </div>
		        </form>
			</div>
		</div>
		<div class="btn-area">
			<div class="row">
				<c:choose>
					<c:when test="${accessType ne '3M' }">
						<div class="col-xs-12" style="margin-bottom:5px;">
							<button type="submit" id="btnSave" class="btn btn-primary btn-block btn-flat co-bk-ws">저장</button>
						</div>
					</c:when>
					<c:otherwise>
						<div class="col-xs-6" style="padding-right:5px;margin-bottom:5px;">
							<button type="submit" id="btnSave" class="btn btn-primary btn-block btn-flat co-bk-ws">변경</button>
						</div>
						<div class="col-xs-6" style="padding-left:5px;margin-bottom:5px;">
							<button type="submit" id="btnLater" class="btn btn-primary btn-block btn-flat co-bk-gray">다음에 변경하기</button>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<c:if test="${accessType eq 'FG'}">
			<div style="text-align:center;">
				<span style="font-size:11px"><a href="<c:url value='/security/securityForm' />" style="margin-right:5px;"  class="co-a-ws" >로그인</a>|<a href="<c:url value='/' />" style="margin-left:5px;"  class="co-a-ws" >회원가입</a></span>
			</div>
		</c:if>
        <div id="resultMsg" class="social-auth-links text-center" style="margin-top: 10px;"></div><!-- /.social-auth-links -->
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->
  </body>
  <form name="testForm"></form>
</html>