package com.r2.movie.model.dao;

import static com.r2.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
				int reviewGrade = rset.getInt("review_grade");
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

	public int insertReview(Connection conn, Review r) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReview");
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getMemberId());
			pstmt.setString(2, r.getMovieId());
			pstmt.setInt(3, r.getReviewGrade());
			pstmt.setString(4, r.getReviewComment());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Map<String, String> getAvg(Connection conn, String movieId) {	
		Map<String, String> map = new HashMap<String, String>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getAvg");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				map.put("avg", rset.getString("avg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return map;
	}

	public Map<String, Integer> getReviewGraphData(Connection conn, String movieId) {
		Map<String, Integer> map = new HashMap<String, Integer>();		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getReviewGraphData");
		try {
			pstmt = conn.prepareStatement(sql);
			for(int i = 1; i <=10; i++) {
				pstmt.setString(1, movieId);
				pstmt.setInt(2, i);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					map.put(Integer.toString(i), rset.getInt("cnt"));
				} else {
					map.put(Integer.toString(i), 0);
				}
				pstmt.clearParameters();
			}
			
			System.out.println("map@DAO=" + map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return map;
	}

	public int deleteReview(Connection conn, int reviewNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteReview");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNum);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
