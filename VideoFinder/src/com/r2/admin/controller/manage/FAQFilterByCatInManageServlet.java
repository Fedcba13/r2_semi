package com.r2.admin.controller.manage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.admin.model.service.FAQService;
import com.r2.admin.model.service.NoticeService;
import com.r2.admin.model.vo.FAQ;

/**
 * Servlet implementation class NoticeFilterByCatServlet
 */
@WebServlet("/admin/manage/filterFAQByCat")
public class FAQFilterByCatInManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FAQFilterByCatInManageServlet() {
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

		// 2.업무로직
		// 2.1 컨텐츠 영역
		String cat = request.getParameter("cat");
		
		List<FAQ> fAQList = new FAQService().getFAQListByCat(cat, cPage, numPerPage);
//				2.2 페이지바 영역
//				페이지 바 구하기 공식2
//				전체 페이지 수 구하기 
		int totalContents = new FAQService().selectTotalContentsByCat(cat);
		int totalPage = (int) Math.ceil(totalContents / (double) numPerPage);
	

		// pageBar html 코드작성
		final int pageBarSize = 5;
		String pageBar = "";

		int pageStart = ((cPage - 1) / (pageBarSize)) * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;

		int pageNo = pageStart;
		

//				a.[이전]
		if (pageNo == 1) {
			pageBar += "<li class='page-item'><a class='page-link' href=''>이전</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/manage/filterFAQByCat?cPage=" + (pageNo - 1)
					+ "&numPerPage=" + numPerPage +"&cat="+cat+ "'>이전</a></li>";
		}
//				b.page
		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if (pageNo == cPage) {
				pageBar += "<li class='page-item'><a class='page-link' href=''>" + pageNo + "</a></li>";
			} else {
				pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/manage/filterFAQByCat?cPage=" + (pageNo)
						+ "&numPerPage=" + numPerPage +"&cat="+cat+ "'>" + pageNo + "</a></li>";
			}
			pageNo++;
		}
//				c.[다음]
		if (pageNo > totalPage) {
			pageBar += "<li class='page-item'><a class='page-link' href=''>다음</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/manage/filterFAQByCat?cPage=" + (pageNo) + "&numPerPage="
					+ numPerPage +"&cat="+cat+ "'>다음</a></li>";
		}
		
		
		//공지 카테고리 불러오기
		
		List<String> catList = new FAQService().getNoticeCategory();
		
		request.setAttribute("fAQList", fAQList);
		request.setAttribute("catList", catList);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.getRequestDispatcher("/WEB-INF/views/admin/manage/manageFAQ.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
