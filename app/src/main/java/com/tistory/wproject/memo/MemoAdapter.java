package com.tistory.wproject.memo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lee on 2016-03-07.
 */
public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    private ArrayList<MemoItem> memoItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text_Title;

        public ViewHolder(View view) {
            super(view);
            text_Title = (TextView) view.findViewById(R.id.list_item_title);
        }
    }

    public MemoAdapter(ArrayList<MemoItem> memoItems) {
        this.memoItems = memoItems;
    }

    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (memoItems.get(position).getTitle() == null) {
            holder.text_Title.setText(memoItems.get(position).getDate());
        } else {
            holder.text_Title.setText(memoItems.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return memoItems.size();
    }
}
