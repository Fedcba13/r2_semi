<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<style>
#movieSearch img {
	width: 185px;
	height: 276px;
}

#movieSearch table {
	margin: 0 auto;
}

#movieSearch td {
	width: 185px;
	padding: 10px 10px 10px 0;
}

#movieSearch td > a{
	display: block;
	position: relative;
	overflow: hidden;
}

#movieSearch td > a:visited, #movieSearch td > a:hover, #movieSearch td > a:active{
	color: white;
}

#movieSearch td > a > div {
	position: absolute;
	background-color: rgba(0,0,0,0.5);
	top:276px;
	transition: all .3s linear;
	height: 100%;
	width: 100%;
	color: white;
	padding: 20px;
}

#movieSearch td:hover > a > div{
	top:0px;
	z-index: 1;
	background-color: rgba(0,0,0,0.7);
}

#movieSearch>#pageBar {
	text-align: center;
}

#pageBar > nav{
    display: inline-block;
}

.pagination>li{
	width: 60px;
}

</style>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script>

var curPage = 1;
var numPerPage = 10;

var param = {};

$(()=>{
	
	param = {
		keyword: '${param.keyword}',
		genre: '${param.genre}',
		yearFrom: '${param.yearFrom}',
		yearTo: '${param.yearTo}',
		cPage: 1
	};
	
	getMovie();
	
});

function getMovie(){
	
	$.ajax({
		url: "${pageContext.request.contextPath}/movie/movieSearch.do",
		type: "post",
		async: false,
		data: param,
		dataType: "json",
		success: function(data){
			
			var movieList = data["list"];
			var pageBar = data["pageBar"];

			$("#movieSearch td").html("");
			
			if(movieList.length == 0){
				 $("#movieSearch>table").empty();
				 $("#movieSearch>table").html("<tr><td style='width:780px; height:590px; text-align: center;'>검색된 결과가 없습니다.</td></tr>");
			}
			
			$.each(movieList, (i, it)=>{
				var html = '';
					
				if(it["poster_path"] == 'undefined' || typeof it["poster_path"] == 'undefined'){
					html += "<a href='${pageContext.request.contextPath}/movie/gotoDetail?movieId="+it["id"]+"'><img src='${pageContext.request.contextPath}/images/noimage.gif'>";
				}else{
					html += "<a href='${pageContext.request.contextPath}/movie/gotoDetail?movieId="+it["id"]+"'><img src='https://image.tmdb.org/t/p/w185/"+it["poster_path"]+"'>";
				}
					html += '<div>';
					html += '<h5>'+it["title"]+'<br><br></h5>';
					html += it["genre"] + "<br>";
					html += it["release_date"];
					html += '</div></a>';
					
					$("#movieSearch tr:eq("+Math.floor(i/4) +") > td:eq("+(i%4)+")").html(html);
				if(it["poster_path"] == 'undefined' || typeof it["poster_path"] == 'undefined'){
					$("#movieSearch tr:eq("+Math.floor(i/4) +") > td:eq("+(i%4)+") > a > div").css("top", "0px");
				}
					
			});
			$("div#movieSearch>#pageBar .pagination").html(pageBar);
		},
		error: function(jqxhr, textStatus, errorThrown){
			console.log("ajax 처리 실패!");
			console.log(jqxhr, textStatus, errorThrown);
		}
	});
	
	$("a.page-link").click(e=>{
		var clickPage = e.target.getAttribute('val');
		if(param.cPage != clickPage){
			param.cPage = clickPage;
			getMovie();
		}
	});
};


</script>
<section>
	<div id="movieSearch">
		<table>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<div id="pageBar">
			<nav aria-label="Page navigation example">
			<ul class="pagination"></ul>
			</nav>
		</div>
	</div>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
