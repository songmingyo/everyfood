<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<sec:authorize access="isAuthenticated()">
	<spring:eval expression="userSession.userType"		var="userType" />
</sec:authorize>
<c:set var="today" value="<%=new java.util.Date()%>" />
<c:set var="ordDt"><fmt:formatDate value="${today}" 	pattern="yyyyMMdd" /></c:set>
<c:set var="todayFmt"><fmt:formatDate value="${today}"	pattern="yyyy-MM-dd" /></c:set>

        
<script type="text/javascript">

var noDataMsg 		= '<spring:message code="message.search.no.data" />';	<%--조회결과가 없습니다. --%>
var sr = /[^0-9]/g;	// 숫자만 표현 
var searchObjName = 'mainFormSearch';  /*searchType(mainFormSearch/ detailFormSearch)*/


$(document).ready(function($){

	setTbodyNoResult("salesOrdSlipList",11,noDataMsg);
	
	<%--  Button Event --%>
	$('#mainFormSearch #btnSalesMstFind').unbind().click(function(){findSalesMst();});		// 매출처 팝업 찾아가기

	<%--  Search Button Event --%>
	$('#btnOrdSlipSearch').click(function(e){
		searchObjName = 'mainFormSearch';
		$('#page').text('1'); 	// 페이지번호 초기화
		ordSlipSearch();		// 매출처 발주 조회 
		e.preventDefault();
	});

	<%-- Detail Search Modal Show Button Event --%>
	$('#btnModalDetail').click(function(e){
		$('#modalDetailSearch').modal("show");
		e.preventDefault();
	});

	<%--  Detail Modal Search Button Event --%>
	$('#btnSearchDetail').click(function(e){	
		
		searchObjName = 'detailFormSearch';
		$('#page').text('1'); 	// 페이지번호 초기화
		$('#modalDetailSearch').modal("hide");
		ordSlipSearch();		// 매출처 발주 조회 
		e.preventDefault();
	});


	<%-- Order Items Modal Close Button Event --%>
	$('#btnCloseItemsModal').click(function(e){
		$('#modalDetailOrderItems').modal("hide");
		e.preventDefault();
	});


	<%-- Detail Search Modal Close Button Event --%>
	$('#btnCloseModal').click(function(e){
		$('#modalDetailSearch').modal("hide");
		e.preventDefault();
	});
	

	<%-- Detail Order(Items list) or Process List Data Modal open Select TD ID Event  --%>
	$('#salesOrdSlipList').delegate( "a", "click", function() {
		
		var rowData = {};
		$(this).parent().parent().find("td").each(function(){
			rowData[$(this).attr('id')] = $(this).html();
		});
		
		if($(this).parent().attr('id') =='salesSlipNoView') {
			$('#modalDetailOrderItems').modal("show");
			doLoadOrderItems(rowData);	// Select items Detail List
		} else return;
	});
	
	
	<%-- ORDER DY, SPLY DY  DATEPICKER 설정 (D-1) --%>
	$('#mainFormSearch #ordDtFrDy').datepicker('setDate', '-1d');		// MAIN 검색  order dt from
	$('#mainFormSearch #ordDtToDy').datepicker('setDate', 'c');			// MAIN 검색  order dt to
	
	$('#detailFormSearch #ordDtFrDy').datepicker('setDate', '-1d');		// DETAIL 검색  order dt from
	$('#detailFormSearch #ordDtToDy').datepicker('setDate', 'c');		// DETAIL 검색  order dt to
	
	$('#detailFormSearch #dlvDtFrDy').datepicker();						// DETAIL 검색  dlv dt from
	$('#detailFormSearch #dlvDtToDy').datepicker();						// DETAIL 검색  dlv dt to
	
	
});



/*매출처 찾기팝업 호출 */
function findSalesMst() {

	commonSalesMstFindLayer('',$("#form-custOrdReg #salesNm").val(), setSalesMstFindLayer);
}


/*매츨처 콜백(후처리 함수 )*/
function setSalesMstFindLayer(data) {
	if (data != null){
		$("#form-custOrdReg #salesCd").val(data.salesCd);
		$("#form-custOrdReg #salesNm").val(data.salesNm);

		if(data.creLim) 	$("#creLim").text(numberComma(data.creLim));		// 여신한도
		if(data.balRec)		$("#balRec").text(numberComma(data.balRec));		// 미수잔액
		if(data.ordDdlnTm)	$("#ordDdlnTm").text(data.ordDdlnTm.replace(/(\d{2})(\d{2})/, '$1:$2'));	/* 2400 > 24:00 */		

		setTbodyInit("salesOrdSlipList");	// DataTable (tbody)초기화
		setTbodyNoResult("salesOrdSlipList",1,noDataMsg);

		$("#headSum #sumQty").text('0');
		$("#footerSum #sumQty").text('0');

		if($("#form-custOrdReg #salesCd").val()) ordSlipSearch();	
	}
}

