package com.example.gifdecodedemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        String urlString = "https://upload.wikimedia.org/wikipedia/commons/c/c0/An_example_animation_made_with_Pivot.gif";
        InputStream stream = null;
        try {
            //  GIFView gifView = new GIFView(this);
            stream = getAssets().open("imagegif.gif");
            GifDecoderView gifDecoderView = new GifDecoderView(this, stream);
            setContentView(gifDecoderView);
        } catch (IOException e) {
            e.printStackTrace();
            setContentView(R.layout.activity_main);
        }


    }

}


