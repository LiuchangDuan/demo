package com.example.imagedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/6/2.
 */
public class PixelsEffect extends Activity {

    private ImageView imageView1, imageView2, imageView3, imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pixels_effect);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test2);

        imageView1 = (ImageView) findViewById(R.id.imageview1);
        imageView2 = (ImageView) findViewById(R.id.imageview2);
        imageView3 = (ImageView) findViewById(R.id.imageview3);
        imageView4 = (ImageView) findViewById(R.id.imageview4);

        //原图
        imageView1.setImageBitmap(bitmap);
        //底片效果
        imageView2.setImageBitmap(ImageHelper.handleImageNegative(bitmap));
        //怀旧效果
        imageView3.setImageBitmap(ImageHelper.handleImagePixelOldPhoto(bitmap));
        //浮雕效果
        imageView4.setImageBitmap(ImageHelper.handleImagePixelsRelief(bitmap));

    }
}
