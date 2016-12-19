package com.ptn.imageuploader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class TestActivity extends Activity{
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
         
        // Loader image - will be shown before loading image
        int loader = R.drawable.ic_launcher;
         
        // Imageview to show
        ImageView image = (ImageView) findViewById(R.id.ivPicture);
         
        // Image url
        String image_url = "http://172.16.33.15/imageuploader/uploads/34.jpeg";
         
        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(getApplicationContext());
         
        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView 
        imgLoader.DisplayImage(image_url, loader, image);
    }
	
}
