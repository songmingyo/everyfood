<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<%--
Name : securityTerms.jsp
Description : 회원가입 약관동의
--%>
<html>
<head>
<style type="text/css">
	.subframe {
		position:absolute;
	    left:50%;
	    top:50%;
	    margin-left:-250px;
	    margin-top:-250px;
	}
	div.subframe{padding:70px; width: 500px;}
	
.subframe > .content{border: 1px solid white; padding: 70px 50px; background: white; border-radius: 10px;}
.subframe > .content > .login-logo h2{padding-bottom: 50px;text-align:center;writing-mode: middle;}
.subframe > .top-content{height:60px;position:relative;display:block;}

.top-content > .sub_title {font-size:30px;font-weight:bold;border-bottom:4px solid #3c8dbc;color:#3c8dbc}
.top-content > .sub_title > i {padding-right:20px;}
.textNm {
		border:1px solid #d5dee8;
		height:30px;
		width:100%;
		padding:0 8px;
		border-radius:4px;
		-webkit-appearance:none;
		vertical-align:middle;
		font-family:/* 'Source Sans Pro', */'Helvetica Neue',Helvetica,Arial,sans-serif;
		box-sizing : border-box;
		font-size:13px;
		color:#656565;
	}
	
.entpInput {height: 60px;}
.inputBox {width:100%;}

.button{font-size:small}
.agreeBtnGroup {text-align: center;margin-top: 40px;}
.agreeBtn{padding:0;margin: 0 10px;min-width:20%;}

.button.sublogin{position:relative;display:inline-block; line-height:50px; height:50px;min-width:100%;border:0px solid #000;-webkit-border-radius:4px;-moz-border-radius:4px; border-radius:4px; margin:0; background-color:#A3095C;-webkit-transition: all 0.2s ease-in-out;-moz-transition: all 0.2s ease-in-out;-ms-transition: all 0.2s ease-in-out;-o-transition: all 0.2s ease-in-out; transition: all 0.2s ease-in-out; font-weight:400; text-align:center; vertical-align:middle;font-size:16px;color:#fff;}
.button.sublogin:hover{ background-color: #8A084F;-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none;}

.button.logmove{position:relative;display:inline-block; line-height:50px; height:50px;min-width:100%;border:0px solid #000;-webkit-border-radius:4px;-moz-border-radius:4px; border-radius:4px; margin:0; background-color:#174a7e;-webkit-transition: all 0.2s ease-in-out;-moz-transition: all 0.2s ease-in-out;-ms-transition: all 0.2s ease-in-out;-o-transition: all 0.2s ease-in-out; transition: all 0.2s ease-in-out; font-weight:500; text-align:center; vertical-align:middle;font-size:16px;color:#fff;}
.button.logmove:hover{ background-color: #062a4e;-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none;}

</style>


<script type="text/javascript">
	$(document).ready(function(){
	     init();
	     
	     $("#testBtn1").click(function() {
	    	 $('#logoutForm').submit();
				return false;
	     })
	});
	
	function init(){
		$('#tempUserId').val("${customUser.userId}")
		$('#tempMasterId').val("${customUser.masterId}")
	}
	
	
	// 업체정보 수정
	function compInfoUpd(){
		
		if(!validate()){
			return false;
		}
		
		if (!confirm("담당자 정보를 저장하시겠습니까?")) return;
		
		$("#hiddenForm input[name=entpManNm]").val( $('#manNm').val());
		$("#hiddenForm input[name=entpManTel]").val($('#manTel').val());
		$("#hiddenForm input[name=entpManEmail]").val($('#manEmail').val());
		$("#hiddenForm input[name=masterId]").val( $('#tempMasterId').val());
		
		var f = document.hiddenForm;
		f.action = '<c:url value="/security/securityUpdateCompInfo"/>';
		f.submit();
		f.action = "";
		
	}
	
	
	// 검증
	function validate(){
		
		if($('#manNm').val().trim() == ""){
			alert('담당자명을 입력해주세요');
			$("#manNm").focus();
			return false;
		}
		
		if($('#manTel').val().trim() == ""){
			alert('담당자번호를 입력해주세요');
			$("#manTel").focus();
			return false;
		}
		
		if($('#manEmail').val().trim() == ""){
			alert('담당자 이메일을 입력해주세요');
			$("#manEmail").focus();
			return false;
		}
		
		var regex = /^([\w\.\_\-\!])+([\w\.\_\-\!])+@([a-zA-Z0-9]+\.)+[a-zA-Z0-9]{2,8}$/i;
		if(!$('#manEmail').val().match(regex)){
			alert("잘못된 이메일 주소입니다.");
			return false;
		} 

		return true;
	}
	
	
</script>

</head>

<body>
	<div class="subframe" style = "padding:5px;" >
		<div class="content">
			<div class="top-content">
				<div class="sub_title">
					업체 정보 입력
				</div>
			</div>
			<div>
				<table class="type1" class="loginValidate" style="border-collapse: collapse; width: 100%" >
					<colgroup>
						<col width="80">
						<col width="*">
					</colgroup>
						<tr>
							<td class="entpInput"><b>담당자명</b>  </td> <td><input type="text" class="inputBox textNm" id="manNm" value="${customUser.entpManNm}" placeholder = "담당자명을 입력하세요"/></td>
						</tr>
						<tr>
							<td class="entpInput"><b>휴대전화</b>  </td> <td><input type="text" class="inputBox textNm" id="manTel" onkeyup="onlyInputNumber(this);" value="${customUser.entpManTel}" placeholder = "휴대전화번호를 입력하세요"/></td>
						</tr>
						<tr>
							<td class="entpInput"><b>E-MAIL</b>  </td> <td><input type="text" class="inputBox textNm" id="manEmail" value="${customUser.entpManEmail}" placeholder = "이메일을 입력하세요"/></td>
						</tr>
					<!-- 	<tr>
							<td class="entpInput">비밀번호 : </td> <td><input type="password" class="inputBox" name="userPw" id="userPw" autocomplete="new-password"/></td>
						</tr> -->
						<tr>
							<td class="entpInput" colspan="2">
								<input type="button" id="testBtn" class="button sublogin agreeBtn" value="저장" onclick="compInfoUpd();" style = "margin-right:15px;min-width:47%;"/>
								<input type="button" id="testBtn1" class="button logmove agreeBtn" value="뒤로가기"style = "min-width:47%;" />
								<input type="hidden" id="tempUserId" />
								<input type="hidden" id="tempMasterId" />
							</td>
						</tr>		
				</table>
			</div>
		</div>
	</div>
	
	<form id="hiddenForm" name="hiddenForm" method="post">
		<sec:csrfInput />
		<input type="hidden" id="entpManNm" name="entpManNm" />
		<input type="hidden" id="entpManTel" name="entpManTel" />
		<input type="hidden" id="entpManEmail" name="entpManEmail" />
		<input type="hidden" id="masterId" name="masterId" />
	</form>
	
	<form id="logoutForm" action="<c:url value="/security/securitySignout"/>" method="post" style="display:none">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>