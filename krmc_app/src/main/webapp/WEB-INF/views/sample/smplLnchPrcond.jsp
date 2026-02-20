<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<%--
	ClassName :  smpLnchPrcond.jsp
	Description : 샘플페이지 _현황
	Modification information
	수정일 			수정자					수정내용
	--------- 	----------------		-----------------
	2022.12.25  	안석진				 최초생성

	Author 	: ASJ
	Since 	: 2022.12
	
--%>

<spring:message code="common.all" 			var="commonall" />			<%-- 전체 --%>
<style>
.warning-txt{
	font-weight:bold;
	color:red;
}
</style>
<script>
$(document).ready(function($){
	//button click event ========================================
// 		$("#btnAccept").unbind().click(null, 		evnetAccept);		//접수
		$("#btnSearch").unbind().click(null, 		eventSearch);		//조회
		$("#btnPopCompSearch").unbind().click(null, fnOpenPopSrchComp);	//업체찾기
	//button click event ========================================
		
	<%-- datepicker 속성 부여 --%>
	$("input.dy").datepicker();
	fncSetDefaultValidDy();	//날짜 input 속성 셋팅(with validate)
	
	
	//onkeyDown event
	$("#srchCompNm").unbind().keydown(function(e){
			switch(e.which) {
	    	case 13 : fnOpenPopSrchComp("Y"); break; // enter
	    	default : break;
		} 
	});
	
	
	//GridInit====================================================
	fncInitSearch();
	eventSearch();
	
	$(window).bind('load resize',function(){
		try{
	        // 그리드의 width를 div 에 맞춰서 적용
	        $('#tabList').setGridWidth($('#grid1container').width()); //Resized to new width as per window

	        var height = $(window).height() - $('#grid1container')[0].offsetTop;

	        if(height > 275){
	        	$('#tabList').setGridHeight(height-142);
	        }else if(height < 300){
	        	$('#tabList').setGridHeight(height-300);
	        }

	    }catch(e){}
	});	
	//GridInit====================================================
		
});

