<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>
<title> <spring:message code="app.community.boardview.pagetitle" /> </title> <%--게시판 상세보기--%>
<script>
	$(document).ready(function($){
		searchComment();
		//enter key이벤트 --------------
		$('#commentsContent').unbind().keydown(function(e) {
			switch(e.which) {
	    	case 13 :eventCommentSave(); break; // enter
	    	default : return true;
	    	}
			e.preventDefault();
	   	});
		$('#btnConment').click(function(e){
	    	eventCommentSave();
	    	e.preventDefault();
	   	});
		$('#btnList').click(function(e){
	    	eventBoardList();
	    	e.preventDefault();
	   	});
		$('#btnUpdate').click(function(e){
			eventUpdateAnswer("U");
	    	e.preventDefault();
	   	});
		$('#btnAnswer').click(function(e){
			eventUpdateAnswer("A");
	    	e.preventDefault();
	   	});
		$('#btnDelete').click(function(e){
			eventDeleteBoardList();
	    	e.preventDefault();
	   	});
		
		//zip파일 다운로드
// 		$('#btnFileDown').click(function(e){
// 			eventFileDownLoad();
// 	    	e.preventDefault();
// 	   	}); 
	});

	/*validation*/
	function validation(){
		var msg ="";
		if(!$.trim($('#commentsContent').val())){
			msg = '<spring:message code="app.community.boardview.validation.m1" />';<%--한줄답글을 입력해 주세요.--%>
			alert(msg);
			$('#commentsContent').focus();
			return false;
		}
		return true;
	}
	/*한줄답글 조회*/
	function searchComment(){
		var searchInfo = {};
		var url = "<c:url value='/web/board/boardArticle_selComment'/>";
		searchInfo['boardIdx'] = $('#boardIdx').val();
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : url,
		      data : JSON.stringify(searchInfo),
		      success : function(data){
	          	setCommentValue(data);
		      }
		});
	}

	function setCommentValue(data){
		setTbodyInit("commentListbody");	// dataList 초기화
		if (data != null){
			$("#commentListTemplate").tmpl(data).appendTo("#commentListbody");
		}
	}

	/*한줄답글 저장*/
	function eventCommentSave(){
		if(!validation())return;
		//if(!checkLength($('#commentsContent').val(),2000))return;
		var commentInfo = {};
		$('#searchFrm').find('input').map(function() {
			if(this.type != 'button')
				commentInfo[this.name] = $(this).val();
		});
		commentInfo['memberCd'] ="<c:out value='${userSession.memberCd}'/>";
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : "<c:url value='/app/board/boardArticle_insComment'/>",
		      data : JSON.stringify(commentInfo),
		      success : function(data){
		    	  if(data.msgCd == "success") {
		    		  $('#commentsContent').val("");
			    	  searchComment();
		    	  }
		      }
		});
	}

	/*한줄답글 삭제*/
	function eventDeleteComment(commentIdx){
		var searchInfo = {};
		searchInfo['commentIdx'] = commentIdx;
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
		      url  : "<c:url value='/app/board/boardArticle_delComment'/>",
		      data : JSON.stringify(searchInfo),
		      success : function(data){
		    	  if(data.msgCd == "success") {
		    		  searchComment();
		    	  }
		      }
		});
	}

	/*게시물 삭제*/
	function eventDeleteBoardList(){

		if(confirm('<spring:message code="app.community.common.delete.confirm" />')){<%--삭제 하시겠습니까?--%>
			var searchInfo = {};
			searchInfo['boardIdx'] = $('#boardIdx').val();
			$.ajax({
			      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
			      url  : "<c:url value='/app/board/boardArticle_delData'/>",
			      data : JSON.stringify(searchInfo),
			      success : function(data){
			    	  if(data.msgCd == "success") {
			    		  eventBoardList();
			    	  }
			      }
			});
		}
	}

	/* 목록 초기화 */
	function _setTbodyInit(){
		$("#commentListbody tr").remove();
	}

	/* 게사물 상세화면  */
	function eventSearchView(encryptBoardCd, encryptBoardIdx){
	   	var parameter="";
		var url = 'web/community/'+encryptBoardCd+'/community';

		parameter	+=	"?boardIdx="		+encodeURIComponent(encryptBoardIdx)
					+	"&updateAnswer="	+$("#hiddenForm input[name='updateAnswer']").val()
					+	"&search="			+encodeURIComponent($("#hiddenForm input[name='search']").val())
					+	"&titleYn="			+$("#hiddenForm input[name='titleYn']").val()
					+	"&contentYn="		+$("#hiddenForm input[name='contentYn']").val()
					+	"&userNmYn="		+$("#hiddenForm input[name='userNmYn']").val();
		url+=parameter;
		location.href = "<c:url value='/'/>"+url;
	}
	/*List 화면이동*/
		function eventBoardList(){
		var f = document.searchForm;

		var boardCd = $("#hiddenForm input[name='boardCd']").val();

		$("#hiddenForm input[name='boardIdx']").val('');
		url = 'web/community/'+boardCd+'/community';
		f.action = "<c:url value='/'/>"+url;
		f.submit();
	}

	/*수정/답변 작성 이동*/
	function eventUpdateAnswer(gubun){
		var f = document.searchForm;
		$("#hiddenForm input[name='updateAnswer']").val(gubun);
		f.action = "<c:url value='/app/board/boardAricle' />";
	   	f.submit();
	}

	/*문자열 길이 check*/
	function checkLength(str, max){
		if (getByteLength(str) > max) {
			alert(max + "<spring:message code="app.community.boardview.checkLength.a1" />");<%--Byte 까지만 입력됩니다.\n 한글 : 3byte 영문 : 2byte--%>
			return false;
		}
		return true;
	}
	
	function eventFileDownLoad(){
		var atchFileId = $.trim($("#fileDownLoad tr").find(":first input[name='atchFileId']").val());
		
		if(atchFileId ==""){
			alert("첨부파일아이디 정보가 없습니다.\r\n 파일다운로드를 할 수 없습니다.");
			return false;
		}
		
		$("#formFileDownload input[name='atchFileId']").val(atchFileId);
		var form = document.formFileDownload;
		form.action = "<c:url value='/app/common/cntFileDownload'/>";
		form.submit();
		form.action = "";
	}

