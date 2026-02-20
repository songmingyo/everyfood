<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/views/common/include/incTaglib.jsp"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<script>
$(document).ready(function(){
	_setNextUrlTab('<c:out value='${frameMenu.menuUrl}'/>','<c:out value='${frameMenu.menuId}'/>','<c:out value='${frameMenu.titleLocale}'/>','<c:out value='${frameMenu.parentId}'/>','<c:out value='${frameMenu.sysCode}'/>');
});
</script>
</html>