/* grid init */
function fncInitSearch(){
	
	$("#tabList").jqGrid({
		datatype: "local",  // 보내는 데이터 타입
		data: [],
		// 컬럼명
		colNames:[
			 '대분류'
			,'중분류'
			,'접수번호'
			,'대표상품명'
			,'업체명'
			,'담당MD'
			,'진행상태'
			,'신청일자'
			,'완료일자'
			,'상선위대상'
			,'hidden'
			,'hidden'
			,'hidden'
		],
		// 헤더에 들어가는 이름
		colModel:[
			{name:'largeCatCdNm'		, index:'LARGE_CAT_CD_NM'		, sortable:false		, width:180		,align:"center"		},
			{name:'midCatCdNm'			, index:'MID_CAT_CD_NM'			, sortable:false		, width:180		,align:"center"		},
			{name:'acceptNo'			, index:'ACCEPT_NO'				, sortable:false		, width:120		,align:"left"		, frozen:true, formatter:sellSelected		,unformat: unfmtCellData},
			{name:'rprsnPrdctNm'		, index:'RPRSN_PRDCD_NM'		, sortable:false		, width:120		,align:"left"		, frozen:true},
			{name:'compNm'				, index:'COMP_NM'				, sortable:false		, width:120		,align:"left"		, frozen:true},
			{name:'utakmnMdNm'			, index:'UTAKMN_MD_NM'			, sortable:false		, width:120		,align:"left"		, frozen:true},
			{name:'lnchStatCdNm'		, index:'LNCH_STAT_CD_NM'		, sortable:false		, width:240		,align:"left" 		, frozen:true},
			{name:'prpsDtFmt'			, index:'PRPS_DT'				, sortable:false		, width:100		,align:"center"		
				,	formatter:function(cellvalue, options, rowObject){
						return $.trim(cellvalue) == "" ? "-" : cellvalue;
					}
			},
			{name:'complDtFmt'			, index:'COMPLE_DT'				, sortable:false		, width:120		,align:"center"
				,	formatter:function(cellvalue, options, rowObject){
						return $.trim(cellvalue) == "" ? "-" : cellvalue;
					}
			},
			{name:'prdSlcCmtYn'			, index:'PRD_SLC_CMT_YN'		, sortable:false		, width:80		,align:"center"		
				,	formatter:function(cellvalue, options, rowObject){
						return $.trim(cellvalue) == "Y"? "<span class='warningTxt'>O</span>" : "-";
					}
			},
			{name:'compCd'				, index:'COMP_CD'				, hidden:true},
			{name:'utakmnMdCd'			, index:'UTKMN_MD_CD'			, hidden:true},
			{name:'lnchStatCd'			, index:'LNCH_STAT_CD'			, hidden:true},
		],
		gridComplete : function() {                                      // 데이터를 성공적으로 가져오면 실행 됨
			var colCount = $(this).getGridParam("colNames").length;
			$("#blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
			$(window).resize();
		},
		loadComplete: function() {
			$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
			
			if ($(this).getGridParam("records")==0) {
				<%--조회결과가 없습니다. --%>
				$(this).addRowData("blankRow", {});
				$(this).find("#blankRow td:nth-child(2)").empty();
				$(this).find("#blankRow td:nth-child(2)").append("<spring:message code='message.search.no.data'/>");	
				
			}else{
				<%-- 데이터를 성공적으로 가져왔을때 별도의 데이터 처리가 필요한경우 --%>
				var allRows = $(this).jqGrid('getDataIDs'); //전체 행 가져오기
// 				console.log(allRows);
				for(var i = 0; i < allRows.length; i++){
					var cl = allRows[i];
					var rowData = $(this).jqGrid('getRowData', cl);
// 					console.log(rowData);
					var acceptNo = $.trim(rowData.acceptNo);
					
					//$(this).jqGrid('setRowData', cl, {'acceptNo': acceptNo}); //행별 데이터 세팅
				}
			}
			$(window).resize();
		},
		loadError:function(xhr, status, error) {										//데이터 못가져오면 실행 됨
			alert('<spring:message code="message.error.process" />');					<%-- 처리중 오류가 발생 하였습니다.--%>
			return false;
		},
		beforeSelectRow: function (rowid, e) {
// 			var $myGrid = $(this),
// 				i = $.jgrid.getCellIndex($(e.target).closest('td')[0]),
// 				cm = $myGrid.jqGrid('getGridParam', 'colModel');
			 <%-- 체크박스 disable여부 --%>
//			var disableYn= $("#jqg_tabList_"+rowid).is(":disabled");
// 			return (cm[i].name === 'cb' && disableYn == false);	
		},
		onSelectRow : function(rowid, colld, val, e) {		//행 선택시 이벤트
			//var rowdata = $(this).getRowData(rowid);		// 선택한 행의 데이터를 가져온다
			
		},
		onCellSelect : function(rowid, iCell, content){		// 셀 선택시 이벤트 (한번클릭),iCell : 선택열번호	, content : 선택셀의 값
// 			console.log(rowid);
// 			console.log(iCell);
// 			console.log(content);
			
			if(rowid === 'blankRow') return;	//조회결과가 없을 경우 클릭 이동(redirect) 막음
			
        	var rowData = $(this).getRowData(rowid);
        	var col = $("#tabList").jqGrid("getGridParam","colModel");
        	var colName = col[iCell].name;
        	var acceptNo = $.trim(rowData.acceptNo);
        	
        	if(colName =="acceptNo" && acceptNo!=""){
        		fncDoSearchView(acceptNo);
        	}else{
        		return;
        	}
		},
		ondblClickRow : function(id, cellcontent, iCol, e) {
        	
		},
		onSelectAll: function(aRowids,status) {	 // 전체선택 클릭시 이벤트
// 			if(status) { // 전체선택 true일 경우
// 				var checkField = $('tr.jqgrow > td > input.cbox:disabled', $(this)[0]);		// 체크박스 disabled된 값 가져오기
// 				var cnt = $(this).getGridParam("reccount");									// 전체 리스트 카운트 조회
// 				var rowId = checkField.parent().parent().attr("id");
// 				$("#tabList").jqGrid('setSelection',rowId,false);
// 				checkField.removeAttr("checked");											// 체크박스 disabled인 경우 체크제거
// 			}
		      	
		},
		<%-- jqGrid 속성, 필요에 따라 주석처리 하여 사용 (삭제X) --%>
		page: 1,															// 현재 페이지
		rowNum: 500,														// 한번에 출력되는 갯수
		rowList:[100,500,1000],												// 한번에 출력되는 갯수 SelectBox
		pager: '#pageList',													// page가 보여질 div
		loadui : "disable",													// 이거 안 써주니 로딩 창 같은게 뜸
		emptyrecords : '<spring:message code="message.search.no.data" />',  // row가 없을 경우 출력 할 text
		gridview: true,														// 그리드 속도
		viewrecords: true,													// 하단에 1/1 또는 데이터가없습니다 추가
		rownumbers:true,													// rowNumber 표시여부
		sortorder: "DESC",                                       			// desc/asc
		loadonce : false,													// reload 여부. [true: 한번만 데이터를 받아오고 그 다음부터는 데이터를 받아오지 않음]
// 		multiselect	: true,													// 체크박스 show
		scroll : false,														// 스크롤 페이징 여부
		autowidth:true,
		shrinkToFit:false,													// 컬럼 width 자동지정여부 (가로 스크롤 이용하기 위해 false지정)
	});
	
	$("#tabList").jqGrid('setGroupHeaders', {
		useColSpanStyle: true,
		groupHeaders:[
			{startColumnName: 'largeCatCdNm', numberOfColumns: 2, titleText: '상품카테고리'}
		]
	});
	
	//틀고정
	$("#tabList").jqGrid('setFrozenColumns');
	
}

//현황 조회
function eventSearch(){
	
	/************valdation**************/
		var srchPrpsStDy = $.trim($("#srchPrpsStDy").val()).replace(/[^0-9]/g,"");
		var srchPrpsEdDy = $.trim($("#srchPrpsEdDy").val()).replace(/[^0-9]/g,"");
		
		if((srchPrpsEdDy != "" && srchPrpsStDy != "") &&(srchPrpsStDy>srchPrpsEdDy)){
			alert("제안기간 검색 종료일은 검색 시작일보다 작을 수 없습니다.");	
			$("#srchPrpsEdDy").val("");
			$("#srchPrpsEdDy").focus();
			return false;
		}
	/************.validation*************/
	
	var searchInfo = {};
	$('#searchForm').find('input , select').map(function() {
		if(this.type !="button" && this.name != null && this.name != ""){
			if($(this).hasClass("dy")){
				searchInfo[this.name] = $.trim($(this).val()).replace(/[^0-9]/g,"");
			}else{
				searchInfo[this.name] = $(this).val();
			}
		}
	});
	
	$("#tabList").jqGrid('setGridParam',{
		url:'<c:url value="/app/smp/smpLnch_selSmpLnchList.json"/>',		// url 주소
		datatype: "json" ,                                    // 보내는 데이터 타입
		ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' },
		postData:searchInfo,                                  // 보내는 데이터 형식
		mtype:'POST',                                         // POST,GET,LOCAL 3가지 존재
		page: 1,
		loadBeforeSend: function(jqXHR) {
			jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
		},
		jsonReader : {
			root:  "resultList",	//조회결과 데이터
			page: "page",			//현재 페이지	
			total: "pagePerCount",	//총 페이지 수
			records: "totalCount",	// 전체 조회된 데이터 총갯수 
			repeatitems: false
		}
	}).trigger("reloadGrid");
	
}

/*상세화면 이동*/
function fncDoSearchView(acceptNo){
	var f = document.detailForm;
	
	if(!acceptNo){
		alert("접수 번호가 존재하지 않아 상세화면에 접근하실 수 없습니다.");
	}
	
	$("#detailForm input[name='acceptNo']").val($.trim(acceptNo));
	
	f.action = "<c:url value='/app/smp/smpLnch_selDetail.do'/>";
	f.submit();
	f.action = "";
	
}

/*업체찾기 팝업 호출*/
function fnOpenPopSrchComp(gbn){
	
	var paramObj = {};
		
	var srchCompNm = $("#searchForm input[name='srchCompNm']").val();
	paramObj['srchCompNm'] = srchCompNm;
	
	<%--fnOpenLayer(콜백함수,검색param, 조회결과 갯수에 따른 return여부, multiselect여부) --%>
	popSrchComp.fnOpenLayer(fnOpenPopSrchCompCallBack, paramObj,gbn,null);
}

/*업체찾기 콜백*/
function fnOpenPopSrchCompCallBack(data){
	$("#searchForm #srchCompNm").val(data.compNm);	//업체명 셋팅
	eventSearch();
}

</script>

<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
	<!-- 검색조건영역 start -->
	<form id="searchForm" name="searchForm" method="post">
		<sec:csrfInput/>
		<div>
			<table class="type1 inputWidth">
				<colgroup>
					<col width="10%">
					<col width="15%">
					<col width="10%">
					<col width="15%">
					<col width="10%">
					<col width="15%">
				</colgroup>
				<tbody>
					<tr>
						<th>상품카테고리</th>
						<td colspan="3">
							<html:codeTag comType="SELECT" objName="srchCatCd" parentID="2054" objId="srchCatCd"  defName="${commonall}" width="45%;"/>
							>>
							<html:codeTag comType="SELECT" objName="srchCatCd2" parentID="2054" objId="srchCatCd2"  defName="${commonall}" width="45%;"/>
						</td>
						<th>진행상태</th>
						<td>
							<html:codeTag comType="SELECT" objName="srchLnchStat" parentID="2054" objId="srchLnchStat"  defName="${commonall}"/>
						</td>
					</tr>
					<tr>
						<th>제안기간</th>
						<td>
							<input type="text" class="dy"	id="srchPrpsStDy"	name="srchPrpsStDy" title="제안시작일"/> ~
							<input type="text" class="dy"	id="srchPrpsEdDy"	name="srchPrpsEdDy" title="제안종료일"/> 
						</td>
						<th>대표상품</th>
						<td>
							<input type="text" id="srchRprsnPrdctNm" name="srchRprsnPrdctNm" style="width: 55%;"/>
						</td>
						<th>업체명</th>
						<td>
							<input type="text" id="srchCompNm" name="srchCompNm" style="width: 55%;"/>
							<input type="hidden" id="srchCompCd" name="srchCompCd"/>
							<html:button id="btnPopCompSearch" name="btnPopCompSearch" auth="select" value="찾기"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
		<!-- 검색조건영역 end -->
		
		<!-- 분류 서브타이틀 및 이벤트 버튼 -->
		<div class="tit_area">
			<h2 class="subhead">입점제안관리현황</h2>
			<div class="btn_l">
				<html:button id="btnAccept" auth="save"   value="접수" />	
				<html:button id="btnSearch" auth="select"   value="조회"/>			
			</div>
		</div>
 		<!-- grid List -->
		<div id="grid1container" class="gridcontainer">
			<table id="tabList">
				<tr>
					<td></td>
				</tr>
			</table>
		</div>
		<div id="pageList"></div>
</div>

<%-- 상세 화면 이동을 위한 form --%>
<form id="detailForm" name="detailForm" method="post">
	<sec:csrfInput/>
	<input type="hidden" id="acceptNo" name="acceptNo"/>
</form>
<%-- ./상세 화면 이동을 위한 form --%>

<!-- 업체찾기 팝업-->
<jsp:include page="/WEB-INF/views/common/popupFind/comPopSrchComp.jsp">
	<jsp:param name="popupId" value="popSrchComp"/>
</jsp:include>
<!-- 업체찾기 팝업-->