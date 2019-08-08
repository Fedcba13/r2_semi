package com.r2.movie.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.r2.common.JDBCTemplate.*;
import com.r2.movie.model.vo.Review;

public class ReviewDAO {
	
	private Properties prop = new Properties();
	
	public ReviewDAO() {
		String fileName = getClass().getResource("/sql/movie/review-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Review> getReviewList(Connection conn, String movieId) {
		List<Review> list = new ArrayList<Review>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getReviewList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String reviewNum = rset.getString("review_num");
				String memberId = rset.getString("member_id");
				String movieId_ = rset.getString("movie_id");
				int reviewGrade = rset.getInt("review grade");
				int reviewLike = rset.getInt("review_like");
				int reviewDislike = rset.getInt("review_dislike");
				Date reviewDate = rset.getDate("review_date");
				int reviewEnabled = rset.getInt("review_enabled");
				String reviewComment = rset.getString("review_comment");
				Review r = new Review(reviewNum, memberId, movieId_, reviewGrade, reviewLike, reviewDislike, reviewDate, reviewEnabled, reviewComment);
				
				list.add(r);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		
		return list;
	}

}
