package com.example.imagedemo02;

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

    public void btnXfermode(View view) {
        startActivity(new Intent(this, RoundRectXfermodeTest.class));
    }

    public void btnMatrix(View view) {
        startActivity(new Intent(this, ImageMatrixTest.class));

    }

}
