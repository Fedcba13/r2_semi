package com.r2.movie.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListByActorServlet
 */
@WebServlet("/movie/listByActor.do")
public class ListByActorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		BufferedReader br = null;
		String result = "";
		try {
			String actorId = request.getParameter("actorId");
			System.out.println(actorId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(new Date());
			System.out.println(today);
			String urlStr = "http://api.themoviedb.org/3/discover/movie?with_cast="+actorId+"&sort_by=release_date.desc&api_key=84d7ecb7406790765081943aa6cac19b&language=ko-KR&primary_release_date.lte="+today;
			URL url = new URL(urlStr);
	        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
	        urlconnection.setRequestMethod("GET");
	        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
	        String line;
	        while((line = br.readLine()) != null) {
	            result = result + line;
	        }
	        System.out.println(result);
	        request.setAttribute("actorList", result);
	    }catch(Exception e){
	        System.out.println(e.getMessage());
	    }		
		response.getWriter().append(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
