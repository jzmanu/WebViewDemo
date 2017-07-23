package com.manu.webviewdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.load.model.GlideUrl;
import com.manu.webviewdemo.R;
import com.manu.webviewdemo.view.MyImageView;

/**
 * 用来显示图片
 */
public class ImageActivity extends Activity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        String imgUrl = intent.getStringExtra("img");
        Glide.with(this).load(imgUrl).into(imageView);
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    private int getScreenWidth(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        System.out.println("widthPixels:"+dm.widthPixels);
        System.out.println("density:"+dm.widthPixels);
        System.out.println("dm.widthPixels*dm.density:"+dm.widthPixels*dm.density);
        return (int) (dm.widthPixels*dm.density);
    }
}
