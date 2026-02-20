<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script src="/resources/plugins/jQuery/jquery.mtz.monthpicker.js" type="text/javascript"></script>

<style>
.mtz-monthpicker-year{
		width : 100px;
	}

	#searchMnPicker {
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
		$('#searchMnPicker').bind('click', function () {
			$('#searchMn').monthpicker('show');
		});

		//개점 예정 월 datepicker 설정
		$("#searchMn").monthpicker({
			monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],				//월 단위
			monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],			//월 단위
			pattern: 'yyyy-mm'
		});
				
		initSalesGrid();
			
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
			
		$('#btnSales_find').unbind().click(function(){ findSalesMst()});
		$('#btnSalesPr_Find').unbind().click(function(){ findSalesPrMst()});
			
		//Resized to new width as per window -------------------------------*/
	    $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 
	
		        var height = $(window).height()-$('#grid1container')[0].offsetTop;
	
			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-100);	//GRID  LIST
			    } 
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-120);	//GRID  LIST
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
	  
	
		// 조회조건 입력필드 enter key이벤트 --------------
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
		//-----------------------------------------------------------------

	});


	/* 마스터 데이터  그리드 초기화 */
	function initSalesGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[
					'구분'
					, '매출처명'
				    , '전월잔액'
				    , '입고금액'
	       			, '출고금액'
	       			, '매출이익'
	       			, '마진율(%)'
	       			, '입금'
	       			, '잔액'
	       			, '결제조건'
	       			, '영업사원'
	       			, '장려비율'
	       	       			
           ],
			colModel:[
					{name:'salesClassNm'	, index:'salesClassNm'	, sortable:true, editable:false, width:80 , align:"center"}
					,{name:'salesNm'	    , index:'salesNm'		, sortable:true, editable:false, width:180 , align:"left"}
					,{name:'prevBalAmt'		, index:'prevBalAmt'	, sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'integer'}
					,{name:'buyAmt'		    , index:'buyAmt'	    , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'integer'}
					,{name:'pureAmt'		, index:'pureAmt'	    , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'integer'}
	            	,{name:'salesProfit'	, index:'salesProfit'   , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'integer'}
	            	,{name:'marRate'	    , index:'marRate'	    , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
	            	,{name:'thisDepAmt'		, index:'thisDepAmt'    , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'integer'}
	            	,{name:'thisBalAmt'		, index:'thisBalAmt'    , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'integer'}
		            ,{name:'setlCon'	    , index:'setlCon'       , sortable:true, editable:false, width:120 , align:"center"}
	            	,{name:'salesPrNm'		, index:'salesPrNm'		, sortable:true, editable:false, width:120 , align:"center"}
	            	,{name:'subsidyRate'	, index:'subsidyRate'	, sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
	            	

		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				/* 조회후 데이터가 한건이라도 있을경우  */
				if(allRows.length > 0 && $("#blankRow").val() != ""){
					$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
					
					// grid rowNum 재설정 (-1로 설정한 경우 row가 많으면 sort 시 row수가 감소)
					gridData.setGridParam({rowNum:allRows.length});
					
					setMarRateFooter();
					
				}
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'salesClassNm' : '합계' });
				
                var sum_prevBalAmt = gridData.jqGrid('getCol','prevBalAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'prevBalAmt':sum_prevBalAmt });
		    	
		    	var sum_buyAmt = gridData.jqGrid('getCol','buyAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'buyAmt':sum_buyAmt });
		    	
		    	var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
		    	
		    	var sum_salesProfit = gridData.jqGrid('getCol','salesProfit', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'salesProfit':sum_salesProfit });
		    	
		    	var sum_thisDepAmt = gridData.jqGrid('getCol','thisDepAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'thisDepAmt':sum_thisDepAmt });
		    	
		    	var sum_thisBalAmt = gridData.jqGrid('getCol','thisBalAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'thisBalAmt':sum_thisBalAmt });
		    	
	            $(window).resize();
	        },
			loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }
	            
	        	$(window).resize();
	        },
			loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');
				return false;
            }

            ,rowNum:-1
            //,pager: '#pageList'
			,loadui : "disable"													// 이거 안 써주니 로딩 창 같은게 뜸
			,gridview: true														// 그리드 속도
			,viewrecords: true													// 하단에 1/1 또는 데이터가없습니다 추가
			,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
			,rownumbers: true													// rowNumber 표시여부
			,autowidth:true
			//,shrinkToFit : false
			,loadonce : true
			,footerrow: true														// 전체 합계를 낼때 사용
			,sortorder : "desc"
		});
	}
	
	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
		
        //Grid 초기화
		$("#tabList").jqGrid('clearGridData');
	}
	
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
	
	/* 조회 */
	function searchEvent(event){
		
		var searchInfo = {};
		
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/as/sal/salSalesMonVatPrftList_selList.json'/>",
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
	
	/* 조회된 결과의 합계 */
	function setMarRateFooter(){
		var searchInfo = {};
		
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','');
		});

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/as/sal/salSalesMonVatPrftFooter_selList.json"/>',
			data : JSON.stringify(searchInfo),
			success : function(data) {
				if(data != null){
					$("#tabList").jqGrid('footerData', 'set', { 'marRate':data.marRateSum });
				}
			}
		});
	}	

	/* 엑셀  */
	function excelEvent(){
		$('#searchForm').attr("action", "<c:url value='/app/as/sal/salSalesMonVatPrftList_selExcelDown'/>").submit();
	}
	
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
	<input type="hidden" id="searchOrdDt" name="searchOrdDt""/>
	<input type="hidden" id="searchBuyDt" name="searchBuyDt""/>
	<sec:csrfInput/>
	<fieldset>
	<legend>매출처기간 월별 이익현황(부가세포함)</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처기간 품목별 이익현황 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="9%">
			<col width="100">
			<col width="9%">
			<col width="100">
			<col width="17%">
			<col width="100">
			<col width="17%">
			<col width="100">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">조회월</label></th>
					<td>
						<input type="text" id="searchMn" name="searchMn" value='<c:out value="${util:getNowDateFmt('yyyy-MM')}"/>' readonly="readonly" style="width: 80%"/>
						<input type="button" id="searchMnPicker" /> 
					</td>
				<th><label for="sele2">구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchHqClass" objName="searchHqClass" parentID="M004" />
					</td>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text" id="searchSalesNm" name="searchSalesNm" style="width: 80%;">
						<input type="hidden" id="searchSalesCd" name="searchSalesCd" style="width: 20%;">
						<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">영업사원</label></th>
					<td>
						<input type="text" id="searchSalesPrNm" name="searchSalesPrNm" style="width: 80%;">
						<input type="hidden" id="searchSalesPrCd" name="searchSalesPrCd" readonly="readonly"  style="width: 20%;">
						<button id="btnSalesPr_Find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">매출처구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchSalesClass" objName="searchSalesClass" parentID="M013"  defName="${commonall}"  />
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
 		<div  style="padding-left: 5px; ">
 		<div class="tit_area">
			<h2 class="subhead">매출처 월별 이익현황(부가세 포함)</h2>
			<div class="btn_l">
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 		<%-- 엑셀 --%>
			</div>
		</div>
		
	</form>
	</div>
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