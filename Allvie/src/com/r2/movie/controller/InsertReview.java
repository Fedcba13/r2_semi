package com.r2.movie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.movie.model.service.ReviewService;
import com.r2.movie.model.vo.Review;

@WebServlet("/movie/insertReview.do")
public class InsertReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		
		String memberId = request.getParameter("memberId");
		int rate = Integer.parseInt(request.getParameter("rate"));
		String movieId = request.getParameter("movieId");
		String comment = request.getParameter("comment");
		
		Review r = new Review(null, memberId, movieId, rate, 0, 0, null, 1, comment);
		
				
		int result = new ReviewService().insertReview(r);
		if(result > 0) {
			System.out.println("리뷰작성성공!");			
		} else {
			System.out.println("리뷰작성오류!");		
		}
		response.getWriter().append(Integer.toString(result));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
