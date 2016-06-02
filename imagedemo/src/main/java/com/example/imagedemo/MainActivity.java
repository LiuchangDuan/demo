package com.example.imagedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnPrimaryColor(View view) {
        startActivity(new Intent(this, PrimaryColor.class));
    }

    public void btnColorMatrix(View view) {
        startActivity(new Intent(this, ColorMatrix.class));
    }

    public void btnPixelsEffect(View view) {
        startActivity(new Intent(this, PixelsEffect.class));
    }

}
