<%@ page language="java" isELIgnored="false" import="java.io.*" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"       uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> PAGE ERROR</title>

<script language="javascript">
	
	$(document).ready(function($){
		
		$('#opacityError').show();
		$('#wrap_error').show();
		
		
		var wrapper = false;
		$('#wrapper').each(function() {
			wrapper = true;
		});
		
		if(!wrapper) $("#wrap_error").css("margin","241px auto 0 -315px");
			
		
	});

</script>
<style type="text/css">
	html, body {  height: 100%;  
	margin: 0;
	padding: 0;
}

*	{margin:0; padding:0;}
html {}
/*  body	 { background-color:#333; font:12px Malgun Gothic, Dotum, sans-serif; color:#333;} */

img	{border:0;}
p	{margin:0; padding:0}
dl, dt, dd	{margin:0; padding:0; vertical-align:top}
img		{font-size:0}

#wrap_error	{position:absolute; left:50%; top:50%; width:544px; height:243px; margin:-141px auto 0 -315px; padding:39px 47px 0 40px;  background-color:#ffffff; border-radius: 10px / 10px;}

.error_layer_warp {position:absolute; top:50%; left:50%;  z-index:10010;}

.error_top	 	{overflow:hidden; height:36px; margin-bottom:30px;}
.error_top h1	{float:left;  color:#000; font:30px ProximaNovaSoft,sans-serif;letter-spacing:-1px;padding:0;}
.error_top span	{float:right; margin-top:3px;}
.error_cnt	 	{ font:13px ProximaNovaSoft,sans-serif;letter-spacing:1px;line-height:20px; padding:2; overflow:hidden; height:30px; margin-bottom:20px; padding:20px 0 50px 10px; border-top:1px solid #e3e3e3; border-bottom:1px solid #e3e3e3;}

.error_message  {font-weight:bold; font-family: "Helvetica, Arial, Geneva, sans-serif"; color: #b63740; font-size: 10pt;}
.error_code 	{font-weight:bold; font-family: "Helvetica, Arial, Geneva, sans-serif"; color: #585eb6; font-size: 8pt; margin-left: 10px;}
.error_result 	{font-weight:bold; font: 11px/10px "Helvetica Neue", Helvetica, Arial, Geneva, sans-serif; color: #313131; margin-left: 20px;}
.error_common 	{font-family: "Verdana, Arial, Helvetica, sans-serif"; color: #313131; font-size: 10pt; margin-left: 20px; }

.btn_error {text-align:center;}

/*padding margin*/
.mr20 {margin-right:20px;}

</style>
</head>
<body>
<spring:eval expression="@config['exception.message.view']" var="msgShow"/>

<div class="error_layer_warp" id="wrap_error" >
		<div>
			<div class="error_top">
				<h1>
					<strong>
	
					<c:choose>
				 		<c:when test="${not empty exception.exceptionName}">
				 			<c:out value="${exception.exceptionName}" />
				 		</c:when>
				 		<c:otherwise>
				 			<spring:message code="error.CommonException" /> <%-- COSTOM EXCEPTION --%>
				 		</c:otherwise>
				 	</c:choose>
					</strong>
				</h1>
				<span><img src="<c:url value="/resources/images/pearl/main/logoW.png"/>" alt="Launching System" width="100;" /></span>
			</div>
		
			<div class="error_cnt" id="error_cnt" >
				<i class="fa fa-warning text-yellow"></i>
				<strong style="color:#b63740;">
					<c:choose>
				 	<c:when test="${not empty exception.httpStatus}">
				 		<c:out value="${exception.httpStatus}" />
				 	</c:when>
				 	<c:otherwise>
				 		<spring:message code="error.msg.common.exception.title" />
				 	</c:otherwise>
				 </c:choose>
				</strong>
					
				<p style="margin-left: 23px;">
				<c:choose>
				 	<c:when test="${not empty exception.errMessage}">
				 		<c:out value="${exception.errMessage}" />
				 	</c:when>
				 	<c:otherwise>
				 		<spring:message code="error.msg.common" />
				 	</c:otherwise>
				 </c:choose>
				 </p>
			</div>
		
			 <div>
	         	<a href="/" class="btn btn-primary btn-xs" >Home</a> | 
	            <a href="javascript:void(0);" onClick="history.back();" class="btn btn-danger btn-xs">Back</a>
	         </div>
        </div>
         
<c:if test="${msgShow eq 'Y'}">
		 <div class="box" style="margin-top: 10px;  width:1000px;  position: absolute;  top: 100%; left: -30%;">
        	<div class="box-header with-border no-padding ">
            	<h4><strong><i class="fa fa-file-text-o"></i> <font class="text-light-blue">Exception</font> print stack trace</strong></h4>
               	<div style="font-size: 8px; margin-left: 25px;">
               	  <p><strong>SIMPLE NAME : </strong> <c:out value="${exception.simpleName}"/> </p>
				  <p><strong>TYPE NAME : </strong> <c:out value="${exception.typeName}"/> </p>
				</div>
            </div><!-- /.box-header -->
            
			<div style=" margin-top: 10px;  border-top:  1px solid #7c7c7c;">
				<p style="padding: 5px;"><c:out value="${exception.stackTrace}"/></p>
			</div> 
        </div> 
</c:if>
     
</div>
    
 	 
	
	
</body>
</html>






