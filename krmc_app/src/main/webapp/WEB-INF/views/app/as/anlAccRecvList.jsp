<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#searchStartDt").datepicker();
		$("#searchEndDt").datepicker();
		initAccRecvListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent);
		$('#btnNew').unbind().click(null,	    newEvent); 
		$('#btnExcel').unbind().click(null,	    excelEvent); 
		$('#btnPrint').unbind().click(null,	    printEvent);
			
		$("#btnBuyCd").unbind().click(function(){
			findBuyMst();
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
			        $('#tabList').setGridHeight(height-180);	//GRID  LIST
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
		/*-----------------------------------------------------------------*/

		
	});
	
	/* 마스터 데이터  그리드 초기화 */
	function initAccRecvListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[ '매입처명'
            			, '잔액'
            			, '매입'
            			, '지급'
            			, '잔액'
            			, '매입'
            			, '지급'
            			, '잔액'
            			, '결제조건'
            			, '은행명'
            			, '계좌번호'
            			, '예금주'
            			, '대표전화번호'
            			, '사업자번호'
            			
           ]
			,colModel:[
		                 {name:'buyNm'		   	        , index:'buyNm'		    , sortable:true, width:180 , align:"left"}
		            	,{name:'balAmt1'	       		, index:'balAmt1'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'buyAmt2'			    , index:'buyAmt2'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'payAmt2'		        , index:'payAmt2'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'balAmt2'		    	, index:'balAmt2'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'buyAmt3'	  	    	, index:'buyAmt3'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'payAmt3'	   		 	, index:'payAmt3'	    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'balAmt3'	   		 	, index:'balAmt3'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'setlCon'	     		, index:'setlCon'	  	, sortable:true, width:100, align:"center"}
		            	,{name:'bankNm'			    	, index:'bankNm'	   	, sortable:true, width:140, align:"center"}
		            	,{name:'bankAccNum'		        , index:'bankAccNum'	, sortable:true, width:160, align:"center"}
		            	,{name:'bankDep'		        , index:'bankDep'		, sortable:true, width:140, align:"center"}
		        		,{name:'telNo'				  	, index:'telNo'	   		, sortable:true, width:120, align:"center"}
		            	,{name:'bsnsNum'		        , index:'bsnsNum'		, sortable:true, width:140, align:"center"}		            	
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            var colModel = $(this).jqGrid('getGridParam', 'colModel'); // 컬럼명을 배열형태로 가져온다.
	            
	            var gridData = $("#tabList");
	            var allRows = gridData.jqGrid('getDataIDs');
	            
	            /* 조회후 데이터가 한건이라도 있을경우  */
				if(allRows.length > 0 && $("#blankRow").val() != ""){
					$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
					
				}
				
				// ===================== Footer Sum 
				gridData.jqGrid('footerData', 'set', { 'buyNm' : '합계' });
				
                var sum_balAmt1 = gridData.jqGrid('getCol','balAmt1', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'balAmt1':sum_balAmt1 });
		    	
		    	var sum_buyAmt2 = gridData.jqGrid('getCol','buyAmt2', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'buyAmt2':sum_buyAmt2 });
		    	
		    	var sum_payAmt2 = gridData.jqGrid('getCol','payAmt2', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'payAmt2':sum_payAmt2 });
		    	
		    	var sum_balAmt2 = gridData.jqGrid('getCol','balAmt2', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'balAmt2':sum_balAmt2 });
		    	
		    	var sum_buyAmt3 = gridData.jqGrid('getCol','buyAmt3', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'buyAmt3':sum_buyAmt3 });
		    	
		    	var sum_payAmt3 = gridData.jqGrid('getCol','payAmt3', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'payAmt3':sum_payAmt3 });
		    	
		    	var sum_balAmt3 = gridData.jqGrid('getCol','balAmt3', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'balAmt3':sum_balAmt3 });
	            
	        	$(window).resize();
	        }
			,loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }else{
	            	var startDt = $("#searchStartDt").val();

	            	var dateObject = new Date(startDt);
	            	dateObject.setMonth(dateObject.getMonth() - 2);
	            	var Mn2Date = dateObject.toLocaleString();
	            	
	            	var dateObject = new Date(startDt);
					dateObject.setMonth(dateObject.getMonth() - 1);
	            	var Mn1Date = dateObject.toLocaleString();
	            	
	            	var dateObject = new Date(startDt);
					dateObject.setMonth(dateObject.getMonth() - 0);
	            	var Mn0Date = dateObject.toLocaleString();
	            	
	            	var f_Index_2 = Mn2Date.indexOf(".");
	            	var s_Index_2 = Mn2Date.indexOf(".", f_Index_2 + 1);
	            	
	            	var f_Index_1 = Mn1Date.indexOf(".");
	            	var s_Index_1 = Mn1Date.indexOf(".", f_Index_1 + 1);
	            	
	            	var f_Index_0 = Mn0Date.indexOf(".");
	            	var s_Index_0 = Mn0Date.indexOf(".", f_Index_0 + 1);
	            	
	            	$(this).jqGrid('setLabel', 'balAmt1', Mn2Date.substring(0,s_Index_2)+' 월 잔액');
	            	
	            	$(this).jqGrid('setLabel', 'buyAmt2', Mn1Date.substring(0,s_Index_1)+' 월 매입');
	            	$(this).jqGrid('setLabel', 'payAmt2', Mn1Date.substring(0,s_Index_1)+' 월 지급');
	            	$(this).jqGrid('setLabel', 'balAmt2', Mn1Date.substring(0,s_Index_1)+' 월 잔액');
	            	
	            	$(this).jqGrid('setLabel', 'buyAmt3', Mn0Date.substring(0,s_Index_0)+' 월 매입');
	            	$(this).jqGrid('setLabel', 'payAmt3', Mn0Date.substring(0,s_Index_0)+' 월 지급');
	            	$(this).jqGrid('setLabel', 'balAmt3', Mn0Date.substring(0,s_Index_0)+' 월 잔액');
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
           ,shrinkToFit : false
           ,footerrow: true
           ,loadonce:true
           ,autowidth:true
		});
	}


	/*매입처 찾기팝업 호출 */
	function findBuyMst(){
		commonBuyMstFindLayer('','',$("#searchBuyNm").val(), setBuyMstFindLayer);
	}

	/*매입처 콜백(후처리 함수 )*/
	function setBuyMstFindLayer(data) {
		$("#searchBuyCd").val(data.buyCd);
		$("#searchBuyNm").val(data.buyNm);
	}


	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
        
        $("#tabList").jqGrid('clearGridData');
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
			url:"<c:url value='/app/as/anlys/anlAccRecvList_selList.json'/>",
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
		let gridObj = $("#tabList");	//grid Obj
		let excelName = "외상매입금";	//엑셀 다운로드 명
		
		exportExcel(gridObj, excelName);
		//$('#searchForm').attr("action", "<c:url value='/app/as/anlys/anlAccRecvList_selExcelDown'/>").submit();
	}
	
	
	/* 출력 */
	function printEvent(){
		
		var url = "<c:url value="/app/as/salesmgmt/anlAccRecvList_prtList.json" />";
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
	
	
	function buy_clear(event){
		$('#searchBuyNm').val('');
		$('#searchBuyCd').val('');
	}
	
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post">
<sec:csrfInput/>
	<fieldset>
	<legend>외상매입금 관리</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>외상매입금 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="*">
			<col width="100">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">일자</label></th>
					<td>
						<input type="text" class="dt" id="searchStartDt" name="searchStartDt"  readonly="readonly" style="width: 20% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="searchEndDt" name="searchEndDt"  readonly="readonly" style="width: 20% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/>  
					</td>
				<th><label for="sele2">매입처명</label></th>
					<td>
						<input type="text"   id="searchBuyNm"    name="searchBuyNm" style="width:30%;" onclick="buy_clear()" autocomplete="off" >
						<input type="hidden" id="searchBuyCd"    name="searchBuyCd" style="width:40%;">
						<button id="btnBuyCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
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
        	<h2>외상매입금</h2>
        	<div>
          		<html:button id="btnNew"   		name="btnNew"    	auth="insert" />
        		<html:button id="btnSearch" 	name="btnSearch"	auth="select" />
				<html:button id="btnExcel"    	name="btnExcel"   	auth="excel" />
				<html:button id="btnPrint"    	name="btnPrint"   	auth="print" />
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

<!-- 매출처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />

</body>
</html>