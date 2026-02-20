<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">
$(document).ready(function(){

	$("#salesDt").datepicker();
	
	$("#salesDt").datepicker('setDate','c+1d');
		
	initCusShipRegGrid();
	initCusShipRegDetailGrid();
	
	/* BUTTON CLICK 이벤트 처리 --------------------------------------------*/
	$('#btnNew').unbind().click(null,	   	newEvent); 	    // 신규
	$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
	$('#btnSave').unbind().click(null,	    saveEvent); 	// 저장
	$('#btnPrdtAdd').unbind().click(null,	prdtAddEvent); 	// 추가출고

	$('#btnSales_find').unbind().click(function(){ findSalesMst()}); 	// 매출처찾기 팝업 버튼이벤트
	
	$('#btnSalesPrMstFind').unbind().click(function(){ findSalesPrMst()}); 	// 영업사원찾기 팝업 버튼이벤트
	
	$('#btnTranPrt').unbind().click(function(){ salesPrt("A") });	
	$('#btnCustPrt').unbind().click(function(){ salesPrt("B") });
	$('#btnCarPrt').unbind().click(function(){ salesPrt("C") });
	$('#btnStkPrt').unbind().click(function(){ salesPrt("D") });
	$('#btnTranCustPrt').unbind().click(function(){ salesPrt("E") });
		
	$('#btnFileUpload').unbind().click(function(){ excelUpload()});
	
	/*-------------------------------------------------------------------*/

	/* grid resize ------------------------------------------------------*/
	$(window).bind('resize', function() {
        try{
            // width
            // 그리드의 width를 div 에 맞춰서 적용
            $('#container1List').setGridWidth($('#grid1container').width()); //Resized to new width as per window
          	$('#container2List').setGridWidth($('#grid2container').width()); //Resized to new width as per window

         	// height
			var height = $(window).height()-$('#grid1container')[0].offsetTop;

			if(height > 275) {
				$('#container1List').setGridHeight(180);

				if(height-320 < 120)
					$('#container2List').setGridHeight(200);
				else
					$('#container2List').setGridHeight(height-250);		
			}
        }catch(e){}
    }).trigger('resize');
	/*-------------------------------------------------------------------*/


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
	
	//최상단 체크박스 클릭 ------------------------------------------------
	    $("#checkHAll").click(function(){
	    	var checkHGbn = $('#checkHGbn').val();
	    	if(checkHGbn == 'Y'){
	    		$(this).attr("src", $(this).attr("src").replace("_yes", "_no"));
	    		//input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
	            $("input[name=chHslipNo]").prop("checked",false);
	            $('#checkHGbn').val("N");
	    	}else{
	    		$(this).attr("src", $(this).attr("src").replace("_no", "_yes"));
	    		//input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
	            $("input[name=chHslipNo]").prop("checked",true);
	            //클릭이 안되있으면
	            $('#checkHGbn').val("Y");
	    	}
	    });
	
	    $("#checkTAll").click(function(){
	    	var checkTGbn = $('#checkTGbn').val();
	    	if(checkTGbn == 'Y'){
	    		$(this).attr("src", $(this).attr("src").replace("_yes", "_no"));
	    		//input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
	            $("input[name=chTslipNo]").prop("checked",false);
	            $('#checkTGbn').val("N");
	    	}else{
	    		$(this).attr("src", $(this).attr("src").replace("_no", "_yes"));
	    		//input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
	            $("input[name=chTslipNo]").prop("checked",true);
	            //클릭이 안되있으면
	            $('#checkTGbn').val("Y");
	    	}
	    });
	//-----------------------------------------------------------------
	
});

