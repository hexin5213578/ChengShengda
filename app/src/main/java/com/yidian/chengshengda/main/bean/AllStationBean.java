package com.yidian.chengshengda.main.bean;

import java.util.List;

public class AllStationBean {

    /**
     * msg : 查询成功
     * type : OK
     * object : [{"id":null,"stationNum":"2","stationMoney":null,"lng":103.98536671612547,"lat":30.638966241746235,"createTime":null,"stationImg":null,"status":2,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"11","stationMoney":null,"lng":103.98575295422361,"lat":30.63724925237881,"createTime":null,"stationImg":null,"status":1,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"12","stationMoney":null,"lng":103.98714770291136,"lat":30.633205898976303,"createTime":null,"stationImg":null,"status":2,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"22","stationMoney":null,"lng":103.98856390927122,"lat":30.629125448670752,"createTime":null,"stationImg":null,"status":1,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"23","stationMoney":null,"lng":104.00233973477171,"lat":30.61579354753148,"createTime":null,"stationImg":null,"status":1,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"24","stationMoney":null,"lng":104.00673855755613,"lat":30.61195243755126,"createTime":null,"stationImg":null,"status":1,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"25","stationMoney":null,"lng":104.00667418453978,"lat":30.595182730900536,"createTime":null,"stationImg":null,"status":2,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"26","stationMoney":null,"lng":104.03079260800169,"lat":30.618046435372353,"createTime":null,"stationImg":null,"status":1,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"27","stationMoney":null,"lng":104.03377522442625,"lat":30.622071956878067,"createTime":null,"stationImg":null,"status":1,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"28","stationMoney":null,"lng":104.03907526943968,"lat":30.636935390816095,"createTime":null,"stationImg":null,"status":1,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"29","stationMoney":null,"lng":104.07330025646971,"lat":30.639261634698624,"createTime":null,"stationImg":null,"status":1,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"11","stationMoney":null,"lng":56,"lat":65,"createTime":null,"stationImg":null,"status":77,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"58","stationMoney":null,"lng":6,"lat":753,"createTime":null,"stationImg":null,"status":74,"place":null,"nickName":null,"endTime":null,"stationInfo":null},{"id":null,"stationNum":"55","stationMoney":null,"lng":587.66245,"lat":369.7413,"createTime":null,"stationImg":null,"status":1,"place":null,"nickName":null,"endTime":null,"stationInfo":null}]
     */

    private String msg;
    private String type;
    private List<ObjectBean> object;

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

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * id : null
         * stationNum : 2
         * stationMoney : null
         * lng : 103.98536671612547
         * lat : 30.638966241746235
         * createTime : null
         * stationImg : null
         * status : 2
         * place : null
         * nickName : null
         * endTime : null
         * stationInfo : null
         */

        private Object id;
        private String stationNum;
        private Object stationMoney;
        private double lng;
        private double lat;
        private Object createTime;
        private Object stationImg;
        private int status;
        private Object place;
        private Object nickName;
        private Object endTime;
        private String stationInfo;

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

        public Object getStationMoney() {
            return stationMoney;
        }

        public void setStationMoney(Object stationMoney) {
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

        public Object getStationImg() {
            return stationImg;
        }

        public void setStationImg(Object stationImg) {
            this.stationImg = stationImg;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getPlace() {
            return place;
        }

        public void setPlace(Object place) {
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

        public String getStationInfo() {
            return stationInfo;
        }

        public void setStationInfo(String stationInfo) {
            this.stationInfo = stationInfo;
        }
    }
}
