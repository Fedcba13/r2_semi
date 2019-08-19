package com.r2.board.controller.scenario;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.board.model.service.BoardService;
import com.r2.board.model.vo.FreeBoard;
import com.r2.board.model.vo.Scenario;

/**
 * Servlet implementation class FreeBoardModifyEnd
 */
@WebServlet("/board/scenarioModifyEnd")
public class ScenarioModifyEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ScenarioModifyEndServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		
		String loc= null;
		
		String scenarioNo = request.getParameter("scenarioNo");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		//freeboard jsp에서 넘어온 값을 freeboard 객체에 저장
		Scenario s = new Scenario();
		
		s.setScenario_No(scenarioNo);
		s.setTitle(title);
		s.setMember_Id(writer);
		s.setScenario_Content(content);
		
		int result = new BoardService().updateScenarioBoard(s);
		
		
		if(result<0) {
			System.out.println("수정 실패");
		}else {
			loc = "/board/scenarioView?boardNo="+scenarioNo;
		}
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/board/scenarioboard/scenarioDummy.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
