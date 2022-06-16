package com.lhw.wanaandroid.util;

public class DBManager {
    private DBManager(){}
    private static DBManager manager = new DBManager();
    public static DBManager getInstance(){
        return manager;
    }
}
