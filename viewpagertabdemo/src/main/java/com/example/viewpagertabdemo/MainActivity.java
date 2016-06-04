package com.example.viewpagertabdemo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mViewPager;

    private PagerAdapter mAdapter;

    private List<View> mViews = new ArrayList<View>();

    //TAB
    private LinearLayout mTabWeChat;
    private LinearLayout mTabFriend;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSettings;

    private ImageButton mWeChatImg;
    private ImageButton mFriendImg;
    private ImageButton mAddressImg;
    private ImageButton mSettingsImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();

        initEvents();

    }

    private void initEvents() {
        mTabWeChat.setOnClickListener(this);
        mTabFriend.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSettings.setOnClickListener(this);

//        mViewPager.setOnPageChangeListener(); //deprecated
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();
                resetImg();
                switch (currentItem) {
                    case 0:
                        mWeChatImg.setImageResource(R.drawable.tab_weixin_pressed);
                        break;
                    case 1:
                        mFriendImg.setImageResource(R.drawable.tab_find_frd_pressed);
                        break;
                    case 2:
                        mAddressImg.setImageResource(R.drawable.tab_address_pressed);
                        break;
                    case 3:
                        mSettingsImg.setImageResource(R.drawable.tab_settings_pressed);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initView() {

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        //tabs
        mTabWeChat = (LinearLayout) findViewById(R.id.id_tab_wechat);
        mTabFriend = (LinearLayout) findViewById(R.id.id_tab_friend);
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabSettings = (LinearLayout) findViewById(R.id.id_tab_settings);

        //ImageButton
        mWeChatImg = (ImageButton) findViewById(R.id.id_tab_wechat_image);
        mFriendImg = (ImageButton) findViewById(R.id.id_tab_friend_image);
        mAddressImg = (ImageButton) findViewById(R.id.id_tab_address_image);
        mSettingsImg = (ImageButton) findViewById(R.id.id_tab_settings_image);

        LayoutInflater mInflater = LayoutInflater.from(this);
        View tab01 = mInflater.inflate(R.layout.tab01, null);
        View tab02 = mInflater.inflate(R.layout.tab02, null);
        View tab03 = mInflater.inflate(R.layout.tab03, null);
        View tab04 = mInflater.inflate(R.layout.tab04, null);

        mViews.add(tab01);
        mViews.add(tab02);
        mViews.add(tab03);
        mViews.add(tab04);

        mAdapter = new PagerAdapter() {

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

        };

        mViewPager.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {

        resetImg();

        //点击哪个就亮哪个
        switch (v.getId()) {
            case R.id.id_tab_wechat:
                mViewPager.setCurrentItem(0);
                mWeChatImg.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case R.id.id_tab_friend:
                mViewPager.setCurrentItem(1);
                mFriendImg.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case R.id.id_tab_address:
                mViewPager.setCurrentItem(2);
                mAddressImg.setImageResource(R.drawable.tab_address_pressed);
                break;
            case R.id.id_tab_settings:
                mViewPager.setCurrentItem(3);
                mSettingsImg.setImageResource(R.drawable.tab_settings_pressed);
                break;
        }
    }

    /**
     * 将所有的图片切换为暗色的
     */
    private void resetImg() {
        mWeChatImg.setImageResource(R.drawable.tab_weixin_normal);
        mFriendImg.setImageResource(R.drawable.tab_find_frd_normal);
        mAddressImg.setImageResource(R.drawable.tab_address_normal);
        mSettingsImg.setImageResource(R.drawable.tab_settings_normal);
    }
}
