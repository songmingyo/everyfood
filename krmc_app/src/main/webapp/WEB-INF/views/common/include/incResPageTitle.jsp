
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp" />

<section class="content-header">
<c:set var="titleLocale" value="Untitled"/>
<c:if test="${acessMenu.titleLocale ne null and acessMenu.titleLocale ne ''}">
	<c:set var="titleLocale" value="${acessMenu.titleLocale}"/>
</c:if>
		
		<h1>
			<strong>${titleLocale}</strong>
			<c:choose>
				<c:when test="${acessMenu.extraInfo != null and acessMenu.extraInfo !='' }">
					<small> ${acessMenu.extraInfo}</small> 
				</c:when>
				<c:otherwise>
			
				</c:otherwise>
			</c:choose>
		</h1>
		<c:set var="mapList" 	 value="${acessMenu.menuList}"/>
		<c:set var="menuIconFmt" value="fa ${acessMenu.menuIcon}"/>
		<ol class="breadcrumb">
		<c:forEach var="item" items="${mapList}" varStatus="index">
				<c:set var="titleLocale" value="Untitled"/>
				<c:if test="${item.titleLocale ne null and item.titleLocale ne ''}">
					<c:set var="titleLocale" value="${item.titleLocale}"/>
				</c:if>
				
				<c:if test="${index.count eq 1}"><li><a href="/"><i class="fa fa-dashboard"></i> Home</a></li></c:if>
				<c:choose>
					<c:when test="${index.last}"><li class="active"> ${titleLocale}</li></c:when>
					<c:otherwise><li><a href="#">${titleLocale}</a></li></c:otherwise>
				</c:choose>
		</c:forEach>
		</ol>
</section>

