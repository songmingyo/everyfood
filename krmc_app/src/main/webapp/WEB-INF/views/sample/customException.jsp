<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<head>

<script type="text/javascript">

$(document).ready(function($){

	/**
	* 핸들러에서 처리
	*/
	$('#btnExcept1').click(function(e){
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json'
		      , url  : '<c:url value="/app/smp/sampleException" />'
		      , data : null
		      , success : function(json){
		    	  if(json.error != null){
			    		 alert('['+json.error.code+']'+json.error.message);
			    	 }else{
			    		 alert('success');
			    	 }
		      }
		});


	});

	/**
	* 개발자가 Exception 받아서 처리할때
	*/
	$('#btnExcept2').click(function(e){
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json'
		      , url  : '<c:url value="/app/smp/sampleExceptionResult" />'
		      , data : null
		      , success : function(json){
		    	 if(json.error != null){
		    		 alert('['+json.error.code+']'+json.error.message);
		    	 }else{
		    		 alert('success');
		    	 }
		      }
		});
	});

	/**
	* 잘못된 URL 호출 404에러 발생
	* - 핸들러에서 처리(404)
	*/
	$('#btnExcept3').click(function(e){
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json'
		      , url  : '<c:url value="/app/smp/abcdefg" />'
		      , data : null
		      , success : function(json){
		      }
		});
	});

	/**
	* throw  new AccessDeniedException
	*- 핸들러에서 처리(500)
	*/
	$('#btnExcept4').click(function(e){
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json'
		      , url  : '<c:url value="/app/smp/sampleExceptionThrow" />'
		      , data : null
		      , success : function(){
		      }
		});
	});

	/**
	* RuntimeException catch response.sendError
	- 핸들러에서 처리(500)
	*/
	$('#btnExcept5').click(function(e){
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json'
		      , url  : '<c:url value="/app/smp/sampleExceptionSendError" />'
		      , data : null
		      , success : function(){
		      }
		});
	});


});


</script>




</head>
<div id="section">
<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />



 <!-- Main content -->
       <section class="content">
          <div class="callout callout-info">
            <h4>Exception Test Page </h4>
            <p>
            	<strong>CommonExceptionResolver or CustomGenericException</strong>
            	<br>Resolver Exception (servlet-context.xml) beans property : exceptionMappings
            </p>

            <ul class="bring-up">
              	<li>Common Exception</li>
              	<li>Custom Exception</li>
              	<li>404 Not Found Exception</li>
			</ul>
          </div>
          <!-- Default box -->

          <div class="box">
            <div class="box-header with-border">
              <h3 class="box-title"><i class="fa fa-server"></i> Sample Test URL </h3>
              <div class="box-tools pull-right">

              </div>
            </div>
            <div class="box-body">
         	<%--
		         400 - Bad Request
				 500 - Internal Server Error
				 406 - Not Acceptable
				 415 - Unsupported Media Type
				 405 - Method Not Allowed
			--%>
			 	<p><small><i class="fa fa-arrow-circle-o-right"></i></small> <strong>Common Exception</strong></p>
	            	<ul>
	            		<li><a href="<c:url value="/app/smp/CommonException" />?param=JavaLength" 	class="btn btn-primary btn-xs" >500-Internal Server Error (Java.Length)</a></li>
	            		<li><a href="<c:url value="/app/smp/CommonException" />" 						class="btn btn-warning btn-xs" >500-Internal Server Error (Throw Exception)</a></li>
	            		<li><a href="<c:url value="/app/smp/CommonException" />?param=AccessDenied"  >AccessDenied</a></li>
	            		<li><a href="<c:url value="/app/smp/CommonException" />?param=Runtime" 		 >Runtime</a></li>
	            		<li><a href="/test" class="btn btn-danger btn-xs" >404-Page Not found.</a></li>
	           		</ul>
	           	<br><br>
	            <p><small><i class="fa fa-arrow-circle-o-right"></i></small> <strong>Custom Exception</strong></p>
	            	<a href="<c:url value="/app/smp/CustomExceptionE100" />" >E100-There is no transaction Data.</a>
	            	<br>
	            	<a href="<c:url value="/app/smp/CustomExceptionE200" />" >E200-The Data does not have access.</a>
	            <br><br>

          	</div><!-- /.box-body -->

            <div class="box-body">
             	<p><small><i class="fa fa-arrow-circle-o-right"></i></small> <strong>Ajax Exception</strong></p>

             	<html:button id="btnExcept1" value="Handler Exception."		 	auth="select"	/>
             	<html:button id="btnExcept2" value="Developer Exception."		auth="select"	/>
             	<html:button id="btnExcept3" value="404-Page Not found."		auth="select"	/>
             	<html:button id="btnExcept4" value="500-throw Exception."		auth="select"	/>
             	<html:button id="btnExcept5" value="403-Response SendError."	auth="select"	/>

            </div>

            </div><!-- /.box-body -->



        </section><!-- /.content -->

</div>
