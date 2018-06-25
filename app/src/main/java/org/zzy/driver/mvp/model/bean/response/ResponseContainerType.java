package org.zzy.driver.mvp.model.bean.response;

import java.io.Serializable;

/**
 * Created by 周正一 on 2016/8/23.
 */
public class ResponseContainerType implements Serializable {
    private static final long serialVersionUID = -2209672927900099842L;
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
