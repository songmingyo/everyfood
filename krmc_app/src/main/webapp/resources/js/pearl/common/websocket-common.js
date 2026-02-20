/**
 * webSocket script
 */

var wsUri = document.getElementById("websockethome").value;

function send_message() {
	
	websocket = new SockJS(wsUri);
	websocket.onopen = function(evt) {
		onOpen(evt)
	};
    websocket.onmessage = function(evt) {
        onMessage(evt)
    };
    websocket.onerror = function(evt) {
        onError(evt)
    };
}
   
function onOpen(evt) {
	writeToScreen("연결완료");
   	doSend("소켓 통신 테스트 메시지");	
}

function onClose(evt) {
	writeToScreen("연결해제");
	websocket.close();
}

function onMessage(evt) {
	//console.log(evt.currentTarget['url']); => 소켓 통신 url 리턴
	//console.log(evt);
    writeToScreen("수신: " + evt.data);
    
    /*IO EXCEPTION 방지*/
    onClose(evt); 
}

function onError(evt) {
	writeToScreen("에러: " + evt);
	onClose(evt);
}

function doSend(message) {
	writeToScreen("발신: " + message);
    websocket.send(message);
}

function writeToScreen(message) {
	console.log(message);
}
