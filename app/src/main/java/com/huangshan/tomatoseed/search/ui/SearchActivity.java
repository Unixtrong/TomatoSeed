package com.huangshan.tomatoseed.search.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.huangshan.tomatoseed.R;

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


}
