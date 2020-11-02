package com.yidian.chengshengda.main.fragment.order.bean;

public class DeleteOrderBean {

    /**
     * msg : 该订单不存在
     * type : FAILED
     * object : 失败
     */

    private String msg;
    private String type;
    private String object;

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

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
