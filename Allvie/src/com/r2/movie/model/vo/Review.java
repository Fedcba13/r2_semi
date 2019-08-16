package com.r2.movie.model.vo;

import java.sql.Date;

public class Review {
	
	private String reviewNum;
	private String memberId;
	private String movieId;
	private int reviewGrade;
	private int reviewLike;
	private int reviewDislike;
	private Date reviewDate;
	private int reviewEnabled;
	private String reviewComment;
	
	public Review() {
		
	}

	public Review(String reviewNum, String memberId, String movieId, int reviewGrade, int reviewLike, int reviewDislike,
			Date reviewDate, int reviewEnabled, String reviewComment) {		
		this.reviewNum = reviewNum;
		this.memberId = memberId;
		this.movieId = movieId;
		this.reviewGrade = reviewGrade;
		this.reviewLike = reviewLike;
		this.reviewDislike = reviewDislike;
		this.reviewDate = reviewDate;
		this.reviewEnabled = reviewEnabled;
		this.reviewComment = reviewComment;
	}

	public String getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(String reviewNum) {
		this.reviewNum = reviewNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public int getReviewGrade() {
		return reviewGrade;
	}

	public void setReviewGrade(int reviewGrade) {
		this.reviewGrade = reviewGrade;
	}

	public int getReviewLike() {
		return reviewLike;
	}

	public void setReviewLike(int reviewLike) {
		this.reviewLike = reviewLike;
	}

	public int getReviewDislike() {
		return reviewDislike;
	}

	public void setReviewDislike(int reviewDislike) {
		this.reviewDislike = reviewDislike;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getReviewEnabled() {
		return reviewEnabled;
	}

	public void setReviewEnabled(int reviewEnabled) {
		this.reviewEnabled = reviewEnabled;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	@Override
	public String toString() {
		return "Review [reviewNum=" + reviewNum + ", memberId=" + memberId + ", movieId=" + movieId + ", reviewGrade="
				+ reviewGrade + ", reviewLike=" + reviewLike + ", reviewDislike=" + reviewDislike + ", reviewDate="
				+ reviewDate + ", reviewEnabled=" + reviewEnabled + ", reviewComment=" + reviewComment + "]";
	}
	
	

}
