<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

var sr = /[^0-9]/g;	// 숫자만 표현 

$(document).ready(function(){
	
	$("#wdrlDt").datepicker();
	$("#promDt").datepicker();
	$("#promExpDt").datepicker();
	
	initAccBuyWdrlRegGrid();
	initAccBuyWdrlRegDetailGrid();
	
	/* BUTTON CLICK 이벤트 처리 --------------------------------------------*/
	$('#btnSearch').unbind().click(null,	    searchEvent); 	// 검색
	$('#btnSave').unbind().click(null,	    	saveEvent); 	// 저장
	$('#btnNew').unbind().click(null,	    	newEvent); 	    // 신규
	
	$('#btnBuy_find').unbind().click(function(){ findBuyMst()}); 	// 매입처찾기 팝업 버튼이벤트

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
				$('#container1List').setGridHeight(220);

				if(height-320 < 120)
					$('#container2List').setGridHeight(320);
				else
					$('#container2List').setGridHeight(height-350);		
			}
        }catch(e){}
    }).trigger('resize');
	/*-------------------------------------------------------------------*/


	/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/
	$('#buyNm').unbind().keydown(function(e) {
		switch(e.which) {
    		case 13 : findBuyMst(); break; // enter
    		default : return true;
    	}
    	e.preventDefault();
   	});
	
	$('#setlAmt').unbind().keydown(function(e) {
		switch(e.which) {
			case 13 : remarksMv(); break; // enter
    		default : return true;
    	}
    	e.preventDefault();
   	});
	
	$('#remarks').unbind().keydown(function(e) {
		switch(e.which) {
    		case 13 : dataAddEvent(); break; // enter
    		default : return true;
    	}
    	e.preventDefault();
   	});
	/*-----------------------------------------------------------------*/

	
});


