<%@page import="com.r2.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//로그인 성공 후 session객체에 저장된 memberLoggedIn가져오기
	request.setCharacterEncoding("UTF-8");
	Member memberLoggedIn = (Member) session.getAttribute("memberLoggedIn");
	String searchKeyword = request.getParameter("keyword") != null ? request.getParameter("keyword") : "";
	String searchGenre = request.getParameter("genre") != null ? request.getParameter("genre") : "";
	String searchYearFrom = request.getParameter("yearFrom") != null ? request.getParameter("yearFrom") : "";
	String searchYearTo = request.getParameter("yearTo") != null ? request.getParameter("yearTo") : "";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Allvie</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link href="https://fonts.googleapis.com/css?family=Gothic+A1|Oswald&display=swap" rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/checkbox.css">
<style>

div.nav {
	width: 300px;
	height: 100%;
	position: fixed;
	left: -300px;
	transition: left .3s linear;
	padding: 0;
	top: 0;
	z-index: 999;
}

div.nav>div.content {
	width: 300px;
	background-color: #333335;
	height: 100%;
	padding: 30px 10px;
	color: #e6e6e6;
}

div.nav>div.arrow {
	position: absolute;
	top: 50%;
	left: 300px;
	transform: translateY(-50%);
	width: 50px;
}

div.nav>.arrow>img {
	width: 64px;
}
</style>
<script>
        $(() => {
        	
        	//검색 했을때 키워드 가져오기
        	var prevKeyword = '<%=searchKeyword%>';
        	var prevGenre = '<%=searchGenre%>';
        	var prevYearFrom = '<%=searchYearFrom%>';
        	var prevYearTo = '<%=searchYearTo%>';
        	
        	var chkList = new Array();
        	
            $(".arrow>img").click(() => {
                var left = $(".nav").css("left");
                if (left == '0px') {
                    $(".nav").css("left", -300);
                } else {
                    $(".nav").css("left", 0);
                }
            });

            var curDateYear = new Date().getFullYear();
            var findYear = 30;

            for(var i = 0; i<findYear; i++){
                $("#yearfrom").append('<option value="'+(curDateYear-findYear+i+1)+'">'+(curDateYear-findYear+i+1)+'</option>');
                $("#yearto").append('<option value="'+(curDateYear-i)+'">'+(curDateYear-i)+'</option>');
            }
            
            $(".chk_genre").change(e=>{
            	$(".chk_all").prop("checked", false);
            	if($("input:checked.chk_genre").length > 2){
            		alert('2개만 선택 가능합니다.');
            		e.target.checked = false;
            	}
            	
            	if($("input:checked.chk_genre").length == 0){
            		$('input.chk_all').prop("checked", true);
            	}
            	
            })
            
            //전체보기 change
            $(".chk_all").change(e=>{
            	if(e.target.checked){
            		chkList = new Array();
            		for(var i=0; i<$('input.chk_genre').length; i++){
            			chkList.push($('input.chk_genre')[i].checked);
            		}
            		$("input:checked.chk_genre").prop("checked",false);
            	}else{
            		for(var i=0; i<$('input.chk_genre').length; i++){
            			$('input.chk_genre')[i].checked = chkList[i];
            		}
            	}
            })
            
			$("#yearfrom").change(()=>{
				var yearfrom = $("#yearfrom").val();
				var yearto = $("#yearto").val();
				
				if(yearto != 0 && yearfrom > yearto){
					alert('시작일이 종료일보다 큽니다.');
					$("#yearfrom option:eq(0)").prop("selected", true);
				}
			});
            
            $("#yearto").change(()=>{
            	var yearfrom = $("#yearfrom").val();
				var yearto = $("#yearto").val();
				
				if(yearfrom != 0 && yearfrom > yearto){
					alert('종료일이 시작일보다 작습니다.');
					$("#yearto option:eq(0)").prop("selected", true);
				}
            });
            
            //검색 버튼 클릭
            $("#button-addon2").click(()=>{
            	var searchKeyword = $("input[name=categorySearch]").val();
            	var searchGenre = '';
            	$("input[name=chk_genre]:checked").each(function(){
            		if(searchGenre != ''){
            			searchGenre += ',';
            		}
            		searchGenre += $(this).val(); 
            	});
            	var yearfrom = $("#yearfrom").val();
            	var yearto = $("#yearto").val();
            	
            	var url  = '<%=request.getContextPath()%>/movie/searchMovie'
            	url += '?keyword='+searchKeyword;
				url += '&genre='+searchGenre;        		
				url += '&yearFrom='+yearfrom;	
				url += '&yearTo='+yearto;
            	
            	location.href = url;
            	
            });
            
            $("#categoryReset").click(()=>{
            	
            	//검색어 초기화
            	$("input[name=categorySearch]").val('');
            	
            	//체크박스 전부 해제
            	chkList = new Array();
            	for(var i=0; i<$('input.chk_genre').length; i++){
            		$('input.chk_genre')[i].checked = false;
        			chkList.push($('input.chk_genre')[i].checked);
        		}
            	$('input.chk_all').prop("checked", true);
            	$("#yearto option:eq(0)").prop("selected", true);
            	$("#yearfrom option:eq(0)").prop("selected", true);
            });
            
            if(prevKeyword != ''){
        		$("input[name=categorySearch]").val(prevKeyword);
        	}
        	
        	if(prevGenre != ''){
        		$("input[name=chk_all]").prop("checked", false);
        		$(prevGenre.split(',')).each((index, item) => {
        			$("input[name=chk_genre][value="+item+"]").prop("checked", true);
        		});
        	}
        	
			if(prevYearFrom != ''){
				$("#yearfrom").val(prevYearFrom).prop("selected", true);
        	}
        	
			if(prevYearTo != ''){
				$("#yearto").val(prevYearTo).prop("selected", true);
			}
            
        });
    </script>
