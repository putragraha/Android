package com.ptn.imageuploader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final int SELECT_PHOTO = 1;
	private ProgressDialog pDialog;
	
	// To get path
	private Uri imageUri;
	
	// To encode image
	private Bitmap bmp;
	private ByteArrayOutputStream byteArrayOutputStream ;
	private byte[] byteArray;
	
	Button btnUpload, btnChoose;
	ImageView ivPicture;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		byteArrayOutputStream = new ByteArrayOutputStream();
		
		btnUpload = (Button) findViewById(R.id.btnUpload);
		btnChoose = (Button) findViewById(R.id.btnChoose);
		ivPicture = (ImageView) findViewById(R.id.ivPicture);
		
		btnChoose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent picPickerIntent = new Intent(Intent.ACTION_PICK);
				picPickerIntent.setType("image/*");
				startActivityForResult(picPickerIntent, SELECT_PHOTO);
			}
		});
		
		btnUpload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new SendPicture().execute();
			}
		});
	}
	
	// Get Image after picked
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
	        super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

	        switch(requestCode) { 
	        case SELECT_PHOTO:
	            if(resultCode == RESULT_OK){
					try {
						// To get path 
						imageUri = imageReturnedIntent.getData();
						
						// To display image
						final InputStream imageStream = getContentResolver().openInputStream(imageUri);
						final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
						ivPicture.setImageBitmap(selectedImage);
						
						// To upload image
						bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

	            }
	        }
	    }

	 
	 /**
		 * Async task class to send json 
		 */
		private class SendPicture extends AsyncTask<Void, Void, String> {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				// Showing progress dialog
				pDialog = new ProgressDialog(MainActivity.this);
				pDialog.setMessage("Adding your data...");
				pDialog.setCancelable(false);
				pDialog.show();

			}
			
			@Override
			protected String doInBackground(Void... params) {
				
				bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
				byteArray = byteArrayOutputStream.toByteArray();
				String convertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
				File file = new File(imageUri.getPath());
				String nameImage = file.getName();
//				Log.d(TAG, convertImage);
//	                Log.d(TAG, file.getName());
//	                Log.d(TAG, imageUri.toString());
				
				String result = "";
				
				try {
					HttpHandler sj = new HttpHandler();
					JSONObject resObj = new JSONObject(sj.sendJson(HttpHandler.MYURL + HttpHandler.INSERT, nameImage, convertImage));
					JSONArray resArr = resObj.getJSONArray(Static.POSTS);
					result = resArr.getString(0);
				} catch (JSONException e) {
					Log.i(TAG, "JSON parse error " + e.getMessage());
				}
				
				return result;
			}
			
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				// Dismiss the progress dialog
				if (pDialog.isShowing())
					pDialog.dismiss();
				
				Log.d(TAG, result);
				/**
				 * Show insert information
				 * */
				if (result.equals(Static.SUCCESS)) {
					Toast.makeText(MainActivity.this, "Image uploaded", Toast.LENGTH_LONG).show();
					Intent iMain = new Intent(MainActivity.this, ViewActivity.class);
					finish();
					startActivity(iMain);
				} else if (result.equals(Static.FAIL)) {
					Toast.makeText(MainActivity.this, "Fail to upload image", Toast.LENGTH_LONG).show();
				}
				
			}
		
		}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
