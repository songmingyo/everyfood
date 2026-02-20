<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #searchDt").datepicker();
		
		initBadStkListGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
		$('#btnSalesPrMstFind').unbind().click(function(){ 	findSalesPrMst()}); 	// 영업사원찾기 팝업 버튼이벤트
		
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
		//-----------------------------------------------------------------

	});
	
	/*영업사원 찾기팝업 호출*/
	function findSalesPrMst() {
		commonSalesPrMstFindLayer('','',$("#salesPrNm").val(), setSalesPrMstFindLayer);
	}
	
	/*영업사원 콜백(후처리 함수 )*/
	function setSalesPrMstFindLayer(data){
		 if (data != null){
			 $("#srchSalesPrCd").val(data.salesPrCd);		/*영업사원코드*/
			 $("#salesPrNm").val(data.salesPrNm);		/*영업사원명*/
			 $("#position").val(data.position);			/*직급*/
		}
	}


	/* 마스터 데이터  그리드 초기화 */
	function initBadStkListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   
				    '매입처명'
           			, '랙번호'
           			, '품목코드'
           			, '품목명'
           			, '규격'
           			, '단위'
           			, '현재고'
           			, '출고수량(평균)'
           			, '재고일수'
           			, '단가'
           			, '재고금액'
           			, '유통기한'
           			, '매출처'
           			, '영업사원'
           			, '최종입고일'
           			, '최종출고일'
           			, '최종입고소비기한'
           			, '최총출고매출처'
           ],
			colModel:[
						{name:'buyNm'			, index:'buyNm'			, sortable:true, editable:false, width:180 , align:"left"}
						,{name:'lackNo1'		, index:'lackNo1'		, sortable:true, editable:false, width:100 , align:"center"}
		            	,{name:'prdtCd'			, index:'prdtCd'		, sortable:true, editable:false, width:90 , align:"center"}
						,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, editable:false, width:180 , align:"left"}
		            	,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, editable:false, width:80 , align:"center"}
		            	,{name:'ordUnitNm'		, index:'ordUnitNm'		, sortable:true, editable:false, width:80 , align:"center"}
		            	,{name:'stkQty'			, index:'stkQty'		, sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'salesQtyAvg'	, index:'salesQtyAvg'	, sortable:true, editable:false, width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'stkDay'			, index:'stkDay'		, sortable:true, editable:false, width:100 , align:"center"}
		            	,{name:'buyCost'		, index:'buyCost'		, sortable:true, editable:false, width:90 , align:"right", formatter:'integer'}
		            	,{name:'stkAmt'			, index:'stkAmt'		, sortable:true, editable:false, width:100 , align:"right", formatter:'integer', sorttype:'number'}
		            	,{name:'exprtDt'		, index:'exprtDt'		, sortable:true, editable:false, width:100 , align:"center"}		            
		            	,{name:'salesNm'		, index:'salesNm'		, sortable:false, editable:false, width:200 , align:"center"}
		            	,{name:'salesPrNm'		, index:'salesPrNm'		, sortable:true, editable:false, width:100 , align:"center"}		            	
		            	,{name:'buyDtLast'		, index:'buyDtLast'		, sortable:true, editable:false, width:100 , align:"center"}
		            	,{name:'salesDtLast'	, index:'salesDtLast'	, sortable:true, editable:false, width:100 , align:"center"}
		            	,{name:'termValLast'	, index:'termValLast'	, sortable:true, editable:false, width:100 , align:"center"}
		            	,{name:'salesNmLast'	, index:'salesNmLast'	, sortable:true, editable:false, width:100 , align:"center"}
		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount)
                							  .attr("style", "text-align: center;");
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				/* 조회후 데이터가 한건이라도 있을경우  */
				if(allRows.length > 0 && $("#blankRow").val() != ""){
					$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
					
					// grid rowNum 재설정 (-1로 설정한 경우 row가 많으면 sort 시 row수가 감소)
					//gridData.setGridParam({rowNum:allRows.length});
				}
				
	         	// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'lackNo1' : '합계' });
				
                var sum_stkQty = gridData.jqGrid('getCol','stkQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'stkQty':sum_stkQty });
		    	
		    	var sum_stkAmt = gridData.jqGrid('getCol','stkAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'stkAmt':sum_stkAmt });
		    	
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

            rowNum:100,
            pager: '#pageList',
			loadui : "disable",													// 이거 안 써주니 로딩 창 같은게 뜸
			gridview : true,													// 그리드 속도
			viewrecords : true,													// 하단에 1/1 또는 데이터가없습니다 추가
			rownumbers : true,													// rowNumber 표시여부
			autowidth : true,
			//shrinkToFit : true,
			loadonce : true,
			footerrow: true,
			sortorder : "desc"
		});
	}
	
	//데이터 셋팅
	function fncSetDtlInfo(data){
		//comp:업체정보, rslt:평가결과
		$.form = $("#mainForm");
			
		if(data != null){

			//조회 데이터 셋팅
			$.form.find("span, input").each(function(){
				dataNm = $(this).attr("name");
				tagNm = this.tagName.toUpperCase();

				settingData = data[dataNm];

				if(tagNm == "SPAN"){
					$(this).text(settingData);
				}else{
					$(this).val(settingData);
				}
			});
		}
	}
	
	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
        
		$("#tabList").jqGrid('clearGridData');
		initBadStkListGrid();
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
			url:"<c:url value='/app/cs/clsBadStkList_selList.json'/>",
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
		
		$('#searchForm').attr("action", "<c:url value='/app/cs/clsBadStkList_selExcelDown'/>").submit();

	}

	function prdt_clear(event){
		$('#searchPrdtNm').val('');
		$('#searchPrdtCd').val('');
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
	<fieldset>
	<legend>악성재고조회</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>악성재고조회 검색조건</caption>
		<colgroup>		
			<col width="15%">
			<col width="15%">
			<col width="15%">
			<col width="*">
			<col width="15%">
			<col width="15*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">조회일자</label></th>
					<td>
						<input type="text" class="dt" id="searchDt" name="searchDt"  readonly="readonly" style="width: 80% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/>
					</td>
				<th><label for="sele2">미출고기간</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchNotDlvDay" objName="searchNotDlvDay" parentID="M020" />
					</td>
				<th><label for="sele2">저장형태</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchStrgType" objName="searchStrgType" parentID="M001" defName="${commonall}" /> <%--전체 --%>
					</td>
			</tr>
			<tr>		
				<th><label for="sele2">대분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="searchLCd" objName="searchLCd" parentID="Y" defName="${commonall}"/>
					</td>
				<th><label for="sele2">중분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="searchMCD" objName="searchMCD" parentID="Y" defName="${commonall}"/>
					</td>
				<th><label for="sele2">영업사원</label></th>
				<td>
					<input type="text" id="salesPrNm" 		name="salesPrNm" 		style="width: 80%;">
					<input type="hidden" id="srchSalesPrCd" name="srchSalesPrCd" 	readonly="readonly"  style="width: 20%;">
					<button id="btnSalesPrMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
				</td>	
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

 <UL style="width: 100%;">
 	<li style="float: top; width: 100%;">
 	<form name="mainForm" id="mainForm"  >
 		<div class="tit_area">
			<h2 class="subhead">악성재고조회</h2>
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
   		<div  style="padding-left: 10px; ">
        <div id="grid1container">
        	<table id="tabList" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</li>
 </UL>            

<!-- CONTENT- BODY end ----------------------------------- -->

</div>
</body>
</html>