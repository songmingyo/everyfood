<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){
		
		var date = new Date();
		var toYear = date.getFullYear();
		
		$("#goalYear").val(toYear);
		
		initGoalGrid();
		
		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	    searchEvent); // 검색
		$('#btnNew').unbind().click(null,	        newEvent);    // 신규
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
  

		// 조회조건 입력필드 enter key이벤트 --------------
		$('#goalYear').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : searchEvent(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});
		//-----------------------------------------------------------------

	});


	/* 마스터 데이터  그리드 초기화 */
	function initGoalGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   
				    '사원명'
           			, '구분'
           			, '1월'
           			, '2월'
           			, '3월'
           			, '4월'
           			, '5월'
           			, '6월'
           			, '7월'
           			, '8월'
           			, '9월'
           			, '10월'
           			, '11월'
           			, '12월'
           			, '계'
           ],
			colModel:[
						{name:'salesPrNm'	, index:'salesPrNm'		, sortable:true, editable:false, width:80 , align:"center"}
						,{name:'gubun'		, index:'gubun'			, sortable:true, editable:false, width:80 , align:"center"}
		            	,{name:'mon1'		, index:'mon1'			, sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'mon2'		, index:'mon2'	        , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'mon3'		, index:'mon3'	        , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'mon4'		, index:'mon4'	        , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'mon5'		, index:'mon5'	        , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'mon6'		, index:'mon6'	        , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'mon7'		, index:'mon7'	        , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'mon8'		, index:'mon8'	        , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'mon9'		, index:'mon9'	        , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'mon10'		, index:'mon10'	        , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'mon11'		, index:'mon11'	        , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'mon12'		, index:'mon12'	        , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'totAmt'		, index:'totAmt'	    , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	
		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            ids = $("#tabList").jqGrid('getDataIDs');
	            
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

            rowNum:100
            ,pager: '#pageList'
			,loadui : "disable"													// 이거 안 써주니 로딩 창 같은게 뜸
			,gridview: true														// 그리드 속도
			,viewrecords: true													// 하단에 1/1 또는 데이터가없습니다 추가
			,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
			,rownumbers:true													// rowNumber 표시여부
			,autowidth:true		
			,loadonce : true
			,sortorder : "desc"
		});
	}
	
	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
        
        var date = new Date();
		var toYear = date.getFullYear();
		
		$("#goalYear").val(toYear);
		
		initGoalGrid();
	}

	
	/* 조회 */
	function searchEvent(event){
		if(!$('#goalYear').val()) {
			alert('목표년도를 입력하세요.');
			$('#goalYear').focus();
			return false;
		}
		
		var searchInfo = {};
		
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/as/sal/salSalesGoalList_selList.json'/>",
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
		
		$('#searchForm').attr("action", "<c:url value='/app/as/sal/salSalesGoalList_selExcelDown'/>").submit();

	}
	
	
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
	<input type="hidden" id="searchOrdDt" name="searchOrdDt""/>
	<input type="hidden" id="searchBuyDt" name="searchBuyDt""/>
	<sec:csrfInput/>
	<fieldset>
	<legend>영업목표현황</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>영업목표현황 검색조건</caption>
		<colgroup>
			<col width="100">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">목표년도</label></th>
					<td>
						<input type="text"   id="goalYear"  name="goalYear" style="width:5%;"> 
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
 		<div  style="padding-left: 5px; ">
 		<div class="tit_area">
			<h2 class="subhead">영업목표현황</h2>
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