package com.bs.course.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BaseFragment extends Fragment {

    private String activityTag = "ActivityName";
    protected View view;

    protected void setContentView(@LayoutRes int viewRes) {
        this.view = View.inflate(getContext(), viewRes, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            return view;
        }
    }

}
