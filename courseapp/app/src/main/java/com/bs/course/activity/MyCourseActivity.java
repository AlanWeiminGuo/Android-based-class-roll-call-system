package com.bs.course.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.course.R;
import com.bs.course.adapter.CourseAdapter;
import com.bs.course.base.BaseActivity;
import com.bs.course.bean.CourseBean;
import com.bs.course.utils.HttpUtil;
import com.bs.course.utils.JsonUitl;
import com.bs.course.utils.Static;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MyCourseActivity extends BaseActivity implements View.OnClickListener {


    private ImageView mBack;
    /**  */
    private TextView mTvTitleName;
    /**
     * 刷新
     */
    private TextView mTvSx;
    private ListView mConvListView;
    private ImageView mImZw;

    private List<CourseBean.DataBean> beanList;
    private List<CourseBean.DataBean> beanList1 = new ArrayList<>();
    private CourseAdapter courseAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initData() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mTvTitleName = (TextView) findViewById(R.id.tv_titleName);
        mTvSx = (TextView) findViewById(R.id.tv_sx);
        mTvSx.setOnClickListener(this);
        mConvListView = (ListView) findViewById(R.id.conv_list_view);
        mImZw = (ImageView) findViewById(R.id.im_zw);
        mTvTitleName.setText("我的课程");
        mBack.setVisibility(View.VISIBLE);

        courseAdapter = new CourseAdapter(this,beanList1);
        mConvListView.setAdapter(courseAdapter);
        mConvListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyCourseActivity.this,KcDetailsActivity.class);
                intent.putExtra("CourseName",beanList1.get(position).getCourseName());
                intent.putExtra("Kcdescribe",beanList1.get(position).getKcdescribe());
                intent.putExtra("state",beanList1.get(position).getState());
                intent.putExtra("courseId",beanList1.get(position).getId());
                startActivity(intent);
            }
        });
        getcourse_list();
    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onClick( View v ) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.tv_sx:
                getcourse_list();
                break;
        }
    }

    private void getcourse_list() {
        beanList1.clear();
        OkHttpUtils
                .post()
                .url(HttpUtil.COURSE_STLISTs)
                .addParams("studentId", Static.userId)
                .addParams("page","1")
                .addParams("limit","10000")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        Log.i("LoginActivity", arg0);
                        CourseBean courseBean =  JsonUitl.GsonToBean(arg0,CourseBean.class);
                        if (0 == courseBean.getCode()) {
                            beanList = courseBean.getData();
                            if (beanList.size() > 0) {
                                beanList1.addAll(beanList);
                                courseAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(MyCourseActivity.this, courseBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError( Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(MyCourseActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
