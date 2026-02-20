<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<%--
	ClassName :  comTermsViewLayer.jsp
	Description : 약관동의 정보 조회
	Modification information
	수정일 			수정자					수정내용
	--------- 	----------------		-----------------
	2023.02.17		안석진				 최초 작성
	
	Author 	: asj
	Since 	: 2023.02
	
--%>
<spring:message code="common.select" var="select" />
<style>
.termsContent{
	border: 1px solid #ddd;
    height: 600px;
    overflow-y: auto;
    padding: 3% 4%;
    margin: 10px 0px;
}

.termsHeader{
	font-size: 15px;
    margin: 0px;
    font-weight: bold;
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
		${param.popupId}_m._eventSearch();
	};

	// 폼 초기화
	${param.popupId}_m._fncInitForm = function() {
		// data 초기화
		$("#${param.popupId}_termsBody").empty();
	};

	// 약관조회
	${param.popupId}_m._eventSearch = function() {
	
		var termsDivnCd			= $.trim($("#${param.popupId}Form #${param.popupId}_termsDivnCd").val());
		var extent01			= $.trim($("#${param.popupId}Form #${param.popupId}_extent01").val());
		
		if(termsDivnCd==""){
			alert("약관구분 정보가 없습니다.\n 관리자에게 문의해주세요.");
			return false;
		}
		
		var searchInfo = {'termsDivnCd':termsDivnCd,'extent01':extent01};
		
		var url = "<c:url value='/"+pgAccessGbn+"/common/selTermsList.json'/>";
		
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
 		      url  : url,
		      data : JSON.stringify(searchInfo),
		      success : function(data){
		    	  if(data!=null){
		    		  ${param.popupId}_m._fncSetTermsInfo(data);
		    	  }else{
		  	    	alert("<spring:message code='message.error.process'/>");		//처리 중 오류가 발생하였습니다.
		  	    	return false;
		    	  }
		    	  
		      }
		});
		
	};
	
	// 약관 정보 세팅
	${param.popupId}_m._fncSetTermsInfo = function(terms){
		for(var i=0; i<terms.length; i++){
			terms[i].cnt = i+1;
		}
		
		$("#${param.popupId}_termsBodyTmpl").tmpl(terms).appendTo("#${param.popupId}_termsBody");
	};
	
	//확인
	${param.popupId}_m._fncConfirm = function(){
     	${param.popupId}_m._closeLayer();// 창닫기
	};
     
	// 팝업레이어 닫기
	${param.popupId}_m._closeLayer = function() {

		$(".pop_layer_new").each(function (){
			if($(this).prop("id") != ${param.popupId}_m._id && $(this).css('display') === 'block'){
				$(this).css('z-index', Number($(this).css('z-index')) + 1000);
			}
		});
		
		${param.popupId}_m._fncInitForm();
		

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

<%-- 본문,(동의여부) --%>
<script id="${param.popupId}_termsBodyTmpl" type="text/x-juery-tmpl">
<h3 id="${param.popupId}_termsHeader" class="termsHeader">
	<c:out value='\${cnt}'/>.<c:out value='\${termsVersionInfo}'/>
	<input type="hidden" name="termsDivnCd" value="<c:out value='\${termsDivnCd}'/>"/>
</h3>
<div id="${param.popupId}_termsContent" class="termsContent">
	{%html termsContent%}
</div>
<%--<div id="${param.popupId}_termsAgreeTF">
	<input type="checkbox" id="${param.popupId}_termsAgreeY" name="${param.popupId}_termsAgreeY" value="Y"/>
	<label for="${param.popupId}_termsAgreeY">동의함</label>
	<input type="checkbox" id="${param.popupId}_termsAgreeN_\${cnt}" name="${param.popupId}_termsAgreeN" value="N"/>
	<label for="${param.popupId}_termsAgreeN">동의하지 않음</label>
</div>--%>
</script>

<div class="pop_layer_new" id="${param.popupId}Layer" style="display:none;">
    <h1>약관 조회</h1>
	<a id="${param.popupId}_btnClose" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
		<div id="pop_section">
			<div class="tit_area">
				<h2>약관 조회</h2>
			</div>
			<div>
				<form id="${param.popupId}Form" name="${param.popupId}Form" method="post">
					<input type="hidden" id="${param.popupId}_termsDivnCd" name="termsDivnCd"/>
					<input type="hidden" id="${param.popupId}_extent01" name="extent01"/>
					<div id="${param.popupId}_termsBody"></div>
	            </form>
            </div>
		</div>
	</div>
</div>
