<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script>
	$(()=>{
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/getList.do",
			success: function(data){
				console.log(data);
			},
			error:function(jqxhr, textStatus, errorThrown){
				console.log("ajax처리실패!!");
				console.log(jqxhr, textStatus, errorThrown);
			}
		});
		$("#gotoDetail").click((e)=>{
			console.log("goto");
			var movieId = e.target.value;
			console.log(movieId);
			location.href="<%=request.getContextPath()%>/movie/gotoDetail?movieId=" + movieId;
		});	
		$("#searchMovie").click(()=>{
			var movieTitle = $("#title").val();
			movieTitle = movieTitle.replace(/ /g, "&&");
			console.log(movieTitle);
			location.href="<%=request.getContextPath()%>/movie/getMovieId?title=" + movieTitle;
		});
	});
	
	
</script>
<title>index</title>
</head>
<body>
	<h2>Index</h2>
	<button id="gotoDetail" value="384018">goto</button><br /><br />
	<input type="text" id="title" placeholder="영화제목을 검색하세요"/>
	<br /><br />
	<button id="searchMovie">찾기</button>
	

</body>
</html>