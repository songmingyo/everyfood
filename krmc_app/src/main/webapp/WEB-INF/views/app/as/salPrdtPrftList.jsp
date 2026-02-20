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
		
		$('#btnSales_find').unbind().click(function(){ findSalesMst()});
		$('#btnPrdt_find').unbind().click(function(){ findPrdtMst()});
		
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
		$('#searchPrdtNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findPrdtMst(this); break; // enter
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
				    '품목코드'
	       			, '품목명'
	       			, '규격'
	       			, '출고수량'
	       			, '입고단가'
	       			, '입고금액'
	       			, '출고단가'
	       			, '출고금액'
	       			, '차액'
	       			, '마진율(%)'
	       			
           ],
			colModel:[
					{name:'prdtCd'		, index:'prdtCd'		, sortable:true, editable:false, width:80 , align:"center"}
					,{name:'prdtNm'		, index:'prdtNm'		, sortable:true, editable:false, width:150 , align:"left"}
	            	,{name:'prdtStd'	, index:'prdtStd'		, sortable:true, editable:false, width:80 , align:"center"}
	            	,{name:'salesQty'	, index:'salesQty'	    , sortable:true, sorttype:'number', editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
	            	,{name:'cost'		, index:'cost'          , sortable:true, sorttype:'number', editable:false, width:50 , align:"right", formatter:'integer'}
	            	,{name:'rcvAmt'		, index:'rcvAmt'	    , sortable:true, sorttype:'number', editable:false, width:70 , align:"right", formatter:'integer'}
	            	,{name:'price'		, index:'price'	        , sortable:true, sorttype:'number', editable:false, width:70 , align:"right", formatter:'integer'}
	            	,{name:'pureAmt'	, index:'pureAmt'       , sortable:true, sorttype:'number', editable:false, width:70 , align:"right", formatter:'integer'}
	            	,{name:'diffAmt'	, index:'diffAmt'	    , sortable:true, sorttype:'number', editable:false, width:70 , align:"right", formatter:'integer'}
	            	,{name:'marRate'	, index:'marRate'	    , sortable:true, sorttype:'number', editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}

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
				//	gridData.setGridParam({rowNum:allRows.length});
					
				}
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'prdtNm' : '합계' });
				
                var sum_salesQty = gridData.jqGrid('getCol','salesQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'salesQty':sum_salesQty });
		    	
		    	var sum_rcvAmt = gridData.jqGrid('getCol','rcvAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'rcvAmt':sum_rcvAmt });
		    	
		    	var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
		    	
		    	var sum_diffAmt = gridData.jqGrid('getCol','diffAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'diffAmt':sum_diffAmt });
		    	
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
	        onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

            	if (id == 'blankRow') return;
           		var rowdata = $(this).getRowData(id);

           		setPrdtStkQty(rowdata);
            }

            ,rowNum:-1
           // ,pager: '#pageList'
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
		$("#searchHqClass").val(data.hqClass);		/*매출처명*/
	}

	//품목 찾기 팝업
	function findPrdtMst(){
		commonSalesPrdtMstFindLayer('', '', $("#searchPrdtNm").val(), $("#searchSalesCd").val(), setSalesPrdtMstFindLayer);
	}

	/*품목 콜백(후처리 함수 )*/
	function setSalesPrdtMstFindLayer(data) {
		if (data != null){
			$("#searchPrdtCd").val(data.prdtCd);
			$("#searchPrdtNm").val(data.prdtNm);
		}
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
					
					$("#saleQty1").val(data.boxQtyComp1);
					$("#saleQty2").val(data.boxQtyComp2);
					$("#saleQty3").val(data.boxQtyComp3);
				}
			}
		});
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
		
		$('#searchForm').find('input').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});
		$('#searchForm').find('select').map(function() {
			searchInfo[this.name] = $(this).val()
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/as/sal/salPrdtPrftList_selList.json'/>",
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
	
	/* 조회된 결과의 합계 */
	function setMarRateFooter(){
		var searchInfo = {};
		
		$('#searchForm').find('input').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','');
		});
		$('#searchForm').find('select').map(function() {
			searchInfo[this.name] = $(this).val()
		});

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/as/sal/salPrdtPrftFooter_selList.json"/>',
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
		$('#searchForm').attr("action", "<c:url value='/app/as/sal/salPrdtPrftList_selExcelDown'/>").submit();
	}
	
	function sales_clear(event){
		$('#searchSalesNm').val('');
		$('#searchSalesCd').val('');
		$('#searchHqClass').val('');
	}
	
	function prdt_clear(event){
		$('#searchPrdtNm').val('');
		$('#searchPrdtCd').val('');
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
	<legend>품목별이익현황</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>품목별이익현황 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="*">
			<col width="100">
			<col width="*">
			<col width="100">
			<col width="10%">
			<col width="100">
			<col width="10%">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">조회일자</label></th>
					<td>
						<input type="text" class="dt" id="searchFromDt" name="searchFromDt"  style="width: 25% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="searchToDt" name="searchToDt"   style="width: 25% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
					</td>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text" id="searchSalesNm" name="searchSalesNm" style="width: 80%;" onclick="sales_clear()">
						<input type="hidden" id="searchSalesCd" name="searchSalesCd" style="width: 20%;">
						<input type="hidden" id="searchHqClass" name="searchHqClass" style="width: 20%;">
						<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">품목</label></th>
					<td colspan=3>
						<input type="text"   id="searchPrdtNm"  name="searchPrdtNm" style="width:80%;" onclick="prdt_clear()">
						<input type="hidden" id="searchPrdtCd"  name="searchPrdtCd" >
						<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
			</tr>
			<tr>
				<th><label for="sele2">대분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="searchLCd" objName="searchLCd" parentID="Y"  defName="${commonall}" />
					</td>
				<th><label for="sele2">중분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="searchMCd" objName="searchMCd" parentID="Y"  defName="${commonall}" />
					</td>
				<th><label for="sele2">하남재고</label></th>
					<td style="border-right:none;">
						<input type="text"   id="haStkQty"  name="haStkQty" readonly >
					</td>
				<th><label for="sele2">여주재고</label></th>
					<td style="border-right:none;">
						<input type="text"   id="yeoStkQty"  name="yeoStkQty" readonly >
					</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->


 <UL style="width: 100%;">
 	<li style="float: top; width: 100%;">
 	<form name="mainForm" id="mainForm"  > 		
 		<div class="tit_area">
			<h2 class="subhead">품목별이익현황</h2>
			<div class="btn_l">
				
				당월 출고량 : 	<input type="text"   	id="saleQty1"  name="saleQty1" 		style="width:15%;" readonly="readonly">&emsp;
				전월 출고량 : 	<input type="text"   	id="saleQty2"  name="saleQty2" 		style="width:15%;" readonly="readonly">&emsp;
				전전월 출고량: 	<input type="text"   	id="saleQty3"  name="saleQty3" 		style="width:15%;" readonly="readonly">
				
				<input type=text   	id="saleQty4"  name="saleQty4" 		style="width:15%;background-color:transparent;border:none" readonly="readonly">
				
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 		<%-- 엑셀 --%>
<%-- 				<html:button id="btnExcel" 			name="btnExcel"   		auth="print" /> --%>
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

</div>
</body>
</html>