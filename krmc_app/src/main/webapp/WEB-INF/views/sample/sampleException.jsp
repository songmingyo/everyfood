<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>

<script type="text/javascript">


$(document).ready(function(){
	/**
	* 핸들러에서 처리
	*/
	$('#btnExcept1').click(function(e){
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false
		      , url  : '<c:url value="/app/sample/sampleException.json" />'
		      , data : null
		      , success : function(json){
		    	  alert('success');
		      }
		});
	});
	/**
	* 개발자가 Exception 받아서 처리할때
	*/
	$('#btnExcept2').click(function(e){
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false
		      , url  : '<c:url value="/app/sample/sampleExceptionResult.json" />'
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
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false
		      , url  : '<c:url value="/app/sample/abcdefg.json" />'
		      , data : null
		      , success : function(json){
		      }
		});
	});
	/**
	*SimpleMappingExceptionResolver 넘어가서 처리
	- 핸들러에서 처리(500)
	*/
	$('#btnExcept4').click(function(e){
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false
		      , url  : '<c:url value="/app/sample/sampleExceptionThrow.json" />'
		      , data : null
		      , success : function(){
		      }
		});
	});
	
	/**
	*Controller 에서 SendError 한 경우
	- 핸들러에서 처리(500)
	*/
	$('#btnExcept5, #btnExcept6').click(function(e){
		$.ajax({
		      contentType : 'application/json; charset=utf-8', type : 'post', dataType : 'json', async : false
		      , url  : '<c:url value="/app/sample/sampleExceptionSendError.json" />'
		      , data : null
		      , success : function(){
		      }
		});
	});

});


</script>
</head>
<body>
<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
<fieldset>

 <div class="tit_area" >
	<h2 class="subhead"> &nbsp;jquery.handler.js</h2>
</div>
<legend>검색</legend>
	
		<table class="type1" summary="">
			<caption>검색</caption>
			<colgroup>
				<col width="250">
				<col width="*">
			</colgroup> 
			<tbody id="_search">
            	<tr>
                	<th ><label for="sele2">ajaxError 걸리는 경우</label></th>
                    <td>
                    	  <html:button id="btnExcept1" auth="save"   value="Controller SendError"   /> :핸들러에서 처리
                    	    <br><br>
						  <html:button id="btnExcept3" auth="save"  value="Wrong URL"   /> : 잘못된 URL 호출 404에러 발생
						  <br><br>
						  <html:button id="btnExcept4" auth="save"  value="Controller Throws (개발표준)"   />  : ExceptionResolver 넘어가서 처리
						  <br><br>
						  <html:button id="btnExcept5" auth="save"  value="Controller Throws + SendError"   /> : Controller 에서 SendError 한 경우(response.sendError....)
                    </td>
                </tr>
                <tr>
                	<th><label for="sele2">ajaxError 걸리지 않는 경우</label></th>
                    <td>
                		<html:button id="btnExcept2" auth="save" value="return Result.put(error)"   /> (개발자가 Exception 받아서 처리할때 후처리를 해야할경우)
                	</td>
                </tr>		
               
            </tbody>
        </table>	
  
		<div class="tit_area" >
			<h2 class="subhead"> &nbsp;잘못된접근 또는 Runtime 오류 발생 </h2>
		</div>

		<table class="type1" summary="">
			<caption>검색</caption>
			<colgroup>
				<col width="250">
				<col width="*">
			</colgroup> 
			<tbody id="_search">
            	<tr>
                	<th ><label for="sele2">잘못된접근(파라메터 비정상)</label></th>
                    <td><a href="/app/sample/sampleException.do?param=AccessDenied">AccessDeniedException</a></td>
                 </tr>
                 <tr>
                	<th ><label for="sele2">런타임오류</label></th>
                	<td><a href="/app/sample/sampleException.do?param=Runtime">RuntimeException</a></td>
                </tr>
                 <tr>
                	<th ><label for="sele2">Exception(Cast Exception)</label></th>
                	<td><a href="/app/sample/sampleException.do?param=JavaLength">Java String Exception</a></td>
                </tr>
                 <tr>
                	<th ><label for="sele2"> 그외 사용자 정의 오류페이지 이동</label></th>
                	<td>
                		<a href="/app/wicketMgrCompFileDownDataException.do">FileDownDataException</a>
						<BR><a href="/app/wicketMgrCompFileDownPermissionException.do">FileDownPermissionException</a>
					</td>
                </tr>
               
            </tbody>
       </table>

     </fieldset>
 </div>	
</body>
</html>