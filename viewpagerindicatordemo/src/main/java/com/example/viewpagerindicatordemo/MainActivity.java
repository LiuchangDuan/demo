package com.example.viewpagerindicatordemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {

    private ViewPager mViewPager;

    private TabPageIndicator mTabPageIndicator;

//    private List<Fragment> mFragments;

    private TabAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabPageIndicator = (TabPageIndicator) findViewById(R.id.id_indicator);

        mAdapter = new TabAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mAdapter);

        mTabPageIndicator.setViewPager(mViewPager, 0);

    }
}
