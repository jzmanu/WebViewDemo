package com.manu.webviewdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.manu.webviewdemo.R;
import com.manu.webviewdemo.utils.Util;

import static com.manu.webviewdemo.R.id.webView3;

/**
 * 加载 HTML 片段
 */
public class WebView3Activity extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view3);
        webView = (WebView) findViewById(webView3);
        //加载 HTML 片段，设置字符集防止乱码出现
        webView.loadData(Util.getHtmlData(),"text/html; charset=UTF-8",null);
    }
}
