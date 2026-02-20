<%@ page language="java"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"	%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<!DOCTYPE HTML>
<html lang="en">
<head>
<!-- Force latest IE rendering engine or ChromeFrame if installed -->
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<meta charset="utf-8">
<title><spring:message code="app.common.ou.fileupload.label.title"/></title>
<meta name="description"
	content="File Upload widget with multiple file selection, drag&amp;drop support, progress bar and preview images for jQuery. Supports cross-domain, chunked and resumable file uploads. Works with any server-side platform (Google App Engine, PHP, Python, Ruby on Rails, Java, etc.) that supports standard HTML form file uploads.">
<meta name="viewport" content="width=device-width">
<!-- Bootstrap CSS Toolkit styles -->
<link rel="stylesheet" href="<c:url value='/resources/plugins/bootstrap/css/bootstrap.min.css'/>"/>
<!-- Bootstrap styles for responsive website layout, supporting different screen sizes -->
<link rel="stylesheet" href="<c:url value='/resources/plugins/bootstrap/css/bootstrap-responsive.min.css'/>"></link>
<!-- Bootstrap CSS fixes for IE6 -->
<!--[if lt IE 7]><link rel="stylesheet" href="/css/bootstrap/css/bootstrap-ie6.min.css"><![endif]-->
<!-- Bootstrap Image Gallery styles -->
<link rel="stylesheet" href="<c:url value='/resources/plugins/bootstrap/css/bootstrap-image-gallery.min.css'/>">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="<c:url value='/resources/plugins/blueimp/css/jquery.fileupload-ui.css'/>"></link>
<!-- CSS adjustments for browsers with JavaScript disabled -->
<noscript><link rel="stylesheet" href="<c:url value='/resources/plugins/blueimp/css/jquery.fileupload-ui-noscript.css'/>"></noscript>
<!-- Shim to make HTML5 elements usable in older Internet Explorer versions -->
<!--[if lt IE 9]><script src="/jquery/blueimp/html5.js"></script><![endif]-->
<style type="text/css">
body {
/*   font-family:'Malgun Gothic' !important; */
	font-family:Arial, sans-serif;
}
</style>
</head>
<body>

	<div class="container-fluid">
		<div class="page-header">
			<h3><spring:message code="app.common.ou.fileupload.label.title"/></h3><!-- 파일업로드 	-->
		</div>
		<blockquote>
			<p>
			<spring:message code="app.common.ou.fileupload.label.doc" 		var = "document"	/> <!-- 문서파일 	-->
			<spring:message code="app.common.ou.fileupload.label.img" 		var = "img"			/> <!-- 이미지파일 	-->
			<spring:message code="app.common.ou.fileupload.label.jasper" 	var = "jasper"		/> <!-- jasper파일	-->
			<spring:message code="app.common.ou.fileupload.label.vm" 		var = "vm"			/> <!-- vm파일	-->
			<spring:message code="app.common.ou.fileupload.label.pdf" 		var = "pdf"			/> <!-- pdf파일	-->
			<spring:message code="app.common.ou.fileupload.label.etc" 		var = "etc"			/> <!-- etc파일	-->
			<c:choose>
					<c:when test="${atchType eq 'doc' }">
						<spring:message code="app.common.ou.fileupload.label.n1"
						arguments = '<code>&lt; ${exceptLimit} &gt;</code>,<code> ${document}</code>' />
					</c:when>
					<c:when test="${atchType eq 'img' }"><code><spring:message code="app.common.ou.fileupload.label.img"/></code>
						<spring:message code="app.common.ou.fileupload.label.n1"
						arguments = '<code>&lt; ${exceptLimit} &gt;</code>,<code>${img}</code>' />
					</c:when>
					<c:when test="${atchType eq 'jasper' }"><code><spring:message code="app.common.ou.fileupload.label.jasper"/></code>
						<spring:message code="app.common.ou.fileupload.label.n1"
						arguments = '<code>&lt; ${exceptLimit} &gt;</code>,<code>${jasper}</code>' />
					</c:when>
					<c:when test="${atchType eq 'vm' }">
						<spring:message code="app.common.ou.fileupload.label.n1"
						arguments = '<code>&lt; ${exceptLimit} &gt;</code>,<code>${vm}</code>' />
					</c:when>
					<c:when test="${atchType eq 'pdf' }">
						<spring:message code="app.common.ou.fileupload.label.n1"
						arguments = '<code>&lt; ${exceptLimit} &gt;</code>,<code>${pdf}</code>' />
					</c:when>
					<c:otherwise>
						<spring:message code="app.common.ou.fileupload.label.n1"
						arguments = '<code>&lt; ${exceptLimit} &gt;</code>,<code>${document}</code> <code>${img}</code> <code>${etc}</code>' />
					</c:otherwise>
				</c:choose><!--첨부가능한 파일 수는 {0}이고 {1}만 가능합니다. 	-->
				<small><spring:message code="app.common.ou.fileupload.label.n4"/></small><!--자세한 파일 종류는 아래 내용을 참조하십시오.	-->
			</p>
		</blockquote>

		<br>
		<!-- The file upload form used as target for the file upload widget -->
		<form id="fileupload" action="<c:url value="/web/common/fileUploadSingle" />" method="post" enctype="multipart/form-data" style="margin:0 !important;">
		<sec:csrfInput/>
			<input type="hidden" id="atchFileId" name="atchFileId" value="${atchFileId }" />
			<input type="hidden" id="kindCd" name="kindCd" value="${kindCd }" />
			<input type="hidden" id="dirPath" name="dirPath" value="${dirPath }" />
			<input type="hidden" id="limitCount" name="limitCount" value="${limitCount }" />
			<input type="hidden" id="returnParam" name="returnParam" value="${returnParam }" />
			<input type="hidden" id="thumbnail" name="thumbnail" value="${thumbnail}" />
			<!-- Redirect browsers with JavaScript disabled to the origin page -->
			<noscript>
				<input type="hidden" name="redirect" value="http://localhost/" />
			</noscript>
			<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
			<p />
			<div class="row-fluid fileupload-buttonbar" style="padding-bottom:10px;">
				<div class="span8">
					<!-- The fileinput-button span is used to style the file input field as button -->
					<span class="btn btn-small btn-success fileinput-button" id="addbtn"> <i
						class="icon-plus icon-white"></i> <span><spring:message code="app.common.ou.fileupload.button.addfile"/></span><!--파일추가-->
						<input type="file" name="files" />
						<!-- <input type="file" name="files" multiple="multiple"  /> -->
					</span>
					<span id="reload" class="btn btn-small btn-warning" >
						<i class="icon-refresh icon-white"></i> <span><spring:message code="app.common.ou.fileupload.button.refresh"/></span><!--페이지 새로고침-->
					</span>
					<%-- <button  id="upoadbtn" name="uploadbtn" type="submit" class="btn btn-small btn-primary start">
						<i class="icon-upload icon-white"></i> <span><spring:message code="app.common.ou.fileupload.button.startupload"/></span><!--업로드 시작-->
					</button> --%>
					<%-- <button id="cancelbtn" name="cancelbtn" type="reset" class="btn btn-small btn-warning cancel" >
						<i class="icon-ban-circle icon-white"></i> <span><spring:message code="app.common.ou.fileupload.button.cancelupload"/></span><!--업로드 취소-->
					</button> --%>
					<%-- <button type="button" class="btn btn-small btn-danger delete" id="deletebtn" name="deletebtn">
						<i class="icon-trash icon-white"></i> <span><spring:message code="app.common.ou.fileupload.button.delfile"/></span><!--파일삭제-->
					</button> --%>

				</div>
				<%--
				<!-- The global progress information -->
				<div class="span5 fileupload-progress fade">
					<!-- The global progress bar -->
					<div class="progress progress-success progress-striped active"
						role="progressbar" aria-valuemin="0" aria-valuemax="100">
						<div class="bar" style="width: 0%;"></div>
					</div>
					<!-- The extended global progress information -->
					<div class="progress-extended">&nbsp;</div>
				</div>
				 --%>
			</div>
			<!--드래그 영역-->
			<div id="dropzone" class="dropzone">
				<div class="content">Drop files here</div>
				<input type="file" name="files" id="dragFile"multiple/>
			</div>
			<!-- The loading indicator is shown during file processing -->
			<div class="fileupload-loading"></div>
			<br>
			<!-- The table listing the files available for upload/download -->
			<table role="presentation" class="table table-striped" style="margin-bottom:0px !important;">
				<tbody class="files" data-toggle="modal-gallery"
					data-target="#modal-gallery"></tbody>
			</table>
		</form>
		<div class="row-fluid" style="text-align: right;">
			<div class="span3" style="float:right">
				<button type="button" class="btn btn-info" id="apply">
					<i class="icon-asterisk icon-white"></i> <span><spring:message code="app.common.ou.fileupload.button.ok"/></span>
					<!--확인-->
				</button>
				<button type="button" class="btn" id="close" >
					<i class="icon-remove"></i> <span><spring:message code="app.common.ou.fileupload.button.close"/></span>
					<!--닫기-->
				</button>
			</div>
		</div>
		<br/>
		<div class="well well-small">
			<h5><spring:message code="app.common.ou.fileupload.label.caution"/> <small><spring:message code="app.common.ou.fileupload.label.common"/></small></h5>
			<ul>
				<!--업로드 가능한 최대 파일용량은 {0}MB 이고, 파일 갯수는 최대 {1}개 까지입니다. (업무별로 1개로 제한될 수 있습니다.)-->
				<fmt:formatNumber var= "limitMaxSize" type="number" value= " ${limitMaxUploadSize / 1000000} " />
				<li><small><spring:message code="app.common.ou.fileupload.label.n2" arguments = '${limitMaxSize},${exceptLimit}' /></small></li>
				<!--첨부가능한 확장자는 ({0}) 입니다.-->
				<li><small><spring:message code="app.common.ou.fileupload.label.n3"
					arguments = "<strong>${fn:toUpperCase(fn:replace(acceptFileTypes,'|',' / ')) }</strong>" />
					</small></li>
				<!--응용프로그램 실행 파일, 동영상관련 및 미디어 파일은 제한됩니다.-->
				<li><small><spring:message code="app.common.ou.fileupload.label.n5"/></small></li>
				<!--업로드하시고 적용버튼을 꼭 클릭하여주십시오. 그전에 창을 닫으면 적용되지않습니다.-->
				<li><small><spring:message code="app.common.ou.fileupload.label.n6"/></small></li>
			</ul>
		</div>
	</div>
	<input type="hidden" id="sync" value="${sync }" />



	<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td class="name" colspan="2"><span>{%=file.name%} <small class="muted">{%=o.formatFileSize(file.size)%}</small></span></td>
        {% if (file.error) { %}
            <td class="error" colspan="2"><span class="label label-important">Error</span> {%=file.error%}</td>
        {% } else if (o.files.valid) { %} <!--다중으로 파일 추가 시 if(!i) 조건 있으면  버튼 표시 안됨-->
            <td>
                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
            </td>
            <td class="start" style="text-align: right;">{% if (!o.options.autoUpload) { %}
                <button class="btn btn-small btn-primary">
                    <i class="icon-upload icon-white"></i>
                    <span><spring:message code="app.common.ou.fileupload.button.startupload"/></span>
                </button>
            {% } %}</td>
        {% } else { %}
            <td colspan="2"></td>
        {% } %}
        <td class="cancel"> <!--다중으로 파일 추가 시 if(!i) 조건 있으면  버튼 표시 안됨-->
            <button class="btn btn-small btn-warning">
                <i class="icon-ban-circle icon-white"></i>
                <span><spring:message code="app.common.ou.fileupload.button.cancel"/></span>
            </button>
        </td>
    </tr>
{% } %}
</script>
	<!-- The template to display files available for download -->
	<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
	{% console.log(file);%}
    <tr class="template-download fade">
		<td class="name" colspan="2"><span>{%=file.name%} <small class="muted">{%=o.formatFileSize(file.size)%}</small></span></td>
        {% if (file.error) { %}
            <td class="error" colspan="2"><span class="label label-important">Error</span> {%=file.error%}</td>
        {% } else { %}
            <td colspan="2"></td>
        {% } %}
        <td class="delete">
            <button class="btn btn-small btn-danger" name="{%='delete_'+i%}"data-type="{%=file.delete_type%}" data-url="{%=file.delete_url%}"{% if (file.delete_with_credentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                <i class="icon-trash icon-white"></i>
                <span><spring:message code="app.common.ou.fileupload.button.delfile"/></span>
                <!--token값 없어서 삭제 동작 안됐었음-->
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </button>
			<input type="hidden" name="atchFileId" value="{%=file.atchFileId%}"/>
			<input type="hidden" name="seq" value="{%=file.seq%}"/>
			<input type="hidden" name="orgFileNm" value="{%=file.name%}"/>
        </td>
    </tr>
{% } %}
</script>



	 <script type="text/javascript" src="<c:url value='/resources/plugins/jQuery/jquery-3.6.0.min.js'/>"></script>
	<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
	<script type="text/javascript" src="<c:url value='/resources/plugins/blueimp/js/vendor/jquery.ui.widget.js'/>"></script>
	<!-- The Templates plugin is included to render the upload/download listings -->
	<script type="text/javascript" src="<c:url value='/resources/plugins/blueimp/tmpl.min.js'/>"></script>
	<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
	<script type="text/javascript" src="<c:url value='/resources/plugins/blueimp/load-image.min.js'/>"></script>
	<!-- The Canvas to Blob plugin is included for image resizing functionality -->
	<script type="text/javascript" src="<c:url value='/resources/plugins/blueimp/canvas-to-blob.min.js'/>"></script>
	<!-- Bootstrap JS and Bootstrap Image Gallery are not required, but included for the demo -->
	<script type="text/javascript" src="<c:url value='/resources/plugins/bootstrap/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/plugins/bootstrap/js/bootstrap-image-gallery.min.js'/>"></script>
	<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
	<script type="text/javascript" src="<c:url value='/resources/plugins/blueimp/js/jquery.iframe-transport.js'/>"></script>
	<!-- The basic File Upload plugin -->
	<script type="text/javascript" src="<c:url value='/resources/plugins/blueimp/js/jquery.fileupload.js'/>"></script>
	<!-- The File Upload file processing plugin -->
	<script type="text/javascript" src="<c:url value='/resources/plugins/blueimp/js/jquery.fileupload-fp.js'/>"></script>
	<!-- The File Upload user interface plugin -->
	<script type="text/javascript" src="<c:url value='/resources/plugins/blueimp/js/jquery.fileupload-ui.js'/>"></script>
	<script type="text/javascript">
	// 파일 드래그 해서 놓을 영역 드래그 동작 처리
	$(document).bind('dragover', function (e) {
	    var dropZone = $('#dropzone'),
	        timeout = window.dropZoneTimeout;
	    if (timeout) {
	        clearTimeout(timeout);
	    } else {
	        dropZone.addClass('in');
	    }
	})
	
	$(document).bind('drop', function (e) {
	    $('#dropzone').hide();
	});
	
// 	$('#addbtn').bind('click', function (e) {
	   
// 		$('#dragFile').remove();
// 	    $('#dropzone').hide();
// 	});
	
	$("#dragFile").bind('change',function(e){
		$('#dragFile').remove();
	    $('#dropzone').hide();
	});
    //새로고침버튼
	$('#reload').bind('click', function (e) {
		window.location.reload();
	});
	
	$(function () {

		window.opener._FILE_UPLOAD_POPUP_OPEN_ = true;

		$('#apply').click(function(e){
			if(window.opener == null
				|| typeof window.opener._FILE_UPLOAD_POPUP_OPEN_  == "undefined"
				|| window.opener._FILE_UPLOAD_POPUP_OPEN_ == false
				|| typeof window.opener._FILE_UPLOAD_POPUP_SYNC_ == "undefined"
				|| $('#sync').val() != window.opener._FILE_UPLOAD_POPUP_SYNC_){

				alert('<spring:message code="app.common.ou.fileupload.alert.a1"/>');<!--적용될 페이지가 초기화 되었거나 윈도우가 존재하지않습니다.-->
				return false;
			}
			var list = [];

			$('.template-download').each(function(index, element){
				var obj={};
				$(element).children('td:last-child').children('input[type="hidden"][value!=""]').each(function(i){
					obj[$(this).attr('name')] = $(this).val();
				});
				obj['gubunValue'] = $('#kindCd').val();
				var filechk = Object.keys(obj).length; // 리턴받아온 결과값 길이 --> 사이즈 0인 파일 업로드시 리턴값 gubunValue만 존재함
				
				if(filechk == 1){ // 리턴받아온 결과값 길이 --> 사이즈 0인 파일 업로드시 리턴값 gubunValue만 존재함
					// alert('<spring:message code="app.common.ou.fileupload.alert.a2"/>');<!--적용될 첨부파일이 업로드되지않았습니다.-->
					return false;
				}
				if($.isEmptyObject(obj) == false){
					list.push(obj);
				}
			});
			
			if(list.length > 0){ 
				window.opener._setFileUploadSendResult(list, $('#returnParam').val());
				window.close();
			}
			else{
				alert('<spring:message code="app.common.ou.fileupload.alert.a2"/>');<!--적용될 첨부파일이 업로드되지않았습니다.-->
				return false;
			}

		});

		$('#close').click(function(e){

			if($('.template-download, .template-upload').length > 0){
				if(confirm('<spring:message code="app.common.ou.fileupload.alert.a3"/>')){<!--업로드 파일 정보를 적용하지않고 닫으시겠습니까?-->
					window.close();
				}else{
					return false;
				}
			}else{
				window.close();
			}
		});

	/*
	$('#fileupload').bind('fileuploadprogress', function (e, data) {
	    // Log the current bitrate for this upload:
	});
	*/
    'use strict';

    // Initialize the jQuery File Upload widget:
    $('#fileupload').fileupload({
        // Uncomment the following to send cross-domain cookies:
        //xhrFields: {withCredentials: true},
		singleFileUploads: true,
		autoUpload: true, //자동업로드
        maxNumberOfFiles: <c:out value="${exceptLimit}"/>,
        maxFileSize:<c:out value="${limitMaxUploadSize}"/>,
        forceIframeTransport:true,
        sequentialUploads:true,
        dropZone: $('#dropzone'), // 파일 드래그 해서 놓을 영역
        acceptFileTypes:/(\.|\/)(<c:out value="${acceptFileTypes}"/>)$/i
    });

    // Enable iframe cross-domain access via redirect option:
    $('#fileupload').fileupload(
        'option',
        'redirect',
        window.location.href.replace(
            /\/[^\/]*$/,
            '/resources/plugins/blueimp/cors/result.html?%s'
        )
    );

    $('#fileupload').bind('fileuploadsubmit', function (e, data) {
        // The example input, doesn't have to be part of the upload form:

        data.formData = {atchFileId : $('#atchFileId').val(), kindCd : $('#kindCd').val(), dirPath : $('#dirPath').val(), limitCount : $('#limitCount').val(), thumbnail : $('#thumbnail').val()};

        if (!data.formData.atchFileId) {
        	alert('<spring:message code="app.common.ou.fileupload.alert.a4"/>');<!--초기화 되었습니다. 창을 닫고 다시 진행하여주십시오.-->
		return false;
        }

    });


	$('#fileupload').fileupload('option', {
	    url: '<c:url value="/web/common/fileUploadSingle?${_csrf.parameterName}=${_csrf.token}" />'
	});


});

</script>
<!-- The XDomainRequest Transport is included for cross-domain file deletion for IE8+ -->
<!--[if gte IE 8]><script src="/jquery/blueimp/js/cors/jquery.xdr-transport.js"></script><![endif]-->
</body>
</html>
