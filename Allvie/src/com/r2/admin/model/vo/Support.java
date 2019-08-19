package com.r2.admin.model.vo;

import java.sql.Date;

import com.google.gson.annotations.SerializedName;

public class Support {

	private String supportNo;
	@SerializedName("sender")
	private String supportSender;
	@SerializedName("receiver")
	private String supportRecevier;
	@SerializedName("msg")
	private String supportContent;
	private Date supportDate;

	public Support() {
		super();
	}

	public Support(String supportNo, String supportSender, String supportRecevier, String supportContent,
			Date supportDate) {
		super();
		this.supportNo = supportNo;
		this.supportSender = supportSender;
		this.supportRecevier = supportRecevier;
		this.supportContent = supportContent;
		this.supportDate = supportDate;
	}

	public String getSupportNo() {
		return supportNo;
	}

	public void setSupportNo(String supportNo) {
		this.supportNo = supportNo;
	}

	public String getSupportSender() {
		return supportSender;
	}

	public void setSupportSender(String supportSender) {
		this.supportSender = supportSender;
	}

	public String getSupportRecevier() {
		return supportRecevier;
	}

	public void setSupportRecevier(String supportRecevier) {
		this.supportRecevier = supportRecevier;
	}

	public String getSupportContent() {
		return supportContent;
	}

	public void setSupportContent(String supportContent) {
		this.supportContent = supportContent;
	}

	public Date getSupportDate() {
		return supportDate;
	}

	public void setSupportDate(Date supportDate) {
		this.supportDate = supportDate;
	}

	@Override
	public String toString() {
		return "Support [supportNo=" + supportNo + ", supportSender=" + supportSender + ", supportRecevier="
				+ supportRecevier + ", supportContent=" + supportContent + ", supportDate=" + supportDate + "]";
	}

}
