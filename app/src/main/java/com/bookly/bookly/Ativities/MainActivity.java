package com.bookly.bookly.Ativities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.bookly.bookly.Fragments.Biography;
import com.bookly.bookly.Fragments.CurrentAffairs;
import com.bookly.bookly.Fragments.EnglishBooks;
import com.bookly.bookly.Fragments.FamousWriters;
import com.bookly.bookly.Fragments.Historu;
import com.bookly.bookly.Fragments.Islamic;
import com.bookly.bookly.Fragments.Motivation;
import com.bookly.bookly.Fragments.Pashto;
import com.bookly.bookly.Fragments.Political;
import com.bookly.bookly.Fragments.Social;
import com.bookly.bookly.Fragments.Sufism;
import com.bookly.bookly.Fragments.UrduNovels;
import com.bookly.bookly.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.kobakei.ratethisapp.RateThisApp;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private InterstitialAd mInterstitialAd;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.about:{
                startActivity(new Intent(MainActivity.this,AboutActivity.class));
                return true;
            }
            case  R.id.reader:{
                startActivity(new Intent(MainActivity.this,DownloadedBooks.class));
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setPageTransformer(true, new RotateUpTransformer());
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        MobileAds.initialize(this,getString(R.string.app_id));
        if(!isNetworkAvailable()){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder
                    .setTitle("No Internet Found!")
                    .setMessage("Do you want to Connect to Internet Now?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Yes button clicked, do something
                            startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    })	.create().show();
        }
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.main_activity_intersetial));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }
        });
        final Handler ha=new Handler();
        ha.postDelayed(new Runnable() {

            @Override
            public void run() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                ha.postDelayed(this, 420000);
            }
        }, 420000);
        RateThisApp.Config config = new RateThisApp.Config(2, 2);
        config.setUrl("https://play.google.com/store/apps/details?id=com.islamiccentral.islamiccentral");
        RateThisApp.init(config);
        RateThisApp.onCreate(this);
        RateThisApp.showRateDialogIfNeeded(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Biography(), "Biography");
        adapter.addFrag(new CurrentAffairs(), "Current Affairs");
        adapter.addFrag(new EnglishBooks(), "English Books");
        adapter.addFrag(new FamousWriters(), "Famous Writers");
        adapter.addFrag(new Historu(), "History");
        adapter.addFrag(new Islamic(), "Islamic");
        adapter.addFrag(new Motivation(), "Motivation");
        adapter.addFrag(new Pashto(), "Pashto");
        adapter.addFrag(new Political(), "Political");
        adapter.addFrag(new Social(), "Social");
        adapter.addFrag(new Sufism(), "Sufism");
        adapter.addFrag(new UrduNovels(), "Urdu Novels");
        viewPager.setAdapter(adapter);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
