<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<title> <spring:message code="app.community.community.pagetitle" /> </title> <%--게시판 리스트 --%>
<style>
th {border-top:0px !important;}
</style>
<script>

	/* onLoad or key event */
	$(document).ready(function($){
		eventSearch();
		//enter key이벤트 --------------
		$('#search').unbind().keydown(function(e) {
			switch(e.which) {
	    	case 13 : eventSearch(this); break; // enter
	    	default : return true;
	    	}
	    	e.preventDefault();
	   	});

		//버튼클릭 이벤트 ---------------------------------------------------
		$('#btnSearch').click(function(e){
			/* 조회버튼을 클릭한 경우는 페이지번호는 1로 초기화 한다. */
			$("#page").val(1);
			eventSearch();
			/* 버튼이 form 안에 존재하면 e.preventDefault(); 필수이다. 안쓰면 autoSubmit 된다. */
			e.preventDefault();
		});
		//-----------------------------------------------------------------

		/* 페이지번호 Click Event 발생시 조회함수 호출하다. */
		$(document).on('click', '#paging a' , function(e){
			// #page : 서버로 보내는 Hidden Input Value
			$('#page').val($(this).attr('link'));
			// 개발자가 만든 조회 함수
			eventSearch();
		});

		$('#btnSave').click(function(e){
			eventSave();
	    	e.preventDefault();
	   	});

		//페이지 카운트 변화시
		$(document).on('click', '#pageRowCount' , function(e) {
			eventSearch();
		});

	});

	//조회버튼 event
	function eventSearch(){
		var searchInfo = {};
		var url = "<c:url value='/web/board/community_selList'/>";
		if(!validation())return;
		checkedYn();

		$("#hiddenForm input[name='search']").val($.trim($("#searchFrm input[name='search']").val()));
		$('#searchFrm').find('input, select').map(function() {
			if(this.type == 'checkbox' || this.type == 'text' || this.name == 'page' || this.name == 'boardCd')
				searchInfo[this.name] = $(this).val();
		});
		searchInfo["search"] = $.trim($("#searchFrm input[name='search']").val());
		searchInfo["pageRowCount"] = $(':radio[name="pageRowCount"]:checked').val();		// page Count


	  	$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : url,
		      data : JSON.stringify(searchInfo),
		      success : function(data){
	          	setTbodyMasterValue(data);
		      }
		});
	}

	/*validation*/
	function validation(){
		if(!$('#titleYn').is(':checked') && !$('#contentYn').is(':checked') && !$('#userNmYn').is(':checked')) {
			alert('<spring:message code="app.community.community.validation.a1" />'); 	<%--검색 조건을 체크하세요! --%>
			$('#search').focus();
			return false;
		}
		if(!$('#search').val().indexOf("%") || $('#search').val().indexOf("%")>0) {
			alert('<spring:message code="app.community.community.validation.a2" />');	<%--검색어에 %는 포함될 수 없습니다! --%>
			$('#search').focus();
			return false;
		}
		return true;
	}

	/*검색조건 체크 확인*/
	function checkedYn(){
		if($('#titleYn').is(':checked')){
			$("#titleYn").val("Y");
			$("#hiddenForm input[name='titleYn']").val("Y");
		} else {
			$("#titleYn").val("N");
			$("#hiddenForm input[name='titleYn']").val("N");
		}
		if($('#contentYn').is(':checked')){
			$("#contentYn").val("Y");
			$("#hiddenForm input[name='contentYn']").val("Y");
		} else {
			$("#contentYn").val("N");
			$("#hiddenForm input[name='contentYn']").val("N");
		}
		if($('#userNmYn').is(':checked')){
			$("#userNmYn").val("Y");
			$("#hiddenForm input[name='userNmYn']").val("Y");
		} else {
			$("#userNmYn").val("N");
			$("#hiddenForm input[name='userNmYn']").val("N");
		}
	}

	/* eventSearch() 후처리(data  객체 그리기) */
	function setTbodyMasterValue(json) {

		var data 	= json.list;

		$("#boardSeq").val(json.boardSeq);
		setTbodyInit();	// dataList 초기화
		if(data!=null){
			var sz 	= data.length;
			var i = 0;
			for(var k=sz;k>0;k--){
				if($("#tody").val()==data[i].regDt.substring(0,10))
					data[i].toDy="true";
				i++;
			}

			var page 	= json.paging;
			var notice = false;
			/* var cnt = 0;
			var boardNotice = false;
			
			for(i=0; i<data.length; i++){
				//검색 자료가 없을 경우
				if(data[i].noticeYn=='0')notice=true;
				else notice=false;
				//공지글 갯수 체크
				if(data[i].boardNoticeYn=="Y"){
					boardNotice = true;
					cnt++;
				}
			}
			
			if(boardNotice){
				//공지글이 아닌것부터 순번 다시 매김
				var newCnt = 1;
				for(i=cnt; i<data.length; i++){
					data[i].rnumSub = newCnt;
					newCnt++;
				}
			} */
			
			$("#dataListTemplate").tmpl(data).appendTo("#dataListbody");
			if(notice){
				$('<tr><td colspan="8" style="text-align: center;">'+'<spring:message code="app.community.community._setTbodyMasterValue.m1" />'+'</td></tr>').appendTo("#dataListbody");	<%--조회결과가 없습니다. --%>
			}
			$('#paging-tmpl').tmpl(page.list).insertBefore($('#paging').empty()).appendTo('#paging');
			
		}else{
			var msg = '<spring:message code="message.search.no.data" />';	<%-- 조회 결과가 없습니다 --%>
			<c:if test="${mgrVO.boardCd ne '001' and mgrVO.boardCd ne '002' }">
				setTbodyNoResult("dataListbody",7,msg);
			</c:if>
			<c:if test="${mgrVO.boardCd eq '001' or mgrVO.boardCd eq '002' }">
				setTbodyNoResult("dataListbody",7,msg);
			</c:if>
			$("#paging").html("");
			return;
		}
	}

	/* 목록 초기화 */
	function setTbodyInit(){
		$("#dataListbody tr").remove();
	}

	/* 게사물 상세화면  */
	function eventSearchView(boardCd, BoardIdx){
		var parameter="";
		var url;
		//9020002 : Q&A, Q&A상세보기는 로그인 사용자만 볼수 있도록
		if($('#boardKindCd').val()=="9020002") url = 'app/community/'+boardCd+'/community';
		else url = 'web/community/'+boardCd+'/community';

		parameter	+=	"?boardIdx="		+encodeURIComponent(BoardIdx)
					+	"&updateAnswer="	+$("#hiddenForm input[name='updateAnswer']").val()
					+	"&search="			+encodeURIComponent($("#hiddenForm input[name='search']").val())
					+	"&titleYn="			+$("#hiddenForm input[name='titleYn']").val()
					+	"&contentYn="		+$("#hiddenForm input[name='contentYn']").val()
					+	"&userNmYn="		+$("#hiddenForm input[name='userNmYn']").val();
		url+=parameter;
		location.href = "<c:url value='/'/>"+url;
	}
	/*글쓰기 이벤트 */
	function eventSave(){
		var f = document.searchForm;
		$("#hiddenForm input[name='boardCd']").val($("#searchFrm input[name='boardCd']").val());
		url = "<c:url value='/app/board/boardAricle' />";
		f.action = url;
	   	f.submit();
	}
