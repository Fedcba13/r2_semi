package com.r2.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.common.mail.GmailSend;
import com.r2.member.model.service.MemberService;

/**
 * Servlet implementation class MemberEmailCheck
 */
@WebServlet("/member/emailCheckDo")
public class MemberEmailCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEmailCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("memberEmail");

		

		
		String num = UUID.randomUUID().toString().replaceAll("-", ""); // -를 제거해 주었다. 
		num = num.substring(0, 5);

        String mailContent = "안녕하세요. Allvie 입니다. "
				+ "<br> E-Mail 인증 번호 입니다. "
				+ "<br><span style='font-weight:bold;'>인증번호 : </span>" 
				+ "<span style='font-size:15px; font-weight:bolder;'>" + num + "</span>" 
				+ "<br>감사합니다."
				+ "<br><br><span><h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "<a href='#'style='font-size:30px; color:#e6e6e6; background-color:#333335;'>&nbsp;<span style='color:#ac162c;'>A</span>llvie&nbsp;</a></h1></span>";
		
        		
        GmailSend mail = new GmailSend();
        mail.GmailSet(email, "Allvie 인증번호 입니다.", mailContent);
        
        String view = "/WEB-INF/views/common/msg.jsp";		
		String msg = "메일 발송이 완료되었습니다. ";
		String loc = "/member/emailCheck";
        
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("email", email);
		request.setAttribute("num", num);
		RequestDispatcher req = request.getRequestDispatcher("/member/emailCheck");
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
