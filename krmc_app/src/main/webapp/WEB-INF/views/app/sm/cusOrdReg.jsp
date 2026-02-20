<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

let balAmtTot = 0;

$(document).ready(function(){

	$("#searchForm #dlvDt").datepicker();
	
	$("#dlvDt").datepicker('setDate','c+1d');

	initGrid();
	initDetailGrid();

	if('${userSession.userType}' == "S1"){
		salesCdAddData();
 	}
	
	/* BUTTON CLICK 이벤트 처리 --------------------------------------------*/
	$('#btnNew').unbind().click(null,	    	newEvent); 	    // 신규
	$('#btnSearch').unbind().click(null,		searchEvent); 	// 검색
	$('#btnSave').unbind().click(null,	    	saveEvent); 	// 저장
	
	$('#btnSalesPrdtList').unbind().click(null,	salesPrdtListEvent); // 대상품목조회
	$('#btnAddPrdtDel').unbind().click(null,	addPrdtDelEvent); // 추가품목삭제

	$("#btnSales_find").unbind().click(function(){
		findSalesMst();
	});		<%--매출처 팝업 찾아가기--%>
	
	$("#btnPrdt_find").unbind().click(function(){
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
					$('#dataDetailList').setGridHeight(height-250);
				else
					$('#dataDetailList').setGridHeight(height-300);		
			}
        }catch(e){}
    }).trigger('resize');
	/*-------------------------------------------------------------------*/
	
	// 조회조건 입력필드 enter key이벤트 --------------
	$('#salesNm').unbind().keydown(function(e) {
		switch(e.which) {
    		case 13 : findSalesMst(this); break; // enter
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
	$('#ordQty').unbind().keydown(function(e) {
		switch(e.which) {
    		case 13 : prdtFreeAdd(this); break; // enter
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
		,colNames:[ '품목코드'
        			, '품목명'
        			, '규격'
        			, '단위'
        			, '단가'
        			, '저장형태'
        			, '과세유무'
        			
        			, '박스당수량'
        			, '부가세유무'
        			, '규격유무'
        			, '발주수량'
        			
        ]
		,colModel:[
					{name:'prdtCd'			, index:'prdtCd'		, sortable:true, width:50 , align:"center"}
            		,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, width:150 , align:"left"}
	                ,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, width:80 , align:"center"}
	                ,{name:'ordUnitNm'		, index:'ordUnitNm'		, sortable:true, width:50 , align:"center"}
	                ,{name:'price'			, index:'price'			, sortable:true, width:50 , align:"right", formatter:'integer'}
	                ,{name:'strgTypeNm'		, index:'strgTypeNm'	, sortable:true, width:50 , align:"center"}
	                ,{name:'vatYnNm'		, index:'vatYnNm'		, sortable:true, width:50 , align:"center"}
	                
	                ,{name:'qtyBox'			, index:'qtyBox'		, sortable:true, width:50 , align:"center", hidden:true}
	                ,{name:'vatYn'			, index:'vatYn'			, sortable:true, width:50, align:"center", hidden:true}
	                ,{name:'stdYn'			, index:'stdYn'			, sortable:true, width:50, align:"center", hidden:true}
	                ,{name:'ordQty'			, index:'ordQty'		, sortable:true, width:50, align:"center", hidden:true}

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
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();
                $(this).addRowData("blankRow", msg);
            }
        	$(window).resize();
        }
		,ondblClickRow: function (rowid, iRow, iCol) {
			var rowdata = $(this).getRowData(rowid);	// 선택한 행의 데이터를 가져온다
			
			if(rowdata.prdtNm != ""){
				setGridClickData(rowdata);
			}
		}
		,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
			return false;
        }
        ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트
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

	});
}



