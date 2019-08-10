package com.r2.movie.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.r2.movie.model.vo.Movie;

@WebServlet("/movie/movieSearch.do")
public class SearchMovieEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static int curPage = 1;
	static ArrayList<Movie> movieList;

	public SearchMovieEndServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");

		movieList = new ArrayList<Movie>();

		String keyword = request.getParameter("keyword");
		String[] genreList = request.getParameter("genre").split(",");
		System.out.println(String.join(",", genreList));
		String yearFrom = request.getParameter("yearFrom");
		String yearTo = request.getParameter("yearTo");

		// 장르 ID 값 변경 => 시간 있으면 DB로 변경
		if (genreList != null) {
			for (int i = 0; i < genreList.length; i++) {
				switch (genreList[i]) {
				case "드라마":
					genreList[i] = "18";
					break;
				case "공포":
					genreList[i] = "27";
					break;
				case "로맨스":
					genreList[i] = "10749";
					break;
				case "모험":
					genreList[i] = "12";
					break;
				case "스릴러":
					genreList[i] = "53";
					break;
				case "다큐":
					genreList[i] = "99";
					break;
				case "코미디":
					genreList[i] = "35";
					break;
				case "애니메이션":
					genreList[i] = "16";
					break;
				case "범죄":
					genreList[i] = "80";
					break;
				case "SF":
					genreList[i] = "878";
					break;
				case "액션":
					genreList[i] = "28";
					break;
				}

			}
		}

		boolean bool = true;
		
		String genre = String.join("|", genreList);

		int total_results = 0;
		int resultPerPage = 10;
		int totalPage = 0;

		StringBuilder urlBuilder = new StringBuilder(
				"https://api.themoviedb.org/3/discover/movie?api_key=84d7ecb7406790765081943aa6cac19b&language=ko-KR&sort_by=popularity.desc&include_adult=false&include_video=false&region=KR");/*
																																																	 * URL
																																																	 */
		if (!yearTo.equals("")) {
			urlBuilder.append(
					"&" + URLEncoder.encode("release_date.lte", "UTF-8") + "=" + URLEncoder.encode(yearTo, "UTF-8"));
		}
		if (!yearFrom.equals("")) {
			urlBuilder.append(
					"&" + URLEncoder.encode("release_date.gte", "UTF-8") + "=" + URLEncoder.encode(yearFrom, "UTF-8"));
		}
		if (!genre.equals("")) {
			urlBuilder
					.append("&" + URLEncoder.encode("with_genres", "UTF-8") + "=" + URLEncoder.encode(genre, "UTF-8"));
		}

		
		
		while(bool) {
			
			StringBuilder urlBuilderTemp = urlBuilder;
			urlBuilderTemp.append("&" + URLEncoder.encode("page", "UTF-8") + "=" + curPage); /* 현재 페이지 */
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();

			HashMap<String, Object> map = new HashMap<String, Object>();
			map = new Gson().fromJson(sb.toString(), HashMap.class);
			
//			double a = (double) map.get("total_pages");

			Gson gson = new Gson();
			Type type = new TypeToken<List<Movie>>() {}.getType();
			
			ArrayList<Movie> movieListTemp = gson.fromJson(new Gson().toJson(map.get("results")), type);
			
			if(movieListTemp != null) {
				for(int i=0; i<movieListTemp.size(); i++) {				
					movieList.add(movieListTemp.get(i));
				}
			}
			
			curPage++;
			if(curPage > 300) {
				bool = false;
			}
		}
		
		new Gson().toJson(movieList, response.getWriter());
		
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
