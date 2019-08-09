<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section>
<div class="member">
<div class="join">
	<ul>
		<li><a href="">아이디 찾기</a></li>
		<li><a href="">비밀번호 찾기</a></li>
	</ul>
</div>
<div class="certi-txt02">
"
	아래의 아이디 찾기 수단 중"
<br />
"
	하나를선택하여 진행해주세요. "
</div>
<h5>본인인증으로 찾기</h5>

<form action="">
<h5>간편찾기</h5>
<ul class="find-wrap01">
	<li class="radio-style">
	<input type="radio" name="id-phone" id="id-find" value='phone' checked/>
	<label for="id-phone">휴대폰번호</label>
	</li>
	<li class="radio-style">
	<input type="radio" name="id-mail" id="id-mail" value='email' />
	<label for="id-mail">이메일주소</label>
	</li>
</ul>
<label for="memberName" class="dsn"></label>
<input type="text" class='input-style01' name='memberName' id='memberName' 
placeholder='이름을 입력해주세요' required/>
<label for="memberEmail" class="dsn"></label>
<input type="text" class='input-style01' name='findInfo' id='memberEmail'
placeholder='(-)없이 번호만 입력해주세요' />
<label for="memberEmail" class="dsn"></label>
<input type="text" class='input-style01' name='findInfo' id='memberEmail'
placeholder='email을 입력해주세요' />

<input type="submit" value='확인' />
</form>
</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>