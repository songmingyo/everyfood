<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#startDt").datepicker();
		$("#endDt").datepicker();
		initCusSampleListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	    newEvent);    // 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
		
		$('#btnSales_find').unbind().click(function(){findSalesMst()}); 	// 매출처찾기 팝업 버튼이벤트
	    $("#btnPrdt_find").unbind().click(function(){findPrdtMst();	});		// 상품 팝업 찾아가기
	    $("#btnSalesPr_find").unbind().click(function(){findSalesPrMst()}); // 영업담당자 찾기
				
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
	
	 /*영업사원 찾기팝업 호출*/
	function findSalesPrMst() {
		commonSalesPrMstFindLayer('','',$("#salesPrNm").val(), setSalesPrMstFindLayer);
	}
	/*영업사원 콜백(후처리 함수 )*/
	function setSalesPrMstFindLayer(data){
		 if (data != null){
			 $("#salesPrNm").val(data.salesPrNm);
			 $("#mTelNo").val(data.mtelNo);
		}
	}
	

	/* 마스터 데이터  그리드 초기화 */
	function initCusSampleListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '일자'
            			, '창고명'
            			, '매출처명'
            			, '품목코드'
            			, '품목명'
            			, '규격'
            			, '구분'
            			, '수량'
            			, '단가'
            			, '공급가'
            			, '영업담당자'
            			, '비고'
        
           ]
			,colModel:[
		                 {name:'freeDt'		   	, index:'freeDt'	    , sortable:true, width:80 , align:"center"}
		            	,{name:'whNm'	       	, index:'whNm'	        , sortable:true, width:120, align:"center"}
		            	,{name:'salesNm'		, index:'salesNm'		, sortable:true, width:180, align:"left"}
		            	,{name:'prdtCd'		    , index:'prdtCd'		, sortable:true, width:100, align:"center"}
		            	,{name:'prdtNm'		    , index:'prdtNm'		, sortable:true, width:180, align:"left"}
		            	,{name:'prdtStd'	  	, index:'prdtStd'		, sortable:true, width:100, align:"center"}
		            	,{name:'freeClassNm'	, index:'freeClassNm'	, sortable:true, width:100, align:"center"}
		            	,{name:'freeQty'	    , index:'freeQty'	    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'cost'	        , index:'cost'		    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'totAmt'			, index:'totAmt'	    , sortable:true, sorttype:'number', width:120, align:"right", formatter:'integer'}
		            	,{name:'salesPrNm'		, index:'salesPrNm'		, sortable:true, width:140, align:"center"}
		            	,{name:'remarks'		, index:'remarks'		, sortable:true, width:200, align:"left"}
		 		        
		     ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				//gridData.jqGrid('hideCol', ["rn"]);
				
				/* 조회후 데이터가 한건이라도 있을경우  */
				if(allRows.length > 0 && $("#blankRow").val() != ""){
					$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
					
					// grid rowNum 재설정 (-1로 설정한 경우 row가 많으면 sort 시 row수가 감소)
					//gridData.setGridParam({rowNum:allRows.length});
					
				}
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'prdtCd' : '합계' });
				
                var sum_freeQty = gridData.jqGrid('getCol','freeQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'freeQty':sum_freeQty });
		    	
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
	        loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');
				return false;
            },

            rowNum:-1
            // ,pager: '#pageList'
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
	
	/*매출처 찾기팝업 호출 */
	function findSalesMst(){
		commonSalesMstFindLayer('','',$("#salesNm").val(), 'Y', setSalesMstFindLayer);
	}
	/*매출처 콜백(후처리 함수 )*/
	function setSalesMstFindLayer(data) {
		$("#salesCd").val(data.salesCd);		/*매출처코드*/
		$("#salesNm").val(data.salesNm);		/*매출처명*/
	}

	/*상품 찾기팝업 호출 */
	function findPrdtMst() {
		commonPrdtMstFindLayer('','',$("#prdtNm").val(), '', setPrdtMstFindLayer);
	}
	
	/*상품 콜백(후처리 함수 )*/
	function setPrdtMstFindLayer(data) {
			$("#prdtCd").val(data.prdtCd);
			$("#prdtNm").val(data.prdtNm);
	}

	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
	}
	
	/*  조회 */
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
			url:"<c:url value='/app/sm/custsales/cusSampleList_selList.json'/>",
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
		$('#searchForm').attr("action", "<c:url value='/app/sm/custsales/cusSampleList_selExcelDown'/>").submit();
		
// 		let gridObj = $("#tabList");	//grid Obj
// 		let excelName = "샘플출고현황";	//엑셀 다운로드 명
		
// 		exportExcel(gridObj, excelName);
	}
	
	function sales_clear(event){
		$('#salesNm').val('');
		$('#salesCd').val('');
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
	<legend>매출처 관리</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처 검색조건</caption>
		<colgroup>
			<col width="120">
			<col width="*">
			<col width="120">
			<col width="*">
			<col width="120">
			<col width="*">
			<col width="120">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th> <label for="sele2">조회일자</label></th>
					<td>
						<input type="text" class="dt" id="startDt" name="startDt"  readonly="readonly" style="width: 40% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="endDt" name="endDt"  readonly="readonly" style="width: 40% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
					</td>
				<th><label for="sele2">매출처명</label></th>
					<td>
						<input type="text" id="salesNm" name="salesNm" style="width: 80%;" onclick="sales_clear()">
						<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;">
						<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
				<th><label for="sele2">구분</label></th>
					<td>
					<html:codeTag comType="SELECT" objId="freeClass" objName="freeClass" parentID="M016"  defName="${commonall}"   />
					</td>
				<th><label for="sele2">창고</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC3"	objId="whCd" objName="whCd" parentID="Y" defName="${commonall}" />
					</td>
			</tr>
			<tr>
				<th><label for="sele2">대분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="lCd" objName="lCd" parentID="Y" width="150px" defName="${commonall}" />
					</td>
				<th><label for="sele2">중분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="mCd" objName="mCd" parentID="Y"  width="200px" defName="${commonall}" />
					</td>
				<th><label for="sele2">품목명</label></th>
					<td>
						<input type="text"   id="prdtNm"  name="prdtNm" style="width:50%;" onclick="prdt_clear()">
						<input type="hidden" id="prdtCd"  name="prdtCd" style="width: 20%;">
						<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
					</td>
				<th><label for="sele2">영업 담당자</label></th>
					<td>
						<input type="text" id="salesPrNm" name="salesPrNm" style="width: 50%;" onclick="salesPrNm_clear()">
						<button id="btnSalesPr_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
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
        	<h2>샘플출고현황</h2>
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

<!-- 영업담당자 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_salesPrMaster.jsp" />
</body>
</html>