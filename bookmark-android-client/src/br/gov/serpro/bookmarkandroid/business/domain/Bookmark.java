package br.gov.serpro.bookmarkandroid.business.domain;

import java.io.Serializable;

public class Bookmark implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String description;

	private String link;

	public Bookmark() {
		super();
	}

	public Bookmark(Long id, String description, String link) {
		
		this.id = id;
		
		this.description = description;
		this.link = link;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
