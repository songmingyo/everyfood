<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">
var comSalesPrMstCallbackFnc = null;


/* Layer Event Creat */
function setFindSalesPrMstFindLayerEvent(){

	/* 레이어 활성화 */
	$('#find_salesPrMst_find_layer').show();
	if($('#opacity').css("display") == "none") {
		$('#opacity').show();
		$('#opacity').attr("style", "height: 190%;");
	}

	/* 레이어 드레그 Event */
	$("#find_salesPrMst_find_layer").draggable({
		handle: $("h1")
		,cancle: $("a.close")
		,containment: "window"
		,scroll: false
	});
	
	document.getElementById('find_salesPrNm').focus();

	/* 레이어 닫기버튼 Click Event */
	$('#find_salesPrMst_find_layer a.close').click(function(e){
		closeFindSalesPrLayer();
	});

	$('#find_salesPrMst_find_layer #btnSalesPrSearch_find').unbind().click(null, eventSalesPrSearchFind); 	// 조회버튼
		
	//조건 입력필드 enter key이벤트 --------------
// 	$('#find_salesPrNm').unbind().keydown(function(e) {

// 		switch(e.which) {
//     	case 13 : eventSalesPrSearchFind(this); break; // enter
//     	default : return true;
//     	}
//     	e.preventDefault();
//    	});
	
	initSalesPrMst();
	
	$('#find_salesPrMst_dataListbody').bind('resize', function() {
	    try{
	        // width
	        // 그리드의 width를 div 에 맞춰서 적용
        	$('#find_salesPrMst_dataListbody').setGridHeight(300);
	        $('#find_salesPrMst_dataListbody').setGridWidth($('#find_salesPrMst_find_layer #gridcontainer').width()); //Resized to new width as per window
	    }catch(e){}
	}).trigger('resize');
}

