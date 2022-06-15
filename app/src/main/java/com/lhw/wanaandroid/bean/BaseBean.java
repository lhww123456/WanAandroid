package com.lhw.wanaandroid.bean;

public class BaseBean<T> {
    /**
     * Copyright 2022 bejson.com
     */

    /**
     * Auto-generated: 2022-06-01 9:58:7
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */


        private T data;
        private int errorCode;
        private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
                "datas=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}

