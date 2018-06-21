package com.modernlib.author.dto;

public class Author {

	private int id;
	private String authorName;
	private String email;
	private String createdBy;
	private String lastModifieBy;
	
	public Author(int id, String authorName, String email, String createdBy,
			String lastModifieBy) {
		this.id = id;
		this.authorName = authorName;
		this.email = email;
		this.createdBy = createdBy;
		this.lastModifieBy = lastModifieBy;
	}

	public Author(String authorName, String email, String createdBy,
			String lastModifieBy) {
		this.authorName = authorName;
		this.email = email;
		this.createdBy = createdBy;
		this.lastModifieBy = lastModifieBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifieBy() {
		return lastModifieBy;
	}

	public void setLastModifieBy(String lastModifieBy) {
		this.lastModifieBy = lastModifieBy;
	}
}
