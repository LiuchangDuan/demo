package com.example.progressbardemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.view.HorizontalProgressBarWithProgress;

public class MainActivity extends AppCompatActivity {

    private final int MSG_UPDATE = 0x110;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int progress = mHProgress.getProgress();
            mHProgress.setProgress(++progress);
            if (progress >= 100) {
                mHandler.removeMessages(MSG_UPDATE);
            }
            mHandler.sendEmptyMessageDelayed(MSG_UPDATE, 100);
        }
    };

    private HorizontalProgressBarWithProgress mHProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHProgress = (HorizontalProgressBarWithProgress) findViewById(R.id.id_progress01);

        mHandler.sendEmptyMessage(MSG_UPDATE);
    }
}
