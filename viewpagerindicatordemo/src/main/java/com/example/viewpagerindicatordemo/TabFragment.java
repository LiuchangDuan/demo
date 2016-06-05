package com.example.viewpagerindicatordemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/5.
 */
@SuppressLint("ValidFragment")
public class TabFragment extends Fragment {

    private int pos;

    public TabFragment(int pos) {
        this.pos = pos;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag, container, false);

        TextView tv = (TextView) view.findViewById(R.id.id_tv);

        tv.setText(TabAdapter.TITLES[pos]);

        return view;

    }
}
