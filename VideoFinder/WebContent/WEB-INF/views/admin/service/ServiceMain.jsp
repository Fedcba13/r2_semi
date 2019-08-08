<%@page import="com.r2.admin.model.vo.FAQ"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	List<FAQ> fAQList = (List)request.getAttribute("fAQList");
	int category = (int)(request.getAttribute("category"));
	
%>
<script src="<%=request.getContextPath()%>/jquery/jquery-3.4.1.js"></script>
<style>
html {
	width: 1024px;
	text-align: center;
}

#searchInput {
	width: 800px;
}

div {
	display: inline-block;
}
</style>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
</head>
<body>
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
				   			if("축구".equals(f.getFAQ_Category())){
			   	
				   %>
					var html = "";				   
					html += '<a href="<%=request.getContextPath()%>/admin/getFAQByNo?FAQ_No=<%=f.getFAQ_No()%>"><%=f.getFAQ_Title()%></a>';
					html += "<br>";
					$("#div_01").append(html);
				   <%
				   			}else if("체육관".equals(f.getFAQ_Category())){
				   %>
				   var html = "";				   
				   html += '<a href="<%=request.getContextPath()%>/admin/getFAQByNo?FAQ_No=<%=f.getFAQ_No()%>"><%=f.getFAQ_Title()%></a>';
					html += "<br>";
					$("#div_02").append(html);
				   
				   <%
				   			}else if("세번째 범주".equals(f.getFAQ_Category())){
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
</body>
</html>