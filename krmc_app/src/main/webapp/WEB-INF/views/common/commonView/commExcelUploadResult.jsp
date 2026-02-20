<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<script>
	
	var msg = '${msg}';
	var excelSysKnd = '${excelSysKnd}';
	var excelWorkKnd = '${excelWorkKnd}';

	var callBackParam = {
			"callBackMsg" : msg
			, "excelSysKnd" : excelSysKnd
			, "excelWorkKnd" : excelWorkKnd
	};	
	
	parent.fncExcel(callBackParam);
	
</script>