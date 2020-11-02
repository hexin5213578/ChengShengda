package com.yidian.chengshengda.setup.bean;

public class UserInfoBean {


    /**
     * msg : 查找成功
     * type : OK
     * object : {"id":49,"phoneNum":"1888433236","nickName":"567986","headImg":"567986","password":null,"age":21,"lng":null,"lat":null,"createTime":"2020-10-22 15:35:10"}
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
         * id : 49
         * phoneNum : 1888433236
         * nickName : 567986
         * headImg : 567986
         * password : null
         * age : 21
         * lng : null
         * lat : null
         * createTime : 2020-10-22 15:35:10
         */

        private int id;
        private String phoneNum;
        private String nickName;
        private String headImg;
        private Object password;
        private int age;
        private Object lng;
        private Object lat;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Object getLng() {
            return lng;
        }

        public void setLng(Object lng) {
            this.lng = lng;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