</head>
<body>
	<div id="container">
		<header>
			<h1><span>A</span>llvie</h1>
			<!-- 메인 메뉴 시작 -->
			<nav>
				<ul class="main-nav">
					<li><a href="<%=request.getContextPath()%>">HOME</a></li>
					<li><a href="#">공지사항</a></li>
					<%
						if (memberLoggedIn != null) {
					%>
					<li><a href="<%=request.getContextPath()%>/member/memberView">회원정보보기</a></li>
					<li><a href="<%=request.getContextPath()%>/member/logout">로그아웃</a></li>
					<%
						} else {
					%>
					<li><a
						href="<%=request.getContextPath()%>/member/memberEnroll">회원가입하기</a></li>
					<li><a href="<%=request.getContextPath()%>/member/memberLogin">로그인</a></li>
					<%
						}
					%>
					<!-- 관리자메뉴추가:관리자인 경우만 출력 -->
					<%
						if (memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())) {
					%>
					<li><a href="<%=request.getContextPath()%>/admin/memberList">회원관리</a></li>
					<%
						}
					%>
				</ul>
			</nav>
		</header>
		<div class="nav">

			<div class="content">
				<p>카테고리</p>
				<div class="input-group mb-3">
					<input type="text" name="categorySearch" class="form-control"
						placeholder="검색어" aria-label="Recipient's username"
						aria-describedby="button-addon2">
					<div class="input-group-append">
						<button class="btn btn-outline-secondary" type="button"
							id="button-addon2">검색</button>
					</div>
				</div>
				<br>
				<div class="categoryGenre">
					<p>장르별</p>
					<table>
						<tr>
							<td><input type="checkbox" name="chk_all" id="0"
								class="chk_all" value="전체" checked> <label for="0">전체</label></td>
							<td><input type="checkbox" name="chk_genre" id="1"
								class="chk_genre" value="드라마"> <label for="1">드라마</label></td>
							<td><input type="checkbox" name="chk_genre" id="4"
								class="chk_genre" value="공포"> <label for="4">공포</label></td>
						</tr>
						<tr>
							<td><input type="checkbox" name="chk_genre" id="5"
								class="chk_genre" value="로맨스"> <label for="5">로맨스</label></td>
							<td><input type="checkbox" name="chk_genre" id="6"
								class="chk_genre" value="모험"> <label for="6">모험</label></td>
							<td><input type="checkbox" name="chk_genre" id="7"
								class="chk_genre" value="스릴러"> <label for="7">스릴러</label></td>
						</tr>
						<tr>
							<td><input type="checkbox" name="chk_genre" id="10"
								class="chk_genre" value="다큐"> <label for="10">다큐</label></td>
							<td><input type="checkbox" name="chk_genre" id="11"
								class="chk_genre" value="코미디"> <label for="11">코미디</label></td>
							<td><input type="checkbox" name="chk_genre" id="15"
								class="chk_genre" value="애니메이션"> <label for="15">애니메이션</label></td>
						</tr>
						<tr>
							<td><input type="checkbox" name="chk_genre" id="16"
								class="chk_genre" value="범죄"> <label for="16">범죄</label></td>
							<td><input type="checkbox" name="chk_genre" id="18"
								class="chk_genre" value="SF"> <label for="18">SF</label></td>
							<td><input type="checkbox" name="chk_genre" id="19"
								class="chk_genre" value="액션"> <label for="19">액션</label></td>
						</tr>
					</table>
				</div>
				<!-- <br>
				<div class="categoryNation">
					<p>국가별</p>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<label class="input-group-text" for="inputGroupSelect01">나라</label>
						</div>
						<select class="custom-select" id="inputGroupSelect01"
							name="country">
							<option selected value='0'>전체</option>
							<option value="KR">한국</option>
							<option value="JP">일본</option>
							<option value="US">미국</option>
							<option value="HK">홍콩</option>
							<option value="GB">영국</option>
							<option value="FR">프랑스</option>
							<option value="ETC">기타</option>
						</select>
					</div>
				</div> -->
				<div class="categoryYear">
					<p>개봉년도</p>
					<div class="input-group mb-3">
						<select class="custom-select" id="yearfrom">
							<option selected value=''>선택안함</option>
						</select> <span>~</span> <select class="custom-select" id="yearto">
							<option selected value=''>선택안함</option>
						</select>
					</div>
				</div>
				<div class="categoryReset">
					<button type="button" class="btn btn-outline-secondary"
						id="categoryReset">초기화</button>
				</div>
			</div>
			<div class="arrow">
				<img src="<%=request.getContextPath()%>/images/arrow.png">
			</div>
		</div>
		<section id="content">