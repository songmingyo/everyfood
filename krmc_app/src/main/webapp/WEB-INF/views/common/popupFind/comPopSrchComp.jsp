<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<%--
	ClassName : comPopSrchComp.jsp
	Description : 업체 검색팝업 (부모창 input 입력값 받아옴)
	Modification information
	수정일 		수정자					수정내용
	--------- 	----------------		-----------------
	2022.12.25  ASJ


	Author 	: ASJ
	Since 	: 2022.12

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

		if (isRtnOneData){
			${param.popupId}_m._isRtnOneData = (isRtnOneData == "Y"||isRtnOneData == "y"||isRtnOneData == true)? true:false;
		}
		
		// 팝업레이어 특성셋팅
		${param.popupId}_m._setConfig($('#'+${param.popupId}_m._id));

		// 버튼이벤트
		
		$('#'+${param.popupId}_m._id).find('#${param.popupId}_btnSearch').unbind().click(function (){${param.popupId}_m._eventSearch(this)}); 	// 조회버튼

		$('#'+${param.popupId}_m._id).find('#${param.popupId}_srchCompNm').unbind().keydown(function(e) {
			switch(e.which) {
	    	case 13 : ${param.popupId}_m._eventSearch(this); break; // enter
	    	default : return true;
	    	}
	    	e.preventDefault();
	   	});

		// 그리드셋팅
		${param.popupId}_m._fncInitGrid();

		// 조회
		${param.popupId}_m._eventSearch();
		
		if($.trim(isMultiSelect)==""|| $.trim(isMultiSelect)==null || isMultiSelect =="N"){
			$('#'+${param.popupId}_m._id).find("#${param.popupId}_tabList").jqGrid('setGridParam',{multiboxonly : true});
			$('#'+${param.popupId}_m._id).find("#${param.popupId}_tabList").jqGrid('hideCol', 'cb');
			$("#${param.popupId}_btnApply").hide();
		}
	};

	// 폼 초기화
	${param.popupId}_m._fncInitForm = function() {
		// 검색조건 초기화
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_srchStrNmDtl").val('');
	};

	// 그리드 셋팅
	${param.popupId}_m._fncInitGrid = function() {
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_tabList").jqGrid({
	        datatype: 'local',
	        data: [],
	        colNames:[
				'<spring:message code="app.common.ComCompanyLayer.data.companyregnum" />'
				,'<spring:message code="app.common.ComCompanyLayer.data.companynm" />'
				,'<spring:message code="app.common.ComCompanyLayer.data.companyceonm" />'
				,'<spring:message code="app.common.ComCompanyLayer.data.local" />'
				,'<spring:message code="app.common.ComCompanyLayer.data.telno" />'
				,''
				,''
        ],

        colModel:[
            {name:'bmanNo'		   	, index:'BMAN_NO'				, sortable:true		, width:100, 	align:"right"	},
            {name:'compNm'   		, index:'COMP_NM'				, sortable:true		, width:150, 	align:"left"	},
            {name:'ceoNm'  	    	, index:'CEO_NM'				, sortable:true		, width:120, 	align:"left"	},
            {name:'zipAddr'  	    , index:'ZIP_ADDR'				, sortable:true		, width:250, 	align:"left"	},
            {name:'repTelNo'  	    , index:'REP_TEL_NO'			, sortable:false	, width:150, 	align:"left"	},
            {name:'masterId'  	    , index:'MASTER_ID'				, hidden:true		},
            {name:'corpnNo'  	    , index:'CORPN_NO'				, hidden:true		}

        ],
        gridComplete : function() {
			var colCount = $(this).getGridParam("colNames").length;
			$('#'+${param.popupId}_m._id).find("#${param.popupId}_blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");

			$(window).resize();
        },
        loadComplete: function() {
            if ($(this).getGridParam("records")==0) {
                
            	var firstColName = $(this).getGridParam("colModel")[1].name;
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                $(this).addRowData("${param.popupId}_blankRow", msg);
                
            }else{
            		var allRows = $('#'+${param.popupId}_m._id).find("#${param.popupId}_tabList").jqGrid('getDataIDs');

	 	            if(allRows.length == 1 && ${param.popupId}_m._isRtnOneData){
	 	            	var rowdata = $(this).jqGrid('getRowData', allRows[0]);
	 	            	if(allRows[0] == "${param.popupId}_blankRow"){
	 	            		return;
	 	            	}
	 	            	 // 선택데이터 콜백
	 		            if (${param.popupId}_m._callback) ${param.popupId}_m._callbackFunc(rowdata);
	 		            // 창닫기
	 		        	${param.popupId}_m._closeLayer();
	 	            }
            }

          	// 그리드사이즈 재조절
	        $('#'+${param.popupId}_m._id).find('#${param.popupId}_tabList').setGridWidth($('#'+${param.popupId}_m._id).find('#${param.popupId}_gridContainer').width());
			$(window).trigger('resize');
        },

        loadError:function(xhr, status, error) {
            return false;
        },
        onSelectRow : function(rowid, colld, val, e) {		//행 선택시 이벤트
        	if (rowid == '${param.popupId}_blankRow'){ return; }
          var rowdata = $(this).getRowData(rowid);		// 선택한 행의 데이터를 가져온다
         
		  if(!rowdata){
            	return;
            }

		  var col = $('#${param.popupId}_tabList').jqGrid("getGridParam","colModel");
	      var colName = col[1].name;
	      if(colName =="cb"){return;}	//체크박스 선택시 
	      
          // 선택데이터 콜백
          if (${param.popupId}_m._callback) ${param.popupId}_m._callbackFunc(rowdata);
          
          // 창닫기
        	${param.popupId}_m._closeLayer();
			
		},
        ondblClickRow: function(rowid, cellcontent, iCol, e) {
        
        },
		width : $('#'+${param.popupId}_m._id).find('#${param.popupId}_gridContainer').width(),
        height: 300,
		page: 1,															// 현재 페이지
		rowNum: 20,															// 한번에 출력되는 갯수
		//rowList:[100,500,1000],											// 한번에 출력되는 갯수 SelectBox
		pager: '#${param.popupId}_pageList',								// page가 보여질 div
		loadui : "disable",													// 이거 안 써주니 로딩 창 같은게 뜸
		emptyrecords : '<spring:message code="message.search.no.data" />',  // row가 없을 경우 출력 할 text
		gridview: true,														// 그리드 속도
		viewrecords: true,													// 하단에 1/1 또는 데이터가없습니다 추가
		rownumbers:true,													// rowNumber 표시여부
		sortorder: "DESC",                                       			// desc/asc
		loadonce : false,													// reload 여부. [true: 한번만 데이터를 받아오고 그 다음부터는 데이터를 받아오지 않음]
		multiselect	: true,													// 체크박스 show
		//scroll : false,														// 스크롤 페이징 여부
		autowidth:true,
		shrinkToFit:false,													// 컬럼 width 자동지정여부 (가로 스크롤 이용하기 위해 false지정)
		
	   });
	};

	// 코드조회
	${param.popupId}_m._eventSearch = function(elem) {

		if(elem && typeof elem !== undefined){
			${param.popupId}_m._isManual = true;
		}else{
			${param.popupId}_m._isManual = false;
		}
		var srchCompNm 	 = $.trim($("#${param.popupId}_srchCompNm").val());
		var searchInfo = {'srchCompNm' : srchCompNm};
		$("#${param.popupId}_tabList").jqGrid('clearGridData');

		$("#${param.popupId}_tabList").jqGrid('setGridParam',{
			 url:'<c:url value="/app/common/findCompany" />' 	// url 주소
			,datatype: "json"                                     				// 보내는 데이터 타입
			,postData: searchInfo                             					// 보내는 데이터 형식
			,mtype:'POST'                                         				// POST,GET,LOCAL 3가지 존재
			,loadBeforeSend: function(jqXHR) {
				jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
			}
			,jsonReader : {
				root:  "list",
				repeatitems: false,
			}
		}).trigger("reloadGrid");
	};
	
	<%--다건 결과 return --%>
	${param.popupId}_m._fncRtnList = function(){
		var rtnList = new Array();
		var selRow = $('#'+${param.popupId}_m._id).find('#${param.popupId}_tabList').getGridParam('selarrrow');	
		
		if(selRow.length <= 0) {
			alert('<spring:message code="message.report.no.selectrow"/>');	//선택된 행이 없습니다.
			return
		}
		
		for(var i = 0; i < selRow.length; i++) {
			var rowData = $('#'+${param.popupId}_m._id).find('#${param.popupId}_tabList').getRowData(selRow[i]);
			rtnList.push(rowData);
		}
		
		// 선택데이터 콜백
        if (${param.popupId}_m._callback) ${param.popupId}_m._callbackFunc(strList);
        
        // 창닫기
      	${param.popupId}_m._closeLayer();
		
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
    <h1>업체조회 </h1>
	<a id="${param.popupId}_btnClose" class="close" ><i class="fa fa-times" style="color:white;"></i></a>

	<div id="pop_content" class="open_content">
		<div id="pop_section">
			<div style="padding-top:5px;">
				<form id="${param.popupId}Form" name="${param.popupId}Form" action="" method="post">
		            <table class="type1">
						<colgroup>
							<col width="100"/>
							<col width="200"/>
						</colgroup>
						<tbody>
							<tr>
								<th>업체명</th>
								<td>
									<input type="text" id="${param.popupId}_srchCompNm" name="${param.popupId}_srchCompNm" style="width: 50%;" />
								</td>
							</tr>
						</tbody>
					</table>
	            </form>
            </div>

			<div class="tit_area">
				<h2>업체목록</h2>
				<div>
					<html:button id="${param.popupId}_btnSearch" auth="select" msgKey="button.select" />
					<!-- 권한없는 버튼의경우 아래처럼 -->
					<%-- <input type="button" id="${param.popupId}_btnSearch" value="조회" class="button btn_gray" /> --%>
				</div>
			</div>
			<div id="${param.popupId}_gridContainer" class="${param.popupId}_gridContainer">
				<table id="${param.popupId}_tabList"><tr><td></td></tr></table>
			</div>
			<div id="${param.popupId}_pageList"></div>
		</div>
	</div>
</div>
