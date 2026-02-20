<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">
var excelUploadCallbackFnc = null;

var excelUploadList = null;

/* Layer Event Creat */
function setExcelUploadLayerEvent(){

	$("#ExcelUpload_layer #ordDt").datepicker();
	$("#ExcelUpload_layer #searchSalesDt").datepicker();
	
	$("#ExcelUpload_layer #ordDt").datepicker('setDate','c');
	$("#ExcelUpload_layer #searchSalesDt").datepicker('setDate','c+1d');
	
	var layerID = 'ExcelUpload_layer';
	setLayerPopupLocationSettings(layerID, 200, 500);
	
	/* 레이어 활성화 */
// 	$('#ExcelUpload_layer').show();
// 	if($('#opacity').css("display") == "none") {
// 		$('#opacity').show();
// 		$('#opacity').attr("style", "height: 190%;");
// 	}

	/* 레이어 드레그 Event */
	$("#ExcelUpload_layer").draggable({
		handle: $("h1")
		,cancle: $("a.close")
		,containment: "window"
		,scroll: false
	});

	/* 레이어 닫기버튼 Click Event */
	$('#ExcelUpload_layer a.close').click(function(e){
		closeExcelUploadLayer();
	});

	$('#ExcelUpload_layer #btnSearch_find').unbind().click(null, eventSearchExcelUploadFind); 	// 조회버튼	
	$('#ExcelUpload_layer #btnExcelUpload').unbind().click(function(){ fncExcelUpload()}); 	// 엑셀업로드
	$('#ExcelUpload_layer #btnSave_find').unbind().click(function(){ saveConfirmEvent()}); 	// 저장

	//조건 입력필드 enter key이벤트 --------------
	
	initExcelUploadList();
	initExcelUploadErrorList();
	
	$(window).bind('resize', function() {
	    try{
	        // width
	        $('#ExcelUpload_uploadList').setGridWidth($('#ExcelUpload_layer #gridcontainer11').width()); //Resized to new width as per window
	        $('#ExcelUpload_uploadErrorList').setGridWidth($('#ExcelUpload_layer #gridcontainer22').width()); //Resized to new width as per window
	        
	     // height
			var height = $(window).height()-$('#grid1container')[0].offsetTop;

			if(height > 275) {
				$('#ExcelUpload_uploadList').setGridHeight(220);

				if(height-320 < 120)
					$('#ExcelUpload_uploadErrorList').setGridHeight(320);
				else
					$('#ExcelUpload_uploadErrorList').setGridHeight(height-450);
			}
	    }catch(e){}
	}).trigger('resize');
}

