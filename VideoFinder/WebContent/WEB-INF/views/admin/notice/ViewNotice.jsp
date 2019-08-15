<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@page import="com.r2.admin.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/admin.css">
<%
	Notice n = (Notice)request.getAttribute("n");
List<String> catList = (List<String>) request.getAttribute("catList");
%>
<style>
#modifyFrm {
	display: none;
}
</style>
<script>
	function displayModify(){
		$("#viewFrm").css("display", "none");
		$("#modifyFrm").css("display", "inline-block");
		
	}
	function validate(){
		var $cateogory = $("#category option:selected").val();
		
		var $title = $("#title").val();		
		var $content = $("#notice_Con").val();
		$content = $content.replace(/(?:\r\n|\r|\n)/g, '<br />');
		
		$("#notice_Con").val($content);
		
		return true;
		
	}
	function goDelete(){
		if(confirm("정말삭제하실겁니까")){
			$("#delFrm").submit();
			
		}
		
	}
	
</script>
<head>
<div id="viewFrm">
	<h2><%=n.getNotice_Title()%></h2>
	<p>
		게시날짜 :
		<%=n.getNotice_Date() %></p>
	<br> <br>
	<div id="contentDiv">
		<%=n.getNotice_Content() %>;
	</div>


	<button onclick="displayModify();">수정하기</button>
	<form action="<%=request.getContextPath()%>/admin/notice/deleteNotice"
		id="delFrm">
		<input type="hidden" name="notice_No" value="<%=n.getNotice_No()%>" />
		<button onclick="goDelete();">삭제하기</button>
	</form>
</div>


<form action="<%=request.getContextPath()%>/admin/notice/modifyNotice"
	onsubmit="return validate();" method="POST" id="modifyFrm">
	<div>
		<select name="notice_Category" id="category">
			<%
				for (String cat : catList) {
			%>
			<option value="<%=cat%>"
				<%=cat.equals(n.getNotice_Category()) ? "seleted" : ""%>><%=cat%></option>
			<%
				}
			%>
		</select> <label for="title">제목</label> <input type="text" name="notice_Title"
			id="title" value="<%=n.getNotice_Title()%>">
	</div>
	<div>
		<label for="content">내용</label>
		<textarea name="notice_Content" id="notice_Con" cols="30" rows="10"
			wrap="hard"><%=n.getNotice_Content()%></textarea>
	</div>
	<input type="hidden" name="notice_No" value="<%=n.getNotice_No()%>" /> <input
		type="submit" value="작성" />
</form>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>