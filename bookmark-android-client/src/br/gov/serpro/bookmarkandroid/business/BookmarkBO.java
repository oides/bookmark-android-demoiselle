package br.gov.serpro.bookmarkandroid.business;

import java.util.List;

import android.content.Context;
import br.gov.serpro.bookmarkandroid.business.database.BookmarkDataSource;
import br.gov.serpro.bookmarkandroid.business.domain.Bookmark;

public class BookmarkBO {

	private BookmarkDataSource dataSource;

	
	public BookmarkBO(Context context) {

		super();

		this.dataSource = new BookmarkDataSource(context);

	}

	public List<Bookmark> findAll() {

		List<Bookmark> retorno = null;
		
		this.dataSource.open();
		retorno = this.dataSource.findAll();
		this.dataSource.close();
		
		return retorno;

	}	
	
	public void deleteAll() {

		this.dataSource.open();
		this.dataSource.deleteAll();
		this.dataSource.close();

	}
	
	public Bookmark insert(Bookmark bookmark) {

		this.dataSource.open();
		bookmark = this.dataSource.saveBookmark(bookmark);
		this.dataSource.close();
		
		return bookmark;

	}

}
