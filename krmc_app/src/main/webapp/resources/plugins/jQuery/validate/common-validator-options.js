/**
 * 공통적용 Validate 추가적인 설정 및 Method 추가
 * Validate 사용하는 경우만 include
 */

$(document).ready(function(){

	// 입력값 체크 설정
	$.validator.setDefaults({
		validClass: "success",  //유효성 체크에 성공한 값들의 클래스이름을 설정
		//onfocusout: "false"
	});
	
	// 영문 + 숫자 조합 체크
	$.validator.addMethod('identifier', function(value, element) {
		return 	this.optional(element) || value.length > 0 && 
		 (value.match(/^[a-zA-Z0-9]{2,6}$/)); })
		
	
});