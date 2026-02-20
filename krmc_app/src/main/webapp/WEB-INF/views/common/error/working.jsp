<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<html>
<head>
<title>working</title>


<script>	

/* onLoad or key event */
$(document).ready(function($){
	

});



</script>
		
</head>
<body>
<form id="sample" name="searchFrm" method="post">

<div id="section">
	<jsp:include page="/WEB-INF/views/common/include/incPageTitle.jsp" />
	<!-- Data 그리트 영역 start ------------------------->
	<div  style="text-align: center; height: 500px;">
		
		<br><br><img src='<c:url value="/resources/images/common/error/img-warning.gif" />' >
		<br><br><strong>Working programing...</strong>
	</div>
	<!-- Data 그리트 영역 end --------------------------->				     
 </div>	
 </form>		

	
</body>
</html>
