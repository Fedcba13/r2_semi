package com.r2.admin.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

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
		}
		
		
		
		return result;
	}

}
