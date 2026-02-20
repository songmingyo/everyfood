<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<%--
	ClassName : smpLnchCompTab.jsp
	Description : 샘플페이지 _탭(업체정보탭)
	Modification information
	수정일 		수정자				수정내용
	--------- 	-----------			-----------------
	2022.12.25  안석진				최초생성

	Author 	: 안석진
	Since 	: 2022.12
--%>


<spring:message code="common.choice"	var="commonchoice"/>	<%-- 선택 --%>

<script>
$(document).ready(function(){
	
	//버튼클릭 이벤트=================================================================================
// 		$("#btnSave").unbind().click(null, 	fncSave);	//저장
	//버튼클릭 이벤트=================================================================================
	
	fncSetCompData();//업체 정보 세팅
	
});

/*업체 정보 세팅*/
function fncSetCompData(){
	var data ={compCd:"221225001", compNm:"테스트 업체"};
	
	$("#smpLnchCompTabTbodyTmpl").tmpl(data).appendTo("#smpLnchCompTabTbody");
}
</script>

<script id="smpLnchCompTabTbodyTmpl" type="text/x-juery-tmpl">
<tr>
	<th>업체코드</th>
	<td><c:out value="\${compCd}"/></td>
</tr>
<tr>
	<th>업체명</th>
	<td><c:out value="\${compNm}"/></td>
</tr>
</script>

<form id="dtlForm_smpLnchCompTab" name="dtlForm_smpLnchCompTab" method="post">
	<sec:csrfInput/>
	<div class="tit_area" style="line-height: 35px;border: none;background: none;font-weight: initial;">
		<span style="margin-left: 10px;">※ 업체정보를 확인해주세요.</span>
<!-- 		    <div class="btn_l"> -->
<%-- 		    	<html:button id="btnSave" 	auth="save" 	value="저장"/> --%>
<!-- 		    </div> -->
	</div>
	<div>
		<table  class="type1 inputWidth">
			<colgroup>
				<col width="10%">
				<col width="15%">
			</colgroup>
			<tbody id="smpLnchCompTabTbody"></tbody>
		</table>
	</div>
</form>

<form id="hiddenForm_smpLnchCompTab" name="hiddenForm_smpLnchCompTab" method="post">
	<sec:csrfInput/>
</form>