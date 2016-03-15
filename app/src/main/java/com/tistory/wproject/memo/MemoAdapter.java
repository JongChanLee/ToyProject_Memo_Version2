package com.tistory.wproject.memo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.tistory.wproject.memo.View.ItemView;
import com.tistory.wproject.memo.View.MainActivity;

import java.util.ArrayList;

/**
 * Created by Lee on 2016-03-07.
 */
public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private ArrayList<MemoItem> memoItems;
    private MainActivity mactivity;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ItemView itemView;

        public ViewHolder(View view, Context context, MainActivity activity) {
            super(view);
            itemView = new ItemView(context, activity);
            itemView.textView = (TextView) view.findViewById(R.id.list_item_title);
            itemView.textView.setClickable(true);
            itemView.textView.setOnClickListener(itemView);
            itemView.textView.setOnLongClickListener(itemView);
            ViewParent parentView = itemView.textView.getParent();
            View parent = (View) parentView;
            parent.setOnClickListener(itemView);
            parent.setOnLongClickListener(itemView);
        }

    }

    public MemoAdapter(ArrayList<MemoItem> memoItems, MainActivity activity, Context context) {
        this.memoItems = memoItems;
        this.mactivity = activity;
        this.mContext = context;
    }

    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);

        final ViewHolder vh = new ViewHolder(v, mContext, mactivity);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (memoItems.get(position).getTitle() == null) {
            holder.itemView.textView.setText(memoItems.get(position).getDate());
            holder.itemView.memoItem = memoItems.get(position);
        } else {
            holder.itemView.textView.setText(memoItems.get(position).getTitle());
            holder.itemView.memoItem = memoItems.get(position);
        }
    }

    @Override
    public int getItemCount() {
        return memoItems.size();
    }
}
