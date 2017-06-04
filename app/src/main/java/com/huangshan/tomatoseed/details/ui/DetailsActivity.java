package com.huangshan.tomatoseed.details.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.huangshan.tomatoseed.R;
import com.huangshan.tomatoseed.bean.SeedDetails;
import com.huangshan.tomatoseed.handler.RequestHandler;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private TextView mTvName;
    private TextView mTvAdress;
    private ListView mLv;
    private TomatoSeed_details_adapter mAdapter;
    private String result;
    private ProgressDialog dialog;
    private String mName;
    private String mMagnet;
    private List<> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        initData();
    }

    private void initData() {
        Intent resultIntent = new Intent();
        result = resultIntent.getStringExtra();
    }

    private void initView() {
        mTvName = (TextView) findViewById(R.id.TomatoSeed_details_tv_name);
        mTvAdress = (TextView) findViewById(R.id.TomatoSeed_details_tv_adress);
        mLv = (ListView) findViewById(R.id.TomatoSeed_details_lv);
        dialog = new ProgressDialog(DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("正在读取详情");
        dialog.setMessage("请稍等");
    }

    public void popupProgressDialog(View view){
        new AsyncTask<String,String,String>() {
            @Override
            protected void onPreExecute() {
                dialog.show();
            }
            @Override
            protected String doInBackground(String[] params) {
                downloadData();
                return result;
            }
            @Override
            protected void onPostExecute(String s) {
                dialog.dismiss();
                mTvName.setText();
                mTvAdress.setText();
                mAdapter = new TomatoSeed_details_adapter(DetailsActivity.this);
                mLv.setAdapter(mAdapter);
            }
        }.execute();
    }

    private String downloadData() {
        SeedDetails seedDetails = RequestHandler.getDetails(result);
        mName = seedDetails.getName();
        mMagnet = seedDetails.getMagnet();
        mList= seedDetails.getSeedFiles();
        return null;
    }
}
