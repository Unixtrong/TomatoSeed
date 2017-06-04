package com.huangshan.tomatoseed.details.ui;

import android.content.Context;
import android.support.v4.util.Pair;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huangshan.tomatoseed.R;
import com.huangshan.tomatoseed.bean.SeedDetails;

import java.util.List;

/**
 * Created by aloong on 2017/5/30.
 */

public class TomatoSeed_details_adapter extends BaseAdapter{
    private Context mContext;
    private List<Pair<String,String>> mList;
    private LayoutInflater mInflater;

    public TomatoSeed_details_adapter(Context context, List<Pair<String,String>> list) {
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mInflater = LayoutInflater.from(mContext);
        Holder holder ;
        if (convertView==null){
            convertView = mInflater.inflate(R.layout.tomatoseed_details_adapter,parent,false);
            holder = new Holder();
            holder.mName = (TextView) convertView.findViewById(R.id.TomatoSeed_details_adapter_tv_left);
            holder.mSize = (TextView) convertView.findViewById(R.id.TomatoSeed_details_adapter_tv_right);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }
        Pair<String, String> pair = mList.get(position);
        holder.mName.setText(pair.first);
        holder.mSize.setText(pair.second);
        return convertView;
    }

    private class Holder{
        private TextView mName;
        private TextView mSize;
    }
}
