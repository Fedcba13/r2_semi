package com.r2.member.model.service;

import static com.r2.common.JDBCTemplate.*;

import java.sql.Connection;

import com.r2.member.model.dao.MemberDAO;
import com.r2.member.model.vo.Member;

public class MemberService {

	public int insertMember(Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDAO().insertMember(conn, m);
		
		close(conn);
		
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}

	public Member selectOne(String memberId) {
		
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOne(conn, memberId);
		close(conn);
		
		return m;
	}

	public int DuplicateByID(String memberId) {
		
		Connection conn = getConnection();
		int result = new MemberDAO().DuplicateByID(conn, memberId);
		close(conn);
		return result;
	}

	public int loginCheck(Member m) {
		
		Connection conn = getConnection();
		int result = new MemberDAO().loginCheck(conn, m);
		
		close(conn);
		
		return result;
	}

	
	
}