/* 마스터 데이터  그리드 초기화 */
function initCusShipRegGrid() {
	$("#container1List").jqGrid({
		 datatype: "local"  // 보내는 데이터 타입
		,data: []
		,colNames:[ '<img id="checkHAll" src="<c:url value="/resources/images/pearl/common/icons_checkbox_no.png"/>" align=middle>'
			        , '전표번호'
        			, '코드'
        			, '호차'
        			, '매출처명'
        			, '공급가'
        			, '부가세'
        			, '합계금액'
        			, '마감유무'
        			, '거래명세서출력 유무'
        			, '고객집계표출력 유무'
        			, '차량별집계표출력 유무'
        			, '재고집계표출력 유무'
        			, '정상/샘플 구분'
        			, '등록자'
        			, '명세표출력담당자'
        			, '명세표출력시간'
        			//, '<img id="checkTAll" src="<c:url value="/resources/images/pearl/common/icons_checkbox_no.png"/>" align=middle>'
        			
        			, '매출일자'
        			, '매출처구분'
        			, '매출구분'
        			, '출고구분'
        			, '거래명세서출력 유무'
        			, '집계표출력 유무'
        			, '차량별집계표출력 유무'
        			, '재고집계표출력 유무'
        			
        ]
		,colModel:[
			        {name:'Check_H_Box'     , index:'Check_H_Box'   , sortable:false, width:40	, align:"center"}         
			        ,{name:'salesSlipNo'	, index:'salesSlipNo'	, sortable:true,  width:120 , align:"center"}
	                ,{name:'salesCd'		, index:'salesCd'		, sortable:true,  width:80 , align:"center"}
	                ,{name:'carNm'			, index:'carNm'			, sortable:true,  width:100 , align:"center"}
	                ,{name:'salesNm'		, index:'salesNm'		, sortable:true,  width:160 , align:"left"}
	                ,{name:'pureAmt'		, index:'pureAmt'		, sortable:true,  width:100 , align:"right", formatter:'integer'}
	                ,{name:'vatAmt'			, index:'vatAmt'		, sortable:true,  width:100 , align:"right", formatter:'integer'}
	                ,{name:'totAmt'			, index:'totAmt'		, sortable:true,  width:100 , align:"right", formatter:'integer'}
	                ,{name:'closeYn'		, index:'closeYn'		, sortable:true,  width:110 , align:"center"}
	                ,{name:'trnscPrtYnNm'	, index:'trnscPrtYnNm'	, sortable:true,  width:120 , align:"center"}
	                ,{name:'smrPrtYnNm'		, index:'smrPrtYnNm'	, sortable:true,  width:120 , align:"center"}
	                ,{name:'carPrtYnNm'		, index:'carPrtYnNm'	, sortable:true,  width:120 , align:"center"}
	                ,{name:'stkPrtYnNm'		, index:'stkPrtYnNm'	, sortable:true,  width:120 , align:"center"}
	                ,{name:'freeClassNm'	, index:'freeClassNm'	, sortable:true,  width:100 , align:"center"}
	                ,{name:'regNm'			, index:'regNm'			, sortable:true,  width:120 , align:"center"}
	                ,{name:'trnscPrtIdNm'	, index:'trnscPrtIdNm'	, sortable:true,  width:120 , align:"center"}
	                ,{name:'trnscPrtDt'		, index:'trnscPrtDt'	, sortable:true,  width:130 , align:"center"}
	                //,{name:'Check_T_Box'    , index:'Check_T_Box'  	, sortable:false, width:40 , align:"center"}
	    	        
	                ,{name:'salesDt'  	    , index:'salesDt'		, hidden:true		}
	                ,{name:'salesClass'	    , index:'salesClass'	, hidden:true		}
	                ,{name:'freeClass'	    , index:'freeClass'		, hidden:true		}
	                ,{name:'prtClass'	    , index:'prtClass'		, hidden:true		}
	                ,{name:'trnscPrtYn'		, index:'trnscPrtYn'	, hidden:true	,  width:120 , align:"center"}
	                ,{name:'smrPrtYn'		, index:'smrPrtYn'		, hidden:true	,  width:120 , align:"center"}
	                ,{name:'carPrtYn'		, index:'carPrtYn'		, hidden:true	,  width:120 , align:"center"}
	                ,{name:'stkPrtYn'		, index:'stkPrtYn'		, hidden:true	,  width:120 , align:"center"}
	                
	     ]
		,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
        	var colCount = $(this).getGridParam("colNames").length;
            $("#blankRow td:nth-child(2)").attr("colspan", colCount)
                						  .attr("style", "text-align: center;");
            
            var gridData = $("#container1List");
			var allRows = gridData.jqGrid('getDataIDs');
			
			if(allRows && allRows.length > 0){
           		$(this).jqGrid("setSelection", allRows[0], false);
            }
            
		    $('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
			
			// ===================== Footer Sum
			gridData.jqGrid('footerData', 'set', { 'salesCd' : '합계' });
			
            var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
	    	
	    	var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
	    	
	    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
            
            // 키 이벤트 처리
			$("#grid1container").unbind('keydown').keydown(function (e) {
				     
			    var grid = $("#container1List");
			    var selectedRowId = grid.getGridParam("selrow");

			    // 현재 선택된 행이 없는 경우, 첫 번째 행을 선택합니다.
			    if (!selectedRowId) {
			        selectedRowId = grid.getDataIDs()[0];
			    }

			    var nextRowId;
			    var prevRowId;
			    var rowIds = grid.getDataIDs();

			    // 현재 선택된 행의 인덱스를 찾습니다.
			    var currentIndex = rowIds.indexOf(selectedRowId);
			     
			    switch(e.which) {
			        case 38: // 위쪽 화살표 키
			            prevRowId = rowIds[currentIndex - 1];
			            if (prevRowId) {
			                e.preventDefault();
			                grid.jqGrid('setSelection', prevRowId, true);
			                scrollToVisibleRow(grid, prevRowId);
			            }
			            break;
			        case 40: // 아래쪽 화살표 키
			            nextRowId = rowIds[currentIndex + 1];
			            if (nextRowId) {
			                e.preventDefault();
			                grid.jqGrid('setSelection', nextRowId, true);
			                scrollToVisibleRow(grid, nextRowId);
			            }
			            break;
			    }
			});

			// 함수 정의: 선택된 행이 보이도록 스크롤 이동
			function scrollToVisibleRow(grid, rowId) {
			    var $grid = grid;
			    var $selectedRow = $grid.find("#" + rowId);
			    var $gridWrapper = $grid.closest(".ui-jqgrid-bdiv");
			    var gridTop = $gridWrapper.scrollTop();
			    var gridBottom = gridTop + $gridWrapper.height();
			    var rowTop = $selectedRow.position().top;
			    var rowBottom = rowTop + $selectedRow.outerHeight();

			    // 선택된 행이 스크롤 범위를 벗어날 경우 스크롤 조정
			    if (rowTop < gridTop) {
			        // 위로 스크롤
			        $gridWrapper.scrollTop(rowTop);
			    } else if (rowBottom > gridBottom) {
			        // 아래로 스크롤
			        $gridWrapper.scrollTop(rowBottom - $gridWrapper.height());
			    } else {
			        // 스크롤 범위 내에 있는 경우
			        // 스크롤을 조정하지 않음
			    }
			}
            
        	$(window).resize();
        }
		,loadComplete: function() {
        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
        	$(this).jqGrid("setLabel", "rn", "No");
            if ($(this).getGridParam("records")==0) {
                var firstColName = $(this).getGridParam("colModel")[1].name;
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                $(this).addRowData("blankRow", msg);
            } else{
            	ids = $(this).jqGrid("getDataIDs");
                if(ids && ids.length > 0){
                	$(this).jqGrid("setSelection", ids[0]);
                }
            }
            
            var allRows = jQuery("#container1List").jqGrid('getDataIDs');			// 전체 rowCount
            
        	for(var i = 0; i < allRows.length; i++){
        		var cl = allRows[i];
				var rowAllData = jQuery("#container1List").getRowData(allRows[i]);
				var checkFiled = ""
				if(rowAllData.salesSlipNo) {
					checkFiled = "<input type='checkbox' name='chHslipNo'><input type='hidden' name='salesSlipNo' value='"+rowAllData.salesSlipNo+"'><input type='hidden' name='salesCd' value='"+rowAllData.salesCd+"'>";
					jQuery("#container1List").jqGrid('setRowData',cl,{'Check_H_Box':checkFiled});			// CheckBox
					
					//checkFiled = "<input type='checkbox' name='chTslipNo'><input type='hidden' name='amtNone' value='Y'>";
// 					jQuery("#container1List").jqGrid('setRowData',cl,{'Check_T_Box':checkFiled});			// CheckBox
				}
		     }
            
        	$(window).resize();
        }
		,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
			//alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
			return false;
        }
        ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

        	if (id == 'blankRow') return;
       		var rowdata = $(this).getRowData(id);

       		cusShipRegDetail(rowdata);
       }

        ,rowNum : -1
        ,loadui: 	"disable"
        ,gridview:    true                 
        ,sortname: 'groupNm'
        ,sortorder: 'asc'
        ,emptyrecords : '<spring:message code="message.search.no.data" />'    <%-- 조회결과가 없습니다.--%>
        ,viewrecords: true
        ,scroll : true
        ,rownumbers:true 
        //,multiselect:true
        ,loadonce:false
        ,shrinkToFit    : false
        ,autowidth:true
        ,footerrow: true

	});
}



