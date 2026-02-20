<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<sec:authorize access="isAuthenticated()">
	<spring:eval expression="userSession.userType"		var="userType" />
</sec:authorize>
<c:set var="today" value="<%=new java.util.Date()%>" />
<c:set var="ordDt"><fmt:formatDate value="${today}" pattern="yyyyMMdd" /></c:set>
<c:set var="todayFmt"><fmt:formatDate value="${today}" pattern="yyyy-MM-dd" /></c:set>

<script type="text/javascript">

var noDataMsg 		= '<spring:message code="message.search.no.data" />';	<%--조회결과가 없습니다. --%>
//var sr = /[^0-9]/g;	// 숫자만 표현 
//var sr = /^[\d]*\.?[\d]{0,2}$/;
var sr =/(^\d*[.]\d{3}$) | ([^0-9.]) |(^\d*[.]{2})/;
		
$(document).ready(function($){

	$("#btnSalesMstFind").unbind().click(function(){findSalesMst();});		// 매출처 팝업 찾아가기
	$("#btnOrdProdSearch").unbind().click(function(){ordProdSearch();});	// 매출처 상품정보 조회 
	$("#btnOrdProdSave").unbind().click(function(){ordProdSave();});		// 매출처 발주등록 
	$("#btnReload").unbind().click(function(){reloadEvent();});				// 페이지 초기화 
	
	<c:choose>
		<c:when test="${userSession.userType == 'S1'}">
			salesMstDataFind(); 
		</c:when>
		<c:otherwise>
			$("#salesNm").unbind().click(function(){setSalesMstFindLayer({salesCd:'',salesNm:'',creLim:'0',balRec:0,ordDdlnTm:'00:00'})});				// 페이지 초기화			
		</c:otherwise>
	</c:choose>
	
	setTbodyNoResult("salesOrdProdList",1,noDataMsg);

	//조건 입력필드 enter key이벤트 --------------
	$('#prdtNm').unbind().keydown(function(e) {

		switch(e.which) {
    	case 13 : ordProdSearch(this); break; // enter
    	default : return true;
    	}
    	e.preventDefault();
   	});

	$("#dlvDt").datepicker('setDate', '+1d');		// MAIN 검색  Contract from
	

});

/* Page Reload event*/
function reloadEvent(){

	$("#ord_prod_form #prdtNm").val('');		//품목명 초기화 
	$('#dlvDt').datepicker('setDate', '+1d');	//납품일자 초기화


	ordProdSearch();							//매출처 품목 정보 reload 
}