/*매출처 발주 현황 조회 */
function ordSlipSearch(){

	var searchInfo = {};

	<%-- Main / Detail 검색조건 parameter  --%>
	$('#'+searchObjName).find('input, select').map(function() {
		searchInfo[this.id] = $(this).val();
	});

	searchInfo['ordDtFrDy']	= $('#'+searchObjName+ ' #ordDtFrDy').val().replace(sr, "");

	$.ajax({
		url: '<c:url value="/app/res/sm/custsales/cusOrdRegList_selSlip.json" />',
	    data: JSON.stringify(searchInfo),
	    type: "POST",

	    beforeSend: function(xhr) {
	          xhr.setRequestHeader("Accept", "application/json");
	          xhr.setRequestHeader("Content-Type", "application/json");
	    },
	    
	    success: function(data) {

				setTbodyInit("salesOrdSlipList");	// DataTable (tbody)초기화
				if(!data || !data.resultList || data.resultList.length <= 0)  {
					setTbodyNoResult("salesOrdSlipList",6,noDataMsg);
					return;
				}

				<%-- TBODY DATA SET --%>
				$("#salesOrdSlipTemplate").tmpl(data.resultList).appendTo("#salesOrdSlipList");

				<%-- Set Paging info --%>
				var page = data.paging;
				if(page) {
					$('#pagingInfo').text(page.totalCount);
					$('#endRowNum').text(page.endRowNum);

					$('#salesOrdSlipList tr').css('cursor','pointer'); 	// TR ROT CURSOR POINT
					$('#tableData_paging-tmpl').tmpl(page.list).insertBefore($('#paging').empty()).appendTo('#paging');   // PAGING BUTTON UI
				}

		}
	});
	
}

<%-- PRODUCT DETAIL DATA LOAD --%>
function doLoadOrderItems(rowData){

	/* set Order head Info*/
	$('#orderDetailForm').find("span").each(function(){
		$(this).text(   rowData[$(this).attr('id')]    );
	});

	
	if(!rowData || !rowData.salesSlipNo) return;

	/*발주품목 리스트 조회 */
	var searchInfo = {};
	searchInfo['salesSlipNo'] = rowData.salesSlipNo;	//발주전표번호

	$.ajax({
		url: '<c:url value="/app/res/sm/custsales/cusOrdRegList_selProd.json" />',
	    data: JSON.stringify(searchInfo),
	    type: "POST",

	    beforeSend: function(xhr) {
	          xhr.setRequestHeader("Accept", "application/json");
	          xhr.setRequestHeader("Content-Type", "application/json");
	    },

	    success: function(data) {

				setTbodyInit("orderItemsList");	// DataTable (tbody)초기화
				if(data.length <= 0)  {
					setTbodyNoResult("orderItemsList",8,noDataMsg);
					return;
				}
				$("#orderItemsListTemplate").tmpl(data).appendTo("#orderItemsList");	// TBODY DATA SET
				
		}
	});
	

	
}

/* 매출처 품목 관리 상세조회 */
function salesProdDetailInfo(salesCd,prdtCd){
	if(!salesCd || !prdtCd) return;
	var searchVal = {};
	searchVal['salesCd'] = salesCd;
	searchVal['prdtCd'] = prdtCd;
	
	commonSalesProdMstFindLayer('',searchVal,null);
}

//매츨처정보 조회 
function salesMstDataFind(){

	var searchInfo = {};
	searchInfo['salesCd'] 	   = $("#ord_prod_form #salesCd").val();	// 매출처코드



	$.ajax({
	      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	      url  : '<c:url value="/app/common/findSalesMasterData" />',
	      data : JSON.stringify(searchInfo),
	      success : function(data){

		     if(data == null) alert('매출처 기본정보 조회가 정상적으로 처리되지 않았습니다.');
	         else setSalesMstFindLayer(data);
         }

	});
	
}
</script>


<%-- ORDER MASTER DATATABLE  --%>
<script id="salesOrdSlipTemplate"  type="text/x-jquery-tmpl">
<tr class='rows'>
	<td id='rnum' 				class='text-center'>\${rnum}</td>
