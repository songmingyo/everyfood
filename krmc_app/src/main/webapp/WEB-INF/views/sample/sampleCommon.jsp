<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">


$(document).ready(function(){

	$('#btnBuyMstFind').unbind().click(function(){ findBuyMst()}); 	 	// 매입처찾기 팝업 버튼이벤트
	$('#btnBuyMstClear').unbind().click(function(){ 						// 매입처찾기 초기화 버튼이벤트
		setBuyMstFindLayer({buyCd:'',buyNm:'',startDt:''})
	}); 	 	
	
	$("#btnSalesMstFind").unbind().click(function(){findSalesMst();});	// 매출처 팝업 찾아가기
	$("#btnSalesMstClear").unbind().click(function(){					// 매출처 초기화 버튼 
		setSalesMstFindLayer({salesCd:'',salesNm:''});
	});

	
	$('#btnDlvrMstFind').unbind().click(function(){ findDlvrMst()}); 	// 차량처찾기 팝업 버튼이벤트
	$('#btnDlvrMstClear').unbind().click(function(){					// 차량처찾기 초기화 버튼이벤트
		setDlvrMstFindLayer({carCd:'',carNm:'',carType:'',drvSnm:''});
	}); 	


	$('#btnSalesPrMstFind').unbind().click(function(){ findSalesPrMst()}); 	// 영업사원찾기 팝업 버튼이벤트
	$('#btnSalesPrMstClear').unbind().click(function(){						// 영업사원찾기 초기화 버튼이벤트
		setSalesPrMstFindLayer({salesPrCd:'',salesPrNm:'',position:''});
	}); 	


	$('#btnPrdtMstFind').unbind().click(function(){ findPrdtMst()}); 	// 상품찾기 팝업 버튼이벤트
	$('#btnPrdtMstClear').unbind().click(function(){					// 상품찾기 초기화 버튼이벤트
		setPrdtMstFindLayer({prdtCd:'',prdtNm:''});
	}); 
	
	

	$('#btnReport').unbind().click(null,   		eventbtnReport);  		// PDF Report download
	$('#btnReportView').unbind().click(null,   	eventbtnReportView);  	// PDF Report view


	$('#btnReportMulti').unbind().click(null,   	eventbtnReportMilti);  		// PDF Report Mult download


	$('#btnCloseJobSpBatchMenual').unbind().click(null,   	manualBatchStart);  		// 메뉴얼 베치 수동 실행 
	

	
	
	

});


/*매입처 찾기팝업 호출 */
function findBuyMst() {
	commonBuyMstFindLayer('','','', setBuyMstFindLayer);
}

/*매입처 콜백(후처리 함수 )*/
function setBuyMstFindLayer(data) {
	if (data != null){
		$("#buyCd").val(data.buyCd);
		$("#buyNm").val(data.buyNm);
		$("#startDt").val(data.startDt);
	}
}


/*매출처 찾기팝업 호출 */
	function findSalesMst() {
		commonSalesMstFindLayer('','','', setSalesMstFindLayer);
	}
	
	/*매츨처 콜백(후처리 함수 )*/
	function setSalesMstFindLayer(data) {
		if (data != null){
			$("#salesCd").val(data.salesCd);
			$("#salesNm").val(data.salesNm);
		}
	}
	

/*배송차량 찾기팝업 호출*/
function findDlvrMst(){
	var searchVals = {find_carNm: $("#carNm").val(), find_salesCd:$("#salesCd").val(),  find_salesNm:$("#salesNm").val()}
	commonDlvrMstFindLayer('','',searchVals, setDlvrMstFindLayer);
}
/*배송차량 콜백(후처리 함수 )*/
 function setDlvrMstFindLayer(data) {
	 if (data != null){
		$("#carCd").val(data.carCd);		/*챠량코드*/
		$("#carNm").val(data.carNm);		/*차량명	*/
		$("#carType").val(data.carType);	/*차량타입 */
		$("#drvSnm").val(data.drvSnm);		/*기사명 */
	 }
}

 /*영업사원 찾기팝업 호출*/
