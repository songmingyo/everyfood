<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">

var noDataMsg 		= '<spring:message code="message.search.no.data" />';	<%--조회결과가 없습니다. --%>
$(document).ready(function($){

	<%-- PageNo Click Event   --%>
	$('#paging').delegate( "a", "click", function() {
		$('#page').text($(this).attr('link')); 	// 페이지번호설정
		eventSearchSales();						// 페이지번호 변경 후 조회 실행
	});

	<%-- 리스트 선택 Row Event  --%>
	$('#salesMstList').delegate( "tr", "click", function() {
		
		var rowData = {};
		$(this).find("input").each(function(){
			rowData[$(this).attr('id')] = $(this).val();
		});
		
		if(comSalesMstCallbackFnc) comSalesMstCallbackFnc(rowData);
		eventCloseSalesModal();
	});
	
});

function eventCloseSalesModal(){
	$('#find_salesmst_find_modal').modal("hide");
}


var comSalesMstCallbackFnc = null;

/* Layer Event Creat */
function setFindSalesMstFindLayerEvent(){

	
	$('#find_salesmst_find_modal #btnCloseModal').unbind().click(null, eventCloseSalesModal); 		// 닫기버튼
	$('#find_salesmst_find_modal #btnSearch_find_sales').unbind().click(null, eventSearchSales); 	// 조회버튼

	/* 모달 레이어 활성화 */
	$('#find_salesmst_find_modal').modal("show");

	//조건 입력필드 enter key이벤트 --------------
	$('#find_salesNm').unbind().keydown(function(e) {

		switch(e.which) {
    	case 13 : eventSearchSales(this); break; // enter
    	default : return true;
    	}
    	e.preventDefault();
   	});


	/*조건 초기화 -----------------*/
	$('#find_salesmst_find_form').find('input').map(function() {
		$(this).val('');
	});

	setTbodyInit("salesMstList");	// DataTable (tbody)초기화
	$("#paging li").remove();
	setTbodyNoResult("salesMstList",6,noDataMsg);
	
}


/* 레이어호출 */
function commonSalesMstFindLayer(affiliate,searchVal,callbackFnc){

	setFindSalesMstFindLayerEvent();

	if(searchVal) {
		$("#find_salesNm").val(searchVal);
		eventSearchSales();
	}

	$("#find_salesNm").focus();
	comSalesMstCallbackFnc = callbackFnc;
}

/* Data 검색 */
function eventSearchSales(){

	var searchInfo = {};
	
	$("#searchHqClass").val("20");
	
	$('#find_salesmst_find_form').find('input').map(function() {
		searchInfo[this.name] = $(this).val();
	});
	

	<%--  PAGE INFO --%>
	if($('#find_salesmst_find_modal #page').text())
		searchInfo['page'] = $('#find_salesmst_find_modal #page').text();
	else searchInfo['page'] = "1";
	searchInfo['pageRowCount'] = $(":input:radio[name=salesmst_pageRowCount]:checked").val();

	
	$.ajax({
		url: '<c:url value="/app/res/common/findSalesMaster" />',
	    data: JSON.stringify(searchInfo),
	    type: "POST",

	    beforeSend: function(xhr) {
	          xhr.setRequestHeader("Accept", "application/json");
	          xhr.setRequestHeader("Content-Type", "application/json");
	    },
	    
	    success: function(data) {

				setTbodyInit("salesMstList");	// DataTable (tbody)초기화
				if(!data || !data.resultList || data.resultList.length <= 0)  {
					setTbodyNoResult("salesMstList",6,noDataMsg);
					return;
				}

				<%-- TBODY DATA SET --%>
				$("#salesMstListTemplate").tmpl(data.resultList).appendTo("#salesMstList");

				<%-- Set Paging info --%>
				var page = data.paging;
				if(page) {
					$('#pagingInfo').text(page.totalCount);
					$('#endRowNum').text(page.endRowNum);

					$('#salesMstList tr').css('cursor','pointer'); 	// TR ROT CURSOR POINT
					$('#tableData_paging-tmpl').tmpl(page.list).insertBefore($('#paging').empty()).appendTo('#paging');   // PAGING BUTTON UI
				}

		}
	});
}





</script>



