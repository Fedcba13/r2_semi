package com.r2.admin.controller;

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
 * Servlet implementation class FAQListServlet
 */
@WebServlet("/admin/getServiceList")
public class ServiceListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//FAQ(+카테고리 수) 가져오기
		List<FAQ> fAQList = new FAQService().getFAQList();
		int category = new FAQService().getCategoryCount();
		
		
		request.setAttribute("fAQList", fAQList);
		request.setAttribute("category", category);
		request.getRequestDispatcher("/WEB-INF/views/service/ServiceMain.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