function initExcelUploadList() {

	$("#ExcelUpload_uploadList").jqGrid({
        datatype: 'local',
        data: [],

     // 헤더에 들어가는 이름
        colNames:[
				 '구분'
				,'RMC코드'
				,'매출처코드'
				,'매출처명'
				,'RMC품목코드'
				,'품목코드'
				,'품목명'
				,'발주수량'
				,'발주금액'
				
				,'매출일자'
				,'발주일자'
				,'부가세유무'
				,'BOX 당 수량'
				,'규격유무'
				,'단위'
				,'박스수량'
				,'단가'
                ,'공급가'
                ,'부가세'
                ,'합계금액'
                ,'비고'
                ,'매출수량'
                
				,'전표번호'
        ],

        colModel:[
        	{name:'fbClass'		   	, index:'fbClass'		, sortable:true		, width:50, 	align:"center"	} 
        	,{name:'salesCd'		, index:'salesCd'		, sortable:true		, width:50, 	align:"center"	}
        	,{name:'cltSalesCd'		, index:'cltSalesCd'	, sortable:true		, width:50, 	align:"center"	}
        	,{name:'salesNm'		, index:'salesNm'		, sortable:true		, width:120, 	align:"left"	}
        	,{name:'prdtCd'		   	, index:'prdtCd'		, sortable:true		, width:80, 	align:"center"	}
        	,{name:'salesPrdtCd'	, index:'salesPrdtCd'	, sortable:true		, width:80, 	align:"center"	}
            ,{name:'prdtNm'		   	, index:'prdtNm'		, sortable:true		, width:150, 	align:"left"	}
            ,{name:'ordQty'   		, index:'ordQty'		, sortable:true		, width:60, 	align:"right", formatter:'integer'}
            ,{name:'ordAmt'  	    , index:'ordAmt'		, sortable:true		, width:70, 	align:"right", formatter:'integer'}
            
            ,{name:'salesDt'   		, index:'salesDt'		, sortable:true		, hidden:true	, width:80, 	align:"left"	}
            ,{name:'ordDt'   		, index:'ordDt'			, sortable:true		, hidden:true	, width:80, 	align:"left"	}
            ,{name:'vatYn'   		, index:'vatYn'			, sortable:true		, hidden:true	, width:80, 	align:"left"	}
            ,{name:'qtyBox' 		, index:'qtyBox'		, sortable:true		, hidden:true	, width:80, 	align:"left"	}
            ,{name:'stdYn' 			, index:'stdYn'			, sortable:true		, hidden:true	, width:80, 	align:"left"	}
            ,{name:'ordUnit'   		, index:'ordUnit'		, sortable:true		, hidden:true	, width:80, 	align:"left"	}
            ,{name:'boxQty'   		, index:'boxQty'		, sortable:true		, hidden:true	, width:80, 	align:"left"	}
            ,{name:'price' 			, index:'price'			, sortable:false	, hidden:true}
     		,{name:'pureAmt' 		, index:'pureAmt'		, sortable:false	, hidden:true}
     		,{name:'vatAmt' 		, index:'vatAmt'		, sortable:false	, hidden:true}
     		,{name:'totAmt' 		, index:'totAmt'		, sortable:false	, hidden:true}
     		,{name:'remarks1' 		, index:'remarks1'		, sortable:false	, hidden:true}
     		,{name:'salesQty' 		, index:'salesQty'		, sortable:false	, hidden:true}
            ,{name:'salesSlipNo'	, index:'salesSlipNo'	, sortable:true		, width:120, 	hidden:true	}
            		
        ],
        gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
        	var colCount = $(this).getGridParam("colNames").length;
            $("#popBlankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;"); 
             
            var gridData = $("#ExcelUpload_uploadList");
			var allRows = gridData.jqGrid('getDataIDs');
             
            /* 조회후 데이터가 한건이라도 있을경우  */
			if(allRows.length > 0 && $("#blankRow").val() != ""){
			    $('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
				
				// grid rowNum 재설정 (-1로 설정한 경우 row가 많으면 sort 시 row수가 감소)
				//gridData.setGridParam({rowNum:allRows.length});
			} 
			
			// ===================== Footer Sum
			gridData.jqGrid('footerData', 'set', { 'salesNm' : '합계' });
			
            var sum_ordQty = gridData.jqGrid('getCol','ordQty', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'ordQty':sum_ordQty });
	    	
	    	var sum_ordAmt = gridData.jqGrid('getCol','ordAmt', false, 'sum');
	    	gridData.jqGrid('footerData', 'set', { 'ordAmt':sum_ordAmt });
	    	
        },
        loadComplete: function() {
            if ($(this).getGridParam("records")==0) {
                var firstColName = $(this).getGridParam("colModel")[1].name;
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                $(this).addRowData("popBlankRow", msg);
            } else{
            	var allRows = $(this).jqGrid('getDataIDs');
            	
				for(var i=0; i<allRows.length;i++){
					var rowId = allRows[i];
					var rowData = $(this).jqGrid('getRowData', rowId);
					
					var ordQty = parseInt(rowData.ordQty);
				    var qtyBox = parseInt(rowData.qtyBox);
				    var strQtyBox = parseInt(ordQty / qtyBox);
					var strEa = ordQty % qtyBox;

					var ordUnit = rowData.ordUnit;
				    var stdYn = rowData.stdYn;
				    
					if(stdYn == "2"){
						$(this).jqGrid('setCell',rowId,'boxQty',String(ordQty).concat(ordUnit));
				    } else {
				    	if(ordQty < qtyBox){
				    		$(this).jqGrid('setCell',rowId,'boxQty',String(ordQty).concat(ordUnit));
				    	} else if(strEa == 0){
				    		if(qtyBox > 1){
				    			$(this).jqGrid('setCell',rowId,'boxQty',String(strQtyBox).concat("BOX"));
				    		} else{
				    			$(this).jqGrid('setCell',rowId,'boxQty',String(ordQty).concat(ordUnit));
				    		}
				    	} else{
				    		$(this).jqGrid('setCell',rowId,'boxQty',String(strQtyBox).concat("BOX/", strEa, ordUnit));
				    	}
				    }
					
				    var price = parseInt(rowData.price);
				    var vatYn = rowData.vatYn;
				    
				    var pureAmt = ordQty * price;
					var vatAmt = parseInt((ordQty * price * 0.1).toFixed());
				    
				    if(vatYn == "2"){
				    	$(this).jqGrid('setCell',rowId,'pureAmt',pureAmt);
				    	$(this).jqGrid('setCell',rowId,'vatAmt',0);
				    	$(this).jqGrid('setCell',rowId,'totAmt',pureAmt);
				    } else{
				    	$(this).jqGrid('setCell',rowId,'pureAmt',pureAmt);
				    	$(this).jqGrid('setCell',rowId,'vatAmt',vatAmt);
				    	$(this).jqGrid('setCell',rowId,'totAmt',pureAmt + vatAmt);
				    }
				}
            }
            
            eventSearchErrorFind();			//  ERROR 조회
            
            $("#ExcelUpload_uploadList").trigger('resize');
            
        },
        loadError:function(xhr, status, error) {
        	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
            return false;
        },
        onSelectRow : function(id, status, e) {                                  

        }

        ,rowNum:-1
        ,loadui : "disable"
        ,gridview:    true
        //,pager: '#pageList11'
        ,sortname: 'prdtNm'
        ,sortorder: 'asc'
        ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
        ,viewrecords: true
        ,scroll : false
        ,rownumbers:true
        ,loadonce:true
        ,footerrow: true														// 전체 합계를 낼때 사용
        ,autowidth:true

    });
}
	
