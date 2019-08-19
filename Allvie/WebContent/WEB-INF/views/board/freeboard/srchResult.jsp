<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%@page import="com.r2.board.model.vo.FreeBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	List<FreeBoard> flist = (List<FreeBoard>) request.getAttribute("flist");
	String pageBar = (String) request.getAttribute("pageBar");
	String keyword = (String) request.getAttribute("keyword");
%>

<script src="<%=request.getContextPath()%>/js/freeboard_bootstrap_js/bootstrap.js"></script> <!-- 부트스트랩 기본 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/freeboard_bootstrap_css/bootstrap.css"> <!-- 부트스트랩 기본 -->

<style>

.cPage {
	font-size: 30px;
}
#srchPanel{

		margin: auto;
		text-align: center;
}

#pageBar{
		margin: auto;
		text-align: center;
		
}

#main-text{
	padding-top: 50px;
	padding-bottom: 50px;
	text-align: center;
}
#main{
	width: 700px;
	height: 100%;
	margin: auto;
}

#goBackToBoard{
	text-align: center;
	margin: 10px;
	color: black;
}

select#srchType{
	height: 30px;
	color: black;
}

input[name=keyword]{
	color: black;
}

form[name=srchForm] > input[type=submit]{
	color: black;
}
</style>


<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath()%>/js/freeboard_bootstrap_js/jquery-3.4.1.js"></script> <!-- jquery  -->
<title>"<%=keyword%>의 검색결과"</title>
</head>
<body>
	<h2 id="main-text">"<%=keyword%>"의 검색 결과
	</h2>

	<div id="main">
		<table id="tbl-board" class="table table-hover">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>


			<%
				for (FreeBoard f: flist) {
			%>
			<tr>
				<%
					int idx = f.getFree_Board_No().indexOf("_");
				%>
				<td><%=f.getFree_Board_No().substring(idx + 1)%></td>
				<td><a
					href="<%=request.getContextPath()%>/board/freeBoardView?boardNo=<%=f.getFree_Board_No()%>">
						<%=f.getFree_Board_Title()%>
				</a></td>
				<td><%=f.getFree_Board_Writer()%></td>
				<td><%=f.getFree_Board_Date()%></td>
				<td><%=f.getFree_Board_ReadCount()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<br />
		<div id='pageBar'>
			<%=pageBar%>
		</div>
		<br />
		<!-- 검색창 -->

		<div id="srchPanel">
		<form action="<%=request.getContextPath()%>/board/boardSrch"
			method="get" name="srchForm">
			<select name="srchType" id="srchType">
				<option value="" >--선택--</option>
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="uniSrch" selected="selected">통합검색</option>
			</select> <input type="text" placeholder="검색어를 입력" name="keyword" /> <input
				type="submit" value="검색" />
		</form>
		<input type="button" value="게시판으로 돌아가기" id="goBackToBoard" />
		</div>


	</div>
	
<script type="text/javascript">

$("#goBackToBoard").click(function () {
	

	location.href 
	= "<%=request.getContextPath()%>/board/freeBoard";	
});


</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
