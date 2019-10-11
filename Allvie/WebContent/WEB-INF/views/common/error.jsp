<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
<style>
body{
	text-align: center;
}
#e-msg{
	color:red;
	font-size: 2em;
}
</style>
</head>
<body>
	<h2>재고관리 오류</h2>
	<p><span id="e-msg">${param.msg }</span></p>
	<a href="${pageContext.request.contextPath }">
		<img src="${pageContext.request.contextPath }/images/home.png" width="30px" alt="메인페이지로 이동">
	</a>
</body>
</html>