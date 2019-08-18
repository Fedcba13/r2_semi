package com.r2.admin.model.service;

import static com.r2.common.JDBCTemplate.close;
import static com.r2.common.JDBCTemplate.commit;
import static com.r2.common.JDBCTemplate.getConnection;
import static com.r2.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.r2.admin.model.dao.SupportDAO;
import com.r2.admin.model.vo.Support;

public class SupportService {
	
	public int insertSupport(Support s) {
		Connection conn = getConnection();
		
		int result = new SupportDAO().insertSupport(conn, s);
		
		
		if(result > 0 && s.getSupportSender().equals("admin")) {
			result = new SupportDAO().updateSupport(conn, s);
		}
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}

	public List<String> getUserList() {
		Connection conn = getConnection();
		List<String> list = new SupportDAO().getUserList(conn);
		close(conn);
		
		return list;
	}

	public List<Support> getMessageList(String userId) {
		Connection conn = getConnection();
		List<Support> list = new SupportDAO().getMessageList(conn, userId);
		close(conn);
		
		return list;
	}

}
