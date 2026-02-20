

var __colors =[
		 "#AD1457"		//RED
		,"#FF8F00"		//ORANGE
		,"#1565C0"		//BLUE
		,"#546E7A"		//DARK BLUE
		,"#2aa2a2"		//GRAY

		,"#778899"
		,"#5F9EA0"
		,"#fff"
		,"#fff"
		,"#fff"

		,"#B2DFDB"
		,"#90A4AE"
		,"#8D6E63"
		,"#673AB7"
		,"#D81B60"

		,"#FFCC80"
		,"#99bc19"
		,"#EC407A"
		,"#E1BEE7"
		,"#29B6F6"
     ];

/**
 * 정규식 패턴
 * fnValidRegExp과 함께 사용
 * 1) EMAIL : 이메일 정규식
 * 2) ONLY_NUMBER : 숫자
 * 3) PW_CHK_01: 패스워드 - 영문, 특수문자, 숫자 포함 8~25자
 */
var REG_PTRN = {
		EMAIL : /^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$/,
		ONLY_NUMBER : /^[0-9]*$/,
		PW_CHK_01 : /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/,
}


if (typeof(Number.prototype.isBetween) === "undefined") {
    Number.prototype.isBetween = function(min, max, notBoundaries) {
        var between = false;
        if (notBoundaries) {
            if ((this < max) && (this > min)) between = true;

        } else {
            if ((this <= max) && (this >= min)) between = true;
        }
        return between;
    }
}

	/**
	 * 메뉴 링크 ACTION
	 * @param _URL
	 * @param _CODE
	 */
	function _setNextUrl(_URL, _CODE, _SYS_CODE){

		//var url = _URL+"?_code="+_CODE+"&sysCode="+_SYS_CODE;
		var url = _URL+"?_code="+_CODE;
		
		location.href = url;

		/*if(document._pgm) {
			var frm = document._pgm;

			frm._code.value=_CODE;
			frm.action = _URL;

			frm.submit();
		}
		else
		{
			var url = _URL+"?_code="+_CODE;
			location.href=url;
		}*/
	}

	/**
	 * 텝메뉴  링크 ACTION
	 * @param _URL, _CODE,_TITLE,_PARENT,_PTITLE
	 * TODOCHECK
	 */
	function _setNextUrlTab(_URL, _MENU_CODE, _TITLE, _PARENT_CODE, _SYS_CODE){

		_eventSetMenu(_PARENT_CODE, _MENU_CODE, _SYS_CODE);

		// 맨처음 메인에서 한번 호출
		var checkT = 0 ;

		if( $('#tt').tabs().text() == "" || $('.tabs-title').size() < 0) {
			var url = _URL+"?_code="+_MENU_CODE+"&sysCode="+_SYS_CODE;
			location.href = url;
		}else{
			// 한번 호출이후 다시 들어오면 중복체크
			for(var i = 0; i < $(".tabs > li").size();i++){
			//	if($('.tabs-title').eq(i).text() == _TITLE || $('.tabs-title').eq(i).text() == _PTITLE){
				if($('.tabs-title').eq(i).text() == _TITLE ){
					checkT++;
				}
			}
		}

		// 중복된 탭이있을경우  해당탭으로 이동
		if(checkT > 0 ) $('#tt').tabs('select', _TITLE.trim());
		else {
			// 중복탭이 없으며 새롭게 생성되는탭의 경우 5개가 넘어가면 맨앞에서 부터 삭제하면서 5개 의 탭 유지
			if( $('.tabs > li').size() >= 5){
				$('#tt').tabs('close',0);
			}

			//if(_PTITLE)_TITLE= _PTITLE;

			// 아이프레임으로 탭생성
			//var content = '<iframe id="'+_MENU_CODE+'" name="'+_MENU_CODE+'" scrolling="no"  onload="autoResize(this,$(this),'+_MENU_CODE+')"  frameborder="0" style="width:100%;"></iframe>';
			var content = '<iframe id="'+_MENU_CODE+'" name="'+_MENU_CODE+'" scrolling="no"   frameborder="0" style="width:100%;"></iframe>';
			$('#tt').tabs('add',{
				title:_TITLE,
				content:content,
				closable:true
			});

			$("#"+_MENU_CODE).attr("src",_URL+"?_code="+_MENU_CODE+"&sysCode="+_SYS_CODE);

			// 기본사이즈
			// window.height
			var iSize = $(this).height();
			$("#"+_MENU_CODE).css("height",iSize);

		}
	}



	/**
	 * 탭 리사이징
	 * @param div container Height
	 */
	function tabResize(divHeight){

		var pp = $('#tt').tabs('getSelected');

		pp.panel('resize',{
			width: '100%',
			height: 'auto'

		});

		var selectedIframe = pp.panel('options').content;

		var iframeSize = $('#'+$(selectedIframe).attr('id')).css('height').replace('px', '');

		if(iframeSize < divHeight){
			$('#'+$(selectedIframe).attr('id')).css('height',divHeight);
		}

		$(window).resize();
	}


	/* tabs 비동기 class 세팅 */
	function _setTabsCss(){

		//var strSelectedMenuId = $("#divSelectedMenuId").text();

		// LNB Tree Init
		setTree(".menu_tree");

		// LNB Toggle Click Function
		$(".menu_tree button.toggle").bind("click", function () {
			fncLnbChilds($(this));
			uiRefresh();
		});

		// LNB 링크가 #인 아이들은 토글 실행
		$(".menu_tree a[href*='#']").click(function () {
			$(this).parent().find(".toggle:first").trigger("click");
			return false;
		});
		// LNB Init
		//if (strSelectedMenuId.length > 0) {
		//	fncOpenSelfTree(strSelectedMenuId);
		//}

	}


	/**
	 * tab메뉴 사용시 iframe 리사이징
	 * @param this
	 */
	function autoResize(i,_i,_CODE)
	{

		  var iheight = $("#"+_CODE).contents().find('#section').height();
		  var iSize = $(_i).height();

		  if(decoratorMenuTabs && decoratorMenuTabs != "N"){

			  if(iheight > iSize){
				$("#"+_CODE).css("height",iheight-40);
			  }else{
				$("#"+_CODE).css("height",iSize-20);
			  }

		  }else{

			  if(iheight > iSize){
				$("#"+_CODE).css("height",iheight);
			  }else{
				$("#"+_CODE).css("height",iSize);
			  }

		  }

	 /*   var iframeHeight=
	    (i).contentWindow.document.body.scrollHeight;
	    (i).height=iframeHeight+180;*/
	}


	/*GRID ROW SPAN*/
	function Merger(gridName, CellName) {

	    var mya = $("#" + gridName + "").getDataIDs();


	    var length = mya.length;
	    for (var i = 0; i < length; i++) {

	        var before = $("#" + gridName + "").jqGrid('getRowData', mya[i]);

	        var rowSpanTaxCount = 1;
	        for (j = i + 1; j <= length; j++) {

	            var end = $("#" + gridName + "").jqGrid('getRowData', mya[j]);
	            if (before[CellName] == end[CellName]) {
	                rowSpanTaxCount++;

	                $("#" + gridName + "").setCell(mya[j], CellName, '', {
	                    display: 'none'
	                });
	            } else {
	            	rowSpanTaxCount = 1;
	                break;
	            }
	           $("#" + CellName + "" + mya[i] + "").attr("rowspan", rowSpanTaxCount);
	        }
	    }
	}


	/*data table 초기화*/
	function setTbodyInit(listTable){
		$("#"+listTable+" tr").remove();
	}

	/*data table 결과없음 처리*/
	function setTbodyNoResult(listTable,cols,msg){

		if(!msg)  msg  = "No Data Found!";
		if(!cols) cols = 10;

		$("#"+listTable).append('<tr class="blankRow"><td colspan="'+cols+'" align=center height=30>'+msg+'</span></td></tr>');
	}



	/**
	 * 파일 확장자 체크
	 * @param obj
	 * @returns {Boolean}
	 */
	function fileValid(obj,type){
		var validDoc = "doc|docx|xls|xlsx|ppt|pptx|txt|jpg|gif|png|avi|swf|hwp";
		var validImg = "jpg|gif|png";
		var valid = "";
		var index = -1;
		var filePath = "";

		filePath = $(obj).val();

		if(!filePath) return;

	    index = filePath.lastIndexOf('.');

		if(type =='img') valid = validImg;
		else valid = validDoc;

		if(index != -1)
			tmp = filePath.substring(index+1, filePath.len);
	    else tmp = "";


		var validImgs = validImg.split("|");
		var state = false;
		for(var i=0; validImgs.length > i; i++) {
			if(validImgs[i] ==  tmp.toLowerCase() ) {
				state = true;
				break;
			}
		}

		if(!state)
		{

			if(type =='img')
				if(__locale && __locale != "ko") alert('Only image files can be registered.(JPG, GIF, PNG)');
				else alert('이미지 파일만 등록가능합니다.(JPG, GIF, PNG)');
			else {

				if(__locale && __locale != "ko") alert('The file extension is a registered impossible.');
				else alert('등록불가능한 파일 확장자입니다.');
			}

			obj.select();
			obj.value="";
		}
	}

	/**
	 * String replaceAll
	 */
	String.prototype.replaceAll = function(str1, str2){
		var temp_str = "";
		if (this.trim() != "" && str1 != str2){
			temp_str = this.trim();

			while (temp_str.indexOf(str1) > -1){
				temp_str = temp_str.replace(str1, str2);
			}
		}
		return temp_str;
	}


	/**
	 * 해당 화면의 레이어 팝업 드레그이벤트
	 * @param layerID
	 */
	function setLayerDraggable(layerID){
		$("#"+layerID).draggable({
			handle: $("h1")
			,cancle: $("a.close")
			,containment: "document"
			,scroll: false
		});
	}



	/**
	 * 해당 화면의 레이어 팝업의 위치 설정
	 * 회면 중앙정렬
	 */
	function setLayerPopupLocationSettings(layerID, layerHeight, layerWidth){
		var $layer = $('#'+layerID);
		var $opacity = $('#opacity');
		$layer.css('top',$(window).scrollTop() + ($(window).height() - layerHeight) / 2);
		$layer.css('left',$(window).scrollLeft() + ($(window).width() - layerWidth) / 2);
		$opacity.css('height', $(document).height());

		$(document).off('keydown').on('keydown', function(e) {
			if (e.keyCode === 27) { // ESC
				e.preventDefault();
				var $btnClose = $layer.find('a.close');
				if ($btnClose.length > 0) {
					$btnClose.click();
				} else {
					$layer.hide();
					$opacity.hide();
				}
			}
		});

		$layer.show();
		$opacity.show();
	}



	/**
	 * 공통 선택박스 호출 함수
	 * mainValue :
	 * parentCd:
	 * subName:
	 * type:
	 * defaultValue: all, choice, ''
	 * seletedValue: -1 or value
	 */
	function selectControllerVal(mainValue,parentCd,subName,type,defaultValue, seletedValue){

		var str = {'keyValue':mainValue, 'parentCd':parentCd, 'type':type};

		var url =  __context + '/app/common/selectCommonLargeCdCodeSub';

		 $.ajaxSetup({
		  		contentType: "application/json; charset=utf-8"
	  		});

		 $.post(	 url
		  			,JSON.stringify(str)
		  			,function(data,status){

		  				if(status == 'success'){
							$("select[name='"+subName+"'] option").remove();
							_setSelectBoxValue(data,subName,defaultValue, seletedValue);

						}
		  			}, 'json');
	}


	/**
	 * 계약 카테고리 선택박스 호출 함수
	 * mainValue :
	 * subName:
	 * defaultValue: all, choice, ''
	 * seletedValue: -1 or value
	 */
	function selectCategoryCode(mainValue,subName,defaultValue, seletedValue){

		var str = {'keyValue':mainValue};

	    var url =  __context + '/app/cont/selectCategoryCode';

		 $.ajaxSetup({
		  		contentType: "application/json; charset=utf-8"
	  		});

		 $.post(	 url
		  			,JSON.stringify(str)
		  			,function(data,status){

		  				if(status == 'success'){
							$("select[name='"+subName+"'] option").remove();
							_setSelectBoxValue(data,subName,defaultValue, seletedValue);

						}
		  			}, 'json');
	}



	/**
	 * 계약 카테고리 선택박스 호출 함수(해당 계약건 조회용)
	 * mainValue :
	 * subName:
	 * defaultValue: all, choice, ''
	 * seletedValue: -1 or value
	 */
	function selectOneContractCategoryCode(mainValue,subName,defaultValue, seletedValue){

		var str = {'keyValue':mainValue};

		var url =  __context + '/app/cont/selectOneContractCategoryCode';

		 $.ajaxSetup({
		  		contentType: "application/json; charset=utf-8"
	  		});

		 $.post(	 url
		  			,JSON.stringify(str)
		  			,function(data,status){

		  				if(status == 'success'){
							$("select[name='"+subName+"'] option").remove();
							_setSelectBoxValue(data,subName,defaultValue, seletedValue);

						}
		  			}, 'json');
	}



	/**
	 * 계약 카테고리 선택박스 호출 함수(리턴 Function)
	 * mainValue :
	 * subName:
	 * defaultValue: all, choice, ''
	 * seletedValue: -1 or value
	 */
	function selectCategoryCodeFnc(mainObj,subName,defaultValue, seletedValue,fnc){

		var str = {'keyValue':mainObj.value.replace('choice','').replace('all','')};

		var url =  __context + '/app/cont/selectCategoryCode';

		 $.ajaxSetup({
		  		contentType: "application/json; charset=utf-8"
	  		});

		 $.post(	 url
		  			,JSON.stringify(str)
		  			,function(data,status){

		  				if(status == 'success'){
							$("select[name='"+subName+"'] option").remove();
							_setSelectBoxValue(data,subName,defaultValue, seletedValue);

							eval(fnc);

						}
		  			}, 'json');
	}



	/**
	 * 계약 중분류 선택박스 호출 함수
	 * mainObj :
	 * parentCd:
	 * subName:
	 * type:
	 * defaultValue: all, choice, ''
	 * seletedValue: -1 or value
	 */
	function selectControllerReturnLargeCdFnc(mainObj,parentCd,subName,type,defaultValue, seletedValue, fnc){

		var str = {'keyValue':mainObj.value.replace('choice','').replace('all',''), 'parentCd':parentCd, 'type':type};

		var url =  __context + '/web/common/selectCommonLargeCdCodeSub';

		 $.ajaxSetup({
		  		contentType: "application/json; charset=utf-8"
	  		});

		 $.post(	 url
		  			,JSON.stringify(str)
		  			,function(data,status){
		  				if(status == 'success'){
							$("select[name='"+subName+"'] option").remove();
							_setSelectBoxValue(data,subName,defaultValue, seletedValue);

							eval(fnc);
						}
		  			}, 'json');
	}

