<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<style>
div#support{
	top: 1000px;
	left: 910px;
	position: fixed;
	background-color: white;
	border-radius: 50%;
	width: 96px;
	height: 96px;
	line-height: 80px;
	text-align: center;
	cursor: pointer;
}

div#support>img{
	width: 64px;
	height: 64px;
}

div#support:hover{
	background-color: #f0522a;
}

div#support:hover > img{
	content: url("${pageContext.request.contextPath}/images/support_white.png");
}

</style>

<script>
	$(()=>{
		$("div#support").css("top",$("section#content").height()-20);
		
		$("div#support").click(()=>{
			
			<c:if test="${empty memberLoggedIn}">
				var option = "width = 500, height = 500, top = "+((window.screen.height-500)/2)+", left = "+((window.screen.width-500)/2)+", location = no"
				console.log(option);
				window.open("${pageContext.request.contextPath}/support/chatRoom.do", "관리자 문의방", option);
			</c:if>
			<c:if test="${!empty memberLoggedIn}">
				alert('로그인 부터 해주세요.');
			</c:if>
		});
		
	});
	
</script>

<div id="support">
	<img src="${pageContext.request.contextPath }/images/support.png">
</div>