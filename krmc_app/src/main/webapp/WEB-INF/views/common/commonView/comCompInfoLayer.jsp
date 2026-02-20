<%@ page language="java"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<%--
 Name : comCompInfoLayer.jsp
 Description : 업체상세정보조회
--%>
<script type="text/javascript">
	/* Layer Event Creat */
	function _setCommCompInfoLayerEvent(masterId){
		/* 레이어 활성화 */
		var layerID = 'commCompInfo';
		setLayerPopupLocationSettings(layerID, 500, 700);

		/* 레이어 드레그 Event */
		setLayerDraggable(layerID);

		/* 레이어 닫기버튼 Click Event */
		$('#'+layerID + ' a.close').click(function(e){
			closeCommCompInfoLayer();
		});

		$('#btnCommCompInfoLayerClose').unbind().click(null, closeCommCompInfoLayer); 	//닫기

		_eventSearchFind(masterId);

	}

	/*조회 event*/
	function _eventSearchFind(masterId){

		var str = { 'masterId'  	: 	masterId }; 		//업체코드세팅
		var url = "<c:url value='/ecs/selectCompInfoData.json'/>";

	  	$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : url,
		      data : JSON.stringify(str),
		      success : function(data){
		    	  setCompInfo(data)
		      }
		});

	}

	/*레이어 close*/
	function closeCommCompInfoLayer(){
		$('#commCompInfo').hide();
	 	$('#opacity').hide();
	}


	/*set company data*/
	function setCompInfo(data){
		//inContWrite에서 인클루드된 jsp에 겹치는 id 있어서 id앞에 tbody명시
		$('#_DataBody #compNm').text(data.compNm) ;
		$('#_DataBody #compNmEn').text(data.compNmEn);
		$('#_DataBody #compNum').text(getConvertBmanNo(data.bmanNo));
		$('#_DataBody #taxNo').text(data.taxNo);
		$('#_DataBody #bizOwnerNm').text(data.ceoNm);
		$('#_DataBody #telNo').text(getConvertPhoneNumber(data.repTelNo));
		$('#_DataBody #bizAddr').text(data.zipAddr);
		$('#_DataBody #bizAddrEn').text(data.zipAddrEn);
		$('#_DataBody #faxTelNo').text(data.faxTelNo);
		
		$('#_DataBody2 #bankAccCd').text(data.bankAccCd);
		$('#_DataBody2 #bankAccNo').text(data.bankAccNo);
		$('#_DataBody2 #bankBeneNm').text(data.bankBeneNm);
		$('#_DataBody2 #conIssuedBy').text(data.conIssuedBy);
		$('#_DataBody2 #conIssuedByEn').text(data.conIssuedByEn);
		$('#_DataBody2 #conIssuedOn').text(data.conIssuedOnFmt);
		$('#_DataBody2 #poaNo').text(data.poaNo);
		$('#_DataBody2 #poaDy').text(data.poaDyFmt);
		$('#_DataBody2 #prAddr').text(data.prAddr);
		$('#_DataBody2 #prAddrEn').text(data.prAddrEn);
		$('#_DataBody2 #identityNo').text(data.identityNo);
		$('#_DataBody2 #identityIssuedOn').text(data.identityIssuedOnFmt);
		$('#_DataBody2 #identityIssuedAt').text(data.identityIssuedAt);
		$('#_DataBody2 #identityIssuedAtEn').text(data.identityIssuedAtEn);
	}

</script>

