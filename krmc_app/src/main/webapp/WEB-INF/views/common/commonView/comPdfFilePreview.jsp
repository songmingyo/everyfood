<%@ page language="java"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>
<title>Preview PDF</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/pearl/base.css"/>" />
<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jquery-3.6.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/plugins/jQuery/jQuery-ui-1.12.1.js"/>"></script>
  
<script type="text/javascript">

$(document).ready(function($){
	try {
		opener.parent.chkChild(); // 부모창 함수 호출
	} catch (e) {
		parent.chkChild2(); // 부모창 함수 호출
	}
});

// PDF 조회(VM to PDF)
function eventSearchPdfFilePdfView(pdfType, workType, KeyValue, fileName) {

	var file = "<c:url value='/app/common/"+pdfType+"_"+workType+"_"+KeyValue+"/loadPdfCommonView'/>";
	
	var f = document.formPdfView;

	$('#formPdfView #file').val(file+"?"+fileName+".pdf");
	
	f.action = "<c:url value='/resources/plugins/pdfjs/web/viewer.html'/>";
	f.target = "DocIfrm";
	f.submit();
	f.target = "";
}

// PDF 조회(pdf 그대로 조회)
function eventSearchPdfFileImageView(atchFileId, atchSeq, fileName) {

	var file = "<c:url value='/app/common/"+atchFileId+"_"+atchSeq+"/loadImageCommon'/>";
	
	var f = document.formPdfView;

	$('#formPdfView #file').val(file+"?"+fileName+".pdf");
	
	f.action = "<c:url value='/resources/plugins/pdfjs/web/viewer.html'/>";
	f.target = "DocIfrm";
	f.submit();
	f.target = "";
}
</script>

</head>
<body>
	<div id="section" style="width:100%; height:100%;">
		<div id="pop_pdfView" style="height: 100%;">
			<iframe id="DocIfrm" name="DocIfrm" width="99.5%" height="99.5%" style="overflow:scroll;border:none;"></iframe>
		</div>
		
		<form id="formPdfView" name="formPdfView">
			<sec:csrfInput/>
			<input type="hidden" id="file" 	name="file"/>
		</form>
		
		<input type="hidden" id="pdfType" name="pdfType" />
		<input type="hidden" id="workType" name="workType" />
		<input type="hidden" id="keyValue" name="keyValue" />
	</div>
</body>
</html>