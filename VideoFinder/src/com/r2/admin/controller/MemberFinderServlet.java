package com.r2.admin.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.admin.model.service.AdminMemberService;
import com.r2.member.model.vo.Member;

/**
 * Servlet implementation class MemberFinderServlet
 */
@WebServlet("/admin/memberFinder")
public class MemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberFinderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		int totalContents = 0;
		
		
		
		String searchType = request.getParameter("searchType");
		List<Member> memberList = null;
		
		
		
		//가입 날짜를 조회시
		if("member_EnrollDate".equals(searchType)) {
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			
			Date searchKeyword = null;
			Date searchKeywordEnd = null;
			try {
				searchKeyword = transFormat.parse(request.getParameter("searchKeyword"));
				searchKeywordEnd = transFormat.parse(request.getParameter("searchKeywordEnd"));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			memberList = new AdminMemberService().selectMembersBySearchDate(searchKeyword, searchKeywordEnd);
			
			totalContents = new AdminMemberService().selectTotalSearchContentsDate(searchKeyword, searchKeywordEnd);
			
		}
		//그외
		else {
			String searchKeyword = request.getParameter("searchKeyword");
			
			memberList = new AdminMemberService().selectMembersBySearch(searchType, searchKeyword);
			
			totalContents = new AdminMemberService().selectTotalSearchContents(searchType, searchKeyword);
		}

		int totalPage = (int) Math.ceil(totalContents / (double) numPerPage);

		
		
// pageBar html 코드작성
		final int pageBarSize = 5;
		String pageBar = "";

		int pageStart = ((cPage - 1) / (pageBarSize)) * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;

		int pageNo = pageStart;

//		a.[이전]
		if (pageNo == 1) {
			pageBar += "<span>[이전]</span>";
		} else {
			pageBar += "<a href = '" + request.getContextPath() + "/admin/memberFinder?cPage=" + (pageNo - 1)
					+ "&numPerPage=" + numPerPage + "'>[이전]</a>";
		}
//		b.page
		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if (pageNo == cPage) {
				pageBar += "<span class = 'cPage'>" + pageNo + "</span>";
			} else {
				pageBar += "<a href = '" + request.getContextPath() + "/admin/memberFinder?cPage=" + (pageNo)
						+ "&numPerPage=" + numPerPage + "'>" + pageNo + "</a>";
			}
			pageNo++;
		}
//		c.[다음]
		if (pageNo > totalPage) {
			pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href = '" + request.getContextPath() + "/admin/memberFinder?cPage=" + (pageNo)
					+ "&numPerPage=" + numPerPage + "'>[다음]</a>";
		}

		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("memberList", memberList);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/member/memberFinder.jsp");
		reqDispatcher.forward(request, response);

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
