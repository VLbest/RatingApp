package com.exia.lan.ratingapp.utils;

public class DB {
    private static DB ourInstance = new DB();
    private static DBHandler handler;

    public static DB getInstance() {
        return ourInstance;
    }

    private DB() {
    }

    public static void setDBHandler(DBHandler h){
        handler = h;
    }

    public static DBHandler getDBHandler(){
        return handler;
    }
}
