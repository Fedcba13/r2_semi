package com.r2.admin.controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.admin.model.service.AdminMemberService;
import com.r2.admin.model.service.NoticeService;
import com.r2.member.model.vo.Member;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/admin/onlyAdmin/member/memberList")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			
		}

		List<Member> memberList = new AdminMemberService().getrMemberList(cPage, numPerPage);
		int totalContents = new AdminMemberService().selectTotalMemberContents();
		int totalPage = (int) Math.ceil(totalContents / (double) numPerPage);

		final int pageBarSize = 5;
		String pageBar = "";

		int pageStart = ((cPage - 1) / (pageBarSize)) * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;

		int pageNo = pageStart;
		
		

		if (pageNo == 1) {
			pageBar += "<li class='page-item'><a class='page-link' href=''>Previous</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/onlyAdmin/member/memberList?cPage=" + (pageNo - 1)
					+ "&numPerPage=" + numPerPage + "'>Previous</a></li>";
		}
		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if (pageNo == cPage) {
				pageBar += "<li class='page-item'><a class='page-link' href=''>" + pageNo + "</a></li>";
			} else {
				pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/onlyAdmin/member/memberList?cPage=" + (pageNo)
						+ "&numPerPage=" + numPerPage + "'>" + pageNo + "</a></li>";
			}
			pageNo++;
		}
		if (pageNo > totalPage) {
			pageBar += "<li class='page-item'><a class='page-link' href=''>Next</a></li>";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/admin/onlyAdmin/member/memberList?cPage=" + (pageNo)
					+ "&numPerPage=" + numPerPage + "'>[다음]</a></li>";
		}

		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("memberList", memberList);
		request.getRequestDispatcher("/WEB-INF/views/admin/member/MemberList.jsp").forward(request, response);
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
