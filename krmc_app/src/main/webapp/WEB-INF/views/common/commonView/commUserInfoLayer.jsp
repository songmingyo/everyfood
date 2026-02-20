<%@ page language="java"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">

	/* Layer Event Creat */
	function _setCommUserInfoLayerEvent(){
		/* 레이어 활성화 */
		var layerID = 'commUserInfo';
		setLayerPopupLocationSettings(layerID, 300, 600);
		
		/* 레이어 드레그 Event */
		setLayerDraggable(layerID);
		
		/* 레이어 닫기버튼 Click Event */
		$('#'+layerID + ' a.close').click(function(e){
			closeContCommUserInfoLayer();
		});
		
		
		$('#btnCommUserInfoLayerClose').unbind().click(null,		closeCommUserInfoLayer); 			//닫기
		
	}
	
	/*레이어 close*/
	function closeCommUserInfoLayer(){
		$('#commUserInfo').hide();
	 	$('#opacity').hide();
	}
	
	/* 레이어호출 */
	function _eventOpenContCommUserInfoLayer(memberCd){
		_setCommUserInfoLayerEvent();		
	}
	

</script>

<div class="pop_layer_new" id="commUserInfo" style="width:700px; height:140px; display:none;">
    <h1><spring:message code="app.common.comUserInfoLayer.title"/></h1>	<%--User Details  --%>
	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
		
			<form id="commUserInfoForm" name="commUserInfoForm" method="post">
			<table style="width: 100%" class="type1">
            	<colgroup>
					<col width="20%">
					<col width="*">
					<col width="20%">
					<col width="*">
				</colgroup>
				
				<tbody id="_DataBody">
					<tr>
						<th><spring:message code="app.common.comUserInfoLayer.title"/></th>			<%-- ID --%>
						<td>
							<span id="userId"></span>
						</td>
						<th><spring:message code="app.common.comUserInfoLayer.label.userId"/></th>	<%-- Name --%>
						<td>
							<span id="userNm"></span>
						</td>
					</tr>
					<tr>
						<th><spring:message code="app.common.comUserInfoLayer.label.telNo"/></th>	<%-- Phone number --%>
						<td>
							<span id="telNo"></span>
						</td>
						<th><spring:message code="app.common.comUserInfoLayer.label.hpNo"/></th>	<%-- Mobile number --%>
						<td>
							<span id="hpNo"></span>
						</td>
					</tr>
					<tr>
						<th><spring:message code="app.common.comUserInfoLayer.label.email"/></th>	<%-- Email --%>
						<td colspan="3">
							<span id="email"></span>
						</td>
					</tr>
				</tbody>
			</table>
			
			</form>
	
			<div align="right" style="padding: 10px 5px 0 0;">
				<html:button id="btnCommUserInfoLayerClose" name="btnCommUserInfoLayerClose" auth="select" msgKey="common.close" /> <%-- close --%>
			</div>
	
		</div>
		
	</div>
	
</div>

    
    