/* 세부코드 그리드 초기화 */
function initDetailGrid() {
    $("#dataDetailList").jqGrid({
         datatype: 'local'
        ,data: []
        ,colNames:[   '전표번호'
                    , '품목코드'
                    , '품명'
                    , '규격'
                    , '단위'
                    , '발주수량'
                    , '박스수량'
                    , '단가'
                    , '공급가'
                    , '부가세'
                    , '합계금액'
                    , '창고명'
                    , '비고'
                    
                    , '발주일자'
                    , '입고요청일자'
                    , '매출처코드'
                    , '전표순번'
                    , '창고코드'
                                        
                    , '규격유무'
                    , '박스당수량'
                    , '부가세유무'
                    
                    , '추가유무'
         ]
    
        ,colModel:[
			{name:'salesSlipNo'		, index:'salesSlipNo'	, sortable:true, width:70 , align:"center"}
			,{name:'prdtCd'			, index:'prdtCd'		, sortable:true, width:50 , align:"center"}
    		,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, width:150 , align:"left"}
            ,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, width:80 , align:"center"}
            ,{name:'ordUnitNm'		, index:'ordUnitNm'		, sortable:true, width:50 , align:"center"}
            ,{name:'ordQty'			, index:'ordQty'		, sortable:true, width:50 , align:"right", formatter: fmtSetGridInputNumHour, unformat: unfmtSetGridInputNumHour}
            ,{name:'boxQty'			, index:'boxQty'		, sortable:true, width:50 , align:"center"} 
            ,{name:'price'			, index:'price'			, sortable:true, width:50 , align:"right", formatter:'integer'}
            ,{name:'pureAmt'		, index:'pureAmt'		, sortable:true, width:50 , align:"right", formatter:'integer'}
            ,{name:'vatAmt'			, index:'vatAmt'		, sortable:true, width:50 , align:"right", formatter:'integer'}
            ,{name:'totAmt'			, index:'totAmt'		, sortable:true, width:50 , align:"right", formatter:'integer'}
            ,{name:'whNm'			, index:'whNm'			, sortable:true, width:70 , align:"center"}
            
            ,{name:'ordDt'			, index:'ordDt'			, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'dlvDt'			, index:'dlvDt'			, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'salesCd'		, index:'salesCd'		, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'salesSeq'		, index:'salesSeq'		, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'whCd'			, index:'whCd'			, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'remarks1'		, index:'remarks1'		, sortable:true, width:50, align:"center", hidden:true}
            
            ,{name:'stdYn'			, index:'stdYn'			, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'qtyBox'			, index:'qtyBox'		, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'vatYn'			, index:'vatYn'		    , sortable:true, width:50, align:"center", hidden:true}
        	
            ,{name:'addDataRow'		, index:'addDataRow'	, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
        ]
       ,gridComplete : function() {
        	var colCount = $(this).getGridParam("colNames").length;
            $("#blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
            
            var gridData = $("#dataDetailList");
            ids = gridData.jqGrid('getDataIDs');
            
            if(ids.length > 0){
           	    $('#remarks1').val($("#dataDetailList").jqGrid('getRowData', ids[0]).remarks1);
            }
             
		    $('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
			
			// ===================== Footer Sum
			gridData.jqGrid('footerData', 'set', { 'prdtCd' : '합계' });
			
			var sum_ordQty = gridData.jqGrid('getCol','ordQty', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'ordQty':sum_ordQty });
	    	
		    var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
	    	
	    	var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
	    	
	    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
            

            if(ids.length > 0){
           	    $('#remarks1').val($("#dataDetailList").jqGrid('getRowData', ids[0]).remarks1);
            }
            
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
	                        $nextInput.setSelectionRange(0, $nextInput.value.length);
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
	                        $nextInput.setSelectionRange(0, $nextInput.value.length);
	                    }
	                }	             
	            });
	         	
	            $("#dataDetailList").unbind('keyup').keyup(function (e) {
	                saveDataCheck();
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
           
           $(this).find("input.dy").datepicker().attr("style", "width:80%");
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
		,rowNum			:-1
        ,loadui 		: "disable"
        ,gridview		: true
        ,sortorder		: 'asc'
        //,cellEdit       : true
        ,viewrecords	: true
        ,scroll 		: true
        ,rownumbers		: true
        ,footerrow		: true
        ,loadonce		: false        
    });
    
    jQuery("#dataDetailList").jqGrid('setFrozenColumns');
}


