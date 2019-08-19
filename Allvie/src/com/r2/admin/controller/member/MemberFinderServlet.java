package com.r2.admin.controller.member;

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
@WebServlet("/admin/onlyAdmin/member/memberFinder")
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
		}
		
		int totalContents = 0;
		
		
		
		String searchType = request.getParameter("searchType");
		List<Member> memberList = null;
		
		String search_Keyword = null;
		Date search_KeywordStart = null;
		Date search_KeywordEnd = null;
		
		
		//가입 날짜를 조회시
		if("member_EnrollDate".equals(searchType)) {
			
			
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				/*
				 * name 값으로부터 날짜를 String으로 가져옴과 동시에 날짜형식으로 parse
				 */
				search_KeywordStart = transFormat.parse(request.getParameter("search_KeywordStart"));
				search_KeywordEnd = transFormat.parse(request.getParameter("search_KeywordEnd"));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			memberList = new AdminMemberService().selectMembersBySearchDate(search_KeywordStart, search_KeywordEnd, cPage, numPerPage);
			
			totalContents = new AdminMemberService().selectTotalSearchContentsDate(search_KeywordStart, search_KeywordEnd);
			
		}
		
		else if(searchType == null) {
			memberList = new AdminMemberService().getrMemberList(cPage, numPerPage);
			totalContents = new AdminMemberService().selectTotalMemberContents();
		}else {
			search_Keyword = request.getParameter("search_Keyword");
			
			memberList = new AdminMemberService().selectMembersBySearch(searchType, search_Keyword, cPage, numPerPage);
			
			totalContents = new AdminMemberService().selectTotalSearchContents(searchType, search_Keyword);
			
		}

		int totalPage = (int) Math.ceil(totalContents / (double) numPerPage);

		
		
		final int pageBarSize = 5;
		String pageBar = "";

		int pageStart = ((cPage - 1) / (pageBarSize)) * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;

		int pageNo = pageStart;


		if (pageNo == 1) {
			pageBar += "<li class='page-item'><a class='page-link' href=''>Previous</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/onlyAdmin/member/memberFinder?cPage=" + (pageNo - 1)
					+ "&numPerPage=" + numPerPage + "'>Previous</a></li>";
		}
		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if (pageNo == cPage) {
				pageBar += "<li class='page-item'><a class='page-link' href=''>" + pageNo + "</a></li>";
			} else {
				pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/onlyAdmin/member/memberFinder?cPage=" + (pageNo)
						+ "&numPerPage=" + numPerPage + "'>" + pageNo + "</a></li>";
			}
			pageNo++;
		}
		if (pageNo > totalPage) {
			pageBar += "<li class='page-item'><a class='page-link' href=''>Next</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/onlyAdmin/member/memberFinder?cPage=" + (pageNo)
					+ "&numPerPage=" + numPerPage + "'>[다음]</a></li>";
		}
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("memberList", memberList);
		request.setAttribute("search_Keyword", search_Keyword);
		request.setAttribute("search_KeywordStart", search_KeywordStart);
		request.setAttribute("search_KeywordEnd", search_KeywordEnd);
		request.setAttribute("searchType", searchType);
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
