<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<!DOCTYPE html>
<html>
<script
	src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/jquery-3.4.1.js"></script>
<!-- jquery  -->
<script
	src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/bootstrap.js"></script>
<!-- 부트스트랩 기본 -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/freeboard_bootstrap_css/bootstrap.css">
<!-- 부트스트랩 기본 -->
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/freeboard.css" />
 --%>

<style>
#main-text {
	padding-top: 30px;
	padding-bottom: 30px;
}

#pageBar {
	text-align: center;
}

.cPage {
	font-size: 30px;
}

#srchForm {
	text-align: center;
}

#btn-write {
	float: right;
}

select#srchType, select#srchType option {
	color: black;
	height: 38px;
}

#srchField {
	display: inline-block;
	size: 30px;
	width: 30%;
}

#srchBtn {
	margin-bottom: 5px;
}

#main {
	width: 700px;
	height: 100%;
	margin: auto;
}

#main-text {
	text-align: center;
}
</style>

<head>
<meta charset="UTF-8">
<title>시나리오 게시판</title>
</head>
<body>
	<h2 id="main-text">시나리오 게시판입니다</h2>

	<div id="main">
		<table id="tbl-board" class="table table-hover">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>

			<c:forEach items="${list}" var="sc">
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
		<hr />
		<c:if test="not empty memberLoggedIn">
			<input type="button" value="글쓰기" id="btn-write"
				class="btn btn-success" />
		</c:if>
		<div id='pageBar'>${pageBar }</div>
		<!-- 검색창 -->
		<form
			action="${pageContext.request.contextPath}/board/scenarioBoardSrch"
			method="get" name="srchForm" id="srchForm">
			<select name="srchType" id="srchType" class="selectpicker">
				<option value=""">--선택--</option>
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="uniSrch" selected="selected">통합검색</option>
			</select> <input type="text" placeholder="검색어를 입력" name="keyword"
				id="srchField" class="form-control" /> <input type="submit"
				value="검색" class="btn btn-primary" id="srchBtn" />
		</form>

		<br />





	</div>


	<script>
		$("#btn-write")
				.click(
						function() {
							location.href = "${pageContext.request.contextPath}/board/SecenarioWrite";
						});
	</script>
</body>
</html>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