/* btnMinus or btnPlus */
function countData(obj) {
	
	var btnId 		 = $(obj).attr('name');	// btnMinus or btnPlus
	var ordQtyObj 	 = $(obj).parent().parent().find("input[name='ordQty']");		// 수량	
	var priceObj 	 = $(obj).parent().parent().find("input[name='price']");		// 판매단가 
	var vatObj 		 = $(obj).parent().parent().find("input[name='vatYn']");		// VAT 여부(1:true/2:false) 
	var pureAmtObj 	 = $(obj).parent().parent().find("input[name='pureAmt']");		// 판매가 
	var vatAmtObj 	 = $(obj).parent().parent().find("input[name='vatAmt']");
	var totAmtHidObj = $(obj).parent().parent().find("input[name='totAmtHid']");	// HIDDEN 
	var totAmtObj	 = $(obj).parent().parent().find("input[name='totAmt']");		// 총금액(판매가+부가세)

	var ordUnitObj 	 = $(obj).parent().parent().find("input[name='ordUnit']"); 
	var ordUnitNmObj = $(obj).parent().parent().find("input[name='ordUnitNm']"); 
	var stdYnObj 	 = $(obj).parent().parent().find("input[name='stdYn']");  
	var qtyBoxObj 	 = $(obj).parent().parent().find("input[name='qtyBox']");  
	var boxQtyObj 	 = $(obj).parent().parent().find("input[name='boxQty']");
	
	if(!isNumberKey(ordQtyObj.val())){
		ordQtyObj.val(0)
		return;
	}
	
	var defValue 	= 0;
	var defAmtValue = 0;
	var vatAmt      = 0;
	var totAmt      = 0;
	
	if(ordQtyObj.val())  {
		defValue 	= ordQtyObj.val().replace(sr,'');
	}

	if(btnId =='btnPlus')  defValue++;
	else if(btnId =='btnMinus'){
		if(defValue > 0) defValue--;	
	}else{}

	
	defAmtValue = Math.round(Number(priceObj.val())*defValue);
	pureAmtObj.text(numberComma(defAmtValue)); 
		
	if(vatObj.val() =='1'){
		vatAmt =  Math.ceil(defValue * Number(priceObj.val()) * 0.1);
	}else{
		vatAmt = 0;
	}
	
	totAmt =  defAmtValue+vatAmt;
	
	pureAmtObj.val(defAmtValue);
	vatAmtObj.val(vatAmt);
	totAmtObj.val(totAmt);
	
	totAmtHidObj.val(totAmt);

	/* BOX_QTY 값 설정 ------------------------------------------------------------*/
	var ordQty 	= parseFloat(defValue); 
	var qtyBox 		= qtyBoxObj.val();
	var strQtyBox 	= parseInt(ordQty / qtyBox );     
	
  	var strEa 		= ordQty % qtyBox;
  	if(strEa % 1 != 0){
    	strEa = strEa.toFixed(2);
    }
  
	if(stdYnObj.val() == "2"){
		//$("#container2List").jqGrid('setCell',rowid,'boxQty',String(ordQty).concat(ordUnit));
		boxQtyObj.val(String(ordQty).concat(ordUnitNmObj.val()));
	} else {
		if(Number(ordQty) < Number(qtyBox)){
			//$("#container2List").jqGrid('setCell',rowid,'boxQty',String(ordQty).concat(ordUnit));
			boxQtyObj.val(String(ordQty).concat(ordUnitNmObj.val()));
		} else if(strEa == 0){ 
			if(Number(qtyBoxObj.val()) > 1){
			//	$("#container2List").jqGrid('setCell',rowid,'boxQty',String(strQtyBox).concat("BOX"));
				boxQtyObj.val(String(strQtyBox).concat("BOX"));
			} else{
	        	//$("#container2List").jqGrid('setCell',rowid,'boxQty',String(ordQty).concat(ordUnit));
				boxQtyObj.val(String(strQtyBox).concat(ordUnitNmObj.val()));
	       	}
	    }else{
	    	//$("#container2List").jqGrid('setCell',rowid,'boxQty',String(strQtyBox).concat("BOX/", strEa, ordUnit));
			boxQtyObj.val(String(strQtyBox).concat("BOX/",strEa,ordUnitNmObj.val()));
		}
	}
	/*-----------------------------------------------------------------------------------*/
    
	//ordQtyObj.val(numberComma(defValue));				// 수량(ORD_QTY)
	
	sumOrdProdList();	// Sum head,footer (amt/qty) 처리 
}


function isNumberKey(number) {
	
	const numStr = number.toString();
	const regEx = /^\d{1,5}(\.\d{1,2})?$/;
	
//    if (!regEx.test(numStr)) {
//    	alert("정수 5자리와 소수점 둘째 자리까지만 입력가능합니다.");    	
//    	return false;
//    }

//     // 100000(99999)이하의 숫자만 입력가능 
//     var patternB = /^\d{5}$/; // 현재 value값이 5자리 숫자이면 . 만 입력가능
//     if (patternB.test(defValue)) {
//         if (charCode != 46) {
//             alert("100,000 이하의 숫자만 입력가능합니다");
//             return false;
//         }
//     }

//     // 소수점 둘째자리까지만 입력가능
//     var patternC = /^\d*[.]\d{2}$/; // 현재 value값이 소수점 둘째짜리 숫자이면 더이상 입력 불가
//     if (patternC.test(defValue)) {
//        alert("소수점 둘째자리까지만 입력가능합니다.");
// 	   return false;
// 	} 

	if (!regEx.test(numStr)) {    	
    	return false;
    }

    return true;

}

