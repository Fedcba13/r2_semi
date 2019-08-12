<%@page import="com.r2.board.model.vo.FreeBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	FreeBoard fb = (FreeBoard)request.getAttribute("fb");

%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>[<%=fb.getFree_Board_Title() %>]수정하기</title>
</head>
<body>
	<h2>[<%=fb.getFree_Board_Title() %>]수정하기</h2>
		<form action="<%=request.getContextPath()%>/board/freeBaordUpdateEnd" method="post">
		<table >
		<tr>
		<th>번호</th>
			<% int idx =fb.getFree_Board_No().indexOf("_");%>
			<td><%=fb.getFree_Board_No().substring(idx+1)%></td>	
		</tr>	
		
		
		<tr>
		<th>제목</th>
		<td><input type="text" name="title" value="<%=fb.getFree_Board_Title() %>" /></td>		
		</tr>	
			
	
		<tr>
		<th>작성자</th>
		<td><%=fb.getFree_Board_Writer() %></td>
		</tr>	
			
		
			
		<tr>
		<th>조회수</th>
		<td><%=fb.getFree_Board_ReadCount() %></td>		
		</tr>	
		
		<tr>
		<th>내용</th>
		<td><textarea name="content" id="content" cols="30" rows="10" ><%=fb.getFree_Board_Content() %></textarea> </td>		
		</tr>	
		
		
		<tr>
		<th>날짜</th>
		<td><%=fb.getFree_Board_Date() %></td>		
		</tr>
	</table>
		<input type="submit" value="수정" onclick="contentValidate();"/>
		
		
		<input type="hidden" name="boardNo" value="<%=fb.getFree_Board_No() %>" />
		<input type="hidden" name="writer" value="<%=fb.getFree_Board_Writer() %>" />
		<input type="hidden" name="readCount" value="<%=fb.getFree_Board_ReadCount() %>" />
		<input type="hidden" name="date" value="<%=fb.getFree_Board_Date() %>" />
	</form>
	<input type="button" value="목록으로 돌아가기" id="goBoardListBtn"/>
	
	
	
</body>

<script>
$("#goBoardListBtn").click(function() {
	location.href 
	= "<%=request.getContextPath()%>/board/freeBoard";
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