/* 세부코드 그리드 초기화 */
function initCusShipRegDetailGrid() {
    $("#container2List").jqGrid({
         datatype: 'local'
        ,data: []
        ,colNames:[   '매출처명'
                    , '품목코드'
                    , '품목명'
                    , '규격'
                    , '단위'
                    , '발주수량'
                    , '박스수량'
                    , '출고수량'
                    , '단가'
                    , '공급가'
                    , '부가세'
                    , '합계금액'
                    , '창고'
                    , '수정자'
                    , '수정시간'
                    , '비고'
                    , '마진율'
                    
                    , '비고'
                    , '전표번호'
                    , '순번'
                    , '박스당수량'
                    , '규격유무'
                    , '부가세유무'
                    , '매출처코드'
                    , '추가필드구분'
                    , '샘플수량'
                    , '원금액'
                    , '원수량'
                    , '창고코드'
         ]
    
        ,colModel:[
             {name:'salesNm'    , index:'salesNm'		, sortable:false, width:150, align:"left"}
        	,{name:'prdtCd' 	, index:'prdtCd'		, sortable:true, width:100, align:"center"}
        	,{name:'prdtNm' 	, index:'prdtNm'		, sortable:true, width:220, align:"left", formatter: fmtSetSrchPrdt, unformat: unfmtGetGridInput}
        	,{name:'prdtStd' 	, index:'prdtStd'		, sortable:false, width:100, align:"left"}
        	,{name:'ordUnit' 	, index:'ordUnit'		, sortable:false, width:70, align:"center"}
        	,{name:'ordQty' 	, index:'ordQty'		, sortable:false, width:80, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
        	,{name:'boxQty' 	, index:'boxQty'		, sortable:false, width:100, align:"right"}
        	,{name:'salesQty' 	, index:'salesQty'		, sortable:false, width:80, editable:true, align:"right", formatter: fmtSetGridInputNumHour, unformat: unfmtSetGridInputNumHour}
        	,{name:'price' 		, index:'price'			, sortable:false, width:80, align:"right", formatter:'integer'}
     		,{name:'pureAmt' 	, index:'pureAmt'		, sortable:false, width:100, align:"right", formatter:'integer'}
     		,{name:'vatAmt' 	, index:'vatAmt'		, sortable:false, width:80, align:"right", formatter:'integer'}
     		,{name:'totAmt' 	, index:'totAmt'		, sortable:false, width:100, align:"right", formatter:'integer'}
     		,{name:'whNm' 		, index:'whNm'			, sortable:false, width:100, align:"center"}
     		,{name:'modNm' 		, index:'modNm'			, sortable:false, width:100, align:"center"}
     		,{name:'modDt' 		, index:'modDt'			, sortable:false, width:200, align:"center"}
     		,{name:'remarks2' 	, index:'remarks2'		, sortable:false, width:200, align:"center"}
     		,{name:'marRate' 	, index:'marRate'		, sortable:false, width:80, align:"center", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
     		
     		,{name:'remarks1' 	 , index:'remarks1'		, sortable:false, width:200, hidden:true, align:"center"}
     		,{name:'salesSlipNo' , index:'salesSlipNo'	, sortable:false, width:100, 	hidden:true, align:"center"}
     		,{name:'salesSeq'    , index:'salesSeq'	    , sortable:false, width:100, 	hidden:true, align:"center", formatter:'integer'}
     		,{name:'qtyBox'      , index:'qtyBox'	    , sortable:false, width:100, 	hidden:true, align:"center"}
     		,{name:'stdYn'       , index:'stdYn'	    , sortable:false, width:100, 	hidden:true, align:"center"}
     		,{name:'vatYn'       , index:'vatYn'	    , sortable:false, width:100, 	hidden:true, align:"center"}
     		,{name:'salesCd'     , index:'salesCd'	    , sortable:false, width:100, 	hidden:true, align:"center"}
     		,{name:'addRow'      , index:'addRow'	    , sortable:false, width:100, 	hidden:true, align:"center"}
     		,{name:'freeQty'     , index:'freeQty'	    , sortable:false, width:100, 	hidden:true, align:"center"}
     		,{name:'orgAmt'      , index:'orgAmt'	    , sortable:false, width:100, 	hidden:true, align:"center"}
     		,{name:'orgQty'      , index:'orgQty'	    , sortable:false, width:100, 	hidden:true, align:"center"}
     		,{name:'whCd'        , index:'whCd'	    	, sortable:false, width:100, 	hidden:true, align:"center"}
     	
        ]
       ,gridComplete : function() {
        	var colCount = $(this).getGridParam("colNames").length;
            $("#blankRow td:nth-child(2)").attr("colspan", colCount)
			  							  .attr("style", "text-align: center;");
            
            var gridData = $("#container2List");
			var allRows = gridData.jqGrid('getDataIDs');
            
            if(allRows.length > 0){
           	    $('#remarks1').val($("#container2List").jqGrid('getRowData', allRows[0]).remarks1);
            }
             
            $('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
			
			// ===================== Footer Sum
			gridData.jqGrid('footerData', 'set', { 'prdtCd' : '합계' });
			
            var sum_salesQty = gridData.jqGrid('getCol','salesQty', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'salesQty':sum_salesQty });
	    	
	    	var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
	    	
	    	var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
	    	
	    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
	    	
	    	
	    	
	    	// input 타입이 text인 셀에 포커스가 잡힐 때의 이벤트 설정
            $("input[type='text']").focus(function () {
                var $cell = $(this).closest("td");
                lastSelectedRow = $cell.closest("tr").attr("id");
                lastSelectedCol = $cell.index();
            
	         	// 키 다운 이벤트 설정
	            $("#container2List").unbind('keydown').keydown(function (e) {
	                var key = e.which;

	             // 오른쪽 화살표 키가 눌렸는지 확인
	                if (key == 39) {
	                    e.preventDefault();
	                    var $nextInput = getNextTextInputCell("next");
	                    if ($nextInput) {
	                        $nextInput.focus();
	                    }
	                }
	                // 왼쪽 화살표 키가 눌렸는지 확인
	                else if (key == 37) {
	                    e.preventDefault();
	                    var $prevInput = getNextTextInputCell("prev");
	                    if ($prevInput) {
	                        $prevInput.focus();
	                    }
	                }
	                // 위쪽 화살표 키가 눌렸는지 확인
	                else if (key == 38) {
	                    e.preventDefault();
	                    var $prevInput = getNextTextInputCell("prevRow");
	                    if ($prevInput) {
	                        $prevInput.focus();
	                    }
	                }
	                // 아래쪽 화살표 키가 눌렸는지 확인
	                else if (key == 40) {
	                    e.preventDefault();
	                    var $nextInput = getNextTextInputCell("nextRow");
	                    if ($nextInput) {
	                        $nextInput.focus();
	                    }
	                }
	            });
	         	
	            $("#container2List").unbind('keyup').keyup(function (e) {
	            	saveDataCheckMain();
	            });

	         	// 다음 input 타입이 text인 셀을 찾는 함수
	            function getNextTextInputCell(direction) {
	                var $currentCell = $("#" + lastSelectedRow + " td:eq(" + lastSelectedCol + ")");
	                var $nextCell;

	                if (direction === "next") {
	                    $nextCell = $currentCell.nextAll('td').find('input[type="text"]').first();
	                    if ($nextCell.length === 0) {
	                        $nextCell = $currentCell.closest('tr').nextAll('tr').find('td').find('input[type="text"]').first();
	                    }
	                }
	                else if (direction === "prev") {
	                	$nextCell = $currentCell.prevAll('td').find('input[type="text"]').last();
                        if ($nextCell.length === 0) {
                            $nextCell = $currentCell.closest('tr').prevAll('tr').find('td').find('input[type="text"]').last();
                        }
	                }
	         		else if (direction === "prevRow") {
	                    var $prevRow = $currentCell.closest('tr').prev('tr');
	                    var $grid = $("#container2List").jqGrid('getDataIDs');
	                    var rowIndex;
	                    
	                    $nextCell = $prevRow.find('td:eq(' + lastSelectedCol + ')').find('input[type="text"]');
                        
	                    $grid.forEach(function(id, index) {
                            if (id === lastSelectedRow) {
                                rowIndex = index;
                                return false; // 반복 종료
                            }
                        });
                        
	                    if ($nextCell.length === 0 && rowIndex != 0) {
	                        for(var i = 0; $nextCell.length < 1; i++ ) {
			                    $prevRow = $prevRow.prev('tr');
		                        $nextCell = $prevRow.find('td:eq(' + lastSelectedCol + ')').find('input[type="text"]');
	                        }
	                    }
	                }
	         		else if (direction === "nextRow") {
	                    var $nextRow = $currentCell.closest('tr').next('tr');
	                    var $grid = $("#container2List").jqGrid('getDataIDs');
	                    var rowIndex, lastRowIndex;
	                    
                        $nextCell = $nextRow.find('td:eq(' + lastSelectedCol + ')').find('input[type="text"]');
                       
                        //현재 focus된 row의 인덱스를 구함
                        $grid.forEach(function(id, index) {
                            if (id === lastSelectedRow) {
                                rowIndex = index;
                                return false; // 반복 종료
                            }
                        });
                        
                        // 행의 개수를 가져옴
						var rowCount = $("#container2List").jqGrid('getGridParam', 'records');
                        
						// 가장 마지막 행부터 역순으로 확인하여 input 타입이 text인 셀을 찾음
						for (var i = rowCount; i >= 1; i--) {
						    var rowHasTextInput = false;
						    $("#container2List").find("tr").eq(i).find('input[type="text"]').each(function() {
						        if ($(this).val() !== "") {
						            rowHasTextInput = true;
						            return false; // forEach 반복 종료
						        }
						    });
						    if (rowHasTextInput) {
						        lastRowIndex = i - 1;
						        break;
						    }
						}
						
	                    if ($nextCell.length === 0 && rowIndex < lastRowIndex) {
	                        for(var i = 0; $nextCell.length < 1; i++ ) {
			                    $nextRow = $nextRow.next('tr');
			                    $nextCell = $nextRow.find('td:eq(' + lastSelectedCol + ')').find('input[type="text"]');
	                        }
	                    }
	                }

	                return $nextCell;
	            }
         	});
         	//여기까지 커서 이동
	    	
       }
       ,loadComplete: function() {
            if ($(this).getGridParam("records")==0) {
                var firstColName = $(this).getGridParam("colModel")[1].name;
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                $(this).addRowData("blankRow", msg);
            } else{
            	var allRows = $(this).jqGrid('getDataIDs');
            	
				for(var i=0; i<allRows.length;i++){
					var rowId = allRows[i];
					var rowData = $(this).jqGrid('getRowData', rowId);
					
					if(rowData.boxQty == ""){
						var salesQty = parseInt(rowData.salesQty);
					    var qtyBox = parseInt(rowData.qtyBox);
					    var strQtyBox = parseInt(salesQty / qtyBox);
						var strEa = salesQty % qtyBox;

						var ordUnit = rowData.ordUnit;
					    var stdYn = rowData.stdYn;
					    
						if(stdYn == "2"){
							$(this).jqGrid('setCell',rowId,'boxQty',String(salesQty).concat(ordUnit));
					    } else {
					    	if(salesQty < qtyBox){
					    		$(this).jqGrid('setCell',rowId,'boxQty',String(salesQty).concat(ordUnit));
					    	} else if(strEa == 0){
					    		if(qtyBox > 1){
					    			$(this).jqGrid('setCell',rowId,'boxQty',String(strQtyBox).concat("BOX"));
					    		} else{
					    			$(this).jqGrid('setCell',rowId,'boxQty',String(salesQty).concat(ordUnit));
					    		}
					    	} else{
					    		$(this).jqGrid('setCell',rowId,'boxQty',String(strQtyBox).concat("BOX/", strEa, ordUnit));
					    	}
					    }
					}
				}
            }
        }
        ,loadError:function(xhr, status, error) {
            alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
            message.error.process
            return false;
        }

        ,onSelectRow : function(id, status, e) {

        }
		,rowNum			: -1
        ,loadui 		: "disable"
        ,gridview		: true
        ,sortname		: 'prdtCd'
        ,sortorder		: 'asc'
        ,emptyrecords 	: '<spring:message code="message.search.no.data" />'     <%-- 조회결과가 없습니다.--%>
        ,viewrecords	: true
        ,scroll 		: true
        ,rownumbers		: true
        ,autowidth      : true
        ,shrinkToFit    : false
        //,cellEdit		: true
        ,loadonce		: true
        ,footerrow: true														// 전체 합계를 낼때 사용
    });
}

//품목 찾기 그리드 세팅
function fmtSetSrchPrdt(cellValue, rowObject, options){
	var cellval = $.trim(cellValue) == ""? "":cellValue;
	var rowid = $.trim(rowObject.rowId);
	var retData = "";
	
	if(rowid.indexOf("blankRow") > -1){
		return retData;
	}

	retData = "<input type='text' id='prdtNm_"+rowid+"' name='prdtNm' value='"+cellval+"' style='width:180px;' onkeydown='javascript:if(event.keyCode == 13){fncOpenPopFindMn(\""+rowid+"\", \"Y\");event.preventDefault()}' />";
	
	retData += "<button name='btnPrdtFind' class='button btn_find' type='botton' onclick='fncOpenPopFindMn(\""+rowid+"\", \"N\")'><i class='fa fa-search'></i></button>";
	
	return retData;	
}


//품목 찾기 팝업
function fncOpenPopFindMn(rowid, gbn){
	var paramObj = {};

	rowid = $.trim(rowid);
	
	var rowData = $("#container2List").jqGrid('getRowData', rowid);

	commonSalesPrdtMstFindLayer('', '', rowData.prdtNm, rowData.salesCd, setSalesPrdtMstFindLayer, rowid);
}

/*품목 콜백(후처리 함수 )*/
function setSalesPrdtMstFindLayer(data) {

	if (data != null){
		//var rowId = $("#container2List").getGridParam("selrow");
		var rowId = data.rowId;
		
		$("#container2List").jqGrid('setCell',rowId,'prdtCd',data.prdtCd);
		$("#container2List").jqGrid('setCell',rowId,'prdtNm',data.prdtNm);
		
		$("#container2List").jqGrid('setCell',rowId,'ordUnit',data.ordUnit);
		$("#container2List").jqGrid('setCell',rowId,'qtyBox',data.qtyBox);
		$("#container2List").jqGrid('setCell',rowId,'stdYn',data.stdYn);
		$("#container2List").jqGrid('setCell',rowId,'vatYn',data.vatYn);
		$("#container2List").jqGrid('setCell',rowId,'price',data.price);
		
		saveDataCheckMain();
	}
}




/*매출처 찾기팝업 호출 */
function findSalesMst(){
	commonSalesMstFindLayer('','',$("#salesNm").val(), '', setSalesMstFindLayer,'Y');
}

/*매출처 콜백(후처리 함수 )*/
function setSalesMstFindLayer(data) {
	$("#salesCd").val(data.salesCd);
	$("#salesNm").val(data.salesNm);
	$("#hqClass").val(data.hqClass);
}



/*배송차량 찾기팝업 호출*/
function findDlvrMst(){
	var searchVals = {find_salesCd:$("#salesCd").val(),  find_salesNm:$("#salesNm").val()}
	commonDlvrMstFindLayer('','',searchVals, setDlvrMstFindLayer);
}
/*배송차량 콜백(후처리 함수 )*/
 function setDlvrMstFindLayer(data) {
	$("#carCd").val(data.carCd);		/*챠량코드*/
	$("#carNm").val(data.carNm);		/*차량명*/
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
		 $("#position").val(data.position);			/*직급*/
	}
}


/*파일일괄업로드 호출 */
function excelUpload(){
	salesExcelUploadLayer('','','', setExcelUploadLayer);
}

/*파일일괄업로드콜백*/
function setExcelUploadLayer(data) {
}

/* 신규 */
function newEvent(event){
    $('form').each(function() {
        this.reset();
    });
    
  	//Grid 초기화
    $("#container1List").jqGrid('clearGridData');
	$("#container2List").jqGrid('clearGridData');
	
	$("#salesDt").datepicker('setDate','c+1d');
}

/* 목록 Data 조회 */
function searchEvent(event){
	
	var searchInfo = {};
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.id] = $(this).val();
	});

	//Grid 초기화
	$("#container1List").jqGrid('clearGridData');
	$("#container2List").jqGrid('clearGridData');	
	
	$("#container1List").jqGrid('setGridParam',{
		url:"<c:url value='/app/sm/custsales/cusShipReg_selList.json'/>",
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
			records: "totalCount",	// 전체 조회된 데이터 총갯수 
			repeatitems: false
		}
	}).trigger("reloadGrid");

}

