package com.r2.board.model.vo;

import java.sql.Date;

public class Scenario {
	private String scenario_No;
	private String title;
	private String member_Id;
	private String scenario_Content;
	private Date scenario_Date;
	private int scenario_Enabled;
	private int readCount;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Scenario [scenario_No=" + scenario_No + ", title=" + title + ", member_Id=" + member_Id
				+ ", scenario_Content=" + scenario_Content + ", scenario_Date=" + scenario_Date + ", scenario_Enabled="
				+ scenario_Enabled + ", readCount=" + readCount + "]";
	}
	public Scenario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Scenario(String scenario_No, String title, String member_Id, String scenario_Content, Date scenario_Date,
			int scenario_Enabled, int readCount) {
		super();
		this.scenario_No = scenario_No;
		this.title = title;
		this.member_Id = member_Id;
		this.scenario_Content = scenario_Content;
		this.scenario_Date = scenario_Date;
		this.scenario_Enabled = scenario_Enabled;
		this.readCount = readCount;
	}
	public String getScenario_No() {
		return scenario_No;
	}
	public void setScenario_No(String scenario_No) {
		this.scenario_No = scenario_No;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMember_Id() {
		return member_Id;
	}
	public void setMember_Id(String member_Id) {
		this.member_Id = member_Id;
	}
	public String getScenario_Content() {
		return scenario_Content;
	}
	public void setScenario_Content(String scenario_Content) {
		this.scenario_Content = scenario_Content;
	}
	public Date getScenario_Date() {
		return scenario_Date;
	}
	public void setScenario_Date(Date scenario_Date) {
		this.scenario_Date = scenario_Date;
	}
	public int getScenario_Enabled() {
		return scenario_Enabled;
	}
	public void setScenario_Enabled(int scenario_Enabled) {
		this.scenario_Enabled = scenario_Enabled;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	
	
	
	
	
	
	
	
}