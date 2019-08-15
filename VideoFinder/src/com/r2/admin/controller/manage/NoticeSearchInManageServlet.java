package com.r2.admin.controller.manage;

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
 * Servlet implementation class NoticeSearchServlet
 */
@WebServlet("/admin/manage/searchNotice")
public class NoticeSearchInManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSearchInManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("********************************");
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
				String search_Keyword = request.getParameter("search_Keyword");
				// 2.업무로직
				// 2.1 컨텐츠 영역
				List<Notice> notList = new NoticeService().getNoticeBySearch(search_Keyword, cPage, numPerPage);
//						2.2 페이지바 영역
//						페이지 바 구하기 공식2
//						전체 페이지 수 구하기 
				int totalContents = new NoticeService().selectTotalContentsBySearch(search_Keyword);
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
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/getNoticeOfAllBoardList?cPage=" + (pageNo - 1)
					+ "&numPerPage=" + numPerPage + "'>이전</a></li>";
		}
//				b.page
		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if (pageNo == cPage) {
				pageBar += "<li class='page-item'><a class='page-link' href=''>" + pageNo + "</a></li>";
			} else {
				pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/getNoticeOfAllBoardList?cPage=" + (pageNo)
						+ "&numPerPage=" + numPerPage + "'>" + pageNo + "</a></li>";
			}
			pageNo++;
		}
//				c.[다음]
		if (pageNo > totalPage) {
			pageBar += "<li class='page-item'><a class='page-link' href=''>다음</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/getNoticeOfAllBoardList?cPage=" + (pageNo)
					+ "&numPerPage=" + numPerPage + "'>다음</a></li>";
		}
				
				

//						a.[이전]
				if (pageNo == 1) {
					pageBar += "<li class='page-item'><a class='page-link' href=''>이전</a></li>";
				} else {
					pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/manage/searchNotice?cPage=" + (pageNo - 1)
							+ "&numPerPage=" + numPerPage + "&search_Keyword="+ search_Keyword+    "'>이전</a></li>";
				}
//						b.page
				while (pageNo <= pageEnd && pageNo <= totalPage) {
					if (pageNo == cPage) {
						pageBar += "<li class='page-item'><a class='page-link' href=''>" + pageNo + "</a></li>";
					} else {
						pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/manage/searchNotice?cPage=" + (pageNo)
								+ "&numPerPage=" + numPerPage + "&search_Keyword="+ search_Keyword+   "'>" + pageNo + "</a></li>";
					}
					pageNo++;
				}
//						c.[다음]
				if (pageNo > totalPage) {
					pageBar += "<li class='page-item'><a class='page-link' href=''>다음</a></li>";
				} else {
					pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/manage/searchNotice?cPage=" + (pageNo) + "&numPerPage="
							+ numPerPage + "&search_Keyword="+ search_Keyword+   "'>다음</a></li>";
				}
				
				
				//공지 카테고리 불러오기
				
				List<String> catList = new NoticeService().getNoticeCategory();
				
				
				request.setAttribute("notList", notList);
				request.setAttribute("catList", catList);
				request.setAttribute("search_Keyword", search_Keyword);
				request.setAttribute("pageBar", pageBar);
				request.setAttribute("cPage", cPage);
				request.setAttribute("numPerPage", numPerPage);
				request.getRequestDispatcher("/WEB-INF/views/admin/manage/noticeFinder.jsp").forward(request, response);

				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
