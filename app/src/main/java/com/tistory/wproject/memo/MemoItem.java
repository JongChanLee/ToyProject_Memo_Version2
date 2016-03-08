package com.tistory.wproject.memo;

/**
 * Created by Lee on 2016-03-07.
 */
public class MemoItem {
    private String Date;
    private String Title;
    private String Memo;

    public MemoItem(String memo, String date) {
        Memo = memo;
        Date = date;
        Title = "제목 없음";
    }

    public MemoItem(String title, String date, String memo) {
        Date = date;
        Memo = memo;
        Title = title;
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
}
