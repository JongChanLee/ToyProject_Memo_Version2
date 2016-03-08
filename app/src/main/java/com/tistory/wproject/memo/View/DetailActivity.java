package com.tistory.wproject.memo.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tistory.wproject.memo.R;

public class DetailActivity extends AppCompatActivity {

    private TextView title;
    private TextView date;
    private TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView) findViewById(R.id.detail_title);
        date = (TextView) findViewById(R.id.detail_date);
        detail = (TextView) findViewById(R.id.detail_detail);
    }


}
