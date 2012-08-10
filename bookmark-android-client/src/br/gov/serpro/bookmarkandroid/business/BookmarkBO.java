package br.gov.serpro.bookmarkandroid.business;

import java.util.List;

import br.gov.serpro.bookmarkandroid.business.domain.Bookmark;

public class BookmarkBO {

	private BookmarkBO bookmarkBO;

	public BookmarkBO() {

		super();

		this.bookmarkBO = new BookmarkBO();

	}

	public List<Bookmark> findAll() throws Exception {

		return this.bookmarkBO.findAll();

	}	

}
