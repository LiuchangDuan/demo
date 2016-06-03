package com.example.imagedemo02;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

/**
 * Created by Administrator on 2016/6/3.
 */
public class ImageMatrixTest extends Activity {

    private GridLayout mGridLayout;
    private ImageMatrixView mImageMatrixView;
    private int mEdWidth;
    private int mEdHeight;
    private float[] mImageMatrix = new float[9];
    private EditText[] mEts = new EditText[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix);
        mImageMatrixView = (ImageMatrixView) findViewById(R.id.view);
        mGridLayout = (GridLayout) findViewById(R.id.grid_group);

        mGridLayout.post(new Runnable() {
            @Override
            public void run() {

                mEdWidth = mGridLayout.getWidth() / 3;
                mEdHeight = mGridLayout.getHeight() / 3;

                addEts();

                initImageMatrix();

            }
        });

    }

    private void addEts() {
        for (int i = 0; i < 9; i++) {
            EditText et = new EditText(ImageMatrixTest.this);
            //居中显示
            et.setGravity(Gravity.CENTER);
            mEts[i] = et;
            mGridLayout.addView(et, mEdWidth, mEdHeight);
        }
    }

    /**
     * 初始矩阵
     * 1 0 0
     * 0 1 0
     * 0 0 1
     */
    private void initImageMatrix() {
        for (int i = 0; i < 9; i++) {
            if (i % 4 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

    private void getImageMatrix() {
        for (int i = 0; i < 9; i++) {
            EditText ed = mEts[i];
            mImageMatrix[i] = Float.parseFloat(ed.getText().toString());
        }
    }


    public void change(View view) {
        getImageMatrix();
        Matrix matrix = new Matrix();
//        matrix.setValues(mImageMatrix);
//        //X轴偏移量，Y轴偏移量
//        matrix.setTranslate(150, 150);
        matrix.setScale(2, 2);
        matrix.postTranslate(200, 200);
        //矩阵先乘和后乘是不一样的
        mImageMatrixView.setImageMatrix(matrix);
        mImageMatrixView.invalidate();
    }

    public void reset(View view) {
        initImageMatrix();
        getImageMatrix();
        Matrix matrix = new Matrix();
        matrix.setValues(mImageMatrix);
        mImageMatrixView.setImageMatrix(matrix);
        mImageMatrixView.invalidate();
    }


}
