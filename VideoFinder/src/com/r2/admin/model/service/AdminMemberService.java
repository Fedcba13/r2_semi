package com.r2.admin.model.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import static com.r2.common.JDBCTemplate.*;

import com.r2.admin.model.dao.AdminMemberDAO;
import com.r2.admin.model.dao.NoticeDAO;
import com.r2.member.model.vo.Member;
public class AdminMemberService {

	public List<Member> getrMemberList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> memberList = new AdminMemberDAO().getMemberList(conn, cPage, numPerPage);
		close(conn);
		return memberList;
		
	}

	public int selectTotalMemberContents() {
		Connection conn = getConnection();
		int totalContents = new AdminMemberDAO().selectTotalMemberContents(conn);
		close(conn);
		return totalContents;
	}

	public int selectTotalSearchContents(String searchType, String searchKeyword) {
		Connection conn = getConnection();
		int totalContents = new AdminMemberDAO().selectTotalSearchContents(conn, searchType, searchKeyword);
		close(conn);
		return totalContents;
	}

	public List<Member> selectMembersBySearch(String searchType, String searchKeyword) {
		Connection conn = getConnection();
		List<Member> memberList = new AdminMemberDAO().selectMembersBySearch(conn, searchKeyword,searchType);
		close(conn);
		return memberList;
	}

	public List<Member> selectMembersBySearchDate(Date searchKeyword, Date searchKeywordEnd) {
		Connection conn = getConnection();
		List<Member> memberList = new AdminMemberDAO().selectMembersBySearchDate(conn, searchKeyword, searchKeywordEnd);
		close(conn);
		return memberList;
	}

	public int selectTotalSearchContentsDate(Date searchKeyword, Date searchKeywordEnd) {
		Connection conn = getConnection();
		int totalContents = new AdminMemberDAO().selectTotalSearchContentsDate(conn, searchKeywordEnd, searchKeyword);
		close(conn);
		return totalContents;
	}

	
	
}
