<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<script language="javascript">

	var tempCallBackFnc;
	
	$(document).ready(function($){
		//초기화 이벤트 ---------------------------------------------------
		$('#btnPopExcelStrSave').unbind().click(null, eventExcelUpload);  	// Excel Upload Load
		//-----------------------------------------------------------------
	});
	
	function exportExcelTempLayer(callBackFnc, excelTempParam){
		
		/* 레이어 활성화 */
        $('#export_exceltemp_layer').show();
		$('#opacity').show();
		$('#excelSysKnd').val("");
		$('#excelWorkKnd').val("");
	
		var excelSysKnd = excelTempParam.excelSysKnd;
		var excelWorkKnd = excelTempParam.excelWorkKnd;
		$('#popExcelUploadForm input[name="excelSysKnd"]').val(excelSysKnd);
		$('#popExcelUploadForm input[name="excelWorkKnd"]').val(excelWorkKnd);		
		
        /* 레이어 드레그 Event */
        $("#export_exceltemp_layer").draggable({
            handle: $("#export_exceltemp_layer>form>h1")
            ,cancel: $("#export_exceltemp_layer>form>a.close")
            ,containment: "parent"
            ,scroll: false
        });
                
        /* 레이어 닫기버튼 Click Event */
         $('#export_exceltemp_layer>form>a.close').click(function(e){

            $('#export_exceltemp_layer').hide();
            /*2개 이상의 레이어일때 opacity hide 제어*/
            if($('.pop_layer_warp:visible').length <= 1){
            	$('#opacity').hide();
            }
            
            e.preventDefault();
         });
        
         tempCallBackFnc = callBackFnc;   
	}
	
	// 엑셀데이터 처리후 수행함수
	function fncExcel(callBackParam) {
		
		var callBackMsg = callBackParam.callBackMsg;
		var excelSysKnd = callBackParam.excelSysKnd;
		var excelWorkKnd = callBackParam.excelWorkKnd;
// 		console.log(excelSysKnd);
// 		console.log(excelWorkKnd);
		
		if(callBackMsg == 'success'){
			alert('업로드 성공'); // 업로드에 성공하였습니다. 		
			fncExcelUploadResult(callBackParam);
		}else {
			if(callBackMsg == 'fail'){
				alert('업로드 실패'); // 업로드에 실패하였습니다.
			}else{
				alert(callBackMsg); 									  // 등록할 데이터 없음
			}
				
			$("#file").val("");
		}

		$('.pop_layer_warp').unmask();	
	}
		
	// 엑셀업로드 수행
	function eventExcelUpload(){
		if(fileValueCheck($('#file').val())){
			if (!confirm( '엑셀업로드 하시겠습니까?')) {
		        return false;
			} 
			
			document.popExcelUploadForm.action = '<c:url value="/app/smp/sample_insSampleExcelUpload?${_csrf.parameterName}=${_csrf.token}"/>';
			document.popExcelUploadForm.target = "popTempExcelhiddenFrame";
			document.popExcelUploadForm.submit();
			
			$('.pop_layer_warp').mask('엑셀업로드 중입니다.');
		}
	}	
	
	// 파일 확장자 체크
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
	
	// 엑셀 업로드 결과 
	function fncExcelUploadResult(callBackParam){
		$("#popExcelUploadForm")[0].reset();
		
		$('#export_exceltemp_layer').hide();
	    // 2개 이상의 레이어일때 opacity hide 제어
        if($('.pop_layer_warp:visible').length <= 1){
        	$('#opacity').hide();
        }
		
	    tempCallBackFnc(callBackParam);
	}
	
</script>

<div class="pop_layer_warp" id="export_exceltemp_layer" style="margin:-20px 0 0 -200px;width:500px; height:150px; display:none;">
	<%-- multipart/form-data로 컨트롤러에 요청시에는 <sec:csrfInput /> 태그를 사용하지않고 경로에 파라미터로 전송해야함. --%>
	<form id="popExcelUploadForm" name="popExcelUploadForm" action="" method="post" enctype="multipart/form-data">	

		<h1>엑셀업로드</h1>
		<%-- <a id="btnClose" href="javascript:void(0);" class="close" ><img src="/images/app/common/btn_close_none.gif" alt="${close}"></a> --%>
		<a id="btnClose" href="javascript:void(0);" class="close" ><i class="fa fa-times" style="color:white;"></i></a>	
		<div id="pop_content" class="open_content">
	         <div id="pop_section">  
				<fieldset></fieldset>
				<table class="type1">
	          		<tr height="50">
	          			<th width="100px" >
	          				<span style="margin-left:10px;font-weight:bold;font-size:12px;">   
	          					업로드 엑셀파일
	          				</span>
	          			</th>
						<td>
							<input type="hidden" id="excelSysKnd" name="excelSysKnd" value="">
							<input type="hidden" id="excelWorkKnd" name="excelWorkKnd" value="">
							<input id="file" name="file" style="float:left" title="Excel File" type="file">
						</td>
					</tr>
				</table>
			</div>	
			<div align="center" style="padding-top:15px">	
				<html:button id="btnPopExcelStrSave" auth="save" value="업로드" />
	        </div>
		</div>
	</form>
</div>

<iframe id="popTempExcelhiddenFrame" name="popTempExcelhiddenFrame" scrolling="no" width="0" height="0" style="display: none;"></iframe>