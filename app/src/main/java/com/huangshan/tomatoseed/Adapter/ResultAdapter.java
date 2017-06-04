package com.huangshan.tomatoseed.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huangshan.tomatoseed.R;
import com.huangshan.tomatoseed.bean.SearchResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meepo on 2017/6/4.
 */

public class ResultAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<SearchResult> mData;
    private LayoutInflater mInflater;


    public ResultAdapter(Context context, List<SearchResult> mdata) {
        mContext = context;
        mData = mdata;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    //获取arraylist中的数据对象的Name
    public String getItemName(int position) {
        SearchResult searchResult = mData.get(position);
        String name = searchResult.getName();
        return name;
    }

    //获取arraylist中的数据对象的id
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            // 该类型的布局第一次出现在界面上
            holder = new Holder();
            convertView = mInflater.inflate(R.layout.res_adapter, parent, false);
            holder.mTvName = (TextView) convertView.findViewById(R.id.res_ad_tv);
            convertView.setTag(holder);
        } else {
            // 从控件的 tag 中加载 holder
            holder = (Holder) convertView.getTag();
        }
        // 通过索引从数据集合中获取数据对象
        SearchResult searchresult = mData.get(position);
        holder.mTvName.setText(searchresult.getName());
        return convertView;
    }


    private class Holder {
        TextView mTvName;
    }
}




