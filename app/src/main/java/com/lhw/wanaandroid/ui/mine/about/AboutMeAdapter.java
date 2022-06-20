package com.lhw.wanaandroid.ui.mine.about;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.OpenSource;

import java.util.List;

public class AboutMeAdapter extends BaseAdapter {

    private List<OpenSource> data;

    public AboutMeAdapter(List<OpenSource> data) {

        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        OpenSource openSource = data.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_open_resource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTvTitle.setText(openSource.getTitle());
        viewHolder.mTvDesc.setText(openSource.getDesc());

        return convertView;
    }

    static class ViewHolder {
        TextView mTvTitle,mTvDesc;
        public ViewHolder(@NonNull View itemView) {
            mTvTitle = itemView.findViewById(R.id.tv_pro_name);
            mTvDesc  = itemView.findViewById(R.id.tv_pro_desc);
        }
    }
}
