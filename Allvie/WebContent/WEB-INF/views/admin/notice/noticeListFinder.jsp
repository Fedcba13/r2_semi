<%@page import="com.r2.admin.model.vo.Notice"%>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/admin.css">
<!DOCTYPE html>
<%
	List<Notice> notList = (List<Notice>)request.getAttribute("notList");
	String pageBar = (String)request.getAttribute("pageBar");
	int numPerPage = (int)request.getAttribute("numPerPage");
	List<String> catList = (List<String>)request.getAttribute("catList");
	String nowCat = (String)request.getAttribute("cat");
	String search_Keyword = (String)request.getAttribute("search_Keyword");
	
%>

<script>
$(()=>{
	$("#categoryChoice").on("change", ()=>{
		$("#catFrm").submit();
	});
	
	$("#searchType").on("change", (e)=>{
		var type = $(e.target).val();
		
		$(".searchFrm").hide();
		$("#search-"+type).css('display','inline-block');
		
	});

	$("#numPerPage").on("change", ()=>{

		console.log("zzz")
		$("#searchNotice").submit();
	});
	$("#categoryChoice").on("change", ()=>{

		$("#searchNotice").submit();
	});
	
});
	function srchValidate(){

		return true;
	}
</script>
<ul class="list-group list-group-horizontal" id="serviceUl">
  <li class="list-group-item"><a href="<%=request.getContextPath()%>/admin/notice/noticeList">공지사항</a></li>
  <li class="list-group-item"><a href="<%=request.getContextPath()%>/admin/fAQ/fAQList">FAQ</a></li>
</ul>

<form action="<%=request.getContextPath()%>/admin/notice/noticeFilter"
	method="POST" id="searchNotice">
	<select name="cat" id="categoryChoice">
		<option value="" selected>분류 선택</option>
		<%
				for(String cat : catList){
			%>
		<option value="<%=cat%>"  <%=cat.equals(nowCat)? "selected" : "" %>><%=cat%></option>
		<%
				}
			%>
	</select>
	<input type="text" name="search_Keyword" id="searchInput"
		value="<%=search_Keyword==null? "" : search_Keyword%>">
	<button type="submit">검색</button>
		페이지당 게시물수 <select name="numPerPage" id="numPerPage">
			<option value="20" <%=numPerPage == 20? "selected":""%>>20</option>
			<option value="10" <%=numPerPage == 10? "selected":""%>>10</option>
			<option value="5" <%=numPerPage == 5? "selected":""%>>5</option>
		</select>
	</form>

<br />

<table id="admin_board_tb">

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
			if(notList != null || !notList.isEmpty()){
				for(Notice n : notList){
				
		%>
		<tr>
			<th><%=n.getNotice_No() %></th>
			<th><%=n.getNotice_Category() %></th>
			<th><a
				href="<%=request.getContextPath() %>/admin/notice/getNoticeByNo?Notice_No=<%=n.getNotice_No() %>"><%=n.getNotice_Title() %></a></th>
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

<br />
<div style="width: 700px; ">
	<div id="pageBar">
		<nav aria-label="Page navigation example">
			<ul class="pagination">
				<%=pageBar%>
			</ul>
		</nav>
	</div>
	<div style="float: right;">
		<%
			if ((memberLoggedIn != null) && ("admin".equals(memberLoggedIn.getMemberId()))) {
		%>
		<button onclick="goWrite();">글쓰기</button>


		<%
			}
		%>
	</div>
</div>
<script>
	function goWrite(){
		location.href = "<%=request.getContextPath() %>/admin/onlyAdmin/notice/goWriteNoticeView";
	}
</script>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>