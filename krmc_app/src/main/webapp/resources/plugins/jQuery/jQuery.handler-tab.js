/* ajax global function */


$(document).ajaxError(function ajaxErrorHandler(event, xhr, ajaxOptions, thrownError) {

		var $dialog = $('#commonExceptionModalFade').dialog({
		    modal:true,
		    autoOpen: false,
		    resizable:false,
		    width: 500,
		    top:  50,
		    close: function() {
		        $(this).dialog('close'); //this is slow
		    }
		}); //init dialog




		$.unblockUI();
		GLOBAL_AJAX_EXCEPTION_STATUSCODE = xhr.status;
		console.log('GLOBAL_AJAX_EXCEPTION_STATUSCODE : ' + GLOBAL_AJAX_EXCEPTION_STATUSCODE);

/*
  			Interceptor 에서 not Session 에 걸린 경우
			- xhr.status :9999,  xhr.statusText: OK, ajaxOptions:Object, thrownError:OK
			그외 Exception
			- xhr.status:500,  xhr.statusText: Internal Server Error, ajaxOptions:Object, thrownError:Internal Server Error
*/

		console.log(xhr);

		console.log("ajaxOptions:"+ajaxOptions);
		console.log("thrownError:"+thrownError);

		var LOGIN_PAGE_URL = "/security/securityForm";

		// 그외 에러코드
		var eleHtml = [], h= -1;


		/* to-be*/
		$('#commonExceptionModalFade').empty();

		eleHtml[++h] = "<div class='modal-dialog' style=\"font-family: '나눔고딕',NanumGothic;  width:90%; border:none; padding:20px; top:30%; -webkit-border-radius:5px; -moz-border-radius:5px;\">";

		eleHtml[++h] = "<div class='modal-content'>	";
		eleHtml[++h] = "<div id='exception-head' class='exception-head'> ";
		eleHtml[++h] = "<h1  style='text-align:center; margin-bottom:20px;'>";
		eleHtml[++h] = "	<i class='fa fa-warning' style='color:#ca4e4e'></i> <strong style='color:#cf7961;'>"+xhr.status+"</strong>  ";
		eleHtml[++h] = "<label style='color:#808080; font-size:0.7em;' >";
		if(xhr.status =='200') eleHtml[++h] 		= "Access Time out";
		else if(xhr.status =='E901') eleHtml[++h]	= "Session Time out";
		else eleHtml[++h] = xhr.statusText;
		eleHtml[++h] = "</label>";

		eleHtml[++h] = "</h1> ";

		eleHtml[++h] = "<p style=\"font-family: 'Arial',NanumGothic; text-align:center; font-size:1.1em; color:#e61a23; font-weight:bold; margin-bottom:20px;\">";

		if(xhr.status =='200')  		eleHtml[++h] = "Data is delayed. please try again!.";
		else if(xhr.status =='E901') 	eleHtml[++h] = "User login time expired. Please login again!";
		else eleHtml[++h] = "An error has occurred in Data Processing.";
		eleHtml[++h] = "</p>";

		eleHtml[++h] = "	<div style=\"font-family: 'Arial',NanumGothic;  border-top: 1px solid #e5e5e5; width:100%; text-align:center;    line-height:20px;  font-size:9pt;color:#808080;\">";
		eleHtml[++h] = "	<i class='fa fa-clock-o'></i>The error will disappear within 1 minute</div> ";

		eleHtml[++h] = "</div>			";

		eleHtml[++h] = "</div> 			";
		eleHtml[++h] = "</div> 			";

		$('#commonExceptionModalFade').append(eleHtml.join(''));

		$dialog.dialog('open');

		setTimeout(function(){
			$dialog.dialog('close');    //this is slow
			//$.unblockUI();
			if(xhr.status =='E901') {
				if(window!= window.top){
					parent.document.location.href= LOGIN_PAGE_URL;
					self.close();
				}else  location.href = LOGIN_PAGE_URL;
			}
		},1000000);

});

$(document).ajaxStart(function() {
	//  console.log('[jquery.handler.js] ajaxStart!');
	GLOBAL_AJAX_EXCEPTION_STATUSCODE = '';
	$.blockUI({
			message: '<img src="/resources/images/common/bu_loading_ns.gif" width="100" height="70" />'

			, overlayCSS: { background: 'transparent'}
			, css:{background: 'transparent', border:'none'}
			, baseZ : 9999
	});
});

var GLOBAL_AJAX_EXCEPTION_STATUSCODE = '';

$(document).ajaxStop(function() {
//	console.log('[jquery.handler.js] ajaxStop!');
	if(GLOBAL_AJAX_EXCEPTION_STATUSCODE == '') {
		$.unblockUI();
	}
});



