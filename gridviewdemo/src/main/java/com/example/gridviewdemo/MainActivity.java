package com.example.gridviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;

    private List<Map<String, Object>> dataList;

    private int[] icon = {R.drawable.address_book, R.drawable.calendar,
            R.drawable.camera, R.drawable.clock, R.drawable.games_control,
            R.drawable.messenger, R.drawable.ringtone, R.drawable.settings,
            R.drawable.speech_balloon, R.drawable.weather, R.drawable.world,
            R.drawable.youtube};

    private String[] iconName = {"通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声",
            "设置", "语音", "天气", "浏览器", "视频"};

    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        //1.准备数据源
        //2.新建适配器（SimpleAdapter）
        //3.GridView加载适配器
        //4.GridView配置事件监听器（OnItemClickListener）
        dataList = new ArrayList<Map<String, Object>>();
//        getData();
        //SimpleAdapter()
        /**
         * context：上下文
         * data：数据源  List<? extends Map<String, ?>> data 一个Map所组成的List集合
         *       每一个Map都会去对应ListView列表中的一行
         *      每一个Map（键-值对）中的键必须包含所有在from中所指定的键
         * resource：列表项的布局文件ID
         * from：Map中的键名
         * to：绑定数据视图中的ID，与from成对应关系
         */
        adapter = new SimpleAdapter(this, getData(), R.layout.item,
                new String[] {"image", "text"}, new int[] {R.id.image, R.id.text});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, iconName[position], Toast.LENGTH_SHORT).show();
    }
}
