package com.r2.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BoardComment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String boardCommentNo;
	private String boardCommentWriter;
	private int boardCommentLevel;
	private String boardCommentContent;
	private String boardRef;
	private String boardCommentRef;
	private Date boardCommentDate;
	private int boardCommentEnabled;

	public BoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardComment(String boardCommentNo, String boardCommentWriter, int boardCommentLevel,
			String boardCommentContent, String boardRef, String boardCommentRef, Date boardCommentDate,
			int boardCommentEnabled) {
		this.boardCommentNo = boardCommentNo;
		this.boardCommentWriter = boardCommentWriter;
		this.boardCommentLevel = boardCommentLevel;
		this.boardCommentContent = boardCommentContent;
		this.boardRef = boardRef;
		this.boardCommentRef = boardCommentRef;
		this.boardCommentDate = boardCommentDate;
		this.boardCommentEnabled = boardCommentEnabled;
	}

	public String getBoardCommentNo() {
		return boardCommentNo;
	}

	public void setBoardCommentNo(String boardCommentNo) {
		this.boardCommentNo = boardCommentNo;
	}

	public String getBoardCommentWriter() {
		return boardCommentWriter;
	}

	public void setBoardCommentWriter(String boardCommentWriter) {
		this.boardCommentWriter = boardCommentWriter;
	}

	public int getBoardCommentLevel() {
		return boardCommentLevel;
	}

	public void setBoardCommentLevel(int boardCommentLevel) {
		this.boardCommentLevel = boardCommentLevel;
	}

	public String getBoardCommentContent() {
		return boardCommentContent;
	}

	public void setBoardCommentContent(String boardCommentContent) {
		this.boardCommentContent = boardCommentContent;
	}

	public String getBoardRef() {
		return boardRef;
	}

	public void setBoardRef(String boardRef) {
		this.boardRef = boardRef;
	}

	public String getBoardCommentRef() {
		return boardCommentRef;
	}

	public void setBoardCommentRef(String boardCommentRef) {
		this.boardCommentRef = boardCommentRef;
	}

	public Date getBoardCommentDate() {
		return boardCommentDate;
	}

	public void setBoardCommentDate(Date boardCommentDate) {
		this.boardCommentDate = boardCommentDate;
	}

	public int getBoardCommentEnabled() {
		return boardCommentEnabled;
	}

	public void setBoardCommentEnabled(int boardCommentEnabled) {
		this.boardCommentEnabled = boardCommentEnabled;
	}

	@Override
	public String toString() {
		return "BoardComment [boardCommentNo=" + boardCommentNo + ", boardCommentWriter=" + boardCommentWriter
				+ ", boardCommentLevel=" + boardCommentLevel + ", boardCommentContent=" + boardCommentContent
				+ ", boardRef=" + boardRef + ", boardCommentRef=" + boardCommentRef + ", boardCommentDate="
				+ boardCommentDate + ", boardCommentEnabled=" + boardCommentEnabled + "]";
	}

}
