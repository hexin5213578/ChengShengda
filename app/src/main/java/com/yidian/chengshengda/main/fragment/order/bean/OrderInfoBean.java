package com.yidian.chengshengda.main.fragment.order.bean;

import java.util.List;

public class OrderInfoBean {

    /**
     * msg : 查找成功
     * type : OK
     * object : {"total":13,"list":[{"id":79,"userId":49,"money":100,"orderType":2,"createTime":"2020-11-02 16:41:02","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2031-11-02 :04:39:49","stationNum":"22","realMoney":null,"allMoney":null},{"id":80,"userId":49,"money":11600,"orderType":2,"createTime":"2020-11-02 16:42:19","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2030-07-02 :04:41:06","stationNum":"22","realMoney":null,"allMoney":null},{"id":81,"userId":49,"money":8200,"orderType":2,"createTime":"2020-11-02 16:42:37","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2027-09-02 :04:41:24","stationNum":"22","realMoney":null,"allMoney":null},{"id":82,"userId":49,"money":15300,"orderType":2,"createTime":"2020-11-02 16:44:39","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2025-02-02 :04:43:26","stationNum":"26","realMoney":null,"allMoney":null},{"id":83,"userId":49,"money":500,"orderType":3,"createTime":"2020-11-02 16:46:03","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2021-04-02 :04:44:50","stationNum":"22","realMoney":null,"allMoney":null},{"id":84,"userId":49,"money":600,"orderType":3,"createTime":"2020-11-02 16:47:18","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2021-05-02 :04:46:05","stationNum":"22","realMoney":null,"allMoney":null},{"id":85,"userId":49,"money":999,"orderType":2,"createTime":"2020-11-02 16:48:33","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2021-09-02 :04:47:20","stationNum":"11","realMoney":null,"allMoney":9990},{"id":86,"userId":49,"money":100,"orderType":2,"createTime":"2020-11-02 16:49:08","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2021-09-02 :04:47:55","stationNum":"22","realMoney":null,"allMoney":1000},{"id":87,"userId":49,"money":100,"orderType":1,"createTime":"2020-11-02 16:51:23","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2026-06-02 :04:50:10","stationNum":"22","realMoney":null,"allMoney":6700},{"id":88,"userId":49,"money":999,"orderType":1,"createTime":"2020-11-02 16:53:13","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2021-03-02 :04:52:00","stationNum":"11","realMoney":null,"allMoney":3996},{"id":89,"userId":49,"money":200,"orderType":1,"createTime":"2020-11-02 17:03:41","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2020-12-02 :05:02:27","stationNum":"23","realMoney":null,"allMoney":200},{"id":90,"userId":49,"money":200,"orderType":1,"createTime":"2020-11-02 17:03:46","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2024-04-02 :05:02:33","stationNum":"23","realMoney":null,"allMoney":8200},{"id":91,"userId":49,"money":999,"orderType":1,"createTime":"2020-11-02 17:05:30","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2021-05-02 :05:04:17","stationNum":"11","realMoney":null,"allMoney":5994}],"pageNum":1,"pageSize":13,"size":13,"startRow":0,"endRow":12,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1}
     */

    private String msg;
    private String type;
    private ObjectBean object;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * total : 13
         * list : [{"id":79,"userId":49,"money":100,"orderType":2,"createTime":"2020-11-02 16:41:02","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2031-11-02 :04:39:49","stationNum":"22","realMoney":null,"allMoney":null},{"id":80,"userId":49,"money":11600,"orderType":2,"createTime":"2020-11-02 16:42:19","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2030-07-02 :04:41:06","stationNum":"22","realMoney":null,"allMoney":null},{"id":81,"userId":49,"money":8200,"orderType":2,"createTime":"2020-11-02 16:42:37","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2027-09-02 :04:41:24","stationNum":"22","realMoney":null,"allMoney":null},{"id":82,"userId":49,"money":15300,"orderType":2,"createTime":"2020-11-02 16:44:39","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2025-02-02 :04:43:26","stationNum":"26","realMoney":null,"allMoney":null},{"id":83,"userId":49,"money":500,"orderType":3,"createTime":"2020-11-02 16:46:03","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2021-04-02 :04:44:50","stationNum":"22","realMoney":null,"allMoney":null},{"id":84,"userId":49,"money":600,"orderType":3,"createTime":"2020-11-02 16:47:18","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2021-05-02 :04:46:05","stationNum":"22","realMoney":null,"allMoney":null},{"id":85,"userId":49,"money":999,"orderType":2,"createTime":"2020-11-02 16:48:33","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2021-09-02 :04:47:20","stationNum":"11","realMoney":null,"allMoney":9990},{"id":86,"userId":49,"money":100,"orderType":2,"createTime":"2020-11-02 16:49:08","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2021-09-02 :04:47:55","stationNum":"22","realMoney":null,"allMoney":1000},{"id":87,"userId":49,"money":100,"orderType":1,"createTime":"2020-11-02 16:51:23","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2026-06-02 :04:50:10","stationNum":"22","realMoney":null,"allMoney":6700},{"id":88,"userId":49,"money":999,"orderType":1,"createTime":"2020-11-02 16:53:13","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2021-03-02 :04:52:00","stationNum":"11","realMoney":null,"allMoney":3996},{"id":89,"userId":49,"money":200,"orderType":1,"createTime":"2020-11-02 17:03:41","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2020-12-02 :05:02:27","stationNum":"23","realMoney":null,"allMoney":200},{"id":90,"userId":49,"money":200,"orderType":1,"createTime":"2020-11-02 17:03:46","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2024-04-02 :05:02:33","stationNum":"23","realMoney":null,"allMoney":8200},{"id":91,"userId":49,"money":999,"orderType":1,"createTime":"2020-11-02 17:05:30","nickName":"洋洋啊","phoneNum":"1888433236","endTime":"2021-05-02 :05:04:17","stationNum":"11","realMoney":null,"allMoney":5994}]
         * pageNum : 1
         * pageSize : 13
         * size : 13
         * startRow : 0
         * endRow : 12
         * pages : 1
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         */

        private int total;
        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : 79
             * userId : 49
             * money : 100.0
             * orderType : 2
             * createTime : 2020-11-02 16:41:02
             * nickName : 洋洋啊
             * phoneNum : 1888433236
             * endTime : 2031-11-02 :04:39:49
             * stationNum : 22
             * realMoney : null
             * allMoney : null
             */

            private int id;
            private int userId;
            private double money;
            private int orderType;
            private String createTime;
            private String nickName;
            private String phoneNum;
            private String endTime;
            private String stationNum;
            private Object realMoney;
            private Object allMoney;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public int getOrderType() {
                return orderType;
            }

            public void setOrderType(int orderType) {
                this.orderType = orderType;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getPhoneNum() {
                return phoneNum;
            }

            public void setPhoneNum(String phoneNum) {
                this.phoneNum = phoneNum;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getStationNum() {
                return stationNum;
            }

            public void setStationNum(String stationNum) {
                this.stationNum = stationNum;
            }

            public Object getRealMoney() {
                return realMoney;
            }

            public void setRealMoney(Object realMoney) {
                this.realMoney = realMoney;
            }

            public Object getAllMoney() {
                return allMoney;
            }

            public void setAllMoney(Object allMoney) {
                this.allMoney = allMoney;
            }
        }
    }
}
