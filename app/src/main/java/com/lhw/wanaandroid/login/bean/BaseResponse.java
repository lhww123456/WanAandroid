package com.lhw.wanaandroid.login.bean;

public class BaseResponse<U> {
    /**
     * Copyright 2022 bejson.com
     */

    /**
     * Auto-generated: 2022-06-01 15:58:7
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */


    private UserData data;
    private int errorCode;
    private String errorMsg;

    public void setData(UserData data) {
        this.data = data;
    }

    public UserData getData() {
        return data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}

