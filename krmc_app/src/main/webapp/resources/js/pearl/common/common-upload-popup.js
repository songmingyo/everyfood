
/**
 * 파일업로드 팝업 설정
 * _FILE_UPLOAD_POPUP_OPEN_ : 현재 팝업이 열려있는지 여부
 * _FILE_UPLOAD_POPUP_SYNC_: 작업의 동기화 번호
 * 문의 yjCho
 */

var _FILE_UPLOAD_POPUP_OPEN_ = false
, _FILE_UPLOAD_POPUP_SYNC_;


function _setUploadWindow(param){
	if(_FILE_UPLOAD_POPUP_OPEN_){
		if(!confirm('이미 파일업로드 작업이 실행되고있습니다. 계속 진행하시겠습니까?')){
			return false;
		}
	}

	/*
	limitCount : 1~, 값이 없으면 default(properties 정의)
	atchFileId : 수정인 경우 필수
	atchType : doc or img or etc (etc 인 경우 doc + img + 그외 zip 추가됨)
	kindCd : 업무접두사 영문 4자리
	dirPath : 저장디렉토리 값이 없으면 default(/)
	returnParam :
	callback function : _setFileUploadSendResult(list, returnParam); // 필수 작성 함수
	window Close callback function : _setFileUploadClose();		// 필수 작성 함수

	예) var param = 'limitCount=1&atchFileId=&atchType=doc&kindCd=ABCD&returnParam=abc';
	*/
	_FILE_UPLOAD_POPUP_SYNC_ = 'keit'+Math.floor(Math.random()*1000000);

	var url = __context + '/web/common/fileUpload?sync=' + _FILE_UPLOAD_POPUP_SYNC_ + '&' + param;

	$.popupWindow(url, {
	    height: 650,
	    width: 650,
	    toolbar: false,
	    scrollbars: false, // safari always adds scrollbars
	    status: false,
	    resizable: true,
	    left: 100,
	    top: 100,
	    center: false, // auto-center
	    createNew: true, // open a new window, or re-use existing popup
	    name: 'upload', // specify custom name for window (overrides createNew option)
	    location: false,
	    menubar: false,
	    onUnload: function() { // callback when window closes
	    	_setUploadWindowOnUnload();
	    }
	});
}

/* Popup Close Event(auto, none argument) */
function _setUploadWindowOnUnload(){
	_FILE_UPLOAD_POPUP_OPEN_ = false;
	if (typeof _setFileUploadClose == 'function') {
		_setFileUploadClose();
	}

}
