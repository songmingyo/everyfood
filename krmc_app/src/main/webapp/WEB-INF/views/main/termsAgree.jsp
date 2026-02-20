<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<%--
Name : securityTerms.jsp
Description : 회원가입 약관동의
--%>
<html>
<head>
<style type="text/css">
	.policyAgree {vertical-align: top;}
	
	div.subframe{padding:70px;}
.subframe > .content{border: 1px solid white; padding: 70px 50px; background: white; border-radius: 10px;}
.subframe > .content > .login-logo h2{padding-bottom: 50px;text-align:center;writing-mode: middle;}
.subframe > .top-content{height:60px;position:relative;display:block;}

.top-content > .sub_title {font-size:30px;font-weight:bold;border-bottom:4px solid #3c8dbc;color:#3c8dbc}
.top-content > .sub_title > i {padding-right:20px;}
.top-content > ul {position:absolute; top:220px; right:115px;}
.top-content > ul > li{display:inline;padding:0 10px;font-size: 17px;border-right:2px solid lightgray;}
.top-content > ul > li.last{display:inline;border-right:0px;padding:0 10px;font-size: 17px;}

/* Terms */
.termsList > .terms {margin-bottom:60px;}
.terms-item{line-height: 30px;height:400px;width:100%;oveflow-x:none;overflow-y:scroll;font-size:17.5px;resize: none;padding:5%;border:1px solid lightgray;}
.checkGroup {float:right}
.checkGroup.all{float:none;}
.checkGroup.all > label{padding-left:0px;}
.checkGroup > label{padding-left:10px;margin-top:10px}
.checkLabel {margin-left:10px;font-size:small;vertical-align:middle}

.table_title{font-size:20px;padding:20px 0;padding-bottom:5px;font-weight:bold;}

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
	     
		/* 약관동의 */
		$("#btnCompAgree").click(function() {
			/************************  (신) 전체 동의만 존재*****************************/
			if(!checkBox('.policyAgree')) {
				alert("<spring:message code='app.common.terms.validation.agree'/>");
				return false;
			} else {
				setJoinTermsMapp();
				var f = document.hiddenForm;
				$("#hiddenForm input[name='agreeAllYn']").val("COMP");
				f.action = '<c:url value="/security/createAccount"/>';
				f.submit();
				f.action = "";
			}
			/***********************************************************************/
		});
		
		$("#btnChnAgree").click(function() {
			/************************  (신) 전체 동의만 존재*****************************/
			if(!checkBox('.policyAgree')) {
				alert("<spring:message code='app.common.terms.validation.agree'/>");
				return false;
			} else {
				
				if (!confirm("동의하시겠습니까?")) return;
				
				setJoinTermsMapp();
				var f = document.hiddenForm;
				f.action = '<c:url value="/security/securityTermsAgree"/>';
				f.submit();
				f.action = "";
			}
			/***********************************************************************/
		});

		//미동의 (로그인페이지로 이동)
		$("#btnNotAgree").click(function() {
			if(opener != null) { // 팝업인지 확인
				if(confirm("팝업창을 닫으시겠습니까?")) {
					window.open('','_self').close(); // 팝업창 닫기
				}
			}else {
				if(confirm("로그인화면으로 이동하시겠습니까?")) {
					$('#logoutForm').submit();
					return false;
				}
			}
		});
		
		// 약관 동의 확인
	});
	
	function setJoinTermsMapp() {
		var joinTermsMapp = "";
		
		$.each($("input[name='policyAgree']:checked"), function() {
			joinTermsMapp += ","+this.value;
		});
		joinTermsMapp = joinTermsMapp.substring(1);
		$("#joinTermsMapp").val(joinTermsMapp);
	}

	/* ischecked */
	function checkBox(value) {
		var result = true; 
		$.each($(""+value), function() {
			if(!$(this).is(":checked")) {
				result = false;
				$(this).focus();
			}
		});
		return result;
	}
	
</script>

</head>

<body>
	<div class="subframe" style = "padding:5px;" >
		<div class="content">
			<div class="top-content">
				<div class="sub_title">
					<spring:message code='app.common.title.terms'/>
				</div>
				<%--<ul>
					<li><a href="?language=vn">VN</a></li>
					<li><a href="?language=en">EN</a></li>
					<li class="last"><a href="?language=ko">KO</a></li>
				</ul>--%>
			</div>
			<div id="termsList" class="termsList">
				<c:forEach items="${termsList}" var="term" varStatus="status">
					<div class="terms" style="margin-bottom:0;">
						<div class="table_title"><c:out value="${term.termsVersionInfo}"/></div>
						<div>
							<div id="terms_${status.count }" class="terms-item" style = "width:100%; padding-top:4%; height: 550px;">
								<c:out value="${term.termsContent}" escapeXml="false" />
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="checkbox checkGroup all" style="margin-top: 15px;margin-left:30px; ">
			    <label><input type="checkbox" id="termsUseAgree" class="policyAgree" autocomplete="off"/><span class="checkLabel"><spring:message code='app.common.terms.label.agree2'/></span></label>
			</div>
			<div class="agreeBtnGroup">
				<%--<button type="button" id="btnCompAgree" class="button logmove agreeBtn">파트너사 회원가입</button>			<!-- Agree. -->--%>
				<button type="button" id="btnChnAgree" class="button sublogin agreeBtn" style = "padding:0;margin: 0 10px;min-width:20%;">동의합니다.</button>				<!-- Agree. -->
				<button type="button" id="btnNotAgree" class="button logmove agreeBtn" style = "padding:0;margin: 0 10px;min-width:20%;"><spring:message code='app.common.terms.btn.notAgree'/></button><!-- Not agree. -->
			</div>
		</div>
	</div>
	<form id="hiddenForm" name="hiddenForm" method="post">
		<sec:csrfInput />
		<input type="hidden" id="agreeAllYn" name="agreeAllYn" value="Y" />
		
		<!-- <input type="hidden" id="joinTermsMapp" name="joinTermsMapp" />
		<input type="hidden" id="joinTermsMapp" name="joinType" /> -->
	</form>
	<form id="logoutForm" action="<c:url value="/security/securitySignout"/>" method="post" style="display:none">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
</body>
</html>