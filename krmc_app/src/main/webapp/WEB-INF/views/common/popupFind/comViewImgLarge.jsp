<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<%--
	ClassName :  comViewImgLarge.jsp
	Description : 이미지 확대 보기 팝업
	Modification information
	수정일 			수정자					수정내용
	--------- 	----------------		-----------------
	2023.01.12		안석진				 최초 작성
	
	Author 	: asj
	Since 	: 2023.01
	
--%>
<spring:message code="common.select" var="select" />
<style>
.imgSlide{
	width:640px;
	height:480px;
	overflow:hidden;
	position:relative;
	margin:0 auto;
}

.imgSlide ul{
	width:1920px;
	position:absolute;
	top:0;
	left:0;
	font-size:0;
}

.imgSlide ul li{
	display:inline-block;
}

.back{
	position: absolute;
    top: 43%;
    left: -5px;
    cursor: pointer;
    z-index: 1;
    font-size: 75px;
}

.next{
	position: absolute;
    top: 43%;
    left: 87%;
    cursor: pointer;
    z-index: 1;
    font-size: 75px;
}

.arrow-prev,
.arrow-next {
    /* position: relative; */
    float:left;
    /* border:1px solid #000; */
    width:100px;
    height:100px;
    margin-right:5px;
}

.arrow-prev::after {
    position: absolute;
    left: 35px; 
    top: 20px;
    content: '';
    width: 50px; /* 사이즈 */
    height: 50px; /* 사이즈 */
    border-top: 5px solid #bd86a3; /* 선 두께 */
    border-right: 5px solid #bd86a3; /* 선 두께 */
    transform: rotate(225deg); /* 각도 */
}

.arrow-next::after {
    position: absolute;
    left: 10px; 
    top: 20px; 
    content: '';
    width: 50px; /* 사이즈 */
    height: 50px; /* 사이즈 */
    border-top: 5px solid #bd86a3; /* 선 두께 */
    border-right: 5px solid #bd86a3; /* 선 두께 */
    transform: rotate(45deg); /* 각도 */
}

</style>
<script type="text/javascript">


