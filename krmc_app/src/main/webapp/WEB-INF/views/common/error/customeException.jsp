<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<spring:eval expression="@config['exception.message.view']" var="msgShow"/>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CUSTOM PAGE ERROR</title>
 <!-- Bootstrap 3.3.4 -->
<link href="<c:url value="/resources/almasaeed/bootstrap/css/bootstrap.min.css"/>"	rel="stylesheet" type="text/css" />

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

	#wrap_error	{ position:absolute;  z-index:10010;  width: 100%; height: 100%; }
	#wrap_error .contentBox{
		width:800px;
	    height:150px;
	    margin:0 auto;
    }
    
	.headline{font-size: 80px; font-weight: 400; float: left; margin-right: 20px;}
	
	.msgbox{
		position: relative;
	    border-radius: 3px;
	    background: #ffffff;
	    border-top: 3px solid #d2d6de;
	    margin-bottom: 20px;
	    width: 80%;
	    box-shadow: 0 1px 1px rgb(0 0 0 / 10%);
	    
	    width:65%;
	    margin:0 auto;
    }
    
    .msgbox-body{
	    border-top-left-radius: 0;
	    border-top-right-radius: 0;
	    border-bottom-right-radius: 3px;
	    border-bottom-left-radius: 3px;
	    padding: 10px;
    }
    
    pre{
	    display: block;
	    padding: 9.5px;
	    margin: 0 0 10px;
	    font-size: 10px;
	    line-height: 1.42857143;
	    color: #333;
	    word-break: break-all;
	    word-wrap: break-word;
	    background-color: #f5f5f5;
	   
	    border-radius: 4px;
	    overflow:scroll; height:400px;
	    border: 1px solid #fafafa!important;
    }
   
   	.msgbox-header {  border-bottom: 1px solid #f4f4f4;}
   
	.msgbox-header h4{font-size: 12px; 
	     margin-top:5px;
	   	 margin-bottom: 5px;
	   	 margin-left: 5px;
  	} 
  
	.errMessage{font-size: 13px;}
  
	.text-yellow{color: #f39c12 !important; }
	.text-red {color: #dd4b39 !important;}
	.text-light-blue{ color: #3c8dbc !important; }
    
	.btn-box{height: 40px; width:500px; margin-top: 10px; margin-bottom:10px;  border-top: 1px solid #f4f4f4; }
	.btn-box p{margin-top: 10px;}
</style>


</head>
<body>


 
    

<div class="error_layer_warp" id="wrap_error" >

	<div class="contentBox">
        <h2 class="headline text-yellow"> <%=response.getStatus() %>  </h2>
		<div class="" >
              <h3>
              	<i class="fa fa-warning text-yellow">
              		<strong>
              		<c:choose>
				 		<c:when test="${not empty customException.exceptionName}">
				 			<c:out value="${customException.exceptionName}" />
				 		</c:when>
				 		<c:otherwise>
				 			<spring:message code="error.CommonException" /> <%-- COSTOM EXCEPTION --%>
				 		</c:otherwise>
				 	</c:choose>
				 	</strong>
              	</i> 
              	<p><small class="text-red"><i class="fa fa-cog"></i> <c:out value="${customException.simpleName}" /></small></p>
	          	<P><small class="errMessage"> 
                <c:choose>
				 	<c:when test="${not empty customException.errMessage}">
				 		<c:out value="${customException.errMessage}" />
				 	</c:when>
				 	<c:otherwise>
				 		<spring:message code="error.msg.common" />
				 	</c:otherwise>
				</c:choose>
				</small>
              	</p>
              </h3>
         </div><!-- /.contentBox 			-->
         <div class="btn-box">
	      	<p>
	      	<a href="/" class="btn btn-primary btn-xs" >Home</a> | 
	        <a href="javascript:void(0);" onClick="history.back();" class="btn btn-danger btn-xs">Back</a>
	        </p>
	     </div>
    </div><!-- /.content 					-->

<c:if test="${msgShow eq 'Y'}">
	<div class="msgbox">
		<div class="msgbox-header">
			<h4><i class="fa fa-file-text-o"></i> <font class="text-light-blue">Exception</font> print stack trace</h4>
		</div><!-- /.box-header -->
		<div class="msgbox-body">
			<pre><c:out value="${customException.stackTrace}" /></pre>
    	</div>
    </div> <!-- /.box -->
</c:if>
</div>




 </body>
 </html>
    
