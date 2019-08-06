package com.r2.movie.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MovieList
 */
@WebServlet("/movie/movieImage.do")
public class FindImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		String title = request.getParameter("title");
		int year = Integer.parseInt(request.getParameter("year"));
		
		String result = "";
		
		String clientId = "clkXlkuNEnCp5ggagP1z";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "2kojvAdjzF";//애플리케이션 클라이언트 시크릿값";
        try {
        	String apiURL = "https://openapi.naver.com/v1/search/movie.json?display=1";
        	apiURL += "&query=" + URLEncoder.encode(title, "utf-8");;
        	//apiURL += "&yearfrom=" + year;
        	//apiURL += "&yearto=" + year;
        	
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer respon = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                respon.append(inputLine);
            }	
            br.close();
            result = respon.toString();
        } catch (Exception e) {
            System.out.println(e);
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
