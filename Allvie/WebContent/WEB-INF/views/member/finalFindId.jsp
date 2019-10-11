<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<style>

section#findInfoId{
	overflow: hidden;
}
ul li a {

    display: inline-block;
    position: relative;
    top: -1px;
    color: #212121;
    font-size: 16px;
    letter-spacing: -0.3px;   
	color : #e6e6e6;
     
}

ul li a:hover{
	color : #f0522a;
	    text-decoration : none;
}
ul li{
    display: list-item;
    list-style : none;
    float : left;
	padding : 10px;
   	text-align : center;
    
}

ul{
   	text-align: center;
    display: inline-block;
    }

.findInfoId{
	text-align: center;
}



</style>
<section id='findInfoId'>
<div class="findInfoId">
<div class="join">
	<ul>
		<li><a href="${pageContext.request.contextPath}/member/findInfoId">아이디 찾기</a></li>
		<li><a href="${pageContext.request.contextPath}/member/findInfoPwd">비밀번호 재설정</a></li>
	</ul>
	<br />
	
</div>
<br />
<h2 style='color:#e6e6e6;'> 확인된 아이디정보 입니다. </h2>

<ul class="memberInfo">
	<li style='color:#e6e6e6;'><h4><b >아이디 : </b> ${m.memberId }</h4></li>
</ul>
</div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>