/* 숫자 소숫점 2자리 만단위 입력 가능 처리 
 * onkeypress="return isNumberKey(event)"  
 */
// function isNumberKey(obj) {
// 	var charCode = (obj.which) ? obj.which : event.keyCode;
//     if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
//         return false;
//     }

//     // text input  value    
// 	var defValue = event.srcElement.value;   

//     // 소수점(.)이 두번 이상 나오지 못하게
//     var patternA = /^\d*[.]\d*$/; // 현재 value값에 소수점(.) 이 있으면 . 입력불가
//     if (patternA.test(defValue)) {
//         if (charCode == 46) {
//             return false;
//         }
//     }

//     // 100000(99999)이하의 숫자만 입력가능 
//     var patternB = /^\d{5}$/; // 현재 value값이 5자리 숫자이면 . 만 입력가능
//     if (patternB.test(defValue)) {
//         if (charCode != 46) {
//             alert("100,000 이하의 숫자만 입력가능합니다");
//             return false;
//         }
//     }

//     // 소수점 둘째자리까지만 입력가능
//     var patternC = /^\d*[.]\d{2}$/; // 현재 value값이 소수점 둘째짜리 숫자이면 더이상 입력 불가
//     if (patternC.test(defValue)) {
//        alert("소수점 둘째자리까지만 입력가능합니다.");
// 	   return false;
// 	}  
//     return true;

// }

function sumOrdProdList(){

	var ordQtyum  = 0;
	var ordAmtSum = 0;
	
	$('#salesOrdProdList').find('input, span').map(function() {
		if(this.name =='ordQty') {
			if($(this).val())  ordQtyum +=Number($(this).val());
		}
		
		if(this.name =='pureAmt') {
			if($(this).val())  ordAmtSum +=Number($(this).val());
		}
	});

	$("#headSum #sumQty").text(ordQtyum);
	$("#footerSum #sumQty").text(ordQtyum);

	$("#headSum #sumAmt").text(numberComma(ordAmtSum));
	$("#footerSum #sumAmt").text(numberComma(ordAmtSum));
}

/*매출처 찾기팝업 호출 */
function findSalesMst() {

	commonSalesMstFindLayer('',$("#form-custOrdReg #salesNm").val(), setSalesMstFindLayer);
}


/*매츨처 콜백(후처리 함수 )*/
function setSalesMstFindLayer(data) {

	//  warning  초기확  
	$('#warningState #warningMessage').text(''); 	
	$('#warningState').hide();
	
	if (data != null){
		$("#form-custOrdReg #salesCd").val(data.salesCd);
		$("#form-custOrdReg #salesNm").val(data.salesNm);
		if(!data.creLim) data['creLim'] = "0";
		if(!data.balRec) data['balRec'] = "0";
		
		$("#creLim").text(numberComma(data.creLim));		// 여신한도

		/* 26.01.21 미수잔액  마이너스 음스 부호 설정 */
		let numBalRec	=	parseInt(data.balRec);
		if (numBalRec < 0) {
			$("#balRec").text('-' + numberComma(data.balRec));		// 미수잔액	
		} else {
			$("#balRec").text(numberComma(data.balRec));		// 미수잔액
		}
		
		if(data.ordDdlnTm)	$("#ordDdlnTm").text(data.ordDdlnTm.replace(/(\d{2})(\d{2})/, '$1:$2'));	/* 2400 > 24:00 */		

		<%-- 여신한도 및 미수금 검증  여신 한도 이상 발주 금액 불가 처리, 미수금 있을경우 발주불가 --%>
// 		if(data.salesCd && (!Number(data.creLim) > 0  || Number(data.balRec) > 0)) {
// 			$('#warningState').show('slow');
// 			$('#warningState #warningMessage').text('여신금액이 없거나 미수금이 존재합니다!'); 
// 		}else $('#warningState').hide('slow');
		

		setTbodyInit("salesOrdProdList");	// DataTable (tbody)초기화
		setTbodyNoResult("salesOrdProdList",1,noDataMsg);

		$("#headSum #sumQty").text('0');
		$("#footerSum #sumQty").text('0');

		$("#headSum #sumAmt").text('0');
		$("#footerSum #sumAmt").text('0');
		
		if($("#form-custOrdReg #salesCd").val()) ordProdSearch();	
	}
}