<%-- SALES MASTER DATA  --%>
<script id="salesMstListTemplate"  type="text/x-jquery-tmpl">
<tr class='rows'>
	<td class='text-center'>\${rnum}</td>

    <td >\${salesCd}</td>
    <td >\${salesNm}</td>
   
	<input type='hidden' id='salesCd' 		value='\${salesCd}'>	<%-- 매출처코드	--%>
	<input type='hidden' id='salesNm' 		value='\${salesNm}'>	<%-- 매출처명칭 	--%>
	<input type='hidden' id='hqClass' 		value='\${hqClass}'>	<%-- 본사구분코드	--%>
	<input type='hidden' id='hqCd' 			value='\${hqCd}'>		<%-- 본사코드		--%>
	<input type='hidden' id='creLim' 		value='\${creLim}'>		<%-- 여신한도		--%>
	<input type='hidden' id='balRec' 		value='\${balRec}'>		<%-- 미수잔액		--%>
	<input type='hidden' id='ordDdlnTm' 	value='\${ordDdlnTm}'>	<%-- 발주마감시간	--%>
	<input type='hidden' id='ordDdlnYn' 	value='\${ordDdlnYn}'>	<%-- 발주마감시간사용여부 	--%>

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


<!-- Modal fade Detail Contents  -->
<div class="modal fade" id="find_salesmst_find_modal" role="dialog">
	 <div class="modal-dialog modal-lg">
		<!-- Modal content-->
	    <div class="modal-content">
	    	<!-- modal header  -->
			<div class="modal-header ">
		    	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title text-blue"><i class="fa fa-search-plus"></i>매출처 찾기</h4>
			</div>
		    <!-- /.modal header  -->


	    	<!-- modal head -->
	    	<!-- 
		    <div class="modal-body" id="modal_head">
		   		<div class="col-sm-12">
					<div id="find_salesmst_find_form" class="input-group input-group-sm">
						<input type="text" class="form-control" placeholder="매출처명"  >
						<span class="input-group-btn">
                      		<button class="btn btn-info btn-flat" type="button"><spring:message code="button.search" /></button>
                   		</span>
					</div>
				</div>
		    </div>	 
	

		    <!-- modal body -->
		    <div class="modal-body" id="modal_body">
		   		
			    
			 	<!-- box-header  -->
			    <div class="box-header">
					<h3 class="box-title">매출처 조회 </h3>
					<div class="box-tools">
						<div class="input-group" id="find_salesmst_find_form">
							<input type="text" id="find_salesNm" name="find_salesNm" class="form-control input-sm pull-left" style="width: 150px;" placeholder="Search"/>
							<input type="hidden" id="searchHqClass" name="searchHqClass" class="form-control input-sm pull-right" style="width: 150px;" placeholder="Search"/>
							
							<button class="btn btn-primary btn-flat" id="btnSearch_find_sales"><i class="fa fa-search"></i> <spring:message code="button.search" /></button>
							
						</div>
					</div>
				</div>
				<!-- /.box-header -->
				
				<!-- box-tools page info -->
				<div class="box-tools">
	                <!-- page rowCount radio -->
			        <span class="small" style="float: right;">
			        	<label><html:codeTag comType="RADIO" space="3" classNm="flat-red" objId="salesmst_pageRowCount" objName="salesmst_pageRowCount" subCode="2" parentID="9069" selectParam="0"  /></label>
			        </span>
			    	<!-- /.page rowCount radio -->
			    </div>
			    <!-- /.box-tools page info -->
				
				  <!-- box box-solid  Master Data -->
                <div class="box-body table-responsive no-padding">
                  <table  id="salesMstTable"  class="table table-hover">
                    <tr class="thead_dark">
                     	<th>#</th>
						<th class="text-center">매출처코드</th>	
						<th class="no-sort">매출처명</th>		
                    </tr>
                     <tbody id="salesMstList" data-link="row" class="rowlink hand-cursor"> </tbody>
                    <!-- 
                      <tr>
	                      <td>1</td>
	                      <td>183</td>
	                      <td>John Doe</td>
	                      <td>11-7-2014</td>
	                      <td><span class="label label-success">Approved</span></td>
	                      <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                      </tr>
                     -->
                    </table>
                </div>
				<!-- /.box box-solid  Master Data -->

				<!-- /.box-tools page nav -->
				<div class="box-tools pull-right" style="margin-top: -10px !important;" >
                   	<ul id="paging" class="pagination pagination-sm no-margin pull-right"></ul>
                </div>
               <!-- /.box-tools page nav -->

		    </div>	 
			<!-- /.body  info -->    

		    <!-- modal footer -->
		    <div class="modal-footer">
		    	<button id="btnCloseModal" class="btn btn-default" type="button" >
					<i class="fa fa-close"></i> <spring:message code="common.close" />
				</button>
			</div>
			<!-- /.modal footer -->

		</div>
		<!-- /. Modal content-->
	</div>
 </div>
<!-- /.modal fade Detail Contract Items -->



