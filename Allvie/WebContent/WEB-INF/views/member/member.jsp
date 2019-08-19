<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src='<%=request.getContextPath() %>/js/jquery-3.4.1.js'></script>
<script>
function checkIdDuplication(){
	
	var memberId = $("#memberId").val().trim();
	
	if(memberId.length == 0){
		alert("아이디를 입력해주세요. ")
		return;
	}
	

	var param = {
			memberId : memberId
			
	}
			$.ajax({
				url : "<%=request.getContextPath()%>/member/memberIdDuplicate.do",
				type : "post",
				data : param,
				dataType : "json",
				success : function(data){
					console.log(data);	
					
					var html = "";
					
					if(data > 0){
						html += "<br><p class='check'>중복된 아이디가 존재합니다.</p>";
					}else{
						html += "<br><p>사용할 수 있는 아이디입니다. </p>";
					}
					
					$(".dbi").html(html);
					
				},
				error : function(jqxhr, textStatus, errorThrown){
					console.log("ajax실패!")
					console.log(jqxhr, textStatus, errorThrown);
				}
			});
	
	
	
}

function validate(){
	var memberId = document.getElementById("memberId");
	var memberPassword = document.getElementById("memberPassword");
	var memberPasswordChk = document.getElementById("memberPasswordChk");
	var memberName = document.getElementById("memberName");
	var memberPhone = document.getElementById("memberPhone");
	
    var rep1 = /^[a-z][a-z\d]{3,11}$/;
	var rep2 = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g;
	var rep3 = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,}$/;

  if(!regExpTest(rep1, memberId, "아이디는 첫글자는 영문 소문자, 하나이상의 숫자를 포함한 4~12자의 조합입니다. " )){
		
	  	return false;
    } 
  

  if(!regExpTest(rep2, memberPassword, "비밀번호는 8~15자리 숫자/문자/특수문자를 포함해야합니다.")){
          return false;
  }
    
  if(memberPassword.value == memberPasswordChk.value){
      return true;
  }else{
	memberPassword.value = "";
	memberPassword.placeholder = "비밀번호가 일치하지않습니다";
	$(memberPassword).addClass('red');
    memberPassword.focus();
     return false;
  }
  
  

  
    
	return false;
	
}

function regExpTest(regExp, what, message) {
    if(regExp.test(what.value)) {
        return true;
       
    }
    
    what.placeholder = message;
	$(what).addClass('red');
    what.value = "";
    what.focus();
   
  	return false;
}

</script>
<script>


 
 
 function kakaoAddress() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수
 
                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }
  
                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("memberAddress").value = fullAddr;

                self.close;
            }
        }).open();
        
        close();
    }
    
    
    
</script>
<style>
p.check{
	color : red;
}

.red::placeholder {
	color : red;
}

section#enroll-container {text-align:center;}
section#enroll-container input{margin:3px;}
section#enroll-container input button{margin:3px;}
section#enroll-container table {margin:0 auto;}
section#enroll-container table th{padding: 0 10px; text-align: center;}

section#enroll-container table td#genreAll{
    float: left;
    font-size : 20px;
	padding: 10px;
}

    
table.memberEnroll td.badge badge-light{
	overflow : hidden;
}

table.memberEnroll tr td input.form-control{
width : 200px;
padding: 0 10px; 
text-align: left;

}

.input-group>.form-control{
width : 200px;
flex : none;

}

h1.top{
    color: #e6e6e6;
    font-family: 'Oswald', sans-serif;
    font-weight: bold;    
    padding-top: 30px;
}

#genreAll{
    color: #e6e6e6;
    font-family: 'Oswald', sans-serif;
    font-weight: bold;    
}


.btn-outline-secondary:hover, div.input-group .btn-outline-secondary:hover{
  	 border-color: #ac162c;
     background: #ac162c;
     
     
}

input.btn-outline-secondary, div.input-group .btn-outline-secondary{
    border-color: #6c757d;
    background: gray;
    color: white;
    font-family: 'Oswald', sans-serif;
    /* margin-left : 25px; */
}

input{
    font-family: 'Oswald', sans-serif;
}

div.button1 input.button{

margin-left : 25px;	

}

 
 

</style>

