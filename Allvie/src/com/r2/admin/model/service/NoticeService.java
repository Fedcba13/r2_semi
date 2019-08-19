package com.r2.admin.model.service;

import static com.r2.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.r2.admin.model.dao.FAQDAO;
import com.r2.admin.model.dao.NoticeDAO;
import com.r2.admin.model.vo.Notice;

public class NoticeService {

	public List<Notice> getNoticeList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> list 
			= new NoticeDAO().getNoticeList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContents() {
		Connection conn = getConnection();
		int totalContents = new NoticeDAO().selectTotalContents(conn);
		close(conn);
		return totalContents;
	}

	public Notice getNoticeByNoticeNo(String notice_No) {
		Connection conn = getConnection();
		Notice n = new NoticeDAO().getNoticeByNoticeNo(conn, notice_No);
		close(conn);
		
		return n;
	}

	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		int result = new NoticeDAO().insertNotice(n, conn);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
				
		
	}

	public List<String> getNoticeCategory() {
		Connection conn = getConnection();
		List<String> catList 
			= new NoticeDAO().getNoticeCategory(conn);
		close(conn);
		return catList;
	}

	public List<Notice> getNotListByFilter(String search_Keyword, String cat, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> notList 
			= new NoticeDAO().getNotListByFilter(conn,cat,  cPage, numPerPage, search_Keyword);
		close(conn);
		return notList;
	}

	public int getTotalContentsByFilter(String search_Keyword, String cat) {
		Connection conn = getConnection();
		int totalContents = new NoticeDAO().getTotalContentsByFilter(conn, cat, search_Keyword);
		close(conn);
		return totalContents;
	}

	public int deleteNotice(String notice_No) {
		Connection conn = getConnection();
		int result = new NoticeDAO().deleteNotice(notice_No, conn);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int modifyNotice(Notice n) {
		Connection conn = getConnection();
		int result = new NoticeDAO().modifyNotice(n, conn);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
}
