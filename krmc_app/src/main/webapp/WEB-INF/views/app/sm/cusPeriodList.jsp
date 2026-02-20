<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">

    $(document).ready(function(){

	    $("#startDt").datepicker();
	    $("#endDt").datepicker();
	    initCusPeriodGrid();
	
	    /* BUTTON CLICK 이벤트 처리 ------------------------------------------*/
	    $('#btnSearch').unbind().click(null,         searchEvent);    // 검색
	    $('#btnNew').unbind().click(null,         	 newEvent);    // 신규 
	    $('#btnExcel').unbind().click(null,       	 excelEvent);   // 액샐 
	    $('#btnPeriodPrt').unbind().click(null,	     periodPrt); 	 // 출력
		$('#btnSales_find').unbind().click(function(){ findSalesMst()}); 	// 매출처찾기 팝업 버튼이벤트
		$("#btnPrdt_find").unbind().click(function(){findPrdtMst();	});		// 상품 팝업 찾아가기
	      
	      
	    /*Resized to new width as per window -------------------------------*/
	      $(window).bind('resize', function() {
	        try{
	            $('#tabList').setGridWidth($('#grid1container').width()); 
	
	            var height = $(window).height()-$('#grid1container')[0].offsetTop;
	
	           if(height > 280)       {
	              $('#tabList').setGridHeight(height-80);   //GRID  LIST
	           }
	            else if(height < 300){
	               $('#tabList').setGridHeight(height-150);   //GRID  LIST
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
	    
	    $('#prdtNm').unbind().keydown(function(e) {
		    switch(e.which) {
		        case 13 : findPrdtMst(this); break; // enter
		    		default : return true;
		    }
		    e.preventDefault();
		});
	    /*-----------------------------------------------------------------*/

    });
    
   /* 마스터 데이터  그리드 초기화 */
   function initCusPeriodGrid() {
      $("#tabList").jqGrid({
          datatype: "local"  // 보내는 데이터 타입
         ,data: []
         ,colNames:[  
                     '구분'
                     , '메출처명'
                     , '창고명'
                     , '매입처명'
                     , '랙번호'
                     , '품목코드'
                     , '품목명'
                     , '규격'
                     , '출고수량'
                     , '박스수량'
                     , '입고단가'
                     , '출고단가'
                     , '공급가'
                     , '부가세'
                     , '합계금액'
                     
                     , '박스당수량'
                     , '단위'
                     , '규격유무'
                     , '출고수량합계'
                     , '공급가합계'
                     , '부가세합계'
                     , '합계금액합계'
 
           ]
           ,colModel:[
                      {name:'salesClassNm'      , index:'salesClassNm'  , sortable:true, width:100, align:"center"}
                     ,{name:'salesNm'           , index:'salesNm'       , sortable:true, width:180, align:"left"}
                     ,{name:'whNm'              , index:'whNm'     	    , sortable:true, width:120, align:"center"}
                     ,{name:'buyNm'             , index:'buyNm'      	, sortable:true, width:180, align:"left"}
                     ,{name:'lackNo1'           , index:'lackNo1'      	, sortable:true, width:180, align:"center"}			//26.01.21 append by song min kyo
                     ,{name:'prdtCd'            , index:'prdtCd'    	, sortable:true, width:120, align:"center"}
                     ,{name:'prdtNm'            , index:'prdtNm'    	, sortable:true, width:180, align:"left"}
                     ,{name:'prdtStd'           , index:'prdtStd'       , sortable:true, width:100, align:"center"}
                     ,{name:'salesQty'          , index:'salesQty'      , sortable:true, sorttype:'number', width:100, align:"right", formatter:'currency', formatoptions:{thousandsSeparator:",",decimalPlaces: 2}}
                     ,{name:'boxQty'            , index:'boxQty'        , sortable:true, width:100, align:"center"}
                     ,{name:'cost'              , index:'cost'          , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
                     ,{name:'price'             , index:'price'         , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
                     ,{name:'pureAmt'           , index:'pureAmt'       , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
                     ,{name:'vatAmt'            , index:'vatAmt'        , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
                     ,{name:'totAmt'            , index:'totAmt'        , sortable:true, sorttype:'number', width:100, align:"right", formatter:'integer'}
                     
                     ,{name:'qtyBox'            , index:'qtyBox'        , sortable:true, hidden:true, width:100, align:"right"}
                     ,{name:'ordUnit'           , index:'ordUnit'       , sortable:true, hidden:true, width:100, align:"right"}
                     ,{name:'stdYn'             , index:'stdYn'         , sortable:true, hidden:true, width:100, align:"right"}
                     ,{name:'salesQtyTot'       , index:'salesQtyTot'   , hidden:true, sortable:true, width:100, align:"right", formatter:'integer'}
                     ,{name:'pureAmtTot'        , index:'pureAmtTot'    , hidden:true, sortable:true, width:100, align:"right", formatter:'integer'}
                     ,{name:'vatAmtTot'         , index:'vatAmtTot'     , hidden:true, sortable:true, width:100, align:"right", formatter:'integer'}
                     ,{name:'totAmtTot'         , index:'totAmtTot'     , hidden:true, sortable:true, width:100, align:"right", formatter:'integer'}
              
           ],
           gridComplete : function() {                                      //데이터를 성공적으로 가져오면 실행 됨
	        	var colCount = $(this).getGridParam("colNames").length;
	            $("#blankRow td:nth-child(2)").attr("colspan", colCount);
	            
	            var gridData = $("#tabList");
				var allRows = gridData.jqGrid('getDataIDs');

				// 조회후 데이터가 한건이라도 있을경우 
				if(allRows.length > 0 && $("#blankRow").val() != ""){
					$('table.ui-jqgrid-ftable tr:eq(0)').css("color","red");			// 합계 color
					
					var salesQtyTot = gridData.jqGrid('getRowData', allRows[0]).salesQtyTot;
					var pureAmtTot = gridData.jqGrid('getRowData', allRows[0]).pureAmtTot;
					var vatAmtTot = gridData.jqGrid('getRowData', allRows[0]).vatAmtTot;
					var totAmtTot = gridData.jqGrid('getRowData', allRows[0]).totAmtTot;
					
				}
				
				// ===================== Footer Sum
				gridData.jqGrid('footerData', 'set', { 'whNm' : '합계' });
				
 				gridData.jqGrid('footerData', 'set', { 'salesQty' : salesQtyTot });
 				gridData.jqGrid('footerData', 'set', { 'pureAmt' : pureAmtTot });
 				gridData.jqGrid('footerData', 'set', { 'vatAmt' : vatAmtTot });
 				gridData.jqGrid('footerData', 'set', { 'totAmt' : totAmtTot });
				
//                 var sum_salesQty = gridData.jqGrid('getCol','salesQty', false, 'sum');
// 		    	gridData.jqGrid('footerData', 'set', { 'salesQty':sum_salesQty });
		    	
// 		    	var sum_pureAmt = gridData.jqGrid('getCol','pureAmt', false, 'sum');
// 		    	gridData.jqGrid('footerData', 'set', { 'pureAmt':sum_pureAmt });
		    	
// 		    	var sum_vatAmt = gridData.jqGrid('getCol','vatAmt', false, 'sum');
// 		    	gridData.jqGrid('footerData', 'set', { 'vatAmt':sum_vatAmt });
		    	
		    	
// 		    	var sum_totAmt = gridData.jqGrid('getCol','totAmt', false, 'sum');
// 		    	gridData.jqGrid('footerData', 'set', { 'totAmt':sum_totAmt });
		    	
	            $(window).resize();
	            
	        },
			loadComplete: function() {
	        	$(".ui-jqgrid .ui-jqgrid-btable").css("cursor","pointer");
	        	$(this).jqGrid("setLabel", "rn", "No");
	            if ($(this).getGridParam("records")==0) {
	                var firstColName = $(this).getGridParam("colModel")[1].name;
	                var msg = new Function("return {'" + firstColName + "':'<spring:message code="message.search.no.data" />'}")();	// 조회결과가 없습니다.
	                $(this).addRowData("blankRow", msg);
	            }
	            
				var allRows = $(this).jqGrid('getDataIDs');
            	
				for(var i=0; i<allRows.length;i++){
					var rowId = allRows[i];
					var rowData = $(this).jqGrid('getRowData', rowId);
					
					if(rowData.boxQty == ""){
						var salesQty = parseInt(rowData.salesQty);
					    var qtyBox = parseInt(rowData.qtyBox);
					    var strQtyBox = parseInt(salesQty / qtyBox);
						var strEa = salesQty % qtyBox;

						var ordUnit = rowData.ordUnit;
					    var stdYn = rowData.stdYn;
					    
						if(stdYn == "2"){
							$(this).jqGrid('setCell',rowId,'boxQty',String(salesQty).concat(ordUnit));
					    } else {
					    	if(salesQty < qtyBox){
					    		$(this).jqGrid('setCell',rowId,'boxQty',String(salesQty).concat(ordUnit));
					    	} else if(strEa == 0){
					    		if(qtyBox > 1){
					    			$(this).jqGrid('setCell',rowId,'boxQty',String(strQtyBox).concat("BOX"));
					    		} else{
					    			$(this).jqGrid('setCell',rowId,'boxQty',String(salesQty).concat(ordUnit));
					    		}
					    	} else{
					    		$(this).jqGrid('setCell',rowId,'boxQty',String(strQtyBox).concat("BOX/", strEa, ordUnit));
					    	}
					    }
					}
				}
	            
	        	$(window).resize();
	        },
	        loadError:function(xhr, status, error) {  //데이터 못가져오면 실행 됨
				alert('<spring:message code="message.error.process" />');
				return false;
            }
	        
	        ,rowNum:1000
            ,pager: '#pageList'
			,loadui : "disable"													
			,gridview: true														
			,viewrecords: true													
			,emptyrecords : ''
			,rownumbers: true													
			,autowidth:true		
			,loadonce : true
			,shrinkToFit : false
			,footerrow: true													
			,sortorder : "desc"
		});
    }
   
    /* 신규 */
    function newEvent(event){
        $('form').each(function() {
            this.reset();
        });
		
        //Grid 초기화
		$("#tabList").jqGrid('clearGridData');
	}
    

    /* 조회 */
    function searchEvent(event){
     
       var searchInfo = {};
           
       $('#searchForm').find('input').map(function() {
          searchInfo[this.name] = $(this).val().replaceAll('-','');
       });
       $('#searchForm').find('select').map(function() {
           searchInfo[this.name] = $(this).val();
       });

      //Grid 초기화
      $("#tabList").jqGrid('clearGridData');      
      
      $("#tabList").jqGrid('setGridParam',{
         url:"<c:url value='/app/sm/custsales/cusPeriodList_selList.json'/>",
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
            root:  "resultList",   //조회결과 데이터
            page: "page",         //현재 페이지   
            total: "pagePerCount",   //총 페이지 수
            records: "totalCount",   // 전체 조회된 데이터 총갯수 
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
        $("#salesCd").val(data.salesCd);
        $("#salesNm").val(data.salesNm);
        $("#hqClass").val(data.hqClass);
        
        $("#prdtNm").focus();
    }
    
    /*상품 찾기팝업 호출 */
    function findPrdtMst() {
//    	commonSalesPrdtMstFindLayer('', '', $("#prdtNm").val(), $("#salesCd").val(), setSalesPrdtMstFindLayer);
//		commonPrdtMstFindLayer('','',$("#prdtNm").val(), $("#salesCd").val(), setSalesPrdtMstFindLayer);


		if($("#salesCd").val() != ''){
			commonCurOrgRegSalesPrdtMstFindLayer('', '', $("#prdtNm").val(), $("#salesCd").val(), setCurOrdRegSalesPrdtMstFindLayer);
		}else{
			alert('매출처를 선택하세요.');
			return;
		}


    }
   
    /*상품 콜백(후처리 함수 )*/
    function setCurOrdRegSalesPrdtMstFindLayer(data) {
        if (data != null){
           $("#prdtCd").val(data.prdtCd);
           $("#prdtNm").val(data.prdtNm);
        }
    }
    
    /* 엑셀  */
    function excelEvent(){
    	$('#searchForm').attr("action", "<c:url value='/app/sm/custsales/cusPeriodList_selExcelDown'/>").submit();
    }	
   
   	/*PDF Report*/
	function periodPrt(){		
		searchForm.action="<c:url value="/app/sm/custsales/cusPeriodListPrint" />";
		searchForm.submit();
	}
   	
	function sales_clear(event){
		$('#salesNm').val('');
		$('#salesCd').val('');
		$('#hqClass').val('');
	}

	function prdt_clear(event){
		$('#prdtNm').val('');
		$('#prdtCd').val('');
	}
	
</script>
</head>
<body>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" var="commonall" />  <%--전체  --%>


<!-- 검색조건 start ----------------------------------------->
<form id="searchForm" name="searchForm" method="post" autocomplete="off">
<input type="hidden" id="hqClass" name="hqClass"/>
<sec:csrfInput/>
   <fieldset>
   <legend>기간별출고현황</legend>
   <table style="width: 100%" summary="" class=type1>
      <caption>기간별출고현황 검색조건</caption>
      <colgroup>
         <col width="150">
         <col width="17%">
         <col width="150">
         <col width="*">
         <col width="150">
         <col width="*">
         <col width="150">
         <col width="*">
      </colgroup>
      <tbody id="_search">
         <tr>
         	<th> <label for="sele2">조회일자</label></th>
				<td>
					<input type="text" class="dt" id="startDt" name="startDt"  readonly="readonly" style="width: 40% !important;" maxlength="8" value="${util:getYearMonthFirstDay()}"  />
					<input type="text" class="dt" id="endDt" name="endDt"  readonly="readonly" style="width: 40% !important;" value="<fmt:formatDate value="${now}" pattern="${localeDatePattern}" />"/> 
				</td>
            <th><label for="sele2">매출처명</label></th>
            	<td>
              		<input type="text" id="salesNm" name="salesNm" style="width: 80%;" onclick="sales_clear()">
						<input type="hidden" id="salesCd" name="salesCd" style="width: 20%;">
						<button id="btnSales_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
            	</td>
         	<th><label for="sele2">품목명</label></th>
            	<td>
               		<input type="text"   id="prdtNm"  name="prdtNm" style="width:80%;" onclick="prdt_clear()">
					<input type="hidden" id="prdtCd"  name="prdtCd" style="width: 20%;">
					<button id="btnPrdt_find"  class="button btn_find" type="button"><i class="fa fa-search"></i></button> 
            	</td>
            <th><label for="sele2">과면세구분</label></th>
                <td>
                    <html:codeTag comType="SELECT" objId="taxYn" objName="taxYn" parentID="M021"  defName="${commonall}"   />
                </td>
         </tr>
         <tr>   
            <th><label for="sele2">매출처구분</label></th>
                <td>
                    <html:codeTag comType="SELECT" objId="salesClass" objName="salesClass" parentID="M013"  defName="${commonall}" />
                </td>
            <th><label for="sele2">창고구분</label></th>
                <td>
                    <html:codeTag comType="SELECT"    dataType="PRC3"   objId="whCd" objName="whCd" parentID="Y" defName="${commonall}" />
                </td>
            <th><label for="sele2">대분류</label></th>
                <td>
                    <html:codeTag comType="SELECT"    dataType="PRC1"   objId="lCd" objName="lCd" parentID="Y" width="150px" defName="${commonall}" />
                </td>
            <th><label for="sele2">중분류</label></th>
                <td>
                    <html:codeTag comType="SELECT"    dataType="PRC2"   objId="mCd" objName="mCd" parentID="Y"  width="150px" defName="${commonall}" />
                </td>
         </tr>
         
         <tr>   
            <th><label for="sele2">영업사원</label></th>
                <td>
                    <html:codeTag comType="SELECT" 	dataType="SALESPRCD"	objId="srchSalesPrCd" objName="srchSalesPrCd" parentID="Y"  defName="${commonall}"/>
                </td> 
                
              <th><label for="sele2">&nbsp;</label></th>
                <td>
                    &nbsp;
                </td>
                
              <th><label for="sele2">&nbsp;</label></th>
                <td>
                    &nbsp;
                </td>
               <th><label for="sele2">&nbsp;</label></th>
                <td>
                    &nbsp;
                </td>                                     
         </tr>
         
      </tbody>
   </table>
   </fieldset>
</form>
<!-- 검색조건 end ----------------------------------------- -->

<!-- 대분류 서브타이틀 및 이벤트 버튼 end     -------------------- -->
 <UL style="width: 100%;">
    <li style="float: left; width: 100%; ">
       <!-- 서브타이틀 및 이벤트 버튼 start  -------- -->
       <div class="tit_area" >
           <h2>기간별출고현황</h2>
           <div>
                <html:button id="btnNew"            name="btnNew"          auth="insert" />       <%-- 신규 --%>
                <html:button id="btnSearch"         name="btnSearch"       auth="select" />       <%-- 조회 --%>
                <html:button id="btnExcel"          name="btnExcel"        auth="excel"    />  
<%--             	<html:button id="btnPeriodPrt" 		name="btnPeriodPrt"    auth="select"  value="출력"  />   --%>
           </div>
      </div>
      <!-- 서브타이틀 및 이벤트 버튼 end  -------- -->
         
         <!-- centent List -------------------------->
        <div id="grid1container">
           <table id="tabList" ><tr><td></td></tr></table>
        </div>
        <div id="pageList"></div>
        <!-- centent List -------------------------->
    </li>
 
<!-- CONTENT- BODY end ----------------------------------- -->

</div>

<!-- 매출처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesMaster.jsp" />

<!-- 상품 찾기  레이어 영역 start  -->
<%-- 2025.03.27 상품찾기 레이어 페이지 변경 by song min kyo (find_SalesPrdtMaster.jsp <-- 기존에 사용하던 페이지) 
<%-- <jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" /> --%>

<!-- 매출처별 상품 찾기 재고 포함  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_CurOrdRegSalesPrdtMaster.jsp" />



</body>
</html>
