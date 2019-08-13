package com.r2.movie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.r2.movie.model.service.ReviewService;

/**
 * Servlet implementation class LikeReviewServlet
 */
@WebServlet("/movie/likeReview.do")
public class LikeReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String movieId = request.getParameter("movieId");
		String memberId = request.getParameter("memberId");
		String reviewNo = request.getParameter("rn");
		
		int result = new ReviewService().likeReview(reviewNo, memberId);
		
		if(result > 0) {
			System.out.println("좋아요 성공");
		} else {
			System.out.println("좋아요 실패");
		}
		
		response.getWriter().append(Integer.toString(result));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