/* 목록에서 더블 클릭 시 상세에 행 추가 */
function setGridClickData(data){
	
	var rowId = $('#dataDetailList').jqGrid('getDataIDs');
	
	var rowSubData = $("#dataDetailList").jqGrid('getRowData', rowId[0]);

	$("#dataDetailList").jqGrid('addRow',{
		initdata : {
			'prdtCd' : data.prdtCd
			, 'prdtNm' : data.prdtNm
			, 'prdtStd' : data.prdtStd
			, 'ordUnitNm' : data.ordUnitNm
			, 'qtyBox' : data.qtyBox
			, 'stdYn' : data.stdYn
			, 'price' : data.price
			, 'vatYn' : data.vatYn
			, 'salesCd' : $("#salesCd").val()
			, 'ordQty' : 0
			, 'addDataRow' : "A"
		},
		position : "first",
		useDefValues : false,
		useFormatter : true
	});

}


/*매출처 찾기팝업 호출 */
function findSalesMst(){
	commonSalesMstFindLayer('','',$("#salesNm").val(), 'Y', 'Y', setSalesMstFindLayer);
}

/*매출처 콜백(후처리 함수 )*/
function setSalesMstFindLayer(data) {
	if(data != null){
		$("#salesCd").val(data.salesCd);		/*매출처코드*/
		$("#salesNm").val(data.salesNm);		/*매출처명*/
		
		//Grid 초기화
		$("#dataList").jqGrid('clearGridData');
		$("#dataDetailList").jqGrid('clearGridData');
		
		salesCdAddData();
		
		$('#prdtNm').focus();
	}
}


//품목 찾기 팝업
function findPrdtMst(){
	if($("#salesCd").val() != ''){
		commonCurOrgRegSalesPrdtMstFindLayer('', '', $("#prdtNm").val(), $("#salesCd").val(), setCurOrdRegSalesPrdtMstFindLayer);
	}else{
		alert('매출처를 선택하세요.');
		return;
	}
}

/*품목 콜백(후처리 함수 )*/
function setCurOrdRegSalesPrdtMstFindLayer(data) {
	if (data != null){
		$("#prdtCd").val(data.prdtCd);
		$("#prdtNm").val(data.prdtNm);
		
		$('#ordQty').focus();
	}
}


 /* 신규 */
function newEvent(event){
//     $('form').each(function() {
//         this.reset();
//     });
    
//   	//Grid 초기화
//     $("#dataList").jqGrid('clearGridData');
// 	$("#dataDetailList").jqGrid('clearGridData');
	
// 	$("#dlvDt").datepicker('setDate','c+1d');

	location.reload();
}

