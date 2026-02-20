<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<sec:authorize access="isAuthenticated()">
	<spring:eval expression="userSession.userType"		var="userType" />
</sec:authorize>
<c:set var="today" value="<%=new java.util.Date()%>" />
<c:set var="ordDt"><fmt:formatDate value="${today}" 	pattern="yyyyMMdd" /></c:set>
<c:set var="todayFmt"><fmt:formatDate value="${today}"	pattern="yyyy-MM-dd" /></c:set>
<spring:message code="common.all" var="commonall" />  <%--전체  --%>
<script type="text/javascript">

var noDataMsg 		= '<spring:message code="message.search.no.data" />';	<%--조회결과가 없습니다. --%>
var sr = /[^0-9]/g;	// 숫자만 표현 
var searchObjName = 'mainFormSearch';  

function setUlNoResult(listUl,msg){
	if(!msg)  msg  = "No Data Found!";
	$("#"+listUl+" li").remove();
	$("#"+listUl).append("<li style='height:50px; padding-top:10px; text-align:center;'><i class='fa fa-exclamation-triangle text-red'></i> "+msg+"</li>");
}


$(document).ready(function($){


	setTbodyNoResult("salesStkList",5,noDataMsg);
	
	
	<%--  Button Event --%>
	$('#mainFormSearch #btnPrdtMstFind').unbind().click(function(){findSalesMst();});		// 상품 팝업 찾아가기

	<%--  Search Button Event --%>
	$('#btnStkSearch').click(function(e){
		
		$('#salesStkPage #page').text('1');
		salesStkSearch();		// 매출처 발주 조회 
		e.preventDefault();
	});

	<%-- Detail Search Modal Show Button Event --%>
	$('#btnModalDetail').click(function(e){
		$('#modalDetailSearch').modal("show");
		e.preventDefault();
	});

	<%--  Detail Modal Search Button Event --%>
	$('#btnSearchDetail').click(function(e){	
		
		$('#salesStkPage #page').text('1'); // 페이지번호 초기화
		$('#modalDetailSearch').modal("hide");
		salesStkSearch();		// 매출처 발주 조회 
		e.preventDefault();
	});

	<%-- Detail Search Modal Close Button Event --%>
	$('#btnCloseModal').click(function(e){
		$('#modalDetailSearch').modal("hide");
		e.preventDefault();
	});
	

	<%-- PageNo Click Event   --%>
	$('#salesStkList_paging #paging').delegate( "a", "click", function() {
		$('#salesStkPage #page').text($(this).attr('link')); 	// 페이지번호설정
		salesStkSearch();										// 페이지번호 변경 후 조회 실행
	});

	<%-- 재고수량 (CARRY OV QTY)--%>
	
	$('.iCheck-helper').click(function(e){
		var checked = $('#checkAll').is(':checked');

		if(checked) $('#sliderOption').css("opacity", "");
		else $('#sliderOption').css("opacity", "0.4");
	});


	
	<%-- STK_DT  DATEPICKER 설정 (D-1) --%>
	$('#mainFormSearch #stkDt').datepicker('setDate', 'c');			// MAIN 검색  order dt to


	 /* ION SLIDER */
    $('#mainFormSearch #carryOvQty_slider').ionRangeSlider({
      min: 0,
      max: 200,
      from: 0,
      to: 100,
      type: 'double',
      step: 1,
      prefix: "",
      prettify: false,
      hasGrid: true,
      onChange: function (data) {
    	  $("#carryOvQty_slider").val(data.fromNumber+";"+data.toNumber);
     }
      
    });

    $('#sliderOption').css("opacity", "0.4");
});




