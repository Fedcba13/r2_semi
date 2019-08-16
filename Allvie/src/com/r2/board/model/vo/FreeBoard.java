package com.r2.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class FreeBoard implements Serializable {

	private static final long serialVersionUID = 1L;

	private String freeBoardNo;
	private String freeBoardTitle;
	private String freeBoardWriter;
	private String freeBoardContent;
	private Date freeBoardDate;
	private int freeBoardReadCount;
	private int freeBoardEnabled;
	private String freeBoardRef;

	public FreeBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FreeBoard(String freeBoardNo, String freeBoardTitle, String freeBoardWriter, String freeBoardContent,
			Date freeBoardDate, int freeBoardReadCount, int freeBoardEnabled, String freeBoardRef) {
		this.freeBoardNo = freeBoardNo;
		this.freeBoardTitle = freeBoardTitle;
		this.freeBoardWriter = freeBoardWriter;
		this.freeBoardContent = freeBoardContent;
		this.freeBoardDate = freeBoardDate;
		this.freeBoardReadCount = freeBoardReadCount;
		this.freeBoardEnabled = freeBoardEnabled;
		this.freeBoardRef = freeBoardRef;
	}

	public String getFreeBoardNo() {
		return freeBoardNo;
	}

	public void setFreeBoardNo(String freeBoardNo) {
		this.freeBoardNo = freeBoardNo;
	}

	public String getFreeBoardTitle() {
		return freeBoardTitle;
	}

	public void setFreeBoardTitle(String freeBoardTitle) {
		this.freeBoardTitle = freeBoardTitle;
	}

	public String getFreeBoardWriter() {
		return freeBoardWriter;
	}

	public void setFreeBoardWriter(String freeBoardWriter) {
		this.freeBoardWriter = freeBoardWriter;
	}

	public String getFreeBoardContent() {
		return freeBoardContent;
	}

	public void setFreeBoardContent(String freeBoardContent) {
		this.freeBoardContent = freeBoardContent;
	}

	public Date getFreeBoardDate() {
		return freeBoardDate;
	}

	public void setFreeBoardDate(Date freeBoardDate) {
		this.freeBoardDate = freeBoardDate;
	}

	public int getFreeBoardReadCount() {
		return freeBoardReadCount;
	}

	public void setFreeBoardReadCount(int freeBoardReadCount) {
		this.freeBoardReadCount = freeBoardReadCount;
	}

	public int getFreeBoardEnabled() {
		return freeBoardEnabled;
	}

	public void setFreeBoardEnabled(int freeBoardEnabled) {
		this.freeBoardEnabled = freeBoardEnabled;
	}

	public String getFreeBoardRef() {
		return freeBoardRef;
	}

	public void setFreeBoardRef(String freeBoardRef) {
		this.freeBoardRef = freeBoardRef;
	}

}
