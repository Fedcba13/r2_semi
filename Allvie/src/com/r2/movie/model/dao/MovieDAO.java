package com.r2.movie.model.dao;

import static com.r2.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.r2.movie.model.vo.Movie;
import com.r2.movie.model.vo.Review;

public class MovieDAO {

	private Properties prop = new Properties();

	public MovieDAO() {
		String fileName = getClass().getResource("/sql/movie/movie-query.properties").getPath();
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

	public List<Movie> selectMovieList(Connection conn, int cPage, int numPerPage, String keyword, String[] genreList,
			String yearFrom, String yearTo) {
		List<Movie> list = new ArrayList<Movie>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMovieList");

		// 조건절 처리
		String condition = "";

		if (keyword != null && !keyword.equals("")) {
			if(!condition.equals("")) {
				condition += " and ";
			}
			condition += "fn like '%'||FN_GET_DIV_KO_CHAR('" + keyword + "')||'%'";
		}

		if (genreList != null && genreList.length != 0) {
			for(int i=0; i<genreList.length; i++) {				
				if(!condition.equals("")) {
					condition += " and ";
				}
				condition += "genre like '%"+genreList[i]+"%'";
			}
		}

		if (yearFrom != null && !yearFrom.equals("")) {
			if(!condition.equals("")) {
				condition += " and ";
			}
			condition += "release_date >= '" +  yearFrom + "'";
		}
		if (yearTo != null && !yearTo.equals("")) {
			if(!condition.equals("")) {
				condition += " and ";
			}
			condition += "release_date <= '" +  yearTo + "'";
		}

		if(condition.equals("")) {
			condition = "1 = 1";
		}
		
		sql = sql.replace("condition", condition);
		
		try {
			pstmt = conn.prepareStatement(sql);

			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Movie m = new Movie();
				m.setId(rset.getString("ID"));
				m.setTitle(rset.getString("TITLE"));
				m.setPoster_path(rset.getString("POSTER_PATH"));
				m.setOriginal_title(rset.getString("ORIGINAL_TITLE"));
				m.setRelease_date(rset.getString("RELEASE_DATE"));
				m.setGenre(rset.getString("genre"));
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int selectTotalMovie(Connection conn, String keyword, String[] genreList, String yearFrom, String yearTo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectTotalMovie");

		// 조건절 처리
		String condition = "";

		if (keyword != null && !keyword.equals("")) {
			if(!condition.equals("")) {
				condition += " and ";
			}
			condition += "fn like '%'||FN_GET_DIV_KO_CHAR('" + keyword + "')||'%'";
		}

		if (genreList != null && genreList.length != 0) {
			for(int i=0; i<genreList.length; i++) {				
				if(!condition.equals("")) {
					condition += " and ";
				}
				condition += "genre like '%"+genreList[i]+"%'";
			}
		}

		if (yearFrom != null && !yearFrom.equals("")) {
			if(!condition.equals("")) {
				condition += " and ";
			}
			condition += "release_date >= '" +  yearFrom + "'";
		}
		if (yearTo != null && !yearTo.equals("")) {
			if(!condition.equals("")) {
				condition += " and ";
			}
			condition += "release_date <= '" +  yearTo + "'";
		}

		if(condition.equals("")) {
			condition = "1 = 1";
		}
		
		sql = sql.replace("condition", condition);
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

}
