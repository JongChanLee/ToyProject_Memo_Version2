package com.tistory.wproject.memo;

import java.io.Serializable;

/**
 * Created by Lee on 2016-03-07.
 */
public class MemoItem implements Serializable {
    private int ID;
    private String Date;
    private String Title;
    private String Memo;

    public MemoItem(int id, String memo, String date) {
        ID = id;
        Memo = memo;
        Date = date;
        Title = "제목 없음";
    }

    public MemoItem(int id, String title, String date, String memo) {
        ID = id;
        Date = date;
        Memo = memo;
        Title = title;
    }

    public int getID() {
        return ID;
    }

    public String getDate() {
        return Date;
    }

    public String getMemo() {
        return Memo;
    }

    public String getTitle() {
        return Title;
    }

    public void setID(int id){
        ID = id;
    }
}
