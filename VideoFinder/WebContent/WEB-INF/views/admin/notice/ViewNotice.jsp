<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="com.r2.admin.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/admin.css">
<%
	Notice n = (Notice)request.getAttribute("n");
	
%>
<head>

    <h2><%=n.getNotice_Title()%></h2>
    <br>
    <br>
    <div id="contentDiv">
    	<%=n.getNotice_Content() %>;
    </div>
    
<script>
</script>    
<%@ include file="/WEB-INF/views/common/footer.jsp" %>