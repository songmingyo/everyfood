<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>
<title> FAQ </title>
<script>

	$(document).ready(function($){
		eventSearch();
		$('#btnSave').click(function(e){
			eventSave();
	    	e.preventDefault();
	   	});
		$('#btnUpdate').click(function(e){
			eventUpdate("U");
	    	e.preventDefault();
	   	});

		/* 페이지번호 Click Event 발생시 조회함수 호출하다. */
		$(document).on('click', '#paging a' , function(e){
			// #page : 서버로 보내는 Hidden Input Value
			$('#page').val($(this).attr('link'));
			// 개발자가 만든 조회 함수
			eventSearch();
		});


		//페이지 카운트 변화시
		$(document).on('click', '#pageRowCount' , function(e) {
			eventSearch();
		});

	});

	//조회버튼 event
	function eventSearch(){
		var searchInfo = {};
			$('#searchFrm').find('input, select').map(function() {
				if(this.type!="button" && (this.name=="boardCd" || this.name=="page" || this.name=="pageRowCount"))
					searchInfo[this.name] = $(this).val();
			});
	  	$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : "<c:url value='/web/board/community_selList'/>",
		      data : JSON.stringify(searchInfo),
		      success : function(data){
	          	setTbodyMasterValue(data);
		      }
		});
	}

	/* _eventSearch() 후처리(data  객체 그리기) */
	function setTbodyMasterValue(json) {
		var data 	= json.list;
		setTbodyInit("dataListbody");	// dataList 초기화
		if(data!=null){
			var sz 	= data.length;
			var page 	= json.paging;
			for(var k=0;k<sz;k++){
				data[k].count = k+1;
			}
			$("#dataListTemplate").tmpl(data).appendTo("#dataListbody");
			$('#paging-tmpl').tmpl(page.list).insertBefore($('#paging').empty()).appendTo('#paging');
			for(var k=0;k<sz;k++){
				if(data[k].atchFileCnt > 0){
					eventSelectAtchFileList(data[k].count,data[k].boardIdx);
				}
			}
			for(var i=0; i<=$('#dataListbody tr').length/2;i++){
				$('#answer'+i).hide();
				$('#boardContent'+i).html($('#boardContent'+i).text());
			}
		}else{
			var msg = '<spring:message code="message.search.no.data" />';	<%-- 조회 결과가 없습니다 --%>
			setTbodyNoResult("dataListbody",2,msg);
			return;
		}
	}
	
	/* 첨부파일 조회 */
	function eventSelectAtchFileList(cnt,boardIdx){
		var searchInfo = {};
		searchInfo['boardIdx'] = boardIdx;
		
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : "<c:url value='/web/board/boardArticle_selListFile'/>",
		      data : JSON.stringify(searchInfo),
		      success : function(data){
	          	setTbodyAtchFileList(data,cnt);
		      }
		});
	}
	
	/* 첨부파일 세팅 */
	function setTbodyAtchFileList(json,cnt){
		var data = json;
		
		$("#atchFileArea"+cnt).empty();
		
		if(data != null){
			$("#atchFileListTemplate").tmpl(data).appendTo("#atchFileArea"+cnt);
		}else{
			return;
		}
		
	}

	/* 파일 다운로드 */
	function atchFileDownload(atchFileId, seq){
		var f = document.formDownloadFile;
		f.action = "<c:url value='/web/common/"+atchFileId+"/"+seq+"/fileDownload'/>";
		f.submit();
		f.action = "";
	}
	
	/* 목록 초기화 */
	function setTbodyInit(){
		$("#dataListbody tr").remove();
	}

	/*글쓰기 이벤트 */
	function eventSave(){
		var f = document.searchForm;
		f.action = "<c:url value='/app/board/boardAricle'/>";
	   	f.submit();
	}

	/*수정 화면 이동*/
	function eventUpdate(){
		var f = document.searchForm;
		$("#hiddenForm input[name='updateAnswer']").val('U');
		f.action = "<c:url value='/app/board/boardAricle'/>";
	   	f.submit();
	}

	/*삭제*/
	function eventDelete(){
		if(confirm('<spring:message code="app.community.boardfaq.eventDelete.a1" />')){ <%--삭제 하시겠습니까? --%>
			var searchInfo = {};
			searchInfo['boardIdx'] = $("#hiddenForm input[name='boardIdx']").val();
			$.ajax({
			      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			      url  : "<c:url value='/app/board/boardArticle_delData'/>",
			      data : JSON.stringify(searchInfo),
			      success : function(data){
			    	  if(data.msgCd == "success") {
			    		  eventSearch();
			    	  }
			      }
			});
		}
	}

	/*목록 토글*/
	function toggle(num){
		for(var i=0; i<=$('#dataListbody tr').length/2;i++){
			if(i!=num)$('#answer'+i).hide();
		}
		$("#hiddenForm input[name='boardIdx']").val($("#boardIdx"+num).val());
		$('#answer'+num).toggle();
	}

	function changeColor(obj){
		$('#dataListbody tr td span').attr('style', "color:black;cursor:pointer;");
		obj.style.color="#dc404c";
	}
