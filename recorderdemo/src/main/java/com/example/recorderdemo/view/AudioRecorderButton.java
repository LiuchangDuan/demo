package com.example.recorderdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.recorderdemo.R;

/**
 * Created by Administrator on 2016/6/5.
 */
public class AudioRecorderButton extends Button {

    private static final int DISTANCE_Y_CANCEL = 50;

    //默认状态
    private static final int STATE_NORMAL = 1;
    //录音状态
    private static final int STATE_RECORDING = 2;
    //取消状态
    private static final int STATE_WANT_TO_CANCEL = 3;

    //当前记录状态
    private int mCurState = STATE_NORMAL;

    //已经开始录音
    private boolean isRecording = false;

    public AudioRecorderButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AudioRecorderButton(Context context) {
        this(context, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //TODO 用于测试
                isRecording = true;
                chanegState(STATE_RECORDING);
                break;
            case MotionEvent.ACTION_MOVE:

                if (isRecording) {
                    //根据x, y的坐标，判断是否想要取消
                    if (wantToCancel(x, y)) {
                        chanegState(STATE_WANT_TO_CANCEL);
                    } else {
                        chanegState(STATE_RECORDING);
                    }
                }

                break;
            case MotionEvent.ACTION_UP:

                if (mCurState == STATE_RECORDING) {
                    //release
                    //callbackToAct
                } else if (mCurState == STATE_WANT_TO_CANCEL) {
                    //cancel
                }

                reset();

                break;
        }

        return super.onTouchEvent(event);

    }

    /**
     * 恢复状态及标志位
     */
    private void reset() {
        isRecording = false;
        chanegState(STATE_NORMAL);
    }

    private boolean wantToCancel(int x, int y) {
        //判断手指的横坐标是否超出按钮范围
        if (x < 0 || x > getWidth()) {
            return true;
        }
        //判断手指的纵坐标是否超出按钮范围
        if (y < -DISTANCE_Y_CANCEL || y > getHeight() + DISTANCE_Y_CANCEL) {
            return true;
        }
        return false;
    }

    private void chanegState(int state) {
        if (mCurState != state) {
            mCurState = state;
            switch (state) {
                case STATE_NORMAL:
                    setBackgroundResource(R.drawable.btn_recorder_normal);
                    setText(R.string.str_recorder_normal);
                    break;
                case STATE_RECORDING:
                    setBackgroundResource(R.drawable.btn_recording);
                    setText(R.string.str_recorder_recording);
                    if (isRecording) {
                        //TODO Dialog.recording();
                    }
                    break;
                case STATE_WANT_TO_CANCEL:
                    setBackgroundResource(R.drawable.btn_recording);
                    setText(R.string.str_recorder_want_cancel);
                    //TODO Dialog.wantCancel();
                    break;
            }
        }
    }
}
