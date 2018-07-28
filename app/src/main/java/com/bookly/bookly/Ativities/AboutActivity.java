package com.bookly.bookly.Ativities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bookly.bookly.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Element versionElement = new Element();
        versionElement.setTitle("Version 1.0");
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.about)
                .setDescription("Bookly is a initiative to spread books all around your devices." +
                        " Now download any latest book you like on your phone without searching hours for a book." +
                        " Our app contains hundreds of books ready to read and all of those books are free." +
                        " So, what are you waiting for? start reading now!")
                .addItem(versionElement)
                .addGroup("Connect with us")
                .addEmail("haseeb.emailbox@gmail.com")
                .addWebsite("http://kasiflerlabs.com/")
                .addFacebook("kasiflerlabs")
                .addPlayStore("com.bookly.bookly")
                .addGitHub("iam-mhaseeb")
                .create();
        setContentView(aboutPage);
    }
}
