package com.r2.movie.model.vo;

import java.io.Serializable;

public class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	private String id;
	private String release_date;
	private String poster_path;

	public Movie(String title, String id, String release_date, String poster_path) {
		super();
		this.title = title;
		this.id = id;
		this.release_date = release_date;
		this.poster_path = poster_path;
	}

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", id=" + id + ", release_date=" + release_date + ", poster_path="
				+ poster_path + "]";
	}

}
