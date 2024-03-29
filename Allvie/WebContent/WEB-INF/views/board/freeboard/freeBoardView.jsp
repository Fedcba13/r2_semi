<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<script src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/bootstrap.js"></script> <!-- 부트스트랩 기본 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/freeboard_bootstrap_css/bootstrap.css"> <!-- 부트스트랩 기본 -->
<script src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/jquery-3.4.1.js"></script> <!-- jquery  -->
<script>
$(()=>{
	//답글(대댓글)작성
	$(".btn-reply").on("click",function(e){
		/*로그인 여부에 따라 분기*/
		
		<c:if test="not empty memberLoggedIn">
		
			//로그인한 경우
			var level = (this.getAttribute('level')*1) + 1;
			var tr = $("<tr></tr>");
			var html = "<td style='display:none; text-align:left;' colspan='2'>";

			html += "<form action='${pageContext.request.contextPath}/board/freeBoardCommentInsert' method='post'>";
			html += "<input type='hidden' name='boardRef' value='${fb.free_Board_No}'>";
			html += "<input type='hidden' name='boardCommentWriter' value='${memberLoggedIn.memberId}'>";
			html += "<input type='hidden' name='boardCommentLevel' value='"+level+"'>";
			html += "<input type='hidden' name='boardCommentRef' value='"+e.target.value+"'>";
			html += "<textarea name='boardCommentContent' cols='60' rows='1' style='height: 38px;'></textarea>";
			html += "<button type='submit' class='btn-reply btn btn-success btn-reply' style='vertical-align: top; margin-left: 10px;'>등록</button>";
			html += "</form>";
			html += "</td>";
			
			tr.html(html);
			
			//클릭한 버튼이 속한 tr다음에 tr을 추가
			tr.insertAfter($(e.target).parent().parent())
				.children("td").slideDown(800)
				.children("form").submit(()=>{
					var len = $(e.target).children("textarea")
										.val().trim().length;
					if(len == 0){
			            e.preventDefault();
			        }
				});
			
			//한번 댓글폼 생성후 이벤트핸들러 제거
			$(e.target).off('click');
			
		</c:if>
		<c:if test="empty memberLoggedIn">
			//로그인하지 않은 경우
			alert('로그인 부터 하세요.');
		</c:if>
	});
	
	function loginCheck(){
		
		<c:if test="empty memberLoggedIn" >
			alert('로그인 부터 하세요.');
			return false;
		</c:if>
		
		return true;
		
	}
	
	$("#goBoardListBtn").click(function() {
		location.href 
		= "${pageContext.request.contextPath}/board/freeBoard";
	});


	function updateFreeBoard() {
		location.href = "${pageContext.request.contextPath}/board/modifyFreeBoard?boardNo=${fb.free_Board_No}";
	}

	function deleteFreeboard() {
		if(confirm("${fb.free_Board_Title} 게시글을 삭제하시겠습니까?"))
		 {
		  
		  location.href = "${pageContext.request.contextPath}/board/deleteFreeBoard?boardNo=${fb.free_Board_No}";
		 }
		 else
		 {
		 alert('삭제를 취소하셨습니다');
		 }
	}
	
	$(".commentDelete").click(function(){
		if(!confirm("정말 삭제하시겠습니까?")){
			alert('삭제를 취소하셨습니다');
		}else{
			location.href="${pageContext.request.contextPath}/board/boardCommentDelete?boardNo=${fb.free_Board_No}&del="+this.getAttribute('no');
		}
	});
	
});

</script>
<style>
td{
	height: 60px;
}

td#content-td{
	height: 180px;
}
section#contentPage{
	padding-top: 30px;
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
	word-break:break-all;
}

th{
	width: 100px;
	height: 50px;
	background-color: lightgray;
	color: black;
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

table#tbl-comment td:nth-child(2n){
	width: 130px;
}

table#tbl-comment td:nth-child(2n+1){
	width: 370px;
}


#boardCommentFrm{
	text-align: center;
}

#btn-insert{
	margin-left: 22.3em;
	color: black;
}

#boardCommentContent{
	color: #333335;
}

td > pre {
    background: #dee2e6;
}

