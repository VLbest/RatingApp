package com.exia.lan.ratingapp.utils;

import com.exia.lan.ratingapp.view.QuestionsParties.interfaces.I_QuestionsDBHandler;

public class DB {
    private static DB ourInstance = new DB();
    private static I_QuestionsDBHandler handler;

    public static DB getInstance() {
        return ourInstance;
    }

    private DB() {
    }

    public static void setDBHandler(I_QuestionsDBHandler h){
        handler = h;
    }

    public static I_QuestionsDBHandler getDBHandler(){
        return handler;
    }
}