/**
 * 계약 카테고리 중분류 선택박스 호출 함수(리턴 Function)
 * mainValue :
 * subName:
 * defaultValue: all, choice, ''
 * seletedValue: -1 or value
 */
function selectFrmMiddleCodeFnc(mainObj,subName,defaultValue,seletedValue,fnc){

	var str = {'keyValue':mainObj.value.replace('choice','').replace('선택','').replace('all','').replace('전체','')};

	var url =  __context + '/ecs/cont/selectFrmMiddleCode.json';

	$.ajax({
		async : false
		,url : url
		,type : 'post'
		,data : JSON.stringify(str)
		,dataType : "json"
		,contentType : "application/json; charset=utf-8"
		,success : function(data) {
			$("select[name='"+subName+"'] option").remove();
			_setSelectBoxValue(data,subName,defaultValue, seletedValue);

			eval(fnc);
		}
	});
}


/**
 * 계약고유속성코드 선택박스 호출 함수(리턴 Function)
 * mainValue :
 * subName:
 * defaultValue: all, choice, ''
 * seletedValue: -1 or value
 */
function selectDcContDivnCodeFnc(mainObj,subName,defaultValue,seletedValue,fnc){

	var str = {'keyValue':mainObj.value.replace('choice','').replace('선택','').replace('all','').replace('전체','')};

	var url =  __context + '/ecs/cont/selectDcContDivnCode.json';

	$.ajax({
		async : false
		,url : url
		,type : 'post'
		,data : JSON.stringify(str)
		,dataType : "json"
		,contentType : "application/json; charset=utf-8"
		,success : function(data) {
			$("select[name='"+subName+"'] option").remove();
			_setSelectBoxValue(data,subName,defaultValue, seletedValue);

			eval(fnc);
		}
	});
}
	/**
	 * 공통 선택박스 호출 콜백 함수
	 */
	function _setSelectBoxValue(data,subName,defaultValue,seletedValue){
		var sz = data.length
		,eleHtml = []
		, h = 0;

		if(data != null && data != ''){
			for(var k=0;k<sz;k++){
				eleHtml[++h] = "<option value='"+data[k].value+"'>"+data[k].text+"</option>"+"\n";
			}
		}else{
			if(defaultValue != ''){
				eleHtml[0] = "<option value=''>"+defaultValue+"</option>"+"\n";
			$("select[name='"+subName+"']").append(eleHtml.join(''));
			}


		}


		/*
		if(defaultValue == 'all'){
			eleHtml[0] = "<option value='all'>전체</option>"+"\n";
		}else if(defaultValue == 'choice'){
			eleHtml[0] = "<option value='choice'>선택</option>"+"\n";
		}else if(defaultValue != ''){
			// 2012.08.31 yjCho
			eleHtml[0] = "<option value=''>"+defaultValue+"</option>"+"\n";
		}else{
			// 값이 없는 경우 무시
		}
		*/

		if(sz <= 0) return;

		if(defaultValue != ''){
			eleHtml[0] = "<option value=''>"+defaultValue+"</option>"+"\n";
		}


		$("select[name='"+subName+"']").append(eleHtml.join(''));
		if(seletedValue != null && seletedValue != ''){
			if(seletedValue == '-1'){
				$("select[name='"+subName+"'] option:last").attr('selected','selected');
			}else{
				$("select[name='"+subName+"']").val(seletedValue);
			}

		}
	}


	/* 새로운 로우 넘버 세팅  */
	function newNumbering(listNm){
		$('#'+listNm+' tr td:first-child').each(function(i){
			$(this).text(i+1);
		});
	}


	/** 계약서 파일 디렉토리 명칭  return*/
	function getContractPath(path,gubunValue,dcDate){

		var strValue = "";

		if(dcDate == null || dcDate == ""){
			dcDate =getRemoveFormat(getCulDate());
		}

		strValue = getContractContextPath(path,gubunValue,dcDate);

		return strValue;
	}

	/** 계약서 파일 디렉토리 명칭  return*/
	function getContractContextPath(path,gubunValue,dcDate){

		var strValue='';

		strValue = path
			 + gubunValue+"/"
			 +dcDate.substring(0,4)+"/"
			 +dcDate.substring(4,6)+"/";
		return strValue;
	}


	// 구분자 제거
	function getRemoveFormat(val) {

		var arrDate = new Array(3);

		if(val.indexOf("-") != 1) 	arrDate = val.split("-");
		else 	arrDate = val.split("/");

		return arrDate[0] + arrDate[1];

	}

	/* return 값 : 현재 yyyyMMdd
	 * rtnType을 넣으면 값에 따라 return ex) getCulDate('-') 호출할 경우 yyyy-MM-dd 반환 */
	function getCulDate(rtnType){
		var year,month,day;
		var todate = new Date();
		var date = '';
		year=todate.getFullYear();

		if( todate.getMonth() < 9 ){
			month = '0'+(todate.getMonth()+1);
		}else{
			month = todate.getMonth()+1;
		}

		if( todate.getDate() < 10 ){
			day = '0'+todate.getDate();
		}else{
			day = todate.getDate();
		}

		date = year+rtnType+month+rtnType+day;

		if(!rtnType)
			date = year+''+month+''+day;
		return	date
	}

	/*그리드 cell 상세링크 속성 처리  formatter:sellSelected */
	function sellSelected(cellvalue, options, rowObject){
		cellvalue = $.trim(cellvalue) == ""?"-":cellvalue;
		var eventCellVal = '<a href="javascript:void(0);"><span ><i class="fa fa-search" ></i>'+cellvalue+'<span></a>';
		return eventCellVal;
	}