function findSalesPrMst() {
	commonSalesPrMstFindLayer('','','', setSalesPrMstFindLayer);
}
/*영업사원 콜백(후처리 함수 )*/
function setSalesPrMstFindLayer(data){
	 if (data != null){
		 $("#salesPrCd").val(data.salesPrCd);		/*영업사원코드*/
		 $("#salesPrNm").val(data.salesPrNm);		/*영업사원명*/
		 $("#position").val(data.position);			/*직급*/
	}
}


/*상품 찾기팝업 호출 */
function findPrdtMst() {
	commonPrdtMstFindLayer('','','', setPrdtMstFindLayer);
}

/*상품 콜백(후처리 함수 )*/
function setPrdtMstFindLayer(data) {
	if (data != null){
		$("#prdtCd").val(data.prdtCd);
		$("#prdtNm").val(data.prdtNm);
	}
}

  

/*PDF Report Download */
function eventbtnReport(){
	searchFrm.action="<c:url value="/app/smp/pdf/sampleJasper" />";
 	searchFrm.submit();
}

/*PDF Report 생성 Event */
 function eventbtnReportMilti(){

/*
	 searchFrm.action="<c:url value="/app/smp/pdf/sampleJasperMulti" />";
	 searchFrm.submit();
*/
	var url 	= "<c:url value="/app/smp/pdf/sampleJasperMulti" />";
	var target 	= "PDFPrint";
	var agt = navigator.userAgent.toLowerCase();
	
	
	if (agt.indexOf("msie") != -1) {
		
		searchFrm.action = url;
		searchFrm.submit();	
	}
	else {
		window.open('','PDFPrint','toolbar=no,resizable=no,location=0,scrollbars=0,width=800,height=700,top=50,left=200');
		searchFrm.action=url;
		searchFrm.target="PDFPrint";
		searchFrm.submit();
	}
		
}


/*PDF Report View*/
function eventbtnReportView(){


		var url 	= "<c:url value="/app/smp/pdf/sampleJasperView" />";
		var target 	= "PDFPrint";
		var agt = navigator.userAgent.toLowerCase();
		
		
		if (agt.indexOf("msie") != -1) {
			
			searchFrm.action = url;
			searchFrm.submit();	
		}
		else {
			window.open('','PDFPrint','toolbar=no,resizable=no,location=0,scrollbars=0,width=800,height=700,top=50,left=200');
			searchFrm.action=url;
			searchFrm.target="PDFPrint";
			searchFrm.submit();
		}

}

//수동 배치 실행
function manualBatchStart() {
	
	if (!confirm(" 배치를 실행하시겠습니까?")) return;
	var batchInfo = {};
	
	$.ajax({
		contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		url : '<c:url value = "/app/mgr/manager/closeJobSpBatchMenual"/>',
		data : JSON.stringify(batchInfo),
		success : function(data) {

			if(data) {
				alert("MsgCd : "+data.msgCd +"    Message : "+data.message);

			}
			
		}
	});
}


</script>


</head>
<body>


<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<spring:message code="common.all" 	 var="nameall" />  <%--전체  --%>
<spring:message code="common.choice" var="choice" />  <%--선택  --%>

<form id="searchFrm" name="searchFrm" method="post">
	<sec:csrfInput/>
	<input type="text" name="memberCds" value="001">
	<input type="text" name="memberCds" value="002">
</form>	
<div class="tit_area" >
	<h2> 샘플 상품분류  </h2>	
	<div>
				
	</div>
</div>
<div style="padding:10px; border: 1px solid #efefef;">
	 상품분류 	 : <html:codeTag comType="SELECT" 	dataType="PRC1"	objId="prodCls1-1" objName="prodCls1-2" parentID="Y" defName="${nameall}" />
</div>
<br>

<div class="tit_area" >
	<h2>  찾기 팝업  </h2>	
	<div>
				
	</div>
