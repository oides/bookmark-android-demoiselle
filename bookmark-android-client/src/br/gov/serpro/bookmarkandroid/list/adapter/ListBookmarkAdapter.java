package br.gov.serpro.bookmarkandroid.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.gov.serpro.bookmarkandroid.business.domain.Bookmark;

public class ListBookmarkAdapter extends ArrayAdapter<Bookmark> {

	private Context context;

	public ListBookmarkAdapter(Context context) {

		super(context, android.R.layout.simple_list_item_1);

		this.context = context;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

		((TextView) rowView).setText(this.getItem(position).getDescription());
		
		return rowView;

	}
}
