/**
 * DATEPICKER 공통에서 복사해서  카피됨
 */

jQuery(function($){
	$.datepicker.regional['en'] = {
		closeText: 'done',
		prevText: 'Prev',
		nextText: 'Next',
		currentText: "Today", // Display text for current month link
		monthNames: ["January","February","March","April","May","June",
			"July","August","September","October","November","December"], // Names of months for drop-down and formatting
		monthNamesShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"], // For formatting
		dayNames: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"], // For formatting
		dayNamesShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"], // For formatting
		dayNamesMin: ["Su","Mo","Tu","We","Th","Fr","Sa"], // Column headings for days starting at Sunday
		weekHeader: "Wk", // Column header for week of the year
		dateFormat: "dd-mm-yy", // See format options on parseDate
		firstDay: 0, // The first day of the week, Sun = 0, Mon = 1, ...
		isRTL: false,
		showMonthAfterYear: true,
		yearSuffix: '',
	
        autoSize:false,	
		changeMonth:true,
		changeYear:true,
		
		showButtonPanel: true,
		showOn: "button",
		buttonImage: "/resources/plugins/jsn/images/jsn/calendar.png",
		buttonImageOnly: true,
		
	};
	$.datepicker.setDefaults($.datepicker.regional['en']);
	 
});

