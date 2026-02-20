<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #searchStkDt").datepicker();
		
		$("#searchStkDt").datepicker('setDate','c');
		
		initInventoryGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
		
		$('#btnStkPrt').unbind().click(null,		stkPrtEvent);
		
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
		$('#searchPrdtNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findPrdtMst(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		//-----------------------------------------------------------------

	});


	/* 마스터 데이터  그리드 초기화 */
	function initInventoryGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   
				    '재고일자'
				    , '품목코드'
           			, '랙번호'
           			, '품명'
           			, '규격'
//           			, '박스입수'
           			
           			, '전일재고'
           			
           			, '입고'
           			, '창고이동'
           			, '반품'
           			, '입고계'
           			
           			, '출고'
           			, '창고이동'
           			, '반품'
           			, '출고계'
           			
           			, '전산 재고수량(낱개)'
           			, '전산 재고수량(박스)'
           			, '단가'
           			, '전산 재고금액'
					, '최종 입고 소비기한'
           ],
			colModel:[
						{name:'stkDt'			, index:'stkDt'			, hidden:true, sortable:true, width:100 , align:"center"}
            			,{name:'prdtCd'			, index:'prdtCd'		, sortable:true, width:100 , align:"center"}
		            	,{name:'lackNo1'		, index:'lackNo1'		, sortable:true, width:100 , align:"center"}
		            	,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, width:180 , align:"left"}
		            	,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, width:100 , align:"center"}
