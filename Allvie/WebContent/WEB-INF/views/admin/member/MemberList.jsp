<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/admin.css">
<%
	List<Member> memberList = (List<Member>) request.getAttribute("memberList");
	String pageBar = (String) request.getAttribute("pageBar");
	int numPerPage = (int) request.getAttribute("numPerPage");
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
	Calendar todayCal = Calendar.getInstance();
	Calendar yesterdayCal = Calendar.getInstance();

	yesterdayCal.add(Calendar.DAY_OF_MONTH, -1);

	String today = transFormat.format(todayCal.getTime());
	String yesterday = transFormat.format(yesterdayCal.getTime());
%>
<style>
div#search-memberId {
	display: inline-block;
}

div#search-memberName {
	display: none;
}

div#search-memberEmail {
	display: none;
}

div#search-memberEnrollDate {
	display: none;
}
</style>
<script>
$(()=>{
	$("#searchType").on("change", (e)=>{
		var type = $(e.target).val();
		$(".searchFrm").hide();
		$("#search-"+type).css('display','inline-block');
		if(type=='memberEnrollDate'){
			$("#above_tb").css('width', '790px');
		}
		
	});
	$("#numPerPage").on("change", ()=>{
		$("#numPerPageFrm").submit();
	});
});
</script>
<h2>회원 관리</h2>
<br />
<div id="above_tb">
	<div id="search-container">
		검색타입&nbsp; <select id="searchType">
			<option value="memberId">아이디</option>
			<option value="memberName">회원명</option>
			<option value="memberEmail">e-mail</option>
			<option value="memberEnrollDate">가입날짜</option>
		</select>
		&nbsp;&nbsp;
		<div id="search-memberId" class="searchFrm">
			<form
				action="<%=request.getContextPath()%>/admin/onlyAdmin/member/memberFinder">
				<input type="hidden" name="searchType" value="member_Id" /> <input
					type="search" name="search_Keyword" size="25"
					placeholder="검색할 아이디를 입력하세요." /> &nbsp;<input type="submit" value="검색" />
			</form>
		</div>
		<div id="search-memberName" class="searchFrm">
			<form
				action="<%=request.getContextPath()%>/admin/onlyAdmin/member/memberFinder">
				<input type="hidden" name="searchType" value="member_Name" /> <input
					type="search" name="search_Keyword" size="25"
					placeholder="검색할 회원명을 입력하세요." /> &nbsp;<input type="submit" value="검색" />
			</form>
		</div>
		<div id="search-memberEmail" class="searchFrm">
			<form
				action="<%=request.getContextPath()%>/admin/onlyAdmin/member/memberFinder">
				<input type="hidden" name="searchType" value="member_Email" /> <input
					type="search" name="search_Keyword" size="25"
					placeholder="검색할 e-mail을 입력하세요." />&nbsp; <input type="submit"
					value="검색" />
			</form>
		</div>

		<div id="search-memberEnrollDate" class="searchFrm">
			<form
				action="<%=request.getContextPath()%>/admin/onlyAdmin/member/memberFinder">
				<input type="hidden" name="searchType" value="member_EnrollDate" />
				<input type="date" name="search_KeywordStart" size="25"
					value="<%=yesterday%>" /> ~ <input type="date"
					name="search_KeywordEnd" size="25" value="<%=today%>" />&nbsp; <input
					type="submit" value="검색" />
			</form>
		</div>

	</div>
	<div id="numPerPage-container">
		<form name="numPerPageFrm" id="numPerPageFrm"
			action="<%=request.getContextPath()%>/admin/onlyAdmin/member/memberList">
			페이지당 게시물수 <select name="numPerPage" id="numPerPage">
				<option value="20" <%=numPerPage == 20 ? "selected" : ""%>>20</option>
				<option value="10" <%=numPerPage == 10 ? "selected" : ""%>>10</option>
				<option value="5" <%=numPerPage == 5 ? "selected" : ""%>>5</option>
			</select>
		</form>
	</div>
</div>

<table id="admin_board_tb">
	<thead>
		<tr>
			<td>ID</td>
			<td>이름</td>
			<td>E-Mail</td>
			<td>가입날짜</td>
			<td>회원 분류</td>
		</tr>
	</thead>
	<tbody>
		<%
			if (memberList != null) {
				for (Member m : memberList) {
		%>
		<tr>
			<th><%=m.getMemberId()%></th>
			<th><%=m.getMemberName()%></th>
			<th><%=m.getMemberEmail()%></th>
			<th><%=m.getMemberEnrollDate()%></th>
			<th><%=m.getMemberCheck()%></th>
		</tr>
		<%
			}
			}
		%>
	</tbody>

</table>
<br />
<div id="pageBar">
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<%=pageBar%>
		</ul>
	</nav>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>