function initExcelUploadErrorList() {

	$("#ExcelUpload_uploadErrorList").jqGrid({
        datatype: 'local',
        data: [],

     // 헤더에 들어가는 이름
        colNames:[
				 '구분'
				,'매출처코드'
				,'매출처명'
				,'품목코드'
				,'품목명'
				,'발주수량'
				
        ],

        colModel:[
        	{name:'fbClass'		   	, index:'fbClass'		, sortable:true		, width:80, 	align:"center"	} 
        	,{name:'cltSalesCd'		, index:'cltSalesCd'	, sortable:true		, width:80, 	align:"center"	}
        	,{name:'salesNm'		, index:'salesNm'		, sortable:true		, width:80, 	align:"left"	}
        	,{name:'salesPrdtCd'	, index:'salesPrdtCd'	, sortable:true		, width:80, 	align:"center"	}
            ,{name:'prdtNm'		   	, index:'prdtNm'		, sortable:true		, width:120, 	align:"left"	}
            ,{name:'ordQty'   		, index:'ordQty'		, sortable:true		, width:80, 	align:"right", formatter:'integer'}
        ],
        gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
        	 var colCount = $(this).getGridParam("colNames").length;
             $("#popBlankRow td:nth-child(2)").attr("colspan", colCount).attr("style", "text-align: center;");
        },
        loadComplete: function() {
            if ($(this).getGridParam("records")==0) {
                var firstColName = $(this).getGridParam("colModel")[1].name;
                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	<%-- 조회결과가 없습니다.--%>
                $(this).addRowData("popBlankRow", msg);
            }
            $("#ExcelUpload_uploadErrorList").trigger('resize');
        },
        loadError:function(xhr, status, error) {
        	alert('<spring:message code="message.error.process" />');	<%-- 처리중 오류가 발생 하였습니다.--%>
            return false;
        },
        onSelectRow : function(id, status, e) {                                  
            
        }

        ,rowNum:-1
        ,loadui : "disable"
        ,gridview:    true
        //,pager: '#pageList22'
        ,sortname: 'prdtNm'
        ,sortorder: 'asc'
        ,emptyrecords : '<spring:message code="message.search.no.data" />'   <%-- 조회결과가 없습니다.--%>
        ,viewrecords: true
        ,scroll : false
        ,rownumbers:true
        ,loadonce:true
        ,autowidth:true

    });
}	

