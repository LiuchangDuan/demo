package com.example.imagedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/6/2.
 */
public class ColorMatrix extends Activity {

    private ImageView mImageView;
    private GridLayout mGroup;
    private Bitmap bitmap;

    private int mEtWidth, mEtHeight;

    private EditText[] mEts = new EditText[20];

    //对应颜色矩阵
    private float[] mColorMatrix = new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_matrix);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mGroup = (GridLayout) findViewById(R.id.group);
        mImageView.setImageBitmap(bitmap);

        //自动优化成Lambda表达式了？？？
        mGroup.post(new Runnable() {

            @Override
            public void run() {
                //控件绘制完成后再去获取宽高
                //若直接在onCreate中去获取
                //则会得到两个0
                //绘制后执行
                mEtWidth = mGroup.getWidth() / 5;
                mEtHeight = mGroup.getHeight() / 5;
                addEts();
                initMatrix();//赋值
            }
        });
    }

    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
        }
    }

    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        android.graphics.ColorMatrix colorMatrix = new android.graphics.ColorMatrix();
        colorMatrix.set(mColorMatrix);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        mImageView.setImageBitmap(bmp);
    }

    public void btnChange(View view) {
        getMatrix();
        setImageMatrix();
    }

    public void btnReset(View view) {
        initMatrix();
        getMatrix();
        setImageMatrix();
    }

    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(ColorMatrix.this);
            mEts[i] = editText;
            mGroup.addView(editText, mEtWidth, mEtHeight);
        }
    }

    /**
     * 初始矩阵
     * 4行5列
     * 0/6/12/18 为1
     * 其余为0
     */
    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

}
