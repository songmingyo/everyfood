<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #salesStartDt, #searchForm #salesEndDt").datepicker();
		
		initSalesPrdtMstGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnSave').unbind().click(null,	        saveEvent);   // 저장
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnPriceUpt').unbind().click(null,	    priceUptEvent);    // 단가일괄적용
		
		
		$("#btnSalesCd").unbind().click(function(){
			findSalesMst();
		});		<%--매출처 팝업 찾아가기--%>
		
		$("#btnPrdtCd").unbind().click(function(){
			findPrdtMst();
		});		<%--상품 팝업 찾아가기--%>
		
		
		//Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-100);	//GRID  LIST
			    } 
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-120);	//GRID  LIST
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

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
		//-----------------------------------------------------------------

	});


	/* 마스터 데이터  그리드 초기화 */
	function initSalesPrdtMstGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   
				    '출고일자'
           			, '매출처명'
           			, '품목코드'
           			, '품명'
           			, '규격'
           			, '단위'
           			, '수량'
           			, '단가'
           			, '공급가'
           			, '부가세'
           			, '합계'
           			
           			, '부가세유무'
           			, '그리드상태'
           			, '매출처코드'
           			, '전표번호'
           			, '전표순번'
           ],
			colModel:[
						{name:'salesDt'			, index:'salesDt'		, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'salesNm'		, index:'salesNm'		, sortable:true, editable:false, width:100, align:"left"}
		            	,{name:'prdtCd'			, index:'prdtCd'		, sortable:true, editable:false, width:80 , align:"center"}
		            	,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, editable:false, width:150 , align:"left"}
		            	,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, editable:false, width:80 , align:"center"}
		            	,{name:'ordUnit'		, index:'ordUnit'		, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'salesQty'		, index:'salesQty'		, sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'price'			, index:'price'			, sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'pureAmt'		, index:'pureAmt'		, sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'vatAmt'			, index:'vatAmt'		, sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'totAmt'			, index:'totAmt'		, sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	
		            	,{name:'vatYn'			, index:'vatYn'		    , hidden:true	, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'gridFlag'		, index:'gridFlag'	    , hidden:true	, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'salesCd'		, index:'salesCd'	    , hidden:true	, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'salesSlipNo'	, index:'salesSlipNo'	, hidden:true	, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'salesSeq'		, index:'salesSeq'		, hidden:true	, sortable:true, editable:false, width:50 , align:"center"}
		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            $(window).resize();
	        },
			loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	                
	                $("#btnPriceUpt").attr("disabled", true);
	            } else{
	            	$("#btnPriceUpt").attr("disabled", false);
	            }
	            
	        	$(window).resize();
	        },
			afterEditCell: function(rowid, name, val, iRow, iCol){

			},
			
			afterSaveCell: function(rowid,name,val,iRow,iCol) {
				
	        },
			loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');
				return false;
            },

            rowNum:100,
            pager: '#pageList',
			loadui : "disable",													// 이거 안 써주니 로딩 창 같은게 뜸
			gridview: true,														// 그리드 속도
			viewrecords: true,													// 하단에 1/1 또는 데이터가없습니다 추가
			rownumbers:true,													// rowNumber 표시여부
			autowidth:true,		
			loadonce : true,
			sortorder : "desc"
		});
	}
	
	
	/*매출처 찾기팝업 호출 */
	function findSalesMst() {
		commonSalesMstFindLayer('','',$("#salesNm").val(), '', setSalesMstFindLayer);
	}
	
	/*매출처 콜백(후처리 함수 )*/
	function setSalesMstFindLayer(data) {
		if (data != null){
			$("#salesCd").val(data.salesCd);
			$("#salesNm").val(data.salesNm);
			$("#hqClass").val(data.hqClass);
			$("#hqCd").val(data.hqCd);
			$('#prdtNm').focus();
		}
	}
	
	
	//품목 찾기 팝업
	function findPrdtMst(){
		commonSalesPrdtMstFindLayer('', '', $("#prdtNm").val(), $("#salesCd").val(), setSalesPrdtMstFindLayer);
	}

	/*품목 콜백(후처리 함수 )*/
	function setSalesPrdtMstFindLayer(data) {
		if (data != null){
			$("#prdtCd").val(data.prdtCd);
			$("#prdtNm").val(data.prdtNm);
		}
	}
	
	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
        
        $("#tabList").jqGrid('clearGridData');
	}

	
	/* 조회 */
	function searchEvent(event){
		var searchInfo = {};
		
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/sm/custsales/cusPriceUpt_selList.json'/>",
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
	
	/* 단가 일괄 반영 */
	function priceUptEvent(event){
		
		var allRows = $("#tabList").jqGrid('getDataIDs');		//전체 rowIds
		
		var salesPrice = parseInt($("#salesPrice").val().replaceAll(',',''));
		
		for(var i=0; i<allRows.length; i++){
			var rowId = allRows[i];
			var rowData = $("#tabList").jqGrid('getRowData', rowId);

			var vatYn = rowData.vatYn;
			var salesQty = parseInt(rowData.salesQty);
			
			var pureAmt = salesPrice * salesQty;
			var vatAmt = Math.ceil(salesPrice * salesQty * 0.1);
	        
			$("#tabList").jqGrid('setCell',rowId,'price',salesPrice);
			$("#tabList").jqGrid('setCell',rowId,'gridFlag','U');
			
			if(vatYn == "2"){
	        	$("#tabList").jqGrid('setCell',rowId,'pureAmt',pureAmt);
	        	$("#tabList").jqGrid('setCell',rowId,'vatAmt',0);
	        	$("#tabList").jqGrid('setCell',rowId,'totAmt',pureAmt);
	        } else{
	        	$("#tabList").jqGrid('setCell',rowId,'pureAmt',pureAmt);
	        	$("#tabList").jqGrid('setCell',rowId,'vatAmt',vatAmt);
	        	$("#tabList").jqGrid('setCell',rowId,'totAmt',pureAmt + vatAmt);
	        }
		}
	}
	

	/* 저장 */
	function saveEvent(event){
		
		$("#tabList").editCell(0, 0, false);

		if(!confirm("저장하시겠습니까?")){return;}

		var saveData = {'cusPriceUptList'  : $("#tabList").getRowData()};
		
		$.ajax({
		    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		    url  : "<c:url value='/app/sm/custsales/cusPriceUpt_insList.json'/>",
 
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
	
	function sales_clear(event){
		$('#salesNm').val('');
		$('#salesCd').val('');
		$('#prdtNm').val('');
		$('#prdtCd').val('');
		$('#price').val('');
		$("#hqClass").val('');
	}
	
	function prdt_clear(event){
		$('#prdtNm').val('');
		$('#prdtCd').val('');
		$('#price').val('');
	}
	
	// keyup 이벤트
	 function inputNumberFormat(obj) {
    	obj.value = comma(uncomma(obj.value));
	 }
	
	 function comma(str) {
	     str = String(str);
	     return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	 }
	
	 function uncomma(str) {
	     str = String(str);
	     return str.replace(/[^\d]+/g, '');
	 }
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
	<sec:csrfInput/>
	
	<input type="hidden" 	id="hqClass"	name="hqClass">
	<input type="hidden" 	id="hqCd"	    name="hqCd">
	
	<fieldset>
	<legend>매출단가 일괄수정</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출단가 일괄수정 검색조건</caption>
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
				<th><label for="sele2">출고일자</label></th>
					<td>
						<input type="text" class="dt" id="salesStartDt" name="salesStartDt"  readonly="readonly" style="width: 40% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="salesEndDt" name="salesEndDt"  readonly="readonly" style="width: 40% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/>
					</td>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text"   id="salesNm"  name="salesNm" style="width:80%;" onclick="sales_clear()" >
						<input type="hidden" id="salesCd"  name="salesCd" >
						<button id="btnSalesCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">품목</label></th>
					<td>
						<input type="text"   id="prdtNm"  name="prdtNm" style="width:80%;" onclick="prdt_clear()" >
						<input type="hidden" id="prdtCd"  name="prdtCd" >
						<button id="btnPrdtCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">단가</label></th>
					<td>
						<input type="text"   id="salesPrice"  name="salesPrice"  value="0" style="width:50%; text-align:right;" onkeyup="inputNumberFormat(this);" />
						<html:button id="btnPriceUpt"   name="btnPriceUpt"  auth="save" value="단가적용"  />
					</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->


<!-- 추가저장정보 end ----------------------------------------- -->

 <UL style="width: 100%;">
 	<li style="float: top; width: 100%;">
 	<form name="mainForm" id="mainForm"  >
 		<div  style="padding-left: 5px; ">
 		<div class="tit_area">
			<h2 class="subhead">매출단가 일괄수정</h2>
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
        	<table id="tabList" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</li>
 </UL>            


<!-- 매출처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesMaster.jsp" />


<!-- 매출처별 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesPrdtMaster.jsp" />


<!-- CONTENT- BODY end ----------------------------------- -->

</div>
</body>
</html>