<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script>

	$(document).ready(function($) {
		
		$("#genDy").datepicker({dateFormat:'yy-mm-dd'});
		
		$("#genDy").val("${today}");
		
		initMasterIfGrid();
		initSubIfGrid();
		
		masterIfList();
		subIfList();
		
		$("#btnSearch").unbind().click(null,	masterIfList);			// 조회
		
		//grid resize
        $(window).bind('resize', function() {
        	try{
                // width
                // 그리드의 width를 div 에 맞춰서 적용
                $('#masterIfList').setGridWidth($('#grid1container').width()); //Resized to new width as per window
                $('#subIfList').setGridWidth($('#grid2container').width()); //Resized to new width as per window

                // height
                var height = $(window).height()-$('#grid1container')[0].offsetTop;

                if(height > 475)
                	$('#masterIfList, #subIfList').setGridHeight(height-220);
                else if(height < 300)
                	$('#masterIfList, #subIfList').setGridHeight(300);
            }catch(e){}
        }).trigger('resize');
	});
	
	// 작업리스트 그리드
	function initMasterIfGrid() {
		$("#masterIfList").jqGrid({
			datatype : "local"
			, data : []
			, colNames : [
				'작업명'
				, '작업종류'
				, '시작일시'
				, '완료일시'
				, '처리결과'
				, '메세지내용'
				, '처리건수'
				, 'hidden'
				, 'hidden'
				, 'hidden'
			]
			, colModel : [
				{name:'applNm'     	 , index:'applNm'   			, sortable:true   , width:30 , align:"center"},
				{name:'ifCdNm'     	 , index:'ifCdNm'   			, sortable:true   , width:20 , align:"center"},
				{name:'startDt'      , index:'startDt'   			, sortable:true   , width:30 , align:"center"},
				{name:'endDt'     	 , index:'endDt'   				, sortable:true   , width:30 , align:"center"},
				{name:'succYn'     	 , index:'succYn'   			, sortable:true   , width:20 , align:"center"},
				{name:'rsltCdNm'     , index:'rsltCdNm'   			, sortable:true   , width:30 , align:"center"},
				{name:'recCnt'     	 , index:'recCnt'   			, sortable:true   , width:20 , align:"center"},
				{name:'applCd'		 , index:'applCd'				, hidden:true},
				{name:'ifCd'		 , index:'ifCd'					, hidden:true},
				{name:'rsltCd'		 , index:'rsltCd'				, hidden:true}
			]
			, gridComplete : function() {
				var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	        	$(window).resize();
			}
			, loadComplete : function() {
				$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }
	        	$(window).resize();
			}
			, loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
            }
			, onSelectRow : function(id, status, e) {
				
			}
			,rowNum:50
            ,loadui : "disable"
            ,gridview:    true
            //,pager: '#pageList'
            ,sortname: 'comCd'
            ,sortorder: 'asc'
            ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
            ,viewrecords: true
            ,scroll : false
            ,rownumbers:true
            ,loadonce:true
            ,autowidth:true
		});
	}
	
	// 수작업 리스트 그리드
	function initSubIfGrid() {
		
		$("#subIfList").jqGrid({
			datatype : 'local'
			, data : []
			, colNames : [
				'작업코드'
				, '작업명'
				, 'hidden'
			]
			, colModel : [
				{name:'dtlCd'     	 , index:'dtlCd'   			, sortable:true   , width:30 , align:"center"},
				{name:'dtlNm'     	 , index:'dtlNm'   			, sortable:true   , width:30 , align:"center"},
				{name:'comCd'		 , index:'comCd'			, hidden:true}
			]
			, gridComplete : function() {
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	        }
			, loadComplete: function() {
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
	                $(this).addRowData("blankRow", msg);
	            }
	        }
			, loadError:function(xhr, status, error) {
                alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
                message.error.process
                return false;
            }
			, onSelectRow : function(id, status, e) {
				
				if (id == 'blankRow')
					return;
				
				var rowdata = $(this).getRowData(id);
				
				var dtlCd = rowdata.dtlCd;
				var dtlNm = rowdata.dtlNm;
				
				manualBatchStart(dtlNm, dtlCd);
			}
			, rowNum	 	: 50
            , loadui 		: "disable"
            , gridview		: true
            // ,pager		: '#pageList'
            , sortname		: 'dtlCd'
            , sortorder		: 'asc'
            , emptyrecords 	: '<spring:message code="message.search.no.data" />'     <%-- 조회결과가 없습니다.--%>
            , viewrecords	: true
            , scroll 		: true
            , rownumbers		: true
            , loadonce		: true
		});
	}
	
	// 작업리스트 조회
	function masterIfList() {
		
		var searchInfo = {};
		
		$("#searchForm").find('input, select').map(function() {
			if (this.name == "genDy") {
				searchInfo[this.name] = $(this).val().replace(/\D/g, "");
			} else {
				searchInfo[this.name] = $(this).val();
			}
		});
		
		$("#masterIfList").jqGrid('setGridParam', {
			url : '<c:url value = "/app/mgr/manager/mgrInterface_selMasterList"/>',
			datatype : "json",
			postData : searchInfo,
			mtype : "POST",
			jsonReader : {
				root : "list",
				repeatitems : false,
			}
		}).trigger("reloadGrid");
	}
	
	// 수작업 리스트
	function subIfList() {
		
		var searchInfo = {};
		
		$("#subIfList").jqGrid('setGridParam', {
			url : '<c:url value = "/app/mgr/manager/mgrInterface_selSubList"/>',
			datatype : "json",
			postData : searchInfo,
			mtype : "POST",
			jsonReader : {
				root : "list",
				repeatitems : false,
			}
		}).trigger("reloadGrid");
	}
	
	// 수동 배치 실행
	function manualBatchStart(dtlNm, dtlCd) {
		
		if (!confirm(dtlNm + " 배치를 실행하시겠습니까?")) return;

		var batchInfo = {'dtlCd' : dtlCd};
		
// 		batchInfo['dtlCd'] = dtlCd;

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			url : '<c:url value = "/app/mgr/manager/closeJobSpBatchMenual"/>',
			data : JSON.stringify(batchInfo),
			success : function(data) {

				if(data) {
					alert("MsgCd : "+data.msgCd +"    Message : "+data.message);

					masterIfList();
					subIfList();
				}
				
			}
		});
	}
