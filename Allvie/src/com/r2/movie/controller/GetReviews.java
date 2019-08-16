package com.r2.movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.r2.movie.model.service.ReviewService;
import com.r2.movie.model.vo.Review;

@WebServlet("/movie/getReviews.do")
public class GetReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		String movieId = request.getParameter("id");
		//작성된 리뷰 목록을 DB로부터  받아와서 list에 저장
		List<Review> list = new ReviewService().getReviewList(movieId);
		//System.out.println("reviewlist@servlet=" + list);
		if(list.isEmpty()) {
			//작성된 리뷰가 없을경우 null을 전달
			response.getWriter().append(null);
		} else {
			//작성된 리뷰가 있을경우 json의 형태로 전달
			new Gson().toJson(list, response.getWriter());			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
