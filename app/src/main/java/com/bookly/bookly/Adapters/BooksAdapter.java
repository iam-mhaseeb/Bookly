package com.bookly.bookly.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookly.bookly.Classes.Book;
import com.bookly.bookly.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Muhammad Haseeb on 2/18/2017.
 */

public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Object> dealslist;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, des;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            des = (TextView) view.findViewById(R.id.des);
            thumbnail = (ImageView) view.findViewById(R.id.img);
        }
    }
    public BooksAdapter(Context mContext, List<Object> dealslist) {
        this.mContext = mContext;
        this.dealslist = dealslist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.book_item, parent, false);
        viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Book album = (Book) dealslist.get(holder.getAdapterPosition());
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.title.setText(album.getTitle());
        Glide.with(mContext)
                .load(album.getImage())
                .into(viewHolder.thumbnail);
        viewHolder.des.setText(album.getDesc());
        viewHolder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return dealslist.size();
    }

}