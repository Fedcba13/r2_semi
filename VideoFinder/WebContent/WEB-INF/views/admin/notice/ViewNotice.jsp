<%@page import="com.r2.admin.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	Notice n = (Notice)request.getAttribute("n");
	
%>
<head>
<style>
  html{
            width: 1024px;
            text-align: center;
        }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2><%=n.getNotice_Title()%></h2>
    <br>
    <br>
    <%=n.getNotice_Content() %>
    
</body>
</html>