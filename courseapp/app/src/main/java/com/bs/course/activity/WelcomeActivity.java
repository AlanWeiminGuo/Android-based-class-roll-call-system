package com.bs.course.activity;

import android.content.Intent;
import android.os.Handler;

import com.bs.course.R;
import com.bs.course.base.BaseActivity;


/**
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity {

	private final int SPLASH_DISPLAY_LENGHT = 3000;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent();
                intent.setClass(WelcomeActivity.this,LoginActivity.class);
                WelcomeActivity.this.startActivity(intent);
                WelcomeActivity.this.finish();
            }
        },SPLASH_DISPLAY_LENGHT);
    }

    @Override
    protected void initListener() {

    }

}
