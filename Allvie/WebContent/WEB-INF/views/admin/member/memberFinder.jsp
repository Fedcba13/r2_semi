<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
	String searchType = (String)request.getAttribute("searchType");
	String search_Keyword = (String)request.getAttribute("search_Keyword");

	
/* 	String search_KeywordStart1 = request.getAttribute("search_KeywordStart").toString();
	String search_KeywordEnd1 = request.getAttribute("search_KeywordEnd").toString();
 */
 Date search_KeywordStart1 = null;
 Date search_KeywordEnd1 = null;
 String search_KeywordStart =null;
 String search_KeywordEnd =null;
 
	if(request.getAttribute("search_KeywordStart") != null){
 	search_KeywordStart1 = (Date)request.getAttribute("search_KeywordStart");
	search_KeywordEnd1 = (Date)request.getAttribute("search_KeywordEnd");
 	
 
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	 search_KeywordStart = transFormat.format(search_KeywordStart1);
	 search_KeywordEnd = transFormat.format(search_KeywordEnd1);
	 
	}
	 System.out.println("search_Keyword : "+search_Keyword);
 
	
%>
<style>
div#search-memberId{display: <%=((search_Keyword == null) || ("member_Id".equals(searchType)))? "inline-block":"none" %>;}
div#search-memberName{display: <%="member_Name".equals(searchType)? "inline-block":"none"  %>;}
div#search-memberEmail{display: <%="member_Email".equals(searchType)? "inline-block":"none"  %>;}
div#search-memberEnrollDate{display: <%="member_EnrollDate".equals(searchType)? "inline-block":"none"  %>;}

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
			<option value="memberId" <%="member_Id".equals(searchType)? "selected":""  %>>아이디</option>
			<option value="memberName" <%="member_Name".equals(searchType)? "selected":""  %>>회원명</option>
			<option value="memberEmail" <%="member_Email".equals(searchType)? "selected":""  %>>e-mail</option>
			<option value="memberEnrollDate" <%="member_EnrollDate".equals(searchType)? "selected":""  %>>가입날짜</option>
		</select>	
		<div id="search-memberId" class="searchFrm">
			<form action="<%=request.getContextPath()%>/admin/member/onlyAdmin/memberFinder">
				<input type="hidden" 
					   name="searchType" 
					   value="member_Id" />
				<input type="search"
					   name="search_Keyword"
					   size="25"
					   value='<%="member_Id".equals(searchType)? search_Keyword:""  %>'
					   <%=(search_Keyword == null)? "placeholder='검색할 아이디를 입력해주세요.'" :""  %>
					   
					   />
				<input type="submit" value="검색" />
			</form>
		</div>
		<div id="search-memberName" class="searchFrm">
			<form action="<%=request.getContextPath()%>/admin/member/onlyAdmin/memberFinder">
				<input type="hidden" 
					   name="searchType" 
					   value="member_Name" />
				<input type="search"
					   name="search_Keyword"
					   size="25"
					    value='<%="member_Name".equals(searchType)? search_Keyword:""  %>'
					   <%=(search_Keyword == null)? "placeholder='검색할 이름를 입력해주세요.'" :""  %>  
					   />
				<input type="submit" value="검색" />
			</form>
		</div>
		<div id="search-memberEmail" class="searchFrm">
			<form action="<%=request.getContextPath()%>/admin/member/onlyAdmin/memberFinder">
				<input type="hidden" 
					   name="searchType" 
					   value="member_Email" />
				<input type="search"
					   name="search_Keyword"
					   size="25"
					    value='<%="member_Email".equals(searchType)? search_Keyword:""  %>'
					   <%=(search_Keyword == null)? "placeholder='검색할 이메일를 입력해주세요.'" :""  %>  
					   />
				<input type="submit" value="검색" />
			</form>
		</div>
		
		<div id="search-memberEnrollDate" class="searchFrm">
			<form action="<%=request.getContextPath()%>/admin/member/onlyAdmin/memberFinder">
				<input type="hidden" 
					   name="searchType" 
					   value="member_EnrollDate" />
				<input type="date"
					   name="search_KeywordStart"
					   size="25"
					    value='<%="member_EnrollDate".equals(searchType)? search_KeywordStart:""  %>'
					    /> 
					    ~ 
				<input type="date"
					   name="search_KeywordEnd"
					   size="25"
					    value='<%="member_EnrollDate".equals(searchType)? search_KeywordEnd:""  %>'
					     />
				<input type="submit" value="검색" />
			</form>
		</div>
	
	</div>
    		<div id="numPerPage-container">
			<form name="numPerPageFrm" id="numPerPageFrm" action="<%=request.getContextPath()%>/admin/onlyAdmin/member/memberFinder">
				페이지당 게시물수
				<select name="numPerPage" id="numPerPage">
					<option value="20" <%=numPerPage == 20? "selected":""%>>20</option>
					<option value="10" <%=numPerPage == 10? "selected":""%>>10</option>
					<option value="5" <%=numPerPage == 5? "selected":""%>>5</option>
				</select>
			</form>
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
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<%=pageBar%>
		</ul>
	</nav>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>