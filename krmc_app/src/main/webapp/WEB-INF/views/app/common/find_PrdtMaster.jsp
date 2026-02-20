<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">
var comPrdtMstCallbackFnc = null;

/* Layer Event Creat */
function setFindPrdtMstFindLayerEvent(){

	/* 레이어 활성화 */
	$('#find_prdtmst_find_layer').show();
	if($('#opacity').css("display") == "none") {
		$('#opacity').show();
		$('#opacity').attr("style", "height: 190%;");
	}

	/* 레이어 드레그 Event */
	$("#find_prdtmst_find_layer").draggable({
		handle: $("h1")
		,cancle: $("a.close")
		,containment: "window"
		,scroll: false
	});
	
	document.getElementById('find_prdtNm').focus();

	/* 레이어 닫기버튼 Click Event */
	$('#find_prdtmst_find_layer a.close').click(function(e){
		closeFindLayer();
	});

	$('#find_prdtmst_find_layer #btnSearch_find').unbind().click(null, eventSearchFind); 	// 조회버튼

	//조건 입력필드 enter key이벤트 --------------
// 	$('#find_prdtNm').unbind().keydown(function(e) {
// 		switch(e.which) {
//     	case 13 : eventSearchFind(this); break; // enter
//     	default : return true;
//     	}
//     	e.preventDefault();
//    	});

	initPrdtMst();
	$('#find_prdtmst_dataListbody').bind('resize', function() {
	    try{
	        // width
	        // 그리드의 width를 div 에 맞춰서 적용
        	$('#find_prdtmst_dataListbody').setGridHeight(300);
	        $('#find_prdtmst_dataListbody').setGridWidth($('#find_prdtmst_find_layer #gridcontainer').width()); //Resized to new width as per window
	    }catch(e){}
	}).trigger('resize');
}
function initPrdtMst() {

	$("#find_prdtmst_dataListbody").jqGrid({
        datatype: 'local',
        data: [],

     // 헤더에 들어가는 이름
        colNames:[
				 '품목코드'
				,'품목명'
				,'규격'
				,'단위'
				
				,'단위명'
				,'원가'
        ],

        colModel:[
             {name:'prdtCd'		   	, index:'prdtCd'		, sortable:true		, width:80, 	align:"center"	}
            ,{name:'prdtNm'		   	, index:'prdtNm'		, sortable:true		, width:120, 	align:"left"	}
            ,{name:'prdtStd'   		, index:'prdtStd'		, sortable:true		, width:80, 	align:"center"	}
            ,{name:'ordUnit'  	    , index:'ordUnit'		, sortable:true		, width:60, 	align:"center"	}
            
            ,{name:'ordUnitNm'      , index:'ordUnitNm'		, sortable:true		, width:60, 	align:"center",	hidden:true}
            ,{name:'cost'  	        , index:'cost'			, sortable:true		, width:60, 	align:"center",	hidden:true}
            		
        ],
        gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
        	var colCount = $(this).getGridParam("colNames").length;
            $("#popBlankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
             
            // 키 이벤트 처리
 			$("#find_prdtmst_find_layer").unbind('keydown').keydown(function (e) {
 				     
 			    var grid = $("#find_prdtmst_dataListbody");
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
	  		        	
			        	if(comPrdtMstCallbackFnc) comPrdtMstCallbackFnc(rowdata);
			        	
			        	closeFindLayer();
			            
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
             
        },
        loadComplete: function() {
            if ($(this).getGridParam("records")==0) {
                var firstColName = $(this).getGridParam("colModel")[1].name;
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                $(this).addRowData("popBlankRow", msg);
            }
            $("#find_prdtmst_dataListbody").trigger('resize');
            
            $("#find_prdtNm").focus();
            
         	// 조회데이터가 한건일 결우 첫번째 로우 onSelectRow  처리  --
         	
         	var rowIds   = $('#find_prdtmst_dataListbody').jqGrid('getDataIDs');
         	
            if ($(this).getGridParam("records")==1) {
	   			$('#find_prdtmst_dataListbody').jqGrid("setSelection", rowIds[0]);
   		 	}
            
            if(rowIds && rowIds.length > 1){
           		$(this).jqGrid("setSelection", rowIds[0], false);
            }
            
        },
        loadError:function(xhr, status, error) {
        	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
            return false;
        },
        onSelectRow : function(id, status, e) {                                  
        	if (id == 'popBlankRow') return;

            var rowdata = $(this).getRowData(id);
            if(!rowdata) return;

        	if(comPrdtMstCallbackFnc) comPrdtMstCallbackFnc(rowdata);
            closeFindLayer();
        },
        //onCellSelect : function(rowid, iCell, content){	// 셀 선택시 이벤트 (한번클릭)		// iCell : 선택열번호	content : 선택셀의 값
        ondblClickRow: function(rowid, iRow, iCell, e) { // row 더블클릭
        	var rowdata = $(this).getRowData(rowid);	// 선택한 행의 데이터를 가져온다
        }

        ,rowNum:1000
        ,loadui : "disable"
        ,gridview:    true
        ,pager: '#pageList3'
        ,sortname: 'prdtNm'
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
function closeFindLayer(){

	$("#find_prdtmst_dataListbody").clearGridData();
	$('#find_prdtmst_find_layer').hide();
	comPrdtMstCallbackFnc = null;

	if($(".pop_layer_new:visible").length == 0) {
		$('#opacity').hide();
	}
}

/* 레이어호출 */
function commonPrdtMstFindLayer(affiliate,hedoYn,searchCVal,searchPVal,callbackFnc){

	/*조건 초기화 -----------------*/
	$('#find_prdtmst_find_form').find('input').map(function() {
		$(this).val('');
	});

	$("#find_prdtNm").focus();
	
	//setTbodyInit("find_prdtmst_dataListbody");	// dataList 초기화
	/*----------------------------*/

	setFindPrdtMstFindLayerEvent();

	if(searchCVal) {
		$("#find_prdtNm").val(searchCVal);
	}

	if(searchPVal) {
		$("#find_buyCd").val(searchPVal);
	}

	eventSearchFind();
	
	$("#find_prdtNm").focus();
	comPrdtMstCallbackFnc = callbackFnc;
}

function eventSearchFind() {

	var searchInfo = {};
	$('#find_prdtmst_find_form').find('input, select').map(function() {
		searchInfo[this.name] = $(this).val();
	});
	
	searchInfo['useYn'] = 'Y';
	
	$("#find_prdtmst_dataListbody").jqGrid('setGridParam',{
        url:"<c:url value='/app/common/findPrdtMaster' />"	
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
<spring:message code="common.all" var="commonall" />  <%--전체  --%>

<div class="pop_layer_new" id="find_prdtmst_find_layer"
	style="margin:-220px 0 0 -228px;width:900px; display:none;">
    <h1>픔목 찾기</h1> 
	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
         	<div class="tit_area">
                <h2>품목마스터 선택 </h2>
            </div>
            <form id="find_prdtmst_find_form" name="find_prdtmst_find_form" action="" method="post" autocomplete="off">
            	<table class="type1">
					<colgroup>
						<col width="150"/>
						<col width="*"/>
<%--
						<col width="150"/>
						<col width="*"/>
 --%>						
						
					</colgroup>
					<tbody>
						<tr>
							<th>품목명</th>
							<td>
								<input type="text" id="find_prdtNm" name="find_prdtNm" style="width: 100%;" />
								<input type="hidden" id="find_buyCd" name="find_buyCd"  />
							</td>
<%--
							<th>사용여부</th>
							<td>
								<html:codeTag comType="SELECT" objId="useYn" objName="useYn" parentID="9002" defName="${commonall}" />  								
							</td>
 --%>							
							
						</tr>
					</tbody>
				</table>
            </form>
	       </div>

	       <div id="pop_section">
				<div class="tit_area">
					<h2>품목 현황</h2> <%--매출처 현황 --%>
					<div>
						<html:button id="btnSearch_find" auth="select"  /> <%--조회 --%>
					</div>
	            </div>
	            <!-- centent List -------------------------->
            	<div id="gridcontainer">
                	<table id="find_prdtmst_dataListbody" ><tr><td></td></tr></table>
            	</div>
            	<div id="pageList3"></div>
            	<!-- centent List -------------------------->
		</div>
	</div>
</div>

