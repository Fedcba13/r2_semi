<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>


//dml요청시 반드시 페이지 이동시켜줄것.

location.href = "${pageContext.request.contextPath.concat(loc)}"; 
</script>