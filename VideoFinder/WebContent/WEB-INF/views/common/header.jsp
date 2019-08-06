<%@page import="com.r2.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//로그인 성공 후 session객체에 저장된 memberLoggedIn가져오기
	request.setCharacterEncoding("UTF-8");
	Member memberLoggedIn = (Member) session.getAttribute("memberLoggedIn"); 
	//	System.out.println("memberLoggedIn@index.jsp = " + memberLoggedIn);
	
	//쿠키관련 처리
	Cookie[] cookies = request.getCookies();
	boolean saveId = false;
	String memberId = "";
	
	if(cookies != null){
		//System.out.println("--------------------------");
		for(Cookie c: cookies){
			String key = c.getName();
			String value = c.getValue();
			//System.out.println(key +" : " + value);
			
			//전송된 saveId쿠키가 있는 경우
			if("saveId".equals(key)){
				saveId = true;
				memberId = value;
			}
				
		}
		//System.out.println("--------------------------");
	}
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script>
	function validate(){
		if($("#memberId").val().trim().lenth == 0){
			alert("아이디를 입력하세요.");
			$("#memberId").focus();
			return false;
		}
		if($("#password").val().trim().length == 0){
			alert("비밀번호를 입력하세요.");
			$("#password").focus();
			return false;
		}

		return true;
	}
	
	/*
	* 회원가입폼 페이지 요청
	*/
	function goEnroll(){
		//WEB-INF하위 디렉토리는자원은 클라이언트 요청으로는 접근할 수 없다.
		//WEB-INF하위의 JSP는 forwarding방식으로만 접근이 가능하다.
		//location.href = "<%=request.getContextPath()%>/WEB-INF/views/member/memberEnroll.jsp"; 
		location.href = "<%=request.getContextPath()%>/member/memberEnroll";
	}
	
</script>
</head>
<body>
	<div id="container">
		<header>
			<h1>Hello MVC</h1>
			<!-- 로그인 메뉴 -->
			<div class="login-container">
				<%if(memberLoggedIn == null){ //로그인 안한 상태, 실패한 상태 %>
				<form action="<%=request.getContextPath() %>/member/login" 
				id="loginFrm" method="post" onsubmit="return validate();">
					<table>
						<tr>
							<td>
								<input type="text" id="memberId" 
									name="memberId" placeholder="아이디"
									tabindex="1" value="<%=saveId?memberId : "" %>"/>
							</td>
							<td>
								<input type="submit" value="로그인" 
									tabindex="3"/>
							</td>
						</tr>
						<tr>
							<td><input type="password" name="password"
									id="password" placeholder="비밀번호" 
									tabindex="2"/>
							</td>
							<td>
								
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" name="saveId"
										id="saveId" <%=saveId?"checked" : ""%>>
								<label for="saveId">아이디저장</label>
								<input type="button" value="회원가입" 
										onclick="goEnroll();"/>
							</td>
						</tr>
					</table>
				</form>
				<%}else{ //로그인에 성공한 경우%>
					<table id="logged-in">
						<tr>
							<td>
								<%=memberLoggedIn.getMemberName() %>님, 안녕하세요.
							</td>
						</tr>	
						<tr>
							<td>
								<input type="button" value="내정보 보기"
									onclick="location.href='<%=request.getContextPath()%>/member/memberView?memberId=<%=memberLoggedIn.getMemberId()%>'">
								<input type="button" value="로그아웃"
									onclick="location.href='<%=request.getContextPath()%>/member/logout'">
							</td>
						</tr>
					</table>
				<%} %>
			</div>
			<!-- 로그인 메뉴 끝 -->
			<!-- 메인 메뉴 시작 -->
			<nav>
				<ul class="main-nav">
					<li><a href="<%=request.getContextPath()%>">HOME</a></li>
					<li><a href="#">공지사항</a></li>
					<li><a href="<%=request.getContextPath()%>/board/boardList">게시판</a></li>
					<li><a href="<%=request.getContextPath()%>/board/photoList">사진게시판</a></li>
					<!-- 관리자메뉴추가:관리자인 경우만 출력 -->
					<%if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())){ %>
						<li><a href="<%=request.getContextPath()%>/admin/memberList">회원관리</a></li>
					<%} %>
				</ul>
			</nav>
		</header>
		
		<section id="content">