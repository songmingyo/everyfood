<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">
var comDlvrMstCallbackFnc = null;


/* Layer Event Creat */
function setFindDlvrMstFindLayerEvent(){

	/* 레이어 활성화 */
	$('#find_DlvrMst_find_layer').show();
	if($('#opacity').css("display") == "none") {
		$('#opacity').show();
		$('#opacity').attr("style", "height: 190%;");
	}

	/* 레이어 드레그 Event */
	$("#find_DlvrMst_find_layer").draggable({
		handle: $("h1")
		,cancle: $("a.close")
		,containment: "window"
		,scroll: false
	});
	
	/* 레이어 닫기버튼 Click Event */
	$('#find_DlvrMst_find_layer a.close').click(function(e){
		closeFindDlvrLayer();
	});

	$('#find_DlvrMst_find_layer #btnDlvrSearch_find').unbind().click(null, eventDlvrSearchFind); 	// 조회버튼
		
	//조건 입력필드 enter key이벤트 --------------
	$('#find_carNm, #find_carNum, #find_drvSnm, #find_salesNm').unbind().keydown(function(e) {
		switch(e.which) {
    	case 13 : eventDlvrSearchFind(this); break; // enter
    	default : return true;
    	}
    	e.preventDefault();
   	});

	initDlvrMst();
	$('#find_DlvrMst_dataListbody').bind('resize', function() {
	    try{
	        // width
	        // 그리드의 width를 div 에 맞춰서 적용
        	$('#find_DlvrMst_dataListbody').setGridHeight(300);
	        $('#find_DlvrMst_dataListbody').setGridWidth($('#find_DlvrMst_find_layer #gridcontainer').width()); //Resized to new width as per window
	    }catch(e){}
	}).trigger('resize');
}
function initDlvrMst() {

	$("#find_DlvrMst_dataListbody").jqGrid({
        datatype: 'local'
       ,data: []

     // 헤더에 들어가는 이름
       ,colNames:[ '코드'
            	, '차량명'
            	, '차량번호'
            	, '차량유형'
            	, '기사명'
            	, '연락처'
            	, '매출처'
            	, '매출처코드'
         ]
		,colModel:[
				 {name:'carCd'			, index:'carCd'		, sortable:false,	width:40  , align:"center"}		<%-- 차량코드 		--%>
		        ,{name:'carNm'			, index:'carNm'		, sortable:true, 	width:60  , align:"left"}		<%-- 차량명 		--%>
		        ,{name:'carNum'			, index:'carNum'	, sortable:true, 	width:50  , align:"center"}		<%-- 차량번호 		--%>
		        ,{name:'carType'		, index:'carType'	, sortable:true, 	width:50  , align:"left"}		<%-- 차량유형 		--%>
		        ,{name:'drvSnm'			, index:'drvSnm'	, sortable:false, 	width:50  , align:"center"}		<%-- 기사명 		--%>
		        ,{name:'mtelNo'			, index:'mtelNo'	, sortable:false, 	width:50  , align:"center"}		<%-- 연락처 		--%>
		        ,{name:'salesNm'		, index:'salesNm'	, sortable:true, 	width:100 , align:"left"}		<%-- 매출처명 		--%>
		        ,{name:'salesCd'		, index:'salesCd'	, hidden:true}										<%-- 매출처코드 	--%>
		        
		]
        ,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
        	 var colCount = $(this).getGridParam("colNames").length;
             $("#popBlankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
        }
        ,loadComplete: function() {
            if ($(this).getGridParam("records")==0) {
                var firstColName = $(this).getGridParam("colModel")[1].name;
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                $(this).addRowData("popBlankRow", msg);
            }
            $("#find_DlvrMst_dataListbody").trigger('resize');


            // 조회데이터가 한건일 결우 첫번째 로우 onSelectRow  처리  --
             if ($(this).getGridParam("records")==1) {
    			var rowIds   = $('#find_DlvrMst_dataListbody').jqGrid('getDataIDs');
    			$('#find_DlvrMst_dataListbody').jqGrid("setSelection", rowIds[0]);
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
            
        	if(comDlvrMstCallbackFnc) comDlvrMstCallbackFnc(rowdata);
            closeFindDlvrLayer();
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
function closeFindDlvrLayer(){

	$("#find_DlvrMst_dataListbody").clearGridData();
	$('#find_DlvrMst_find_layer').hide();
	comDlvrMstCallbackFnc = null;

	if($(".pop_layer_new:visible").length == 0) {
		$('#opacity').hide();
	}
}




/* 레이어호출 */
function commonDlvrMstFindLayer(affiliate,hedoYn,searchVals,callbackFnc){

	/*조건 초기화 -----------------*/
	$('#find_DlvrMst_find_form').find('input').map(function() {
		$(this).val('');
	});

	//setTbodyInit("find_DlvrMst_dataListbody");	// dataList 초기화
	/*----------------------------*/

	setFindDlvrMstFindLayerEvent();

	if(searchVals) {
		setBindElement($('#find_DlvrMst_find_form'), searchVals);
		eventDlvrSearchFind();
	}

	$("#find_carNm").focus();
	comDlvrMstCallbackFnc = callbackFnc;
}

function eventDlvrSearchFind() {
	
// 	if(!$('#find_carNm').val() && !$('#find_carNum').val() && !$('#find_drvSnm').val() && !$('#find_salesNm').val() ) {
<%-- 		alert('<spring:message code="app.common.ComCompanyLayer._eventSearchFind.a1" />');조회조건을 하나이상 입력하세요! --%>
// 		$('#find_carNm').focus();
// 		return;
// 	}

	var searchInfo = {};
	$('#find_DlvrMst_find_form').find('input').map(function() {
		searchInfo[this.name] = $(this).val();
	});

	$("#find_DlvrMst_dataListbody").jqGrid('setGridParam',{
        url:"<c:url value='/app/common/findDlvrMaster' />"	
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




/*commonUtil이동예정 */
function setBindElement(form, data){
$.each(data, function(i, el) {

  var 
      fel = form.find('*[name="' + i + '"]'),
      type = "", tag = "";

   if (fel.length > 0) {

       tag = fel[0].tagName.toLowerCase();

       if (tag == "select" || tag == "textarea") { //...
          $(fel).val(el);
       }
       else if (tag == "input") {
          type = $(fel[0]).attr("type");
           if (type == "text" || type == "password" || type == "hidden") {
              fel.val(el);
           } 
           else if (type == "checkbox") {
              if (el)  
                 fel.attr("checked", "checked"); 
           }
           else if (type == "radio") {
               fel.filter('[value="'+el+'"]').attr("checked", "checked"); 
           }
       }
   }
})
}
</script>

<div class="pop_layer_new" id="find_DlvrMst_find_layer"
	style="margin:-220px 0 0 -228px;width:900px; display:none;">
    <h1>거래처 찾기</h1> 
	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
         	<div class="tit_area">
                <h2>매입처 선택 </h2>
            </div>
            <form id="find_DlvrMst_find_form" name="find_DlvrMst_find_form" action="" method="post" autocomplete="off">
            	<table class="type1">
					<colgroup>
						<col width="80"/>
						<col width="120"/>
						<col width="80"/>
						<col width="100"/>
						<col width="80"/>
						<col width="100"/>
						<col width="80"/>
						<col width="*"/>
					</colgroup>
					<tbody>
						<tr>
							<th>호차</th>
							<td><input type="text" id="find_carNm" name="find_carNm" style="width: 90%;" /></td>
							<th>차량번호</th>
							<td><input type="text" id="find_bsnsNum" name="find_carNum" style="width: 90%;" /></td>
							<th>기사명</th>
							<td><input type="text" id="find_drvSnm" name="find_drvSnm" style="width: 90%;" /></td>
							<th>매출처명</th>
							<td><input type="text" id="find_salesNm" name="find_salesNm" style="width: 95%;" />
								<input type="hidden" id="find_salesCd" name="find_salesCd" />
							</td>
							
						</tr> 
					</tbody>
				</table> 
            </form>
	       </div>

	       <div id="pop_section">
				<div class="tit_area">
					<h2>배송차량 현황</h2> 
					<div>
						<html:button id="btnDlvrSearch_find" auth="select"  /> <%--조회 --%>
					</div>
	            </div>
	            <!-- centent List -------------------------->
            	<div id="gridcontainer" class="gridcontainer">
                	<table id="find_DlvrMst_dataListbody" ><tr><td></td></tr></table>
            	</div>
            	<div id="pageList2"></div>
            	<!-- centent List -------------------------->
		</div>
	</div>
</div>

