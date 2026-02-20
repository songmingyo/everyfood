/**
	fileName : commonGridFmtUtilNew.js
	Description : jqGrid 공통 Fomatter/Unformat Util
	Author 	: yja
	Since 	: 2022.08
*/

/*
 * input box Formatter
 */
function fmtSetGridInput(cellvalue, options, rowObject){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : escapeXml(cellvalue);
	var retData = "";
	retData = "<input type='text' id='"+colNm+"_"+rowId+"' name='"+colNm+"' value='"+cellVal+"' style='width:100%'/>";
	
	return retData;
}

/*
 * input box Formatter
 * - availDataLen : 입력 가능한 최대 byte 수
 */
function fmtSetGridInputLen(cellvalue, options, rowObject, availDataLen,phText){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : escapeXml(cellvalue);
	var retData = "";
	if($.trim(phText)!=""){
		retData = "<input type='text' id='"+colNm+"_"+rowId+"' name='"+colNm+"' value='"+cellVal+"' style='width:100%' onkeyup='chkByte(this,"+availDataLen+")' placeholder='"+phText+"'/>";
	}else{
		retData = "<input type='text' id='"+colNm+"_"+rowId+"' name='"+colNm+"' value='"+cellVal+"' style='width:100%' onkeyup='chkByte(this,"+availDataLen+")'/>";		
	}
	
	return retData;
}

