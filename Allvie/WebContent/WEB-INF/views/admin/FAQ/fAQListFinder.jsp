<%@page import="com.r2.admin.model.vo.FAQ"%>
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
	List<FAQ> fAQList = (List)request.getAttribute("fAQList");
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
		$("#searchFAQ").submit();
	});
	$("#categoryChoice").on("change", ()=>{

		$("#searchFAQ").submit();
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

<form action="<%=request.getContextPath()%>/admin/faq/fAQFilter"
	method="POST" id="searchFAQ">
	<input type="text" name="search_Keyword" id="searchInput"
		value="<%=search_Keyword==null? "" : search_Keyword%>">
	<button type="submit">검색</button>
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
		페이지당 게시물수 <select name="numPerPage" id="numPerPage">
			<option value="20" <%=numPerPage == 20? "selected":""%>>20</option>
			<option value="10" <%=numPerPage == 10? "selected":""%>>10</option>
			<option value="5" <%=numPerPage == 5? "selected":""%>>5</option>
		</select>
	</form>



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
			if(fAQList != null || !fAQList.isEmpty()){
				for(FAQ f : fAQList){
				
		%>
		<tr>
			<th><%=f.getFAQ_No() %></th>
			<th><%=f.getFAQ_Category() %></th>
			<th><a
				href="<%=request.getContextPath() %>/admin/getFAQByNo?FAQ_No=<%=f.getFAQ_No() %>"><%=f.getFAQ_Title() %></a></th>
			<th><%=f.getFAQ_Writer() %></th>
			<th><%=f.getFAQ_Date() %></th>
			<th><%=f.getFAQ_Readcount() %></th>
		</tr>
		<%
				}
			}
				
%>

	</tbody>

</table>


<div id="pageBar">
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<%=pageBar%>
		</ul>
	</nav>
</div>
<%
	if((memberLoggedIn !=null) &&("admin".equals(memberLoggedIn))){
		
%>
<button onclick="goWrite();">글쓰기</button>

<%
	}
%>

<script>
	function goWrite(){
		location.href = "<%=request.getContextPath() %>/admin/onlyAdmin/FAQ/goWriteFAQView";
	}
</script>




<%@ include file="/WEB-INF/views/common/footer.jsp"%>