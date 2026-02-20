<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<sec:authorize access="isAuthenticated()">
	<spring:eval expression="userSession.userType"		var="userType" />
</sec:authorize>

<!-- Content Wrapper. Contains page content -->
 <div class="content-wrapper">

	<!-- Content Header (Page header) -->
	<jsp:include page="/WEB-INF/views/common/include/incResPageTitle.jsp" />

	<!-- Main content -->
	<section class="content">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">작업중</h3>
				</div>
				<div class="box-body" style="text-align: center; font-size: 24px; height: 100px;">
					<span class="glyphicon glyphicon-cog"></span>
					<span class="glyphicon-class">Working programing...</span>
				</div>
				<div class="box-footer"></div>
			</div>
    </section>
    <!--/. Main content -->
 </div>
<!-- /.Content Wrapper. Contains page content -->
