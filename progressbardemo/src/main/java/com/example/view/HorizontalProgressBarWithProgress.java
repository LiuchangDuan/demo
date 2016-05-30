package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.example.progressbardemo.R;

/**
 * Created by Administrator on 2016/5/30.
 */
public class HorizontalProgressBarWithProgress extends ProgressBar {

    private static final int DEFAULT_TEXT_SIZE = 10; // sp
    private static final int DEFAULT_TEXT_COLOR = 0xFFFC00D1;
    private static final int DEFAULT_COLOR_UNREACH = 0xFFD3D6DA;
    private static final int DEFAULT_HEIGHT_UNREACH = 2; // dp
    private static final int DEFAULT_COLOR_REACH = DEFAULT_TEXT_COLOR;
    private static final int DEFAULT_HEIGHT_REACH = 2; // dp
    private static final int DEFAULT_TEXT_OFFSET = 10; // dp

    private int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
    private int mTextColor = DEFAULT_TEXT_COLOR;
    private int mUnReachColor = DEFAULT_COLOR_UNREACH;
    private int mUnReachHeight = dp2px(DEFAULT_HEIGHT_UNREACH);
    private int mReachColor = DEFAULT_COLOR_REACH;
    private int mReachHeight = dp2px(DEFAULT_HEIGHT_REACH);
    private int mTextOffset = dp2px(DEFAULT_TEXT_OFFSET);

    private Paint mPaint = new Paint();

    /**
     * 在onMeasure中初始化
     * 在onDraw中使用
     */
    private int mRealWidth;

    public HorizontalProgressBarWithProgress(Context context) {
        this(context, null);
    }

    public HorizontalProgressBarWithProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressBarWithProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义属性
        obtainStyledAttrs(attrs);
    }

    /**
     * 获取自定义属性
     *
     * @param attrs
     */
    private void obtainStyledAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs,
                R.styleable.HorizontalProgressBarWithProgress);

        mTextSize = (int) ta.getDimension(
                R.styleable.HorizontalProgressBarWithProgress_progress_text_size, mTextSize);

        mTextColor = ta.getColor(
                R.styleable.HorizontalProgressBarWithProgress_progress_text_color, mTextColor);

        mTextOffset = (int) ta.getDimension(
                R.styleable.HorizontalProgressBarWithProgress_progress_text_offset, mTextOffset);

        mUnReachColor = ta.getColor(
                R.styleable.HorizontalProgressBarWithProgress_progress_unreach_color, mUnReachColor);

        mUnReachHeight = (int) ta.getDimension(
                R.styleable.HorizontalProgressBarWithProgress_progress_unreach_height, mUnReachHeight);

        mReachColor = ta.getColor(
                R.styleable.HorizontalProgressBarWithProgress_progress_reach_color, mReachColor);

        mReachHeight = (int) ta.getDimension(
                R.styleable.HorizontalProgressBarWithProgress_progress_reach_height, mReachHeight);

        ta.recycle();

        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthVal = MeasureSpec.getSize(widthMeasureSpec);

        int height = measureHeight(heightMeasureSpec);

        //确定view的宽和高
        setMeasuredDimension(widthVal, height);

        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int measureHeight(int heightMeasureSpec) {

        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);

        //精确值（如match_parent、200dp一类）
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            //当前绘制底线（descent） 当前绘制顶线（ascent） ascent为负值
            //结果为文字的高度
            int textHeight = (int) (mPaint.descent() - mPaint.ascent());
            result = getPaddingTop() + getPaddingBottom() +
                    Math.max(Math.max(mReachHeight, mUnReachHeight), Math.abs(textHeight));

            //测量值不能超过给定的size值
            if (mode == MeasureSpec.AT_MOST) {
                return Math.min(result, size);
            }

        }

        return result;

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();

        canvas.translate(getPaddingLeft(), getHeight() / 2);

        boolean noNeedUnReach = false;

        //draw reach bar
        String text = getProgress() + "%";

        int textWidth = (int) mPaint.measureText(text);

        float radio = getProgress() * 1.0f / getMax();

        float progressX = radio * mRealWidth;

        if (progressX + textWidth > mRealWidth) {
            progressX = mRealWidth - textWidth;
            noNeedUnReach = true;
        }

        float endX = progressX - mTextOffset / 2;

        if (endX > 0) {
            mPaint.setColor(mReachColor);
            mPaint.setStrokeWidth(mReachHeight);
            canvas.drawLine(0, 0, endX, 0, mPaint);
        }

        //draw text
        mPaint.setColor(mTextColor);
        int y = (int) (-(mPaint.descent() + mPaint.ascent()) / 2);
        canvas.drawText(text, progressX, y, mPaint);

        //draw unreach bar
        if (!noNeedUnReach) {
            float start = progressX + mTextOffset / 2 + textWidth;
            mPaint.setColor(mUnReachColor);
            mPaint.setStrokeWidth(mUnReachHeight);
            canvas.drawLine(start, 0, mRealWidth, 0, mPaint);
        }

        canvas.restore();
    }

    private int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                getResources().getDisplayMetrics());
    }

    private int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal,
                getResources().getDisplayMetrics());
    }

}
