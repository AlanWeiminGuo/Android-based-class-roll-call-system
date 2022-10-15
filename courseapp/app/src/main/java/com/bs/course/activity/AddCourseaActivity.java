package com.bs.course.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.course.R;
import com.bs.course.base.BaseActivity;
import com.bs.course.bean.Response;
import com.bs.course.bean.StudentBean;
import com.bs.course.utils.HttpUtil;
import com.bs.course.utils.JsonUitl;
import com.bs.course.utils.Static;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class AddCourseaActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mLinBack;
    /**
     * 添加课程
     */
    private TextView mTvTitle;
    private RelativeLayout mRelTitle;
    /**
     * 请输入课程名称
     */
    private EditText mTvName;
    /**
     * 请输入课程名称
     */
    private TextView mTvContent;
    /**
     * 添加
     */
    private Button mBtnLogin;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_add_coursea;
    }

    @Override
    protected void initData() {
        initView();
    }

    @Override
    protected void initListener() {

    }


    public void initView() {
        mLinBack = (LinearLayout) findViewById(R.id.lin_back);
        mLinBack.setOnClickListener(this);
        mTvTitle = (TextView) findViewById(R.id.tv_Title);
        mRelTitle = (RelativeLayout) findViewById(R.id.rel_title);
        mTvName = (EditText) findViewById(R.id.tv_name);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
    }


    /**
     * 添加课程
     */
    private void addcourse(){
        if (mTvName.getText().toString().trim().equals("")) {
            Toast.makeText(AddCourseaActivity.this, "名称不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        if (mTvContent.getText().toString().trim().equals("")) {
            Toast.makeText(AddCourseaActivity.this, "描述不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        OkHttpUtils
                .post()
                .url(HttpUtil.ADDCOURSE)
                .addParams("courseName", mTvName.getText().toString().trim())
                .addParams("state", "正常")
                .addParams("teacherId", Static.userId)
                .addParams("kcdescribe", mTvContent.getText().toString().trim())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        Response userEntity = JsonUitl.GsonToBean(arg0,Response.class);
                        if (0 == userEntity.getCode()) {
                            Toast.makeText(AddCourseaActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            mTvName.setText("");
                            mTvContent.setText("");
                        } else {
                            Toast.makeText(AddCourseaActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(AddCourseaActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_login:
                addcourse();
                break;
        }
    }
}
