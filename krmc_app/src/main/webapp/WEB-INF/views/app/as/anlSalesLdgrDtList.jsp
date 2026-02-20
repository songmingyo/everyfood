<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){


		initAnlSalesLdgrDtListGrid();
		
		$("#startDt").datepicker();
		$("#endDt").datepicker();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	    newEvent);    // 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
		$('#btnPrt').unbind().click(null,		eventPrt); 	 // 출력
			
		$("#btnSalesCd").unbind().click(function(){
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
		$('#salesNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findSalesMst(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		/*-----------------------------------------------------------------*/
		
	});	
	
	
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
		
        //Grid 초기화
		$("#tabList").jqGrid('clearGridData');
	}
	

	/* 마스터 데이터  그리드 초기화 */
	function initAnlSalesLdgrDtListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '일자'
            			, '구분'
            			, '매출처명'
            			, '품명'
            			, '규격'
            			, '수량'
            			, '단가'
            			, '공급가'
            			, '부가세'
            			, '합계(VAT포함)'
            			, '현금'
            			, '어음'   
            			, '장려금'
            			, '기타'
            			, '당월현잔액'
           ]
			,colModel:[
		                 {name:'dtGubun'		   	, index:'dtGubun'		    , sortable:true, width:80 , align:"center"}
		            	,{name:'gubun'	         	, index:'gubun'		    	, sortable:true, width:80, align:"center"}
		            	,{name:'salesNm'		    , index:'salesNm'			, sortable:true, width:200, align:"left"}
		            	,{name:'prdtNm'		    	, index:'prdtNm'			, sortable:true, width:180, align:"left"}
		            	,{name:'prdtStd'	  		, index:'prdtStd'			, sortable:true, width:100, align:"center"}
		            	,{name:'qty'		   	 	, index:'qty'	   			, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'price'	   		 	, index:'price'		   		, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'pureAmt'	    	, index:'pureAmt'	  	    , sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'vatAmt'				, index:'vatAmt'	   		, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'totAmt'		        , index:'totAmt'			, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'paidAmt1'	   	 	, index:'paidAmt1'		    , sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'paidAmt2'	    	, index:'paidAmt2'	  	    , sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'paidAmt3'			, index:'paidAmt3'	   		, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'paidAmt4'		    , index:'paidAmt4'			, sortable:true, width:100, align:"right", formatter:'integer'}
		            	,{name:'balAmt'		        , index:'balAmt'			, sortable:true, width:100, align:"right", formatter:'integer'}
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount)	            	
                							  .attr("style", "text-align: center;");
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

           ,rowNum:1000
           ,loadui : "disable"
           ,gridview:    true
           ,pager: '#pageList'
           ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
           ,viewrecords: true
           ,scroll : false
           ,rownumbers:true
           ,shrinkToFit : false
           ,loadonce:true
           ,autowidth:true
		});
		
		//2줄 헤더
		jQuery("#tabList").jqGrid('setGroupHeaders', {
			useColSpanStyle: true, 
			groupHeaders:[
				{startColumnName: 'paidAmt1', numberOfColumns: 4, titleText: '입금내역'},
			],
		});
	}


	/* grid1container / tabList Data 조회 */
	function searchEvent(event){
		
	    var searchInfo = {};
  	      
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');		
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/as/anlys/anlSalesLdgrDtList_selList.json'/>",
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
		let gridObj = $("#tabList");	//grid Obj
		let excelName = "매출처원장(일별)";	//엑셀 다운로드 명
		
		exportExcel(gridObj, excelName);
		//$('#searchForm').attr("action", "<c:url value='/app/as/anlys/anlSalesLdgrDtList_selExcelDown'/>").submit();
	}
	
	/*PDF Report*/
	function eventPrt(){
		
		var url = "<c:url value="/app/as/anlys/anlSalesLdgrDtList_prtList" />";
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
	}
	
	function sales_clear(event){
		
		$('#salesNm').val('');
		$('#salesCd').val('');
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
	<legend>매출처원장(일별)</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처원장(일별) 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="10%">
			<col width="100">
			<col width="10%">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">일자</label></th>
					<td colspan="2">
						<input type="text" class="dt" id="startDt" name="startDt" style="width: 40% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
						<input type="text" class="dt" id="endDt" name="endDt" style="width: 40% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/>
					</td>
				<th><label for="sele2">매출처명</label></th>
				<td>
					<input type="text"   id="salesNm"    name="salesNm" style="width:20%;"  onclick="sales_clear()">
					<input type="hidden" id="salesCd"    name="salesCd" style="width:40%;">
					<button id="btnSalesCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
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
        	<h2>매출처원장(일별)</h2>
        	<div>
          		<html:button id="btnNew"   		name="btnNew" 	auth="insert" />
        		<html:button id="btnSearch" 	name="btnSearch"	auth="select" />
 				<html:button id="btnExcel"    	name="btnExcel"   	auth="excel" />
 				<html:button id="btnPrt" 		name="btnPrt"  	auth="select"  value="출력"  /> 
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

