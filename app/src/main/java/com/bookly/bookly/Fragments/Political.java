package com.bookly.bookly.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bookly.bookly.Classes.Book;
import com.bookly.bookly.Holders.BooksHolder;
import com.bookly.bookly.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;

/**
 * A simple {@link Fragment} subclass.
 */
public class Political extends Fragment {

    private RecyclerView recyclerView;
    private Context c;
    private FirebaseRecyclerAdapter<Book, BooksHolder> madapter;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog mProgressDialog;
    Timer updateAdsTimer;
    private GridLayoutManager mLayoutManager;
    public Political() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_political, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        c = getActivity();
        mLayoutManager = new GridLayoutManager(c,2);
        recyclerView.setLayoutManager(mLayoutManager);
        mProgressDialog = new ProgressDialog(c);
        mProgressDialog = ProgressDialog.show(c, "Fetching books for you...", "Please wait!");
        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseRecyclerOptions<Book> options =
                new FirebaseRecyclerOptions.Builder<Book>()
                        .setQuery(firebaseDatabase.getReference().child("Political"), Book.class)
                        .build();
        madapter = new FirebaseRecyclerAdapter<Book, BooksHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BooksHolder holder, int position, Book model) {
                holder.setmContext(c);
                holder.setImgUrl(model.getImage());
                holder.setBookTitle(model.getTitle());
                holder.setDownloadUrl(model.getLink());
            }

            @Override
            public BooksHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.book_item, parent, false);
                return new BooksHolder(view);
            }
        };
        recyclerView.setAdapter(madapter);
        mProgressDialog.dismiss();
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        madapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        madapter.stopListening();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(updateAdsTimer!=null)
            updateAdsTimer.cancel();
    }


}
