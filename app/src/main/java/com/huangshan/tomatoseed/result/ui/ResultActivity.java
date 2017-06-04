package com.huangshan.tomatoseed.result.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.huangshan.tomatoseed.R;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private ArrayList<番号> mlist;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mListView = (ListView) findViewById();

        prepareData();
    }


    //接收上一页传进来的关键词，返回String
    public String getKeyword() {
        String s = " ";

        return s;
    }


    //根据关键词请求服务器，返回arrarylist
    public  ArrayList<番号> prepareData(String keyword) {



        return mlist;
    }

    //利用得到的mlist，绑定adapter，展示listview
    public void showDate(ArrayList<番号> list) {


    }


    //设置listview点击事件的监听器
    class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        //           侦测点击了第几行
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        }
    }
}
