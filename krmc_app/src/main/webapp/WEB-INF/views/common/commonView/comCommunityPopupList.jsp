<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" 	uri="http://www.springframework.org/tags"%>

<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

	_init();

});

function _init(){
	var searchInfo = {};
	searchInfo['alertYn'] = "Y";
	searchInfo['boardCd'] = "001";

	$.ajax({
		 contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	      url  : '<c:url value="/web/common/inc/communityPopupList"/>',
	      data : JSON.stringify(searchInfo),
	      success : function(data){
	    	  if(data != null){
	    		  	var zoneW = '780';
	    		    var zoneH = '500';

	    			var list = data;

	    			var x = 0;
	    			var y = 0;

	    			for(var i=0; i<list.length; i++){

		    		    var html = "";
						html += "<div id=\"info_process_layer_"+list[i].boardIdx+"\"" + "style=\"display: none; padding:5px\">";
						html += "<div id=\"communityContent\" class=\"zoneLayerStyle_content_body\" style=\"overflow:auto;weight:"+Number(zoneW-100)+"px;height:"+Number(zoneH-100)+"px;border:1px solid #dae2e6; background:#f8f8f8; padding:10px\">";
						html += "<span id=\"popCn"+list[i].boardIdx+"\" ></span>";
						html += "</div>";
						html += "<div style='width:100%;background: #f8f8f8; float:right;text-align:right;margin-top:5px;border:#dae2e6 1px solid;'>";
						html += "<span style='cursor: pointer;font-size:0.75em;margin-top: 2px;margin-left:10px; float: right; color: #6256be;' onclick=\"dayClose('"+list[i].boardIdx+"')\"><spring:message code='app.community.popup.dayClose'/>&nbsp;</span>";
						html += "</div>";
						html += "</div>"

	    				$('#communityPopup').html(html);
	    				var postionX = 'center+'+x;
	    				var postionY = 'center+'+y;
	    				var cookie = unescape(getCookie(list[i].boardIdx)).split("/");
	    				var showHide=false;
	    				for(var j=0; j<cookie.length; j++){
	    					if(cookie[j]==list[i].boardIdx) showHide=true;
	    				}

	    				if(!showHide){
	    					var randomPos = "center" + (Math.random() * 10 < 5 ? "-" : "+") + Math.random() * 100 + " " + "left" + (Math.random() * 10 < 5 ? "-" : "+") + Math.random() * 100;
		    				$( '#info_process_layer_'+list[i].boardIdx ).dialog({
		    					title : list[i].boardTitle,   	//--- 팝업 타이틀에 나타나는 문구 설정
		    					width : zoneW,      		//---넓이 설정
		    					maxWidth : zoneW+100,    	//--- 최대 넓이 설정
		    					modal : false,    			//--- 레이어 팝업을 뜰 때 부모창을 비활성화 설정.
		    					resizable : false,   		//--- 사이즈 조절 불가 설정 maxWidth나 maxHeight가 쓸모없어짐.
		    					position : {my:"center", at:randomPos, of:window},
		    					dragStart : function(){     //--- 이벤트 옵션 설정. 드래그가 시작되면 호출...
		    			         }
		    			    });
		    				$('#info_process_layer_'+list[i].boardIdx+" #zoneNm"+list[i].boardIdx).html(list[i].boardTitle);
		    	          	$('#info_process_layer_'+list[i].boardIdx+" #popCn"+list[i].boardIdx).html(list[i].boardContent);
		    	          	x+=10;
		    	          	y+=17;
		    	          	$("#info_process_layer_"+list[i].boardIdx).parent().css({
		    	          		"z-index" : 99999999,
		    	          		"padding" : "0px",
		    	          		"border"  : "none"
		    	          	});
		    	          	$("#info_process_layer_"+list[i].boardIdx).parent().children().eq(0).css({
		    	          		"border-radius" : "unset",
		    	          		"border"		: "none",
		    	          		"background"	: "#025a77"
		    	          	});
		    	          	$("#info_process_layer_"+list[i].boardIdx).parent().children().eq(0).find("button").css({
		    	          		"background"	: "#025a77",
		    	          		"border"		: "none"
		    	          	});
		    	          	$("#info_process_layer_"+list[i].boardIdx).children().eq(0).find("img").css({
		    	          		"max-width"	: "100%",
		    	          		"height"	: "auto"
		    	          	});
		    	          	$("#info_process_layer_"+list[i].boardIdx).parent().children().eq(0).find("button").css({
		    	          		"outline"  	: "none",
		    	          		"box-shadow": "none",
		    	          		"-webkit-box-shadow":"none"
		    	          	});
		    	          	$("#info_process_layer_"+list[i].boardIdx).parent().children().eq(0).find("button").children().eq(0).remove();
		    	          	var font = "<i class=\"fa fa-times\" style=\"color:white;\"></i>";
		    	          	$("#info_process_layer_"+list[i].boardIdx).parent().children().eq(0).find("button").prepend(font);
	    				}
	    			}
	    	  }
	      }
	});
}

function dayClose(idx){

	var cookieIdx;
	var cidx;
	cookieIdx = unescape(getCookie(idx));
	cidx = cookieIdx+"/"+idx;
	setCookie(idx,cidx,1);

	$('#info_process_layer_'+idx).dialog("close");
}

function setCookie( name, value, expiredays )
{
	 var todayDate = new Date();
     todayDate.setDate(todayDate.getDate() + expiredays);
     todayDate = new Date(todayDate.getFullYear(),todayDate.getMonth(),todayDate.getDate());
     document.cookie = name+"="+escape(value)+"; path=/;expires="+todayDate.toGMTString()+";";
}

function getCookie(name){

	var idx = [];
	var cookie = document.cookie.split("; ");
    for(var i=0; i<cookie.length; i++){
            if(name == cookie[i].split("=")[0]){
				return cookie[i].split("=")[1];
            }
    }
    return "";

}

</script>


<div id='communityPopup'></div>

