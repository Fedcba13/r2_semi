<%@page import="com.r2.board.model.vo.FreeBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
<!DOCTYPE html>

<%
 	List<FreeBoard> list = (List<FreeBoard>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<html>
<script src="<%=request.getContextPath()%>/js/freeboard_bootstrap_js/jquery-3.4.1.js"></script> <!-- jquery  -->
<script src="<%=request.getContextPath()%>/js/freeboard_bootstrap_js/bootstrap.js"></script> <!-- 부트스트랩 기본 -->

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/freeboard_bootstrap_css/bootstrap.css"> <!-- 부트스트랩 기본 -->
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/freeboard.css" />
 --%>

<style>
#main-text{
	padding-top: 50px;
	margin-bottom: 50px;
	text-align: center;
}

#pageBar{
	text-align: center;

}

.cPage{
	font-size: 30px;
}


#srchForm{
	text-align: center;
	    margin-bottom: 5px;
	}

#srchField{
	display: inline-block;
	size: 30px;
	width: 360px;
}

#srchBtn{
	margin-bottom: 5px;
}

#main{
	width: 700px;
	height: 100%;
	margin: auto;
}

select#srchType, select#srchType option{
	color: black;
	 height: 38px;	
}

#btn-write{
	margin-bottom: 5px;
}


</style>

 <head>
<meta charset="UTF-8">
<title>freeboard</title>
</head>
<body>
	<h2 id="main-text">자유게시판에 오신것을 환영합니다~</h2>
	
	<div id="main">
		<table id="tbl-board" class="table table-hover">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<!-- 첨부파일이 있는 경우, 
		file.png이미지가 해당 td에 보이도록 하세요 -->
				
			<%for(FreeBoard fb: list){ %>
		<tr>
			<% int idx =fb.getFree_Board_No().indexOf("_");%>
			<td><%=fb.getFree_Board_No().substring(idx+1)%></td>
			<td>
				<a href="<%=request.getContextPath()%>/board/freeBoardView?boardNo=<%=fb.getFree_Board_No()%>">
					<%=fb.getFree_Board_Title() %>
				</a>
			</td>
			<td><%=fb.getFree_Board_Writer() %></td>
			<td><%=fb.getFree_Board_Date() %></td>
			<td><%= fb.getFree_Board_ReadCount()%></td>
		</tr>
		<%} %>
	</table>
	<hr />
	
		<div id='pageBar' >
		<%=pageBar %>
		</div>
				<!-- 검색창 -->
		<form action="<%=request.getContextPath()%>/board/boardSrch"
	method="get" name="srchForm" id="srchForm">
		<select name="srchType" id="srchType" class="selectpicker" >
			<option value="" ">--선택--</option>
			<option value="writer">작성자</option>
			<option value="title">제목</option>
			<option value="uniSrch" selected="selected" >통합검색</option>		
		</select>
		<input type="text" placeholder="검색어를 입력하세요	" name="keyword" id="srchField" class="form-control"/>
		<input type="submit" value="검색" class="btn btn-primary" id="srchBtn"/>
		<input type="button" value="글쓰기" id="btn-write"  class="btn btn-success"/>
		</form>
		
		<br />
		
		


		
	</div>

	
<script>

$("#btn-write").click(function () {
	

	location.href 
	= "<%=request.getContextPath()%>/board/freeBoardWrite";	
});




</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>