//escape xml entity
function escapeXml(unsafe) {
	    if($.trim(unsafe)!=""){
		 return unsafe.replace(/[<>&\"\'/]/g,
		    function (c) {
		        switch (c) {
		            case '<': return '&lt;';
		            case '>': return '&gt;';
		            case '&': return '&amp;';
		            case '\'': return '&apos;';
		            case '"': return '&quot;';
		            case '/': return '&#47;';
		        }
		    });
		}

	}

/*
 * 인풋박스 Unformat
 */
function unfmtGetGridInput(cellvalue, options, cell){
	var retData = $.trim($('input', cell).val());
	return retData;
}

/*
 * 날짜 형식(데이트피커) input Formatter
 */
function fmtSetGridDatePicker(cellvalue, options, rowObject){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : $.trim(cellvalue).replace(/[^0-9]/g, "").replace(/(\d{4})(\d{2})(\d{2})/g,"$1-$2-$3");
	var retData = "<input type='text' id='"+colNm+"_"+rowId+"' name='"+colNm+"' class='dy' value='"+cellVal+"' style='width:85px' maxlength='8' onblur='fncSetDateFormatVailed(this)' oninput='this.value = this.value.replace(/[^0-9]/g, \"\")' onfocus='fncRemainOnlyNumber(this)'/>";
	
	return retData;
}

/*
 * 날짜 형식 Unformat
 */
function unfmtGetGridDatePicker(cellvalue, options, cell){
	var retData = $.trim($('input', cell).val()).replace(/[^0-9]/g,"");
	
	//데이터 길이가 8자리(20220101)가 아니면 빈값 return 
	if(retData.length != 8){
		return "";
	}
	
	return retData;
}

/*
 * 시간 형식 (24시간) input Formatter
 */
function fmtSetGridInputTm24(cellvalue, options, rowObject){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : $.trim(cellvalue).replace(/[^0-9]/g,"");
	var retData = "";
		
	var hour, min;
	
	if(cellVal == "" || cellVal.length < 4){
		hour = "";
		min = "";
	}else{
		hour = cellVal.substring(0,2);
		min = cellVal.substring(2,4);
	}
	
	retData = "<input type='text' id='"+colNm+"HH_"+rowId+"' name='"+colNm+"HH' value='"+hour+"' onblur='lpadForKeyup(this, \"2\", \"0\")' style='width:45%' maxlength='2' oninput='this.value = this.value.replace(/[^0-9]/g,\"\")' onkeyup='fncChkTmForKeyup(this,\"H\")'/>";
	retData += " : ";
	retData += "<input type='text' id='"+colNm+"MM_"+rowId+"' name='"+colNm+"MM' value='"+min+"' onblur='lpadForKeyup(this, \"2\", \"0\")' style='width:45%' maxlength='2' oninput='this.value = this.value.replace(/[^0-9]/g,\"\")' onkeyup='fncChkTmForKeyup(this, \"M\")'/>";
	
	return retData;
}

/*
 * 시간형식 (12시간) input Formatter
 */
function fmtSetGridInputTm12(cellvalue, options, rowObject){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : $.trim(cellvalue).replace(/[^0-9]/g,"");
	var retData = "";
		
	var hour, min;
	
	if(cellVal == "" || cellVal.length < 4){
		hour = "";
		min = "";
	}else{
		hour = cellVal.substring(0,2);
		min = cellVal.substring(2,4);
	}
	
	retData = "<input type='text' id='"+colNm+"HH_"+rowId+"' name='"+colNm+"HH' value='"+hour+"' style='width:45%' maxlength='2' onblur='lpadForKeyup(this, \"2\", \"0\")' oninput='this.value = this.value' onkeyup='fncChkTmForKeyup(this,\"12H\")'/>";
	retData += " : ";
	retData += "<input type='text' id='"+colNm+"MM_"+rowId+"' name='"+colNm+"MM' value='"+min+"' style='width:45%' maxlength='2' onblur='lpadForKeyup(this, \"2\", \"0\")' oninput='this.value = this.value' onkeyup='fncChkTmForKeyup(this, \"M\")'/>";
	
	return retData;
}

/*
 * 시간형식 Unformat
 */
function unfmtGetGridInputTm(cellvalue, options, cell){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	var colNm = $.trim(options.colModel.name);
	var retData = "";
	
	var hour = $.trim($('input[name="'+colNm+'HH"]', cell).val()).replace(/[^0-9]/g,"");
	var min	= $.trim($('input[name="'+colNm+'MM"]', cell).val()).replace(/[^0-9]/g,"");
	
	retData = hour+min;

	retData = ($.trim(retData) == "" || $.trim(retData).length != 4)? "":retData+"0000";
	
	return retData;
}

/*
 * 시간형식 text Formatter
 */
function fmtSetGridTextTm(cellvalue, options, rowObject){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : $.trim(cellvalue).replace(/[^0-9]/g,"");
	var retData = "";
	
	var hour, min;
	if(cellVal == "" || cellVal.length < 4){
		hour = "";
		min = "";
	}else{
		hour = cellVal.substring(0,2);
		min = cellVal.substring(2,4);
	}
	
	if(hour && min){
		retData = hour + ":" + min;
	}
	
	return retData;
}

/*
 * 셀렉트 박스(with codelist) Formatter
 * - codeList : 셀렉트 박스 구성용 codeList data (필수)
 */
function fmtSetGridSelectBox(cellvalue, options, rowObject, codeList){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : cellvalue;
	var retData = "";
	
	//selectBox 구성
 	retData = getFmtGridSelectBoxFrStrCodList(colNm,"선택", cellVal, codeList);
	
	return retData;
}

/*
 * 셀렉트 박스(common) Formatter
 * - 코드리스트 없는 selectBox 구성
 * - gbnType 필수. 필요시 추가하여 사용.
 */
function fmtSetGridCommonSelectBox(cellvalue, options, rowObject, gbnType){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : cellvalue;
	var retData = "";
	
	var selectedData = "value='"+cellVal+"'";
	
	if(gbnType){
		gbnType = $.trim(gbnType).toLowerCase();
		
		if($.trim(gbnType) == 'yn'){
			retData = "<select id='"+colNm+"' name='"+colNm+"' class='form-control'>";
			retData += "<option value=''>선택</option>";
			retData += "<option value='Y'>Y</option>";
			retData += "<option value='N'>N</option>";
			retData += "</select>";
		}else{
			retData = "<select id='"+colNm+"' name='"+colNm+"' class='form-control'>";
			retData += "<option value=''>선택</option>";
			retData += "</select>";
		}
		
		var position = retData.search(selectedData) + selectedData.length;
		retData = [retData.slice(0, position)," selected", retData.slice(position)].join('');
		
	}else{
		retData = "<select id='"+colNm+"' name='"+colNm+"' class='form-control'>";
		retData += "<option value=''>선택</option>";
		retData += "</select>";
	}
	
	return retData;
}


/*
 * 셀렉트 박스 Unformat
 */
function unfmtGetGridSelectBox(cellvalue, options, cell){
	var retData = $.trim($('select option:selected', cell).val());
	return retData;
}

/*
 * 전화번호 input Formatter
 */
function fmtSetGridInputTelNo(cellvalue, options, rowObject){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : cellvalue;
	var retData = "";
	
	retData = "<input type='text' id='"+colNm+"_"+rowId+"' name='"+colNm+"' value='"+cellVal+"' style='width:100%' oninput='fncSetDefInputHpNo(this);' placeholder='010-1234-5678' maxlength='13'/>";
	
	return retData;
}

/*
 * input 숫자형 Unformat
 * - data 없을 경우, 공백반환
 * - cf. 금액 input unformat (data 없을 경우, 0 반환)
 */
function unfmtGetGridInputNum(cellvalue, options, cell){
	var retData = $.trim($('input', cell).val()).replace(/[^0-9]/g,"");
	return retData;
}

/*
 * 이메일 input Formatter
 * - emailBackCdList : 이메일 뒷자리 코드리스트 (필수데이터)
 */
 function fmtSetGridInputEmail(cellvalue, options, rowObject, emailBackCdList){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : cellvalue;
	var retData = "";
	
	var email = cellVal.split("@");
	var emailFront = $.trim(email[0]);
	var emailBackCd = $.trim(email[1]);
	
	var retDataFr = "<input type='text' name='emailFront' value='"+emailFront+"' style='width:45%' onkeyup='chkByte(this, 80)'/>";
	var retDataEd = getFmtGridSelectBoxFrStrCodListForEmail("선택", emailBackCd, emailBackCdList);
	
	retData = retDataFr+" @ "+retDataEd;
	return retData;
}

/*
 * 이메일 Unformat
 */
function unfmtGetGridInputEmail(cellvalue, options, cell){
	var emailFront = $.trim($('input[name="emailFront"]', cell).val());
	var emailBack = $.trim($('select[name="emailBackCd"] option:selected', cell).text());

	var retData = emailFront + "@" + emailBack;	
	return retData;
}

/*
 * 금액 input Formatter
 */
function fmtSetGridInputAmt(cellvalue, options, rowObject){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : cellvalue;
	var retData = "";
	
	cellVal = Number(cellVal).toFixed(0);
	cellVal = numberComma(cellVal.toString());
	
	retData = "<input type='text' id='"+colNm+"_"+rowId+"' name='"+colNm+"' value='"+cellVal+"' style='width:100%' maxlength='15'  oninput='this.value = this.value.replace(/[^0-9]/g, \"\")' onkeyup='chkByte(this, 15)' onfocus='initNumber(this)' onblur='setNumberComma(this);'/>";
	return retData;
}

/*
 * 금액 unformat
 */
function unfmtGetGridInputAmt(cellvalue, options, cell){
	var retData = $.trim($('input', cell).val()).replace(/[^0-9]/g, "");	
	return $.trim(retData) == ""?"0":retData;	
}

/*
 * 숫자형 input Formatter
 */
function fmtSetGridInputNum(cellvalue, options, rowObject, maxlen){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : $.trim(cellvalue).replace(/[^0-9]/g, "");
	var retData = "";
	retData = "<input type='text' id='"+colNm+"_"+rowId+"' name='"+colNm+"' value='"+$.trim(cellVal)+"' oninput='this.value=this.value.replace(/[^0-9]/g,\"\")' onkeyup='fncDelFrontZero(this)' maxlength='"+maxlen+"'/>";
	
	return retData;
	
}


/*
 * 숫자형 소숫점 허용2자리  input Formatter
 */
function fmtSetGridInputNumHour(cellvalue, options, rowObject, maxlen){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : cellvalue;
	var retData = "";
	
	retData = "<input type='text' id='"+colNm+"_"+rowId+"' name='"+colNm+"' value='"+$.trim(cellVal)+"' oninput='getHour(this);' onkeyup='fncDelFrontZero(this)' maxlength='"+maxlen+"' autocomplete='off'/>";
	
	return retData;
}

function unfmtSetGridInputNumHour(cellvalue, options, cell){
	var retData = $.trim($('input', cell).val()).replace(/[^-\.0-9]/g, "");	
	return $.trim(retData) == "" ? "0.00":retData;	
}



/*
 * 계좌번호 
 */
function fmtSetGridInputAcntNo(cellvalue, options, rowObject, availDataLen,phText){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : $.trim(cellvalue).replace(/[^0-9]/g, "");
	var retData = "";
	if($.trim(phText)!=""){
		retData = "<input type='text' id='"+colNm+"_"+rowId+"' name='"+colNm+"' value='"+$.trim(cellVal)+"' oninput='this.value=this.value.replace(/[^0-9]/g,\"\")' onkeyup='fncDelFrontZero(this)' placeholder='"+phText+"' style='width:100%' />";
	}else{
		retData = "<input type='text' id='"+colNm+"_"+rowId+"' name='"+colNm+"' value='"+$.trim(cellVal)+"' oninput='this.value=this.value.replace(/[^0-9]/g,\"\")' onkeyup='fncDelFrontZero(this)' style='width:100%' />";		
	}
	
	
	return retData;
	
}

/*
 * 라디오 버튼(with codelist) Formatter
 * - codeList : 라디오 버튼 구성용 codeList data (필수)
 */
function fmtSetGridRadio(cellvalue, options, rowObject, defFieldYn, codeList){
	var rowId = $.trim(options.rowId);
	
	if(rowId.indexOf("blankRow")>-1) return "";
	
	var colNm = $.trim(options.colModel.name)+"_"+rowId;
	var cellVal = $.trim(cellvalue) == "" ? "" : cellvalue;
	var retData = "";
	
	//selectBox 구성
 	retData = getFmtGridRadioButtonFrStrCodList(colNm, cellVal, defFieldYn, codeList);
	
	return retData;
}

/*
 * 라디오버튼 Unformat
 */
function unfmtGetGridRadio(cellvalue, options, cell){
	var retData = $.trim($('input[type=radio]:checked', cell).val());
	return retData;
}

/*소숫점 2자리 정규식 */
function getHour(obj) {
	var nums = obj.value; 
	var pattern = /(^\d+$)|(^\d{1,}.\d{0,2}$)/; 

	if (!pattern.test(nums)) {
		 obj.value = "";
		return;
	}
 }
