package com.r2.member.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.member.model.service.MemberService;
import com.r2.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrolledServlet
 */
@WebServlet("/member/memberEnrolled")
public class MemberEnrolledServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrolledServlet() {
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
		String mName = request.getParameter("memberName");
		String mEmail = request.getParameter("memberEmail");
		String mAddr = request.getParameter("memberAddress");
		String mPhone = request.getParameter("memberPhone");
		String[] Genre = request.getParameterValues("genre");
		String mGenre = "";
		
		if(Genre != null) {
			mGenre = String.join(", ", Genre);
		}
		
		Member m = new Member();
		m.setMemberId(mId);
		m.setMemberName(mName);
		m.setMemberPassword(mPwd);
		m.setMemberEmail(mEmail);
		m.setMemberAddress(mAddr);
		m.setMemberPhone(mPhone);
		m.setMemberFavoriteGenre(mGenre);
		
		int result = 0;
		
		System.out.println();
		
		result = new MemberService().insertMember(m);
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";

		String loc = "/";

		if(result>0)
			msg = "성공적으로 회원등록했습니다.";
		else 
			msg = "회원등록에 실패했습니다.";	
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
