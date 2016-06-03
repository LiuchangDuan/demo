package com.example.imagedemo02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/3.
 */
public class RoundRectXfermodeView extends View {

    private Bitmap mBitmap;
    private Bitmap mOut;
    private Paint mPaint;

    public RoundRectXfermodeView(Context context) {
        super(context);
        initView();
    }

    public RoundRectXfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RoundRectXfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        //禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);

        mOut = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(mOut);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //Dst 遮罩层
        canvas.drawRoundRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(), 50, 50, mPaint);

        //只适用于具有alpha通道的图片
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //Src
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        mPaint.setXfermode(null);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mOut, 0, 0, null);
    }
}
