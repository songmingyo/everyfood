 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#buyStartDt").datepicker();
		$("#buyEndDt").datepicker();
		
		initBuyOrderGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
	
		$('#btnNew').unbind().click(null,	    newEvent);    // 신규
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
			
		$("#btnBuyCd").unbind().click(function(){
			findBuyMst();
		});		<%--매입처 팝업 찾아가기--%>
			$("#btnPrdtCd").unbind().click(function(){
			findPrdtMst();
		});		<%--상품 팝업 찾아가기--%>
		
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    $('#tabList').setGridHeight(height-100);	//GRID  LIST
			    
			}catch(e){}
		}).trigger('resize');
		/*----------------------------------------------------------------*/
  

		/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/
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
		/*-----------------------------------------------------------------*/

		
	});
	
	
	/*매입처 찾기팝업 호출 */
	function findBuyMst() {
		commonBuyMstFindLayer('','',$("#buyNm").val(), setBuyMstFindLayer);
	}
	
	/*매입처 콜백(후처리 함수 )*/
	function setBuyMstFindLayer(data) {
		if (data != null){
			$("#buyCd").val(data.buyCd);
			$("#buyNm").val(data.buyNm);
			
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
	}
	

	/* 마스터 데이터  그리드 초기화 */
	function initBuyOrderGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '일자'
            			, '매입처명'
            			, '품목코드'
            			, '품목명'
            			, '규격'
            			, '입고예정일'
            			, '단가'
            			, '수량'
            			, '공급가'
            			, '부가세'
            			, '합계금액'
            			, '비고'
           ]
			,colModel:[
		                 {name:'ordDt'			, index:'ordDt'			, sortable:true, width:90, align:"center"}
		            	,{name:'buyNm'	    	, index:'buyNm'			, sortable:true, width:180, align:"left"}
		            	,{name:'prdtCd'			, index:'prdtCd'		, sortable:true, width:100, align:"center"}
		            	,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, width:180, align:"left"}
		            	,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, width:100, align:"center"}
		            	,{name:'buyDt'			, index:'buyDt'			, sortable:true, width:100, align:"center"}
		            	,{name:'cost'			, index:'cost'			, sortable:true, sorttype:'number', width:80, align:"right", formatter:'integer'}
		            	,{name:'ordQty'			, index:'ordQty'		, sortable:true, sorttype:'number', width:80, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'pureAmt'		, index:'pureAmt'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'vatAmt'			, index:'vatAmt'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'totAmt'			, index:'totAmt'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'remarks'		, index:'remarks'		, sortable:true, width:200, align:"left"}
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
				gridData.jqGrid('footerData', 'set', { 'buyNm' : '계' });
				
                var sum_ordQty = gridData.jqGrid('getCol','ordQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'ordQty':sum_ordQty });
		    	
		
		    	var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
		    	
		    	var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
		    	
		    	
		    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
		    	
	        //    $(window).resize();
	        },
	        loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();
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
			,rownumbers: true													// rowNumber 표시여부
			,autowidth:true		
			,loadonce : true
			//,shrinkToFit : false
			,footerrow: true														// 전체 합계를 낼때 사용
			,sortorder : "desc"
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
			url:"<c:url value='/app/br/buy/buyOrdList_selList.json'/>",
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
		$('#searchForm').attr("action", "<c:url value='/app/br/buy/buyOrdList_selExcelDown'/>").submit();
	}
	
	function buy_clear(event){
		$('#buyNm').val('');
		$('#buyCd').val('');
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
<form id="searchForm" name="searchForm" method="post">
<sec:csrfInput/>
	<fieldset>
	<legend>매입처발주현황</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매입처발주현황</caption>
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
						<input type="text" class="dt" id="buyStartDt" name="buyStartDt"  style="width: 30% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="buyEndDt" name="buyEndDt"   style="width: 30% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
					</td>
				<th><label for="sele2">매입처명</label></th>
					<td>
						<c:choose>
							<c:when test ="${userSession.userType eq 'B1'}"> 
								<input type="text" id="buyNm" name="buyNm" style="width: 80%;" value="${userSession.username}" autocomplete="off" disabled/>
								<input type="hidden" id="buyCd" name="buyCd" style="width: 20%;" value="${userSession.userId}">
							</c:when>
							<c:otherwise>
								<input type="text" id="buyNm" name="buyNm" style="width: 80%;" onclick="sales_clear()" autocomplete="off">
								<input type="hidden" id="buyCd" name="buyCd" style="width: 20%;">
								<button id="btnBuyCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
							</c:otherwise>
						</c:choose>
					
					</td>
				<th><label for="sele2">품목명</label></th>
					<td>
						<input type="text"   id="prdtNm"  name="prdtNm" style="width:70%;" onclick="prdt_clear()" autocomplete="off" >
						<input type="hidden" id="prdtCd"  name="prdtCd" style="width:70%;">
						<button id="btnPrdtCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
			</tr>
			<tr>
				<th><label for="sele2">대분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="lCd" objName="lCd" parentID="Y" defName="${commonall}" />
					</td>
				<th><label for="sele2">중분류</label></th>
					<td colspan=3>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="mCd" objName="mCd" parentID="Y"  defName="${commonall}" />
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
        	<h2>매입처발주현황</h2>
        	<div>        	
        		<html:button id="btnNew"  		name="btnNew"    	auth="insert" /> 		<%-- 신규 --%>
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

<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />


<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />
</body>
</html>