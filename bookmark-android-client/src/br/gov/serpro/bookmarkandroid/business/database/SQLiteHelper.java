package br.gov.serpro.bookmarkandroid.business.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_BOOKMARK = "bookmark";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_LINK = "link";

	private static final String DATABASE_NAME = "bookmarks.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = "create table " + TABLE_BOOKMARK + "(" + COLUMN_ID + " integer primary key, " + COLUMN_DESCRIPTION + " text not null," + COLUMN_LINK + " text not null);";

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARK);
		onCreate(db);
	}

}
