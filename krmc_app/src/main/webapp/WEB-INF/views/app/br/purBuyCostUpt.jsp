<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #buyStartDt, #searchForm #buyEndDt").datepicker();
		
		initSalesPrdtMstGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnSave').unbind().click(null,	        saveEvent);   // 저장
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnCostUpt').unbind().click(null,	    costUptEvent);    // 단가일괄적용
		
		
		$("#btnBuy_find").unbind().click(function(){
			findBuyMst();
		});		<%--매입처 팝업 찾아가기--%>
		
		$("#btnPrdt_find").unbind().click(function(){
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
		$('#buyNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findBuyMst(this); break; // enter
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
				    '일고일자'
           			, '매입처명'
           			, '품목코드'
           			, '품명'
           			, '규격'
           			, '단위'
           			, '입고량'
           			, '단가'
           			, '공급가'
           			, '부가세'
           			, '합계'
           			
           			, '부가세유무'
           			, '그리드상태'
           			, '매입처코드'
           			, '전표번호'
           			, '전표순번'
           ],
			colModel:[
						{name:'buyDt'			, index:'buyDt'			, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'buyNm'			, index:'buyNm'			, sortable:true, editable:false, width:100, align:"left"}
		            	,{name:'prdtCd'			, index:'prdtCd'		, sortable:true, editable:false, width:80 , align:"center"}
		            	,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, editable:false, width:160 , align:"left"}
		            	,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'ordUnit'		, index:'ordUnit'		, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'buyQty'			, index:'buyQty'		, sortable:true, editable:false, width:50 , align:"right", formatter:'integer'}
		            	,{name:'cost'			, index:'cost'			, sortable:true, sorttype:'number', editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'pureAmt'		, index:'pureAmt'		, sortable:true, sorttype:'number', editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'vatAmt'			, index:'vatAmt'		, sortable:true, sorttype:'number', editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'totAmt'			, index:'totAmt'		, sortable:true, sorttype:'number', editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	
		            	,{name:'vatYn'			, index:'vatYn'		, hidden:true	, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'gridFlag'		, index:'gridFlag'	, hidden:true	, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'buyCd'			, index:'buyCd'		, hidden:true	, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'buySlipNo'		, index:'buySlipNo'	, hidden:true	, sortable:true, editable:false, width:50 , align:"center"}
		            	,{name:'buySeq'			, index:'buySeq'	, hidden:true	, sortable:true, editable:false, width:50 , align:"center"}		            	
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
	                
	                $("#btnCostUpt").attr("disabled", true);
	            } else{
	            	$("#btnCostUpt").attr("disabled", false);
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
	
	
	/*매입처 찾기팝업 호출 */
	function findBuyMst() {
		commonBuyMstFindLayer('','',$("#buyNm").val(), setBuyMstFindLayer);
	}
	
	/*매입처 콜백(후처리 함수 )*/
	function setBuyMstFindLayer(data) {
		if (data != null){
			$("#buyCd").val(data.buyCd);
			$("#buyNm").val(data.buyNm);
			$("#telNo").val(data.telNo);
			$("#salesPrNm").val(data.salesPrNm);
			$("#salesPrHp").val(data.salesPrHp);
			$("#setlCon").val(data.setlCon);
			
			$('#prdtNm').focus();
		}
	}
	
	/*상품 찾기팝업 호출 */
	function findPrdtMst() {
		commonPrdtMstFindLayer('','',$("#prdtNm").val(), $("#buyCd").val(), setPrdtMstFindLayer);
	}
	
	/*상품 콜백(후처리 함수 )*/
	function setPrdtMstFindLayer(data) {
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
			url:"<c:url value='/app/br/buy/buyCostUpt_selList.json'/>",
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
	function costUptEvent(event){
		
		var allRows = $("#tabList").jqGrid('getDataIDs');		//전체 rowIds
		
		var buyCost = parseInt($("#buyCost").val().replaceAll(',',''));
		
		for(var i=0; i<allRows.length; i++){
			var rowId = allRows[i];
			var rowData = $("#tabList").jqGrid('getRowData', rowId);

			var vatYn = rowData.vatYn;
			var buyQty = parseInt(rowData.buyQty);
			
			var pureAmt = buyCost * buyQty;
			var vatAmt = Math.ceil(buyCost * buyQty * 0.1);
	        
			$("#tabList").jqGrid('setCell',rowId,'cost',buyCost);
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

		var saveData = {'buyCostUptList'  : $("#tabList").getRowData()};
		
		$.ajax({
		    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		    url  : "<c:url value='/app/br/buy/buyCostUpt_insList.json'/>",
 
		    data : JSON.stringify(saveData),
	 	    success : function(data){
	   	 	
	 	    	if(data.msgCd =="0") {
					alert("신규 : "+ data.rtnValue01+"건  |  수정 : "+data.rtnValue02+"건이 처리 정상 되었습니다.");
					
	   				searchEvent();			//  재조회
			   	}else{
					alert("처리중 오류가 발생하였습니다. Code : "+msgCd+ "Message : "+message)
				}
      	    }
		});

	}
	
	function buy_clear(event){
		$('#buyNm').val('');
		$('#buyCd').val('');
		$('#prdtNm').val('');
		$('#prdtCd').val('');
		$('#cost').val('');
	}
	
	function prdt_clear(event){
		$('#prdtNm').val('');
		$('#prdtCd').val('');
		$('#cost').val('');
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
<form id="searchForm" name="searchForm" method="post">
	<sec:csrfInput/>
	<fieldset>
	<legend>매입단가 일괄수정</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매입단가 일괄수정 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="17%">
			<col width="100">
			<col width="17%">
			<col width="100">
			<col width="17%">
			<col width="100">
			<col width="10%">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">입고일자</label></th>
					<td>
						<input type="text" class="dt" id="buyStartDt" name="buyStartDt"   style="width: 40% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="buyEndDt" name="buyEndDt"   style="width: 40% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/>
					</td>
				<th><label for="sele2">매입처명</label></th>
					<td>
						<input type="text"   id="buyNm"  name="buyNm" style="width:80%;" onclick="buy_clear()"  onkeyup="if(window.event.keyCode==13){findBuyMst()}" autocomplete="off">
						<input type="hidden" id="buyCd"  name="buyCd" >
						<button id="btnBuy_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
					</td>
				<th><label for="sele2">품목</label></th>
					<td>
						<input type="text"   id="prdtNm"  name="prdtNm" style="width:80%;" onclick="prdt_clear()" onkeyup="if(window.event.keyCode==13){findPrdtMst()}" autocomplete="off">
						<input type="hidden" id="prdtCd"  name="prdtCd" >
						<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
					</td>
				<th><label for="sele2">단가</label></th>
					<td>
						<input type="text"   id="buyCost"  name="buyCost"  value="0" style="text-align:right;" onkeyup="inputNumberFormat(this);" />
					</td>
					<td style="border-left:none;">
						<html:button id="btnCostUpt"   name="btnCostUpt"  auth="save" value="단가적용"  />
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
			<h2 class="subhead">매입단가 일괄수정</h2>
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


<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />


<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />


<!-- CONTENT- BODY end ----------------------------------- -->

</div>
</body>
</html>