package br.gov.serpro.bookmarkandroid.list;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import br.gov.serpro.bookmarkandroid.R;
import br.gov.serpro.bookmarkandroid.add.AddBookmarkActivity;
import br.gov.serpro.bookmarkandroid.business.domain.Bookmark;
import br.gov.serpro.bookmarkandroid.business.service.BookmarkService;
import br.gov.serpro.bookmarkandroid.edit.EditBookmarkActivity;
import br.gov.serpro.bookmarkandroid.list.adapter.ListBookmarkAdapter;

public class ListBookmarkActivity extends ListActivity {

	public static final String SELECTED_BOOKMARK = null;
	
	private ListBookmarkAdapter adapter;
	private ProgressDialog dialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.list_bookmark);
        
        this.loadBookmarkListView();
		
		this.addEventsBookmarkListView();
        
    }

	private void loadBookmarkListView() {
		
		this.adapter = new ListBookmarkAdapter(this);        
        getListView().setAdapter(this.adapter);
        
		this.dialog = ProgressDialog.show(this, "", "Carregando Bookmarks. Por favor, aguarde...", true);
		
		BookmarkStartupTask bookmarkStartupTask = new BookmarkStartupTask();		
		bookmarkStartupTask.execute();
		
	}
	
	private void addEventsBookmarkListView() {

		ListView bookmarkListView = (ListView) this.findViewById(android.R.id.list);

		bookmarkListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				
				Intent myIntent = new Intent(ListBookmarkActivity.this, EditBookmarkActivity.class);						
				myIntent.putExtra(SELECTED_BOOKMARK, ListBookmarkActivity.this.getBookmark(position));
				
				ListBookmarkActivity.this.startActivity(myIntent);
				
			}		

		});

	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
        getMenuInflater().inflate(R.menu.list_bookmark, menu);
        
        return true;
        
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

			case R.id.newBookmark:

				Intent myIntent = new Intent(this, AddBookmarkActivity.class);
				this.startActivity(myIntent);

				break;

			case R.id.reloadBookmark:
				
				this.dialog = ProgressDialog.show(this, "", "Carregando Bookmarks. Por favor, aguarde...", true);
				
				BookmarkStartupTask bookmarkStartupTask = new BookmarkStartupTask();		
				bookmarkStartupTask.execute();
				
				break;

			default:
				
				break;

		}

		return true;
	}

	private class BookmarkStartupTask extends AsyncTask<Void, Void, List<Bookmark>> {

		@Override
		protected List<Bookmark> doInBackground(Void... params) {

			List<Bookmark> listBookmarks = null;

			try {

				BookmarkService bookmarkService = new BookmarkService();
				
				listBookmarks = bookmarkService.findAll();

			} catch (Exception e) {
			}

			return listBookmarks;
		}

		@Override
		protected void onPostExecute(List<Bookmark> listBookmarks) {

			ListBookmarkActivity.this.dialog.cancel();
			
			ListBookmarkActivity.this.adapter.clear();
			ListBookmarkActivity.this.adapter.addAll(listBookmarks);
			
			ListBookmarkActivity.this.adapter.notifyDataSetChanged();
			
		}
	}
    
	private Bookmark getBookmark(int position) {
		
		ListView bookmarkListView = (ListView) this.findViewById(android.R.id.list);
		
		return (Bookmark) bookmarkListView.getItemAtPosition(position);
		
	}
	
}
