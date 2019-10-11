<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">

	<br>
	<hr>
	<h1>검색결과</h1>
	
	<c:if test="not empty unionList">
		<c:forEach var="n" items="${unionList }">
			<c:if test="fn:substring(n.notice_No, 0, 3) == 'NOT'">
		<h3><a href="${pageContext.request.contextPath}/admin/notice/getNoticeByNo?Notice_No=${n.notice_No}">${n.notice_Title}</a></h3>
		<p style="color: #f0522a;">
		&lt;공지사항&gt;
		</p>
		</c:if>	
			<c:if test="fn:substring(n.notice_No, 0, 3) != 'NOT'">
		<h3><a href="${pageContext.request.contextPath}/admin/FAQ/getFAQByNo?FAQ_No=${n.notice_No}">${n.notice_Title}</a></h3>
		<p style="color: #f0522a;">
		&lt;FAQ&gt;
		</p>
		</c:if>
		<div style="text-align: left; width: 600px;">
			<p>${n.notice_Content}</p>
		</div>
		<hr />
	</c:forEach>
	</c:if>
	<script>
		
	</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />