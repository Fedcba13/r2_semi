package com.r2.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.member.model.service.MemberService;
import com.r2.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdatePassword
 */
@WebServlet(urlPatterns="/member/updatePasswordEnd", name="MemberUpdatePassword")
public class MemberUpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memberId = request.getParameter("memberId");
		String memberPassword = request.getParameter("memberPassword");
		String newPwd = request.getParameter("newPassword");
		
		Member m = new Member();
		m.setMemberId(memberId);
		m.setMemberPassword(memberPassword);
		int result = new MemberService().loginCheck(m);
		
		String msg = "";
		String loc = "";
		String view = "/WEB-INF/views/common/msg.jsp";

		if(result == 1) {
			
			result = new MemberService().updatePwd(memberId, newPwd);
			
			if(result > 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("self.close();");
				out.print("opener.location.href = '"+request.getContextPath()+"/member/memberView';");
				out.print("opener.alert('비밀번호 변경이 완료되었습니다.');");
				out.print("</script>");
				
		
			}
			
		}else {
			
			msg = "현재 비밀번호가 일치하지 않습니다.";
			loc = "/member/memberView";
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			RequestDispatcher req = request.getRequestDispatcher(view);
			req.forward(request, response);
			
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
