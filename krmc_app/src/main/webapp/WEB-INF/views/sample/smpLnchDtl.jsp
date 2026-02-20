<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<%--
	ClassName : smpLnchDtl.jsp
	Description : 샘플페이지 _상세
	Modification information
	수정일 		수정자				수정내용
	--------- 	-----------			-----------------
	2022.12.25  안석진				최초생성

	Author 	: 안석진
	Since 	: 2022.12
--%>


<style>

div.tabGroups.subTab {
	border: 1px solid #e5e5e5;
	border-radius: 0;
	padding: 10px;
	border-bottom-right-radius: 3px;
	border-bottom-left-radius: 3px;
}

div.tabGroups.subTab>ul.tabItemList {
	background: none;
}

.nav-tabs>li>a {
	border: 1px solid #d2d6de;
	border-bottom: none;
}

</style>

<script>
$(document).ready(function($) {
	
	//버튼클릭 이벤트=================================================================================
		$("#btnMoveList").unbind().click(null, 	fncMoveList);	//목록
	//버튼클릭 이벤트=================================================================================
	
	
	//탭 창 포커싱 돼있을 때 엔터키 막기
	$('.tabItemList li').keydown(function() {
		if (event.keyCode === 13) {
		    event.preventDefault();
		};
	});
	
});

//Tab 클릭에 따른 jsp 내용을 .tab_container영역에 뿌려준다.
	$(function () {
		var acceptNo =  $.trim($("#hiddenTabForm input[name='acceptNo']").val());
		if(acceptNo !=""){
			$("#tabItems li").eq(0).addClass("active");					//(임시) 첫번 째 탭 활성화	
		}else{
			$("#tabItems li").addClass("noAcc");
		}
		
		var url = $("#tabItems").find("li:eq(0)").attr("data-url");	//화면에 가지고있는 첫번째 탭 url

    $(".tab_container").load('<c:url value="' + url + '"/>', fncMakeTabData());

    $("ul.tabItemList li").not(".noAcc").click(function () {
    	url = $(this).attr("data-url");			//클릭한 탭 URL 선언

		//탭 클릭&이동시 validation
//     	if(this.id =="clBuyModelTab"){
//     		if(claimId ==""){
//     			alert("기본정보 저장 후 이동가능합니다.");
//     			return;
//     		}
//     	}
	    	
    	
    	/*tab 색상이벤트*/
        $("ul.tabItemList li").removeClass("active");
        $(this).addClass("active");
        $(".tab_container").load("<c:url value='" + url + "'/>", fncMakeTabData());		//클릭한 JSP 조회
    });
    
    
    
});

/*목록으로 이동*/
function fncMoveList(){
	var f = document.hiddenTabForm;
	f.action = "<c:url value='/app/smp/smpLnch.do'/>";
	f.submit();
	f.action = "";
}

/* 페이지 이동에 필요한 데이터 셋팅 */
function fncMakeTabData(){
	var dataList = {};

	dataList['acceptNo'] = $("#hiddenTabForm input[name='acceptNo']").val();
	dataList['compCd']	= $("#hiddenTabForm input[name='compCd']").val();
	return dataList;
}


/* 탭 재조회 */
function fncReloadTabMst(){
	var currTab = $("#tabItems li.active").first();			//현재 선택된 탭
	if(!currTab) currTab = $("#tabItems li").eq(0);			//선택한 탭이 없을 경우, 첫 번째 탭
	var chkCurrTabNoAcc = $(currTab).hasClass("noAcc");		//선택한 탭의 접근 여부 
	
	var reloadUrl = "";
	
	//전체 탭 활성화 해제
	$("#tabItems li").removeClass("active");
	
	//선택된 탭이 접근 불가 탭이 아닌 경우 선택 탭 활성화
	if(!chkCurrTabNoAcc){
		$(currTab).addClass("active");
		reloadUrl = $(currTab).attr("data-url");
	}else{
		//선택된 탭이 접근 불가 탭일 경우, 첫번째 탭 활성화 
		reloadUrl = $("#tabItems").find("li:eq(0)").attr("data-url");
	}
	
	$(".tab_container").load('<c:url value="' + reloadUrl + '"/>', fncMakeTabData()); 
}	

</script>


<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
	
	<%-- 제안 상세정보 start --%>
	<div id="feWorkDiv">
		<div class="tit_area">
			<h2>제안 상세 정보</h2>
			<div class="btn_l">
				<html:button id="btnMoveList" name="btnMoveList" auth="select" value="목록"/>
			</div>
		</div>
	</div>
	<div>
		<table  class="type1 inputWidth">
			<colgroup>
				<col width="10%">
				<col width="15%">
			</colgroup>
			<tbody>
				<tr>
					<th>접수번호</th>
					<td><c:out value='${paramVo.acceptNo }'/></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<!-- 업무별 탭메뉴 Start -->
	<div id="tabGroupContainer" class="tabGroups subTab">
		<ul id="tabItems" class="nav-tabs-group nav-tabs tabItemList">	<%-- 탭 리스트 --%>
			<%-- 
		        .inactive	: 기본 탭 클래스 (접근가능)			**클래스 삭제하면 정렬 안됨
		        .active		: 선택된 탭 클래스 (접근가능)
		        .noAcc		: 접근을 막을 탭 클래스 (접근불가)
		        리스트에서는 inactive 클래스만 가짐, 다른 클래스들은 스크립트로 조작.
		    --%>
	    	<li class="inactive" id="smpLnchCompTab" data-url="<c:url value='/app/smp/smpLnch_selCompTab.do'/>"><a href="javascript:;">업체정보</a></li>
<%-- 			<li class="inactive" id="clRawTab"  data-url="<c:url value='/app/food/cl/tab/clRawTab.do'/>"><a href="javascript:;">원재료</a></li> --%>
		</ul>
		<div class="tab_container"></div>	<%-- 탭 JSP 표시 영역 --%>
	</div>
	<!-- ./업무별 탭메뉴 End -->
</div>


<%-- 탭 데이터 form --%>
<form id="hiddenTabForm" name="hiddenTabForm" method="post">
	<sec:csrfInput/>
	<input type="hidden" id="acceptNo"	 name="acceptNo"	value="<c:out value='${paramVo.acceptNo}'/>"/>
	<input type="hidden" id="compCd"	 name="compCd"		value="<c:out value='${paramVo.compCd}'/>"/>
</form>

<!-- 업체찾기 팝업-->
<%-- <jsp:include page="/WEB-INF/views/common/popupFind/comPopSrchComp.jsp"> --%>
<%-- 	<jsp:param name="popupId" value="popSrchComp"/> --%>
<%-- </jsp:include> --%>
<!-- 업체찾기 팝업-->
