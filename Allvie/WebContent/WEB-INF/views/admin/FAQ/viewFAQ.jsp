<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.r2.admin.model.vo.FAQ"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@page import="com.r2.admin.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/admin.css">
<%
	FAQ f = (FAQ) request.getAttribute("f");
	List<String> catList = (List<String>) request.getAttribute("catList");

%>
<style>
#modifyFrm {
	display: none;
}
</style>
<head>
<script>
	function displayModify(){
		$("#viewFrm").css("display", "none");
		$("#modifyFrm").css("display", "inline-block");
		
	}
	function validate(){
		var $cateogory = $("#category option:selected").val();
		
		var $title = $("#title").val();		
		var $content = $("#fAQ_Con").val();
		$content = $content.replace(/(?:\r\n|\r|\n)/g, '<br />');
		
		$("#fAQ_Con").val($content);
		
		return true;
		
	}
	function goDelete(){
		if(confirm("정말삭제하실겁니까")){
			$("#delFrm").submit();
			
		}
		
	}
	function goModifyFrm(){
		location.href="<%=request.getContextPath()%>/admin/fAQ/goModify?fAQ_No="+"<%=f.getFAQ_No()%>";
};
</script>
<div id="viewFrm">
	<h2><%=f.getFAQ_Title()%></h2>
	<p>게시날짜 : <%=f.getFAQ_Date() %>
	<br />
	최종 수정일 : <%=f.getFAQ_Date_Modified() %>
		</p>

	<br> <br>
	<div id="contentDiv">
		<%=f.getFAQ_Content()%>
	</div>
	<%
	if((memberLoggedIn !=null) &&("admin".equals(memberLoggedIn.getMemberId()))){
		
%>
<br />
<button onclick="goModifyFrm();">수정하기</button>
	<form action="<%=request.getContextPath()%>/admin/onlyAdmin/fAQ/deleteFAQ" id="delFrm">
		<input type="hidden" name="FAQ_No" value="<%=f.getFAQ_No()%>" />
		<button onclick="goDelete();">삭제하기</button>
	</form>

<%
	}
%>



</div>





<%@ include file="/WEB-INF/views/common/footer.jsp"%>