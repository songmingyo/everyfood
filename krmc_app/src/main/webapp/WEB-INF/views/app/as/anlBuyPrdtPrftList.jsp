<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){
		
		initAnlBuyPrdtPrftListGrid();
			
		$("#searchStartDt").datepicker();
		$("#searchEndDt").datepicker();
		  
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	 searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	     newEvent);     // 신규 
		$('#btnExcel').unbind().click(null,	     excelEvent);	// 액샐 
			
		$("#btnBuyCd").unbind().click(function(){
			findBuyMst();
		});
		$("#btnPrdt_find").unbind().click(function(){
			findPrdtMst();
		});
	
		
		
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-100);	//GRID  LIST
			    }
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-160);	//GRID  LIST
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

		/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/
		$('#searchBuyNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findBuyMst(this); break; // enter
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
		/*-----------------------------------------------------------------*/
		
	});
	
	
	/* 마스터 데이터  그리드 초기화 */
	function initAnlBuyPrdtPrftListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '매입처명'
            			, '품목코드'
            			, '품목명'
            			, '규격'
            			, '입고단가'
            			, '입고금액'
            			, '출고단가'
            			, '출고금액'
            			, '매출이익'
            			, '마진율(%)'
            			, '출고수량'
            ]
			,colModel:[
		                 {name:'buyNm'		    , index:'buyNm'		    	, sortable:true, width:180 , align:"left"}
		            	,{name:'prdtCd'	    	, index:'prdtCd'		    , sortable:true, width:100, align:"center"}
		            	,{name:'prdtNm'		    , index:'prdtNm'			, sortable:true, width:160, align:"left"}
		            	,{name:'prdtStd'		, index:'prdtStd'			, sortable:true, width:100, align:"center"}
		            	,{name:'cost'	  		, index:'cost'				, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'buyAmt'		   	, index:'buyAmt'	    	, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'price'	   		, index:'price'		    	, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'pureAmt'	    , index:'pureAmt'	  	    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'salesPrft'		, index:'salesPrft'	   		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'marRate'		, index:'marRate'			, sortable:true, sorttype:'number', width:100, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
		            	,{name:'salesQty'		, index:'salesQty'			, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
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
					//gridData.setGridParam({rowNum:allRows.length});
					
					setMarRateFooter();
					
				}
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'prdtNm' : '합계' });
				
                var sum_buyAmt = gridData.jqGrid('getCol','buyAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'buyAmt':sum_buyAmt });
		    	
		    	var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
		    	
		    	var sum_salesProfit = gridData.jqGrid('getCol','salesProfit', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'salesProfit':sum_salesProfit });
		    	
		    	var sum_salesQty = gridData.jqGrid('getCol','salesQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'salesQty':sum_salesQty });
	            
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
				//alert('<spring:message code="message.error.process" />');
				return false;
            }

           ,rowNum:1000
           ,loadui : "disable"
           ,gridview:    true
           ,pager: '#pageList'
           ,sortorder: 'asc'
           ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
           ,viewrecords: true
           ,scroll : false
           ,rownumbers:true
           ,loadonce:true
           //,shrinkToFit : false
           ,footerrow: true
           ,autowidth:true
		});
	}

	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
	}
	
	/* Data 조회 */
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
			url:"<c:url value='/app/as/anlys/anlBuyPrdtPrftList_selList.json'/>",
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
		
		$('#searchForm').find('input').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','');
		});
		
		$('#searchForm').find('select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/as/anlys/anlBuyPrdtPrftFooter_selList.json"/>',
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
		$('#searchForm').attr("action", "<c:url value='/app/as/anlys/anlBuyPrdtPrftList_selExcelDown'/>").submit();
	}


	/*매입처 찾기팝업 호출 */
	function findBuyMst() {
		commonBuyMstFindLayer('','',$("#searchBuyNm").val(), setBuyMstFindLayer);
	}
	
	/*매입처 콜백(후처리 함수 )*/
	function setBuyMstFindLayer(data) {
		if (data != null){
			$("#searchBuyCd").val(data.buyCd);
			$("#searchBuyNm").val(data.buyNm);
		}
	}
	
	/*품목 찾기 팝업 */
	function findPrdtMst(){
		commonPrdtMstFindLayer('', '', $("#searchPrdtNm").val(), '', setPrdtMstFindLayer);
	}

	/*품목 콜백(후처리 함수 )*/
	function setPrdtMstFindLayer(data) {
		if (data != null){
			$("#searchPrdtCd").val(data.prdtCd);
			$("#searchPrdtNm").val(data.prdtNm);
		}
	}
	
	function buy_clear(event){
		$('#searchBuyNm').val('');
		$('#searchBuyCd').val('');			
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
<sec:csrfInput/>
	<fieldset>
	<legend>매입처품목별이익현황</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매입처품목별이익현황 검색조건</caption>
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
						<input type="text" class="dt" id="searchStartDt" name="searchStartDt" readonly style="width: 30% !important;" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="searchEndDt" name="searchEndDt" readonly style="width: 30% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/>
					</td>
			
				<th><label for="sele2">매입처명</label></th>
					<td>
						<input type="text"   id="searchBuyNm"    name="searchBuyNm" style="width:70%;" onclick="buy_clear()">
						<input type="hidden" id="searchBuyCd"    name="searchBuyCd" style="width:70%;">
						<button id="btnBuyCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>  
					</td>
				<th><label for="sele2">품목명</label></th>
					<td>
						<input type="text"   id="searchPrdtNm"  name="searchPrdtNm" style="width:70%;" onclick="prdt_clear()">
						<input type="hidden" id="searchPrdtCd"  name="searchPrdtCd" >
						<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
				</td>
			</tr>
			<tr>
				<th><label for="sele2">대분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="searchLCd" objName="searchLCd" parentID="Y" defName="${commonall}" />
					</td>
				<th><label for="sele2">중분류</label></th>
					<td colspan=3>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="searchMCd" objName="searchMCd" parentID="Y" defName="${commonall}" />
					</td>
				
			</tr>
			
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 분류 서브타이틀 및 이벤트 버튼 start  --------------------- -->


<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
 <UL style="width: 100%;">
 	<LI style="float: left; width: 100%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>매입처품목별이익현황</h2>
        	<div>
          		<html:button id="btnNew"   		name="btnNew" 		auth="insert" /> 	   <%-- 신규 --%>
        		<html:button id="btnSearch" 	name="btnSearch"	auth="select" /> 	   <%-- 조회 --%>
				<html:button id="btnExcel"    	name="btnExcel"   	auth="excel"  /> 	   <%-- 엑셀 --%>
        	</div>
		</div>
		<!-- 서브타이틀 및 이벤트 버튼 end  -------- -->
			
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="tabList" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</LI>
 
<!-- CONTENT- BODY end ----------------------------------- -->

</div>

<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />


<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />
</body>
</html>