<%--
	<td id='salesSlipNoView' 	class='text-center'>
		<a href='javascript:void(0);'><span ><i class='fa fa-search'></i> \${salesSlipNo}<span></a>	
		<span class='label label-primary'>\${ordItemCount}</span> 
	</td>
--%>
	<td id='salesSlipNo'    class='text-center'>\${salesSlipNo}</td>
	<td id='ordDtLocale'	class='text-center' >\${ordDtLocale}</td> 
	<td id='dlvDtLocale'	class='text-center'>\${dlvDtLocale}</td> 
	<td id='salesNm' 		class='text-left'>\${salesNm}</td>
	<td id='prdtNm' 		class='text-left'>\${prdtNm}</td>
	<td id='ordQty' 		class='text-right'>\${Number(ordQty)}</td>

	<td id='pureAmt' 		class='text-right'>\${numberComma(pureAmt)}</td>
	<td id='vatAmt' 		class='text-right'>\${numberComma(vatAmt)}</td>
	<td id='totAmt' 		class='text-right'>\${numberComma(totAmt)}</td>
	<td id='regUserNm' 		class='text-center'>\${regUserNm}</td>

 	<td id='salesSlipNo' 	style='display:none;'>\${salesSlipNo}</td>
	<td id='ordItemCount' 	style='display:none;'>\${ordItemCount}</td>
</tr>
</script>


<%-- ORDER DETAIL ITEM DATATABLE  --%>
<script id="orderItemsListTemplate"  type="text/x-jquery-tmpl">
<tr class='rows'>
	<td id='salesSeq'	class='text-center'>\${salesSeq}</td>
	<td id='prdtCd'		class='text-center'>\${prdtCd}</td>
    <td id='prdtNm'		class='text-left'>\${prdtNm}</td>
    <td id='prdtStd'	class='text-center'>\${prdtStd}</td>
 	<td id='ordUnit'	class='text-center'>\${ordUnit}</td>
    <td id='ordQty'		class='text-right'>\${numberComma(ordQty)}</td>
	<td id='boxQty'		class='text-right'>\${numberComma(boxQty)}</td>
	<td id='price'		class='text-right'>\${numberComma(price)}</td>
    <td id='pureAmt'  	class='text-right'>\${numberComma(pureAmt)}</td>
    <td id='vatAmt'  	class='text-right'>\${numberComma(vatAmt)}</td>
    <td id='totAmt'  	class='text-right'>\${numberComma(totAmt)}</td>
</tr>
</script>

<%-- PAGING BUTTON NAV  --%>
<script type="text/x-jquery-tmpl" id="tableData_paging-tmpl" >
{%if pageNumber == '<<'%}
	<li><a href="javascript:;"  link="\${linkPageNumber}"><i class="fa fa-angle-double-left"></i></a></li>
{%elif pageNumber == '<'%}
	<li><a href="javascript:;"  link="\${linkPageNumber}"><i class="fa fa-angle-left"></i></a></li>
{%elif pageNumber == '>'%}
	<li><a href="javascript:;"  link="\${linkPageNumber}"><i class="fa fa-angle-right"></i></a></li>
{%elif pageNumber == '>>'%}
	<li><a href="javascript:;"  link="\${linkPageNumber}"><i class="fa fa-angle-double-right"></i></a></li>
{%elif cl == 'on'%}
	<li class="active"><a href="javascript:;"  link="\${linkPageNumber}">\${pageNumber}</a></li>
{%else%}
	<li><a href="javascript:;"  link="\${linkPageNumber}">\${pageNumber}</a></li>
{%/if%}
</script>

