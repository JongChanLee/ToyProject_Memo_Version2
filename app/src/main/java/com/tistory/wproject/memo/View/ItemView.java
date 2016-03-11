package com.tistory.wproject.memo.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tistory.wproject.memo.MemoItem;

/**
 * Created by Lee on 2016-03-11.
 */
public class ItemView extends LinearLayout {

    public MemoItem memoItem;
    public TextView textView;

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