</script>
  
<script id="dataListTemplate"  type="text/x-jquery-tmpl">
	<tr style="{%if noticeYn == '0'%}background:#f7f7f7;{%/if%} {%if delYn == 'Y'%} font-color: gray;{%/if%}">
		<td class="tdm" style="border-left:0px;">
			{%if noticeYn == '0'%}
				<B>*</B>
			{%else%}
				\${rnum}   <!-- 210826 요청 순번(최신글부터 1 시작)-->
			{%/if%}
		</td>
		 <td class="tdm">\${boardIdx}</td>
 		<td class="tdm">\${boardExposureNm}</td>
 		<td class="tdm">\${alertTypeNm}</td>
		{%if depth == '0'%}
			<td style="text-align: left;padding-left: 10px;">
		{%else%}
			<td style="text-align: left;padding-left: calc(2*\${depth}0px);">
		{%/if%}
			<span class='ellipsis'>
				{%if depth > 0%}
					<img src=/resources/images/pearl/common/icons-reply.gif align=middle>
				{%/if%}
					<a href="javascript:void(0);" onClick=eventSearchView('\${boardCd}','\${boardIdx}') title='\${boardTitle}'{%if delYn == 'Y' %}style="text-decoration: none;color: gray;"{%/if%}>
						{%if isUrgencyYn == 'Y' || boardNoticeYn == 'Y'%}
							<B>\${boardTitle}</B>
						{%else%}
							\${boardTitle}
						{%/if%}
					</a>
				<c:if test="${mgrVO.commentYn eq'Y'}">
					{%if commentNum != '0'%}
						<img src=/resources/images/pearl/common/icons-comment.gif align=middle>
						<B>(\${commentNum})</B>
					{%/if%}
				</c:if>
				{%if toDy == 'true'%}
					<img src=/resources/images/pearl/common/icons-new.gif align=middle>
				{%/if%}
				{%if atchFileCnt != 0%}
					<img src=/resources/images/pearl/common/icons-file.gif align=middle>
				{%/if%}
			</span>
		</td>
		<c:if test="${mgrVO.boardCd ne '001' and mgrVO.boardCd ne '002' }">
			<td class="tdm">\${userNm}</td>
		</c:if>
		<td class="tdm">\${regDtLocale}</td>
		<td class="tdm" style="border-right:0px;">\${boardCount}</td>
	</tr>
