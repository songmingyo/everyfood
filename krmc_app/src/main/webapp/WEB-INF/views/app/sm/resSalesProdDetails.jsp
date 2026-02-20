<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">

var noDataMsg 		= '<spring:message code="message.search.no.data" />';	<%--조회결과가 없습니다. --%>
$(document).ready(function($){
	
});

function eventCloseSalesProdModal(){
	$('#find_salesprodmst_find_modal').modal("hide");
}


var comSalesProdMstCallbackFnc = null;

/* Layer Event Creat */
function setFindSalesProdMstFindLayerEvent(){

	
	$('#find_salesprodmst_find_modal #btnCloseModal').unbind().click(null, eventCloseSalesProdModal); 			// 닫기버튼

	/* 모달 레이어 활성화 */
	$('#find_salesprodmst_find_modal').modal("show");
	
}
 

/* 레이어호출 */
function commonSalesProdMstFindLayer(affiliate,searchVal,callbackFnc){

	setFindSalesProdMstFindLayerEvent();

	setBindElement($('#find_salesprodmst_find_form'), searchVal);

	eventSearchSalesProd();

	comSalesProdMstCallbackFnc = callbackFnc;
}

/* Data 검색 */
function eventSearchSalesProd(){

	var searchInfo = {};
	$('#find_salesprodmst_find_form').find('input').map(function() {
		searchInfo[this.name] = $(this).val();
	});

	$.ajax({
		url: '<c:url value="/app/res/sm/custsales/cusOrdReg_selSalesProd.json" />',
	    data: JSON.stringify(searchInfo),
	    type: "POST",

	    beforeSend: function(xhr) {
	          xhr.setRequestHeader("Accept", "application/json");
	          xhr.setRequestHeader("Content-Type", "application/json");
	    },

	    success: function(data) {

				setTbodyInit("SalesProdMstList");	// DataTable (tbody)초기화
				if(!data )  {
					return;
				} 

				$('#find_salesprodmst_find_modal #salesNm').text(data.salesNm);
				$('#find_salesprodmst_find_modal #prdtNm').text(data.prdtNm);

				$('#find_salesprodmst_find_modal #lNm').text(data.lNm);
				$('#find_salesprodmst_find_modal #mNm').text(data.mNm);

				$('#find_salesprodmst_find_modal #price').text(numberComma(data.price));
				$('#find_salesprodmst_find_modal #prdtStd').text(data.prdtStd);				

				$('#find_salesprodmst_find_modal #ordUnitNm').text(data.ordUnitNm);	
				$('#find_salesprodmst_find_modal #qtyBox').text(numberComma(data.qtyBox));	
				$('#find_salesprodmst_find_modal #originNm').text(data.originNm);		
				  
				 
		}
	});
}



/*commonUtil이동예정 */
function setBindElement(form, data){
if(!data || !form) return;

$.each(data, function(i, el) {

  var 
      fel = form.find('*[name="' + i + '"]'),
      type = "", tag = "";

   if (fel.length > 0) {

       tag = fel[0].tagName.toLowerCase();

       if (tag == "select" || tag == "textarea") { //...
          $(fel).val(el);
       }
       else if (tag == "input") {
          type = $(fel[0]).attr("type");
           if (type == "text" || type == "password" || type == "hidden") {
              fel.val(el);
           } 
           else if (type == "checkbox") {
              if (el)  
                 fel.attr("checked", "checked"); 
           }
           else if (type == "radio") {
               fel.filter('[value="'+el+'"]').attr("checked", "checked"); 
           }
       }
   }
})
}
</script>


<!-- Modal fade Detail Contents  -->
<div class="modal fade" id="find_salesprodmst_find_modal" role="dialog">
	 <div class="modal-dialog modal-lg">
		<!-- Modal content-->
	    <div class="modal-content">
	    	<!-- modal header  -->
			<div class="modal-header ">
		    	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title text-blue"><i class="fa fa-search-plus"></i>매출처 품목상세</h4>
			</div>
		    <!-- /.modal header  -->
			
	    	<!-- modal head -->
		    <div class="mailbox-read-info" id="modal_head">
		   		<div class="col-sm-12">
					<div id="find_salesprodmst_find_form">
						<h5>
							<span id="salesNm"> - </span>
						</h5>
						<h4>
							<i class="fa fa-bookmark-o"></i>
							<span id="prdtNm"> - </span>
						</h4>
						<input type="hidden" class="form-control" id="salesCd" name="salesCd" placeholder="매출처코드"  >
						<input type="hidden" class="form-control" id="prdtCd"  name="prdtCd"  placeholder="상품코드"   >
					</div>
				</div>
		    </div>	

		    <!-- modal body -->
		    <div class="mailbox-read-message">
		   		
			    <!-- box box-solid  Master Data -->
                <div class="box-body" style="padding-top: 0px;">
                
                	<div class="form-group" style="margin-bottom:20px; ">
                		<div class="input-group text-muted" >
                			<small>
                				<i class="fa fa-server"></i>  <span id="lNm"> </span> <span style="padding: 4px;"> <i class="fa fa-angle-right"></i> </span> <span id="mNm"></span>
                			</small>
                		</div>	
                	</div>
               		 <div class="form-group" style="margin-bottom:5px;">
               		 	<label>판매단가 :</label>
               		 	<span id="price">0</span>
               		 </div>
               		 <div class="form-group" style="margin-bottom:5px;">
               		 	<label>규격 :</label>
               		 	<span id="prdtStd"> - </span>
                	 </div>
                	  <div class="form-group" style="margin-bottom:5px;">
               		 	<label>주문단위 :</label>
               		 	<span id="ordUnitNm">-</span>
                	 </div>
                	 <div class="form-group" style="margin-bottom:5px;">
               		 	<label>박스당수량 :</label>
               		 	<span id="qtyBox">0</span>
                	 </div>
                	  <div class="form-group" style="margin-bottom:5px;">
               		 	<label>원산지 :</label>
               		 	<span id="originNm"> - </span>
                	 </div>
                </div>
				<!-- /.box box-solid  Master Data -->


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



