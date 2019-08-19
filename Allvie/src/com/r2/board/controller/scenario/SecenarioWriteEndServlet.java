package com.r2.board.controller.scenario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.board.model.service.BoardService;
import com.r2.board.model.vo.Scenario;

/**
 * Servlet implementation class SecenarioWriteServletEnd
 */
@WebServlet("/board/scenarioWriteEnd")
public class SecenarioWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecenarioWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		/*
		 * content = content.replaceAll("<", "&lt;") .replaceAll(">", "&gt;");
		 */

		
		
		Scenario s = new Scenario();
		s.setMember_Id(writer);
		s.setTitle(title);
		s.setScenario_Content(content);
		
		
		int result = new BoardService().insertScenarioBoard(s);
		

		String msg = "";
		String loc = "/board/scenarioBoardList";

		if(result>0) {
			msg = "게시글 등록성공!";
			loc = "/board/scenarioView?boardNo="+"sce_"+result;
		}
		else {
			msg = "게시글 등록실패!";
		}
		
		//3.view단 처리
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
