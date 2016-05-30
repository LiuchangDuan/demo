package com.example.progressbardemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.view.HorizontalProgressBarWithProgress;
import com.example.view.RoundProgressBarWithProgress;

public class MainActivity extends AppCompatActivity {

    private final int MSG_UPDATE = 0x110;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int progress = mHProgress.getProgress();
            mHProgress.setProgress(++progress);
            mRProgress.setProgress(++progress);
            if (progress >= 100) {
                mHandler.removeMessages(MSG_UPDATE);
            }
            mHandler.sendEmptyMessageDelayed(MSG_UPDATE, 100);
        }
    };

    private HorizontalProgressBarWithProgress mHProgress;

    private RoundProgressBarWithProgress mRProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHProgress = (HorizontalProgressBarWithProgress) findViewById(R.id.id_progress01);

        mRProgress = (RoundProgressBarWithProgress) findViewById(R.id.id_progress02);

        mHandler.sendEmptyMessage(MSG_UPDATE);
    }
}
