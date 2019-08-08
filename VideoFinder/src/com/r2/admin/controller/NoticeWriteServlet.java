package com.r2.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.admin.model.service.NoticeService;
import com.r2.admin.model.vo.Notice;

/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/admin/writeNotice")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
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
		
		System.out.println(notice_Content+"********");
		
		Notice n = new Notice();
		n.setNotice_Category(notice_Category);
		n.setNotice_Title(notice_Title);
		n.setNotice_Content(notice_Content);
		
		int result = new NoticeService().insertNotice(n);
		
		if(result > 0) {
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
