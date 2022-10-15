package com.bs.course.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bs.course.R;
import com.bs.course.application.MyApplication;
import com.bs.course.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class TMainActivity extends BaseActivity implements View.OnClickListener, OnBannerListener {
    private Banner mMBanner;
    /**
     * 我的课程
     */
    private TextView mTvMycoursea;
    /**
     * 发布课程
     */
    private TextView mTvAddcoursea;
    /**
     * 个人资料
     */
    private TextView mTvInfo;
    /**
     * 修改密码
     */
    private TextView mTvUppass;
    /**
     * 教师对学生缺课次数查询
     */
    private TextView mTvTQuekecishu;
    /**
     * 教师对课程缺课率查询
     */
    private TextView mTvTQuekelv;
    /**
     * 退出登录
     */
    private Button mButSignOut;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_tcmain;
    }

    @Override
    protected void initData() {
        initView();
    }

    @Override
    protected void initListener() {

    }

    public void initView() {
        mMBanner = (Banner) findViewById(R.id.mBanner);

        //图片资源
        int[] imageResourceID = new int[]{R.mipmap.ic_bg01, R.mipmap.ic_bg02, R.mipmap.ic_bg03};
        List<Integer> imgeList = new ArrayList<>();
        //轮播标题
        String[] mtitle = new String[]{"", "", ""};
        List<String> titleList = new ArrayList<>();

        for (int i = 0; i < imageResourceID.length; i++) {
            imgeList.add(imageResourceID[i]);//把图片资源循环放入list里面
            titleList.add(mtitle[i]);//把标题循环设置进列表里面
            //设置图片加载器，通过Glide加载图片
            mMBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(TMainActivity.this).load(path).into(imageView);
                }
            });
            //设置轮播的动画效果,里面有很多种特效,可以到GitHub上查看文档。
            mMBanner.setBannerAnimation(Transformer.Accordion);
            mMBanner.setImages(imgeList);//设置图片资源
            mMBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//设置banner显示样式（带标题的样式）
            mMBanner.setBannerTitles(titleList); //设置标题集合（当banner样式有显示title时）
            //设置指示器位置（即图片下面的那个小圆点）
            mMBanner.setIndicatorGravity(BannerConfig.CENTER);
            mMBanner.setDelayTime(3000);//设置轮播时间3秒切换下一图
            mMBanner.setOnBannerListener(this);//设置监听
            mMBanner.start();//开始进行banner渲染
        }
        mTvMycoursea = (TextView) findViewById(R.id.tv_mycoursea);
        mTvMycoursea.setOnClickListener(this);
        mTvAddcoursea = (TextView) findViewById(R.id.tv_addcoursea);
        mTvAddcoursea.setOnClickListener(this);
        mTvInfo = (TextView) findViewById(R.id.tv_info);
        mTvInfo.setOnClickListener(this);
        mTvUppass = (TextView) findViewById(R.id.tv_uppass);
        mTvUppass.setOnClickListener(this);
        mTvTQuekecishu = (TextView) findViewById(R.id.t_quekecishu);
        mTvTQuekecishu.setOnClickListener(this);
        mTvTQuekelv = (TextView) findViewById(R.id.t_quekelv);
        mTvTQuekelv.setOnClickListener(this);
        mButSignOut = (Button) findViewById(R.id.but_sign_out);
        mButSignOut.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_mycoursea:
                intent = new Intent(TMainActivity.this, TMyCourseaActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_addcoursea:
                intent = new Intent(TMainActivity.this, AddCourseaActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_info:
                intent = new Intent(TMainActivity.this, TeInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_uppass:
                intent = new Intent(TMainActivity.this, UpPwdActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
                break;
            case R.id.t_quekecishu:
                intent = new Intent(TMainActivity.this, TQuekecishuActivity.class);
                startActivity(intent);
                break;
            case R.id.t_quekelv:
                intent = new Intent(TMainActivity.this, TQuekelvActivity.class);
                startActivity(intent);
                break;
            case R.id.but_sign_out:
                showDialog();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mMBanner.startAutoPlay();//开始轮播
    }

    @Override
    public void onStop() {
        super.onStop();
        mMBanner.stopAutoPlay();//结束轮播
    }

    //对轮播图设置点击监听事件
    @Override
    public void OnBannerClick(int position) {
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TMainActivity.this);
        builder.setTitle("提示");
        builder.setMessage("您确定要退出登录？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                MyApplication.getInstance().AppExit();
                Intent intent = new Intent(TMainActivity.this, LoginActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
