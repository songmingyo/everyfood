<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<%--
	ClassName : comFindKsicCatCd.jsp
	Description : 표준산업분류(업종, 업태) 찾기 팝업
	Modification information
	수정일 			수정자					수정내용
	--------- 		----------------		-----------------
	2023.01.12		윤지아					최초생성

	Author 	: yja
	Since 	: 2023.01

	* 팝업창을 구성하는 검색조건, 그리드의 헤더 구성 등만 변경하면 됩니다.
	* 팝업간 변수명 충돌방지를 위해 ${param.popupId} 를 이용합니다.
	
--%>

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
			"top" : $(window).scrollTop() + 100
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
	${param.popupId}_m.fnOpenLayer = function(callbackFunc, opt, isRtnOneData, isMultiSelect) {
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
				if(_optKey[key] == "srchKsicMedCatCd"){
					$('#'+${param.popupId}_m._id).find("#${param.popupId}_srchKsicMedCatCdData").val(opt[_optKey[key]]);
				}
			}
		}

// 결과값이 하나일때 처리 해당팝업에서는 사용안함
// 		if (isRtnOneData){
// 			${param.popupId}_m._isRtnOneData = (isRtnOneData == "Y"||isRtnOneData == "y"||isRtnOneData == true)? true:false;
// 		}

		// 팝업레이어 특성셋팅
		${param.popupId}_m._setConfig($('#'+${param.popupId}_m._id));

		// 버튼이벤트
		$('#'+${param.popupId}_m._id).find('#${param.popupId}_btnSearch').unbind().click(function (){${param.popupId}_m._eventSearch(this)}); 	// 조회버튼

// 		$('#'+${param.popupId}_m._id).find('#${param.popupId}_srchKsicLarCatCd, #${param.popupId}_srchKsicMedCatCd').unbind().change(function(e) {
// 			switch(e.which) {
// 		    	case 13 : ${param.popupId}_m._eventSearch(this); break; // enter
// 		    	default : return true;
// 	    	}
// 	    	e.preventDefault();
// 	   	});
		
		// 중분류 셋팅
		${param.popupId}_m._fncSetKsicMedCatCd();
		
		// 조회
		${param.popupId}_m._eventSearch();
		
		/* 페이지번호 Click Event 발생시 조회함수 호출하다. */
		$(document).on('click', '#${param.popupId}_paging a' , function(e){
			// #page : 서버로 보내는 Hidden Input Value
			$('#${param.popupId}_page').val($(this).attr('link'));
			// 개발자가 만든 조회 함수
			${param.popupId}_m._eventSearch();
		});
		
		$(document).on('change', '#${param.popupId}_srchKsicLarCatCd', function(e){
			$("#${param.popupId}_srchKsicMedCatCd").val("");			
			$("#${param.popupId}_srchKsicMedCatCdData").val("");			
			//중분류 셋팅
			${param.popupId}_m._fncSetKsicMedCatCd();
			//코드 조회
			${param.popupId}_m._eventSearch();
		});
		
		$(document).on('change', '#${param.popupId}_srchKsicMedCatCd', function(e){
			$("#${param.popupId}_srchKsicMedCatCdData").val($.trim($(this).val()));
		});
		
