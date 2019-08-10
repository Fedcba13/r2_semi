package com.r2.movie.model.service;

import static com.r2.common.JDBCTemplate.close;
import static com.r2.common.JDBCTemplate.commit;
import static com.r2.common.JDBCTemplate.getConnection;
import static com.r2.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.r2.movie.model.dao.ReviewDAO;
import com.r2.movie.model.vo.Review;

public class ReviewService {

	public List<Review> getReviewList(String movieId) {
		Connection conn = getConnection();
		List<Review> list = new ReviewDAO().getReviewList(conn, movieId);
		
		close(conn);
		return list;
	}

	public int insertReview(Review r) {
		Connection conn = getConnection();
		int result = new ReviewDAO().insertReview(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Map<String, String> getAvg(String movieId) {
		Connection conn = getConnection();
		Map<String, String> map = new ReviewDAO().getAvg(conn, movieId);		
		
		close(conn);
		return map;
	}

	public Map<String, Integer> getReviewGraphData(String movieId) {
		Connection conn = getConnection();
		Map<String, Integer> map = new ReviewDAO().getReviewGraphData(conn, movieId);
		
		close(conn);
		return map;
	}

}