function initSalesPrMst() {

	$("#find_salesPrMst_dataListbody").jqGrid({
        datatype: 'local'
       ,data: []

     // 헤더에 들어가는 이름
       ,colNames:[ '영업사원코드'
            	, '영업사원명'
            	, '직급'
            	, '휴대폰번호'
         ]
		,colModel:[
				 {name:'salesPrCd'	, index:'salesPrCd'	, sortable:false,	width:40  , align:"center"}		<%-- 엉업사원코드  		--%>
		        ,{name:'salesPrNm'	, index:'salesPrNm'	, sortable:true, 	width:60  , align:"left"}		<%-- 영업사원명 		--%>
		        ,{name:'position'	, index:'position'	, sortable:true, 	width:50  , align:"center"}		<%-- 직급 		--%>
		        ,{name:'mtelNo'		, index:'mtelNo'	, sortable:true, 	width:50  , align:"left"}		<%-- 휴대폰번호 		--%>
		]
        ,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
        	 var colCount = $(this).getGridParam("colNames").length;
             $("#popBlankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
             
			 ids = $(this).jqGrid("getDataIDs");
             
             if(ids && ids.length > 0){
            	 $(this).jqGrid("setSelection", ids[0], false);
             }
             
            // 키 이벤트 처리
 			$("#find_salesPrMst_find_layer").unbind('keydown').keydown(function (e) {
 				     
 			    var grid = $("#find_salesPrMst_dataListbody");
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
 			                grid.jqGrid('setSelection', prevRowId, false);
 			                scrollToVisibleRow(grid, prevRowId);
 			            }
 			            break;
 			        case 40: // 아래쪽 화살표 키
 			            nextRowId = rowIds[currentIndex + 1];
 			            if (nextRowId) {
 			                e.preventDefault();
 			                grid.jqGrid('setSelection', nextRowId, false);
 			                scrollToVisibleRow(grid, nextRowId);
 			            }
 			            break;
 			        case 13: // 엔터키
			        	e.preventDefault();
				        
			        	thisRowId = rowIds[currentIndex];
			        	
 			        	var rowdata = grid.getRowData(thisRowId);
 	  		            if(!rowdata) return;

 	  		            if(comSalesPrMstCallbackFnc) comSalesPrMstCallbackFnc(rowdata);
 	  	                closeFindSalesPrLayer();
 	  		            
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
        }
        ,loadComplete: function() {
            if ($(this).getGridParam("records")==0) {
                var firstColName = $(this).getGridParam("colModel")[1].name;
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                $(this).addRowData("popBlankRow", msg);
            }
            $("#find_salesPrMst_dataListbody").trigger('resize');


            // 조회데이터가 한건일 결우 첫번째 로우 onSelectRow  처리  --
             if ($(this).getGridParam("records")==1) {
    			var rowIds   = $('#find_salesPrMst_dataListbody').jqGrid('getDataIDs');
    			$('#find_salesPrMst_dataListbody').jqGrid("setSelection", rowIds[0]);
    		 }
    	}
        ,loadError:function(xhr, status, error) {
        	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
            return false;
        }
        ,onSelectRow : function(id, status, e) {                                  
        	if (id == 'popBlankRow') return;

            var rowdata = $(this).getRowData(id);
            if(!rowdata) return;
            
        	if(comSalesPrMstCallbackFnc) comSalesPrMstCallbackFnc(rowdata);
            closeFindSalesPrLayer();
        }
        //onCellSelect : function(rowid, iCell, content){	// 셀 선택시 이벤트 (한번클릭)		// iCell : 선택열번호	content : 선택셀의 값
        ,ondblClickRow: function(rowid, iRow, iCell, e) { // row 더블클릭
        	var rowdata = $(this).getRowData(rowid);	// 선택한 행의 데이터를 가져온다
        }

        ,rowNum:50
        ,loadui : "disable"
        ,gridview:    true
        ,pager: '#pageList2'
        ,sortname: 'salesNm'
        ,sortorder: 'asc'
        ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
        ,viewrecords: true
        ,scroll : false
        ,rownumbers:true
        ,loadonce:true
        ,autowidth:true

    });
}
	
/*레이어 close*/
function closeFindSalesPrLayer(){

	$("#find_salesPrMst_dataListbody").clearGridData();
	$('#find_salesPrMst_find_layer').hide();
	comSalesPrMstCallbackFnc = null;

	if($(".pop_layer_new:visible").length == 0) {
		$('#opacity').hide();
	}
}



/* 레이어호출 */
function commonSalesPrMstFindLayer(affiliate,hedoYn,searchVals,callbackFnc){

	/*조건 초기화 -----------------*/
	$('#find_salesPrMst_find_form').find('input').map(function() {
		$(this).val('');
	});

	//setTbodyInit("find_salesPrMst_dataListbody");	// dataList 초기화
	/*----------------------------*/

	setFindSalesPrMstFindLayerEvent();

	if(searchVals) {
		$('#find_salesPrNm').val(searchVals);
		eventSalesPrSearchFind();
	}

	$("#find_salesPrNm").focus();
	
	comSalesPrMstCallbackFnc = callbackFnc;
}

function eventSalesPrSearchFind() {

	var searchInfo = {};
	$('#find_salesPrMst_find_form').find('input').map(function() {
		searchInfo[this.name] = $(this).val();
	});

	$("#find_salesPrMst_dataListbody").jqGrid('setGridParam',{
        url:"<c:url value='/app/common/findSalesPrMaster' />"	
       ,datatype: "json" 
       ,postData: searchInfo
       ,mtype:'POST'
       ,ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' }  //ajax contentType 설정
       ,page: 1
       ,loadBeforeSend: function(jqXHR) {
        		jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
        }
	   ,jsonReader : {
		   root:  "resultList",	//조회결과 데이터
			page: "page",			//현재 페이지	
			total: "pagePerCount",	//총 페이지 수
			records: "totalCount",	// 전체 조회된 데이터 총갯수 
			repeatitems: false
        }
 	}).trigger("reloadGrid");
}


</script>

<div class="pop_layer_new" id="find_salesPrMst_find_layer" style="margin:-220px 0 0 -228px;width:900px; display:none;">
    <h1>영업사원 찾기</h1> 
	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
         	<div class="tit_area">
                <h2>영업사원 선택 </h2>
            </div>
            <form id="find_salesPrMst_find_form" name="find_salesPrMst_find_form" action="" method="post" autocomplete="off">
            	<table class="type1">
					<colgroup>
						<col width="100"/>
						<col width="*"/>
					</colgroup>
					<tbody>
						<tr>
							<th>영업사원명</th>
							<td><input type="text" id="find_salesPrNm" name="find_salesPrNm" style="width: 90%;" /></td>
						</tr> 
					</tbody>
				</table> 
            </form>
	       </div>

	       <div id="pop_section">
				<div class="tit_area">
					<h2>영업사원 현황</h2> 
					<div>
						<html:button id="btnSalesPrSearch_find" auth="select"  /> <%--조회 --%>
					</div>
	            </div>
	            <!-- centent List -------------------------->
            	<div id="gridcontainer" class="gridcontainer">
                	<table id="find_salesPrMst_dataListbody" ><tr><td></td></tr></table>
            	</div>
            	<div id="pageList2"></div>
            	<!-- centent List -------------------------->
		</div>
	</div>
</div>

