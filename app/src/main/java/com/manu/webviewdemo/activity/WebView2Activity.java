package com.manu.webviewdemo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.webkit.WebView;
import android.widget.Toast;

import com.manu.webviewdemo.R;

import java.io.File;
import java.security.Permission;

import static com.manu.webviewdemo.R.id.webView2;

/**
 * 加载 HTML 文件
 */
public class WebView2Activity extends Activity {
    private static final int WRITE = 0x1;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);
        webView = (WebView) findViewById(webView2);
        //加载 assets 目录下的 HTML 文件
//        webView.loadUrl("file:///android_asset/mine.html");
        //加载 SD 卡上的 HTML 文件（测试错误）
//        webView.loadUrl("content://com.android.htmlfileprovider/sdcard/mine.html");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED)//如果用户没有授予权限
        {
            /**
             * 如果用户拒绝了某权限，该方法返回 true
             * 主要是当用户再次请求时，向用户提供必要的权限说明
             */
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE))
            {
                Toast.makeText(this, "该操作需要获取 SD 卡存储权限", Toast.LENGTH_SHORT).show();
            }else{
                //请求需要的权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE);
            }
        }else{
            //加载 SD 卡上的 HTML 文件
            webView.loadUrl("file:///"+Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"mine.html");
        }
    }
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode){
            case WRITE:
                if (permissions.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    webView.loadUrl("file:///"+Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"mine.html");
                }else{
                    Toast.makeText(this, "请到设置中心打开访问 SD 卡的权限，才能使用该功能", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