/*레이어 close*/
function closeExcelUploadLayer(){

	$("#ExcelUpload_uploadList").clearGridData();
	$("#ExcelUpload_uploadErrorList").clearGridData();
	
	$('#ExcelUpload_layer').hide();
	excelUploadCallbackFnc = null;

	if($(".pop_layer_new:visible").length == 0) {
		$('#opacity').hide();
	}
}

/* 레이어호출 */
function salesExcelUploadLayer(affiliate, hedoYn, searchCVal, callbackFnc){

	/*조건 초기화 -----------------*/
	$('#ExcelUpload_form').find('input').map(function() {
		$(this).val('');
	});

	//setTbodyInit("ExcelUpload_uploadList");	// dataList 초기화
	/*----------------------------*/

	setExcelUploadLayerEvent();

	excelUploadCallbackFnc = callbackFnc;
}

/*정상 조회*/
function eventSearchExcelUploadFind() {
	
	var searchInfo = {};
	$('#ExcelUpload_form').find('input').map(function() {
		searchInfo[this.name] = $(this).val().replaceAll('-','');
	});
	
	//Grid 초기화
	$("#ExcelUpload_uploadList").clearGridData();
	$("#ExcelUpload_uploadErrorList").clearGridData();

	$("#ExcelUpload_uploadList").jqGrid('setGridParam',{
        url:"<c:url value='/app/sm/custsales/cusShipReg_selExcelUploadList.json' />"
       ,datatype: "json" 
       ,postData: searchInfo
       ,mtype:'POST'
       ,ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' }  //ajax contentType 설정
       ,page: 1
       ,loadBeforeSend: function(jqXHR) {
        		jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
        }
	   ,jsonReader : {
			root:  "resultList",	//조회결과 데이터
			page: "page",			//현재 페이지	
			total: "pagePerCount",	//총 페이지 수
			records: "totalCount",	// 전체 조회된 데이터 총갯수 
			repeatitems: false
        }
 	}).trigger("reloadGrid");
}


/* 에러 조회 */
function eventSearchErrorFind() {
	
	var searchInfo = {};
	$('#ExcelUpload_form').find('input').map(function() {
		searchInfo[this.name] = $(this).val().replaceAll('-','');
	});

	$("#ExcelUpload_uploadErrorList").jqGrid('setGridParam',{
        url:"<c:url value='/app/sm/custsales/cusShipReg_selExcelUploadErrorList.json' />"
       ,datatype: "json" 
       ,postData: searchInfo
       ,mtype:'POST'
       ,ajaxGridOptions: { contentType: 'application/x-www-form-urlencoded; charset=utf-8' }  //ajax contentType 설정
       ,page: 1
       ,loadBeforeSend: function(jqXHR) {
        		jqXHR.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
        }
	   ,jsonReader : {
			root:  "resultList",	//조회결과 데이터
			page: "page",			//현재 페이지	
			total: "pagePerCount",	//총 페이지 수
			records: "totalCount",	// 전체 조회된 데이터 총갯수 
			repeatitems: false
        }
 	}).trigger("reloadGrid");
}

