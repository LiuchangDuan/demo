package com.example.webviewdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private String url = "http://2016.qq.com";

    private WebView webView;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Uri uri = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
        init();
    }

    private void init() {
        webView = (WebView) findViewById(R.id.webView);
        //WebView加载本地资源
//        webView.loadUrl("file:///android_asset/example.html");
        //WebView加载Web资源
        webView.loadUrl(url);
        //覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为
        //使得网页可以在WebView中打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制网页在WebView中去打开
                //如果为false调用系统浏览器或第三方浏览器打开
//                return super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return true;
            }
            //WebViewClient帮助WebView去处理一些页面控制，和请求通知
        });
        //启用支持JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //WebView加载页面优先使用缓存加载
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //newProgress 1-100之间的整数
                if (newProgress == 100) {
                    //网页加载完毕，关闭ProgressDialog
                    closeDialog();
                } else {
                    //网页正在加载，打开ProgressDialog
                    openDialog(newProgress);
                }
//                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    private void openDialog(int newProgress) {
        if (dialog == null) {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("正在加载");
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setProgress(newProgress);
            dialog.show();
        } else {
            dialog.setProgress(newProgress);
        }
    }

    //改写物理按键------返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            Toast.makeText(this, webView.getUrl(), Toast.LENGTH_SHORT).show();
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            } else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
