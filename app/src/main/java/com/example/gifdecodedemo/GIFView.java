package com.example.gifdecodedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.io.IOException;

public class GIFView extends View {
    private static final String FILE_NAME = "imagegif.gif";
    private Movie movie;
    private long movieStart;
    public GIFView(Context context) throws IOException {
        super(context);
        movie=Movie.decodeStream(getResources().getAssets().open(FILE_NAME));
    }
    public GIFView(Context context, AttributeSet attrs) throws IOException{
        super(context, attrs);
        movie=Movie.decodeStream(getResources().getAssets().open(FILE_NAME));
    }
    public GIFView(Context context, AttributeSet attrs, int defStyle) throws IOException {
        super(context, attrs, defStyle);
        movie=Movie.decodeStream(getResources().getAssets().open(FILE_NAME));
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long now=android.os.SystemClock.uptimeMillis();
        Paint p = new Paint();
        p.setAntiAlias(true);
        if (movieStart == 0)
            movieStart = now;
        int relTime;
        relTime = (int)((now - movieStart) % movie.duration());
        movie.setTime(relTime);
        movie.draw(canvas,0,0);
        this.invalidate();
    }
}