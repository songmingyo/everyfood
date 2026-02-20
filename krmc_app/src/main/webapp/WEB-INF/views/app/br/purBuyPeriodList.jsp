<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#buyStartDt").datepicker();
		$("#buyEndDt").datepicker();
		
		initBuyPeriodListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    	searchEvent); 	// 검색
	    $('#btnInit').unbind().click(null,	    newEvent); 		// 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	       // 액샐 
		$('#btnPeriodPrt').unbind().click(null,		periodPrt);
			
		$("#btnBuyCd").unbind().click(function(){
			findBuyMst();
		});
		
		$("#btnPrdtCd").unbind().click(function(){
			findPrdtMst();
		});		<%--상품 팝업 찾아가기--%>
		
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-100);	//GRID  LIST
			    }
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-150);	//GRID  LIST
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

	
	function periodPrt(){
		$("#buyStartDt").val($("#buyStartDt").val().replaceAll('-',''));
		$("#buyEndDt").val($("#buyEndDt").val().replaceAll('-',''));
		
		searchForm.action="<c:url value="/app/br/buy/periodListPrint" />";
		searchForm.submit();
	}
	

	/* 마스터 데이터  그리드 초기화 */
	function initBuyPeriodListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[	 '매입처명'
            			, '품목코드'
            			, '품목명'
            			, '규격'
            			, '입고수량'
            			, '단가'
            			, '공급가'
            			, '부가세'
            			, '합계금액'
              ]
			,colModel:[
		            	{name:'buyNm'	    	, index:'buyNm'	        , sortable:true, width:180, align:"left"}
		            	,{name:'prdtCd'			, index:'prdtCd'		, sortable:true, width:100, align:"center"}
		            	,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, width:180, align:"left"}
		            	,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, width:100, align:"center"}
		            	,{name:'buyQty'			, index:'buyQty'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
                    	,{name:'cost'			, index:'cost'		    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'pureAmt'		, index:'pureAmt'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'vatAmt'			, index:'vatAmt'	    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'totAmt'			, index:'totAmt'	    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
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
				gridData.jqGrid('footerData', 'set', { 'prdtCd' : '계' });
				
                var sum_buyQty = gridData.jqGrid('getCol','buyQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'buyQty':sum_buyQty });
		    	
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
			url:"<c:url value='/app/br/buy/buyPeriodList_selList.json'/>",
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

	// 엑셀 다운로드
	function excelEvent(){
		$('#searchForm').attr("action", "<c:url value='/app/br/buy/buyPeriodList_selExcelDown'/>").submit();
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
	<legend>기간별매입현황</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>기간별매입현황 검색조건</caption>
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
						<input type="text" class="dt" id="buyStartDt" name="buyStartDt"  style="width: 40% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="buyEndDt" name="buyEndDt"  style="width: 40% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
					</td>
				<th><label for="sele2">매입처명</label></th>
					<td>
						<input type="text"   id="buyNm"    name="buyNm" style="width:70%;" autocomplete="off" >
						<input type="hidden" id="buyCd"    name="buyCd" style="width:70%;">
						<button id="btnBuyCd" class="button btn_find" type="button" ><i class="fa fa-search"></i></button> 
					</td>
				<th><label for="sele2">품목명</label></th>
					<td>
						<input type="text"   id="prdtNm"  name="prdtNm" style="width:80%;"  onclick="prdt_clear()" autocomplete="off">
						<input type="hidden" id="prdtCd"  name="prdtCd" style="width:70%;">
						<button id="btnPrdtCd" class="button btn_find" type="button" ><i class="fa fa-search"></i></button>
					</td>
			</tr>
			<tr>
				<th><label for="sele2">대분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="lCd" objName="lCd" parentID="Y" width="90%" defName="${commonall}" />
					</td>
				<th><label for="sele2">중분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="mCd" objName="mCd" parentID="Y"  width="90%" defName="${commonall}" />
					</td>
				<th><label for="sele2">창고</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC3"	objId="whCd" objName="whCd" parentID="Y" defName="${commonall}" />
					</td>	
			</tr>
		</tbody>
	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->


<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->

 <UL style="width: 100%;">
 	<li style="float: top; width: 100%;">
 	<form name="mainForm" id="mainForm"  >
 		<div  style="padding-left: 5px; ">
 		<div class="tit_area">
			<h2 class="subhead">기간별매입현황</h2>
			<div class="btn_l">
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 		<%-- 엑셀 --%>
<%-- 				<html:button id="btnPeriodPrt" 		name="btnPeriodPrt"   		auth="select"  		value="출력"  /> --%>
			</div>
		</div>
		
	</form>
	</div>
 	</li>
 	<li style="float: down; width: 100%; ">
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="tabList" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</li>
 </UL>            
 
 
<!-- CONTENT- BODY end ----------------------------------- -->

</div>

<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />


<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />
</body>
</html>