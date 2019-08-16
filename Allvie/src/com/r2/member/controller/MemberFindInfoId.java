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
 * Servlet implementation class MemberFindInfoId
 */
@WebServlet("/member/findInfoIdEnd")
public class MemberFindInfoId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFindInfoId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String mName = request.getParameter("memberName");
		String mInfo = request.getParameter("findInfo");
		
		Member m = new MemberService().easyFind(mName, mInfo);
		

		String view = "";
		String loc = "";
		String msg = "";
		
		if(m.getMemberCheck() == 4) {
			msg = "조회되는 회원이 없습니다.";
			view = "/WEB-INF/views/common/msg.jsp"; 
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}else {
		if(m != null) {			
			view = "/WEB-INF/views/member/finalFindId.jsp";
			request.setAttribute("m", m);
		}
		else {
			view = "/WEB-INF/views/common/msg.jsp"; 
			msg = "해당회원이 없습니다.";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}
		}
		
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
