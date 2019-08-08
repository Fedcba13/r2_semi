package com.r2.admin.model.service;

import static com.r2.common.JDBCTemplate.*;


import java.sql.Connection;
import java.util.List;

import com.r2.admin.model.dao.FAQDAO;
import com.r2.admin.model.vo.FAQ;

public class FAQService {

	public List<FAQ> getFAQList() {
		Connection conn = getConnection();
		List<FAQ> list 
			= new FAQDAO().getFAQListList(conn);
		close(conn);
		return list;
	}

	public int getCategoryCount() {
		Connection conn = getConnection();
		int category
			= new FAQDAO().getCategoryCount(conn);
		close(conn);
		return category;
	}

	public FAQ getFAQByFAQNo(String fAQ_No) {
		Connection conn = getConnection();
		FAQ f = new FAQDAO().getFAQByFAQNo(conn, fAQ_No);
		close(conn);
		
		return f;
		
	}
	
	
}