/* 대상품목 조회 */
function salesPrdtListEvent(event){
	
	if(!$('#salesCd').val()) {
		alert('매출처를 입력하세요.');
		$('#salesNm').focus();
		return false;
	}	
	
	var searchInfo = {};
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.id] = $(this).val().replaceAll('-','');
	});

	//Grid 초기화
	$("#dataList").jqGrid('clearGridData');
	
	$("#dlvDt").prop("readonly", true);	
	$("#searchForm #dlvDt").datepicker("disable");
	
	$("#dataList").jqGrid('setGridParam',{
		url:"<c:url value='/app/sm/custsales/cusOrdReg_selPrdtList.json'/>",
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

/* 발주 등록 내역 조회 */
function searchEvent(){

	if(!$('#salesCd').val()) {
		alert('매출처를 입력하세요.');
		$('#salesNm').focus();
		return false;
	}	
	
	var searchInfo = {};
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.id] = $(this).val().replaceAll('-','');
	});
	
	//Grid 초기화
	$("#dataDetailList").jqGrid('clearGridData');
	
	$("#dlvDt").prop("readonly", true);	
	$("#searchForm #dlvDt").datepicker("disable");

  	$("#dataDetailList").jqGrid('setGridParam',{
        url:'<c:url value="/app/sm/custsales/cusOrdReg_selList.json" />', 		
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

/* 저장 */
function saveEvent(event){
	
	$("#dataDetailList").editCell(0, 0, false);
	
	if($("#salesCd").val() == ''){
    	alert('매출처를 선택하세요.');
    	$("#salesNm").focus();    	
    	return;
    }

	var gridData = $("#dataDetailList").jqGrid('getRowData');
	
    var seen = {};
    
    // 그리드 데이터를 순회
    var slipNo	=	"";
    for (var i = 0; i < gridData.length; i++) {
        var value 	= gridData[i]['prdtCd'];
        slipNo 		= gridData[i]['salesSlipNo']
        // 중복 확인
        if (seen[value]) {
        	alert('중복 품목코드가 존재합니다.');
        	return;
        }else{
        	seen[value] = true;
        }
    }
    

// 	if(!saveDataCheck()){
// 		return;
// 	}

	var creLim = $("#creLim").val().replace(/,/g, '');;

	if(balAmtTot > creLim){
		alert('발주 금액이 여신 한도를 초과했습니다.\n\n 관리팀에 문의해주세요.(031-793-4997)');
		return false;
	}
	
	//-----입고예정일자는 발주일자 보다 작을수 없다
	var ordDt	=	$("#ordDt").val().replaceAll("-", "");		//발주일자
	var dlvDt	=	$("#dlvDt").val().replaceAll("-", "");		//입고 예정일자
	
	if (dlvDt <= ordDt && ('${userSession.userType}' == "S1" || '${userSession.userType}' == "S2")) {
		alert("발주일자 이후로 입고예정일자를 지정해주세요");
		return;
	}
	
	//-----2024.12.15 add by song min kyo 전표생성 되었을 경우 저장 불가능
	<%--
		2024.12.15 윤현수 부장 요청
		매출처에서 발주 등록 저장 후 수량 변경 및 수정 못하게 고정 처리 부탁드립니다
	 --%>
	if (slipNo != "") {
		alert("발주등록 후 수량변경 및 수정은 불가능 합니다");
		return;
	}
	
	if(!confirm("저장하시겠습니까?")){
		return;
	}

	var saveData = {'orderRegList'  : $("#dataDetailList").getRowData()
			        , 'whCd'        : $('#whCd').val()
			        , 'remarks1'    : $('#remarks1').val()
			        , 'salesCd'     : $('#salesCd').val()
			        , 'ordDt'       : $('#ordDt').val().replaceAll('-','')
			        , 'dlvDt'       : $('#dlvDt').val().replaceAll('-','')
	               };

	$.ajax({
	    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	    url  : "<c:url value='/app/sm/custsales/cusOrdReg_insOrdList.json'/>",

	    data : JSON.stringify(saveData),
 	    success : function(data){

 	    	if(data.msgCd =="0") {
				alert("신규 : "+ data.rtnValue01+"건  |  수정 : "+data.rtnValue02+"건이 처리 정상 되었습니다.");
				
   				searchEvent();
 	    	}else if(data.msgCd =="2") { //전체 발주마감 에러
 	    		alert(data.message);
 	    	}else if(data.msgCd =="3") { //대상 전표 발주마감 에러
 	    		alert(data.message);
		   	}else{
				alert("처리중 오류가 발생하였습니다. Code : "+data.msgCd+ "Message : "+data.message);
			}
  	    }
	});
}

//박스수량, 금액 및 여신 한도 체크
function saveDataCheck(){
    
	var ordDt = $('#ordDt').val().replaceAll('-','');
	var dlvDt = $('#dlvDt').val().replaceAll('-','');
	
	var gridData = $("#dataDetailList");
	var allRows = gridData.jqGrid('getDataIDs');
	
	var totAmt = 0;
	var balAmt = 0;
	var balRecAmt = $("#balRecAmt").val().replace(/,/g, '');;
	var creLim = $("#creLim").val().replace(/,/g, '');;

	for(var i=0; i<allRows.length;i++){
		var rowid = allRows[i];
		var rowData = $('#dataDetailList').jqGrid('getRowData', rowid);
		

		var ordQty = parseFloat(rowData.ordQty);
	    var qtyBox = rowData.qtyBox;
	    var strQtyBox = parseInt(ordQty / qtyBox);
		
	    var strEa = ordQty % qtyBox;
	    if(strEa % 1 != 0){
	    	strEa = strEa.toFixed(2);
	    }
	    
		var ordUnitNm = rowData.ordUnitNm;
	    var stdYn = rowData.stdYn;
	   
		if(stdYn == "2"){
	    	$("#dataDetailList").jqGrid('setCell',rowid,'boxQty',String(ordQty).concat(ordUnitNm));
	    } else {
	    	if(ordQty < qtyBox){
	    		$("#dataDetailList").jqGrid('setCell',rowid,'boxQty',String(ordQty).concat(ordUnitNm));
	    	} else if(strEa == 0){
	    		if(qtyBox > 1){
	    			$("#dataDetailList").jqGrid('setCell',rowid,'boxQty',String(strQtyBox).concat("BOX"));
	    		} else{
	    			$("#dataDetailList").jqGrid('setCell',rowid,'boxQty',String(ordQty).concat(ordUnitNm));
	    		}
	    	} else{
	    		$("#dataDetailList").jqGrid('setCell',rowid,'boxQty',String(strQtyBox).concat("BOX/", strEa, ordUnitNm));
	    	}
	    }

	    var price = parseInt(rowData.price);
	    var vatYn = rowData.vatYn;
	    
	    var pureAmt = Math.round(ordQty * price);
		var vatAmt = Math.round(pureAmt * 0.1);
	    
	    if(vatYn == "2"){
	    	$("#dataDetailList").jqGrid('setCell',rowid,'pureAmt',pureAmt);
	    	$("#dataDetailList").jqGrid('setCell',rowid,'vatAmt',0);
	    	$("#dataDetailList").jqGrid('setCell',rowid,'totAmt',pureAmt);
	    	
	    	totAmt = parseInt(totAmt) + parseInt(pureAmt);
	    } else{
	    	$("#dataDetailList").jqGrid('setCell',rowid,'pureAmt',pureAmt);
	    	$("#dataDetailList").jqGrid('setCell',rowid,'vatAmt',vatAmt);
	    	$("#dataDetailList").jqGrid('setCell',rowid,'totAmt',pureAmt + vatAmt);
	    	
	    	totAmt = parseInt(totAmt) + parseInt(pureAmt + vatAmt);
	    }
	    
	    var rowDataTemp = $('#dataDetailList').jqGrid('getRowData', rowid);
	    var pureAmtTemp = rowDataTemp.pureAmt;
	    
// 	    if(price != 0){
// 	    	if(pureAmtTemp == 0){
// 		    	alert('발주금액 계산에 문제가 발생했습니다. \n\n저장 버튼을 다시 눌러 주시기 바랍니다.');
// 		    	return false;
// 		    }
// 	    }
	    
		
	}
	
	gridData.jqGrid('footerData', 'set', { 'prdtCd' : '합계' });
	
	var sum_ordQty = gridData.jqGrid('getCol','ordQty', false, 'sum');
	gridData.jqGrid('footerData', 'set', { 'ordQty':sum_ordQty });
	
    var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
	
	var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
	gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
	
	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
	
	balAmtTot = totAmt + parseInt(balRecAmt);
	
// 	if(totAmt > creLim){
// 		alert('발주 금액이 여신 한도를 초과했습니다.\n\n 관리팀에 문의해주세요.(031-793-4997)');
// 		return false;
// 	}
	return true;
}


/* 수량에서 엔터 눌렀을 경우 해당 품목의 데이터를 가지고 와서 그리드에 보여줌 */
function prdtFreeAdd(event){
	if(!$('#salesCd').val()) {
		alert('매출처를 입력하세요.');
		$('#salesNm').focus();
		return false;
	}		
	if(!$('#prdtNm').val()) {
		alert('품목을 입력하세요.');
		$('#prdtNm').focus();
		return false;
	}
	if(!$('#ordQty').val()) {
		alert('수량을 입력하세요.');
		$('#ordQty').focus();
		return false;
	}

	var searchInfo = {};
	
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.name] = $(this).val().replaceAll('-','')
	});
	
	$("#dlvDt").prop("readonly", true);	
	$("#searchForm #dlvDt").datepicker("disable");

	$.ajax({
		contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		url:"<c:url value='/app/sm/custsales/cusOrdReg_selPrdtAdd.json'/>",
		data: JSON.stringify(searchInfo),
		success : function(data){
			
			//blankRow 제거
			$("#dataDetailList").jqGrid('delRowData', 'blankRow');
			
			var rowId = $("#dataDetailList").getGridParam("reccount");
			
			var ordQty =  $("#ordQty").val();
						
			var rowSubData = $("#dataDetailList").jqGrid('getRowData', rowId[0]);
			
			$("#dataDetailList").jqGrid('addRowData', rowId+1, data.cusOrdRegVo, 'last');
			$("#dataDetailList").jqGrid('setCell',rowId+1,'ordQty',ordQty);
			$("#dataDetailList").jqGrid('setCell',rowId+1,'addDataRow',"A");

			//하나의 품목 입력 후 input 박스 초기화
			prdt_clear();
			
			//수량에서 엔터를 눌렀을때 박스및 금액 계산
			saveDataCheck();
			
// 			var input = document.getElementById("salesNm");
// 		    input.setAttribute("readonly", true);
		    
// 		    var button = document.getElementById("btnSales_find");
// 		    button.disabled = true;
		    
// 		    document.getElementById("salesNm").onclick = function(event) {
// 		        // 클릭 이벤트의 기본 동작을 취소합니다.
// 		        event.preventDefault();
// 		        // 이벤트를 중지합니다.
// 		        return false;
// 		    };
			
			$('#prdtNm').focus();
			
		}
		
	});
}


