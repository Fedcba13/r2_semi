<%@page import="com.r2.admin.model.vo.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	List<Notice> unionList = (List<Notice>)request.getAttribute("unionList");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	width: 1024px;
	text-align: center;
}
</style>
</head>
<body>
	<br>
	<hr>
	<h1>검색결과</h1>
	<%
		if (unionList != null || !unionList.isEmpty()) {
			for (Notice n : unionList) {
				if("NOT".equals(n.getNotice_No().substring(0, 3))){
	%>
		<h3><a href="<%=request.getContextPath()%>/admin/getNoticeByNo?Notice_No=<%=n.getNotice_No()%>"><%=n.getNotice_Title() %></a></h3>
		<p style="color: red;">
		&lt;공지사항&gt;
		</p>
		<%
				}else{
		%>
		<h3><a href="<%=request.getContextPath()%>/admin/getFAQByNo?FAQ_No=<%=n.getNotice_No()%>"><%=n.getNotice_Title() %></a></h3>
		<p style="color: red;">
		&lt;FAQ&gt;
		</p>
		<%
				}
		%>
		<p><%=n.getNotice_Content() %></p>
	<%
			}
		}
	%>
	<script>
		
	</script>
</body>
</html>