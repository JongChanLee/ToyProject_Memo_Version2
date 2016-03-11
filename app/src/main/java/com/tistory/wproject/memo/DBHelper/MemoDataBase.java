package com.tistory.wproject.memo.DBHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tistory.wproject.memo.MemoItem;

import java.util.ArrayList;

/**
 * Created by Lee on 2016-03-07.
 */
public class MemoDataBase {
    SQLiteDatabase db;

    public MemoDataBase(SQLiteDatabase db) {
        this.db = db;
    }

    public ArrayList<MemoItem> getMemoArray(String tablename) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + tablename, null);

        ArrayList<MemoItem> memoItems = new ArrayList<>();

        int count = cursor.getCount();
        String[] memo = new String[count];
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();
            memoItems.add(new MemoItem(i + 1, cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        return memoItems;
    }
}
