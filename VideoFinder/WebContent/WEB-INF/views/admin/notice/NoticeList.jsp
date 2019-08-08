<%@page import="com.r2.admin.model.vo.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	List<Notice> list = (List<Notice>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	int numPerPage = (int)request.getAttribute("numPerPage");
%>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath()%>/jquery/jquery-3.4.1.js"></script>
<title>Insert title here</title>
</head>
<script>
$(()=>{
	$("#searchType").on("change", (e)=>{
		var type = $(e.target).val();
		
		$(".searchFrm").hide();
		$("#search-"+type).css('display','inline-block');
		
	});

	$("#numPerPage").on("change", ()=>{
		$("#numPerPageFrm").submit();
	});
});
</script>
<body>
<h2>게시판</h2>

	
		<div id="numPerPage-container">
			<form name="numPerPageFrm" id="numPerPageFrm">
				페이지당 게시물수
				<select name="numPerPage" id="numPerPage">
					<option value="20" <%=numPerPage == 20? "selected":""%>>20</option>
					<option value="10" <%=numPerPage == 10? "selected":""%>>10</option>
					<option value="5" <%=numPerPage == 5? "selected":""%>>5</option>
				</select>
			</form>
		</div>
	<table>

		<thead>
			<tr>
				<th>번호</th>
				<th>분류</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		<%
			if(list != null || !list.isEmpty()){
				for(Notice n : list){
				
		%>
			<tr>
				<th><%=n.getNotice_No() %></th>
				<th><%=n.getNotice_Category() %></th>
				<th><%=n.getNotice_Title() %></th>
				<th><%=n.getNotice_Writer() %></th>
				<th><%=n.getNotice_Date() %></th>
				<th><%=n.getNotice_Readcount() %></th>
			</tr>
		<%
				}
			}
		%>
		</tbody>

	</table>

	<div id="pageBar">
		<%=pageBar%>
	</div>

</body>
</html>