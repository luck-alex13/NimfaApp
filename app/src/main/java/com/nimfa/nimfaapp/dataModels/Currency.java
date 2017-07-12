package com.nimfa.nimfaapp.dataModels;

import java.util.ArrayList;
import java.util.Map;

/**
 * NimfaApp
 * Created by Alex on 09.07.2017.
 * contact on luck.alex13@gmail.com
 * © Alexander Novikov 2017
 */

public class Currency {
    private String name, help;
    private int icon;
    private int amount;
    private float cost;
    private ArrayList<Details> details;


    public Currency(String name, float cost, int icon, ArrayList<Details> detailsArrayList,String help) {
        this.name = name;
        this.cost = cost;
        this.icon = icon;
        this.details = detailsArrayList;
        this.help = help;
    }

    public Currency(String name, float cost, int icon) {
        this.name = name;
        this.cost = cost;
        this.icon = icon;
    }

    public Currency(String name, float cost, int icon, int amount) {
        this.name = name;
        this.cost = cost;
        this.icon = icon;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ArrayList<Details> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Details> details) {
        this.details = details;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }
}
