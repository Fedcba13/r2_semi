package com.r2.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberEmail;
	private int memberPoint;
	private String memberAddress;
	private String memberPhone;
	private String memberFavoriteGenre;
	private Date memberEnrollDate;
	private int memberCheck;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String memberId, String memberPassword, String memberName, String memberEmail, int memberPoint,
			String memberAddress, String memberPhone, String memberFavoriteGenre, Date memberEnrollDate,
			int memberCheck) {
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberPoint = memberPoint;
		this.memberAddress = memberAddress;
		this.memberPhone = memberPhone;
		this.memberFavoriteGenre = memberFavoriteGenre;
		this.memberEnrollDate = memberEnrollDate;
		this.memberCheck = memberCheck;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public int getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberFavoriteGenre() {
		return memberFavoriteGenre;
	}

	public void setMemberFavoriteGenre(String memberFavoriteGenre) {
		this.memberFavoriteGenre = memberFavoriteGenre;
	}

	public Date getMemberEnrollDate() {
		return memberEnrollDate;
	}

	public void setMemberEnrollDate(Date memberEnrollDate) {
		this.memberEnrollDate = memberEnrollDate;
	}

	public int getMemberCheck() {
		return memberCheck;
	}

	public void setMemberCheck(int memberCheck) {
		this.memberCheck = memberCheck;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPassword=" + memberPassword + ", memberName=" + memberName
				+ ", memberEmail=" + memberEmail + ", memberPoint=" + memberPoint + ", memberAddress=" + memberAddress
				+ ", memberPhone=" + memberPhone + ", memberFavoriteGenre=" + memberFavoriteGenre
				+ ", memberEnrollDate=" + memberEnrollDate + ", memberCheck=" + memberCheck + "]";
	}

}
