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
	private String original_title;
	private String[] genre_ids;

	public Movie(String title, String id, String release_date, String poster_path, String original_title) {
		super();
		this.title = title;
		this.id = id;
		this.release_date = release_date;
		this.poster_path = poster_path;
		this.original_title = original_title;
	}

	public String[] getGenre_ids() {
		return genre_ids;
	}

	public void setGenre_ids(String[] genre_ids) {
		this.genre_ids = genre_ids;
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

	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", id=" + id + ", release_date=" + release_date + ", poster_path="
				+ poster_path + ", original_title=" + original_title + "]";
	}

}
