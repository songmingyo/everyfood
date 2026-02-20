<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>


<style type="text/css">
	.ui-datepicker-calendar {display: none;}​ 
</style>

<script type="text/javascript">

	$(document).ready(function(){

		$('#yearMn').datepicker({ 
		     changeMonth: true, 
		     changeYear: true, 
		     dateFormat: 'yy-mm', 

		     onClose: function() { 
		        var iMonth = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
		        var iYear = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
		        $("#yearMn").datepicker('setDate', new Date(iYear, iMonth, 1)); 
		     }, 

		     beforeShow: function() { 
		       if ((selDate = $(this).val()).length > 0)  
		       { 
		          iYear = selDate.substring(selDate.length - 4, selDate.length); 
		          iMonth = jQuery.inArray(selDate.substring(0, selDate.length - 5),  
		                   $(this).datepicker('option', 'monthNames')); 
		          $(this).datepicker('option', 'defaultDate', new Date(iYear, iMonth, 1)); 
		          $(this).datepicker('setDate', new Date(iYear, iMonth, 1)); 
		       } 
		    } 
		  }); 
		  
		initCusMonSlipListGrid();

		/* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnNew').unbind().click(null,	    newEvent);    // 신규 
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐 
		$('#btnSales_find').unbind().click(function(){ findSalesMst()}); 	// 매출처찾기 팝업 버튼이벤트
		
		/*Resized to new width as per window -------------------------------*/
        $(window).bind('resize', function() {
		    try{
		        $('#tabList').setGridWidth($('#grid1container').width()); 

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;

			    if(height > 280)	 	{
				    $('#tabList').setGridHeight(height-120);	//GRID  LIST
			    }
		        else if(height < 300){
			        $('#tabList').setGridHeight(height-180);	//GRID  LIST
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

	/* 마스터 데이터  그리드 초기화 */
	function initCusMonSlipListGrid() {
		$("#tabList").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '구분'
            			, '고객사명'
            			, '창고명'
            			, '과세'
            			, '부가세'
            			, '과세합계'
            			, '면세'
            			, '합계'
            	
           ]
			,colModel:[
		                 {name:'salesClassNm'	   	, index:'salesClassNm'		, sortable:true, width:50 , align:"center"}
		            	,{name:'salesNm'	  	 	, index:'salesNm'  			, sortable:true, width:180, align:"left"}
		            	,{name:'whNm'				, index:'whNm'		    	, sortable:true, width:120, align:"center"}
		              	,{name:'pureAmt'	    	, index:'pureAmt'			, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'vatAmt'	  			, index:'vatAmt'			, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'pureTotAmt'	    	, index:'pureTotAmt'		, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		            	,{name:'freeAmt'		    , index:'freeAmt'		    , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
		               	,{name:'totAmt'	       		, index:'totAmt'	    	, sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}

		        
		     ],
		 gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');
				
				/* 조회후 데이터가 한건이라도 있을경우  */
				$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'whNm' : '계' });
				

		    	var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });

		    	var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
		    	
		    	
		    	var sum_pureTotAmt = gridData.jqGrid('getCol','pureTotAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'pureTotAmt':sum_pureTotAmt });
		    	
		     	var sum_freeAmt = gridData.jqGrid('getCol','freeAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'freeAmt':sum_freeAmt });
		    	
		    	
		    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
		    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });	
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
	        beforeProcessing : function(data) {
	        	$("#buyNm").attr("readonly", true);
	        },
			 beforeSelectRow : function(rowId, e) {

				$("#tabList").jqGrid('setColProp', 'salesPrdtCd1', {editable:true});

				return true;
		 	},
			loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');
				return false;
            },

            rowNum:-1
            //pager: '#pageList'
			,loadui : "disable"													// 이거 안 써주니 로딩 창 같은게 뜸
			,gridview: true														// 그리드 속도
			,viewrecords: true													// 하단에 1/1 또는 데이터가없습니다 추가
			,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
			,rownumbers:true													// rowNumber 표시여부
			,autowidth:true		
			,loadonce : true
			,footerrow: true														// 전체 합계를 낼때 사용
			,sortorder : "desc"
		});
	}

	/* grid1container / tabList Data 조회 */
	function searchEvent(event){
			if(!$('#yearMn').val()) {
			alert('조회월을 입력하세요.');
			$('#yearMn').focus();
			return false;
		}
	      var searchInfo = {};
  	      
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val().replaceAll('-','')
		});

		//Grid 초기화
		$("#tabList").jqGrid('clearGridData');		
		
		$("#tabList").jqGrid('setGridParam',{
			url:"<c:url value='/app/sm/custsales/cusMonSlipList_selList.json'/>",
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

	/*매출처 찾기팝업 호출 */
	function findSalesMst(){
		commonSalesMstFindLayer('','',$("#salesNm").val(), '', setSalesMstFindLayer);
	}
	/*매출처 콜백(후처리 함수 )*/
	function setSalesMstFindLayer(data) {
		$("#salesCd").val(data.salesCd);		/*매출처코드*/
		$("#salesNm").val(data.salesNm);		/*매출처명*/
	}


	/* 신규 */
	function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
	}
	
	/* 엑셀  */
	function excelEvent(){
 		$('#searchForm').attr("action", "<c:url value='/app/sm/custsales/cusMonSlipList_selExcelDown'/>").submit();
	}

	function reSetDate(obj) {
		obj.value = obj.value.trim().replaceAll("-","");
		setRightFocus(obj,0);
	}
	
	function isValidYMDThisPageOnly(comp)
	{
		if(!comp.value && comp.value.trim().length <= 0) return;
		if(!isValidYMDStringThisPageOnly(comp.value))
		{
			comp.focus();
			return false;
		}
		
		isDateCheck(comp);
	}
	
	function isValidYMDStringThisPageOnly(strComp)
	{		
		var dates = strComp.trim().replaceAll("-","");

		if(dates.length != 6) return;
		else
		{
			var t_year  = Number(dates.substring(0,4));
			var t_month = Number(dates.substring(4,6));
			
			if (t_year < 1900 || t_year > 9999)
			{ 
				alert('년도 입력오류입니다.!');
				return false;
			}
			if (t_month <1 || t_month > 12)
			{
				alert('해당월 입력오류입니다.!');
				return false;
			}			
		}
		
		return true;
	}
	
	function isDateCheck(obj)
	{
		if(obj.value.replaceAll("-","").length > 4 ) 		
			obj.value	=	obj.value.substring(0,4)+"-"+obj.value.replaceAll("-","").substring(4);

		if(obj.value.length > 7) 	
			obj.value	=	obj.value.substring(0,7)+"-"+obj.value.substring(7,10);

		return obj.value;	
	}
	
	function sales_clear(){
		$("#salesNm").val('');
		$("#salesCd").val('');
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
	<legend>매출처 관리</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>매출처 검색조건</caption>
		<colgroup>
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
			<col width="150">
			<col width="*">
		</colgroup>
		<tbody id="_search">
			<tr>
				<th><label for="sele2">조회월</label></th>
					<td>
						<input type="text" name="yearMn" id="yearMn" maxlength="6" style="width:70px" value="${util:getNowDateFmt('yyyy-MM')}" onFocus="reSetDate(this);" onkeypress="onlyNumber();" onblur="isValidYMDThisPageOnly(this);"/> 
					</td>
					<th><label for="sele2">구분</label></th>
					<td>
					<html:codeTag comType="SELECT" objId="hqClass" objName="hqClass" parentID="M004"  />
				</td>
				<th><label for="sele2">매출처구분</label></th>
					<td>
					<html:codeTag comType="SELECT" objId="salesClass" objName="salesClass" parentID="M013"  defName="${commonall}"   />
				</td>
				<th><label for="sele2">창고구분</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC3"	objId="whCd" objName="whCd" parentID="Y" defName="${commonall}" />
				</td>
			</tr>
				<th><label for="sele2">매출처명</label></th>
				<td colspan=7>
					<input type="text" id="salesNm" name="salesNm" style="width: 20%;" onclick="sales_clear()">
					<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;">
					<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
				</td>
			<tr>
				
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
        	<h2>매출처월별출고현황</h2>
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

<!-- 매출처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesMaster.jsp" />



<!-- 상품 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />
</body>
</html>