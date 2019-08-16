package com.r2.movie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.movie.model.service.ReviewService;

@WebServlet("/movie/dislikeReview.do")
public class DislikeReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String movieId = request.getParameter("movieId");
		String memberId = request.getParameter("memberId");
		String reviewNo = request.getParameter("rn");

		int result = new ReviewService().dislikeReview(reviewNo, memberId);

		if (result > 0) {
			System.out.println("좋아요 성공");
		} else {
			System.out.println("좋아요 실패");
		}
		
		response.getWriter().append(Integer.toString(result));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