/* 세부 (상세)정보조회 */
function cusShipRegDetail(rowObj){

	//서브코드 리스트 조회------------------------------------------
	var str = rowObj;
	
	$("#container2List").jqGrid('clearGridData');

  	$("#container2List").jqGrid('setGridParam',{
        url:'<c:url value="/app/sm/custsales/cusShipReg_selDetailList.json" />', 		
        datatype: "json",     
        postData:str,         
        mtype:'POST',         
        loadBeforeSend: function(jqXHR) {
            jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
        },
		jsonReader : {
            root:  "rows",
            repeatitems: false,
        }

    }).trigger("reloadGrid");
  	//-------------------------------------------------------------
}

/* 저장 */
function saveEvent(event){
	
// 	if(!saveDataCheckMain()){
// 		return;
// 	}
	
	var allRows = $("#container2List").jqGrid('getDataIDs');		//전체 rowIds
	
	for(var i = 0; i < allRows.length; i++){
		var rowId = allRows[i];
		var rowData = $("#container2List").jqGrid('getRowData', rowId);
		
		if(rowData.prdtCd == "" || rowData.prdtCd == null){
			alert('품목을 입력해 주세요.');
			return;
		}
		
		$("#container2List").jqGrid('setCell',rowId,'freeQty',rowData.salesQty);
	}
	
	$("#container2List").editCell(0, 0, false);
	
	if(!confirm("저장하시겠습니까?")){return;}
	
	var selectedRowId = $("#container1List").getGridParam("selrow");
	
	var selectRowData = $("#container1List").jqGrid('getRowData', selectedRowId);
	
	if(selectRowData.freeClass == '00'){
		var strUrl = "<c:url value='/app/sm/custsales/cusShipReg_insList.json'/>"

		var saveData = {'cusSalesDlvList' : $("#container2List").getRowData()
	        , 'salesDt'       : $('#salesDt').val().replaceAll('-','')
	        , 'whCd'          : $('#whCd').val()
	        , 'remarks1'      : $('#remarks1').val()
              };
	}else{
		var strUrl = "<c:url value='/app/sm/custsales/cusSampleReg_insList.json'/>"
		
		var saveData = {'cusSampleRegList' : $("#container2List").getRowData()
	        , 'whCd'          : $('#whCd').val()
	        , 'remarks1'      : $('#remarks1').val()
	        , 'freeClass'     : selectRowData.freeClass
	        , 'freeDt'        : $('#salesDt').val().replaceAll('-','')
              };
	}
	

	$.ajax({
	    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	    url  : strUrl,

	    data : JSON.stringify(saveData),
 	    success : function(data){
   	 	
 	    	if(data.msgCd =="0") {
				alert("신규 : "+ data.rtnValue01+"건  |  수정 : "+data.rtnValue02+"건이 정상 처리되었습니다.");
				
   				searchEvent();			//  재조회
		   	} else if (data.msgCd == "dupl") {
		   		alert(data.message);
		   	} else {
				alert("처리중 오류가 발생하였습니다. Code : "+data.msgCd+ "Message : "+data.message)
			}
  	    }
	});
}

