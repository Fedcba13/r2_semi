package com.r2.board.controller.scenario;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.board.model.service.BoardService;
import com.r2.board.model.vo.BoardComment;

/**
 * Servlet implementation class FreeBoardCommentInsert
 */
@WebServlet("/board/scenarioBoardCommentInsert")
public class ScenarioCommentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ScenarioCommentInsert() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String boardCommentWriter= request.getParameter("boardCommentWriter");
		int boardCommentLevel = Integer.parseInt(request.getParameter("boardCommentLevel"));
		String boardCommentContent= request.getParameter("boardCommentContent");
		String boardRef = request.getParameter("boardRef");
//		int boardCommentRef = Integer.parseInt(request.getParameter("boardCommentRef")); 
		String boardCommentRef = request.getParameter("boardCommentRef"); 

		BoardComment bc = new BoardComment();
		
		//comment_no, comment date 생략(seq, sysdate로 자동 지정)
		bc.setBoard_Comment_Writer(boardCommentWriter);
		bc.setBoard_Comment_Content(boardCommentContent);
		bc.setBoard_Comment_Level(boardCommentLevel);
		bc.setBoard_Ref(boardRef);
		bc.setBoard_Comment_Ref(boardCommentRef);
		
		int result = new BoardService().insertScenarioBoardComment(bc);
		if(result<1) {
			System.out.println("처리실패");
			
		}
		String loc = "/board/scenarioView?boardNo="+boardRef;	
		
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/board/scenarioboard/scenarioDummy.jsp").forward(request, response);
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