</style>

<head>
<meta charset="UTF-8">
<title>${fb.free_Board_Title}</title>
</head>
<body>



	<section id="contentPage">
	<h2>게시글 상세보기</h2>
	<br />
	<table class="table table-hover border border-primary" id="board-table" >	
		
		
		<tr>
		<th>제목</th>
		<td>${fb.free_Board_Title}</td>		
		</tr>	
			
	
		<tr>
		<th>작성자</th>
		<td>${fb.free_Board_Writer}</td>		
		</tr>	
		
			
		<tr>
		<th>조회수</th>
		<td>${fb.free_Board_ReadCount}</td>		
		</tr>	
		
		<tr>
		<th >내용</th>
		<td id="content-td"><p>${fb.free_Board_Content}</p></td>		
		</tr>	
		
		
		<tr>
		<th>날짜</th>
		<td>${fb.free_Board_Date}</td>		
		</tr>
		
	</table>
	<div id="buttons">
	<input type="button" value="목록으로 돌아가기" id="goBoardListBtn" class="btn btn-primary"/>
	<c:if test="not empty memberLoggedIn && (fb.free_Board_Writer == memberLoggedIn.memberId || 'admin' == memberLoggedIn.memberId)">	
		<input type="button" value="수정" onclick="updateFreeBoard();" class="btn btn-warning"/>
		<input type="button" value="삭제" onclick="deleteFreeboard();" class="btn btn-danger"/>
	</c:if>
	</div>
	</section>
	<br />
	<br />

	<!-- 댓글 테이블@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->


	<section id="comment-section">
		<hr style="margin-top: 30px;" />
		<table id="tbl-comment">
		
		
				
				
				
			
			<c:if test="not empty bclist">
			<c:forEach items="${bclist }" var="bc">
			<!-- 댓글 레벨 1@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ -->
			<tr class='comment'>
				<td><sub class=comment-writer>${bc.board_Comment_Writer}</sub>
					<sub class=comment-date>${bc.board_Comment_Date}</sub> <br>
					<pre>${bc.board_Comment_Content}</pre></td>
				<td>
					<button class="btn-reply btn btn-success btn-reply"
						level='${bc.board_Comment_Level}'
						value="${bc.board_Comment_No}">답글</button>
					<c:if test="bc.board_Comment_Writer == memberLoggedIn.memberId"> 
					 	<input type="button" value="삭제" class="btn btn-danger commentDelete" no="${bc.board_Comment_No}" /> 
					</c:if>
				</td>
			</tr>

	</c:forEach>
		</c:if>
		</table>
	</section>

	<c:if test="not empty memberLoggedIn">
	<h2 style="padding: 30px;">댓글 입력창</h2>
	<div class="comment-editor">
		<form
			action="${pageContext.request.contextPath}/board/freeBoardCommentInsert"
			name="boardCommentFrm" method="post" id="boardCommentFrm"
			onsubmit="return loginCheck();">
			<input type="hidden" name="boardRef"
				value="${fb.free_Board_No}" /> <input type="hidden"
				name="boardCommentWriter" value="${memberLoggedIn.memberId}" />
				<input type="hidden" name="boardCommentLevel" value="1" />
				<input type="hidden" name="boardCommentRef" value="0" />
			<!-- 댓글인 경우 참조댓글이 없으므로 0으로 초기화 -->
			<textarea name="boardCommentContent" id="boardCommentContent"
				cols="60" rows="3"></textarea>
			<br />
			<button type="submit" id="btn-insert">등록하기</button>
		</form>
	</div>
	</c:if>

<script>

$("#goBoardListBtn").click(function() {
	location.href 
	= "${pageContext.request.contextPath}/board/freeBoard";
});


function updateFreeBoard() {
	location.href = "${pageContext.request.contextPath}/board/modifyFreeBoard?boardNo=${fb.free_Board_No}";
}

function deleteFreeboard() {
	if(confirm("${fb.free_Board_Title} 게시글을 삭제하시겠습니까?"))
	 {
	  
	  location.href = "${pageContext.request.contextPath}/board/deleteFreeBoard?boardNo=${fb.free_Board_No}";
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
