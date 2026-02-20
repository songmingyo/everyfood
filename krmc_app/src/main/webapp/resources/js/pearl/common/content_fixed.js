
/**
 * container aside, content resize function
 */ 
$(function(){
	var timeout,delay = 260, numDefault = 230;
	
	$("#hideLeft").click(function(){
		if($(this).hasClass("opened")){
			//$('#aside').animate({left: - 126}, {duration: 220});
			//$('#content').css('max-width','1280px');
			$('#aside').css('width','50px');
			$('#content').animate({
				marginLeft: '50'
			}, {
				duration: 260,
				complete: function() {
					$("#hideLeft").removeClass("opened").addClass("closed");
					//$("#hideLeft").animate({right: '-20'}, {duration: 220});
					__setTriggerWindow();
				}
			});
			$('#aside').attr("class","closed_aside");
			$('#topMenuBar').animate({'margin-left':'-=80px'},{duration: 220});
			//$('#menu_tree').css("display","none");
			$("#lm_pc").addClass("lmClose");
			return false;
		} else {
			$('#aside').css('width','230px');
			//$('#aside').animate({left: '0'}, {duration: 260});
			$('#content').animate({
				marginLeft: numDefault
			}, {
				duration: 260,
				complete: function() {
					$("#hideLeft").removeClass("closed").addClass("opened");
					//$("#hideLeft").animate({right: '0'}, {duration: 220});
					__setTriggerWindow();
				}
			});
			$('#aside').attr("class","opened_aside");
			//$('#content').css('max-width','1080px');
			$('#menu_tree').css("display","");
			
			$('#topMenuBar').animate({'margin-left':'+=80px'},{duration: 220}); 
			//$('#hideLeft').removeClass("active");
			$("#lm_pc").removeClass("lmClose");
			
			return false;
		}
	});

	$('#asideOver').bind('mouseleave', function() {
		if($(this).hasClass("active")){
			if (timeout) window.clearTimeout(timeout);
			timeout = window.setTimeout(function(){
				$('#aside').stop().animate({width: numDefault}, {duration: 200});
			}, delay);
		}else{
			return;
		}
	});
});



function __setTriggerWindow(){

	$(window).trigger("resize");
}