function addPrdtDelEvent(event){	
	
	var ids = $("#dataDetailList").jqGrid('getDataIDs');
	
	var rowId = $("#dataDetailList").jqGrid('getGridParam', "selrow");
	var rowData = $("#dataDetailList" ).jqGrid('getRowData', rowId);  
	
	if(rowData.addDataRow == "A"){
		$("#dataDetailList").jqGrid('delRowData', rowId);			
    }
	
//	var ids = $("#dataDetailList").jqGrid('getDataIDs');

//     for(var i=0; i<ids.length;i++){
//     	var rowData = $("#dataDetailList").jqGrid('getRowData', ids[i]);
//     	var rowId = $.trim(ids[i]);

// 		if(rowData.addDataRow == "A"){
// 			$("#dataDetailList").jqGrid('delRowData', rowId);
//         }
//     }
}


function salesCdAddData(){
	
	var searchInfo = {};
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.name] = $(this).val().replaceAll('-','')
	});

	$.ajax({
		contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		url:"<c:url value='/app/common/selectSalesAddData'/>",
		data: JSON.stringify(searchInfo),
		success : function(data){

			if(data != null) {
				//결과 데이터 셋팅
				$("#creLim").val(data.creLim.replace(/\B(?=(\d{3})+(?!\d))/g, ","));
				$("#setlDt").val(data.setlDt);
				$("#lastBalAmt").val(data.lastBalAmt.replace(/\B(?=(\d{3})+(?!\d))/g, ","));
				$("#salesAmt").val(data.salesAmt.replace(/\B(?=(\d{3})+(?!\d))/g, ","));
				$("#setlAmt").val(data.setlAmt.replace(/\B(?=(\d{3})+(?!\d))/g, ","));
				$("#balRecAmt").val(data.balRecAmt.replace(/\B(?=(\d{3})+(?!\d))/g, ","));
				$("#carNm").val(data.carNm);
				
			}
		}
		
	});
}

