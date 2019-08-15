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
 * Servlet implementation class NoteListServlet
 */
@WebServlet("/admin/fAQ/fAQList")
public class FAQListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FAQListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
		
		// 1.파라미터핸들링
		int numPerPage = 5;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (NumberFormatException e) {

		}

		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));// NumberFormatException발생을 가정하라.
		} catch (NumberFormatException e) {
			// 예외발생하면 기본값 1을 가져다 씀으로 따로 예외처리 필요 없음.
		}

		List<FAQ> fAQList = new FAQService().getfAQList(cPage, numPerPage);
		int totalContents = new FAQService().selectTotalContents();
		System.out.println("컨텐츠 수 : " + totalContents);
		int totalPage = (int) Math.ceil(totalContents / (double) numPerPage);
		System.out.println("페이지 수 : " + totalPage);

		final int pageBarSize = 5;
		String pageBar = "";

		int pageStart = ((cPage - 1) / (pageBarSize)) * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;

		int pageNo = pageStart;

		
		if (pageNo == 1) {
			pageBar += "<li class='page-item'><a class='page-link' href=''>Previous</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/fAQfAQList?cPage=" + (pageNo - 1)
					+ "&numPerPage=" + numPerPage + "'>Previous</a></li>";
		}
		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if (pageNo == cPage) {
				pageBar += "<li class='page-item'><a class='page-link' href=''>" + pageNo + "</a></li>";
			} else {
				pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/fAQfAQList?cPage=" + (pageNo)
						+ "&numPerPage=" + numPerPage + "'>" + pageNo + "</a></li>";
			}
			pageNo++;
		}
		if (pageNo > totalPage) {
			pageBar += "<li class='page-item'><a class='page-link' href=''>Next</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/fAQfAQList?cPage=" + (pageNo)
					+ "&numPerPage=" + numPerPage +"'>Next</a></li>";
		}

		
		
		
		
		List<String> catList = new FAQService().getFAQCategory();
		
		
		request.setAttribute("fAQList", fAQList);
		request.setAttribute("catList", catList);
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		
	
		request.getRequestDispatcher("/WEB-INF/views/admin/FAQ/fAQList.jsp").forward(request, response);

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
