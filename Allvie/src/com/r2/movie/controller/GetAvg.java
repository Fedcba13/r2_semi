package com.r2.movie.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.r2.movie.model.service.ReviewService;

/**
 * Servlet implementation class GetAvg
 */
@WebServlet("/movie/getAvg.do")
public class GetAvg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf=8");
		
		String movieId = request.getParameter("id");
		Map<String, String> map = new ReviewService().getAvg(movieId);
		System.out.println("map=" + map);
		if(map.get("avg") == null) {
			response.getWriter().append(null);
		} else {
			new Gson().toJson(map, response.getWriter());			
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