/*매출처 발주대상 품목 조회 */
function ordProdSearch(){


	//  warning  초기확  
	$('#warningSaveState #warningSaveMessage').text(''); 	
	$('#warningSaveState').hide();
	
	if(!$.trim($("#ord_prod_form #salesCd").val())) {
		alert('선택된 매출처가 없습니다.');
		return;
	}
	
	var searchInfo = {};
	$('#ord_prod_form').find('input').map(function() {
		searchInfo[this.name] = $(this).val();
	});


	$.ajax({
		url: '<c:url value="/app/res/sm/custsales/cusOrdReg_selOrdProdList.json" />',
	    data: JSON.stringify(searchInfo),
	    type: "POST",

	    beforeSend: function(xhr) {
	          xhr.setRequestHeader("Accept", "application/json");
	          xhr.setRequestHeader("Content-Type", "application/json");
	    },

	    success: function(data) {

			setTbodyInit("salesOrdProdList");	// DataTable (tbody)초기화
			if(!data || !data || data.length <= 0)  {
				setTbodyNoResult("salesOrdProdList",1,noDataMsg);
				return;
			}
			<%-- TBODY DATA SET --%>
			$("#salesOrdProdTemplate").tmpl(data).appendTo("#salesOrdProdList");

			sumOrdProdList();// Sum head,footer (amt/qty) 처리 
		}
	});
	
}


