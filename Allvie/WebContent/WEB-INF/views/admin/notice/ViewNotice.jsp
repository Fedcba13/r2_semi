<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@page import="com.r2.admin.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/admin.css">
<%
	Notice n = (Notice) request.getAttribute("n");
	List<String> catList = (List<String>) request.getAttribute("catList");
	String modCon = (String) request.getAttribute("modCon");
%>
<style>
#modifyFrm {
	display: none;
}
</style>
<script>
	function displayModify() {
		$("#viewFrm").css("display", "none");
		$("#modifyFrm").css("display", "inline-block");

	}
	function validate() {
		var $cateogory = $("#category option:selected").val();

		var $title = $("#title").val();
		var $content = $("#notice_Con").val();
		$content = $content.replace(/(?:\r\n|\r|\n)/g, '<br />');

		$("#notice_Con").val($content);

		return true;

	}
	function goDelete() {
		if (confirm("정말삭제하실겁니까")) {
			$("#delFrm").submit();

		}

	}

		function goModifyFrm(){
			location.href="<%=request.getContextPath()%>/admin/notice/goModify?Notice_No=" + "<%=n.getNotice_No()%>";
	};

</script>
<head>
<div id="viewFrm">
	<h2><%=n.getNotice_Title()%></h2>
	<p>
		게시날짜 :
		<%=n.getNotice_Date()%>
		<br />
		최종 수정일 : 
		<%=n.getFAQ_Date_Modified() %>
		</p>
	<br> <br>
	<div id="contentDiv">
		<%=n.getNotice_Content()%>
	</div>
	<br />
	<div style='float: right;'>
		<%
			if ((memberLoggedIn != null) && ("admin".equals(memberLoggedIn.getMemberId()))) {
		%>
		<button onclick="goModifyFrm();">수정하기</button>
		<form action="<%=request.getContextPath()%>/admin/onlyAdmin/notice/deleteNotice"
			id="delFrm">
			<input type="hidden" name="notice_No" value="<%=n.getNotice_No()%>" />
			<button onclick="goDelete();">삭제하기</button>
		</form>

		<%
			}
		%>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>