package com.example.gifdecodedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

public class GifView extends View {

    private Movie mMovie;
    private long mMovieStart;
    private int mResourceId;

    public GifView(Context context, int mResourceId) {
        super(context);
        setFocusable(true);
        InputStream is = context.getResources().openRawResource(mResourceId);
        mMovie = Movie.decodeStream(is);
    }

    public GifView(Context context, AttributeSet attr) {
        super(context, attr);
        setFocusable(true);
        InputStream is = context.getResources().openRawResource(mResourceId);
        mMovie = Movie.decodeStream(is);
    }

    public GifView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        setFocusable(true);
        InputStream is = context.getResources().openRawResource(mResourceId);
        mMovie = Movie.decodeStream(is);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//       canvas.drawColor(Color.TRANSPARENT);
        final long now = SystemClock.uptimeMillis();
        if (mMovieStart == 0) {
            mMovieStart = now;
        }
        if (mMovie != null) {
            int dur = mMovie.duration();
//         Log.d("gif Canvas", "duration: " + mMovie.duration());
            if (dur == 0) {
                dur = 4000;
            }

            int relTime = (int) ((now - mMovieStart) % dur);
            mMovie.setTime(relTime);
            mMovie.draw(canvas, 10, 10);
//         Log.d("gif Canvas", mMovie.width() + "x" + mMovie.height());
            invalidate();
        }
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

}
