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
		
		// System.out.println("logincheckDAO1"+result);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPassword());
			pstmt.setString(3, m.getMemberId());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("login_check");
			}
			
			// System.out.println("logincheckDAO2"+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public Member easyFind(Connection conn, String mName, String mInfo) {
		
		Member m = null;		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("easyFind");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mName);
			pstmt.setString(2, mInfo);
			pstmt.setString(3, mInfo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("m_id"));
				m.setMemberEnrollDate(rset.getDate("member_enrolldate"));
			}
			System.out.println(m);
			// System.out.println(m.getMemberId());
		} catch (SQLException e) {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

	public int changePwd(Connection conn, String memberId, String mPwd) {
		
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("changePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mPwd);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return result;
	}

	public Member selectOneByName(Connection conn, String memberName) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneByName");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberName);

			rset = pstmt.executeQuery();

			while (rset.next()) {
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
		} finally {
			close(rset);
			close(pstmt);
		}

		return m;
	}

	public int updateMember(Connection conn, Member m) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberName());
			pstmt.setString(2, m.getMemberEmail());
			pstmt.setString(3, m.getMemberPhone());
			pstmt.setString(4, m.getMemberAddress());
			pstmt.setString(5, m.getMemberFavoriteGenre());
			pstmt.setString(6, m.getMemberId());
			
			result = pstmt.executeUpdate();
								
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updatePwd(Connection conn, String memberId, String newPwd) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPwd);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteChk(Connection conn, Member m) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("deleteChk");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberId());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				result = rset.getInt("member_check");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
}
