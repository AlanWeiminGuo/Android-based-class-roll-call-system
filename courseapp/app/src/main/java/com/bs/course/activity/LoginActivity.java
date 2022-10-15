package com.bs.course.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.course.R;
import com.bs.course.base.BaseActivity;
import com.bs.course.bean.StudentBean;
import com.bs.course.bean.TeacherBean;
import com.bs.course.utils.HttpUtil;
import com.bs.course.utils.JsonUitl;
import com.bs.course.utils.Static;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;


public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_Title)
    TextView tvTitle;
    @BindView(R.id.rel_title)
    RelativeLayout relTitle;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_pass)
    EditText userPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.line1)
    LinearLayout line1;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.btn_login,R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                Register();
                break;
        }
    }

    public void login() {
        if (userName.getText().toString().trim().equals("")) {
            Toast.makeText(LoginActivity.this, "账号不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        if (userPass.getText().toString().trim().equals("")) {
            Toast.makeText(LoginActivity.this, "密码不能为空！", Toast.LENGTH_LONG).show();
            return;
        }
        change_juese();
    }

    public void Register() {
        change_juese2();
    }

    public void change_juese(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this); //定义一个AlertDialog
        String[] strarr = {"老师","学生"};
        builder.setItems(strarr, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface arg0, int arg1){
                // 自动生成的方法存根
                if (arg1 == 0) {
                    teacherlogin();
                }else {//女
                    stlogin();
                }
            }
        });
        builder.show();
    }

    public void change_juese2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this); //定义一个AlertDialog
        String[] strarr = {"老师","学生"};
        builder.setItems(strarr, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface arg0, int arg1){
                // 自动生成的方法存根
                if (arg1 == 0) {
                    Intent intent = new Intent(LoginActivity.this, TRegisterActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(LoginActivity.this, StRegisterActivity.class);
                    startActivity(intent);
                }
            }
        });
        builder.show();
    }

    /**
     * 学生登录
     */
    public void stlogin(){
        OkHttpUtils
                .post()
                .url(HttpUtil.USER_LOGIN)//传递学生的学号、密码
                .addParams("studentId", userName.getText().toString().trim())
                .addParams("password", userPass.getText().toString().trim())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        Log.i("LoginActivity", arg0);
                        StudentBean baseData = JsonUitl.GsonToBean(arg0,StudentBean.class);
                        if (0 == baseData.getCode()) {
                            Intent intent = new Intent(LoginActivity.this, StMainActivity.class);
                            startActivity(intent);
                            Static.userId = baseData.getUser().getId();
                            Static.studentId = baseData.getUser().getStudentId();
                            if (baseData.getUser().getHead() == null){
                                Static.head  = "";
                            }else {
                                Static.head = baseData.getUser().getHead();
                            }
                            Static.name = baseData.getUser().getName();
                            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, baseData.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(LoginActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    /**老师登录*/
    private  void teacherlogin(){
        OkHttpUtils
                .post()
                .url(HttpUtil.TEACHER_LOGIN)//传递教师的工号、密码
                .addParams("jobNumber", userName.getText().toString().trim())
                .addParams("password", userPass.getText().toString().trim())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        Log.i("LoginActivity", arg0);
                        TeacherBean baseData = JsonUitl.GsonToBean(arg0,TeacherBean.class);//初始化一个老师
                        if (0 == baseData.getCode()) {
                            Intent intent = new Intent(LoginActivity.this, TMainActivity.class);
                            startActivity(intent);
                            Static.userId = baseData.getUser().getId();
                            Static.head = baseData.getUser().getHead();
                            Static.name = baseData.getUser().getName();
                            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, baseData.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(LoginActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
