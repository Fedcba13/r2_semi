package com.r2.admin.controller.FAQ;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.admin.model.service.FAQService;
import com.r2.admin.model.vo.FAQ;

/**
 * Servlet implementation class FAQByFAQNoServlet
 */
@WebServlet("/admin/fAQ/goModify")
public class FAQModifyBeforeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FAQModifyBeforeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String FAQ_No = request.getParameter("fAQ_No");

		FAQ f = new FAQService().getFAQByFAQNo(FAQ_No);
		List<String> catList = new FAQService().getFAQCategory();
		String modCon = f.getFAQ_Content().replaceAll("<br />", "&#10;");

		request.setAttribute("modCon", modCon);
		request.setAttribute("f", f);
		request.setAttribute("catList", catList);
		request.getRequestDispatcher("/WEB-INF/views/admin/FAQ/modifyFAQ.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
