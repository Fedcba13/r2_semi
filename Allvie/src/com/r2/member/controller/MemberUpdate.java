package com.r2.member.controller;

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
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		String memberEmail = request.getParameter("memberEmail");
		String memberPhone = request.getParameter("memberPhone");
		String memberAddress = request.getParameter("memberAddress");
		String[] genres = request.getParameterValues("genre");
		
				
		String memberFavoriteGenre = "";
		
		if(genres!=null) memberFavoriteGenre = String.join(",", genres);
		
		Member m = new Member();
		m.setMemberId(memberId);
		m.setMemberName(memberName);
		m.setMemberEmail(memberEmail);
		m.setMemberPhone(memberPhone);
		m.setMemberAddress(memberAddress);
		m.setMemberFavoriteGenre(memberFavoriteGenre);
		
		int result = new MemberService().updateMember(m);
		
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";

		String loc = "/";

		if(result>0){
			msg = "성공적으로 회원정보를 수정했습니다.";
			loc = "/member/memberView";
			
			//갱신된 회원정보를 session에 반영한다.
			Member memberLoggedIn
				= (Member)request.getSession()
						 		 .getAttribute("memberLoggedIn");
			if(!"admin".equals(memberLoggedIn.getMemberId())) {
				request.getSession()
					   .setAttribute("memberLoggedIn", 
							   		 new MemberService().selectOne(memberId));
			}
		}
		else 
			msg = "회원정보수정에 실패했습니다.";	
		
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
