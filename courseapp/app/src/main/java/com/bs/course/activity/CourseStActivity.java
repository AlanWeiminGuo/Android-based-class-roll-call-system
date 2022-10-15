package com.bs.course.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.course.application.MyApplication;
import com.bs.course.base.BaseActivity;
import com.bs.course.R;
import com.bs.course.adapter.CourseStAdapter;
import com.bs.course.bean.CourseStBean;
import com.bs.course.bean.Response;
import com.bs.course.bean.StudentBean;
import com.bs.course.utils.HttpUtil;
import com.bs.course.utils.JsonUitl;
import com.bs.course.utils.Static;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class CourseStActivity extends BaseActivity {
    @BindView(R.id.lin_back)
    LinearLayout linBack;
    @BindView(R.id.tv_caozuo)
    TextView tvCaozuo;
    @BindView(R.id.listview)
    ListView listview;

    private List<CourseStBean.DataBean> beanList;
    private List<CourseStBean.DataBean> beanList1 = new ArrayList<>();
    private CourseStAdapter courseStAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_coursest;
    }

    @Override
    protected void initData() {
        final String state = getIntent().getStringExtra("state");
        if (state.equals("正常")) {
            tvCaozuo.setText("上课");
            tvCaozuo.setVisibility(View.VISIBLE);
        } else if (state.equals("上课中")) {
            tvCaozuo.setText("下课");
            tvCaozuo.setVisibility(View.VISIBLE);
        } else {
            tvCaozuo.setVisibility(View.GONE);
        }
        courseStAdapter = new CourseStAdapter(this, beanList1);
        listview.setAdapter(courseStAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (state.equals("上课中")) {
                    inputTitleDialog(beanList1.get(position).getId());
                }
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CourseStActivity.this);
                builder.setTitle("提示");
                builder.setMessage("您确定要将该名学生标记为已签到吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialog, int which ) {



                        OkHttpUtils
                                .post()
                                .url(HttpUtil.QIANDAOCHANGE)
                                .addParams("studentId", beanList1.get(position).getId())//这是学生的学号
                                .addParams("courseId", getIntent().getStringExtra("id"))
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onResponse(String arg0, int arg1) {
                                        StudentBean userEntity = JsonUitl.GsonToBean(arg0,StudentBean.class);
                                        if (0 == userEntity.getCode()) {
                                            Toast.makeText(CourseStActivity.this, "签到情况修改成功", Toast.LENGTH_SHORT).show();



                                        } else {
                                            Toast.makeText(CourseStActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Call arg0, Exception arg1, int arg2) {
                                        Toast.makeText(CourseStActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                                    }
                                });


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
                return true;
            }
        });

        getcourse_list();
    }

    private void inputTitleDialog(final String stUsierId) {
        final EditText inputServer = new EditText(this);
        inputServer.setFocusable(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请您输入该学生的成绩").setView(inputServer).setNegativeButton("取消", null);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String inputName = inputServer.getText().toString().trim();
                        addchegnji(stUsierId,inputName);
                    }
                });
        builder.show();
    }

    /**
     * 设置学生成绩
     */
    private void addchegnji(String stUsierId,String cj){
        OkHttpUtils
                .post()
                .url(HttpUtil.ADDCJ)
                .addParams("studentId", stUsierId)
                .addParams("studentcj", cj)
                .addParams("courseId", getIntent().getStringExtra("id"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        Log.i("LoginActivity", arg0);
                        Response response = JsonUitl.GsonToBean(arg0,Response.class);
                        if (0 == response.getCode()) {
                            Toast.makeText(CourseStActivity.this, "添加成绩成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CourseStActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(CourseStActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });

    }



    @Override
    protected void initListener() {

    }


    @OnClick({R.id.lin_back, R.id.tv_caozuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_caozuo:
                if (tvCaozuo.getText().toString().equals("上课")) {
                    sk();
                } else {
                    xk();
                }
                break;
        }
    }

    /**
     * 获取课堂人员
     */
    private void getcourse_list() {
        OkHttpUtils
                .post()
                .url(HttpUtil.COURSE_STLIST)
                .addParams("courseId", getIntent().getStringExtra("id"))
                .addParams("page", "1")
                .addParams("limit", "1000")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        Log.i("LoginActivity", arg0);
                        CourseStBean courseBean = JsonUitl.GsonToBean(arg0,CourseStBean.class);
                        if (0 == courseBean.getCode()) {
                            beanList = courseBean.getData();
                            if (beanList.size() > 0) {
                                beanList1.addAll(beanList);
                                courseStAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(CourseStActivity.this, courseBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(CourseStActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 上课
     */
    private void sk() {
        OkHttpUtils
                .post()
                .url(HttpUtil.TEACHER_SK)
                .addParams("id", getIntent().getStringExtra("id"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        Log.i("LoginActivity", arg0);
                        Response response = JsonUitl.GsonToBean(arg0,Response.class);
                        if (0 == response.getCode()) {
                            Toast.makeText(CourseStActivity.this, "上课了", Toast.LENGTH_SHORT).show();
                            tvCaozuo.setText("下课");
                            tvCaozuo.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(CourseStActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(CourseStActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 下课
     */
    private void xk() {
        OkHttpUtils
                .post()
                .url(HttpUtil.TEACHER_XK)
                .addParams("id", getIntent().getStringExtra("id"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        Response response = JsonUitl.GsonToBean(arg0,Response.class);
                        if (0 == response.getCode()) {
                            Toast.makeText(CourseStActivity.this, "下课了", Toast.LENGTH_SHORT).show();
                            tvCaozuo.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(CourseStActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(CourseStActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }



}
