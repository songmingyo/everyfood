<%@ page language="java"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>


<!-- password crypto js -->
<script type="text/javascript" src="/js/crypto/sha256.js"></script>
<script type="text/javascript" src="/js/crypto/enc-base64-min.js"></script>
<!-- 끝 -->

<script type="text/javascript">

	/* Layer Event Creat */
	function _setCommonPwdCheckLayerEvent(type){
		/* 레이어 활성화 */
		var layerID = 'common_PwdCheck_layer';
		
		setLayerPopupLocationSettings(layerID, 510, 600);
		
		/* 레이어 드레그 Event */
		setLayerDraggable(layerID);
		
		$("#checkPwd").focus();
		
		/* 레이어 닫기버튼 Click Event */
		$('#'+layerID + ' a.close').unbind().click(function(e){		closePwdCheckLayer001();		});
		//$('#btnClose').unbind().click(function(e){			closePwdCheckLayer001();		});
		$('#btnPwCheck').unbind().click(function(e){			reloadPwdCheckLayer(this);		});
		
		$("#checkPwdForm").find("input[name='checkPwd']").unbind().keydown(function(e) {
			switch(e.which) {
	    	case 13 : reloadPwdCheckLayer(this); break; // enter
	    	default : return true;
	    	}
	    	e.preventDefault();
	   	});
	}
	
	
	/* 검색 및 조회 */
	function reloadPwdCheckLayer(){
		
		if(!$('#checkPwd').val()) {
			alert('비밀번호를 입력하세요');
			$('#checkPwd').focus();
			return;
		}	
		
		var userType = "<c:out value='${userSession.userType}'/>";
		
		var str = 	{
				'userName'	:	'${userSession.userId}',
				'password' 	: 	$('#checkPwd').val()
				};
		
// 		console.log(str);
				$.ajaxSetup({contentType: "application/json; charset=utf-8" });
		$.post('/security/verifyUserInfo.json'
				, JSON.stringify(str)
		  		, function(data){
					var msg="";
					if (data.resultCode == "success") {
						
						//마스킹 해제
						_setSuccessForm();
				
						//팝업레이어 닫기
						closePwdCheckLayer001();

					}else if(data.resultCode == "fail"){
						if(data.msgCd == "-2"){
							msg = '접속오류 관리자에게 문의하세요'; 
							
						}else if(data.msgCd == "-3"){
							msg = '비밀번호가 일치하지 않습니다.'; 
						}
						$("#checkPwd").val("");
						$("#checkPwd").focus();
						alert(msg);
					
					} else{
						
						 msg = '처리중 오류가 발생 하였습니다';
						alert(msg);
					
					}
					 
				}, 'json');
	}
	
	
	
	/*레이어 close*/
	function closePwdCheckLayer001(){

		$("#checkPwd").val("");										// 레이어 패스워드 초기화
		$('#common_PwdCheck_layer').hide();
	 	$('#opacity').hide();
	}
	
	/*레이어 회원/회사 개인정보 조회시 close*/
	function closePwdCheckLayer002(){

		$("#checkPwd").val("");										// 레이어 패스워드 초기화
		$('#common_PwdCheck_layer').hide();
	}
	
	/* 레이어호출 */
	function _commonPwdCheckLayer(){
		_setCommonPwdCheckLayerEvent();
	}
</script>
<div class="pop_layer_warp" id="common_PwdCheck_layer" style="width:400px; height:140px; display:none;">
    <h1>비밀번호 확인</h1>
	<spring:message code="common.close" var="commonclose"/> <%-- 닫기 --%>
	<a href="#" class="close"><i class="fa fa-times" style="color:white;"></i></a>	
	
	<!-- search-result Start -->
	<div id="pop_content" class="open_content">
	
			<div id="pop_section">
			
				<div class="tit_area">
					<h2>비밀번호 확인</h2>
					<div class="btn_l">
						<html:button id="btnPwCheck" auth="select" value="확 인" css="" />
					</div>
				</div>
				
				
				<div class="table_type1">
				<form id="checkPwdForm" name="checkPwdForm" method="post">
				<input type="hidden" id="searchType" name="searchType">
				<table class="type1" summary="비밀번호 확인">
					<caption>비밀번호 확인</caption>
					<colgroup>
						<col width='40%'>
						<col width='*'>
					</colgroup>
					<tbody>
						<tr>
							<th scope="col"><label><strong style="width:150px;">비밀번호 확인</strong></label></th>
							<td >
								<input type="password" id="checkPwd" name="checkPwd" style = "width:100%;"/>
							</td>
						</tr>
					</tbody>
				</table>
				</form>
				</div>
				
				
				
			</div>
			
		</div>
		<!-- sub-con End -->
		

</div>