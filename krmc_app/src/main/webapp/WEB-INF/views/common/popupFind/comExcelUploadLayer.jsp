<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script type="text/javascript">
	var comExcelUploadCallbackFnc = null;
	/* Layer Event Creat */
	function _setCommonExcelUploadLayerEvent(){
		
		/* 레이어 활성화 */
		var layerID = 'common_excel_upload_layer';
		setLayerPopupLocationSettings(layerID, 200, 350);
		
		/* 레이어 활성화 */
		$('#common_excel_upload_layer').show();
		$('#opacity').show();
		
		/* 레이어 드레그 Event */
		$("#common_excel_upload_layer").draggable({
			handle: $("h1")
			,cancle: $("a.close")
			,containment: "parent"
			,scroll: false
		});
			
		/* 레이어 닫기버튼 Click Event */
		$('#common_excel_upload_layer a.close').click(function(e){
			closeExcepUploadLayer();
		});	
		
		$('#btnExcelStrSave').unbind().click(null, _eventExcelStrSave); 	  	//엑셀 업로드 파일 적용하기 이벤트
	}
	
	/*레이어 close*/
	function closeExcepUploadLayer(){
		$('#common_excel_upload_layer').hide();
		comExcelUploadCallbackFnc = null;
	 	$('#opacity').hide();
	}	
	
	/* 레이어호출 */
	function _commonExcelUploadLayer(gbn,callbackFnc){	
		
		$("#excelUploadForm input[name='excelFileUploadGbn']").val(gbn);		
		$("#excelUploadForm input[name='file']").val("");		//ie8은 안먹힘..
		comExcelUploadCallbackFnc = callbackFnc;
		_setCommonExcelUploadLayerEvent();		
	}
	
	/* 계약서 작성시 협력 업체 추가 이벤트 */
	function _eventContAppendCompanyList() {
		
		if (!confirm( '엑셀업로드 하시겠습니까?')) {
	        return false;
		} 
		$("#excelUploadForm").ajaxSubmit({
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			type : 'post',
			dataType : "json",
			async : false,
			url : "/app/common/excelUpload.json",
			success : function(data) {
				
				var contCompList = data.resultList;
				if(comExcelUploadCallbackFnc) comExcelUploadCallbackFnc(contCompList);
			},
			loadError:function(xhr, status, error){
				//do something...
			}
		});
	}
	
	/* 표준거래 계약서 갱싱 일괄발송 대상  */
	function _eventContRenewalCompanyList() {
		$("#excelUploadForm").ajaxSubmit({
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
	 	    type : 'post',
	 	    dataType : "json",
	 	    async : false,
	 	    url : "/app/common/contRenewal_excelUpload.json",
	 	    success : function(data) {	 	    	
	 	    	conMasterIds	=	data.conMasterIds;		//엑셀에서 추출한 협력사 코드 배열 값	 	
	 	    	
	 	    	_eventSearch();
	 	    },
	 	   	loadError:function(xhr, status, error){
	 	   		//do something...
	       	}
	 	});	
	}
	
	/* 표준거래계약서 갱신 일괄회수 대상  */
	function _eventContBatchHoesuContractList() {
		$("#excelUploadForm").ajaxSubmit({
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
	 	    type : 'post',
	 	    dataType : "json",
	 	    async : false,
	 	    url : "/app/common/contRenewal_excelUpload.json",
	 	    success : function(data) {	 	    	
	 	    	conMasterIds	=	data.conMasterIds;		//엑셀에서 추출한 협력사 코드 배열 값	 	
	 	    	
	 	    	_eventSearch();
	 	    },
	 	   	loadError:function(xhr, status, error){
	 	   		//do something...
	       	}
	 	});	
	}
	
	/* 엑셀 파일 업로드 적용하기 이벤트 */
	function _eventExcelStrSave(){
		if(fileValueCheck($('#file').val())){
			
			var excelUploadgbn	=	$("#excelUploadForm input[name='excelFileUploadGbn']").val();	//엑셀 파일 업로드 구분
			
			//계약서 작성 협력사 추가
			if (excelUploadgbn == "cont_append_company") {
				_eventContAppendCompanyList();
				
			//표준거래 계약서 갱신 일괄발송 대상 
			} else if (excelUploadgbn == "cont_renewal_company") {
				_eventContRenewalCompanyList();
			
			//표준거래계약서 갱신 일괄회수 대상 
			} else if (excelUploadgbn == "cont_batchHoesu_contract") {
				_eventContBatchHoesuContractList();
			}
			closeExcepUploadLayer();
		}
	}
	
	/* 파일 확장자 체크 */
	function fileValueCheck(val){
	    var filename = val;
	    var filetype = filename.substring(filename.lastIndexOf(".") + 1);
	    
	    filetype = filetype.toUpperCase();
	    var filterStr = new Array("XLS", "XLSX");
	    for (var i = 0; i < filterStr.length; i++) {
	        if (filetype == filterStr[i]) {
	            return true;
	        }
	    }
	    
	    /**해당 파일의 확장자 {0}은(는) 업로드가 불가능합니다.*/
		alert('<spring:message code="message.fail.excelUpload.type" arguments="'+filetype+'"/>');
	    return false;
	}
</script>





<div class="pop_layer_new" id="common_excel_upload_layer" 
	style="margin:-220px 0 0 -228px;width:800px; display:none;">
    <h1>Excep Upload</h1>    
	<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	
	<form id="excelUploadForm"		name="excelUploadForm"		enctype="multipart/form-data"	method="post">
	
		<input	type="hidden"	id="excelFileUploadGbn"		name="excelFileUploadGbn">
	
		<div class="table_type4" style="margin-bottom: 1px;">
			<table class="type1">
					<tr height="30">          	
						<th width="180px" >
							<span style="margin-left:10px;font-weight:bold;font-size:12px;"> Excel Upload File :</span>
						</th>
						<td>
							<input name="file"			id="file" 	style="width: 430px;" title="Excel File" type="file">
							<html:button id="btnExcelStrSave" 	auth="save" value="파일 적용하기"  />
						</td>
					</tr>
			</table>
	 	</div>	
	</form>		
</div>

<iframe id="popTempExcelhiddenFrame" name="popTempExcelhiddenFrame" scrolling="no" width="0" height="0" style="display: none;"></iframe>
    