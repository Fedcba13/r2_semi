<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="com.r2.admin.model.vo.FAQ"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<FAQ> fAQList = (List)request.getAttribute("fAQList");
	int category = (int)(request.getAttribute("category"));
	
%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/admin.css">
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
		color: red;
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
	<form action="<%=request.getContextPath()%>/union/getUnionList"
		method="POST" onkeydown="return captureReturnKey(event)">
		<input type="text" name="search_Keyword" id="searchInput"
			placeholder="어떤 도움이 필요하세요?">
		<button type="submit" id="searchFin">검색</button>
	<ul class="list-group" id="list-group">

	</ul>
</form>
<div id="fAQ_Area">
	<div id="div_01">
		<h2>축구</h2>
	</div>
	<div id="div_02">
		<h2>체육관</h2>
	</div>
	<div id="div_03">
		<h2>세번쨰 범주</h2>
	</div>
</div>



<script>
	function captureReturnKey(e){
		 if(e.keyCode==13)
			return false;
	}
	
<%-- 	function goBoardBylist-group(Notice_No){
		var go = "<%=request.getContextPath()%>/admin/searchBylist-group?Notice_No="+NoticeNo;
	 	location.href= go;
	}
	 --%>
	 function kk(){
		   <%
		   		for(FAQ f : fAQList){
		   			if("가입 절차".equals(f.getFAQ_Category())){
	   	
		   %>1
			var html = "";				   
			html += '<a href="<%=request.getContextPath()%>/admin/getFAQByNo?FAQ_No=<%=f.getFAQ_No()%>"><%=f.getFAQ_Title()%></a>';
			html += "<br>";
			$("#div_01").append(html);
		   <%
		   			}else if("내 계정 관리".equals(f.getFAQ_Category())){
		   %>
		   var html = "";				   
		   html += '<a href="<%=request.getContextPath()%>/admin/getFAQByNo?FAQ_No=<%=f.getFAQ_No()%>"><%=f.getFAQ_Title()%></a>';
			html += "<br>";
			$("#div_02").append(html);
		   
		   <%
		   			}else if("빠른 링크".equals(f.getFAQ_Category())){
		   %>
		   	var html = "";				   
		   	html += '<a href="<%=request.getContextPath()%>/admin/getFAQByNo?FAQ_No=<%=f.getFAQ_No()%>"><%=f.getFAQ_Title()%></a>';
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
	/* 			 $(e.target).val($sel.text()); 
				$("#list-group").hide().children().remove(); */
				var valArr = $sel.text().split('<');
				 $("#searchInput").val(valArr[0]);
				$("#list-group").hide().children().remove();
				
				$("#searchFin").click();
				
			}else{
				var searchKeyword = $(e.target).val();
				//사용자 입력값이 없다 => 조기리턴
				if (searchKeyword.trim().length == 0){
					return;
				}
				$.ajax({
					url: "<%=request.getContextPath()%>/admin/noticeSrch/csv/autoComplete.do",
					type: "post",
					data: "searchKeyword="+searchKeyword,
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
								name =  n.Notice_Title.replace(searchKeyword, "<span class='srchVal'>"+searchKeyword+"</span>");
							
								
		
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
							/* $("#searchKeyword").val($(e.target).text());
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