/*그리드 cell Unformatter - 태그 제거 후 데이터만 추출 */
	function unfmtCellData(cellvalue, options, cell){
		cellvalue = $.trim(cellvalue)==""?"":cellvalue;

		var retData = cellvalue.replace(/<[^>]*>?/g, '');
		return retData;
	}

	/*날짜를 'yyyyMMdd'형식으로 변환
	 * ex)'29/10/2018 -> '20181029'  */
	function getConvertDate(date){
		
//		date = date.replace(/\//gi, "");
		date = date.replace(/[^0-9]/g, "");
		var dtLen = $.trim(date).length;
		
		if(__locale !='ko'){
			if(dtLen == 8){
				var year = date.substr(4,4);
				var month = date.substr(2,2);
				var day = date.substr(0,2);
				date = year+ ""+month+""+day;
			}
		}
		return date;
	}

	/*입력받은 날짜를 "/" 를 붙여줌
	 * getConvertDateReverse(yyyyMMdd)
	 *  ex) ko - 99991230  -> 9999/12/30
	 *      en, vn - 99991230  -> 30/12/9999*/
	function getConvertDateReverse(date){
		var year = date.substring(0,4);
		var month = date.substring(4,6);
		var day = date.substring(6,8)
		if(!date) {
			data = "";
		}else {
			if(__locale == 'en' || __locale == 'vn')
				date = day + '-' + month + '-' + year
			else
				date = year + '-' + month + '-' + day
		}
		return date;
	}



	//-----------------------------------------------------------------------
	//숫자만 입력가능하도록
	//-----------------------------------------------------------------------
	//예)
	//html : <input type='text' name='test' onkeypress="onlyNumber();">
	//-----------------------------------------------------------------------
	function onlyNumber() {
		if((event.keyCode<48) || (event.keyCode>57))
		{
			if(event.preventDefault){
				//  IE
	        	event.preventDefault();
	    	} else {
	    		//  표준 브라우저(IE9, 파이어폭스, 오페라, 사파리, 크롬)
	        	event.returnValue = false;
			}
		}
	}

    /**
     * 숫자로만 이루어져 있는지 체크 한다.
     * ex) <input type="text" id="bmanNo" name="bmanNo" onkeyup="return isNumber(this);" />
     * @param    num, type
     * @return   boolean
     */
    function isNumber(num, type){
        var inText = num.value;
        var ret;
        for (var i = 0; i < inText.length; i++) {
        	ret = inText.charCodeAt(i);
        	if(type == null) {
                if (!((ret > 47) && (ret < 58)) && ret != 46) {
                    num.value = "";
                    num.focus();
                    return false;
                }
        	} else if(type == 'bmanNo'){
        		if (!((ret > 46) && (ret < 58)) && ret != 45) {
                    num.value = "";
                    num.focus();
                    return false;
                }
        	} else if(type == 'price'){
        		if (!((ret > 46) && (ret < 58)) && ret != 44) {
                    num.value = "";
                    num.focus();
                    return false;
                }
        	}
        	else {
        		if (!((ret > 47) && (ret < 58))) {
                    num.value = "";
                    num.focus();
                    return false;
                }
        	}
        }
        return true;
    }


    /*Number To Words
     * converNumToWord(number, 'EN')
     *
     * */
    function converNumToWord(number ,type){
    	number = removeNumberComma(number)
    	var rtnWords="";
    	var numberArray = {};
		var integer = "";		//받아온값 정수
		var decimal = "";		//소수
		var rtnDecimal = "";	//가공소수
		var gap ="";
    	if (type.toLowerCase() == 'en'){//영문변환
    		if(number == '0' ){
    			return 'Zero'
    		}else if(number > 0){
    			if(number.indexOf(".") > -1){
    				numberArray = number.split(".");
    				integer = numberArray[0];
    				decimal = numberArray[1];
    				rtnDecimal = "";
    				if(parseInt(decimal) !=0 ){
    					for(var i = 0; i < decimal.length; i++){
    						if("0" == decimal.charAt(i)){
    							rtnDecimal += " zero";
    						}else{
    							rtnDecimal += " "+units[decimal.charAt(i)];;
    						}
    					}
    					gap = integer == 0 ? "":" ";
 	    				rtnDecimal =  gap + "point"+  rtnDecimal;
    				}
    				rtnWords =convertEn(integer) + rtnDecimal;
    			}else{
    				rtnWords =convertEn(number);
    			}
    			return rtnWords;
    		}else{
    			return '';
    		}
    	}else{//베트남어 변환
    		if(number == '0' ){
    			return 'Không'
    		}else if(number > 0){
    			if(number.indexOf(".") > -1){
    				numberArray = number.split(".");
    				integer = numberArray[0];
    				decimal = numberArray[1];
    				rtnDecimal = "";
    				if(parseInt(decimal) !=0 ){
    					for(var i = 0; i < decimal.length; i++){
    						if("0" == decimal.charAt(i)){
    							rtnDecimal += " không";
    						}else{
    							rtnDecimal += " "+convertVn(decimal.substring(i,decimal.length));
 	 	    					break;
    						}
    					}
    					gap = integer == 0 ? "":" ";
 	    				rtnDecimal =  gap + "chấm"+  rtnDecimal;
    				}
    				rtnWords =convertVn(integer) + rtnDecimal;
    			}else{
    				rtnWords =convertVn(number);
    			}
    			return rtnWords;
    		}else{
    			return '';
    		}
    	}

    }
    /*1~19*/
    var units = [ "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve","thirteen", "fourteen", "fifteen", "sixteen"
    			, "seventeen", "eighteen", "nineteen" ];
	/*10자리수*/
	var tens = ["",	"", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" ];

	/*숫자 - > 영문으로 convert
	 * 10 -> ten*/
	function convertEn(number) {


		if (number < 20) {
			return units[number];
		}

		if (number < 100) {
			return tens[parseInt(number / 10)] + ((number % 10 != 0) ? " " : "") + units[number % 10];
		}

		if (number < 1000) {
			return units[parseInt(number / 100)] + " hundred" + ((number % 100 != 0) ? " " : "") + convertEn(number % 100);
		}

		if (number < 1000000) {
			return convertEn(parseInt(number / 1000)) + " thousand" + ((number % 1000 != 0) ? " " : "") + convertEn(number % 1000);
		}

	    if (number < 1000000000) {
			return convertEn(parseInt(number / 1000000)) + " million " + ((number % 1000000 != 0) ? " " : "") + convertEn(number % 1000000);
	    }

	    if(number < 1000000000000){
	    	return convertEn(parseInt(number / 1000000000)) + " billion " + ((number % 1000000000 != 0) ? " " : "") + convertEn(number % 1000000000);
	    }

	    return convertEn(parseInt(number / 1000000000000)) + " trillion " + ((number % 1000000000000 != 0) ? " " : "") + convertEn(number % 1000000000000);
	}
	/*숫자 - > 베트남어로 convert
	 * 10 -> ?*/
	/*1자리수*/
 	var unitsVn = [ "",
 			"một",				//1
 			"hai",				//2
 			"ba",				//3
 			"bốn",				//4
 			"năm",				//5
 			"sáu",				//6
 			"bảy",				//7
 			"tám",				//8
 			"chín"				//9
 			];

 	/*10자리수*/
 	var tensVn = ["",
 			"mười", 			// 10
 			"hai mươi",			// 20
 			"ba mươi",			// 30
 			"bốn mươi",			// 40
 			"năm mươi",			// 50
 			"sáu mươi",			// 50
 			"bảy mươi",			// 70
 			"tám mươi",			// 80
 			"chín mươi",		// 90

 	]

 	/*숫자 베트남어으로 convert*/
 	function convertVn(number) {
 		var unitsDigit = "";//1의자리수


 		if (number < 10) {

 			return unitsVn[number % 10];
 		}
 		if (number < 100) {
 			if(number % 10 == 1 && number > 20){/*21부터 1은 mốt로발음*/
 				unitsDigit = "mốt";
 			}else if(number % 10 != 0 && number % 5 == 0 && 15 <= number){/*15~95 까지 1의자리수가 5일 경우 lăm로발음*/
 				unitsDigit = "lăm";
 			}else{
 				unitsDigit = unitsVn[number % 10];
 			}

 			return tensVn[parseInt(number / 10)] + ((number % 10 != 0) ? " " : "") + unitsDigit;
 		}

 		if (number < 1000) {

 			if(0 < number % 100 && number % 100 < 10){/*10의자리가  0이면 10자리에 lẻ 추가*/
 				unitsDigit  = " lẻ " + unitsVn[number % 10];
 			}else{
 				unitsDigit = ((number % 100 != 0) ? " " : "") + convertVn(number % 100);
 			}
 			return unitsVn[parseInt(number / 100)] + " trăm" + unitsDigit;
 		}

 		if (number < 1000000) {
 			return convertVn(parseInt(number / 1000)) + " nghìn" + ((number % 1000 != 0) ? " " : "") + convertVn(number % 1000);
 		}

 	    if (number < 1000000000) {
 			return convertVn(parseInt(number / 1000000)) + " triệu" + ((number % 1000000 != 0) ? " " : "") + convertVn(number % 1000000);
 	    }

 	    if(number < 1000000000000){
 	    	return convertVn(parseInt(number / 1000000000)) + " tỷ" + ((number % 1000000000 != 0) ? " " : "") + convertVn(number % 1000000000);//억
 	    }

 	   return convertVn(parseInt(number / 1000000000000)) + " nghìn tỷ" + ((number % 1000000000000 != 0) ? " " : "") + convertVn(number % 1000000000000);//조

 	}

 	/*금액에 콤마 setNumberComma(this)*/
	function setNumberComma(obj) {
		var rgx1 = /\D/g;  // /[^0-9]/g 와 같은 표현
		var rgx2 = /(\d+)(\d{3})/;

		/*콤마 찍어주는 함수*/
		function setComma(inNum) {
			var outNum;
		    outNum = inNum;
		    while (rgx2.test(outNum)) {
		    	outNum = outNum.replace(rgx2, '$1' + ',' + '$2');
		    }
		    return outNum;
		}

		/*숫자 가져와서 콤마*/
		var num01;
	    var num02;
	    num01 = obj.value;
	    num02 = num01.replace(rgx1,"");
	    num01 = setComma(num02);
	    obj.value =  num01;

	}
	/*금액 ,콤마 없애기*/
	function initNumber(obj) {
		var init = obj.value.replaceAll(",","");
		obj.value = init;
//		return;
	}
	/*콤마 remove*/
	function removeNumberComma(str) {
		return str.replace(/[^\d\.\-\ ]/g, '');
	}
	/*콤마 찍어주는 함수*/
	function numberComma(str) {

		str = String(Math.floor(str));
		str = str.replace(/[^\d]+/g, '');
		return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	}
	/*콤마 찍어주는 함수*/
	function addComma(str) {

		return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	}

	var StringBuffer = function() {
		this.buffer = new Array();
	};

	StringBuffer.prototype.append = function(str) {
		this.buffer[this.buffer.length] = str;
		return this;
	};

	StringBuffer.prototype.toString = function() {
		return this.buffer.join("");
	};
	
	/**
	 * 커스텀 서브코드 리스트 조회
	 * - 대분류 extent로 맵핑된 중분류 조회
	 */
	function getCustomSubCodeListToSelBox(subName, defaultValue, selectedValue, masterParentCd, masterDtlCd, subValue, subValue2) {
		var str = {
			masterParentCd : $.trim(masterParentCd)
			,masterDtlCd : $.trim(masterDtlCd)
			,subValue : $.trim(subValue)
			,subValue2 : $.trim(subValue2)
		};

		var url =  __context + '/web/common/selCustomSubCodeList.json';

		$.ajaxSetup({
			contentType: "application/json; charset=utf-8"
		});

		$.post(	 url
			,JSON.stringify(str)
			,function(data,status){

				if(status == 'success'){
					$("select[name='"+subName+"'] option").remove();
					_setCustomCodeSelectBoxValue(data,subName,defaultValue, selectedValue);

				}
			}, 'json');
	}
	
	/**
	 * 커스텀 코드 리스트 조회
	 */
	function getCustomCodeListToSelBox(subName, defaultValue, selectedValue, parentCd, subValue, subValue2) {
		var str = {
			parentCd : $.trim(parentCd)
			,subValue : $.trim(subValue)
			,subValue2 : $.trim(subValue2)
		};

		var url =  __context + '/app/common/selCustomCodeList.json';

		$.ajaxSetup({
			contentType: "application/json; charset=utf-8"
		});

		$.post(	 url
			,JSON.stringify(str)
			,function(data,status){

				if(status == 'success'){
					$("select[name='"+subName+"'] option").remove();
					_setCustomCodeSelectBoxValue(data,subName,defaultValue, selectedValue);

				}
			}, 'json');
	}

	/**
	 * 커스텀 코드 셀렉트박스 셋팅(callback)
	 */
	function _setCustomCodeSelectBoxValue(data,subName,defaultValue,selectedValue){
		var sz = data.length
		,eleHtml = []
		, h = 0;

		if(data != null && data != ''){
			for(var k=0;k<sz;k++){
				eleHtml[++h] = "<option value='"+data[k].CODE_ID+"'>"+data[k].CODE_NAME+"</option>"+"\n";
			}
		}else{
			if(defaultValue != ''){
				eleHtml[0] = "<option value=''>"+defaultValue+"</option>"+"\n";
			$("select[name='"+subName+"']").append(eleHtml.join(''));
			}


		}

		if(sz <= 0) return;

		if(defaultValue != ''){
			eleHtml[0] = "<option value=''>"+defaultValue+"</option>"+"\n";
		}

		$("select[name='"+subName+"']").append(eleHtml.join(''));
		if(selectedValue != null && selectedValue != ''){
			if(selectedValue == '-1'){
				$("select[name='"+subName+"'] option:last").attr('selected','selected');
			}else{
				$("select[name='"+subName+"']").val(selectedValue);
			}

		}
	}


	/*선택이미지 등록 정보 또는 빈 박스 삭제*/
	function fn_DelImageBox(obj){
		var obj_ = $(obj).parent().parent();
		var imgErrorSrc = '/resources/images/pearl/common/noimage3.png';
		var imgSuccessSrc = $(obj).nextAll("img").attr("src");
		var fileType = $(obj).parent().parent().find("#viewer").attr("type");
		var result = true;

		if(obj_.find("input[name='imgPclNo']").val()) {
			result = confirm("삭제하시겠습니까?");
		}

		if(result == true) {
			if($("#addImageBoxYn").val() == 'Y') {
				obj_.remove();
			} else {
				obj_.find("#viewer").attr("data","");
				obj_.find("#viewer").css("display","none");
				obj_.find("img").css("display","inline");
				obj_.find("img").attr('src', imgErrorSrc);
				obj_.find("input[type='file']").prop("disabled",false);
				obj_.find("div[name='btnShooting']").css("color","black");
				obj_.find("input[type='radio']").prop("disabled",false);
				obj_.find("input[name='imgPclNo']").val("");
				obj_.find("input[name='stdocsId']").val("");
				obj_.find("input[name='inpCmt']").val("");
			}
		}
	}

	/**
	 * 사진보관함, 사진보고서 사진촬영  공통
	 */
	function commonPictUploadPhoto(obj, e, context,filePathNm, pdfYn, obj_rtnFnc, fileKindCd){
		var jsonUrl = context+"/app/common/commonFileload_insImageUpload.json";


	    var fileList = e.target.files[0];
		var fileName = fileList.name;  //원본파일명
		var fileExt = fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase(); //파일확장자

		var fileType = fileList.type.split("/");

		if(fileType[0] != "image") {
			alert("이미지 파일이 아닙니다.");
			$(obj).val("");
			return;
		}
		    // 읽기
		var reader = new FileReader();
		    reader.readAsDataURL(fileList);

	    var dataURI = null;
	    	//로드 한 후
	    	reader.onload = function(){

	    	/*-MetaData Info-------------------------------------------------------------------*/

	     	var props = null;
	     	try{
		     	if(fileExt == 'JPG') {
		     		props = display(atob(this.result.replace(/^.*?,/,'')), fileName);
		     	}
	     	}catch(error){}

	        /*-------------------------------------------------------------------------------*/



	        //썸네일 이미지 생성
	        var tempImage = new Image(); //drawImage 메서드에 넣기 위해 이미지 객체화
	        tempImage.src = reader.result; //data-uri를 이미지 객체에 주입

	        tempImage.onload = function() {

		    	var MAX_WIDTH = 2048;
		    	var MAX_HEIGHT = 1536;

		    	var width = tempImage.width;
		        var height = tempImage.height;
		        var angle = 0;

	            if (width > height) {
	            	  if (width > MAX_WIDTH) {
	            	    height *= MAX_WIDTH / width;
	            	    width = MAX_WIDTH;
	            	  }
	            	} else {
	            	  if (height > MAX_HEIGHT) {

	            	    width *= MAX_HEIGHT / height;
	            	    height = MAX_HEIGHT;
	            	  }
	            }

	        	//리사이즈를 위해 캔버스 객체 생성
	            var canvas        = document.createElement('canvas');
	            var canvasContext = canvas.getContext("2d");
		        //var orientation   = typeofCheck(props.Orientationofimage);
	            var orientation;
	            if(props) {
	            	orientation   = typeofCheck(props.Orientationofimage);
	            }

		        var oriset = 0;
	            //캔버스 크기 설정
	           // canvas.width  = width; //가로 100px
	           // canvas.height = height; //세로 100px

	          	// 촬영방향   [ 1 : 0도 회전, 3 : 180도 회전(뒤집어진), 6 : 시계방향으로 270도 회전, 8 : 시계방향으로 90도 회전된 사진 ]
	            var rot = 0;
	            if(orientation && orientation != "1") {

	            	if(orientation == "3")  	 oriset = 180;	//180도 회전(뒤집어진)
	            	else if(orientation == "6")  oriset = 270;	//시계방향으로 270도 회전
	            	else if(orientation == "8")  oriset = 90; 	//시계방향으로 90도 회전된 사진
	            	else oriset = 0;

	            	rot = 360-oriset;
	            }


	            switch(rot) {
	            default :
	            case 0 :
	                canvas.setAttribute('width',  width);
	                canvas.setAttribute('height', height);
	                canvasContext.rotate(rot*Math.PI/180);
	                canvasContext.drawImage(this, 0, 0, width, height);
	                break;
	            case 90 :
	            	canvas.setAttribute('width',  height);
	                canvas.setAttribute('height', width);
	                canvasContext.rotate(rot*Math.PI/180);

	                canvasContext.drawImage(this, 0, -height, width,height);
	                break;
	            case 180 :
	                canvas.setAttribute('width',  width);
	                canvas.setAttribute('height', height);
	                canvasContext.rotate(rot*Math.PI/180);
	                canvasContext.drawImage(this, -width, -height, width, height);
	                break;
	            case 270 :
	            case -90 :
	                canvas.setAttribute('width',  height);
	                canvas.setAttribute('height', width);
	                canvasContext.rotate(rot*Math.PI/180);
	                canvasContext.drawImage(this, -width, 0, width, height);
	                break;
	            }

	            //이미지를 캔버스에 그리기
	           // canvasContext.drawImage(this, 0, 0, width, height);

	            //캔버스에 그린 이미지를 다시 data-uri 형태로 변환
	            dataURI = canvas.toDataURL("image/jpeg");


	        	//console.log(props);
	        	var blobBin = atob(dataURI.split(',')[1]);
	        	var array = [];

	        	for(var i = 0; i < blobBin.length; i++) {
	        	    array.push(blobBin.charCodeAt(i));
	        	}
	        	var file=new Blob([new Uint8Array(array)], {type: 'image/png'});


	        	var formdata = new FormData();
	        	formdata.append("file", 	 file);		// 저장파일정보
	        	formdata.append("orgFileNm" ,fileName);	// 원본파일명
	        	formdata.append("pdfYn"		,pdfYn);	// pdf변환여부
	        	formdata.append("filePathNm",filePathNm);//디렉토리파일path
	        	formdata.append("fileKindCd",fileKindCd);//디렉토리파일path

	        	/*이미지메타정보 ------------------ */
	        	if(props){
		        	formdata.append("tagGpsLatitude",		typeofCheck(props.DecLatitude));			// GPS고도
		        	formdata.append("tagGpsLongitude",		typeofCheck(props.DecLongitude));			// GPS lat
		        	formdata.append("tagGpsAltitude",		typeofCheck(props.Altitudereference));		// GPS lon
		        	formdata.append("tagExifImageHeight", 	typeofCheck(props.PixelHeight)); 		    // Pixel Height
		        	formdata.append("tagExifImageWidth", 	typeofCheck(props.PixelWidth));		    	// Pixel Width
		        	formdata.append("tagDatetimeOriginal", 	typeofCheck(props.DateTimeOriginal));     	// Date Time Original
		        	formdata.append("tagDatetime",			typeofCheck(props.DateTimeDigitized));     	// Date Time Digitized
		        	formdata.append("tagMake",				typeofCheck(props.Make));			        // Make
		        	formdata.append("tagModel",				typeofCheck(props.Model));				    // Model

		        	if(props.Orientationofimage) {
		        		formdata.append("tagOrientation",		typeofCheck(props.Orientationofimage)); 	// Orientation of image
		        	}
		        	formdata.append("tagSoftware",			typeofCheck(props.Software));				// Software
	        	}

	           $.ajax({
	        	  url: jsonUrl,
	              type: "POST",
	              data: formdata,
	              dataType:'json',
	              processData: false,   // tell jQuery not to process the data
	              contentType: false ,  // tell jQuery not to set contentType,
	              success : function(data){
	            	if(data.resultCode !="success") {
	            		//이미지등록중 오류가 발생하였습니다.
	                	alert('이미지등록중 오류가발생했습니다.'+" \n"+data.message+"["+data.msgCd+"]");
	             	}
	          		if(obj_rtnFnc) $(obj_rtnFnc(data,obj));
	        	  }
	             });

	        }
		 };
	}

	/**
	 * input value byte Check
	 * ex)<input type="text" onkeyup ='chkByte(this,"5")'/>
	 */
	function chkByte(obj, maxLength){
		var $this = $(obj);			//input
		var str = $this.val();		//input value
		var _byte = 0;				//byte
		var strLength = 0;
		var charStr = '';
		var cutStr = '';
		if (str.length <= 0) {
			return;
 		}
		for (var i = 0; i < str.length; i++) {
			charStr = str.charAt(i);
			if (escape(charStr).length > 4) {	//한글
 				_byte += 2;
 			}else {								//영어
 				_byte++;
 			}

			if (_byte <= maxLength) {
				strLength = i + 1;
			}
		}
		if (_byte > maxLength) {
 			cutStr = $this.val().substr(0, strLength);
 			$this.val(cutStr);
 			return;
 		}
	}

	//이미지 세팅
	commonPictSet = function commonPictSet(data,obj){
		/* 이미지등록/선택 후 이미지 조회 후처리 함수  */
		if(!data) return;

		$(obj).parent().parent().parent().parent().find("input[name=imgPclNo]").val(data.rtnValue01);			// 이미지고유번호
		$(obj).parent().parent().find("input[type='radio']").prop("disabled", true);
		$(obj).parent().parent().find("input[type='file']").prop("disabled",true);
		$(obj).parent().parent().find("div[name='btnShooting']").css("color",'#ccc6b6');

		if(data.rtnValue04 != null && data.rtnValue04 != "") {
			//PDF일 경우
			$(obj).parent().parent().parent().parent().find("#viewer").attr("type", "application/pdf");
			$(obj).parent().parent().parent().parent().find("#btnEngImage").css("display","inline-block");
			$(obj).parent().parent().parent().parent().find("#viewer").css("display","block");
			$(obj).parent().parent().parent().parent().find("img[name='imgTest']").css("display","none");		//IMGTEST 숨기기

			if($(obj).parent().parent().parent().parent().find("#viewer").attr("data") != "data:application/pdf;base64," + data.rtnValue05 +"#toolbar=0"){
				$(obj).parent().parent().parent().parent().find("#viewer").attr("data", "data:application/pdf;base64," + data.rtnValue05 +"#toolbar=0");
			}
		} else {
			var imageFullUrl = "/app/common/"+data.rtnValue01+"/loadImageInfo";
			$(obj).parent().parent().parent().parent().find("#viewer").attr("type", "image/gif");
			$(obj).parent().parent().parent().parent().find("img[name='imgTest']").css("display","");			//IMGTEST 보이기
			$(obj).parent().parent().parent().parent().find("img[name='imgTest']").attr('src',imageFullUrl)
		}

	}

	function fnCommonPdfThumb(imageFullUrl, obj) {
		// Loaded via <script> tag, create shortcut to access PDF.js exports.
  		var pdfjsLib = window['pdfjs-dist/build/pdf'];

  		// The workerSrc property shall be specified.
  		pdfjsLib.GlobalWorkerOptions.workerSrc = "/resources/plugins/pdfThum/js/pdf.worker_thum.js";

  		var pdfDoc = null,
  				pageNum = 1,
  				pageRendering = false,
  				pageNumPending = null,
  				scale = 0.8,
  				canvas,
  				ctx;

  	    /**
  	     * Asynchronously downloads PDF.
  	     */
  	    pdfjsLib.getDocument(imageFullUrl).promise.then(function(pdfDoc_) {
  	        pdfDoc = pdfDoc_;
			//obj.parentNode.parentNode.parentNode.querySelector('#page_count').textContent = pdfDoc.numPages;

  	        // Initial/first page rendering
  	        renderPage(pageNum);
  	    });

  		// /**
  		//  * Get page info from document, resize canvas accordingly, and render page.
  		//  * @param num Page number.
  		//  */
  		function renderPage(num) {
  			canvas = obj.parentNode.parentNode.parentNode.querySelector('#the-canvas');
  	        ctx = canvas.getContext('2d');
  			pageRendering = true;


  			// Using promise to fetch the page
  			pdfDoc.getPage(num).then(function(page) {
  	            var desiredWidth = canvas.width;
  	            var desiredHeight = canvas.height;
  	          	var viewport = page.getViewport({ scale: 1 });
  	          	var scale = 1;
  	          	if(viewport.width > viewport.height){
  	          		scale = desiredWidth / viewport.width;
  	          	} else {
  	         	 	scale = desiredHeight / viewport.height;
  	          	}


  	            var viewport = page.getViewport({ scale: scale });

  				// Render PDF page into canvas context
  				var renderContext = {
  					canvasContext: ctx,
  					viewport: viewport
  				};
  				var renderTask = page.render(renderContext);

  				// Wait for rendering to finish
  				renderTask.promise.then(function() {
  					pageRendering = false;
  					if (pageNumPending !== null) {
  						// New page rendering is pending
  						renderPage(pageNumPending);
  						pageNumPending = null;
  					}
  				});
  			});

  			// Update page counters
  			//obj.parentNode.parentNode.parentNode.querySelector('#page_num').textContent = num
  		}

  		/**
  		 * If another page rendering in progress, waits until the rendering is
  		 * finised. Otherwise, executes rendering immediately.
  		 */
  		function queueRenderPage(num) {
  			if (pageRendering) {
  				pageNumPending = num;
  			} else {
  				renderPage(num);
  			}
  		}

  		/**
  		 * Displays previous page.
  		 */
  		function onPrevPage() {
  			if (pageNum <= 1) {
  				return;
  			}
  			pageNum--;
  			queueRenderPage(pageNum);
  		}

  		/**
  		 * Displays next page.
  		 */
  		function onNextPage() {
  			if (pageNum >= pdfDoc.numPages) {
  				return;
  			}
  			pageNum++;
  			queueRenderPage(pageNum);
  		}
	}

	/**
	 * 단일 데이터의 데이터 없음 처리
	 * - null / undefined / "" 확인 후, 데이터 값이 없으면 공백("") 반환
	 */
	function fncSetDefaultSingleData(data,trimChk){
		var flag = false;	//데이터 유무 체크

		//데이터가 존재하지 않을 경우, flag true
		if(typeof(data) == "undefined"){
			flag = true;
		}else if(null == data || "" == data){
			flag = true;
		}

		//flag가 true일 경우 공백반환
		if(flag){
			return "";
		}

		//trimChk == Y일 경우, 공백 제거한 데이터 체크
		if(trimChk == "Y" || trimChk == "y"){
			if("" == data.trim()){
				return "";
			}
		}

		//데이터가 있을 경우 데이터 리턴
		return data;
	}

	/**
	 * 정규식 validation
	 * Ex) /^[0-9]/g : only number
	 * fnValidRegExp('abcd1234' , REG_PTRN.ONLY_NUMBER)
	 *
	 * @param s : 대상
	 * @param regPtrn : 정규식_pattern
	 * @returns boolean : true || false
	 */
	function fnValidRegExp(s, regPtrn) {
		return regPtrn.test(s)
	}


	//숫자만 입력
	function onlyInputNumber(obj) {
	    $(obj).keyup(function(){
	         $(this).val($(this).val().replace(/[^0-9]/g,""));
	    });
	}

	//숫자만 입력
	function onlyInputNumberDot(obj) {
	    $(obj).keyup(function(){
	         $(this).val($(this).val().replace(/[^0-9.]/g,""));
	    });
	}

	//금액 콤마 - value 계산용
	function amtComma(amt) {
	    var num = amt + '';
	    for(var regx = /(\d+)(\d{3})/;regx.test(num); num = num.replace(regx, '$1' + ',' + '$2'));
	    return num;
	}

	//금액 콤마 - onkeyup용
	function amtFormat(obj) {
	  var num = obj.value + '';
	  for(var regx = /(\d+)(\d{3})/;regx.test(num); num = num.replace(regx, '$1' + ',' + '$2'));
	  obj.value = num;
	}

	//금액 콤마 제거
	function unNumberFormat(obj) {
		var num = obj.value;
		obj.value = (num.replace(/\,/g,""));
	};

	/* 2021-12-03
	* Make form and action.
	* Form.util.MakeFormAndAction
	* #폼 태그 자동 생성 #submit
	* Parameter
	* 1. 폼태그의 attribute. ex)
	* {
	*    action: "/cos/mgr/cosBrandMgrEdit.do",
	*    target: "_self",
	*    method: "POST"
	* }
	* 2. 폼 데이터 - json
	* 3. 컨트롤키 푸쉬 여부 - event.ctrlKey
	*/

	var _tags = {
		span: "<span></span>",
		div: "<div></div>",
		form: "<form></form>",
		table: "<table></table>",
		tr: "<tr></tr>",
		th: "<th></th>",
		td: "<td></td>",
		p: "<p></p>",
		textarea: "<textarea></textarea>",
		input: "<input></input>",
		i: "<i></i>",
		label: "<label></label>",
		select: "<select></select>",
		option: "<option></option>",
	}

	function makeFormAndSubmit(attributeByForm, data, isCtrlKey) {
		$("body").find("#form-make-byFn").remove();

		if( isCtrlKey ) {
			attributeByForm.target = "_Blank";
		}

		let form = $(_tags.form).appendTo(wrapper);
		form.attr($.extend(attributeByForm, {
			id: "form-make-byFn"
		}));
		form.css({display: "none"});

		for( let key in data ) {
			let input = $(_tags.input).appendTo(form);
			input.attr({name: key});
			input.val(data[key]);
		}

		let input = $(_tags.input).appendTo(form);
		let csrfKey = $("meta[name='_csrf_parameter']").attr("content");
		let csrfValue = $("meta[name='"+csrfKey+"']").attr("content");
		input.attr({name: csrfKey}).val(csrfValue);

		form.submit();
	}


	//금액콤마추가&백만단위생략&부호 추가
	function fncHundread(obj){
		var num = obj.toString();
		var newNum ="";
		var chgNum ="";
		var sign = ""; //부호
		sign = num.substr(0,1);

		if(sign =="-"){num = num.substr(1);}

		if(num=="0" || num ==""){
			newNum = "0";
		}else{
			newNum = (num/1000000).toFixed(1);
		}

		var newNoInt = newNum.split(".")[0];
		var newNoDecimal = newNum.split(".")[1];

		if(newNoInt.length>3){
			var rgx = /(\d+)(\d{3})/;

			while(rgx.test(newNoInt)){
				chgNum = newNoInt.replace(rgx,'$1' + ',' + '$2');
				newNoInt = chgNum;
			}
			newNum = newNoInt+"."+newNoDecimal;
			chgNum = newNum;
		}else{
			chgNum = newNum;
		}

		if(sign =="-"){
			chgNum = sign+chgNum;
		}
		return chgNum;
	}


	//날짜 input 속성 셋팅
	function fncSetDefaultDy(param){
		var attrNm="dy";
		if($.trim(param) != ""){
			attrNm = param;
		}

		$("."+attrNm).attr("maxlength", "8");
		$("."+attrNm).attr("onblur", "fncSetDateFormat(this)");
		$("."+attrNm).attr("oninput", "this.value = this.value.replace(/[^0-9]/g, '')");
		$("."+attrNm).attr("onfocus", "fncRemainOnlyNumber(this)");
	}

	//날짜 형식으로 셋팅
	function fncSetDateFormat(obj){
		var date = $.trim(obj.value).replace(/^[0-9]/g,"");

		//날짜값이 8자가 아니면 공백변환(저장 및 조회 시 오류 방지 위함)
		if(obj.value.length == 8){
			obj.value = obj.value.replace(/(\d{4})(\d{2})(\d{2})/g, "$1-$2-$3");
		}else{
			obj.value="";
		}
	}

	// 날짜 형식체크후 셋팅
	function fncSetDateFormatVailed(obj){

		var date = $.trim(obj.value).replace(/^[0-9]/g,"");

		//날짜값이 8자가 아니면 공백변환(저장 및 조회 시 오류 방지 위함)
		if(obj.value.length == 8){
			let regex = RegExp(/^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/);
			let objVal = obj.value.replace(/(\d{4})(\d{2})(\d{2})/g, "$1-$2-$3");
			if(regex.test(objVal) == false) {
				alert("날짜 형식에 맞도록 입력해 주세요");
				obj.value = "";
				obj.focus();
			}else{
				obj.value = objVal;
			}
		}else{
			obj.value = "";
		}
	}

	//onfocus 숫자만 남기기
	function fncRemainOnlyNumber(obj){
		obj.value = obj.value.replace(/[^0-9]/g,"");
	}

	//onblur 숫자 콤마찍기
	function fncSetAmtFormat(obj){
		return $(obj).val($(obj).val().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,'));
	}


/**
 * float형 숫자 길이 제한(onkeyup)
 * e : event
 * i : 정수부
 * f : 소수부
 */
function fncNumLengthChk(obj, e, i, f){
	var val = $.trim(obj.value);
	/**
		1. 숫자 및 소수점 제외 제거
		2. 1-9로 시작하지 않을 경우 전부제거
		3. 소수점 1개이상 사용할 경우 1개만 남기고 제거
	 */
	val = val.replace(/[^0-9.]/g,"").replace(/(^[^1-9]).*/g,"").replace(/(\.[^\.]*).*\.+/g,"$1");

	//소수점이 있을 경우,
	if(val.indexOf(".")>-1){
		//소수점 f개 이하 정규식
		var regUnder = new RegExp('/.*\.[0-9]{0,'+f+'}$/g');

		//정규식에 부합하지 않을 경우,
		if(!(regUnder).test(val)){
			//소수점 이하 f자리 까지만 자르기
			val = val.substring(0,val.indexOf(".")+Number(f+1));

			//정수부분이 i개보다 많을 경우, i개로 자르기
			if($.trim(val.split(".")[0]).length > i){
				val = val.split(".")[0].substring(0,i)+"."+val.split(".")[1];
			}
		}
	}else{
	//소수점이 없을 경우,
		//정수부분 i개 이하 정규식
		var regFront = new RegExp('/^[1-9]{1}\d{0,'+Number(i-1)+'}(\.\d+)?$/g');
		//정규식에 부합하지 않을 경우, 자르기
		if(!(regFront).test(val)){
			val = val.substring(0,i);
		}
	}

	obj.value = val;
}

/**
 * 소수점으로 끝나는 숫자 소수점 제거(onblur)
 */
function fncDelLstDot(obj){
	obj.value = obj.value.replace(/(.*)\.+$/g,"$1");
}

/*날짜 유효성 체크 [검증필요] */
function fncCheckValidDate(value) {
	var result = true;
	try {
	    var date = value.split("-");
	    var y = parseInt(date[0], 10),
	        m = parseInt(date[1], 10),
	        d = parseInt(date[2], 10);

	    var dateRegex = /^(?=\d)(?:(?:31(?!.(?:0?[2469]|11))|(?:30|29)(?!.0?2)|29(?=.0?2.(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(?:\x20|$))|(?:2[0-8]|1\d|0?[1-9]))([-.\/])(?:1[012]|0?[1-9])\1(?:1[6-9]|[2-9]\d)?\d\d(?:(?=\x20\d)\x20|$))?(((0?[1-9]|1[012])(:[0-5]\d){0,2}(\x20[AP]M))|([01]\d|2[0-3])(:[0-5]\d){1,2})?$/;
	    result = dateRegex.test(d+'-'+m+'-'+y);
	} catch (err) {
		result = false;
	}
    return result;
}


/**
 * 특수문자 포함여부 체크
 */
function spStrCheck(str) {
	var spPattern = /[~!@#$%^&*_+|<>?:{}'"\\;=-]/g; // 특수문자 정규식
	str = str.trim();

	// 특수문자 체크
	if(spPattern.test(str)) {
		return true; // 특수문자 존재
	}else {
		return false;
	}
}



/**
 * 특수문자 제거
 */
function removeSpStr(str) {
	var spPattern = /[~!@#$%^&*_+|<>?:{}'"\\;=-]/g; // 특수문자 정규식
	str = str.trim();

	// 특수문자 체크
	if(spPattern.test(str)) {
		return str.replace(spPattern, ""); // 특수문자 제거
	}else {
		return str;
	}
}

//특수문자 제거
function checkCharValid(obj){
    var RegExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;	//정규식 구문
    if (RegExp.test(obj.value)) {
      alert("특수문자는 입력하실 수 없습니다.");
	  //alert("Special characters cannot be allowed");
      // 특수문자 모두 제거
      obj.value = obj.value.replace(RegExp , '');
    }
  }


/**
 * 입력 글자 최대값
 * onkeyup="cal_byte(this, '200', 'name');"
 */
function cal_byte(Obj, VMax, ObjName) {
	var tmpStr;
	var temp = 0;
	var onechar;
	var tcount = 0;
	var aquery = Obj.value;

	tmpStr = new String(aquery);
	temp = tmpStr.length;

	for (k=0;k<temp;k++) {
		onechar = tmpStr.charAt(k);

		if (escape(onechar).length > 4) {
			tcount += 2;
		} else {
			tcount++;
		}
	}

	if(tcount>VMax) {
		reserve = tcount - VMax;

		alert(ObjName+"은(는) "+VMax+"바이트 이상은 전송하실수 없습니다.\r\n쓰신 메세지는 "+reserve+"바이트가 초과되었습니다.\r\n초과된 부분은 자동으로 삭제됩니다.");

		nets_check(Obj, VMax);
		return false;
	}
	return true;
}

/**
 * 입력 글자 최대값 오버값 삭제
 */
function nets_check(Obj,lsMax){
	 var tmpStr;
	 var temp=0;
	 var onechar;
	 var tcount;
	 tcount = 0;

	 var aquery = Obj.value;

	 tmpStr = new String(aquery);
	 temp = tmpStr.length;

	 for(k=0;k<temp;k++)
	 {
	  onechar = tmpStr.charAt(k);
	  
	  if(escape(onechar).length > 4)
	  {
	   tcount += 2;
	  }
	  else tcount++;
	  /*
	  else if(onechar!='\r')
	  {
	   tcount++;
	  }
	  */
	  if(tcount>lsMax)
	  {
	   tmpStr = tmpStr.substring(0,k-1); 
	   break;
	  }
	 }
	 Obj.focus();
	 Obj.value = tmpStr;
}

/**
 * 입력 글자 최대값
 * onkeyup="cal_length(this, '20', 'name');"
 */
function cal_length(Obj, VMax, ObjName) {
	var tmpStr;
	var length = 0;
	var aquery = Obj.value;

	tmpStr = new String(aquery);
	length = tmpStr.length;

	if(length>VMax) {

		alert(ObjName+" 길이가 초과되었습니다.");
		Obj.value =aquery.substring(0,VMax);
		return false;
	}
	return true;
}

/**
 * 특수문자 포함여부 체크
 * onkeyup="spStrKeyCheck(this);"
 */
function spStrKeyCheck(Obj) {
	var spPattern = /[~!@#$%^&*_+|<>?:{}'"\\;=-]/g; // 특수문자 정규식
	var value = Obj.value.trim();

	// 특수문자 체크
	if(spPattern.test(value)) {
		alert("특수문자는 입력할 수 없습니다.");
		Obj.value = value.replace(spPattern, ""); // 특수문자 제거
		this.focus();
	}
}

/**
 * 문자열을 받아서 null이면 대체할 문자열을 리턴
 * @param val
 * @param str
 * @return rtnStr
 * */
function Nullchk(val, str) {
	if(val == null || val == "" ||  val == "null" ||  val == "0"){
		return str;
	}else{
		return val;
	}
}

function setEmpty(elementId) {
	$("#" + elementId).empty();
}

function setText(elementId, text) {
	$("#" + elementId).text(text);
}

function tmpl(templateId, data, toWhere) {
	$("#" + templateId).tmpl(data).appendTo("#" + toWhere);
}

// 주어진 매개변수를 대문자 처리해서 반환
function capitalize(str) {
	return str.replace(/\b[a-z]/g, char => char.toUpperCase());
}

//날짜 input 속성 셋팅(with validate)
function fncSetDefaultValidDy(param) {
	var attrNm = "dy";
	if ($.trim(param) != "") {
		attrNm = param;

	}

	$("." + attrNm).attr("maxlength", "8");
	$("." + attrNm).attr("onblur", "fncSetDateFormatVailed(this)");
	$("." + attrNm).attr("oninput", "this.value = this.value.replace(/[^0-9]/g, '')");
	$("." + attrNm).attr("onfocus", "fncRemainOnlyNumber(this)");
}

//앞에붙은 0 지우기
function fncDelFrontZero(obj) {
	obj.value = obj.value.replace(/^0+([0-9]+)/g, "$1");

}

//금액 input 속성 셋팅
function fncSetDefaultAmt(param) {
	var attrNm = "amt";
	if ($.trim(param) != "") {
		attrNm = param;
	}

	$("." + attrNm).attr("onblur", "setNumberComma(this)");
	$("." + attrNm).attr("onkeyup", "fncDelFrontZero(this)");
	$("." + attrNm).attr("oninput", "this.value = this.value.replace(/[^0-9]/g, '')");
	$("." + attrNm).attr("onfocus", "initNumber(this)");
	$("." + attrNm).attr("style", "text-align:right;");
}

//핸드폰번호 oninput
function fncSetDefInputHpNo(obj) {
	obj.value = obj.value.replace(/[^0-9]/g, '').replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, '$1-$2-$3').replace('--', '-');
}

//핸드폰번호 onkeyup
function getHpNoFmt(hpNo){
	hpNo = $.trim(hpNo).replace(/[^0-9]/g, '').replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, '$1-$2-$3').replace('--', '-');
	return hpNo;
}

/* jqGrid 포커스 이동 event
* mvGbn : scroll 위치 구분 (미 입력 시, rowId 입력 필수)
* gridId : 대상 그리드 id (필수)
* rowId : focus를 줄 rowId (mvGbn 미 입력 시, 입력 필수)
*/
function fncMvGridFocus(mvGbn, gridId, rowId) {
	mvGbn = $.trim(mvGbn).toLowerCase();

	var pos;

	if (mvGbn == "top") { //그리드 최상단으로 이동
		pos = 0;
	} else if (mvGbn == "end") { //그리도 최하단으로 이동
		pos = $("#" + gridId).css("height").replace(/px/g,
			"");
	} else { //지정한 row 위치로 이동
		pos = $("#" + gridId).find("#" + rowId).position().top;

	}

	$("#" + gridId).closest(".ui-jqgrid-bdiv").animate({ scrollTop: pos }, 500);


}

//monthpicker option defaultSet
function fncMonPickerSetting() {
	var rtn = {
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], //월 단위
		pattern: 'yyyy-mm', //날짜포맷
		openOnFocus: false //focus시 달력 표시 유무

	};
	return rtn;

}

//연,월 형식체크후 셋팅
function fncSetDataFormatMonValid(obj) {

	var date = $.trim(obj.value).replace(/[^0-9]/g, "");

//날짜값이 6자가 아니면 공백변환(저장 및 조회 시 오류방지 위함)
	if (obj.value.length == 6) {
		let regex = RegExp(/^\d{4}-(0[1-9]|1[012])$/);
		let objVal = obj.value.replace(/(\d{4})(\d{2})/g, "$1-$2");
		if (regex.test(objVal) == false) {
			alert("날짜 형식에 맞도록 입력해 주세요");
			obj.value = "";
			obj.focus();
			return false;
		} else {
			obj.value = objVal;
			return false;
		}
	} else {
		obj.value
			= "";
		return false;
	}
	return true;

}

/**
* 코드 리스트로 Grid SelectBox 구성
* subName : selectBox id/name
* defaultValue : 기본선택 데이터 (def:선택)
* selectedValue
: 선택된 값
* codeList : 그리드용 코드 리스트(string)

*/
function getFmtGridSelectBoxFrStrCodList(subName, defaultValue, selectedValue, codeListStr) {
	var codeId = ""
		, codeName = ""
		, codeData = ""
		, eleHtml = []
		, h = 0
		, retData = ""
		, codeArr = [];

	if (subName == null || subName == '') return retData;

	eleHtml[0] = "<select id='" + subName + "' name='" + subName + "'>";

	if (defaultValue != '') {
		eleHtml[++h] = "<option value=''>" + defaultValue + "</option>\n";
	} else {
		eleHtml[++h] = "<option value=''>" + 선택 + "</option>\n";
	}

	if (codeListStr != null && $.trim(codeListStr) != "") {
		codeArr = codeListStr.split(";");

		for (var i = 0; i < codeArr.length; i++) {
			codeData = codeArr[i].split(":");

			if (codeData == null || codeData.length <= 0) return false;

			codeId = $.trim(codeData[0]);
			codeName = $.trim(codeData[1]) == "" ? "" : codeData[1];

			if (selectedValue != null && selectedValue != "") {
				if (selectedValue == "-1" || selectedValue.toLowerCase() == "last") {
					eleHtml[++h] = (Number(i + 1) == codeArr.length) ? "<option value='" + codeId + "' selected>" + codeName + "</option>" : "<option value='" + codeId + "'>" + codeName + "</option>";
				} else {
					eleHtml[++h] = ($.trim(selectedValue) == $.trim(codeId)) ? "<option value='" + codeId + "' selected>" + codeName + "</option>" : "<option value='" + codeId + "'>" + codeName + "</option>";

				}
			} else {
				eleHtml[++h] = "<option value='" + codeId + "'>" + codeName + "</option>";
			}
		}
	}

	eleHtml[++h] = "</select>";
	retData = eleHtml.join('');


	return retData;

}

/**
* 코드 리스트로 Grid SelectBox 구성 For Email
* defaultValue : 기본선택 데이터 (def:선택)
* selectedValue
: 선택된 값
* codeList : 그리드용 코드 리스트(string)

*/
function getFmtGridSelectBoxFrStrCodListForEmail(defaultValue, selectedValue, codeListStr) {
	var codeId = ""
		, codeName = ""
		, codeData = ""
		, eleHtml = []
		, h = 0
		, retData = ""
		, codeArr = [];

	eleHtml[0] = "<select id='emailBackCd' name='emailBackCd' class='form-control' style='width:45% !important'>";


	if (defaultValue != '') {
		eleHtml[++h] = "<option value=''>" + defaultValue + "</option>\n";
	} else {
		eleHtml[++h] = "<option value=''>" + 선택 + "</option>\n";
	}

	if (codeListStr != null && $.trim(codeListStr) != "") {
		codeArr = codeListStr.split(";");

		for (var i = 0; i < codeArr.length; i++) {
			codeData = codeArr[i].split(":");

			if (codeData == null || codeData.length <= 0) return false;

			codeId = $.trim(codeData[0]);
			codeName = $.trim(codeData[1]) == "" ? "" : codeData[1];

			if (selectedValue != null && selectedValue != "") {
				if (selectedValue == "-1" || selectedValue.toLowerCase() == "last") {
					eleHtml[++h] = (Number(i + 1) == codeArr.length) ? "<option value='" + codeId + "' selected>" + codeName + "</option>" : "<option value='" + codeId + "'>" + codeName + "</option>";
				} else {
					//메일은 코드값이 아닌 Email 주소로 비교하기 때문에 codeName셋팅
					eleHtml[++h] = ($.trim(selectedValue) == $.trim(codeName)) ? "<option value='" + codeId + "' selected>" + codeName + "</option>" : "<option value='" + codeId + "'>" + codeName + "</option>";
				}
			} else {
				eleHtml[++h] = "<option value='" + codeId + "'>" + codeName + "</option>";
			}

		}
	}

	eleHtml[++h] = "</select>";
	retData = eleHtml.join('');

	return retData;
}


/**
* 지점 코드 조회
* subName : selectBox 명 (필수)
* defaultValue : 기본 셋팅 값
* selectedVlue
: 선택 셋팅 값
* paramPostnBrnchCd : 조회조건_지점코드(선택)
* paramDivcd : 조회조건_사업부코드(선택)

*/
function getPostnBrnchCdCodeListToSelBox(subName, defaultValue, selectedValue, paramPostnBrnchCd, paramDivcd) {
	var str = {};

	var postnArr = []
		, divcdArr = [];

	if (!!paramPostnBrnchCd) {
		postnArr = $.trim(paramPostnBrnchCd).split(",");
		if (postnArr.length == 1) {
			str.srchPostnBrnchCd = paramPostnBrnchCd;
		} else {
			str.srchPostnBrnchCdList = postnArr;
		}

	}

	if (!!paramDivcd) {
		divcdArr = $.trim(paramDivcd).split(",");


		if (divcdArr.length == 1) {
			str.srchDivcd = paramDivcd;
		} else {
			str.srchDivcdList = divcdArr;
		}

	}


	var url = __context + '/app/common/selectCodeListPostnBrnchCd.json';

	$.ajaxSetup({
		contentType: "application/json; charset=utf-8"
	});

	$.post(url
		, JSON.stringify(str)
		, function(data, status) {

			if (status == 'success') {
				$("select[name='" + subName + "'] option").remove();
				_setCustomCodeSelectBoxValue(data, subName, defaultValue, selectedValue);

			}
		}, 'json');
}

/**
* LEFT padding
* str
: 입력 문자열
* padLen : 패딩처리할 길이
* padStr : 패딩용 문자
*/
function lpad(str, padLen, padStr) {
//입력한 문자열의 길이가 패딩처리할 길이보다작으면 문자열을 그대로 반환
	if (padStr.length > padLen) {
		return str;
	}

	//문자열로
	str += "";
	padStr +=
		"";

	//입력한 문자열의 길이가 패딩처리할 길이보다 작으면 반복하여 패딩문자열로 채워줌
	while (str.length < padLen) {
		str = padStr + str;
	}

	//패딩처리할 길이보다 문자열의 길이가 클 경우, 패딩처리할 길이만큼만 잘라서 반환
	str = str.length >= padLen ? str.substring(0, padLen) : str;

	return str;
}

/**
* onkeyup용 left padding

*/
function lpadForKeyup(obj, padLen, padStr) {
	var str = $.trim($(obj).val());

	//입력한 문자열이 없으면 빈값 반환
	if ($.trim(str).length < 1) {
		return "";
	}

	//입력한 문자열의 길이가 패딩처리할 길이보다 작으면 문자열을 그대로 반환
	if (padStr.length > padLen) {

		return str;

	}

	//문자열로
	str += "";
	padStr += "";

	//입력한 문자열의 길이가 패딩처리할 길이보다 작으면 반복하여 패딩문자열로 채워줌
	while (str.length < padLen) {
		str = padStr + str;
	}


	//패딩처리할 길이보다 문자열의 길이가 클 경우, 패딩처리할 길이만큼만 잘라서 반환
	str = str.length >= padLen ? str.substring(0, padLen) : str;

	obj.value = str;

}

//시간 형식 체크 for onkeyup
function fncChkTmForKeyup(obj, gbn) {
	var strTm = $.trim(obj.value);
	var regExp;

	if ($.trim(gbn) == "12H") {
		regExp = /^([1-9]|0[1-9]|1[0-2])$/;
	} else if ($.trim(gbn) == "H") {
		regExp = /^([0-9]|[0-1][0-9]|2[0-3])$/;
	} else if ($.trim(gbn) == "M") {
		regExp = /^([0-5][0-9]|[0-9])$/;

	}

	if (obj.value.length == 1 || obj.value.length == 2) {
		if (!regExp.test(strTm)) {
			alert("시간형식에 맞도록 입력해주세요.");
			obj.value = "";
			obj.focus();
		} else {
			obj.value = obj.value;
		}
	} else {
		obj.value = "";
	}
}

//Unformat - 숫자만(없을경우, 0)
function unfmtCellDataOnlyNum(cellvalue, options, cell) {
	var retData = $.trim($('input', cell).val()).replace(/[^0-9]/g, "");
	return $.trim(retData) == "" ? "0" : retData;
}

//Formatter-계좌번호
function fmtSetInputAcntNo(cellvalue, options, rowObject) {
	var rowId = options.rowId;
	var colNm = $.trim(options.colModel.name);
	var cellVal = $.trim(cellvalue) == "" ? "" : cellvalue;
	var retData = "";

	if (rowId.indexOf("blankRow") > -1) return retData;

	retData = "<input type='text' id='" + colNm + "_" + rowId + "' name='" + colNm + "' value='" + cellVal + "' style='width:100%' maxlength='16' oninput='this.value = this.value.replace(/[^0-9]/g, \"\")' onkeyup='chkByte(this, 16)'/>";


	return retData;

}

/**
* 특수문자 포함여부 체크
* onkeyup="fncSpStrKeyCheck(this);"
*/
function fncSpStrKeyCheck(Obj) {
	var spPattern = /[^\w\sㄱ-힣]|[\_]/g; // 특수문자 정규식
	var value = Obj.value.trim();

	// 특수문자 체크
	if (spPattern.test(value)) {
		alert("특수문자는 입력할 수 없습니다.");
		Obj.value = value.replace(spPattern, ""); // 특수문자 제거
		this.focus();

	}
}

//escape xml entity
function escapeXml(unsafe) {
	if ($.trim(unsafe) != "") {
		return unsafe.replace(/[<>&\"\'/]/g,
			function(c) {
				switch (c) {
					case '<': return '<';
					case '>': return '>';
					case '&': return '&';
					case '\'': return '';
					case '"': return '"';
					case '/': return '/';
				}
			});
	}


}

//check Empty object
function isEmptyObj(obj) {
	if (obj.constructor === Object
		&& Object.keys(obj).length === 0) {
		return true;
	}

	return false;
}


//받은 입력값 특수문자 변환 처리
function unEscapeXml(pValue) {
	if(pValue){
	    var strReturenValue = "";
	    strReturenValue = pValue.replace(/&amp;/gi, '&')
	    .replace(/&lt;/gi, '<')
	    .replace(/&gt;/gi, '>')
	    .replace(/&quot;/gi, '"')
	    .replace(/&apos;/gi, '\'')
	    .replace(/&nbsp;/gi, ' ')
	    .replace(/&#39;/gi, "'")
	    .replace(/&#40;/gi, "\\(")
	    .replace(/&#41;/gi, "\\)");
	    return strReturenValue;
    }else{
		return pValue;
	}
}

/** jqGrid의 검색조건(postData)을 해당 form에 append */
function fnSetPostData($form) {
	$.each($('#tabList').getGridParam('postData'), function(key, value) {
		var arr = ['sidx', 'sord', 'rows', 'page'];

		if (arr.indexOf(key) > -1 || key.indexOf('srch') == 0) {
			var $ipt = $form.find('input[name=' + key + ']');
			if ($ipt.length > 0) {
				$ipt.remove();
			}
			if (value) {
				$form.append($('<input type="hidden" name="' + key + '" value="' + value + '">'));
			}
		}
	});
}

/** form의 검색조건(postData)을 remove */
function fnRemovePostData($form) {
	$.each($form.find("input[type='hidden']"), function() {
		var arr = ['sidx', 'sord', 'rows', 'page'];
		var name = this.name;

		if (arr.indexOf(name) > -1 || name.indexOf('srch') == 0) {
			var $ipt = $(this);
			if ($ipt.length > 0) {
				$ipt.remove();
			}
		}
	});
}

/** editor의 HTML을 보정하여 반환(html, body, input 태그 등) */
function editorMakeHtml(html) {
	// html 태그가 없으면 삽임
	if (html.indexOf("<html") < 0) {
		html = "<html>" + html + "</html>";
	}
	// body 태그가 없으면 삽입
	let body = '<body style="font-family: \'Malgun Gothic\', \'맑은 고딕\', Dotum,\'돋움\', sans-serif !important;">';
	if (html.indexOf("<body") < 0) {
		// body 여는 태그 삽입
		let idx = html.indexOf("</style>") + 8;
		let front = html.substring(0, idx);
		let back = html.substring(idx);
		html = front + body + back;

		// body 닫는 태그 삽입
		idx = html.indexOf("</html>");
		front = html.substring(0, idx);
		back = html.substring(idx);
		html = front + "</body>" + back;
	}

	// input 닫는태그 >인것 />로 변환
	let idx = 0;
	let inputStIdx = html.indexOf("<input", idx);
	let inputEndIdx = 0;
	let inputHtml = "";
	while (inputStIdx > -1) {
		inputEndIdx = html.indexOf(">", inputStIdx);
		inputHtml = html.substring(inputStIdx, inputEndIdx + 1);
		if (inputHtml.indexOf("/>") < 0) {
			inputHtml = html.substring(inputStIdx, inputEndIdx) + "/>";
			html = html.substring(0, inputStIdx) + inputHtml + html.substring(inputEndIdx + 1);
			inputEndIdx += 1;
		}
		idx = inputEndIdx + 1;
		inputStIdx = html.indexOf("<input", idx);
	}
	return html;
}

/**
* 코드 리스트로 Grid RadioButton 구성
* subName : object id
* selectedValue: 선택된 값
* defFieldYn : 선택안함 필드 사용여부
* codeList : 그리드용 코드 리스트(string)
*/
function getFmtGridRadioButtonFrStrCodList(subName, selectedValue, defFieldYn, codeListStr){
	var codeId = ""
		, codeName = ""
		, codeData = ""
		, eleHtml = []
		, h = 0
		, retData = ""
		, codeArr = [];
		
	if (subName == null || subName == '') return retData;
	
	if (codeListStr != null && $.trim(codeListStr) != "") {
		if($.trim(defFieldYn).toUpperCase() != "Y"){
			codeListStr = codeListStr.slice(codeListStr.indexOf(";") + 1);
		}
		
		codeArr = codeListStr.split(";");
		
		for(var i = 0; i < codeArr.length; i++){
			codeData = codeArr[i].split(":");
			
			if(codeData == null | codeData.length <= 0) return false;
			
			codeId = $.trim(codeData[0]);
			codeName = $.trim(codeData[1]) == "" ? "" : codeData[1];
			
			if ($.trim(selectedValue) != "" && $.trim(codeId) == $.trim(selectedValue)) {
				eleHtml[++h] = "<input type='radio' id='"+subName+"_"+Number(i + 1)+"' name='"+subName+"' value='"+codeId+"' style='margin-right:5px;vertical-align:bottom;' checked/>";
			} else {
				eleHtml[++h] = "<input type='radio' id='"+subName+"_"+Number(i + 1)+"' name='"+subName+"' value='"+codeId+"' style='margin-right:5px;vertical-align:bottom;'/>";
			}
			eleHtml[++h] = "<label for='"+subName+"_"+Number(i + 1)+"' style='margin-right:10px;'>"+codeName+"</label>";
		}
	}
	
	retData = eleHtml.join('');
	return retData;
}

/**
 * 상품카테고리 셀렉트박스 조회
 * subName: selectBox명
 * defaultValue
 * selectedValue
 * srchSaleChnnCd :판매채널명
 * srchLvl :대분류/중분류 구분 (1:대분류,2:중분류)
 * srchMainProdlCatCd :대분류 코드
 */
function getCustomSubCategoryListToSelBox(subName, defaultValue, selectedValue,srchSaleChnnCd,srchLvl,srchMainProdlCatCd) {
	var str = {
		srchSaleChnnCd : $.trim(srchSaleChnnCd)
		,srchLvl : $.trim(srchLvl)
		,srchMainProdlCatCd:$.trim(srchMainProdlCatCd)
	};

	var url =  __context + '/app/common/selCustomTagCategoryList.json';

	$.ajaxSetup({
		contentType: "application/json; charset=utf-8"
	});

	$.post(	 url
		,JSON.stringify(str)
		,function(data,status){

			if(status == 'success'){
				$("select[name='"+subName+"'] option").remove();
				_setCustomCodeSelectBoxValue(data,subName,defaultValue, selectedValue);

			}
		}, 'json');
}

/**
 * 팀 셀렉트박스 조회
 * subName: selectBox명
 * defaultValue : 기본 name
 * selectedValue : 셋팅 value
 * srchWorkType : 조회유형구분
 * srchTeamDivnCd : 팀유형구분코드
 * srchSaleChnnCd : 채널코드
 * srchMainProdlCatCd : 카테고리대분류
 * srchMainProdmCatCd : 카테고리중분류
 */
function getCustomTlsTeamListToSelBox(subName, defaultValue, selectedValue, srchWorkType, srchTeamDivnCd, srchSaleChnnCd, srchMainProdlCatCd, srchMainProdmCatCd){
	var str = {
		srchWorkType : $.trim(srchWorkType)
		, srchTeamDivnCd : $.trim(srchTeamDivnCd)
		, srchSaleChnnCd : $.trim(srchSaleChnnCd)
		, srchMainProdlCatCd : $.trim(srchMainProdlCatCd)
		, srchMainProdmCatCd : $.trim(srchMainProdmCatCd)
	};
	
	var url =  __context + '/app/common/selCustomTagTeamInfoList.json';

	$.ajaxSetup({
		contentType: "application/json; charset=utf-8"
	});

	$.post(	 url
		,JSON.stringify(str)
		,function(data,status){

			if(status == 'success'){
				$("select[name='"+subName+"'] option").remove();
				_setCustomCodeSelectBoxValue(data,subName,defaultValue, selectedValue);

			}
		}, 'json');
}

/**
 * 팀 정보 text 조회
 * subStrId : text 영역아이디
 * subValId : value 영역아이디
 * srchWorkType : 조회유형구분
 * srchTeamDivnCd : 팀유형구분코드
 * srchSaleChnnCd : 채널코드
 * srchMainProdlCatCd : 카테고리대분류
 * srchMainProdmCatCd : 카테고리중분류
 */
function getCustomTlsTeamListToTxt(subStrId, subValId, srchWorkType, srchTeamDivnCd, srchSaleChnnCd, srchMainProdlCatCd, srchMainProdmCatCd){
	var str = {
		srchWorkType : $.trim(srchWorkType)
		, srchTeamDivnCd : $.trim(srchTeamDivnCd)
		, srchSaleChnnCd : $.trim(srchSaleChnnCd)
		, srchMainProdlCatCd : $.trim(srchMainProdlCatCd)
		, srchMainProdmCatCd : $.trim(srchMainProdmCatCd)
	};
	
	var url =  __context + '/app/common/selCustomTagTeamInfoList.json';

	$.ajaxSetup({
		contentType: "application/json; charset=utf-8"
	});

	$.post(	 url
		,JSON.stringify(str)
		,function(data,status){

			if(status == 'success'){
				_setCustomCodeTxtValue(data,subStrId,subValId);

			}
		}, 'json');
}

/**
 * custom code list text 셋팅
 */
function _setCustomCodeTxtValue(data, subStrId, subValId){
	var sz = data.length
		,eleTxtHtml = []
		,eleValHtml = []
		, h = 0;
	
	if(data != null && data != ""){
		for(var k=0;k<sz;k++){
			eleTxtHtml[++h] = data[k].CODE_NAME;
			eleValHtml[++h] = data[k].CODE_ID;
		}
	}else{
		$("#"+subStrId).text("-");
		if(subValId != null && subValId != ""){
			$("#"+subValId).val("");
		}
	}
	
	if(sz<=0){
		$("#"+subStrId).text("-");
		if(subValId != null && subValId != ""){
			$("#"+subValId).val("");
		}
		return;
	}
	
	$("#"+subStrId).text(eleTxtHtml.join(',').slice(1).replace(/,+/g,","));
	if(subValId != null && subValId != ""){
		$("#"+subValId).val(eleTxtHtml.join(',').slice(1).replace(/,+/g,","));
	}
}

/**
 * Custom String to SelectBox
 * - 커스텀 셀렉트박스 구성(문자열 to 셀렉트박스)
 */
function setCustomTagStrCodeListToSelBox(strData, subName, defaultValue,selectedValue){
	var sz = $.trim(strData).length
	,eleHtml = []
	, h = 0;
	
	if(defaultValue != ''){
		eleHtml[0] = "<option value=''>"+defaultValue+"</option>"+"\n";
	}else{
		eleHtml[0] = "<option value=''>전체</option>"+"\n";
	}
	
	if($.trim(strData) == "" || sz <= 0){
		$("select[name='"+subName+"']").append(eleHtml.join(''));
		return;
	}
	
	var codeList = strData.split(";");
	
	if(codeList == null || codeList.length <= 0){
		$("select[name='"+subName+"']").append(eleHtml.join(''));
		return;
	}

	var codeId = "";
	var codeName = "";
	for(var i=0; i<codeList.length; i++){
		codeId = $.trim(codeList[i].split(":")[0]);
		codeName = codeList[i].split(":")[1];
		eleHtml[++h] = "<option value='"+codeId+"'>"+codeName+"</option>"+"\n";
	}
	
	$("select[name='"+subName+"']").append(eleHtml.join(''));
	
	if(selectedValue != null && selectedValue != ''){
		if(selectedValue == '-1'){
			$("select[name='"+subName+"'] option:last").attr('selected','selected');
		}else{
			$("select[name='"+subName+"']").val(selectedValue);
		}
	}
	
}

//커서 해당 위치로 이동
function setRightFocus(obj, pPos) {
    
    pPos = obj.value.length-pPos;
    if(obj.setSelectionRange) 
    {
      obj.focus();
      obj.setSelectionRange(pPos,pPos);
    } 
    else if(obj.createTextRange) 
    {
      var range = obj.createTextRange(); 
      range.collapse(true);
      range.moveEnd('character', pPos);
      range.moveStart('character', pPos); 
      range.select();
    }
  }
  
  
  
/** excel download  */
function exportExcel(pGridObj, pFileName) {
	/* Get Data (loadComplete) */
	const rows = pGridObj.getRowData();
	const colNames = pGridObj.jqGrid('getGridParam', 'colNames');
	const colModel = pGridObj.jqGrid('getGridParam', 'colModel');
	const headData = [];

	/* Sort Data (Header) */
	colModel.forEach((col, index) => {

		/* Exclude No Data */
		for (let i = 0; i < rows.length; i++) {
			const row = rows[i];

			if (!col.hidden && col.index !== undefined && col.index != 'Check_Box' && row[col.index] !== undefined) {
				headData.push({
					key: col.index,
					name: colNames[index]
				});
				break;
			}
		}
	});

	/* Set Table  */
	let excel = '<table border="1px"><tr>';

	/* thead */
	for (let key in headData) {
		excel += `<th>${headData[key].name}</th>`;
	}
	excel += '</tr>';

	/* tbody */
	rows.forEach(row => {
		excel += '<tr>';

		for (let key in headData) {
			const rowKey = headData[key].key;
			let cell = row[rowKey];
			
			/* check falsy value*/
            if (cell === null || cell === undefined) cell = '';

			excel += `<td style="mso-number-format:\\@">${String(cell)}</td>`;
		}
		excel += '</tr>';
	});

	excel += "</table>";

	/* DownLoad */
	const dataType = 'data:application/vnd.ms-excel;charset=utf-8';
	const tableHtml = encodeURIComponent(excel);
	const a = document.createElement('a');
	a.href = dataType + ',%EF%BB%BF' + tableHtml;
	a.download = pFileName + '.xls';
	a.click();
}