<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% Member m = (Member)request.getAttribute("m"); %>
<style>
ul li a {
	text-align : center;
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
    	text-align : center;
    
}


</style>
<section id='findInfoId'>
<div class="member">
<div class="join">
	<ul>
		<li><a href="<%=request.getContextPath() %>/member/findInfoId">아이디 찾기</a></li>
		<li><a href="<%=request.getContextPath() %>/member/findInfoPwd">비밀번호 재설정</a></li>
	</ul>
	<br />
	
</div>
<br />
<h5> 확인된 아이디정보 입니다. </h5>

<ul class="memberInfo">
	
	<li><b>아이디 : </b> <%=m.getMemberId() %></li>
	
</ul>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>