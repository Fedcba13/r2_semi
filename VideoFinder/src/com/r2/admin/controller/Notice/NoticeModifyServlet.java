package com.r2.admin.controller.Notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.admin.model.service.NoticeService;
import com.r2.admin.model.vo.Notice;

/**
 * Servlet implementation class FAQModifyServlet
 */
@WebServlet("/admin/notice/modifyNotice")
public class NoticeModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_Category = request.getParameter("notice_Category");
		String notice_Title = request.getParameter("notice_Title");
		String notice_Content = request.getParameter("notice_Content");
		String notice_No= request.getParameter("notice_No");
		
		
		Notice n = new Notice();
		n.setNotice_Category(notice_Category);
		n.setNotice_Title(notice_Title);
		n.setNotice_Content(notice_Content);
		n.setNotice_No(notice_No);
		
		int result = new NoticeService().modifyNotice(n);
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/admin/notice/getNoticeByNo?Notice_No="+notice_No;


		if(result > 0) {
			msg = "공지사항을 성공적으로 수정했습니다.";
		}else {
			msg = "공지사항 수정 실패했습니다.";
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
