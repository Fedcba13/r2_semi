<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="com.r2.admin.model.vo.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/admin.css">
<%
	List<Notice> unionList = (List<Notice>)request.getAttribute("unionList");
%>

	<br>
	<hr>
	<h1>검색결과</h1>
	<%
		if (unionList != null || !unionList.isEmpty()) {
			for (Notice n : unionList) {
				if("NOT".equals(n.getNotice_No().substring(0, 3))){
	%>
		<h3><a href="<%=request.getContextPath()%>/admin/notice/getNoticeByNo?Notice_No=<%=n.getNotice_No()%>"><%=n.getNotice_Title() %></a></h3>
		<p style="color: red;">
		&lt;공지사항&gt;
		</p>
		<%
				}else{
		%>
		<h3><a href="<%=request.getContextPath()%>/admin/FAQ/getFAQByNo?FAQ_No=<%=n.getNotice_No()%>"><%=n.getNotice_Title() %></a></h3>
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
<%@ include file="/WEB-INF/views/common/footer.jsp" %>