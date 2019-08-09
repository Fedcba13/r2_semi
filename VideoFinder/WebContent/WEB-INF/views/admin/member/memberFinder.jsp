<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/admin.css">
<%
	List<Member> memberList = (List<Member>) request.getAttribute("memberList");
	String pageBar = (String) request.getAttribute("pageBar");
	int numPerPage = (int) request.getAttribute("numPerPage");
%>
<style>
div#search-memberId{display: inline-block;}
div#search-memberName{display: none;}
div#search-memberEmail{display: none;}
div#search-memberEnrollDate{display: none;}

</style>
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
<h2>회원 관리</h2>
	<div id="search-container">
		검색타입: 
		<select id="searchType">
			<option value="memberId">아이디</option>
			<option value="memberName">회원명</option>
			<option value="memberEmail">e-mail</option>
			<option value="memberEnrollDate">가입날짜</option>
		</select>	
		<div id="search-memberId" class="searchFrm">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" 
					   name="searchType" 
					   value="member_Id" />
				<input type="search"
					   name="searchKeyword"
					   size="25"
					   placeholder="검색할 아이디를 입력하세요." />
				<input type="submit" value="검색" />
			</form>
		</div>
		<div id="search-memberName" class="searchFrm">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" 
					   name="searchType" 
					   value="member_Name" />
				<input type="search"
					   name="searchKeyword"
					   size="25"
					   placeholder="검색할 회원명을 입력하세요." />
				<input type="submit" value="검색" />
			</form>
		</div>
		<div id="search-memberEmail" class="searchFrm">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" 
					   name="searchType" 
					   value="member_Email" />
				<input type="search"
					   name="searchKeyword"
					   size="25"
					   placeholder="검색할 e-mail을 입력하세요." />
				<input type="submit" value="검색" />
			</form>
		</div>
		
		<div id="search-memberEnrollDate" class="searchFrm">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" 
					   name="searchType" 
					   value="member_EnrollDate" />
				<input type="date"
					   name="searchKeyword"
					   size="25" /> ~ 
				<input type="date"
					   name="searchKeywordEnd"
					   size="25"/>
				<input type="submit" value="검색" />
			</form>
		</div>
	
	</div>
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
                <td>ID</td>
                <td>이름</td>
                <td>E-Mail</td>
                <td>가입날짜</td>
                <td>회원 분류</td>
            </tr>
        </thead>
        <tbody>
        <%
        	if(memberList != null){
        		for(Member m : memberList){
        %>
            <tr>
                <th><%=m.getMemberId() %></th>
                <th><%=m.getMemberName() %></th>
                <th><%=m.getMemberEmail() %></th>
                <th><%=m.getMemberEnrollDate() %></th>
                <th><%=m.getMemberCheck() %></th>
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



<%@ include file="/WEB-INF/views/common/footer.jsp" %>