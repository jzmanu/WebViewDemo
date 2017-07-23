package com.manu.webviewdemo.helper;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.manu.webviewdemo.activity.ImageActivity;

/**
 * 与 javascript 通信的 Java 对象，提供 javascript 调用的方法
 * Created by lcdn on 2017/7/20 0020.
 */
public class AndroidInterface {
    private Context context;
    public AndroidInterface(Context context) {
        this.context = context;
    }

    /**
     * 添加注解 @JavascriptInterface
     * javascript 要调用的方法
     */
    @JavascriptInterface
    public void showImage(String imgUrl){
        Intent intent = new Intent();
        intent.putExtra("img",imgUrl);
        intent.setClass(context,ImageActivity.class);
        context.startActivity(intent);
    }
}
