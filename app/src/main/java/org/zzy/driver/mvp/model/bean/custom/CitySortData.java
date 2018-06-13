package org.zzy.driver.mvp.model.bean.custom;

/**
 * Created by zhou on 2018/6/8.
 */

public class CitySortData  {

    private String name;   //显示的数据
    private String sortLetters;  //显示数据拼音的首字母
    private String nameCode;//编码
    /**
     * centerLng : 116.407526
     * parentName : 北京
     * code : 110100
     * level : 2
     * parentCode : 110000
     * geoArea : 华北
     * fullName : 北京市北京市
     * id : 2
     * centerLat : 39.90403
     */

    private double centerLng;
    private String parentName;
    private int level;
    private String parentCode;
    private String geoArea;
    private String fullName;
    private int id;
    private double centerLat;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

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
