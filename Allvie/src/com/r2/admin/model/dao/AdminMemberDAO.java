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
import java.util.Date;
import java.util.List;
import java.util.Properties;
import com.r2.member.model.vo.Member;

public class AdminMemberDAO {

	private Properties prop = new Properties();

	public AdminMemberDAO() {
		String fileName = AdminMemberDAO.class.getResource("/sql/admin/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Member> getMemberList(Connection conn, int cPage, int numPerPage) {
		List<Member> memberList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getMemberList");

		try {
			pstmt = conn.prepareStatement(sql);

			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				m.setMemberEnrollDate(rset.getDate("MEMBER_ENROLLDATE"));
				m.setMemberCheck(rset.getInt("MEMBER_CHECK"));

				memberList.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return memberList;
	}

	public int selectTotalMemberContents(Connection conn) {
		int totalContents = 0;
		String sql = prop.getProperty("selectTotalMemberContents");
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

	public int selectTotalSearchContents(Connection conn, String searchType, String searchKeyword) {
		int totalContents = 0;
		String sql = prop.getProperty("selectTotalSearchContents");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		sql = sql.replace("column", searchType);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
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

	public List<Member> selectMembersBySearch(Connection conn, String searchKeyword, String searchType, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMembersBySearch");
		sql = sql.replace("column", searchType);
		try {
			pstmt = conn.prepareStatement(sql);
			if("".equals(searchKeyword)) {
				pstmt.setString(1, "%");
			}else {
				pstmt.setString(1, "%" + searchKeyword + "%");
			}
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				m.setMemberEnrollDate(rset.getDate("MEMBER_ENROLLDATE"));
				m.setMemberCheck(rset.getInt("MEMBER_CHECK"));
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

	public List<Member> selectMembersBySearchDate(Connection conn, Date searchKeyword, Date searchKeywordEnd, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMembersBySearchDate");
		try {
			pstmt = conn.prepareStatement(sql);
			
			/*
			 * java.sql.Date형태로 SQL에 set
			 */
			java.sql.Date sqlDate1 = new java.sql.Date(searchKeyword.getTime());
			java.sql.Date sqlDate2 = new java.sql.Date(searchKeywordEnd.getTime());
			pstmt.setDate(1, sqlDate1);
			pstmt.setDate(2, sqlDate2);
			
			
			
			
			int start = (cPage - 1) * numPerPage + 1;
			int end = cPage * numPerPage;
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			
			
			
			
			

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				m.setMemberEnrollDate(rset.getDate("MEMBER_ENROLLDATE"));
				m.setMemberCheck(rset.getInt("MEMBER_CHECK"));
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

	public int selectTotalSearchContentsDate(Connection conn, Date searchKeywordEnd, Date searchKeyword) {
		int totalContents = 0;
		String sql = prop.getProperty("selectTotalSearchContentsDate");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(sql);
			java.sql.Date sqlDate1 = new java.sql.Date(searchKeyword.getTime());
			java.sql.Date sqlDate2 = new java.sql.Date(searchKeywordEnd.getTime());
			pstmt.setDate(1, sqlDate1);
			pstmt.setDate(2, sqlDate2);
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
