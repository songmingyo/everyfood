<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#startDt").datepicker();
		$("#endDt").datepicker();
		initCusRtnListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	    newEvent);    // 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
		$('#btnSales_find').unbind().click(function(){ findSalesMst()}); 	// 매출처찾기 팝업 버튼이벤트
	    $("#btnPrdt_find").unbind().click(function(){findPrdtMst();	});		// 상품 팝업 찾아가기
		
		
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-80);	//GRID  LIST
			    }
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-140);	//GRID  LIST
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

		/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/
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
		/*-----------------------------------------------------------------*/

		
	});
	
	/* 마스터 데이터  그리드 초기화 */
	function initCusRtnListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '일자'
            			, '전표번호'
            			, '창고명'
            			, '매출처명'
            			, '품목코드'
            			, '품목명'
            			, '규격'
            			, '반품구분'
            			, '단가'
            			, '수량'
            			, '공급가'
            			, '부가세'
            			, '합계금액'
            			, '비고'
            			, '최초등록자'
            			, '최초등록시간'

           ]
			,colModel:[
		                 {name:'rtnDt'		    	, index:'rtnDt'		    , sortable:true, width:100, align:"center"}
		            	,{name:'salesSlipNo'	   	, index:'salesSlipNo'   , sortable:true, width:120, align:"center"}
		            	,{name:'whNm'				, index:'whNm'		    , sortable:true, width:100, align:"center"}
		            	,{name:'salesNm'		    , index:'salesNm'		, sortable:true, width:180, align:"left"}
		            	,{name:'prdtCd'		    	, index:'prdtCd'		, sortable:true, width:100, align:"center"}
		            	,{name:'prdtNm'	  			, index:'prdtNm'		, sortable:true, width:180, align:"left"}
		            	,{name:'prdtStd'	    	, index:'prdtStd'	    , sortable:true, width:100, align:"center"}
		            	,{name:'rtnClassNm'			, index:'rtnClassNm'	, sortable:true, width:100, align:"center"}
		               	,{name:'price'	        	, index:'price'		    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'rtnQty'	     		, index:'rtnQty'	    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'pureAmt'			, index:'pureAmt'	    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'vatAmt'		        , index:'vatAmt'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'totAmt'		        , index:'totAmt'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'remarks'			, index:'remarks'	    , sortable:true, width:200, align:"left"}
		            	,{name:'regId'		        , index:'regId'	     	, sortable:true, width:100, align:"center"}
		           		,{name:'regDt'			    , index:'regDt'		    , sortable:true, width:100, align:"center"}
		            
		        
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
				var colCount = $(this).getGridParam("colNames").length;
    			$("#blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				// 조회후 데이터가 한건이라도 있을경우 
// 				if(allRows.length > 0 && $("#blankRow").val() != ""){
				$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
					
// 					// grid rowNum 재설정 (-1로 설정한 경우 row가 많으면 sort 시 row수가 감소)
// 					gridData.setGridParam({rowNum:allRows.length});
					
// 				}
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'whNm' : '합계' });
				
                var sum_rtnQty = gridData.jqGrid('getCol','rtnQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'rtnQty':sum_rtnQty });
		    	
		    	var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
		    	
		    	var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
		    	
		    	
		    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
	            
	        	//$(window).resize();
	        }
			,loadComplete: function() {
// 	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
// 	        	$(this).jqGrid("setLabel", "rn", "No");
// 	            if ($(this).getGridParam("records")==0) {
// 	                var firstColName = $(this).getGridParam("colModel")[1].name;
// 	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();
// 	                $(this).addRowData("blankRow", msg);
// 	            }
	            
	            if ($(this).getGridParam("records")==0) {
    				$(this).addRowData("blankRow", {});
    				$(this).find("#blankRow td:nth-child(2)").empty();
    				$(this).find("#blankRow td:nth-child(2)").append("<spring:message code='message.search.no.data'/>");	/* 조회결과가 없습니다. */
				}
	            
	        	$(window).resize();
	        }
			,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
            }
            
           ,rowNum:-1
           ,loadui : "disable"
           ,gridview:    true
           //,pager: '#pageList'
           ,sortorder: 'asc'
           ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
           ,viewrecords: true
           ,scroll : true
           ,shrinkToFit : false
           ,rownumbers:true
           ,footerrow: true
           ,loadonce:true
           ,autowidth:true
		});
	}

	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
		
        //Grid 초기화
		$("#tabList").jqGrid('clearGridData');
	}
	
	/* grid1container / tabList Data 조회 */
	function searchEvent(event){

	      var searchInfo = {};
  	      
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');		
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/sm/custsales/cusRtnList_selList.json'/>",
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

	/*매출처 찾기팝업 호출 */
	function findSalesMst(){
		commonSalesMstFindLayer('','',$("#salesNm").val(), '', setSalesMstFindLayer);
	}
	
	/*매출처 콜백(후처리 함수 )*/
	function setSalesMstFindLayer(data) {
		$("#salesCd").val(data.salesCd);
		$("#salesNm").val(data.salesNm);
		$("#hqClass").val(data.hqClass);
	}
		/*상품 찾기팝업 호출 */
	function findPrdtMst() {
		commonPrdtMstFindLayer('','',$("#prdtNm").val(), '', setPrdtMstFindLayer);
	}
	
	/*상품 콜백(후처리 함수 )*/
	function setPrdtMstFindLayer(data) {
		if (data != null){
			$("#prdtCd").val(data.prdtCd);
			$("#prdtNm").val(data.prdtNm);
		}
	}
	
	/* 엑셀  */
	function excelEvent(){
		$('#searchForm').attr("action", "<c:url value='/app/sm/custsales/cusRtnList_selExcelDown'/>").submit();
	}

	function sales_clear(event){
		$('#salesNm').val('');
		$('#salesCd').val('');
		$("#hqClass").val('');
	}

	function prdt_clear(event){
		$('#prdtNm').val('');
		$('#prdtCd').val('');
	}
	
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
<input type="hidden" name="hqClass" id="hqClass" />
<sec:csrfInput/>
	<fieldset>
	<legend>매출처 관리</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처 검색조건</caption>
		<colgroup>
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">반품일자</label></th>
					<td>
						<input type="text" class="dt" id="startDt" name="startDt"  readonly="readonly" style="width: 30% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="endDt" name="endDt"  readonly="readonly" style="width: 30% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
					</td>
				<th><label for="sele2">반품구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="rtnClass" objName="rtnClass" parentID="M012"  defName="${commonall}"   />
					</td>
				<th><label for="sele2">창고구분</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC3"	objId="whCd" objName="whCd" parentID="Y" defName="${commonall}" />
					</td>
			</tr>
			<tr>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text" id="salesNm" name="salesNm" style="width: 80%;" onclick="sales_clear()">
						<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;">
						<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
				</td>
				<th><label for="sele2">품목명</label></th>
					<td colspan=3>
						<input type="text"   id="prdtNm"  name="prdtNm" style="width:30%;" onclick="prdt_clear()">
						<input type="hidden" id="prdtCd"  name="prdtCd" style="width: 20%;">
						<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
					</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
 <UL style="width: 100%;">
 	<LI style="float: left; width: 100%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>매입처발주현황</h2>
        	<div>
          		<html:button id="btnNew"   			name="btnNew" 	auth="insert" /> 		<%-- 신규 --%>
        		<html:button id="btnSearch" 	name="btnSearch"	auth="select" /> 	   <%-- 조회 --%>
				<html:button id="btnExcel"    	name="btnExcel"   	auth="excel" 	/> 	   <%-- 엑셀 --%>
        	</div>
		</div>
		<!-- 서브타이틀 및 이벤트 버튼 end  -------- -->
			
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="tabList" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</LI>
 
 	
 
 
<!-- CONTENT- BODY end ----------------------------------- -->

</div>

<!-- 매출처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesMaster.jsp" />



<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />
</body>
</html>