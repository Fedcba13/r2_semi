<%@page import="com.r2.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	HttpSession newSession = request.getSession();
	Member m = (Member) newSession.getAttribute("memberLoggedIn");
// 		System.out.println(m);
	

	//vo필드가 null인 것을 검사. null인 객체는 null인 문자열을 출력하지 않도록함.
	String memberId_ = m.getMemberId();
	String password = m.getMemberPassword();
	String memberName = m.getMemberName();
	String email = m.getMemberEmail()!=null?m.getMemberEmail():"";
	String phone = m.getMemberPhone();
	String address = m.getMemberAddress()!=null?m.getMemberAddress():"";
	
	String[] genre = new String[11];
	//똑같은 크기의 checked배열생성
	String[] genreChk = new String[11];
	for(int i=0; i< genreChk.length; i++){
		genreChk[i] = "";		
	}

	if(m.getMemberFavoriteGenre() != null){
		genre = m.getMemberFavoriteGenre().split(",");
	
		for(int i=0; i<genre.length; i++){
			
			switch(genre[i]){
			case "드라마": genreChk[0] = "checked"; break;
			case "로맨스": genreChk[1] = "checked"; break;
			case "코미디": genreChk[2] = "checked"; break;
			case "액션": genreChk[3] = "checked"; break;
			case "SF": genreChk[4] = "checked"; break;
			case "모험": genreChk[5] = "checked"; break;
			case "공포": genreChk[6] = "checked"; break;
			case "애니메이션": genreChk[7] = "checked"; break;
			case "다큐멘터리": genreChk[8] = "checked"; break;
			case "스릴러": genreChk[9] = "checked"; break;
			case "범죄": genreChk[10] = "checked"; break;
			}
			
		}
		
	}
	
	
%>
<style>
input{
	border : none;
	background : none;
}

section#enroll-container{
	text-align : center;
	margin : auto;
}


table{

	margin : auto;
}
</style>
<script>
function updateValidate(){
	
	return true;
}

function deleteMember(){
	var bool = confirm("정말로 탈퇴하시겠습니까?");
	if(bool){
		var frm = $("#memberDelFrm");
		frm.submit();
	}
		
}

function updatePassword(){
	var url = "<%=request.getContextPath()%>/member/memberUpdatePwd?memberId=<%=memberId_%>";
    var title = "updatePassword";
    var status =  "left=500px, top=200px, width=400px, height=210px";
    
	var popup = window.open(url,title,status);
	
}

</script>
<section id="enroll-container">
	<h2>회원 정보 보기</h2>
	<form action="<%=request.getContextPath()%>/member/memberUpdate"
		  name="memberUpdateFrm"
		  method="post"
		  onsubmit="return updateValidate();">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text"
						   name="memberId"
						   id="memberId_"
						   value="<%=memberId_%>" 
						   readonly 
						   required/>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="button" value="비밀번호변경 	>>"
			   onclick="updatePassword();" />
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text"
						   name="memberName"
						   id="memberName"
						   value="<%=memberName%>" 
						   required/>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="email"
						   name="memberEmail"
						   value="<%=email%>" 
						   id="memberEmail"/>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>
					<input type="tel"
						   name="memberPhone"
						   id="memberPhone"
						   placeholder="(-없이) 01012341234"
						   maxlength="11"
						   value="<%=phone%>" 
						   required/>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input type="text"
						   name="memberAddress"
						   value="<%=address%>" 
						   id="memberAddress"/>
				</td>
			</tr>
			<tr>
				<th>선호장르</th>
				<td>
					<input type="checkbox" name="genre" id="genre1" value="드라마" <%=genreChk[0] %>/>
					<label for="genre1">드라마</label>
					<input type="checkbox" name="genre" id="genre2" value="로맨스" <%=genreChk[1] %>/>
					<label for="genre2">로맨스</label>
					<input type="checkbox" name="genre" id="genre3" value="코미디" <%=genreChk[2] %>/>
					<label for="genre3">코미디</label>
					<input type="checkbox" name="genre" id="genre4" value="액션" <%=genreChk[3] %>/>
					<label for="genre4">액션</label>
					<input type="checkbox" name="genre" id="genre5" value="SF" <%=genreChk[4] %>/>
					<label for="genre5">SF</label>
					<input type="checkbox" name="genre" id="genre6" value="모험" <%=genreChk[5] %>/>
					<label for="genre6">모험</label>
					<br />
					<input type="checkbox" name="genre" id="genre7" value="공포" <%=genreChk[6] %>/>
					<label for="genre7">공포</label>
					<input type="checkbox" name="genre" id="genre8" value="애니메이션" <%=genreChk[7] %>/>
					<label for="genre8">애니메이션</label>
					<input type="checkbox" name="genre" id="genre9" value="다큐멘터리" <%=genreChk[8] %>/>
					<label for="genre9">다큐멘터리</label>
					<input type="checkbox" name="genre" id="genre10" value="스릴러" <%=genreChk[9] %>/>
					<label for="genre10">스릴러</label>
					<input type="checkbox" name="genre" id="genre11" value="범죄" <%=genreChk[10] %>/>
					<label for="genre11">범죄</label>
					
				</td>
			</tr>
			
		</table>
		<input type="submit" value="정보수정" />

		<input type="button" value="회원탈퇴" onclick="deleteMember();" />	  
	</form>
</section>
<form action="<%=request.getContextPath() %>/member/memberDelete"
	  id="memberDelFrm">
	<input type="hidden" name="memberId" value="<%=memberId_ %>" />
</form>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>