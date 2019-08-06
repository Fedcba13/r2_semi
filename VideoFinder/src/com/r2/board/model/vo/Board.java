package com.r2.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {

	private static final long serialVersionUID = 1L;

	private String boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private Date boardDate;
	private int boardReadCount;
	private int boardCategry;
	private int boardEnabled;

	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Board(String boardNo, String boardTitle, String boardWriter, String boardContent, Date boardDate,
			int boardReadCount, int boardCategry, int boardEnabled) {
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.boardReadCount = boardReadCount;
		this.boardCategry = boardCategry;
		this.boardEnabled = boardEnabled;
	}

	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardReadCount() {
		return boardReadCount;
	}

	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}

	public int getBoardCategry() {
		return boardCategry;
	}

	public void setBoardCategry(int boardCategry) {
		this.boardCategry = boardCategry;
	}

	public int getBoardEnabled() {
		return boardEnabled;
	}

	public void setBoardEnabled(int boardEnabled) {
		this.boardEnabled = boardEnabled;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", boardDate=" + boardDate + ", boardReadCount=" + boardReadCount
				+ ", boardCategry=" + boardCategry + ", boardEnabled=" + boardEnabled + "]";
	}

}
