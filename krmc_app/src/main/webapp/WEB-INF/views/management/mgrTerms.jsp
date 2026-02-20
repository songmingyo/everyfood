<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/resources/plugins/summernote/summernote-lite.css">
<script type="text/javascript" src="/resources/plugins/summernote/summernote-lite.js"></script>
<script type="text/javascript" src="/resources/plugins/summernote/lang/summernote-ko-KR.js"></script>
<script type="text/javascript" src="/resources/plugins/jQuery/jquery.popupWindow.js"></script>
<script type="text/javascript" src="/resources/plugins/jQuery/jquery.form-3.25.js"></script>
<script type="text/javascript" src="/resources/js/pearl/common/common-upload-popup.js"></script>

<script>
var content;
	$(document).ready(function($){
		
		$("#termsContent").summernote({
			height : 600
			, minHeight : null
			, maxHeight : null
			, focus : true
			, toolbar: [
			    ['font', ['bold', 'italic', 'underline', 'clear','strikethrough','superscript','subscript']],
			    ['fontname', ['fontname']],
			    ['fontsize',['fontsize']],
			    ['color', ['color']],
			    ['para', ['paragraph']],
			    ['height', ['height']],
			    ['table', ['table']],
			    ['insert', ['picture', 'hr']],
			    ['view', ['fullscreen', 'codeview','undo','redo']],
			    ['help', ['help']]
			  ]
		});
		
		$(".note-insert").remove();
		
		$("#termsDivnCd").change(function(){
			var termsDivnCd = $("#termsDivnCd").val();
			if(termsDivnCd != '' && termsDivnCd != '0'){	// "약관" 선택시,
				selectBoxChange();
			}else{		// "선택" 선택시, 데이터 전부 clear
				
				$('#termsVersionInfo').val("");
				//$('#termsContent').val("");
				$('#regDtFmt').val("");
				$('#regId').val("");
				
				$("#termsContent").summernote('code','<p><br></p>');
			}
			
		});
		
		$('#btnSave').unbind().click(null, doSave);
		
	});
	
	function selectBoxChange(){ 
		
		var searchInfo = {};
		searchInfo["termsDivnCd"] = $('#termsDivnCd').val();
		
		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			url : '<c:url value = "/app/mgr/manager/mgrTerms_selList"/>',
			data : JSON.stringify(searchInfo),
			success : function(data){

				$('#termsVersionInfo').val(data[0].CODE_NAME);
				$('#regDtFmt').val(data[0].REG_DT);
				$('#regId').val(data[0].REG_ID);
				if(undefined == data[0].TERMS_CONTENT){
					$("#termsContent").summernote('code','<p><br></p>');
				}else{
				$('#termsContent').summernote('code', data[0].TERMS_CONTENT);
					
				}
			}
		});
	}
	
	function doSave(){
		
		if (!confirm("저장하시겠습니까?")) return;
		
		//구분 선택
		if(!$.trim($("#termsDivnCd").val())) {
			alert("<spring:message code='app.manager.mgrTerms.alert.gubun'/>");
			$('#termsDivnCd').focus();
			return false;
		}
		//저장 버전 체크
		var insertInfo = {};
		
		$('#searchForm').find('input, select, textarea').map(function() {
			if(this.type != 'button')
				insertInfo[this.name] = $(this).val();
		});
			insertInfo["termsDivnCd"] = $('#termsDivnCd').val();
			
			$.ajax({
				contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
				url : '<c:url value = "/app/mgr/manager/mgrTerms_insert"/>',
				data : JSON.stringify(insertInfo),
				success : function(data){
					if(data.msgCd == "success"){
						alert("<spring:message code='app.manager.mgrTerms.alert.success' />");
						selectBoxChange();
					}
					else if(data.msgCd == "already"){
						alert("<spring:message code='app.manager.mgrTerms.alert.version' />");
						$("#termsVersionInfo").focus();
					}

				}
			});
		
	}
</script>
</head>

<body>
<div id = "section">
	<jsp:include page = "/WEB-INF/views/common/include/incPageTitle.jsp" />
	<div class = "tit_area">
		<h2><spring:message code="app.manager.mgrTerms.title" /></h2>
		<div>
			<spring:message code = "common.save" var = "save"/>
			<html:button id = "btnSave" name = "btnSave" auth = "save" objTitle = "${save }"/>
		</div>
	</div>
	
	<form id = "searchForm" name = "searchForm" method = "post">
			<!-- 약관 관리 내용 입력 start -->
			<table class = "type1">
				<caption></caption>
				<colgroup>
					<col width = "150">
					<col width = "*">
					<col width = "150">
					<col width = "*">
				</colgroup>
			<tbody>
				<tr>
					<th>* <spring:message code="app.manager.mgrTerms.gubun" /></th>
					<td>
						<spring:message code="common.select" var="select" /> <%--선택 --%>
						<html:codeTag comType="SELECT" objName="termsDivnCd"  width="50%;" objId="termsDivnCd" parentID="9012" defName='${select}' subCode2="Y"></html:codeTag>
					</td>
					<th><spring:message code="app.manager.mgrTerms.modId" /></th>
					<td>
						<input type = "text" name = "regId" id = "regId" title = "변경자" readonly style = "width: 30%"/>
					</td>
				</tr>
				<tr>
					<th>* <spring:message code="app.manager.mgrTerms.version" /></th>
					<td>
						<input type = "text" id = "termsVersionInfo" name = "termsVersionInfo" style = "width : 50%;"/>
					</td>
					<th><spring:message code="app.manager.mgrTerms.modDt" /></th>
					
					<td>
					
					<input type="text" name="regDtFmt" id="regDtFmt" title="등록일" readonly style="width:30%;"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="app.manager.mgrTerms.termsContent" /></th>
                    <td height="500%" colspan = "3">
                    	<textarea id="termsContent" name="termsContent" class="textarea" title="" style="height: 100%;"> </textarea>
                    </td>
				</tr>
			</tbody>
		</table>
		</form>
</div>
</body>