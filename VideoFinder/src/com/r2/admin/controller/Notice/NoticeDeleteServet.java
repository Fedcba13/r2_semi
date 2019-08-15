package com.r2.admin.controller.Notice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.admin.model.service.FAQService;
import com.r2.admin.model.service.NoticeService;

/**
 * Servlet implementation class FAQDeleteServet
 */
@WebServlet("/admin/notice/deleteNotice")
public class NoticeDeleteServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_No = request.getParameter("notice_No");
		
		int result = new NoticeService().deleteNotice(notice_No);
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/admin/notice/noticeList";


		if(result > 0) {
			msg = "공지사항을 성공적으로 삭제했습니다.";
		}else {
			msg = "공지사항을 삭제에 실패했습니다.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher(view).forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
