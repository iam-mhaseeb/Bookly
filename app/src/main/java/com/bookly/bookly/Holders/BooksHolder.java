package com.bookly.bookly.Holders;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookly.bookly.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by Haseeb on 13/05/2018.
 */

public class BooksHolder extends RecyclerView.ViewHolder {

    private ImageView bookImg;
    private TextView bookTitle;
    private ImageButton downloadBook;
    private String imgUrl, downloadUrl;
    private Context mContext;


    public BooksHolder(final View itemView){
        super(itemView);

        bookImg = (ImageView) itemView.findViewById(R.id.img);
        bookTitle = (TextView) itemView.findViewById(R.id.title);
        downloadBook = (ImageButton) itemView.findViewById(R.id.download);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        Glide.with(mContext)
                .load(imgUrl)
                .thumbnail(Glide.with(mContext)
                        .load(R.mipmap.ic_launcher))
                .into(bookImg);
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public void setmContext(Context context){
        mContext = context;
    }

    public String getBookTitle() {
        return bookTitle.getText().toString();
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle.setText(bookTitle);
    }
}