/* 마스터 데이터  그리드 초기화 */
function initAccBuyWdrlRegGrid() {
	$("#container1List").jqGrid({
		 datatype: "local"  // 보내는 데이터 타입
		,data: []
		,colNames:[   '입금일자'
        			, '매입처코드'
        			, '매입처명'
        			, '입금구분'
        			, '입금금액'
        			
        			, '입금구분코드'
     	        ]
		,colModel:[
	                 {name:'wdrlDt'			, index:'wdrlDt'			, sortable:true, width:50 , align:"center" }
	                ,{name:'buyCd'		, index:'buyCd'		, sortable:true, width:30 , align:"center"}
	                ,{name:'buyNm'		, index:'buyNm'		, sortable:true, width:100 , align:"left"}
	                ,{name:'setlClassNm'	, index:'setlClassNm'	, sortable:true, width:50 , align:"center"}
	                ,{name:'setlAmt'		, index:'setlAmt'		, sortable:true, width:50 , align:"right", formatter:'integer'}
	                
	                ,{name:'setlClass'		, index:'setlClass'		, sortable:true, width:50 , align:"center", hidden:true}
		        
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
		,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
			//alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
			return false;
        }
        ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

        	if (id == 'blankRow') return;
       		var rowdata = $(this).getRowData(id);

       		cusShipRegDetail(rowdata);
        }
        ,rowNum		:-1
        ,loadui: 	"disable"              
        ,gridview:  true                 
        //,sortname: 'groupNm'
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
function initAccBuyWdrlRegDetailGrid() {
    $("#container2List").jqGrid({
         datatype: 'local'
        ,data: []
        ,colNames:[   '지급일자'
                    , '매입처명'
                    , '지급구분'
                    , '지급금액'
                    , '어음발행일'
                    , '어음만기일'
                    , '적요'
                    , '등록일자'
                    , '등록자'
                    , '수정자'
                    
                    , '매입처코드'
                    , '지급구분코드'
                    , '그리드상태'
         ]
    
        ,colModel:[
             {name:'wdrlDt'    	  , index:'wdrlDt'		, sortable:false  , width:80    , align:"center"}
        	,{name:'buyNm' 	  	  , index:'buyNm'		, sortable:false  , width:150 	, align:"left"}
        	,{name:'setlClassNm'  , index:'setlClassNm'	, sortable:false  , width:80 	, align:"center"}
        	,{name:'setlAmt' 	  , index:'setlAmt'		, sortable:false  , width:100   , editable:true, align:"right", formatter:'integer'}
        	,{name:'promDt' 	  , index:'promDt'		, sortable:false  , width:120 	, align:"center"}
        	,{name:'promExpDt' 	  , index:'promExpDt'	, sortable:false  , width:120 	, align:"center"}
        	,{name:'remarks' 	  , index:'remarks'		, sortable:false  , width:200 	, editable:true, align:"left"}
        	,{name:'regDt' 		  , index:'regDt'		, sortable:false  , width:110 	, align:"center"}
     		,{name:'regNm' 		  , index:'regNm'		, sortable:false  , width:80 	, align:"center"}
     		,{name:'modNm' 		  , index:'modNm'		, sortable:false  , width:80 	, align:"center"}
     		
     		,{name:'buyCd' 	    , index:'buyCd'		    , sortable:false  , width:150 	, align:"left", hidden:true}
     		,{name:'setlClass'  , index:'setlClass'  	, sortable:false  , width:80 	, align:"center", hidden:true}
     		,{name:'gridFlag'   , index:'gridFlag'		, sortable:false  , width:80 	, align:"center", hidden:true}
     	
        ]
       ,gridComplete : function() {
        	var colCount = $(this).getGridParam("colNames").length;
            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
            
            var gridData = $("#container2List");
			var allRows = gridData.jqGrid('getDataIDs');
			
			// 조회후 데이터가 한건이라도 있을경우 
			if(allRows.length > 0 && $("#blankRow").val() != ""){
				$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
			}
			
			// ===================== Footer Sum
			gridData.jqGrid('footerData', 'set', { 'setlClassNm' : '합계' });
			
            var sum_setlAmt = gridData.jqGrid('getCol','setlAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'setlAmt':sum_setlAmt });
	    	
       }
       ,loadComplete: function() {
            if ($(this).getGridParam("records")==0) {
                var firstColName = $(this).getGridParam("colModel")[1].name;
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();
                $(this).addRowData("blankRow", msg);
            }
            
            $(window).resize();
        }

       ,loadError:function(xhr, status, error) {
            alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
            message.error.process
            return false;
        }

		,rowNum			:-1
        ,loadui 		: "disable"
        ,gridview		: true
        ,sortname		: 'buyCd'
        ,sortorder		: 'asc'
        ,emptyrecords 	: '<spring:message code="message.search.no.data" />'     <%-- 조회결과가 없습니다.--%>
        ,viewrecords	: true
        ,cellEdit       : true
        ,scroll 		: true
        ,rownumbers		: true
        ,footerrow      : true
        ,loadonce		: true
    });
}


/*매입처 찾기팝업 호출 */
function findBuyMst(){
	commonBuyMstFindLayer('','',$("#buyNm").val(), setBuyMstFindLayer);
}

/*매입처 콜백(후처리 함수 )*/
function setBuyMstFindLayer(data) {

	$("#buyCd").val(data.buyCd);
	$("#buyNm").val(data.buyNm);
	
	$("#setlAmt").focus();
	
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



/* Data 조회 */
function searchEvent(event){
	var searchInfo = {};
	
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.id] = $(this).val().replaceAll('-','')
	});

	//Detail Grid 초기화
	$("#container2List").jqGrid('clearGridData');
	
	$("#container1List").jqGrid('setGridParam',{
		url:"<c:url value='/app/as/account/accBuyWdrlReg_selList.json'/>",
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

  	$("#container2List").jqGrid('setGridParam',{
        url:'<c:url value="/app/as/account/accBuyWdrlReg_selDetailList.json" />', 		
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


/*저장 버튼 이벤트 */
function saveEvent(){

	$("#container2List").editCell(0, 0, false);

	if(!confirm("저장하시겠습니까?")){return;}
	
	var saveData = {'accBuyWdrlRegList'  : $("#container2List").getRowData()
	               };

	$.ajax({
	      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	      url  : '<c:url value="/app/as/account/accBuyWdrlReg_insInfo.json" />',
	      data : JSON.stringify(saveData),
	      success : function(data){            

	    	  if(data.msgCd =="0") {
					alert("신규 : "+ data.rtnValue01+"건  |  수정 : "+data.rtnValue02+"건이 처리 정상 되었습니다.");
					
					setAmt_clear(); remarks_clear();
					
					searchEvent();			//  재조회
			   	}else{
			   		alert("처리중 오류가 발생하였습니다.\n"+data.message)
					searchEvent();
				}
	      }
	});
}
	

/* 적요 엔터시 입력된 데이터 반영 */
function dataAddEvent(){
	
	var ids = $("#container2List").jqGrid('getDataIDs');

    var rowData = $("#container2List").jqGrid('getRowData', ids[0]);

    if($("#buyCd").val() == ''){
    	alert('매입처를 선택하세요.');
    	$("#buyNm").focus();    	
    	
    	return;
    }
    
    if(rowData.buyNm == ''){
    	$("#container2List").jqGrid('clearGridData');
    }
    
	$("#container2List").jqGrid('addRow',{
		initdata : {
			'wdrlDt' : $("#wdrlDt").val()
			, 'buyNm' :$("#buyNm").val()
			, 'setlClassNm' : $("#setlClass option:selected").text()
			, 'setlAmt' : $("#setlAmt").val()
			, 'promDt' : $("#promDt").val()
			, 'promExpDt' : $("#promExpDt").val()
			, 'remarks' : $("#remarks").val()
			
			, 'gridFlag' : "I"
			, 'buyCd' :$("#buyCd").val()
			, 'setlClass' : $("#setlClass option:selected").val()
		},
		position : "first",
		useDefValues : false,
		useFormatter : true
	});
	
	buy_clear();
	setAmt_clear();
	remarks_clear();
	
	$("#buyNm").focus();
}

function remarksMv(){
	$("#remarks").focus();
}

function buy_clear(){
	$("#buyNm").val('');
	$("#buyCd").val('');
}

function setAmt_clear(){
	$("#setlAmt").val('');
}

function remarks_clear(){
	$("#remarks").val('');
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
	<legend>매입처지급등록</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매입처지급등록 검색조건</caption>
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
				<th><label for="sele2">지급일자</label></th>
					<td>
						<input type="text" class="dt" id="wdrlDt" name="wdrlDt" style="width: 40% !important;" maxlength="8" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"  />
					</td>
				<th><label for="sele2">매입처명</label></th>
					<td>
						<input type="text" id="buyNm" name="buyNm" style="width: 80%;" onclick="buy_clear()" autocomplete="off">
						<input type="hidden" id="buyCd" name="buyCd" style="width: 20%;">
						<button id="btnBuy_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">지급구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="setlClass" objName="setlClass" parentID="M014" /></td>
					</td>
				<th><label for="sele2">지급금액</label></th>
					<td>
						<input type="text" id="setlAmt" name="setlAmt" style="width: 60%;" onclick="setAmt_clear()" autocomplete="off">
					</td>
			</tr>
			<tr>
				<th><label for="sele2">어음발행일</label></th>
					<td>
						<input type="text" class="dt" id="promDt" name="promDt" style="width: 40% !important;"  />
					</td>
				<th><label for="sele2">어음만기일</label></th>
					<td colspan=5>
						<input type="text" class="dt" id="promExpDt" name="promExpDt" style="width: 10% !important;"/>
					</td>
			</tr>
			<th><label for="sele2">적요</label></th>
				<td  colspan=7>
					<input type="text" id="remarks" name="remarks" onclick="remarks_clear()" autocomplete="off">
				
				</td>
			<tr>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- CONTENT- BODY  ----------------------------------- -->

	<!-- 전체 서브타이틀 및 이벤트 버튼 start  -------------------->
	<div class="tit_area">
		<h2>지급등록전체</h2>
		<div>
			<html:button id="btnNew" 	auth="insert"  	/> 		<%-- 신규 --%>  
			<html:button id="btnSearch" auth="select" 	/> 		<%-- 조회 --%>
			<html:button id="btnSave" auth="save"  		/> 		<%-- 저장 --%>
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
    	<h2>지급상세현황 </h2>	
		<div >
		
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

<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />

</div>
</body>
</html>
