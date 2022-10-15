package com.bs.course.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.course.base.BaseActivity;
import com.bs.course.R;
import com.bs.course.bean.Response;
import com.bs.course.utils.HttpUtil;
import com.bs.course.utils.JsonUitl;
import com.bs.course.utils.Static;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class KcXqActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout mLinBack;
    /**
     * 课程详情
     */
    private TextView mTvTitle;
    /**  */
    private TextView mTvCourseName;
    /**  */
    private TextView mTvKcdescribe;
    /**
     * 选课
     */
    private TextView mTvQd;
    /**
     * 查看成绩
     */
    private TextView mTvCkcj;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_kcxq;
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
        mTvCourseName = (TextView) findViewById(R.id.tv_courseName);
        mTvKcdescribe = (TextView) findViewById(R.id.tv_kcdescribe);
        mTvQd = (TextView) findViewById(R.id.tv_qd);
        mTvQd.setOnClickListener(this);
        mTvCkcj = (TextView) findViewById(R.id.tv_ckcj);

        mTvCourseName.setText(getIntent().getStringExtra("CourseName"));
        mTvKcdescribe.setText(getIntent().getStringExtra("Kcdescribe"));
    }

    @Override
    public void onClick( View v ) {
        switch (v.getId()) {
            default:
                break;
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_qd:
                showDialog(getIntent().getStringExtra("courseId"));
                break;
        }
    }

    public void showDialog( final String courseId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(KcXqActivity.this);
        builder.setTitle("提示");
        builder.setMessage("您确定要选此课程？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                addstudent(courseId);
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


    /**
     * 选课
     */
    private void addstudent(String courseId){
        OkHttpUtils
                .post()
                .url(HttpUtil.ADDSTUDENT)
                .addParams("student", Static.userId)
                .addParams("courseId", courseId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        Response userEntity = JsonUitl.GsonToBean(arg0,Response.class);
                        if (0 == userEntity.getCode()) {
                            Toast.makeText(KcXqActivity.this, "选课成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(KcXqActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError( Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(KcXqActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
