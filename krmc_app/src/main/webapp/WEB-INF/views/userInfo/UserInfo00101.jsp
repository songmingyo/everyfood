<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>
<script>
	
	// 개인정보 확인 유무
	var psnInfoYn = '';
	
	$(document).ready(function($) {
		
		psnInfoYn = 'N';
		$("#btnInfoView").show();
		
		$("#btnInfoView").unbind().click(null, _eventInfoView);
		
		// 사용자 정보 조회
		_userInfoInit();
		
		// 회사 정보 조회
		_compInit();
		
	});
	
	// 개인정보 보기
	function _eventInfoView() {
		_commonPwdCheckLayer();
	}
	
	function _userInfoInit() {
		
		var memberCd = "${userSession.memberCd}";
		
		var searchInfo = {};
		
		searchInfo['memberCd'] = memberCd;
		searchInfo['psnInfoYn'] = psnInfoYn;
		
		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/userInfo/userInfo_selUserInfo.json"/>',
			data : JSON.stringify(searchInfo),
			success : function(data) {
				if (data == null) {
					alert("오류가 발생하였습니다.");
				}
				else
					userInfoView(data);
			}
		});
	}
	
	function userInfoView(data) {
		
		var userData = data.userData;
		
		if (userData) {
			$("#userNm").text(Nullchk(userData.userNm, ""));
			$("#userId").text(Nullchk(userData.userId, ""));
			$("#telNo").text(Nullchk(userData.telNo, ""));
			$("#hpNo").text(Nullchk(userData.hpNo, ""));
			$("#userType").text(Nullchk(userData.userTypeNm, ""));
			$("#email").text(Nullchk(userData.email, ""));
			$("#compNm").text(Nullchk(userData.compNm, ""));
			$("#teamList").text(Nullchk(userData.teamList, ""));
			
			if ("Y" == userData.useYn)
				$("#useYn").text("사용");
			else
				$("#useYn").text("미사용");
				
		}
	}
	
	function _compInit() {
		
		var searchInfo = {};
		
		searchInfo['masterId'] = "${userSession.masterId}";
		searchInfo['psnInfoYn'] = psnInfoYn;

		$.ajax({
			contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false,
			url : '<c:url value = "/app/userInfo/userInfo_selCompInfo.json"/>',
			data : JSON.stringify(searchInfo),
			success : function(data) {
				if (data == null) {
					alert("오류가 발생하였습니다.");
				}
				else
					compInfoView(data);
			}
		});
	}
	
	function compInfoView(data) {
		
		var compData = data.compData;
		
		if (compData) {
			$("#compCompNm").text(Nullchk(compData.compNm, ""));
			$("#bmanNo").text(Nullchk(compData.bmanNo, ""));
			$("#ceoNm").text(Nullchk(compData.ceoNm, ""));
			$("#btypNm").text(Nullchk(compData.btypNm, ""));
			$("#bizItmNm").text(Nullchk(compData.bizItmNm, ""));
			$("#faxTelNo").text(Nullchk(compData.faxTelNo, ""));
			$("#repTelNo").text(Nullchk(compData.repTelNo, ""));
			
			var fullAddr = "(" + Nullchk(compData.zipNo,"") + ")" + " "+ Nullchk(compData.zipAddr,"") + " " + Nullchk(compData.dtlAddr,"");
			$("#addr").text(fullAddr);
		}
	}
	
	/*비밀번호 체크 레이어에서 통과 했을 경우 마스킹 해제*/
	function _setSuccessForm(){
		
		/*개인정보 확인 유무 */
		 psnInfoYn='Y';
	
		//사용자 정보 조회
		_userInfoInit();
		//회사 정보 데이터 조회
		_compInit();
		
		$('#btnInfoView').hide(); //개인정보보기 버튼숨기기
	}
	
</script>

</head>

<body>
	<div id = "section">
		<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
		
		<!-- 코드 서브타이틀 및 이벤트 버튼 start  ------------------>
		<div class="tit_area">
			<h2 class="subhead">사용자 정보</h2>
			<div class="btn_l">
				<html:button id="btnInfoView" auth="select" value = "개인정보보기"/><%-- 삭제 --%>
			</div>
		</div>
		
		<input type = "hidden" id = "memberCd" name = "memberCd">
		
		<table class = "type1">
			<caption>사용자 정보</caption>
			<colgroup>
				<col width = "20%">
				<col width = "*">
				<col width = "20%">
				<col width = "*">
			</colgroup>
			<tbody>
				<tr>
					<th scope = "col">사용자명</th>
					<td id = "userNm"></td>
					<th scope = "col">아이디</th>
					<td id = "userId"></td>
				</tr>
				<tr>
					<th scope = "col">전화번호</th>
					<td id = "telNo"></td>
					<th scope = "col">휴대폰번호</th>
					<td id = "hpNo"></td>
				</tr>
				<tr>
					<th scope = "col">사용자 유형</th>
					<td id = "userType"></td>
					<th scope = "col">E-mail</th>
					<td id = "email"></td>
				</tr>
				<tr>
					<th scope = "col">소속</th>
					<td id = "compNm"></td>
					<th scope = "col">사용여부</th>
					<td id = "useYn"></td>
				</tr>
				<tr>
					<th scope = "col">팀소속 정보</th>
					<td colspan = "3" id = "teamList"></td>
				</tr>
			</tbody>
		</table>
		
		<div class="tit_area">
			<h2 class="subhead">업체 정보</h2>
			<div class="btn_l">

			</div>
		</div>
		
		<input type = "hidden" id = "masterId" name = "masterId">
		
		<table class = "type1">
			<caption>업체 정보</caption>
			<colgroup>
				<col width = "20%">
				<col width = "*">
				<col width = "20%">
				<col width = "*">
			</colgroup>
			<tbody>
				<tr>
					<th scope = "col">업체명</th>
					<td id = "compCompNm"></td>
					<th scope = "col">사업자등록번호</th>
					<td id = "bmanNo"></td>
				</tr>
				<tr>
					<th scope = "col">대표자명</th>
					<td id = "ceoNm"></td>
					<th scope = "col">업태</th>
					<td id = "btypNm"></td>
				</tr>
				<tr>
					<th scope = "col">업종</th>
					<td id = "bizItmNm"></td>
					<th scope = "col">팩스번호</th>
					<td id = "faxTelNo"></td>
				</tr>
				<tr>
					<th scope = "col">대표번호</th>
					<td id = "repTelNo"></td>
					<th scope = "col">주소</th>
					<td id = "addr"></td>
				</tr>
			</tbody>
		</table>
	</div>

<jsp:include page = "/WEB-INF/views/userInfo/UserInfo00102.jsp" />
</body>