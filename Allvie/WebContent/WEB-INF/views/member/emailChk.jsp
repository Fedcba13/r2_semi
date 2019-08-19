<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.r2.member.model.vo.Member" %>
<%
	String email = (String)request.getParameter("memberEmail");

	String num = (String)request.getAttribute("num");
	if(num != null){
		email = (String)request.getAttribute("email");
	}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 인증</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
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
<style>
div#emailChk-container{background-color:#333335;}
div#emailChk-container table {margin:10px auto; border-spacing: 20px;}
div#emailChk-container table tr:last-of-type td {text-align:center;}
input.btn{margin-bottom : 20px;}
h1.top {
	text-align:center; 
	padding-top:10px;
	color:#e6e6e6;
	font-weight:bolder;}
</style>
<script>
function emailConfirm(){
	var email = $("#memberEmail").val();
			
	var bool = confirm("메일주소로 인증번호를 발송하겠습니까?");
	if(bool){
		$("#memberEmail_").val(email);
		var frm = $("#emailCheck");
		frm.submit();
	}
}

function CheckEnd(){
	
	var numChk = $("#numChk").val().trim();
	var num = $("#num").val();
	if(numChk != num){
		/* $('#newPassword').place */
		alert('인증번호를 다시 확인해주세요. ');
		return false;
	}
	
	var frm = opener.document.enrollFrm;

	frm.memberEmail.value = '<%=email%>';
	frm.emailChk.value = 0;
	frm.memberAddress.focus();
	self.close();
	

		return true;	
	
}

</script>
</head>
<body>


<form action="<%=request.getContextPath()%>/member/emailCheckDo" id="emailCheck">
	<input type="hidden" id='memberEmail_' name="memberEmail" value="<%=email %>">
</form>
	<div id="emailChk-container">
<h1 class='top'>이메일 인증</h1>

<br />
			<table>
				<tr>
					
					<td>
					
					<div class="input-group mb-3">
					<input type="email" class="form-control"
						id="memberEmail" value="<%=email %>" required>
					<input type="button" class="btn btn-outline-secondary"
						onclick="emailConfirm();" id='clickop' value="메일 발송">	
					</div></td>
				</tr>
				<tr>
					<td>	
						<input type="text" class="form-control" id="numChk" placeholder='인증번호 입력' required><br>
						<input type="hidden" id="num" value="<%=num%>"><br>
						<input type="hidden" id="enrollCheck" value="1"><br>
					</td>
				</tr>
				<tr>
					<td colspan="2">\
						<input type="submit"  class="btn btn-outline-secondary" value="확인" onclick="return CheckEnd();"/>&nbsp;
						<input type="button" class="btn btn-outline-secondary" onclick="self.close();" value="닫기" />						
					</td>
				</tr>
			</table>

	
	</div>
</body>
</html>