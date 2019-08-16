package com.r2.admin.model.service;

import static com.r2.common.JDBCTemplate.close;
import static com.r2.common.JDBCTemplate.getConnection;
import static com.r2.common.JDBCTemplate.rollback;

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
			= new FAQDAO().getFAQList(conn);
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

	public List<FAQ> getfAQList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<FAQ> fAQList 
			= new FAQDAO().getfAQList(conn, cPage, numPerPage);
		close(conn);
		return fAQList;
	}
	
	public List<FAQ> getFAQListByFilter(String search_Keyword, String cat, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<FAQ> fAQList 
			= new FAQDAO().getFAQListByFilter(conn,cat,  cPage, numPerPage, search_Keyword);
		close(conn);
		return fAQList;
	}

	public int getTotalContentsByFilter(String search_Keyword, String cat) {
		Connection conn = getConnection();
		int totalContents = new FAQDAO().getTotalContentsByFilter(conn, cat, search_Keyword);
		close(conn);
		return totalContents;
	}

	public int insertFAQ(FAQ f) {
		Connection conn = getConnection();
		int result = new FAQDAO().insertFAQ(f, conn);
		if(result>0) {
			close(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public int modifyFAQ(FAQ f) {
		Connection conn = getConnection();
		int result = new FAQDAO().modifyFAQ(f, conn);
		if(result>0) {
			close(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public int deleteFAQ(String fAQ_No) {
		Connection conn = getConnection();
		int result = new FAQDAO().deleteFAQ(fAQ_No, conn);
		if(result>0) {
			close(conn);
		}else {
			rollback(conn);
		}
		return result;
	}
	
	
}
