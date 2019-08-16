<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	content: url("<%=request.getContextPath()%>/images/support_white.png");
}

</style>

<script>
	$(()=>{
		$("div#support").css("top",$("section#content").height()-20);
		
		$("div#support").click(()=>{
			var option = "width = 500, height = 500, top = "+((window.screen.height-500)/2)+", left = "+((window.screen.width-500)/2)+", location = no"
			console.log(option);
			window.open("<%=request.getContextPath()%>/support/chatRoom.do", "관리자 문의방", option);
		});
		
	});
	
</script>

<div id="support">
	<img src="<%=request.getContextPath()%>/images/support.png">
</div>