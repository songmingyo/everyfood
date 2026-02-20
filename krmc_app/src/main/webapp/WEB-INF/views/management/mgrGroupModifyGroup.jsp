<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<spring:message code='common.choice' var="commonChoice"/>	<%-- 선택 --%>
<script type="text/javascript">

	/* 등록 또는 수정 처리 여부 - 참일 경우 부모창을 재조회 한다. */
	var _SYSTEM_UPDATE_PROC_COUNT = false;

	/* Layer Event Creat */
	function setSystemGroupUpdateLayerEvent(){

		/* 레이어 활성화 */
	 	$('#system_group_layer').show();
		$('#opacity').show();


		/* 레이어 드레그 Event */
	  	$("#system_group_layer").draggable({
		    handle: $("h1")
			,cancle: $("a.close")
			,containment: "#opacity"
			,scroll: false
		});

		/* 레이어 닫기버튼 Click Event */
		 $('#system_group_layer a.close').click(function(e){

		 	closePopup();

		 });

		 /* 저장버튼 Click Event */
	 	 $('#btnSystemGroupUpdate').unbind().click(null, systemGroupUpdateProc);

		 /* Form validate */
	 	 $('#systemGroupUpdateForm').validate({
				rules:{
					groupId : { required: true, identifier: true, minlength:2, maxlength:6 },
					groupFg : { required: true},
			        groupNm : {required:  true, minlength: 2, maxlength: 50 },
			     groupSubNm : { required: true, minlength: 2, maxlength: 50 },
				  groupDesc : { maxlength: 100 }
				}

		});
	}

	/* 레이어호출 */
	function systemGroupUpdateLayer(mode){

		$("#systemGroupUpdateForm").find("label").filter(".error").remove();

		_SYSTEM_UPDATE_PROC_COUNT = false;	// 초기화

		var selectedGroupId = $('#selectedGroupId').val();
		$('#systemGroupUpdateForm input[name="saveMode"]').val(mode);

		setInputFormInit();
		setSystemGroupUpdateLayerEvent();


		if(mode == 'U'){
			if(selectedGroupId == ''){
				alert('<spring:message code="app.manager.groupedit._systemgroupupdatelayer.a1" />');<%--수정하려는 그룹을 선택하십시오.--%>
				closePopup();
			}else{
				$.ajax({
					contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
					url  : '<c:url value="/app/mgr/manager/mgrGroup_selGroupEdit" />',
					data : JSON.stringify({'groupId'   : selectedGroupId }),
					success : function(data){
						if(data == null) {
							alert('<spring:message code="app.manager.groupedit._systemgroupupdatelayer.a2" />'); <%--선택된 그룹의 정보가 조회되지 않았습니다.--%>
						}else setUpdateFormInit(data);

					}
				});
			}
		}
	}
	/* 저장 및 수정 실행 */
	function systemGroupUpdateProc(){
		
		
		//if validate Success
		if($('#systemGroupUpdateForm').valid()){
			var useYn 		= "";	//사용여부 Y/N

			var fromData = {};
			$('#systemGroupUpdateForm').find('input, select, textarea').map(function() {

				
				if(this.name) {
					if(this.name =='useYn'){
						if(!useYn) {
							if( this.value =='Y' && $(this)[0].checked) 	useYn = "Y";
							else useYn = "N";
							fromData[this.name] = useYn;
						}
					}
					else fromData[this.name] = $(this).val();
				}
			});

			
			// 저장 이벤트 json 실행---------------------------
			$.ajax({
			      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			      url  : '<c:url value="/app/mgr/manager/mgrGroup_insGroupData" />',
			      data : JSON.stringify(fromData),
			      success : function(data){
			    	  if(data == null) msg = '<spring:message code="app.manager.groupedit._systemgroupupdateproc.m1" />'; <%--"처리된 데이터가 없습니다.";--%>
		            else if('success' == data.msgCd)
		            {
		            	msg = '<spring:message code="app.manager.groupedit._systemgroupupdateproc.m2" />'; <%--"저장이 정상적으로 처리되었습니다.";--%>
		            	_SYSTEM_UPDATE_PROC_COUNT = true;
		            	$('#systemGroupUpdateForm input[name="saveMode"]').val('U');
		            	closePopup();
		            }
		            else if('duple'  == data.msgCd ){
		            	msg = '<spring:message code="app.manager.groupedit._systemgroupupdateproc.m3" />'; <%--"입력하신 그룹아이디는 이미 사용중입니다.";--%>
		            }
		            else msg = '<spring:message code="app.manager.groupedit._systemgroupupdateproc.m4" />'; <%--"저장중 오류가 발생하였습니다.";--%>

		          	alert(msg);
		          	
		          	closePopup();
			      }
			});
		}
		//-----------------------------------------------
	}


	/* 신규 등록 화면 초기화 */
	function setInputFormInit() {

			$('#systemGroupUpdateForm input[name="groupId"]').val('').attr('readonly',false).attr('style', '').next().show();
			$('#systemGroupUpdateForm input[name="groupNm"]').val('');
			$('#systemGroupUpdateForm input[name="groupSubNm"]').val('');
			$('#systemGroupUpdateForm textarea[name="groupDesc"]').val('');
			$('#systemGroupUpdateForm select[name="groupFg"]').val('');
			$('#systemGroupUpdateForm input[name="useYn"][value="Y"]').attr('checked', 'checked');
			$('#systemGroupUpdateForm input[name="useYn"][value="N"]').attr('checked', false);
	}
	/* 수정 화면 초기화 */
	function setUpdateFormInit(data) {

		$('#systemGroupUpdateForm input[name="groupId"]').val(data.groupId)
			.attr('readonly', 'readonly').attr('style', 'background-color: #efefef').next().hide();
		$('#systemGroupUpdateForm input[name="groupNm"]').val(data.groupNm);
		$('#systemGroupUpdateForm input[name="groupSubNm"]').val(data.groupSubNm);
		$('#systemGroupUpdateForm textarea[name="groupDesc"]').val(data.groupDesc);

		$('#systemGroupUpdateForm select[name="groupFg"]').val(data.groupFg);
		if(data.useYn == 'Y'){
			$('#systemGroupUpdateForm input[name="useYn"][value="Y"]').attr('checked', 'checked');
			$('#systemGroupUpdateForm input[name="useYn"][value="N"]').attr('checked', false);
		}else{
			$('#systemGroupUpdateForm input[name="useYn"][value="Y"]').attr('checked', false);
			$('#systemGroupUpdateForm input[name="useYn"][value="N"]').attr('checked', 'checked');
		}

	}
	
	function closePopup(){
		$('#system_group_layer').hide();
		$('#opacity').hide();
		
		$('*').filter(function(){
			return $(this).data('tooltipsterNs');
		}).tooltipster('hide');		
		
		
		if(_SYSTEM_UPDATE_PROC_COUNT) eventSearch();			// 그룹 재조회
	}
