package com.example.imagedemo02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/3.
 */
public class BitmapShaderView extends View {

    private Bitmap mBitmap;
    private Paint mPaint;
    private BitmapShader mBitmapShader;

    public BitmapShaderView(Context context) {
        super(context);
    }

    public BitmapShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint = new Paint();

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);

        //拉伸 对最下面一个像素进行拉伸
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //重复
//        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        //镜像 MIRROR
//        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);

        //绑上图章
        mPaint.setShader(mBitmapShader);

//        canvas.drawCircle(300, 200, 150, mPaint);
        canvas.drawCircle(600, 200, 200, mPaint);
    }
}
