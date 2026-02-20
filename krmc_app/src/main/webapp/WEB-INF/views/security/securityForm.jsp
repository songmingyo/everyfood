<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>
<script>
	$(document).ready(function() {
		//button click event =======================================================================
		$("#btnSignIn").unbind().click(eventSignIn, null);					//일반 로그인
		$("#btnFindPw").unbind().click(fncMoveToFindPw, null);				//비밀번호 찾기
		
		//validate form
		$('#securityForm').validate({
			rules: {
	        	userId    : { required: true}
	           ,userPw    : { required: true}
	        }
			,messages: {
				userId   : {required: "<div class='validate'><i class='fa fa-info-circle'></i> ID |  <spring:message code="required" /><div>"}
			   ,userPw   : {required: "<div class='validate'><i class='fa fa-info-circle'></i> PASSWORD | <spring:message code="required" /><div>"}
			}
			
		});
		
		//input keydown event
		$("input").keydown(function(){
			if (event.keyCode === 13) {			//엔터키 이벤트 :: ID, PW 모두 입력 시 PW 입력란에서만 실행
		    	if($(this).attr('type') == "password" && $('#userId').val() && $('#userPw').val()){
		    		eventSignIn();
		    	}else{
		    		event.preventDefault();
		    	}
		    }else if(event.keyCode === 32){		//스페이스바 입력 금지
		    	event.preventDefault();
		    }
		});

		$('#userId').focus();
		
		
	});

	//로그인
	function eventSignIn(){
		if($('#securityForm').valid()){
			$('#securityForm').attr("action", "<c:url value='/security/securityLogin'/>").submit();
		}
	}
	
	
	
	//비밀번호 찾기
	function fncMoveToFindPw(){
		location.href = "<c:url value='/security/pwForgot'/>"; 	
	}
	
	//메인화면으로 이동
	function fncMoveToMain(){
		location.href = "<c:url value='/'/>";
	}
	
