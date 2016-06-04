package com.example.imagedemo02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/4.
 */
public class MeshView extends View {

    //将图像分成多少格
    private int WIDTH = 200;
    private int HEIGHT = 200;

    //交点坐标的个数
    private int COUNT = (WIDTH + 1) * (HEIGHT + 1);

    //用于保存COUNT的坐标
    //x0, y0, x1, y1......
    private float[] verts = new float[COUNT * 2];

    //用于保存原始的坐标
    private float[] orig = new float[COUNT * 2];

    private Bitmap mBitmap;

    private float K = 1;


    public MeshView(Context context) {
        super(context);
        initView();
    }

    public MeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        int index = 0;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        float bmWidth = mBitmap.getWidth();
        float bmHeight = mBitmap.getHeight();

        for (int i = 0; i < HEIGHT + 1; i++) {
            float fy = bmHeight * i / HEIGHT;
            for (int j = 0; j < WIDTH + 1; j++) {
                float fx = bmWidth * j / WIDTH;
                //X轴坐标 放在偶数位
                verts[index * 2 + 0] = fx;
                orig[index * 2 + 0] = verts[index * 2 + 0];
                //Y轴坐标 放在奇数位
                //向下移动200
                verts[index * 2 + 1] = fy + 200;
                orig[index * 2 + 1] = verts[index * 2 + 1];
                index += 1;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < HEIGHT + 1; i++) {
            for (int j = 0; j < WIDTH + 1; j++) {
                verts[(i * (WIDTH + 1) + j) * 2 + 0] += 0;
                //利用正弦函数的周期性
                float offsetY = (float) Math.sin((float) j / WIDTH * 2 * Math.PI + K * 2 * Math.PI);
                verts[(i * (WIDTH + 1) + j) * 2 + 1] = orig[(i * (WIDTH + 1) + j) * 2 + 1] + offsetY * 50;
            }
        }

        //平移 旗帜飘扬效果
        K += 0.1F;

        canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);

        invalidate();

    }
}