</script>

<script id="dataListTemplate"  type="text/x-jquery-tmpl">
	<tr onclick="javascript:toggle('\${count}')">
		<td style="text-align:center;">
			{%if noticeYn =='0'%}
				<B>*</B>
			{%else%}
				\${rnum}
			{%/if%}
		</td>
		<td style="text-align: left; padding-left: 10px;">
			<span class='ellipsis' style="cursor: pointer;"  onmouseover="javascript:changeColor(this);">
				{%if isUrgencyYn == 'Y' || boardNoticeYn == 'Y'%}
					<B>\${boardTitle}</B>
				{%else%}
					\${boardTitle}
				{%/if%}
			</span>
		</td>
	</tr>
	<tr id="answer\${count}" name="answer\${count}" hidden="true">
		<td colspan="2" style="padding:5px;background:#f7f7f7;">
			<c:if test="${mgrVO.writeYn eq 'Y' and (mgrVO.memberCd eq userSession.memberCd)}">
				<div id="btn\${count}" style="float:right;">
					<spring:message code="app.community.boardfaq.btnUpdate.title" var="editTitle"/> <%--수정 --%>
					<spring:message code="app.community.boardfaq.btnDelete.title" var="deleteTitle"/> <%--삭제 --%>
					<input type="button" class="button btn_gray" id="btnUpdate" name="btnUpdate"  value="${editTitle}"   onclick="javascript:eventUpdate()"/>
					<input type="button" class="button btn_red" id="btnDelete" name="btnDelete"  value="${deleteTitle}" onclick="javascript:eventDelete()"/>
				</div>
			</c:if>
			<div id="boardContent\${count}" style="text-align: left;margin-left: 50px;margin-top:10px;margin-bottom:10px;">\${boardContent}</div>
			{%if atchFileCnt > 0 %}
			<div id="atchFileArea\${count}" style="text-align: left; margin-left: 40px; margin-right: 40px; padding: 10px; border-top: 1px solid lightgray;">
			</div>
			{%/if%}
			<input type="hidden" name="boardIdx\${count}" id="boardIdx\${count}" value="\${boardIdx}">
		</td>
	</tr>
</script>

<script id="atchFileListTemplate"  type="text/x-jquery-tmpl">
{%if atchFileId != null%}
	<div>
		<a href="javascript:;" title="\${orgFileNm}" onclick="atchFileDownload('<c:out value='\${atchFileId}'/>', '<c:out value='\${seq}'/>');">
	    	\${orgFileNm}
		</a>
	</div>
{%/if%}
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
    <form id="searchFrm" name="searchFrm" method="post">
    <sec:csrfInput/>
    <input type="hidden" name="boardCd" id="boardCd" value="${mgrVO.boardCd}">
    <input type="hidden" name="page" id="page"	value="1" />
	<!-- Data 그리트 영역 end --------------------------->
	<div style="margin-bottom: 10px;">
		<div style="float:right;">
			<c:if test="${not empty userSession.memberCd and mgrVO.writeYn eq 'Y' and userSession.userType eq 'SA'}">
				<spring:message code="app.community.boardfaq.btnSave.objTitle" var="wrObjTitle" />
				<html:button style="margin-bottom: 10px;" id="btnSave" name="btnSave" auth="save" msgKey="button.Writing" objTitle="${wrObjTitle}" />
			</c:if>
		</div>
	</div>

	<div>
	<!-- data list -->
		<table id="faq" class="type1" summary="<spring:message code="app.community.boardfaq.webacc.bdtag.table.summary" />"> <%--FAQ 조회 내역 --%>
			<caption><spring:message code="app.community.boardfaq.webacc.bdtag.table.caption" /></caption> <%--FAQ 조회 내역 --%>
			<colgroup>
				<col width="50">
				<col width="*">
			</colgroup>
			<thead>
				<tr>
					<th><spring:message code="app.community.boardfaq.head.no" /></th> <%--순번 --%>
					<th><spring:message code="app.community.boardfaq.head.title" /></th> <%--제목 --%>
				</tr>
			</thead>
			<tbody id="dataListbody"></tbody>
		</table>
	</div>
	<div style="margin-top: 10px;">
		<div class="paging" id="paging" style="padding: 0 0 0 0;"></div>
	</div>
	</form>
 </div>
<form id="hiddenForm" name="searchForm" method="post">
<sec:csrfInput/>
<input type="hidden" name="boardIdx" id="boardIdx">
<input type="hidden" name="boardCd" id="boardCd" value="${mgrVO.boardCd}">
<input type="hidden" name="updateAnswer" id="updateAnswer">
</form>
<form id="formDownloadFile" name="formDownloadFile" method="post">
	<sec:csrfInput/>
</form>
</body>
</html>
