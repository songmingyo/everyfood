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

		initAnlBuyLdgrMonListGrid();

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
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	    newEvent);    // 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
		$('#btnPrint').unbind().click(null,	    printEvent);	// 출력 
			
		$("#btnBuyCd").unbind().click(function(){
			findBuyMst();
		});		<%--매입처 팝업 찾아가기--%>
		
		
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
		/*-----------------------------------------------------------------*/
		
	});
	

	/* 마스터 데이터  그리드 초기화 */
	function initAnlBuyLdgrMonListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[  
            			 '매입처명'
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
            ]
			,colModel:[
		                {name:'buyNm'	    		, index:'buyNm'	   			, sortable:true, width:180, align:"left"}
		            	,{name:'classNm'			, index:'classNm'			, sortable:true, width:100, align:"center"}
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
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	        	$(window).resize();
	        }
	        ,beforeProcessing: function(data) {
	        	
	        	var startYm = $("#searchYearMnSt").val();
            	var endYm = $("#searchYearMnEt").val();
            	
            	var startYmDate = new Date(startYm);
            	
            	var Mn1Date = startYmDate.toLocaleString();
            	
            	var nextYm2 = new Date(startYmDate.getFullYear(), startYmDate.getMonth() + 1);	            	
            	var Mn2Date = nextYm2.toLocaleString();
            	
            	var nextYm3 = new Date(startYmDate.getFullYear(), startYmDate.getMonth() + 2);	            	
            	var Mn3Date = nextYm3.toLocaleString();
            	
            	var nextYm4 = new Date(startYmDate.getFullYear(), startYmDate.getMonth() + 3);	            	
            	var Mn4Date = nextYm4.toLocaleString();
            	
            	var nextYm5 = new Date(startYmDate.getFullYear(), startYmDate.getMonth() + 4);	            	
            	var Mn5Date = nextYm5.toLocaleString();
            	
            	var nextYm6 = new Date(startYmDate.getFullYear(), startYmDate.getMonth() + 5);	            	
            	var Mn6Date = nextYm6.toLocaleString();
            	
            	var nextYm7 = new Date(startYmDate.getFullYear(), startYmDate.getMonth() + 6);	            	
            	var Mn7Date = nextYm7.toLocaleString();
            	
            	var nextYm8 = new Date(startYmDate.getFullYear(), startYmDate.getMonth() + 7);	            	
            	var Mn8Date = nextYm8.toLocaleString();
            	
            	var nextYm9 = new Date(startYmDate.getFullYear(), startYmDate.getMonth() + 8);	            	
            	var Mn9Date = nextYm9.toLocaleString();
            	
            	var nextYm10 = new Date(startYmDate.getFullYear(), startYmDate.getMonth() + 9);	            	
            	var Mn10Date = nextYm10.toLocaleString();
            	
            	var nextYm11 = new Date(startYmDate.getFullYear(), startYmDate.getMonth() + 10);	            	
            	var Mn11Date = nextYm11.toLocaleString();
            	
            	var nextYm12 = new Date(startYmDate.getFullYear(), startYmDate.getMonth() + 11);	            	
            	var Mn12Date = nextYm12.toLocaleString();
            	
            	$(this).jqGrid('setLabel', 'mon1', Mn1Date.substring(0,8) + ' 월');
            	$(this).jqGrid('setLabel', 'mon2', Mn2Date.substring(0,8) + ' 월');
            	$(this).jqGrid('setLabel', 'mon3', Mn3Date.substring(0,8) + ' 월');
            	$(this).jqGrid('setLabel', 'mon4', Mn4Date.substring(0,8) + ' 월');
            	$(this).jqGrid('setLabel', 'mon5', Mn5Date.substring(0,8) + ' 월');
            	$(this).jqGrid('setLabel', 'mon6', Mn6Date.substring(0,8) + ' 월');
            	$(this).jqGrid('setLabel', 'mon7', Mn7Date.substring(0,8) + ' 월');
            	$(this).jqGrid('setLabel', 'mon8', Mn8Date.substring(0,8) + ' 월');
            	$(this).jqGrid('setLabel', 'mon9', Mn9Date.substring(0,8) + ' 월');
            	$(this).jqGrid('setLabel', 'mon10', Mn10Date.substring(0,8) + ' 월');
            	$(this).jqGrid('setLabel', 'mon11', Mn11Date.substring(0,8) + ' 월');
            	$(this).jqGrid('setLabel', 'mon12', Mn12Date.substring(0,8) + ' 월');

            	var temp;

            	var colModel = $(this).jqGrid('getGridParam', 'colModel');
    
            	var nextMn = startYmDate.getMonth()+1;
                
            	var mn1 =  colModel[3] ;
            	
            	console.log(mn1);
            	
            	var mn2 =  colModel[4] ;
            	var mn3 =  colModel[5] ;
            	var mn4 =  colModel[6] ;
            	var mn5 =  colModel[7] ;
            	var mn6 =  colModel[8] ;
            	var mn7 =  colModel[9] ;
            	var mn8 =  colModel[10];
            	var mn9 =  colModel[11];
            	var mn10 = colModel[12];
            	var mn11 = colModel[13];
            	var mn12 = colModel[14];
            	
            	if(nextMn==2){
                	colModel[3] = mn2;
                	colModel[4] = mn3;
                	colModel[5] = mn4;
                	colModel[6] = mn5;
                	colModel[7] = mn6;
                	colModel[8] = mn7;
                	colModel[9]  = mn8;
                	colModel[10] = mn9;
                	colModel[11] = mn10;
                	colModel[12] = mn11;
                	colModel[13] = mn12;
                	colModel[14] = mn1;
            	}else if(nextMn==3){
            		colModel[3] = mn3;
                	colModel[4] = mn4;
                	colModel[5] = mn5;
                	colModel[6] = mn6;
                	colModel[7] = mn7;
                	colModel[8] = mn8;
                	colModel[9]  = mn9;
                	colModel[10] = mn10;
                	colModel[11] = mn11;
                	colModel[12] = mn12;
                	colModel[13] = mn1;
                	colModel[14] = mn2;
            	}else if(nextMn==4){
            		colModel[3] = mn4;
                	colModel[4] = mn5;
                	colModel[5] = mn6;
                	colModel[6] = mn7;
                	colModel[7] = mn8;
                	colModel[8] = mn9;
                	colModel[9]  = mn10;
                	colModel[10] = mn11;
                	colModel[11] = mn12;
                	colModel[12] = mn1;
                	colModel[13] = mn2;
                	colModel[14] = mn3;
            	}else if(nextMn==5){
            		colModel[3] = mn5;
                	colModel[4] = mn6;
                	colModel[5] = mn7;
                	colModel[6] = mn8;
                	colModel[7] = mn9;
                	colModel[8] = mn10;
                	colModel[9]  = mn11;
                	colModel[10] = mn12;
                	colModel[11] = mn1;
                	colModel[12] = mn2;
                	colModel[13] = mn3;
                	colModel[14] = mn4;
            	}else if(nextMn==6){
            		colModel[3] = mn6;
                	colModel[4] = mn7;
                	colModel[5] = mn8;
                	colModel[6] = mn9;
                	colModel[7] = mn10;
                	colModel[8] = mn11;
                	colModel[9]  = mn12;
                	colModel[10] = mn1;
                	colModel[11] = mn2;
                	colModel[12] = mn3;
                	colModel[13] = mn4;
                	colModel[14] = mn5;
            	}else if(nextMn==7){
            		colModel[3] = mn7;
                	colModel[4] = mn8;
                	colModel[5] = mn9;
                	colModel[6] = mn10;
                	colModel[7] = mn11;
                	colModel[8] = mn12;
                	colModel[9]  = mn1;
                	colModel[10] = mn2;
                	colModel[11] = mn3;
                	colModel[12] = mn4;
                	colModel[13] = mn5;
                	colModel[14] = mn6;
            	}else if(nextMn==8){
            		colModel[3] = mn8;
                	colModel[4] = mn9;
                	colModel[5] = mn10;
                	colModel[6] = mn11;
                	colModel[7] = mn12;
                	colModel[8] = mn1;
                	colModel[9]  = mn2;
                	colModel[10] = mn3;
                	colModel[11] = mn4;
                	colModel[12] = mn5;
                	colModel[13] = mn6;
                	colModel[14] = mn7;
            	}else if(nextMn==9){
            		colModel[3] = mn9;
                	colModel[4] = mn10;
                	colModel[5] = mn11;
                	colModel[6] = mn12;
                	colModel[7] = mn1;
                	colModel[8] = mn2;
                	colModel[9]  = mn3;
                	colModel[10] = mn4;
                	colModel[11] = mn5;
                	colModel[12] = mn6;
                	colModel[13] = mn7;
                	colModel[14] = mn8;
            	}else if(nextMn==10){
            		colModel[3] = mn10;
                	colModel[4] = mn11;
                	colModel[5] = mn12;
                	colModel[6] = mn1;
                	colModel[7] = mn2;
                	colModel[8] = mn3;
                	colModel[9]  = mn4;
                	colModel[10] = mn5;
                	colModel[11] = mn6;
                	colModel[12] = mn7;
                	colModel[13] = mn8;
                	colModel[14] = mn9;
            	}else if(nextMn==11){
            		colModel[3] = mn11;
                	colModel[4] = mn12;
                	colModel[5] = mn1;
                	colModel[6] = mn2;
                	colModel[7] = mn3;
                	colModel[8] = mn4;
                	colModel[9]  = mn5;
                	colModel[10] = mn6;
                	colModel[11] = mn7;
                	colModel[12] = mn8;
                	colModel[13] = mn9;
                	colModel[14] = mn10;
            	}else if(nextMn==12){
            		colModel[3] = mn12;
                	colModel[4] = mn1;
                	colModel[5] = mn2;
                	colModel[6] = mn3;
                	colModel[7] = mn4;
                	colModel[8] = mn5;
                	colModel[9]  = mn6;
                	colModel[10] = mn7;
                	colModel[11] = mn8;
                	colModel[12] = mn9;
                	colModel[13] = mn10;
                	colModel[14] = mn11;
            	}
            	
            	$(this).jqGrid('setGridParam', { colModel: colModel });
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
           ,sortname: 'searchBuyCd'
           ,sortorder: 'asc'
           ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
           ,viewrecords: true
           ,scroll : false
           ,rownumbers:true
           ,loadonce:true
           ,shrinkToFit : false
           ,autowidth:true
		});
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
	

	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
        
      	//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
	}

	/* grid1container / tabList Data 조회 */
	function searchEvent(event){

	    var searchInfo = {};
  	      
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
		
		$("#tabList").jqGrid('GridUnload');
		
		initAnlBuyLdgrMonListGrid();
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/as/anlys/anlBuyLdgrMonList_selList.json'/>",
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
		let excelName = "매입처원장(년별)";	//엑셀 다운로드 명
		
		exportExcel(gridObj, excelName);
		
		//$('#searchForm').attr("action", "<c:url value='/app/as/anlys/anlBuyLdgrMonList_selExcelDown'/>").submit();
	}
	
	/* 출력 */
	function printEvent(){
		var url = "<c:url value="/app/as/anlys/buyLdgrMonListPrint" />";
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
<form id="searchForm" name="searchForm" method="post">
<sec:csrfInput/>
	<fieldset>
	<legend>매입처원장(년간)</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매입처원장(년간) 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="*">
			<col width="100">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">조회월</label></th>
					<td>
						<input type="text" name="searchYearMnSt" id="searchYearMnSt" maxlength="6" style="width:70px" value="${util:getNowDateFmt('yyyy-MM')}"  style="width: 30%"/>
						<input type="button" id="searchMnStPicker" /> 
						<input type="text" name="searchYearMnEt" id="searchYearMnEt" maxlength="6" style="width:70px" value="${util:getNowDateFmt('yyyy-MM')}"  style="width: 30%"/>
						<input type="button" id="searchMnEtPicker" />  
					</td>
			
			<th><label for="sele2">매입처명</label></th>
				<td>
					<input type="text"   id="searchBuyNm"    name="searchBuyNm" style="width:30%;" autocomplete="off">
					<input type="hidden" id="searchBuyCd"    name="searchBuyCd" style="width:70%;">
					<button id="btnBuyCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
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
        	<h2>매입처원장(연간)</h2>
        	<div>
          		<html:button id="btnNew"   			name="btnNew" 	auth="insert" /> 		<%-- 신규 --%>
        		<html:button id="btnSearch" 	name="btnSearch"	auth="select" /> 	   <%-- 조회 --%>
				<html:button id="btnExcel"    	name="btnExcel"   	auth="excel" 	/> 	   <%-- 엑셀 --%>
				<html:button id="btnPrint"    	name="btnPrint"   	auth="print" 	/> 	   <%-- 출력 --%>
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

</body>
</html>