</script>


<style type="text/css">
 
    
    pre{
	    display: block;
	    padding: 9.5px;
	    margin: 0 0 10px;
	    font-size: 12px;
	    line-height: 1.42857143;
	    color: #333;
	    word-break: break-all;
	    word-wrap: break-word;
	
	   
	    border-radius: 4px;
	    overflow:scroll; height:300px;
	    border: 1px solid #fafafa!important;
    }
   
</style>

<spring:message code="app.community.boardview.label.commentdelete" var="commentdelete"/>
<script id="commentListTemplate"  type="text/x-jquery-tmpl">
	<tr>
		<td style="word-wrap:break-word"><img src=/resources/images/pearl/common/icons-reply_comment.gif align=middle> <c:out value='\${commentsContent}'/></td>
		<td class='tdm'><c:out value='\${regDtLocale}'/></td>
		<td class='tdm'>
			<c:if test="${mgrVO.memberCd eq userSession.memberCd or userSession.memberCd eq memberCd}">
				<a href="javascript:void(0);" onClick=eventDeleteComment('<c:out value="\${commentIdx}"/>')><i class="fa fa-times" /></a>
			</c:if>
		</td>
	</tr>
</script>

</head>
<body>

<form id="searchFrm" name="searchFrm" method="post">
<sec:csrfInput/>
<input type="hidden" id="boardIdx" name="boardIdx" value="<c:out value='${vo.boardIdx}'/>" />
<input type="hidden" id="boardCd" name="boardCd" value="<c:out value='${vo.boardCd}'/>" />
<input type="hidden" id="userNm" name="userNm" value="<c:out value='${vo.userNm}'/>" />
<input type="hidden" id="memberCd" name="memberCd" value="<c:out value='${vo.memberCd}'/>" />
<input type="hidden" id="regId" name="regId" value="<c:out value='${vo.regId}'/>"/>
<input type="hidden" id="modId" name="modId" value="<c:out value='${vo.modId}'/>"/>
<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
	<div class="tit_area" >
       	<!-- 수정/삭제/답변/목록 버튼 start ------------------------------------>
		<div>
			<c:choose>
	  			<c:when test="${mgrVO.boardCd eq '001'}">
	  				 <c:if test="${(userSession.userType eq 'SA' or userSession.userType eq 'SU' ) and
						(mgrVO.replyYn eq 'Y' and not empty userSession.memberCd)}">
						<spring:message code="app.community.boardview.label.reply" var="reply" />
						<html:button id="btnAnswer" name="btnAnswer" auth="save" value="  ${reply}  " objTitle="${reply}"/>
					</c:if> 
					<c:if test="${ (userSession.userType eq 'SA' or userSession.userType eq 'SU' ) and
							(vo.memberCd eq userSession.memberCd || mgrVO.memberCd eq userSession.memberCd)}">
						<spring:message code="common.edit" var="cedit" /> 		<%--수정 --%>
						<spring:message code="common.delete" var="cdelete" />	<%--삭제 --%>
						<html:button id="btnUpdate" name="btnUpdate"  auth="update"  objTitle="${cedit}"/>
						<html:button id="btnDelete" name="btnDelete" auth="delete" objTitle="${cdelete}"/>
					</c:if>
	            </c:when>
	            <c:otherwise>
	            	 <c:if test="${mgrVO.replyYn eq 'Y' and not empty userSession.memberCd}">
						<spring:message code="app.community.boardview.label.reply" var="reply" />
						<html:button id="btnAnswer" name="btnAnswer" auth="save" value="  ${reply}  " objTitle="${reply}"/>
					</c:if> 
					<c:if test="${vo.memberCd eq userSession.memberCd || mgrVO.memberCd eq userSession.memberCd}">
						<spring:message code="common.edit" var="cedit" /> 		<%--수정 --%>
						<spring:message code="common.delete" var="cdelete" />	<%--삭제 --%>
						<html:button id="btnUpdate" name="btnUpdate" auth="edit"  objTitle="${cedit}"/>
						<html:button id="btnDelete" name="btnDelete" auth="delete" objTitle="${cdelete}"/>
					</c:if>
	            </c:otherwise>
            </c:choose>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<html:button id="btnList" name="btnList" auth="select" msgKey="button.list"/>
