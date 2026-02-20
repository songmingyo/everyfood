<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#startDt").datepicker();
		$("#endDt").datepicker();
		initAccSalesDepListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	    newEvent);    // 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
		$('#btnCreditPrt').unbind().click(null,		creditPrt); 	 // 출력
		
		$('#btnSalesPrMstFind').unbind().click(function(){ findSalesPrMst()}); 	// 영업사원찾기 팝업 버튼이벤트	
		$('#btnSales_find').unbind().click(function(){ findSalesMst()}); 	// 매출처찾기 팝업 버튼이벤트
		
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
		$('#salesNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findSalesMst(this); break; // enter
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
		/*-----------------------------------------------------------------*/
		
	});
	
	
	
	/*매출처 찾기팝업 호출 */
	function findSalesMst(){
		commonSalesMstFindLayer('','',$("#salesNm").val(), '', setSalesyMstFindLayer,'Y');
	}
	/*매출처 콜백(후처리 함수 )*/
	function setSalesyMstFindLayer(data) {
		$("#salesCd").val(data.salesCd);
		$("#salesNm").val(data.salesNm);
	}
	
	/*영업사원 찾기팝업 호출*/
	function findSalesPrMst() {
		commonSalesPrMstFindLayer('','',$("#salesPrNm").val(), setSalesPrMstFindLayer);
	}
	/*영업사원 콜백(후처리 함수 )*/
	function setSalesPrMstFindLayer(data){
		if (data != null){
			$("#salesPrCd").val(data.salesPrCd);
		 	$("#salesPrNm").val(data.salesPrNm);
		}
	}

	/* 마스터 데이터  그리드 초기화 */
	function initAccSalesDepListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[ '매출처구분'
            			, '매출처'
            			, '매출처명'
            			, '잔액'
            			, '매출'
            			, '입금'
            			, '잔액'
            			, '매출'
            			, '입금'
            			, '잔액'
            			, '결제조건'
            			, '영업사원'
            			, '여신한도'
            			
            			, '출력타이틀1'
            			, '출력타이틀2'
            			, '출력타이틀3'
            			, '출력타이틀4'
            			, '출력타이틀5'
            			, '출력타이틀6'
            			, '출력타이틀7'
            			, '시작일'
            			, '종료일'
            			, '영업사원조회'
            			, '매출처조회'
            			, '구분'
            			, '사업자 등록번호'
           ]
			,colModel:[
		                 {name:'salesClassNm'    		, index:'salesClassNm' 		, sortable:true, width:100, align:"center"}
		            	,{name:'salesCd'				, index:'salesCd'			, sortable:true, width:100, align:"center"}
		            	,{name:'salesNm'		        , index:'salesNm'			, sortable:true, width:180, align:"left"}
		            	,{name:'balAmt1'		    	, index:'balAmt1'			, sortable:true, sorttype:'number', width:110, align:"right", formatter:'integer'}
		            	,{name:'salesAmt2'	  			, index:'salesAmt2'			, sortable:true, sorttype:'number', width:110, align:"right", formatter:'integer'}
		            	,{name:'depAmt2'		   		, index:'depAmt2'	    	, sortable:true, sorttype:'number', width:110, align:"right", formatter:'integer'}
		            	,{name:'balAmt2'	   		 	, index:'balAmt2'		    , sortable:true, sorttype:'number', width:110, align:"right", formatter:'integer'}
		            	,{name:'salesAmt3'	     		, index:'salesAmt3'	  	    , sortable:true, sorttype:'number', width:110, align:"right", formatter:'integer'}
		            	,{name:'depAmt3'				, index:'depAmt3'	   		, sortable:true, sorttype:'number', width:110, align:"right", formatter:'integer'}
		            	,{name:'balAmt3'		        , index:'balAmt3'			, sortable:true, sorttype:'number', width:110, align:"right", formatter:'integer'}
		            	,{name:'setlCon'		  	    , index:'setlCon'		    , sortable:true, width:120, align:"center"}
		        		,{name:'salesPrNm'				, index:'salesPrNm'	   		, sortable:true, width:110, align:"center"}
		            	,{name:'creLim'		  	        , index:'creLim'			, sortable:true, width:100, align:"right", formatter:'integer'}
		            	
		            	,{name:'title1'					, index:'title1'	   		, sortable:true, hidden:true, width:110, align:"center"}
			            ,{name:'title2'					, index:'title2'	   		, sortable:true, hidden:true, width:110, align:"center"}
			            ,{name:'title3'					, index:'title3'	   		, sortable:true, hidden:true, width:110, align:"center"}
			            ,{name:'title4'					, index:'title4'	   		, sortable:true, hidden:true, width:110, align:"center"}
			            ,{name:'title5'					, index:'title5'	   		, sortable:true, hidden:true, width:110, align:"center"}
			            ,{name:'title6'					, index:'title6'	   		, sortable:true, hidden:true, width:110, align:"center"}
			            ,{name:'title7'					, index:'title7'	   		, sortable:true, hidden:true, width:110, align:"center"}
		            	,{name:'startDt'				, index:'startDt'	   		, sortable:true, hidden:true, width:110, align:"center"}
		            	,{name:'endDt'					, index:'endDt'	   			, sortable:true, hidden:true, width:110, align:"center"}
		            	,{name:'salesPrNmSearch'		, index:'salesPrNmSearch'	, sortable:true, hidden:true, width:110, align:"center"}
		            	,{name:'salesNmSearch'			, index:'salesNmSearch'	   	, sortable:true, hidden:true, width:110, align:"center"}
		            	,{name:'searchHqCdNm'			, index:'searchHqCdNm'	   	, sortable:true, hidden:true, width:110, align:"center"}
		            	,{name:'bsnsNum'				, index:'bsnsNum'	   		, width:110, align:"center"}
		            	
		     ]
			 ,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				//gridData.jqGrid('hideCol', ["rn"]);
				
				/* 조회후 데이터가 한건이라도 있을경우  */
				if(allRows.length > 0 && $("#blankRow").val() != ""){
					$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
					
					// grid rowNum 재설정 (-1로 설정한 경우 row가 많으면 sort 시 row수가 감소)
					//gridData.setGridParam({rowNum:allRows.length});
					
				}
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'salesCd' : '계' });
				
                var sum_balAmt1 = gridData.jqGrid('getCol','balAmt1', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'balAmt1':sum_balAmt1 });
		    	
		    	
		    	var sum_salesAmt2 = gridData.jqGrid('getCol','salesAmt2', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'salesAmt2':sum_salesAmt2 });
		    	
		
		    	var sum_depAmt2 = gridData.jqGrid('getCol','depAmt2', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'depAmt2':sum_depAmt2 });
		    	
		    	var sum_balAmt2 = gridData.jqGrid('getCol','balAmt2', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'balAmt2':sum_balAmt2 });
		    	
		   
		    	var sum_salesAmt3 = gridData.jqGrid('getCol','salesAmt3', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'salesAmt3':sum_salesAmt3 });
		    
		    	
		       	var sum_depAmt3 = gridData.jqGrid('getCol','depAmt3', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'depAmt3':sum_depAmt3 });
		    
		    	
		    	var sum_balAmt3 = gridData.jqGrid('getCol','balAmt3', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'balAmt3':sum_balAmt3 });
		    	
		    	
	        //    $(window).resize();
	        },
			loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }else{
	            	var startDt = $("#startDt").val();
	            	
	            	var dateObject = new Date(startDt);	            	
	            	dateObject.setMonth(dateObject.getMonth() - 2);	            	
	            	Mn2Date = dateObject.getFullYear() + ('0' + (dateObject.getMonth() + 1)).slice(-2) + ('0' + dateObject.getDate()).slice(-2);
	            	
	            	var dateObject = new Date(startDt);	            	
					dateObject.setMonth(dateObject.getMonth() - 1);	            	
	            	Mn1Date = dateObject.getFullYear() + ('0' + (dateObject.getMonth() + 1)).slice(-2) + ('0' + dateObject.getDate()).slice(-2);
	            	
	            	var dateObject = new Date(startDt);	            	
					dateObject.setMonth(dateObject.getMonth() - 0);	            	
	            	Mn0Date = dateObject.getFullYear() + ('0' + (dateObject.getMonth() + 1)).slice(-2) + ('0' + dateObject.getDate()).slice(-2);
	            	
	            	$(this).jqGrid('setLabel', 'balAmt1', Mn2Date.substring(2,4)+'년 '+Mn2Date.substring(4,6)+'월 잔액');
	            	
	            	$(this).jqGrid('setLabel', 'salesAmt2', Mn1Date.substring(2,4)+'년 '+Mn1Date.substring(4,6)+'월 매출');
	            	$(this).jqGrid('setLabel', 'depAmt2', Mn1Date.substring(2,4)+'년 '+Mn1Date.substring(4,6)+'월 입금');
	            	$(this).jqGrid('setLabel', 'balAmt2', Mn1Date.substring(2,4)+'년 '+Mn1Date.substring(4,6)+'월 잔액');
	            	
	            	$(this).jqGrid('setLabel', 'salesAmt3', Mn0Date.substring(2,4)+'년 '+Mn0Date.substring(4,6)+'월 매출');
	            	$(this).jqGrid('setLabel', 'depAmt3', Mn0Date.substring(2,4)+'년 '+Mn0Date.substring(4,6)+'월 입금');
	            	$(this).jqGrid('setLabel', 'balAmt3', Mn0Date.substring(2,4)+'년 '+Mn0Date.substring(4,6)+'월 잔액');
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
			,rownumbers:true													// rowNumber 표시여부
			,autowidth:true		
			,loadonce : true
			,footerrow: true														// 전체 합계를 낼때 사용
			//,shrinkToFit : false
			,sortorder : "desc"
		});
	}

	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
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
			url:"<c:url value='/app/as/anlys/anlCreditSalesList_selList.json'/>",
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
// 		let excelName = "외상매출금(상세)";	//엑셀 다운로드 명
		
