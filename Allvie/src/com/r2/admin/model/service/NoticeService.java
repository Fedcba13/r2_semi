package com.r2.admin.model.service;

import static com.r2.common.JDBCTemplate.close;
import static com.r2.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

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

	public List<Notice> getNoticeListByCat(String cat, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> list 
			= new NoticeDAO().getNoticeListByCat(conn,cat,  cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Notice> getNoticeBySearch(String search_Keyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Notice> list 
			= new NoticeDAO().getNoticeBySearch(conn, search_Keyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
}
