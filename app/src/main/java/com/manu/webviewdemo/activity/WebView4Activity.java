package com.manu.webviewdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.manu.webviewdemo.R;
import com.manu.webviewdemo.helper.AndroidInterface;

/**
 * 为WebView中的图片设置点击事件
 */
public class WebView4Activity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view4);
        webView = (WebView) findViewById(R.id.webView4);
        WebSettings webSettings = webView.getSettings();
        //设置支持JavaScript
        webSettings.setJavaScriptEnabled(true);
        //防止中文乱码
        webSettings.setDefaultTextEncodingName("UTF-8");
        //加载 assets 目录下的 HTML 文件
        webView.loadUrl("file:///android_asset/index.html");
        //注入Java对象并映射到JavaScript中
        webView.addJavascriptInterface(new AndroidInterface(this),"imageListener");
        //设置 WebViewClient 监听相关事件
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //保证页面加载完成后Java对象注入到JavaScript中
                webView.loadUrl("javascript:findImg()");
            }
        });
    }
}