<%-- 			<html:button id="btnFileDown" name="btnFileDown" auth="select" value="zip다운로드"/>  --%>
		</div>
		<!--  수정/삭제/답변/목록
		 버튼  end ----------------------------------->
	</div>
	<!--  게시물 내용 start ----------------------------------->
		<table class="type1" summary="">
			<caption><spring:message code="app.community.boardview.webacc.bdtag.table.caption.board"/></caption> <%--게시물 내용 --%>
			<colgroup>
				<col width="130">
				<col width="*">
				<col width="130">
				<col width="*">
			</colgroup>
			<tbody>
            	<tr>
                	<th><label for="sele2"><spring:message code="app.community.boardview.label.title" /> </label></th> <%--제목--%>
                    <td colspan="3" style="word-wrap:break-word">
						<c:out value="${vo.boardTitle}" escapeXml="true"/>
                    </td>
				</tr>
				<tr>
                	<th><label for="sele2"><spring:message code="app.community.boardview.label.usernm" /></label></th><%--작성자--%>
                <c:choose>
				<c:when test="${mgrVO.boardCd eq '900'}">		
                    <td colspan="3">
				</c:when>
				<c:otherwise>
					<td>
				</c:otherwise>
				</c:choose>
						<c:out value='${vo.userNm}'/>
                    </td>
				<c:if test="${vo.boardCd ne '900'}">
                    <th><label for="sele2">EMAIL</label></th>
                    <td>
						<c:out value='${vo.email}'/>
                    </td>
                </c:if>
				</tr>
				
				<c:if test="${userSession.userType eq 'SA'}">
					<tr>
	                	<th><label for="sele2"><spring:message code="app.community.boardsave.label.contentstype" /></label></th><%--공지속성--%>
	                    <td colspan="3">
	                    <span style="margin-left: 15px; font-weight: bold;"><i class="fa fa-caret-right"></i><spring:message code="app.community.boardsave.label.boardnoticeyn"/> : </span>${vo.boardNoticeYn} <%--상단공지 --%>
	                    <span style="margin-left: 15px; font-weight: bold;"><i class="fa fa-caret-right"></i><spring:message code="app.community.boardsave.label.isurgencyyn" /> 	: </span>${vo.isUrgencyYn}<%--강조사용 --%>
	                    <span style="margin-left: 15px; font-weight: bold;"><i class="fa fa-caret-right"></i><spring:message code="app.community.boardsave.label.delyn" />  : </span>${vo.delYn}<%--게시취소 --%>
						<span style="margin-left: 15px; font-weight: bold;"><i class="fa fa-caret-right"></i><spring:message code="app.community.boardsave.label.biztype" />: </span>${vo.alertTypeNm}
	                   	<span style="margin-left: 15px; font-weight: bold;"><i class="fa fa-caret-right"></i><spring:message code="app.community.boardsave.label.expousertype" /> </span>: ${vo.boardExposureNm}
	                    </td>
	                 </tr>
				</c:if>
				
				
				<tr>
                	<th><label for="sele2"><spring:message code="app.community.boardview.label.regdt" /></label></th> <%--작성일--%>
                    <td>
						<c:out value='${vo.regDtLocale}'/>
                    </td>
                    <th><label for="sele2"><spring:message code="app.community.boardview.label.boardcount" /></label></th> <%--조회수--%>
                    <td>
           				<c:out value='${vo.boardCount}'/>
                    </td>
				</tr>
            </tbody>
		</table>

		<table class="type1" summary="" style="margin-top: 10px">
			<caption>내용</caption>
			<tbody>
            	<tr>
                    <td height="100px" style="vertical-align:top">
							<pre style="font-size:20px;"><c:out value='${vo.boardContent}' escapeXml="false"/></pre>
				    </td>
				</tr>
            </tbody>
		</table>
		<c:if test="${not empty vo.atchFileId}">
			<table class="type1" summary="" style="margin-top: 10px">
				<colgroup>
					<col width="130">
					<col width="*">
	            </colgroup>
				<tbody>
				<tr>
					<th><label for="sele2"><spring:message code="app.community.boardview.label.filelist" /></label></th>	<%--파일 전체 목록--%>
                    <td>
                    	<c:import url="/web/common/${vo.atchFileId}/fileDownloadList" charEncoding="utf-8"></c:import>
                    </td>
		        </tr>
				</tbody>
			</table>
		</c:if>
		<!--  게시물 내용 end ----------------------------------->
		<!--  한줄 답글 start ----------------------------------->
		<c:if test="${mgrVO.commentYn eq 'Y' and userSession.userType eq 'SA' }"> 
			<c:if test="${not empty userSession.memberCd}">
				<table id="commentTb" class="type1" summary="" style="margin-top: 10px">
					<caption><spring:message code="app.community.boardview.webacc.bdtag.table.caption.reply"/></caption> <%--한줄답글 --%>
					<colgroup>
						<col width="*">
						<col width="150px;">
		            </colgroup>
					<tbody>
		            	<tr>
		                    <td height="50px">
		                   		<spring:message code="app.community.boardview.label.onereply" 	var="onereply"/>	<%--한줄답글--%>
								<input type="text" id="commentsContent" name="commentsContent" style="width:100%; height: 100%;" title="<c:out value='${onereply}'/>" maxlength="1000"/>
		                    </td>
		                    <td>
		                    	<button type="button" id="btnConment" name="btnConment" title="<c:out value='${onereply}'/>" class="buttotn btn_gray" style="width:-webkit-fill-available;height:50px;"><c:out value='${onereply}'/></button>
		                    </td>
						</tr>
		            </tbody>
				</table>
			</c:if>
		</c:if>
		<!-- 210826일반사용자에게 답글 보이게 수정---->
			<table id="commentList" class="type1" summary="" style="margin-top: 10px">
				<colgroup>
					<col width="*">
					<col width="120">
					<col width="30">
				</colgroup>
				<tbody id="commentListbody"></tbody>
			</table>
		<!-- 일반사용자에게 답글 보이게 수정---->
	   <!--  한줄 답글 end ----------------------------------->
	    <!--  다음글/이전글 start ----------------------------------->
	   <c:if test="${not empty 	preNextList}">
	   <table class="type1" summary="" style="margin-top: 10px">
			<caption><spring:message code="app.community.boardview.webacc.bdtag.table.caption.prenextlist"/></caption> <%--이전글/다음글 --%>
			<colgroup>
				<col width="100">
				<col width="*">
				<col width="100">
				<col width="100">
				<col width="100">
			</colgroup>
			<thead>
			<tr>
				<th><spring:message code="app.community.boardview.head.prenext" /></th>		<%--구분 --%>
				<th><spring:message code="app.community.boardview.head.boardtitle" /></th>	<%--제목 --%>
				<th><spring:message code="app.community.boardview.head.usernm" /></th>		<%--작성자 --%>
				<th><spring:message code="app.community.boardview.head.regdt" /></th>		<%--등록일 --%>
				<th><spring:message code="app.community.boardview.head.boardcount" /></th>	<%--조회수 --%>
			</tr>
			</thead>
			<tbody>
            	<c:forEach items="${preNextList}" var="list" varStatus="index">
                	<tr>
                   		<td style="text-align:center;">
                   		<c:if test="${list.preNext == 1}">	<%-- 이전글 --%>
                   			<c:choose>
                        		<c:when test="${list.thread < vo.thread}"><spring:message code="app.community.boardview.label.prenext" /></c:when>	
                        		<c:otherwise><spring:message code="app.community.boardview.label.next" /></c:otherwise>	
                        	</c:choose>
                   		</c:if>
                   		<c:if test="${list.preNext == 2}">	<%-- 다음글 --%>
                			<c:choose>
                        		<c:when test="${list.thread < vo.thread}"><spring:message code="app.community.boardview.label.prenext" /></c:when>	
                        		<c:otherwise><spring:message code="app.community.boardview.label.next" /></c:otherwise>	
                        	</c:choose>
                   		</c:if>
                   		</td>
              			<td class="tdl">
	                   		 <span class='ellipsis'>
	                   		 	<a href="javascript:void(0);" onClick="eventSearchView('<c:out value="${list.boardCd}"/>', '<c:out value="${list.boardIdx}"/>')" title="<c:out value='${list.boardTitle}'/>"/><c:out value="${list.boardTitle}" escapeXml="true"/></a>
	              	<%-- 			<c:if test="${list.preNext == 1}">
		                   			<c:choose>
		                        		<c:when test="${list.thread < vo.thread}"></c:when>
		                        		<c:otherwise><a href="javascript:void(0);" onClick="eventSearchView('${list.boardCd}', '${list.boardIdx}')" title="${list.boardTitle}"/><c:out value="${list.boardTitle}" escapeXml="true"/></a></c:otherwise>
		                        	</c:choose>
	                   			</c:if>
	              				<c:if test="${list.preNext == 2}">
		                        	<a href="javascript:void(0);" onClick="eventSearchView('${list.boardCd}', '${list.boardIdx}')" title="${list.boardTitle}"/><c:out value="${list.boardTitle}" escapeXml="true"/></a>
	                   			</c:if>
	                --%>
	                   		</span>
              			</td>
              			<td class="tdm"><c:out value='${list.userNm}'/></td>
              			<td class="tdm"><c:out value='${list.regDtLocaleShort}'/></td>
              			<td class="tdm"><c:out value='${list.boardCount}'/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:if>
		<!--  다음글/이전글 end ----------------------------------->

</div>
</form>
<form id="hiddenForm" name="searchForm" method="post">
<sec:csrfInput/>
<input type="hidden" id="boardIdx" name="boardIdx" value="<c:out value='${vo.boardIdx}'/>" />
<input type="hidden" id="boardCd" name="boardCd" value="<c:out value='${vo.boardCd}'/>" />
<input type="hidden" name="updateAnswer" id="updateAnswer">
<input type="hidden" id="search" name="search" value="<c:out value='${searchVO.search}'/>" />
<input type="hidden" name="titleYn" id="titleYn" value="<c:out value='${searchVO.titleYn}'/>">
<input type="hidden" name="contentYn" id="contentYn" value="<c:out value='${searchVO.contentYn}'/>">
<input type="hidden" name="userNmYn" id="userNmYn" value="<c:out value='${searchVO.userNmYn}'/>">
</form>

<form id="formFileDownload" name="formFileDownload" method="post">
	<sec:csrfInput/>
	<input type="hidden" name="atchFileId"/>
	<input type="hidden" name="seq"/>
</form>

</body>
</html>
