<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String loc = (String)request.getAttribute("loc");

	

%>
<script>


//dml요청시 반드시 페이지 이동시켜줄것.
location.href = "<%=request.getContextPath() + loc%>"; 
</script>