<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script src="/resources/plugins/jQuery/jquery.mtz.monthpicker.js" type="text/javascript"></script>

<style>
.mtz-monthpicker-year{
		width : 100px;
	}

	#searchMnStPicker {
		background: url('/resources/plugins/jsn/images/jsn/calendar.png');
		border: 0;
		height: 16px;
		width: 16px;
		cursor: pointer;
	}
	
	#searchMnEtPicker {
		background: url('/resources/plugins/jsn/images/jsn/calendar.png');
		border: 0;
		height: 16px;
		width: 16px;
		cursor: pointer;
	}
	
</style>

<script type="text/javascript">

	$(document).ready(function(){

		/* 달력 버튼 클릭시 MonthPicker Show */
		$('#searchMnStPicker').bind('click', function () {
			$('#searchYearMnSt').monthpicker('show');
		});
		
		/* 달력 버튼 클릭시 MonthPicker Show */
		$('#searchMnEtPicker').bind('click', function () {
			$('#searchYearMnEt').monthpicker('show');
		});

		//월 datepicker 설정
		$("#searchYearMnSt").monthpicker({
			monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],				//월 단위
			monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],			//월 단위
			pattern: 'yyyy-mm'
		});
		
		$("#searchYearMnEt").monthpicker({
			monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],				//월 단위
			monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],			//월 단위
			pattern: 'yyyy-mm'
		});
		  
		initAnlSalesLdgrVatEcptListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	    newEvent);    // 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
			
		$("#btnSales_find").unbind().click(function(){
			findSalesMst();
		});		
		$('#btnSalesPr_Find').unbind().click(function(){
			findSalesPrMst()
		});
	
		
		
		
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-120);	//GRID  LIST
			    }
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-150);	//GRID  LIST
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

		/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/
		$('#searchSalesNm').unbind().keydown(function(e) {
			switch(e.which) {
	    	case 13 : findSalesMst(this); break; // enter
		   		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		$('#searchSalesPrNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findSalesPrMst(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		/*-----------------------------------------------------------------*/

		
	});
	
	
	
	/*매출처 찾기팝업 호출 */
	function findSalesMst(){
		commonSalesMstFindLayer('','',$("#searchSalesNm").val(), '', setSalesyMstFindLayer);
	}
	/*매출처 콜백(후처리 함수 )*/
	function setSalesyMstFindLayer(data) {
		$("#searchSalesCd").val(data.salesCd);		/*매출처코드*/
		$("#searchSalesNm").val(data.salesNm);		/*매출처명*/
	}
	
	/*영업사원 찾기팝업 호출*/
	function findSalesPrMst() {
		commonSalesPrMstFindLayer('','',$("#searchSalesPrNm").val(), setSalesPrMstFindLayer);
	}
	/*영업사원 콜백(후처리 함수 )*/
	function setSalesPrMstFindLayer(data){
		 if (data != null){
			$("#searchSalesPrCd").val(data.salesPrCd);		/*영업사원코드*/
		 	$("#searchSalesPrNm").val(data.salesPrNm);		/*영업사원명*/
		}
	}
	
	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
		
        //Grid 초기화
		$("#tabList").jqGrid('clearGridData');
	}
	
	
	/* 마스터 데이터  그리드 초기화 */
	function initAnlSalesLdgrVatEcptListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   
            			 '매출처명'
            			, '1월'
            			, '2월'
            			, '3월'
            			, '4월'
            			, '5월'
            			, '6월'
            			, '7월'
            			, '8월'
            			, '9월'   
            			, '10월'
            			, '11월'
            			, '12월'
            			, '합계'
            			, '영업사원'
           ]
			,colModel:[
		                 {name:'salesNm'	 		, index:'salesNm'		 	, sortable:true, width:180 , align:"left"}
		            	,{name:'mon1'		        , index:'mon1'				, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'mon2'		    	, index:'mon2'				, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'mon3'	  			, index:'mon3'				, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'mon4'		   	 	, index:'mon4'	    		, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'mon5'	   			, index:'mon5'		    	, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'mon6'	     		, index:'mon6'	  	    	, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'mon7'				, index:'mon7'	   			, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'mon8'		        , index:'mon8'				, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'mon9'		  	    , index:'mon9'		    	, sortable:true, width:100, align:"right", formatter:'integer'}
		        		,{name:'mon10'				, index:'mon10'	   			, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'mon11'		  	    , index:'mon11'				, sortable:true, width:100, align:"right", formatter:'integer'}
		           		,{name:'mon12'		  	    , index:'mon12'		    	, sortable:true, width:100, align:"right", formatter:'integer'}
		        		,{name:'totAmt'				, index:'totAmt'   			, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'salesPrNm'		    , index:'salesPrNm'			, sortable:true, width:120, align:"center"}
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				/* 조회후 데이터가 한건이라도 있을경우  */
				if(allRows.length > 0 && $("#blankRow").val() != ""){
					$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
					
					// grid rowNum 재설정 (-1로 설정한 경우 row가 많으면 sort 시 row수가 감소)
					gridData.setGridParam({rowNum:allRows.length});
				}
	            
	        	// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'salesNm' : '합계' });
				
                var sum_mon1 = gridData.jqGrid('getCol','mon1', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'mon1':sum_mon1 });
		    	
		    	var sum_mon2 = gridData.jqGrid('getCol','mon2', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'mon2':sum_mon2 });
		    	
		    	var sum_mon3 = gridData.jqGrid('getCol','mon3', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'mon3':sum_mon3 });
		    	
		    	var sum_mon4 = gridData.jqGrid('getCol','mon4', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'mon4':sum_mon4 });
		    	
		    	var sum_mon5 = gridData.jqGrid('getCol','mon5', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'mon5':sum_mon5 });
		    	
		    	var sum_mon6 = gridData.jqGrid('getCol','mon6', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'mon6':sum_mon6 });
		    	
		    	var sum_mon7 = gridData.jqGrid('getCol','mon7', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'mon7':sum_mon7 });
		    	
		    	var sum_mon8 = gridData.jqGrid('getCol','mon8', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'mon8':sum_mon8 });
		    	
		    	var sum_mon9 = gridData.jqGrid('getCol','mon9', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'mon9':sum_mon9 });
		    	
		    	var sum_mon10 = gridData.jqGrid('getCol','mon10', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'mon10':sum_mon10 });
		    	
		    	var sum_mon11 = gridData.jqGrid('getCol','mon11', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'mon11':sum_mon11 });
		    	
		    	var sum_mon12 = gridData.jqGrid('getCol','mon12', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'mon12':sum_mon12 });
		    	
		    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
	            
	        	$(window).resize();
	        }
			,loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }
	        	$(window).resize();
	        }
			,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				//alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
            }
           ,rowNum:-1
           ,loadui : "disable"
           ,gridview:    true
           //,pager: '#pageList'
           ,sortname: 'buyCd'
           ,sortorder: 'asc'
           ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
           ,viewrecords: true
           ,scroll : false
           ,rownumbers:true
           ,loadonce:true
           ,shrinkToFit : false
           ,footerrow: true
           ,autowidth:true
		});
	}


	/*  조회 */
	function searchEvent(event){

  	    var searchInfo = {};
  	      
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');		
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/as/anlys/anlSalesLdgrVatEcptList_selList.json'/>",
			datatype: "json",
			postData: searchInfo,
			ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' },
			page: 1,
			mtype:'POST',
			//jqGrid AJAX POST 방식으로 보낼때, CSRF TOKEN값을 함께 보내줘야한다.
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


	/* 엑셀  */
	function excelEvent(){

// 		let gridObj = $("#tabList");	//grid Obj
// 		let excelName = "매출처원장부가세제외";	//엑셀 다운로드 명
		
// 		exportExcel(gridObj, excelName);
		
		$('#searchForm').attr("action", "<c:url value='/app/as/anlys/anlSalesLdgrVatEcptList_selExcelDown'/>").submit();
	}
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
	<sec:csrfInput/>
	<fieldset>
	<legend>매출처원장부가세제외</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처원장부가세제외 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="*">
			<col width="100">
			<col width="*">
			<col width="100">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">조회일자</label></th>
					<td>
            			<input type="text" name="searchYearMnSt" id="searchYearMnSt" maxlength="6" style="width:70px" value="${util:getNowDateFmt('yyyy-MM')}" readonly="readonly" style="width: 80%"/>
						<input type="button" id="searchMnStPicker" /> 
						<input type="text" name="searchYearMnEt" id="searchYearMnEt" maxlength="6" style="width:70px" value="${util:getNowDateFmt('yyyy-MM')}" readonly="readonly" style="width: 80%"/>
						<input type="button" id="searchMnEtPicker" />
					</td>
				<th><label for="sele2">구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchSalesHqClass" objName="searchSalesHqClass" parentID="M004" />
					</td>
				<th><label for="sele2">매출처구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchSalesClass" objName="searchSalesClass" parentID="M013"  defName="${commonall}"  />
					</td>
			</tr>
			<tr>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text" id="searchSalesNm" name="searchSalesNm" style="width: 80%;">
						<input type="hidden" id="searchSalesCd" name="searchSalesCd" style="width: 20%;">
						<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
					
				<th><label for="sele2">영업사원</label></th>
					<td colspan=3>
						<input type="text" id="searchSalesPrNm" name="searchSalesPrNm" style="width: 30%;">
						<input type="hidden" id="searchSalesPrCd" name="searchSalesPrCd" readonly="readonly"  style="width: 20%;">
						<button id="btnSalesPr_Find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>				
			</tr>
			
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->


<!-- 추가저장정보 end ----------------------------------------- -->

 <UL style="width: 100%;">
 	<li style="float: top; width: 100%;">
 	<form name="mainForm" id="mainForm"  >
 		<div class="tit_area">
			<h2 class="subhead">매출처원장부가세제외</h2>
			<div class="btn_l">
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 		<%-- 엑셀 --%>
			</div>
		</div>
		
	</form>
 	</li>
 	<li style="float: down; width: 100%; ">
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="tabList" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</li>
 </UL>            

<!-- CONTENT- BODY end ----------------------------------- -->

<!-- 매출처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesMaster.jsp" />

<!-- 영업사원 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_salesPrMaster.jsp" />

</div>
</body>
</html>