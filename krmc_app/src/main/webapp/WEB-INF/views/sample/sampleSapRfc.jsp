<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script>	
	$(document).ready(function($){
		
		initSearch();
		
		$('#btnSapRfc').unbind().click(function(){fncSapRfc()});					// SAP 연동

		//grid resize
		$(window).bind('resize', function() {
		    try{
		        // width
		        // 그리드의 width를 div 에 맞춰서 적용
		        $('#tabList').setGridWidth($('#grid1container').width()); //Resized to new width as per window

		        var height = $(window).height() - $('#grid1container')[0].offsetTop;
// 		        console.log(height);
		        if(height > 275){
		        	$('#tabList').setGridHeight(height-110);
		        }else if(height < 300){
		        	$('#tabList').setGridHeight(height-300);
		        }

		    }catch(e){}
		}).trigger('resize');
	});
	
	
	function initSearch(){
		$("#tabList").jqGrid({
			datatype: "local",  // 보내는 데이터 타입
			data: [],

			// 컬럼명
			/* '<spring:message code="app.manager.memberviewlist.head.code" />'  메세지프로퍼티 사용방법 */
			colNames:[
				'고객번호'
				,'유통경로'				
				,'상호명'
				,'영업개점일'
				,'영업종료일'
				,'상권'
				,'지역'
				,'점포형태'
				,'사원번호'
				,'소속지점'	
				,'사업부 코드'
				,'사업자 코드'
				,'점포주소'
				,'점포  SV명'
				,'first num'
			],
			// 헤더에 들어가는 이름
			colModel:[
				{name:'KUNNR'			, index:'KUNNR'		, sortable:false		, width:80		,align:"center"}
				,{name:'VTWEG'			, index:'VTWEG'		, sortable:false		, width:140		,align:"center"}				
				,{name:'ZSNAME'			, index:'ZSNAME'	, sortable:false		, width:140		,align:"left"}
				,{name:'ZFDAT'			, index:'ZFDAT'		, sortable:false		, width:100		,align:"center"}
				,{name:'ZTDAT'			, index:'ZTDAT'		, sortable:false		, width:100		,align:"center"}
				,{name:'ZTAREA'			, index:'ZTAREA'	, sortable:false		, width:140		,align:"center"}				
				,{name:'ZLOCA'			, index:'ZLOCA'		, sortable:false		, width:140		,align:"center"}				
				,{name:'ZSTYPE'			, index:'ZSTYPE'	, sortable:false		, width:140		,align:"center"}
				,{name:'ZSVNO'			, index:'ZSVNO'		, sortable:false		, width:140		,align:"center"}				
				,{name:'VKBUR'			, index:'VKBUR'		, sortable:false		, width:140		,align:"center"}				
				,{name:'SPART'			, index:'SPART'		, sortable:false		, width:140		,align:"center"}				
				,{name:'STCD2'			, index:'STCD2'		, sortable:false		, width:140		,align:"center"}
				,{name:'FULLADDR'		, index:'FULLADDR'	, sortable:false		, width:140		,align:"center"}
				,{name:'ZENAME'			, index:'ZENAME'	, sortable:false		, width:140		,align:"center"}				
				,{name:'TELF1'			, index:'TELF1'		, sortable:false		, width:140		,align:"center"}
			],
			gridComplete : function() {                                      // 데이터를 성공적으로 가져오면 실행 됨
				var colCount = $(this).getGridParam("colNames").length;
				$("#blankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
				$(window).resize();
			},
			loadComplete: function() {
				$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
				if ($(this).getGridParam("records")==0) {
					var firstColName = $(this).getGridParam("colModel")[1].name;
					var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
					$(this).addRowData("blankRow", msg);
					$(window).resize();
				}
			},
			loadError:function(xhr, status, error) {										// 데이터 못가져오면 실행 됨
				//alert('<spring:message code="message.error.process" />');
				return false;
			},
			onSelectRow : function(rowid, colld, val, e) {									// 행 선택시 이벤트

			},
			//onCellSelect : function(rowid, iCell, content){
			ondblClickRow: function(rowid, iRow, iCell, e) { // row 더블클릭
			
			},
			
			page		: 1,
			rowNum		: 20,
			//rowList	: [20,30,50,100],
			loadui		: 'disable',
			gridview	: true,
			viewrecords	: true,
			rownumbers	: true,
			shinkToFit	: true,
			loadonce	: false,
 	        autowidth	: true
		});
	}	
	
	// sap 연동
	function fncSapRfc(){
		
		var searchInfo;
 
		$("#tabList").jqGrid('setGridParam',{
			url:'<c:url value="/app/smp/sample_selCallSapRfc" />'
			,datatype: "json"
			,postData: searchInfo
			,ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' }
			,mtype:'POST'
			//jqGrid AJAX POST 방식으로 보낼때, CSRF TOKEN값을 함께 보내줘야한다.
			,loadBeforeSend: function(jqXHR) {
				jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
			}
		}).trigger("reloadGrid");
	}

</script>


<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />

	<!-- 검색조건 start -->	
	<form id="searchForm" name="searchForm" method="post">
	    <!-- 서브타이틀 및 이벤트 버튼 start -->
		<div class="tit_area">
			<h2>SAP RFC</h2>
			<div class="btn_l">			
				<html:button id="btnSapRfc"		auth="select" 	value="SAP RFC" />				
			</div>
		</div>   
		<!-- 서브타이틀 및 이벤트 버튼 end -->  
	</form>
	
	<!-- centent List -->
	<div id="grid1container" class="gridcontainer">
	    <table id="tabList">
	    	<tr>
	    		<td></td>
	    	</tr>
	    </table>
	</div>
	<!-- centent List -->	     
</div>	
