package com.yidian.chengshengda.main.bean;

import com.amap.api.maps.model.LatLng;

public class SendLocationBean {
    private String title;
    private String address;
    private LatLng latLng;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