</script>

<div class="pop_layer_new" id="system_group_layer"
	style="position:absolute;left:35%; top:25%;width:500px; display:none;">
 <input type="hidden" id="validationYn">
    <h1><spring:message code="app.manager.groupedit.toptitle" /></h1> <%-- 그룹정보관리 --%>
    <spring:message code="common.close" var="commonclose"/> <%-- 닫기 --%>
	<a href="javascript:void(0);" class="close"><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
         <div id="pop_section">
            <div class="tit_area">
                <h2 style="float: left;"><spring:message code="app.manager.groupedit.title.info" /></h2>	<%-- 그룹정보 --%>
                <div>
                   <html:button id="btnSystemGroupUpdate" auth="save" msgKey="button.save"   /> <%-- 저장 --%>
                </div>
            </div>
            <form id="systemGroupUpdateForm" name="systemGroupUpdateForm" method="post">
            <table class="type1" summary="<spring:message code="app.manager.groupedit.webacc.bdtag.table.summary.mastergroup" />">  <%--그룹정보 변경 --%>
                    <caption><spring:message code="app.manager.groupedit.webacc.bdtag.table.caption.mastergroup" /></caption>		<%--그룹정보 변경 --%>
                    <colgroup>
                    <col width="25%">
                    <col width="*">
                    </colgroup>
                    <tbody>
	                    <tr>
	                        <th>* <spring:message code="app.manager.groupedit.label.groupid" /></th> <%--그룹아이디 --%>
	                        <td>
	                        	<input type="hidden" name="saveMode" value="I" />
	                        	<input type="text" style="width: 150px; ime-mode:disabled;" name="groupId" value=""  />
	                        	<span style="padding:7px 0 0 0; color:#8f8f8f; display:block; font-size:10px;"><spring:message code="app.manager.groupedit.label.msg1" /></span> <%--영문+숫자 조합 자릿수 4-6 --%>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th>* <spring:message code="app.manager.groupedit.label.groupFg" /></th> <%-- 그룹사용유형 --%>
	                        <td>
	                        	<html:codeTag comType="SELECT" objId="groupFg" objName="groupFg" parentID="9202" defName="${commonChoice }" />
	                        </td>
	                    </tr>
	                    <tr>
	                        <th>* <spring:message code="app.manager.groupedit.label.groupnm" /></th> <%--그룹명칭(ko) --%>
	                        <td><input type="text" style="width: 80%;" name="groupNm" value=""  /></td>
	                    </tr>
	                     <tr>
	                        <th>* <spring:message code="app.manager.groupedit.label.groupsubnm" /></th> <%--그룹명칭(en) --%>
	                        <td><input type="text" style="width: 80%" name="groupSubNm" value="" /></td>
						</tr>
	                    <tr>
	                    	<th><spring:message code="app.manager.groupedit.label.useyn" /></th> <%-- 사용여부 --%>
	                   		<td>
	                        	<html:codeTag comType="RADIO" objId="useYn" objName="useYn" parentID="9002" selectParam="0"  />
	                        </td>
	                    </tr>
	                    <tr>
	                        <th><spring:message code="app.manager.groupedit.label.groupdesc" /></th> <%--그룹설명 --%>
	                        <td><textarea name="groupDesc" rows="5" cols="56" style="height: 50px; width: 90%"></textarea></td>
	                     </tr>
                    </tbody>
			</table>
			</form>
		</div>
	</div>
</div>
