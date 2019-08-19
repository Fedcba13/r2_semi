<%@page import="com.r2.admin.model.vo.Notice"%>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/admin.css">

<%
Notice n = (Notice) request.getAttribute("n");
List<String> catList = (List<String>) request.getAttribute("catList");
String modCon = (String)request.getAttribute("modCon");
%>
<style>
#title {
	width: 300px;
}
</style>
<h2>공지사항 수정</h2>
<form
	action="<%=request.getContextPath()%>/admin/onlyAdmin/notice/modifyNotice"
	onsubmit="return validate();" method="POST">
	<input type="hidden" name="notice_No" value="<%=n.getNotice_No()%>"/>
	<div>
		<select name="notice_Category" id="category">
			<%
				for(String cat : catList){
			%>
			<option value="<%=cat%>"><%=cat%></option>
			<%
				}
			%>
		</select>&nbsp;&nbsp; <label for="title">제목</label> <input type="text"
			name="notice_Title" id="title" value='<%=n.getNotice_Title()%>'>
	</div>
	<br /> <br />
	<div>
		<textarea name="notice_Content" id="notice_Con" cols="70" rows="10"
			wrap="hard"><%=modCon %></textarea>
	</div>
	<input type="submit" value="수정" />
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