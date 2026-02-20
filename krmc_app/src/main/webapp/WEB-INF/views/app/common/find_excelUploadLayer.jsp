<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js" integrity="sha384-qlmct0AOBiA2VPZkMY3+2WqkHtIQ9lSdAsAn5RUJD/3vA5MKDgSGcdmIv4ycVxyn" crossorigin="anonymous"></script>
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
	
	/* 업로드 이벤트 */
	function _eventExcelStrSave() {

		
		$("#excelUploadForm").ajaxSubmit({
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			type : 'post',
			dataType : 'json',
			async : false,
			url : '<c:url value="/app/common/excelUpload.json?${_csrf.parameterName}=${_csrf.token}"/>',
			success : function(data) {
				if(comExcelUploadCallbackFnc) comExcelUploadCallbackFnc(data);

				closeExcepUploadLayer();
				
			},
			loadError:function(xhr, status, error){
				//do something...
			}
		
		});
		
/*
		var options = {
				target:  '#divToUpdate',
				url:     '<c:url value="/app/common/excelUpload.json?${_csrf.parameterName}=${_csrf.token}"/>',
				success: function(data) {
					alert('Thanks for your comment!');
					if(comExcelUploadCallbackFnc) comExcelUploadCallbackFnc(data.resultList);
					closeExcepUploadLayer();
			}
		};

		// pass options to ajaxForm
		$('#excelUploadForm').ajaxForm(options);
		*/
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
    <h1>Excel Upload</h1>    
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
    