function sales_clear(event){
	$('#salesNm').val('');
	$('#salesCd').val('');
	$('#prdtNm').val('');
	$('#prdtCd').val('');
	$('#ordQty').val('');
}

function prdt_clear(event){
	$('#prdtNm').val('');
	$('#prdtCd').val('');
	$('#ordQty').val('');
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
<fieldset>
	<legend>매출처발주등록</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처발주등록 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="10%">
			<col width="100">
			<col width="20%">
			<col width="100">
			<col width="20%">
			<col width="100">
			<col width="10%">
			<col width="100">
			<col width="10%">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">입고예정일자</label></th>
					<td>
						<input type="text" id="dlvDt" name="dlvDt" style="width:90px" >
					</td>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<c:choose>
							<c:when test ="${userSession.userType eq 'S1'}"> 
								<input type="text" id="salesNm" name="salesNm" style="width: 80%;" value="${userSession.username}" disabled/>
								<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;" value="${userSession.userId}">
							</c:when>
							<c:otherwise>
								<input type="text" id="salesNm" name="salesNm" style="width: 80%;" onclick="sales_clear()">
								<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;">
								<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
							</c:otherwise>
						</c:choose>
					</td>
				<th><label for="sele2">품목</label></th>
					<td>
						<input type="text"   id="prdtNm"  name="prdtNm" style="width:80%;" onclick="prdt_clear()">
						<input type="hidden" id="prdtCd"  name="prdtCd" >
						<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
					</td>
				<th><label for="sele2">수량</label></th>
					<td>
						<input type="text"   id="ordQty"  name="ordQty" style="width:80%; text-align:right;">
					</td>
				<th><label for="sele2">발주일자</label></th>
					<td>
						<input type="text" id="ordDt" name="ordDt" readonly="readonly" style="width: 100px !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/>
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
 		<div  style="padding-left: 0px; ">
 		<div class="tit_area">
			<h2 class="subhead">매출처발주목록</h2>
		    <div class="btn_l">
			    <html:button id="btnSalesPrdtList"  name="btnSalesPrdtList"	auth="select" value="대상품목조회"/> 		<%-- 대상품목조회 --%>
				&nbsp;&nbsp;&nbsp;
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnSave"   		name="btnSave"     		auth="save"   />		<%-- 저장 --%>
			</div>
		</div>
	</form>
	</div>	
 	</li>
 	
 	
 	<li style="float: top; width: 100%;">
 		<form>
 		<div class="tit_area">
           	<div>
           		<th><label margin-top: 2px;">&ensp;&ensp;&ensp;&ensp;배송차량&ensp;</i></label></th>
	           		<td>
	           			<input  type="text"   id="carNm"    name="carNm" style="height: 24px; width: 10%;" disabled>
	           		</td>&ensp;&ensp;
           		<th><label margin-top: 2px;">여신한도&ensp;</i></label></th>
	           		<td>
	           			<input  type="text"   id="creLim"    name="creLim" style="height: 24px; width: 8%; text-align:right;" disabled>
	           		</td>&ensp;&ensp;
           		<th><label margin-top: 2px;">결제일자&ensp;</i></label></th>
	           		<td>
	           			<input  type="text"   id="setlDt"    name="setlDt" style="height: 24px; width: 8%; text-align:center;" disabled>
	           		</td>&ensp;&ensp;
           		<th><label margin-top: 2px;">전월미수&ensp;</i></label></th>
	           		<td>
	           			<input  type="text"   id="lastBalAmt"    name="lastBalAmt" style="height: 24px; width: 8%; text-align:right;" disabled>
	           		</td>&ensp;&ensp;
           		<th><label margin-top: 2px;">당월매출&ensp;</i></label></th>
	           		<td>
	           			<input  type="text"   id="salesAmt"    name="salesAmt" style="height: 24px; width: 8%; text-align:right;" disabled>
	           		</td>&ensp;&ensp;
           		<th><label margin-top: 2px;">당월입금&ensp;</i></label></th>
	           		<td>
	           			<input  type="text"   id="setlAmt"    name="setlAmt" style="height: 24px; width: 8%; text-align:right;" disabled>
	           		</td>&ensp;&ensp;
           		<th><label margin-top: 2px;">미수잔액&ensp;</i></label></th>
	           		<td>
	           			<input  type="text"   id="balRecAmt"   name="balRecAmt" style="height: 24px; width: 8%; text-align:right;" disabled>
	           		</td>
           	</div>
		</div>
		</form>
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
    	<h2>발주 품목 </h2>	
		<div >
			<label style="margin-top:2px;">※비고 </label>
			<input type="text" id="remarks1" name="remarks1" style="height: 24px; width:900px; margin-right: 100px;">
			<label style="margin-top:2px;">출고창고 </label>
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

<!-- 매출처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesActMaster.jsp" />


<!-- 매출처별 상품 찾기 재고 포함  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_CurOrdRegSalesPrdtMaster.jsp" />


</div>
</body>
</html>
