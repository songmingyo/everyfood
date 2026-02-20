<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#searchStartDt").datepicker();
		$("#searchEndDt").datepicker();
		initCusOrdListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	    newEvent);    // 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐		
			
		$("#btnSales_find").unbind().click(function(){
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
	function initCusOrdListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '일자'
            			, '전표번호'
            			, '매출처명'
            			, '품목코드'
            			, '품목명'
            			, '규격'
            			, '출고예정일'
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
		                 {name:'ordDt'		    	, index:'ordDt'		    , sortable:true, width:100 , align:"center"}
		            	,{name:'salesSlipNo'	   	, index:'salesSlipNo'	, sortable:true, width:110, align:"center"}
		            	,{name:'salesNm'			, index:'salesNm'		, sortable:true, width:180, align:"left"}
		            	,{name:'prdtCd'		        , index:'prdtCd'		, sortable:true, width:110, align:"center"}
		            	,{name:'prdtNm'		    	, index:'prdtNm'		, sortable:true, width:180, align:"left"}
		            	,{name:'prdtStd'	  		, index:'prdtStd'		, sortable:true, width:100, align:"center"}
		            	,{name:'dlvDt'		    	, index:'dlvDt'		    , sortable:true, width:100, align:"center"}
		            	,{name:'price'	        	, index:'price'		    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'ordQty'	     		, index:'ordQty'	    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'pureAmt'			, index:'pureAmt'	    , sortable:true, sorttype:'number', width:120, align:"right", formatter:'integer'}
		            	,{name:'vatAmt'		        , index:'vatAmt'		, sortable:true, sorttype:'number', width:120, align:"right", formatter:'integer'}
		            	,{name:'totAmt'		        , index:'totAmt'		, sortable:true, sorttype:'number', width:120, align:"right", formatter:'integer'}
		            	,{name:'remarks1'			, index:'remarks1'		, sortable:true, width:180, align:"left"}
		            	,{name:'userNm'		        , index:'userNm'     	, sortable:true, width:140, align:"center"}
		           		,{name:'regDt'			    , index:'regDt'		    , sortable:true, width:140, align:"center"}
		            
		        
		     ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				/* 조회후 데이터가 한건이라도 있을경우  */
				$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color

				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'salesNm' : '계' });
				
                var sum_ordQty = gridData.jqGrid('getCol','ordQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'ordQty':sum_ordQty });
		    	
		    
		        var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
		    	
		    	var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
		    	
		    	
		    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
		    	
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
	        beforeProcessing : function(data) {
	        	$("#buyNm").attr("readonly", true);
	        },
			 beforeSelectRow : function(rowId, e) {

				$("#tabList").jqGrid('setColProp', 'salesPrdtCd1', {editable:true});

				return true;
		 	},
			loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');
				return false;
            },

            rowNum:1000
            ,pager: '#pageList'
			,loadui : "disable"													// 이거 안 써주니 로딩 창 같은게 뜸
			,gridview: true														// 그리드 속도
			,viewrecords: true													// 하단에 1/1 또는 데이터가없습니다 추가
			,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
			,rownumbers:true													// rowNumber 표시여부
			,autowidth:true		
			,loadonce : true
			,shrinkToFit : false
			,footerrow: true														// 전체 합계를 낼때 사용
			,sortorder : "desc"
		});
	}
	


	/* 조회 */
	function searchEvent(event){
  	
	    var searchInfo = {};
  	      
	    $('#searchForm').find('input').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','');
		});
		
		$('#searchForm').find('select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');		
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/sm/custsales/cusOrdList_selList.json'/>",
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


	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
		
        //Grid 초기화
		$("#tabList").jqGrid('clearGridData');
	}
	
	/*매출처 찾기팝업 호출 */
	function findSalesMst(){
		commonSalesMstFindLayer('','',$("#salesNm").val(), '', setSalesMstFindLayer);
	}
	/*매출처 콜백(후처리 함수 )*/
	function setSalesMstFindLayer(data) {
		$("#salesCd").val(data.salesCd);
		$("#salesNm").val(data.salesNm);
		$("#searchHqClass").val(data.hqClass);
		
		$("#prdtNm").focus();
	}
	
	/*상품 찾기팝업 호출 */
	function findPrdtMst() {
		commonSalesPrdtMstFindLayer('', '', $("#prdtNm").val(), $("#salesCd").val(), setSalesPrdtMstFindLayer);
	}
	
	/*상품 콜백(후처리 함수 )*/
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
	}
	
	/* 엑셀  */
	function excelEvent(){
		$('#searchForm').attr("action", "<c:url value='/app/sm/custsales/cusOrdList_selExcelDown'/>").submit();
	}
	
	
	function sales_clear(event){
		$('#salesNm').val('');
		$('#salesCd').val('');
		$('#searchHqClass').val('');
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
<sec:csrfInput/>
	<fieldset>
	<legend>매출처 발주현황</legend>
	<input type="hidden" id="searchHqClass" name="searchHqClass" />
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처 검색조건</caption>
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
				<th> <label for="sele2">조회일자</label></th>
					<td>
						<input type="text" class="dt" id="searchStartDt" name="searchStartDt"  readonly="readonly" style="width: 30% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="searchEndDt" name="searchEndDt"  readonly="readonly" style="width: 30% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
					</td>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<c:choose>
							<c:when test ="${userSession.userType eq 'S1'}"> 
								<input type="text" id="salesNm" name="salesNm" style="width: 80%;" value="${userSession.username}" disabled/>
								<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;" value="${userSession.userId}">
							</c:when>
							<c:otherwise>
								<input type="text" id="salesNm" name="salesNm" style="width: 80%;" onclick="sales_clear()">
								<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;">
								<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
							</c:otherwise>
						</c:choose>
					</td>
				<th><label for="sele2">품목명</label></th>
					<td>
						<input type="text"   id="prdtNm"  name="prdtNm" style="width:80%;"  onclick="prdt_clear()">
						<input type="hidden" id="prdtCd"  name="prdtCd" style="width:70%;">
						<button id="btnPrdtCd" class="button btn_find" type="button" ><i class="fa fa-search"></i></button>
					</td>
			</tr>
			<tr>
				<th><label for="sele2">대분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="lCd" objName="lCd" parentID="Y" width="80%" defName="${commonall}" />
					</td>
				<th><label for="sele2">중분류</label></th>
					<td colspan=3>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="mCd" objName="mCd" parentID="Y"  width="40%" defName="${commonall}" />
					</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 분류 서브타이틀 및 이벤트 버튼 start  --------------------- -->
<!-- 
<div class="tit_area">
	<h2>TITLE</h2>
	<div>
		<html:button id="btnInit" 	auth="insert"  /> 			<%-- 신규 --%>  
		<html:button id="btnSave" 	auth="save"   /> 			<%-- 저장 --%>
		<%-- <html:button id="btnDelete" auth="delete"  /> 			 삭제 --%>
		&nbsp;
		<html:button id="btnSearch" auth="select" /> 			<%-- 조회 --%>
	</div>
</div>
 -->


<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
 <UL style="width: 100%;">
 	<LI style="float: left; width: 100%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>매출처발주현황</h2>
        	<div>
          		<html:button id="btnNew"   			name="btnNew" 		auth="insert" /> 	   <%-- 신규 --%>
        		<html:button id="btnSearch" 		name="btnSearch"	auth="select" /> 	   <%-- 조회 --%>
				<html:button id="btnExcel"    		name="btnExcel"   	auth="excel" 	/> 	   <%-- 엑셀 --%>
			
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
<jsp:include page="/WEB-INF/views/app/common/find_SalesPrdtMaster.jsp" />
</body>
</html>