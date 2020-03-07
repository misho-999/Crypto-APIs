package com.taskcrypto.service.models;

import javax.xml.crypto.Data;
import java.util.Date;

public class NewsServiceModel {
    private Date date;
    private String title;
    private String shortDescription;
    private String text;

    public NewsServiceModel() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
