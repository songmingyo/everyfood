<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#mainform #estDt, #mainform #effDt, #mainform #salesStDt, #mainform #buyStDt").datepicker();
		
		initPrdtMgmtGrid();
		initSubEstGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchHeadEvent); 	// 검색
		$('#btnSave').unbind().click(null,	    saveEvent); 	// 저장
		$('#btnInit').unbind().click(null,	    newEvent); 		// 신규
		
		$('#btnInEstPrt').unbind().click(null,	    salesEstInPrint); 		// 내부견적서출력
		$('#btnOutEstPrt').unbind().click(null,	    salesEstOutPrint); 		// 외부견적서출력
		$('#btnNewPrdtEstPrt').unbind().click(null,	salesEstNewPrdtPrint); 		// 신규품목입점의뢰서출력
		
		$('#btnPrdtSearch').unbind().click(null,	searchPrdtEvent); 		// 품목조회
		$('#btnPrdtMove').unbind().click(null,		prdtMoveEvent); 		// 선택품목이동
		
		$('#btnPrdtAdd').unbind().click(null,		prdtAddEvent); 		// 추가출고
		$('#btnPrdtDel').unbind().click(null,		prdtDelEvent); 		// 삭제
		
		$("#btnEstNum_find").unbind().click(function(){
			findEstNum();
		});
		$("#btnSales_find").unbind().click(function(){
			findSalesMst();
		});
		$("#btnSalesPr_find").unbind().click(function(){
			findSalesPrMst();
		});
		
		
		/*Resized to new width as per window -------------------------------*/
    
        $(window).bind('resize', function() {
		    try{	
		        $('#container1List').setGridWidth($('#grid1container').width()); 	// main gride
				$('#container2List').setGridWidth($('#grid2container').width()); 	// left footer (left)

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;	// right main gride


		        if(height > 280)	 	{
				    $('#container1List').setGridHeight(height-100);	//GRID  LIST
				}
		        else if(height < 300){
			        $('#container1List').setGridHeight(height-105);	//GRID  LIST
			    }
			    /*left footer height 고정------------------*/
		    	$('#container2List').setGridHeight(230);
				
			}catch(e){}
		}).trigger('resize');
		
		/*----------------------------------------------------------------*/
  

		/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/
		$('#searchEstNum').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findEstNum(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		$('#prdtNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : searchPrdtEvent(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
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


		/* Form validate --------------------------------------------------*/
        $('#mainform').validate({
	        rules: {
	        	 prdtNm   : { required: true, maxlength: 50}
	        }
			,messages: {
				prdtNm   : {required: "<div class='validate'><i class='fa fa-info-circle'></i>품명 입력하세요!<div>"}  
			}
	    });

		
	});

	/* 품목 데이터 그리드 초기화 */
	function initPrdtMgmtGrid() {
		$("#container1List").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[ '품목코드'
						, '품목명'
            			, '규격'
            			, '입고가'
            			, '기준원가'
            			, '견적가'
            			
            			, '단위'
            			, '보관방법'
            			, '원산지'
            			, '유통기한'
            			, '매입처'
            			, '매입처담당자'
            			, '매입처담당자전화번호'
            			, '결제조건'            			
            			, '부가세유무'
            			
            ]
			,colModel:[
						{name:'prdtCd'			, index:'prdtCd'	, sortable:true, width:100 , align:"center"}         
						,{name:'prdtNm'			, index:'prdtNm'	, sortable:true, width:180, align:"left"}
		            	,{name:'prdtStd'		, index:'prdtStd'	, sortable:true, width:80 , align:"center"}
		            	,{name:'buyCost'		, index:'buyCost'	, sortable:true, width:100 , align:"right", formatter:'integer'}
		            	,{name:'baseCost'		, index:'baseCost'	, sortable:true, width:100 , align:"right", formatter:'integer'}
		            	,{name:'estPrice'		, index:'estPrice'	, sortable:true, width:100 , align:"right", formatter:'integer'}
		            	
		            	,{name:'ordUnitNm'		, index:'ordUnitNm'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'strgTypeNm'		, index:'strgTypeNm'	, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'originNm'		, index:'originNm'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'exprtDt'		, index:'exprtDt'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'buyNm'			, index:'buyNm'			, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'salesPrNm'		, index:'salesPrNm'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'salesPrHp'		, index:'salesPrHp'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'setlCon'		, index:'setlCon'		, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'vatYn'			, index:'vatYn'		, hidden:true, sortable:true, width:50 , align:"center"}
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
            ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

            	if (id == 'blankRow') return;
            }
            ,ondblClickRow: function (rowid, iRow, iCol) {
    			var rowdata = $(this).getRowData(rowid);	// 선택한 행의 데이터를 가져온다
    			
    			if(rowdata.prdtNm != ""){
    				prdtDblMoveEvent(rowdata);
    			}
    		}
            
            ,rowNum:-1
            ,loadui : "disable"
            ,gridview:    true
            //,pager: '#pageList'
            ,sortname: 'prdtCd'
            ,sortorder: 'asc'
            ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
            ,viewrecords: true
            ,multiselect : true
            ,scroll : true
            ,loadonce:true
            ,shrinkToFit: false
            ,autowidth:true
		});
	}
	
	
	/* 견적서 항목 그리드 초기화 */
	function initSubEstGrid() {
		$("#container2List").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '품명'
            			, '규격'
            			, '단위'
            			, '입고가'
            			, '기준원가'
            			, '견적가'
            			, '부가세'
            			, '마진(%)'
            			, '월예상판매량'
            			, '보관방법'
            			, '원산지'
            			, '유통기한'
            			, '최초입고수량'
            			, '매입처명'
            			, '매입처연락처'
            			, '매입처담당자'
            			, '결제조건'
            			, '입고방법'
            			, '확정'
            			, '삭제구분'
            			
            			, '삭제구분'            			
            			, '견적번호'
            			, '견적차수'
            			, '픔목코드'
            			, '부가세유무'
            			, '그리드상태'
            ]
			,colModel:[
		                 {name:'prdtNm'			, index:'prdtNm'		, sortable:true, width:150, align:"left", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
		            	,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, width:120 , align:"left", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
		            	,{name:'ordUnitNm'		, index:'ordUnitNm'		, sortable:true, width:100 , align:"center", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
		            	,{name:'buyCost'		, index:'buyCost'		, sortable:true, width:100 , align:"right", formatter:fmtSetGridInputAmt,	unformat: unfmtGetGridInputAmt}
		            	,{name:'baseCost'		, index:'baseCost'		, sortable:true, width:100 , align:"right", formatter:fmtSetGridInputAmt,	unformat: unfmtGetGridInputAmt}
		            	,{name:'estPrice'		, index:'estPrice'		, sortable:true, width:100 , align:"right", formatter:fmtSetGridInputAmt,	unformat: unfmtGetGridInputAmt}
		            	,{name:'vatAmt'			, index:'vatAmt'		, sortable:true, width:100 , align:"right", formatter:fmtSetGridInputAmt,	unformat: unfmtGetGridInputAmt}
		            	,{name:'marginRate'		, index:'marginRate'	, sortable:true, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'monSalesQty'	, index:'monSalesQty'	, sortable:true, width:100 , align:"right", formatter:fmtSetGridInputAmt,	unformat: unfmtGetGridInputAmt}
		            	,{name:'saveKindNm'		, index:'saveKindNm'	, sortable:true, width:120 , align:"center", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
		            	,{name:'originNm'		, index:'originNm'		, sortable:true, width:120 , align:"center", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
		            	,{name:'exprtDt'		, index:'exprtDt'		, sortable:true, width:100 , align:"center", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
		            	,{name:'buyStQty'		, index:'buyStQty'		, sortable:true, width:100 , align:"right", formatter:fmtSetGridInputAmt,	unformat: unfmtGetGridInputAmt}
		            	,{name:'buyNm'			, index:'buyNm'			, sortable:true, width:220 , align:"left", formatter: fmtSetSrchBuyNm,	unformat: unfmtGetGridInput}
		            	,{name:'buyTelNo'		, index:'buyTelNo'		, sortable:true, width:100 , align:"center", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
		            	,{name:'buyPrNm'		, index:'buyPrNm'		, sortable:true, width:120 , align:"center", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
		            	,{name:'setlCon'		, index:'setlCon'		, sortable:true, width:100 , align:"center", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
		            	,{name:'buyHow'			, index:'buyHow'		, sortable:true, width:100 , align:"center", formatter:fmtSetGridInput,	unformat: unfmtGetGridInput}
		            	,{name:'confirmYn'		, index:'confirmYn'		, sortable:true, width:100 , align:"center", formatter:function(cellvalue, options, rowObject){return fmtSetGridSelectBox(cellvalue, options, rowObject, "<c:out value='${codeList_confirmYn}'/>");}	,unformat: unfmtGetGridSelectBox}
		            	,{name:'useYnNm'		, index:'useYnNm'		, sortable:true, width:100 , align:"center"}
		            	
		            	,{name:'useYn'			, index:'useYn'			, sortable:true, width:100 , hidden:true, align:"center"}
		            	,{name:'estNum'			, index:'estNum'		, sortable:true, width:50 , hidden:true, align:"center"}
		            	,{name:'estSeq'			, index:'estSeq'		, sortable:true, width:50 , hidden:true, align:"center"}
		            	,{name:'prdtCd'			, index:'prdtCd'		, sortable:true, width:50 , hidden:true, align:"center"}
		            	,{name:'vatYn'			, index:'vatYn'			, sortable:true, width:50 , hidden:true, align:"center"}
		            	,{name:'gridFlag'		, index:'gridFlag'		, sortable:true, width:50 , hidden:true, align:"center"}
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	        	$("#container2List").find("#blankRow td:nth-child(2)").attr("colspan", colCount);
	        	
	        	
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
	        	
	        	$(window).resize();
	        }
			,loadComplete: function() {
				$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
// 	            if ($(this).getGridParam("records")==0) {
// 	                var firstColName = $(this).getGridParam("colModel")[1].name;
// 	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	//조회결과가 없습니다.
// 	                $(this).addRowData("blankRow", msg);
// 	            }
	            
	        	$(window).resize();
	        }
			,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				return false;
            }
            ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트
            }
            
//             ,afterEditCell: function(rowid, name, val, iRow, iCol){

//     			$("#" + rowid + "_" + name).blur(function(){
//     		        $("#container2List").jqGrid("saveCell",iRow,iCol);
//     		    });
//     	    }
//     	    , afterSaveCell: function(rowid,name,val,iRow,iCol) {
//     	    	var data = $("#container2List").getRowData(rowid);
    	        
//                 var gridFlag = data.gridFlag;

//                 if(gridFlag == "N"){
//                 	$("#container2List").jqGrid('setCell', rowid, 'gridFlag', 'U');
//                 }else {
//                 	$("#container2List").jqGrid('setCell', rowid, 'gridFlag', 'I');
//                 }                

//     	    	if(name == "buyCost" || name =="estPrice"){
//     	    		setGridData(data, val, rowid, name);
//     			}
    			
//     			 $("#container2List").saveRow(rowid, false);
//     	    }

            ,rowNum:-1
            ,loadui : "disable"
            ,gridview:    true
            ,sortname: 'prdtCd'
            ,sortorder: 'asc'
            //,emptyrecords : '<spring:message code="message.search.no.data" />'
            ,viewrecords: true
            ,scroll: false
            //,cellEdit: true
            ,rownumbers:true
            ,loadonce:true
            ,shrinkToFit: false
            ,autowidth:true
		});
	}
	
	//품목 찾기 그리드 세팅
	function fmtSetSrchBuyNm(cellValue, rowObject, options){
		var cellval = $.trim(cellValue) == ""? "":cellValue;
		var rowid = $.trim(rowObject.rowId);
		var retData = "";
		
		if(rowid.indexOf("blankRow") > -1){
			return retData;
		}

		retData = "<input type='text' id='buyNm_"+rowid+"' name='buyNm' value='"+cellval+"' style='width:180px;' onkeydown='javascript:if(event.keyCode == 13){fncOpenPopFindMn(\""+rowid+"\", \"Y\");event.preventDefault()}' />";
		
		retData += "<button name='btnBuyNmFind' class='button btn_find' type='botton' onclick='fncOpenPopFindMn(\""+rowid+"\", \"N\")'><i class='fa fa-search'></i></button>";
		
		return retData;	
	}


	//매입처 찾기 팝업
	function fncOpenPopFindMn(rowid, gbn){
		var paramObj = {};

		rowid = $.trim(rowid);
		
		var rowData = $("#container2List").jqGrid('getRowData', rowid);

		commonBuyMstFindLayer('','',rowData.buyNm, setBuyMstFindLayer);
		
	}

	/*품목 콜백(후처리 함수 )*/
	function setBuyMstFindLayer(data) {

		if (data != null){
			var rowId = $("#container2List").getGridParam("selrow");
			
			$("#container2List").jqGrid('setCell',rowId,'buyNm',data.buyNm);
			$("#container2List").jqGrid('setCell',rowId,'buyTelNo',data.telNo);
			$("#container2List").jqGrid('setCell',rowId,'buyPrNm',data.salesPrNm);
			$("#container2List").jqGrid('setCell',rowId,'setlCon',data.setlCon);
		}
	}
	
	/* 선택 품목 이동 */
	function prdtMoveEvent(event){
		var selRows = $("#container1List").jqGrid('getGridParam', 'selarrrow');
		var selLen = selRows.length;
		
		var rowId;
		var rowData;

		for(var i=0; i<selLen; i++){
			rowId = selRows[i];
			rowData = $("#container1List").jqGrid('getRowData', rowId);
			
			//blankRow 제거
			$("#container2List").jqGrid('delRowData', 'blankRow');
			
			rowIdAdd = $("#container2List").getGridParam("reccount");
			
			$("#container2List").jqGrid('addRowData', rowIdAdd+1, rowData, 'last');
			
			/* 견적서 항목 순번 채번 */
			var rowDataAdd = $("#container2List").jqGrid('getRowData', rowIdAdd);
			
			if(rowDataAdd.gridFlag == "N"){
				var estSeq = rowDataAdd.estSeq + (i+1);
			}else {
				var estSeq = i+1;
			}
			
			$("#container2List").jqGrid('setCell', rowIdAdd+1, 'gridFlag', 'I');
			$("#container2List").jqGrid('setCell', rowIdAdd+1, 'estSeq', estSeq);
			
			//setGridData(rowData, '', rowIdAdd+1, '');
		}
		
	}
	
	/* 품목 더블클릭 이동 */
	function prdtDblMoveEvent(data){

		//blankRow 제거
		$("#container2List").jqGrid('delRowData', 'blankRow');
			
		var rowIdAdd = $("#container2List").getGridParam("reccount");
			
		$("#container2List").jqGrid('addRowData', rowIdAdd+1, data, 'last');
			
		var estSeq = data.estSeq + 1;
			
		$("#container2List").jqGrid('setCell', rowIdAdd+1, 'gridFlag', 'I');
		$("#container2List").jqGrid('setCell', rowIdAdd+1, 'estSeq', estSeq);
			
		//setGridData(data, '', rowIdAdd+1, '');
	}
	
	// 저장전 값 계산
	function setGridData(){
		var allRows = $("#container2List").jqGrid('getDataIDs');
    	
		var profitClass = $("#profitClass").val();
		
		for(var i=0; i<allRows.length;i++){
			var rowId = allRows[i];
			var rowData = $("#container2List").jqGrid('getRowData', rowId);
			
			var buyCost = parseInt(rowData.buyCost);
			var estPrice = parseInt(rowData.estPrice);
			var vatYn = rowData.vatYn;

			if(buyCost != 0){
				if(profitClass == '10' || profitClass == ''){
					var marginRate = ((estPrice - buyCost) / buyCost) * 100;
				
					$("#container2List").jqGrid('setCell', rowId, 'marginRate', marginRate.toFixed(2));
				} else{
					var marginRate = (estPrice - buyCost) / estPrice * 100
					$("#container2List").jqGrid('setCell', rowId, 'marginRate', marginRate.toFixed(2));
				}

				if(rowData.prdtCd != ''){
					if(vatYn == "1"){
				    	$("#container2List").jqGrid('setCell', rowId, 'vatAmt', Math.round(estPrice * 0.1));
				    }
				}
			}else{
				$("#container2List").jqGrid('setCell', rowId, 'marginRate', '0.00');
			}
			
		}
	}
	
	/*매출처 찾기팝업 호출 */
	function findSalesMst(){
		commonSalesMstFindLayer('','',$("#salesNm").val(), '', setSalesMstFindLayer);
	}
	/*매출처 콜백(후처리 함수 )*/
	function setSalesMstFindLayer(data) {
		if (data != null){
			$("#salesCd").val(data.salesCd);			/*매출처코드*/
			$("#salesNm").val(data.salesNm);			/*매출처명*/
			$("#profitClass").val(data.profitClass);	/*이익율구분*/
			$("#salesTelNo").val(data.telNo);			/*매출처대표번호*/			
		}
	}
	
	/*견적서 찾기팝업 호출 */
	function findEstNum(){
		commonEstNumFindLayer('','',$("#searchEstNum").val(), setEstNumFindLayer);
	}
	/*견적서 콜백(후처리 함수 )*/
	function setEstNumFindLayer(data) {
		if (data != null){
			$("#searchEstNum").val(data.estNum);		/*견적서번호*/
		}
	}
	
	 /*영업사원 찾기팝업 호출*/
	function findSalesPrMst() {
		commonSalesPrMstFindLayer('','',$("#salesPrNm").val(), setSalesPrMstFindLayer);
	}
	/*영업사원 콜백(후처리 함수 )*/
	function setSalesPrMstFindLayer(data){
		 if (data != null){
			 $("#salesPrNm").val(data.salesPrNm);
			 $("#mTelNo").val(data.mtelNo);
		}
	}

	/* 신규 */
	function newEvent(event){
	    $('form').each(function() {
	        this.reset();
	    });
	    
	  //Grid 초기화
		$("#container1List").jqGrid('clearGridData');
		$("#container2List").jqGrid('clearGridData');
	}
	
	/* 품목 조회 */
	function searchPrdtEvent(event){
		var searchInfo = {};
		searchInfo['prdtNm'] = $("#prdtNm").val();
	
		//Grid 초기화
		$("#container1List").jqGrid('clearGridData');
		
		$("#container1List").jqGrid('setGridParam',{
			url:"<c:url value='/app/sm/custsales/cusSalesEstReg_selPrdtList.json'/>",
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
	
	
	/* 견적서 Head 데이터 조회 */
	function searchHeadEvent(event){
		if($.trim($('#searchEstNum').val()) == ''){
			alert('견적번호를 선택하세요.');
			return;
		}
		
		var searchInfo = {};
		
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			url:"<c:url value='/app/sm/custsales/cusSalesEstReg_selHeadList.json'/>",
			data: JSON.stringify(searchInfo),
			success : function(data){
				if(data != null) {
					$.form = $("#dataFrom");

					//조회 데이터 셋팅
					$.form.find("span, input, label").each(function(){
						if(this.type != "button" && this.type != "radio"){
							dataNm = $(this).attr("name");
							tagNm = this.tagName.toUpperCase();

							settingData = data[dataNm];
	
							if(tagNm == "SPAN"){
								$(this).text(settingData);
							}else{
								$(this).val(settingData);
							}
						}
					});

					searchItemEvent(searchInfo);					
				}else{
	      			alert('조회 데이타가 없습니다.');
	      		}
			}
		});
	}
	
	
	/* 견적서 Item 조회 */
	function searchItemEvent(searchInfo){

		//Grid 초기화
		$("#container2List").jqGrid('clearGridData');		
		
		$("#container2List").jqGrid('setGridParam',{
			url:"<c:url value='/app/sm/custsales/cusSalesEstReg_selItemList.json'/>",
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
				repeatitems: false
			}
		}).trigger("reloadGrid");

	}
	
	/*저장 버튼 이벤트 */
	function saveEvent(){
		
		if($('#salesNm').val() == ''){
			alert('매출처를 입력하세요');
			return;
		}
		if($('#estDt').val() == ''){
			alert('견적일자를 입력하세요');
			return;
		}
		
		$("#container2List").editCell(0, 0, false);

		setGridData();
		
		if(!confirm("저장하시겠습니까?")){return;}
		
		var saveData = {'cusSalesEstRegList'   : $("#container2List").getRowData()};
		
		$('#mainform').find('input , select').map(function() {
			saveData[this.name] = $(this).val().replaceAll('-','');
		});

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		    url  : '<c:url value="/app/sm/custsales/cusSalesEstReg_insData.json" />',
		    data : JSON.stringify(saveData),
		    success : function(data){
				if(data.msgCd =="0") {
					alert("신규 : "+ data.rtnValue01+"건  |  수정 : "+data.rtnValue02+"건이 정상 처리 되었습니다.");
					
					newEvent();					
					$("#searchEstNum").val(data.rtnValue03);
					searchHeadEvent();
				}else{
					alert("처리중 오류가 발생하였습니다. Code : "+data.msgCd+ "Message : "+data.message)
				}
		    }
		});

	}
	
	/* 추가출고 */
	function prdtAddEvent(){
		//blankRow 제거
		$("#container2List").jqGrid('delRowData', 'blankRow');
		
		$("#container2List").jqGrid('addRow',{
			initdata : {
				'gridFlag' : 'I'
			},
			position : "last",
			useDefValues : false,
			useFormatter : true
		});
		
	}
	
	/* 품목 삭제 */
	function prdtDelEvent(){
		var ids = $("#container2List").jqGrid('getDataIDs');

		var rowId = $("#container2List").getGridParam("selrow");
		
	    if (rowId != null){
	    	var rowData = $("#container2List").jqGrid('getRowData', rowId);
	    	if(rowData.gridFlag == "I"){
				$("#container2List").jqGrid('delRowData', rowId);
	        }else{
	    		$("#container2List").jqGrid('setCell', rowId, 'useYnNm', '삭제');
	    		$("#container2List").jqGrid('setCell', rowId, 'useYn', 'N');
	    		$("#container2List").jqGrid('setCell', rowId, 'gridFlag', 'U');
	        }
	    }else{
	    	alert("선택된 행이 없습니다.")
	    }
	}
	
	/* 내부견적서 출력 */
	function salesEstInPrint(){
		
		$("#searchPrtClass").val("IN");
		
		var url = "<c:url value="/app/sm/custsales/curSalesEstInPrint" />";
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
	
	/* 외부견적서 출력 */
	function salesEstOutPrint(){
		
		$("#searchPrtClass").val("OUT");
		
		var url = "<c:url value="/app/sm/custsales/curSalesEstOutPrint" />";
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
	
	/* 신규품목입점의뢰서 출력 */
	function salesEstNewPrdtPrint(){
		
		$("#searchPrtClass").val("NEW");
		
		var url = "<c:url value="/app/sm/custsales/curSalesEstNewPrdtPrint" />";
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
	
	function prdt_clear(event){
		$('#prdtNm').val('');
	}
	function estNum_clear(event){
		$('#searchEstNum').val('');
	}
	function salesNm_clear(event){
		$('#salesCd').val('');
		$('#salesNm').val('');
	}
	function salesPrNm_clear(event){
		$('#salesPrNm').val('');
	}

</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
<input type="hidden" id="searchPrtClass" name="searchPrtClass">
<sec:csrfInput/>
	<fieldset>
	<legend>매출처견적서등록</legend>
	<table  summary="" class=type1>
		<caption>매출처견적서등록 검색조건</caption>
		<colgroup>
			<col width="150">
			<col width="300">
			<col width="150">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2" >견적번호</label></th>
				<td>
					<input type="text" id="searchEstNum" name="searchEstNum" style="width:70%;" onclick="estNum_clear()">
					<button id="btnEstNum_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
				</td>
				<th><label for="sele2">확정유무</label></th>
				<td>
					<html:codeTag comType="SELECT" objId="confirmYn" objName="confirmYn" parentID="M009" defName="${commonall}"/>
				</td>
			</tr>
		</tbody>

	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
<form name="mainform" id="mainform"  autocomplete="off" >
 <UL style="width: 100%;">
	
 	<LI style="float: left; width: 35%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        		<th><label for="sele2">&nbsp;&nbsp;&nbsp;품목명 :&nbsp;</label></th> 
        			<td>
        				<input type="text"   id="prdtNm"    name="prdtNm" style="width:40%; height: 26px;" onclick="prdt_clear()">
        			</td>
	        	<html:button id="btnPrdtSearch" auth="select" value="품목조회" />
				<html:button id="btnPrdtMove"   auth="select" value="선택품목반영" /> 
		</div>
		<!-- 서브타이틀 및 이벤트 버튼 end  -------- -->
			
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="container1List" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</LI>
 
 	<LI style="float: left; width: 65%;">
 		<div style="padding-left: 5px;">
	   		<div class="tit_area">
				<h2 class="subhead">견적서 등록</h2>
				<div class="btn_l">
					<html:button id="btnInEstPrt" 		name="btnInEstPrt"			auth="select"  value="내부견적서출력"  />
					<html:button id="btnOutEstPrt" 		name="btnOutEstPrt"   		auth="select"  value="외부견적서출력"  />
					<html:button id="btnNewPrdtEstPrt" 	name="btnNewPrdtEstPrt"   	auth="select"  value="신규품목입점의뢰서출력"  />
					&emsp;&emsp;&emsp;&emsp;&emsp;
					<html:button id="btnInit"     name="btnInit"    auth="insert" /> 		<%-- 신규  --%>
					<html:button id="btnSearch"   name="btnSearch"  auth="select" /> 		<%-- 조회 --%>
					<html:button id="btnSave"     name="btnSave"    auth="save" 	/> 		<%-- 저장  --%>
				</div>
			</div>
			<div id="dataFrom" class="dataFrom">
				<table class="type1" >
					<colgroup>
						<col width="170"/>
						<col width="*"/>
						<col width="170"/>
						<col width="*"/>
					</colgroup>
					<tbody>
						<tr>
							<th><label for="sele2">매출처명</label></th>
							<td>
								<input type="text" id="salesCd" name="salesCd" readonly="readonly" style="width: 20%;">
								<input type="text" id="salesNm" name="salesNm" style="width: 50%;" onclick="salesNm_clear()">
								<input type="hidden" id="profitClass" name="profitClass" style="width: 50%;">
								<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
							</td>
							<th><label for="sele2">고객사대표번호</label></th>
								<td>
									<input type="text"   id="salesTelNo"   name="salesTelNo"  style="width: 50%;">
								</td>
						</tr>
						<tr>
							<th>견적일</th>
							<td>
								<input type="text" id="estDt" name="estDt" style="width: 30% " />
							</td>
							<th>유효일</th>
							<td >
								<input type="text" class="dt" id="effDt" name="effDt" style="width: 100px !important;" />
							</td>
						</tr>
						<tr>
							<th>영업담당자</th>
							<td>
								<input type="text" id="salesPrNm" name="salesPrNm" style="width: 50%;" onclick="salesPrNm_clear()">
								<button id="btnSalesPr_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
							</td>
							<th>핸드폰</th>
							<td>
								<input type="text"   id="mTelNo"    name="mTelNo" style="width: 50%;">
							</td>
						</tr>
						<tr>
							<th>매출시작예정일</th>
							<td>
								<input type="text" class="dt" id="salesStDt" name="salesStDt" style="width: 100px !important;" />
							</td>
							<th>최초입고예정일</th>
							<td>
								<input type="text" class="dt" id="buyStDt" name="buyStDt" style="width: 100px !important;" />
							</td>
						</tr>
						<tr>
							<th>매출의뢰담당자</th>
							<td>
								<input type="text"   id="reqSalesPrNm"    name="reqSalesPrNm" style="width: 50%;">
							</td>
							<th>매출처의뢰담당자연락처</th>
							<td>
								<input type="text"   id="reqSalesTelNo"    name="reqSalesTelNo" style="width: 50%;">
							</td>
						</tr>
						<tr>
							<th>견적조건</th>
							<td colspan=3>
								<input type="text"   id="cnstrRemarks"    name="cnstrRemarks" style="width:70%;">
						</tr>
						<tr>
							<th>특이사항</th>
							<td colspan=3>
								<input type="text"   id="spclRemarks"    name="spclRemarks" style="width:70%;">
						</tr>					
					</tbody>
				</table>
				
			</div>
			<div  style="padding-left: 5px;" id="estData" class="estData">
				<UL>
					<LI style="float: left; width: 100%;">
						<!-- 분류 서브타이틀 및 이벤트 버튼 start  ----------------->
						<div class="tit_area">
		                	<h2><a href="#" onClick="testFunction();">견적서 품목 내역</a></h2>
		                	<div>
				                <td style="border-left:none;">
									<html:button id="btnPrdtAdd"   name="btnPrdtAdd"  auth="save" value="추가출고"  />
									<html:button id="btnPrdtDel"   name="btnPrdtDel"  auth="save" value="삭제"  />
								</td>
							</div>
						</div>
						<!-- 대분류 서브타이틀 및 이벤트 버튼 end  ----------------->
		
		                <!-- centent List -------------------------->
		                <div id="grid2container" class="gridcontainer">
		                	<table id="container2List" ><tr><td></td></tr></table>
		                </div>
		
		                <div id="pageList2"></div>
		                <!-- centent List -------------------------->
					</LI>
				</UL>
		</div>
		
	 </LI>
 	</UL>
 </form>
	
 
 
<!-- CONTENT- BODY end ----------------------------------- -->



<!-- 매출처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesMaster.jsp" />

<!-- 영업담당자 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_salesPrMaster.jsp" />

<!-- 견적서 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_EstNum.jsp" />

<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />

</div>
</body>
</html>

