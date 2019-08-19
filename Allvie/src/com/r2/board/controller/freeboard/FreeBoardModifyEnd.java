package com.r2.board.controller.freeboard;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.board.model.service.BoardService;
import com.r2.board.model.vo.FreeBoard;

/**
 * Servlet implementation class FreeBoardModifyEnd
 */
@WebServlet("/board/freeBaordUpdateEnd")
public class FreeBoardModifyEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FreeBoardModifyEnd() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		
		String loc= null;
		
		String boardNo = request.getParameter("boardNo");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		//freeboard jsp에서 넘어온 값을 freeboard 객체에 저장
		FreeBoard fb = new FreeBoard();
		
		fb.setFree_Board_No(boardNo);
		fb.setFree_Board_Title(title);
		fb.setFree_Board_Writer(writer);
		fb.setFree_Board_Content(content);
		
		int result = new BoardService().updateFreeBoard(fb);
		
		
		if(result<0) {
			System.out.println("수정 실패");
		}else {
			loc = "/board/freeBoardView?boardNo="+boardNo;
		}
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/board/freeboard/freeBoardDummy.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