//		            	,{name:'qtyBox'			, index:'prdtStd'		, sortable:true, width:100 , align:"center"}
		            	
		            	,{name:'prevStkQty'		, index:'prevStkQty'	, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
						
		            	
		            	,{name:'buyQty'			, index:'buyQty'		, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}} 
		            	,{name:'inWhQty'		, index:'inWhQty'		, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'buyRtnQty'		, index:'buyRtnQty'		, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}} 
		            	,{name:'buyTotQty'		, index:'buyTotQty'		, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}} 
		            	
		            	,{name:'dlvQty'			, index:'dlvQty'		, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}} 
		            	,{name:'outWhQty'		, index:'outWhQty'		, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'dlvRtnQty'		, index:'dlvRtnQty'		, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'dlvTotQty'		, index:'dlvTotQty'		, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	
		            	,{name:'stkQty'			, index:'stkQty'		, sortable:true, sorttype:'number', width:120 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
		            	,{name:'boxQtyComp'		, index:'boxQtyComp'	, sortable:true, sorttype:'number', width:120 , align:"center"}
		            	,{name:'cost'			, index:'cost'			, sortable:true, width:100 , align:"right", formatter:'integer'}
		            	,{name:'stkAmt'			, index:'stkAmt'		, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'integer'}
						,{name:'termVal'		, index:'termVal'		, sortable:true, width:120 , align:"center"}
		            	
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
				gridData.jqGrid('footerData', 'set', { 'prdtNm' : '합계' });
				
                var sum_prevStkQty = gridData.jqGrid('getCol','prevStkQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'prevStkQty':sum_prevStkQty });
		    	
		    	var sum_buyQty = gridData.jqGrid('getCol','buyQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'buyQty':sum_buyQty });
		    	
		    	var sum_inWhQty = gridData.jqGrid('getCol','inWhQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'inWhQty':sum_inWhQty });
		    	
		    	var sum_buyRtnQty = gridData.jqGrid('getCol','buyRtnQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'buyRtnQty':sum_buyRtnQty });
		    	
		    	var sum_buyTotQty = gridData.jqGrid('getCol','buyTotQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'buyTotQty':sum_buyTotQty });
		    	
		    	var sum_dlvQty = gridData.jqGrid('getCol','dlvQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'dlvQty':sum_dlvQty });
		    	
		    	var sum_outWhQty = gridData.jqGrid('getCol','outWhQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'outWhQty':sum_outWhQty });
		    	
		    	var sum_dlvRtnQty = gridData.jqGrid('getCol','dlvRtnQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'dlvRtnQty':sum_dlvRtnQty });
		    	
		    	var sum_dlvTotQty = gridData.jqGrid('getCol','dlvTotQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'dlvTotQty':sum_dlvTotQty });
		    	
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
            onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

            	if (id == 'blankRow') return;
           		var rowdata = $(this).getRowData(id);

           		setSaletStkQty(rowdata);       
            },


            rowNum:-1,
            //pager: '#pageList',
			loadui : "disable",													// 이거 안 써주니 로딩 창 같은게 뜸
			gridview : true,													// 그리드 속도
			viewrecords : true,													// 하단에 1/1 또는 데이터가없습니다 추가
			rownumbers : true,													// rowNumber 표시여부
			autowidth : true,
			shrinkToFit : false,
			loadonce : true,
			footerrow: true,
			sortorder : "desc"
		});
		
		//2줄 헤더
		jQuery("#tabList").jqGrid('setGroupHeaders', {
			useColSpanStyle: true, 
			groupHeaders:[
				{startColumnName: 'buyQty', numberOfColumns: 4, titleText: '입고현황'},
				{startColumnName: 'dlvQty', numberOfColumns: 4, titleText: '출고현황'},
			],
		});
	}
	
	/* 품목별 당월, 전월, 전전월 출고량 */
	function setSaletStkQty(rowData){
		var searchData = 
		       {'prdtCd'  : rowData.prdtCd 
		       };		
		
		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/cs/selPrdtQtyMonth.json"/>',
			data : JSON.stringify(searchData),
			success : function(data) {
				if(data != null){
					$("#saleQty1").val(data.boxQtyComp1);
					$("#saleQty2").val(data.boxQtyComp2);
					$("#saleQty3").val(data.boxQtyComp3);
				}
			}
		});
	}
	
	
	/*상품 찾기팝업 호출 */
	function findPrdtMst() {
		commonPrdtMstFindLayer('','',$("#searchPrdtNm").val(), '', setPrdtMstFindLayer);
	}
	
	/*상품 콜백(후처리 함수 )*/
	function setPrdtMstFindLayer(data) {
		if (data != null){
			$("#searchPrdtCd").val(data.prdtCd);
			$("#searchPrdtNm").val(data.prdtNm);
		}
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
        
		$("#searchStkDt").datepicker('setDate','c');
		
		$("#tabList").jqGrid('clearGridData');
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
			url:"<c:url value='/app/cs/clsDayStkList_selList.json'/>",
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
		
		$("#excelStkDt").val($("#searchStkDt").val().replaceAll('-',''));
		
		$('#searchForm').attr("action", "<c:url value='/app/cs/clsDayStkList_selExcelDown'/>").submit();

	}

	
	/*PDF Report*/
	function stkPrtEvent(){
		
		$("#excelStkDt").val($("#searchStkDt").val().replaceAll('-',''));
		
		var url = "<c:url value="/app/cs/stkListPrint" />";
		var target = "PDFPrint";
		var agt = navigator.userAgent.toLowerCase();

		if (agt.indexOf("msie") != -1) {		
			searchForm.action = url;
			searchForm.submit();
		}
		else {
			window.open('','PDFPrint','toolbar=no,resizable=no,location=0,scrollbars=0,width=800,height=700,top=50,left=200');
			searchForm.action=url;
			searchForm.target="PDFPrint";
			searchForm.submit();
		}
		
		
		
		searchForm.action="<c:url value="/app/cs/stkListPrint" />";
		searchForm.submit();
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
    <input type="hidden" id="excelStkDt" name="excelStkDt""/>
	<sec:csrfInput/>
	<fieldset>
	<legend>일재고조회</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>일재고조회 검색조건</caption>
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
				<th><label for="sele2">조회일</label></th>
					<td>
						<input type="text" class="dt" id="searchStkDt" name="searchStkDt"  readonly="readonly" style="width: 100px !important;" />
					</td>
				<th><label for="sele2">저장형태</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchStrgType" objName="searchStrgType" parentID="M001" defName="${commonall}" /> <%--전체 --%>
					</td>
				<th><label for="sele2">창고</label></th>
					<td>
			 			<html:codeTag comType="SELECT" dataType="PRC3" objId="searchWhCd" objName="searchWhCd" height="24px" parentID="Y" defValue=""  defName="${commonall}"/>
			 		</td>
				<th><label for="sele2">품목</label></th>
					<td>
						<input type="text"   id="searchPrdtNm"  name="searchPrdtNm" style="width:50%;" onclick="prdt_clear()">
						<input type="hidden" id="searchPrdtCd"  name="searchPrdtCd" >
						<button id="btnPrdtCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
					</td>
			</tr>
			<tr>
				<th><label for="sele2">대분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="searchLCd" objName="searchLCd" parentID="Y" defName="${commonall}"/>
					</td>
				<th><label for="sele2">중분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="searchMCd" objName="searchMCd" parentID="Y" defName="${commonall}" width="90%" />
					</td>
					
				<th><label for="sele2">출고량</label></th>
				<td colspan="3">
					당월 : <input type="text"   	id="saleQty1"  name="saleQty1" 		style="width:15%;" readonly="readonly">
					전월 : <input type="text"   	id="saleQty2"  name="saleQty2" 		style="width:15%;" readonly="readonly">
					전전월 : <input type="text"   id="saleQty3"  name="saleQty3" 		style="width:15%;" readonly="readonly">
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
			<h2 class="subhead">일재고등록</h2>
			<div class="btn_l">
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 		<%-- 엑셀 --%>
<%-- 				<html:button id="btnStkPrt" 		name="btnStkPrt"   		auth="print" /> 		인쇄 --%>
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

<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />


<!-- CONTENT- BODY end ----------------------------------- -->

</div>
</body>
</html>