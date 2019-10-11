<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="com.r2.admin.model.vo.FAQ"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<FAQ> fAQList = (List)request.getAttribute("fAQList");
	int category = (int)(request.getAttribute("category"));
	
%>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
<style>
#searchInput {
	width: 800px;
}

div {
	display: inline-block;
}
	#list-group{
		backgournd-color: blue;
		text-align: left;
		padding: 0 44px;
		list-style: none;
		cursor: pointer;
		width: 889px; 
		margin : 0 0 0 40px;
		position: absolute;
	}
	#list-group li.sel{
		background: lightseagreen;
		color: white;
	}

	span.srchVal{
		color: #f0522a;
	}
	#list-group li{
	 height: 10px;
	}

	#fAQ_Area div{
		padding: 40px 0 0 0;
		vertical-align: top;
	}
	a{
	 color: black;
	 text-decoration: none;
	}
	#fAQ_Area div{
		text-align: lefth;
	}

	

</style>

	<h1>고객센터</h1>
	<br />
	<form action="${pageContext.request.contextPath}/union/getUnionList"
		method="POST" onkeydown="return captureReturnKey(event)">
		<input type="text" name="search_Keyword" id="searchInput"
			placeholder="어떤 도움이 필요하세요?">
		<button type="submit" id="searchFin">검색</button>
	<ul class="list-group" id="list-group">

	</ul>
</form>
<div id="fAQ_Area" style="width: 700px;">
	<div id="div_01">
		<h2>가입 절차</h2>
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;
	<div id="div_02">
		<h2>내 계정 관리</h2>
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;
	<div id="div_03">
		<h2>서비스/콘텐츠</h2>
	</div>
</div>



<script>
	function captureReturnKey(e){
		 if(e.keyCode==13)
			return false;
	}
	
	 function kk(){
		   <%
		   		for(FAQ f : fAQList){
		   			if("가입 절차".equals(f.getFAQ_Category())){
	   	
		   %>1
			var html = "";				   
			html += '<a href="${pageContext.request.contextPath}/admin/FAQ/getFAQByNo?FAQ_No=<%=f.getFAQ_No()%>"><%=f.getFAQ_Title()%></a>';
			html += "<br>";
			$("#div_01").append(html);
		   <%
		   			}else if("내 계정 관리".equals(f.getFAQ_Category())){
		   %>
		   var html = "";				   
		   html += '<a href="${pageContext.request.contextPath}/admin/FAQ/getFAQByNo?FAQ_No=<%=f.getFAQ_No()%>"><%=f.getFAQ_Title()%></a>';
			html += "<br>";
			$("#div_02").append(html);
		   
		   <%
		   			}else if("서비스/콘텐츠".equals(f.getFAQ_Category())){
		   %>
		   	var html = "";				   
		   	html += '<a href="${pageContext.request.contextPath}/admin/FAQ/getFAQByNo?FAQ_No=<%=f.getFAQ_No()%>"><%=f.getFAQ_Title()%></a>';
			html += "<br>";
			$("#div_03").append(html);
		   <%}
			}%>
	   
 };
 window.onload=kk;
	
	$(()=>{
		
		
		
		$("#searchInput").keyup(e => {
			
			var $sel = $(".sel");
			var $li = $("#list-group li");
			
			if(e.key == "ArrowDown"){
				if($sel.length == 0){
					$li.eq(0).addClass("sel");
				}else if($sel.is($li.last())){
					
				}else{
					$sel.removeClass("sel").next().addClass("sel");
				}
			}else if(e.key == "ArrowUp"){
				console.log("ArrowUp");
				if($sel.length == 0){

				}else if($sel.is($li.first())){
					$sel.removeClass("sel");
				}else{
					$sel.removeClass("sel").prev().addClass("sel");
				}
				
			}else if(e.key == "Enter"){

								
				//그냥 검색어를 엔터했을때
				if($sel.text() == ''){
				 $("#searchFin").click(); 
					
				}
					
				//리스트에서 엔터를 눌렀을 떄 
				else{
				var valArr = $sel.text().split('<');
				$("#searchInput").val(valArr[0]);
				$("#list-group").hide().children().remove();
				 $("#searchFin").click(); 
					
				}
				
			}else{
				var search_Keyword = $(e.target).val();
				//사용자 입력값이 없다 => 조기리턴
				if (search_Keyword.trim().length == 0){
					return;
				}
				$.ajax({
					url: "${pageContext.request.contextPath}/admin/noticeSrch/csv/autoComplete.do",
					type: "post",
					data: "search_Keyword="+search_Keyword,
					dataType: "json",
					
					success: function(data){
						if(data === null){
							$("#list-group").hide();	
						}else{
							var html = "";
							
							$.each(data, (i, n)=>{
 								 var cat = '';
								if('NOT' === n.Notice_No.substring(0, 3) ){
									var cat = '&lt;공지사항&gt;';
								}else if('FAQ' === n.Notice_No.substring(0, 3) ){
									cat = '&lt;FAQ&gt;';
								} 
								name =  n.Notice_Title.replace(search_Keyword, "<span class='srchVal'>"+search_Keyword+"</span>");
								
		
								html += "<li class='list-group-item d-flex justify-content-between align-items-center'>";
								html += name;
								html += "<span class='badge badge-primary badge-pill'>";
								html += cat;
								html += "</span></li>";

/* 						html += "<li>";
								html += name;
								html += cat;
 								html += "</li>";
 								 */
 								
							});
							$("#list-group").html(html);
							
						}
						//마우스 이벤트 핸들러 추가
						$("#list-group li").click(e=>{
							/* $("#search_Keyword").val($(e.target).text());
							$("#list-group").hide().children().remove(); */
							$("#list-group").hide().children().remove();
							var valArr = $(e.target).text().split('<');
							 $("#searchInput").val(valArr[0]);
							/* $("#searchInput").val($(e.target).text());  */
							$("#searchFin").click();
						}).hover(e=>{
							$(e.target).addClass("sel").siblings().removeClass("sel");
						}, e=>{
							$(e.target).removeClass("sel");
						});
						
						
					},
					error: function(jqxhr, textStatus, errorThrown){
						console.log("ajax 처리 실패");
						console.log(jqxhr, textStatus, errorThrown);
					}
				});
			
			}
			
		});
		
	});
		   
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>