<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<style>
.join ul li a {
	display: inline-block;
	line-height: 58px;
	position: relative;
	top: -1px;
	color: #212121;
	font-size: 16px;
	letter-spacing: -0.3px;
	font-family: 'NanumSquareBold';
}

ul li {
	display: list-item;
	list-style: none;
	float: left;
	padding: 10px;
}

input#memberId.form-control, input#user-email.form-control {
	width: 300px;
	margin-left: 10px;
	display: block;
	/*     width: 100%; */
	height: calc(1.5em + .75rem + 2px);
	padding: .375rem .75rem;
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #495057;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid #ced4da;
	border-radius: .25rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
}
</style>

<section id='findInfoPwd'>
	<div class="member">
		<div class="join">
			<ul>
				<li><a href="<%=request.getContextPath() %>/member/findInfoId">아이디
						찾기</a></li>
				<li><a href="<%=request.getContextPath() %>/member/findInfoPwd">비밀번호
						재설정</a></li>
			</ul>
			<br />

		</div>
		<br />
		<h5>비밀번호 재설정</h5>
		<form action="<%=request.getContextPath()%>/member/findInfoPwdEnd" id='findInfoPwd' method="post">
			<label for="memberName" class="dsn"></label> 
			<input type="text"class='form-control' name='memberId' id='memberId'
				placeholder='아이디를 입력해주세요' required /> 
			<label for='user-email-label'class='dsn' id='user-email-label'></label> 
			<input type='email'class='form-control' name='findInfo' id='user-email'
				placeholder='email주소를 입력해주세요' required/> <br /> 
			<input type="submit" value='확인' class="btn btn-outline-secondary"/>
	</div>
	</form>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>