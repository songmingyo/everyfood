<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*"   %>
<%@ page import="org.springframework.http.HttpStatus" %>
<%@ page import="org.apache.commons.lang.*"%>
<%@ page import="org.springframework.tronic.exception.*" %>
<%@ page import="org.springframework.util.ClassUtils" %>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>


<%	
	StringWriter sw = new StringWriter();
	PrintWriter  pw = new PrintWriter(sw);
	exception.printStackTrace(pw); 


%>

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
<spring:eval expression="@config['exception.message.view']" var="msgShow"/>
<link href="/resources/almasaeed/documentation/style.css" rel="stylesheet" type="text/css" />
<!-- Content Contains page content -->

	<div class="content">

        <!-- Main content -->
        <section class="content">

          <div class="error-page">
            <h2 class="headline text-yellow"> <%=response.getStatus() %>  </h2>
			  <div class="error-content">
              <h3><i class="fa fa-warning text-yellow"><strong><spring:message code="error.InternalServerError" /> </strong></i> 
	           	 <p><small class="text-red"> <small class="text-red"><i class="fa fa-cog"></i></small> <%=ClassUtils.getShortName(exception.getClass()) %></small></p>
	          	 <P style="line-height:12px;"><small style="font-size: 13px; "> <spring:message code="error.msg.common.exception.title" />
	          	 	<br><spring:message code="error.msg.common" />
	          	 </small></p>
             	<p style=" margin-top: 20px; margin-bottom:10px; ">
              		<a href="/" class="btn btn-primary btn-xs" >Home</a> | 
	        		<a href="javascript:void(0);" onClick="history.back();" class="btn btn-danger btn-xs">Back</a>
              	</p>
              	
              </h3>
            </div><!-- /.error-content -->
          </div><!-- /.error-page -->
          
          
        
        </section><!-- /.content -->
        
        <c:if test="${msgShow eq 'Y'}">
          <div class="box">
        	<div class="box-header with-border no-padding">
            	<h4 style="margin-left: 10px;"><i class="fa fa-file-text-o"></i> <font class="text-light-blue">Exception</font> print stack trace</h4>
               	<div class="box-tools pull-right">
                	<small></small>
                </div>
            </div><!-- /.box-header -->
			<div class="box-body">
				<pre class="prettyprint" style="border: 1px solid #fafafa!important;"><%=sw %></pre>
            </div>
          </div> <!-- /.box -->
        </c:if>
    </div><!-- /.content -->
    
</body>

</html>
      