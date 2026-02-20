<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>

<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>

 	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
     
      
	<!--
		Used for including CSRF token in JSON requests
		Also see bottom of this file for adding CSRF token to JQuery AJAX requests
		default header name is X-CSRF-TOKEN 
	-->
	 <meta name="_csrf" content="${_csrf.token}"/>
	 <meta name="_csrf_header" content="${_csrf.headerName}"/>
	
     
    <!-- Bootstrap 3.3.4 -->
    <link href="<c:url value="/resources/almasaeed/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="<c:url value="/resources/almasaeed/dist/css/AdminLTE.min.css"/>" rel="stylesheet" type="text/css" />
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body >    
<!-- Content Contains page content -->
<div class="content">

        <!-- Main content -->
        <section class="content">

          <div class="error-page">
            <h2 class="headline text-yellow"> 403</h2>
			  <div class="error-content">
              <h3><i class="fa fa-warning text-yellow"><strong><spring:message code="error.Forbidden" /> </strong></i> </h3>
              <p>
               	<p><small class="text-red"><i class="fa fa-cog"></i>Forbidden</small></p>
	          	<P><small class="errMessage"> <spring:message code="error.msg.forbidden" /></small></p>
          		<p>
	      			<a href="/" class="btn btn-primary btn-xs" >Home</a> | 
	        		<a href="javascript:void(0);" onClick="history.back();" class="btn btn-danger btn-xs">Back</a>
	        	</p>
	        </div><!-- /.error-content -->
          </div><!-- /.error-page -->
        </section><!-- /.content -->
        
        
</div><!-- /.content -->
</body>

</html>
      