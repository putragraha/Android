package com.ptn.imageuploader;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ViewActivity extends Activity{

	private static final String TAG = ViewActivity.class.getSimpleName();
	private ListView lv;
	private Button btnNew;
	private ProgressDialog pDialog;
	ArrayList<Image> listImage = new ArrayList<Image>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		
		lv = (ListView) findViewById(R.id.list);
		btnNew = (Button) findViewById(R.id.btnNew);
		
		btnNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent iNew = new Intent(ViewActivity.this, MainActivity.class);
				startActivity(iNew);
			}
		});
		
		// Load images
		new GetImages().execute();
	}
	
	/**
	 * Async task class to get json by making HTTP call
	 */
	public class GetImages extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(ViewActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... params) {
			HttpHandler sh = new HttpHandler();

			// Making a request to url and getting response
			String jsonStr = sh.callJson();

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);

					// Getting JSON Array node
					JSONArray images = jsonObj.getJSONArray(Static.IMAGE);
//					JSONArray contacts = new JSONArray(jsonStr);
					if (!images.getJSONObject(0).equals(Static.EMPTY)) {
						// looping through All Contacts
						for (int i = 0; i < images.length(); i++) {
							JSONObject c = images.getJSONObject(i);
							Image img = new Image();

							img.setImage(c.getString(Static.IMAGE));
							
							// adding contact to contact list
							listImage.add(img);
						}
					} 
				} catch (final JSONException e) {
					Log.e(TAG, "Json parsing error: " + e.getMessage());
				}
			} else {
				Log.e(TAG, "Couldn't get json from server.");
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(
								getApplicationContext(),
								"Couldn't get json from server. Check LogCat for possible errors!",
								Toast.LENGTH_LONG).show();
					}
				});

			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			if (listImage.size() > 0) {
				Adapter adapter = new Adapter(getApplicationContext(), listImage);
				lv.setAdapter(adapter);
			} 
		}

	}
}
