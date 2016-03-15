package com.tistory.wproject.memo.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tistory.wproject.memo.View.MainActivity;

/**
 * Created by Lee on 2016-03-07.
 */
public class CreateAndLoadDBHelper extends SQLiteOpenHelper {

    public CreateAndLoadDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SQL = "create table " + MainActivity.TABLE_NAME + "("
                + " title text, "
                + " date text, "
                + " memo text)";

        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
