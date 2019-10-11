<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	height: 100%;
	margin: auto;
}

#submitBtn{
	
    padding: 0.375rem 9rem;
    margin-left: 4em;
    margin-right: 0.5em;
    margin-top: 1em;
    
    text-align: center;
}

#ModifyBtn{
	
	padding: 0.375rem 9em;
    margin-left: 8.3em;
    margin-right: 0.5em;
    margin-top: 1em;
    text-align: center;
}

#content{

width: 317px;
}

#goBoardListBtn{
    padding: 0.375rem 5.9em;
    margin-left: 8.3em;
    margin-right: 0.5em;
    margin-top: 1em;
    text-align: center;
}

input[name=title], textarea#content{
	color: black;
}


</style>


<head>
<meta charset="UTF-8">
<title>[${fb.free_Board_Title}]수정하기</title>
</head>
<body>
	<div id="main">

	<h2 id="main-text">[${fb.free_Board_Title}]수정하기</h2>
		<form action="${pageContext.request.contextPath}/board/freeBaordUpdateEnd" method="post">
		<table class="table table-hover">
		<tr>
		<th>번호</th>
			<c:set var="idx" value="${fn:indexOf(fb.free_Board_No, '_') }" />
			<td>${fn:substring(fb.free_Board_No, index+1, fn:length(fb.free_Board_No)) }</td>
		</tr>	
		
		
		<tr>
		<th>제목</th>
		<td><input type="text" name="title" value="${fb.free_Board_Title}" /></td>		
		</tr>	
			
	
		<tr>
		<th>작성자</th>
		<td>${fb.free_Board_Writer}</td>
		</tr>	
			
		
			
	
		
		<tr>
		<th>내용</th>
		<td><textarea name="content" id="content" cols="30" rows="10" >${fb.free_Board_Content}</textarea> </td>		
		</tr>	
		

	</table>
		<input type="submit" value="수정" onclick="contentValidate();" id="ModifyBtn" class="btn btn-warning"/>
		<input type="button" value="목록으로 돌아가기" id="goBoardListBtn" class="btn btn-info"/>
		
		
		<input type="hidden" name="boardNo" value="${fb.free_Board_No}" />
		<input type="hidden" name="writer" value="${fb.free_Board_Writer}" />
		<input type="hidden" name="readCount" value="${fb.free_Board_ReadCount}" />
		<input type="hidden" name="date" value="${fb.free_Board_Date}" />
	</form>
	</div>
	
	
</body>

<script>
$("#goBoardListBtn").click(function() {
	location.href 
	= "${pageContext.request.contextPath}/board/freeBoard";
});


function contentValidate(){
	var content = $("[name=boardContent]").val();
	if(content.trim().length == 0){
		alert("내용을 입력하세요.");
		return false;
	}
	
	return true;
}
</script>

</html>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
