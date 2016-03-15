package com.tistory.wproject.memo.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tistory.wproject.memo.MemoItem;
import com.tistory.wproject.memo.R;

public class DetailActivity extends AppCompatActivity {

    private TextView title;
    private TextView date;
    private TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MemoItem memoItem = (MemoItem) getIntent().getSerializableExtra("memoitem");

        title = (TextView) findViewById(R.id.detail_title);
        date = (TextView) findViewById(R.id.detail_date);
        detail = (TextView) findViewById(R.id.detail_detail);
        if ( memoItem.getTitle() == null ) {
            title.setText("제목 없음");
        } else {
            title.setText(memoItem.getTitle());
        }
        date.setText(memoItem.getDate());
        detail.setText(memoItem.getMemo());


    }

}
