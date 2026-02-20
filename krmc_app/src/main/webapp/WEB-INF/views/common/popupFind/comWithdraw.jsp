<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<%--
	ClassName :  comWithdraw.jsp
	Description : 탈퇴하기 팝업
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
.withDrawInfo_ul{margin:15px 15px 15px 24px;} 
.withDrawInfo_ul> li{list-style:disc !important; font-weight:bold !important; font-size:14px; margin-bottom:15px; color:#c70101;}
 
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
		$('#'+${param.popupId}_m._id).find('#${param.popupId}_btnDelMem').unbind().click(function (){${param.popupId}_m._eventDelMem(this)}); 	// 탈퇴버튼
		
		// 키 이벤트
		$("#${param.popupId}_userPw").keydown(function(){
			if (event.keyCode === 13) {
				${param.popupId}_m._eventDelMem();
		    }
		});
	};

	// 폼 초기화
	${param.popupId}_m._fncInitForm = function() {
		// data 초기화
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_memberCd").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_userPw").val('');
	};

	// 탈퇴처리
	${param.popupId}_m._eventDelMem = function(elem) {

		if(elem && typeof elem !== undefined){
			${param.popupId}_m._isManual = true;
		}else{
			${param.popupId}_m._isManual = false;
		}
	
		var memberCd  	= $.trim($("#${param.popupId}_memberCd").val());
		var userPw  	= $.trim($("#${param.popupId}_userPw").val());
		
		if(memberCd ==""){
			alert("계정정보가 존재하지 않습니다. 탈퇴처리가 불가합니다.");
			return false;
		}	
		
		if(userPw ==""){
			alert("비밀번호를 입력하세요");
			$("#${param.popupId}_userPw").focus();
			return false;
		}	
		
		var param = {'memberCd':memberCd,'userPw':userPw,'accessType':'MY'};
		
		if (!confirm("시스템 탈퇴시 추후 재가입이 필요합니다.\n탈퇴하시겠습니까?")){ return false;}
		
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : "<c:url value='/front/common/updateMemUseYn.json'/>",
		      data : JSON.stringify(param),
		      success : function(data){
					if(data != null){
						var msgCd = data.msgCd;
						
						switch(msgCd){
						case "success":
							alert("정상적으로 삭제되었습니다.");
							if (${param.popupId}_m._callback) ${param.popupId}_m._callbackFunc(msgCd); // 조회된데이터 콜백
							${param.popupId}_m._closeLayer();// 창닫기
							
							break;
						case "busexception":
							var resultCode = data.resultCode;
							if(resultCode == "002"){
								alert("진행중인 제안건이 존재합니다.\n탈퇴가 불가합니다.");	<%--002, 진행중인 제안건이 존재합니다.--%>
								break;
							}else if(resultCode =="003"){
								alert(data.message);	<%--003, 비밀번호 불일치--%>
								break;
							}else{
								alert('<spring:message code="message.error.process" />');					<%-- 처리중 오류가 발생 하였습니다.--%>
								break;
							}
						default :
							alert('<spring:message code="message.error.process" />');					<%-- 처리중 오류가 발생 하였습니다.--%>
							break;
						}
					}else{
						alert('<spring:message code="message.error.process" />');					<%-- 처리중 오류가 발생 하였습니다.--%>
					}
		      }
		});
	};
	
     
	// 팝업레이어 닫기
	${param.popupId}_m._closeLayer = function() {

		$(".pop_layer_new").each(function (){
			if($(this).prop("id") != ${param.popupId}_m._id && $(this).css('display') === 'block'){
				$(this).css('z-index', Number($(this).css('z-index')) + 1000);
			}
		});
		${param.popupId}_m._fncInitForm();

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
    <h1>시스템 탈퇴</h1>
	<a id="${param.popupId}_btnClose" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
		<div id="pop_section">
			<div>
				<ul class="withDrawInfo_ul">
					<li>시스템 탈퇴 시 추후 재가입이 필요합니다.</li>
					<li>진행중인 제안건이 있는 경우 탈퇴가 불가 합니다.</li>
				</ul>
			</div>
			<div>
				<form id="${param.popupId}Form" name="${param.popupId}Form" method="post">
					<input type="hidden" id="${param.popupId}_memberCd" name="${param.popupId}_memberCd"/>
		            <table class="type1">
						<colgroup>
							<col width="100"/>
							<col width="250"/>
						</colgroup>
						<tbody>
							<tr>
								<th>비밀번호</th>
								<td>
									<input type="password" class="pwPlaceholder" placeholder="현재 비밀번호 입력" id="${param.popupId}_userPw" name="${param.popupId}_userPw" autocomplete="new-password"/>
									<html:button id="${param.popupId}_btnDelMem" name="${param.popupId}_btnDelMem" auth="delete"  value="탈퇴"/>
								</td>
							</tr>
						</tbody>
					</table>
	            </form>
            </div>
		</div>
	</div>
</div>