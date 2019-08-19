<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String loc = (String)request.getAttribute("loc");

	

	System.out.println("loc@msg.jsp="+loc);
	/* loc@msg.jsp=/board/freeBoardView?boardNo=125 */
%>
<script>


//dml요청시 반드시 페이지 이동시켜줄것.
location.href = "<%=request.getContextPath() + loc%>"; 
</script>