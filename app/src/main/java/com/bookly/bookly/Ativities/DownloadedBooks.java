package com.bookly.bookly.Ativities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bookly.bookly.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

public class DownloadedBooks extends AppCompatActivity {

    private Toolbar toolbar;
    private List<File> pdfFiles;
    private ListView recyclerView;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded_books);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Downloaded Books");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pdfFiles = new ArrayList<File>();
        recyclerView = (ListView) findViewById(R.id.recycler_view);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog = ProgressDialog.show(this, "Fetching books for you...", "Please wait!");
        recyclerView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                return view;
            }
        });
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                Toast.makeText(getApplicationContext(),
                        "You have clicked " + position,
                        Toast.LENGTH_LONG).show();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Permissions.check(this, new String[]{Manifest.permission.INTERNET,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        "Reading/Writing permissions required.", new Permissions.Options()
                                .setSettingsDialogTitle("Warning!").setRationaleDialogTitle("Info"),
                        new PermissionHandler() {
                            @Override
                            public void onGranted() {
                                Toast.makeText(DownloadedBooks.this,"Permissions Granted!",Toast.LENGTH_SHORT).show();
                                walkdir(Environment.getExternalStorageDirectory());
                            }

                            @Override
                            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                                super.onDenied(context, deniedPermissions);
                                Toast.makeText(DownloadedBooks.this,"All permissions " +
                                        "required to make your experience better.\nAllow permissions to" +
                                        " use this feature.",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
        walkdir(Environment.getExternalStorageDirectory());
        mProgressDialog.dismiss();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public void walkdir(File dir) {
        String pdfPattern = ".pdf";

        File listFile[] = dir.listFiles();

        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    walkdir(listFile[i]);
                } else {
                    if (listFile[i].getName().endsWith(pdfPattern)){
                        pdfFiles.add(listFile[i]);
                        mProgressDialog.dismiss();
                    }
                }
            }
        }
    }
}
