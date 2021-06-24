package com.example.canvas.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.canvas.R;

public class CustomView extends View {

    private static final int SquareSize= 200;
    private Rect mRectSquare;
    private Paint mPaintSquare;
    private int mSquareColor;
    private int mSquareSize;
    private Paint mPaintCircle;

    private float cx=0f,cy=0f;
    private float r = 100f;





    public CustomView(Context context) {
        super(context);
        init(null);

    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    private void init(@Nullable AttributeSet set){
        mRectSquare = new Rect();
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(Color.RED);

//        if(set==null){
//            return;
//        }

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.CustomView);
        mSquareColor = ta.getColor(R.styleable.CustomView_SquareColor, Color.GREEN);
        mSquareSize = ta.getDimensionPixelSize(R.styleable.CustomView_SquareSize, SquareSize);

        mPaintSquare.setColor(mSquareColor);

        ta.recycle();



    }

    public void SwapColor() {
        mPaintSquare.setColor(mPaintSquare.getColor() == Color.GREEN ? Color.RED : Color.GREEN);

        postInvalidate();
    }


    @Override
    public void onDraw(Canvas canvas){

        mRectSquare.left=50;
        mRectSquare.right=mRectSquare.left+ mSquareSize;
        mRectSquare.top=50;
        mRectSquare.bottom=mRectSquare.top + mSquareSize;

        if(cx==0||cy==0) {
            cx= getWidth()/2;
            cy= getHeight()/2;
        }


        canvas.drawRect(mRectSquare, mPaintSquare);
        canvas.drawCircle(cx, cy, r, mPaintCircle);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                return true;
            }

            case MotionEvent.ACTION_MOVE: {
                float x = event.getX();
                float y = event.getY();

                double dx = Math.pow(x-cx , 2 );
                double dy = Math.pow(y-cy, 2);

                if(dx+dy <Math.pow(r, 2)) {
                    cx=x;
                    cy=y;
                    postInvalidate();

                    return true;
                }

                return value;
            }
        }
        return value;
    }
}
