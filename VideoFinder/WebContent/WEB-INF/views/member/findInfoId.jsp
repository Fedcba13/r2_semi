<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
.join h5 {
    font-size: 20px;
    color: #212121;
    margin: 0 0 10px;
    text-align : center;
}


.member{

	width : 50%;
	margin : 0 auto;
	text-align : center;

}

input#memberName.form-control, input#user-phone-email.form-control, input#user-phone-email_.form-control, input#memberName_.form-control{
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
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}

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

ul li{
    display: list-item;
    list-style : none;
    float : left;
    padding : 10px;
    
}

</style>
<section id='findInfoId'>
<div class="member">
<div class="join">
	<ul>
		<li><a href="<%=request.getContextPath() %>/member/findInfoId">아이디 찾기</a></li>
		<li><a href="<%=request.getContextPath() %>/member/findInfoPwd">비밀번호 재설정</a></li>
	</ul>
	
</div>

<h5>본인인증으로 찾기</h5>
<hr>
<form action="<%=request.getContextPath()%>/member/findInfoIdByEmail" id="findInfoIdByEmail" name="findInfoIdByEmail" method="post">
	<label for="memberName" class="dsn"></label> 
	<input type="text"class='form-control' name='memberName_' id='memberName_'
			placeholder='이름을 입력해주세요' required />
	<label for='user-phone-email-label' class='dsn' id='user-phone-email-label'></label> 
	<input type='email' class='form-control' name='findInfo_' id='user-phone-email_'
			placeholder='등록하신 메일주소를 입력해주세요. ' />
			<br>
	<input type="submit" value='확인' class="btn btn-outline-secondary"/>
</form>
<br />
<form action="<%=request.getContextPath()%>/member/findInfoIdEnd" id="findInfoId" name="findInfoId" method="post" >
<h5>간편찾기</h5>
	<ul class="find-wrap01">
		<li class="radio-style"><input type="radio" name="id-find"
			id="id-phone" value='phone' checked onchange='radio();' /> 
		<label
			for="id-phone">휴대폰번호</label></li>
		<li class="radio-style"><input type="radio" name="id-find"
			id="id-mail" value='email' onchange='radio();' /> 
		<label for="id-mail">이메일주소</label></li>
		</ul>
		<br />
		<label for="memberName" class="dsn"></label> 
		<input type="text"class='form-control' name='memberName' id='memberName'
			placeholder='이름을 입력해주세요' required />
	<div id='radioInput'>
		<label for='user-phone-email-label' class='dsn' id='user-phone-email-label'></label> 
		<input type='text' class='form-control' name='findInfo' id='user-phone-email'
			placeholder='(-)없이 번호만 입력해주세요' />
	</div>
		<br />
		<input type="submit" value='확인' class="btn btn-outline-secondary"/>
</form>
</div>
</section>

<script>

function radio(){
	html= "";
	if($("#id-mail").prop("checked") == true){
		html += "<label for='user-phone-email-label' class='dsn' id='user-phone-email-label'></label>";
		html += "<input type='text' class='form-control' name='findInfo' id='user-phone-email' placeholder='email을 입력해주세요' />";
	} else{
		html += "<label for='user-phone-email-label' class='dsn'id='user-phone-email-label'></label>";
		html += "<input type='text'class='form-control' name='findInfo' id='user-phone-email'";
		html += "placeholder='(-)없이 번호만 입력해주세요' />";
	}
	
		$('#radioInput').html(html);
}

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>