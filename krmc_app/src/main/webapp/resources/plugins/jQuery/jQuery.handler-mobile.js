var GLOBAL_AJAX_EXCEPTION_STATUSCODE = '';

$(document).ajaxError(function ajaxErrorHandler(event, xhr, ajaxOptions, thrownError) {
		
	$.unblockUI();
	alert("처리중 오류가 발생하였습니다.");
	
	GLOBAL_AJAX_EXCEPTION_STATUSCODE = xhr.status;
	console.log('GLOBAL_AJAX_EXCEPTION_STATUSCODE : ' + GLOBAL_AJAX_EXCEPTION_STATUSCODE);
	
	console.log(xhr);
	console.log("ajaxOptions:"+ajaxOptions);
	console.log("thrownError:"+thrownError);
});


$(document).ajaxStart(function() {
	GLOBAL_AJAX_EXCEPTION_STATUSCODE = '';
	$.blockUI({
			message: '<img src="/resources/images/common/bu_loading_ns.gif" width="100" height="70" />'

			, overlayCSS: { background: 'transparent'}
			, css:{background: 'transparent', border:'none'}
			, baseZ : 9999
	});
});


$(document).ajaxStop(function() {
	if(GLOBAL_AJAX_EXCEPTION_STATUSCODE == '') {
		$.unblockUI();
	}
});