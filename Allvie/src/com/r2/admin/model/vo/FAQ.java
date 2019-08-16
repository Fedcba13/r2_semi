package com.r2.admin.model.vo;

import java.util.Date;

public class FAQ {
	private String FAQ_No;
	private String FAQ_Title;
	private String FAQ_Writer;
	private String FAQ_Content;
	private Date FAQ_Date;
	private int FAQ_Readcount;
	private String FAQ_Category;
	private int FAQ_Enabled;

	public FAQ() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FAQ(String fAQ_No, String fAQ_Title, String fAQ_Writer, String fAQ_Content, Date fAQ_Date, int fAQ_Readcount,
			String fAQ_Category, int fAQ_Enabled) {
		super();
		FAQ_No = fAQ_No;
		FAQ_Title = fAQ_Title;
		FAQ_Writer = fAQ_Writer;
		FAQ_Content = fAQ_Content;
		FAQ_Date = fAQ_Date;
		FAQ_Readcount = fAQ_Readcount;
		FAQ_Category = fAQ_Category;
		FAQ_Enabled = fAQ_Enabled;
	}

	public String getFAQ_No() {
		return FAQ_No;
	}

	public void setFAQ_No(String fAQ_No) {
		FAQ_No = fAQ_No;
	}

	public String getFAQ_Title() {
		return FAQ_Title;
	}

	public void setFAQ_Title(String fAQ_Title) {
		FAQ_Title = fAQ_Title;
	}

	public String getFAQ_Writer() {
		return FAQ_Writer;
	}

	public void setFAQ_Writer(String fAQ_Writer) {
		FAQ_Writer = fAQ_Writer;
	}

	public String getFAQ_Content() {
		return FAQ_Content;
	}

	public void setFAQ_Content(String fAQ_Content) {
		FAQ_Content = fAQ_Content;
	}

	public Date getFAQ_Date() {
		return FAQ_Date;
	}

	public void setFAQ_Date(Date fAQ_Date) {
		FAQ_Date = fAQ_Date;
	}

	public int getFAQ_Readcount() {
		return FAQ_Readcount;
	}

	public void setFAQ_Readcount(int fAQ_Readcount) {
		FAQ_Readcount = fAQ_Readcount;
	}

	public String getFAQ_Category() {
		return FAQ_Category;
	}

	public void setFAQ_Category(String fAQ_Category) {
		FAQ_Category = fAQ_Category;
	}

	public int getFAQ_Enabled() {
		return FAQ_Enabled;
	}

	public void setFAQ_Enabled(int fAQ_Enabled) {
		FAQ_Enabled = fAQ_Enabled;
	}

	@Override
	public String toString() {
		return "FAQ [FAQ_No=" + FAQ_No + ", FAQ_Title=" + FAQ_Title + ", FAQ_Writer=" + FAQ_Writer + ", FAQ_Content="
				+ FAQ_Content + ", FAQ_Date=" + FAQ_Date + ", FAQ_Readcount=" + FAQ_Readcount + ", FAQ_Category="
				+ FAQ_Category + ", FAQ_Enabled=" + FAQ_Enabled + "]";
	}

}
