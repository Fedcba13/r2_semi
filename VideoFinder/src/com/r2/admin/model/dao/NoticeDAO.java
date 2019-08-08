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

import com.r2.admin.model.vo.Notice;

public class NoticeDAO {
	
	private Properties prop = new Properties();

	public NoticeDAO() {
		String fileName = NoticeDAO.class.getResource("/sql/admin/notice/notice-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Notice> getNoticeList(Connection conn, int cPage, int numPerPage) {
		List<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getNoticeList");
		
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
				Notice n = new Notice();
				n.setNotice_No(rset.getString("Notice_NO"));
				n.setNotice_Title(rset.getString("Notice_TITLE"));
				n.setNotice_Writer(rset.getString("Notice_WRITER"));
				n.setNotice_Content(rset.getString("Notice_CONTENT"));
				n.setNotice_Date(rset.getDate("Notice_DATE"));
				n.setNotice_Readcount(rset.getInt("Notice_READCOUNT"));
				n.setNotice_Category(rset.getString("Notice_CATEGORY"));
				n.setNotice_Enabled(rset.getInt("Notice_ENABLED"));
				System.out.println(n);
				
				list.add(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
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

	public Notice getNoticeByNoticeNo(Connection conn, String notice_No) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getNoticeByNoticeNo");
		Notice n = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice_No);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				n = new Notice();
				n.setNotice_No(rset.getString("NOTICE_NO"));
				n.setNotice_Title(rset.getString("NOTICE_TITLE"));
				n.setNotice_Writer(rset.getString("NOTICE_WRITER"));
				n.setNotice_Content(rset.getString("NOTICE_CONTENT"));
				n.setNotice_Date(rset.getDate("NOTICE_DATE"));
				n.setNotice_Readcount(rset.getInt("NOTICE_READCOUNT"));
				n.setNotice_Category(rset.getString("NOTICE_CATEGORY"));
				n.setNotice_Enabled(rset.getInt("NOTICE_ENABLED"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
		
	}

	public int insertNotice(Notice n, Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNotice_Title());
			pstmt.setString(2, n.getNotice_Content());
			pstmt.setString(3, n.getNotice_Category());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	

}
