<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">
$(document).ready(function(){

	$("#searchFreeDt").datepicker();
	
	initGrid();
	initDetailGrid();
	
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
					$('#dataDetailList').setGridHeight(230);
				else
					$('#dataDetailList').setGridHeight(height-280);		
			}
        }catch(e){}
    }).trigger('resize');
	/*-------------------------------------------------------------------*/
	
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
	$('#freeQty').unbind().keydown(function(e) {
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
        			, '저장형태'
        			, '과세유무'
        			, 'Box당 수량'
        			, '원가'
        			
        			, '부가세유무'
        			, '규격유무'
        			, '출고수량'
        			
        ]
		,colModel:[
					{name:'prdtCd'			, index:'prdtCd'		, sortable:true, width:50 , align:"center"}
            		,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, width:150 , align:"left"}
	                ,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, width:80 , align:"center"}
	                ,{name:'ordUnit'		, index:'ordUnit'		, sortable:true, width:50 , align:"center"}
	                ,{name:'strgType'		, index:'strgType'		, sortable:true, width:50 , align:"center"}
	                ,{name:'vatYnNm'		, index:'vatYnNm'		, sortable:true, width:50 , align:"center"}
	                ,{name:'qtyBox'			, index:'qtyBox'		, sortable:true, width:50 , align:"center"}
	                ,{name:'cost'			, index:'cost'			, sortable:true, width:50 , align:"center"}
	                
	                ,{name:'vatYn'			, index:'vatYn'			, sortable:true, width:50, align:"center", hidden:true}
	                ,{name:'stdYn'			, index:'stdYn'			, sortable:true, width:50, align:"center", hidden:true}
	                ,{name:'freeQty'		, index:'freeQty'		, sortable:true, width:50, align:"center", hidden:true}

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
			
			setGridClickData(rowdata);
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
                    , '출고구분'
                    , '출고수량'
                    , '박스수량'
                    , '창고명'
                    , '등록자'
                    , '수정자'
                    
                    , '출고일자'
                    , '전표순번'
                    , '출고구분'
                    , '창고코드'
                    , '비고'
                    
                    , '규격유무'
                    , '박스당수량'
                    
                    , '추가유무'
                    , '그리드 상태'
         ]
    
        ,colModel:[
			{name:'salesSlipNo'		, index:'salesSlipNo'	, sortable:true, width:80 , align:"center"}
			,{name:'prdtCd'			, index:'prdtCd'		, sortable:true, width:50 , align:"center"}
    		,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, width:160 , align:"left"}
            ,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, width:80 , align:"center"}
            ,{name:'ordUnit'		, index:'ordUnit'		, sortable:true, width:50 , align:"center"}
            ,{name:'freeClassNm'	, index:'freeClassNm'	, sortable:true, width:50 , align:"center"}
            ,{name:'freeQty'		, index:'freeQty'		, sortable:true, width:50 , align:"right", formatter: fmtSetGridInputNumHour, unformat: unfmtSetGridInputNumHour} 
            ,{name:'boxQty'			, index:'boxQty'		, sortable:true, width:50 , align:"center"} 
            ,{name:'whNm'			, index:'whNm'			, sortable:true, width:70 , align:"center"}
            ,{name:'regNm' 			, index:'regNm'			, sortable:false, width:80, align:"center"}
            ,{name:'modNm' 			, index:'modNm'			, sortable:false, width:80, align:"center"}
            
            ,{name:'freeDt'			, index:'freeDt'		, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'salesCd'		, index:'salesCd'		, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'salesSeq'		, index:'salesSeq'		, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'freeClass'		, index:'freeClass'		, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'whCd'			, index:'whCd'			, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'remarks1'		, index:'remarks1'		, sortable:true, width:50, align:"center", hidden:true}
            
            ,{name:'stdYn'			, index:'stdYn'			, sortable:true, width:50, align:"center", hidden:true}
            ,{name:'qtyBox'			, index:'qtyBox'		, sortable:true, width:50, align:"center", hidden:true}
        	
            ,{name:'addDataRow'		, index:'addDataRow'	, sortable:true, hidden:true, editable:false, width:50 , align:"center"}
        ]
       ,gridComplete : function() {
        	var colCount = $(this).getGridParam("colNames").length;
            $("#blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
            
            ids = $("#dataDetailList").jqGrid('getDataIDs');

            if(ids.length > 0){
           	    $('#remarks1').val($("#dataDetailList").jqGrid('getRowData', ids[0]).remarks1);
            }
            
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
           
           if ($(this).getGridParam("records")==0) {
        	   var firstColName = $(this).getGridParam("colModel")[1].name;
               var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();
               $(this).addRowData("blankRow", msg);
           }
           
          // $(this).find("input.dy").datepicker().attr("style", "width:80%");
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
        //,cellEdit       : true
        ,viewrecords	: true
        ,scroll 		: true
        ,rownumbers		: true
        ,loadonce		: false        
    });
    
}

