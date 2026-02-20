<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>


<script type="text/javascript">

	$(document).ready(function(){
		
		$("#searchForm #searchStartDt, #searchForm #searchEndDt").datepicker();
		
		initDspListGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
		$('#btnExcel').unbind().click(null,	        excelEvent);  // 엑셀
		
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
	function initDspListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   
				    '일자'
				    , '창고명'
				    , '품목코드'
           			, '품명'
           			, '규격'           			
           			, '구분'           			
           			, '수량'
           			, '단가'
           			, '금액'
           			, '비고'           			
           			, '등록자'
           			, '등록시간'
           ],
			colModel:[
						{name:'dspDt'			, index:'dspDt'			, sortable:true, width:100 , align:"center"}
						,{name:'whNm'			, index:'whNm'			, sortable:true, width:100 , align:"center"}
            			,{name:'prdtCd'			, index:'prdtCd'		, sortable:true, width:100 , align:"center"}
		            	,{name:'prdtNm'			, index:'prdtNm'		, sortable:true, width:200 , align:"left"}
		            	,{name:'prdtStd'		, index:'prdtStd'		, sortable:true, width:100 , align:"center"}		            	
		            	,{name:'dspClassNm'		, index:'dspClassNm'	, sortable:true, width:150 , align:"center"}
		            	,{name:'dspQty'			, index:'dspQty'		, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'integer'} 
		            	,{name:'cost'			, index:'cost'			, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'integer'}
		            	,{name:'dspAmt'			, index:'dspAmt'		, sortable:true, sorttype:'number', width:100 , align:"right", formatter:'integer'} 
		            	,{name:'remarks'		, index:'remarks'		, sortable:true, width:200 , align:"left"}
		            	,{name:'regNm'			, index:'regNm'			, sortable:true, width:120 , align:"center"} 
		            	,{name:'regDt'			, index:'regDt'			, sortable:true, width:150 , align:"center"}
		            	
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
				
                var sum_dspQty = gridData.jqGrid('getCol','dspQty', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'dspQty':sum_dspQty });
		    	
		    	var sum_dspAmt = gridData.jqGrid('getCol','dspAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'dspAmt':sum_dspAmt });
	            
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

		$("#tabList").jqGrid('clearGridData');
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
			url:"<c:url value='/app/cs/clsDsplLossList_selList.json'/>",
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
		
		$('#searchForm').attr("action", "<c:url value='/app/cs/clsDsplLossList_selExcelDown'/>").submit();

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
	<sec:csrfInput/>
	<fieldset>
	<legend>폐기/로스현황</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>폐기/로스현황 검색조건</caption>
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
						<input type="text" class="dt" id="searchStartDt" name="searchStartDt"  readonly="readonly" style="width: 40% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="searchEndDt" name="searchEndDt"  readonly="readonly" style="width: 40% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/>
					</td>
				<th><label for="sele2">폐기로스구분</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="searchDspClass" objName="searchDspClass" parentID="M015" defName="${commonall}" /> <%--전체 --%>
					</td>
				<th><label for="sele2">창고구분</label></th>
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
					<td colspan=5>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="searchMCd" objName="searchMCd" parentID="Y" defName="${commonall}"/>
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
			<h2 class="subhead">폐기/로스현황</h2>
			<div class="btn_l">
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 		<%-- 엑셀 --%>
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