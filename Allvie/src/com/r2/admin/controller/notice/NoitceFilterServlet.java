package com.r2.admin.controller.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.admin.model.service.NoticeService;
import com.r2.admin.model.vo.Notice;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/admin/notice/noticeFilter")
public class NoitceFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoitceFilterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search_Keyword = request.getParameter("search_Keyword");
		String cat = request.getParameter("cat");
		
		
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
		
		List<Notice> notList = new NoticeService().getNotListByFilter(search_Keyword, cat, cPage, numPerPage);
		int totalContents = new NoticeService().getTotalContentsByFilter(search_Keyword, cat);

		
		int totalPage = (int) Math.ceil(totalContents / (double) numPerPage);

		
		// pageBar html 코드작성
		final int pageBarSize = 5;
		String pageBar = "";

		int pageStart = ((cPage - 1) / (pageBarSize)) * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;

		int pageNo = pageStart;


//		a.[이전]
		if (pageNo == 1) {
			pageBar += "<li class='page-item'><a class='page-link' href=''>Previous</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/notice/noticeFilter?cPage=" + (pageNo - 1)
					+ "&numPerPage=" + numPerPage + "&search_Keyword="+search_Keyword+"&cat="+cat+ "'>Previous</a></li>";
		}
//		b.page
		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if (pageNo == cPage) {
				pageBar += "<li class='page-item'><a class='page-link' href=''>" + pageNo + "</a></li>";
			} else {
				pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/notice/noticeFilter?cPage=" + (pageNo)
						+ "&numPerPage=" + numPerPage + "&search_Keyword="+search_Keyword+"&cat="+cat+  "'>" + pageNo + "</a></li>";
			}
			pageNo++;
		}
//		c.[다음]
		if (pageNo > totalPage) {
			pageBar += "<li class='page-item'><a class='page-link' href=''>Next</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/notice/noticeFilter?cPage=" + (pageNo)
					+ "&numPerPage=" + numPerPage +"&search_Keyword="+search_Keyword+"&cat="+cat+ "'>Next</a></li>";
		}
		
		
		List<String> catList = new NoticeService().getNoticeCategory();
		request.setAttribute("search_Keyword", search_Keyword);
		request.setAttribute("cat", cat);
		request.setAttribute("notList", notList);
		request.setAttribute("catList", catList);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.getRequestDispatcher("/WEB-INF/views/admin/notice/noticeListFinder.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
