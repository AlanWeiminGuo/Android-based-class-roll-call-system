package com.bs.course.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bs.course.R;
import com.bs.course.activity.LoginActivity;
import com.bs.course.activity.MyCourseActivity;
import com.bs.course.activity.StInfoActivity;
import com.bs.course.activity.StQuekecishuActivity;
import com.bs.course.activity.UpPwdActivity;
import com.bs.course.application.MyApplication;
import com.bs.course.base.BaseFragment;

public class MyFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    /**
     * 个人资料
     */
    private TextView mTvTitle;
    private RelativeLayout mRelT;
    /**
     * 个人资料
     */
    private TextView mTvInfo;
    /**
     * 我的课堂
     */
    private TextView mTvMykt;
    /**
     * 修改密码
     */
    private TextView mTvEdpass;
    /**
     * 缺课次数查询
     */
    private TextView mst_quekecishu;
    /**
     * 退出登录
     */
    private Button mButSignOut;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.fragemnt_my, null);
        initView(view);
        return view;
    }

    private void initView( View view ) {
        mTvTitle = (TextView) view.findViewById(R.id.tv_Title);
        mRelT = (RelativeLayout) view.findViewById(R.id.rel_t);
        mTvInfo = (TextView) view.findViewById(R.id.tv_info);
        mTvInfo.setOnClickListener(this);
        mTvMykt = (TextView) view.findViewById(R.id.tv_mykt);
        mTvMykt.setOnClickListener(this);
        mTvEdpass = (TextView) view.findViewById(R.id.tv_edpass);
        mTvEdpass.setOnClickListener(this);
        mst_quekecishu = (TextView) view.findViewById(R.id.st_quekecishu);
        mst_quekecishu.setOnClickListener(this);
        mButSignOut = (Button) view.findViewById(R.id.but_sign_out);
        mButSignOut.setOnClickListener(this);
    }

    @Override
    public void onClick( View v ) {
        Intent intent;
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_info:
                intent = new Intent(getContext(), StInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_mykt:
                intent = new Intent(getContext(), MyCourseActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_edpass:
                intent = new Intent(getContext(), UpPwdActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.st_quekecishu:
                intent = new Intent(getContext(), StQuekecishuActivity.class);
                startActivity(intent);
                break;
            case R.id.but_sign_out:
                showDialog();
                break;
        }
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示");
        builder.setMessage("您确定要退出登录？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                MyApplication.getInstance().AppExit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
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
}