/* 최종 확정 저장 */
function saveConfirmEvent(event){
	
	//박스수량, 금액
	saveDataCheck();
	
	if(!confirm("저장하시겠습니까?")){return;}

	var saveData = {'cusSalesDlvList' : $("#ExcelUpload_uploadList").getRowData()
	        		, 'salesDt'       : $('#searchSalesDt').val().replaceAll('-','')
	        		, 'ordDt'         : $('#ordDt').val().replaceAll('-','')
	        		, 'whCd'          : $('#whCd').val()
           		   };

	$.ajax({
	    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	    url  : "<c:url value='/app/sm/custsales/cusShipExcelReg_insList.json'/>",

	    data : JSON.stringify(saveData),
 	    success : function(data){
   	 	
 	    	if(data.msgCd =="0") {
				alert("신규 : "+ data.rtnValue01+"건이 정상 처리되었습니다.");
		   	}else{
				alert("처리중 오류가 발생하였습니다. Code : "+msgCd+ "Message : "+message)
			}
  	    }
	});
}


//박스수량, 금액
function saveDataCheck(){
    
	var allRows = $('#ExcelUpload_uploadErrorList').jqGrid('getDataIDs');

	for(var i=0; i<allRows.length;i++){
		var rowid = allRows[i];
		var rowData = $('#ExcelUpload_uploadErrorList').jqGrid('getRowData', rowid);
		
		var salesQty = parseFloat(rowData.salesQty);
	    var qtyBox = rowData.qtyBox;
	    var strQtyBox = parseInt(salesQty / qtyBox);
	    
	    var strEa = salesQty % qtyBox;
	    
	    if(strEa % 1 != 0){
	    	strEa = strEa.toFixed(2);
	    }
	    	
		var ordUnit = rowData.ordUnit;
	    var stdYn = rowData.stdYn;
	   
		if(stdYn == "2"){
	    	$("#ExcelUpload_uploadErrorList").jqGrid('setCell',rowid,'boxQty',String(salesQty).concat(ordUnit));
	    } else {
	    	if(salesQty < qtyBox){
	    		$("#ExcelUpload_uploadErrorList").jqGrid('setCell',rowid,'boxQty',String(salesQty).concat(ordUnit));
	    	} else if(strEa == 0){
	    		if(qtyBox > 1){
	    			$("#ExcelUpload_uploadErrorList").jqGrid('setCell',rowid,'boxQty',String(strQtyBox).concat("BOX"));
	    		} else{
	    			$("#ExcelUpload_uploadErrorList").jqGrid('setCell',rowid,'boxQty',String(salesQty).concat(ordUnit));
	    		}
	    	} else{
	    		$("#ExcelUpload_uploadErrorList").jqGrid('setCell',rowid,'boxQty',String(strQtyBox).concat("BOX/", strEa, ordUnit));
	    	}
	    }
	    
	    var price = parseInt(rowData.price);
	    var vatYn = rowData.vatYn;
	    
	    var pureAmt = Math.round(salesQty * price);
		var vatAmt = Math.round(pureAmt * 0.1);
	    
	    if(vatYn == "2"){
	    	$("#ExcelUpload_uploadErrorList").jqGrid('setCell',rowid,'pureAmt',pureAmt);
	    	$("#ExcelUpload_uploadErrorList").jqGrid('setCell',rowid,'vatAmt',0);
	    	$("#ExcelUpload_uploadErrorList").jqGrid('setCell',rowid,'totAmt',pureAmt);
	    } else{
	    	$("#ExcelUpload_uploadErrorList").jqGrid('setCell',rowid,'pureAmt',pureAmt);
	    	$("#ExcelUpload_uploadErrorList").jqGrid('setCell',rowid,'vatAmt',vatAmt);
	    	$("#ExcelUpload_uploadErrorList").jqGrid('setCell',rowid,'totAmt',pureAmt + vatAmt);
	    }
	    
	}

	return true;
}


