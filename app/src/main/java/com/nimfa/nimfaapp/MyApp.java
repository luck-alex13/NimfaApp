package com.nimfa.nimfaapp;

import android.app.Application;

/**
 * NimfaApp
 * Created by Alex on 05.07.2017.
 * contact on luck.alex13@gmail.com
 * © Alexander Novikov 2017
 */

public class MyApp extends Application {

    private String userEmail;
    public static MyApp Instance;

    private int loanAmoumt;


    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public int getLoanAmoumt() {
        return loanAmoumt;
    }

    public void setLoanAmoumt(int loanAmoumt) {
        this.loanAmoumt = loanAmoumt;
    }
}
