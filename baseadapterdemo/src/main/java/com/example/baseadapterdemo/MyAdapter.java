package com.example.baseadapterdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/6/5.
 */
public class MyAdapter extends BaseAdapter {

    private List<ItemBean> mList;

    private LayoutInflater mInflater;

    //记录消耗的时间
    private long mSumTime;

    public MyAdapter(Context context, List<ItemBean> list) {
        mList = list;
        //context要使用当前的Adapter的界面对象
        //mInflater：布局装载器对象
        mInflater = LayoutInflater.from(context);
    }

    //返回ListView需要显示的数据数量
    @Override
    public int getCount() {
        return mList.size();
    }

    //获取数据集中与指定索引对应的数据项
    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    //返回指定索引对应的数据项
    @Override
    public long getItemId(int position) {
        return position;
    }

    //返回每一项的显示内容
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //****************************************************************************
        //没有利用到ListView的h缓存机制
        //没有任何优化，每次都创建新的View，设置控件
        //效率极其低下

//        long start = System.nanoTime();
//
//        //第一个参数：需要装载到item中的布局文件
//        //由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常写null
//        View view = mInflater.inflate(R.layout.item, null);
//
//        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
//
//        TextView title = (TextView) view.findViewById(R.id.tv_title);
//
//        TextView content = (TextView) view.findViewById(R.id.tv_content);
//
//        ItemBean bean = mList.get(position);
//
//        imageView.setImageResource(bean.ItemImageResId);
//
//        title.setText(bean.ItemTitle);
//
//        content.setText(bean.ItemContent);
//
//        long end = System.nanoTime();
//
//        long dValue = end - start;
//
//        mSumTime += dValue;
//
//        Log.d("Main", String.valueOf(mSumTime));
//
//        return view;
        //*********************************************************************************


        //**********************************************************************************

        /**
         *
         * 利用了ListView的缓存特性，如果没有缓存才创建新的View
         *
         * 但findViewById依然会浪费大量时间
         *
         */

//        long start = System.nanoTime();

        //为空：View未被实例化 缓存池中无缓存
//        if (convertView == null) {
//
//            convertView = mInflater.inflate(R.layout.item, null);
//
//        }
//
//        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
//
//        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
//
//        TextView content = (TextView) convertView.findViewById(R.id.tv_content);
//
//        ItemBean bean = mList.get(position);
//
//        imageView.setImageResource(bean.ItemImageResId);
//
//        title.setText(bean.ItemTitle);
//
//        content.setText(bean.ItemContent);
//
//        long end = System.nanoTime();
//
//        long dValue = end - start;
//
//        mSumTime += dValue;
//
//        Log.d("Main", String.valueOf(mSumTime));
//
//        return convertView;

        //************************************************************************************


        //************************************************************************************

        /**
         * 不仅利用了ListView的缓存
         * 更通过ViewHolder类来实现显示数据的视图的缓存
         * 避免多次通过findViewById寻找控件
         */

        //获取系统纳秒时间
        long start = System.nanoTime();

        ViewHolder viewHolder;

        //判断convertView是否为空
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
            //通过setTag将ViewHolder与convertView绑定
            convertView.setTag(viewHolder);
        } else {
            //getTag()取出关联的ViewHolder
            //通过ViewHolder对象找到对应控件
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemBean bean = mList.get(position);
        viewHolder.imageView.setImageResource(bean.ItemImageResId);
        viewHolder.title.setText(bean.ItemTitle);
        viewHolder.content.setText(bean.ItemContent);

        long end = System.nanoTime();

        long dValue = end - start;

        mSumTime += dValue;

        Log.d("Main", String.valueOf(mSumTime));

        return convertView;

    }

    //创建内部类ViewHolder
    //避免重复的findViewById
    class ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView content;
    }

}