<!-- Content Wrapper. Contains page content -->
 <div class="content-wrapper">

	<!-- Content Header (Page header) -->
	<jsp:include page="/WEB-INF/views/common/include/incResPageTitle.jsp" />

	<!-- Main content -->
	<section class="content">
	
		
		
		<div class="box box-primary ">
			<div class="box-header with-border">
				
				<div class="box-title user-block">
					<div style="padding-bottom: 4px; font-size: 0.9em; font-weight: bold;"><a>발주 정보 조회</a></div>
				</div>
				<div class="box-tools pull-right">
	            	<button id="btnOrdSlipSearch" class="btn btn-danger btn-sm" type="button" >
						<i class="fa fa-search"></i> <spring:message code="button.search" />
					</button>
                </div>
			</div>
			<div class="box-body"  id="mainFormSearch">
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="form-group">
						<label>발주일자</label>
						<div class="input-group">
	                     	<input type="text" class="form-control pull-left" id="ordDtFrDy" style="width: 48%;" placeholder='To' 	readonly="readonly" />
						</div><!-- /.input group -->
	                </div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="form-group">
						
						<c:choose>
							<c:when test="${userSession.userType == 'S1'}">
								<div class="input-group" id="form-custOrdReg">
								<input type="text" class="form-control pull-right"	 id="salesNm" name="salesNm" placeholder='매출처명' value="${userSession.username}" readonly="readonly"/>
								<input type="hidden" class="form-control pull-right" id="salesCd" name="salesCd" placeholder='매출처코드' value="${userSession.userId}" readonly="readonly" />
							</div>
						</c:when>
						<c:otherwise>
							<div id="form-custOrdReg">
								<input type="text" class="form-control pull-left"	 id="salesNm" name="salesNm" style="width: 80%;" placeholder='매출처명'/>
								<input type="hidden" class="form-control pull-right" id="salesCd" name="salesCd" placeholder='매출처코드' readonly="readonly" />
								<button type="button" id="btnSalesMstFind" class="btn btn-primary btn-flat"><i class="fa fa-search"></i> <spring:message code="button.search" />  </button>
	                   		</div>
						</c:otherwise>
						</c:choose>
						
<!-- 						<div class="mailbox-read-info" style="padding-top: 5px; text-align: center;"> -->
<!-- 							<ul class="list-unstyled"> -->
<!-- 								<li style="float: left; width: 50%; text-align: left;"> -->
<!-- 									<span class="badge bg-yellow">여</span> -->
<!-- 									<span id="creLim">0</span> -->
<!-- 								</li>	 -->
<!-- 								<li style="float: left; width: 50%; text-align: left;"> -->
<!-- 									<span class="badge bg-red">미</span> -->
<!-- 									<span id="balRec">0</span> -->
<!-- 							</ul> -->
<!-- 						</div> -->
					</div>	
				</div>
			</div>
		</div>
		<!-- Default box -->
		<div class="box box-body">
			<!-- box box-solid  Master Data -->
			<div class="box box-solid table-responsive no-padding">
				<table id="orderTable" class="table  table-bordered">
					<thead>
	                	<tr class="thead_dark">
							<th class="text-center">#</th>
							<th class="no-sort text-center">전표번호</th>
							<th class="text-center">발주일자</th>
							<th class="text-center">출고일자</th>
							<th class="text-center">매출처</th>
							<th class="text-center">품목명</th>
							<th class="text-center">수량</th>
							<th class="text-center">공급가</th>
							<th class="text-center">부가세</th>
							<th class="text-center">합계금액</th>
							<th class="text-center">등록자</th>
						</tr>
					</thead>	
					<tbody id="salesOrdSlipList">			
				</table>
			</div>
		</div>	
    </section>
    <!--/. Main content -->
 </div>
<!-- /.Content Wrapper. Contains page content -->




<!-- modal fade Detail Search -->
 <div class="modal fade" id="modalDetailSearch" role="dialog">

  <div class="modal-dialog modal-lg">
	<!-- Modal content-->
    <div class="modal-content">
    	<!-- modal header  -->
		<div class="modal-header ">
	    	<button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title text-blue"><i class="fa fa-search-plus"></i> 상세조회 </h4>
		</div>
	    <!-- /.modal header  -->


    	<!-- modal body -->
	    <div id="detailFormSearch" class="modal-body">

			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="form-group">
                	<c:choose>
						<c:when test="${userSession.userType == 'SA' || userSession.userType == 'SU'}">
							<div class="input-group" id="form-custOrdReg">
								<input type="text" class="form-control pull-right"	 id="salesNm" name="salesNm" 	placeholder='매출처명'  readonly="readonly"/>
								<input type="hidden" class="form-control pull-right" id="salesCd" name="salesCd" 	placeholder='매출처코드' readonly="readonly" />
	                   			
								<span class="input-group-btn">
									<button type="button" id="btnSalesMstFind" class="btn btn-primary btn-flat"><i class="fa fa-search"></i></button>
								</span>
							</div>
						</c:when>
						<c:otherwise>
							<div id="form-custOrdReg">
								<input type="text" class="form-control pull-right"	 id="salesNm" name="salesNm"  value='<sec:authentication property="principal.compNm"/>'	placeholder='매출처명'  readonly="readonly"/>
								<input type="hidden" class="form-control pull-right" id="salesCd" name="salesCd"  value='<sec:authentication property="principal.compCd"/>'	placeholder='매출처코드' readonly="readonly" />
	                   		</div>
						</c:otherwise>
					</c:choose>
                 </div>
            </div>

			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="form-group">
					<label>발주일자</label>
					<div>
						<input type="text" class="form-control pull-right" id="ordDtToDy" style="width: 45%;" placeholder='To' 	readonly="readonly" />
					</div>
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="form-group">
                	<label for="deptName">전표번호</label>
                    <input type="text" class="form-control" id="salesSlipNo" name="salesSlipNo" >
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="form-group">
					<label>납품일자</label>	
					<div class="input-group">
						<div class="input-group-addon"><i class="fa fa-calendar"></i></div>
						<input type="text" class="form-control pull-right" id="dlvDtToDy" style="width: 50%;" placeholder='To' 	readonly="readonly" />
	                    <input type="text" class="form-control pull-right" id="dlvDtFrDy" style="width: 50%;" placeholder='From' readonly="readonly"	/>
					</div>
				</div>
			</div>
			

		</div>
    	<!-- /.modal body -->

    	<!-- modal footer -->
	    <div class="modal-footer">
	    	<button id="btnCloseModal" class="btn btn-default" type="button" >
				<i class="fa fa-close"></i> <spring:message code="button.cancle" />
			</button>

	    	<button id="btnSearchDetail" class="btn btn-primary" type="button" >
				<i class="fa fa-search"></i> <spring:message code="button.search" />
			</button>
		</div>
		<!-- /.modal footer -->
    </div>
   </div>
   <!-- /.Modal content-->
