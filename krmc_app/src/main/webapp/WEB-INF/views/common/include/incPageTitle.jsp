
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp" />


<div class="page_tit_area">
		<c:set var="titleLocale" value="Untitled"/>
		<c:if test="${acessMenu.titleLocale ne null and acessMenu.titleLocale ne ''}">
			<c:set var="titleLocale" value="${acessMenu.titleLocale}"/>
		</c:if>
		
	
		<c:choose>
			<c:when test="${empty acessMenu.menuIcon }">
				<c:set var="menuIcon" value="fa-chevron-right"/>
			</c:when>
			<c:otherwise>
				<c:set var="menuIcon"  value="${acessMenu.menuIcon}"/>
			</c:otherwise>
		</c:choose>		
				
		<h2><i class="fa <c:out value="${menuIcon}"/>" style="font-size: 0.7em;"></i>${titleLocale}&nbsp;</h2> 
		<c:choose>
			<c:when test="${acessMenu.extraInfo != null and acessMenu.extraInfo !='' }">
				<span class="page_comment" style="font-size:12px;"> / ${acessMenu.extraInfo}</span> 
			</c:when>
			<c:otherwise>
				
			</c:otherwise>
		</c:choose>
		
		
		<div class="page_map">
			
			<c:set var="mapList" value="${acessMenu.menuList}"/>
			
			<c:forEach var="item" items="${mapList}" varStatus="index">
				<c:set var="titleLocale" value="Untitled"/>
				<c:if test="${item.titleLocale ne null and item.titleLocale ne ''}">
					<c:set var="titleLocale" value="${item.titleLocale}"/>
				</c:if>
				
				<c:if test="${index.count eq 1}"><b>Home</b></c:if>
				<i class="fa  fa-angle-right"></i>
				<c:choose>
					<c:when test="${index.last}"><span class="last_map"> ${titleLocale}</span></c:when>
					<c:otherwise>${titleLocale}</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		
</div>  