//박스수량, 금액
function saveDataCheckMain(){

	var allRows = $('#container2List').jqGrid('getDataIDs');

	for(var i=0; i<allRows.length;i++){
		var rowid = allRows[i];
		var rowData = $('#container2List').jqGrid('getRowData', rowid);

		var salesQty = parseFloat(rowData.salesQty);
	    var qtyBox = rowData.qtyBox;
	    var strQtyBox = parseInt(salesQty / qtyBox);
	    
	    var strEa = salesQty % qtyBox;
	    
	    if(strEa % 1 != 0){
	    	strEa = strEa.toFixed(2);
	    }
	    	
		var ordUnit = rowData.ordUnit;
	    var stdYn = rowData.stdYn;
	   
		if(stdYn == "2"){
	    	$("#container2List").jqGrid('setCell',rowid,'boxQty',String(salesQty).concat(ordUnit));
	    } else {
	    	if(salesQty < qtyBox){
	    		$("#container2List").jqGrid('setCell',rowid,'boxQty',String(salesQty).concat(ordUnit));
	    	} else if(strEa == 0){
	    		if(qtyBox > 1){
	    			$("#container2List").jqGrid('setCell',rowid,'boxQty',String(strQtyBox).concat("BOX"));
	    		} else{
	    			$("#container2List").jqGrid('setCell',rowid,'boxQty',String(salesQty).concat(ordUnit));
	    		}
	    	} else{
	    		$("#container2List").jqGrid('setCell',rowid,'boxQty',String(strQtyBox).concat("BOX/", strEa, ordUnit));
	    	}
	    }
	    
	    var price = parseInt(rowData.price);
	    var vatYn = rowData.vatYn;
	    
	    var pureAmt = Math.round(salesQty * price);
		var vatAmt = Math.round(pureAmt * 0.1);
	    
	    if(vatYn == "2"){
	    	$("#container2List").jqGrid('setCell',rowid,'pureAmt',pureAmt);
	    	$("#container2List").jqGrid('setCell',rowid,'vatAmt',0);
	    	$("#container2List").jqGrid('setCell',rowid,'totAmt',pureAmt);
	    } else{
	    	$("#container2List").jqGrid('setCell',rowid,'pureAmt',pureAmt);
	    	$("#container2List").jqGrid('setCell',rowid,'vatAmt',vatAmt);
	    	$("#container2List").jqGrid('setCell',rowid,'totAmt',pureAmt + vatAmt);
	    }
	    
	}
	
	// ===================== Footer Sum
	var sum_pureAmt = $('#container2List').jqGrid('getCol','pureAmt', false, 'sum');
    $('#container2List').jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
	
	var sum_vatAmt = $('#container2List').jqGrid('getCol','vatAmt', false, 'sum');
	$('#container2List').jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
	
	var sum_totAmt = $('#container2List').jqGrid('getCol','totAmt', false, 'sum');
	$('#container2List').jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });

	return true;
}


