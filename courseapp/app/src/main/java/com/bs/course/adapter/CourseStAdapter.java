package com.bs.course.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bs.course.R;
import com.bs.course.bean.CourseStBean;
import com.bs.course.view.CircleImageView;

import java.util.List;

public class CourseStAdapter extends BaseAdapter {
    private Context context;
    private List<CourseStBean.DataBean> beanList;

    public CourseStAdapter(Context context, List<CourseStBean.DataBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_coursest,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tv_courseName = (TextView) convertView.findViewById(R.id.tv_courseName);
            viewHolder.tv_kcdescribe = (TextView) convertView.findViewById(R.id.tv_kcdescribe);
            viewHolder.tv_startTime = (TextView) convertView.findViewById(R.id.tv_startTime);
            viewHolder.touxiang = (CircleImageView)convertView.findViewById(R.id.touxiang);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_courseName.setText(beanList.get(position).getStudentId());
        viewHolder.tv_kcdescribe.setText(beanList.get(position).getName()+"   "+beanList.get(position).getSex());
        viewHolder.tv_startTime.setText(beanList.get(position).getState());
        return convertView;
    }

    private final class ViewHolder {
        TextView tv_courseName,tv_kcdescribe,tv_startTime;
        CircleImageView touxiang;
    }
}
