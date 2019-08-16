package com.r2.admin.model.service;

import static com.r2.common.JDBCTemplate.close;
import static com.r2.common.JDBCTemplate.commit;
import static com.r2.common.JDBCTemplate.getConnection;
import static com.r2.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.r2.admin.model.dao.SupportDAO;
import com.r2.admin.model.vo.Support;

public class SupportService {
	
	public int insertSupport(Support s) {
		Connection conn = getConnection();
		
		int result = new SupportDAO().insertSupport(conn, s);
		
		close(conn);
		
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}

}