/* 재고 현황 조회 */
function salesStkSearch(){

	var searchInfo = {};
	
	if($("#mainFormSearch #prdtNm").val() == ''){
		alert('품목명을 입력하세요.');
		return;
	}

	<%-- Main  검색조건 parameter  --%>
	$('#mainFormSearch').find('input, select').map(function() {
		searchInfo[this.id] = $(this).val();
	});

	$("#salesStkList li").remove();
	 
	$.ajax({
		url: '<c:url value="/app/res/sm/custsales/cusStkList_selStkList.json" />',
	    data: JSON.stringify(searchInfo),
	    type: "POST",

	    beforeSend: function(xhr) {
	          xhr.setRequestHeader("Accept", "application/json");
	          xhr.setRequestHeader("Content-Type", "application/json");
	    },
	    
	    success: function(data) {

    		setTbodyInit("salesStkList");
    		
			if(!data || !data.resultList || data.resultList.length <= 0)  {
				setTbodyNoResult("salesStkList",noDataMsg);
				return;
			}
			<%-- TBODY DATA SET --%>
			$("#salesStkTemplate").tmpl(data.resultList).appendTo("#salesStkList");

		}
	});
	
}


/* 상품 상세조회 */
function salesProdDetailInfo(prdtCd){
	if(!prdtCd) return;
	var searchVal = {};
	searchVal['prdtCd'] = prdtCd;
	
	commonProdMstFindLayer('',searchVal,null);
}


function prdt_clear(){
	$("#mainFormSearch #prdtNm").val('');
}

</script>



<%-- STK MASTER DATATABLE  --%>
<script id="salesStkTemplate"  type="text/x-jquery-tmpl">
<tr class='rows'>
	<td id='prdtCd'		class='text-center'>\${prdtCd}</td>
	<td id='prdtNm'		class='text-left'>\${prdtNm}</td>
    <td id='prdtStd'	class='text-center'>\${prdtStd}</td>
    <td id='haStkQty'	class='text-right'>\${numberComma(haStkQty)}</td>
 	<td id='yeoStkQty'	class='text-right'>\${numberComma(yeoStkQty)}</td>
</tr>
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
					<div style="padding-bottom: 4px; font-size: 0.9em; font-weight: bold;"><a>재고 정보 조회</a></div>
				</div>
				<div class="box-tools pull-right">
	            	<button id="btnStkSearch" class="btn btn-danger btn-sm" type="button" >
						<i class="fa fa-search"></i> <spring:message code="button.search" />
					</button>
                </div>
			</div>
			<div class="box-body"  id="mainFormSearch">
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="form-group">
						<label>재고일자</label>
						<div class="input-group">
	                     	<input type="text" class="form-control pull-left" id="stkDt" style="width: 50%;" readonly="readonly" />
						</div><!-- /.input group -->
	                </div>
				</div>
				<div class="col-sm-6">
					<div class="form-group" style="margin-bottom: 5px;">
						<input type="text" class="form-control" placeholder='품목명' id="prdtNm" name="prdtNm"  onclick="prdt_clear()">
					</div>
				</div>
				
			</div>
		</div>
		<!-- Default box -->
		<div class="box box-body">
			<!-- box box-solid  Master Data -->
			<div class="box box-solid table-responsive no-padding">
				<table id="stkTable" class="table  table-bordered">
					<thead>
	                	<tr class="thead_dark">
							<th class="text-center">품목</th>
							<th class="text-center">품목명</th>
							<th class="text-center">규격</th>
							<th class="text-center">하남재고</th>
							<th class="text-center">여주재고</th>
						</tr>
					</thead>	
					<tbody id="salesStkList">
				</table>
        	</div>
           <!-- /.box-tools page nav -->	
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
                	<label>상품명</label>
                    <input type="text" class="form-control" id="prdtNm" name="prdtNm" >
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="form-group">
					<label>상품 대분류 </label>
					<html:codeTag comType="SELECT" 	dataType="PRC1"	objId="lCd" objName="lCd"  parentID="Y" defName="${commonall}" />
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="form-group">
					<label>상품 중분류 </label>
					<html:codeTag comType="SELECT" 	dataType="PRC2"	objId="mCd" objName="mCd"   parentID="Y" defName="${commonall}" />
				</div>
			</div>
			
			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="form-group">
					<html:codeTag comType="CHECKBOX" space="3" classNm="flat-red" objId="strgType" objName="strgType"  parentID="M001" selectParam="0" defName="${commonall}" />
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


<!-- 상품상세 모달 레이어 영역 start  -->
 <jsp:include page="/WEB-INF/views/app/sm/resProdDetails.jsp" /> 
<!-- /상품상세 모달  레이어 영역 end  -->
