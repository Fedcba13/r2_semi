package com.r2.admin.controller.support;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.r2.admin.model.service.SupportService;
import com.r2.admin.model.vo.Support;

/**
 * Servlet implementation class AjaxSendDMServlet
 */
@WebServlet("/chat/sendDM.do")
public class AjaxSendDMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxSendDMServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		//2.파라미터
		String dm = request.getParameter("dm");
		Map<String, Object> dmMap = new Gson().fromJson(dm, Map.class);
		
		//3.업무로직
		Map<String, Session> clients
			= HelloWebSocket.clients;
		Set<String> userIdSet = clients.keySet();
		String result = "";
		
		//접속자 확인
		if(userIdSet.contains(dmMap.get("receiver"))) {
			Session receiver = clients.get(dmMap.get("receiver"));
			receiver.getBasicRemote()
				    .sendText(dm);
			
			Support s = new Support();
			s.setSupportRecevier(dmMap.get("receiver").toString());
			s.setSupportSender(dmMap.get("sender").toString());
			s.setSupportContent(dmMap.get("msg").toString());
			
			new SupportService().insertSupport(s);
			
			result = "DM 전송 성공!";
		}
		else {
			result = "해당 사용자가 접속중이 아닙니다.";
		}
		
		//본인에게 보내기
		if(userIdSet.contains(dmMap.get("sender"))) {
			Session receiver = clients.get(dmMap.get("sender"));
			receiver.getBasicRemote()
				    .sendText(dm);
			
			result = "DM 전송 성공!";
		}
		else {
			result = "해당 사용자가 접속중이 아닙니다.";
		}
		
		//4.view단
		Map<String, String> resultMap 
			= new HashMap<>();
		resultMap.put("result", result);
		new Gson().toJson(resultMap, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
