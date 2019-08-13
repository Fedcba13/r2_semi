package com.r2.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.r2.member.model.service.MemberService;
import com.r2.member.model.vo.Member;

/**
 * Servlet implementation class MemberLongin
 */
@WebServlet("/member/memberLoginEnd")
public class MemberLongin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberLongin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String mId = request.getParameter("memberId");
		String mPwd = request.getParameter("memberPassword");

		String saveId = request.getParameter("id-save");

//		HttpSession newSession = request.getSession();
//		
//		Member member = (Member) newSession.getAttribute("memberLoggedIn");
//		
//		if(member != null) {
//			
//			if(!member.getMemberId().isEmpty()) {
//				System.out.println(member.getMemberId());
//			}
//		}

		Member m = new Member();
		m.setMemberId(mId);
		m.setMemberPassword(mPwd);

		// System.out.println(m);
		int result = new MemberService().deleteChk(m);

		String view = "";
		String loc = "/";
		String msg = "";
		
		if (result == 4) {
			
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "탈퇴한 회원입니다.";
			loc = "/member/memberLogin";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);

			RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
			reqDispatcher.forward(request, response);
		} else {
			result = new MemberService().loginCheck(m);

//		String referer = request.getHeader("Referer");
//		String origin = request.getHeader("Origin");
//		String url = request.getRequestURL().toString();
//		String uri = request.getRequestURI();
//		
//		int idx = referer.indexOf(request.getContextPath()) + request.getContextPath().length();
//		loc = referer.substring(idx);

			// System.out.println("loc : " + loc);

			if (result == 1) {
				view = "/index.jsp";
				Member memberLoggedIn = new MemberService().selectOne(mId);

				HttpSession session = request.getSession();

				session.setMaxInactiveInterval(60 * 60);
				session.setAttribute("memberLoggedIn", memberLoggedIn);

				if (saveId != null) {
					Cookie c = new Cookie("saveId", mId);
					c.setMaxAge(7 * 24 * 60 * 60);
					c.setPath("/");
					response.addCookie(c);
				} else {
					Cookie c = new Cookie("saveId", mId);
					c.setMaxAge(0);
					c.setPath("/");
					response.addCookie(c);
				}

				RequestDispatcher req = request.getRequestDispatcher(view);
				req.forward(request, response);

			} else {
				view = "/WEB-INF/views/common/msg.jsp";

				if (result == 0) {
					msg = "비밀번호가 틀렷습니다. ";
				} else if (result == -1) {
					msg = "아이디가 존재하지 않습니다. ";
				}

				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);

				RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
				reqDispatcher.forward(request, response);
			}
		}

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