</div>	
<div style="padding:10px; border: 1px solid #efefef;">
	
	<strong>매입처찾기 </strong>
	매입처코드: <input type="text" id="buyCd" 		name="buyCd" 	style="width: 100px;">
	매입처명칭: <input type="text" id="buyNm" 		name="buyNm" 	style="width: 200px;">
	거래시작일: <input type="text" id="startDt" 	name="startDt" 	style="width: 100px;">
	<button id="btnBuyMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
	<button id="btnBuyMstClear" class="button btn_find" type="button"><i class="fa fa-eraser"></i></button>
		
</div>

<div style="padding:10px; border: 1px solid #efefef;">
	<strong>매출처찾기 </strong>
	매출처코드: <input type="text" id="salesCd" 		name="salesCd" 	style="width: 100px;">
	매출처명칭: <input type="text" id="salesNm" 		name="salesNm" 	style="width: 200px;">
	<button id="btnSalesMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
	<button id="btnSalesMstClear" class="button btn_find" type="button"><i class="fa fa-eraser"></i></button>
</div>
			
<div style="padding:10px; border: 1px solid #efefef;">
	<strong>차량찾기</strong> 
	차량코드: <input type="text"	id="carCd" 		name="carCd" 	style="width: 100px;">
	차량명: <input type="text"  	id="carNm" 		name="carNm" 	style="width: 200px;">
	차량타입: <input type="text"  	id="carType" 	name="carType" 	style="width: 100px;">
	기사명: <input type="text"  	id="drvSnm" 	name="drvSnm" 	style="width: 100px;">
	<button id="btnDlvrMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
	<button id="btnDlvrMstClear" class="button btn_find" type="button"><i class="fa fa-eraser"></i></button>
</div>


<div style="padding:10px; border: 1px solid #efefef;">
	<strong>영업사원찾기 </strong>
	영업사원코드: <input type="text" id="salesPrCd" 		name="salesPrCd" 	style="width: 100px;">
	영업사원명 :  <input type="text" id="salesPrNm" 		name="salesPrNm" 	style="width: 200px;">
	직급 : 	   <input type="text" id="position" 		name="position" 	style="width: 200px;">
	<button id="btnSalesPrMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
	<button id="btnSalesPrMstClear" class="button btn_find" type="button"><i class="fa fa-eraser"></i></button>
</div>

<div style="padding:10px; border: 1px solid #efefef;">
	<strong>상품찾기 </strong>
	상품명: <input type="text" 	 id="prdtNm" 	name="prdtNm" 	style="width: 100px;">
	상품코드 :  <input type="text" id="prdtCd" 	name="prdtCd" 	style="width: 200px;">
	
	<button id="btnPrdtMstFind"  class="button btn_find" type="button"><i class="fa fa-search"></i></button>
	<button id="btnPrdtMstClear" class="button btn_find" type="button"><i class="fa fa-eraser"></i></button>
</div>


<div class="tit_area" >
	<h2> PDF Report(jasperreports)</h2>	
	<div>
				
	</div>
</div>	
<div style="padding:10px; border: 1px solid #efefef;">
	단일 레포트 <html:button id="btnReport" auth="print" value="PDF Report Download"/>  <html:button id="btnReportView" auth="print" value="PDF Report View"/> 
</div>

<div style="padding:10px; border: 1px solid #efefef;">
	다건 레포트 병합  <html:button id="btnReportMulti" auth="print" value="PDF Report Multi Download"/>   
</div>


<div style="padding:10px; border: 1px solid #efefef;">
	베치 수동 실행   <html:button id="btnCloseJobSpBatchMenual" auth="print" value="CloseJobSpBatchMenual"/>   
</div>

</div>


<!-- 매입처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_BuyMaster.jsp" />

<!-- 매출처 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_SalesMaster.jsp" />

<!-- 배송차량 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_DlvrMaster.jsp" />

<!-- 영업사원 찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_salesPrMaster.jsp" />

<!-- 상품  찾기  레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find_PrdtMaster.jsp" />


