package com.bookly.bookly.Classes;

/**
 * Created by Muhammad Haseeb on 2/18/2017.
 */

public class Book {
    String title;
    String image;
    String desc;
    String link;

    public Book(String title, String image, String desc, String link) {
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
