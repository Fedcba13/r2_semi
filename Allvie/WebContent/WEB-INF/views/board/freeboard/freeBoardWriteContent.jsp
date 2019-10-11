<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/jquery-3.4.1.js"></script> <!-- jquery  -->
<script src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/bootstrap.js"></script> <!-- 부트스트랩 기본 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/freeboard_bootstrap_css/bootstrap.css"> <!-- 부트스트랩 기본 -->

<style>
#main-text{
	padding-top: 50px;
	padding-bottom: 50px;
	text-align: center;
}

#main{
	width: 700px;
	margin: 0 auto;
	text-align: center;
}

#main > form{
	display: inline-block;
}

#submitBtn{
	
    padding: 0.375rem 9rem;
    margin-left: 4em;
    margin-right: 0.5em;
    margin-top: 1em;
    
    text-align: center;
}
th{
	text-align: center;
}

</style>


<div id="main">
	<h2 id="main-text">글쓰기</h2>
	<form action="${pageContext.request.contextPath}/board/freeBoardWriteEnd"
	method="get" name="writeForm"
	>
		<table id="tbl-free-board-write">
		
		
	
		<tr>
		<th>작성자</th>
		<td><input  type="text" name="writer" id="writer" value="${memberLoggedIn.memberId}" required="required" readonly="readonly" class="form-control form-control-lg"/></td>		
		</tr>	
		
		<tr>
		<th>제목</th>
		<td><input type="text" name="title" id="title" required="required" class="form-control form-control-lg"/></td>		
		</tr>	
		
		<tr>
		<th>내용</th>
		<td><textarea name="freeboardcontent" id="freeboardcontent" cols="40" rows="5" required="required" class="form-control"></textarea></td>		
		</tr>	
		<tr>
		<th colspan="2">
			<input type="submit" value="등록하기" onclick="boardValidate();" class="btn btn-success" id="submitBtn"/>
		</th>
		</tr>

		
		</table>
	</form>

</div>


<script>
function boardValidate(){

	var title = $("#title").val();
	if(title.trim().length == 0){
		alert("제목을 입력하세요.");
		return false;
	}
	var content = $("#freeboardcontent").val();
	if(content.trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}
	return true;
}
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />