<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>



.member{

	width : 50%;
	margin : 0 auto;
	text-align : center;

}


input#memberName.form-control, input#user-phone-email.form-control, input#user-phone-email_.form-control, input#memberName_.form-control{
	width: 300px;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;

}

.join ul li a {
    display: inline-block;
    position: relative;
    top: -1px;
    font-size: 16px;
    letter-spacing: -0.3px;
	color : #e6e6e6;
}

ul li{
    display: list-item;
    list-style : none;
    float : left;
	padding : 10px; 
	text-aligh : center;
    
}

div.join, div.join>ul{
	overflow: hidden;
}

div#formdiv{
	padding-top: 40px;
}

div.join>ul{
    display: inline-block;
    text-align: center;
    margin: 0 auto;
    padding:0;
}

div#formdiv, form#findInfoId{
	overflow: hidden;
	padding-bottom : 10px;
	display: inline-block;
}

	
}
h2{
	color: #e6e6e6;
    font-family: 'Oswald', sans-serif;
    font-weight: bold;    
    padding-top: 30px;
}

.btn-outline-secondary:hover{
  	 border-color: #ac162c;
     background: #ac162c;
}

input.btn-outline-secondary{
    border-color: #6c757d;
    background: gray;
    color: white;
    margin-left : 25px;
    padding-botton : 10px;
}

ul li a:hover{
	color : #f0522a;
	text-decoration : none;
}


</style>
<section id='findInfoId'>

<div class="member">
<div id="formdiv">
<h2 class='top' style='color:#e6e6e6;'>본인인증으로 찾기</h2>
<hr>
<div class="join">
	<ul>
		<li><a href="<%=request.getContextPath() %>/member/findInfoId">아이디 찾기</a></li>
		<li><a href="<%=request.getContextPath() %>/member/findInfoPwd">비밀번호 재설정</a></li>
	</ul>
	
</div>
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
</div>

<form action="<%=request.getContextPath()%>/member/findInfoIdEnd" id="findInfoId" name="findInfoId" method="post" >
<h2 class='top' style='color:#e6e6e6;'>간편찾기</h2>
<hr>

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