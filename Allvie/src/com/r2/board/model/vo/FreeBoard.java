package com.r2.board.model.vo;

import java.sql.Date;

public class FreeBoard {

	private String free_Board_No;
	private String free_Board_Title;
	private String free_Board_Writer;
	private String free_Board_Content; 
	private Date free_Board_Date; 
	private int free_Board_ReadCount;
	private int free_Board_Category;
	private int free_Board_Enabled;
	private int free_Board_Ref;
	
	
	
	
	
	
	

	public FreeBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FreeBoard(String free_Board_No, String free_Board_Title, String free_Board_Writer, String free_Board_Content,
			Date free_Board_Date, int free_Board_ReadCount, int free_Board_Category, int free_Board_Enabled,
			int free_Board_Ref) {
		super();
		this.free_Board_No = free_Board_No;
		this.free_Board_Title = free_Board_Title;
		this.free_Board_Writer = free_Board_Writer;
		this.free_Board_Content = free_Board_Content;
		this.free_Board_Date = free_Board_Date;
		this.free_Board_ReadCount = free_Board_ReadCount;
		this.free_Board_Category = free_Board_Category;
		this.free_Board_Enabled = free_Board_Enabled;
		this.free_Board_Ref = free_Board_Ref;
	}
	public String getFree_Board_No() {
		return free_Board_No;
	}
	public void setFree_Board_No(String free_Board_No) {
		this.free_Board_No = free_Board_No;
	}
	public String getFree_Board_Title() {
		return free_Board_Title;
	}
	public void setFree_Board_Title(String free_Board_Title) {
		this.free_Board_Title = free_Board_Title;
	}
	public String getFree_Board_Writer() {
		return free_Board_Writer;
	}
	public void setFree_Board_Writer(String free_Board_Writer) {
		this.free_Board_Writer = free_Board_Writer;
	}
	public String getFree_Board_Content() {
		return free_Board_Content;
	}
	public void setFree_Board_Content(String free_Board_Content) {
		this.free_Board_Content = free_Board_Content;
	}
	public Date getFree_Board_Date() {
		return free_Board_Date;
	}
	public void setFree_Board_Date(Date free_Board_Date) {
		this.free_Board_Date = free_Board_Date;
	}
	public int getFree_Board_ReadCount() {
		return free_Board_ReadCount;
	}
	public void setFree_Board_ReadCount(int free_Board_ReadCount) {
		this.free_Board_ReadCount = free_Board_ReadCount;
	}
	public int getFree_Board_Category() {
		return free_Board_Category;
	}
	public void setFree_Board_Category(int free_Board_Category) {
		this.free_Board_Category = free_Board_Category;
	}
	public int getFree_Board_Enabled() {
		return free_Board_Enabled;
	}
	public void setFree_Board_Enabled(int free_Board_Enabled) {
		this.free_Board_Enabled = free_Board_Enabled;
	}
	public int getFree_Board_Ref() {
		return free_Board_Ref;
	}
	public void setFree_Board_Ref(int free_Board_Ref) {
		this.free_Board_Ref = free_Board_Ref;
	}
	
	
	@Override
	public String toString() {
		return "FreeBoard [free_Board_No=" + free_Board_No + ", free_Board_Title=" + free_Board_Title
				+ ", free_Board_Writer=" + free_Board_Writer + ", free_Board_Content=" + free_Board_Content
				+ ", free_Board_Date=" + free_Board_Date + ", free_Board_ReadCount=" + free_Board_ReadCount
				+ ", free_Board_Category=" + free_Board_Category + ", free_Board_Enabled=" + free_Board_Enabled
				+ ", free_Board_Ref=" + free_Board_Ref + "]";
	}
}
