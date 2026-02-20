<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#salesDt").datepicker();
		initCusSchdlStkListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	    newEvent);    // 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
			
		$("#btnSalesCd").unbind().click(function(){
			findSalesMst();
		});		
		<%--상품 팝업 찾아가기--%>
			$("#btnPrdtCd").unbind().click(function(){
			findPrdtMst();
		});		
		
		
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-100);	//GRID  LIST
			    }
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-160);	//GRID  LIST
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

		/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/

		/*-----------------------------------------------------------------*/

		
	});
	
	
	/* 마스터 데이터  그리드 초기화 */
	function initCusSchdlStkListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '품목명'
            			, '규격'
            			, '단위'
            			, '전월출고량'
            			, '현재고(조회시점)'
            			, '출고수량'
            			, '잔여수량(출고일자기준)'
            			, '재고일수'

           ]
			,colModel:[
		                 {name:'prdtNm'		    	, index:'prdtNm'	    , sortable:true, width:180, align:"left"}
		            	,{name:'prdtStd'	      	, index:'prdtStd'	    , sortable:true, width:100, align:"center"}
		            	,{name:'ordUnitNm'			, index:'ordUnitNm'	    , sortable:true, width:80,  align:"center"}
		            	,{name:'preSalesQty'		, index:'preSalesQty'	, sortable:true, sorttype:'number', width:100, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'stkQty'		        , index:'stkQty'	    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'salesQty'		    , index:'salesQty'	    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'remStkQty'	  		, index:'remStkQty'	    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'stkDt'	        	, index:'stkDt'		    , sortable:true, sorttype:'number', width:120, align:"right", formatter:'integer'}
		     ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            $('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'prdtStd' : '합계' });
				
                var sum_preSalesQty = gridData.jqGrid('getCol','preSalesQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'preSalesQty':sum_preSalesQty });		    	
		
		    	var sum_stkQty = gridData.jqGrid('getCol','stkQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'stkQty':sum_stkQty });
		    	
		    	var sum_salesQty = gridData.jqGrid('getCol','salesQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'salesQty':sum_salesQty });		    	
		    	
		    	var sum_remStkQty = gridData.jqGrid('getCol','remStkQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'remStkQty':sum_remStkQty });
		    	
	        //    $(window).resize();
	        },
			loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	        	$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");
	        	
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
            }

		 	,rowNum:-1
	        ,loadui : "disable"
            ,gridview:    true
            ,sortorder: 'asc'
            ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
            ,viewrecords: true
            ,scroll : true
           // ,shrinkToFit : false
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
			url:"<c:url value='/app/sm/custsales/cusSchdlStkList_selList.json'/>",
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
		$('#searchForm').attr("action", "<c:url value='/app/sm/custsales/cusSchdlStkList_selExcelDown'/>").submit();
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
	<legend>출고예정재고리스트</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>출고예정재고리스트 검색조건</caption>
		<colgroup>
			<col width="150">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">출고일자</label></th>
					<td>
				<input type="text" class="dt" id="salesDt" name="salesDt"  style="width: 8% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
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
        	<h2>출고예정재고리스트</h2>
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

</body>
</html>