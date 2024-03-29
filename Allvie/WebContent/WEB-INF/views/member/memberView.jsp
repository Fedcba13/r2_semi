<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<style>
input{
	border : none;
	background : none;
}

section#enroll-container{
	text-align : center;
	margin : auto;
}


table#view{
	padding-top : 20px;
	padding-bottom : 20px;
	width : 50%;
	margin : auto;
	border-collapse: separate;
  	border-spacing: 0 10px;
  	
/*   	 font-family: 'Oswald', sans-serif; */
}
td, tr {
	color : #e6e6e6;
	font-family: 'Oswald', sans-serif;
	text-align : left;
}



#password{ 
	color : #e6e6e6;
	font-weight : bold;
}

#password:hover{
	color : #f0522a;
	font-weight : bold;
}

#top{
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
}

input{
    font-family: 'Oswald', sans-serif;
}
</style>
<script>

$(()=>{
<c:if test="${not empty memberLoggedIn.memberFavoriteGenre }">
	<c:forEach var="genre" items="${fn:split(memberLoggedIn.memberFavoriteGenre, ',') }">
		$("input[type=checkbox][name=genre][value=${genre }]").prop("checked", true)
	</c:forEach>
</c:if>
	
})
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
	var url = "${pageContext.request.contextPath}/member/memberUpdatePwd?memberId=${memberLoggedIn.memberId}";
    var title = "updatePassword";
    var status =  "left=500px, top=200px, width=665px, height=327px";

    var popup = window.open(url,title,status);
	
}

</script>
<section id="enroll-container">
	<h2 id='top'>회원 정보 보기</h2>
	<form action="${pageContext.request.contextPath}/member/memberUpdate"
		  name="memberUpdateFrm"
		  method="post"
		  onsubmit="return updateValidate();">
		<table id='view'>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text"
						   name="memberId"
						   id="memberId_"
						   value="${memberLoggedIn.memberId }" 
						   readonly 
						   required/>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="button" id='password' style='font-weight: bold;'value="비밀번호변경  >>"
			   onclick="updatePassword();" />
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text"
						   name="memberName"
						   id="memberName"
						   value="${memberLoggedIn.memberName }" 
						   required/>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="email"
						   name="memberEmail"
						   value="${memberLoggedIn.memberEmail }" 
						   id="memberEmail" readonly/>
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
						   value="${memberLoggedIn.memberPhone }" 
						   required/>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<input type="text"
						   name="memberAddress"
						   value="${memberLoggedIn.memberAddress }" 
						   id="memberAddress"/>
				</td>
			</tr>
			<tr>
				<th>선호장르</th>
				<td>
					<input type="checkbox" name="genre" id="genre1" value="드라마"/>
					<label for="genre1">드라마</label>
					<input type="checkbox" name="genre" id="genre2" value="로맨스"/>
					<label for="genre2">로맨스</label>
					<input type="checkbox" name="genre" id="genre3" value="코미디"/>
					<label for="genre3">코미디</label>
					<input type="checkbox" name="genre" id="genre4" value="액션"/>
					<label for="genre4">액션</label>
					<input type="checkbox" name="genre" id="genre5" value="SF"/>
					<label for="genre5">SF</label>
					<input type="checkbox" name="genre" id="genre6" value="모험"/>
					<label for="genre6">모험</label>
					<br />
					<input type="checkbox" name="genre" id="genre7" value="공포"/>
					<label for="genre7">공포</label>
					<input type="checkbox" name="genre" id="genre8" value="애니메이션"/>
					<label for="genre8">애니메이션</label>
					<input type="checkbox" name="genre" id="genre9" value="다큐멘터리"/>
					<label for="genre9">다큐멘터리</label>
					<input type="checkbox" name="genre" id="genre10" value="스릴러"/>
					<label for="genre10">스릴러</label>
					<input type="checkbox" name="genre" id="genre11" value="범죄"/>
					<label for="genre11">범죄</label>
					
				</td>
			</tr>
			
		</table>
		<input type="submit" class="btn btn-outline-secondary" value="정보수정" />
		
		<input type="button"  class="btn btn-outline-secondary"value="회원탈퇴" onclick="deleteMember();" />	  
	</form>
</section>
<form action="${pageContext.request.contextPath }/member/memberDelete"
	  id="memberDelFrm">
	<input type="hidden" name="memberId" value="${memberLoggedIn.memberId }" />
</form>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />