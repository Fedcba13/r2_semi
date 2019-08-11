package com.r2.admin.controller.FAQ;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.admin.model.service.UnionService;
import com.r2.admin.model.vo.Notice;

/**
 * Servlet implementation class FAQListBySearchServlet
 */
@WebServlet("/union/getUnionList")
public class FAQListBySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FAQListBySearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String search_Keyword = request.getParameter("search_Keyword");
		
		List<Notice> unionList = new UnionService().getUnionSearchList(search_Keyword);
		
		request.setAttribute("unionList", unionList);
		request.setAttribute("search_Keyword", search_Keyword);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/service/unionSearchList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
