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

}
