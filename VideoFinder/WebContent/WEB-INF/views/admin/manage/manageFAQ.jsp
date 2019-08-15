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
	List<FAQ> fAQList = (List<FAQ>)request.getAttribute("fAQList");
	String pageBar = (String)request.getAttribute("pageBar");
	int numPerPage = (int)request.getAttribute("numPerPage");
	List<String> catList = (List<String>)request.getAttribute("catList");
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
		$("#numPerPageFrm").submit();
	});
	
});
	function srchValidate(){
		var $searchInput = $("#searchInput").val;
		if($searchInput.trim().length == 0){
			return false;
		}
		return true;
	}
</script>

<button type="button" name="manageButton" class="btn btn-primary btn-sm" onclick="location.href='<%=request.getContextPath()%>/admin/getNoticeOfAllBoardList'">공지사항</button>
<button type="button" name="manageButton" class="btn btn-primary btn-sm" onclick="location.href='<%=request.getContextPath()%>/admin/getFAQOfAllBoardList'">FAQ</button>
<%-- <ul>
	<li><a
		href="<%=request.getContextPath()%>/admin/getNoticeOfAllBoardList">공지사항</a></li>
	<li><a
		href="<%=request.getContextPath()%>/admin/getFAQOfAllBoardList">FAQ</a></li>
	<li><a href="">??</a></li>
</ul> --%>
<form action="<%=request.getContextPath()%>/admin/searchFAQ"
	method="POST" onsubmit="return srchValidate();">
	<input type="text" name="search_Keyword" id="searchInput"
		placeholder="어떤 도움이 필요하세요?">
	<button type="submit">검색</button>
</form>
<form action="<%=request.getContextPath()%>/admin/manage/filterFAQByCat"
	id="catFrm">
	<select name="cat" id="categoryChoice">
		<option value="" selected>분류 선택</option>
		<%
				for(String cat : catList){
			%>
		<option value="<%=cat%>"><%=cat%></option>
		<%
				}
			%>
	</select>
</form>
<div id="numPerPage-container">
	<form name="numPerPageFrm" id="numPerPageFrm">
		페이지당 게시물수 <select name="numPerPage" id="numPerPage">
			<option value="20" <%=numPerPage == 20? "selected":""%>>20</option>
			<option value="10" <%=numPerPage == 10? "selected":""%>>10</option>
			<option value="5" <%=numPerPage == 5? "selected":""%>>5</option>
		</select>
	</form>
</div>





<table class="table table-dark" style="width: 830px;">



	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">분류</th>
			<th scope="col">제목</th>
			<th scope="col">작성자</th>
			<th scope="col">작성일</th>
			<th scope="col">조회수</th>
		</tr>
	</thead>


	<tbody>
		<%
			if(fAQList != null || !fAQList.isEmpty()){
				for(FAQ f : fAQList){
				
		%>
		<tr>
			<th scope="row"><%=f.getFAQ_No() %></th>
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
<button onclick="goWrite();">글쓰기</button>

<script>
	function goWrite(){
		location.href = "<%=request.getContextPath() %>/admin/goWriteNoticeView";
	}
</script>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>