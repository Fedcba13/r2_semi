package com.r2.member.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.common.filter.MVCUtils;
import com.r2.common.mail.GmailSend;
import com.r2.member.model.service.MemberService;
import com.r2.member.model.vo.Member;

/**
 * Servlet implementation class Member
 */
@WebServlet(urlPatterns="/member/findInfoPwdEnd", name="MemberFindInfoPwd")
public class MemberFindInfoPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFindInfoPwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		String memberEmail = request.getParameter("findInfo");
		
		Member m = new MemberService().selectOne(memberId);
		
		String msg = "";
		String loc = "";
		String view = "/WEB-INF/views/common/msg.jsp";
		
		if(m.getMemberCheck() == 4) {
			msg = "가입된 회원정보가 없습니다. ";
			loc = "/member/findInfoPwd";
		}else {
			
		if(memberEmail.equals(m.getMemberEmail())) {
			
			String mPwd = UUID.randomUUID().toString().replaceAll("-", ""); // -를 제거해 주었다. 
			mPwd = mPwd.substring(0, 10); //uuid를 앞에서부터 10자리 잘라줌. 
			
			System.out.println(mPwd);
			
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberPassword(MVCUtils.getSha512(mPwd));
	        
			int result = new MemberService().changePwd(member);
			String mailContent = "안녕하세요. Allvie 입니다. "
					+ "<br>" + m.getMemberId() + "님의 임시비밀번호는 아래와 같습니다. "
					+ "<br><span style='font-weight:bold;'>임시 비밀번호 : </span>" 
					+ "<span style='font-size:15px; font-weight:bolder;'>" + mPwd + "</span>" 
					+ "<br>감사합니다."
					+ "<br> 로그인을 하시려면 하단의 로고를 클릭해주세요. "
					+ "<br><span><h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ "<a href='htp://localhost:9090/vf/member/memberLogin'style='font-size:30px; color:#e6e6e6; background-color:#333335;'>&nbsp;<span style='color:#ac162c;'>A</span>llvie&nbsp;</a></h1></span>";
			
			
		GmailSend mail = new GmailSend();
		mail.GmailSet(m.getMemberEmail(), "Allvie 임시비밀번호 입니다.", mailContent);
		
			msg = "임시비밀번호 발송이 완료되었습니다.";
			loc = "/index.jsp";
		
		} else {
			
			msg = "등록된 정보가 없습니다.";
			loc = "/member/findInfoPwd";
			
			

		}
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
