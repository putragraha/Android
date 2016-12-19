package com.ptn.imageuploader;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Adapter extends BaseAdapter {
	private static ArrayList<Image> listImage;
	private LayoutInflater mInflater;
	private ImageLoader imageLoader;
//	private Bitmap bmp;
	
	public Adapter(Context context, ArrayList<Image> con) {
		listImage = con;
		mInflater = LayoutInflater.from(context);
		imageLoader = new ImageLoader(context);
	}
	
	@Override
	public int getCount() {
		return listImage.size();
	}

	@Override
	public Object getItem(int position) {
		return listImage.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder;
		
		// Initiate view holder
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, null);
			mHolder = new ViewHolder();
			mHolder.ivPicture = (ImageView) convertView.findViewById(R.id.ivPicture);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder)convertView.getTag();
		}
		
		// set view content
		//Log.d("AdapterKencur", HttpHandler.MYURL+listImage.get(position).getImage());
//		ImageLoader imgLoader = new ImageLoader(ctx);
//		imgLoader.DisplayImage(HttpHandler.MYURL+listImage.get(position).getImage(), R.drawable.ic_launcher, mHolder.ivPicture);
//		try {
//			InputStream in = new java.net.URL(HttpHandler.MYURL+listImage.get(position).getImage()).openStream();
//			bmp = BitmapFactory.decodeStream(in);
//		} catch (MalformedURLException e) {
//			Log.v("Adapter", e.getMessage());
//		} catch (IOException e) {
//			Log.v("Adapter", e.getMessage());
//		} 
		
        
		//mHolder.ivPicture.setImageBitmap(bmp);
//		URL url = null;
//		try {
//			url = new URL(HttpHandler.MYURL+listImage.get(position).getImage());
//		} catch (MalformedURLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		InputStream content = null;
//		try {
//			content = (InputStream)url.getContent();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Drawable d = Drawable.createFromStream(content , "src"); 
//		mHolder.ivPicture.setImageDrawable(d);
		
		//mHolder.ivPicture.setImageBitmap(bmp);
		String url = HttpHandler.MYURL+listImage.get(position).getImage();
//		String url = "http://172.16.33.15/imageuploader/uploads/34.png";
		Log.d("AdapterPosition", String.valueOf(position));
		Log.d("AdapterURL", url);
		imageLoader.DisplayImage(url, R.drawable.ic_launcher, mHolder.ivPicture);
		
		return convertView;
	}
	
	static class ViewHolder {
		ImageView ivPicture;
	}

}
