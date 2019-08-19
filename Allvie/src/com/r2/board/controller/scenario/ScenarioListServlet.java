package com.r2.board.controller.scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.board.model.service.BoardService;
import com.r2.board.model.vo.FreeBoard;
import com.r2.board.model.vo.Scenario;



@WebServlet("/board/ScenarioBoard")
public class ScenarioListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScenarioListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		
		final int numPerPage = 5;//한페이지당 수
		int cPage = 1;//요청페이지
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(Exception e){
		}
			
			List<Scenario> list = new BoardService().selectScenarioBoardListAll(cPage, numPerPage);
			//게시판 글목록을 담을 list
			
			
			
			int totalBoardCount = new BoardService().selectScenarioBoardCount();
			//(공식2)전체페이지수 구하기
			int totalPage = (int)Math.ceil((double)totalBoardCount/numPerPage);
			
			//2.3 페이지바구성
			String pageBar = "";	
			int pageBarSize = 5;
			//(공식3)시작페이지 번호 세팅
			//cPage=5,pageBarSize=5 -> 1
			//cPage=6,pageBarSize=5 -> 6
			int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
			//종료페이지 번호 세팅
			int pageEnd = pageStart+pageBarSize-1;
			int pageNo = pageStart;
		
			//[이전] section
			if(pageNo == 1 ){
				//pageBar += "<span>[이전]</span>"; 
			}
			else {
				pageBar += "<a href='"+request.getContextPath()+"/board/ScenarioBoard?cPage="+(pageNo-1)+" '>[이전]</a> ";
			}
			
			// pageNo section
			// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
			while(!(pageNo>pageEnd || pageNo > totalPage)){
				
				if(cPage == pageNo ){
					pageBar += "<span class='cPage'>"+pageNo+"</span> ";
				} 
				else {
					pageBar += "<a class='cPage' href='"+request.getContextPath()+"/board/ScenarioBoard?cPage="+pageNo+"'>"+pageNo+"</a> ";
				}
				pageNo++;
			}
			
			//[다음] section
			if(pageNo > totalPage){
				//pageBar += "<span>[다음]</span>";
			} else {
				pageBar += "<a href='"+request.getContextPath()+"/board/ScenarioBoard?cPage="+pageNo+" >[다음]</a>";
			}
			
			
			
			
			
			request.setAttribute("list", list);
			request.setAttribute("pageBar",pageBar);
			
			request.getRequestDispatcher("/WEB-INF/views/board/scenarioboard/ScenarioList.jsp").forward(request, response);

	
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
