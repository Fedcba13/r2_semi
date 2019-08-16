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
<style>
#title{
	width:300px;
}
</style>
<h2>공지사항 쓰기</h2>
<form action="<%=request.getContextPath()%>/admin/onlyAdmin/fAQ/writeFAQ"
	onsubmit="return validate();" method="POST">
	<div>
		<select name="fAQ_Category" id="category">
			<%
				for(String cat : catList){
			%>
			<option value="<%=cat%>"><%=cat%></option>
			<%
				}
			%>
		</select>&nbsp;&nbsp;  <label for="title">제목</label> <input type="text" name="fAQ_Title"
			id="title">
	</div>
	<div>
		<label for="content">내용</label>
		<textarea name="fAQ_Content" id="fAQ_Con" cols="70" rows="10"
			wrap="hard">내용을 입력하세요.</textarea>
	</div>
	<input
		type="submit" value="작성" />
</form>
<script>
	function validate(){
		var $cateogory = $("#category option:selected").val();
		
		var $title = $("#title").val();		
		var $content = $("#fAQ_Con").val();
		console.log($content);
		$content = $content.replace(/(?:\r\n|\r|\n)/g, '<br />');
		console.log($content);
		
		$("#fAQ_Con").val($content);
		
		return true;
		
	}
	$("#fAQ_Con").on("click", function(){
		$("#fAQ_Con").val("");
	});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>