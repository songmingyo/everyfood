<%@ page language="java"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page pageEncoding="utf-8"%>

<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>


<div style="text-align: center; width: 800px; margin: 100 auto;"  >
	  <div class="error_cnt" id="error_cnt" >
		 <img src="/resources/images/common/error/img-warning.gif" />
		 <br />
		 <strong style="color:#b63740;">
		 	계약서 파일이 존재하지 않거나 손상되었습니다.
		 </strong>
		 <br>
		 &nbsp; &nbsp; <spring:message code="error.msg.common" />
	  </div>
</div>



