package com.huangshan.tomatoseed.result.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.huangshan.tomatoseed.Adapter.ResultAdapter;
import com.huangshan.tomatoseed.R;
import com.huangshan.tomatoseed.bean.SearchResult;
import com.huangshan.tomatoseed.bean.SeedDetails;
import com.huangshan.tomatoseed.details.ui.DetailsActivity;
import com.huangshan.tomatoseed.handler.RequestHandler;
import com.huangshan.tomatoseed.search.ui.SearchActivity;
import com.huangshan.tomatoseed.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private List mlist;
    private List<SearchResult> mDataList;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mListView = (ListView) findViewById(R.id.res_lv);

        showDate();
    }


    //接收上一页传进来的关键词，返回String
    public String getKeyword() {
        Intent intent = getIntent();
        String keyword = intent.getStringExtra("keyword");
        return keyword;
    }

    //根据关键词请求服务器，返回List
    public List prepareData(String keyword) {
        mDataList = RequestHandler.searchKeyword(keyword);
        if (mDataList == null) {
            Tools.toastMessage(this, "资源未找到！");
            returnLastpage();
        }
        return mDataList;
    }

    //利用得到的mlist，绑定adapter，展示listview
    public void showDate() {
        ResultAdapter arrayAdapter = new ResultAdapter(this, prepareData(getKeyword()));
        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener());
    }

    //listview点击事件的监听器
    class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        //侦测点击了第几行
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            SearchResult searchresult = (SearchResult) adapterView.getItemAtPosition(position);
            String url = searchresult.getUrl();

            Intent next = new Intent();
            next.putExtra("URL", url);
            next.setClass(ResultActivity.this, DetailsActivity.class);
            startActivity(next);
        }
    }

    //返回上一页
    public void returnLastpage() {
        Intent next = new Intent();
        next.setClass(this, SearchActivity.class);
        startActivity(next);
    }

    //开始请求网络后弹出进度条
    public void popupProgressDialog(View view) {

        new AsyncTask<String, Void, String>() {
            ProgressDialog dialog;                               //弹窗进度条

            @Override
            protected void onPreExecute() {
                dialog = new ProgressDialog(ResultActivity.this);
                dialog.setCancelable(true);                     //可以点旁边取消
                dialog.setTitle("正在搜索！");
                dialog.setMessage("0");
                dialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                int mdialogmessage = 0;

                if (mDataList == null) {
                    while (mdialogmessage < 10000) {
                        sleepData(1);
                        mdialogmessage++;
                        returnLastpage();
                        Tools.toastMessage(ResultActivity.this, "请求超时！请重新搜索");

                    }
                    if (mDataList != null) {
                        dialog.dismiss();
                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String s) {
                dialog.dismiss();           //消失
            }
        }.execute();
    }


    private void sleepData(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
