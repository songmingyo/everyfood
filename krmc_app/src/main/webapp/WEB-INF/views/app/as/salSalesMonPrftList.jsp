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
		
		initSalesGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
		
		$('#btnSales_find').unbind().click(function(){ findSalesMst()}); 	// 매출처찾기 팝업 버튼이벤트
		$('#btnPrdt_find').unbind().click(function(){ findPrdtMst()}); 	// 품목찾기 팝업 버튼이벤트
		$('#btnSalesPr_Find').unbind().click(function(){ findSalesPrMst()}); 	// 영업사원찾기 팝업 버튼이벤트
		
		//Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-50);	//GRID  LIST
			    } 
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-70);	//GRID  LIST
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
	       	       			
           ],
			colModel:[
					{name:'salesClassNm'	, index:'salesClassNm'	, sortable:true, editable:false, width:100 , align:"center"}
					,{name:'salesNm'	    , index:'salesNm'		, sortable:true, editable:false, width:180 , align:"left"}
					,{name:'classNm'		, index:'classNm'		, sortable:true, editable:false, width:120 , align:"center"}
					,{name:'mon1'		    , index:'mon1'			, sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	            	,{name:'mon2'	    	, index:'mon2'			, sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	            	,{name:'mon3'		    , index:'mon3'          , sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	            	,{name:'mon4'		    , index:'mon4'	    	, sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	            	,{name:'mon5'		    , index:'mon5'	        , sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	            	,{name:'mon6'	    	, index:'mon6'       	, sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	            	,{name:'mon7'			, index:'mon7'			, sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	            	,{name:'mon8'	    	, index:'mon8'	    	, sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	                ,{name:'mon9'		    , index:'mon9'          , sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	            	,{name:'mon10'		    , index:'mon10'	    	, sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	            	,{name:'mon11'		    , index:'mon11'	        , sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	            	,{name:'mon12'	    	, index:'mon12'         , sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	            	,{name:'monTot'			, index:'monTot'		, sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
	            	,{name:'salesPrNm'		, index:'salesPrNm'		, sortable:true, editable:false, width:100 , align:"center" }

		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
				$(window).resize();
	        },
	        beforeProcessing: function(data) {

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
                
            	var mn1 = colModel[4];
            	var mn2 = colModel[5];
            	var mn3 = colModel[6];
            	var mn4 = colModel[7];
            	var mn5 = colModel[8];
            	var mn6 = colModel[9];
            	var mn7 = colModel[10];
            	var mn8 = colModel[11];
            	var mn9 = colModel[12];
            	var mn10 = colModel[13];
            	var mn11 = colModel[14];
            	var mn12 = colModel[15];
            	
            	if(nextMn==2){
                	colModel[4] = mn2;
                	colModel[5] = mn3;
                	colModel[6] = mn4;
                	colModel[7] = mn5;
                	colModel[8] = mn6;
                	colModel[9] = mn7;
                	colModel[10] = mn8;
                	colModel[11] = mn9;
                	colModel[12] = mn10;
                	colModel[13] = mn11;
                	colModel[14] = mn12;
                	colModel[15] = mn1;
            	}else if(nextMn==3){
            		colModel[4] = mn3;
                	colModel[5] = mn4;
                	colModel[6] = mn5;
                	colModel[7] = mn6;
                	colModel[8] = mn7;
                	colModel[9] = mn8;
                	colModel[10] = mn9;
                	colModel[11] = mn10;
                	colModel[12] = mn11;
                	colModel[13] = mn12;
                	colModel[14] = mn1;
                	colModel[15] = mn2;
            	}else if(nextMn==4){
            		colModel[4] = mn4;
                	colModel[5] = mn5;
                	colModel[6] = mn6;
                	colModel[7] = mn7;
                	colModel[8] = mn8;
                	colModel[9] = mn9;
                	colModel[10] = mn10;
                	colModel[11] = mn11;
                	colModel[12] = mn12;
                	colModel[13] = mn1;
                	colModel[14] = mn2;
                	colModel[15] = mn3;
            	}else if(nextMn==5){
            		colModel[4] = mn5;
                	colModel[5] = mn6;
                	colModel[6] = mn7;
                	colModel[7] = mn8;
                	colModel[8] = mn9;
                	colModel[9] = mn10;
                	colModel[10] = mn11;
                	colModel[11] = mn12;
                	colModel[12] = mn1;
                	colModel[13] = mn2;
                	colModel[14] = mn3;
                	colModel[15] = mn4;
            	}else if(nextMn==6){
            		colModel[4] = mn6;
                	colModel[5] = mn7;
                	colModel[6] = mn8;
                	colModel[7] = mn9;
                	colModel[8] = mn10;
                	colModel[9] = mn11;
                	colModel[10] = mn12;
                	colModel[11] = mn1;
                	colModel[12] = mn2;
                	colModel[13] = mn3;
                	colModel[14] = mn4;
                	colModel[15] = mn5;
            	}else if(nextMn==7){
            		colModel[4] = mn7;
                	colModel[5] = mn8;
                	colModel[6] = mn9;
                	colModel[7] = mn10;
                	colModel[8] = mn11;
                	colModel[9] = mn12;
                	colModel[10] = mn1;
                	colModel[11] = mn2;
                	colModel[12] = mn3;
                	colModel[13] = mn4;
                	colModel[14] = mn5;
                	colModel[15] = mn6;
            	}else if(nextMn==8){
            		colModel[4] = mn8;
                	colModel[5] = mn9;
                	colModel[6] = mn10;
                	colModel[7] = mn11;
                	colModel[8] = mn12;
                	colModel[9] = mn1;
                	colModel[10] = mn2;
                	colModel[11] = mn3;
                	colModel[12] = mn4;
                	colModel[13] = mn5;
                	colModel[14] = mn6;
                	colModel[15] = mn7;
            	}else if(nextMn==9){
            		colModel[4] = mn9;
                	colModel[5] = mn10;
                	colModel[6] = mn11;
                	colModel[7] = mn12;
                	colModel[8] = mn1;
                	colModel[9] = mn2;
                	colModel[10] = mn3;
                	colModel[11] = mn4;
                	colModel[12] = mn5;
                	colModel[13] = mn6;
                	colModel[14] = mn7;
                	colModel[15] = mn8;
            	}else if(nextMn==10){
            		colModel[4] = mn10;
                	colModel[5] = mn11;
                	colModel[6] = mn12;
                	colModel[7] = mn1;
                	colModel[8] = mn2;
                	colModel[9] = mn3;
                	colModel[10] = mn4;
                	colModel[11] = mn5;
                	colModel[12] = mn6;
                	colModel[13] = mn7;
                	colModel[14] = mn8;
                	colModel[15] = mn9;
            	}else if(nextMn==11){
            		colModel[4] = mn11;
                	colModel[5] = mn12;
                	colModel[6] = mn1;
                	colModel[7] = mn2;
                	colModel[8] = mn3;
                	colModel[9] = mn4;
                	colModel[10] = mn5;
                	colModel[11] = mn6;
                	colModel[12] = mn7;
                	colModel[13] = mn8;
                	colModel[14] = mn9;
                	colModel[15] = mn10;
            	}else if(nextMn==12){
            		colModel[4] = mn12;
                	colModel[5] = mn1;
                	colModel[6] = mn2;
                	colModel[7] = mn3;
                	colModel[8] = mn4;
                	colModel[9] = mn5;
                	colModel[10] = mn6;
                	colModel[11] = mn7;
                	colModel[12] = mn8;
                	colModel[13] = mn9;
                	colModel[14] = mn10;
                	colModel[15] = mn11;
            	}
            	
            	$(this).jqGrid('setGridParam', { colModel: colModel });
	        },
			loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");

	        	if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            } else {
					
					var gridData = $("#tabList");
					var rowIds = gridData.getDataIDs();
					gridData.jqGrid('setRowData', rowIds[0], false, {color:'red'});
					gridData.jqGrid('setRowData', rowIds[1], false, {color:'red'});
					gridData.jqGrid('setRowData', rowIds[2], false, {color:'red'});
					gridData.jqGrid('setRowData', rowIds[3], false, {color:'red'});
						
				}
				
	        	// ===================== Footer Sum
/*				
	        	var gridData = $("#tabList");
				gridData.jqGrid('footerData', 'set', { 'salesNm' : '합 계' });
				
				
				var sum_mon1 = gridData.jqGrid('getCol','mon1', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon1':sum_mon1 });
			    
			    var sum_mon2 = gridData.jqGrid('getCol','mon2', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon2':sum_mon2 });
			    
			    var sum_mon3 = gridData.jqGrid('getCol','mon3', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon3':sum_mon3 });
			    
			    var sum_mon4 = gridData.jqGrid('getCol','mon4', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon4':sum_mon4 });
			    
			    var sum_mon5 = gridData.jqGrid('getCol','mon5', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon5':sum_mon5 });
			    
			    var sum_mon6 = gridData.jqGrid('getCol','mon6', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon6':sum_mon6 });
			    
			    var sum_mon7 = gridData.jqGrid('getCol','mon7', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon7':sum_mon7 });
			    
			    var sum_mon8 = gridData.jqGrid('getCol','mon8', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon8':sum_mon8 });
			    
			    var sum_mon9 = gridData.jqGrid('getCol','mon9', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon9':sum_mon9 });
			    
			    var sum_mon10 = gridData.jqGrid('getCol','mon10', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon10':sum_mon10 });
			    
			    var sum_mon11 = gridData.jqGrid('getCol','mon11', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon11':sum_mon11 });
			    
			    var sum_mon12 = gridData.jqGrid('getCol','mon12', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon12':sum_mon12 });
			    
			    var sum_mon12 = gridData.jqGrid('getCol','mon12', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'mon12':sum_mon12 });
			    
			    var sum_monTot = gridData.jqGrid('getCol','monTot', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'monTot':sum_monTot });
			
*/			    			    
//			    $('table.ui-jqgrid-ftable tr:first').children("td").css("background-color", "#dfeffc");
//			    $('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			//footer row font color
			    

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
			,rownumbers: true													// rowNumber 표시여부
			,autowidth:true	
			,shrinkToFit : false
			,loadonce : true
			,footerrow : true
			,sortorder : "desc"
		});
	}
	
	function dataMv(){
		var grid = $("#tabList");
    	
    	var allRows = grid.jqGrid('getDataIDs');		//전체 rowIds
    	
    	for(var i = 0; i < allRows.length; i++){
    		var rowId = allRows[i];
    		var rowData = grid.jqGrid('getRowData', rowId);
    		
    		rowData['thirdColumn'] = rowData['secondColumn'];
    		
    		grid.jqGrid('setRowData', rowId, rowData);
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
	
	/*매출처 찾기팝업 호출 */
	function findSalesMst(){
		commonSalesMstFindLayer('','',$("#searchSalesNm").val(), '', setSalesyMstFindLayer);
	}
	
	/*매출처 콜백(후처리 함수 )*/
	function setSalesyMstFindLayer(data) {
		$("#searchSalesCd").val(data.salesCd);		/*매출처코드*/
		$("#searchSalesNm").val(data.salesNm);		/*매출처명*/
		
		$("#searchPrdtNm").focus();
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
			
			$("#searchSalesPrNm").focus();
		}
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
		
		alert("DATA 조회에 최대5분 이상 소요될 수 있습니다. \n잠시만 기다려 주세요.");
		
		var startYm = $("#searchYearMnSt").val();
    	var endYm = $("#searchYearMnEt").val();
    	
    	var dateDiff = ((endYm.substring(0,4) - startYm.substring(0,4)) * 12) + (endYm.substring(5,7) - startYm.substring(5,7));
		
    	if(dateDiff >= 12){
    		alert('조회월은 12개월을 넘을수 없습니다.');
    		return false;
    	}
    	
		var searchInfo = {};
		
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
		
		$("#tabList").jqGrid('GridUnload');
		
		initSalesGrid();
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/as/sal/salSalesMonPrftList_selList.json'/>",
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
		let excelName = "매출처월이익현황";	//엑셀 다운로드 명
		
		exportExcel(gridObj, excelName);
		//$('#searchForm').attr("action", "<c:url value='/app/as/sal/salSalesMonPrftList_selExcelDown'/>").submit();

	}
	
	function reSetDate(obj) {
		obj.value = obj.value.trim().replaceAll("-","");
		setRightFocus(obj,0);
	}
	
	function isValidYMDThisPageOnly(comp)
	{
		if(!comp.value && comp.value.trim().length <= 0) return;
		if(!isValidYMDStringThisPageOnly(comp.value))
		{
			comp.focus();
			return false;
		}
		
		isDateCheck(comp);
	}
	
	function isValidYMDStringThisPageOnly(strComp)
	{		
		var dates = strComp.trim().replaceAll("-","");

		if(dates.length != 6) return;
		else
		{
			var t_year  = Number(dates.substring(0,4));
			var t_month = Number(dates.substring(4,6));
			
			if (t_year < 1900 || t_year > 9999)
			{ 
				alert('년도 입력오류입니다.!');
				return false;
			}
			if (t_month <1 || t_month > 12)
			{
				alert('해당월 입력오류입니다.!');
				return false;
			}			
		}
		
		return true;
	}
	
	function sales_clear(event){
		$('#searchSalesNm').val('');
		$('#searchSalesCd').val('');
	}
	
	function prdt_clear(event){
		$('#searchPrdtNm').val('');
		$('#searchPrdtCd').val('');
	}
	
	function salesPr_clear(event){
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
	<input type="hidden" id="searchOrdDt" name="searchOrdDt""/>
	<input type="hidden" id="searchBuyDt" name="searchBuyDt""/>
	<sec:csrfInput/>
	<fieldset>
	<legend>매출처월이익현황</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처월이익현황 검색조건</caption>
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
				<th><label for="sele2">조회월</label></th>
					<td>
						<input type="text" name="searchYearMnSt" id="searchYearMnSt" maxlength="6" style="width:70px" value="${util:getNowDateFmt('yyyy-MM')}" readonly="readonly" style="width: 80%"/>
						<input type="button" id="searchMnStPicker" /> 
						<input type="text" name="searchYearMnEt" id="searchYearMnEt" maxlength="6" style="width:70px" value="${util:getNowDateFmt('yyyy-MM')}" readonly="readonly" style="width: 80%"/>
						<input type="button" id="searchMnEtPicker" />  
					</td>
				<th><label for="sele2">구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchSalesHqClass" objName="searchSalesHqClass" parentID="M004" />
					</td>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text" id="searchSalesNm" name="searchSalesNm" style="width: 50%;" onclick="sales_clear()">
						<input type="hidden" id="searchSalesCd" name="searchSalesCd" style="width: 20%;">
						<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
			</tr>
			<tr>
				<th><label for="sele2">품목</label></th>
					<td>
						<input type="text"   id="searchPrdtNm"  name="searchPrdtNm" style="width:50%;" onclick="prdt_clear()">
						<input type="hidden" id="searchPrdtCd"  name="searchPrdtCd" >
						<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">영업사원</label></th>
					<td>
						<input type="text" id="searchSalesPrNm" name="searchSalesPrNm" style="width: 50%;" onclick="salesPr_clear()">
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
 		<div class="tit_area">
			<h2 class="subhead">매출처월이익현황</h2>
			<div class="btn_l">
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 		<%-- 엑셀 --%>
			</div>
		</div>
		
	</form>
 	</li>
 	<div style="padding-top: 5px; ">
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