</script>

<spring:message code="common.first" var="first" />	<%--처음 --%>
<spring:message code="common.next" var="next"/>		<%--다음 --%>
<spring:message code="common.last" var="last"/>		<%--마지막 --%>
<spring:message code="common.previous" var="prev"/>	<%--이전 --%>

<script type="text/x-jquery-tmpl" id="paging-tmpl" >
	{%if pageNumber == '<<'%}
		<a href="javascript:;" class="btn" link="\${linkPageNumber}"><img src="/resources/images/pearl/common/btn_first.gif" alt="${first}" /></a>
	{%elif pageNumber == '<'%}
		<a href="javascript:;" class="btn" link="\${linkPageNumber}"><img src="/resources/images/pearl/common/btn_pre.gif" alt="${prev}" /></a>
	{%elif pageNumber == '>'%}
		<a href="javascript:;" class="btn" link="\${linkPageNumber}"><img src="/resources/images/pearl/common/btn_next.gif" alt="${next}" /></a>
	{%elif pageNumber == '>>'%}
		<a href="javascript:;" class="btn" link="\${linkPageNumber}"><img src="/resources/images/pearl/common/btn_last.gif"  alt="${last}" /></a>
	{%else%}
		<a href="javascript:;" class="\${cl}" link="\${linkPageNumber}" title='\${pageNumber}'>\${pageNumber}</a>
	{%/if %}
</script>

