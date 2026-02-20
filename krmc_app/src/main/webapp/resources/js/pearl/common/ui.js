/**
 * container aside, content resize function
 */ 
$(function(){
	$("#hideCal").click(function(){
		if($(this).hasClass("opened")){
			$('#calWrapp').slideUp(230, function() {
				$("#hideCal").removeClass("opened").addClass("closed");
			});
			$('#calSet').animate({marginBottom: '0'}, {duration: 230});
			return false;
		} else {
			$('#calWrapp').slideDown(230, function() {
				$("#hideCal").removeClass("closed").addClass("opened");
			});
			$('#calSet').animate({marginBottom: '15'}, {duration: 230});
			return false;
		}
	});
});

function uiRefresh(i){
	mainTabNum();
	//resizeTabBox();
	var docHeight = ($(document).height()) - 86;
	var docHeightF = ($(document).height()) - 30; 
	var asideDiv = $("#aside");
	var asideOver =$("#asideOver");
	if($("#btnFullView").hasClass("full")){
		asideDiv.height(docHeightF);
		//asideOver.height(docHeightF-30);
	}else{
		asideDiv.height(docHeight);
		//asideOver.height(docHeight-30);
	}
	//alert(docHeight);
}


//
$(function(){
	$("#btnFullView").click(function(){
		if($(this).hasClass("normal")){
			$("#header").slideUp(300, function(){
				uiRefresh(); 
			});
			$('#aside').animate({top: '0'}, {duration: 300});
			$('#container').animate({marginTop: '0'}, {duration: 300});
			$('#content').animate({paddingTop: '0'}, {duration: 300});
			if($("#hideLeft").hasClass("opened")){
				$("#hideLeft").trigger('click');
				
			}
			$('#content').animate({marginLeft: '0'}, {duration: 300});
			$(this).removeClass("normal").addClass("full");
			
		}else{
			$("#header").slideDown(300, function(){
				uiRefresh(); 
			});
			$('#aside').animate({top: '65'}, {duration: 300});
			$('#container').animate({marginTop: '-65'}, {duration: 300});
			$('#content').animate({paddingTop: '65'}, {duration: 300});
			if($("#hideLeft").hasClass("closed")){
				$("#hideLeft").trigger('click');
				
			}
			$('#content').animate({marginLeft: '220'}, {duration: 300});
			$(this).removeClass("full").addClass("normal");
		}
	});
 });


function resizeTabA(){
	var tabLength = $("#tabArea").find("li").length;	
	if(tabLength <= 5){
		$("#tabArea").width(525);
	}else if(tabLength > 5, tabLength <= 10){
		$("#tabArea").width(1050);
	}else if(tabLength > 10, tabLength <= 15){
		$("#tabArea").width(1575);
	}else if(tabLength > 15, tabLength <= 20){
		$("#tabArea").width(2100);
	}
}

//
function mainTabNum(){
	var tabLength = $("#tabArea").find("li").length;	
	//if(tabLength < 6){
	//	$("#btnTabsLeft").hide();
	//	$("#btnTabsRight").hide();	
	//	$("#btnVeiwAllTab").hide();	
	//}else{
	//	$("#btnTabsLeft").show();
	//	$("#btnTabsRight").show();	
	//	$("#btnVeiwAllTab").show();	
	//}
	//resizeTabA(); //
}
//
function viewTabAll(i){
	if(i == 1){
		$("#tabWrap").removeClass("rolling").addClass("spread");
		if( navigator.appName.indexOf("Microsoft") > -1 ){ //IE
			if( navigator.appVersion.indexOf("MSIE 6") > -1){ //IE6
				$("#tabOuter").height($("#tabOuter ul").height());
			}
			else if( navigator.appVersion.indexOf("MSIE 7") > -1){ //IE7
				$("#tabOuter").height($("#tabOuter ul").height());
			} else {
				$("#tabOuter").height($("#tabOuter ul").height() - 5);	
			}
		}else{
			$("#tabOuter").height($("#tabOuter ul").height() - 5);	
		}
		$("#tabArea").css("left","0");
		$("#tabArea").css("width","");
		$("#btnTabsLeft").attr("class","prev dis");
		$("#btnTabsRight").attr("class","next dis");
		$("#btnVeiwAllTab").hide();
	}else{
		$("#tabWrap").removeClass("spread").addClass("rolling");
		$("#tabOuter").height(20);
		$("#btnTabsLeft").attr("class","prev on");
		$("#btnTabsRight").attr("class","next off");
		$("#btnVeiwAllTab").show();
	}
}

//
function resizeTabBox(){
	//540
	//525
	var contWidth = $("# ").width();
	var btnsWidth = $("#rightBtns").width();
	var tabWidth = 105;
	var etcWidth = 70;
	var tabAreaWidth = contWidth -  btnsWidth - etcWidth - 15;
	var intTabCount = parseInt(tabAreaWidth / 105); 
	var tabAreaWidthR = intTabCount*tabWidth;
	$("#leftTabs").width(tabAreaWidthR + 15);
	$("#tabOuter").width(tabAreaWidthR);
}
 

//

function listSwipe(i){
	var tabLength = $("#tabArea").find("li").length;	
	var tabWrap = $("#tabArea");
	var tabOuterW = parseInt($("#tabOuter").width());
	var tabWrapLW = tabOuterW - (105*  tabLength);
	var tabWrapLWEnd = tabOuterW - (105*  (tabLength - 1));
	var tabWrapRWEnd = -105;
	var nowLeft = parseInt(tabWrap.css("left"));
	var moveLeft = nowLeft - 105;
	var moveRight = nowLeft +105;
	if(tabWrap.is(':animated')){
		return false;
	}else if($("#leftTabs a").hasClass("dis")){
		return false;
	}else{
		if(i == 0){
			if(tabWrapLW == nowLeft){
				return false;
			}else if(tabWrapLWEnd == nowLeft){
				tabWrap.animate({left: moveLeft }, 400);
				$("#btnTabsLeft").removeClass("on").addClass("off");
			}else{
				tabWrap.animate({left: moveLeft }, 400);
				$("#btnTabsRight").removeClass("off").addClass("on");
			}
		}else{
			if(nowLeft == 0){
				return false;
			}else if(tabWrapRWEnd == nowLeft){
				tabWrap.animate({left: moveRight }, 400);
				$("#btnTabsRight").removeClass("on").addClass("off");
			}else{
				tabWrap.animate({left: moveRight }, 400);
				$("#btnTabsLeft").removeClass("off").addClass("on");
			}
		}
	}
}

/* iframe 높이값 리사이즈 */
function resizeIframe($obj, intHeight) {
	$obj.height(intHeight);
}


