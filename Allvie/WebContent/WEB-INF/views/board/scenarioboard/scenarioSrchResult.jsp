<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/bootstrap.js"></script> <!-- 부트스트랩 기본 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/freeboard_bootstrap_css/bootstrap.css"> <!-- 부트스트랩 기본 -->

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
}

select#srchType, input[name=keyword][type=text], div#srchPanel input[type=submit], #goBackToBoard{
	color: black;
}

</style>


<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/jquery-3.4.1.js"></script> <!-- jquery  -->
<title>"${keyword}의 검색결과"</title>
</head>
<body>
	<h2 id="main-text">"${keyword}"의 검색 결과
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


			<c:forEach items="${s}" var="sc">
				<c:set var="index" value="${fn:indexOf(sc.scenario_No, '_') }" />
				<tr>
					<td>${fn:substring(sc.scenario_No, index+1, fn:length(sc.scenario_No))}</td>
					<td><a href="${pageContext.request.contextPath}/board/scenarioView?boardNo=${sc.scenario_No}">${sc.title} </a></td>
					<td>${sc.member_Id}</td>
					<td>${sc.scenario_Date}</td>
					<td>${sc.readCount}</td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<div id='pageBar'>
			${pageBar}
		</div>
		<br />
		<!-- 검색창 -->

		<div id="srchPanel">
		<form action="${pageContext.request.contextPath}/board/scenarioBoardSrch"
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
	= "${pageContext.request.contextPath}/board/ScenarioBoard";	
});


</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
