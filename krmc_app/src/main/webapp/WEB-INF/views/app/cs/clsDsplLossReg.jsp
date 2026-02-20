<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">
$(document).ready(function(){

	$("#searchDspDt").datepicker();
	
	initGrid();
	initDetailGrid();
	
	/* BUTTON CLICK 이벤트 처리 --------------------------------------------*/
	$('#btnNew').unbind().click(null,	    	newEvent); 	    // 신규
	$('#btnSearch').unbind().click(null,		searchEvent); 	// 검색
	$('#btnSave').unbind().click(null,	    	saveEvent); 	// 저장
	
	$('#btnDspPrdtList').unbind().click(null,	salesPrdtListEvent); // 대상품목조회

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
				$('#dataList').setGridHeight(220);

				if(height-320 < 120)
					$('#dataDetailList').setGridHeight(320);
				else
					$('#dataDetailList').setGridHeight(height-350);		
			}
        }catch(e){}
    }).trigger('resize');
	/*-------------------------------------------------------------------*/
	
	// 조회조건 입력필드 enter key이벤트 --------------
	$('#searchPrdtNm').unbind().keydown(function(e) {
		switch(e.which) {
    		case 13 : findPrdtMst(this); break; // enter
    		default : return true;
    	}
    	e.preventDefault();
   	});
	$('#searchDspQty').unbind().keydown(function(e) {
		switch(e.which) {
    		case 13 : prdtDspAdd(this); break; // enter
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
        			, '입수수량'
        			, '원가'
        			
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

        ,rowNum: -1                   
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
        ,colNames:[   '품목코드'
                    , '품명'
                    , '규격'
                    , '단위'
                    , '구분'
                    , '수량'
                    , '단가'
                    , '금액'
                    , '창고명'
                    , '비고'
                    , '등록자'
                    , '수정자'
                    
                    , '전표번호'
                    , '전표순번'
                    
                    , '추가유무'
                    , '그리드 상태'
                    , '원 금액'
                    , '원 비고'
         ]
    
        ,colModel:[
			{name:'prdtCd'			, index:'prdtCd'		, width:100  , align:"center"}
    		,{name:'prdtNm'			, index:'prdtNm'		, width:200 , align:"left"}
            ,{name:'prdtStd'		, index:'prdtStd'		, width:120  , align:"center"}
            ,{name:'ordUnit'		, index:'ordUnit'		, width:80  , align:"center"}
            ,{name:'dspClassNm'		, index:'dspClassNm'	, width:80  , align:"center"}
            ,{name:'dspQty'			, index:'dspQty'		, width:80  , align:"right", formatter: fmtSetGridInputNumHour, unformat: unfmtSetGridInputNumHour} 
            ,{name:'cost'			, index:'cost'			, width:100  , align:"right", formatter:'integer'} 
            ,{name:'dspAmt'			, index:'dspAmt'		, width:130 , align:"right", formatter:'integer'}
            ,{name:'whNm'			, index:'whNm'			, width:100  , align:"center"}
            ,{name:'remarks'		, index:'remarks'		, width:200 , align:"left", formatter: fmtSetGridInput, unformat: unfmtGetGridInput}
            ,{name:'regNm' 			, index:'regNm'			, width:120 , align:"center"}
            ,{name:'modNm' 			, index:'modNm'			, width:120 , align:"center"}
            
            ,{name:'buySlipNo'		, index:'buySlipNo'		, hidden:true}
			,{name:'buySeq'			, index:'buySeq'		, hidden:true}
            
            ,{name:'addDataRow'		, index:'addDataRow'	, hidden:true}
            ,{name:'gridFlag'		, index:'gridFlag'		, hidden:true}
            ,{name:'dspOrgAmt'		, index:'dspOrgAmt'		, hidden:true, width:100  , align:"right", formatter:'integer'}
            ,{name:'orgRemarks'		, index:'orgRemarks'	, hidden:true, width:100  , align:"right"}
        ]
       ,gridComplete : function() {
        	var colCount = $(this).getGridParam("colNames").length;
            $("#blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
            
            ids = $("#dataDetailList").jqGrid('getDataIDs');

            if(ids.length > 0){
           	    $('#remarks').val($("#dataDetailList").jqGrid('getRowData', ids[0]).remarks);
            }
            
            $(window).resize();
       }
       ,loadComplete: function() {
           $(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
           
           if ($(this).getGridParam("records")==0) {
        	   var firstColName = $(this).getGridParam("colModel")[1].name;
               var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();
               $(this).addRowData("blankRow", msg);
           }
           
           $(this).find("input.dy").datepicker().attr("style", "width:80%");
       }
//        ,afterEditCell: function(rowid, name, val, iRow, iCol){
// 			$("#" + rowid + "_" + cellname).blur(function(){
// 		        $("#dataDetailList").jqGrid("saveCell",iRow,iCol);
// 		    });
// 	   }
// 	   , afterSaveCell: function(rowid,name,val,iRow,iCol) {
// 			var data = $("#dataDetailList").getRowData(rowid);
			
// 			var gridFlag = data.gridFlag;
	           
//             if(gridFlag == "N" ) {
// 				$("#dataDetailList").jqGrid('setCell', rowid, 'gridFlag' , 'U');
//             } else{
//             	$("#dataDetailList").jqGrid('setCell', rowid, 'gridFlag' , 'I');
//             }

//             if(name == "dspQty"){
//             	$("#dataDetailList").jqGrid('setCell', rowid, 'dspAmt' , val * data.cost);
//             }
            
//             $("#dataDetailList").saveRow(rowid, false);
           
//        }
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
        ,emptyrecords 	: '<spring:message code="message.search.no.data" />'     <%-- 조회결과가 없습니다.--%>
        //,cellEdit       : true
        ,viewrecords	: true
        ,scroll 		: true
        ,rownumbers		: true
		,autowidth      : true
		,loadonce       : false
    });
    
    jQuery("#dataDetailList").jqGrid('setFrozenColumns');
}


/* 목록에서 더블 클릭 시 상세에 행 추가 */
function setGridClickData(data){
	
	var rowId = $('#dataDetailList').jqGrid('getDataIDs');
	
	$("#dataDetailList").jqGrid('addRow',{
		initdata : {
			'prdtCd' : data.prdtCd
			, 'prdtNm' : data.prdtNm
			, 'prdtStd' : data.prdtStd
			, 'ordUnit' : data.ordUnit
			, 'dspQty' : 0
			, 'cost' : data.cost
			, 'addDataRow' : "A"
			, 'gridFlag' : "I"
		},
		position : "last",
		useDefValues : false,
		useFormatter : true
	});

}

function setGridData(){
	var ids = $("#dataDetailList").jqGrid('getDataIDs');

    for(var i=0; i<ids.length;i++){
    	var rowData = $("#dataDetailList").jqGrid('getRowData', ids[i]);
    	
		var cost = parseInt(rowData.cost);
        
        var dspAmt = Math.round(rowData.dspQty * rowData.cost);
        
       	$("#dataDetailList").jqGrid('setCell',ids[i],'dspAmt',dspAmt);
    }
}


//품목 찾기 팝업
function findPrdtMst(){
	commonPrdtMstFindLayer('', '', $("#searchPrdtNm").val(), '', setPrdtMstFindLayer);
}

/*품목 콜백(후처리 함수 )*/
function setPrdtMstFindLayer(data) {
	if (data != null){
		$("#searchPrdtCd").val(data.prdtCd);
		$("#searchPrdtNm").val(data.prdtNm);
		
		$('#searchDspQty').focus();
	}
}


 /* 신규 */
function newEvent(event){
    $('form').each(function() {
        this.reset();
    });
    
  	//Grid 초기화
    $("#dataList").jqGrid('clearGridData');
	$("#dataDetailList").jqGrid('clearGridData');
}

/* 대상품목 조회 */
function salesPrdtListEvent(event){
	
	var searchInfo = {};

	//Grid 초기화
	$("#dataList").jqGrid('clearGridData');
	
	$("#dataList").jqGrid('setGridParam',{
		url:"<c:url value='/app/cs/closing/clsDsplLossRegPrdt_selList.json'/>",
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

	var searchInfo = {};
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.id] = $(this).val().replaceAll('-','');
	});
	
	//Grid 초기화
	$("#dataDetailList").jqGrid('clearGridData');

  	$("#dataDetailList").jqGrid('setGridParam',{
        url:'<c:url value="/app/cs/closing/clsDsplLossReg_selDetailList.json" />', 		
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

/* 저장 */
function saveEvent(event){
	
	$("#dataDetailList").editCell(0, 0, false);
	
	setGridData();

	if(!confirm("저장하시겠습니까?")){return;}
	
	var saveData = {'clsDsplLossRegList'  : $("#dataDetailList").getRowData()
			        , 'dspDt'         : $('#searchDspDt').val().replaceAll('-','')
			        , 'whCd'          : $('#whCd').val()
			        , 'dspClass'      : $('#searchDspClass').val()
	               };

	$.ajax({
	    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	    url  : "<c:url value='/app/cs/closing/clsDsplLossReg_insList.json'/>",

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
function prdtDspAdd(event){
	if(!$('#searchPrdtNm').val()) {
		alert('품목을 입력하세요.');
		$('#searchPrdtNm').focus();
		return false;
	}
	if(!$('#searchDspQty').val()) {
		alert('수량을 입력하세요.');
		$('#searchDspQty').focus();
		return false;
	}

	var searchInfo = {};
	$('#searchForm').find('input , select').map(function() {
		searchInfo[this.name] = $(this).val().replaceAll('-','')
	});

	$.ajax({
		contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		url:"<c:url value='/app/cs/closing/clsDsplLossReg_selPrdtAdd.json'/>",
		data: JSON.stringify(searchInfo),
		success : function(data){
			
			//blankRow 제거
			$("#dataDetailList").jqGrid('delRowData', 'blankRow');
			
			rowId = $("#dataDetailList").getGridParam("reccount");
			
 			var dspQty = $("#searchDspQty").val();
 			var dspAmt = parseInt(dspQty) * parseInt(data.clsDsplLossRegVo.cost);

 			$("#dataDetailList").jqGrid('addRowData', rowId+1, data.clsDsplLossRegVo, 'last');
			
 			$("#dataDetailList").jqGrid('setCell',rowId+1,'dspQty', dspQty);
 			$("#dataDetailList").jqGrid('setCell',rowId+1,'dspAmt', dspAmt);
			$("#dataDetailList").jqGrid('setCell',rowId+1,'addDataRow',"A");
			$("#dataDetailList").jqGrid('setCell',rowId+1,'gridFlag',"I");
			
			
			//하나의 품목 입력 후 input 박스 초기화
			prdt_clear();
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


function prdt_clear(event){
	$('#searchPrdtNm').val('');
	$('#searchPrdtCd').val('');
	$('#searchDspQty').val('');
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
	<legend>폐기/로스 등록</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>폐기/로스 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="20%">
			<col width="100">
			<col width="20%">
			<col width="100">
			<col width="20%">
			<col width="100">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">품목</label></th>
					<td>
						<input type="text"   id="searchPrdtNm"  name="searchPrdtNm" style="width:50%;" onclick="prdt_clear()">
						<input type="hidden" id="searchPrdtCd"  name="searchPrdtCd" >
						<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
					</td>
				<th><label for="sele2">수량</label></th>
					<td>
						<input type="text"   id="searchDspQty"  name="searchDspQty" style="width:40%; text-align:right;">
					</td>
				<th><label for="sele2">일자</label></th>
					<td>
						<input type="text" class="dt" id="searchDspDt" name="searchDspDt"  readonly="readonly" style="width: 100px !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/>
					</td>
				<th><label for="sele2">구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchDspClass" objName="searchDspClass" parentID="M015" />
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
			<h2 class="subhead">폐기/로스 목록</h2>
			<div class="btn_l">
				<html:button id="btnDspPrdtList"  name="btnDspPrdtList"	auth="select" value="대상품목조회"/> 		<%-- 대상품목조회 --%>
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
    	<h2>폐기/로스 품목 </h2>	
		<div >
			<label style="margin-top:2px;">창고 </label>
				<html:codeTag comType="SELECT" dataType="PRC3" objId="whCd" objName="whCd" height="24px" parentID="Y" defValue="" />
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

<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />

</div>
</body>
</html>