/* 추가출고 */
function prdtAddEvent(event){
	
	var ids = $("#container1List").jqGrid('getDataIDs');
	var rowMainData = $("#container1List").jqGrid('getRowData', ids[0]);
	
	var salesCd = rowMainData.salesCd;
	
	if($.trim(salesCd) == ""){
		alert('발주 데이터없이 추가는 불가능합니다.')
		return;
	}
	
	var rowId = $('#container2List').jqGrid('getDataIDs');
	
	if(rowId.length == 25){
		alert('전표당 25건만 가능합니다.');
		return;
	}
	
	var rowSubData = $("#container2List").jqGrid('getRowData', rowId[0]);
	
	$("#container2List").jqGrid('addRow',{
		initdata : {
			'salesCd' : rowSubData.salesCd
			, 'salesNm' : rowSubData.salesNm
			, 'addRow' : 'A'
		},
		position : "first",
		useDefValues : false,
		useFormatter : true
	});
}


/* 매출처 출고 출력 */
function salesPrt(prtClass){
	
	$("#container1List").editCell(0, 0, false);
	
	//출력 대상 선택
	var chkRowCnt = $("input[name='chHslipNo']:checked:visible").length;
	
	if(chkRowCnt==0){
		return;
	}
	
	$("#checkHAll").attr("src",$('#checkHAll').attr("src").replace("_yes", "_no"));

	var hSlipNoJobList = [];
	
	$("input[name='chHslipNo']:checked").each(function(){	
		var hSlipNoInfo = {};
		
		hSlipNoInfo['salesSlipNo']  = $(this).parent().children('input[name="salesSlipNo"]').attr('value');
		hSlipNoInfo['salesCd']  = $(this).parent().children('input[name="salesCd"]').attr('value');
		
// 		if($("input:checkbox[name='chTslipNo']").prop("checked")){
// 			hSlipNoInfo['amtNone']  = 'Y';
// 		}else{
// 			hSlipNoInfo['amtNone']  = 'N';
// 		}
		
		if($(this).parent().parent().find("input[name='chTslipNo']").prop("checked")){
			hSlipNoInfo['amtNone']  = 'Y';
		}else{
			hSlipNoInfo['amtNone']  = 'N';
		}
		
		hSlipNoJobList.push(hSlipNoInfo);
	});
			
	
	// 금액 미 표기 유무
// 	var chkRowCnt_T = $("input[name='chTslipNo']:checked:visible").length;
	
// 	$("#checkTAll").attr("src",$('#checkTAll').attr("src").replace("_yes", "_no"));
	
// 	var tSlipNoJobList = [];
	
// 	$("input[name='chTslipNo']:checked:visible").each(function(){	
// 		var tSlipNoInfo = {};
		
// 		tSlipNoInfo['amtNone']  = $(this).parent().children('input[name="amtNone"]').attr('value');

// 		tSlipNoJobList.push(tSlipNoInfo);
// 	});
	
	var input1, input2, input3, input4, input5, input6;
	var tailRow = 0;

	for(var i = 0; i < chkRowCnt; i++){
		
		input1 = $("<input>")
       			 .attr("type", "hidden")
        		 .attr("name", "item["+i+"].salesSlipNos").val(hSlipNoJobList[i].salesSlipNo);
		
		input2 = $("<input>")
			     .attr("type", "hidden")
        	     .attr("name", "item["+i+"].salesCds").val(hSlipNoJobList[i].salesCd);
		
  	    input3 = $("<input>")
			     .attr("type", "hidden")
        		 .attr("name", "item["+i+"].salesDts").val($("#salesDt").val().replaceAll('-',''));
  	    
  	   	input4 = $("<input>")
		         .attr("type", "hidden")
                 .attr("name", "item["+i+"].amtNone").val(hSlipNoJobList[i].amtNone);
  	   	
//   	    if(chkRowCnt_T > tailRow){
// 	   		input4 = $("<input>")
// 			         .attr("type", "hidden")
//                    .attr("name", "item["+i+"].amtNone").val(tSlipNoJobList[i].amtNone);
// 	   		tailRow++;
// 	   	}
  	   	
  	    input5 = $("<input>")
	             .attr("type", "hidden")
		         .attr("name", "item["+i+"].prtClass").val(prtClass);
  	    
//   	    input6 = $("<input>")
// 		         .attr("type", "hidden")
// 		         .attr("name", "item["+i+"].accRcvYn").val($("#accRcvYn").val().replaceAll('-',''));
  	    
		$('#searchForm').append($(input1));
		$('#searchForm').append($(input2));
		$('#searchForm').append($(input3));
		$('#searchForm').append($(input4));
		$('#searchForm').append($(input5));
// 		$('#searchForm').append($(input6));
	}

	if(prtClass=='A' || prtClass=='E'){
		var url = "<c:url value="/app/sm/custsales/cusShipReg_prtTran" />";
	}else{
		var url = "<c:url value="/app/sm/custsales/cusShipReg_prtOther" />";
	}
	
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
	
	for(var i = 0; i < chkRowCnt; i++){
		$("#searchForm input[name='item["+i+"].salesSlipNos']").remove();
		$("#searchForm input[name='item["+i+"].salesCds']").remove();
		$("#searchForm input[name='item["+i+"].salesDts']").remove();
		$("#searchForm input[name='item["+i+"].amtNone']").remove();
		$("#searchForm input[name='item["+i+"].prtClass']").remove();
// 		$("#searchForm input[name='item["+i+"].accRcvYn']").remove();
	}
	
}


