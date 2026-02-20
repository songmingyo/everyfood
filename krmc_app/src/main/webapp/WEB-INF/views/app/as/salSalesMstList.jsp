<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #srchFrDt, #searchForm #srchToDt").datepicker();
		
		initDspListGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
	
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
  

	});


	/* 마스터 데이터  그리드 초기화 */
	function initDspListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   
				    '거래시작일'
				    , '거래처명'
				    , '매출처구분'
				    , '업태'
				    , '종목'
				    , '출고금액'
				    , '매출이익'
				    , '마진율(%)'
				    
//				    , '거래품목수'
//         			, '매장수'
           			, '결재조건'           			
//           			, '에상매출액'           			
           			, '장려금'
           			, '보증금'
           			, '여신한도'           			
           			, '물류대행사'
           			, '개설자'
           			, '담당자'
           			, '거래종료일'
           ],
			colModel:[
						{name:'startDt'			, index:'startDt'		, sortable:true, width:100 , align:"center"}
						,{name:'salesNm'		, index:'salesNm'		, sortable:true, width:300, align:"left"}
						,{name:'salesClassNm'	, index:'salesClassNm'	, sortable:true, width:100, align:"center"}
						,{name:'busiCon'		, index:'busiCon'		, width:100 , align:"center"}
						,{name:'busiType'		, index:'busiType'		, width:150 , align:"center"}
						
						,{name:'pureAmt'		, index:'pureAmt'		, width:120 , align:"right",	formatter:'integer'}
						,{name:'profitAmt3'		, index:'profitAmt3'	, width:120 , align:"right",	formatter:'integer'}
						,{name:'marRate3'		, index:'marRate3'		, width:80 , align:"right"}
												
						
//            			,{name:'cnt'			, index:'cnt'			, width:100 , align:"right"}
//		            	,{name:'storeCnt'		, index:'storeCnt'		, width:60  , align:"left"}
		            	,{name:'setlCon'		, index:'setlCon'		, width:80 , align:"center"}		            	
//		            	,{name:'expSalesAmt'	, index:'expSalesAmt'	, width:100 , align:"center",	foramatter:'integer'}
		            	,{name:'subsidyRate'	, index:'subsidyRate'	, width:100 , align:"right"} 
		            	,{name:'warrAmt'		, index:'warrAmt'		, width:100 , align:"right",	formatter:'integer'}
		            	,{name:'creLim'			, index:'creLim'		, width:120 , align:"right",  	formatter:'integer'}
		            	,{name:'carNm'			, index:'carNm'			, width:120 , align:"center"}
		            	,{name:'newSalesPrCdNm'	, index:'newSalesPrCdNm', width:120 , align:"center"}
		            	,{name:'salesPrCdNm'	, index:'salesPrCdNm'	, width:100 , align:"center"}
		            	,{name:'endDt'			, index:'endDt'			, sortable:true, width:150 , align:"center"}
		            	
		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount)
                							  .attr("style", "text-align: center;");
	            
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

            rowNum:1000,
            pager: '#pageList',
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
			url:"<c:url value='/app/as/sal/salSalesMstList_selList.json'/>",
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
		$('#searchForm').attr("action", "<c:url value='/app/as/sal/salSalesMstList_selExcelDown'/>").submit();
	}
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>

<c:set var="tody"  value="${now}" />



<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
	<sec:csrfInput/>
	<fieldset>
	<legend>영업신규현황</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>영업신규현황 검색조건</caption>
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
						<input type="text" class="dt" id="srchFrDt" name="srchFrDt"  style="width: 25% !important;" maxlength="8" value="${util:getNowDateFmt('yyyy')}-01-01"  />
<%--							<input type="text" class="dt" id="srchFrDt" name="srchFrDt"  style="width: 25% !important;" maxlength="8"  /> --%> 
						<input type="text" class="dt" id="srchToDt" name="srchToDt"		value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"	  style="width: 25% !important;" />
					</td>
				<th><label for="sele2">개설자</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="SALESPRCD"	objId="srchNewSalesPrCd" objName="srchNewSalesPrCd" parentID="Y"  defName="${commonall}"/>						
					</td>	
				<th><label for="sele2">영업사원</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="SALESPRCD"	objId="srchSalesPrCd" objName="srchSalesPrCd" parentID="Y"  defName="${commonall}"/>						
					</td>
				<th><label for="sele2">매출처구분</label></th>
				<td>
					<html:codeTag comType="SELECT" objId="srchSalesClass" objName="srchSalesClass" parentID="M013"  defName="${commonall}"  />
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
			<h2 class="subhead">영업신규현황</h2>
			<div class="btn_l">
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 						<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 						<%-- 엑셀 --%>
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