</script>
<style type="text/css">
	.button.sublogin{position:relative;display:inline-block; line-height:30px; height:40px;min-width:100%;border:0px solid #000;-webkit- border-radius:4px;-moz- border-radius:4px; border-radius:4px; margin:0; background-color:#c70101;-webkit-transition: all 0.2s ease-in-out;-moz-transition: all 0.2s ease-in-out;-ms-transition: all 0.2s ease-in-out;-o-transition: all 0.2s ease-in-out; transition: all 0.2s ease-in-out; font-weight:400; text-align:center; vertical-align:middle;font-size:16px;color:#fff;}
	.button.sublogin:hover{ background-color: #a60000;-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none;}
	
	.sysNm{position:relative; font-size: 1.4em; }
	.sysNm .sysSubNm{position: absolute;top:15px;}
	h3.title{font-weight:bold;}
	
	.login-logo{border-bottom: 1px solid #efefef;}
	.login-logo > img#main{margin-bottom: 20px; margin-top: 15px;}
	
	.co-ws{color:#c21515 !important;  }
	.co-bk-ws{background:#c21515 !important;}
	.co-bk-ws:hover{background:#e03d3d !important;}
	
	.form-group > div.has-feedback+div.has-feedback{margin-top:5px;}
	
	.btn{border:none;}
</style>
</head>

<body>
	<!-- login page start -->
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
			<h3 class="title">로그인</h3>

			<div class="row">			
				<!-- loginForm -->
				<div class="col-xs-9">
				<form id="securityForm" method="post">
					<sec:csrfInput/>
					<div class="form-group">
						<div class="has-feedback">
							<span class="glyphicon glyphicon-envelope form-control-feedback fa fa-user" style="margin-top: 11px;"></span>
							<input type="text" class="id form-control" placeholder="아이디 입력" id="userId" name="userId" autocomplete="off"/>
						</div>
						<div class="has-feedback">
							<span class="glyphicon glyphicon-lock form-control-feedback"></span>
							<input type="password" class="pwd form-control" placeholder="비밀번호 입력" id="userPw" name="userPw" autocomplete="new-password"/>
						</div>
					</div>
				</form>			
				</div>
				<div class="col-xs-3" style="padding-left:0px; word-break:break-word;">
					<button type="submit" id="btnSignIn" class="btn btn-primary btn-block btn-flat co-bk-ws" style="white-space:normal;height:73px;">로그인</button>
				</div>	
			</div>
			<!-- button area -->
<!-- 			<div class="btn-area"> -->
<!-- 				<a href="#" id="btnFindPw">비밀번호 찾기</a> -->
				
<!-- 			</div> -->
			<!-- ./button area -->
			<!-- exception area -->
			<div class="social-auth-links text-center" style="margin-top: 10px;">
				<c:if test="${not empty param.fail }">
					<c:set var="failMassage"  value="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}" /> <!-- 실패 메세지 파라메터 -->
					<!-- 레드문구 -->
					<span class="text-red" style="font-size:smaller;">
						<c:if test="${not empty failMassage}"> <!-- 실패 메세지가 존재 할 경우 -->
							<i class="fa fa-warning"></i>
								<c:choose>
									<c:when test="${failMassage eq '01' or failMassage eq '07' }"> 
									  	<spring:message code="error.msg.security.not.match"/>				<!-- 로그인 사용자 아이디 또는 패스워드가 불일치 합니다. -->
									</c:when>
									<c:when test="${failMassage eq '02'}">
										<spring:message code="error.msg.security.company.not.suspended"/>	<!-- 사용자의 협력사가 중지된 상태입니다.(사용안함) -->
									</c:when>
									<c:when test="${failMassage eq '03'}">
										<spring:message code="error.msg.security.company.not.allow"/>		<!-- 협력사 승인 요청중입니다.(사용안함) -->
									</c:when>
									<c:when test="${failMassage eq '04' }">
										<spring:message code="error.msg.security.username.withdraw"/>		<!-- 탈퇴 처리된 계정입니다 -->
									</c:when>
									<c:when test="${failMassage eq '05' }">
										<spring:message code="error.msg.security.locked"/>					<!-- 해당 사용자는 잠김 상태입니다. 패스워드 찾기 후 로그인 진행바랍니다. -->
									</c:when>
									<c:when test="${failMassage eq '06' }">
										<spring:message code="error.msg.security.not.used"/>				<!-- 해당 사용자는 미사용 상태입니다.-->
									</c:when>
									<c:when test="${failMassage eq '08' }">
										<spring:message code="error.msg.custom.passChgNeed"/>				<!-- 비밀번호 변경이 필요합니다. -->
									</c:when>
									<c:when test="${failMassage eq '09' }">
										<spring:message code="error.msg.custom.passLastDy"/>				<!-- 비밀번호 변경 후 3개월이 지났습니다. 안전한 사용을 위해 비밀번호를 변경해야합니다. -->
									</c:when>
									<c:when test="${failMassage eq '91' }"> 
										<spring:message code="error.msg.custom.legacy.conn.exception"/>		<!-- 로그인 검증 시스템 접근오류(관리자에게 문의하세요) -->
									</c:when>	
									<c:when test="${failMassage eq '92' }"> 
										<spring:message code="error.msg.custom.legacy.not.match"/>			<!-- 통합로그인 사용자 정보가 없거나 아이디 또는 패스워드 불일치 합니다.  -->
									</c:when>							
									<c:when test="${failMassage eq '11' }">
										<spring:message code="error.msg.custom.access.permission.denied"/>	<!-- 시스템 사용 권한이 없습니다. 관리자에게 문의하세요 -->
									</c:when>
									<c:otherwise>
										<spring:message code="error.msg.common.exception.title"/>			<!-- Data 처리중 알수없는 오류가 발생하였습니다.-->
									</c:otherwise>
								</c:choose>
						</c:if>
					</span>

					<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>
				</c:if>
			</div>
			<!-- ./exception area -->
		</div>
	</div>
	<!-- login page end -->
</body>

</html>