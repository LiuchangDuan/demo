package com.example.fiveinarow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private FiveInARowPanel fiveInARowPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fiveInARowPanel = (FiveInARowPanel) findViewById(R.id.id_fiveinarow);

    }


    /**
     * 把再来一局功能设置在菜单栏
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            //再来一局
            fiveInARowPanel.start();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
