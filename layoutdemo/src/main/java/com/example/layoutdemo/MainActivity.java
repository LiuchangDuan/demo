package com.example.layoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ViewStub stub;
    TextView titl_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        titl_tv = (TextView) findViewById(R.id.title);
        titl_tv.setText("自定义布局");

        button = (Button) findViewById(R.id.button);
        stub = (ViewStub) findViewById(R.id.stub);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stub.inflate();
            }
        });
    }
}
