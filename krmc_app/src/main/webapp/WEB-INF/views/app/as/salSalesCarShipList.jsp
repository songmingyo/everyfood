```````````````````````````````````````<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #searchFromDt, #searchForm #searchToDt").datepicker();
		
		initSalesGrid();
		
		if('${userSession.userType}' == "SM"){
			$('#tabList').jqGrid('hideCol', 'marRate');
		}
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
		
		$('#btnSalesPrMstFind').unbind().click(function(){ findSalesPrMst()}); 	// 영업사원찾기 팝업 버튼이벤트
		
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
		$('#salesPrNm').unbind().keydown(function(e) {
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
				    '호차'
	       			, '차량번호'
	       			, '배송기사명'
	       			, '매출처구분'
	       			, '매출처명'
	       			, '출고금액(부가세제외)'
	       			, '출고금액'
	       			, '매출이익'
	       			, '마진율(%)'
	       			, '중량(kg)'
	       			
	       			, '입고금액'
	       			, '매출이익율구분'
	       			
	       			, '공급금액'
	       			, '출고금액'
	       			, '매출이익'
           ],
			colModel:[
					{name:'carNm'			, index:'carNm'				, sortable:true, editable:false, width:70 , align:"center"}
					,{name:'carNum'			, index:'carNum'			, sortable:true, editable:false, width:60 , align:"center"}
	            	,{name:'drvSnm'			, index:'drvSnm'			, sortable:true, editable:false, width:50 , align:"center"}
	            	,{name:'salesClass'		, index:'salesClass'        , sortable:true, editable:false, width:50 , align:"center"}
	            	,{name:'salesNm'		, index:'salesNm'	        , sortable:true, editable:false, width:150 , align:"left"}
	            	,{name:'pureAmt'		, index:'pureAmt'	        , sortable:true, sorttype:'number', editable:false, width:70 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
	            	,{name:'totAmt'			, index:'totAmt'	        , sortable:true, sorttype:'number', editable:false, width:70 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
	            	,{name:'salesProfit'	, index:'salesProfit'       , sortable:true, sorttype:'number', editable:false, width:70 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
	            	,{name:'marRate'		, index:'marRate'	        , sortable:true, sorttype:'number', editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
	            	,{name:'weight'			, index:'weight'	        , sortable:true, sorttype:'number', editable:false, width:50 , align:"right"}
	            	
	            	,{name:'buyAmt'			, index:'buyAmt'	   		, sortable:true, editable:false, hidden:true, width:50 , align:"right"}
	            	,{name:'profitClass'	, index:'profitClass'	    , sortable:true, editable:false, hidden:true, width:50 , align:"right"}
	            	
	            	,{name:'pureAmtTot'		, index:'pureAmtTot'	    , sortable:true, editable:false, hidden:true, width:50 , align:"right"}
	            	,{name:'totAmtTot'		, index:'totAmtTot'	    	, sortable:true, editable:false, hidden:true, width:50 , align:"right"}
	            	,{name:'salesProfitTot'	, index:'salesProfitTot'	, sortable:true, editable:false, hidden:true, width:50 , align:"right"}

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
					//gridData.setGridParam({rowNum:allRows.length});
					
				}
				
				allData = gridData.jqGrid('getRowData');
				
				gridData.jqGrid('footerData', 'set', { 'prdtStd' : '합계' });
				
				var pureAmtTotTotValue = allData[0] ? allData[0].pureAmtTot : null;
				var totAmtTotTotValue = allData[0] ? allData[0].totAmtTot : null;
				var vatAmtTotTotValue = allData[0] ? allData[0].salesProfitTot : null;
				
				gridData.jqGrid('footerData', 'set', { 'pureAmt': pureAmtTotTotValue });
				gridData.jqGrid('footerData', 'set', { 'totAmt': totAmtTotTotValue });
				gridData.jqGrid('footerData', 'set', { 'salesProfit': vatAmtTotTotValue });
				
				// ===================== Footer Sum
// 				gridData.jqGrid('footerData', 'set', { 'carNum' : '합계' });
				
//                 var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
// 		    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
		    	
// 		    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
// 		    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
		    	
// 		    	var sum_salesProfit = gridData.jqGrid('getCol','salesProfit', false, 'sum');
// 		    	gridData.jqGrid('footerData', 'set', { 'salesProfit':sum_salesProfit });
		    	
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
            },

            rowNum:1000
            ,pager: '#pageList'
			,loadui : "disable"													// 이거 안 써주니 로딩 창 같은게 뜸
			,gridview: true														// 그리드 속도
			,viewrecords: true													// 하단에 1/1 또는 데이터가없습니다 추가
			,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
			,rownumbers:true													// rowNumber 표시여부
			,autowidth:true		
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
		
		initSalesGrid();
	}
	
	/*영업사원 찾기팝업 호출*/
	function findSalesPrMst() {
		commonSalesPrMstFindLayer('','',$("#salesPrNm").val(), setSalesPrMstFindLayer);
	}
	/*영업사원 콜백(후처리 함수 )*/
	function setSalesPrMstFindLayer(data){
		 if (data != null){
			 $("#salesPrCd").val(data.salesPrCd);		/*영업사원코드*/
			 $("#salesPrNm").val(data.salesPrNm);		/*영업사원명*/
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
			url:"<c:url value='/app/as/sal/salSalesCarShipList_selList.json'/>",
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
		
		setMarRateFooter();
	}
	
	
	function setMarRateFooter(){
		var searchInfo = {};
		
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','');
		});

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/as/sal/salSalesCarShipFooter_selList.json"/>',
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
		
		$('#searchForm').attr("action", "<c:url value='/app/as/sal/salSalesCarShipList_selExcelDown'/>").submit();

	}
	
	function salesPr_clear(event){
		$('#salesPrNm').val('');
		$('#salesPrNCd').val('');
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
	<legend>차량별배송현황표</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>차량별배송현황표 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="*">
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
						<input type="text" class="dt" id="searchFromDt" name="searchFromDt"   style="width: 100px !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="searchToDt" name="searchToDt"   style="width: 100px !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
					</td>
				<th><label for="sele2">배송차랑</label></th>
				<td>
					<html:codeTag comType="SELECT" dataType="CAR" objId="searchCarCd" objName="searchCarCd" height="24px" parentID="Y" defValue="" defName="${commonall}"  />
				</td>
				<th><label for="sele2">매출처구분</label></th>
				<td>
					<html:codeTag comType="SELECT" objId="searchSalesClass" objName="searchSalesClass" parentID="M013"  defName="${commonall}"  />
				</td>
				<th><label for="sele2">영업사원</label></th>
				<td>
					<input type="text" id="salesPrNm" name="salesPrNm" style="width: 50%;" onclick="salesPr_clear()">
					<input type="hidden" id="salesPrCd" name="salesPrCd" readonly="readonly"  style="width: 20%;">
					<button id="btnSalesPrMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
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
			<h2 class="subhead">차량별배송현황표</h2>
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

<!-- 영업사원 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_salesPrMaster.jsp" />

</div>
</body>
</html>