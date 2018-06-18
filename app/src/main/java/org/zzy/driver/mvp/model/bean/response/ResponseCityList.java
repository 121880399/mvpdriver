package org.zzy.driver.mvp.model.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhou on 2018/6/8.
 */

public class ResponseCityList {

    /**
     * centerLng : 116.407526
     * parentName : 北京
     * code : 110100
     * level : 2
     * parentCode : 110000
     * name : 北京
     * geoArea : 华北
     * fullName : 北京市北京市
     * id : 2
     * centerLat : 39.90403
     */

    private double centerLng;
    private String parentName;
    private String code;
    private int level;
    private String parentCode;
    private String name;
    private String geoArea;
    private String fullName;
    private int id;
    private double centerLat;
    /**
     * centerLng : 116.407526
     * centerLat : 39.90403
     */



    public double getCenterLng() {
        return centerLng;
    }

    public void setCenterLng(double centerLng) {
        this.centerLng = centerLng;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeoArea() {
        return geoArea;
    }

    public void setGeoArea(String geoArea) {
        this.geoArea = geoArea;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(double centerLat) {
        this.centerLat = centerLat;
    }

}
