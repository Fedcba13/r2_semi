<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/admin.css">

<%
	List<String> catList = (List<String>)request.getAttribute("catList");
%>
<h2>공지사항 쓰기</h2>
<form action="<%=request.getContextPath()%>/admin/writeNotice"
	onsubmit="return validate();" method="POST">
	<div>
		<select name="notice_Category" id="category">
			<%
				for(String cat : catList){
			%>
			<option value="<%=cat%>"><%=cat%></option>
			<%
				}
			%>
		</select> <label for="title">제목</label> <input type="text" name="notice_Title"
			id="title">
	</div>
	<div>
		<label for="content">내용</label>
		<textarea name="notice_Content" id="notice_Con" cols="30" rows="10"
			wrap="hard"></textarea>
	</div>
	<input type="button" value="d" onclick="validate();" /> <input
		type="submit" value="작성" />
</form>
<script>
	function validate(){
		var $cateogory = $("#category option:selected").val();
		
		var $title = $("#title").val();		
		var $content = $("#notice_Con").val();
		console.log($content);
		$content = $content.replace(/(?:\r\n|\r|\n)/g, '<br />');
		console.log($content);
		
		$("#notice_Con").val($content);
		
		return true;
		
	}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>