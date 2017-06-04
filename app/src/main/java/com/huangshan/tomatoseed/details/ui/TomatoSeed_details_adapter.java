package com.huangshan.tomatoseed.details.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by aloong on 2017/5/30.
 */

public class TomatoSeed_details_adapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;

    public TomatoSeed_details_adapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
