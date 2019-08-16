<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String actorId = (String)request.getAttribute("actorId");
%>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.actorImg{
		margin-left: 15px;
		margin-top: 10px;
		font-size: 25px;
		font-weight: bold;
		font-style: italic;
		color: gray;		
	}
	.movieList{
		margin: 10px 0;
		padding: 10px;
		font-size: 25px;
		font-weight: bold;
		font-style: italic;
		color: gray;
	}
	.movieList:hover{
		cursor: pointer;
	}
</style>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script>
	var param =	{
		actorId: <%=actorId%>
	}
	$(()=>{
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/actorOverview.do",
			data: param,
			success: function(data){
				console.log(data);
				var html = "";
				html += "<span class='actorImg'><img src='https://image.tmdb.org/t/p/w92//" + data.profile_path + "' class='img-thumbnail'/>"+data.name+"(이)가 출연한 최신영화!</span>"
				
				$("#actor-overview").html(html);
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax처리실패!!");
				console.log(jqxhr, textStatus, errorThrown);
			}
		})
		
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/listByActor.do",
			data: param,
			success: function(data){
				console.log(data);
				var html = "";
				for(var i = 0; i < 5; i++){
					html += "<span class='movieList' onclick='gotoDetail("+data.results[i].id+");'><img src='https://image.tmdb.org/t/p/w185//" + data.results[i].poster_path + "'/>"+data.results[i].title+"</span><br><br>";
				}
				$("#movie-list").html(html);
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax처리실패!!");
				console.log(jqxhr, textStatus, errorThrown);
			}			
		})
	});
	function gotoDetail(movieId){		
		location.href="<%=request.getContextPath()%>/movie/gotoDetail?movieId=" + movieId;
	}
</script>
</head>
<body>
	<div id="actor-overview">
		
	</div>
	
	<hr />
	
	<div id="movie-list">
	
	</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>