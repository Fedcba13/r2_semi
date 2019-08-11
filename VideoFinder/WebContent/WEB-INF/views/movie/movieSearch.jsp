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

#movieRank > #pageBar{
	text-align: center;
}
</style>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script>

var curPage = 1;
var numPerPage = 10;

var param = {};

$(()=>{
	
	param = {
		keyword: '<%=keyword%>',
		genre: '<%=genre%>',
		yearFrom: '<%=yearFrom%>',
		yearTo: '<%=yearTo%>',
		cPage: 1
	};
	
	getMovie();
	
});

function getMovie(){
	
	$.ajax({
		url: "<%=request.getContextPath()%>/movie/movieSearch.do",
		type: "post",
		async: false,
		data: param,
		dataType: "json",
		success: function(data){
			var movieList = data["list"];
			var pageBar = data["pageBar"];

			var html = '';
			$.each(movieList, (i, it)=>{
					
					if(i == 0 || i == 2){
						html += '<tr>';
					}
					
					html += '<td>';
					html += "<a href='<%=request.getContextPath()%>/movie/gotoDetail?movieId="+it["id"]+"'><img src='https://image.tmdb.org/t/p/w185/"+it["poster_path"]+"'></a>"
					html += '</td>';
					
					if(i == 1 || i == 3){
						html += '</tr>';
					}
				
			});
			$("#movieRank table").html(html);
			$("div#movieRank>#pageBar").html(pageBar);
		},
		error: function(jqxhr, textStatus, errorThrown){
			console.log("ajax 처리 실패!");
			console.log(jqxhr, textStatus, errorThrown);
		}
	});
	
	$("a.page").click(e=>{
		param.cPage = e.target.getAttribute('val');
		getMovie();
	});
	
};


</script>
<section>
	<div id="movieRank">
		<table></table>
		<div id="pageBar"></div>
	</div> 
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
