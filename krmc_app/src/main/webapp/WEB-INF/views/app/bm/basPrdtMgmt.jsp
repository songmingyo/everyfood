<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

	$(document).ready(function(){

		$("#mainform #costStartDt,#mainform #buyStartDt").datepicker();
				
		initPrdtMgmtGrid();
		initSubBuyGrid();
		initSubIssGrid();

		/* BUTTON CliCK 이벤트 처리 ------------------------------------------*/
		$('#btnSearch').unbind().click(null,	searchEvent); 	// 검색
		$('#btnSave').unbind().click(null,	    saveEvent); 	// 저장
		$('#btnInit').unbind().click(null,	    newInit); 		// 신규
		$('#btnExcel').unbind().click(null,	    excelEvent);	// 액샐
		//$('#btnPrint').unbind().click(null,	    printEvent); 		// 출력
		$('#btnSalesPrMstFind').unbind().click(function(){ 	findSalesPrMst()}); 	// 영업사원찾기 팝업 버튼이벤트
		$('#btnSales_find').unbind().click(function(){ 		findSalesMst()}); 		// 매출처찾기 팝업 버튼이벤트
		
		$("#btnBuyCd").unbind().click(function(){
			findBuyMst();
		});		<%--매입처 팝업 찾아가기--%>
		
		/*Resized to new width as per window -------------------------------*/
    
        $(window).bind('resize', function() {
		    try{	
		        $('#container1List').setGridWidth($('#grid1container').width()); 	// right main gride
				$('#container2List').setGridWidth($('#grid2container').width()); 	// left footer (left)
				$('#container3List').setGridWidth($('#grid3container').width()); 	// left footer (right)

		        var height = $(window).height()-$('#grid1container')[0].offsetTop;	// right main gride


		        if(height > 280)	 	{
				    $('#container1List').setGridHeight(height-80);	//GRID  liST
				    $('#prdtMgmtData').height(height-90);  			//TALBE DATA
				}
		        else if(height < 300){
			        $('#container1List').setGridHeight(height-15);	//GRID  liST
			        $('#prdtMgmtData').height(height-65);  			//TALBE DATA
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
		
		$('#buyNm').unbind().keydown(function(e) {
			switch(e.which) {
	    		case 13 : findBuyMst(this); break; // enter
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
	
	/*매출처 찾기팝업 호출 */
	function findSalesMst(){
		commonSalesMstFindLayer('','',$("#salesNm").val(), '', setSalesMstFindLayer,'Y');
	}

	/*매출처 콜백(후처리 함수 )*/
	function setSalesMstFindLayer(data) {
		$("#salesCd").val(data.salesCd);
		$("#salesNm").val(data.salesNm);
		$("#hqClass").val(data.hqClass);
	}
	
	/*영업사원 찾기팝업 호출*/
	function findSalesPrMst() {
		commonSalesPrMstFindLayer('','',$("#salesPrNm").val(), setSalesPrMstFindLayer);
	}
	
	/*영업사원 콜백(후처리 함수 )*/
	function setSalesPrMstFindLayer(data){
		 if (data != null){
			 $("#salesPrCd").val(data.salesPrCd);		/*영업사원코드*/
			 $("#salesPrNm").val(data.salesPrNm);		/*영업사원명*/
			 $("#position").val(data.position);			/*직급*/
		}
	}
	
	/*매입처 찾기팝업 호출 */
	function findBuyMst() {
		commonBuyMstFindLayer('','',$("#buyNm").val(), setBuyMstFindLayer);
	}
	
	/*매입처 콜백(후처리 함수 )*/
	function setBuyMstFindLayer(data) {
		
		if (data != null){
			$("#prevBuyCd").val($("#buyCd").val())
			$("#prevBuyNm").val($("#buyNm").val())
			
			$("#buyCd").val(data.buyCd);
			$("#buyNm").val(data.buyNm);
		}
	}

	/* 마스터 데이터  그리드 초기화 */
	function initPrdtMgmtGrid() {
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

           ,rowNum:100
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
					
					$.form = $("#PrdtMgmtData");
					
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
					
					$("#PrdtMgmtData").find("select[name='lCd']").val(data.lCd);
					$("#PrdtMgmtData").find("select[name='mCd']").val(data.mCd);
					$("#PrdtMgmtData").find("select[name='strgType']").val(data.strgType);
					$("#PrdtMgmtData").find("select[name='useYn']").val(data.useYn);
					$("#PrdtMgmtData").find("select[name='ordLdTm']").val(data.ordLdTm);
					$("#PrdtMgmtData").find("select[name='vatYn']").val(data.vatYn);
					$("#PrdtMgmtData").find("select[name='taxYn']").val(data.taxYn);
					$("#PrdtMgmtData").find("select[name='ordUnit']").val(data.ordUnit);
					$("#PrdtMgmtData").find("select[name='stdYn']").val(data.stdYn);
					$("#PrdtMgmtData").find("select[name='timeLimit']").val(data.timeLimit);
					$("#PrdtMgmtData").find("select[name='issSluggish']").val(data.issSluggish);

					$("#PrdtMgmtSub").find("input[id='haStkQty']").val(data.haStkQty);
					$("#PrdtMgmtSub").find("input[id='yeoStkQty']").val(data.yeoStkQty);
					
					$("#PrdtMgmtSub").find("input[id='lastSalesQty']").val(data.lastSalesQty);
					$("#PrdtMgmtSub").find("input[id='twoMnSalesQty']").val(data.twoMnSalesQty);

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
	

	/*저장 버튼 이벤트 */
	function saveEvent(){

// 		if(!$('#mainform').valid()) return;

		var dataInfo = {};

		if($('#prdtNm').val() == ''){
			alert('품명을 입력하세요.');
			return;
		}
		
		if($('#prdtStd').val() == ''){
			alert('규격을 입력하세요.');
			return;
		}
		
		if($('#qtyBox').val() == 0){
			alert('박스당 수량을 입력하세요.');
			return;
		}
		
		
		$('#mainform').find('input , select').map(function() {
			 dataInfo[this.name] = $(this).val();
		});

		var regex = /[^0-9]/g;
		
		dataInfo["costStartDt"] = dataInfo.costStartDt.replace(regex, 	""); 	//숫자만 추출 
		dataInfo["buyStartDt"] 	= dataInfo.buyStartDt.replace(regex, 	""); 	//숫자만 추출 
		dataInfo["cost"] 		= dataInfo.cost.replace(/,/g, ""); 		//콤마제거
		dataInfo["baseCost"] 	= dataInfo.baseCost.replace(/,/g, 	""); 	//콤마제거
		dataInfo["estPrice"] 	= dataInfo.estPrice.replace(/,/g, 	""); 	//콤마제거

		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : '<c:url value="/app/bm/baseinfo/prdtMgmt_insData.json" />',
		      data : JSON.stringify(dataInfo),
		      success : function(data){            

		    	if(data == null) msg = '<spring:message code="message.error.process" />'; <%--"처리중 오류가 발생했습니다.";--%>
	            else if('success' == data.msgCd)
	            {
	            	msg = '<spring:message code="message.success.process" />'; 	<%--"정상적으로 처리되었습니다.";--%>
	            	dataInfo['carCd'] = data.rtnValue01;
	            	searchEvent(); 			// 리스트 데이터 제조회 
	            	detailEvent(dataInfo);	// 수정데이터 상세 제조회 
	            }
	            else if('duple' == data.msgCd) {
	            	msg ='<spring:message code="message.error.duplication" />'; <%-- "중복된 데이터 입니다.";--%>
	            }
	            else msg = '<spring:message code="message.error.process" />';	<%--"처리중 오류가 발생 하였습니다.";--%>
	          	alert(msg);
		      }
		});

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
		$('#PrdtMgmtData').find('input, textarea').each(function() {
			if(this.type != "button" && this.type != "radio"){
				$(this).val("");
			}
			
		$("#PrdtMgmtData").find("input[id='qtyBox']").val(0);
		$("#PrdtMgmtData").find("input[id='cost']").val(0);
		$("#PrdtMgmtData").find("input[id='baseCost']").val(0);
		$("#PrdtMgmtData").find("input[id='estPrice']").val(0);
		$("#PrdtMgmtData").find("input[id='caseW']").val(0);
		$("#PrdtMgmtData").find("input[id='caseL']").val(0);
		$("#PrdtMgmtData").find("input[id='caseH']").val(0);
		$("#PrdtMgmtData").find("input[id='caseWt']").val(0);
		
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
	<legend>상품마스터관리</legend>
	<table style="width: 100%" summary="" class=type1>
		<caption>상품마스터 검색조건</caption>

		<tbody id="_search">
			<tr>
				<th><label for="sele2">품목코드/명</label></th>
				<td><input type="text" id="find_prdtNm" name="find_prdtNm"></td>
				<th><label for="sele2">대분류</label></th>
					<td colspan=3>
						<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="lCd" objName="lCd" parentID="Y" defName="${commonall}" />
					</td>
				<th><label for="sele2">중분류</label></th>
					<td colspan=3>
						<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="mCd" objName="mCd" parentID="Y" defName="${commonall}" />
					</td>
			</tr>
			<tr>
				<th><label for="sele2">기한임박</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="timeLimit" objName="timeLimit" parentID="9008" defName="${commonall}" />
					</td>
				<th><label for="sele2">출고부진</label></th>
					<td>
						<html:codeTag comType="SELECT" objId="issSluggish" objName="issSluggish" parentID="9008" defName="${commonall}" />
					</td>
				<th><label for="sele2">담당자</label></th>
				<td><input type="text" id="prdtMgrPr" name="prdtMgrPr"></td>
				<th><label for="sele2">창고</label></th>
					<td>
						<html:codeTag comType="SELECT" 	dataType="PRC3"	objId="whCd" objName="whCd" parentID="Y" defName="${commonall}" />
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
				<h2 class="subhead">품목마스터 등록/수정</h2>
				<div class="btn_l">
					<html:button id="btnInit"     name="btnInit"    auth="insert" /> 		<%-- 신규  --%>
					<html:button id="btnSearch"   name="btnSearch"  auth="select" /> 		<%-- 조회 --%>
					<html:button id="btnSave"     name="btnSave"    auth="save" 	/> 		<%-- 저장  --%>
					<html:button id="btnExcel"    name="btnExcel"   auth="excel" 	/> 		<%-- 엑셀  --%>
				</div>
			</div>
			<div id="PrdtMgmtData" class="PrdtMgmtData">
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
								<html:codeTag comType="SELECT" width="90%" dataType="PRC1"	objId="lCd" objName="lCd" parentID="Y" />
							</td>
						<th><label for="sele2">중분류</label></th>
							<td>
								<html:codeTag comType="SELECT" width="90%" dataType="PRC2"	objId="mCd" objName="mCd" parentID="Y" />
								
							</td>
						<th><label for="sele2">저장형태</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="strgType" objName="strgType" parentID="M001" />
							</td>
					</tr>
					<tr>
						<th>LACK번호1</th>
						<td>
							<input type="text"   id="lackNo1"   name="lackNo1" >
						</td>
						<th>LACK번호2</th>
						<td >
							<input type="text"   id="lackNo2"   name="lackNo2" >
						</td>
						<th><label for="sele2">사용유무</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="useYn" objName="useYn" parentID="M009" />
							</td>
					</tr>
					<tr>
						<th>품목명</th>
						<td>
							<input type="text"   id="prdtNm"    name="prdtNm">
						</td>
						<th>규격</th>
						<td>
							<input type="text"   id="prdtStd"    name="prdtStd">
						</td>
						<th>품목코드</th>
						<td>
							<input type="text"   id="prdtCd"    name="prdtCd"	readonly>
						</td>
					</tr>
					<tr>
						<th><label for="sele2">단위</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="ordUnit" objName="ordUnit" parentID="M006" />
							</td>
						<th>박스당수량</th>
							<td>
								<input type="text"   id="qtyBox"    name="qtyBox" value="0">
							</td>
						<th>원산지</th>
							<td>
								<input type="text"   id="originNm"    name="originNm">
							</td>
					</tr>
					<tr>
						<th>원가적용일</th>
						<td>
							<input type="text" class="dt" id="costStartDt" name="costStartDt" readonly="readonly" style="width: 100px !important;" />
						</td>
						<th>입고단가</th>
						<td>
							<input type="text"   id="cost"    name="cost" value="0" style="text-align:right;" onkeyup="inputNumberFormat(this);">
						</td>
						<th>기준원가</th>
						<td>
							<input type="text"   id="baseCost"    name="baseCost" value="0" style="text-align:right;" onkeyup="inputNumberFormat(this);">
						</td>
					</tr>
					<tr>
						<th>매입처</th>
						<td>
							<input type="text"   id="buyNm"    name="buyNm" style="width:70%;" onclick="buyClear();">
							<input type="hidden" id="buyCd"    name="buyCd" style="width:70%;">
							<button id="btnBuyCd"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
						</td>
						<th>매입처적용일</th>
						<td>
							<input type="text" class="dt" id="buyStartDt" name="buyStartDt" readonly="readonly" style="width: 100px !important;" />
						</td>
						<th>견적가</th>
						<td>
							<input type="text"   id="estPrice"    name="estPrice" value="0" style="text-align:right;" onkeyup="inputNumberFormat(this);">
						</td>
					</tr>
					<tr>
						<th>매입처상품코드</th>
						<td>
							<input type="text"   id="buyPrdtCd"    name="buyPrdtCd">
						</td>
						<th>이전매입처</th>
						<td>
							<input type="text"     id="prevBuyNm"    name="prevBuyNm" readonly="readonly">
							<input type="hidden"   id="prevBuyCd"    name="prevBuyCd" readonly="readonly">
						</td>
						<th><label for="sele2">발주리드타임</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="ordLdTm" objName="ordLdTm" parentID="M002" />
							</td>
					</tr>
					<tr>
						<th><label for="sele2">과면세</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="vatYn" objName="vatYn" parentID="9008" />
							</td>
						<th><label for="sele2">세금계산서</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="taxYn" objName="taxYn" parentID="9008" />
							</td>
						<th>유통기한</th>
						<td>
							<input type="text"   id="exprtDt"    name="exprtDt">
						</td>
					</tr>
					<tr>
						<th>CASE가로</th>
						<td>
							<input type="text"   id="caseW"    name="caseW" value="0">
						</td>
						<th>CASE세로</th>
						<td>
							<input type="text"   id="caseL"    name="caseL" value="0">
						</td>
						<th>판매(수입원)</th>
						<td>
							<input type="text"   id="mnfct"    name="mnfct">
						</td>
					</tr>
					<tr>
						<th>CASE높이</th>
						<td>
							<input type="text"   id="caseH"    name="caseH" value="0">
						</td>
						<th>CASE중량</th>
						<td>
							<input type="text"   id="caseWt"    name="caseWt" value="0">
						</td>
<%--						
						<th>담당자</th>
						<td>
							<input type="text"   id="prdtMgrPr"    name="prdtMgrPr">
						</td>
 --%>						
 						<th><label for="sele2">영업사원</label></th>
						<td>
							<input type="text" id="salesPrNm" name="salesPrNm" style="width: 80%;">
							<input type="hidden" id="salesPrCd" name="salesPrCd" readonly="readonly"  style="width: 20%;">
							<button id="btnSalesPrMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
						</td>
					</tr>
					<tr>
						<th><label for="sele2">규격유무</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="stdYn" objName="stdYn" parentID="9008" />
							</td>
						<th><label for="sele2">기한임박</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="timeLimit" objName="timeLimit" parentID="9008" />
							</td>
						<th><label for="sele2">출고부진</label></th>
							<td>
								<html:codeTag comType="SELECT" objId="issSluggish" objName="issSluggish" parentID="9008" />
							</td>				
					</tr>
					<tr>
						<th>비고</th>
						<td colspan=3>
							<input type="text"   id="remarks"    name="remarks">
						</td>
						<th><label for="sele2">매출처명</label></th>
						<td>
							<input type="text" id="salesNm" name="salesNm" style="width: 80%;">
							<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;">
							<input type="hidden" id="hqClass" name="hqClass" style="width: 20%;">
							<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
						</td>
					</tr>
					<tr>
						<th>등록자</th>
						<td><input type="text"   id="regNm"    name="regNm" readonly="readonly"></td>
						<th>등록일시</th>
						<td colspan=3><input type="text"   id="regDt"    name="regDt" readonly="readonly" style="width:30%;"></td>
					</tr>
					<tr>
						<th>수정자</th>
						<td><input type="text"   id="modNm"    name="modNm" readonly="readonly"></td>
						<th>수정일시</th>
						<td colspan=3><input type="text"   id="modDt"    name="modDt" readonly="readonly" style="width:30%;"></td>
					</tr>
					</tbody>
				</table>
				
			</div>
		</form>
	 </li>
 	</ul>
 

<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />

<!-- 영업사원 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_salesPrMaster.jsp" />

<!-- 매출처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesMaster.jsp" />
 
 
<!-- CONTENT- BODY end ----------------------------------- -->



</div>
</body>
</html>

