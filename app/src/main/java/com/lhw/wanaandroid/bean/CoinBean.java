package com.lhw.wanaandroid.bean;

import java.util.List;

public class CoinBean {

    /**
     * curPage : 1
     * datas : [{"coinCount":24,"date":1653440529000,"desc":"2022-05-25 09:02:09 签到 , 积分：10 + 14","id":622097,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":23,"date":1653354600000,"desc":"2022-05-24 09:10:00 签到 , 积分：10 + 13","id":621606,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":22,"date":1653268039000,"desc":"2022-05-23 09:07:19 签到 , 积分：10 + 12","id":620999,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":21,"date":1653013270000,"desc":"2022-05-20 10:21:10 签到 , 积分：10 + 11","id":619916,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":20,"date":1652939233000,"desc":"2022-05-19 13:47:13 签到 , 积分：10 + 10","id":619388,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":19,"date":1652837474000,"desc":"2022-05-18 09:31:14 签到 , 积分：10 + 9","id":618680,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":18,"date":1652231113000,"desc":"2022-05-11 09:05:13 签到 , 积分：10 + 8","id":615386,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":17,"date":1652148020000,"desc":"2022-05-10 10:00:20 签到 , 积分：10 + 7","id":614914,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":16,"date":1652082705000,"desc":"2022-05-09 15:51:45 签到 , 积分：10 + 6","id":614600,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":15,"date":1651914221000,"desc":"2022-05-07 17:03:41 签到 , 积分：10 + 5","id":613872,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":14,"date":1651108295000,"desc":"2022-04-28 09:11:35 签到 , 积分：10 + 4","id":610380,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":13,"date":1651037625000,"desc":"2022-04-27 13:33:45 签到 , 积分：10 + 3","id":610032,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":12,"date":1650954413000,"desc":"2022-04-26 14:26:53 签到 , 积分：10 + 2","id":609529,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":11,"date":1650872096000,"desc":"2022-04-25 15:34:56 签到 , 积分：10 + 1","id":609023,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"},{"coinCount":10,"date":1650618820000,"desc":"2022-04-22 17:13:40 签到 , 积分：10 + 0","id":607734,"reason":"签到","type":1,"userId":130208,"userName":"13184610037"}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 15
     */
    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<CoinDataBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CoinDataBean> getDatas() {
        return datas;
    }

    public void setDatas(List<CoinDataBean> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "CoinBean{" +
                "curPage=" + curPage +
                ", offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                ", datas=" + datas +
                '}';
    }

    public static class CoinDataBean {
        /**
         * coinCount : 24
         * date : 1653440529000
         * desc : 2022-05-25 09:02:09 签到 , 积分：10 + 14
         * id : 622097
         * reason : 签到
         * type : 1
         * userId : 130208
         * userName : 13184610037
         */

        private int coinCount;
        private long date;
        private String desc;
        private int id;
        private String reason;
        private int type;
        private int userId;
        private String userName;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return "CoinDataBean{" +
                    "coinCount=" + coinCount +
                    ", date=" + date +
                    ", desc='" + desc + '\'' +
                    ", id=" + id +
                    ", reason='" + reason + '\'' +
                    ", type=" + type +
                    ", userId=" + userId +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }
}
