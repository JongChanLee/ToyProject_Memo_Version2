package com.tistory.wproject.memo.DBHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tistory.wproject.memo.MemoItem;
import com.tistory.wproject.memo.View.MainActivity;

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
        Log.d("memo", "getMemoArray들어옴");
        Cursor cursor = db.rawQuery("SELECT * FROM " + tablename, null);

        ArrayList<MemoItem> memoItems = new ArrayList<>();

        int count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            Log.d("memo", "getMemoArray의 for문 들어옴");
            cursor.moveToNext();
            if (cursor.getString(0).equals("null"))
                memoItems.add(0, new MemoItem(i, null, cursor.getString(1), cursor.getString(2)));
            else
                memoItems.add(0, new MemoItem(i, cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
        return memoItems;
    }

    public void deleteAll(String tablename) {
        db.delete(tablename, null, null);
    }


    public void save(ArrayList<MemoItem> memoItems) {
        Log.d("memo", "save들어옴");
        /**
         ContentValues newValues = new ContentValues();
         for (int i = 0; i < memoItems.size(); i++) {
         newValues.put("title", memoItems.get(i).getTitle());
         newValues.put("date", memoItems.get(i).getDate());
         newValues.put("memo", memoItems.get(i).getMemo());

         db.insert(MainActivity.TABLE_NAME, null, newValues);
         }*/

        for (int i = memoItems.size() - 1; i >= 0; i--)
            db.execSQL("INSERT INTO " + MainActivity.TABLE_NAME + " VALUES " + "('" + memoItems.get(i).getTitle() + "', '"
                    + memoItems.get(i).getDate() + "', '" + memoItems.get(i).getMemo() + "')");

    }

}
