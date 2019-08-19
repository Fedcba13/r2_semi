package com.r2.admin.controller.support;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.r2.admin.model.service.SupportService;
import com.r2.admin.model.vo.Support;

/**
 * Servlet implementation class AdminMessageListServlet
 */
@WebServlet("/chat/messageList.do")
public class AdminMessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminMessageListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");

		String id = request.getParameter("id");

		List<String> userList = null;

		// admin일 경우 질문한 사람 목록 가져오기(안 읽은것만)
		if (id.equals("admin")) {
			userList = new SupportService().getUserList();
		} else {
			userList = new ArrayList<>();
			userList.add(id);
		}

		HashMap<String, List<Support>> map = new HashMap<String, List<Support>>();
		
		//메세지 목록 가져오기
		if (userList != null && userList.size() != 0) {
			for (int i = 0; i < userList.size(); i++) {
				System.out.println(userList.get(i));
				List<Support> messageList = new SupportService().getMessageList(userList.get(i));

				if (messageList != null && messageList.size() != 0) {
					map.put(userList.get(i), messageList);
				}

			}
		}

		new Gson().toJson(map, response.getWriter());

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
