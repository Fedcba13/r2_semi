package com.r2.movie.model.service;

import static com.r2.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.r2.movie.model.dao.ReviewDAO;
import com.r2.movie.model.vo.Review;

public class ReviewService {

	public List<Review> getReviewList(String movieId) {
		Connection conn = getConnection();
		List<Review> list = new ReviewDAO().getReviewList(conn, movieId);
		
		close(conn);
		return list;
	}

}
