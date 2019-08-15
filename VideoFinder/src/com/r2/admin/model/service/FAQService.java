package com.r2.admin.model.service;

import static com.r2.common.JDBCTemplate.close;
import static com.r2.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.r2.admin.model.dao.FAQDAO;
import com.r2.admin.model.dao.NoticeDAO;
import com.r2.admin.model.vo.FAQ;
import com.r2.admin.model.vo.Notice;

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

	public List<FAQ> getFAQListInManage(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<FAQ> fAQList 
			= new FAQDAO().getFAQListInManage(conn, cPage, numPerPage);
		close(conn);
		return fAQList;
	}

	public int selectTotalContents() {
		Connection conn = getConnection();
		int totalContents = new FAQDAO().selectTotalContents(conn);
		close(conn);
		return totalContents;
	}

	public List<String> getFAQCategory() {
		Connection conn = getConnection();
		List<String> catList 
			= new FAQDAO().getFAQCategory(conn);
		close(conn);
		return catList;
	}

	public List<FAQ> getFAQBySearch(String search_Keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<FAQ> list 
			= new FAQDAO().getFAQBySearch(conn, search_Keyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContentsBySearch(String search_Keyword) {
		Connection conn = getConnection();
		int totalContents = new FAQDAO().selectTotalContentsBySearch(conn, search_Keyword);
		close(conn);
		return totalContents;
	}

	public List<FAQ> getFAQListByCat(String cat, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<FAQ> list 
			= new FAQDAO().getFAQListByCat(conn, cat, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContentsByCat(String cat) {
		Connection conn = getConnection();
		int totalContents = new FAQDAO().selectTotalContentsByCat(conn, cat);
		close(conn);
		return totalContents;
	}
	
	
}
