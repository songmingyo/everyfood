<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<%--
	ClassName :  comLnkgMemList.jsp
	Description : 입점상담 계정연동 팝업
	Modification information
	수정일 			수정자					수정내용
	--------- 	----------------		-----------------
	2023.02.06		안석진				 최초 작성
	
	Author 	: asj
	Since 	: 2023.02
	
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
		
		//연동대상 리스토 조회
		${param.popupId}_m._eventSrchPlsMemList();

	};

	// 폼 초기화
	${param.popupId}_m._fncInitForm = function() {
		// data 초기화
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_memberCd").val('');
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_bmanNo").val('');
	};
	
	//연동대상 리스트 조회
	${param.popupId}_m._eventSrchPlsMemList= function(){
		
		var searchInfo = {};
		$('#${param.popupId}Form').find('input').map(function() {
			if(this.type !="button" && this.name != null && this.name != ""){
				var name = $.trim(this.name).split("_")[1];
				searchInfo[name] = $.trim($(this).val());
			}
		});
		
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : "<c:url value='/front/mmbr/mmbrMyInfo_selLnkgTargetPlsList.json'/>",
		      data : JSON.stringify(searchInfo),
		      success : function(data){
		    	  ${param.popupId}_m._fncSetSrchTbody(data);
		      }
		});
		
	};
	
	//조회결과 setting
	${param.popupId}_m._fncSetSrchTbody=function(json){
		var data = json.resultList;
		setTbodyInit("${param.popupId}_dataListbody");	// dataList 초기화
		
		if(data !=null){
			var size = data.length;
			var page 	= json.paging;
			
			for(var k=0;k<size;k++){
				data[k].count = k+1;
			}
			
			$("#${param.popupId}_dataListTemplate").tmpl(data).appendTo("#${param.popupId}_dataListbody");
			$('#${param.popupId}_paging-tmpl').tmpl(page.list).insertBefore($('#${param.popupId}_paging').empty()).appendTo('#${param.popupId}_paging');
			
		}else{
			var msg = '<spring:message code="message.search.no.data" />';	<%-- 조회 결과가 없습니다 --%>
			setTbodyNoResult("${param.popupId}_dataListbody",4,msg);
			
			//하단 페이지 초기화
			 var emptyPages = {};
	         emptyPages.cl = 'on';
	         emptyPages.pageNumber = '1';
	         emptyPages.linkPageNumber = '1';
	         
	         $('#${param.popupId}_paging-tmpl').tmpl(emptyPages).insertBefore($('#${param.popupId}_paging').empty()).appendTo('#${param.popupId}_paging');
	         
			return;
		}
		
	};
	
	<%--계정연동,연동해제--%>
	${param.popupId}_m._fncOpenLnkgMemVerify = function(obj) {
		
		var paramObj ={};
		var lnkgId = $.trim($(obj).closest("tr").find("td[data-Nm='lnkgId']").text());
		var lnkgMemberCd = $.trim($(obj).closest("tr").find("input[name='lnkgMemberCd']").val()); //연동대상 memberCd
		var memberCd = $.trim($("#${param.popupId}_memberCd").val());							  //로그인 사용자(SCM) memberCd
		var name =$.trim(obj.name).split("_")[1];
		var type="";
		
		if(name=="btnNewLnkg"){
			type = "N";
		}else if(name=="btnDelLnkg"){
			type = "D";
		}
		
		//동작 구분[N:계정연동,D:연동해제]
		paramObj.lnkgId = lnkgId;
		paramObj.lnkgMemberCd = lnkgMemberCd;
		paramObj.memberCd = memberCd;
		paramObj.type = type;
		
		popComLnkgMemVerify.fnOpenLayer(${param.popupId}_m._fncOpenLnkgMemVerifyCallBack,paramObj,'',null);
	};
	
	<%-- 계정연동/해제 콜백--%>
	${param.popupId}_m._fncOpenLnkgMemVerifyCallBack = function(data){
		if(data.msgCd=="success"){
			${param.popupId}_m._eventSrchPlsMemList(); //연동대상 리스트 재조회
		}
	}
	
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


<script id="${param.popupId}_dataListTemplate"  type="text/x-jquery-tmpl">
<tr>
	<td class="tdm"><c:out value="\${rnum}"/></td>
	<td data-Nm='lnkgId'>
		<c:out value="\${userIdMsk}"/>
		<input type="hidden" name="lnkgMemberCd" value="<c:out value='\${lnkgMemberCd}'/>"/>
	</td>
	<td class="tdm">
		{%if regDtFmt!="" && regDtFmt!=null%}
			<c:out value="\${regDtFmt}"/>
		{%else%}
			-
		{%/if%}
	</td>
	<td class="tdm">
		{%if lnkgFlag=='N'%}
			<html:button id="${param.popupId}_btnNewLnkg\${rnum}" name="${param.popupId}_btnNewLnkg"   auth="update"   value="계정 연동" event="onclick='${param.popupId}_m._fncOpenLnkgMemVerify(this);'"/>
		{%elif lnkgFlag=='C'%}
			타 계정 연동중
		{%else%}
			<html:button id="${param.popupId}_btnDelLnkg_\${rnum}" name="${param.popupId}_btnDelLnkg"   auth="delete"  value="연동 해제" event="onclick='${param.popupId}_m._fncOpenLnkgMemVerify(this);'"/>
		{%/if%}
	</td>
</tr>
</script>

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
		<a href="javascript:;" class="\${cl}" link="\${linkPageNumber}" title='\${pageNumber}'>\${pageNumber}</a>
	{%/if %}
</script>
 
<div class="pop_layer_new" id="${param.popupId}Layer" style="display:none;">
    <h1>입점상담 계정 연동</h1>
	<a id="${param.popupId}_btnClose" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
		<div id="pop_section">
			<div class="tit_area">
				<h2 class="subhead">입점상담 계정 현황 List</h2>
			</div>
			<form id="${param.popupId}Form" name="${param.popupId}Form" method="post">
				<input type="hidden" id="${param.popupId}_memberCd" name="${param.popupId}_memberCd"/>
				<input type="hidden" id="${param.popupId}_bmanNo" name="${param.popupId}_bmanNo"/>
			</form>
			<!-- 조회결과 -->
			<table class="type3">
				<colgroup>
					<col width="5%"/>
					<col width="*"/>
					<col width="*"/>
					<col width="*"/>
				</colgroup>
				<thead>
					<tr>
						<th class="tdm">No.</th>
						<th class="tdm">입점상담 계정</th>
						<th class="tdm">연동일시</th>
						<th class="tdm">연동</th>
					</tr>
				</thead>
				<tbody id="${param.popupId}_dataListbody"></tbody>
			</table>		
			<!-- 조회결과 -->
		</div>
	</div>
	<div style="margin-top: 10px;">
		<div class="paging" id="${param.popupId}_paging" style="padding: 0px;"></div>
	</div>
</div>


<!-- 연동계정 인증 팝업-->
<jsp:include page="/WEB-INF/views/common/popupFind/comLnkgMemVerify.jsp">
	<jsp:param name="popupId" value="popComLnkgMemVerify"/>
</jsp:include>
<!-- 연동계정 인증 팝업-->