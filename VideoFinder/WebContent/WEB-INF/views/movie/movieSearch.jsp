<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	String keyword = request.getParameter("keyword");
	String genre = request.getParameter("genre");
	String yearFrom = request.getParameter("yearFrom");
	String yearTo = request.getParameter("yearTo");
%>

<style>
#movieRank img {
	width: 185px;
	height: 265px;
}

#movieRank table {
	margin: 0 auto;
}

#movieRank td {
	width: 60px;
	height: 60px;
	color: white;
	position: relative;
	padding: 20px;
}

#movieRank td>p {
	transform: translateX(-50%);
	width: 60px;
	height: 60px;
	color: white;
	position: absolute;
	z-index: 9;
	background-size: cover;
	top: 0px;
	line-height: 60px;
	text-align: center;
	font-weight: bold;
	font-size: 22px;
	background-image: url('<%=request.getContextPath()%>/img/star.png');
}
</style>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script>

var curPage = 1;
var numPerPage = 10;

$(()=>{
	
	var param = {
		keyword: '<%=keyword%>',
		genre: '<%=genre%>',
		yearFrom: '<%=yearFrom%>',
		yearTo: '<%=yearTo%>'
	};
	
	$.ajax({
		url: "<%=request.getContextPath()%>/movie/movieSearch.do",
		type: "post",
		async: false,
		data: param,
		dataType: "json",
		success: function(data){
			console.log(data);
		},
		error: function(jqxhr, textStatus, errorThrown){
			console.log("ajax 처리 실패!");
			console.log(jqxhr, textStatus, errorThrown);
		}
	});	
});


</script>
<section>
	<div id="movieRank">
		<table></table>
	</div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
