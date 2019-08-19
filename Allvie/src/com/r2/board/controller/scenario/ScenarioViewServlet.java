package com.r2.board.controller.scenario;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.board.model.service.BoardService;
import com.r2.board.model.vo.BoardComment;
import com.r2.board.model.vo.FreeBoard;
import com.r2.board.model.vo.Scenario;

/**
 * Servlet implementation class FreeBoardViewServlet
 */
@WebServlet("/board/scenarioView")
public class ScenarioViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScenarioViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		
		
		Scenario s = null;	
		
		//게시물 번호 가져오기
		String scenarioNo = (String)request.getParameter("boardNo");
		
		
		
				//쿠키검사
				Cookie[] cookies = request.getCookies();
				String boardCookieVal = "";
				boolean hasRead = false;
				if(cookies != null) {
					for(Cookie c: cookies) {
						String name = c.getName();
						String value = c.getValue();
						if("boardCookie".equals(name)) {
							boardCookieVal = value;
							if(value.contains("|"+scenarioNo+"|")) {
								hasRead = true;
								break;
							}
						}
					}
				}
		
				

				//현재 게시글을 최초로 읽는다면.   
				if(!hasRead) {
					Cookie boardCookie 
						= new Cookie("boardCookie", boardCookieVal+"|"+scenarioNo+"|");
					//setMaxAge를 생략하면, 영속한다.
					boardCookie.setPath(request.getContextPath()+"/board");
					
					response.addCookie(boardCookie);
				}
				
				
				
				//게시물 가져오기
				s = new BoardService().selectScenarioOne(scenarioNo, hasRead);	

				
				//댓글 가져오기
				List<BoardComment> bclist = new BoardService().selectCommentListByBoardNo(scenarioNo);
				
						
				request.setAttribute("s", s);
				request.setAttribute("bclist", bclist);
				
				String view = "/WEB-INF/views/board/boardView.jsp";
				
				//게시글 가져오기에 실패한경우
				if(s == null){
					request.setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
					request.setAttribute("loc", "/board/ScenarioBoard");
					view = "/WEB-INF/views/common/msg.jsp";
					System.out.println("게시물 가져오기 실패");
				}
				
				
				
				
				
				request.getRequestDispatcher("/WEB-INF/views/board/scenarioboard/scenarioView.jsp")
					   .forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
