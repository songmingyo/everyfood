```````````````````````````````````````<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #searchStartDt, #searchForm #searchEndDt").datepicker();
		
		initSalesGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
		
		$('#btnSalesPrMstFind').unbind().click(function(){ findSalesPrMst()}); 	// 영업사원찾기 팝업 버튼이벤트
		
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
		$('#salesPrNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findSalesPrMst(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		//-----------------------------------------------------------------

	});


	/* 마스터 데이터  그리드 초기화 */
	function initSalesGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   
				    '영업담당자'
           			, '매출목표'
           			, '매출일계'
           			, '매출월계'
           			, '달성금액차'
           			, '달성율(%)'
           			, '전월실적'
           			, '전월대비실적율(%)'
           			, '전년동월실적'
           			, '전년대비실적율(%)'
           ],
			colModel:[
						{name:'salesPrNm'		, index:'salesPrNm'			, sortable:true, editable:false, width:120 , align:"center"}
						,{name:'goalAmt'		, index:'goalAmt'			, sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'integer'}
		            	,{name:'thisDayAmt'		, index:'thisDayAmt'		, sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'integer'}
		            	,{name:'thisMonAmt'		, index:'thisMonAmt'	    , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'integer'}
		            	,{name:'thisMonDiff'	, index:'thisMonDiff'	    , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'integer'}
		            	,{name:'thisMonRate'	, index:'thisMonRate'	    , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
		            	,{name:'prevMonAmt'		, index:'prevMonAmt'	    , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'integer'}
		            	,{name:'prevMonRate'	, index:'prevMonRate'	    , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
		            	,{name:'prevYearAmt'	, index:'prevYearAmt'	    , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'integer'}
		            	,{name:'prevYearRate'	, index:'prevYearRate'	    , sortable:true, sorttype:'number', editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 1}}
		            	
		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				/* 조회후 데이터가 한건이라도 있을경우  */
				if(allRows.length > 0 && $("#blankRow").val() != ""){
					$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
					
					// grid rowNum 재설정 (-1로 설정한 경우 row가 많으면 sort 시 row수가 감소)
					//gridData.setGridParam({rowNum:allRows.length});
					
				}
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'salesPrNm' : '합계' });
				
                var sum_goalAmt = gridData.jqGrid('getCol','goalAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'goalAmt':sum_goalAmt });
		    	
		    	var sum_thisDayAmt = gridData.jqGrid('getCol','thisDayAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'thisDayAmt':sum_thisDayAmt });
		    	
		    	var sum_thisMonAmt = gridData.jqGrid('getCol','thisMonAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'thisMonAmt':sum_thisMonAmt });
		    	
		    	var sum_thisMonDiff = gridData.jqGrid('getCol','thisMonDiff', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'thisMonDiff':sum_thisMonDiff });
		    	
		    	if(sum_goalAmt==0){
		    		var sum_thisMonRate = 0;
		    	}else{
		    		var sum_thisMonRate = parseInt(sum_thisMonAmt) / parseInt(sum_goalAmt) * 100;
		    		sum_thisMonRate = sum_thisMonRate.toFixed(1);
		    	}		    	
		    	gridData.jqGrid('footerData', 'set', { 'thisMonRate':sum_thisMonRate });
		    	
		    	var sum_prevMonAmt = gridData.jqGrid('getCol','prevMonAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'prevMonAmt':sum_prevMonAmt });
		    	
		    	if(sum_prevMonAmt==0){
		    		var sum_prevMonRate = 0;
		    	}else{
		    		var sum_prevMonRate = parseInt(sum_thisMonAmt) / parseInt(sum_prevMonAmt) * 100;
		    		sum_prevMonRate = sum_prevMonRate.toFixed(1);
		    	}
		    	gridData.jqGrid('footerData', 'set', { 'prevMonRate':sum_prevMonRate });
		    	
		    	var sum_prevYearAmt = gridData.jqGrid('getCol','prevYearAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'prevYearAmt':sum_prevYearAmt });
		    	
		    	if(sum_prevYearAmt==0){
		    		var sum_prevYearRate = 0;
		    	}else{
		    		var sum_prevYearRate = parseInt(sum_thisMonAmt) / parseInt(sum_prevYearAmt) * 100;
		    		sum_prevYearRate = sum_prevYearRate.toFixed(1);
		    	}
		    	gridData.jqGrid('footerData', 'set', { 'prevYearRate':sum_prevYearRate });
	            
	            $(window).resize();
	        },
			loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }
	            
	        	$(window).resize();
	        },
			loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');
				return false;
            },

            rowNum:-1
            //,pager: '#pageList'
			,loadui : "disable"													// 이거 안 써주니 로딩 창 같은게 뜸
			,gridview: true														// 그리드 속도
			,viewrecords: true													// 하단에 1/1 또는 데이터가없습니다 추가
			,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
			,rownumbers:true													// rowNumber 표시여부
			,autowidth:true		
			,loadonce : true
			,footerrow: true														// 전체 합계를 낼때 사용
			,sortorder : "desc"
		});
	}
	
	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
		
		initSalesGrid();
	}
	
	/*영업사원 찾기팝업 호출*/
	function findSalesPrMst() {
		commonSalesPrMstFindLayer('','',$("#salesPrNm").val(), setSalesPrMstFindLayer);
	}
	/*영업사원 콜백(후처리 함수 )*/
	function setSalesPrMstFindLayer(data){
		 if (data != null){
			 $("#salesPrCd").val(data.salesPrCd);		/*영업사원코드*/
			 $("#salesPrNm").val(data.salesPrNm);		/*영업사원명*/
		}
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
			url:"<c:url value='/app/as/sal/salSalesPrPerfList_selList.json'/>",
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
	

	
	/* 엑셀  */
	function excelEvent(){
		
		$('#searchForm').attr("action", "<c:url value='/app/as/sal/salSalesPrPerfList_selExcelDown'/>").submit();

	}
	
	function reSetDate(obj) {
		obj.value = obj.value.trim().replaceAll("-","");
		setRightFocus(obj,0);
	}
	
	function isValidYMDThisPageOnly(comp)
	{
		if(!comp.value && comp.value.trim().length <= 0) return;
		if(!isValidYMDStringThisPageOnly(comp.value))
		{
			comp.focus();
			return false;
		}
		
		isDateCheck(comp);
	}
	
	function isValidYMDStringThisPageOnly(strComp)
	{		
		var dates = strComp.trim().replaceAll("-","");

		if(dates.length != 6) return;
		else
		{
			var t_year  = Number(dates.substring(0,4));
			var t_month = Number(dates.substring(4,6));
			
			if (t_year < 1900 || t_year > 9999)
			{ 
				alert('년도 입력오류입니다.!');
				return false;
			}
			if (t_month <1 || t_month > 12)
			{
				alert('해당월 입력오류입니다.!');
				return false;
			}			
		}
		
		return true;
	}
	
	function isDateCheck(obj)
	{
		if(obj.value.replaceAll("-","").length > 4 ) 		
			obj.value	=	obj.value.substring(0,4)+"-"+obj.value.replaceAll("-","").substring(4);

		if(obj.value.length > 7) 	
			obj.value	=	obj.value.substring(0,7)+"-"+obj.value.substring(7,10);

		return obj.value;	
	}
	
	function salesPr_clear(event){
		$('#salesPrNm').val('');
		$('#salesPrCd').val('');
	}
	
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
	<input type="hidden" id="searchOrdDt" name="searchOrdDt""/>
	<input type="hidden" id="searchBuyDt" name="searchBuyDt""/>
	<sec:csrfInput/>
	<fieldset>
	<legend>영업실적현황</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>영업실적현황 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="*">
			<col width="100">
			<col width="*">
			<col width="100">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">조회일자</label></th>
					<td>
						<input type="text" class="dt" id="searchStartDt" name="searchStartDt"   style="width: 25% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="searchEndDt" name="searchEndDt"   style="width: 25% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
					</td>
				<th><label for="sele2">부가세</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="vatYn" objName="vatYn" parentID="M902"   selectParam="N" />
					</td>
				<th><label for="sele2">영업사원</label></th>
					<td>
						<input type="text" id="salesPrNm" name="salesPrNm" style="width: 50%;" onclick="salesPr_clear()" >
						<input type="hidden" id="salesPrCd" name="salesPrCd" readonly="readonly"  style="width: 20%;">
						<button id="btnSalesPrMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
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
			<h2 class="subhead">영업실적현황</h2>
			<div class="btn_l">
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 		<%-- 엑셀 --%>
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

<!-- CONTENT- BODY end ----------------------------------- -->

<!-- 영업사원 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_salesPrMaster.jsp" />

</div>
</body>
</html>