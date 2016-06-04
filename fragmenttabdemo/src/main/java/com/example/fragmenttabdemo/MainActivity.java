package com.example.fragmenttabdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout mTabWeChat;
    private LinearLayout mTabFriend;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSettings;

    private ImageButton mImgWeChat;
    private ImageButton mImgFriend;
    private ImageButton mImgAddress;
    private ImageButton mImgSettings;

    private Fragment mTab01;
    private Fragment mTab02;
    private Fragment mTab03;
    private Fragment mTab04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();

        initEvents();

        setSelect(0);

    }

    private void initEvents() {

        mTabWeChat.setOnClickListener(this);
        mTabFriend.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSettings.setOnClickListener(this);

    }

    private void initView() {

        mTabWeChat = (LinearLayout) findViewById(R.id.id_tab_wechat);
        mTabFriend = (LinearLayout) findViewById(R.id.id_tab_friend);
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabSettings = (LinearLayout) findViewById(R.id.id_tab_settings);

        mImgWeChat = (ImageButton) findViewById(R.id.id_tab_wechat_image);
        mImgFriend = (ImageButton) findViewById(R.id.id_tab_friend_image);
        mImgAddress = (ImageButton) findViewById(R.id.id_tab_address_image);
        mImgSettings = (ImageButton) findViewById(R.id.id_tab_settings_image);

//        mTab01 = new WeChatFragment();
//        mTab02 = new FriendFragment();
//        mTab01 = new AddressFragment();
//        mTab01 = new SettingsFragment();

    }

    private void setSelect(int i) {

//        android.app.FragmentManager fm = getFragmentManager();
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();

        hideFragment(transaction);

        //把图片设置为亮的
        //设置内容区域
        switch (i) {
            case 0:
                if (mTab01 == null) {
                    mTab01 = new WeChatFragment();
                    transaction.add(R.id.id_content, mTab01);
                } else {
                    transaction.show(mTab01);
                }
                mImgWeChat.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:
                if (mTab02 == null) {
                    mTab02 = new FriendFragment();
                    transaction.add(R.id.id_content, mTab02);
                } else {
                    transaction.show(mTab02);
                }
                mImgFriend.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:
                if (mTab03 == null) {
                    mTab03 = new AddressFragment();
                    transaction.add(R.id.id_content, mTab03);
                } else {
                    transaction.show(mTab03);
                }
                mImgAddress.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:
                if (mTab04 == null) {
                    mTab04 = new SettingsFragment();
                    transaction.add(R.id.id_content, mTab04);
                } else {
                    transaction.show(mTab04);
                }
                mImgSettings.setImageResource(R.drawable.tab_settings_pressed);
                break;
        }

        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction) {

        if (mTab01 != null) {
            transaction.hide(mTab01);
        }
        if (mTab02 != null) {
            transaction.hide(mTab02);
        }
        if (mTab03 != null) {
            transaction.hide(mTab03);
        }
        if (mTab04 != null) {
            transaction.hide(mTab04);
        }

    }

    @Override
    public void onClick(View v) {

        resetImgs();

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
