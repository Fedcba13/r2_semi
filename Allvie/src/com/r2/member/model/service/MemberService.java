package com.r2.member.model.service;

import static com.r2.common.JDBCTemplate.close;
import static com.r2.common.JDBCTemplate.commit;
import static com.r2.common.JDBCTemplate.getConnection;
import static com.r2.common.JDBCTemplate.rollback;

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

	public Member easyFind(String mName, String mInfo) {
		
		Connection conn = getConnection();
		
		Member m = new MemberDAO().easyFind(conn, mName, mInfo);
		
		close(conn);
		
		return m;
	}

	public int changePwd(Member member) {
		
		Connection conn = getConnection();
		int result = new MemberDAO().changePwd(conn, member);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Member selectOneByName(String memberName) {
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOneByName(conn, memberName);
		close(conn);
		
		return m;
	}

	public int updateMember(Member m) {
		
		Connection conn = getConnection();
		
		int result = new MemberDAO().updateMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}		
		close(conn);
		
		return result;
	}

	public int deleteMember(String memberId) {

		Connection conn = getConnection();
		int result = new MemberDAO().deleteMember(conn, memberId);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}		
		close(conn);
		
		return result;
	}

	public int updatePwd(String memberId, String newPwd) {
		Connection conn = getConnection();
		
		int result = new MemberDAO().updatePwd(conn, memberId, newPwd);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}		
		close(conn);
		
		return result;
	}

	public int deleteChk(Member m) {

		Connection conn = getConnection();
		
		int result = new MemberDAO().deleteChk(conn, m);
		
		close(conn);
		
		return result;
	}

	
	
}
