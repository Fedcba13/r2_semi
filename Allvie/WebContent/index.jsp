<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
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

#movieRank tr:nth-child(1)>td {
	padding-top: 40px;
}

#movieRank tr:nth-child(2)>td {
	padding-bottom: 40px;
}

#movieRank tr:nth-child(1)>td>p {
	margin-top: 40px;
}

#movieRank td>p {
	width: 40px;
	height: 40px;
	background-color: #ac162c;
	color: white;
	position: absolute;
	z-index: 9;
	background-size: cover;
	top: 0px;
	left: 0px;
	line-height: 40px;
	text-align: center;
	font-weight: bold;
	font-size: 22px;
	margin: 20px;
	opacity: 0.7;
}
</style>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script>

var curPage = 1;
var numPerPage = 10;

//사진 찾기 (영화 진흥 위원회에서 가져온 무비오피스의 이름으로 tmdb에서 가져옵니다.)
function findImg(title, year){
	
	var param = {
		title : title,
		year : year
	}
	
	var result = new Array();
	
	$.ajax({
		url: "${pageContext.request.contextPath}/movie/movieImage.do",
		type: "post",
		async: false,
		data: param,
		dataType: "json",
		success: function(data){
			var movieImgUrl = data["results"][0]["poster_path"];
			result[0] = movieImgUrl;
			var movieId = data["results"][0]["id"];
			result[1] = movieId;
		},
		error: function(jqxhr, textStatus, errorThrown){
			console.log("ajax 처리 실패!");
			console.log(jqxhr, textStatus, errorThrown);
			result[0] = '';
			result[1] = "#";
			location.reload();
		}
	});	
	
	return result;
}

$(()=>{
	var curDate = new Date().getTime() - (1*24*60*60*1000);
	var yesterday = new Date(curDate);
	var year = yesterday.getFullYear();
	var month = yesterday.getMonth()+1;
	var day = yesterday.getDate();
	if(month < 10){ month = "0" + month; }
	if(day < 10) { day = "0" + day; }
	
	var yester = year+month+day;
	
	var xhr = new XMLHttpRequest();
	var url = 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json'; /*URL*/
	var queryParams = '?key=38c4a960deaa96ef6be3f012be0f001d'; /*Service Key*/
	queryParams += '&targetDt='+yester; /*상영년도*/
	xhr.open('GET', url + queryParams);
	xhr.onreadystatechange = function () {
		if (this.readyState == 4) {
			var temp = JSON.parse(this.responseText);
			var movieList = temp["boxOfficeResult"]["dailyBoxOfficeList"]

			var html = '';
			$.each(movieList, (i, it)=>{
				
				if(i<8){	
					var year = it["openDt"].substring(0,4);
					
					//사진찾기 url
					var result = findImg(it["movieNm"], year);
					
					if(i == 0 || i == 4){
						html += '<tr>';
					}
					
					html += '<td>';
					if(result[0] != ''){						
						html += "<a href='${pageContext.request.contextPath}/movie/gotoDetail?movieId="+result[1]+"'><img src='https://image.tmdb.org/t/p/w185/"+result[0]+"'></a>"
					}else{
						html += "<a href='"+result[1]+"'><img src='"+result[0]+"'></a>"
					}
					html += "<p class='rankTitle'>" + it["rank"] +"</p>";
					html += '</td>';
					
					if(i == 3 || i == 7){
						html += '</tr>';
					}
				}
				
			});
			$("#movieRank table").append(html);
			
		}
	}; 
	xhr.send('');
});


</script>
<section>
	<div id="movieRank">
		<table></table>
	</div>


</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
