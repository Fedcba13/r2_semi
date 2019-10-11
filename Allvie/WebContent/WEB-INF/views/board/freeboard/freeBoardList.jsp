<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<script
	src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/bootstrap.js"></script>
<!-- 부트스트랩 기본 -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/freeboard_bootstrap_css/bootstrap.css">

<style>
#main-text {
	padding-top: 50px;
	margin-bottom: 50px;
	text-align: center;
}

#pageBar {
	text-align: center;
}

.cPage {
	font-size: 30px;
}

#srchForm {
	text-align: center;
	margin-bottom: 5px;
}

#srchField {
	display: inline-block;
	size: 30px;
	width: 360px;
}

#srchBtn {
	margin-bottom: 5px;
}

#main {
	width: 700px;
	height: 100%;
	margin: auto;
}

select#srchType, select#srchType option {
	color: black;
	height: 38px;
}

#btn-write {
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

			<c:forEach var="f" items="${flist }">
				<c:set var="idx" value="fn:indexOf(f.free_Board_No, '_')"></c:set>
				<tr>

					<td>${fn:substring(f.free_Board_No, idx+1, fn:length(f.free_Board_No))}</td>
					<td><a
						href="${pageContext.request.contextPath}/board/freeBoardView?boardNo=${f.free_Board_No}">${f.free_Board_Title}</a></td>
					<td>${f.free_Board_Writer}</td>
					<td>${f.free_Board_Date}</td>
					<td>${f.free_Board_ReadCount}</td>
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
		<form action="${pageContext.request.contextPath}/board/boardSrch"
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

							location.href = "${pageContext.request.contextPath}/board/freeBoardWrite";
						});
	</script>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />