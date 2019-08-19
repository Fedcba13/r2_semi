<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String movieId = request.getParameter("movieId");	
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/movieDetail.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/billboard.js/1.10.0/billboard.css"
	integrity="sha256-jlXa7fWz2sBhybXbdmpT/IZ3stQ5Da4nPv7g1hon9DE="
	crossorigin="anonymous" />
<script src="https://d3js.org/d3.v4.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/billboard.js/1.10.0/billboard.js"
	integrity="sha256-yOoWKqYsWnbqCItgLblnkyxF68tOXHTqbMlGhaFWUvA="
	crossorigin="anonymous"></script>
<link data-require="fontawesome@*" data-semver="4.5.0" rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.checked {
  color: orange;
}
</style>
<title></title>
<script>
	var movieId = {
		id: <%=movieId%>
	}
	
	var avg
	
	
	$(()=>{
		var pageTitle;		
		//페이지 로딩시 영화에 대한 전반적인 정보를 받아오는 ajax
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/getDetail.do",
			data: movieId,
			type: "get",
			dataType: "json",
			success:function(data){
				pageTitle = data.title;
				$("title").text(pageTitle);
				//console.log(data);
				//많은 정보를 받아오지만 포스터 이미지, 타이틀, 줄거리, 장르, 개봉일을 받아와 출력한다
				var html = "<table id='details'>";
				if(data.poster_path == null){
					html += "<tr><td rowspan='7' colspan='2'><img src='<%=request.getContextPath()%>/images/noimage.gif' style='width:344px; height:515px;'/></td></tr>"										
				} else {
					html += "<tr><td rowspan='7' colspan='2'><img src='https://image.tmdb.org/t/p/w342//" + data.poster_path + "'/></td></tr>"					
				}
				html += "<tr><td colspan='2' id='title'>타이틀</td></tr>"
				html += "<tr><td colspan='2' id='title-data'>"+data.title+"</td></tr>"
				html += "<tr><td colspan='2' id='overview'>줄거리</td></tr>"
				html += "<tr><td colspan='2' id='overview-data'>"+data.overview+"</td></tr>"
				html += "<tr><td colspan='2' id='genres'>장르</td></tr>"
				html += "<tr><td colspan='2' id='genre-data'>"
					$.each(data.genres, (i)=>{
						html += "<span class='gr' onclick='searchByGenre(this);' title='"+data.genres[i].name+"(으)로 검색'>" +data.genres[i].name +" </span>";
					});
				html += "</tr>"
				//평점은 API에서 제공하는 데이터도 있지만 사용하지 않고 테이블에 저장된 리뷰점수들의 평균을 내서 표시한다
				//평점을 나타내는 그래프는 그래프를 제공하는 API를 사용하였다
				html += "<tr><td id='release_date'>개봉일</td><td id='date-data'>"+data.release_date+"</td><td id='grade'>평점</td><td><div id='gaugeChart'></div></td></tr>"
				html += "</table>";
				$("#info-container").html(html);	
				getAvg();
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax처리실패!!");
				console.log(jqxhr, textStatus, errorThrown);
			}			
		});
		//해당영화의 감독과 출연한 배우의 데이터를 받아와 출력한다.
		//배우데이터는 주역급(상위 5명)만 표시한다
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/getActors.do",
			data: movieId,
			type: "get",
			dataType: "json",
			success: function(data){
				//console.log(data);
				var html = ""
				$.each(data.crew, (i)=>{
					//console.log(data.crew[i].job);
					if("Director" === data.crew[i].job){
						if(data.crew[i].profile_path != null){
							html += "<div class='tn'><span><img src='https://image.tmdb.org/t/p/w92//" + data.crew[i].profile_path + "' class='img-thumbnail'/></span><br>"							
						} else {
							html += "<div class='tn'><span><img src='<%=request.getContextPath()%>/images/noimage.gif' class='img-thumbnail' style='width: 92px; height: 150px;'/></span><br>"
						}
						html += "<span>"+data.crew[i].name +"</span><br>";
						html += "<span>감독</span><br><br></div>";
					}
				})
				var castLength = 0;
				if(data.cast.length <5){
					castLength = data.cast.length;
				} else {
					castLength = 5;
				}
				for(var i = 0; i < castLength; i++){		
					if(data.cast[i].profile_path != null){
						html += "<div class='tn'><span><img src='https://image.tmdb.org/t/p/w92//" + data.cast[i].profile_path + "' class='img-thumbnail' onclick='searchByActor("+data.cast[i].id+")' style='cursor: pointer;'/></span><br>"						
					} else {
						html += "<div class='tn'><span><img src='<%=request.getContextPath()%>/images/noimage.gif' class='img-thumbnail' style='width: 92px; height: 150px;'/></span><br>"
					}
					html += "<span onclick='searchByActor("+data.cast[i].id+")' style='cursor: pointer;'>"+data.cast[i].name +"</span><br>";
					html += "<span>"+data.cast[i].character +"역</span><br><br></div>";
				}
				//html += "</p>";
				$("#actors").append(html);
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax처리실패!!");
				console.log(jqxhr, textStatus, errorThrown);
			}
		});
		//해당 영화의 관련영상을 표시한다.
		//관련영상은 youtube의 링크를 사용한다
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/getVideos.do",
			data: movieId,
			type: "get",
			datatype: "json",
			success: function(data){
				/* console.log(data);
				console.log(data.results); */
				var html = ""
				for(var i = 0; i < data.results.length; i++){
					if(i == 0){
						html += "<li data-target='#carouselExampleCaptions' data-slide-to='" + i + "' class='active'></li>" 						
					} else {
						html += "<li data-target='#carouselExampleCaptions' data-slide-to='" + i + "'></li>" 						
					}
				}
				$(".carousel-indicators").append(html);
				html = "";
				for(var j = 0; j < data.results.length; j++){
					if(j == 0){
						html += "<div class='carousel-item active'><iframe width='600px;' height='500px;' src='https://www.youtube.com/embed/"+data.results[j].key+"' allowfullscreen></iframe><div class='carousel-caption d-none d-md-block'><h5>"+data.results[j].name+"</h5></div></div>";
					} else {
						html += "<div class='carousel-item'><iframe width='600px;' height='500px;' src='https://www.youtube.com/embed/"+data.results[j].key+"'  allowfullscreen></iframe><div class='carousel-caption d-none d-md-block'><h5>"+data.results[j].name+"</h5></div></div>";						
					}
					/* console.log(data.results[j].key);
					console.log(data.results[j].name); */
				}
				/* console.log("j=" + j);
				console.log(html); */
				$(".carousel-inner").append(html);
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax처리실패!!");
				console.log(jqxhr, textStatus, errorThrown);
			}
		});
		//별점관련
		//클릭 시 class를 추가하거나 제거해서 클릭한 지점까지의 별을 채우고 점수를 입력하게 된다.
		function setRating(rating) {
		    $('#rating-input').val(rating);
		    // fill all the stars assigning the '.selected' class
		    $('.rating-star').removeClass('fa-star-o').addClass('selected');
		    // empty all the stars to the right of the mouse
		    $('.rating-star#rating-' + rating + ' ~ .rating-star').removeClass('selected').addClass('fa-star-o');
		  }
		  
		  $('.rating-star')
		  .on('mouseover', function(e) {
		    var rating = $(e.target).data('rating');
		    // fill all the stars
		    $('.rating-star').removeClass('fa-star-o').addClass('fa-star');
		    // empty all the stars to the right of the mouse
		    $('.rating-star#rating-' + rating + ' ~ .rating-star').removeClass('fa-star').addClass('fa-star-o');
		  })
		  .on('mouseleave', function (e) {
		    // empty all the stars except those with class .selected
		    $('.rating-star').removeClass('fa-star').addClass('fa-star-o');
		  })
		  .on('click', function(e) {
		    var rating = $(e.target).data('rating');
		    setRating(rating);
		  })
		  .on('keyup', function(e){
		    // if spacebar is pressed while selecting a star
		    if (e.keyCode === 32) {
		      // set rating (same as clicking on the star)
		      var rating = $(e.target).data('rating');
		      setRating(rating);
		    }
		  });
		  //페이지 로딩시 해당 영화에 대해 작성된 사용자 리뷰를 가져온다
		  getReviews(1);
		  //페이지 로딩시 리뷰 현황을 나타내는 그래프를 가져온다
		  //평점 그래프와 마찬가지로 API를 사용하였다.
		  getReviewGraph();
		  //리뷰작성 버튼 클릭시 실행되는 이벤트 핸들러
		  $("#send-review").on("click", ()=>{
			  var param = {
					  memberId: $("#memberId").val(),
					  rate: $("#rating-input").val(),
					  movieId: <%=movieId%>,
					  comment: $("#review-comment").val()			  
			  }
			  //리뷰 멘트 또는 별점 모두 작성되지 않았다면 제출하지 않고 리턴한다
			  if(param.rate == 0 || param.comment.trim().length == 0){
				  alert("평점과 리뷰를 모두 작성하세요");
				  return ;
			  }
			  //리뷰의 길이가 너무 길어지는것을 방지
			  if(param.comment.trim().length > 140){
				  alert("너무 길어");
				  return;
			  }
			 /*  console.log(data.comment);
			  console.log(data.rate);
			  console.log(data); */
			  //리뷰작성에 문제가 없을 시 ajax를 실행
			  $.ajax({
				 url: "<%=request.getContextPath()%>/movie/insertReview.do",
				 data: param,
				 success: function(data){	
					 //만약 접속한 아이디가 해당영화에 리뷰한 기록이 있다면 리뷰작성을 막는다(1영화당 하나의 리뷰만 작성가능)
					 if(""==data){
						 alert("이미 리뷰한 영화입니다.")
					 } else {
						 //리뷰 작성이성공적으로 됐을 경우 리뷰목록, 그래프, 평점을 새로 갱신한다
						 getReviews(1);
						 getReviewGraph();
						 $("#review-comment").val("");
						 $("#rating-input").val(0);
						 getAvg();						 
					 }
				 },
				 error: function(jqxhr, textStatus, errorThrown){
						console.log("ajax처리실패!!");
						console.log(jqxhr, textStatus, errorThrown);
					}
			  });
		  })
		  //리뷰작성은 로그인 후 이용할 수 있도록 방지
		  $("#review-comment").on("click", ()=>{
			<%if(memberLoggedIn == null){%>
				alert("로그인 후 이용해주세요");
			<%}%>
		  })
		  $("#review-comment").keydown((k)=>{
			  if(k.keyCode == 13){
				  var param = {
						  memberId: $("#memberId").val(),
						  rate: $("#rating-input").val(),
						  movieId: <%=movieId%>,
						  comment: $("#review-comment").val()			  
				  }
				  //리뷰 멘트 또는 별점 모두 작성되지 않았다면 제출하지 않고 리턴한다
				  if(param.rate == 0 || param.comment.trim().length == 0){
					  alert("평점과 리뷰를 모두 작성하세요");
					  return ;
				  }
				  //리뷰의 길이가 너무 길어지는것을 방지
				  if(param.comment.trim().length > 140){
					  alert("너무 길어");
					  return;
				  }
				 /*  console.log(data.comment);
				  console.log(data.rate);
				  console.log(data); */
				  //리뷰작성에 문제가 없을 시 ajax를 실행
				  $.ajax({
					 url: "<%=request.getContextPath()%>/movie/insertReview.do",
					 data: param,
					 success: function(data){	
						 //만약 접속한 아이디가 해당영화에 리뷰한 기록이 있다면 리뷰작성을 막는다(1영화당 하나의 리뷰만 작성가능)
						 if(""==data){
							 alert("이미 리뷰한 영화입니다.")
						 } else {
							 //리뷰 작성이성공적으로 됐을 경우 리뷰목록, 그래프, 평점을 새로 갱신한다
							 getReviews(1);
							 getReviewGraph();
							 $("#review-comment").val("");
							 $("#rating-input").val(0);
							 getAvg();						 
						 }
					 },
					 error: function(jqxhr, textStatus, errorThrown){
							console.log("ajax처리실패!!");
							console.log(jqxhr, textStatus, errorThrown);
						}
				  });
			  }
		  })
	})//onload함수 종료
	//리뷰목록 가져오는 함수
	function getReviews(cPage){
		var numPerPage = 10;
		var pageStart = (cPage-1)*numPerPage;
		var pageEnd = cPage*numPerPage-1;
		var totalPage = 1;
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/getReviews.do",
			data: movieId,
			dataType: "json",
			success: function(data){
				//console.log(data);
				var html = "";
				if(data == null){
					html += "<tr><td colspan='5' id='noReview'>작성된 리뷰가 없습니다</td></tr>"
				} else {
					$.each(data, (i)=>{
						html += "<tr><th scope='row'>"+data[i].memberId+"</th><td colspan='2' width=460 style='word-break:break-all'>"+data[i].reviewComment+"</td>";
						html += "<td colspan='2'>"
						for(var j = 0; j < 10; j++){
							if(j < data[i].reviewGrade){
								html += "<span class='fa fa-star checked'></span>";							
							} else {
								html += "<span class='fa fa-star'></span>";
							}
						}
						html += "</td>";
						html += "<td><img src='<%=request.getContextPath()%>/images/thumbUp.png' title='좋아요' onclick='likeReview(this)' class='like' ><span>" +data[i].reviewLike+ "</span>&nbsp;<img src='<%=request.getContextPath()%>/images/thumbDown.png' title='싫어요' onclick='dislikeReview(this)' class='like' style='width: 30px; height: 30px;'><span>" + data[i].reviewDislike +"</span></td>"
						<%if(memberLoggedIn != null){%>
							if( data[i].memberId == "<%=memberLoggedIn.getMemberId()%>" || "admin" == "<%=memberLoggedIn.getMemberId()%>"){
								html += "<td><button class='btn btn-danger' onclick='deleteReview(this);'>삭제</button></td>";
							} else {
								html += "<td></td>";
							}
						<%} else {%>
							html += "<td></td>";
						<%}%>
						html += "<td class='reviewNo' style='display:none;'>"+data[i].reviewNum+"</td>";
						html += "</tr>";
					});				
				}
				
				
				$("#written").html(html);
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax처리실패!!");
				console.log(jqxhr, textStatus, errorThrown);
			}			
		});		
	}	
	//리뷰현황 그래프
	function getReviewGraph(){
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/reviewGraph.do",
			data: movieId,
			success: function(data){
				//console.log(data);				
				var chart = bb.generate({
					 data: {
					    columns: [
						["1점", data["1"]],
						["2점", data["2"]],
						["3점", data["3"]],
						["4점", data["4"]],
						["5점", data["5"]],
						["6점", data["6"]],
						["7점", data["7"]],
						["8점", data["8"]],
						["9점", data["9"]],
						["10점", data["10"]]
						
					    ],
					    type: "bar"
					  },
					  bar: {
					    width: {
					      ratio: 0.4
					    },
					    padding: 50
					  },			  
					  resize:{
						auto: true
					  },
					  axis: {
					        x: {
					            show: false
					            
					        }        
					    },
					  bindto: "#review-graph"
					});
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax처리실패!!");
				console.log(jqxhr, textStatus, errorThrown);
			}
		});		
	}
	//리뷰 삭제 함수
	//리뷰삭제 버튼은 관리자 또는 접속한 아이디가 작성한 리뷰에만 보이기 때문에 따로 권한설정을 체크하지 않는다
	function deleteReview(e){
		if(confirm("삭제하시겠습니까?")){			
			var tr = $(e).parent().parent();		
			var td = tr.children();		
			var param = {
				rn:	td.eq(5).text()
			}
			$.ajax({
				url: "<%=request.getContextPath()%>/movie/deleteReview.do",
				data: param,
				success: function(){
					//성공시 리뷰목록, 현황, 평점을 갱신
					getReviews(1);
					getReviewGraph();
					getAvg();
				},
				error: function(jqxhr, textStatus, errorThrown){
					console.log("ajax처리실패!!");
					console.log(jqxhr, textStatus, errorThrown);
				}
			});
		} else {
			return ;
		}		
	}
	//리뷰 좋아요
	function likeReview(e){
		<%if(memberLoggedIn == null){%>
				alert("로그인후 이용하세요");
				return ;
		<%}%>
		var tr = $(e).parent().parent();		
		var td = tr.children();		
		var param = {
			<%if(memberLoggedIn!=null){%>
			memberId: "<%=memberLoggedIn.getMemberId()%>",
			<%}%>			
			rn:	td.eq(5).text(),
			movieId: <%=movieId%>,
		}
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/likeReview.do",
			data: param,
			success:function(data){
				if(""==data){
					alert("좋아요/싫어요는 한 리뷰당 한번만 할수 있습니다");
				} else {
					getReviews(1);					
				}
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax처리실패!!");
				console.log(jqxhr, textStatus, errorThrown);
			}
		})
	}
	//리뷰 싫어요
	function dislikeReview(e){
		<%if(memberLoggedIn == null){%>
				alert("로그인후 이용하세요");
				return ;
		<%}%>
		var tr = $(e).parent().parent();		
		var td = tr.children();		
		var param = {
			<%if(memberLoggedIn!=null){%>
			memberId: "<%=memberLoggedIn.getMemberId()%>",
			<%}%>
			rn:	td.eq(5).text(),
			movieId: <%=movieId%>,
		}
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/dislikeReview.do",
			data: param,
			success:function(data){
				if(""==data){
					alert("좋아요/싫어요는 한 리뷰당 한번만 할수 있습니다");
				} else {
					getReviews(1);					
				}
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax처리실패!!");
				console.log(jqxhr, textStatus, errorThrown);
			}
		})
		
	}
	//평점 그래프 관련 함수
	function getAvg(){
		$.ajax({
			 url: "<%=request.getContextPath()%>/movie/getAvg.do",
			 data: movieId,
			 dataType: "json",
			 success: function(data){
				 //console.log(data);
				 if(data == null){
					 avg = 0;					 
				 } else {
					 avg = data.avg;					 
				 }
				 //console.log(avg);
				 var chart = bb.generate({
					 data: {
						    columns: [["평점", 0]],
						    type: "gauge"						    
						  },
						    "gauge": {
						    	label: {
									show: true,
									format: function(value, ratio) { return value+"점"; },
									min: 0,
									max: 10,								
									width: 10
								},
								"max": 10
						    },
						  size: {
						    height: 80
						  },  
						  bindto: "#gaugeChart"
				});
				setTimeout(function() {
					chart.load({
						columns: [["평점", avg]]
					});
				}, 500);
			 },
			 error: function(jqxhr, textStatus, errorThrown){
					console.log("ajax처리실패!!");
					console.log(jqxhr, textStatus, errorThrown);
			 }
		});
	}
	//장르 목록에서 클릭시 해당 장르가 포함된 영화를 검색
	//검색 페이지로 넘어가야 하므로 location.href를 사용함
	function searchByGenre(e){
		var docu = $(e).text();		
		if(docu.indexOf("다큐멘터리")> -1){
			docu = "다큐";
		} 
		var genre = docu;
		//console.log(genre);			
		location.href="<%=request.getContextPath()%>/movie/searchByGenre?genre=" + genre;
	}
	//배우 사진/이름을 클릭 시 해당 배우가 풀연한 최신 영화목록을 출력하는 페이지로 이동
	function searchByActor(actorId){
		//console.log(actorId);
		location.href="<%=request.getContextPath()%>/movie/searchByActor?actorId=" + actorId;
	}
	
