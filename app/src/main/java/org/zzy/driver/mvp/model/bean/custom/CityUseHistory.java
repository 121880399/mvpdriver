package org.zzy.driver.mvp.model.bean.custom;


import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;

/**
 * @function 城市选择记录
 */
@Entity
public class CityUseHistory implements Serializable{
    private static final long serialVersionUID = 4373650575500934123L;
    @Id
    private long id;
    private String name;
    private String code;
    @Index
    private String createUser;
    private java.util.Date updateTime;
    private Integer count;
    private String centerLat;
    private String centerLng;
    private String fullCityName;
    private String parentName;

    public CityUseHistory() {
    }

    public CityUseHistory(Long id) {
        this.id = id;
    }

    public CityUseHistory(Long id, String name, String code, String createUser, java.util.Date updateTime, Integer count, String centerLat, String centerLng, String fullCityName, String parentName) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.count = count;
        this.centerLat = centerLat;
        this.centerLng = centerLng;
        this.fullCityName = fullCityName;
        this.parentName = parentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(String centerLat) {
        this.centerLat = centerLat;
    }

    public String getCenterLng() {
        return centerLng;
    }

    public void setCenterLng(String centerLng) {
        this.centerLng = centerLng;
    }

    public String getFullCityName() {
        return fullCityName;
    }

    public void setFullCityName(String fullCityName) {
        this.fullCityName = fullCityName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

}
