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
		$.ajax({
			 url: "<%=request.getContextPath()%>/movie/getAvg.do",
			 data: movieId,
			 dataType: "json",
			 success: function(data){
				 console.log(data);
				 avg = data.avg;
				 console.log(avg);
			 },
			 error: function(jqxhr, textStatus, errorThrown){
					console.log("ajax처리실패!!");
					console.log(jqxhr, textStatus, errorThrown);
			 }
		});
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/getDetail.do",
			data: movieId,
			type: "get",
			dataType: "json",
			success:function(data){
				pageTitle = data.title;
				$("title").text(pageTitle);
				console.log(data);				
				var html = "<div id='details'>";
				html += "<div id='main-poster'><img src='https://image.tmdb.org/t/p/w342//" + data.poster_path + "'/></div>";
				html += "<div id='title'>타이틀<br><span>" + data.title +"</span></div><br>";
				html += "<div id='overview'>줄거리<br><span> " + data.overview +"</span></div><br>";
				html += "<div id='genres'>장르<br>"
				$.each(data.genres, (i)=>{
					html +="<span class='gr'>" +data.genres[i].name +" </span>";
				});
				html +="</div><br>";
				html += "<div id='release_date'>개봉일<br><span>" + data.release_date + "</span></div>";
				html += "<div id='gaugeChart'></div><p id='grade'>평점</p>";
				html += "</div>";
				$("#info-container").html(html);
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
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/getActors.do",
			data: movieId,
			type: "get",
			dataType: "json",
			success: function(data){
				console.log(data);
				var html = ""
				$.each(data.crew, (i)=>{
					//console.log(data.crew[i].job);
					if("Director" === data.crew[i].job){
						html += "<div class='tn'><span><img src='https://image.tmdb.org/t/p/w92//" + data.crew[i].profile_path + "' class='img-thumbnail'/></span><br>"
						html += "<span>"+data.crew[i].name +"</span><br>";
						html += "<span>감독</span><br><br></div>";
					}
				})
				for(var i = 0; i < 5; i++){					
					html += "<div class='tn'><span><img src='https://image.tmdb.org/t/p/w92//" + data.cast[i].profile_path + "' class='img-thumbnail'/></span><br>"
					html += "<span>"+data.cast[i].name +"</span><br>";
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
		  getReviews();
		  getReviewGraph();
		  $("#send-review").on("click", ()=>{
			  var param = {
					  memberId: $("#memberId").val(),
					  rate: $("#rating-input").val(),
					  movieId: <%=movieId%>,
					  comment: $("#review-comment").val()			  
			  }
			  if(param.rate == 0 || param.comment.trim().length == 0){
				  alert("평점과 리뷰를 모두 작성하세요");
				  return ;
			  }
			  if(param.comment.trim().length > 100){
				  alert("너무 길어");
				  return;
			  }
			 /*  console.log(data.comment);
			  console.log(data.rate);
			  console.log(data); */
			  $.ajax({
				 url: "<%=request.getContextPath()%>/movie/insertReview.do",
				 data: param,
				 success: function(data){
					 getReviews();
					 getReviewGraph();
					 $("#review-comment").val("");
					 $("#rating-input").val(0);
				 },
				 error: function(jqxhr, textStatus, errorThrown){
						console.log("ajax처리실패!!");
						console.log(jqxhr, textStatus, errorThrown);
					}
			  });
		  })
	})
	//리뷰목록 가져오는 함수
	function getReviews(){
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/getReviews.do",
			data: movieId,
			dataType: "json",
			success: function(data){
				console.log(data);
				
				var html = "";
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
					html += "<td><img src='<%=request.getContextPath()%>/images/thumbUp.png' title='좋아요'><span>" +data[i].reviewLike+ "</span>&nbsp;<img src='<%=request.getContextPath()%>/images/thumbDown.png' title='싫어요'><span>" + data[i].reviewDislike +"</span></td>"
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
				
				$("#written").html(html);
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax처리실패!!");
				console.log(jqxhr, textStatus, errorThrown);
			}			
		});		
	}	
	function getReviewGraph(){
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/reviewGraph.do",
			data: movieId,
			success: function(data){
				console.log(data);				
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
	function deleteReview(e){		
		var tr = $(e).parent().parent();		
		var td = tr.children();		
		var param = {
			rn:	td.eq(5).text()
		}
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/deleteReview.do",
			data: param,
			success: function(){
				getReviews();
				getReviewGraph();
			},
			error: function(jqxhr, textStatus, errorThrown){
				console.log("ajax처리실패!!");
				console.log(jqxhr, textStatus, errorThrown);
			}
		});
		
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
			<textarea name="review-comment" id="review-comment" rows="3" placeholder="리뷰를 입력하세요"></textarea>
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