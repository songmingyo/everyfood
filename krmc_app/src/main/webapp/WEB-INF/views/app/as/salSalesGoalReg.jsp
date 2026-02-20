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
		$('#btnSave').unbind().click(null,	    	saveEvent); 	// 저장
		
		
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
           			
           			, '사원번호'
           ],
			colModel:[
						{name:'salesPrNm'		, index:'salesPrNm'		, sortable:true, editable:false, width:80 , align:"center"}
		            	,{name:'goalAmt01'		, index:'goalAmt01'		, sortable:true, editable:true, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'goalAmt02'		, index:'goalAmt02'	    , sortable:true, editable:true, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'goalAmt03'		, index:'goalAmt03'	    , sortable:true, editable:true, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'goalAmt04'		, index:'goalAmt04'	    , sortable:true, editable:true, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'goalAmt05'		, index:'goalAmt05'	    , sortable:true, editable:true, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'goalAmt06'		, index:'goalAmt06'	    , sortable:true, editable:true, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'goalAmt07'		, index:'goalAmt07'	    , sortable:true, editable:true, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'goalAmt08'		, index:'goalAmt08'	    , sortable:true, editable:true, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'goalAmt09'		, index:'goalAmt09'	    , sortable:true, editable:true, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'goalAmt10'		, index:'goalAmt10'	    , sortable:true, editable:true, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'goalAmt11'		, index:'goalAmt11'	    , sortable:true, editable:true, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'goalAmt12'		, index:'goalAmt12'	    , sortable:true, editable:true, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'goalTot'		, index:'goalTot'	    , sortable:true, editable:false, width:50 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	
		            	,{name:'salesPrCd'		, index:'salesPrCd'		, sortable:true, editable:false, width:80 , align:"center", hidden:true}
		    ],
			gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	        	// ===================== Footer Sum
	        	var gridData = $("#tabList");
				gridData.jqGrid('footerData', 'set', { 'salesPrNm' : '합계' });
				
				var sum_goalAmt01 = gridData.jqGrid('getCol','goalAmt01', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalAmt01':sum_goalAmt01 });
			    
			    var sum_goalAmt02 = gridData.jqGrid('getCol','goalAmt02', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalAmt02':sum_goalAmt02 });
			    
			    var sum_goalAmt03 = gridData.jqGrid('getCol','goalAmt03', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalAmt03':sum_goalAmt03 });
			    
			    var sum_goalAmt04 = gridData.jqGrid('getCol','goalAmt04', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalAmt04':sum_goalAmt04 });
			    
			    var sum_goalAmt05 = gridData.jqGrid('getCol','goalAmt05', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalAmt05':sum_goalAmt05 });
			    
			    var sum_goalAmt06 = gridData.jqGrid('getCol','goalAmt06', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalAmt06':sum_goalAmt06 });
			    
			    var sum_goalAmt07 = gridData.jqGrid('getCol','goalAmt07', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalAmt07':sum_goalAmt07 });
			    
			    var sum_goalAmt08 = gridData.jqGrid('getCol','goalAmt08', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalAmt08':sum_goalAmt08 });
			    
			    var sum_goalAmt09 = gridData.jqGrid('getCol','goalAmt09', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalAmt09':sum_goalAmt09 });
			    
			    var sum_goalAmt10 = gridData.jqGrid('getCol','goalAmt10', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalAmt10':sum_goalAmt10 });
			    
			    var sum_goalAmt11 = gridData.jqGrid('getCol','goalAmt11', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalAmt11':sum_goalAmt11 });
			    
			    var sum_goalAmt12 = gridData.jqGrid('getCol','goalAmt12', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalAmt12':sum_goalAmt12 });
			    
			    var sum_goalTot = gridData.jqGrid('getCol','goalTot', false, 'sum');
			    gridData.jqGrid('footerData', 'set', { 'goalTot':sum_goalTot });
			    
			    
//			    $('table.ui-jqgrid-ftable tr:first').children("td").css("background-color", "#dfeffc");
			    $('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
	            
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
	        afterEditCell: function(rowid, name, val, iRow, iCol){
				$("#" + rowid + "_" + cellname).blur(function(){
			        $("#tabList").jqGrid("saveCell",iRow,iCol);
			    });
		    },
		    afterSaveCell: function(rowid, name, val, iRow, iCol) {
		    	
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
			,cellEdit : true
			,sortorder : "desc"
			,footerrow : true
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
		
		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
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
			searchInfo[this.name] = $(this).val()
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/as/sal/salSalesGoalReg_selList.json'/>",
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
	
	
	/* 저장 */
	function saveEvent(event){
		
		$("#dataDetailList").editCell(0, 0, false);

		if(!confirm("저장하시겠습니까?")){return;}
		
		var saveData = {'salSalesGoalRegList'  : $("#tabList").getRowData()
				        , 'goalYear'        : $('#goalYear').val()
		               };

		$.ajax({
		    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		    url  : "<c:url value='/app/as/sal/salSalesGoalReg_insList.json'/>",

		    data : JSON.stringify(saveData),
	 	    success : function(data){

	 	    	if(data.msgCd =="0") {
					alert("신규 : "+ data.rtnValue01+"건  |  수정 : "+data.rtnValue02+"건이 처리 정상 되었습니다.");
					
	   				searchEvent();			//  재조회
			   	}else{
					alert("처리중 오류가 발생하였습니다. Code : "+data.msgCd+ "Message : "+data.message)
				}
	  	    }
		});
	}


	
	/* 엑셀  */
	function excelEvent(){
		
		$('#searchForm').attr("action", "<c:url value='/app/as/sal/salSalesGoalReg_selExcelDown'/>").submit();

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
	<legend>영업목표등록</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>영업목표등록 검색조건</caption>
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
			<h2 class="subhead">영업목표등록</h2>
			<div class="btn_l">
				<html:button id="btnNew"   			name="btnNew"      		auth="insert" /> 		<%-- 신규 --%>
				<html:button id="btnSearch" 		name="btnSearch"   		auth="select" /> 		<%-- 조회 --%>
				<html:button id="btnExcel" 			name="btnExcel"   		auth="excel" /> 		<%-- 엑셀 --%>
				<html:button id="btnSave" 			name="btnSave"   		auth="save" /> 			<%-- 저장 --%>
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