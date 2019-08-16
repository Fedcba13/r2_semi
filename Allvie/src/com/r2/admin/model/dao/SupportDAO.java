package com.r2.admin.model.dao;

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
import static com.r2.common.JDBCTemplate.*;

import com.r2.admin.model.vo.Support;

public class SupportDAO {
	
	private Properties prop = new Properties();

	public SupportDAO() {
		String fileName = AdminMemberDAO.class.getResource("/sql/admin/support/support-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertSupport(Connection conn, Support s) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("insertSupport");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, s.getSupportSender());
			pstmt.setString(2, s.getSupportRecevier());
			pstmt.setString(3, s.getSupportContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		
		return result;
	}

	public List<String> getUserList(Connection conn) {
		List<String> list = new ArrayList<String>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getUserList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(rset.getString("support_sender"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<Support> getMessageList(Connection conn, String userId) {
		List<Support> list = new ArrayList<Support>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getMessageList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Support s = new Support();
				
				s.setSupportSender(rset.getString("support_sender"));
				s.setSupportRecevier(rset.getString("support_recevier"));
				s.setSupportContent(rset.getString("support_content"));
				
				list.add(s);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

}
