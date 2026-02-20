```````````````````````````````````````<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("#startDt").datepicker();
		$("#endDt").datepicker();
		
		initBuyRcptListGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
		$('#btnOrdPrt').unbind().click(null,		ordPrt); 	 // 발주서출력
		
		$("#btnBuyCd").unbind().click(function(){
			findBuyMst();
		});		<%--매입처 팝업 찾아가기--%>
		
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
	function initBuyRcptListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '매입처명'
            			, 'Lack번호'
            			, '창고명'
            			, '품목코드'
            			, '품목명'
            			, '입고일자'
            			, '규격'
            			, '단가'
            			, '수량'
            			, '공급가'
            			, '부가세'
            			, '합계금액'
            			, '발주일자'
            			, '유통기한'
            			, '소비기간'
            			, '비고'
            			, '매입처상품코드'
           ]
			,colModel:[
		                 {name:'buyNm'			, index:'buyNm'			, sortable:true, width:180, align:"left"}
		            	,{name:'lackNo1'	   	, index:'lackNo1'		, sortable:true, width:100, align:"center"}
		            	,{name:'whNm'			, index:'whNm'			, sortable:true, width:100, align:"center"}
		            	,{name:'prdtCd'			, index:'prdtCd'		, sortable:true, width:100, align:"center"}
		             	,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, width:180, align:"left"}
		            	,{name:'buyDt'			, index:'buyDt'			, sortable:true, width:100, align:"center"}
		            	,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, width:100, align:"center"}
		            	,{name:'cost'			, index:'cost'			, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'buyQty'			, index:'buyQty'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'pureAmt'		, index:'pureAmt'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'vatAmt'			, index:'vatAmt'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'totAmt'			, index:'totAmt'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'ordDt'			, index:'ordDt'			, sortable:true, width:100, align:"center"}
		            	,{name:'exprtDt'		, index:'exprtDt'		, sortable:true, width:100, align:"center"}
		            	,{name:'termVal'		, index:'termVal'		, sortable:true, width:100, align:"center"}
		            	,{name:'remarks'		, index:'remarks'		, sortable:true, width:100, align:"left"}
		                ,{name:'buyPrdtCd'		, index:'buyPrdtCd'		, sortable:true, width:100, align:"center"}
		     ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				/* 조회후 데이터가 한건이라도 있을경우  */
				if(allRows.length > 0 && $("#blankRow").val() != ""){
					$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
					
					// grid rowNum 재설정 (-1로 설정한 경우 row가 많으면 sort 시 row수가 감소)
					//gridData.setGridParam({rowNum:allRows.length});
					
				}
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'buyDt' : '합계' });
				
                var sum_buyQty = gridData.jqGrid('getCol','buyQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'buyQty':sum_buyQty });
		    	
		
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
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();
	                $(this).addRowData("blankRow", msg);
	            }
	        	$(window).resize();
	        },
			loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');
				return false;
            },
            onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

            	if (id == 'blankRow') return;
           		var rowdata = $(this).getRowData(id);

           		setPrdtStkQty(rowdata);
            }

            ,rowNum:-1
            //,pager: '#pageList'
			,loadui : "disable"													// 이거 안 써주니 로딩 창 같은게 뜸
			,gridview: true														// 그리드 속도
			,viewrecords: true													// 하단에 1/1 또는 데이터가없습니다 추가
			,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
			,rownumbers : true													// rowNumber 표시여부
			,autowidth:true
			,loadonce : true
			,footerrow: true														// 전체 합계를 낼때 사용
			,shrinkToFit : false
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
			
			$('#ordQty').focus();
		}
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
		url:"<c:url value='/app/br/buy/buyRcptList_selList.json'/>",
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
		
		<!--setMarRateFooter();-->
	}
	
	/* 품목별 재고 */
	function setPrdtStkQty(rowData){
		var searchData = 
		       {'prdtCd'  : rowData.prdtCd 
		       };		
		
		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/common/selectPrdtStkQty"/>',
			data : JSON.stringify(searchData),
			success : function(data) {
				if(data != null){
					$("#haStkQty").val(data.haStkQty);
					$("#yeoStkQty").val(data.yeoStkQty);
				}
			}
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
			
			$('#searchPrdtNm').focus();
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
			
			$('#ordQty').focus();
		}
	}
	
	/*PDF Report*/
	function ordPrt(){
		searchForm.action="<c:url value="/app/br/buy/buyRcptListPrint" />";
		searchForm.submit();
	}
	
	/* 엑셀  */
	function excelEvent(){
		$('#searchForm').attr("action", "<c:url value='/app/br/buy/buyRcptList_selExcelDown'/>").submit();
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
	<input type="hidden" id="searchOrdDt" name="searchOrdDt""/>
	<input type="hidden" id="searchBuyDt" name="searchBuyDt""/>
	<sec:csrfInput/>
	<fieldset>
	<legend>매입처입고현황</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매입처입고현황 검색조건</caption>
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
				<th><label for="sele2">조회일자</label></th>
					<td>
						<input type="text" class="dt" id="startDt" name="startDt"  style="width:37% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="endDt" name="endDt"   style="width:37% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
					</td>
				<th><label for="sele2">매입처명</label></th>
					<td>
						<input type="text"   id="buyNm"    name="buyNm" style="width:70%;" onclick="buy_clear()"  autocomplete="off">
						<input type="hidden" id="buyCd"    name="buyCd" style="width:70%;">
						<button id="btnBuyCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
					</td>
				<th><label for="sele2">품목명</label></th>
					<td colspan=3>
						<input type="text"   id="prdtNm"  name="prdtNm" style="width:40%;" onclick="prdt_clear()"  autocomplete="off">
						<input type="hidden" id="prdtCd"  name="prdtCd" style="width:70%;">
						<button id="btnPrdtCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
			</tr>
			<tr>
				<th><label for="sele2">과면세</td>
					<td>
						<html:codeTag comType="SELECT" objId="taxYn" objName="taxYn" parentID="M005"  defName="${commonall}"   /></td>
					</td>
				<th><label for="sele2">창고</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC3"	objId="whCd" objName="whCd" parentID="Y" defName="${commonall}" />
					</td>
				<th><label for="sele2">대분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="lCd" objName="lCd" parentID="Y" width="90%" defName="${commonall}" />
					</td>
				<th><label for="sele2">중분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="mCd" objName="mCd" parentID="Y"  width="90%" defName="${commonall}" />
					</td>
				
			</tr>
			<tr>
				<th><label for="sele2">하남재고</label></th>
					<td style="border-right:none;">
						<input type="text"   id="haStkQty"  name="haStkQty" style="text-align:right;" readonly >
					</td>
				<th><label for="sele2">여주재고</label></th>
					<td style="border-right:none;">
						<input type="text"   id="yeoStkQty"  name="yeoStkQty" style="text-align:right;" readonly >
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
 		
 		<div class="tit_area">
			<h2 class="subhead">매입처입고현황</h2>
			<div class="btn_l">
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 		<%-- 엑셀 --%>
<%-- 				<html:button id="btnOrdPrt" 		name="btnOrdPrt"   		auth="select"  value="출력"  /> --%>
			</div>
		</div>
	</form>
 	</li>
 	<li style="float: down; width: 100%; ">
   		<!-- centent List -------------------------->
   		<div  style="padding-top: 2px; ">
		
        <div id="grid1container">
        	<table id="tabList" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</li>
 </UL>            

<!-- CONTENT- BODY end ----------------------------------- -->

<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />



<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />

</div>
</body>
</html>