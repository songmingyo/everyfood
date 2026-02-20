<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<%--
	ClassName :  comPersInfoView.jsp
	Description : 개인정보 보기 팝업
	Modification information
	수정일 			수정자					수정내용
	--------- 	----------------		-----------------
	2023.01.12		안석진				 최초 작성
	
	Author 	: asj
	Since 	: 2023.01
	
--%>
<spring:message code="common.select" var="select" />
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
		$('#'+${param.popupId}_m._id).find('#${param.popupId}_btnSearch').unbind().click(function (){${param.popupId}_m._eventSearch(this)}); 	// 조회버튼

		$('#'+${param.popupId}_m._id).find('#${param.popupId}_pw').unbind().keydown(function(e) {
			switch(e.which) {
	    	case 13 : ${param.popupId}_m._eventSearch(this); break; // enter
	    	default : return true;
	    	}
	    	e.preventDefault();
	   	});
		
		${param.popupId}_m._fncSetForm();
	};

	// 폼 초기화
	${param.popupId}_m._fncInitForm = function() {
		// data 초기화
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_workGbn").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_persPclNo").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_rendCmt").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_rendDivnCd").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_pw").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_appliNo").val('');
		
		$("#${param.popupId}Layer").find("h1[data-type='persInfo'],h2[data-type='persInfo'],tr[data-type='persInfo']").show();
		$("#${param.popupId}Layer").find("h1[data-type='pwChk'],h2[data-type='pwChk'],tr[data-type='pwChk']").hide();
	};
	
	//workGbn에 따라 입력 form 변경
	${param.popupId}_m._fncSetForm = function() {
		var workGbn			= $.trim($("#${param.popupId}Form #${param.popupId}_workGbn").val());
		if(workGbn =="W09"||workGbn =="W10"){
			$("#${param.popupId}Layer").find("h1[data-type='persInfo'],h2[data-type='persInfo'],tr[data-type='persInfo']").hide();
			$("#${param.popupId}Layer").find("h1[data-type='pwChk'],h2[data-type='pwChk'],tr[data-type='pwChk']").show();
		}
	};

	// 개인정보 조회이력 저장 & 조회
	${param.popupId}_m._eventSearch = function(elem) {

		if(elem && typeof elem !== undefined){
			${param.popupId}_m._isManual = true;
		}else{
			${param.popupId}_m._isManual = false;
		}
	
		var workGbn			= $.trim($("#${param.popupId}Form #${param.popupId}_workGbn").val());
		var persPclNo  	 	= $.trim($("#${param.popupId}Form #${param.popupId}_persPclNo").val());
		var persGenDivnCd 	= $.trim($("#${param.popupId}Form #${param.popupId}_persGenDivnCd").val());
		var rendDivnCd		= $.trim($("#${param.popupId}Form select[name='rendDivnCd'] option:selected").val());
		var rendCmt 		= $.trim($("#${param.popupId}Form #${param.popupId}_rendCmt").val());
		var pw				= $.trim($("#${param.popupId}Form #${param.popupId}_pw").val());
		var appliNo			= $.trim($("#${param.popupId}Form #${param.popupId}_appliNo").val());
		
		if(workGbn=="W09"||workGbn=="W10"){
			rendDivnCd ="9016099"; //기타
			rendCmt="나의 정보 수정";
		}
		
		if(workGbn==""){
			alert("업무구분코드 정보가 없습니다. 개인정보 조회가 불가합니다.");
			return false;
		}
		
		if(persPclNo=="" ||persGenDivnCd==""){
			alert("열람대상 정보가 없습니다. 개인정보 조회가 불가합니다.");
			return false;
		}
		
		if(workGbn == "W08" && appliNo == ""){
			alert("제안번호가 존재하지 않습니다. 개인정보 조회가 불가합니다.");
			return false;
		}
		
		if(rendDivnCd==""){
			alert("열람사유 정보가 없습니다. 열람사유를 선택하세요.");
			$("#${param.popupId}Form select[name='rendDivnCd']").focus();
			return false;
		}
		
		if(rendDivnCd=="9016099" && rendCmt ==""){ //기타사유일때 열람사유 입력 필수
			alert("열람사유를 입력하세요.");
			$("#${param.popupId}Form input[name='rendCmt']").focus();
			return false;
		}
		
		if(pw==""){
			alert("비밀번호를 입력하세요.");
			$("#${param.popupId}Form #${param.popupId}_pw").focus();
			return false;
		}
		
		var searchInfo = {'workGbn':workGbn,'persPclNo':persPclNo,'persGenDivnCd':persGenDivnCd,'rendDivnCd' : rendDivnCd, 'rendCmt':rendCmt , 'pw':pw , 'appliNo':appliNo};
		
// 		var url = "<c:url value='/"+pgAccessGbn+"/common/insPersInfoLog.json'/>";
		
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
 		      url  : "<c:url value='/app/common/insPersInfoLog.json'/>",
// 			  url  : url,
		      data : JSON.stringify(searchInfo),
		      success : function(data){
		    	  if(data.msgCd =="success"){
		    		var cbData;
		    		
		    		if(workGbn == "W08"){	<%-- 입점제안관리 상세)업체부가정보 - 계좌정보 조회 --%>
		    			cbData = data.resultMap.bankVo;
		    		}else{
		    			cbData = data.resultMap.userVo;
		    		}
		    		  
		    		cbData.workGbn=workGbn;
		    		if (${param.popupId}_m._callback) ${param.popupId}_m._callbackFunc(cbData); // 조회된데이터 콜백
			    	${param.popupId}_m._closeLayer();// 창닫기
			    	
		 	      }else if(data.msgCd =="busexception"){
		 	    	 
		 	    	 if(data.resultCode=="ERR-01"){
		 	    		alert("비밀번호를 입력하세요.");
		 	    		
		 	    	 }else if(data.resultCode=="ERR-02"){
		 	    		alert("열람사유를 선택하세요.");
		 	    		
		 	    	 }else if(data.resultCode=="ERR-03"){
		 	    		alert("열람대상 정보가 없습니다. 개인정보 조회가 불가합니다.");
		 	    		 
		 	    	 }else if(data.resultCode=="ERR-04"){
		 	    		alert("업무구분 정보가 없습니다. 개인정보 조회가 불가합니다.");
		 	    		
		 	    	 }else if(data.resultCode=="ERR-05"){
		 	    		alert("비밀번호가 일치하지 않습니다. 개인정보 조회가 불가합니다.");
		 	    		 
		 	    	 }else if(data.resultCode=="ERR-06"){
		 	    		alert("열람대상의 회원정보가 없습니다. 개인정보 조회가 불가합니다.");
		 	    		
		 	    	 }else{
		 	    		alert("<spring:message code='message.error.process'/>");		//처리 중 오류가 발생하였습니다.
		 	    	 }
		 				
		 	      }else{
		 	    	alert("<spring:message code='message.error.process'/>");		//처리 중 오류가 발생하였습니다.
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
    <h1 data-type="persInfo">개인정보 보기</h1>
    <h1 data-type="pwChk" style="display:none;">비밀번호 확인</h1>
	<a id="${param.popupId}_btnClose" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
		<div id="pop_section">
			<div class="tit_area">
				<h2 data-type="persInfo">개인정보 보기</h2>
				<h2 data-type="pwChk" style="display:none;">비밀번호 확인</h2>
				<div class="btn_l">
					<html:button id="${param.popupId}_btnSearch" auth="select"   value="조회"/>			
				</div>
			</div>
			<div >
				<form id="${param.popupId}Form" name="${param.popupId}Form" method="post">
					<input type="hidden" id="${param.popupId}_workGbn" name="workGbn"/>
					<input type="hidden" id="${param.popupId}_persPclNo" name="persPclNo"/>
					<input type="hidden" id="${param.popupId}_persGenDivnCd" name="persGenDivnCd"/>
					<input type="hidden" id="${param.popupId}_appliNo" name="appliNo"/>
		            <table class="type1">
						<colgroup>
							<col width="100"/>
							<col width="250"/>
						</colgroup>
						<tbody>
							<tr data-type="persInfo">
								<th class="req">열람사유</th>
								<td>
									<html:codeTag comType="SELECT" objId="${param.popupId}_rendDivnCd" objName="rendDivnCd" parentID="9016"  defName="${select}" width="33%;"/>
									<input type="text" id="${param.popupId}_rendCmt" name="rendCmt" style="width: 65%;" placeholder="열람사유를 입력하세요." onkeyup="chkByte(this,200);"/>
								</td>
							</tr>
							<tr>
								<th class="req">비밀번호</th>
								<td>
									<input type="password" class="pwPlaceholder" id="${param.popupId}_pw" name="pw" style="width: 70%;" placeholder="비밀번호를 입력하세요."/>
									<!-- <input type="password" class="pwd form-control" placeholder="새 비밀번호 입력" id="userPw" name="userPw" autocomplete="new-password"/> -->
								</td>
							</tr>
						</tbody>
					</table>
	            </form>
            </div>
		</div>
	</div>
</div>