</script>
</head>

<body>
	<div id="info-container"></div>
	<hr class='clear' />
	<div id="actors">
		<h2 class="subject">출연진</h2>
	</div>
	<hr class='clear' />
	<div id="videos">
		<h2 class="subject">미디어</h2>
		<div class="bd-example">
			<div id="carouselExampleCaptions" class="carousel slide"
				data-ride="carousel">
				<ol class="carousel-indicators">
				</ol>
				<div class="carousel-inner"></div>
				<a class="carousel-control-prev" href="#carouselExampleCaptions"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>
	</div>
	<hr class='clear' />
	<div id="review-insert">
		<h2 class="subject">리뷰하기</h2>
		<form>
			<!-- hide the input -->
			<input type="hidden" name="rating" id="rating-input" min="1" max="10" value="0"/>	
			<input type="hidden" name="memberId" id="memberId" value="<%=memberLoggedIn != null?memberLoggedIn.getMemberId():""%>"/>	

		<div class="rating" role="optgroup">
			<i class="fa fa-star-o fa-2x rating-star" id="rating-1" data-rating="1" tabindex="0" aria-label="Rate as one out of 10 stars" role="radio" title="1점"></i> 
			<i class="fa fa-star-o fa-2x rating-star" id="rating-2" data-rating="2" tabindex="0" aria-label="Rate as one out of 10 stars" role="radio" title="2점"></i> 
			<i class="fa fa-star-o fa-2x rating-star" id="rating-3" data-rating="3" tabindex="0" aria-label="Rate as one out of 10 stars" role="radio" title="3점"></i> 
			<i class="fa fa-star-o fa-2x rating-star" id="rating-4" data-rating="4" tabindex="0" aria-label="Rate as one out of 10 stars" role="radio" title="4점"></i> 
			<i class="fa fa-star-o fa-2x rating-star" id="rating-5" data-rating="5" tabindex="0" aria-label="Rate as one out of 10 stars" role="radio" title="5점"></i> 
			<i class="fa fa-star-o fa-2x rating-star" id="rating-6" data-rating="6" tabindex="0" aria-label="Rate as one out of 10 stars" role="radio" title="6점"></i> 
			<i class="fa fa-star-o fa-2x rating-star" id="rating-7" data-rating="7" tabindex="0" aria-label="Rate as one out of 10 stars" role="radio" title="7점"></i> 
			<i class="fa fa-star-o fa-2x rating-star" id="rating-8" data-rating="8" tabindex="0" aria-label="Rate as one out of 10 stars" role="radio" title="8점"></i> 
			<i class="fa fa-star-o fa-2x rating-star" id="rating-9" data-rating="9" tabindex="0" aria-label="Rate as one out of 10 stars" role="radio" title="9점"></i> 
			<i class="fa fa-star-o fa-2x rating-star" id="rating-10" data-rating="10" tabindex="0" aria-label="Rate as one out of 10 stars" role="radio" title="10점"></i>			
		</div>
		<div id="review-editor">
			<textarea name="review-comment" id="review-comment" rows="3" placeholder="리뷰를 입력하세요" style='color:black;'></textarea>
			<button type="button" class="btn btn-success" id="send-review">평가하기</button>		
		</div>
		</form>
	</div>
	<hr class='clear' />
	<div id="reviews">
		<h2 class="subject">리뷰현황</h2>
		<div id="review-graph">
		
		</div>
		<div id="written-comments">
			<table id="written" class="table">
				
			</table>
		</div>
	</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>