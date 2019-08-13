﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.r2.member.model.vo.Member" %>
<%
	//이후 관리자가 회원관리할 경우에 대비해서 session객체에서 값을 꺼내오지 않도록 함.
	String memberId = (String)request.getParameter("memberId");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
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
<style>
div#updatePassword-container{background:red;}
div#updatePassword-container table {margin:0 auto; border-spacing: 20px;}
div#updatePassword-container table tr:last-of-type td {text-align:center;}
</style>
<script>
function passwordValidate(){
	var pwd_new = $("#newPassword").val().trim();
	var pwd_chk = $("#password_chk").val().trim();
	
	if(pwd_new!=pwd_chk){
		/* $('#newPassword').place */
		$("#newPassword").select();
		return false;
	}

	return true;	
}
</script>
</head>
<body>
	<div id="updatePassword-container">
		<form name="updatePwdFrm" action="<%=request.getContextPath()%>/member/updatePasswordEnd" method="post" >
			<table>
				<tr>
					
					<td><input type="password" class="form-control" name="memberPassword" id="memberPassword" placeholder='현재 비밀번호' required></td>
				</tr>
				<tr>
					<td>
						<input type="password" class="form-control" name="newPassword" id="newPassword" placeholder='새로운 비밀번호' required>
					</td>
				</tr>
				<tr>
					<td>	
						<input type="password" class="form-control" id="password_chk" placeholder='새로운 비밀번호 확인' required><br>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit"  class="btn btn-outline-secondary" value="변경" onclick="return passwordValidate();"/>&nbsp;
						<input type="button" class="btn btn-outline-secondary" onclick="self.close();" value="닫기" />						
					</td>
				</tr>
			</table>
			<input type="hidden" name="memberId" value="<%=memberId %>" />
		</form>
	</div>
</body>
</html>