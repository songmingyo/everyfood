<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="java.io.*"   %>
<%@ page import="org.springframework.http.HttpStatus" %>
<%@ page import="org.apache.commons.lang.*"%>
<%@ page import="org.springframework.tronic.exception.*" %>
<%@ page import="org.springframework.util.ClassUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%-- 
<%	
	StringWriter sw = new StringWriter();
	PrintWriter  pw = new PrintWriter(sw);
	exception.printStackTrace(pw); 
%>
--%>

<%
	StringWriter sw =  null;
	PrintWriter  pw = null;
	try {
		sw = new StringWriter();
		pw = new PrintWriter(sw);
	} catch (Exception e) {
		throw new RuntimeException(e);
	} finally {
		if(sw!=null)  sw.close();
		if(pw!=null)  pw.close();
	}
%>

<spring:eval expression="@config['exception.message.view']" var="msgShow"/>
<link href="/resources/almasaeed/documentation/style.css" rel="stylesheet" type="text/css" />
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
        

    <!-- Main content -->
    <section class="content">
		<div class="error-page">
	    	<h1 class="headline text-yellow"><%=response.getStatus() %> </h1>
	        <div class="error-content">
	             <h3><i class="fa fa-warning text-yellow"> INTERNAL SERVER ERROR</i> 
	           	 <br><small class="text-red"> <small class="text-red"><i class="fa fa-cog"></i></small> <%=ClassUtils.getShortName(exception.getClass()) %></small>
	            </h3>
	             <p>
					Meanwhile, you may <a href='/'>return to dashboard</a> or try using the event once again.
	             </p>
	             <div>
                      <a href="/" class="btn btn-primary btn-xs" >Home</a>
                      <a href="javascript:void(0);" onClick="history.back();" class="btn btn-danger btn-xs">Back</a>
                 </div>
	    	</div><!-- /.error-content -->
	    </div><!-- /.error-page -->
	      
<%-- 
<c:if test="${msgShow eq 'Y'}">
	    <div class="box">
        	<div class="box-header with-border no-padding" style="margin-left: 10px;">
            	<h4><i class="fa fa-file-text-o"></i> <font class="text-light-blue">Exception</font> print stack trace</h4>
               	<div class="box-tools pull-right">
                	<button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip" title="Collapse"><i class="fa fa-minus"></i></button>
                	<button class="btn btn-box-tool" data-widget="remove"   data-toggle="tooltip" title="Remove"><i class="fa fa-times"></i></button>
                </div>
            </div><!-- /.box-header -->
			<div class="box-body">
				<pre class="prettyprint" style="border: 1px solid #fafafa!important;"><%=sw %></pre>
            </div>
        </div> <!-- /.box -->  
</c:if>      
--%>
    </section><!-- /.Main content -->
    
</div><!-- /.content-wrapper -->
