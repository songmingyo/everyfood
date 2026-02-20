<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<%--
	ClassName :  comChgPw.jsp
	Description : 비밀번호 변경 팝업
	Modification information
	수정일 			수정자					수정내용
	--------- 	----------------		-----------------
	2023.02.06		안석진				 최초 작성
	
	Author 	: asj
	Since 	: 2023.02
	
--%>
<style>
.pwPlaceholder::placeholder{
	font-size:13px !important;
}
</style>
<script type="text/javascript">
var ${param.popupId} = (function(){

	${param.popupId}_m = {};
	const _AUTO_MODE = 0x01;	// 부모창에서 key가 되는 값을 넘겨 검색했을 때 결과가 1개인 경우 자동으로 팝업 닫히고 부모창에 검색 결과를 넘겨줌
	const _MANUAL_MODE = 0x02;	// 자동으로 팝업이 닫히지 않음

	${param.popupId}_m._id = "${param.popupId}Layer";	
	${param.popupId}_m._POP_MODE = _MANUAL_MODE;
	${param.popupId}_m.callbackFuncNm = "";
	${param.popupId}_m._callback = false;
	${param.popupId}_m._callbackFunc = function(){};
	${param.popupId}_m._isManual = false;									// 사용자가 버튼을 눌러 검색했는지의 여부
	${param.popupId}_m._isRtnOneData="";
	
	//팝업속성 지정
	${param.popupId}_m._setConfig = function(arg){

		arg.css({
			"top" : $(window).scrollTop() + 10
			, "left": '40%'
			, "margin-left": '-228px'
			, "width": '800px'
		});

		var popCnt = 0;
		$(".pop_layer_new").each(function (){
			if($(this).css('display') === 'block'){
				popCnt++
			}
		});

		// 팝업레이어 및 백그라운드 활성
		$('#'+${param.popupId}_m._id).show();
		$(".pop_layer_new").each(function (){
			if($(this).prop("id") != ${param.popupId}_m._id && $(this).css('display') === 'block'){
				$(this).css('z-index', Number($(this).css('z-index')) - 1000);
			}
		});
		if(popCnt == 0){
			$('#opacity').show();
		}

		// 레이어 드레그 Event
		arg.draggable({
			handle: $('#'+${param.popupId}_m._id + " h1")
			,cancle: $('#'+${param.popupId}_m._id + " a.close")
			,containment: '#'+${param.popupId}_m._id + " #opacity"
			,scroll: false
		});

		// 레이어 닫기버튼 Click Event
		$('#'+${param.popupId}_m._id).find('#${param.popupId}_btnClose').unbind().click(function(e){
			${param.popupId}_m._closeLayer();
		});
	};

	// 레이어호출 이벤트
	${param.popupId}_m.fnOpenLayer = function(callbackFunc, opt, isRtnOneData,isMultiSelect) {
		${param.popupId}_m._fncInitForm();

		if (callbackFunc && typeof callbackFunc === 'function'){
			${param.popupId}_m._callback = true;
			${param.popupId}_m._callbackFunc = callbackFunc;
		}else{
			${param.popupId}_m._callback = false;
			${param.popupId}_m._callbackFunc = function(){};
		}

		if (opt){
			var _optKey = Object.keys(opt);
			for (var key in _optKey){
				$('#'+${param.popupId}_m._id).find("#${param.popupId}_"+_optKey[key]).val(opt[_optKey[key]]);
			}
		}
		
		// 팝업레이어 특성셋팅
		${param.popupId}_m._setConfig($('#'+${param.popupId}_m._id));

		// 버튼이벤트
		$('#'+${param.popupId}_m._id).find('#${param.popupId}_btnUpdatePw').unbind().click(function (){${param.popupId}_m._eventUpdatePw(this)}); 	// 변경버튼
		
		$('#${param.popupId}Form').validate({
			rules: {
				${param.popupId}_newPw   	: { required: true, minlength : 8 , maxlength:25, notInc: $('#${param.popupId}_email').val(), exprChkNew: true},
	        	${param.popupId}_newPwChk  	: { required: true, noSpace: true, equalTo: "#${param.popupId}_newPw", maxlength:20 },
	        }
			,messages: {
				${param.popupId}_newPw   	: {	 required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code='app.manager.memberviewlist._eventsave.a3' /><div>" <!-- 비밀번호를 입력하세요!  	-->
	    					    ,notInc:  "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code='app.common.ou.login.alert.valid.pwd'/><div>" 		  <!-- 비밀번호에 아이디 및 공백은 포함할 수 없습니다. -->
	    					    ,exprChkNew: "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code='app.common.ou.login.alert.valid.pwd3'/><div>" 	  <!-- 비밀번호는 숫자와 영문자,특수문자를 혼용하여 8~25글자를 입력해야 합니다. -->
	    					  }
	    		,${param.popupId}_newPwChk  : {required : "<div class='validate'><i class='fa fa-info-circle'></i> <spring:message code='app.manager.memberviewlist._eventsave.a4'/><div>"	}<!-- 비밀번호를 입력하세요!  	-->
			}
			
		});
		

	};

	// 폼 초기화
	${param.popupId}_m._fncInitForm = function() {
		// data 초기화
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_memberCd").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_genDivnCd").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_userPw").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_newPw").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_newPwChk").val('');
	};

	// 개인정보 조회이력 저장 & 조회
	${param.popupId}_m._eventUpdatePw = function(elem) {

		if(elem && typeof elem !== undefined){
			${param.popupId}_m._isManual = true;
		}else{
			${param.popupId}_m._isManual = false;
		}
		
		if($('#${param.popupId}Form').valid()){
			
			var memberCd  	= $.trim($("#${param.popupId}_memberCd").val());
			var userPw  	= $.trim($("#${param.popupId}_userPw").val());
			var newPw  		= $.trim($("#${param.popupId}_newPw").val());
			var newPwChk 	= $.trim($("#${param.popupId}_newPwChk").val());
			
			if(memberCd ==""){
				alert("계정정보가 존재하지 않습니다. 비밀번호 변경이 불가합니다.");
				return false;
			}	
			
			var param = {'memberCd':memberCd,'userPw':userPw,'newPw':newPw,'newPwChk':newPwChk,'accessType':"MY"};
			
			$.ajax({
			      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			      url  : "<c:url value='/front/common/updatePwdInfo.json'/>",
			      data : JSON.stringify(param),
			      success : function(data){
			    	  if(data.msgCd =="success"){
		    		    alert("<spring:message code='message.success.process'/>");	//정상적으로 처리되었습니다.
			    		${param.popupId}_m._closeLayer();// 창닫기
			    		
			 	      }else if(data.msgCd =="busexception"){
			 	    	  switch(data.resultCode){
			 	    	  	case "noAuth":
			 	    	  		alert("입점상담 시스템 전용계정이 아닙니다. 비밀번호를 변경할 수 없습니다.");
			 	    	  		return false;
			 	    	  	case "withDrawFg":
			 	    	  		alert("탈퇴처리된 회원입니다. 비밀번호를 변경할 수 없습니다.");
			 	    	  		return false;
			 	    	  	case "incId":
			 	    	  		alert("비밀번호에 아이디는 포함할 수 없습니다.");
			 	    	  		return false;
			 	    	  	case "notMatch":
			 	    	  		alert("비밀번호는 숫자와 영문자,특수문자를 혼용하여 8~25글자를 입력해야 합니다.");
			 	    	  		return false;
			 	    		case "noData":
			 	    			alert("사용자 정보가 없습니다. 비밀번호 변경이 불가합니다.");
			 	    	  		return false;
			 	    		case"verifyFail":
			 	    			alert("현재 비밀번호가 일치하지 않습니다.\n비밀번호 변경이 불가합니다.");
			 	    			return false;
			 	    	  }
			 	      }else{
			 	    	alert("<spring:message code='message.error.process'/>");		//처리 중 오류가 발생하였습니다.
			 	      }
			      }
			});
			
		}
		
	};
	
     
	// 팝업레이어 닫기
	${param.popupId}_m._closeLayer = function() {

		$(".pop_layer_new").each(function (){
			if($(this).prop("id") != ${param.popupId}_m._id && $(this).css('display') === 'block'){
				$(this).css('z-index', Number($(this).css('z-index')) + 1000);
			}
		});
		${param.popupId}_m._fncInitForm();

		$('*').filter(function(){
			return $(this).data('tooltipsterNs');
		}).tooltipster('hide');
		
		// 그리드 데이터 클리어
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_tabList").clearGridData();

		// 팝업레이어 및 백그라운드 숨김
		$('#'+${param.popupId}_m._id).hide();

		var popCnt = 0;
		$(".pop_layer_new").each(function (){
			if($(this).css('display') === 'block'){
				popCnt++
			}
		});

		if(popCnt == 0){
			$('#opacity').hide();
		}

        $("html").css({"overflow": ''});	// 스크롤 생성
	};

	return ${param.popupId}_m;
})();
</script>

