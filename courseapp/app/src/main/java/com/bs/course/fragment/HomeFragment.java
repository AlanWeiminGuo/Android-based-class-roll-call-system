package com.bs.course.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.course.activity.KcXqActivity;
import com.bs.course.adapter.CourseAdapter;
import com.bs.course.base.BaseFragment;
import com.bs.course.bean.CourseBean;
import com.bs.course.utils.HttpUtil;
import com.bs.course.utils.JsonUitl;
import com.bs.course.R;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class HomeFragment extends BaseFragment implements View.OnClickListener, OnBannerListener {

    private View view;
    private ImageView mBack;
    /**  */
    private TextView mTvTitleName;
    /**
     * 刷新
     */
    private TextView mTvSx;
    private ListView mConvListView;
    private ImageView mImZw;

    private Banner mBanner;

    private List<CourseBean.DataBean> beanList;
    private List<CourseBean.DataBean> beanList1 = new ArrayList<>();
    private CourseAdapter courseAdapter;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.fragment_list, null);
        initView(view);
        return view;
    }

    private void initView( View view ) {
        mBack = (ImageView) view.findViewById(R.id.back);
        mTvTitleName = (TextView) view.findViewById(R.id.tv_titleName);
        mTvSx = (TextView) view.findViewById(R.id.tv_sx);
        mTvSx.setOnClickListener(this);
        mConvListView = (ListView) view.findViewById(R.id.conv_list_view);
        mImZw = (ImageView) view.findViewById(R.id.im_zw);
        mTvTitleName.setText("全部课程");

        courseAdapter = new CourseAdapter(getActivity(),beanList1);
        mConvListView.setAdapter(courseAdapter);
        mConvListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), KcXqActivity.class);
                intent.putExtra("CourseName",beanList1.get(position).getCourseName());
                intent.putExtra("Kcdescribe",beanList1.get(position).getKcdescribe());
                intent.putExtra("StartTime",beanList1.get(position).getStartTime());
                intent.putExtra("EndTime",beanList1.get(position).getEndTime());
                intent.putExtra("state",beanList1.get(position).getState());
                intent.putExtra("courseId",beanList1.get(position).getId());
                startActivity(intent);
            }
        });

        mBanner = view.findViewById(R.id.mBanner);
        mBanner.setVisibility(View.VISIBLE);
        //图片资源
        int[] imageResourceID = new int[]{R.mipmap.ic_bg01, R.mipmap.ic_bg02, R.mipmap.ic_bg03};
        List<Integer> imgeList = new ArrayList<>();
        //轮播标题
        String[] mtitle = new String[]{"", "", ""};
        List<String> titleList = new ArrayList<>();

        for (int i = 0; i < imageResourceID.length; i++) {
            imgeList.add(imageResourceID[i]);//把图片资源循环放入list里面
            titleList.add(mtitle[i]);//把标题循环设置进列表里面
            //设置图片加载器，通过Glide加载图片
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(getActivity()).load(path).into(imageView);
                }
            });
            //设置轮播的动画效果,里面有很多种特效,可以到GitHub上查看文档。
            mBanner.setBannerAnimation(Transformer.Accordion);
            mBanner.setImages(imgeList);//设置图片资源
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);//设置banner显示样式（带标题的样式）
            mBanner.setBannerTitles(titleList); //设置标题集合（当banner样式有显示title时）
            //设置指示器位置（即图片下面的那个小圆点）
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.setDelayTime(3000);//设置轮播时间3秒切换下一图
            mBanner.setOnBannerListener(this);//设置监听
            mBanner.start();//开始进行banner渲染
        }

        getcourse_list();
    }



    @Override
    public void onClick( View v ) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_sx:
                getcourse_list();
                break;
        }
    }

    private void getcourse_list() {
        OkHttpUtils
                .post()
                .url(HttpUtil.STUDENTCOURSE_LIST)
                .addParams("state","正常")
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
                                beanList1.clear();
                                beanList1.addAll(beanList);
                                mImZw.setVisibility(View.GONE);
                                mConvListView.setVisibility(View.VISIBLE);
                                courseAdapter.notifyDataSetChanged();
                            }else {
                                mImZw.setVisibility(View.VISIBLE);
                                mConvListView.setVisibility(View.GONE);
                            }
                        }else {
                            Toast.makeText(getActivity(), courseBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError( Call arg0, Exception arg1, int arg2) {
                        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();//开始轮播
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();//结束轮播
    }

    //对轮播图设置点击监听事件
    @Override
    public void OnBannerClick(int position) {
    }
}
