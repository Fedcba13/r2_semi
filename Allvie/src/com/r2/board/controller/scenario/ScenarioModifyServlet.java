package com.r2.board.controller.scenario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.board.model.service.BoardService;
import com.r2.board.model.vo.FreeBoard;
import com.r2.board.model.vo.Scenario;

/**
 * Servlet implementation class ModifyFreeBoard
 */
@WebServlet("/board/modifyScenarioBoard")
public class ScenarioModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScenarioModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		
		String scenarioNo = request.getParameter("boardNo");
		boolean tr = true;
		
		Scenario s = new BoardService().selectScenarioOne(scenarioNo, tr);
		
		request.setAttribute("scenarioNo", scenarioNo);
		request.setAttribute("s", s);
		request.getRequestDispatcher("/WEB-INF/views/board/scenarioboard/scnarioModify.jsp").forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
