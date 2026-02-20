<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<%--
	ClassName :  mgrUserInfoCheckHist.jsp
	Description : 개인정보 조회이력List
	Modification information
	수정일 			수정자					수정내용
	--------- 	----------------		-----------------
	2023.01.19   최진성 					불필요한 코드 삭제 
	Author 	: JS
	Since 	: 2023.01.18
	
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
		$('#btnExcelDown').unbind().click(function(){ fncExcelDown()});	//엑셀
		$("#btnSearch").unbind().click(null, 		eventSearch);		//조회
	//button click event ========================================
		
	<%-- datepicker 속성 부여 --%>
	$("#srchFrDy, #srchToDy").datepicker();
	
	fncSetDefaultValidDy();	//날짜 input 속성 셋팅(with validate)
	
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
			 '로그순번'
			,'파일종류'
			,'다운로드내용'
			,'다운로드파일'
			,'실행시간MS'
			,'접근일자'
			,'접근아이피'
			,'사용자명'
			,'등록일시'
			,'hidden'
		],
		// 헤더에 들어가는 이름
		colModel:[
			{name:'logSeq'		    , index:'LOG_SEQ'		       , sortable:false		, width:80		,align:"center"		},
			{name:'fileKindCdNm'	, index:'FILE_KIND_CD_NM'	   , sortable:false		, width:150		,align:"center"		},
			{name:'dwldCmd'			, index:'DWLD_CMD'			   , sortable:false		, width:400		,align:"left"		},
			{name:'fileInfo'		, index:'FILE_INFO'			   , sortable:false		, width:400		,align:"left"		},
			{name:'executeTime'		, index:'EXECUTE_TIME'		   , sortable:false		, width:100		,align:"center"		},
			{name:'accessDyFmt'		, index:'ACCESS_DY'			   , sortable:false		, width:100		,align:"center"		},
			{name:'resIp'		    , index:'RES_IP'		       , sortable:false		, width:100		,align:"center" 	},
			{name:'userNm'			, index:'USER_NM'			   , sortable:false		, width:80		,align:"center"		},
			{name:'regDt'			, index:'REG_DT'			   , sortable:false		, width:180		,align:"center"		},
			{name:'fileKindCd'		, index:'FILE_KIND_CD'		   , hidden:true},
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
				for(var i = 0; i < allRows.length; i++){
					var cl = allRows[i];
					var rowData = $(this).jqGrid('getRowData', cl);
					var acceptNo = $.trim(rowData.acceptNo);
					
				}
			}
			$(window).resize();
		},
		loadError:function(xhr, status, error) {										//데이터 못가져오면 실행 됨
			alert('<spring:message code="message.error.process" />');					<%-- 처리중 오류가 발생 하였습니다.--%>
			return false;
		},
		onCellSelect : function(rowid, iCell, content){		// 셀 선택시 이벤트 (한번클릭),iCell : 선택열번호	, content : 선택셀의 값
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
		shrinkToFit:true,													// 컬럼 width 자동지정여부 (가로 스크롤 이용하기 위해 false지정)
	});
	
	//틀고정
	$("#tabList").jqGrid('setFrozenColumns');
	
}

//현황 조회
function eventSearch(){
	
	/************valdation**************/
	var srchFrDy = $.trim($("#srchFrDy").val()).replace(/[^0-9]/g,"");
	var srchToDy = $.trim($("#srchToDy").val()).replace(/[^0-9]/g,"");
	
	if((srchFrDy != "" && srchToDy != "") &&(srchFrDy>srchToDy)){
		alert("조회기간 검색 종료일은 검색 시작일보다 작을 수 없습니다.");	
		$("#srchToDy").val("");
		$("#srchToDy").focus();
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
		url:'<c:url value="/app/mgr/manager/mgrUserFileHistory_selectMgrUserFileHistory.json"/>',		// url 주소
		datatype: "json" ,                                    // 보내는 데이터 타입
		ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' },
		postData:searchInfo,                                  // 보내는 데이터 형식
		mtype:'POST',                                         // POST,GET,LOCAL 3가지 존재
		page: 1,
		loadBeforeSend: function(jqXHR) {
			jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
		},
		jsonReader : {
			root:  "list",	//조회결과 데이터
			page: "page",			//현재 페이지	
			total: "pagePerCount",	//총 페이지 수
			records: "totalCount",	// 전체 조회된 데이터 총갯수 
			repeatitems: false
		}
	}).trigger("reloadGrid");
	
}

//Excel Download
function fncExcelDown(){
	
	var srchfileKindCd = $("#searchForm #srchfileKindCd").val();
	var srchFrDy = $("#searchForm #srchFrDy").val().replaceAll("-","");
	var srchToDy = $("#searchForm #srchToDy").val().replaceAll("-","");
	
	$('#excelForm input[name="srchfileKindCd"]').val(srchfileKindCd);
	$('#excelForm input[name="srchFrDy"]').val(srchFrDy);
	$('#excelForm input[name="srchToDy"]').val(srchToDy);
	
	$('#excelForm').attr("action", "<c:url value='/app/mgr/manager/mgrUserInfoCheckHist_selectMgrUserFileHistoryExcelDown'/>").submit();
}		

</script>
	<!-- 엑셀다운로드 폼 -->
	<form id="excelForm" name="excelForm" method="post">
		<sec:csrfInput/>
		<input type="hidden" name="srchfileKindCd" />
		<input type="hidden" name="srchFrDy" />
		<input type="hidden" name="srchToDy" />
	</form>

<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
	<!-- 검색조건영역 start -->
	<form id="searchForm" name="searchForm" method="post">
		<sec:csrfInput/>
		<div>
			<table class="type1 inputWidth">
				<colgroup>
					<col width="10%">
					<col width="40%">
					<col width="10%">
					<col width="40%">
					<col width="10%">
					<col width="10%">
				</colgroup>
				<tbody>
					<tr>
						<th>조회일자</th>
						<td>
							<input type="text" id="srchFrDy" class="dy" name="srchFrDy" style="width:40%" disabled="disabled" value="<fmt:formatDate value="${srchFrDy}" pattern="${localeDatePattern}" />"/>
							~
							<input type="text" id="srchToDy" class="dy" name="srchToDy" style="width:40%" disabled="disabled" value="<fmt:formatDate value="${srchToDy}" pattern="${localeDatePattern}" />"/>
						</td>
						<th>파일종류</th>
						<td>
							<html:codeTag comType="SELECT" objName="srchfileKindCd" parentID="9040" objId="srchfileKindCd"  defName="${commonall}"/>
						</td>
		
					</tr>
				</tbody>
			</table>
		</div>
		</form>
		<!-- 검색조건영역 end -->
		
		<!-- 분류 서브타이틀 및 이벤트 버튼 -->
		<div class="tit_area">
			<h2 class="subhead">사용자별 파일조회 이력</h2>
			<div class="btn_l">
				<html:button id="btnExcelDown" auth="excel"  msgKey="button.excelDownload" value="엑셀" />	
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
