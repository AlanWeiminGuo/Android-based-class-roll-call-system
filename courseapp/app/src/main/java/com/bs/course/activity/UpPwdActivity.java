package com.bs.course.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bs.course.base.BaseActivity;
import com.bs.course.R;
import com.bs.course.bean.StudentBean;
import com.bs.course.utils.HttpUtil;
import com.bs.course.utils.JsonUitl;
import com.bs.course.utils.Static;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class UpPwdActivity extends BaseActivity {

    @BindView(R.id.lin_back)
    LinearLayout linBack;
    @BindView(R.id.old_pass)
    EditText oldPass;
    @BindView(R.id.user_pass)
    EditText userPass;
    @BindView(R.id.user_newpass)
    EditText userNewpass;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_uppwd;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.lin_back, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_login:
                if (getIntent().getStringExtra("type").equals("1")){
                    stuppwd();
                }else {
                    teuppwd();
                }
                break;
        }
    }

    /**
     * 学生修改密码
     */
    private void stuppwd() {
        if (oldPass.getText().toString().trim().equals("")) {
            Toast.makeText(UpPwdActivity.this, "旧密码不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        if (userPass.getText().toString().trim().equals("")) {
            Toast.makeText(UpPwdActivity.this, "新密码不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        if (userNewpass.getText().toString().trim().equals("")) {
            Toast.makeText(UpPwdActivity.this, "确认新密码不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!userPass.getText().toString().trim().equals(userNewpass.getText().toString().trim())){
            Toast.makeText(UpPwdActivity.this, "两次密码不一致!", Toast.LENGTH_LONG).show();
            return;
        }
        OkHttpUtils
                .post()
                .url(HttpUtil.UPDATE_PASSWORD)
                .addParams("id", Static.userId)
                .addParams("password", oldPass.getText().toString().trim())
                .addParams("newpassword", userPass.getText().toString().trim())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        StudentBean userEntity = JsonUitl.GsonToBean(arg0,StudentBean.class);
                        if (0 == userEntity.getCode()) {
                            Toast.makeText(UpPwdActivity.this, "修改成功,请重新登录！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UpPwdActivity.this,LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(UpPwdActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(UpPwdActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    /**
     * 老师修改密码
     */
    private void teuppwd() {
        if (oldPass.getText().toString().trim().equals("")) {
            Toast.makeText(UpPwdActivity.this, "旧密码不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        if (userPass.getText().toString().trim().equals("")) {
            Toast.makeText(UpPwdActivity.this, "新密码不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        if (userNewpass.getText().toString().trim().equals("")) {
            Toast.makeText(UpPwdActivity.this, "确认新密码不能为空!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!userPass.getText().toString().trim().equals(userNewpass.getText().toString().trim())){
            Toast.makeText(UpPwdActivity.this, "两次密码不一致!", Toast.LENGTH_LONG).show();
            return;
        }
        OkHttpUtils
                .post()
                .url(HttpUtil.TEACHERUPPWD)
                .addParams("id", Static.userId)
                .addParams("password", oldPass.getText().toString().trim())
                .addParams("newpassword", userPass.getText().toString().trim())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        StudentBean userEntity = JsonUitl.GsonToBean(arg0,StudentBean.class);
                        if (0 == userEntity.getCode()) {
                            Toast.makeText(UpPwdActivity.this, "修改成功,请重新登录！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UpPwdActivity.this,LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(UpPwdActivity.this, userEntity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(UpPwdActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
