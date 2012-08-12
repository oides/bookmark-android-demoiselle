package br.gov.serpro.bookmarkandroid.edit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import br.gov.serpro.bookmarkandroid.R;
import br.gov.serpro.bookmarkandroid.business.domain.Bookmark;
import br.gov.serpro.bookmarkandroid.business.service.BookmarkService;
import br.gov.serpro.bookmarkandroid.list.ListBookmarkActivity;

public class EditBookmarkActivity extends Activity {
	
	private ProgressDialog dialog = null;
	private EditText description;
	private EditText link;
	private String id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.add_bookmark);

		this.loadEditFields();
		
	}

	private void loadEditFields() {
		
		this.description = (EditText) findViewById(R.id.description);
		this.link = (EditText) findViewById(R.id.link);
				
		Bookmark bookmark = (Bookmark) super.getIntent().getExtras().get(ListBookmarkActivity.SELECTED_BOOKMARK);
		
		this.id = bookmark.getId().toString();
		this.description.setText(bookmark.getDescription());
		this.link.setText(bookmark.getLink());
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.edit_bookmark, menu);

		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

//		Intent myIntent = null;

		switch (item.getItemId()) {

			case R.id.saveBookmark:
	
				this.dialog = ProgressDialog.show(this, "", "Salvando Bookmark. Por favor, aguarde...", true);
	
				BookmarkEditTask bookmarkEditTask = new BookmarkEditTask();		
				bookmarkEditTask.execute(this.id, this.description.getText().toString(), this.link.getText().toString());
				
				break;
	
			case R.id.deleteBookmark:
	
				this.dialog = ProgressDialog.show(this, "", "Removendo Bookmark. Por favor, aguarde...", true);
	
				BookmarkDeleteTask bookmarkDeleteTask = new BookmarkDeleteTask();		
				bookmarkDeleteTask.execute(this.id);
				
				break;

			case android.R.id.home:

//				myIntent = new Intent(this, ListFlightActivity.class);
//				myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//				this.startActivity(myIntent);

				break;

			default:
				break;

		}

		return true;
	}

	private class BookmarkEditTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {

			try {

				BookmarkService bookmarkService = new BookmarkService();
				
				bookmarkService.update(params[0], params[1], params[2]);

			} catch (Exception e) {
			}
			
			return null;
			
		}

		@Override
		protected void onPostExecute(Void param) {

			EditBookmarkActivity.this.dialog.cancel();
			
			Intent intent = new Intent(EditBookmarkActivity.this, ListBookmarkActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			EditBookmarkActivity.this.startActivity(intent);
			
		}
	}

	private class BookmarkDeleteTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {

			try {

				BookmarkService bookmarkService = new BookmarkService();
				
				bookmarkService.delete(params[0]);

			} catch (Exception e) {
			}
			
			return null;
			
		}

		@Override
		protected void onPostExecute(Void param) {

			EditBookmarkActivity.this.dialog.cancel();
			
			Intent intent = new Intent(EditBookmarkActivity.this, ListBookmarkActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			EditBookmarkActivity.this.startActivity(intent);
			
		}
	}

}
