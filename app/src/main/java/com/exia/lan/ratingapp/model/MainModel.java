package com.exia.lan.ratingapp.model;

public class MainModel extends AbstractlModel {

    private String BtText;

    public MainModel(){
        this.BtText = "Start";
    }

    public void setVal(String text){
        this.BtText = text;
        setChanged();
        notifyObservers();
    }

    public String getBtText(){
        return this.BtText;
    }

}
