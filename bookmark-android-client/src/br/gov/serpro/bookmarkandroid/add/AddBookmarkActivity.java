package br.gov.serpro.bookmarkandroid.add;

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
import br.gov.serpro.bookmarkandroid.business.service.BookmarkService;
import br.gov.serpro.bookmarkandroid.list.ListBookmarkActivity;

public class AddBookmarkActivity extends Activity {
	
	private ProgressDialog dialog = null;
	private EditText description;
	private EditText link;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.add_bookmark);

		this.description = (EditText) findViewById(R.id.description);
		this.link = (EditText) findViewById(R.id.link);
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_bookmark, menu);

		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

//		Intent myIntent = null;

		switch (item.getItemId()) {

			case R.id.saveBookmark:

				this.dialog = ProgressDialog.show(this, "", "Salvando Bookmark. Por favor, aguarde...", true);

				BookmarkAddTask bookmarkAddTask = new BookmarkAddTask();		
				bookmarkAddTask.execute(this.description.getText().toString(), this.link.getText().toString());
				
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

	private class BookmarkAddTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {

			try {

				BookmarkService bookmarkService = new BookmarkService();
				
				bookmarkService.insert(params[0], params[1]);

			} catch (Exception e) {
			}
			
			return null;
			
		}

		@Override
		protected void onPostExecute(Void param) {

			AddBookmarkActivity.this.dialog.cancel();
			
			Intent intent = new Intent(AddBookmarkActivity.this, ListBookmarkActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			AddBookmarkActivity.this.startActivity(intent);
			
		}
	}

}