function sales_clear(event){
	$('#salesNm').val('');
	$('#salesCd').val('');
}

function salesPr_clear(event){
	$('#salesPrNm').val('');
	$('#salesPrCd').val('');
}

</script>
</head>
<body>
<div id="section">

<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  		<%--전체  --%>
<spring:message code="common.choice" var="commonchoice" />  <%--선택  --%>

<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
<sec:csrfInput/>
<input type="hidden" 	id="checkHGbn"	name="checkHGbn" value="N">
<input type="hidden" 	id="checkTGbn"	name="checkTGbn" value="N">
<fieldset>
	<legend>매출처출고등록</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처출고등록 검색조건</caption>
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
				<th><label for="sele2">출고일자</label></th>
					<td><input type="text" id="salesDt" name="salesDt" style="width:90px" ></td>
				<th><label for="sele2">매출처명</label></th>
				<td>
					<input type="text" id="salesNm" name="salesNm" style="width: 80%;" onclick="sales_clear()">
					<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;">
					<input type="hidden" id="hqClass" name="hqClass" style="width: 20%;">
					<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
				</td>
				<th><label for="sele2">영업사원</label></th>
				<td>
					<input type="text" id="salesPrNm" name="salesPrNm" style="width: 80%;" onclick="salesPr_clear()">
					<input type="hidden" id="salesPrCd" name="salesPrCd" readonly="readonly"  style="width: 20%;">
					<button id="btnSalesPrMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
				</td>
				<th><label for="sele2">배송차량</label></th>	<%-- TFM_SALES_ADD_INFO (매출처 추가정보 관리) --%>
				<td>
					<html:codeTag comType="SELECT" dataType="CAR" objId="carCd" objName="carCd" height="24px" parentID="Y" defValue="" defName="${commonchoice}"  />
				</td>
			</tr>
			<tr>
				<th><label for="sele2">고객사별집계출력유무</label></th>
					<td><html:codeTag comType="SELECT" objId="smrPrtYn" objName="smrPrtYn" parentID="M009"  defName="${commonall}"  /></td>
