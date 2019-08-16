package com.r2.admin.controller.FAQ;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.admin.model.service.FAQService;

/**
 * Servlet implementation class FAQDeleteServet
 */
@WebServlet("/admin/onlyAdmin/fAQ/deleteFAQ")
public class FAQDeleteServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FAQDeleteServet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String FAQ_No = request.getParameter("FAQ_No");
		
		int result = new FAQService().deleteFAQ(FAQ_No);
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/admin/fAQ/fAQList";


		if(result > 0) {
			msg = "FAQ를 성공적으로 삭제했습니다.";
		}else {
			msg = "FAQ를 삭제에 실패했습니다.";
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
