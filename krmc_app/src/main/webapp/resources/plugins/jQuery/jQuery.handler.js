/* ajax global function */
$(document).ajaxError(function ajaxErrorHandler(event, xhr, ajaxOptions, thrownError) {
	
	console.log('[jquery.handler.js] ajaxError!');
	
	$.unblockUI();
	GLOBAL_AJAX_EXCEPTION_STATUSCODE = xhr.status;
	//console.log('GLOBAL_AJAX_EXCEPTION_STATUSCODE : ' + GLOBAL_AJAX_EXCEPTION_STATUSCODE);

	/* Interceptor 에서  Session time-out 에 걸린 경우
		- xhr.status :9999,  xhr.statusText: OK, ajaxOptions:Object, thrownError:OK
		그외 Exception
		- xhr.status:500,  xhr.statusText: Internal Server Error, ajaxOptions:Object, thrownError:Internal Server Error
	*/
	/*
	console.log(xhr);
	console.log(ajaxOptions);
	console.log(thrownError);
	console.log(GLOBAL_AJAX_EXCEPTION_STATUSCODE);
	*/
	
		var LOGIN_PAGE_URL = "/";
	
	if(xhr.status == '303'){
		// 인터셉터에서 보낸 필수인 세션이 없다는 에러 코드 Code(9999)이면 로그인 화면으로 보낸다.
		if(window!= window.top){
			parent.document.location.href= '/';
			self.close();
		}else {
			location.href = '/';
		}
		return false;
	}else if(xhr.status == '0'){
		return true;
	}else{
		// 그외 에러코드 
		var eleHtml = [], h= -1;
		var statusNames = "";
		
		switch(xhr.status){
			case 200: statusNames = "OK"; 
			break;
			case 201: statusNames = "Created Successfully"; 
			break;
			case 202: statusNames = "Accepted";
			break
			case 203: statusNames = "Non-Authoritative Information";
			break
			case 204: statusNames = "No Content";
			break
			case 300: statusNames = "Multiple Choices";
			break
			case 301: statusNames = "Moved Permanently";
			break
			case 302: statusNames = "Moved Temporarily";
			break
			case 303: statusNames = "See Other";
			break
			case 304: statusNames = "Not Modified";
			break
			case 305: statusNames = "Use Proxy";
			break
			case 400: statusNames = "Bad Request";
			break
			case 401: statusNames = "Unauthorized";
			break
			case 403: statusNames ="Forbidden";
			break
			case 404: statusNames ="Not Found";
			break
			case 500: statusNames ="Internal Server Error";
			break
			default : statusNames = "System Error";
			break
		}

		eleHtml[++h] = "<div> ";
		eleHtml[++h] = "    <h2 style='font-size: 50px; font-weight: 400;color:#f39c12 !important;'>"+xhr.status+" </h2> ";
		eleHtml[++h] = "	<div style='font-size: 20px;'> ";
		eleHtml[++h] = "          <h3> ";
		eleHtml[++h] = "          	<i style='color:#f39c12 !important;'>"+statusNames+"</i>";
		eleHtml[++h] = "          	<p><small style='color: #dd4b39 !important;'> <i class='fa fa-cog'></i> 데이터 처리중에 오류가 발생하였습니다.</small></p> ";
		eleHtml[++h] = "          </h3> ";
		eleHtml[++h] = "     </div> ";
		eleHtml[++h] = "     <div style='margin-top:10px;'>해당 에러내용은 1분안에 사라집니다.</div> ";
		eleHtml[++h] = "     <div style='margin-top:10px;'> ";
		if(xhr.status =='403') {
			eleHtml[++h] = "     	<p> <a href='javascript:void(0);' id='btnHandler_Home'  class='btn btn-danger btn-xs'>CLOSE</a> </p> ";
		}else eleHtml[++h] = "     	<p> <a href='javascript:void(0);' id='btnHandler_Close' class='btn btn-danger btn-xs'>CLOSE</a> </p> ";
		eleHtml[++h] = "     </div> ";
		eleHtml[++h] = "</div> 	";

		$.blockUI({ message: eleHtml.join('')
						, css : {	  width: '500px'
									, height:'180px'
									, border: 'none'
									, padding: '40px'
									, top: '30%'
									, left: '30%'
									, '-webkit-border-radius': '5px'
							        , '-moz-border-radius': '5px'
							        , cursor: 'default'
						}
					//	,timeout:10000
				});
	
			setTimeout(function () {
				if(xhr.status =='403') {       
					if(window!= window.top){
						parent.document.location.href= LOGIN_PAGE_URL;
						self.close();
					}else  location.href = LOGIN_PAGE_URL;
				}else  $.unblockUI();
		    }, 10000);
		
			$('#btnHandler_Close').click(function(){ $.unblockUI();});
			
			$('#btnHandler_Home').click(function(){
				if(window!= window.top){
					parent.document.location.href= LOGIN_PAGE_URL;
					self.close();
				}else  location.href = LOGIN_PAGE_URL;
			});
		
		//return false;
	}
	//return true;
});
var GLOBAL_AJAX_EXCEPTION_STATUSCODE = '';

$(document).ajaxStart(function() {
	GLOBAL_AJAX_EXCEPTION_STATUSCODE = '';
	$.blockUI({
			message: '<img src="/resources/images/common/bx/bu_loading.gif"/>' 
			, overlayCSS: { background: 'transparent'}
			, css:{background: 'transparent', border:'none',zIndex:11000}
	});
});



$(document).ajaxStop(function() {
//	console.log('[jquery.handler.js] ajaxStop!');

	if(GLOBAL_AJAX_EXCEPTION_STATUSCODE == '') {
		$.unblockUI();
	}
	
});
