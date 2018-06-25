package org.zzy.driver.mvp.model.bean.response;

import java.io.Serializable;

/**
 * Created by 周正一 on 2016/8/23.
 */
public class ResponseVehicleType implements Serializable {

    private static final long serialVersionUID = 3175776731193467001L;
    private int id;
    private String name;
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
