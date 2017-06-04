package com.huangshan.tomatoseed.details.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.huangshan.tomatoseed.R;
import com.huangshan.tomatoseed.bean.SeedDetails;
import com.huangshan.tomatoseed.handler.RequestHandler;
import com.huangshan.tomatoseed.utils.Tools;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private TextView mTvName;
    private TextView mTvAdress;
    private ListView mLv;
    private TomatoSeedDetailsAdapter mAdapter;
    private String result;
    private ProgressDialog dialog;
    private SeedDetails seedDetails;
    private int mProgress = 98;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        initData();
        popupProgressDialog();
    }

    private void initData() {
        Intent resultIntent = getIntent();
        result = resultIntent.getStringExtra("URL");
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

    public void popupProgressDialog(){
        new AsyncTask<String,Integer,SeedDetails>() {
            @Override
            protected void onPreExecute() {
                dialog.show();
            }
            @Override
            protected SeedDetails doInBackground(String [] params) {
                while (mProgress<100){
                    mProgress++;
                    publishProgress(mProgress);
                }
               return downloadData();
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                mProgressBar.setProgress(values[0]);
                mProgressBar.setSecondaryProgress(values[0]*2);
            }

            @Override
            protected void onPostExecute(SeedDetails s) {
                dialog.dismiss();
                mTvName.setText(seedDetails.getName());
                mTvAdress.setText(seedDetails.getMagnet());
                mAdapter = new TomatoSeedDetailsAdapter(DetailsActivity.this,seedDetails.getSeedFiles());
                mLv.setAdapter(mAdapter);
            }
        }.execute();
    }

    private SeedDetails downloadData() {
        seedDetails = RequestHandler.getDetails(result);
        if (seedDetails==null){
            Toast.makeText(this,"未找到",Toast.LENGTH_SHORT).show();
            return null;
        }else {
            return seedDetails;
        }
    }
}
