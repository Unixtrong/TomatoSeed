package com.huangshan.tomatoseed.result.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.huangshan.tomatoseed.Adapter.ResultAdapter;
import com.huangshan.tomatoseed.R;
import com.huangshan.tomatoseed.bean.SearchResult;
import com.huangshan.tomatoseed.details.ui.DetailsActivity;
import com.huangshan.tomatoseed.handler.RequestHandler;
import com.huangshan.tomatoseed.search.ui.SearchActivity;
import com.huangshan.tomatoseed.utils.Tools;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private List mlist; // 这个命名不标准
    private List<SearchResult> mDataList;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mListView = (ListView) findViewById(R.id.res_lv);

        popupProgressDialog();

    }


    //接收上一页传进来的关键词，返回String
    public String getKeyword() {
        Intent intent = getIntent();
        String keyword = intent.getStringExtra("keyword");
        return keyword;
    }
    

    //利用得到的mlist，绑定adapter，展示listview
    public void showDate() {
        ResultAdapter arrayAdapter = new ResultAdapter(this, mDataList);
        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener());
    }

    //返回上一页
    public void returnLastpage() {
        Intent next = new Intent();
        next.setClass(this, SearchActivity.class);
        startActivity(next);
    }

    //开始请求网络后弹出进度条
    public void popupProgressDialog() {

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
                mDataList = RequestHandler.searchKeyword(getKeyword());
                if (mDataList == null) {
                    Tools.toastMessage(ResultActivity.this, "资源未找到！");
                    returnLastpage();
                }
                return null;
            }


            @Override
            protected void onPostExecute(String s) {
                dialog.dismiss();
                showDate();
            }
        }.execute();
    }

    // 这个方法未使用 by Adan
    private void sleepData(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}
