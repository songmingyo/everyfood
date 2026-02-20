<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">
var comEstNumCallbackFnc = null;

/* Layer Event Creat */
function setFindEstNumFindLayerEvent(){

	/* 레이어 활성화 */
	$('#find_estnum_find_layer').show();
	if($('#opacity').css("display") == "none") {
		$('#opacity').show();
		$('#opacity').attr("style", "height: 190%;");
	}

	/* 레이어 드레그 Event */
	$("#find_estnum_find_layer").draggable({
		handle: $("h1")
		,cancle: $("a.close")
		,containment: "window"
		,scroll: false
	});
	
	document.getElementById('find_estNum').focus();

	/* 레이어 닫기버튼 Click Event */
	$('#find_estnum_find_layer a.close').click(function(e){
		closeFindEstNumLayer();
	});

	$('#find_estnum_find_layer #btnSearch_find_estNum').unbind().click(null, eventSearchEstNum); 	// 조회버튼

	//조건 입력필드 enter key이벤트 --------------
	$('#find_estNum').unbind().keydown(function(e) {

		switch(e.which) {
    	case 13 : eventSearchEstNum(this); break; // enter
    	default : return true;
    	}
    	e.preventDefault();
   	});

	initEstNum();
	
	$('#find_estnum_dataListbody').bind('resize', function() {
	    try{
	        // width
	        // 그리드의 width를 div 에 맞춰서 적용
        	$('#find_estnum_dataListbody').setGridHeight(300);
	        $('#find_estnum_dataListbody').setGridWidth($('#find_estnum_find_layer #gridcontainer').width()); //Resized to new width as per window
	    }catch(e){}
	}).trigger('resize');
}
function initEstNum() {

	$("#find_estnum_dataListbody").jqGrid({
        datatype: 'local',
        data: [],

     // 헤더에 들어가는 이름
        colNames:[
			 '견적번호'
			,'매출처명'
	    ],
	
	    colModel:[
	         {name:'estNum'		   	, index:'estNum'		, sortable:true		, width:100, 	align:"center"	}	/*본사구분명*/
	        ,{name:'salesNm'   		, index:'salesNm'		, sortable:true		, width:150, 	align:"left"	}	/*매출처명*/
            		
        ],
        gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
        	 var colCount = $(this).getGridParam("colNames").length;
             $("#popBlankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
        },
        loadComplete: function() {
        	if ($(this).getGridParam("records")==0) {
                var firstColName = $(this).getGridParam("colModel")[1].name;
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                $(this).addRowData("popBlankRow", msg);
            }
            
            $("#find_estnum_dataListbody").trigger('resize');
            
        	 // 조회데이터가 한건일 결우 첫번째 로우 onSelectRow  처리  --
            if ($(this).getGridParam("records")==1) {
	   			var rowIds   = $('#find_estnum_dataListbody').jqGrid('getDataIDs');
	   			$('#find_estnum_dataListbody').jqGrid("setSelection", rowIds[0]);
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
            
        	if(comEstNumCallbackFnc) comEstNumCallbackFnc(rowdata);
            closeFindEstNumLayer();
        },
        //onCellSelect : function(rowid, iCell, content){	// 셀 선택시 이벤트 (한번클릭)		// iCell : 선택열번호	content : 선택셀의 값
        ondblClickRow: function(rowid, iRow, iCell, e) { // row 더블클릭
        	var rowdata = $(this).getRowData(rowid);	// 선택한 행의 데이터를 가져온다
        }

        ,rowNum:50
        ,loadui : "disable"
        ,gridview:    true
        ,pager: '#pageList4'
        ,sortname: 'estNum'
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
function closeFindEstNumLayer(){

	$("#find_estnum_dataListbody").clearGridData();
	$('#find_estnum_find_layer').hide();
	comEstNumCallbackFnc = null;

	if($(".pop_layer_new:visible").length == 0) {
		$('#opacity').hide();
	}
}

/* 레이어호출 */
function commonEstNumFindLayer(affiliate,hedoYn,searchVal,callbackFnc){

	/*조건 초기화 -----------------*/
	$('#find_estnum_find_form').find('input').map(function() {
		$(this).val('');
	});

	//setTbodyInit("find_estnum_dataListbody");	// dataList 초기화
	/*----------------------------*/

	setFindEstNumFindLayerEvent();

	if(searchVal) {
		$("#find_estNum").val(searchVal);
		eventSearchEstNum();
	}

	$("#find_estNum").focus();
	comEstNumCallbackFnc = callbackFnc;
}

function eventSearchEstNum() {

	var searchInfo = {};
	$('#find_estnum_find_form').find('input').map(function() {
		searchInfo[this.name] = $(this).val();
	});

	$("#find_estnum_dataListbody").jqGrid('setGridParam',{
        url:"<c:url value='/app/common/findEstNum' />"	
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

<div class="pop_layer_new" id="find_estnum_find_layer"
	style="margin:-220px 0 0 -228px;width:900px; display:none;">
    <h1>견적서 찾기</h1> 
	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
         	<div class="tit_area">
                <h2>견적번호 선택 </h2>
            </div>
            <form id="find_estnum_find_form" name="find_estnum_find_form" action="" method="post" autocomplete="off">
            	<table class="type1">
					<colgroup>
						<col width="150"/>
						<col width="*"/>
					</colgroup>
					<tbody>
						<tr>
							<th>견적번호</th>
							<td>
								<input type="text" id="find_estNum" name="find_estNum" />
							</td>
							<th>매출처명</th>
							<td>
								<input type="text" id="find_salesNm" name="find_salesNm" />
							</td>
						</tr>
					</tbody>
				</table>
            </form>
	       </div>

	       <div id="pop_section">
				<div class="tit_area">
					<h2>견적서 현황</h2> <%--매출처 현황 --%>
					<div>
						<html:button id="btnSearch_find_estNum" auth="select"  /> <%--조회 --%>
					</div>
	            </div>
	            <!-- centent List -------------------------->
            	<div id="gridcontainer">
                	<table id="find_estnum_dataListbody" ><tr><td></td></tr></table>
            	</div>
            	<div id="pageList4"></div>
            	<!-- centent List -------------------------->
		</div>
	</div>
</div>

