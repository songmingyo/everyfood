<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" />
   
<script type="text/javascript">


var tody = "${today}";
//조회버튼 event
function communityFullData(){

	
	var searchInfo = {};
	var url = "<c:url value='/app/board/community_selList'/>";
	
	$('#CommunityFullForm').find('input, select').map(function() {
		searchInfo[this.name] = $(this).val();
	});
	searchInfo["pageRowCount"] 	= $(':radio[name="pageRowCount"]:checked').val();		// page Count

  	$.ajax({
	      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json',
	      url  : url,
	      data : JSON.stringify(searchInfo),
	      success : function(data){
          	setTbodyMasterValue(data);
	      }
	});
}


/* eventSearch() 후처리(data  객체 그리기) */
function setTbodyMasterValue(json) {

	var data 	= json.list;

	$("#boardSeq").val(json.boardSeq);
	$("#dataListbody tr").remove();
	if(data!=null){
		var sz 	= data.length;
		var i = 0;
		for(var k=sz;k>0;k--){
			if(tody == data[i].regDt.substring(0,10))
				data[i].toDy="true";
			i++;
		}

		var page 	= json.paging;
		$("#dataListTemplate").tmpl(data).appendTo("#dataListbody");
		$('#paging-tmpl').tmpl(page.list).insertBefore($('#paging').empty()).appendTo('#paging');
		
	}else{
		setTbodyNoResult('dataListbody',7,'<spring:message code="message.search.no.data" />'); 	<%-- 조회 결과가 없습니다 --%>
		$("#paging").html("");
		return;
	}
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
			<span class='notice_ellipsis'>
				{%if depth != 0%}
					<img src=/resources/images/common/icon_reply.png align=middle width=13px;>
				{%/if%}
					<a href="javascript:void(0);" onClick=eventSearchView('\${boardCd}','\${boardIdx}') title='\${boardTitle}'{%if delYn == 'Y' %}style="text-decoration: none;color: gray;"{%/if%}>
						{%if isUrgencyYn == 'Y' || boardNoticeYn == 'Y'%}
							<b>\${boardTitle}</b>
						{%else%}
							\${boardTitle}
						{%/if%}
					</a>
				
			</span>
				{%if atchFileCnt != 0%}
					<label class='notice_label notice_gray'>File</label>
				{%/if%}
				{%if toDy == 'true'%}
					<label class='notice_label notice_red'> New</label>
				{%/if%}
				{%if commentNum != '0'%}
					<label class='notice_label notice_yellow'>\${commentNum}</label>
				{%/if%}
				
		</td>
		<td class="tdm">\${userNm}</td>
		<td class="tdm">\${regDtLocale}</td>
	<%--	<td class="tdm" style="border-right:0px;">\${boardCount}</td> --%>
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

	
	
<style type="text/css">

table.type1 {width:100%;}
table.type1 th,
table.type1 td { padding:5px;  font-size:13px; background-color: #fff;}
table.type1 th { color:#555; background:#fff;  text-align: center;border:1px solid #efefef; }
table.type1 th.req::before{content:'* '; color:red;}

table.type1 td.tdm {text-align:center; border-left-color: #FFFFFF;  height: 17px; }
table.type1 td.tdmr {text-align:right; border-left-color: #FFFFFF;  height: 17px; }
table.type1 td.tdms {padding:2px 2px 2px 2px; }


/*page title style */
.page_tit_area {overflow:hidden;  margin-top:5px; border-bottom:1px solid #efefef; margin-bottom: 15px; padding-bottom:5px;}
.page_tit_area h2 {float:left; padding:0 0 0 0; height:23px; color:#696969; font-size:20px; font-weight:bold; }
.page_tit_area .page_map {float:right; margin-top: 14px; font-size:11px; color: #757575; font: 11px/10px Arial, "Helvetica Neue", Helvetica, Arial, Geneva, sans-serif;}
.page_tit_area .page_map .last_map {color: #c84033; font-weight: bold;}
.page_tit_area .page_comment {font-size:12px; color: #757575; font: 11px/10px Arial, "Helvetica Neue", Helvetica, Arial, Geneva, sans-serif; margin-top:10px; float:left;}


.paging { padding:1px 0 0; text-align:center; }
.paging img { vertical-align:middle;}
.paging a {font-size:0.8em; display:inline-block; width:20px; height:20px; padding:4px 0 0; text-align:center; border:1px solid #efefef; background:#fff; vertical-align:top; color:#8f8f8f; line-height:12px;}
.paging a.btn,
.paging a.btn:hover { width:auto; height:auto; padding:0; border:0; background:none;}
.paging a.on,
.paging a:hover { border:1px solid #4c799a; background:#518aac; font-weight:bold; color:#fff;}


.page_area {
	overflow:hidden; margin-top:0px; border:1px solid #efefef; background:#fafafb; padding: 0px;height: 25px;
	border: 1px solid transparent;
  	border-bottom-left-radius: 4px;
  	border-bottom-right-radius: 4px;
  	border-color: #cfdbe2;
}
.page_area span {float:left; margin-top: 5px; margin-left:5px;  padding:0 0 0 0; height:0px; font: 11px/10px Arial, "Helvetica Neue", Helvetica, Arial, Geneva, sans-serif; color:#7783c9;}
.page_area .pages {float:right;  margin-top: 0px; margin-right:2px; padding:0 0 0 0; text-align:center; }

.notice_label{
	padding-right:5px;
	padding-left:5px;
    border-radius: 0.5em;
    border: 1px solid white;
    font-weight:bold;   
    display: block;
    font-size: 0.5em;
    line-height: 14px;
    text-align: center;
    padding-top: 1px;
    float: right;
  
}

.notice_red{
    background: #dd4b39;
     color: white;
}

.notice_white{
    background: #fff;
     color: #333;
}

.notice_gray{
    background: #efefef;
    color: #333;
}

.notice_yellow{
    background: #f39c12;
    color: #fff;
}

.notice_blue{
    background: #0073b7;
    color: #fff;
}
.notice_ellipsis {
	
 	display:inline-block;
	width: 240px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;  

}



</style>
	
	<!-- Data 그리트 영역 start ------------------------->
	<div style="border:1px solid #e5e5e5; background:#f7f7f7;">
	<!-- data list -->
		<table id="notice_table" class="type1" >	<%--게시판 검색 --%>
			<caption><spring:message code="app.community.community.webacc.bdtag.table.caption.board" /></caption>			<%--게시판 검색 --%>
			<colgroup>
				<col width="50">
				<col width="80">
				<col width="80">
				<col width="80">
				<col width="*">
				<col width="100">
	           	<col width="150">
				<!-- <col width="60"> -->
			</colgroup>
			<thead>
				<tr>
					<th><spring:message code="app.community.community.head.no" />			</th>	<%--순번 --%>
					<th><spring:message code="app.community.community.head.boardidx" />		</th> 	<%--게시글번호 --%>
					<th><spring:message code="app.community.community.head.exposure" />		</th> 	<%--사용자유형 --%>
					<th><spring:message code="app.community.community.head.alertType" />	</th> 	<%--게시유형 --%>
					<th><spring:message code="app.community.community.head.boardtitle" />	</th>	<%--제목 --%>
					<th><spring:message code="app.community.community.head.usernm" />		</th>	<%--작성자 --%>
					<th><spring:message code="app.community.community.head.regdt" />		</th>	<%--등록일 --%>
				<!-- 	<th><spring:message code="app.community.community.head.boardcount" />	</th> -->	<%--조회수 --%>
				</tr>
			</thead>
			<tbody id="dataListbody"></tbody>
		</table>
	</div>

	<div class="page_area" >
       	<span><html:codeTag comType="RADIO" objId="pageRowCount" objName="pageRowCount" parentID="9069" selectParam="100"  /></span>
		<div class="pages"><div class="paging" id="paging"></div></div>
	</div>
	
	<form id="CommunityFullForm" name="CommunityFullForm">
		<input type="hidden"  name="boardCd"	 		id="boardCd" 		value="${mgrVO.boardCd}">
		<input type="hidden"  name="boardKindCd"		id="boardKindCd"	value="${mgrVO.boardKindCd}" />
   		<input type="hidden"  name="page" 				id="page"			value="1" />
	</form>
	
	<!-- Data 그리트 영역 end --------------------------->

	
	<script type="text/javascript">
	/* onLoad or key event */
	$(document).ready(function($){
		communityFullData();


		$('input[type=radio][name="pageRowCount"]').change(function() {
			communityFullData();
		});

	});
	</script>