<div class="pop_layer_new" id="${param.popupId}Layer" style="display:none;">
    <h1>비밀번호 변경</h1>
	<a id="${param.popupId}_btnClose" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
		<div id="pop_section">
			<div class="tit_area">
				<h2>비밀번호 변경</h2>
				<div class="btn_l">
					<html:button id="${param.popupId}_btnUpdatePw" auth="update"   value="변경"/>			
				</div>
			</div>
			<div >
				<form id="${param.popupId}Form" name="${param.popupId}Form" method="post">
					<input type="hidden" id="${param.popupId}_memberCd" name="${param.popupId}_memberCd"/>
					<input type="hidden" id="${param.popupId}_email" name="${param.popupId}_email"/>
					<input type="hidden" id="${param.popupId}_genDivnCd" name="${param.popupId}_genDivnCd"/>
		            <table class="type1">
						<colgroup>
							<col width="100"/>
							<col width="250"/>
						</colgroup>
						<tbody>
							<tr>
								<th class="req">현재 비밀번호</th>
								<td>
									<input type="password" class="pwPlaceholder" placeholder="현재 비밀번호 입력" id="${param.popupId}_userPw" name="${param.popupId}_userPw" autocomplete="new-password"/>
								</td>
							</tr>
							<tr>
								<th class="req">신규 비밀번호</th>
								<td>
									<input type="password" class="pwPlaceholder" placeholder="새 비밀번호 입력" id="${param.popupId}_newPw" name="${param.popupId}_newPw" style="width: 70%;"/>
								</td>
							</tr>
							<tr>
								<th class="req">신규 비밀번호 확인</th>
								<td>
									<input type="password" class="pwPlaceholder" placeholder="새 비밀번호 입력" id="${param.popupId}_newPwChk" name="${param.popupId}_newPwChk" style="width: 70%;"/>
								</td>
							</tr>
						</tbody>
					</table>
	            </form>
            </div>
		</div>
	</div>
</div>
