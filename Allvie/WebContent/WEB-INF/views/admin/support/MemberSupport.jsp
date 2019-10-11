<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의</title>
<c:set var="id" value="${memberLoggedIn.memberId }"/>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<style>
div.msg{
	width: 500px;
	height: 400px;
	margin: 0 auto;
	padding: 10px;
}

#msg-container{
	height: 400px;
	overflow-y: scroll;
}

#msg-container > div > input#dm-msg{
	height: 50px;
}
</style>	
<script>
$(()=>{
	
	var id = '${id}';
	if(id == '' || id == 'null'){
		opener.alert('로그인을 먼저 해주세요.');
		self.close();
	}
	
	//메세지 불러오기
	messageList();
	
	var receiver = 'admin';
	
	var host = location.host;
	var ws = new WebSocket('ws://'+host+"${pageContext.request.contextPath}/chat/helloWebSocket");
	
	//최초연결시 open이벤트 핸들러
	ws.onopen = e => {
		console.log("ws open!");
	};
	//서버로부터 message를 전달 받았을때 핸들러
	ws.onmessage = e => {
		console.log("ws message: "+e.data);
		var o = JSON.parse(e.data);
		
		
		messageAdd(o);
		
		
	}
	//서버처리 도중 error발생시 핸들러
	ws.onerror = e => {
		console.log("ws error!");
	}
	//서버와 연결 종료시 핸들러
	ws.onclose = e => {
		console.log("ws close!");
	}
	
	//dm전송
	$("#dm-send").click(()=>{
		
		var dm = $("#dm-msg").val().trim();
		
		if(id == 'admin'){
			if(dm.length == 0 || $("#dm-client option:selected").val() == '' || $("#dm-client option:selected").val()=='접속자 목록'){
				alert('답변 대상자를 선택하세요.');
				return;
			}
			
			receiver = $("#dm-client option:selected").val();
			
		}else{
			receiver = 'admin';
		}
		
		var dm = $("#dm-msg").val().trim();
		
		var dm = {
			type: "dm",
			msg: $("#dm-msg").val(),
			sender: "${id}",
			receiver: receiver,
			time: Date.now()
		}
	
		var param = {
				dm: JSON.stringify(dm)
		}
		
		$.ajax({
			url:"${pageContext.request.contextPath}/chat/sendDM.do",
			data: param,
			dataType: "json",
			success: data => {
				console.log(data);
				if(data["result"] == 'no' && id != 'admin'){
					var html = "<li class='list-group-item' style='text-align: center; background-color: gray;'>관리자가 부재중입니다. 잠시만 기다려주세요.</li>";
					$("#msg-container ul").append(html);
				}
			}, 
			error: (jqxhr, textStatus, err)=>{
				console.log("ajax처리실패!");
				console.log(jqxhr, textStatus, err);
			}
	
		});
		
		$("#dm-msg").val('');

	});
	
	//DM 접속자목록 가져오기
	function getSupporMemberList(){
		$.ajax({
			url: "${pageContext.request.contextPath}/chat/userList.do",
			dataType: "json",
			success: function(data){
				//console.log(data);
				
				var html = "<option value='' disabled>접속자 목록</option>";
				$.each(data, (i, userId)=>{
					html += "<option value='"+userId+"'>"+userId+"</option>";
				});
				$("#dm-client").html(html);
			},
			error: function(jqxhr, textStatus, err){
				console.log("ajax처리실패");
				console.log(jqxhr, textStatus, err);
			}
		});
	};
	
	
	//엔터키 눌렀을때 send
	$("#dm-msg").keydown(e=>{
		if(e.key == "Enter"){
			$("#dm-send").trigger("click");
		}
	}); 
	
	//관리자 전용 => 목록 바꾸기.
	$("#dm-client").on('change', e=>{
		var selected = $("#dm-client option:selected").val();
		console.log(selected);
		if(selected == '')
			return;
		
		$("#chat-container").children('div.msg').hide();
		
		$("#msg-"+selected).show();
		
		if(selected == '접속자 목록'){
			$("#msg-container").show();
		}
		
	});
	
	//관리자 이전 데이터추가 db 가져오기
	
	function messageList(){
		
		$.ajax({
			url: "${pageContext.request.contextPath}/chat/messageList.do?id=${id}",
			dataType: "json",
			success: function(data){
				console.log(data);
				for(key in data) {
					for(var num=data[key].length-1; num>=0; num--){
						messageAdd(data[key][num]);
					}
				}
			
			},
			error: function(jqxhr, textStatus, err){
				console.log("ajax처리실패");
				console.log(jqxhr, textStatus, err);
			}
		});
	}
	
	
	//메세지 추가
	function messageAdd(o){
		var html ='';
		if(o.sender == id){
			html = "<li class='list-group-item' style='text-align: right;'>";
		}else{
			html = "<li class='list-group-item'>";
		}
		html += "<span class='badge badge-dark'>"+o.sender+"</span>";
		html += "&nbsp;&nbsp;"+o.msg;
		html += "</li>";
		
		if(id == 'admin'){
			//관리자 전용
			//div가 있는지 확인
			
			var container = o.sender;
			
			if(o.sender == 'admin'){
				container = o.receiver;
			}
			
			if ($("#msg-"+container+"").length == 0) {
			//div가 없을때 option 추가 및 div추가
				$("#chat-container").prepend("<div class='msg' id='msg-"+container+"' style='display: none;'><ul class='list-group list-group-flush'></ul></div>");
				$("#dm-client").append("<option value='"+container+"'>"+container+" - "+"</option>");
			}

			$("#msg-"+container+" ul").append(html);

			//scroll처리
			var scrollHeight = $("#msg-"+container).prop("scrollHeight");
			$("#msg-"+container).scrollTop(scrollHeight);
		}else{
			//개인 전용
			$("#msg-container ul").append(html);
			//scroll처리
			var scrollHeight = $("#msg-container").prop("scrollHeight");
			//console.log(scrollHeight);
			$("#msg-container").scrollTop(scrollHeight);
		}
	}
	 
	
})
</script>
</head>
<body>
	<section id="chat-container">
		<!-- 메세지목록 -->
		<div id="msg-container" class="msg">
			<ul class="list-group list-group-flush"></ul>
		</div>
			<%-- 접속자 목록은 관리자만 확인 가능 --%>
			<c:if test="not empty id && 'admin' == id">
				<div id="dm-container" class="input-group mb-3">
					<div class="input-group-prepend">
						<label class="input-group-text" for="dm-client">Options</label>
					</div>
					<select class="custom-select" id="dm-client">
						<option selected>접속자 목록</option>
					</select>
				</div>
			</c:if>
		<!-- 사용자입력 -->
		<div class="input-group mb-3">
			<input type="text" class="form-control" id="dm-msg">
			<div class="input-group-append">
				<button class="btn btn-outline-secondary" type="button" id="dm-send">Send</button>
			</div>
		</div>
	</section>
</body>
</html>