// 		exportExcel(gridObj, excelName);
		
		$('#searchForm').attr("action", "<c:url value='/app/as/anlys/anlCreditSalesList_selExcelDown'/>").submit();
	}
	
	/* PDF Report */
	function creditPrt(){

		var url = "<c:url value="/app/as/anlys/anlCreditSalesListPrint" />";
		var target = "PDFPrint";
		var agt = navigator.userAgent.toLowerCase();
		
		var gridData = $("#tabList").jqGrid("getRowData");
		var jsonData = JSON.stringify(gridData);

		$("#gridDataInput").val(jsonData);
		
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
	
	function sales_clear(){
		$("#salesCd").val('');
		$("#salesNm").val('');
	}
	
	function salesPr_clear(){
		$("#salesPrCd").val('');
		$("#salesPrNm").val('');
	}
	
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">

<input type="hidden" 	id="gridDataInput"	name="gridDataInput" value="">

<input type="hidden" 	id="searchHqCdNm"	name="searchHqCdNm" value="">

<sec:csrfInput/>
	<fieldset>
	<legend>매출처 관리</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처 검색조건</caption>
		<colgroup>
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">일자</label></th>
					<td>
						<input type="text" class="dt" id="startDt" name="startDt"  readonly="readonly" style="width: 30% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="endDt" name="endDt"  readonly="readonly" style="width: 30% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/>  
					</td>
				<th><label for="sele2">구분</th>
					<td>
						<html:codeTag comType="SELECT" objId="searchHqCd" objName="searchHqCd" parentID="M004" /> <%--전체 --%>
					</td>
				<th><label for="sele2">매출처구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="salesClass" objName="salesClass" parentID="M013"  defName="${commonall}"   />
					</td>
			</tr>
			<tr>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text" id="salesNm" name="salesNm" style="width: 80%;" onclick="sales_clear()">
						<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;">
						<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">영업사원</label></th>
					<td colspan=3>
						<input type="text" id="salesPrNm" name="salesPrNm" style="width: 30%;" onclick="salesPr_clear()">
						<input type="hidden" id="salesPrCd" name="salesPrCd" readonly="readonly"  style="width: 20%;">
						<button id="btnSalesPrMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
			</tr>
			
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 분류 서브타이틀 및 이벤트 버튼 start  --------------------- -->
<!-- 
<div class="tit_area">
	<h2>TITLE</h2>
	<div>
		<html:button id="btnInit" 	auth="insert"  /> 			<%-- 신규 --%>  
		<html:button id="btnSave" 	auth="save"   /> 			<%-- 저장 --%>
		<%-- <html:button id="btnDelete" auth="delete"  /> 			 삭제 --%>
		&nbsp;
		<html:button id="btnSearch" auth="select" /> 			<%-- 조회 --%>
	</div>
</div>
 -->


<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
 <UL style="width: 100%;">
 	<LI style="float: left; width: 100%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>외상매출금(상세)</h2>
        	<div>
          		<html:button id="btnNew"  		    name="btnNew" 	   		auth="insert" /> 		<%-- 신규 --%>
        		<html:button id="btnSearch" 	    name="btnSearch"		auth="select" /> 	   <%-- 조회 --%>
				<html:button id="btnExcel"    	    name="btnExcel"   		auth="excel"  /> 	   <%-- 엑셀 --%>
                <html:button id="btnCreditPrt" 		name="btnCreditPrt"  	auth="select"  value="출력"  />         	
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
<jsp:include page="/WEB-INF/views/app/common/find_SalesMaster.jsp" />



<!-- 영업사원 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_salesPrMaster.jsp" />
</body>
</html>