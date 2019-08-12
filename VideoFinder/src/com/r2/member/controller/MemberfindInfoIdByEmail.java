package com.r2.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.common.GmailSend;
import com.r2.member.model.service.MemberService;
import com.r2.member.model.vo.Member;

/**
 * Servlet implementation class MemberfindInfoIdByEmail
 */
@WebServlet("/member/findInfoIdByEmail")
public class MemberfindInfoIdByEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberfindInfoIdByEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memberName = request.getParameter("memberName_");
		String memberEmail = request.getParameter("findInfo_");
		
		Member m = new MemberService().selectOneByName(memberName);
		
		String msg = "";
		String loc = "";
		String view = "/WEB-INF/views/common/msg.jsp";
		
		if(memberEmail.equals(m.getMemberEmail())) {
			String mailContent = "안녕하세요. Allvie 입니다. \n"
					+ m.getMemberName() + "님의 ID는 아래와 같습니다. \n"
					+ "가입된 아이디 : " + m.getMemberId()
					+ "\n감사합니다."
					+ "\n 로그인을 하시려면 하단의 링크를 눌러주세요. "
					+ "localhost:9090/member/memberLogin";
			
			GmailSend mail = new GmailSend();
			mail.GmailSet(m.getMemberEmail(), "Allvie 아이디입니다.", mailContent);
		
			msg = "등록된 메일로 아이디가 발송되었습니다.";
			loc = "/index.jsp";
			
			
		} else {
			msg = "등록된 정보가 없습니다.";
			loc = "/member/findInfoId";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		RequestDispatcher req = request.getRequestDispatcher(view);
		req.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
