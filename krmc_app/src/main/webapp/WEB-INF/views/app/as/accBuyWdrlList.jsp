<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#startDt").datepicker();
		$("#endDt").datepicker();
		
		initAccBuyWdrlListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	    newEvent);    // 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
			
		$("#btnBuyCd").unbind().click(function(){
			findSalesMst();
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
		$('#buyNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findBuyMst(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		/*-----------------------------------------------------------------*/

		
	});
	
	
	
	/*매출처 찾기팝업 호출 */
	function findBuyMst(){
		commonBuyMstFindLayer('','',$("#buyNm").val(), setBuyMstFindLayer);
	}
	/*매출처 콜백(후처리 함수 )*/
	function setBuyMstFindLayer(data) {
		$("#buyCd").val(data.buyCd);
		$("#buyNm").val(data.buyNm);
	}
	
	
	/* 마스터 데이터  그리드 초기화 */
	function initAccBuyWdrlListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '지급일자'
            			, '매입처명'
            			, '송금'
            			, '현금'
            			, '카드'
            			, '어음'
            			, '장려금'
            			, '수수료'
            			, '수입'
            			, '기타'
            			, '합계'
            			, '지급조건'

           ]
			,colModel:[
		                 {name:'wdrlDt'		    		, index:'wdrlDt'		    , sortable:true, width:120 , align:"center"}
		            	,{name:'buyNm'	         		, index:'buyNm'		    	, sortable:true, width:180, align:"left"}
		            	,{name:'accountAmt'				, index:'accountAmt'		, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'cashAmt'		        , index:'cashAmt'			, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'cardAmt'		    	, index:'cardAmt'			, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'noteAmt'	  			, index:'noteAmt'			, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'subsidyAmt'		    	, index:'subsidyAmt'	    , sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'vatAmt'	       		 	, index:'vatAmt'		    , sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'incomeAmt'	     		, index:'incomeAmt'	  	    , sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'etcAmt'					, index:'etcAmt'	   		, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'totAmt'		  	        , index:'totAmt'			, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'setlCon'		        , index:'setlCon'		    , sortable:true, width:100, align:"left"}
		        
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'wdrlDt' : '합계' });
				
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
				//alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
            }
            ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

            	if (id == 'blankRow') return;
           		var rowdata = $(this).getRowData(id);

                detailEvent(rowdata);
           }

           ,rowNum:-1
           ,loadui : "disable"
           ,gridview:    true
           //,pager: '#pageList'
           ,sortname: 'buyCd'
           ,sortorder: 'asc'
           ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
           ,viewrecords: true
           ,scroll : false
           ,rownumbers:true
           //,shrinkToFit : false
           ,loadonce:true
           ,footerrow: true
           ,autowidth:true
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
			url:"<c:url value='/app/as/account/accBuyWdrlList_selList.json'/>",
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
	}
	
	/* 엑셀  */
	function excelEvent(){
		$('#searchForm').attr("action", "<c:url value='/app/as/account/accBuyWdrlList_selExcelDown'/>").submit();
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
	<legend>매입처별지급현황</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매입처 검색조건</caption>
		<colgroup>
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">일자</label></th>
					<td>
						<input type="text" class="dt" id="startDt" name="startDt" style="width: 20% !important;"  maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="endDt" name="endDt" style="width: 20% !important;" maxlength="8" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"  />
					</td>
				<th><label for="sele2">매입처명</label></th>
				<td>
					<input type="text"   id="buyNm"    name="buyNm" style="width:30%;" autocomplete="off">
					<input type="hidden" id="buyCd"    name="buyCd" style="width:70%;">
					<button id="btnBuyCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
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
        	<h2>매입처별지급현황</h2>
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

<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />


</body>
</html>