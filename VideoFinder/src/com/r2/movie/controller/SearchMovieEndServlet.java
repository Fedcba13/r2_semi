package com.r2.movie.controller;

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
import com.r2.movie.model.service.MovieService;
import com.r2.movie.model.vo.Movie;

@WebServlet("/movie/movieSearch.do")
public class SearchMovieEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		String keyword = request.getParameter("keyword");
		String[] genreList = request.getParameter("genre").split(",");
		String yearFrom = request.getParameter("yearFrom");
		String yearTo = request.getParameter("yearTo");

		int numPerPage = 4;

		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {

		}

		// 2. 업무로직
		List<Movie> list = new MovieService().selectMovieList(cPage, numPerPage, keyword, genreList, yearFrom, yearTo);

		int totalContents = new MovieService().selectTotalMovie(keyword, genreList, yearFrom, yearTo);

		int totalPage = (int) Math.ceil((double) totalContents / numPerPage);
		
		final int pageBarSize = 5;
		String pageBar = "";

		int pageStart = pageBarSize * ((cPage - 1) / pageBarSize) + 1;
		int pageEnd = pageStart + pageBarSize - 1;

		int pageNo = pageStart;

		// a. [이전]
		if (pageNo == 1) {
			pageBar += "<span>[이전]</span>";
		} else {
			pageBar += "<a href='#' class='page' val='"+(pageNo-1)+"'>[이전]</a>";
		}

		// b. page
		while (pageNo <= pageEnd && pageNo <= totalPage) {
			// 현재페이지인 경우. 링크 필요 없음.
			if (pageNo == cPage) {
				pageBar += "<span class='cPage'>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='#' class='page' val='"+pageNo+"'>" + pageNo + "</a>";
			}
			pageNo++;
		}

		// c. [다음]
		if (pageNo > totalPage) {
			pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href='#' class='page' val='"+pageNo+"'>[다음]</a>";
		}
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("pageBar",pageBar);
		map.put("cPage", cPage);

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
