<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<script src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/bootstrap.js"></script> <!-- 부트스트랩 기본 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/freeboard_bootstrap_css/bootstrap.css"> <!-- 부트스트랩 기본 -->
<script src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/jquery-3.4.1.js"></script> <!-- jquery  -->

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

h2{
	padding-top: 50px;
	padding-bottom: 50px;
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

table#board-table th{
	color:black;
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
	width:600px; 
	margin:0 auto; 
	border-collapse:collapse; 
	clear:both; 
	border-bottom: 1px solid;
}


#boardCommentFrm{
	text-align: center;
}

#btn-insert{
	margin-left: 22.3em;
	color: black;
}
</style>

<head>
<meta charset="UTF-8">
<title>${s.title }</title>
</head>
<body style="margin-left: 4em;">



	<section id="contentPage">
	<h2>게시글 상세보기</h2>
	<br />
	<table class="table table-hover border border-primary" id="board-table" >
		<tr>

		
		
		<tr>
		<th>제목</th>
		<td>${s.title }</td>		
		</tr>	
			
	
		<tr>
		<th>작성자</th>
		<td><a href="">${s.member_Id}</a></td>		
		</tr>	
		
			
		<tr>
		<th>조회수</th>
		<td>${s.readCount}</td>		
		</tr>	
		
		<tr>
		<th >내용</th>
		<td id="content-td"><p>${s.scenario_Content}</p></td>		
		</tr>	
		
		
		<tr>
		<th>날짜</th>
		<td>${s.scenario_Date}</td>		
		</tr>
		
	</table>
	<div id="buttons">
	<input type="button" value="목록으로 돌아가기" id="goBoardListBtn" class="btn btn-primary"/>
		<c:if test="not empty memberLoggedIn && (s.member_Id == memberLoggedIn.memberId || 'admin' == memberLoggedIn.memberId)">
			<input type="button" value="수정" onclick="updateFreeBoard();" class="btn btn-warning"/>
			<input type="button" value="삭제" onclick="deleteFreeboard();" class="btn btn-danger"/>
		</c:if>
	</div>
	</section>

	<!-- 댓글 테이블@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->


	<section id="comment-section">
	<hr style="margin-top: 30px;"/>
		<table id="tbl-comment">



			<c:if test="not empty bclist">
				<c:forEach var="bc" items="bclist">
					<c:if test="bc.board_Comment_Level == 1">
						<tr class=level1>
							<td><sub class=comment-writer>${bc.board_Comment_Writer}</sub>
								<sub class=comment-date>${bc.board_Comment_Date}</sub> <br />
								${bc.board_Comment_Content}</td>
							<td>
								<button class="btn-reply btn btn-success"
									value="${bc.board_Comment_No}">답글</button>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
		</table>
	 </section>

			<h2>댓글 입력창</h2>

	
		<div class="comment-editor">
			<form action="${pageContext.request.contextPath}/board/scenarioBoardCommentInsert"
				  name="boardCommentFrm"
				  method="post" id="boardCommentFrm">
				<input type="hidden" name="boardRef" 
					   value="${s.scenario_No}" />
				<input type="hidden" name="boardCommentWriter"
                       value="${memberLoggedIn.memberId}" />
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
	= "${pageContext.request.contextPath}/board/ScenarioBoard";
});


function updateFreeBoard() {
	location.href = "${pageContext.request.contextPath}/board/modifyScenarioBoard?boardNo=${s.scenario_No}";
}

function deleteFreeboard() {
	if(confirm("${s.title} 게시글을 삭제하시겠습니까?"))
	 {
	  
	  location.href = "${pageContext.request.contextPath}/board/deleteScenario?boardNo=${s.scenario_No}";
	 }
	 else
	 {
	 alert('삭제를 취소하셨습니다');
	 }
	

}


</script>	
</body>
</html>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />