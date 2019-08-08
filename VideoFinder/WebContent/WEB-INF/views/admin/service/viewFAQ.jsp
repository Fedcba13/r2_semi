<%@page import="com.r2.admin.model.vo.FAQ"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	FAQ f = (FAQ)request.getAttribute("f");
	
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
  html{
            width: 1024px;
            text-align: center;
        }
</style>
<body>
    <h2><%=f.getFAQ_Title() %></h2>
    <br>
    <br>
    <%=f.getFAQ_Content() %>
    
</body></html>