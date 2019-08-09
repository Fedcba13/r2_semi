package com.r2.member.model.dao;




import static com.r2.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.r2.member.model.vo.Member;

public class MemberDAO {

	private Properties prop = new Properties();
	
	public MemberDAO() {
		String fileName 
			= MemberDAO.class		
					   .getResource("/sql/member/member-query.properties")
					   .getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertMember(Connection conn, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPassword());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberEmail());
			pstmt.setString(5, m.getMemberAddress());
			pstmt.setString(6, m.getMemberPhone());
			pstmt.setString(7, m.getMemberFavoriteGenre());
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}

	public Member selectOne(Connection conn, String memberId) {
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberAddress(rset.getString("member_address"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberFavoriteGenre(rset.getString("member_favorite_genre"));
				m.setMemberEnrollDate(rset.getDate("member_enrolldate"));
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		
		
		return m;
	}

	public int DuplicateByID(Connection conn, String memberId) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("DuplicateByID");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		
		return result;
	}

	public int loginCheck(Connection conn, Member m) {
		
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("loginCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPassword());
			pstmt.setString(3, m.getMemberId());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("login_check");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
}
