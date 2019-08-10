package com.r2.movie.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetMovieId
 */
@WebServlet("/movie/getMovieId")
public class GetMovieId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BufferedReader br = null;
		String result = "";
		try {
			String title = request.getParameter("title");
//			String movieId = request.getParameter("id");
//			System.out.println(movieId);
			String urlStr = "https://api.themoviedb.org/3/search/movie?api_key=84d7ecb7406790765081943aa6cac19b&query=" + title +"&language=ko-KR";
			URL url = new URL(urlStr);
	        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
	        urlconnection.setRequestMethod("GET");
	        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
	        String line;
	        while((line = br.readLine()) != null) {
	            result = result + line;
	        }
	        System.out.println(result);
	        request.setAttribute("result", result);
	    }catch(Exception e){
	        System.out.println(e.getMessage());
	    }
		request.getRequestDispatcher("/WEB-INF/views/movie/searchResult.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