var ${param.popupId} = (function(){

	${param.popupId}_m = {};
	const _AUTO_MODE = 0x01;	// 부모창에서 key가 되는 값을 넘겨 검색했을 때 결과가 1개인 경우 자동으로 팝업 닫히고 부모창에 검색 결과를 넘겨줌
	const _MANUAL_MODE = 0x02;	// 자동으로 팝업이 닫히지 않음

	${param.popupId}_m._id = "${param.popupId}Layer";	
	${param.popupId}_m._POP_MODE = _MANUAL_MODE;
	${param.popupId}_m.callbackFuncNm = "";
	${param.popupId}_m._callback = false;
	${param.popupId}_m._callbackFunc = function(){};
	${param.popupId}_m._isManual = false;									// 사용자가 버튼을 눌러 검색했는지의 여부
	${param.popupId}_m._isRtnOneData="";
	
	${param.popupId}_m._imgs;
	${param.popupId}_m._img_cnt;
	${param.popupId}_m._img_position =1;
	${param.popupId}_m._img_selIdx =1;
	//팝업속성 지정
	${param.popupId}_m._setConfig = function(arg){

		arg.css({
			"top" : $(window).scrollTop() + 10
			, "left": '40%'
			, "margin-left": '-228px'
			, "width": '800px'
		});

		var popCnt = 0;
		$(".pop_layer_new").each(function (){
			if($(this).css('display') === 'block'){
				popCnt++
			}
		});

		// 팝업레이어 및 백그라운드 활성
		$('#'+${param.popupId}_m._id).show();
		$(".pop_layer_new").each(function (){
			if($(this).prop("id") != ${param.popupId}_m._id && $(this).css('display') === 'block'){
				$(this).css('z-index', Number($(this).css('z-index')) - 1000);
			}
		});
		if(popCnt == 0){
			$('#opacity').show();
		}

		// 레이어 드레그 Event
		arg.draggable({
			handle: $('#'+${param.popupId}_m._id + " h1")
			,cancle: $('#'+${param.popupId}_m._id + " a.close")
			,containment: '#'+${param.popupId}_m._id + " #opacity"
			,scroll: false
		});

		// 레이어 닫기버튼 Click Event
		$('#'+${param.popupId}_m._id).find('#${param.popupId}_btnClose').unbind().click(function(e){
			${param.popupId}_m._closeLayer();
		});
	};

	// 레이어호출 이벤트
	${param.popupId}_m.fnOpenLayer = function(callbackFunc, opt, isRtnOneData,isMultiSelect) {
		${param.popupId}_m._fncInitForm();

		if (callbackFunc && typeof callbackFunc === 'function'){
			${param.popupId}_m._callback = true;
			${param.popupId}_m._callbackFunc = callbackFunc;
		}else{
			${param.popupId}_m._callback = false;
			${param.popupId}_m._callbackFunc = function(){};
		}

		if (opt){
			var _optKey = Object.keys(opt);
			for (var key in _optKey){
				$('#'+${param.popupId}_m._id).find("#${param.popupId}_"+_optKey[key]).val(opt[_optKey[key]]);
			}
			
			${param.popupId}_m._eventSearch(opt["imgList"]);
			${param.popupId}_m._img_selIdx = opt["selIdx"];
		}
		
		// 팝업레이어 특성셋팅
		${param.popupId}_m._setConfig($('#'+${param.popupId}_m._id));

		// 버튼이벤트
		$('#'+${param.popupId}_m._id).find('#${param.popupId}_back').unbind().click(function (){${param.popupId}_m._fncMoveBack(this)});
		$('#'+${param.popupId}_m._id).find('#${param.popupId}_next').unbind().click(function (){${param.popupId}_m._fncMoveNext(this)});
		
		
	};

	// 폼 초기화
	${param.popupId}_m._fncInitForm = function() {
		// data 초기화
		
		$("#${param.popupId}_appliNo").val("");
		$("#${param.popupId}_appliFileDivnCd").val("");
		
		${param.popupId}_m._img_position =1;
		${param.popupId}_m._img_selIdx =1;
	};

	// 이미지 리스트 조회
	${param.popupId}_m._eventSearch = function(elem) {

		if(elem && typeof elem !== undefined){
			${param.popupId}_m._isManual = true;
		}else{
			${param.popupId}_m._isManual = false;
		}
		var imgList = elem;
		if(imgList.length>0){
			var param ={};
			var atchFileIds = [];
			for(var i=0; i<imgList.length; i++){
				atchFileIds.push(imgList[i].atchFileId);
			}
			
			param.atchFileIds = atchFileIds;
			
			// 조회 이벤트 json 실행---------------------------
	 		$.ajax({
	 		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	 		      url  : "<c:url value='/web/common/fileDownloadListAtchFileIds'/>",
	 		      data : JSON.stringify(param),
	 	 	     success : function(data){
		 	    	 
	 	 	    	 if(data!=null){
	 	 	    		//이미지 리스트 세팅 >> 각 업무별 조회 후 템플릿 셋팅
	 	 	    		for(var i=0; i<data.length; i++){
	 	 	    			data[i].filePathNm = data[i].atchFileId+"_"+data[i].seq;
	 	 	    			data[i].pgAccessGbn = pgAccessGbn;
	 	 	    		}
		 	    		$("#${param.popupId}_imgUl").empty();
	 	 	    		$("#${param.popupId}_imgList").tmpl(data).appendTo("#${param.popupId}_imgUl");
	 	 	    		${param.popupId}_m._imgs = $(".imgSlide ul");
	 	 	    		${param.popupId}_m._imgs.attr("style","left:0px;");
	 		 	   		${param.popupId}_m._img_cnt = ${param.popupId}_m._imgs.children().length;
	 		 	   		${param.popupId}_m._fncImgPosInit();
	 	 	    	 }else{
	 	  	    		alert("<spring:message code='message.error.process'/>");		//처리 중 오류가 발생하였습니다.
	 	  	    	}
	 	 	     }
	 		});
	 		//----------------------------------------------
			
		}
		
	};
	
	${param.popupId}_m._fncImgPosInit= function(){
		var pos = 640*${param.popupId}_m._img_selIdx;
		
		if(${param.popupId}_m._img_selIdx>0){
			
			${param.popupId}_m._imgs.animate({
				left: '-='+pos+'px'
			});
			${param.popupId}_m._img_position+=${param.popupId}_m._img_selIdx;
		}
		
	};
	
	${param.popupId}_m._fncMoveBack = function(obj){
		if(1<${param.popupId}_m._img_position){
			${param.popupId}_m._imgs.animate({
				left: '+=640px'
			});
			${param.popupId}_m._img_position--;
		}
	};
	
	${param.popupId}_m._fncMoveNext = function(obj){
		if(${param.popupId}_m._img_cnt > ${param.popupId}_m._img_position){
			${param.popupId}_m._imgs.animate({
				left: '-=640px'
			});
			${param.popupId}_m._img_position++;
		}
	};
     
	// 팝업레이어 닫기
	${param.popupId}_m._closeLayer = function() {

		$(".pop_layer_new").each(function (){
			if($(this).prop("id") != ${param.popupId}_m._id && $(this).css('display') === 'block'){
				$(this).css('z-index', Number($(this).css('z-index')) + 1000);
			}
		});
		${param.popupId}_m._fncInitForm();

		// 그리드 데이터 클리어
		$('#'+${param.popupId}_m._id).find("#${param.popupId}_tabList").clearGridData();

		// 팝업레이어 및 백그라운드 숨김
		$('#'+${param.popupId}_m._id).hide();

		var popCnt = 0;
		$(".pop_layer_new").each(function (){
			if($(this).css('display') === 'block'){
				popCnt++
			}
		});

		if(popCnt == 0){
			$('#opacity').hide();
		}

        $("html").css({"overflow": ''});	// 스크롤 생성
	};

	return ${param.popupId}_m;
})();
</script>

<script id="${param.popupId}_imgList"  type="text/x-jquery-tmpl">
<li>
	<img src="<c:url value='/\${pgAccessGbn}/common/\${filePathNm}/loadImageCommon'/>" width="640" height="480"/>
</li>
</script>

<div class="pop_layer_new" id="${param.popupId}Layer" style="display:none;">
    <h1>이미지 보기</h1>
	<a id="${param.popupId}_btnClose" class="close" ><i class="fa fa-times" style="color:white;"></i></a>
	<div id="pop_content" class="open_content">
		<div id="pop_section">
			<form id="${param.popupId}Form" name="${param.popupId}Form" method="post">
				<input type="hidden" id="${param.popupId}_appliNo" name="appliNo"/>
				<input type="hidden" id="${param.popupId}_appliFileDivnCd" name="appliFileDivnCd"/>
				<span id="${param.popupId}_back" class="back arrow-prev"></span>
				<div class="imgSlide">
					<ul id="${param.popupId}_imgUl"></ul>
				</div>
				<span id="${param.popupId}_next" class="next arrow-next"></span>
            </form>
		</div>
	</div>
</div>
