package com.bs.course.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.course.R;
import com.bs.course.base.BaseActivity;
import com.bs.course.bean.TeacherBean;
import com.bs.course.utils.HttpUtil;
import com.bs.course.utils.JsonUitl;
import com.bs.course.utils.Static;
import com.next.easynavigation.view.EasyNavigationBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class TQuekelvActivity extends BaseActivity {
    private static EasyNavigationBar navigationBar;


    @BindView(R.id.lin_back)
    LinearLayout linBack;
    @BindView(R.id.course_name)
    EditText course_name;
    @BindView(R.id.btn_chaxun)
    Button btnchaxun;
    @BindView(R.id.result)
    TextView result;

    static String res= "";//最终用来赋值result的字符串
    static String name= "";//循环中使用的课程名称
    static float m= 0;//未签到人数
    static float n= 0;//总体人数
    static int z= 0;// 实现循环的次数
    static int p=-1;//实现哪一行的统计
    static double u = 0;//用来实现总计

    @OnClick({R.id.lin_back, R.id.btn_chaxun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                res= "";
                p=-1;
                u=0;
                finish();
                break;

            case R.id.btn_chaxun:
                chaxun();
                break;
        }
    }



    public void chaxun() {
        //第一步  获取课程的个数
        OkHttpUtils
                .post()
                .url(HttpUtil.T_Quekelvcount)//传递课程名和学号，获得要查询的课程的个数
                .addParams("course_name", course_name.getText().toString().trim())
                .addParams("teacherId", Static.userId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {

                        TeacherBean userEntity = JsonUitl.GsonToBean(arg0,TeacherBean.class);

                        if (0==userEntity.getCode()) {//成功
                            z= Integer.valueOf(userEntity.getMsg());
                            Toast.makeText(TQuekelvActivity.this,userEntity.getMsg(), Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(TQuekelvActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(TQuekelvActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });

p=p+1;
if(p<=z-1) {
    //第二步获取课程的名字
    OkHttpUtils
            .post()
            .url(HttpUtil.T_Quekelv)//传递课程名和学号
            .addParams("course_name", course_name.getText().toString().trim())
            .addParams("teacherId", Static.userId)
            .addParams("count", String.valueOf(p))
            .build()
            .execute(new StringCallback() {
                @Override
                public void onResponse(String arg0, int arg1) {
                    TeacherBean userEntity = JsonUitl.GsonToBean(arg0, TeacherBean.class);

                    if (0 == userEntity.getCode()) {//成功
                        name = userEntity.getMsg();
                        res = res + name;


                        //第三步根据刚刚获取的课程的名字去查找该课程未签到人数
                        OkHttpUtils
                                .post()
                                .url(HttpUtil.T_QuekelvUnsign)//传递课程名和学号
                                .addParams("course_name", name)
                                .addParams("teacherId", Static.userId)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onResponse(String arg0, int arg1) {
                                        TeacherBean userEntity = JsonUitl.GsonToBean(arg0, TeacherBean.class);

                                        if (0 == userEntity.getCode()) {//成功
                                            m = Float.valueOf(userEntity.getMsg());
                                            res = res + "       " + userEntity.getMsg();
                                            //第四步根据刚刚获取的课程的名字去查找该课程全体选课人数
                                            OkHttpUtils
                                                    .post()
                                                    .url(HttpUtil.T_QuekelvAll)//传递课程名和学号
                                                    .addParams("course_name", name)
                                                    .addParams("teacherId", Static.userId)
                                                    .build()
                                                    .execute(new StringCallback() {
                                                        @Override
                                                        public void onResponse(String arg01, int arg11) {
                                                            TeacherBean userEntity2 = JsonUitl.GsonToBean(arg01, TeacherBean.class);

                                                            if (0 == userEntity2.getCode()) {//成功
                                                                n = Float.valueOf(userEntity2.getMsg());
                                                                res = res + " / " + userEntity2.getMsg();

                                                                float j = m / n;
                                                                res = res + "       " + (j * 100.0) + "%" + "\n";
                                                                    u=u+j * 100.0;

                                                            } else {
                                                                Toast.makeText(TQuekelvActivity.this, userEntity2.getMsg(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        }

                                                        @Override
                                                        public void onError(Call arg01, Exception arg11, int arg2) {
                                                            Toast.makeText(TQuekelvActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                        } else {
                                            Toast.makeText(TQuekelvActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Call arg0, Exception arg1, int arg2) {
                                        Toast.makeText(TQuekelvActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                                    }
                                });



                    } else {
                        Toast.makeText(TQuekelvActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Call arg0, Exception arg1, int arg2) {
                    Toast.makeText(TQuekelvActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                }
            });

}

if(p==z)
{
    double conclude=0;
    conclude= u/p;
    res=res+"\n"+"             总计缺课率："+conclude+"%";
}

        result.setText(res);
















    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_t_quekelv;
    }

    @Override
    protected void initData() {



    }

    public static void setNavigationBar(int selectTab1) {

    }





    @Override
    protected void initListener() {

    }
}