function ordProdSave(){

	// 저장data 생성---------------------------------
	var orderRegData = [];
	
	
	$('input[name=ordQty]').each(function() {	
	
    	var orderRegList = {};
		$(this).parents('tr:first').find('input').map(function(){ 

			/*상품코드, 발주수량, 이전발주수량(수정모드일때), 전표번호, 전표순번 */
			if(this.name =='prdtCd' || this.name =='ordQty' || this.name =='ordQtyBef'  || this.name =='salesSlipNo' || this.name =='salesSeq' 
		       || this.name =='boxQty' || this.name =='price' || this.name =='pureAmt' || this.name =='vatAmt' || this.name =='totAmt') 
			{
				orderRegList[this.name] = $(this).val();
			}
		});

		<%-- 발주대상정보(orderRegList) [ 처리대상 발주품목 정보 생성(orderRegData) ]
			 수량이 0보다 크거나 기등록된 발주품목일경우 (신규/수정/삭제 처리 데이터로 생성 ) 
			 전표번호가 있고 발주수량이 없거나 0인 경우 삭제 처리 함 
		--%>
		if(Number(orderRegList.ordQty) > 0 || orderRegList.salesSlipNo) {
			orderRegData.push(orderRegList);
		}
		
	});
	//--------------------------------------------------
		
	/*----[ 여신 & 미수금 검증 ] -------------------------*/
	 /*ORD_DT 기준 발주금액 <= 여신금액*/
//	var sumAmt  = $("#headSum #sumAmt").text().replace(sr,'');	// 발주금액 합계
	var sumAmt  = $("#footerSum #sumAmt").text().replace(sr,'');	// 발주금액 합계
	var creLim  = $("#creLim").text().replace(sr,'');			// 여신한도
	var balRec  = $("#balRec").text().replace(sr,'');			// 미수잔액
	var msg     = "";
	
	if(Number(parseInt(sumAmt) + parseInt(balRec)) > Number(creLim) )	{
		 msg = "발주금액이 여신한도를 초과하였습니다."; 
	}
	
	if(msg) {
		$('#warningSaveState #warningSaveMessage').text(msg);
		$('#warningSaveState').show('slow');
		return;
	}
     /*----------------------------------------------*/
     
	//-----입고예정일자는 발주일자 보다 작을수 없다
	var ordDt	=	$("#ordDt").val().replaceAll("-", "");		//발주일자
	var dlvDt	=	$("#dlvDt").val().replaceAll("-", "");		//입고 예정일자
	
	if (dlvDt <= ordDt && ('${userSession.userType}' == "S1" || '${userSession.userType}' == "S2")) {
		alert("발주일자 이후로 입고예정일자를 지정해주세요");
		return;
	}
	
	//-----2024.12.15 add by song min kyo 전표생성 되었을 경우 저장 불가능
	<%--
		2024.12.15 윤현수 부장 요청
		매출처에서 발주 등록 저장 후 수량 변경 및 수정 못하게 고정 처리 부탁드립니다
	 --%>
	/*
	if (slipNo != "") {
		alert("발주등록 후 수량변경 및 수정은 불가능 합니다");
		return;
	}
	*/
	
	var searchInfo = {};
	searchInfo['orderRegList'] = orderRegData; 
	searchInfo['salesCd'] 	   = $("#ord_prod_form #salesCd").val();
	searchInfo['ordDt'] 	   = $("#ord_prod_form #ordDt").val().replaceAll("-", ""); 
	searchInfo['dlvDt'] 	   = $("#headSum #dlvDt").val().replaceAll("-", "");
	searchInfo['whCd'] 	       = '10001';
	
	var modyfiCnt	=	0;
	for ( var i = 0; i < orderRegData.length; i++) {
//		console.log(orderRegData[i].salesSlipNo);
//		console.log("ordQtyBef == " + orderRegData[i].ordQtyBef);
//		console.log("ordQty == " + orderRegData[i].ordQty);
		
		if (orderRegData[i].salesSlipNo != "") {
			/*
			if (orderRegData[i].ordQty != orderRegData[i].ordQtyBef) {
				modyfiCnt++;
			}
			*/
			modyfiCnt++;
		}
	}
	
	/* 2025.06.12 주석처리 모바일 발주 안된다는 요청에 의한 주석처리
	if (modyfiCnt > 0) {
		alert("발주등록 후 수량변경 및 수정은 불가능 합니다");
		return;
	}
	*/
	

	if(!confirm("저장하시겠습니까?")){
		return;
	} 
     
	$.ajax({
		url: '<c:url value="/app/res/sm/custsales/cusOrdReg_insOrdProdList.json" />',
	    data: JSON.stringify(searchInfo),
	    type: "POST",

	    beforeSend: function(xhr) {
	          xhr.setRequestHeader("Accept", "application/json");
	          xhr.setRequestHeader("Content-Type", "application/json");
	    },

	    success: function(data) {
		   	if(data.msgCd =="0") {
				alert("신규 : "+ data.rtnValue01+"건  |  수정 : "+data.rtnValue02+"건이 정상 처리 되었습니다.");
				ordProdSearch();
		   	}else{
		   		if(data.msgCd =="1") 		alert("처리할 데이터가 없습니다. 다시 등록 해주세요!");	
		   		else if(data.msgCd =="2")   alert("발주등록이 마감되었습니다.\n → 마감시간:"+data.rtnValue01.replace(sr,''));
				else if(data.msgCd =="3")	alert("마감된 전표번호가 존재합니다.\n → 전표번호:"+data.rtnValue01.replace(sr,''));	
				else alert("처리중 오류가 발생하였습니다.\n Code : "+data.msgCd+ "  Message : "+data.message)
			}
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
	searchInfo['find_salesCd'] 	   = ${userSession.userId};

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

function sales_clear(){
	$("#ord_prod_form #salesCd").val('');
	$("#ord_prod_form #salesNm").val('');
}

function prdt_clear(){
	$("#ord_prod_form #prdtNm").val('');
}


</script>

<%-- Data Temp  --%>
<script type="text/x-jquery-tmpl" id="salesOrdProdTemplate"   >
	<tr>
		<td>
			<div class="col-md-12 col-sm-12 col-xs-12" style="padding: 2px;"> 
				<div><strong>\${prdtNm}</strong></div>
			</div>	
			<div class="col-md-12 col-sm-12 col-xs-12" style="padding: 0px;">
				<dd style="font-size: 0.9em; color: #777;">
					<code>\${numberComma(price)} | \${prdtStd} </code>
				</dd>

				<td>
						
					<input type="text"  onChange='countData(this);' class="form-control" id="ordQty" style="text-align: right; font-size:15px; width:100px;" 
							name="ordQty" 	value="\${Number(ordQty)}">
	
					</div>
						
					<input type="hidden" 		id="ordQtyBef"		name="ordQtyBef"	value="\${ordQty}">
					<input type="hidden" 		id="price" 			name="price" 		value="\${price}">
					<input type="hidden" 		id="prdtCd"			name="prdtCd"  		value="\${prdtCd}">
					<input type="hidden" 		id="salesSlipNo"	name="salesSlipNo"  value="\${salesSlipNo}">
					<input type="hidden" 		id="salesSeq"		name="salesSeq" 	value="\${salesSeq}">
					<input type="hidden" 		id="vatYn"			name="vatYn" 		value="\${vatYn}">
					<input type="hidden" 		id="totAmtHid"		name="totAmtHid" 	value="\${totAmtHid}">
					<input type="hidden"		id="stdYn"			name="stdYn" 		value="\${stdYn}">
					<input type="hidden"		id="boxQty"			name="boxQty" 		value="\${boxQty}">
					<input type="hidden"		id="qtyBox"			name="qtyBox" 		value="\${qtyBox}">
					<input type="hidden" 		id="ordUnitNm"		name="ordUnitNm" 	value="\${ordUnitNm}">
					<input type="hidden"		id="ordUnit"		name="ordUnit" 		value="\${ordUnit}">
					
					<input type="hidden"		id="pureAmt"		name="pureAmt" 		value="\${pureAmt}">
					<input type="hidden"		id="vatAmt"			name="vatAmt" 		value="\${vatAmt}">
					<input type="hidden"		id="totAmt"			name="totAmt" 		value="\${totAmt}">

					<input type="hidden"		id="whCd"			name="whCd" 		value="\'10001'">

					

			</td>
	
			<div  class="col-md-12 col-sm-12 col-xs-12" style="padding: 0px; padding-top: 5px;"> 
				<div class="form-group" style="margin-bottom: 2px;" >
					<div class="input-group">
<%--
						<span class="input-group-addon " style="text-align:left; width:50%; border:0px; background: none; padding: 0px; padding-left: 5px;">
							 
							<cite>
								<span id="totAmt" name="totAmt" style="font-size:18px; color">\${totAmt}</span>
							</cite>
							
						</span>
						<div style="position: relative;">
						
						<input type="text"  onChange='countData(this);' class="form-control" id="ordQty" style="text-align: center; font-size:15px; width:100px;" 
								name="ordQty" 	onkeypress="return isNumberKey(event)" 		value="\${Number(ordQty)}">
	
						</div>
						
						<input type="hidden" 		id="ordQtyBef"		name="ordQtyBef"	value="\${ordQty}">
						<input type="hidden" 		id="price" 			name="price" 		value="\${price}">
						<input type="hidden" 		id="prdtCd"			name="prdtCd"  		value="\${prdtCd}">
						<input type="hidden" 		id="salesSlipNo"	name="salesSlipNo"  value="\${salesSlipNo}">
						<input type="hidden" 		id="salesSeq"		name="salesSeq" 	value="\${salesSeq}">
						<input type="hidden" 		id="vatYn"			name="vatYn" 		value="\${vatYn}">
						<input type="hidden" 		id="totAmtHid"		name="totAmtHid" 	value="\${totAmtHid}">
						<input type="hidden"		id="stdYn"			name="stdYn" 		value="\${stdYn}">
						<input type="hidden"		id="boxQty"			name="boxQty" 		value="\${boxQty}">
						<input type="hidden"		id="qtyBox"			name="qtyBox" 		value="\${qtyBox}">
						<input type="hidden" 		id="ordUnitNm"		name="ordUnitNm" 	value="\${ordUnitNm}">
						<input type="hidden"		id="ordUnit"		name="ordUnit" 		value="\${ordUnit}">
						
						<input type="hidden"		id="pureAmt"		name="pureAmt" 		value="\${pureAmt}">
						<input type="hidden"		id="vatAmt"			name="vatAmt" 		value="\${vatAmt}">
						<input type="hidden"		id="totAmt"			name="totAmt" 		value="\${totAmt}">

						<span class="input-group-addon text-gray" style="border:0px; width:25px; background: none; padding: 0px; padding-left: 2px; padding-top: 18px;">
							<small>\${ordUnitNm}</small>
						</span>
--%>
					</div>
					
				</div>
			</div>
		</td>
	</tr>
</script>

<!-- Content Wrapper. Contains page content -->
 <div class="content-wrapper">

	<!-- Content Header (Page header) -->
	<jsp:include page="/WEB-INF/views/common/include/incResPageTitle.jsp" />

	<!-- Main content -->
	<section class="content">
	
		
		
		<div class="box box-solid">
			<div class="box-header with-border" style="padding-top: 10px; padding-bottom: 2px;">
				
				<div class="box-title user-block">
					<div style="padding-bottom: 4px; font-size: 0.9em; font-weight: bold;"><a>발주대상 정보 조회</a></div>
					<div class="text-muted" style="font-size: 0.7em;"><cite><i class="fa fa-clock-o"> </i> <c:out value="${todayFmt}" /></cite></div>
				</div>
				<div class="box-tools pull-right">
	            	
	            	<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
	                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
	                <button class="btn btn-box-tool" data-widget="reload" id="btnReload"><i class="fa fa-refresh"></i></button>
                </div>
			</div>
			<div class="box-body"  id="ord_prod_form">
				<div class="col-sm-6" >
					<div class="form-group" style="margin-bottom: 5px;">
						
							<c:choose>
								<c:when test="${userSession.userType == 'S1'}">
									<div class="input-group" id="form-custOrdReg">
									<input type="text" class="form-control pull-right"	 id="salesNm" name="salesNm"    placeholder='매출처명'  readonly="readonly"/>
									<input type="hidden" class="form-control pull-right" id="salesCd" name="salesCd" 	placeholder='매출처코드' readonly="readonly" />
		                   			<input type="hidden" id="ordDt"   name="ordDt" 		placeholder='발주일자' value="<c:out value="${ordDt}" />" readonly="readonly" />
								</div>
							</c:when>
							<c:otherwise>
								<div id="form-custOrdReg">
									<input type="text" class="form-control pull-left"	 id="salesNm" name="salesNm"  style="width: 80%;"   placeholder='매출처명' onclick="sales_clear()" />
									<input type="hidden" class="form-control pull-right" id="salesCd" name="salesCd"  placeholder='매출처코드' readonly="readonly" />
		                   			<input type="hidden" id="ordDt"   name="ordDt" 		placeholder='발주일자' value="<c:out value="${ordDt}" />" readonly="readonly" />

									<button type="button" id="btnSalesMstFind" class="btn btn-primary btn-flat"><i class="fa fa-search"></i> <spring:message code="button.search" /></button>
									
								</div>
							</c:otherwise>
							</c:choose>
						
						<div  class="mailbox-read-info" style="padding-top: 5px; text-align: center;">
							<ul class="list-unstyled">
								<li style="float: left; width: 50%; text-align: left;">
									<span class="badge bg-yellow">여</span>
									<span id="creLim">0</span>
								</li>	
								<li style="float: left; width: 50%; text-align: left;">
									<span class="badge bg-red">미</span>
									<span id="balRec">0</span>
							</ul>
						</div>
						<div class="mailbox-read-info" id="warningState" style="display: none;" > 
							<small class="text-red"><i class="fa fa-warning"></i> <span  id="warningMessage"></span></small>
						</div>
					</div>	
				</div>
				
				<div class="col-sm-6">
					<div class="form-group" style="margin-bottom: 5px;">
					<input type="text" class="form-control" placeholder='발주 품목명' id="prdtNm" name="prdtNm"  onclick="prdt_clear()">
					</div>
				</div>
			</div>
		</div>
		<!-- Default box -->
		<div class="box box-solid">
			<div class="box-header with-border">
				<h3 class="box-title">
					<i class="fa fa-server"></i> 발주 List
				</h3>
				<!--  box-tools -->
				<div class="box-tools pull-right">
					<button id="btnOrdProdSearch" class="btn btn-primary btn-sm" type="button" >
						<i class="fa fa-search"></i> <spring:message code="button.search" />
					</button>
					<button id="btnOrdProdSave" class="btn btn-danger btn-sm" type="button" >
						<i class="fa fa-edit"></i> <spring:message code="button.save" />
					</button>
				</div>
				<!-- /.box-tools -->
			</div> 
			
			<div class="mailbox-read-info" id="warningSaveState" style="display: none;" > 
				<small class="text-red"><i class="fa fa-warning"></i> <span  id="warningSaveMessage"></span></small>
			</div>
			<div class="box-header with-border" id="headSum" style="padding: 0px;">
			
				<div class="box-title" style="padding-bottom: 2px; padding-left: 5px; padding-top: 5px;">
					<div class="input-group" style="width: 200px; ">
						<div class="input-group-addon"><small>납품일자</small> <i class="fa fa fa-calendar"></i></div>
						<input type="text" class="form-control pull-right" id="dlvDt" placeholder='납품일자' readonly="readonly" />
					</div>
				</div>
					
<!-- 				<div class="box-tools pull-right"> -->
<!-- 					<label style="font-weight: normal;" id="sumQty">0</label> / <label style="font-weight: normal;" id="sumAmt">0</label> -->
<!-- 				</div> -->
			</div>
			
			<div class="box-body" style="padding-top:2px; ">
				<table class="table table-striped">
					<tbody id="salesOrdProdList"/>
				</table>
			</div>
			<div class="box-footer with-border" style="padding:2px;" id="footerSum">
				<div class="pull-right link-black" style="margin-right: 5px;">
					<label style="font-weight: normal;" id="sumQty">0</label> / <label style="font-weight: normal;" id="sumAmt">0</label>
				</div>
			</div>
			<!--  /.box-header-->
		</div>	
    </section>
    <!--/. Main content -->
 </div>
<!-- /.Content Wrapper. Contains page content -->




<!-- 매출처 찾기 모달 레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/common/find-res_SalesMaster.jsp" />
<!-- /매출처 찾기 모달  레이어 영역 end  -->


<!-- 매출처 찾기 모달 레이어 영역 start  -->
<jsp:include page="/WEB-INF/views/app/sm/resSalesProdDetails.jsp" />
<!-- /매출처 찾기 모달  레이어 영역 end  -->
