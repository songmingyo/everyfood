<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">
$(document).ready(function(){

	$("#rtnDt").datepicker();
	
	$("input.dy").datepicker().attr("style", "width:80%");
	
	initGrid();
	initDetailGrid();
	
	/* BUTTON CLICK 이벤트 처리 --------------------------------------------*/
	$('#btnNew').unbind().click(null,	    	newEvent); 	    // 신규
	$('#btnSearch').unbind().click(null,		searchEvent); 	// 검색
	$('#btnSave').unbind().click(null,	    	saveEvent); 	// 저장
	$('#btnAddPrdtDel').unbind().click(null,	addPrdtDelEvent); // 추가품목삭제	

	$("#btnBuyCd").unbind().click(function(){
		findBuyMst();
	});		<%--매입처 팝업 찾아가기--%>
	
	$("#btnPrdtCd").unbind().click(function(){
		findPrdtMst();
	});		<%--상품 팝업 찾아가기--%>
	
	/*-------------------------------------------------------------------*/

	/* grid resize ------------------------------------------------------*/
	$(window).bind('resize', function() {
        try{
            // width
            // 그리드의 width를 div 에 맞춰서 적용
            $('#dataList').setGridWidth($('#grid1container').width()); //Resized to new width as per window
          	$('#dataDetailList').setGridWidth($('#grid2container').width()); //Resized to new width as per window

         	// height
			var height = $(window).height()-$('#grid1container')[0].offsetTop;

			if(height > 275) {
				$('#dataList').setGridHeight(180);

				if(height-320 < 120)
					$('#dataDetailList').setGridHeight(200);
				else
					$('#dataDetailList').setGridHeight(height-250);		
			}
        }catch(e){}
    }).trigger('resize');
	/*-------------------------------------------------------------------*/
	
	// 조회조건 입력필드 enter key이벤트 --------------
	$('#buyNm').unbind().keydown(function(e) {
		switch(e.which) {
    		case 13 : findBuyMst(this); break; // enter
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
	$('#rtnQty').unbind().keydown(function(e) {
		switch(e.which) {
    		case 13 : prdtRtnAdd(this); break; // enter
    		default : return true;
    	}
    	e.preventDefault();
   	});
	//-----------------------------------------------------------------

});


/* 마스터 데이터  그리드 초기화 */
function initGrid() {
	$("#dataList").jqGrid({
		 datatype: "local"  // 보내는 데이터 타입
		,data: []
		,colNames:[   '매입처코드'
        			, '매입처명'
        			, '공급가'
        			, '부가세'
        			, '합계금액'
        			, '소비기한입력여부'
        			, '등록자'
        			, '등록시간'
        			
        			, '반품일자'
        			, '전표번호'
        ]
		,colModel:[
	                 {name:'buyCd'			, index:'buyCd'		, sortable:true, width:30 , align:"center"}
	                ,{name:'buyNm'			, index:'buyNm'		, sortable:true, width:100 , align:"left"}
	                ,{name:'pureAmt'		, index:'pureAmt'	, sortable:true, width:50 , align:"right", formatter:'integer'}
	                ,{name:'vatAmt'			, index:'vatAmt'	, sortable:true, width:50 , align:"right", formatter:'integer'}
	                ,{name:'totAmt'			, index:'totAmt'	, sortable:true, width:50 , align:"right", formatter:'integer'}
	                ,{name:'termValYn'		, index:'termValYn'	, sortable:true, width:50 , align:"center"} 
	                ,{name:'regNm'			, index:'regNm'		, sortable:true, width:50 , align:"center"} 
	                ,{name:'regDt'			, index:'regDt'		, sortable:true, width:50 , align:"center"}
	                
	                ,{name:'rtnDt'			, index:'rtnDt'		, sortable:true, width:50 , align:"center", hidden:true}
	                ,{name:'buySlipNo'		, index:'buySlipNo'	, sortable:true, width:50 , align:"center", hidden:true}

	     ]
		,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
        	var colCount = $(this).getGridParam("colNames").length;
            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
            
            $('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
            
            var gridData = $("#dataList");
			var allRows = gridData.jqGrid('getDataIDs');
            
            $('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
            
        	// ===================== Footer Sum
			gridData.jqGrid('footerData', 'set', { 'buyCd' : '합계' });
            
			var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
	    	
	    	var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
	    	
	    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
	            
    	    // 키 이벤트 처리
			$("#grid1container").unbind('keydown').keydown(function (e) {
				     
			    var grid = $("#dataList");
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
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();
                $(this).addRowData("blankRow", msg);
            } else{
            	ids = $(this).jqGrid("getDataIDs");
                if(ids && ids.length > 0){
                	$(this).jqGrid("setSelection", ids[0]);
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

       		buyRcptRegDetail(rowdata);
       }

        ,rowNum:-1                   
        ,loadui: 	"disable"              
        ,gridview:    true                 
        ,sortname: 'groupNm'
        ,sortorder: 'asc'
        ,emptyrecords : '<spring:message code="message.search.no.data" />'    <%-- 조회결과가 없습니다.--%>
        ,viewrecords: true
        ,scroll : true
        ,rownumbers:true 
        ,loadonce:true
        ,autowidth:true
        ,footerrow : true

	});
}



/* 세부코드 그리드 초기화 */
function initDetailGrid() {
    $("#dataDetailList").jqGrid({
         datatype: 'local'
        ,data: []
        ,colNames:[   '매입처명'
                    , '품목코드'
                    , '품명(규격)'
                    , '단위'
                    , '반품수량'
                    , '박스수량'
                    , '단가'
                    , '공급가'
                    , '부가세'
                    , '합계금액'
                    , '소비기한'
                    , '창고명'
                    , '비고'
                    , '수정자'
                    , '수정시간'
                    
                    , '매입처코드'
                    , 'Box당 수량'
                    , '규격유무'
                    , '부가세유무'
                    , '전표번호'
                    , '순번'
                    , '창고코드'
                    , '반품일자'
                    
                    , '추가유무'
                    , '그리드 상태'
                    , '그리드 상태'
                    , '그리드 상태'
                    , '그리드 상태'
         ]
    
        ,colModel:[
             {name:'buyNm'		, index:'buyNm'		, sortable:false  , width:150   , align:"left"}
        	,{name:'prdtCd' 	, index:'prdtCd'	, sortable:false  , width:80 	, align:"center"}
        	,{name:'prdtNm' 	, index:'prdtNm'	, sortable:false  , width:180 	, align:"left"}
        	,{name:'ordUnit' 	, index:'ordUnit'	, sortable:false  , width:80 	, align:"center"}
        	,{name:'rtnQty' 	, index:'rtnQty'	, sortable:false  , width:100 	, align:"right", formatter: fmtSetGridInputNumHour, unformat: unfmtSetGridInputNumHour}
        	,{name:'boxQty' 	, index:'boxQty'	, sortable:false  , width:100 	, align:"right"}
        	,{name:'cost' 		, index:'cost'		, sortable:false  , width:80 	, align:"right", formatter:'integer'}
     		,{name:'pureAmt' 	, index:'pureAmt'	, sortable:false  , width:100 	, align:"right", formatter:fmtSetGridInputAmt, unformat: unfmtGetGridInputAmt}
     		,{name:'vatAmt' 	, index:'vatAmt'	, sortable:false  , width:100 	, align:"right", formatter:fmtSetGridInputAmt, unformat: unfmtGetGridInputAmt}
     		,{name:'totAmt' 	, index:'totAmt'	, sortable:false  , width:100 	, align:"right", formatter:'integer'}
     		,{name:'termVal'	, index:'termVal'	, sortable:false  , width:120 	, align:"center", formatter:fmtSetGridDatePicker, unformat: unfmtGetGridDatePicker}
     		,{name:'whNm' 		, index:'whNm'		, sortable:false  , width:100 	, align:"center"}
     		,{name:'remarks'	, index:'remarks'	, sortable:false  , width:180 	, align:"left", formatter:fmtSetGridInputLen, unformat:unfmtGetGridInput}
     		,{name:'modNm'		, index:'modNm'		, sortable:false  , width:100 	, align:"center"}
     		,{name:'modDt'		, index:'modDt'		, sortable:false  , width:120 	, align:"center"}
     		
     		,{name:'buyCd'		, index:'buyCd'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
     		,{name:'qtyBox'		, index:'qtyBox'	, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
        	,{name:'stdYn'		, index:'stdYn'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
        	,{name:'vatYn'		, index:'vatYn'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
        	,{name:'buySlipNo'	, index:'buySlipNo'	, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
        	,{name:'buySeq'		, index:'buySeq'	, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
        	,{name:'whCd'		, index:'whCd'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
        	,{name:'rtnDt' 		, index:'rtnDt'		, sortable:false  , width:160 	, align:"center", hidden:true}
        	
        	,{name:'addDataRow'		, index:'addDataRow'	, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
        	,{name:'gridFlag'		, index:'gridFlag'		, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
        	,{name:'rtnQtyOrg' 	, index:'rtnQtyOrg'		, sortable:false, hidden:true  , width:100 	, align:"right", formatter: fmtSetGridInputNumHour, unformat: unfmtSetGridInputNumHour}
        	,{name:'pureAmtOrg' 	, index:'pureAmtOrg'		, sortable:false, hidden:true  , width:100 	, align:"right", formatter:fmtSetGridInputAmt, unformat: unfmtGetGridInputAmt}
        	,{name:'vatAmtOrg' 	, index:'vatAmtOrg'		, sortable:false, hidden:true  , width:100 	, align:"right", formatter:fmtSetGridInputAmt, unformat: unfmtGetGridInputAmt}
        ]
       ,gridComplete : function() {
        	var colCount = $(this).getGridParam("colNames").length;
            $("#blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
            
            var gridData = $("#dataDetailList");
			var allRows = gridData.jqGrid('getDataIDs');
            
            $('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
            
        	// ===================== Footer Sum
			gridData.jqGrid('footerData', 'set', { 'prdtCd' : '합계' });
            
			var sum_rtnQty = gridData.jqGrid('getCol','rtnQty', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'rtnQty':sum_rtnQty });
			
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
	            $("#dataDetailList").unbind('keydown').keydown(function (e) {
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
	         	
	            $("#dataDetailList").unbind('keyup').keyup(function (e) {
	            	setGridBuyData();
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
	                    var $grid = $("#dataDetailList").jqGrid('getDataIDs');
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
	                    var $grid = $("#dataDetailList").jqGrid('getDataIDs');
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
						var rowCount = $("#dataDetailList").jqGrid('getGridParam', 'records');
                        
						// 가장 마지막 행부터 역순으로 확인하여 input 타입이 text인 셀을 찾음
						for (var i = rowCount; i >= 1; i--) {
						    var rowHasTextInput = false;
						    $("#dataDetailList").find("tr").eq(i).find('input[type="text"]').each(function() {
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
            
            $(window).resize();
       }
       ,loadComplete: function() {
           $(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
           
           if ($(this).getGridParam("records")==0) {
        	   var firstColName = $(this).getGridParam("colModel")[1].name;
               var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();
               $(this).addRowData("blankRow", msg);
           }else{
        	   var allRows = $(this).jqGrid('getDataIDs');
        	   
        	   for(var i = 0; i < allRows.length; i++){
					var cl = allRows[i];
					var rowData = $(this).jqGrid('getRowData', cl);
					
					$(this).find("#"+cl).find("input[name='termVal']").datepicker();
        	   }
           }
        
           $(window).resize();
       }

       ,loadError:function(xhr, status, error) {
            alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
            message.error.process
            return false;
        }
       ,onSelectRow : function(id, status, e) {

            if (id == 'blankRow')return;
        	var rowdata = $(this).getRowData(id);

        	if(!rowdata) return;
        }
		,rowNum			: -1
        ,loadui 		: "disable"
        ,gridview		: true
        ,sortorder		: 'asc'
        ,emptyrecords 	: '<spring:message code="message.search.no.data" />'     <%-- 조회결과가 없습니다.--%>
        ,viewrecords	: true
        ,scroll 		: true
        ,autowidth      : true
		,shrinkToFit    : false
        ,rownumbers		: true
        ,loadonce		: false
        ,footerrow		: true
    });
    
    jQuery("#dataDetailList").jqGrid('setFrozenColumns');
}

// 박스수량 및 금액 조정
function setGridBuyData(){
	
	var ids = $("#dataDetailList").jqGrid('getDataIDs');

	for(var i=0; i<ids.length;i++){
		var rowData = $("#dataDetailList").jqGrid('getRowData', ids[i]);

		var rtnQty = parseFloat(rowData.rtnQty);
	    var qtyBox = rowData.qtyBox;
	    var strQtyBox = parseInt(rtnQty / qtyBox);
		
	    var strEa = rtnQty % qtyBox;
		if(strEa % 1 != 0){
	    	strEa = strEa.toFixed(2);
	    }
		
		var ordUnit = rowData.ordUnit;
	    var stdYn = rowData.stdYn;
	   
		if(stdYn == "2"){
	    	$("#dataDetailList").jqGrid('setCell',ids[i],'boxQty',String(rtnQty).concat(ordUnit));
	    } else {	    	
	    	if(rtnQty < qtyBox){
	    		$("#dataDetailList").jqGrid('setCell',ids[i],'boxQty',String(rtnQty).concat(ordUnit));
	    	} else if(strEa == 0){
	    		if(qtyBox > 1){
	    			$("#dataDetailList").jqGrid('setCell',ids[i],'boxQty',String(strQtyBox).concat("BOX"));
	    		} else{
	    			$("#dataDetailList").jqGrid('setCell',ids[i],'boxQty',String(rtnQty).concat(ordUnit));
	    		}
	    	} else{	    		
	    		$("#dataDetailList").jqGrid('setCell',ids[i],'boxQty',String(strQtyBox).concat("BOX/", strEa, ordUnit));
	    	}
	    }

		var totAmt = rowData.totAmt;

		var cost = parseInt(rowData.cost);
	    var vatYn = rowData.vatYn;
	    
	    var pureAmt = Math.round(rtnQty * cost);
		var vatAmt = Math.round(rtnQty * cost * 0.1);
		
		var rtnQtyOrg = parseFloat(rowData.rtnQtyOrg);
		var pureAmtOrg = rowData.pureAmtOrg;
		var vatAmtOrg = rowData.vatAmtOrg;

/* 입력한 반품수량을 쿼리에서 가져온 반품수량이랑 동일 할 경우 계산이 되지 않아 주석 처리 by song min kyo 2025.01.07 */
		if(rtnQty != rtnQtyOrg){
			if(vatYn == "2"){
		    	$("#dataDetailList").jqGrid('setCell',ids[i],'pureAmt',pureAmt);
		    	$("#dataDetailList").jqGrid('setCell',ids[i],'vatAmt',0);
		    	$("#dataDetailList").jqGrid('setCell',ids[i],'totAmt',pureAmt);
		    } else{
		    	$("#dataDetailList").jqGrid('setCell',ids[i],'pureAmt',pureAmt);
		    	$("#dataDetailList").jqGrid('setCell',ids[i],'vatAmt',vatAmt);
		    	$("#dataDetailList").jqGrid('setCell',ids[i],'totAmt',pureAmt + vatAmt);
			}
		}else{
			$("#dataDetailList").jqGrid('setCell',ids[i],'totAmt',parseInt(rowData.pureAmt) + parseInt(rowData.vatAmt));
		}
	

		//----- 금액계산을 해당구문으로 스크립트 변경 by song min kyo 2025.01.07
// 		if(vatYn == "2"){
// 	    	$("#dataDetailList").jqGrid('setCell',ids[i],'pureAmt',pureAmt);
// 	    	$("#dataDetailList").jqGrid('setCell',ids[i],'vatAmt',0);
// 	    	$("#dataDetailList").jqGrid('setCell',ids[i],'totAmt',pureAmt);
// 	    } else{
// 	    	$("#dataDetailList").jqGrid('setCell',ids[i],'pureAmt',pureAmt);
// 	    	$("#dataDetailList").jqGrid('setCell',ids[i],'vatAmt',vatAmt);
// 	    	$("#dataDetailList").jqGrid('setCell',ids[i],'totAmt',pureAmt + vatAmt);
// 		}

	}
	
	var sum_rtnQty = $("#dataDetailList").jqGrid('getCol','rtnQty', false, 'sum');
	$("#dataDetailList").jqGrid('footerData', 'set', { 'rtnQty':sum_rtnQty });
	
    var sum_pureAmt = $("#dataDetailList").jqGrid('getCol','pureAmt', false, 'sum');
    $("#dataDetailList").jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
	
	var sum_vatAmt = $("#dataDetailList").jqGrid('getCol','vatAmt', false, 'sum');
	$("#dataDetailList").jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
	
	var sum_totAmt = $("#dataDetailList").jqGrid('getCol','totAmt', false, 'sum');
	$("#dataDetailList").jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
}



/*매입처 찾기팝업 호출 */
function findBuyMst(){
	commonBuyMstFindLayer('','',$("#buyNm").val(), setBuyMstFindLayer);
}

/*매입처 콜백(후처리 함수 )*/
function setBuyMstFindLayer(data) {
	if (data != null){
		$("#buyCd").val(data.buyCd);
		$("#buyNm").val(data.buyNm);
		
//		$('#prdtNm').focus();
		document.getElementById('prdtNm').focus();
	}
	
	
}

/*상품 찾기팝업 호출 */
function findPrdtMst() {
	commonPrdtMstFindLayer('','',$("#prdtNm").val(), $("#buyCd").val(), setPrdtMstFindLayer);
}

/*상품 콜백(후처리 함수 )*/
function setPrdtMstFindLayer(data) {
	if (data != null){
		$("#prdtCd").val(data.prdtCd);
		$("#prdtNm").val(data.prdtNm);
		
//		$("#rtnQty").focus();
		document.getElementById('rtnQty').focus();
	}
}

 /* 신규 */
function newEvent(event){
    $('form').each(function() {
        this.reset();
    });
    
    $("#dataList").jqGrid('clearGridData');
    $("#dataDetailList").jqGrid('clearGridData');
}

/* 리스트 Data 조회 */
function searchEvent(event){
	
	var searchInfo = {};
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.id] = $(this).val().replaceAll('-','');
	});

	//Grid 초기화
	$("#dataList").jqGrid('clearGridData');
	$("#dataDetailList").jqGrid('clearGridData');
	
	$("#dataList").jqGrid('setGridParam',{
		url:"<c:url value='/app/br/buy/buyRtnReg_selList.json'/>",
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
function buyRcptRegDetail(rowObj){

	//서브코드 리스트 조회------------------------------------------
	var str = rowObj;
	
	$("#dataDetailList").jqGrid('clearGridData');

  	$("#dataDetailList").jqGrid('setGridParam',{
        url:'<c:url value="/app/br/buy/buyRtnReg_selDetailList.json" />', 		
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
	
	setGridBuyData();
	
	$("#dataDetailList").editCell(0, 0, false);

	if(!confirm("저장하시겠습니까?")){return;}

	var saveData = {'buyRtnRegList'  : $("#dataDetailList").getRowData()
			        , 'whCd'         : $('#whCd').val()
			        , 'rtnClass'     : $('#rtnClass').val()
			        , 'rtnDt'        : $('#rtnDt').val().replaceAll('-','')
	               };

	$.ajax({
	    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	    url  : "<c:url value='/app/br/buy/buyRtnReg_insList.json'/>",

	    data : JSON.stringify(saveData),
 	    success : function(data){
   	 	
 	    	if(data.msgCd =="0") {
				alert("신규 : "+ data.rtnValue01+"건  |  수정 : "+data.rtnValue02+"건이 처리 정상 되었습니다.");
				
   				searchEvent();			//  재조회
		   	}else{
				alert("처리중 오류가 발생하였습니다. Code : "+data.msgCd+ "Message : "+data.message)
			}
  	    }
	});
}


/* 수량에서 엔터 눌렀을 경우 해당 품목의 데이터를 가지고 와서 그리드에 보여줌 */
function prdtRtnAdd(event){
	if(!$('#buyNm').val()) {
		alert('매입처를 입력하세요.');
		$('#buyNm').focus();
		return false;
	}		
	if(!$('#prdtNm').val()) {
		alert('품목을 입력하세요.');
		$('#prdtNm').focus();
		return false;
	}
	if(!$('#rtnQty').val()) {
		alert('반품 수량을 입력하세요.');
		$('#rtnQty').focus();
		return false;
	}
	
	var ids = $("#dataDetailList").jqGrid('getDataIDs');
	var rowData = $("#dataDetailList").jqGrid('getRowData', ids[0]);
	
	if((rowData.buyCd != '' && typeof rowData.buyCd != 'undefined') && rowData.buyCd != $("#buyCd").val()){
		alert('아래 선택된 매입처와 조회한 매입처가 서로 다릅니다.')
		return;
	}

	var searchInfo = {};
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.name] = $(this).val().replaceAll('-','')
	});

	$.ajax({
		contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		url:"<c:url value='/app/br/buy/buyRtnReg_selPrdtAddList.json'/>",
		data: JSON.stringify(searchInfo),
		success : function(data){
			
			//blankRow 제거
			$("#dataDetailList").jqGrid('delRowData', 'blankRow');
			
			var rowId = $("#dataDetailList").getGridParam("reccount");
			
			var rtnQty =  $("#rtnQty").val();
			
			$("#dataDetailList").jqGrid('addRowData', rowId+1, data.buyRtnRegVo, 'last');
			$("#dataDetailList").jqGrid('setCell',rowId+1,'rtnQty',rtnQty);
			$("#dataDetailList").jqGrid('setCell',rowId+1,'addDataRow',"A");
			
			$("#dataDetailList input.dy").datepicker().attr("style", "width:80%");
			
			//하나의 품목 입력 후 input 박스 초기화
			prdt_clear();
			
			$("#prdtNm").focus();
		}
		
	});
}


function addPrdtDelEvent(event){	
	var ids = $("#dataDetailList").jqGrid('getDataIDs');

    for(var i=0; i<ids.length;i++){
    	var rowData = $("#dataDetailList").jqGrid('getRowData', ids[i]);
    	var rowId = $.trim(ids[i]);

		if(rowData.addDataRow == "A"){
			$("#dataDetailList").jqGrid('delRowData', rowId);
        }
    }
}

function buy_clear(event){
	$('#buyNm').val('');
	$('#buyCd').val('');
	$('#prdtNm').val('');
	$('#prdtCd').val('');
	$('#rtnQty').val('');
}

function prdt_clear(event){
	$('#prdtNm').val('');
	$('#prdtCd').val('');
	$('#rtnQty').val('');
}


</script>
</head>
<body>
<div id="section">

<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  		<%--전체  --%>
<spring:message code="common.choice" var="commonchoice" />  <%--선택  --%>

<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post">
<fieldset>
	<legend>매입처반품등록</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매입처반품등록 검색조건</caption>
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
				<th><label for="sele2">매입처명</label></th>
					<td>
						<input type="text"   id="buyNm"  name="buyNm" style="width:50%;" onclick="buy_clear()" autocomplete="off">
						<input type="hidden" id="buyCd"  name="buyCd" >
						<button id="btnBuyCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">품목</label></th>
					<td>
						<input type="text"   id="prdtNm"  name="prdtNm" style="width:50%;" onclick="prdt_clear()" autocomplete="off">
						<input type="hidden" id="prdtCd"  name="prdtCd" >
						<button id="btnPrdtCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">수량</label></th>
					<td>
						<input type="text"   id="rtnQty"  name="rtnQty" style="width:40%; text-align:right;" autocomplete="off">
					</td>
			</tr>
			<tr>
				<th><label for="sele2">반품일자</label></th>
					<td>
						<input type="text" class="dt" id="rtnDt" name="rtnDt"  style="width: 100px !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/>
					</td>
				<th><label for="sele2">반품구분</label></th>
					<td colspan=3>
						<html:codeTag comType="SELECT" objId="rtnClass" objName="rtnClass" parentID="M010" />
					</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- CONTENT- BODY  ----------------------------------- -->


 <UL style="width: 100%;">
 	<li style="float: top; width: 100%;">
 	<form name="mainForm" id="mainForm"  >
 		<div  style="padding-left: 5px; ">
 		<div class="tit_area">
			<h2 class="subhead">매입처별반품목록</h2>
			<div class="btn_l">

				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnSave"   		name="btnSave"     		auth="save"   />		<%-- 저장 --%>
			</div>
		</div>
		
	</form>
	</div>	
 	</li>
 	<li style="float: down; width: 100%; ">
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="dataList" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</li>
 </UL>
    
    <!-- 코드 서브타이틀 및 이벤트 버튼 start  ------------------>
	<div class="tit_area">
    	<h2>매입처별 반품상세 현황 </h2>	
		<div >
			<label style="margin-top:2px;">반품창고 :</label>
			 <html:codeTag comType="SELECT" dataType="PRC3" objId="whCd" objName="whCd" height="24px" parentID="Y" defValue="" />
			<html:button id="btnAddPrdtDel" auth="insert" value="추가품목삭제" />
		</div>
	</div>
	<!-- 서브타이틀 및 이벤트 버튼 end     --------------------->

    <!-- centent List -------------------------->
	<div id="grid2container" class="gridcontainer" >
    	<table id="dataDetailList" ><tr><td></td></tr></table>
	</div>
	<div id="pageList2"></div>
    <!-- centent List -------------------------->
            
<!-- CONTENT- BODY end ----------------------------------- -->

<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />


<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />

</div>
</body>
</html>
