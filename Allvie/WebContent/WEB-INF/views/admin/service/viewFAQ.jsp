<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="com.r2.admin.model.vo.FAQ"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/admin.css">
<%
	FAQ f = (FAQ)request.getAttribute("f");
	
%>

<body>
    <h2><%=f.getFAQ_Title() %></h2>
    <br>
    <br>
    <%=f.getFAQ_Content() %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>