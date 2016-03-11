package com.tistory.wproject.memo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tistory.wproject.memo.View.ItemView;

import java.util.ArrayList;

/**
 * Created by Lee on 2016-03-07.
 */
public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private ArrayList<MemoItem> memoItems;
    private Activity mactivity;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


        public ItemView itemView;
        public OnItemCilckListener itemCilckListener;


        public ViewHolder(View view, OnItemCilckListener listener, Context context) {
            super(view);
            itemView = new ItemView(context);
            itemCilckListener = listener;
            itemView.textView = (TextView) view.findViewById(R.id.list_item_title);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemCilckListener.onClick(v);
        }

        @Override
        public boolean onLongClick(View v) {
            itemCilckListener.onLongClick(v);
            return true;
        }

        public static interface OnItemCilckListener {
            public void onClick(View view);

            public boolean onLongClick(View view);
        }
    }

    public MemoAdapter(ArrayList<MemoItem> memoItems, Activity activity, Context context) {
        this.memoItems = memoItems;
        this.mactivity = activity;
        this.mContext = context;
    }

    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);


        final ViewHolder vh = new ViewHolder(v, new MemoAdapter.ViewHolder.OnItemCilckListener() {
            @Override
            public void onClick(View view) {

                TextView textView = (TextView) view;
                Log.d("memo", " 성공!");
            }

            @Override
            public boolean onLongClick(View view) {
                Log.d("click", "Long Click : " + view.getId());
                return true;
            }
        }, mContext);
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
