package com.r2.admin.model.dao;

import static com.r2.common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.r2.admin.model.vo.Notice;

public class UnionDAO {
	private Properties prop = new Properties();
	
	public UnionDAO() {
		String fileName = FAQDAO.class.getResource("/sql/admin/union/union-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Notice> getUnionSearchList(Connection conn, String search_Keyword) {
		List<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getUnionSearchList");

		try {
			// 미완성쿼리객체생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+search_Keyword+"%");
			pstmt.setString(2, "%"+search_Keyword+"%");
			pstmt.setString(3, "%"+search_Keyword+"%");
			pstmt.setString(4, "%"+search_Keyword+"%");
			
			// 쿼리실행
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Notice n = new Notice();
				n.setNotice_No(rset.getString("NOTICE_NO"));
				n.setNotice_Title(rset.getString("NOTICE_TITLE"));
				n.setNotice_Content(rset.getString("NOTICE_CONTENT"));
				
				
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

	public List<Notice> getAutoListBysrchKeyword(Connection conn, String searchKeyword) {
		List<Notice> autoList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getAutoListBysrchKeyword");

		try {
			// 미완성쿼리객체생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setString(2, "%"+searchKeyword+"%");
			
			// 쿼리실행
			rset = pstmt.executeQuery();
			String cat="";
			while (rset.next()) {
				Notice n = new Notice();
				n.setNotice_Title(rset.getString("NOTICE_TITLE"));
				n.setNotice_No(rset.getString("NOTICE_NO"));
				
				
				autoList.add(n);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return autoList;
	}

}
