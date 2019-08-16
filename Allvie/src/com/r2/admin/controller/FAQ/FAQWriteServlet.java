package com.r2.admin.controller.FAQ;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.admin.model.service.FAQService;
import com.r2.admin.model.service.NoticeService;
import com.r2.admin.model.vo.FAQ;
import com.r2.admin.model.vo.Notice;

/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/admin/onlyAdmin/fAQ/writeFAQ")
public class FAQWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FAQWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fAQ_Category = request.getParameter("fAQ_Category");
		String fAQ_Title = request.getParameter("fAQ_Title");
		String fAQ_Content = request.getParameter("fAQ_Content");
		
		
		FAQ f = new FAQ();
		f.setFAQ_Category(fAQ_Category);
		f.setFAQ_Title(fAQ_Title);
		f.setFAQ_Content(fAQ_Content);
		
		int result = new FAQService().insertFAQ(f);
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/admin/fAQ/fAQList";


		if(result > 0) {
			msg = "FAQ를 성공적으로 등록했습니다.";
		}else {
			msg = "FAQ를 등록에 실패했습니다.";
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
