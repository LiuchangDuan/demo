package com.example.viewpagerdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private List<View> viewList;

    private ViewPager pager;

    private PagerTabStrip tab;

    private List<String> titleList;

    private List<Fragment> fragList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        viewList = new ArrayList<View>();

        /**
         * 通过View对象去作为ViewPager的数据源
         */
        View view1 = View.inflate(this, R.layout.view1, null);
        View view2 = View.inflate(this, R.layout.view2, null);
        View view3 = View.inflate(this, R.layout.view3, null);
        View view4 = View.inflate(this, R.layout.view4,null);

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        //为ViewPager页卡设置标题
        titleList = new ArrayList<String>();
        titleList.add("第一页");
        titleList.add("第二页");
        titleList.add("第三页");
        titleList.add("第四页");

        /**
         * 通过Fragment作为ViewPager的数据源
         */
        fragList = new ArrayList<Fragment>();
        fragList.add(new Fragment1());
        fragList.add(new Fragment2());
        fragList.add(new Fragment3());
        fragList.add(new Fragment4());

        //为PagerTabStrip设置一些属性
        tab = (PagerTabStrip) findViewById(R.id.tab);
        tab.setBackgroundColor(Color.YELLOW);
        tab.setTextColor(Color.RED);
        tab.setDrawFullUnderline(false);
        tab.setTabIndicatorColor(Color.GREEN);

        //初始化ViewPager
        pager = (ViewPager) findViewById(R.id.pager);

        //创建PagerAdapter的适配器
        MyPagerAdapter adapter = new MyPagerAdapter(viewList, titleList);

        //ViewPager加载适配器
//        pager.setAdapter(adapter);

        MyFragmentPagerAdapter adapter2 = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragList, titleList);
//        pager.setAdapter(adapter2);

        MyFragmentPagerAdapter2 adapter3 = new MyFragmentPagerAdapter2(getSupportFragmentManager(), fragList, titleList);
        pager.setAdapter(adapter3);
//        pager.setOnPageChangeListener(this); // deprecated
        pager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this, "当前是第" + (position + 1) + "个页面", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
