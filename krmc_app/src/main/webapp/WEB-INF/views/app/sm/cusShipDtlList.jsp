```````````````````````````````````````<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #buyStartDt, #searchForm #buyEndDt").datepicker();
		
		initSalesGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
		$('#btnShipDtlPrt').unbind().click(null,    shipDtlPrt); 	 // 출력
		$('#btnSales_find').unbind().click(function(){ findSalesMst()}); 	// 매출처찾기 팝업 버튼이벤트
	    $("#btnPrdt_find").unbind().click(function(){findPrdtMst();	});		// 상품 팝업 찾아가기
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
		$('#salesNm').unbind().keydown(function(e) {
			switch(e.which) {
	    	case 13 : findSalesMst(this); break; // enter
		   		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		$('#prdtNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findPrdtMst(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
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
				    '일자'
	       			, '매출처구분'
	       			, '창고명'
	       			, '렉번호'
	       			, '보관방법'
	       			, '호차'
	       			, '매출처명'
	       			, '전표번호'
	       			, '품목코드'
	       			, '품목명'
	       			, '규격'
	       			, '단위'
	       			, '단가'
	       			, '수량'
	       			, '공급가'
	       			, '부가세'
	       			, '합계금액'
	       			, '구분'
	       			, '비고'
	       			
	       			, '수량합계'
	       			, '공급가합계'
	       			, '부가세합계'
	       			, '총합계'
	       			
           ],
			colModel:[
					{name:'dt'				, index:'dt'			, sortable:true, editable:false, width:100 , align:"center"}
					,{name:'salesClassNm'	, index:'salesClassNm'	, sortable:true, editable:false, width:120 , align:"center"}
	            	,{name:'whNm'			, index:'whNm'			, sortable:true, editable:false, width:80 , align:"center"}
	            	,{name:'lackNo1'		, index:'lackNo1'       , sortable:true, editable:false, width:120 , align:"center"}
	            	,{name:'saveKindNm'		, index:'saveKindNm'    , sortable:true, editable:false, width:80 , align:"center"}
	            	,{name:'carNm'			, index:'carNm'	        , sortable:true, editable:false, width:120 , align:"left"}
	            	,{name:'salesNm'		, index:'salesNm'	    , sortable:true, editable:false, width:180 , align:"left"}
	            	,{name:'salesSlipNo'	, index:'salesSlipNo'   , sortable:true, editable:false, width:100 , align:"center"}
	            	,{name:'prdtCd'			, index:'prdtCd'        , sortable:true, editable:false, width:100 , align:"center"}
	            	,{name:'prdtNm'			, index:'prdtNm'	    , sortable:true, editable:false, width:180 , align:"left"}
	            	,{name:'prdtStd'		, index:'prdtStd'	    , sortable:true, editable:false, width:100 , align:"center"}
					,{name:'ordUnitNm'		, index:'ordUnitNm'	    , sortable:true, editable:false, width:80 , align:"center"}
	            	,{name:'price'			, index:'price'	        , sortable:true, editable:false, width:80 , align:"right", formatter:'integer'}
	            	,{name:'qty'			, index:'qty'           , sortable:true, sorttype:'number', editable:false, width:80 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}, summaryType:'sum'}
	            	,{name:'pureAmt'		, index:'pureAmt'	    , sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'integer', summaryType:'sum'}
	            	,{name:'vatAmt'			, index:'vatAmt'	    , sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'integer', summaryType:'sum'}
					,{name:'totAmt'			, index:'totAmt'	    , sortable:true, sorttype:'number', editable:false, width:110 , align:"right", formatter:'integer', summaryType:'sum'}
	            	,{name:'gubun'			, index:'gubun'         , sortable:true, editable:false, width:110 , align:"center"}
	            	,{name:'remarks1'		, index:'remarks1'	    , sortable:true, editable:false, width:200 , align:"left"}
	            	
	            	,{name:'qtyTot'			, index:'qtyTot'	    , sortable:true, editable:false, hidden:true, width:200 , align:"left"}
	            	,{name:'pureAmtTot'		, index:'pureAmtTot'	, sortable:true, editable:false, hidden:true, width:200 , align:"left"}
	            	,{name:'vatAmtTot'		, index:'vatAmtTot'	    , sortable:true, editable:false, hidden:true, width:200 , align:"left"}
	            	,{name:'totAmtTot'		, index:'totAmtTot'	    , sortable:true, editable:false, hidden:true, width:200 , align:"left"}
	            
		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				//gridData.jqGrid('hideCol', ["rn"]);
				
				/* 조회후 데이터가 한건이라도 있을경우  */
				if(allRows.length > 0 && $("#blankRow").val() != ""){
					$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
					
					// grid rowNum 재설정 (-1로 설정한 경우 row가 많으면 sort 시 row수가 감소)
				//	gridData.setGridParam({rowNum:allRows.length});
					
				}
				
				// ===================== Footer 표기
				
				allData = gridData.jqGrid('getRowData');
				
				gridData.jqGrid('footerData', 'set', { 'prdtStd' : '합계' });
				
				var qtyTotValue = allData[0] ? allData[0].qtyTot : null;
				var pureAmtTotTotValue = allData[0] ? allData[0].pureAmtTot : null;
				var vatAmtTotTotValue = allData[0] ? allData[0].vatAmtTot : null;
				var totAmtTotTotValue = allData[0] ? allData[0].totAmtTot : null;
				
				gridData.jqGrid('footerData', 'set', { 'qty': qtyTotValue });
				gridData.jqGrid('footerData', 'set', { 'pureAmt': pureAmtTotTotValue });
				gridData.jqGrid('footerData', 'set', { 'vatAmt': vatAmtTotTotValue });
				gridData.jqGrid('footerData', 'set', { 'totAmt': totAmtTotTotValue });
				
				// ===================== Footer Sum
				
// 				var sum_qty = gridData.jqGrid('getCol','qty', false, 'sum');
// 		    	gridData.jqGrid('footerData', 'set', { 'qty':sum_qty });		    	
		
// 		    	var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
// 		    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
		    	
// 		    	var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
// 		    	gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
		    	
		    	
// 		    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
// 		    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
		    	
	        //    $(window).resize();
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
            onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

            	if (id == 'blankRow') return;
           		var rowdata = $(this).getRowData(id);

           		setPrdtStkQty(rowdata);
            }

            ,rowNum:1000
            ,pager: '#pageList'
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
			,grouping:true
			,groupingView : {
			   		groupField : ['dt'],
			   		groupSummary : [true],
			   		groupColumnShow : [true],
			   		groupText : ['<b>{0} - {1} 건</b>'],
			   		groupCollapse : false,
					groupOrder: ['asc'],
			   	}
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
		commonSalesMstFindLayer('','',$("#salesNm").val(), 'Y', setSalesMstFindLayer, 'Y');
	}
	
	/*매출처 콜백(후처리 함수 )*/
	function setSalesMstFindLayer(data) {
		$("#salesCd").val(data.salesCd);
		$("#salesNm").val(data.salesNm);
		$("#hqClass").val(data.hqClass);
	}


	//품목 찾기 팝업
	function findPrdtMst(){
		commonPrdtMstFindLayer('','',$("#prdtNm").val(), '', setPrdtMstFindLayer);
	}

	/*품목 콜백(후처리 함수 )*/
	function setPrdtMstFindLayer(data) {
		if (data != null){
			$("#prdtCd").val(data.prdtCd);
			$("#prdtNm").val(data.prdtNm);
		}
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
		
		$('#searchForm').find('input').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','');
		});
		
		$('#searchForm').find('select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/sm/custsales/cusShipDtlList_selList.json'/>",
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
		
		//setMarRateFooter();
	}
	
	/* 품목별 재고 */
	function setPrdtStkQty(rowData){
		var searchData = 
		       {'prdtCd'  : rowData.prdtCd 
		       };		
		
		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/common/selectPrdtStkQty"/>',
			data : JSON.stringify(searchData),
			success : function(data) {
				if(data != null){
					$("#haStkQty").val(data.haStkQty);
					$("#yeoStkQty").val(data.yeoStkQty);
				}
			}
		});
	}
	
	/* 엑셀  */
	function excelEvent(){
		$('#salesCdExcel').val($('#salesCd').val());
		$('#hqClassExcel').val($('#hqClass').val());
		
		$('#searchForm').attr("action", "<c:url value='/app/sm/custsales/cusShipDtlList_selExcelDown'/>").submit();
	}
	
	function sales_clear(event){
		$('#salesNm').val('');
		$('#salesCd').val('');
		$('#hqClass').val('');
	}
	
	function prdt_clear(event){
		$('#prdtNm').val('');
		$('#prdtCd').val('');
	}
	
	/*PDF Report*/
	function shipDtlPrt(){
		$("#salesNmSearch").val($("#salesNm").val());
		$("#prdtNmSearch").val($("#prdtNm").val());
		
		var url = "<c:url value="/app/sm/custsales/cusShipDtlListPrint.json" />";
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
	

	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
	<input type="hidden" id="salesNmSearch" name="salesNmSearch""/>
	<input type="hidden" id="prdtNmSearch" name="prdtNmSearch""/>
	<input type="hidden" id="salesCdExcel" name="salesCdExcel""/>
	<input type="hidden" id="hqClassExcel" name="hqClassExcel""/>
	<sec:csrfInput/>
	<fieldset>
	<legend>매출처출고현황(상세)</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처출고현황(상세) 검색조건</caption>
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
				<th> <label for="sele2">조회일자</label></th>
					<td>
						<input type="text" class="dt" id="buyStartDt" name="buyStartDt"  readonly="readonly" style="width: 40% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="buyEndDt" name="buyEndDt"  readonly="readonly" style="width: 40% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
					</td>
				<th><label for="sele2">매출처명</label></th>				
					<td>
						<c:choose>
							<c:when test ="${userSession.userType eq 'S1'}"> 
								<input type="text" id="salesNm" name="salesNm" style="width: 80%;" value="${userSession.username}" disabled/>
								<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;" value="${userSession.userId}">
								<input type="hidden" id="hqClass" name="hqClass" style="width: 20%;" value="${userSession.hqClass}">
							</c:when>
							<c:otherwise>
								<input type="text" id="salesNm" name="salesNm" style="width: 80%;" onclick="sales_clear()">
								<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;">
								<input type="hidden" id="hqClass" name="hqClass" style="width: 20%;">
								<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
							</c:otherwise>
						</c:choose>
					</td>
				<th><label for="sele2">품목</label></th>
					<td>
						<input type="text"   id="prdtNm"  name="prdtNm" style="width:80%;" onclick="prdt_clear()">
						<input type="hidden" id="prdtCd"  name="prdtCd" style="width: 20%;">
						<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
					</td>
			    <th><label for="sele2">영업사원</label></th>
					<td>
						<input type="text" id="salesPrNm" name="salesPrNm" style="width: 80%;">
						<input type="hidden" id="salesPrCd" name="salesPrCd" readonly="readonly"  style="width: 20%;">
						<button id="btnSalesPrMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
			</tr>
			<tr>			  
				<th><label for="sele2">대분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="lCd" objName="lCd" parentID="Y"  defName="${commonall}"/>
					</td>
				<th><label for="sele2">중분류</label></th>
					<td colspan=3>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="mCd" objName="mCd" parentID="Y"  defName="${commonall}" />
					</td>
			
					<th><label for="sele2">매출처구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="salesClass" objName="salesClass" parentID="M013"  defName="${commonall}"   />
				</td>
				
			</tr>
			<tr>
				<th><label for="sele2">창고구분</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC3"	objId="whCd" objName="whCd" parentID="Y" defName="${commonall}" />
					</td>
				<th><label for="sele2">과면세구분</label></th>
         	    	<td>
          	    		<html:codeTag comType="SELECT" objId="taxYn" objName="taxYn" parentID="M005"  defName="${commonall}"   />
          			</td>
				<th><label for="sele2">하남재고</label></th>
					<td style="border-right:none;">
						<input type="text"   id="haStkQty"  name="haStkQty" style="text-align:right;" readonly >
					</td>
				<th><label for="sele2">여주재고</label></th>
					<td style="border-right:none;">
						<input type="text"   id="yeoStkQty"  name="yeoStkQty" style="text-align:right;" readonly >
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
			<h2 class="subhead">매출처출고현황(상세)</h2>
			<div class="btn_l">
				<html:button id="btnNew"   			name="btnNew"      		    auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		    auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		    auth="excel" /> 		<%-- 엑셀 --%>
				<html:button id="btnShipDtlPrt" 	name="btnShipDtlPrt"    	auth="select"  value="출력"  />
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

<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />

<!-- 영업사원 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_salesPrMaster.jsp" />

</div>
</body>
</html>