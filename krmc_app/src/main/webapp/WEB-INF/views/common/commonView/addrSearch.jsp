<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<!-- <script language="javascript" src="resources/js/jquery-1.7.1.min.js"></script> -->
<script type="text/javascript" src="/resources/plugins/jQuery/jquery-3.6.0.min.js"></script>

<spring:eval expression="@config['system.env']"		var="systemEnv" />

<style>
 	.logo-div {
		display: table;
		height:50px;
		width:100%;
		background-color: #F1F1F1;
	}
	
	.logo-img {
		display: table-cell;
		text-align: right;
		vertical-align: middle;
	}
	
	.logo-span {
		display: table-cell;
		text-align: left;
		vertical-align: middle;
	
	}
	
	.logo-span-text {
		margin-left: 30px;
		font-size: 20px;
	}

		
	
	.textAddr {
		border:1px solid #d5dee8;
		height:30px;
		width:100%;
		padding:0 8px;
		border-radius:4px;
		-webkit-appearance:none;
		vertical-align:middle;
		font-family:/* 'Source Sans Pro', */'Helvetica Neue',Helvetica,Arial,sans-serif;
		box-sizing : border-box;
		font-size:13px;
		color:#656565;
	}
	
	.buttonAddr {
		position:relative;
		top:0px;
		padding:0 10px;
		display:inline-block; 
		line-height:25px;
		height:25px;
	 	min-width:30px;
		width:auto;
		border:1px solid #c7c7c7;
		-webkit-border-radius:4px;
		-moz-border-radius:4px;
		border-radius:4px;
		margin:0;
		background-color:#ffffff;
		-webkit-transition: all 0.2s ease-in-out;
		-moz-transition: all 0.2s ease-in-out;
		-ms-transition: all 0.2s ease-in-out;
		-o-transition: all 0.2s ease-in-out;
		transition: all 0.2s ease-in-out;
		font-weight:600; 
		text-align:center; 
		vertical-align:middle;
		font-size:13px;
		color:#666;
		cursor:pointer;
	}
	
	ul li{
		display: inline-block;
		margin-left: 20px;
	}
	
	.table-wrapper span{
		cursor: pointer;
	}
	
	.table-wrapper span:hover{
		text-decoration: underline;
	}
	
	
	table{
		border-collapse: separate;
		border-spacing: 0 20px;
	}
</style>

<script type="text/javascript">	
$(document).ready(function($){
	//엔터키 시작 ------------------------------------------------------------
    $('#keyword').unbind().keydown(function(e) {
    	switch(e.which) {
			case 13 :btnSearchEngin(); break; // enter
			default : return true;
    	}
    	e.preventDefault();
    });
	
	$("#addr2").unbind().keydown(function(e) {
		switch(e.which) {
			case 13 : btnReturn(); break;
			default : return true;
		}
		e.preventDefault();
	})
	
	$("#addrTb").hide();
	
});


//통합검색 list submit
jQuery.list_action = function(){
	var juso = $("#keyword").val();
	if(juso == ""){
		alert("검색어를 입력해주세요.");
		return false;
	}
	
	$("#returnFrm").find("input[type='text']").val("");
	
	$.ajax({
		url :"/web/common/addrSearch_selectAddr.json"
		, type: "post"
 		,data : $("#searchFrm").serialize()
 		,success : function(xmlStr){
 			$("#blank").hide();
 			$("#addrTb").show();
 			$("#ajaxList").html("");
			$("#pageApi").html("");
 			if(xmlStr != null){
 				var lastNum = "";
 				var resultCode = xmlStr.resultCode;
 				var resultMessage = xmlStr.resultMessage;
 				if(resultCode == 1){
     				var dataLen = xmlStr.resultPageLastNum;
					makeList(xmlStr, dataLen);
 				}else if(resultCode == 0){
 					alert(resultMessage);					
 				} else{
 					alert("주소검색 오류");
// 					console.log(resultMessage);				
					$('#addrDtl').attr('style','display:none');
 				}
			} else{
				alert("주소검색 실패.");
				$('#addrDtl').attr('style','display:none');
			}
 		}
	});
};