</script>

<div id = "section">
<spring:message code="common.all" 	 var="nameall" />  <%--전체  --%>
<spring:message code="common.choice" var="choice" />  <%--선택  --%>
	<jsp:include page = "/WEB-INF/views/common/include/incPageTitle.jsp" />
	
	<!-- 검색조건 start ------------------------------------>
	<form id = "searchForm" name = "searchForm" method = "post">
		<fieldset>
			<legend>I/F 관리</legend>
			<table style = "width:100%" class = type1>
				<caption>I/F 관리</caption>
				<colgroup>
					<col width="160">
					<col width="*">
					<col width="160">
					<col width="*">
					<col width="160">
					<col width="*">
				</colgroup>
				<tbody id = "_search">
					<tr>
						<th><label for="sele2">수행일자</label></th>
						<td><input type="text" id="genDy" name="genDy" readonly style = "width:30%;"></td>
						<th><label for="sele2">작업종류</label></th>
						<td><html:codeTag comType="SELECT"  objId="applCd"      objName="applCd"       parentID="IF01"  defName="${nameall}" /></td>
						<th><label for="sele2">처리결과</label></th>
						<td><html:codeTag comType="SELECT" objName="rsltCd" parentID="IF02" objId="rsltCd"  defName="${nameall}" /> </td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</form>
	
	 <!-- 분류 서브타이틀 및 이벤트 버튼 start  -------------------->
	<%-- <div class="tit_area">
		<h2>인터페이스 List</h2>
		<div>
			<html:button id="btnSearch" auth="select" /> 조회
		</div>
	</div> --%>
	<!-- 대분류 서브타이틀 및 이벤트 버튼 end     ---------------->
	
	<!-- 검색조건 end ----------------------------------->
	
	<!-- CONTENT BODY start  ----------------->
	<table style = "width : 100%">
		<colgroup>
			<col width = "70%" />
			<col width = "5" />
			<col width = "30%" />
		</colgroup>
		
		<tr>
			<td>
				<!-- 분류 서브타이틀 및 이벤트 버튼 start  ----------------->
				<div class = "tit_area">
					<h2>작업 리스트</h2>
					<div>
						<html:button id="btnSearch" auth="select" />
					</div>
				</div>
				
				<div id = "grid1container" class = "gridcontainer">
					<table id = "masterIfList"><tr><td></td></tr></table>
				</div>
				
				<div id="pageList"></div>
			</td>
			<td></td>
			<td>
				<div class = "tit_area">
					<h2>수작업 리스트</h2>
				</div>
				
				<div id = "grid2container" class = "gridcontainer">
					<table id = "subIfList"><tr><td></td></tr></table>
				</div>
				
				<div id = "pageList2"></div>
			</td>
		</tr>
	</table>
	
	
</div>