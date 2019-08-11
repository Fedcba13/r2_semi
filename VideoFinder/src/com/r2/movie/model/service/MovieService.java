package com.r2.movie.model.service;

import static com.r2.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.r2.movie.model.dao.MovieDAO;
import com.r2.movie.model.vo.Movie;

public class MovieService {

	public List<Movie> selectMovieList(int cPage, int numPerPage, String keyword, String[] genreList, String yearFrom,
			String yearTo) {
		Connection conn = getConnection();
		List<Movie> list = new MovieDAO().selectMovieList(conn, cPage, numPerPage, keyword, genreList, yearFrom, yearTo);
		close(conn);
		return list;
	}

	public int selectTotalMovie(String keyword, String[] genreList, String yearFrom, String yearTo) {
		int result = 0;
		Connection conn = getConnection();
		result = new MovieDAO().selectTotalMovie(conn, keyword, genreList, yearFrom, yearTo);
		close(conn);
		return result;
	}

}