<!-- 				<th><label for="sele2">미수잔액표기유무</label></th> -->
<%-- 					<td><html:codeTag comType="SELECT" objId="accRcvYn" objName="accRcvYn" parentID="M009"  defName="${commonall}"   /></td> --%>
				<th><label for="sele2">매출처구분</label></th>
					<td colspan="5"><html:codeTag comType="SELECT" objId="salesClass" objName="salesClass" parentID="M013"  defName="${commonall}"   /></td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>


<!-- 검색조건 end ----------------------------------------- -->

<!-- CONTENT- BODY  ----------------------------------- -->

	<!-- 출고예정 전체 서브타이틀 및 이벤트 버튼 start  -------------------->
	<div class="tit_area">
		<h2>출고예정전체</h2>
		<div>
			<html:button id="btnTranPrt" 		auth="print"  value=" 거래명세표 출력"  		/>  
			<html:button id="btnCustPrt" 		auth="print"  value=" 고객사별집계표 출력"  	/>  
			<html:button id="btnCarPrt" 		auth="print"  value=" 차량별집계표 출력"  	/>  
			<html:button id="btnStkPrt" 		auth="print"  value=" 재고집계표 출력"  		/>
			<html:button id="btnTranCustPrt" 	auth="print"  value=" 거래명세표(공급받는자용)"	/>    
			<html:button id="btnFileUpload" 	auth="update" value=" 파일업로드"  			/>    
			&nbsp;	
			<html:button id="btnNew" 	auth="insert"  	/> 		<%-- 신규 --%>  
			<html:button id="btnSearch" auth="select" 	/> 		<%-- 조회 --%>
			<html:button id="btnSave" 	auth="save"  	/> 		<%-- 저장 --%>
		</div>
	</div>
	<!-- 출고예정 전체 서브타이틀 및 이벤트 버튼 start  -------------------->

	<!-- 출고예정 전체 List -------------------------->
	<div id="grid1container" class="gridcontainer">
    	<table id="container1List" ><tr><td></td></tr></table>
    </div>

    <div id="pageList"></div>
    <!-- 출고예정 전체 List -------------------------->
    
    
    
    <!-- 코드 서브타이틀 및 이벤트 버튼 start  ------------------>
	<div class="tit_area">
    	<h2>매출처 주문상세현황 </h2>	
		<div >
			<label style="margin-top:2px;">※비고 </label>
			<input type="text" id="remarks1" name="remarks1" style="height: 24px; width:900px; margin-right: 100px;">
			<label style="margin-top:2px;">출고창고 </label>
			 <html:codeTag comType="SELECT" dataType="PRC3" objId="whCd" objName="whCd" height="24px" parentID="Y" defValue="" />
			<html:button id="btnPrdtAdd" auth="insert" value="추가출고" />
		</div>
	</div>
	<!-- 서브타이틀 및 이벤트 버튼 end     --------------------->

    <!-- centent List -------------------------->
	<div id="grid2container" class="gridcontainer" >
    	<table id="container2List" ><tr><td></td></tr></table>
	</div>
	<div id="pageList2"></div>
    <!-- centent List -------------------------->
            
<!-- CONTENT- BODY end ----------------------------------- -->

<!-- 매출처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesMaster.jsp" />

<!-- 영업사원 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_salesPrMaster.jsp" />

<!-- 매출처별 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesPrdtMaster.jsp" />

<!-- 엑셀 업로드 팝업  -->
<jsp:include page="/WEB-INF/views/app/sm/cusShipExcelUpload.jsp" />

<%-- <jsp:include page="/WEB-INF/views/management/mgrMemberInsertUser.jsp" /> --%>
<%-- <jsp:include page="/WEB-INF/views/management/mgrMemberInsertUserNew.jsp" /> --%>
<!-- 사용자정보 신규 등록 레이어 영역 end  -->

</div>
</body>
</html>