// 		if($.trim(isMultiSelect)==""|| $.trim(isMultiSelect)==null || isMultiSelect =="N"){
// 			$('#'+${param.popupId}_m._id).find("#${param.popupId}_tabList").jqGrid('setGridParam',{multiboxonly : true});
// 			$('#'+${param.popupId}_m._id).find("#${param.popupId}_tabList").jqGrid('hideCol', 'cb');
// 			$("#${param.popupId}_btnApply").hide();
// 		}
	};

	// 폼 초기화
	${param.popupId}_m._fncInitForm = function() {
		// 검색조건 초기화
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_srchKsicLarCatCd").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_srchKsicMedCatCd").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_srchKsicMedCatCdData").val('');
	};

	// 코드조회
	${param.popupId}_m._eventSearch = function(elem) {
		if(elem && typeof elem !== undefined){
			${param.popupId}_m._isManual = true;
		}else{
			${param.popupId}_m._isManual = false;
		}
		
		var page = $.trim($("#${param.popupId}_page").val()); //검색결과 페이지
		var srchKsicLarCatCd 	 = $.trim($("#${param.popupId}_srchKsicLarCatCd").val());	//대분류 코드
		var srchKsicMedCatCd 	 = $.trim($("#${param.popupId}_srchKsicMedCatCdData").val());	//중분류 코드
		var searchInfo = {
			'page': page
			,'srchKsicLarCatCd' : srchKsicLarCatCd
			,'srchKsicMedCatCd' : srchKsicMedCatCd
		};
		
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : "<c:url value='/web/common/selectTlsKsicList.json'/>",
		      data : JSON.stringify(searchInfo),
		      success : function(data){
		    	  ${param.popupId}_m._fncSetSrchTbody(data);
		      }
		      
		});
		
	};
	
	//리스트 조회결과 세팅
	${param.popupId}_m._fncSetSrchTbody=function(json){
		var data = json.resultList;
		setTbodyInit("${param.popupId}_dataListbody");	// dataList 초기화
		
		if(data != null && data.length > 0){
			var size = data.length;
			var page = json.paging;
			
			for(var k=0;k<size;k++){
				data[k].count = k+1;
			}
			
			$("#${param.popupId}_dataListTemplate").tmpl(data).appendTo("#${param.popupId}_dataListbody");
			$('#${param.popupId}_paging-tmpl').tmpl(page.list).insertBefore($('#${param.popupId}_paging').empty()).appendTo('#${param.popupId}_paging');
			
		}else{
			var msg = '<spring:message code="message.search.no.data" />';	<%-- 조회 결과가 없습니다 --%>
			setTbodyNoResult("${param.popupId}_dataListbody",3,msg);
			
			var emptyPages = {};
			emptyPages.cl = 'on';
			emptyPages.pageNumber = '1';
			emptyPages.linkPageNumber = '1';
			$('#${param.popupId}_paging-tmpl').tmpl(emptyPages).insertBefore($('#${param.popupId}_paging').empty()).appendTo('#${param.popupId}_paging');
			return;
		}
	};
	
	// 중분류 코드 셋팅
	${param.popupId}_m._fncSetKsicMedCatCd = function(){
		//clear area
		$("#${param.popupId}_srchKsicMedCatCd").empty();
		
		//대분류코드
		var srchKsicLarCatCd = $.trim($("#${param.popupId}_srchKsicLarCatCd").val());
		var srchKsicMedCatCd = $.trim($("#${param.popupId}_srchKsicMedCatCdData").val());

		getCustomSubCodeListToSelBox("${param.popupId}_srchKsicMedCatCd", "선택", srchKsicMedCatCd, "5800", srchKsicLarCatCd);
	}
	
	// 선택 데이터 콜백
	${param.popupId}_m._fncSelDataCallBack = function(obj) {
		var rowdata={};
		
		$(obj).find('input').each(function(i){
			if(this.type !="button" && this.name != ""){
				rowdata[this.name] = $.trim($(this).val());
			}
		});
		
		 if (${param.popupId}_m._callback) ${param.popupId}_m._callbackFunc(rowdata); // 선택데이터 콜백
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

<%-- dataListTemplate --%>
<script id="${param.popupId}_dataListTemplate"  type="text/x-jquery-tmpl">
	<tr onclick="${param.popupId}_m._fncSelDataCallBack(this)"">
		<td class="tdm">\${rnum}</td>
		<td class="tdm">
			<c:out value='\${ksicLarCatCdNm}'/>
			<input type="hidden" id="${param.popupId}_ksicLarCatCd_\${rnum}" name="ksicLarCatCd" value="<c:out value='\${ksicLarCatCd}'/>"/>
			<input type="hidden" id="${param.popupId}_ksicLarCatCdNm_\${rnum}" name="ksicLarCatCdNm" value="<c:out value='\${ksicLarCatCdNm}'/>"/>
		</td>
		<td>
			[<c:out value='\${ksicMedCatCd}'/>] <c:out value='\${ksicMedCatCdNm}'/>
			<input type="hidden" id="${param.popupId}_ksicMedCatCd_\${rnum}" name="ksicMedCatCd" value="<c:out value='\${ksicMedCatCd}'/>"/>
			<input type="hidden" id="${param.popupId}_ksicMedCatCdNm_\${rnum}" name="ksicMedCatCdNm" value="<c:out value='\${ksicMedCatCdNm}'/>"/>
		</td>
	</tr>
</script>

<%-- paging template --%>
<script  id="${param.popupId}_paging-tmpl" type="text/x-jquery-tmpl">
	{%if pageNumber == '<<'%}
		<a href="javascript:;" class="btn" link="\${linkPageNumber}"><img src="/resources/images/pearl/common/btn_first.gif" alt="${first}" /></a>
	{%elif pageNumber == '<'%}
		<a href="javascript:;" class="btn" link="\${linkPageNumber}"><img src="/resources/images/pearl/common/btn_pre.gif" alt="${prev}" /></a>
	{%elif pageNumber == '>'%}
		<a href="javascript:;" class="btn" link="\${linkPageNumber}"><img src="/resources/images/pearl/common/btn_next.gif" alt="${next}" /></a>
	{%elif pageNumber == '>>'%}
		<a href="javascript:;" class="btn" link="\${linkPageNumber}"><img src="/resources/images/pearl/common/btn_last.gif"  alt="${last}" /></a>
	{%else%}
		<a href="javascript:;" class="\${cl}" link="\${linkPageNumber}" title='\${pageNumber}'><c:out value='\${pageNumber}'/></a>
	{%/if %}
</script>

<div class="pop_layer_new" id="${param.popupId}Layer" style="display:none;">
    <h1>표준산업분류 검색</h1>
	<a id="${param.popupId}_btnClose" class="close" style="cursor:pointer;"><i class="fa fa-times" style="color:white;"></i></a>

	<div id="pop_content" class="open_content">
		<div id="pop_section">
			<div>
				<div class="tit_area">
					<h2>업종,업태 검색</h2>
					<div class="btn_l">
						<html:button id="${param.popupId}_btnSearch" auth="select" msgKey="button.search"/>
					</div>
				</div>
				<form id="${param.popupId}Form" name="${param.popupId}Form" method="post">
				<sec:csrfInput/>
				<input type="hidden" name="page" id="${param.popupId}_page"	value="1" />
				<input type="hidden" name="${param.popupId}_srchKsicMedCatCdData" id="${param.popupId}_srchKsicMedCatCdData" value=""/>
				<table class="type1">
					<colgroup>
						<col width="15%"/>
						<col width="35%"/>
						<col width="15%"/>
						<col width="35%"/>
					</colgroup>
					<tbody>
						<tr>
							<th>업종</th>
							<td><html:codeTag comType="SELECT" objName="${param.popupId}_srchKsicLarCatCd" parentID="5800" objId="${param.popupId}_srchKsicLarCatCd" defValue="" defName="선택" width="260px"/></td>
							<th>업태</th>
							<td>
								<select id="${param.popupId}_srchKsicMedCatCd" name="${param.popupId}_srchKsicMedCatCd" class="form-control" style="width:260px;">
									<option value="">선택</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
				<table class="type3" style="margin-top:8px;">
					<colgroup>
						<col width="5%"/>
						<col width="35%"/>
						<col width="60%"/>
					</colgroup>
					<thead>
						<tr>
							<th>No.</th>
							<th>업종</th>
							<th>업태</th>
						</tr>
					</thead>
					<tbody id="${param.popupId}_dataListbody">
					</tbody>
				</table>
				<div style="margin-top: 10px;">
					<div class="paging" id="${param.popupId}_paging" style="padding: 0px;">
					</div>
				</div>
				</form>
            </div>
		</div>
	</div>
</div>