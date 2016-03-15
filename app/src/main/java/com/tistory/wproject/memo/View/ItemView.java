package com.tistory.wproject.memo.View;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tistory.wproject.memo.MemoItem;

/**
 * Created by Lee on 2016-03-11.
 */
public class ItemView extends LinearLayout implements View.OnClickListener, View.OnLongClickListener {

    public MemoItem memoItem;
    public TextView textView;
    private Context mContext;
    private MainActivity mActivity;

    public ItemView(Context context, MainActivity mActivity) {
        super(context);
        this.mContext = context;
        this.mActivity = mActivity;
    }

    public ItemView(Context context, AttributeSet attrs, MainActivity mActivity) {
        super(context, attrs);
        this.mContext = context;
        this.mActivity = mActivity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("memoitem", memoItem);
        View sharedView = textView;
        String transitionName;
        if (memoItem.getTitle() == null) {
            textView.setTransitionName("sharedDate");
            transitionName = "sharedDate";
        } else
            transitionName = "sharedView";

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(mActivity, sharedView, transitionName);
        mActivity.startActivityForResult(intent, MainActivity.REQUEST_CODE_DETAIL, transitionActivityOptions.toBundle());
    }

    @Override
    public boolean onLongClick(View v) {
        Dialog dialog = createDialogBox(v);
        dialog.show();
        return false;
    }

    private AlertDialog createDialogBox(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        if (memoItem.getTitle() == null)
            builder.setTitle(memoItem.getDate());
        else
            builder.setTitle(memoItem.getTitle());
        builder.setMessage("메모를 삭제하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mActivity.removeMemo(memoItem.getID());
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, "삭제를 취소하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
