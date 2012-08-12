package br.gov.serpro.bookmarkandroid;

import java.util.List;

import android.app.Application;
import android.os.AsyncTask;
import br.gov.serpro.bookmarkandroid.business.BookmarkBO;
import br.gov.serpro.bookmarkandroid.business.domain.Bookmark;
import br.gov.serpro.bookmarkandroid.business.service.BookmarkService;

public class BookmarkApp extends Application {

	@Override
	public void onCreate() {
		
		super.onCreate();
		
		BookmarkStartupTask bookmarkStartupTask = new BookmarkStartupTask();		
		bookmarkStartupTask.execute();
	
	}
	
	private class BookmarkStartupTask extends AsyncTask<Void, Void, List<Bookmark>> {

		private Exception error = null;

		@Override
		protected List<Bookmark> doInBackground(Void... params) {

			error = null;
			List<Bookmark> listBookmarks = null;

			try {

				BookmarkService bookmarkService = new BookmarkService();
				
				listBookmarks = bookmarkService.findAll();

			} catch (Exception e) {
				error = e;
			}

			return listBookmarks;
		}

		@Override
		protected void onPostExecute(List<Bookmark> listBookmarks) {

			BookmarkBO bookmarkBO = new BookmarkBO(BookmarkApp.this.getApplicationContext());
			
			bookmarkBO.deleteAll();

			for (Bookmark bookmark : listBookmarks) {
				bookmarkBO.insert(bookmark);
			}
			
		}
	}

}
