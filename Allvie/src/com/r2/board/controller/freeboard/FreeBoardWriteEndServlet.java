package com.r2.board.controller.freeboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.board.model.service.BoardService;
import com.r2.board.model.vo.FreeBoard;


/**
 * Servlet implementation class FreeBoardWriteEndServlet
 */
@WebServlet("/board/freeBoardWriteEnd")
public class FreeBoardWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FreeBoardWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");

	
		
		
		
				//파라미터 가져오기
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String content = request.getParameter("freeboardcontent");
				//보안상 태그를 그대로 저장하는것은 문제가 있으므로 태그 특수문자 변환
				content = content.replaceAll("<", "&lt;")
										   .replaceAll(">", "&gt;");
				
				FreeBoard fb = new FreeBoard();
				fb.setFree_Board_Title(title);
				fb.setFree_Board_Writer(writer);
				fb.setFree_Board_Content(content);

				
				//2.업무로직
				int result = new BoardService().insertBoard(fb);
				
				String msg = "";
				String loc = "/board/boardList";

				if(result>0) {
					msg = "게시글 등록성공!";
					loc = "/board/freeBoardView?boardNo="+"fre_"+result;
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
