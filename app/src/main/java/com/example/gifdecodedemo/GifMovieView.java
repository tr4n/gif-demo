package com.example.gifdecodedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.os.SystemClock;
import android.view.View;

import java.io.InputStream;

public class GifMovieView extends View {

    private Movie mMovie;
    private InputStream mInputStream;
    private long mMoviestart;

    public GifMovieView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);
        final long now = SystemClock.uptimeMillis();
        if (mMoviestart == 0) mMoviestart = now;
        Paint p = new Paint();
        p.setAntiAlias(true);
        final int relTime = (int) ((now - mMoviestart) % mMovie.duration());
        mMovie.setTime(relTime);
        mMovie.draw(canvas, 10, 10);
        this.invalidate();
    }

    public void init(InputStream inputStream) {
        this.mInputStream = inputStream;
        this.mMovie = Movie.decodeStream(mInputStream);
    }
}

