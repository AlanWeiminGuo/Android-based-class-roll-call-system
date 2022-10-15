package com.bs.course.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.course.R;
import com.bs.course.base.BaseActivity;
import com.bs.course.bean.StudentBean;
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

public class TQuekecishuActivity extends BaseActivity {
    private static EasyNavigationBar navigationBar;

    @BindView(R.id.course_name)
    EditText course_name;
    @BindView(R.id.studentId)
    EditText studentId;
    @BindView(R.id.btn_chaxun)
    Button btnchaxun;
    @BindView(R.id.result)
    TextView result;

    @OnClick({R.id.btn_chaxun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_chaxun:
                chaxun();
                break;
        }
    }
    public void chaxun2() {
        final String CLS="com.mysql.jdbc.Driver";
        final String URL="jdbc:mysql://192.168.1.105:8081/course";
        final String USER="root";
        final String PWD="root";



        new Thread(new Runnable() {
            @Override
            public void run() {
                int count=0;
                Toast.makeText(TQuekecishuActivity.this,"222", Toast.LENGTH_SHORT).show();
                try{
                    Class.forName(CLS);
                    Connection connection = DriverManager.getConnection(URL,USER,PWD);
                    String sql="select studentId from t_student where 1=1";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()){
                        count = resultSet.getInt("s");
                        Toast.makeText(TQuekecishuActivity.this,"222", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(TQuekecishuActivity.this,"333", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(TQuekecishuActivity.this,"444", Toast.LENGTH_SHORT).show();
                }

            }
        }).start();





    }


    public void chaxun() {

        OkHttpUtils
                .post()
                .url(HttpUtil.T_Quekecishu)//传递课程名和学号
                .addParams("course_name", course_name.getText().toString().trim())
                .addParams("studentId", studentId.getText().toString().trim())
                .addParams("teacherId", Static.userId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        TeacherBean userEntity = JsonUitl.GsonToBean(arg0,TeacherBean.class);
                        String z= null;
                        if (0==userEntity.getCode()) {//成功
                            z=userEntity.getMsg();
                            result.setText(z);
                            Toast.makeText(TQuekecishuActivity.this, "查询成功", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(TQuekecishuActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(TQuekecishuActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_t_quekecishu;
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
