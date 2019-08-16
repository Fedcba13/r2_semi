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
 * Servlet implementation class GetMovieDetailServlet
 */
@WebServlet("/movie/getDetail.do")
public class GetMovieDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩 설정
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		BufferedReader br = null;
		String result = "";
		try {
			//필요한 파라미터를 세팅
			String movieId = request.getParameter("id");
			//System.out.println(movieId);
			//API DB에 보낼 쿼리문 작성
			String urlStr = "https://api.themoviedb.org/3/movie/"+movieId+"?api_key=84d7ecb7406790765081943aa6cac19b&language=ko-KR";
			//URL객체 생성
			URL url = new URL(urlStr);
	        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
	        urlconnection.setRequestMethod("GET");
	        //생성된 url객체로 쿼리문을 보내고 받은 데이터를 inputStream을 이용하여 읽어온다
	        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
	        String line;
	        //읽어온 데이터를 String 객체에 저장
	        while((line = br.readLine()) != null) {
	            result = result + line;
	        }
	        //System.out.println(result);
	        //저장된 String을 attribute에 등록
	        request.setAttribute("movieDetail", result);
	    }catch(Exception e){
	        System.out.println(e.getMessage());
	    }
		//등록된 값을 전달.
		//쿼리문의 결과값은 이미 JSON의 형태로 오기 때문에 Gson의 toJson을 이용하지 않고 그대로 보낸다.
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
