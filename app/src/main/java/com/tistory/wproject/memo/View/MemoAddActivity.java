package com.tistory.wproject.memo.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tistory.wproject.memo.R;

public class MemoAddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText addMemoTitle;
    private EditText addMemoDetail;
    private Button buttonAgree;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_add);

        init();

    }

    private void init() {
        addMemoTitle = (EditText) findViewById(R.id.add_memo_title);
        addMemoDetail = (EditText) findViewById(R.id.add_memo_detail);
        buttonAgree = (Button) findViewById(R.id.add_memo_agree);
        buttonCancel = (Button) findViewById(R.id.add_memo_cancel);

        buttonAgree.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_memo_agree: {

                if (addMemoDetail.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "메모를 입력해야만 저장할 수 있습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent resultIntent = new Intent();

                    if (addMemoTitle.getText().toString().length() != 0) {
                        String title = addMemoTitle.getText().toString();
                        resultIntent.putExtra("title", title);
                    }
                    String memo = addMemoDetail.getText().toString();
                    resultIntent.putExtra("memo", memo);

                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
                break;
            }
            case R.id.add_memo_cancel: {
                setResult(RESULT_CANCELED);
                Toast.makeText(getApplicationContext(), "취소버튼을 클릭하셨습니다.", Toast.LENGTH_SHORT).show();
                finish();
                break;
            }

        }
    }
}
