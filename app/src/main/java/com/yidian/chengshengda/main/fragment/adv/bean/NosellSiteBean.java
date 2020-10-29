package com.yidian.chengshengda.main.fragment.adv.bean;

import java.util.List;

public class NosellSiteBean {

    /**
     * msg : 查询成功
     * type : OK
     * object : {"total":8,"list":[{"id":null,"stationNum":"11","stationMoney":999,"lng":103.98575295422361,"lat":30.63724925237881,"createTime":null,"stationImg":null,"status":null,"place":"七里生活广场","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"22","stationMoney":100,"lng":103.98856390927122,"lat":30.629125448670752,"createTime":null,"stationImg":null,"status":null,"place":"培风小区","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"23","stationMoney":200,"lng":104.00233973477171,"lat":30.61579354753148,"createTime":null,"stationImg":null,"status":null,"place":"成都市第三十七中学","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"24","stationMoney":100,"lng":104.00673855755613,"lat":30.61195243755126,"createTime":null,"stationImg":null,"status":null,"place":"爱萌亲子园","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"26","stationMoney":300,"lng":104.03079260800169,"lat":30.618046435372353,"createTime":null,"stationImg":null,"status":null,"place":"易鹏汽修","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"27","stationMoney":200,"lng":104.03377522442625,"lat":30.622071956878067,"createTime":null,"stationImg":null,"status":null,"place":"子墨便利店","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"28","stationMoney":200,"lng":104.03907526943968,"lat":30.636935390816095,"createTime":null,"stationImg":null,"status":null,"place":"万寿桥路站","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"29","stationMoney":300,"lng":104.07330025646971,"lat":30.639261634698624,"createTime":null,"stationImg":null,"status":null,"place":"红坝子火锅","nickName":null,"endTime":null,"stationInfo":null}],"pageNum":1,"pageSize":10,"size":8,"startRow":1,"endRow":8,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1}
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
         * total : 8
         * list : [{"id":null,"stationNum":"11","stationMoney":999,"lng":103.98575295422361,"lat":30.63724925237881,"createTime":null,"stationImg":null,"status":null,"place":"七里生活广场","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"22","stationMoney":100,"lng":103.98856390927122,"lat":30.629125448670752,"createTime":null,"stationImg":null,"status":null,"place":"培风小区","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"23","stationMoney":200,"lng":104.00233973477171,"lat":30.61579354753148,"createTime":null,"stationImg":null,"status":null,"place":"成都市第三十七中学","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"24","stationMoney":100,"lng":104.00673855755613,"lat":30.61195243755126,"createTime":null,"stationImg":null,"status":null,"place":"爱萌亲子园","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"26","stationMoney":300,"lng":104.03079260800169,"lat":30.618046435372353,"createTime":null,"stationImg":null,"status":null,"place":"易鹏汽修","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"27","stationMoney":200,"lng":104.03377522442625,"lat":30.622071956878067,"createTime":null,"stationImg":null,"status":null,"place":"子墨便利店","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"28","stationMoney":200,"lng":104.03907526943968,"lat":30.636935390816095,"createTime":null,"stationImg":null,"status":null,"place":"万寿桥路站","nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"29","stationMoney":300,"lng":104.07330025646971,"lat":30.639261634698624,"createTime":null,"stationImg":null,"status":null,"place":"红坝子火锅","nickName":null,"endTime":null,"stationInfo":null}]
         * pageNum : 1
         * pageSize : 10
         * size : 8
         * startRow : 1
         * endRow : 8
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
             * id : null
             * stationNum : 11
             * stationMoney : 999
             * lng : 103.98575295422361
             * lat : 30.63724925237881
             * createTime : null
             * stationImg : null
             * status : null
             * place : 七里生活广场
             * nickName : null
             * endTime : null
             * stationInfo : null
             */

            private Object id;
            private String stationNum;
            private int stationMoney;
            private double lng;
            private double lat;
            private Object createTime;
            private String stationImg;
            private Object status;
            private String place;
            private Object nickName;
            private Object endTime;
            private Object stationInfo;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getStationNum() {
                return stationNum;
            }

            public void setStationNum(String stationNum) {
                this.stationNum = stationNum;
            }

            public int getStationMoney() {
                return stationMoney;
            }

            public void setStationMoney(int stationMoney) {
                this.stationMoney = stationMoney;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public String getStationImg() {
                return stationImg;
            }

            public void setStationImg(String stationImg) {
                this.stationImg = stationImg;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }

            public Object getNickName() {
                return nickName;
            }

            public void setNickName(Object nickName) {
                this.nickName = nickName;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public Object getStationInfo() {
                return stationInfo;
            }

            public void setStationInfo(Object stationInfo) {
                this.stationInfo = stationInfo;
            }
        }
    }
}
