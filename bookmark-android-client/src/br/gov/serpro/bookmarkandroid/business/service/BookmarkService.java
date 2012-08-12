package br.gov.serpro.bookmarkandroid.business.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.gov.serpro.bookmarkandroid.business.domain.Bookmark;

public class BookmarkService {

	private final static String SERVICE_URI = "http://bookmark-mobile-gae-2.appspot.com/rest/bookmark";
	
	public List<Bookmark> findAll() throws Exception {

		return this.executeServiceArray("/findAll");
		
	}
	
	public void delete(String bookmarkId) throws Exception {
		
		this.executeServiceVoid("/delete/" + encode(bookmarkId));
		
	}

	public void insert(String description, String link) throws Exception {
		
		this.executeServiceVoid("/insert/" + encode(description) + "/" + encode(link));
		
	}

	public void update(String bookmarkId, String description, String link) throws Exception {
		
		this.executeServiceVoid("/update/" + encode(bookmarkId) + "/" + encode(description) + "/" + encode(link));
		
	}

	private List<Bookmark> executeServiceArray(String operation) throws Exception {
		
		List<Bookmark> retorno = null;

		HttpGet request = new HttpGet(SERVICE_URI + operation);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		DefaultHttpClient httpClient = new DefaultHttpClient();

		String theString = new String("");

		try {
			
			HttpResponse response = httpClient.execute(request);
			HttpEntity responseEntity = response.getEntity();
			InputStream stream = responseEntity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			stream.close();
			theString = builder.toString();

			System.out.println(theString);
			retorno = this.formateJSONArray(theString);

		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new Exception("Infelizmente o servidor não está respondendo.");
			
		}

		return retorno;		
		
	}
	
	private void executeServiceVoid(String operation) throws Exception {
		
		HttpGet request = new HttpGet(SERVICE_URI + operation);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		DefaultHttpClient httpClient = new DefaultHttpClient();

		try {
			
			httpClient.execute(request);

		} catch (Exception e) {
			
			e.printStackTrace();
			
			throw new Exception("Infelizmente o servidor não está respondendo.");
			
		}
		
	}
	
	private List<Bookmark> formateJSONArray(String theString) throws JSONException {

		List<Bookmark> flightsList = new ArrayList<Bookmark>();
		
		JSONArray jsonArray = new JSONArray(theString);
		
		for (int i = 0; i < jsonArray.length(); i++) {
			
			flightsList.add(this.loadBookmark(jsonArray.getJSONObject(i)));
			
		}
		
		return flightsList;
	}
	
	private Bookmark loadBookmark(JSONObject json) throws JSONException {
		
		return new Bookmark(json.getLong("id"), json.getString("description"), json.getString("link"));
		
	}

	private String encode(String param) throws UnsupportedEncodingException {
		
		return URLEncoder.encode(param, "UTF-8").replace("+", "%20");
		
	}
}
