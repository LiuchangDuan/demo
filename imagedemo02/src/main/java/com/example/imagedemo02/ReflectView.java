package com.example.imagedemo02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/4.
 */
public class ReflectView extends View {

    private Bitmap mSrcBitmap;
    private Bitmap mRefBitmap;

    private Paint mPaint;

    public ReflectView(Context context) {
        super(context);
        initView();
    }

    public ReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        Matrix matrix = new Matrix();
        //X不变，Y取反 X轴对称
        matrix.setScale(1, -1);
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0, mSrcBitmap.getWidth(),
                mSrcBitmap.getHeight(), matrix, true);
        mPaint = new Paint();
        //设置渐变效果
//        mPaint.setShader(new LinearGradient(0, mSrcBitmap.getHeight(), 0, mSrcBitmap.getHeight() * 2,
//                Color.BLUE, Color.RED, Shader.TileMode.CLAMP));
        mPaint.setShader(new LinearGradient(0, mSrcBitmap.getHeight(), 0, mSrcBitmap.getHeight() * 1.4F,
                0xDD000000, 0x10000000, Shader.TileMode.CLAMP));
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        //倒影效果
        canvas.drawBitmap(mRefBitmap, 0, mSrcBitmap.getHeight(), null);
        canvas.drawRect(0, mRefBitmap.getHeight(), mRefBitmap.getWidth(),
                mRefBitmap.getHeight() * 2, mPaint);
    }
}
