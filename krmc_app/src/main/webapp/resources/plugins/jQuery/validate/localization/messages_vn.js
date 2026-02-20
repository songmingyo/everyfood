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
	required: "<div class='validate'><i class='fa fa-info-circle'></i> Đây là trường bắt buộc.</div>",
	remote: "Vui lòng sửa hạng mục này.",
	email: "<div class='validate'><i class='fa fa-info-circle'></i> Vui lòng nhập địa chỉ email hợp lệ.</div>",
	url: "<div class='validate'><i class='fa fa-info-circle'></i> Vui lòng nhập đường link hợp lệ.</div>",
	date: "Vui lòng nhập ngày tháng hợp lệ.",
	dateISO: "Vui lòng nhập ngày tháng (ISO) hợp lệ.",
	number: "Vui lòng nhập số hợp lệ.",
	digits: "<div class='validate'><i class='fa fa-info-circle'></i> Bạn chỉ có thể nhập chữ số.</div>",
	creditcard: "Số thẻ tín dụng không hợp lệ.",
	equalTo: "<div class='validate'><i class='fa fa-info-circle'></i>Vui lòng nhập lại đúng giá trị.</div>",
	extension: "<div class='validate'><i class='fa fa-info-circle'></i> Đây không phải phần mở rộng hợp lệ.</div>",
	maxlength: $.validator.format( "<div class='validate'><i class='fa fa-info-circle'></i> Bạn không thể nhập vượt quá {0} ký tự.</div>" ),
	minlength: $.validator.format( "<div class='validate'><i class='fa fa-info-circle'></i> Vui lòng nhập tối thiểu {0} ký tự.</div>" ),
	rangelength: $.validator.format( "Vui lòng nhập giá trị có độ dài từ {0} đến {1} ký tự." ),
	range: $.validator.format( "Vui lòng nhập giá trị trong khoảng từ {0} đến {1}." ),
	max: $.validator.format( "Vui lòng nhập giá trị từ {0} trở xuống." ),
	min: $.validator.format( "Vui lòng nhập giá trị từ {0} trở lên." ),
	identifier: "<div class='validate'><i class='fa fa-info-circle'></i> Bạn chỉ có thể nhập chữ tiếng Anh và chữ số.</div>"
} );

}));