package com.example.imageloaddemo;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.LruCache;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/6/5.
 *
 * 图片加载类
 *
 */
public class ImageLoader {

    private static ImageLoader mInstance;

    //图片缓存的核心对象
    private LruCache<String, Bitmap> mLruCache;

    //线程池
    private ExecutorService mThreadPool;

    private static final int DEFAULT_THREAD_COUNT = 1;

    //队列的调度方式
    private Type mType = Type.LIFO;

    //任务队列
    private LinkedList<Runnable> mTaskQueue;

    //后台轮询线程
    private Thread mPoolThread;

    private Handler mPoolThreadHandler;

    //UI线程中的Handler
    private Handler mUIHandler;



    public enum Type {
        FIFO, LIFO;
    }

    private ImageLoader(int threadCount, Type type) {
        init(threadCount, type);
    }

    /**
     * 初始化
     * @param threadCount
     * @param type
     */
    private void init(int threadCount, Type type) {

        //后台轮询线程
        mPoolThread = new Thread(){

            @Override
            public void run () {
                Looper.prepare();
                mPoolThreadHandler = new Handler() {

                    @Override
                    public void handleMessage(Message msg) {
                        //线程池去取出一个任务进行执行

                    }

                };
                Looper.loop();
            }

        };

        mPoolThread.start();

        //获取我们应用的最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();

        int cacheMemory = maxMemory / 8;

        mLruCache = new LruCache<String, Bitmap>(cacheMemory) {

            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

        //创建线程池
        mThreadPool = Executors.newFixedThreadPool(threadCount);

        mTaskQueue = new LinkedList<Runnable>();

        mType = type;

    }

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized(ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(DEFAULT_THREAD_COUNT, Type.LIFO);
                }
            }
        }

        return mInstance;

    }

}
