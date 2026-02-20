(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define( ["jquery", "../jquery.validate"], factory );
	} else if (typeof module === "object" && module.exports) {
		module.exports = factory( require( "jquery" ) );
	} else {
		factory( jQuery );
	}
}(function( $ ) {

/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: EN (English; 영어)
 */
$.extend( $.validator.messages, {
	required: "<div class='validate'><i class='fa fa-info-circle'></i> This field is required.</div>",
	remote: "Please fix this field.",
	email: "<div class='validate'><i class='fa fa-info-circle'></i> Please enter a valid email address.</div>",
	url: "<div class='validate'><i class='fa fa-info-circle'></i> Please enter a valid URL.</div>",
	date: "Please enter a valid date.",
	dateISO: "Please enter a valid date ( ISO ).",
	number: "Please enter a valid number.",
	digits: "<div class='validate'><i class='fa fa-info-circle'></i> Please enter only digits.</div>",
	creditcard: "credit card number is incorrect.",
	equalTo: "<div class='validate'><i class='fa fa-info-circle'></i>Please enter the same value again.</div>",
	extension: "<div class='validate'><i class='fa fa-info-circle'></i> It is not a valid extension.</div>",
	maxlength: $.validator.format( "<div class='validate'><i class='fa fa-info-circle'></i> Please enter no more than {0} characters.</div>" ),
	minlength: $.validator.format( "<div class='validate'><i class='fa fa-info-circle'></i> Please enter at least {0} characters.</div>" ),
	rangelength: $.validator.format( "Please enter a value between {0} and {1} characters long." ),
	range: $.validator.format( "Please enter a value between {0} and {1}." ),
	max: $.validator.format( "Please enter a value less than or equal to {0}." ),
	min: $.validator.format( "Please enter a value greater than or equal to {0}." ),
	identifier: "<div class='validate'><i class='fa fa-info-circle'></i> Only English and numbers can be entered.</div>"
} );

}));