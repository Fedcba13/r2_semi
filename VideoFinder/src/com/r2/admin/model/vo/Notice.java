package com.r2.admin.model.vo;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3325093484572704189L;
	private String Notice_No;
	private String Notice_Title;
	private String Notice_Writer;
	private String Notice_Content;
	private Date Notice_Date;
	private int Notice_Readcount;
	private String Notice_Category;
	private int Notice_Enabled;

	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//union 검색 결과용 생성자
	public Notice(String notice_No, String notice_Title, String notice_Writer, String notice_Content, Date notice_Date,
			int notice_Readcount, String notice_Category, int notice_Enabled) {
		super();
		Notice_No = notice_No;
		Notice_Title = notice_Title;
		Notice_Writer = notice_Writer;
		Notice_Content = notice_Content;
		Notice_Date = notice_Date;
		Notice_Readcount = notice_Readcount;
		Notice_Category = notice_Category;
		Notice_Enabled = notice_Enabled;
	}
	
	public Notice(String notice_Title, String notice_Writer, String notice_Content, Date notice_Date,
			String notice_Category) {
		super();
		Notice_Title = notice_Title;
		Notice_Writer = notice_Writer;
		Notice_Content = notice_Content;
		Notice_Date = notice_Date;
		Notice_Category = notice_Category;
	}

	public String getNotice_No() {
		return Notice_No;
	}

	public void setNotice_No(String notice_No) {
		Notice_No = notice_No;
	}

	public String getNotice_Title() {
		return Notice_Title;
	}

	public void setNotice_Title(String notice_Title) {
		Notice_Title = notice_Title;
	}

	public String getNotice_Writer() {
		return Notice_Writer;
	}

	public void setNotice_Writer(String notice_Writer) {
		Notice_Writer = notice_Writer;
	}

	public String getNotice_Content() {
		return Notice_Content;
	}

	public void setNotice_Content(String notice_Content) {
		Notice_Content = notice_Content;
	}

	public Date getNotice_Date() {
		return Notice_Date;
	}

	public void setNotice_Date(Date notice_Date) {
		Notice_Date = notice_Date;
	}

	public int getNotice_Readcount() {
		return Notice_Readcount;
	}

	public void setNotice_Readcount(int notice_Readcount) {
		Notice_Readcount = notice_Readcount;
	}

	public String getNotice_Category() {
		return Notice_Category;
	}

	public void setNotice_Category(String notice_Category) {
		Notice_Category = notice_Category;
	}

	public int getNotice_Enabled() {
		return Notice_Enabled;
	}

	public void setNotice_Enabled(int notice_Enabled) {
		Notice_Enabled = notice_Enabled;
	}

	@Override
	public String toString() {
		return "Notice [Notice_No=" + Notice_No + ", Notice_Title=" + Notice_Title + ", Notice_Writer=" + Notice_Writer
				+ ", Notice_Content=" + Notice_Content + ", Notice_Date=" + Notice_Date + ", Notice_Readcount="
				+ Notice_Readcount + ", Notice_Category=" + Notice_Category + ", Notice_Enabled=" + Notice_Enabled
				+ "]";
	}

}
