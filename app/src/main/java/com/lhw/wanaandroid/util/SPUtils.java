package com.lhw.wanaandroid.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {
    private SPUtils(){};
    private static SPUtils instance  = new SPUtils();
    //SP存储对象
    private static SharedPreferences sp;
    public static SPUtils getInstance(){
        if(sp == null){
            sp = MyApplication.getGloableContext().getSharedPreferences("bank",
                    Context.MODE_PRIVATE);
        }
        return instance;
    }

    //保存数据
    public  void saveState(String key,Object value){
        if(value instanceof String){
            sp.edit().putString(key, (String) value).commit();
        }else if(value instanceof Boolean){
            sp.edit().putBoolean(key, (Boolean) value).commit();
        }else if(value instanceof Integer){
            sp.edit().putInt(key, (Integer) value).commit();
        }
    }

    //获取数据
    public  String getState(String key,String defValue){
        return  sp.getString(key,defValue);
    }

    public  Boolean getState(String key,Boolean defValue){
        return  sp.getBoolean(key,defValue);
    }
    public  Integer getState(String key,int defValue){
        return  sp.getInt(key,defValue);
    }


}