//tbody list 그리기.
function makeList(data, dataLen){
	var htmlStr = "";
	var k = 1;
	var doroNm ="";
	var jibunNm ="";
	var zipSub = "";
	var maxLength = 1000;
	var dataList = data.resultList;
	var totalCnt = data.resultPageTotalCnt;

	if(totalCnt >= maxLength){
		alert("검색조건이 많습니다. \n주소를 상세히 입력해 주세요.");
		return false;
	}else {
		if(dataList.length > 0){		
			for(var i=0; i <dataList.length; i++){
				// 주소검색 검색엔진 로컬 접속 IP주소와 개발/운영 접속 IP주소 다름 -> 반환 변수명 다름
				if("${systemEnv}" == "LOCAL"){
					jibunNm = dataList[i].ADRB1+" "+dataList[i].ADRB3+" "+dataList[i].BLDB;
				} else{
					jibunNm = dataList[i].ADRB4+" "+dataList[i].ADRB3+" "+dataList[i].BLDB;
				}
				
				doroNm = dataList[i].NADRB1+" "+dataList[i].NADRB3+" "+dataList[i].BLDB;
				doroNm2 = dataList[i].NADRB1+" "+dataList[i].NADRB3;
					htmlStr += "<tr>";
					htmlStr += "<td style = 'text-align:center; width:15%'>"+k+"</td>";
					htmlStr += "<td style = 'width:400px;';>";
					htmlStr += "<span id='ck_zipNo' zip_no='"+dataList[i].ZIPB+"' zip_nos='"+dataList[i].ZIPBS+"' doro_nm='"+doroNm+"' bjc='"+dataList[i].BJBC+"' nnmr='"+dataList[i].NNMRB+"' nnmb='"+dataList[i].NNMBB+"' roadPostAddr='"+doroNm+"' zibunPostAddr='"+jibunNm+"' roadYn='Y' class='txt-bold'>"+doroNm+"</span>";
					htmlStr += "<br />";
					htmlStr += "<span id='ck_zipNo' zip_no='"+dataList[i].ZIPB+"' zip_nos='"+dataList[i].ZIPBS+"' doro_nm='"+jibunNm+"' bjc='"+dataList[i].BJBC+"' nnmr='"+dataList[i].NNMRB+"' nnmb='"+dataList[i].NNMBB+"' roadPostAddr='"+doroNm+"' zibunPostAddr='"+jibunNm+"' roadYn='N' class='txt-small'>[지번]"+jibunNm+"</span>";
					htmlStr += "</td>";
					htmlStr += "<td style = 'width:15%; text-align:center;'>"+dataList[i].ZIPB+" </td>";
					htmlStr += "</tr>";
				k++;
			}
		}else {
			htmlStr += "<tr>";
			htmlStr += "<td align='center' colspan = '3'>조회된 정보가 없습니다.</td>";
			htmlStr += "<td></td>";
			htmlStr += "<td></td>";
			htmlStr += "</tr>";
		}
	}
	
	$("#ajaxList").html(htmlStr);
	pageMake(dataLen);

}	

//페이지 처리  
function pageMake(lastNum){
	
	var pageNum = $("#currentPage").val();
	
	var paggingStr = "";
	for( i=1; i<=lastNum; i++ ){
		if( pageNum == i ){
			paggingStr +="<li class='active'><a href='javascript:goPage("+i+");'>" + i + "</a></li>";
		}else{
			paggingStr += "<li><a href='javascript:goPage("+i+");'>" + i + "</a></li>";
		}
	}		
	$("#pageApi").html(paggingStr);
	$('#addrDtl').attr('style','display:block');
	
}

