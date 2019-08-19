<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@page import="com.r2.board.model.vo.Scenario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String ctx = request.getContextPath();
	Scenario s = (Scenario)request.getAttribute("s");
	String scenarioNo = (String)request.getAttribute("scenarioNo");
%>
<style>
#main-content{
	text-align: center;
}

#title{
	margin-top: 30px;
}

#ir1{
	width: 700px;
	height: 500px;

}
img{
	max-width: 200px;
	max-height: 300px;
}
</style>
<script src="<%=request.getContextPath()%>/js/freeboard_bootstrap_js/bootstrap.js"></script> <!-- 부트스트랩 기본 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/freeboard_bootstrap_css/bootstrap.css"> <!-- 부트스트랩 기본 -->
<script type="text/javascript" src="<%=ctx %>/SE2/js/HuskyEZCreator.js" charset="utf-8"></script> 
<script src="<%=ctx%>/js/freeboard_bootstrap_js/jquery-3.4.1.js"></script> <!-- jquery  -->
<script type="text/javascript"> 
var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "ir1",
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "<%=ctx%>/SE2/SmartEditor2Skin.html",  
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,         
              fOnBeforeUnload : function(){
                   
              }
          }, 
          fOnAppLoad : function(){
              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
              oEditors.getById["ir1"].exec("PASTE_HTML", [""]);
          },
          fCreator: "createSEditor2"
      });
      
      //저장버튼 클릭시 form 전송
      $("#save").click(function(){
          oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
          $("#frm").submit();
      });    
});



</script>

</head>
<body id="main-content">
<form id="frm" action="<%=ctx%>/board/scenarioModifyEnd" method="get" >
<table width="100%">
        <tr>

            <td><input type="text" id="title" name="title" style="width:650px" placeholder="게시물 제목을 입력하세요" class="form-control form-control-lg" value="<%=s.getTitle()%>"></td>
        </tr>
        <tr>

            <td>
                <textarea rows="10" cols="30" id="ir1" name="content" style="width:650px; height:800px; ">
					<%=s.getScenario_Content() %>
                </textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input hidden="hidden" value="abc" name="writer">
                <input hidden="hidden" value="<%=scenarioNo%>" name="scenarioNo">
                <input type="submit" id="save" value="작성하기"/>
                <input type="button" value="취소"  id="cancel"/>
                
            </td>
        </tr>
</table>
</form>

</body>
<script>

$("#cancel").click(function () {
	

	location.href 
	= "<%=request.getContextPath()%>/board/ScenarioBoard";	
});




</script>


</html>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
