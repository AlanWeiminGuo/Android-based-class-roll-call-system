package com.bs.course.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.Toast;

import com.bs.course.R;
import com.bs.course.application.MyApplication;
import com.bs.course.base.BaseActivity;
import com.bs.course.fragment.HomeFragment;
import com.bs.course.fragment.MyFragment;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class StMainActivity extends BaseActivity {
    private static EasyNavigationBar navigationBar;


    //选中时变色
    private String[] tabText = {"首页","我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.actionbar_home, R.mipmap.actionbar_my};
    //选中时icon
    private int[] selectIcon = {R.mipmap.actionbar_home_sel, R.mipmap.actionbar_my_sel};

    private List<Fragment> fragments = new ArrayList<>();
    private HomeFragment homeFragment;
    private MyFragment tMyFragment;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        navigationBar = findViewById(R.id.navigationBar);
        homeFragment = new HomeFragment();
        tMyFragment = new MyFragment();

        fragments.add(homeFragment);
        fragments.add(tMyFragment);

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .canScroll(false)  //Viewpager能否左右滑动
                .normalTextColor(Color.parseColor("#666666"))   //Tab未选中时字体颜色
                .selectTextColor(Color.parseColor("#095896"))   //Tab选中时字体颜色
                .fragmentManager(getSupportFragmentManager())
                .build();
        setNavigationBar(0);

    }

    public static void setNavigationBar(int selectTab1) {
        navigationBar.selectTab(selectTab1);
    }

    //退出时的时间
    private long mExitTime;

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            MyApplication.getInstance().AppExit();
        }
    }

    @Override
    protected void initListener() {

    }
}
