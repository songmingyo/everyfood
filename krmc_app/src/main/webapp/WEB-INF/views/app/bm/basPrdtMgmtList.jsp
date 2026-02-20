<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		initprdtMgmtListGrid();
		initSubBuyGrid();
		initSubIssGrid();

		/* BUTTON CliCK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnInit').unbind().click(null,	    newInit); 		// 신규
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐
		
		
		/*Resized to new width as per window -------------------------------*/
    
        $(window).bind('resize', function() {
		    try{	
		        $('#container1List').setGridWidth($('#grid1container').width()); 	// right main gride
				$('#container2List').setGridWidth($('#grid2container').width()); 	// left footer (left)
				$('#container3List').setGridWidth($('#grid3container').width()); 	// left footer (right)

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;	// right main gride


		        if(height > 280)	 	{
				    $('#container1List').setGridHeight(height-80);	//GRID  liST
				    $('#prdtMgmtListData').height(height-220);  			//TALBE DATA
				}
		        else if(height < 300){
			        $('#container1List').setGridHeight(height-15);	//GRID  liST
			        $('#prdtMgmtListData').height(height-220);  			//TALBE DATA
			    }
			    /*left footer height 고정------------------*/
		    	$('#container2List').setGridHeight(320);
				$('#container3List').setGridHeight(320);
				
			}catch(e){}
		}).trigger('resize');
		
		/*----------------------------------------------------------------*/
  

		/* 조회조건 입력필드 enter key이벤트 -----------------------------------*/
		$('#find_prdtNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : searchEvent(this); break; // enter
	    		default : return true;
	    	}
	    	e.preventDefault();
	   	});

		/*-----------------------------------------------------------------*/


		/* Form validate --------------------------------------------------*/
        $('#mainform').validate({
	        rules: {
	        	 prdtNm   : { required: true, maxlength: 50}
	        }
			,messages: {
				prdtNm   : {required: "<div class='validate'><i class='fa fa-info-circle'></i>품명 입력하세요!<div>"}  
			}
	    });

		
	});
	

	/* 마스터 데이터  그리드 초기화 */
	function initprdtMgmtListGrid() {
		$("#container1List").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '품목명'
            			, '규격'
            			, '입고단가'
            			, '품목코드'
            			, '사용유무'
            ]
			,colModel:[
		                 {name:'prdtNm'			, index:'prdtNm'	, sortable:true, width:100, align:"left"}
		            	,{name:'prdtStd'		, index:'prdtStd'	, sortable:true, width:50 , align:"center"}
		            	,{name:'cost'			, index:'cost'		, sortable:true, width:50 , align:"right", formatter:'integer'}
		            	,{name:'prdtCd'			, index:'prdtCd'	, hidden:true, sortable:true, width:50 , align:"center"}
		            	,{name:'useYn'			, index:'useYn'		, hidden:true, sortable:true, width:50}
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount)
                							  .attr("style", "text-align: center;");
	            
	            var ids = $("#container1List").getDataIDs();
            	
            	$.each( ids, function(idx, rowId) {
            		rowData = $("#container1List").getRowData(rowId) ;
	            	    
	                if ( rowData.useYn == "N" ) {
		                // 색깔 변경하기
	                    $("#container1List").setRowData(rowId, false, {color:'red'}) ;
	                }
	            });
            	
            	if(ids && ids.length > 0){
        			$('#container1List').jqGrid("setSelection", ids[0]);
        		}
	            
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
				//alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
				return false;
            }
            ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트

            	if (id == 'blankRow') return;
           		var rowdata = $(this).getRowData(id);

                detailEvent(rowdata);
           }

           ,rowNum:1000
           ,loadui : "disable"
           ,gridview:    true
           ,pager: '#pageList'
           ,sortname: 'prdtCd'
           ,sortorder: 'asc'
           ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
           ,viewrecords: true
           ,scroll : false
           ,rownumbers:true
           ,loadonce:true
           ,autowidth:true
		});
	}
	
	
	/* 최근입고데이터 그리드 초기화 */
	function initSubBuyGrid() {
		$("#container2List").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '입고일자'
            			, '소비기한'
            			, '매입처'
            			, '센터'
            			, '수량'
            			, '단가'
            			, '금액'
            			, '부가세'
            			, '합계'
            ]
			,colModel:[
		                 {name:'buyDtFmt'	, index:'buyDt'		, sortable:true, width:80, align:"center"}
		            	,{name:'termVal'	, index:'termVal'	, sortable:true, width:80 , align:"center"}
		            	,{name:'buyNm'		, index:'buyNm'		, sortable:true, width:120 , align:"left"}
		            	,{name:'whNm'		, index:'whNm'		, sortable:true, width:70 , align:"center"}
		            	,{name:'buyQty'		, index:'buyQty'	, sortable:true, width:60 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'cost'		, index:'cost'		, sortable:true, width:80 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'pureAmt'	, index:'pureAmt'	, sortable:true, width:80 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'vatAmt'		, index:'vatAmt'	, sortable:true, width:80 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'totAmt'		, index:'totAmt'	, sortable:true, width:80 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	        	$("#container2List").find("#blankRow td:nth-child(2)").attr("colspan", colCount);
	        	$(window).resize();
	        }
			,loadComplete: function() {
				$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	//조회결과가 없습니다.
	                $(this).addRowData("blankRow", msg);
	            }
	        	$(window).resize();
	        }
			,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				return false;
            }
            ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트
           }

            ,rowNum:-1
            ,loadui : "disable"
            ,gridview:    true
           // ,pager: '#pageList'
            ,sortname: 'prdtCd'
            ,sortorder: 'asc'
            ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
            ,viewrecords: true
            ,scroll : false
            ,rownumbers:true
            ,loadonce:true
            ,shrinkToFit : false
            ,autowidth:true
		});
	}
	
	/* 최근출고데이터 그리드 초기화 */
	function initSubIssGrid() {
		$("#container3List").jqGrid({
			 datatype: "local"  // 보내는 데이터 타입
			,data: []
			,colNames:[   '출고일자'
            			, '매출처'
            			, '구분'
            			, '센터'
            			, '수량'
            			, '단가'
            			, '금액'
            			, '부가세'
            			, '합계'
            ]
			,colModel:[
		                 {name:'salesDtFmt'	, index:'salesDt'		, sortable:true, width:80, align:"center"}
		            	,{name:'salesNm'	, index:'salesNm'		, sortable:true, width:120 , align:"center"}
		            	,{name:'salesClass'	, index:'salesClass'	, sortable:true, width:60 , align:"center"}
		            	,{name:'whNm'		, index:'whNm'			, sortable:true, width:80 , align:"center"}
		            	,{name:'salesQty'	, index:'salesQty'		, sortable:true, width:60 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'price'		, index:'price'			, sortable:true, width:80 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'pureAmt'	, index:'pureAmt'		, sortable:true, width:80 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'vatAmt'		, index:'vatAmt'		, sortable:true, width:80 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		            	,{name:'totAmt'		, index:'totAmt'		, sortable:true, width:80 , align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 0}}
		     ]
			,gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	        	$("#container3List").find("#blankRow td:nth-child(2)").attr("colspan", colCount);
	        	$(window).resize();
	        }
			,loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	//조회결과가 없습니다.
	                $(this).addRowData("blankRow", msg);
	            }
	        	$(window).resize();
	        }
			,loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				return false;
            }
            ,onSelectRow : function(id, status, e) { 	//행 선택시 이벤트
           }

            ,rowNum:-1
            ,loadui : "disable"
            ,gridview:    true
         //   ,pager: '#pageList'
            ,sortname: 'prdtCd'
            ,sortorder: 'asc'
            ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
            ,viewrecords: true
            ,scroll : false
            ,rownumbers:true
            ,loadonce:true
            ,autowidth:true
            ,shrinkToFit : false
		});
	}
	
	
	/* 리스트 데이터 조회 */
	function searchEvent(event){
		newInit(); // 입력 From  초기화 
		
		var searchInfo = {};
		
		$('#searchForm').find('input , select').map(function() {
			searchInfo[this.name] = $(this).val();
		});

		//Grid 초기화
		$("#container1List").jqGrid('clearGridData');		
		
		$("#container1List").jqGrid('setGridParam',{
			url:"<c:url value='/app/bm/baseinfo/prdtMgmt_selList.json'/>",
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

	/* 선택 Row 데이터 상세 */
	function detailEvent(searchInfo){

		if(!searchInfo || !searchInfo.prdtCd) return;
		
		searchInfo["whCd"] = $.trim($("#searchForm select[name='whCd']").val());
		
		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/bm/baseinfo/prdtMgmt_selDetail.json"/>',
			data : JSON.stringify(searchInfo),
			success : function(data) {

				if(data != null) {
					
					$.form = $("#prdtMgmtListData");
					
					var cost = numberWithCommas(data.cost);
					var baseCost = numberWithCommas(data.baseCost);

					data.cost = cost;
					data.baseCost = baseCost;

					//조회 데이터 셋팅
					$.form.find("span, input, label").each(function(){
						if(this.type != "button" && this.type != "radio"){
							dataNm = $(this).attr("name");
							tagNm = this.tagName.toUpperCase();
	
							settingData = data[dataNm];
	
							if(tagNm == "SPAN"){
								$(this).text(settingData);
							}else{
								$(this).val(settingData);
							}
						}
					});
					
					$("#prdtMgmtListData").find("select[name='lCd']").val(data.lCd);
					$("#prdtMgmtListData").find("select[name='mCd']").val(data.mCd);
					$("#prdtMgmtListData").find("select[name='strgType']").val(data.strgType);
					$("#prdtMgmtListData").find("select[name='useYn']").val(data.useYn);
					$("#prdtMgmtListData").find("select[name='ordLdTm']").val(data.ordLdTm);
					$("#prdtMgmtListData").find("select[name='vatYn']").val(data.vatYn);
					$("#prdtMgmtListData").find("select[name='taxYn']").val(data.taxYn);
					$("#prdtMgmtListData").find("select[name='ordUnit']").val(data.ordUnit);
					$("#prdtMgmtListData").find("select[name='stdYn']").val(data.stdYn);
					$("#prdtMgmtListData").find("select[name='timeLimit']").val(data.timeLimit);
					$("#prdtMgmtListData").find("select[name='issSluggish']").val(data.issSluggish);

					$("#prdtMgmtListSub").find("input[id='haStkQty']").val(data.haStkQty);
					$("#prdtMgmtListSub").find("input[id='yeoStkQty']").val(data.yeoStkQty);
					
					$("#prdtMgmtListSub").find("input[id='thisSalesQty']").val(data.thisSalesQty);
					$("#prdtMgmtListSub").find("input[id='lastSalesQty']").val(data.lastSalesQty);
					$("#prdtMgmtListSub").find("input[id='twoMnSalesQty']").val(data.twoMnSalesQty);

					searchBuyEvent(searchInfo);
					searchSalesEvent(searchInfo);
					
				}
			}
		});
	}
	
	/* 최근입고 데이터 조회 */
	function searchBuyEvent(searchInfo){

		if(!searchInfo || !searchInfo.prdtCd) return;

		//Grid 초기화
		$("#container2List").jqGrid('clearGridData');		
		
		$("#container2List").jqGrid('setGridParam',{
			url:"<c:url value='/app/bm/baseinfo/prdtBuy_selList.json'/>",
			datatype: "json",
			postData: searchInfo,
			ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' },
	//		page: 1,
			mtype:'POST',
			//jqGrid AJAX POST 방식으로 보낼때, CSRF TOKEN값을 함께 보내줘야한다.
			loadBeforeSend: function(jqXHR) {
				jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
			},
			jsonReader : {
				root:  "resultList",	//조회결과 데이터
			//	page: "page",			//현재 페이지	
		//		total: "pagePerCount",	//총 페이지 수
		//		records: "totalCount",	// 전체 조회된 데이터 총갯수 
				repeatitems: false
			}
		}).trigger("reloadGrid");

	}
	
	/* 최근출고 데이터 조회 */
	function searchSalesEvent(searchInfo){

		if(!searchInfo || !searchInfo.prdtCd) return;

		//Grid 초기화
		$("#container3List").jqGrid('clearGridData');		
		
		$("#container3List").jqGrid('setGridParam',{
			url:"<c:url value='/app/bm/baseinfo/prdtSales_selList.json'/>",
			datatype: "json",
			postData: searchInfo,
			ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' },
	//		page: 1,
			mtype:'POST',
			//jqGrid AJAX POST 방식으로 보낼때, CSRF TOKEN값을 함께 보내줘야한다.
			loadBeforeSend: function(jqXHR) {
				jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
			},
			jsonReader : {
				root:  "resultList",	//조회결과 데이터
		//		page: "page",			//현재 페이지	
		//		total: "pagePerCount",	//총 페이지 수
		//		records: "totalCount",	// 전체 조회된 데이터 총갯수 
				repeatitems: false
			}
		}).trigger("reloadGrid");

	}
	

	/* 엑셀  */
	function excelEvent(){

		$('#searchForm').attr("action", "<c:url value='/app/bm/baseinfo/prdtMgmt_selExcelDown'/>").submit();

	}
	
	// 천단위 콤마 (소수점포함) 
	function numberWithCommas(num) { 
	    var parts = num.toString().split("."); 
	    return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (parts[1] ? "." + parts[1] : ""); 
    }
	
	// keyup 이벤트
	 function inputNumberFormat(obj) {
     obj.value = comma(uncomma(obj.value));
	 }
	
	 function comma(str) {
	     str = String(str);
	     return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	 }
	
	 function uncomma(str) {
	     str = String(str);
	     return str.replace(/[^\d]+/g, '');
	 }


	/*입력필드 초기화(신규,조회 Event)*/
	function newInit(){
		$('#prdtMgmtListData').find('input, textarea').each(function() {
			if(this.type != "button" && this.type != "radio"){
				$(this).val("");
			}
			
		$("#prdtMgmtListData").find("input[id='qtyBox']").val(0);
		$("#prdtMgmtListData").find("input[id='cost']").val(0);
		$("#prdtMgmtListData").find("input[id='baseCost']").val(0);
		$("#prdtMgmtListData").find("input[id='estPrice']").val(0);
		$("#prdtMgmtListData").find("input[id='caseW']").val(0);
		$("#prdtMgmtListData").find("input[id='caseL']").val(0);
		$("#prdtMgmtListData").find("input[id='caseH']").val(0);
		$("#prdtMgmtListData").find("input[id='caseWt']").val(0);
		
		});
		
	}
	
	function buyClear(){
		$("#buyCd").val('');
		$("#buyNm").val('');
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
	<legend>품목마스터관리</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>품목마스터 검색조건</caption>
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
				<th><label for="sele2">품목코드/명</label></th>
				<td><input type="text" id="find_prdtNm" name="find_prdtNm"></td>
				<th><label for="sele2">대분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="lCd" objName="lCd" parentID="Y" defName="${commonall}" />
					</td>
				<th><label for="sele2">중분류</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="mCd" objName="mCd" parentID="Y" defName="${commonall}" width="80%" />
					</td>
				<th><label for="sele2"><spring:message code="app.manager.memberviewlist.label.useyn" /></label></th><%--사용여부 --%>
					<td>
						<html:codeTag comType="SELECT" objId="useYn" objName="useYn" parentID="9002" defName="${commonall}" /> <%--전체 --%>
					</td>
			</tr>
		</tbody>

	</table>
	</fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->


<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
 <ul style="width: 100%;">
 	<li style="float: left; width: 25%; ">
 		<!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
 		<div class="tit_area" >
        	<h2>품목마스터관리</h2>
		</div>
		<!-- 서브타이틀 및 이벤트 버튼 end  -------- -->
			
   		<!-- centent List -------------------------->
        <div id="grid1container">
        	<table id="container1List" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
 	</li>
 
 	<li style="float: left; width: 75%;">
   		<form name="mainform" id="mainform"  autocomplete="off" >
 		<div style="padding-left: 5px;">
	   		<div class="tit_area">
				<h2 class="subhead">품목마스터 조회</h2>
				<div class="btn_l">
					<html:button id="btnInit"     name="btnInit"    auth="insert" /> 		<%-- 신규  --%>
					<html:button id="btnSearch"   name="btnSearch"  auth="select" /> 		<%-- 조회 --%>
					<html:button id="btnExcel"    name="btnExcel"   auth="excel" 	/> 		<%-- 엑셀  --%>
				</div>
			</div>
			<div id="prdtMgmtListData" class="prdtMgmtListData">
				<table class="type1" >
					<colgroup>
						<col width="100"/>
						<col width="*"/>
						<col width="100"/>
						<col width="*"/>
						<col width="100"/>
						<col width="*"/>
					</colgroup>
					<tbody>
					<tr>
						<th><label for="sele2">대분류</label></th>
							<td>
								<html:codeTag comType="SELECT" width="90%" dataType="PRC1"	objId="lCd" objName="lCd" parentID="Y" disabled="true"/>
							</td>
						<th><label for="sele2">중분류</label></th>
							<td>
								<html:codeTag comType="SELECT" width="90%" dataType="PRC2"	objId="mCd" objName="mCd" parentID="Y" disabled="true"/>
								
							</td>
						<th><label for="sele2">저장형태</label></th>
							<td conspan=3>
								<html:codeTag comType="SELECT" objId="strgType" objName="strgType" parentID="M001" disabled="true"/>
							</td>
					</tr>
					<tr>
						<th>LACK번호1</th>
						<td>
							<input type="text"   id="lackNo1"   name="lackNo1"  disabled="true">
						</td>
						<th>LACK번호2</th>
						<td >
							<input type="text"   id="lackNo2"   name="lackNo2"  disabled="true">
						</td>
						<th><label for="sele2">사용유무</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="useYn" objName="useYn" parentID="M009"  disabled="true"/>
							</td>
					</tr>
					<tr>
						<th>품목명</th>
						<td>
							<input type="text"   id="prdtNm"    name="prdtNm" disabled="true">
						</td>
						<th>규격</th>
						<td>
							<input type="text"   id="prdtStd"    name="prdtStd" disabled="true">
						</td>
						<th>품목코드</th>
						<td>
							<input type="text"   id="prdtCd"    name="prdtCd"	readonly>
						</td>
					</tr>
					<tr>
						<th><label for="sele2">단위</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="ordUnit" objName="ordUnit" parentID="M006"  disabled="true"/>
							</td>
						<th>박스당수량</th>
							<td>
								<input type="text"   id="qtyBox"    name="qtyBox" value="0" disabled="true">
							</td>
						<th>원산지</th>
							<td>
								<input type="text"   id="originNm"    name="originNm" disabled="true">
							</td>
					</tr>
					<tr>
						<th>원가적용일</th>
						<td>
							<input type="text" class="dt" id="costStartDt" name="costStartDt" readonly="readonly" style="width: 100px !important;"  disabled="true"/>
						</td>
						<th>입고단가</th>
						<td>
							<input type="text"   id="cost"    name="cost" value="0" style="text-align:right;" onkeyup="inputNumberFormat(this);" disabled="true">
						</td>
						<th>기준원가</th>
						<td>
							<input type="text"   id="baseCost"    name="baseCost" value="0" style="text-align:right;" onkeyup="inputNumberFormat(this);" disabled="true">
						</td>
					</tr>
					<tr>
						<th>매입처</th>
						<td>
							<input type="text"   id="buyNm"    name="buyNm" style="width:70%;" onclick="buyClear();" disabled="true">
							<input type="hidden" id="buyCd"    name="buyCd" style="width:70%;">
						</td>
						<th>매입처적용일</th>
						<td>
							<input type="text" class="dt" id="buyStartDt" name="buyStartDt" readonly="readonly" style="width: 100px !important;"  disabled="true" />
						</td>
						<th>견적가</th>
						<td>
							<input type="text"   id="estPrice"    name="estPrice" value="0" style="text-align:right;" disabled="true">
						</td>
					</tr>
					<tr>
						<th>매입처상품코드</th>
						<td>
							<input type="text"   id="buyPrdtCd"    name="buyPrdtCd" disabled="true">
						</td>
						<th>이전매입처</th>
						<td>
							<input type="text"     id="prevBuyNm"    name="prevBuyNm" readonly="readonly" disabled="true">
							<input type="hidden"   id="prevBuyCd"    name="prevBuyCd" readonly="readonly" disabled="true">
						</td>
						<th><label for="sele2">규격유무</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="stdYn" objName="stdYn" parentID="9008"  disabled="true"/>
							</td>
					</tr>
					<tr>
						<th><label for="sele2">과면세</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="vatYn" objName="vatYn" parentID="9008" disabled="true" />
							</td>
						<th><label for="sele2">세금계산서</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="taxYn" objName="taxYn" parentID="9008" disabled="true" />
							</td>
						<th>유통기한</th>
						<td>
							<input type="text"   id="exprtDt"    name="exprtDt" disabled="true">
						</td>
					</tr>
					<tr>
						<th>CASE가로</th>
						<td>
							<input type="text"   id="caseW"    name="caseW" value="0" disabled="true">
						</td>
						<th>CASE세로</th>
						<td>
							<input type="text"   id="caseL"    name="caseL" value="0" disabled="true">
						</td>
						<th>판매(수입원)</th>
						<td>
							<input type="text"   id="mnfct"    name="mnfct" disabled="true">
						</td>
					</tr>
					<tr>
						<th>CASE높이</th>
						<td>
							<input type="text"   id="caseH"    name="caseH" value="0" disabled="true">
						</td>
						<th>CASE중량</th>
						<td>
							<input type="text"   id="caseWt"    name="caseWt" value="0" disabled="true">
						</td>
						<th>담당자</th>
						<td>
							<input type="text"   id="salesPrNm"    name="salesPrNm" 	disabled="true">
						</td>
					</tr>
					<tr>
						<th>비고</th>
						<td colspan=5>
							<input type="text"   id="remarks"    name="remarks">
						</td>
					</tr>
					</tbody>
				</table>
				
			</div>
			<div id="prdtMgmtListSub" class="prdtMgmtListSub">
				<ul>
					<li style="float: left; width: 50%;">
						<!-- 분류 서브타이틀 및 이벤트 버튼 start  ----------------->
		                <div class="tit_area">
		                	<h2>최근입고데이터</h2>
		                	<div style="margin-top: 2px; margin-left: 200px;">
		                		<th align="right">하남재고</th>
			                		<td>
			                			<input  type="text"   id="haStkQty"  readonly  name="haStkQty" style="height: 24px; width: 25%; text-align:right;">
			                		</td>
		                		<th>여주재고</th>
			                		<td>
			                			<input  type="text"   id="yeoStkQty" readonly name="yeoStkQty" style="height: 24px; width: 25%; text-align:right;">
			                		</td>
		                	</div>
						</div>
						<!-- 대분류 서브타이틀 및 이벤트 버튼 end  ----------------->
		
		                <!-- centent List -------------------------->
		                <div id="grid2container" class="gridcontainer">
		                	<table id="container2List" ><tr><td></td></tr></table>
		                </div>
		
		                <div id="pageList2"></div>
		                <!-- centent List -------------------------->
					</li>
					<li style="float: left; width: 50%;">
						<div  style="padding-left: 2px;">
							<!-- 코드 서브타이틀 및 이벤트 버튼 start  ------------------>
			                <div class="tit_area">
			                	<div style="margin-top: 2px; margin-left: 10px;">
			                		<th align="right">당월 출고</th>
				                		<td>
				                			<input  type="text"   id="thisSalesQty"  readonly  name="thisSalesQty" style="height: 24px; width: 17%; text-align:right;">
				                		</td>
			                		<th align="right">전월 출고</th>
				                		<td>
				                			<input  type="text"   id="lastSalesQty"  readonly  name="lastSalesQty" style="height: 24px; width: 17%; text-align:right;">
				                		</td>
			                		<th align="right">전전월 출고</th>
				                		<td>
				                			<input  type="text"   id="twoMnSalesQty" readonly name="twoMnSalesQty" style="height: 24px; width: 17%; text-align:right;">
				                		</td>
		                	</div>
							</div>
							<!-- 서브타이틀 및 이벤트 버튼 end     --------------------->
			
			                <!-- centent List -------------------------->
							<div id="grid3container" class="gridcontainer" >
			                	<table id="container3List" ><tr><td></td></tr></table>
							</div>
			
							<div id="pageList3"></div>
			                <!-- centent List -------------------------->
		               </div> 
					</li>
				</ul>
		</div>
		</form>
	 </li>
 	</ul>
 

<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />
 
 
<!-- CONTENT- BODY end ----------------------------------- -->



</div>
</body>
</html>

