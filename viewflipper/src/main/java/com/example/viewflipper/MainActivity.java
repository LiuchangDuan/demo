package com.example.viewflipper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper flipper;
    private int[] resId = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};
    private float startX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        //动态导入的方式为ViewFlipper加入子View
        for (int i = 0; i < resId.length; i++) {
            flipper.addView(getImageView(resId[i]));
        }
//        //为ViewFlipper添加动画效果
//        flipper.setInAnimation(this, R.anim.left_in);
//        flipper.setOutAnimation(this, R.anim.left_out);
//        //设定ViewFlipper视图切换的时间间隔
//        flipper.setFlipInterval(3000);
//        //开始播放
//        flipper.startFlipping();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            //手指落下
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            //手指滑动
            case MotionEvent.ACTION_MOVE:
                break;
            //手指离开
            case MotionEvent.ACTION_UP:
                //向右滑动（看前一页）
                if (event.getX() - startX > 100) {
//                    flipper.setInAnimation(this, R.anim.left_in);
//                    flipper.setInAnimation(this, R.anim.left_out);
                    flipper.showPrevious();//显示前一页
                }
                //向左滑动（看后一页）
                if (startX - event.getX() > 100) {
//                    flipper.setInAnimation(this, R.anim.right_in);
//                    flipper.setInAnimation(this, R.anim.right_out);
                    flipper.showNext();//显示后一页
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private ImageView getImageView(int resId) {
        ImageView image = new ImageView(this);
        //按图片宽高
//        image.setImageResource(resId);
        //铺满整个屏幕
        image.setBackgroundResource(resId);
        return image;
    }

}