//통합검색td 클릭 이벤트
$(document).on('click', '#ck_zipNo', function(){
	$("#addrZip").val( jQuery(this).attr("zip_no") );
	$("#addrZipS").val( jQuery(this).attr("zip_nos") );
	$("#bjc").val( jQuery(this).attr("bjc") );
	$("#nnmr").val( jQuery(this).attr("nnmr") );
	$("#nnmb").val( jQuery(this).attr("nnmb") );
	$("#roadPostAddr").val( jQuery(this).attr("roadPostAddr") );
	$("#zibunPostAddr").val( jQuery(this).attr("zibunPostAddr") );
	$("#roadYn").val( jQuery(this).attr("roadYn") );
	$("#addr").val( jQuery(this).attr("doro_nm") );
	
	$("#addr2").val("");
	$("#addr2").focus();
	
	
	console.log(jQuery(this).attr("zip_no"));
	console.log(jQuery(this).attr("zip_nos"));
	console.log(jQuery(this).attr("bjc"));
	console.log(jQuery(this).attr("nnmr"));
	console.log(jQuery(this).attr("nnmb"));
	console.log(jQuery(this).attr("roadPostAddr"));
	console.log(jQuery(this).attr("zibunPostAddr"));
	console.log(jQuery(this).attr("roadYn"));
	console.log(jQuery(this).attr("doro_nm"));
	
});
		
//통합 검색 버튼 이벤트
function btnSearchEngin(){
	jQuery("#currentPage").val("1");
	jQuery.list_action();
}
		
//페이지 이동
function goPage(_curPage){
	jQuery("#currentPage").val(_curPage);
	jQuery.list_action();
}

//주소 선택
function btnReturn(){
	if($('#addr2').val().trim() == ""){
		alert("나머지 주소를 입력해주세요");
		$("#keyword").focus();
		return false;
	}


console.log($("#returnFrm").serialize());

	$.ajax({
		url :"/web/common/addrSearch_selectAddrRefine.json"
		, type: "post"
 		,data : $("#returnFrm").serialize()
 		,success : function(xmlStr){
 			debugger;
 			var zprn = $("#addrZip").val();					// 우편번호
			var zprns = $("#addrZipS").val();				// 우편번호순번
			var nadr = $("#addr").val();					// 선택주소
			var nnmb = $("#nnmb").val();					// 건물관리번호
			var roadPostAddr = $("#roadPostAddr").val();	// 도로명주소
			var zibunPostAddr = $("#zibunPostAddr").val();	// 지번주소
			var roadYn = $("#roadYn").val();				// 도로명선택여부
			var addr2 = $("#addr2").val();					// 나머지 주소
	
// 			console.log(xmlStr)
			// 주소 매핑(도로명<->지번) 성공
			// 도로명주소 검색 시 H : 입력새길에 1:1 지번매칭성공하였습니다.
			// 지번주소 검색 시 I : 입력지번에 1:1 새길매칭성공하였습니다.
 			if(xmlStr.RCD3 == "H" || xmlStr.RCD3 == "I"){
 				opener.setData1(zprn, zprns, nadr, nnmb, roadPostAddr, zibunPostAddr, roadYn, addr2);
 				window.close();
 				
 			// 매핑오류 주소
 			// 도로명주소 검색 시 A : 존재하지 않는 건물번호입니다.
			// 지번주소 검색 시 4 : 지번 형식이 잘못되었습니다.
 			// 3 : 매핑 실패 주소 검색(매핑 성공된 주소에 상세주소 없이 검색 시 정상호출됨)
 			// 3-1 매핑 실패 도로명주소에 상세주소 검색 : 입력주소의 건물번호가 잘못되어 매핑을 할 수 없습니다. / 상세주소 입력오류입니다.
 			// 3-2 매핑 실패 도로명주소에 상세주소 없이 검색 : 입력주소의 건물번호가 잘못되어 매핑을 할 수 없습니다. / 존재하지 않는 건물번호입니다.
 			// 3-3 매핑 실패 지번주소에 상세주소 없이 검색 : 지번이 존재하나 새길과 매핑되는 자료가 없습니다 / 새우편번호를 찾지 못한 주소입니다
 			} else if(xmlStr.RCD3 == "3" || xmlStr.RCD3 == "4" || xmlStr.RCD3 == "A"){
 				if(confirm("도로명 주소가 정확하지 않아 지번주소로 대체 됩니다.")){
 					roadYn = "N";
 					opener.setData1(zprn, zprns, nadr, nnmb, roadPostAddr, zibunPostAddr, roadYn, addr2);
 		 			window.close();
 				}
 			} else{
 				alert("주소를 다시 확인해주세요");
 			}
 		}
	});
}

