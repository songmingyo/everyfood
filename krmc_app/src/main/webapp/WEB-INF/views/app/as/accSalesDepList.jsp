<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#startDt").datepicker();
		$("#endDt").datepicker();
		
		initAccSalesDepListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	    newEvent);    // 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
			
		$("#btnSalesCd").unbind().click(function(){
			findSalesMst();
		});		
		
		
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-120);	//GRID  LIST
			    }
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-180);	//GRID  LIST
		        }
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

		/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/
		$('#salesNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findSalesMst(); break; // enter
	    		default : return true;
	    	}
    		e.preventDefault();
   		});
		/*-----------------------------------------------------------------*/

		
	});
	

	/* 마스터 데이터  그리드 초기화 */
	function initAccSalesDepListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '입금일자'
            			, '매출처명'
            			, '계좌입금'
            			, '현금'
            			, '카드'
            			, '어음'
            			, '장려금'
            			, '수수료'
            			, '수입'
            			, '기타'
            			, '합계'
            			, '수금조건'
            			, '적요'

           ]
			,colModel:[
		                 {name:'depDt'		    		, index:'depDt'		    , sortable:true, width:100, align:"center"}
		            	,{name:'salesNm'	         	, index:'salesNm'		, sortable:true, width:180, align:"left"}
		            	,{name:'accountAmt'				, index:'accountAmt'	, sortable:true, width:120, align:"right", formatter:'integer', formatter:'integer'}
		            	,{name:'cashAmt'		        , index:'cashAmt'		, sortable:true, width:120, align:"right", formatter:'integer', formatter:'integer'}
		            	,{name:'cardAmt'		    	, index:'cardAmt'		, sortable:true, width:120, align:"right", formatter:'integer', formatter:'integer'}
		            	,{name:'noteAmt'	  			, index:'noteAmt'		, sortable:true, width:120, align:"right", formatter:'integer', formatter:'integer'}
		            	,{name:'subsidyAmt'		    	, index:'subsidyAmt'	, sortable:true, width:120, align:"right", formatter:'integer', formatter:'integer'}
		            	,{name:'vatAmt'	       		 	, index:'vatAmt'		, sortable:true, width:120, align:"right", formatter:'integer', formatter:'integer'}
		            	,{name:'incomeAmt'	     		, index:'incomeAmt'	  	, sortable:true, width:120, align:"right", formatter:'integer', formatter:'integer'}
		            	,{name:'etcAmt'					, index:'etcAmt'	   	, sortable:true, width:120, align:"right", formatter:'integer', formatter:'integer'}
		            	,{name:'totAmt'		  	        , index:'totAmt'		, sortable:true, width:120, align:"right", formatter:'integer', formatter:'integer'}
		            	,{name:'setlCon'		        , index:'setlCon'		, sortable:true, width:140, align:"center"}
		            	,{name:'remarks'		        , index:'remarks'		, sortable:true, width:140, align:"left"}
		        
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				/* 조회후 데이터가 한건이라도 있을경우  */
				if(allRows.length > 0 && $("#blankRow").val() != ""){
					$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
					
					// grid rowNum 재설정 (-1로 설정한 경우 row가 많으면 sort 시 row수가 감소)
				//	gridData.setGridParam({rowNum:allRows.length});
					
				}
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'depDt' : '합계' });
				
                var sum_accountAmt = gridData.jqGrid('getCol','accountAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'accountAmt':sum_accountAmt });
		    	
		    	var sum_cashAmt = gridData.jqGrid('getCol','cashAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'cashAmt':sum_cashAmt });
		    	
		    	var sum_cardAmt = gridData.jqGrid('getCol','cardAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'cardAmt':sum_cardAmt });
		    	
		    	var sum_noteAmt = gridData.jqGrid('getCol','noteAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'noteAmt':sum_noteAmt });
		    	
		    	var sum_subsidyAmt = gridData.jqGrid('getCol','subsidyAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'subsidyAmt':sum_subsidyAmt });
		    	
		    	var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
		    	
		    	var sum_incomeAmt = gridData.jqGrid('getCol','incomeAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'incomeAmt':sum_incomeAmt });
		    	
		    	var sum_etcAmt = gridData.jqGrid('getCol','etcAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'etcAmt':sum_etcAmt });
		    	
		    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
	            
	        	$(window).resize();
	        }
			,loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }
	        	$(window).resize();
	        }
			,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
            }
            
			,rowNum : -1
            ,loadui : "disable"
            ,gridview:    true
            ,sortorder: 'asc'
            ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
            ,viewrecords: true
            ,rownumbers:true
            //,shrinkToFit : false
	        ,footerrow: true
            ,autowidth:true
            ,loadonce:true
            ,scroll:true
		});
	}

	/*매출처 찾기팝업 호출 */
	function findSalesMst(){
		commonSalesMstFindLayer('','',$("#salesNm").val(), '', setSalesyMstFindLayer);
	}
	/*매출처 콜백(후처리 함수 )*/
	function setSalesyMstFindLayer(data) {
		$("#salesCd").val(data.salesCd);		/*매출처코드*/
		$("#salesNm").val(data.salesNm);		/*매출처명*/
	}
	
	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
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
			url:"<c:url value='/app/as/account/accSalesDepList_selList.json'/>",
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
		$('#searchForm').attr("action", "<c:url value='/app/as/account/accSalesDepList_selExcelDown'/>").submit();
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
				<th><label for="sele2">일자</label></th>
					<td>
						<input type="text" class="dt" id="startDt" name="startDt" style="width: 30% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="endDt" name="endDt" style="width: 30% !important;" maxlength="8" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"  />
					</td>
				<th><label for="sele2">구분</th>
					<td>
						<html:codeTag comType="SELECT" objId="hqClass" objName="hqClass" parentID="M004" /> <%--전체 --%>
					</td>
				<th><label for="sele2">매출처명</label></th>
				<td>
					<input type="text"   id="salesNm"    name="salesNm" style="width:70%;"  autocomplete="off">
					<input type="hidden" id="salesCd"    name="salesCd" style="width:70%;">
					<button id="btnSalesCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
				</td>
			</tr>
			
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 분류 서브타이틀 및 이벤트 버튼 start  --------------------- -->


<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
 <UL style="width: 100%;">
 	<LI style="float: left; width: 100%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>매출처별입금현황</h2>
        	<div>
          		<html:button id="btnNew"   		name="btnNew" 	auth="insert" /> 		<%-- 신규 --%>
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


</body>
</html>