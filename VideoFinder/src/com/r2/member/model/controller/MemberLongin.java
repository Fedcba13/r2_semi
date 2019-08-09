package com.r2.member.model.controller;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("memberId");
		String mPwd = request.getParameter("memberPassword");
		
		String saveId = request.getParameter("id-save");
		
		System.out.println(saveId);
		
		Member m = new Member();
		m.setMemberId(mId);
		m.setMemberPassword(mPwd);
		
		int result = new MemberService().loginCheck(m);
		
		String view = "";
		String loc = "/";
		
		if(result == 1 ) {
			view = "/index.jsp";
			Member memberLoggedIn = new MemberService().selectOne(mId);
			
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*60);
			session.setAttribute("memberLoggedIn", memberLoggedIn);
			
			if(saveId != null) {
				Cookie c = new Cookie("saveId", mId);
				c.setMaxAge(7*24*60*60);
				c.setPath("/");
				response.addCookie(c);
			} else {
				Cookie c = new Cookie("saveId", mId);
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
			}
			
			RequestDispatcher req= request.getRequestDispatcher(view);
			req.forward(request, response);
			
		} else {
			view = "/WEB-INF/views/common/msg.jsp";
			String msg = "";
			if(result == 0) {
				msg = "비밀번호가 틀렷습니다. ";
			} else if(result == -1) {
				msg = "아이디가 존재하지 않습니다. ";
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		
			RequestDispatcher reqDispatcher
			= request.getRequestDispatcher(view);
			reqDispatcher.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