/* 엑셀 저장 */
function excelSave(event){
	
	var saveData = {'cusSalesDlvExcelUploadList'  : excelUploadList.resultList
					, 'salesDt'       : $('#ExcelUpload_layer #searchSalesDt').val().replaceAll('-','')
					, 'ordDt'       : $('#ExcelUpload_layer #ordDt').val().replaceAll('-','')
           };

	$.ajax({
	    contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	    url  : "<c:url value='/app/sm/custsales/cusShipReg_insExcelUploadList.json'/>",

	    data : JSON.stringify(saveData),
 	    success : function(data){
   	 	
 	    	if(data.msgCd =="0") {
				alert("신규 : "+ data.rtnValue01+"건이 정상 처리되었습니다.");
				
				eventSearchExcelUploadFind();				//  정상 조회
		   	}else{
				alert("처리중 오류가 발생하였습니다. Code : "+data.msgCd+ "Message : "+data.message)
			}
  	    }
	});
}


function fncExcelUpload(){
	_commonExcelUploadLayer('',fncExcelTempReload);
}

//엑셀업로드 콜백
function fncExcelTempReload(callBackParam) {
	excelUploadList = callBackParam;
	excelSave();
}

</script>

<div class="pop_layer_new" id="ExcelUpload_layer"
	style="margin:-320px 0 0 -328px;width:1200px; display:none;">
    <h1>출고 엑셀 업로드</h1> 
	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
         	<div class="tit_area">
                <h2>엑셀 업로드 </h2>
            </div>
            <form id="ExcelUpload_form" name="ExcelUpload_form" action="" method="post">
            	<table class="type1">
					<colgroup>
						<col width="150"/>
						<col width="*"/>
						<col width="150"/>
						<col width="*"/>
						<col width="150"/>
						<col width="*"/>
					</colgroup>
					<tbody>
						<tr>
							<th><label for="sele2">발주일자</label></th>
								<td>
									<input type="text" id="ordDt" name="ordDt" readonly="readonly"  style="width:90px">
								</td>
							<th><label for="sele2">출고예정일자</label></th>
								<td>
									<input type="text" id="searchSalesDt" name="searchSalesDt" readonly="readonly"  style="width:90px">
								</td>
							<th><label for="sele2">출고창고</label></th>
								<td>
									<html:codeTag comType="SELECT" dataType="PRC3" objId="whCd" objName="whCd" height="24px" parentID="" defValue="" />
								</td>
						</tr>
					</tbody>
				</table>
            </form>
	       </div>

	       <div id="pop_section">
				<div class="tit_area">
					<h2>업로드대상</h2>
					<div>
						<html:button id="btnExcelUpload"	auth="insert" 	value="엑셀업로드" />
						<html:button id="btnSearch_find" auth="select"  /> <%--조회 --%>
						<html:button id="btnSave_find" auth="save"  /> <%--저장 --%>
					</div>
					
	            </div>
	            <!-- centent List -------------------------->
            	<div id="gridcontainer11">
                	<table id="ExcelUpload_uploadList" ><tr><td></td></tr></table>
            	</div>
            	<div id="pageList11"></div>
            	<!-- centent List -------------------------->
            	
            	<div class="tit_area">
					<h2>매출처 품목 매핑 이상내역</h2>
	            </div>
	            <!-- centent List -------------------------->
            	<div id="gridcontainer22">
                	<table id="ExcelUpload_uploadErrorList" ><tr><td></td></tr></table>
            	</div>
            	<div id="pageList11"></div>
            	<!-- centent List -------------------------->
		</div>
	</div>
</div>

<!-- 엑셀업로드용 팝업창 start -->
	<jsp:include page="/WEB-INF/views/app/common/find_excelUploadLayer.jsp" />
<!-- 엑셀업로드 end -->
