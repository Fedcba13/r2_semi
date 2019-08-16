<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>

/* .member-layout{
	padding : 40px;
} */
.login ul.join-find {
    margin: 20px auto 0;
    overflow: hidden;
    width: 340px;
}

.input-wrap01 li {
	margin: 20px auto 0;
}

.form-control{
width : 300px;}

.login ul.checkbox-wrap01 {
    margin: 24px 0 43px;
    overflow: hidden;
    text-align: center;
}

.login ul.checkbox-wrap01 li {
    margin-right: 15px;
	display: inline-block;
}



ul.input-wrap01 li{
text-align : center;
display : inline-block;}

label {
    margin: 0;
    padding: 0;
    vertical-align: middle;
}

button, label {
    cursor: pointer;
}
ul {
    margin: 0;
    padding: 0;
    list-style: none;
    display: block;
    
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 40px;
}

.join-find li#menu a{
	    float : left;
	    padding : 5px;
	    color : gray;
	    
}
li {
    text-align: -webkit-match-parent;
}

.check-style input[type=checkbox].check-purple+label:before {
    border: 1px solid #d9d9d9;
}



* {
    box-sizing: border-box;
}

.member{text-align:center;}

.btn-outline-secondary:hover, .btn-outline-dark:hover{
  	 border-color: #ac162c;
     background: #ac162c;
     
     
}



input.btn-outline-secondary{
    border-color: #6c757d;
    background: gray;
    color: white;
    font-family: 'Oswald', sans-serif;
    /* margin-left : 25px; */
}

img{
	width : 100%;
	padding : 10px;
}

ul.join-find li#menu:after {
    position: absolute;
    top: 5px;
    right: 0;
    content: "";
    width: 1px;
    height: 14px;
    background-color: #e5e5e5;
}

</style>

</head>
<body>
<table>
  <tr style="vertical-align:middle">
    <td style="padding-top: 60px;width:50%;border-right:1px solid gray; padding-right:12px; ">

<img src="<%=request.getContextPath() %>/images/loginMovie.jpg" alt="" />


</td>
<td style="padding-left:10px">
<div class="member">
	<div class='login member-layout'>
		<h1 style='color:#e6e6e6;'>로그인</h1>
		<form action="<%=request.getContextPath()%>/member/memberLoginEnd" 
				id="logIn" name="logIn" method="post" onsubmit='return check();'>
		<ul class="input-wrap01">
			<li>
				<input type="text" class="form-control" name='memberId' id='memberId'
				placeholder='아이디를 입력해주세요' />
			</li>
			<br />
			<li>
				<input type="text" class="form-control" name='memberPassword' id='memberPassword'
				placeholder='비밀번호를 입력하여주세요' />
			</li>
		</ul>
		<ul class="checkbox-wrap01">
			<li class="checkbox-wrap01">
				<div class="btn-group-toggle" data-toggle="buttons">
				<laber for='id-save' class="btn btn-outline-dark">아이디 저장
				<input type="checkbox" id='id-save' class='check-purple' />
				</laber>
				</div>
			</li>
			<li>
				<input type="submit" class="btn btn-outline-secondary" value="로그인">
			</li>
		</ul>
		
		</form>
		<ul class="join-find">
			<li id='menu'><a href="<%=request.getContextPath()%>/member/memberEnroll">회원가입</a></li>
			<li id='menu'><a href="<%=request.getContextPath()%>/member/findInfoId">아이디찾기</a></li>
			<li id='menu'><a href="<%=request.getContextPath()%>/member/findInfoPwd">비밀번호 재설정</a></li>
		</ul>
	</div>
</div>
</td>
</tr>
</table>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>



