package com.example.fragmentpageradapterdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;

    private FragmentPagerAdapter mAdapter;

    private List<Fragment> mFragments;

    private LinearLayout mTabWeChat;
    private LinearLayout mTabFriend;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSettings;

    private ImageButton mImgWeChat;
    private ImageButton mImgFriend;
    private ImageButton mImgAddress;
    private ImageButton mImgSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();

        initEvents();

        setSelect(1);

    }

    private void initEvents() {

        mTabWeChat.setOnClickListener(this);
        mTabFriend.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSettings.setOnClickListener(this);

    }

    private void initView() {

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabWeChat = (LinearLayout) findViewById(R.id.id_tab_wechat);
        mTabFriend = (LinearLayout) findViewById(R.id.id_tab_friend);
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabSettings = (LinearLayout) findViewById(R.id.id_tab_settings);

        mImgWeChat = (ImageButton) findViewById(R.id.id_tab_wechat_image);
        mImgFriend = (ImageButton) findViewById(R.id.id_tab_friend_image);
        mImgAddress = (ImageButton) findViewById(R.id.id_tab_address_image);
        mImgSettings = (ImageButton) findViewById(R.id.id_tab_settings_image);

        mFragments = new ArrayList<Fragment>();
        Fragment mTab01 = new WeChatFragment();
        Fragment mTab02 = new FriendFragment();
        Fragment mTab03 = new AddressFragment();
        Fragment mTab04 = new SettingsFragment();
        mFragments.add(mTab01);
        mFragments.add(mTab02);
        mFragments.add(mTab03);
        mFragments.add(mTab04);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {

//        resetImgs();

        switch (v.getId()) {
            case R.id.id_tab_wechat:
                setSelect(0);
                break;
            case R.id.id_tab_friend:
                setSelect(1);
                break;
            case R.id.id_tab_address:
                setSelect(2);
                break;
            case R.id.id_tab_settings:
                setSelect(3);
                break;
        }
    }

    private void setSelect(int i) {

        setTab(i);

        mViewPager.setCurrentItem(i);

    }

    private void setTab(int i ) {

        resetImgs();

        //设置图片为亮色
        //切换内容区域
        switch (i) {
            case 0:
                mImgWeChat.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:
                mImgFriend.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:
                mImgAddress.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:
                mImgSettings.setImageResource(R.drawable.tab_settings_pressed);
                break;
        }

    }

    /**
     * 切换图片至暗色
     */
    private void resetImgs() {
        mImgWeChat.setImageResource(R.drawable.tab_weixin_normal);
        mImgFriend.setImageResource(R.drawable.tab_find_frd_normal);
        mImgAddress.setImageResource(R.drawable.tab_address_normal);
        mImgSettings.setImageResource(R.drawable.tab_settings_normal);
    }

}