/* 그리드 데이터 세팅 */
function setGridFreeData(){
	
	var ids = $("#dataDetailList").jqGrid('getDataIDs');

    for(var i=0; i<ids.length;i++){
    	var rowData = $("#dataDetailList").jqGrid('getRowData', ids[i]);

    	var freeQty = parseFloat(rowData.freeQty);
	    var qtyBox = parseInt(rowData.qtyBox);
	    var strQtyBox = parseInt(freeQty / qtyBox);
		
	    var strEa = freeQty % qtyBox;
	    if(strEa % 1 != 0){
	    	strEa = strEa.toFixed(2);
	    }
	    
		var ordUnit = rowData.ordUnit;
	    var stdYn = rowData.stdYn;

		if(stdYn == "2"){
			$("#dataDetailList").jqGrid('setCell',ids[i],'boxQty',String(freeQty).concat(ordUnit));
		} else {
			if(freeQty < qtyBox){
				$("#dataDetailList").jqGrid('setCell',ids[i],'boxQty',String(freeQty).concat(ordUnit));
			} else if(strEa == 0){
				if(qtyBox > 1){
					$("#dataDetailList").jqGrid('setCell',ids[i],'boxQty',String(strQtyBox).concat("BOX"));
				} else{
					$("#dataDetailList").jqGrid('setCell',ids[i],'boxQty',String(freeQty).concat(ordUnit));
				}
			} else{
				$("#dataDetailList").jqGrid('setCell',ids[i],'boxQty',String(strQtyBox).concat("BOX/", strEa, ordUnit));
			}
		}
    }
}

/* 목록에서 더블 클릭 시 상세에 행 추가 */
function setGridClickData(data){
	
	var rowId = $('#dataDetailList').jqGrid('getDataIDs');
	
	var rowSubData = $("#dataDetailList").jqGrid('getRowData', rowId[0]);
	
	if(rowSubData.SalesCd == ''){
		//Grid 초기화
		$("#dataDetailList").jqGrid('clearGridData');
	}
	
	var freeClass = $("#searchFreeClass option:selected").val();			
	var freeClassNm = $("#searchFreeClass option:selected").text();

	$("#dataDetailList").jqGrid('addRow',{
		initdata : {
			'prdtCd' : data.prdtCd
			, 'prdtNm' : data.prdtNm
			, 'prdtStd' : data.prdtStd
			, 'ordUnit' : data.ordUnit
			, 'qtyBox' : data.qtyBox
			, 'stdYn' : data.stdYn
			, 'salesCd' : $("#searchSalesCd").val()
			, 'freeQty' : 0
			, 'freeClass' : freeClass
			, 'freeClassNm' : freeClassNm
			, 'addDataRow' : "A"
		},
		position : "first",
		useDefValues : false,
		useFormatter : true
	});

}


/*매출처 찾기팝업 호출 */
function findSalesMst(){
	commonSalesMstFindLayer('','',$("#searchSalesNm").val(), 'Y', 'Y', setSalesMstFindLayer);
}

/*매출처 콜백(후처리 함수 )*/
function setSalesMstFindLayer(data) {
	if(data != null){
		$("#searchSalesCd").val(data.salesCd);		/*매출처코드*/
		$("#searchSalesNm").val(data.salesNm);		/*매출처명*/
		
		//Grid 초기화
		$("#dataList").jqGrid('clearGridData');
		$("#dataDetailList").jqGrid('clearGridData');
		
		$('#searchPrdtNm').focus();
	}
}

//품목 찾기 팝업
function findPrdtMst(){
	commonCurSampleRegPrdtMstFindLayer('', '', $("#searchPrdtNm").val(), 'Y', setPrdtMstFindLayer);
}

/*품목 콜백(후처리 함수 )*/
function setPrdtMstFindLayer(data) {
	if (data != null){
		$("#searchPrdtCd").val(data.prdtCd);
		$("#searchPrdtNm").val(data.prdtNm);
		
		$('#freeQty').focus();
	}
}


 /* 신규 */
function newEvent(event){
    $('form').each(function() {
        this.reset();
    });
    
    $("#remarks1").val('');
    
  	//Grid 초기화
    $("#dataList").jqGrid('clearGridData');
	$("#dataDetailList").jqGrid('clearGridData');

}

