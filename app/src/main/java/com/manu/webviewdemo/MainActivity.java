package com.manu.webviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.manu.webviewdemo.activity.WebView2Activity;
import com.manu.webviewdemo.activity.WebView3Activity;
import com.manu.webviewdemo.activity.WebView4Activity;
import com.manu.webviewdemo.activity.WebViewActivity;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener{
    private TextView tv_loadNet;
    private TextView tv_loadLocalHtml;
    private TextView tv_loadHtmlData;
    private TextView tv_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_loadNet = (TextView) findViewById(R.id.tv_loadNet);
        tv_loadLocalHtml = (TextView) findViewById(R.id.tv_loadLocalHtml);
        tv_loadHtmlData = (TextView) findViewById(R.id.tv_loadHtmlData);
        tv_setting = (TextView) findViewById(R.id.tv_setting);

        tv_loadNet.setOnClickListener(this);
        tv_loadLocalHtml.setOnClickListener(this);
        tv_loadHtmlData.setOnClickListener(this);
        tv_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.tv_loadNet:
                intent = new Intent(this,WebViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_loadLocalHtml:
                intent = new Intent(this,WebView2Activity.class);
                startActivity(intent);
                break;
            case R.id.tv_loadHtmlData:
                intent = new Intent(this,WebView3Activity.class);
                startActivity(intent);
                break;
            case R.id.tv_setting:
                intent = new Intent(this,WebView4Activity.class);
                startActivity(intent);
                break;

        }
    }
}
