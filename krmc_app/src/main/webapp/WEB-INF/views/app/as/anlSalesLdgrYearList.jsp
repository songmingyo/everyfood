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


		initAnlSalesYearListGrid();

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
		  
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
			
		$("#btnSalesCd").unbind().click(function(){
			findSalesMst();
		});	
		
		$('#btnSalesPrMstFind').unbind().click(function(){ 
			findSalesPrMst()
		});	
		
		
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280){
				    $('#tabList').setGridHeight(height-100);	//GRID  LIST
			    }
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-160);	//GRID  LIST
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
	}
	

	/* 마스터 데이터  그리드 초기화 */
	function initAnlSalesYearListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '구분'
            			, '매출처명'
            			, '항목'
            			, '01월'
            			, '02월'
            			, '03월'
            			, '04월'
            			, '05월'
            			, '06월'
            			, '07월'
            			, '08월'
            			, '09월'
            			, '10월'
            			, '11월'
            			, '12월'
            			, '합계'
            			, '영업사원'
            ]
			,colModel:[
						{name:'salesClassNm'		, index:'salesClassNm'		, sortable:true, width:100, align:"center"}
            			,{name:'salesNm'	    	, index:'salesNm'		    , sortable:true, width:180, align:"left"}
		            	,{name:'classNm'	    	, index:'classNm'	    	, sortable:true, width:100, align:"center"}
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
		        		,{name:'totAmt'				, index:'totAmt'	   		, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'salesPrNm'		    , index:'salesPrNm'			, sortable:true, width:120, align:"center"}
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
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
			url:"<c:url value='/app/as/anlys/anlSalesLdgrYearList_selList.json'/>",
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
		$('#searchForm').attr("action", "<c:url value='/app/as/anlys/anlSalesLdgrYearList_selExcelDown'/>").submit();
	}
	
	function sales_clear(){
		$('#searchSalesNm').val('');
		$('#searchSalesCd').val('');
	}
	
	function salesPr_clear(){
		$('#searchSalesPrNm').val('');
		$('#searchSalesPrCd').val('');
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
	<legend>매출처원장(년간)</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처원장(년간)</caption>
		<colgroup>
			<col width="100">
			<col width="15%">
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
				<th><label for="sele2">조회월</label></th>
					<td>
						<input type="text" name="searchYearMnSt" id="searchYearMnSt" maxlength="6" style="width:70px" value="${util:getNowDateFmt('yyyy-MM')}" readonly="readonly" style="width: 80%"/>
						<input type="button" id="searchMnStPicker" /> 
						<input type="text" name="searchYearMnEt" id="searchYearMnEt" maxlength="6" style="width:70px" value="${util:getNowDateFmt('yyyy-MM')}" readonly="readonly" style="width: 80%"/>
						<input type="button" id="searchMnEtPicker" />    
					</td>
				<th><label for="sele2">구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchHqClass" objName="searchHqClass" parentID="M004" />
					</td>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text"   id="searchSalesNm"    name="searchSalesNm" style="width:80%;" onclick="sales_clear()">
						<input type="hidden" id="searchSalesCd"    name="searchSalesCd" style="width:40%;">
						<button id="btnSalesCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">영업사원</label></th>
					<td>
						<input type="text"   id="searchSalesPrNm"  name="searchSalesPrNm" style="width:80%;" onclick="salesPr_clear()">
						<input type="hidden" id="searchSalesPrCd"  name="searchSalesPrCd" style="width:70%;">
						<button id="btnSalesPrMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">매출처구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchSalesClass" objName="searchSalesClass" parentID="M013"  defName="${commonall}"   />
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
        	<h2>매출처원장(연간)</h2>
        	<div>
          		<html:button id="btnNew"   		name="btnNew" 		auth="insert" />
        		<html:button id="btnSearch" 	name="btnSearch"	auth="select" />
				<html:button id="btnExcel"    	name="btnExcel"   	auth="excel" 	/>
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