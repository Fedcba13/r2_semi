package com.r2.admin.model.dao;


import static com.r2.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.r2.admin.model.vo.FAQ;
import com.r2.admin.model.vo.Notice;

public class FAQDAO {
	private Properties prop = new Properties();

	public FAQDAO() {
		String fileName = FAQDAO.class.getResource("/sql/admin/FAQ/FAQ-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
			System.out.println("[[prop loading 완료:" + fileName + "]]");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<FAQ> getFAQListList(Connection conn) {
		List<FAQ> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getFAQListList");

		try {
			// 미완성쿼리객체생성
			pstmt = conn.prepareStatement(sql);

			// 쿼리실행
			rset = pstmt.executeQuery();

			while (rset.next()) {
				FAQ f = new FAQ();
				f.setFAQ_No(rset.getString("FAQ_NO"));
				f.setFAQ_Title(rset.getString("FAQ_TITLE"));
				f.setFAQ_Writer(rset.getString("FAQ_WRITER"));
				f.setFAQ_Content(rset.getString("FAQ_CONTENT"));
				f.setFAQ_Date(rset.getDate("FAQ_DATE"));
				f.setFAQ_Readcount(rset.getInt("FAQ_READCOUNT"));
				f.setFAQ_Category(rset.getString("FAQ_CATEGORY"));
				f.setFAQ_Enabled(rset.getInt("FAQ_ENABLED"));
				
				
				list.add(f);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int getCategoryCount(Connection conn) {
		int category = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getCategoryCount");
		try {
			// 미완성쿼리객체생성
			pstmt = conn.prepareStatement(sql);

			// 쿼리실행
			rset = pstmt.executeQuery();

			while (rset.next()) {
				
				category =rset.getInt("RESULT"); 
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return category;
	}

	public FAQ getFAQByFAQNo(Connection conn, String fAQ_No) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getFAQByFAQNo");
		FAQ f = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fAQ_No);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				f = new FAQ();
				f.setFAQ_No(rset.getString("FAQ_NO"));
				f.setFAQ_Title(rset.getString("FAQ_TITLE"));
				f.setFAQ_Writer(rset.getString("FAQ_WRITER"));
				f.setFAQ_Content(rset.getString("FAQ_CONTENT"));
				f.setFAQ_Date(rset.getDate("FAQ_DATE"));
				f.setFAQ_Readcount(rset.getInt("FAQ_READCOUNT"));
				f.setFAQ_Category(rset.getString("FAQ_CATEGORY"));
				f.setFAQ_Enabled(rset.getInt("FAQ_ENABLED"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}

	public List<FAQ> getFAQListInManage(Connection conn, int cPage, int numPerPage) {
		List<FAQ> fAQList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getFAQListInManage");

		try {
			// 미완성쿼리객체생성
			pstmt = conn.prepareStatement(sql);

			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			// 쿼리실행
			rset = pstmt.executeQuery();

			while (rset.next()) {
				FAQ f = new FAQ();
				f.setFAQ_No(rset.getString("FAQ_NO"));
				f.setFAQ_Title(rset.getString("FAQ_TITLE"));
				f.setFAQ_Writer(rset.getString("FAQ_WRITER"));
				f.setFAQ_Content(rset.getString("FAQ_CONTENT"));
				f.setFAQ_Date(rset.getDate("FAQ_DATE"));
				f.setFAQ_Readcount(rset.getInt("FAQ_READCOUNT"));
				f.setFAQ_Category(rset.getString("FAQ_CATEGORY"));
				f.setFAQ_Enabled(rset.getInt("FAQ_ENABLED"));
				System.out.println(f);

				fAQList.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return fAQList;
	}

	public int selectTotalContents(Connection conn) {
		int totalContents = 0;
		String sql = prop.getProperty("selectTotalContents");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				totalContents = (rset.getInt("RESULT"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return totalContents;
	}

	public List<String> getFAQCategory(Connection conn) {
		List<String> catList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getFAQCategory");

		try {
			// 미완성쿼리객체생성
			pstmt = conn.prepareStatement(sql);
			// 쿼리실행
			rset = pstmt.executeQuery();

			while (rset.next()) {

				catList.add(rset.getString("CAT_NAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return catList;
	}

	public List<FAQ> getFAQBySearch(Connection conn, String search_Keyword, int cPage, int numPerPage) {
		List<FAQ> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getFAQBySearch");

		try {
			// 미완성쿼리객체생성
			pstmt = conn.prepareStatement(sql);

			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			pstmt.setString(1, "%"+search_Keyword+"%");
			pstmt.setString(2, "%"+search_Keyword+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);

			// 쿼리실행
			rset = pstmt.executeQuery();

			while (rset.next()) {
				FAQ f = new FAQ();
				f.setFAQ_No(rset.getString("FAQ_NO"));
				f.setFAQ_Title(rset.getString("FAQ_TITLE"));
				f.setFAQ_Writer(rset.getString("FAQ_WRITER"));
				f.setFAQ_Content(rset.getString("FAQ_CONTENT"));
				f.setFAQ_Date(rset.getDate("FAQ_DATE"));
				f.setFAQ_Readcount(rset.getInt("FAQ_READCOUNT"));
				f.setFAQ_Category(rset.getString("FAQ_CATEGORY"));
				f.setFAQ_Enabled(rset.getInt("FAQ_ENABLED"));

				list.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectTotalContentsBySearch(Connection conn, String search_Keyword) {
		int totalContents = 0;
		String sql = prop.getProperty("selectTotalContentsBySearch");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search_Keyword+"%");
			pstmt.setString(2, "%"+search_Keyword+"%");
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				totalContents = (rset.getInt("RESULT"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return totalContents;
	}

	public List<FAQ> getFAQListByCat(Connection conn, String cat, int cPage, int numPerPage) {
		List<FAQ> fAQList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getFAQListByCat");

		try {
			// 미완성쿼리객체생성
			pstmt = conn.prepareStatement(sql);

			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			pstmt.setString(1, cat);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			// 쿼리실행
			rset = pstmt.executeQuery();

			while (rset.next()) {
				FAQ f = new FAQ();
				f.setFAQ_No(rset.getString("FAQ_NO"));
				f.setFAQ_Title(rset.getString("FAQ_TITLE"));
				f.setFAQ_Writer(rset.getString("FAQ_WRITER"));
				f.setFAQ_Content(rset.getString("FAQ_CONTENT"));
				f.setFAQ_Date(rset.getDate("FAQ_DATE"));
				f.setFAQ_Readcount(rset.getInt("FAQ_READCOUNT"));
				f.setFAQ_Category(rset.getString("FAQ_CATEGORY"));
				f.setFAQ_Enabled(rset.getInt("FAQ_ENABLED"));

				fAQList.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return fAQList;
	}

	public int selectTotalContentsByCat(Connection conn, String cat) {
		int totalContents = 0;
		String sql = prop.getProperty("selectTotalContentsByCat");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cat);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				totalContents = (rset.getInt("RESULT"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return totalContents;
	}

}
