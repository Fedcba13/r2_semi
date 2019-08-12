package com.r2.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.board.model.service.BoardService;
import com.r2.board.model.vo.FreeBoard;

/**
 * Servlet implementation class FreeBoardSrchServlet
 */
@WebServlet("/board/boardSrch")
public class FreeBoardSrchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FreeBoardSrchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int numPerPage = 4;//한페이지당 수
		int cPage = 1;//요청페이지
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(Exception e){

		}
		
		
		
		String keyword = request.getParameter("keyword");
		String srchType = request.getParameter("srchType");
		List<FreeBoard> flist = new BoardService().srchBoard(srchType, keyword, cPage, numPerPage);
		int totalBoardCount = new BoardService().srchBoardCountBy(srchType, keyword);
		int totalPage = (int)Math.ceil((double)totalBoardCount/numPerPage);
		
		System.out.println(totalBoardCount);
		
		
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
			pageBar += "<a href='"+request.getContextPath()+"/board/freeBoard?cPage="+(pageNo-1)+" '>[이전]</a> ";
		}
		
		
		while(!(pageNo>pageEnd || pageNo > totalPage)){
			
			if(cPage == pageNo ){
				pageBar += "<span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<a class='cPage' href='"+request.getContextPath()+"/board/boardSrch?srchType="+srchType+"&keyword="+keyword+"&cPage="+pageNo+"'>"+pageNo+"</a> ";
			}																							
			pageNo++;
		}
		
		//[다음] section
		if(pageNo > totalPage){
			//pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/board/freeBoard?cPage="+pageNo+" >[다음]</a>";
		}
		
		
		
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("srchType", srchType);
		request.setAttribute("flist", flist);
		request.setAttribute("keyword", keyword);

		request.getRequestDispatcher("/WEB-INF/views/board/freeboard/srchResult.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