<section id="enroll-container">
		<h1 class='top'>회원가입</h1>
		<br />
		
		<form action="<%=request.getContextPath()%>/member/memberEnrolled"
			id="enrollFrm" name="enrollFrm" method="post" onsubmit="return validate();">
			<table class='memberEnroll'>
				<tr>

					<td>
						<div class="input-group mb-3">
						
 							<input type="text" class="form-control"
								placeholder="아이디를 입력하세요. " id="memberId" name="memberId"
								required>
							<div class="input-group-append">
								<button class="btn btn-outline-secondary" type="button"
									id="checkId" onclick="checkIdDuplication();")>중복확인</button>
							</div>
						</div>
						<div class="dbi"></div>
					</td>
				</tr>
				<tr>

					<td><input type="password" class="form-control"
						placeholder="비밀번호를 입력하세요. " id="memberPassword"
						name="memberPassword" required></td>
				</tr>
				<tr>

					<td><input type="password" class="form-control"
						placeholder="비밀번호 확인" id="memberPasswordChk"
						name="memberPasswordChk" required>
					<div class="pwdChk"></div>	
					</td>
				</tr>
				<tr>

					<td><input type="text" class="form-control"
						placeholder="이름을 입력하세요. " id="memberName" name="memberName"
						required></td>
				</tr>
				<tr>

					<td>
					<div class="input-group mb-3">
					<input type="email" class="form-control"
						placeholder="이메일 주소를 입력하세요. " id="memberEmail" name="memberEmail"
						required>
						<input type="button" id='chkEmail' class="btn btn-outline-secondary"
								onclick="emailConfirm();" value="이메일 인증">	
					</div></td>
				</tr>
				<tr>


					<td>
					<input type="text" class="form-control" placeholder="주소를 입력하세요. " id="memberAddress" name="memberAddress">
					<div class="input-group mb-3">
					<input type="text" class="form-control"
						placeholder="상세주소를 입력하세요. " id="memberAddress1" name="memberAddress1">
						
					<div class="input-group-append">
						<input type="button" class="btn btn-outline-secondary"
								onclick="kakaoAddress()" value="주소 검색">
							</div>		
							</div>			

					</td>
				</tr>
				<tr>

					<td><input type="text" class="form-control" id="memberPhone"
						name="memberPhone" placeholder="핸드폰번호를 입력하세요.(-) 없이 입력 "></td>
				</tr>
				<tr>
					<td class="badge" id='genreAll'>선호 장르</td>
				</tr>
				<tr>
					<td>
					
					<div class="btn-group-toggle" data-toggle="buttons">
  					<label for="genre1" class="btn btn-outline-secondary" >드라마/ 가족 
					<input type="checkbox" name="genre" id="genre1"value="드라마"> 
					</label>
					<label for="genre2" class="btn btn-outline-secondary">로맨스
					<input type="checkbox" name="genre" id="genre2" value="로맨스"> 
					</label> 
					<label for="genre3" class="btn btn-outline-secondary">코미디
					<input type="checkbox" name="genre"	id="genre3" value="코미디"> 
					</label> 
					<label for="genre4" class="btn btn-outline-secondary">액션
					<input type="checkbox" name="genre" id="genre4" value="액션"> 
					</label> 
						<br /><br />
					<label for="genre5" class="btn btn-outline-secondary">SF/판타지
					<input type="checkbox" name="genre"id="genre5" value="SF"> 
					</label>
					<label for="genre6" class="btn btn-outline-secondary">모험
					<input type="checkbox" name="genre" id="genre6" value="모험">
					</label> 
					<label for="genre7" class="btn btn-outline-secondary">공포
					<input type="checkbox" name="genre" id="genre7" value="공포"> 
					</label> 
					<label for="genre8" class="btn btn-outline-secondary">애니메이션
					<input type="checkbox" name="genre" id="genre8" value="애니메이션"> 
					</label> 
						<br /><br />
					<label for="genre9" class="btn btn-outline-secondary">다큐멘터리
					<input type="checkbox" name="genre"	id="genre9" value="다큐멘터리"> 
					</label>
               		<label for="genre10" class="btn btn-outline-secondary">스릴러
					<input type="checkbox" name="스릴러" id="genre10" value="스릴러">
					</label>
            	 	<label for="genre11" class="btn btn-outline-secondary">범죄
					<input type="checkbox" name="범죄" id="genre11" value="범죄">
					</label>
               		</div>
					</td>
				</tr>
			</table>
			<div class='button1'>
			<br />
			<input type="submit" class="btn btn-outline-secondary button" value="회원가입">
			<input type="reset" class="btn btn-outline-secondary button" value="취소">
			
			</div>
			<br />
		</form>
<form action="" name="forEmail">
	<input type="hidden" name="memberEmail" />
</form>
<script>
$('#chkEmail').click(() =>{
    
    var url = "<%=request.getContextPath()%>/member/emailCheck";
    var title = "EmailCheck";
    var status =  "left=500px, top=200px, width=400px, height=210px";
        
    var popup = open("", title, status);
    
    var email = document.getElementById('memberEmail');
    //폼이랑 팝업연결
    var frm = document.forEmail;
    frm.memberEmail.value = email.value;
    frm.action = url;
    frm.target = title;
    frm.method = "post";
    frm.submit();

 });

</script>
	</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>