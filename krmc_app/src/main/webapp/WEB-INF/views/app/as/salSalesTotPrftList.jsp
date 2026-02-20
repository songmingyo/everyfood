```````````````````````````````````````<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #searchFromDt, #searchForm #searchToDt").datepicker();
		
		initSalesGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
		$('#btnPrint').unbind().click(null,	        printEvent);  // 출력
		
		$('#btnSales_find').unbind().click(function(){ findSalesMst()}); 	// 매출처찾기 팝업 버튼이벤트
		$('#btnPrdt_find').unbind().click(function(){ findPrdtMst()}); 	// 품목찾기 팝업 버튼이벤트
		$('#btnSalesPr_Find').unbind().click(function(){ findSalesPrMst()}); 	// 영업사원찾기 팝업 버튼이벤트
		
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
				    , '매출'
	       			, '이익'
	       			, '마진율'
	       			, '매출'
	       			, '이익'
	       			, '마진율'
	       			, '매입'
	       			, '매출'
	       			, '이익'
	       			, '마진율'
	       			, '영업사원'
	       			, '장려비율'
	       	       			
           ],
			colModel:[
					{name:'salesClassNm'	 	, index:'salesClassNm'		, sortable:true, editable:false, width:80 , align:"center"}
					,{name:'salesNm'	    	, index:'salesNm'			, sortable:true, editable:false, width:180 , align:"left"}
					,{name:'salesAmt1'			, index:'salesAmt1'			, sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'integer'}
					,{name:'profitAmt1'	    	, index:'profitAmt1'		, sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'integer'}
	            	,{name:'marRate1'    		, index:'marRate1'			, sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
	            	,{name:'salesAmt2'		    , index:'salesAmt2'         , sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'integer'}
	            	,{name:'profitAmt2'		    , index:'profitAmt2'	    , sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'integer'}
	            	,{name:'marRate2'	   		, index:'marRate2'	    	, sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
	            	,{name:'buyAmt3'	    	, index:'buyAmt3'       	, sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'integer'}
	            	,{name:'salesAmt3'			, index:'salesAmt3'			, sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'integer'}
	            	,{name:'profitAmt3'	    	, index:'profitAmt3'	    , sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'integer'}
	            	,{name:'marRate3'	   		, index:'marRate3'       	, sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
	            	,{name:'salesPrNm'			, index:'salesPrNm'			, sortable:true, editable:false, width:120 , align:"center"}
	            	,{name:'subsidyRate'	   	, index:'subsidyRate'	    , sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}

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
// 					gridData.setGridParam({rowNum:allRows.length});
					
					setMarRateFooter();
					
				}
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'salesNm' : '계' });
				
                var sum_salesAmt1 = gridData.jqGrid('getCol','salesAmt1', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'salesAmt1':sum_salesAmt1 });
		    	
		    	var sum_profitAmt1 = gridData.jqGrid('getCol','profitAmt1', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'profitAmt1':sum_profitAmt1});
		    	
		        var sum_salesAmt2 = gridData.jqGrid('getCol','salesAmt2', false, 'sum');
			  	gridData.jqGrid('footerData', 'set', { 'salesAmt2':sum_salesAmt2 });
			    	
			  	var sum_profitAmt2 = gridData.jqGrid('getCol','profitAmt2', false, 'sum');
			   	gridData.jqGrid('footerData', 'set', { 'profitAmt2':sum_profitAmt2});
			   
			    var sum_buyAmt3 = gridData.jqGrid('getCol','buyAmt3', false, 'sum');
			  	gridData.jqGrid('footerData', 'set', { 'buyAmt3':sum_buyAmt3 });
			   	
			    var sum_salesAmt3 = gridData.jqGrid('getCol','salesAmt3', false, 'sum');
			  	gridData.jqGrid('footerData', 'set', { 'salesAmt3':sum_salesAmt3 });
				    	
			   	var sum_profitAmt3 = gridData.jqGrid('getCol','profitAmt3', false, 'sum');
			   	gridData.jqGrid('footerData', 'set', { 'profitAmt3':sum_profitAmt3});
		    
		    	
		    	var sum_qty = gridData.jqGrid('getCol','qty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'qty':sum_qty });
		    	
	            $(window).resize();
	        },
			loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }else{
	            	var startDt = $("#searchFromDt").val();
	            	
	            	var dateObject = new Date(startDt);	            	
	            	dateObject.setMonth(dateObject.getMonth() - 2);	            	
	            	Mn2Date = dateObject.getFullYear() + ('0' + (dateObject.getMonth() + 1)).slice(-2) + ('0' + dateObject.getDate()).slice(-2);
	            	
	            	var dateObject = new Date(startDt);	            	
					dateObject.setMonth(dateObject.getMonth() - 1);	            	
	            	Mn1Date = dateObject.getFullYear() + ('0' + (dateObject.getMonth() + 1)).slice(-2) + ('0' + dateObject.getDate()).slice(-2);
	            	
	            	var dateObject = new Date(startDt);	            	
					dateObject.setMonth(dateObject.getMonth() - 0);	            	
	            	Mn0Date = dateObject.getFullYear() + ('0' + (dateObject.getMonth() + 1)).slice(-2) + ('0' + dateObject.getDate()).slice(-2);
	            	
	            	$(this).jqGrid('setLabel', 'salesAmt1', Mn2Date.substring(2,4)+'년 '+Mn2Date.substring(4,6)+' 월 매출');
	            	$(this).jqGrid('setLabel', 'profitAmt1', Mn2Date.substring(2,4)+'년 '+Mn2Date.substring(4,6)+' 월 이익');
	            	$(this).jqGrid('setLabel', 'marRate1', Mn2Date.substring(2,4)+'년 '+Mn2Date.substring(4,6)+' 월 마진율');
	            	
	            	$(this).jqGrid('setLabel', 'salesAmt2', Mn1Date.substring(2,4)+'년 '+Mn1Date.substring(4,6)+' 월 매출');
	            	$(this).jqGrid('setLabel', 'profitAmt2', Mn1Date.substring(2,4)+'년 '+Mn1Date.substring(4,6)+' 월 이익');
	            	$(this).jqGrid('setLabel', 'marRate2', Mn1Date.substring(2,4)+'년 '+Mn1Date.substring(4,6)+' 월 마진율');
	            	
	            	$(this).jqGrid('setLabel', 'buyAmt3', Mn0Date.substring(2,4)+'년 '+Mn0Date.substring(4,6)+' 월 매입');
	            	$(this).jqGrid('setLabel', 'salesAmt3', Mn0Date.substring(2,4)+'년 '+Mn0Date.substring(4,6)+' 월 매출');
	            	$(this).jqGrid('setLabel', 'profitAmt3', Mn0Date.substring(2,4)+'년 '+Mn0Date.substring(4,6)+' 월 이익');
	            	$(this).jqGrid('setLabel', 'marRate3', Mn0Date.substring(2,4)+'년 '+Mn0Date.substring(4,6)+' 월 마진율');
	            }
	            
	        	$(window).resize();
	        },
			loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');
				return false;
            },
            onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

            	if (id == 'blankRow') return;
           		var rowdata = $(this).getRowData(id);

            }

            ,rowNum:-1
            //,pager: '#pageList'
			,loadui : "disable"													// 이거 안 써주니 로딩 창 같은게 뜸
			,gridview: true														// 그리드 속도
			,viewrecords: true													// 하단에 1/1 또는 데이터가없습니다 추가
			,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
			,rownumbers: true													// rowNumber 표시여부
			,autowidth:true		
			,loadonce : true
			,shrinkToFit : false
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
		
		var fromDt = $("#searchFromDt").val();
		var toDt = $("#searchToDt").val();
		
		if(fromDt.substring(0,7) != toDt.substring(0,7)){
			alert("조회월이 서로 다릅니다.");
			return;
		}
		
		var searchInfo = {};
		
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/as/salesmgmt/salSalesTotPrftList_selList.json'/>",
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
			url : '<c:url value = "/app/as/salesmgmt/salSalesTotPrftFooter_selList.json"/>',
			data : JSON.stringify(searchInfo),
			success : function(data) {
				if(data != null){
					$("#tabList").jqGrid('footerData', 'set', { 'marRate1':data.marRateSum1 });
					$("#tabList").jqGrid('footerData', 'set', { 'marRate2':data.marRateSum2 });
					$("#tabList").jqGrid('footerData', 'set', { 'marRate3':data.marRateSum3 });
				}
			}
		});
	}
	

	/* 엑셀  */
	function excelEvent(){
		let gridObj = $("#tabList");	//grid Obj
		let excelName = "통합매출처이익율(부가세별도)";	//엑셀 다운로드 명
		
		exportExcel(gridObj, excelName);
	}
	
	/* 출력 */
	function printEvent(){
		
		var url = "<c:url value="/app/as/salesmgmt/salSalesTotPrftList_prtList.json" />";
		var target = "PDFPrint";
		var agt = navigator.userAgent.toLowerCase();

		if (agt.indexOf("msie") != -1) {		
			searchForm.action = url;
			searchForm.submit();
		}
		else {
			window.open('','PDFPrint','toolbar=no,resizable=no,location=0,scrollbars=0,width=800,height=700,top=50,left=200');
			searchForm.action=url;
			searchForm.target="PDFPrint";
			searchForm.submit();
		}
	}
	
	function salesPr_clear(){
		$("#searchSalesPrCd").val('');
		$("#searchSalesPrNm").val('');
	}
	function sales_clear(event){
		$('#searchSalesNm').val('');
		$('#searchSalesCd').val('');
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
	<legend>통합매출처이익률(전월대비)</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>통합매출처이익률(전월대비) 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="*">
			<col width="100">
			<col width="10%">
			<col width="100">
			<col width="*">
			<col width="100">
			<col width="*">
			<col width="100">
			<col width="10%">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">조회일자</label></th>
					<td>
						<input type="text" class="dt" id="searchFromDt" name="searchFromDt"  readonly="readonly" style="width: 90px !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="searchToDt" name="searchToDt"  readonly="readonly" style="width: 90px !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
					</td>
				<th><label for="sele2">구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchHqClass" objName="searchHqClass" parentID="M004" />
					</td>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text" id="searchSalesNm" name="searchSalesNm" style="width: 80%;"  onclick="sales_clear()">
						<input type="hidden" id="searchSalesCd" name="searchSalesCd" style="width: 20%;">
						<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">영업사원</label></th>
					<td>
						<input type="text" id="searchSalesPrNm" name="searchSalesPrNm" style="width: 50%;"  onclick="salesPr_clear()">
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
 		<div  style="padding-left: 0px; ">
 		<div class="tit_area">
			<h2 class="subhead">통합매출처이익률(전월대비)</h2>
			<div class="btn_l">
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 		<%-- 엑셀 --%>
				<html:button id="btnPrint" 			name="btnPrint"   		auth="print" /> 		<%-- 출력 --%>
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

<!-- 매출처별 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesPrdtMaster.jsp" />

<!-- 영업사원 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_salesPrMaster.jsp" />

</div>
</body>
</html>