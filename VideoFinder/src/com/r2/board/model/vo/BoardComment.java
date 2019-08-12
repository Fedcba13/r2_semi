package com.r2.board.model.vo;

import java.sql.Date;

public class BoardComment {
	private String board_Comment_No;
	private String board_Comment_Writer;
	private int board_Comment_Level;
	private String board_Comment_Content;
	private String board_Ref;
	private String board_Comment_Ref;
	private Date board_Comment_Date;
	private int board_Comment_Enabled;
	
	
	
	public String getBoard_Comment_No() {
		return board_Comment_No;
	}
	public void setBoard_Comment_No(String board_Comment_No) {
		this.board_Comment_No = board_Comment_No;
	}
	public String getBoard_Comment_Writer() {
		return board_Comment_Writer;
	}
	public void setBoard_Comment_Writer(String board_Comment_Writer) {
		this.board_Comment_Writer = board_Comment_Writer;
	}
	public int getBoard_Comment_Level() {
		return board_Comment_Level;
	}
	public void setBoard_Comment_Level(int board_Comment_Level) {
		this.board_Comment_Level = board_Comment_Level;
	}
	public String getBoard_Comment_Content() {
		return board_Comment_Content;
	}
	public void setBoard_Comment_Content(String board_Comment_Content) {
		this.board_Comment_Content = board_Comment_Content;
	}
	public String getBoard_Ref() {
		return board_Ref;
	}
	public void setBoard_Ref(String board_Ref) {
		this.board_Ref = board_Ref;
	}
	public String getBoard_Comment_Ref() {
		return board_Comment_Ref;
	}
	public void setBoard_Comment_Ref(String board_Comment_Ref) {
		this.board_Comment_Ref = board_Comment_Ref;
	}
	public Date getBoard_Comment_Date() {
		return board_Comment_Date;
	}
	public void setBoard_Comment_Date(Date board_Comment_Date) {
		this.board_Comment_Date = board_Comment_Date;
	}
	public int getBoard_Comment_Enabled() {
		return board_Comment_Enabled;
	}
	public void setBoard_Comment_Enabled(int board_Comment_Enabled) {
		this.board_Comment_Enabled = board_Comment_Enabled;
	}
	public BoardComment(String board_Comment_No, String board_Comment_Writer, int board_Comment_Level,
			String board_Comment_Content, String board_Ref, String board_Comment_Ref, Date board_Comment_Date,
			int board_Comment_Enabled) {
		super();
		this.board_Comment_No = board_Comment_No;
		this.board_Comment_Writer = board_Comment_Writer;
		this.board_Comment_Level = board_Comment_Level;
		this.board_Comment_Content = board_Comment_Content;
		this.board_Ref = board_Ref;
		this.board_Comment_Ref = board_Comment_Ref;
		this.board_Comment_Date = board_Comment_Date;
		this.board_Comment_Enabled = board_Comment_Enabled;
	}
	public BoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BoardComment [board_Comment_No=" + board_Comment_No + ", board_Comment_Writer=" + board_Comment_Writer
				+ ", board_Comment_Level=" + board_Comment_Level + ", board_Comment_Content=" + board_Comment_Content
				+ ", board_Ref=" + board_Ref + ", board_Comment_Ref=" + board_Comment_Ref + ", board_Comment_Date="
				+ board_Comment_Date + ", board_Comment_Enabled=" + board_Comment_Enabled + "]";
	}
	
	
	
}