package com.manu.webviewdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.manu.webviewdemo.R;

/**
 * 加载完整网页
 */
public class WebViewActivity extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        webView.loadUrl("https://www.baidu.com");
//        webView.loadUrl("http://blog.csdn.net/");
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("http://www.qq.com/");
    }
}
