<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String result = (String)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script>
	$(()=>{
		var result = eval(<%=result%>);
		console.log(result);
		var html ="<ol>"
		for(var i = 0; i < result.results.length; i++){
			html += "<a href='<%=request.getContextPath()%>/movie/gotoDetail?movieId="+result.results[i].id+"'><li><img src='https://image.tmdb.org/t/p/w92//" + result.results[i].poster_path + "'/>"+result.results[i].title+"</li></a>"
		}
		html +="</ol>";
		$("#list").append(html);
	});
</script>
</head>
<body>
	<div id="list">
	</div>

</body>
</html>