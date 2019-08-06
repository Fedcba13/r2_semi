<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
.movie{
    display: inline-block;
}

.movie img{
    width:140px;
    height: 200px;
}
</style>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script>

function findImg(title, year){
	
	var param = {
		title : title,
		year : year
	}
	
	var result = '';
	
	$.ajax({
		url: "<%=request.getContextPath()%>/movie/movieImage.do",
		type: "post",
		async: false,
		data: param,
		dataType: "json",
		success: function(data){
			var temp = data["items"][0]["image"];
			result = temp;
		},
		error: function(jqxhr, textStatus, errorThrown){
			console.log("ajax 처리 실패!");
			console.log(jqxhr, textStatus, errorThrown);
			result = '';
		}
	});	
	return result;
}


$(()=>{
	var xhr = new XMLHttpRequest();
	var url = 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json'; /*URL*/
	var queryParams = '?key=38c4a960deaa96ef6be3f012be0f001d'; /*Service Key*/
	queryParams += '&targetDt=20190805'; /*상영년도*/
	xhr.open('GET', url + queryParams); xhr.onreadystatechange = function () {
		if (this.readyState == 4) {
			var temp = JSON.parse(this.responseText);
			var movieList = temp["boxOfficeResult"]["dailyBoxOfficeList"]
			
			$.each(movieList, (i, it)=>{
				
				var year = it["openDt"].substring(0,4);
				
				//사진찾기 url
				//var result = findImg(it["movieNm"], year);
				var result = '';
				console.log(result);

				var div = $("<div class='movie'></div>");
				var html = '';
				
				html += "<img src='"+result+"'>"
				html += "<p>(" + it["rank"] +")" + it["movieNm"] + "</p>";
				html += "<p>" + it["openDt"] + "</p>";
				
				div.append(html);
				
				$("#movieRank").append(div);
			});
			
		}
	}; 
	xhr.send('');
});

</script>
<section>
	<div id="movieRank"></div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>