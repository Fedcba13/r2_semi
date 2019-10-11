<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/header.jsp" />
<style>
body {
	color: yellow;
}

#main-content {
	text-align: center;
}

#title {
	margin-top: 30px;
}

#ir1 {
	width: 700px;
	height: 500px;
	padding-left: 20em;
}

img {
	max-width: 200px;
	max-height: 300px;
}

form#frm {
	text-align: center;
	display: inline-block;
}

form#frm>table {
	width: 655px;
}

form#frm table td input {
	color: black;
}
</style>
<script
	src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/bootstrap.js"></script>
<!-- 부트스트랩 기본 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/freeboard_bootstrap_css/bootstrap.css">
<!-- 부트스트랩 기본 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/SE2/js/HuskyEZCreator.js"
	charset="utf-8"></script>
<script
	src="${pageContext.request.contextPath}/js/freeboard_bootstrap_js/jquery-3.4.1.js"></script>
<!-- jquery  -->
<script type="text/javascript">
	var oEditors = [];
	$(function() {
		nhn.husky.EZCreator
				.createInIFrame({
					oAppRef : oEditors,
					elPlaceHolder : "ir1",
					//SmartEditor2Skin.html 파일이 존재하는 경로
					sSkinURI : "${pageContext.request.contextPath}/SE2/SmartEditor2Skin.html",
					htParams : {
						// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseToolbar : true,
						// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseVerticalResizer : true,
						// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
						bUseModeChanger : true,
						fOnBeforeUnload : function() {

						}
					},
					fOnAppLoad : function() {
						//기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
						oEditors.getById["ir1"].exec("PASTE_HTML", [ "" ]);
					},
					fCreator : "createSEditor2"
				});

		//저장버튼 클릭시 form 전송
		$("#save").click(function() {
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			$("#frm").submit();
		});
	});
</script>

</head>
<body id="main-content">
	<form id="frm"
		action="${pageContext.request.contextPath}/board/scenarioWriteEnd"
		method="get">
		<table width="100%">
			<tr>

				<td><input type="text" id="title" name="title"
					style="width: 650px" / placeholder="게시물 제목을 입력하세요"
					class="form-control form-control-lg"></td>
			</tr>
			<tr>

				<td><textarea rows="10" cols="30" id="ir1" name="content"
						style="width: 650px; height: 800px;">
<p>
							<span style="font-size: 24pt;"></span>
						</p><p>
							<br>
						</p><p>
							<span style="font-size: 24pt;">포스터</span>
						</p><p>
							<span style="font-size: 24pt;">&lt;이곳에 사진 첨부&gt;</span>
						</p><p>
							<span style="font-size: 24pt;"><br></span>
						</p><p>
							<span style="font-size: 24pt;"><br></span>
						</p><p>
							<span style="font-size: 24pt;">영화 제목</span>
						</p><blockquote class="se2_quote7">
							<p>&nbsp;</p>
						</blockquote><span style="font-size: 24pt;">장르</span>
						<blockquote class="se2_quote7">
							<p>&nbsp;</p>
						</blockquote><span style="font-size: 24pt;">출연진</span>
						<blockquote class="se2_quote7">
							<p>&nbsp;</p>
							<p>
								<br>
							</p>
						</blockquote><span style="font-size: 24pt;">줄거리</span>
						<blockquote class="se2_quote7">
							<p>&nbsp;</p>
							<p>
								<br>
							</p>
							<p>
								<br>
							</p>
							<p>
								<br>
							</p>
							<p>
								<br>
							</p>
						</blockquote><p>&nbsp;</p>
                                                                </textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input hidden="hidden" value="abc"
					name="writer"> <input type="submit" id="save" value="작성하기" />
					<input type="button" value="취소" id="cancel" /></td>
			</tr>
		</table>
	</form>

</body>
<script>
	$("#cancel")
			.click(
					function() {

						location.href = "${pageContext.request.contextPath}/board/ScenarioBoard";
					});
</script>


</html>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
