package com.tistory.wproject.memo.View;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.tistory.wproject.memo.DBHelper.CreateAndLoadDBHelper;
import com.tistory.wproject.memo.DBHelper.MemoDataBase;
import com.tistory.wproject.memo.MemoAdapter;
import com.tistory.wproject.memo.MemoItem;
import com.tistory.wproject.memo.MemoItemDecoration;
import com.tistory.wproject.memo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MEMO_ADD = 2000;
    public static final int REQUEST_CODE_DETAIL = 2001;
    public static final String DATABASE_NAME = "Memo.db";
    public static final String TABLE_NAME = "MemoDB";
    public int DATABASE_VERSION = 1;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayouManager;
    private ArrayList<MemoItem> memoItems;
    private MemoDataBase db;
    private SQLiteOpenHelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.deleteDatabase(DATABASE_NAME);
        getMemoArray();

        initRecyclerView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemoAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MEMO_ADD);

            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.memo_list_recyclerView);

        mRecyclerView.setHasFixedSize(true);

        mLayouManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayouManager);

        mAdapter = new MemoAdapter(memoItems, this, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.addItemDecoration(new MemoItemDecoration(getApplicationContext()));
    }

    private void getMemoArray() {
        dbhelper = new CreateAndLoadDBHelper(getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
        db = new MemoDataBase(dbhelper.getReadableDatabase());

        memoItems = db.getMemoArray(TABLE_NAME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MEMO_ADD) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "RESULT_OK", Toast.LENGTH_SHORT).show();
                String title = data.getExtras().getString("title", null);
                String memo = data.getExtras().getString("memo");

                long miliTime = System.currentTimeMillis();
                Date mdate = new Date(miliTime);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH시 mm분", Locale.KOREA);
                String date = dateFormat.format(mdate);

                MemoItem newMemo = new MemoItem(memoItems.size(), title, date, memo);
                memoItems.add(0, newMemo);
                mAdapter.notifyDataSetChanged();

            }
        }
    }

    public void removeMemo(int ID) {

        final int removePosition = memoItems.size() - ID - 1;
        final MemoItem deleteItem = memoItems.remove(removePosition);
        for (int i = removePosition - 1; i >= 0; i--) {
            int id = memoItems.get(i).getID();
            memoItems.get(i).setID(id - 1);
        }
        mAdapter.notifyDataSetChanged();
        String snackbarText;
        if (deleteItem.getTitle() == null)
            snackbarText = deleteItem.getDate();
        else
            snackbarText = deleteItem.getTitle();
        Snackbar.make(mRecyclerView,  snackbarText + "\n 메모가 삭제되었습니다.", Snackbar.LENGTH_LONG).setAction("되돌리기", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoItems.add(removePosition, deleteItem);
                mAdapter.notifyDataSetChanged();
            }
        }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.deleteAll(TABLE_NAME);
        db.save(memoItems);
    }
}
