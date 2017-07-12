package com.nimfa.nimfaapp.dataModels;

/**
 * Created by Таня on 11.07.2017.
 */

public class Details {

    private String title;
    private String value;

    public Details(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
