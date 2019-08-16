package com.r2.board.controller;

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

	
		
		
		
		
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");

	
				//Secure-Coding
				//XSS Cross-site Scripting:
				//사용자 작성 내용이 필터링 없이 db에 저장될 경우
				//개인정보 탈취 및 보안상 여러 위험을 야기할 수 있다.
				String content = request.getParameter("freeboardcontent");
				System.out.println(content+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
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
