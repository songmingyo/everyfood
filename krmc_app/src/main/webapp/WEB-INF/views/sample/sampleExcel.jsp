<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<spring:eval expression="@config['batch.url']"		var="batchUrl" />

<script>	
	$(document).ready(function($){
		
		initSearch();
		initExcel();

		//버튼클릭 이벤트
		$('#btnSearch').click(function(e){
			/* 조회버튼을 클릭한 경우는 페이지번호는 1로 초기화 한다. */
			//$("#page").val(1);
			eventSearch();
			/* 버튼이 form 안에 존재하면 e.preventDefault(); 필수이다. 안쓰면 autoSubmit 된다. */
			e.preventDefault();
		});

		//사용자명,아이디,소속 조회조건 입력필드 enter key이벤트 --------------
		$('#srchComCd').unbind().keydown(function(e) {
			switch(e.which) {
	    	case 13 : eventSearch(this); break; // enter
	    	default : return true;
	    	}
	    	e.preventDefault();
	   	});
		
		$('#btnExcelDown').unbind().click(function(){ fncExcelDown()});					// 엑셀다운로드
	  	$('#btnExcelUpload').unbind().click(function(){ fncExcelUpload()}); 			// 엑셀업로드
	  	$('#btnPdfView').unbind().click(function(){ fncPdfView()}); 					// pdfView
	  

		//grid resize
		$(window).bind('resize', function() {
		 
		    	// width
	        	// 그리드의 width를 div 에 맞춰서 적용
	        	$('#tabList').setGridWidth($('#grid1container').width()); //Resized to new width as per window
	        	$('#tabExcelList').setGridWidth($('#grid2container').width()); //Resized to new width as per window

	        	$('#tabList').setGridHeight(200);
				$('#tabExcelList').setGridHeight(200);
		}).trigger('resize');		
	});
	
	
	function initSearch(){
		$("#tabList").jqGrid({
			datatype: "local",  // 보내는 데이터 타입
			data: [],

			// 컬럼명
			/* '<spring:message code="app.manager.memberviewlist.head.code" />'  메세지프로퍼티 사용방법 */
			colNames:[
				'마스터코드'
				,'마스터코드명'
				,'상세코드'
				,'상세코드명'
				,'코드설명'
				,'정렬순서'
			],
			// 헤더에 들어가는 이름
			colModel:[
				{name:'comCd'			, index:'comCd'		, sortable:true		, width:80		,align:"center"},
				{name:'comNm'			, index:'comNm'		, sortable:true		, width:140		,align:"left"},
				{name:'dtlCd'			, index:'dtlCd'		, sortable:true		, width:140		,align:"center"},
				{name:'dtlNm'			, index:'dtlNm'		, sortable:true		, width:100		,align:"center"},
				{name:'codeDecs'		, index:'codeDecs'	, sortable:true		, width:100		,align:"center"},
				{name:'sortNo'			, index:'sortNo'	, sortable:true		, width:140		,align:"center"}				
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
			loadError:function(xhr, status, error) {										//데이터 못가져오면 실행 됨
				console.log("xhr : " + xhr);
				console.log("status : " + status);
				console.log("error : " + error);
				//alert('<spring:message code="message.error.process" />');					<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
			},
			onSelectRow : function(rowid, colld, val, e) {									//행 선택시 이벤트

			},
			//onCellSelect : function(rowid, iCell, content){	// 셀 선택시 이벤트 (한번클릭)		// iCell : 선택열번호	content : 선택셀의 값
			ondblClickRow: function(rowid, iRow, iCell, e) { // row 더블클릭
			
			},
			
			page	: 1,
			rowNum	: 100,
			//rowList	: [20,30,50,100],
			loadui	: 'disable',
			gridview: true,
			pager	: '#pageList',
			viewrecords	: true,
			rownumbers	: true,
			shinkToFit	: true,
			loadonce	: false,
 	        autowidth	: true
		});
	}	
	
	function eventSearch(){

		var srchComCd = $("#searchForm").find("#srchComCd").val();
		var srchDtlCd = $("#searchForm").find("#srchDtlCd").val();
		//var srchStartDt = $("#searchForm").find("#srchStartDt").val().replace(/\//g, "");

		var searchInfo = {'srchComCd' : srchComCd, 'srchDtlCd' : srchDtlCd};		
		
		$("#tabList").jqGrid('clearGridData');

		$("#tabList").jqGrid('setGridParam',{
			url:'<c:url value="/app/smp/sample_selSampleExcel.json" />'
			,datatype: "json"
			,postData: searchInfo
			,mtype:'POST'
			,loadBeforeSend: function(jqXHR) {
				jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
			}
			,jsonReader : {  
                root:  "list",				// 조회결과데이터
               	total: "totalPageCount",	// 총 페이지 수
               	records : "totalCount",		// 페이징영역 조회된 데이터 총갯수
                repeatitems: false
			}
		}).trigger("reloadGrid");
		
	}

	/*
	root:  "list",
    total: "total",
    records : "records",
    repeatitems: false  
    */
	
	// Excel Download
	function fncExcelDown(){
		
		var srchComCd = $("#searchForm").find("#srchComCd").val();
		var srchDtlCd = $("#searchForm").find("#srchDtlCd").val();
		
		$('#excelForm input[name="srchComCd"]').val(srchComCd);
		$('#excelForm input[name="srchDtlCd"]').val(srchDtlCd);
		
		$('#excelForm').attr("action", "<c:url value='/app/smp/sample_selSampleExcelDown'/>").submit();
	}
	
	// Excel Upload
	function fncExcelUpload(){
		
	//	var obj;				// 파라미터 
	//	var excelTempParam;		// 파라미터
	//	excelTempParam = {'excelSysKnd' : "SMP", 'excelWorkKnd' : "001"}
				
		// 엑셀업로드 공통팝업
	//	exportExcelTempLayer(fncExcelTempReload, excelTempParam);


		_commonExcelUploadLayer('',fncExcelTempReload);
	}	
	
	// 엑셀업로드 콜백
	function fncExcelTempReload(callBackParam) {

		$("#tabExcelList").jqGrid('clearGridData');
		$("#tabExcelList")[0].addJSONData(callBackParam.resultList);
	
	}
	
	
	// PDF VIEW
	var pdfStat = false;
	var pdfViewPop;
	function fncPdfView(){		
		
		$("#pdfViewForm input[name='comCd']").val("9301");
		$("#pdfViewForm input[name='fileName']").val("Sample_Report");
		
		pdfStat = true;		
		// 팝업 윈도우 설정 
		var specs = 
			"height="+ $(window).height() +
			",width=800"+
			",top=20"+
			",left="+($(window).scrollLeft() + ($(document).width() - 800) / 2) +
			",scrollbars=yes"+
			",resizable=yes"
			;
		
		// 팝업 활성화
		pdfViewPop = window.open("<c:out value='/app/common/comPdfFilePreview'/>", "popup", specs);
	}

	// PDF 자식창 함수 제어
	function chkChild() {

		if(!pdfStat) {
			pdfViewPop.close();
			return false;
		}

		// 키값 세팅
		var searchData = "";
		searchData += $("#pdfViewForm input[name='comCd']").val();

		// PDF 호출 설정
		var pdfType  = "HTML" 	    			// HTML or JASPER
		var workType = "SMP";        			// 업무구분(점포개발, 시공, 컨세션, 정보관리)
		var KeyValue = searchData;  			// 키 값(파라미터값)
		var fileName = $("#hiddenForm input[name='fileName']").val(); 					// 파일명

		pdfViewPop.eventSearchPdfFilePdfView(pdfType, workType, KeyValue, fileName);	// 자식창 함수 호출

		pdfStat = false;
	}
	
	function fncCustomEmsSend(){
		
		$.ajax({
	      	contentType : 'application/json; charset=utf-8'
	      	, type : 'post'
	      	, dataType : 'json'
	      	, url : '/app/smp/sample_insCustomEmsSend.json'
	      	//, data : null
	      	, async: true
	      	, success : function(data){
	      		if(data != null){
		 			alert("전송성공");
	      		}else{
	      			alert("전송실패");
	      			return false;
	      		}
	      	}
			, error:function(request, status, error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});		
	}
	
	
	function fncEmsSend(){
		
		$.ajax({
	      	contentType : 'application/json; charset=utf-8'
	      	, type : 'post'
	      	, dataType : 'json'
	      	, url : '/app/smp/sample_insEmsSend.json'
	      	//, data : null
	      	, async: true
	      	, success : function(data){
	      		if(data != null){
					alert("전송성공");
	      		}else{
	      			alert("전송실패");
	      			return false;
	      		}
	      	}
			, error:function(request, status, error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});		
	}



	//인터페이스 수동실행
	function fncInterFaceSend(){
		var dataInfo = {};
		dataInfo["applCd"] = "001";
		dataInfo["extent01"] = "Y";

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			url  :"<c:url value='/web/interFaceTest'/>",
			data : JSON.stringify(dataInfo),
			success : function(data){
				if(data.resultCode == "success"){
					alert("전송성공");
				}else{
					alert("전송실패");
					return false;
				}

			}
		});
	}






	function initExcel(){
		$("#tabExcelList").jqGrid({
			datatype: "local",  // 보내는 데이터 타입
			data: [],
		  colNames:[
				 'Cel0'
				,'Cel1'
				,'Cel2'
				,'Cel3'
				,'Cel4'
				,'Cel5'
				,'Cel6'
			],
			// 헤더에 들어가는 이름
			colModel:[
				 {name:'COL_0'	, index:'No'		, sortable:true		, width:80		}
				,{name:'COL_1'	, index:'PrdtNm'	, sortable:true		, width:140		}
				,{name:'COL_2'	, index:'PrdtCd'	, sortable:true		, width:140		}
				,{name:'COL_3'	, index:'PrdtStd'	, sortable:true		, width:100		}
				,{name:'COL_4'	, index:'cost'		, sortable:true		, width:100		}
				,{name:'COL_5'	, index:'boxQty'	, sortable:true		, width:140		}				
				,{name:'COL_6'	, index:'salesAmt'	, sortable:true		, width:140		}				
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
			loadError:function(xhr, status, error) {										//데이터 못가져오면 실행 됨
				console.log("xhr : " + xhr);
				console.log("status : " + status);
				console.log("error : " + error);
				//alert('<spring:message code="message.error.process" />');					<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
			},
			page	: 1,
			rowNum	: 100,
			//rowList	: [20,30,50,100],
			loadui	: 'disable',
			gridview: true,
			pager	: '#pageExcelList',
			viewrecords	: true,
			rownumbers	: true,
			shinkToFit	: true,
			loadonce	: false,
 	        autowidth	: true
		});
	}	
</script>


<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />

	<!-- 검색조건 start -->	
	<form id="searchForm" name="searchForm" method="post">
		<fieldset>
			<legend>검색</legend>
			<table style="width:100%" summary="" class="type1">
				<caption>검색</caption>
				<colgroup>
					<col width="130">
					<col width="*">
				</colgroup> 
				<tbody id="_search">
	            	<tr>
	                	<th>
	                		<label for="sele2">마스터 코드명</label>
	                	</th>
	                    <td>
	                    	<input type="text" id="srchComCd" name="srchComCd" value="5800" style="width: 200px;" disabled="disabled" />
							<html:codeTag comType="SELECT" objId="srchDtlCd" objName="srchDtlCd" parentID="5800" defName="전체" />
	                    </td>
					</tr>
	            </tbody>
			</table> 
	    </fieldset>
	    <!-- 검색조건 end -->
	     
	    
	</form>
	<!-- 서브타이틀 및 이벤트 버튼 start -->
	<div class="tit_area">
			<h2>코드 LIST</h2>
			<div class="btn_l">
				<html:button id="btnSearch"			auth="select" />			
				<html:button id="btnExcelDown"		auth="excel" 	msgKey="button.excelDownload" />
				<html:button id="btnPdfView"		auth="select" 	value="VM REPORT VIEW" />
			</div>
	</div>   
	<!-- 서브타이틀 및 이벤트 버튼 end -->  
	<!-- centent List -->
	<div id="grid1container" class="gridcontainer">
	    <table id="tabList"><tr><td></td></tr></table>
	</div>
	
	
	<div class="tit_area">
			<h2>Excel Upload List</h2>
			<div class="btn_l">
				<a class="button btn_white" href="/resources/download/EXCEL_TEST_PRDT.xlsx" >업로드 예제파일 다운로드 </a>
				
				<html:button id="btnExcelUpload"	auth="insert" 	value="엑셀업로드" />
			</div>
	</div>  
	<!-- centent List -->
	<div id="grid2container" class="gridcontainer">
	    <table id="tabExcelList"><tr><td></td></tr></table>
	</div>
	
	<div id="pageExcelList"></div>
	<!-- centent List -->	     
</div>	

<!-- 엑셀업로드용 팝업창 start -->
	<jsp:include page="/WEB-INF/views/app/common/find_excelUploadLayer.jsp" />
<!-- 엑셀업로드 end -->

<!-- 엑셀다운로드 폼 -->
<form id="excelForm" name="excelForm" method="post">
	<sec:csrfInput/>
	<input type="hidden" name="srchComCd" />
	<input type="hidden" name="srchDtlCd" />
</form>

<!-- velocity연계 pdf view -->
<form id="pdfViewForm" name="pdfViewForm" method="post">
	<sec:csrfInput />
	<input type="hidden" id="comCd" name="comCd" />
	<input type="hidden" id="fileName" name="fileName" /> 
</form>