/* 대상품목 조회 */
function salesPrdtListEvent(event){
	
	if(!$('#searchSalesNm').val()) {
		alert('매출처를 입력하세요.');
		$('#searchSalesNm').focus();
		return false;
	}	
	
	var searchInfo = {};
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.id] = $(this).val().replaceAll('-','');
	});

	//Grid 초기화
	$("#dataList").jqGrid('clearGridData');
	
	$("#dataList").jqGrid('setGridParam',{
		url:"<c:url value='/app/sm/custsales/cusSampleReg_selList.json'/>",
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

/* 샘플 등록 내역 조회 */
function searchEvent(){

	if(!$('#searchSalesNm').val()) {
		alert('매출처를 입력하세요.');
		$('#searchSalesNm').focus();
		return false;
	}	
	
	var searchInfo = {};
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.id] = $(this).val().replaceAll('-','');
	});
	
	//Grid 초기화
	$("#dataDetailList").jqGrid('clearGridData');

  	$("#dataDetailList").jqGrid('setGridParam',{
        url:'<c:url value="/app/sm/custsales/cusSampleReg_selDetailList.json" />', 		
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
	
	setGridFreeData();
	
	$("#dataDetailList").editCell(0, 0, false);
	
	if(!confirm("저장하시겠습니까?")){return;}
	
	var saveData = {'cusSampleRegList'  : $("#dataDetailList").getRowData()
			        , 'whCd'          : $('#whCd').val()
			        , 'freeClass'     : $('#searchFreeClass').val()
			        , 'freeDt'        : $('#searchFreeDt').val().replaceAll('-','')
			        , 'remarks1'      : $('#remarks1').val()
	               };

	$.ajax({
	    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	    url  : "<c:url value='/app/sm/custsales/cusSampleReg_insList.json'/>",

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
function prdtFreeAdd(event){
	if(!$('#searchSalesNm').val()) {
		alert('매출처를 입력하세요.');
		$('#searchSalesNm').focus();
		return false;
	}		
	if(!$('#searchPrdtNm').val()) {
		alert('품목을 입력하세요.');
		$('#searchPrdtNm').focus();
		return false;
	}
	if(!$('#freeQty').val()) {
		alert('수량을 입력하세요.');
		$('#freeQty').focus();
		return false;
	}

	var searchInfo = {};
	
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.name] = $(this).val();
	});

	$.ajax({
		contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		url:"<c:url value='/app/sm/custsales/cusSampleReg_selPrdtAdd.json'/>",
		data: JSON.stringify(searchInfo),
		success : function(data){
			
			//blankRow 제거
			$("#dataDetailList").jqGrid('delRowData', 'blankRow');
			
			rowId = $("#dataDetailList").getGridParam("reccount");
			
			var freeQty =  $("#freeQty").val();
			
			$("#dataDetailList").jqGrid('addRowData', rowId+1, data.cusSampleRegVo, 'first');
			$("#dataDetailList").jqGrid('setCell',rowId+1,'freeQty',freeQty);
			$("#dataDetailList").jqGrid('setCell',rowId+1,'addDataRow',"A");
			
			$("#dataDetailList").jqGrid('setCell',rowId+1,'freeClass',$("#searchFreeClass option:selected").val());			
			$("#dataDetailList").jqGrid('setCell',rowId+1,'freeClassNm',$("#searchFreeClass option:selected").text());

			//setGridFreeData(data.cusSampleRegVo, freeQty, rowId+1, 'freeQty');
			
			//하나의 품목 입력 후 input 박스 초기화
			prdt_clear();
			
			$("#searchPrdtNm").focus();
		}
		
	});
}


function addPrdtDelEvent(event){	
	var ids = $("#dataDetailList").jqGrid('getDataIDs');
	
	var rowId = $("#dataDetailList").getGridParam("selrow");
	
    if (rowId != null){
    	var rowData = $("#dataDetailList").jqGrid('getRowData', rowId);
    	if(rowData.addDataRow == "A"){
			$("#dataDetailList").jqGrid('delRowData', rowId);
        }
    }else{
    	alert("선택된 행이 없습니다.")
    }
}

function sales_clear(event){
	$('#searchSalesNm').val('');
	$('#searchSalesCd').val('');
	$('#searchPrdtNm').val('');
	$('#searchPrdtCd').val('');
	$('#freeQty').val('');
}

function prdt_clear(event){
	$('#searchPrdtNm').val('');
	$('#searchPrdtCd').val('');
	$('#freeQty').val('');
	
	$('#searchPrdtNm').focus();
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
	<legend>매출처샘플등록</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처샘플등록 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="20%">
			<col width="100">
			<col width="20%">
			<col width="100">
			<col width="10%">
			<col width="100">
			<col width="20%">
			<col width="100">
			<col width="10%">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text" id="searchSalesNm" name="searchSalesNm" style="width: 70%;" onclick="sales_clear()">
						<input type="hidden" id="searchSalesCd" name="searchSalesCd" style="width: 20%;">
						<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">품목</label></th>
					<td>
						<input type="text"   id="searchPrdtNm"  name="searchPrdtNm" style="width:70%;" onclick="prdt_clear()">
						<input type="hidden" id="searchPrdtCd"  name="searchPrdtCd" >
						<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
					</td>
				<th><label for="sele2">수량</label></th>
					<td>
						<input type="text"   id="freeQty"  name="freeQty" style="width:80%; text-align:right;">
					</td>
				<th><label for="sele2">출고일자</label></th>
					<td>
						<input type="text" class="dt" id="searchFreeDt" name="searchFreeDt"  style="width: 100px !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/>
					</td>
				<th><label for="sele2">출고구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchFreeClass" objName="searchFreeClass" parentID="M016" />
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
			<h2 class="subhead">매출처샘플목록</h2>
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
    	<h2>샘플출고 품목 </h2>	
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
<jsp:include page="/WEB-INF/views/app/common/find_CurSampleRegPrdtMaster.jsp" />

</div>
</body>
</html>
