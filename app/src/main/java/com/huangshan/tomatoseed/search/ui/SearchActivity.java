package com.huangshan.tomatoseed.search.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.huangshan.tomatoseed.R;
import com.huangshan.tomatoseed.result.ui.ResultActivity;

public class SearchActivity extends AppCompatActivity {

    private EditText mKeyEditText;
    private ImageView mSearchImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        mKeyEditText = (EditText) findViewById(R.id.et_ser_keyword);
        mSearchImageView = (ImageView) findViewById(R.id.iv_ser_button);
    }

    public void onClickSearch(View view) {
        final String keyword = mKeyEditText.getText().toString();
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("keyword", keyword);
        startActivity(intent);
    }
}