</div>
<!-- /.modal fade Detail Search -->


<!-- modal fade Detail Order Items -->
 <div class="modal fade" id="modalDetailOrderItems" role="dialog">
	 <div class="modal-dialog modal-lg">
		<!-- Modal content-->
	    <div class="modal-content">
	    	<!-- modal header  -->
			<div class="modal-header ">
		    	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title text-blue"><i class="fa fa-search-plus"></i> 발주 품목 상세 </h4>
			</div>
		    <!-- /.modal header  -->


	    	<!-- modal body -->
		    <div class="modal-body" style="padding-left: 0px; padding-right: 0px;">
		    	<!-- /.head title info -->

		    	<div id="orderDetailForm" class="mailbox-read-info">
					<h3><i class="fa fa-bookmark-o"></i> <span id="salesSlipNo"></span>
						<span id="ordQty" 	class='badge bg-yellow'>0</span>
						<span id="totAmt"   class='badge bg-red'>0</span>
					</h3>
                	<h5>
                		<div><strong>매출처명 </strong> : 	  <span id="salesNm"></span></div>
                	 	<div style="padding-top: 5px;">
                	 		 <span class='badge bg-aqua'>발주</span> <span id="ordDtLocale"></span>&nbsp;
                	 		 <span class='badge bg-blue'>납품</span> <span  id="dlvDtLocale"></span>
                	 	</div>
                	 </h5>
                </div>
                <!-- /.head title info -->

                 <!-- items content info -->
                <div class="mailbox-read-message" id="orderItemsContent">
               		<div class="box box-solid table-responsive no-padding">
	               		<table id="orderItemsTable" class="table  table-bordered">
	                    	<thead>
	                    	<tr class="thead_gray">
	                    		<th class="text-center">발주순번</th>		
	                    		<th class="text-center">품목코드</th>		
			                	<th class="text-center">품명</th>	
			                	<th class="text-center">규격</th>
								<th class="text-center">단위</th>
								<th class="text-center">발주수량 </th>	
								<th class="text-center">박스수량 </th>	
								<th class="text-center">단가</th>	
								<th class="text-center">공급가</th>
								<th class="text-center">부가세</th>
								<th class="text-center">합계</th>	
							</tr>
		                	</thead>
		                   <tbody id="orderItemsList" data-link="row" class="rowlink hand-cursor"> </tbody>
						</table>
					</div>
                </div>
                  <!-- /.items content info -->
		    </div>
		    <!-- /.modal body -->

		    <!-- modal footer -->
		    <div class="modal-footer">
		    	<button id="btnCloseItemsModal" class="btn btn-default" type="button" >
					<i class="fa fa-close"></i> <spring:message code="common.close" />
				</button>
			</div>
			<!-- /.modal footer -->

		</div>
	</div>
 </div>
<!-- /.modal fade Detail Order Items -->


<!-- 매출처 찾기 모달 레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find-res_SalesMaster.jsp" />
<!-- /매출처 찾기 모달  레이어 영역 end  -->


<!-- 상품 상세  모달 레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/sm/resSalesProdDetails.jsp" />
<!-- / 상품 상세  모달  레이어 영역 end  -->
