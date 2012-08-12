package br.gov.serpro.bookmarkandroid.business.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import br.gov.serpro.bookmarkandroid.business.domain.Bookmark;

public class BookmarkDataSource {

	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;
	private String[] allColumns = { SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_DESCRIPTION, SQLiteHelper.COLUMN_LINK };

	public BookmarkDataSource(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Bookmark saveBookmark(Bookmark bookmark) {

		ContentValues values = new ContentValues();

		values.put(SQLiteHelper.COLUMN_ID, bookmark.getId());
		values.put(SQLiteHelper.COLUMN_DESCRIPTION, bookmark.getDescription());
		values.put(SQLiteHelper.COLUMN_LINK, bookmark.getLink());

		database.insert(SQLiteHelper.TABLE_BOOKMARK, null, values);

		return bookmark;
		
	}

	public void deleteBookmark(Bookmark bookmark) {

		long id = bookmark.getId();

		database.delete(SQLiteHelper.TABLE_BOOKMARK, SQLiteHelper.COLUMN_ID + " = " + id, null);

	}

	public void deleteAll() {

		database.delete(SQLiteHelper.TABLE_BOOKMARK, null, null);

	}

	public List<Bookmark> findAll() {

		List<Bookmark> bookmarks = new ArrayList<Bookmark>();

		Cursor cursor = database.query(SQLiteHelper.TABLE_BOOKMARK, allColumns, null, null, null, null, null);

		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {

			Bookmark bookmark = cursorToBookmark(cursor);
			bookmarks.add(bookmark);
			cursor.moveToNext();

		}

		cursor.close();

		return bookmarks;

	}

	private Bookmark cursorToBookmark(Cursor cursor) {
		
		Bookmark bookmark = new Bookmark();
		
		bookmark.setId(cursor.getLong(0));
		bookmark.setDescription(cursor.getString(1));
		bookmark.setLink(cursor.getString(2));
		
		return bookmark;
	}

}
