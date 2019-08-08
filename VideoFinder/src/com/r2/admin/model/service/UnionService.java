package com.r2.admin.model.service;

import static com.r2.common.JDBCTemplate.close;
import static com.r2.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.r2.admin.model.dao.UnionDAO;
import com.r2.admin.model.vo.Notice;

public class UnionService {

	public List<Notice> getUnionSearchList(String search_Keyword) {
		Connection conn = getConnection();
		System.out.println("서비스 !!!");
		List<Notice> unionList 
			= new UnionDAO().getUnionSearchList(conn, search_Keyword);
		close(conn);
		return unionList;
	}
	
	
}
