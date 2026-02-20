<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#startDt").datepicker();
		$("#endDt").datepicker();
		
		initCusPriceUnconfListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	    newEvent); 	// 신규  
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
		$('#btnPricePrt').unbind().click(null,		pricePrt); 	 // 출력
		$('#btnSalesPrMstFind').unbind().click(function(){ findSalesPrMst()}); 	// 영업사원찾기 팝업 버튼이벤트	
		$('#btnSales_find').unbind().click(function(){ findSalesMst()}); 	// 매출처찾기 팝업 버튼이벤트
			
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-120);	//GRID  LIST
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
	

	/* 마스터 데이터  그리드 초기화 */
	function initCusPriceUnconfListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '매출일자'
            			, '품목명'
            			, '규격'
            			, '판매수량'
            			, '매입단가'
            			, '매출단가'
            			, '전표번호'
            			, '매출처명'
            			, '영업담당자'
            			, '등록자'
            			, '등록시간'
            			, '수정자'
            			, '수정시간'
           ]
			,colModel:[
		                 {name:'salesDt'		    , index:'salesDt'		    , sortable:true, width:80 , align:"center"}
		            	,{name:'prdtNm'	         	, index:'prdtNm'	    	, sortable:true, width:180, align:"left"}
		            	,{name:'prdtStd'			, index:'prdtStd'			, sortable:true, width:100, align:"center"}
		            	,{name:'salesQty'		    , index:'salesQty'			, sortable:true, width:100,  align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'cost'		    	, index:'cost'	    		, sortable:true, width:100,  align:"right", formatter:'integer'}
		            	,{name:'pureAmt'		   	, index:'pureAmt'	    	, sortable:true, width:100,  align:"right", formatter:'integer'}
		            	,{name:'salesSlipNo'    	, index:'salesSlipNo'	    , sortable:true, width:110, align:"center"}
		            	,{name:'salesNm'	       	, index:'salesNm'		    , sortable:true, width:180, align:"left"}
		            	,{name:'salesPrNm'	     	, index:'salesPrNm'	    	, sortable:true, width:130, align:"center"}
		               	,{name:'regId'		        , index:'regId'				, sortable:true, width:120, align:"center"}
		            	,{name:'regDt'		        , index:'regDt'				, sortable:true, width:130, align:"center"}
		            	,{name:'modId'	        	, index:'modId' 			, sortable:true, width:120, align:"center"}
		            	,{name:'modDt'		        , index:'modDt'	   			, sortable:true, width:130, align:"center"}
		           		
		        
		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
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
           ,loadui : "disable"
           ,gridview:    true
           //,pager: '#pageList'
           ,sortname: 'prdtNm'
           ,sortorder: 'asc'
           ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
           ,viewrecords: true
           ,scroll : false
           ,rownumbers:true
           ,shrinkToFit : false
           ,loadonce:true
           ,autowidth:true
		});
	}
	

	/* grid1container / tabList Data 조회 */
	function searchEvent(event){
  	
     	var searchInfo = {};
  	      
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');		
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/sm/custsales/cusPriceUnconf_selList.json'/>",
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
		commonSalesMstFindLayer('','',$("#salesNm").val(), 'Y', setSalesMstFindLayer);
	}
	
	/*매출처 콜백(후처리 함수 )*/
	function setSalesMstFindLayer(data) {
		$("#salesCd").val(data.salesCd);
		$("#salesNm").val(data.salesNm);
		$("#hqClass").val(data.hqClass);
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
		
	/* 엑셀  */
	function excelEvent(){
		$('#searchForm').attr("action", "<c:url value='/app/sm/custsales/cusPriceUnconfList_selExcelDown'/>").submit();
	}
	
	/*PDF Report*/
	function pricePrt(){		
		searchForm.action="<c:url value="/app/sm/custsales/cusPriceListPrint" />";
		searchForm.submit();
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
<input type="hidden" id="hqClass" name="hqClass""/>
<sec:csrfInput/>
	<fieldset>

	<legend>매출단가미확정건</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출단가미확정건</caption>
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
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text" id="salesNm" name="salesNm" style="width: 80%;" onclick="sales_clear()">
						<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;">
						<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">영업사원</label></th>
					<td>
						<input type="text" id="salesPrNm" name="salesPrNm" style="width: 80%;">
						<input type="hidden" id="salesPrCd" name="salesPrCd" readonly="readonly"  style="width: 20%;">
						<button id="btnSalesPrMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
 <UL style="width: 100%;">
 	<LI style="float: left; width: 100%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>매출단가미확정건</h2>
        	<div>
        		<html:button id="btnNew"   			name="btnNew" 	auth="insert" /> 		<%-- 신규 --%>
        		<html:button id="btnSearch" 	name="btnSearch"	auth="select" /> 	   <%-- 조회 --%>
				<html:button id="btnExcel"    	name="btnExcel"   	auth="excel" 	/> 	   <%-- 엑셀  --%>
			<%--	<html:button id="btnMasterDelete" 	auth="delete" />  	 삭제 --%>
<%-- 			<html:button id="btnPricePrt" 		name="btnPricePrt"   		auth="select"  value="출력"  /> --%>
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