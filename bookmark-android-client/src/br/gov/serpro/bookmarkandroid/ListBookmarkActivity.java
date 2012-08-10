package br.gov.serpro.bookmarkandroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class ListBookmarkActivity extends ListActivity {

	private ArrayAdapter<String> adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.list_bookmark);
        
        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"teste 1", "teste 2"});        
        getListView().setAdapter(this.adapter);
        
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

				Toast.makeText(this, "Novo Bookmark", Toast.LENGTH_SHORT).show();
//				Intent myIntent = new Intent(this, NewFlightActivity.class);
//				this.startActivity(myIntent);

				break;

			case R.id.reloadBookmark:
				
				Toast.makeText(this, "Atualizando Bookmark", Toast.LENGTH_SHORT).show();
				
				break;

			default:
				
				break;

		}

		return true;
	}

    
}
