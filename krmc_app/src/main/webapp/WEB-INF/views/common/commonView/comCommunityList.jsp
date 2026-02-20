<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

	<style type="text/css">
		@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800');
		
		.notice_Box li{
			border-bottom: 1px solid #e3ebf2;
			position: relative;
			padding-top:10px;
			padding-left: 10px !important;
			min-height: 35px;
			height: auto;
			color: #464646;
			font-size: 12px;
			list-style-type: none; 
		}
		
		.notice_Box li p{
			float: left;
			padding-left:10px;
			position:relative;
			font-size: 12px;
		}
		
		.notice_Box li p span{
			
			font-size: 12px;
			text-align: right;
		}
		
		
		.notice_Box li a {padding-right:10px; }
		.notice_Box li a .hrefhov{color:#7f7f7f !important;text-decoration:underline;}
		
		.notice_Box .noDataFound{text-align: center; font-size: 12px; }
		
		.date-fn {font-size: 10px;}
		
		.ellipsis {
			  width: 450px;
			  white-space: nowrap;
			  overflow: hidden;
			  text-overflow: ellipsis;  
		}
	</style>
<div class="notice_Box">
<c:if test="${not empty communityList }">
	<c:forEach items="${communityList}" var="list" varStatus="index" >
			<c:set var="rowCnt" value="${index.count}"/>
			<c:url value="/web/community/${list.BOARD_CD}/community" var="urlVal" >
				<c:param name="boardIdx"  	  value="${list.BOARD_IDX}"  />
			</c:url>
			
			<fmt:parseDate value="${list.REG_DT}" var="parsedRegDt" pattern="yyyyMMddHHmmss" />
    		<ul>
				<li title="<c:out value="${list.BOARD_TITLE}"/>">
					<p ><span><c:out value="${list.BOARD_IDX}"/></span></p>
					<p class="ellipsis"><a href="${urlVal}" class="hrefhov" ><c:out value="${list.BOARD_TITLE}"/></a></p>
					<p style="width: 100px;"><span><c:out value="${list.USER_NM}"/></span></p>
					<p ><span><fmt:formatDate pattern="${localeDatePattern}" value="${parsedRegDt}"/></span></p>
				</li>
			</ul>
	</c:forEach>
</c:if>
<c:if test="${empty communityList }">
	<ul><li class="noDataFound" title="nodatafound" >No Data Found! </li></ul>			
</c:if>
</div>	