//총길이 100byte. 한글 50자. 영어,기타 100byte.
function inputLengthCheck(eventInput){
	var inputText = $("#keyword").val();
	var inputMaxLength = $("#keyword").prop("maxlength");
	var j = 0;
	var count = 0;
	for(var i = 0;i < inputText.length;i++) 
	{ 
		val = escape(inputText.charAt(i)).length; 
		if(val == 6){
			j++;
		}
		j++;
		if(j <= inputMaxLength){
			count++;
		}
	}
	if(j > inputMaxLength){
		$("#keyword").val(inputText.substr(0, count));
	}
}

</script>
<head>
<title>주소검색</title>
</head>
<body style="margin:0px;">
<div class="container" style="width: 100%;">

	<div class="row">
		<form class="form-horizontal" id="searchFrm" name="searchFrm" onsubmit="return false">
		<sec:csrfInput/>
		<input type="hidden" name="currentPage" id="currentPage" value="1"/>
		<div class="logo-div">
			<div class="logo-span">
				<span class="logo-span-text"><strong>주소찾기</strong></span>
			</div>
		</div>
 		<div class = "logo-div">
 			<div style="display: inline-block; margin-left: 30px;"><b>통합검색</b></div>
 			<div style="display: inline-block; margin-left: 20px;"><input type="text" class="textAddr" id="keyword" name="keyword" value="" size="30" placeholder="주소를 검색하세요"/></div>
 			<div style="display: inline-block; margin-left: 20px;"><input type="button" id="btnSearch" value="조회" onclick="btnSearchEngin()" class="buttonAddr"/></div>
 		</div>
 		
	    
	    </form>
	</div>
	
	<div class="row" style="max-height: 300px; overflow-y: auto; width:100%;" id = "addrTb">
		<table style = "word-break:break-all;" class = "table-wrapper">
			<colgroup>
				<col width = "15%"/>
				<col width = "70%"/>
				<col width = "15%"/>
			</colgroup>
			<thead>
				<!-- <tr>
					<th>NO</th>
					<th>주소</th>
					<th>우편번호</th>
				</tr> -->
			</thead>
            <tbody id="ajaxList"></tbody>
		</table>
	</div>
	
	<div class="row" align="center">
		<div style="text-align: center;">
			 <nav aria-label="Page navigation">
				<!-- <ul class="pagination" style="margin:10px;"> -->
				<ul class="pagination" id="pageApi" style="list-style: none; margin-left:-70px;"></ul>
			</nav>
		</div>
	</div>

	<div class="row" id="blank" align="center" >

		 		<div align="left">
		 			<div class="logo-img">
						<img class="logo-src" src="/resources/images/pearl/common/address.jpg" width="485px;"/>
					</div>
		 		</div>
			</div>
	</div>
	
	<div class="row" id="addrDtl" style="margin-left: -15px; display: none;" align="center" >
		<hr class="std"/>
		
		 <span class="col-sm-12" style="margin-bottom: 7px;">상세 주소를 입력해주세요</span>
		<form class="form-horizontal" id="returnFrm" name="returnFrm">
			<sec:csrfInput/>
			<input type="hidden" id="addrZipS" name="addrZipS"/>
			<input type="hidden" id="bjc" name="bjc"/>
			<input type="hidden" id="nnmr" name="nnmr"/>
			<input type="hidden" id="nnmb" name="nnmb"/>
			<input type="hidden" id="roadPostAddr" name="roadPostAddr"/>
			<input type="hidden" id="zibunPostAddr" name="zibunPostAddr"/>
			<input type="hidden" id="roadYn" name="roadYn"/>
			<div align="left">

		 	<input type="hidden" id="addrZip" name="addrZip"/>
		 	<input type="hidden" id="addr" name="addr" />

		 		<div style="margin-top: 20px">
		 			<div style="display: inline-block; margin-left: 20px;">상세 주소 </div>
		 			<div style="display: inline-block; margin-left: 20px;">
		 				<input type="text" class="textAddr" id="addr2" name="addr2" value="" size="30"/>
		 				
		 			</div>
		 			<div style="display: inline-block; margin-left: 20px;">
		 			<input type="button" class = "buttonAddr" id="btnConfirm" value="확인" onclick="btnReturn()"/>
		 			</div>
		 		</div>
			</div>
		</form>
	</div>

</body>
</html>