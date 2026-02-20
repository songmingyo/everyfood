
$(function(){
	var timeout, delay = 230;	
	var numDefault = 200;
	var expNum = 200;

	
	$("#hideLeft").click(function(){
		
		if($(this).hasClass("opened"))
		{
			$('#aside').animate({left: - numDefault}, {duration: 220});
			$('#content').animate({
				marginLeft: '0'
			}, {
				duration: 260,
				complete: function() {
					$("#hideLeft").removeClass("opened").addClass("closed");
					$("#hideLeft").animate({right: '-20'}, {duration: 220});
					__setTriggerWindow();
				}
			});
			$('#aside').attr("class","closed_aside");
			$('#topMenuBar').animate({'margin-left':'-=80px'},{duration: 220}); 
			
			
			return false;
		}
		else 
		{
			$('#aside').animate({left: '0'}, {duration: 220});
			$('#content').animate({
				marginLeft: numDefault
			}, {
				duration: 260,
				complete: function() {
					$("#hideLeft").removeClass("closed").addClass("opened");
					$("#hideLeft").animate({right: '0'}, {duration: 220});
					__setTriggerWindow();
				}
			});
			$('#aside').attr("class","opened_aside");
			$('#topMenuBar').animate({'margin-left':'+=80px'},{duration: 220});
			
			return false;
		}
		
		

	});


	$('#asideOver').bind('mouseleave', function() {
		if($(this).hasClass("active")){
			if (timeout) window.clearTimeout(timeout);
			timeout = window.setTimeout(function(){
				$('#aside').stop().animate({width: numDefault}, {duration: 200});
			}, delay);
		}else return;
		
		
	});
	
});



function __setTriggerWindow(){
	$(window).trigger("resize");
}