</head>
<body>
<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
    <form id="searchFrm" name="searchFrm" method="post" onsubmit="javascript:return false;">
    <sec:csrfInput/>
    <input type="hidden" name="boardCd" id="boardCd" value="${mgrVO.boardCd}" />
    <input type="hidden" name="boardKindCd" id="boardKindCd" value="${mgrVO.boardKindCd}" />
    <input type="hidden" name="page" id="page"	value="1" />
    <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
    <input type="hidden" name="tody" id="tody"	value="${today}" />
    <!-- 검색조건/검색/글쓰기버튼 start ------------------------------------>
    <div style=" margin-bottom: 5px;">
    <spring:message code="app.community.community.label.title" var="labletitle" /> <%--제목 --%>
    <spring:message code="app.community.community.label.content" var="lablecontent" /><%--내용 --%>
    <spring:message code="app.community.community.label.user" var="lableuser" />  <%--작성자 --%>
		<input type="checkbox" id="titleYn" name="titleYn" title="${labletitle}" value="N" checked/><strong>${labletitle} </strong>
		<input type="checkbox" id="contentYn" name="contentYn" title="${lablecontent}" value="N"/><strong>${lablecontent} </strong>
		<c:if test="${mgrVO.boardCd ne '001' and mgrVO.boardCd ne '002' }">
		<input type="checkbox" id="userNmYn" name="userNmYn" title="${lableuser}" value="N"/><strong>${lableuser} </strong>
		</c:if>
		&nbsp;&nbsp;&nbsp;
		<spring:message code="app.community.community.label.searchword" var="word" /> <%--검색어 --%>
		<input type="text" id="search" name="search" style="width:200px;" title="${word}"/>
		<html:button id="btnSearch" name="btnSearch" auth="select"  />
		<sec:authorize access="isAuthenticated()">
			<c:choose>
	  			<c:when test="${mgrVO.boardCd eq '001' or mgrVO.boardCd eq '002' or mgrVO.boardCd eq '900'}">
	  				<c:if test="${ (not empty userSession.memberCd and (mgrVO.writeYn eq 'Y' or mgrVO.memberCd eq userSession.memberCd))}">
	     				<div style="float:right;">
	     					<spring:message code="button.Writing" var="Writing" /> <%--검색 --%>
							<html:button id="btnSave" name="btnSave" auth="save" msgKey="button.Writing" objTitle="${Writing}"/>
						</div>
	     			</c:if>
	            </c:when>
	            <c:otherwise>
	            	<c:if test="${not empty userSession.memberCd and (mgrVO.writeYn eq 'Y' or mgrVO.memberCd eq userSession.memberCd)}">
	     				<div style="float:right;">
	     				<spring:message code="button.Writing" var="Writing" /> <%--검색 --%>
						<html:button id="btnSave" name="btnSave" auth="save" msgKey="button.Writing" objTitle="${Writing}"/>
					</div>
	     			</c:if>
	            </c:otherwise>
            </c:choose>
		</sec:authorize>

	</div>
	</form>
	<!--  검색조건/검색 / 글쓰기버튼  end ----------------------------------->

	<!-- Data 그리트 영역 start ------------------------->
	<div style="border:1px solid #e5e5e5; background:#f7f7f7;">
	<!-- data list -->
		<table class="type1" style="background:white;" summary="<spring:message code="app.community.community.webacc.bdtag.table.summary.board" />">	<%--게시판 검색 --%>
			<caption><spring:message code="app.community.community.webacc.bdtag.table.caption.board" /></caption>			<%--게시판 검색 --%>
			<colgroup>
				<col width="50">
				<col width="80">
				<col width="80">
				<col width="80">
				<col width="*">
				<c:if test="${mgrVO.boardCd ne '001' and mgrVO.boardCd ne '002' }">
	            	<col width="120">
	            </c:if>
				<col width="150">
				<col width="60">
			</colgroup>
			<thead>
				<tr>

					<th style="border-left:0px;"><spring:message code="app.community.community.head.no" /></th>			<%--순번 --%>
					<th><spring:message code="app.community.community.head.boardidx" /></th> 	<%--게시글번호 --%>
					<th><spring:message code="app.community.community.head.exposure" /></th> 	<%--사용자유형 --%>
					<th><spring:message code="app.community.community.head.alertType" /></th> 	<%--게시유형 --%>
					<th><spring:message code="app.community.community.head.boardtitle" /></th>	<%--제목 --%>
					<c:if test="${mgrVO.boardCd ne '001' and mgrVO.boardCd ne '002' }">
						<th><spring:message code="app.community.community.head.usernm" /></th>		<%--작성자 --%>
					</c:if>
					<th><spring:message code="app.community.community.head.regdt" /></th>		<%--등록일 --%>
					<th style="border-right:0px;"><spring:message code="app.community.community.head.boardcount" /></th>	<%--조회수 --%>
				</tr>
			</thead>
			<tbody id="dataListbody"></tbody>
		</table>
	</div>

	<div class="page_area" >
       	<span><html:codeTag comType="RADIO" objId="pageRowCount" objName="pageRowCount" parentID="9069" selectParam="0"  /></span>
		<div class="pages"><div class="paging" id="paging"></div></div>
	</div>
	<!-- Data 그리트 영역 end --------------------------->
 </div>
<form id="hiddenForm" name="searchForm" method="post">
<sec:csrfInput/>
<input type="hidden" name="boardIdx" id="boardIdx">
<input type="hidden" name="boardCd" id="boardCd">
<input type="hidden" name="updateAnswer" id="updateAnswer">
<input type="hidden" name="search" id="search">
<input type="hidden" name="titleYn" id="titleYn">
<input type="hidden" name="contentYn" id="contentYn">
<input type="hidden" name="userNmYn" id="userNmYn">
</form>


</body>
</html>
