package com.bs.course.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.course.R;
import com.bs.course.base.BaseActivity;
import com.bs.course.bean.ChengJi;
import com.bs.course.bean.StudentBean;
import com.bs.course.utils.HttpUtil;
import com.bs.course.utils.JsonUitl;
import com.bs.course.utils.Static;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 签到
 */
public class KcDetailsActivity extends BaseActivity implements SensorEventListener {
    @BindView(R.id.lin_back)
    LinearLayout linBack;
    @BindView(R.id.tv_courseName)
    TextView tvCourseName;
    @BindView(R.id.tv_kcdescribe)
    TextView tvKcdescribe;
    @BindView(R.id.tv_qd)
    TextView tvQd;
    @BindView(R.id.tv_ckcj)
    TextView tvCkcj;
    int n=0;
    private SensorManager mSensorMgr;//声明一个传感管理器对象
    private Vibrator mVibrator;//声明一个震动器对象

    @Override
    public int getLayoutRes() {
        return R.layout.course_details;
    }

    @Override
    protected void initData() {
        tvCourseName.setText(getIntent().getStringExtra("CourseName"));
        tvKcdescribe.setText(getIntent().getStringExtra("Kcdescribe"));
        if (getIntent().getStringExtra("state").equals("已完成")) {
            tvQd.setText("已结束");
            tvCkcj.setVisibility(View.VISIBLE);
        } else if (getIntent().getStringExtra("state").equals("上课中")){
            tvQd.setText("签到");
        }else {
            tvQd.setVisibility(View.GONE);
        }
        //从系统服务中获取传感管理器对象
        mSensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //从系统服务中获取振动器对象
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    protected void initListener() {

    }



    @OnClick({R.id.lin_back, R.id.tv_qd, R.id.tv_ckcj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.tv_qd:
                if (tvQd.getText().toString().equals("签到")) {
                    qd();
                } else {
                    Toast.makeText(KcDetailsActivity.this, "课程已结束", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tv_ckcj:
                ckktcj();
                break;
        }
    }


    /**
     * 签到
     */
    public void qd() {
        OkHttpUtils
                .post()
                .url(HttpUtil.USER_QD)
                .addParams("studentId", Static.userId)
                .addParams("courseId", getIntent().getStringExtra("courseId"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        Log.i("LoginActivity", arg0);
                        StudentBean baseData = JsonUitl.GsonToBean(arg0,StudentBean.class);
                        if (0 == baseData.getCode()) {
                            Toast.makeText(KcDetailsActivity.this, baseData.getMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(KcDetailsActivity.this, baseData.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(KcDetailsActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void ckktcj() {
        OkHttpUtils
                .post()
                .url(HttpUtil.GETCJ)
                .addParams("studentId", Static.userId)
                .addParams("courseId", getIntent().getStringExtra("courseId"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(String arg0, int arg1) {
                        Log.i("LoginActivity", arg0);
                        ChengJi baseData = JsonUitl.GsonToBean(arg0,ChengJi.class);
                        if (0 == baseData.getCode()) {
                            Toast.makeText(KcDetailsActivity.this, "你在该课堂成绩是"+baseData.getData().getStudentcj()+"分", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(KcDetailsActivity.this, baseData.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(KcDetailsActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorMgr.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorMgr.registerListener(this
                ,mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                ,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){//加速度变更事件
            //value[0]:X轴,value[1]:Y轴，values[2]:Z轴
            float[] values = event.values;

            if ((Math.abs(values[0])>15) || Math.abs(values[1])>15 || Math.abs(values[2])>15){
                if ((getIntent().getStringExtra("state").equals("上课中"))&&(n==0)) {
                    n = 1;
                    qd();
                }
                //系统检测摇一摇事件后，震动手机提示用户，震动500毫秒
                mVibrator.vibrate(500);
            }
        }
    }

    //当传感器精度改变时回调该方法，一般无需处理
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
