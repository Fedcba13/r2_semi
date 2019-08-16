<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="com.r2.admin.model.vo.FAQ"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<FAQ> fAQList = (List)request.getAttribute("fAQList");
	int category = (int)(request.getAttribute("category"));
	
%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/admin.css">
<style>
#searchInput {
	width: 800px;
}

div {
	display: inline-block;
}
</style>

	<h1>고객센터</h1>
	<form action="<%=request.getContextPath()%>/union/getUnionList"
		method="POST">
		<input type="text" name="search_Keyword" id="searchInput"
			placeholder="어떤 도움이 필요하세요?">
		<button type="submit">검색</button>
	</form>
	<div id="div_01">
		<h2>축구</h2>
	</div>
	<div id="div_02">
		<h2>체육관</h2>
	</div>
	<div id="div_03">
		<h2>세번쨰 범주</h2>
	</div>

	<script>
		   function kk(){
				   <%
				   		for(FAQ f : fAQList){
				   			if("가입 절차".equals(f.getFAQ_Category())){
			   	
				   %>
					var html = "";				   
					html += '<a href="<%=request.getContextPath()%>/admin/getFAQByNo?FAQ_No=<%=f.getFAQ_No()%>"><%=f.getFAQ_Title()%></a>';
					html += "<br>";
					$("#div_01").append(html);
				   <%
				   			}else if("내 계정 관리".equals(f.getFAQ_Category())){
				   %>
				   var html = "";				   
				   html += '<a href="<%=request.getContextPath()%>/admin/getFAQByNo?FAQ_No=<%=f.getFAQ_No()%>"><%=f.getFAQ_Title()%></a>';
					html += "<br>";
					$("#div_02").append(html);
				   
				   <%
				   			}else if("빠른 링크".equals(f.getFAQ_Category())){
				   %>
				   	var html = "";				   
				   	html += '<a href="<%=request.getContextPath()%>/admin/getFAQByNo?FAQ_No=<%=f.getFAQ_No()%>"><%=f.getFAQ_Title()%></a>';
					html += "<br>";
					$("#div_03").append(html);
				   <%
				   			}
				   		}
				   %>
			   
		   };
		   window.onload=kk;
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>