<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@page import="com.r2.board.model.vo.Scenario"%>
<%@page import="java.util.List"%>
<%@page import="com.r2.board.model.vo.BoardComment"%>
<%@page import="com.r2.board.model.vo.FreeBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Scenario s = (Scenario)request.getAttribute("s");
/* 	String memberLoggedIn = fb.getFree_Board_Writer();
 */	List<BoardComment> bclist = (List<BoardComment>)request.getAttribute("bclist");
%>

<script src="<%=request.getContextPath()%>/js/freeboard_bootstrap_js/bootstrap.js"></script> <!-- 부트스트랩 기본 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/freeboard_bootstrap_css/bootstrap.css"> <!-- 부트스트랩 기본 -->
<script src="<%=request.getContextPath()%>/js/freeboard_bootstrap_js/jquery-3.4.1.js"></script> <!-- jquery  -->

<style>


img{
	max-width: 400px;
	max-height: 500px;
}


td{
	height: 60px;
}

td#content-td{
	height: 180px;
}
section#contentPage{
	margin-top: 30px;
}

h2{
	margin-top: 50px;
	margin-bottom: 100px;
	margin: 0 auto;
	text-align: center;
}

table#board-table {
	width: 700px;
	margin: 0 auto;
/* 	border-collapse: collapse;
 */	border: 0.5px solid;
	border-top: 0.5 solid;
}

table td{
	width: 500px;
	text-align: center;
	word-break:break-all;
}

th{
	width: 100px;
	height: 50px;
	background-color: lightgray;
	
}




#buttons{
	margin-top: 25px;
	margin-left: 26em;
	text-align: center;
	

}



section#comment-section{
	margin-top: 10px;
}

table#tbl-comment{
	width:580px; 
	margin:0 auto; 
	border-collapse:collapse; 
	clear:both; 
	}


#boardCommentFrm{
	text-align: center;
}

#btn-insert{
	margin-left: 22.3em;
}
</style>

<head>
<meta charset="UTF-8">
<title><%=s.getTitle() %></title>
</head>
<body style="margin-left: 4em;">



	<section id="contentPage">
	<h2>게시글 상세보기</h2>
	<br />
	<table class="table table-hover border border-primary" id="board-table" >
		<tr>

		
		
		<tr>
		<th>제목</th>
		<td><%=s.getTitle() %></td>		
		</tr>	
			
	
		<tr>
		<th>작성자</th>
		<td><a href=""><%=s.getMember_Id() %></a></td>		
		</tr>	
		
			
		<tr>
		<th>조회수</th>
		<td><%=s.getReadCount() %></td>		
		</tr>	
		
		<tr>
		<th >내용</th>
		<td id="content-td"><p><%=s.getScenario_Content() %></p></td>		
		</tr>	
		
		
		<tr>
		<th>날짜</th>
		<td><%=s.getScenario_Date() %></td>		
		</tr>
		
	</table>
	<div id="buttons">
	<input type="button" value="목록으로 돌아가기" id="goBoardListBtn" class="btn btn-primary"/>
		<%if(memberLoggedIn!=null && 
			(s.getMember_Id().equals(memberLoggedIn.getMemberId())
			|| "admin123".equals(memberLoggedIn.getMemberId())) ){%>
	
	<input type="button" value="수정" onclick="updateFreeBoard();" class="btn btn-warning"/>
	<input type="button" value="삭제" onclick="deleteFreeboard();" class="btn btn-danger"/>
	<%} %>
	</div>
	</section>
	<br />
	<br />

	<!-- 댓글 테이블@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->


	<section id="comment-section">
	<hr style="margin-top: 30px;"/>
		<table id="tbl-comment">
			<%
			if(bclist != null){
				for(BoardComment bc : bclist){
					if(bc.getBoard_Comment_Level()==1){
			%>
			<!-- 댓글 레빌 1@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
					<tr class=level1>
						<td>
							<sub class=comment-writer><%=bc.getBoard_Comment_Writer() %></sub>
							<sub class=comment-date><%=bc.getBoard_Comment_Date()%></sub>
							<br />
							
							<%=bc.getBoard_Comment_Content() %>
						</td>
						<td>
							<button class="btn-reply btn btn-success" 
									value="<%=bc.getBoard_Comment_No()%>">답글</button>
							<%--@실습문제:
								 관리자/댓글작성자에 한해 이버튼을 노출시키고,
								 댓글 삭제 기능추가. 
								 댓글삭제후에는 현재페이지로 다시 이동함.
							  --%>

										
						
						</td>
					</tr>
			<% 		} else { %>
			<!-- 댓글 레빌 2@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
<%-- 					<tr class=level2>
						<td>
							<sub class=comment-writer><%=bc.getBoard_Comment_Writer() %></sub>
							<sub class=comment-date><%=bc.getBoard_Comment_Date()%></sub>
							<br />
							
							<%=bc.getBoard_Comment_Content() %>
						</td>
						<td>
							삭제버튼 추가
							<%if(memberLoggedIn!=null 
								&& ("admin".equals(memberLoggedIn.getMemberId()) 
										|| bc.getBoardCommentWriter().equals(memberLoggedIn.getMemberId()) )){%>
							<button class="btn-delete" value="1<%=bc.getBoardCommentNo()%>">삭제</button>
							<%} %>
						</td>
					</tr> --%>
		
			<%
					}//end of if : level1, level2
		
				}//end of for	
			} 
			%>
		</table>
	 </section>
			<br />
			<br />
			<br />

			<h2>댓글 입력창</h2>

	
		<div class="comment-editor">
			<form action="<%=request.getContextPath()%>/board/scenarioBoardCommentInsert"
				  name="boardCommentFrm"
				  method="post" id="boardCommentFrm">
				<input type="hidden" name="boardRef" 
					   value="<%=s.getScenario_No()%>" />
				<input type="hidden" name="boardCommentWriter" 
					   value="<%=memberLoggedIn.getMemberId() %>" />
				<input type="hidden" name="boardCommentLevel" 
					   value="1" />
				<input type="hidden" name="boardCommentRef" 
					   value="0" /> <!-- 댓글인 경우 참조댓글이 없으므로 0으로 초기화 -->
				<textarea name="boardCommentContent" 
						  id="boardCommentContent" 
						  cols="60" rows="3"></textarea>
						  <br />
				<button type="submit"
					    id="btn-insert">등록하기</button>			
			</form>
		</div>
	

<script>
(()=>{
	alert("asdasd");
})
$("#goBoardListBtn").click(function() {
	location.href 
	= "<%=request.getContextPath()%>/board/ScenarioBoard";
});


function updateFreeBoard() {
	location.href = "<%=request.getContextPath()%>/board/modifyScenarioBoard?boardNo=<%=s.getScenario_No()%>";
}

function deleteFreeboard() {
	if(confirm("<%=s.getTitle()%> 게시글을 삭제하시겠습니까?"))
	 {
	  
	  location.href = "<%=request.getContextPath()%>/board/deleteScenario?boardNo=<%=s.getScenario_No()%>";
	 }
	 else
	 {
	 alert('삭제를 취소하셨습니다');
	 }
	

}


</script>	
</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