<div class="pop_layer_new" id="commCompInfo" style="position:absolute; width:800px; height:170px; display:none;">
    <h1><spring:message code="app.common.comCompInfoLayer.title" /></h1>	<%-- 업체 상세정보 --%>

	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content" style="padding-top:10px;">
         <div id="pop_section">

			<form id="commCompInfoForm" name="commCompInfoForm" method="post">

            <table style="width: 100%" class="type1">
            	<colgroup>
					<col width="*">
                    <col width="250">
                    <col width="*">
                    <col width="250">
				</colgroup>

				<tbody id="_DataBody">
					<tr>
						<th><spring:message code="app.common.comCompInfoLayer.label.compNm" /></th>		<%-- 회사명 --%>
						<td>
							<span id="compNm"></span>
						</td>
						<th><spring:message code="app.common.comCompInfoLayer.label.bmanno" /></th>	<%-- 사업자번호 --%>
						<td>
							<span id="compNum"></span>
						</td>
					</tr>
					<tr>						
						<th><spring:message code="app.common.comCompInfoLayer.label.bizOwnerNm" /></th>	 <%-- 대표자명 --%>
						<td>
							<span id="bizOwnerNm"></span>
						</td>
						<th><spring:message code="app.common.comCompInfoLayer.label.repTelNo" /></th>		  <%-- 대표전화 --%>
						<td>
							<span id="telNo"></span>
						</td>
					</tr>
					<tr>
						<th><spring:message code="app.common.comCompInfoLayer.label.bizAddr" /></th>	  <%-- 주소 --%>
						<td colspan="3">
							<span id="bizAddr"></span>
						</td>
					</tr>
				</tbody>
			</table>
			<table style="width: 100%" class="type1">
            	<colgroup>
					<col width="*">
                    <col width="250">
                    <col width="*">
                    <col width="250">            
				</colgroup>
				<tbody id="_DataBody2">
				<%--	<tr>
						<th><spring:message code="app.common.comCompInfoLayer.label.bankaccnm" /></th>	  // 은행명 
						<td>
							<span id="bankAccCd"></span>
						</td>
						<th><spring:message code="app.common.comCompInfoLayer.label.bankaccno" /></th>	  // 계좌번호  
						<td>
							<span id="bankAccNo"></span>
						</td>
					</tr>
					<tr>
						<th><spring:message code="app.common.comCompInfoLayer.label.bankbenenm" /></th>	 //수취인명
						<td>
							<span id="bankBeneNm"></span>
						</td>
						<th><spring:message code="app.common.comCompInfoLayer.label.busissuedon" /></th>	  //사업자번호 발행일자
						<td>
							<span id="conIssuedOn"></span>
						</td>
					</tr>
					<tr>
						<th><spring:message code="app.common.comCompInfoLayer.label.busissuedby" /></th>	  // 사업자번호 발급기관
						<td >
							<span id="conIssuedBy"></span>
						</td>
						<th><spring:message code="app.common.comCompInfoLayer.label.busissuedbyEn" /></th>	  업자번호 발급기관 영문   
						<td>
							<span id="conIssuedByEn"></span>
						</td>		
					</tr>
					<tr>
						<th><spring:message code="app.common.comCompInfoLayer.label.poaNo" /></th>	  // 위임장 번호 
						<td>
							<span id="poaNo"></span>
						</td>
						<th><spring:message code="app.common.comCompInfoLayer.label.poaDy" /></th>	 // 위임장 날짜   
						<td>
							<span id="poaDy"></span>
						</td>
					</tr>
					<tr>
						<th><spring:message code="app.common.comCompInfoLayer.label.prAddr" /></th>	   //거주지
						<td>
							<span id="prAddr"></span>
						</td>
						<th><spring:message code="app.common.comCompInfoLayer.label.prAddrEn" /></th>	  // 거주지 영문   
						<td>
							<span id="prAddrEn"></span>
						</td>
					</tr>
					<tr>
						<th><spring:message code="app.common.comCompInfoLayer.label.identityNo" /></th>	  //신분증번호/여권번호 
						<td>
							<span id="identityNo"></span>
						</td>
						<th><spring:message code="app.common.comCompInfoLayer.label.identityOn" /></th>	 //신분증번호/여권번호/발급일자   
						<td>
							<span id="identityIssuedOn"></span>
						</td>
					</tr>
					<tr>
						<th><spring:message code="app.common.comCompInfoLayer.label.identityAt" /></th>	  // 신분증,여권번호 발급기관 
						<td>
							<span id="identityIssuedAt"></span>
						</td>
						<th><spring:message code="app.common.comCompInfoLayer.label.identityAtEn" /></th>	  //신분증,여권번호 발급기관 영문   
						<td>
							<span id="identityIssuedAtEn"></span>
						</td>
					</tr> --%>
				</tbody>
			</table>
			</form>

			<div align="right" style="padding: 10px 5px 0 0;">
				<html:button id="btnCommCompInfoLayerClose" name="btnCommCompInfoLayerClose"  auth="select" msgKey="common.close" /> <%-- close --%>
			</div>

		</div>

	</div>

</div>
