package com.jhm69.cse11.Model;

public class Notice {

    String name;
    String date;
    String text;
    String image;


    public Notice(String name, String date, String text, String image) {
        this.name = name;
        this.date = date;
        this.text = text;
        this.image = image;
    }
    public Notice(String name, String date, String text) {
        this.name = name;
        this.date = date;
        this.text = text;
    }

    public Notice() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}