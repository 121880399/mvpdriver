package org.zzy.driver.mvp.model.bean.response;


import java.io.Serializable;

/**
 * Created by 周正一 on 2018/5/24.
 */
public class ResponseVehicle  implements Serializable {
    private int driver_id;//司机id
    private String code;//车牌号
    private int owner_type;//归属类型
    private int isActivite;//是否为当前车辆
    private int vehicle_id;//车辆id
    private String vehicle_type_code;//车辆类型编码
    private String vehicle_type;//车辆类型名称
    private int support_40gp;//是否支持40以上箱型
    private int auth_status;//认证状态
    private int status;//车辆状态

    public int getOwner_type() {
        return owner_type;
    }

    public void setOwner_type(int owner_type) {
        this.owner_type = owner_type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSupport_40gp() {
        return support_40gp;
    }

    public void setSupport_40gp(int support_40gp) {
        this.support_40gp = support_40gp;
    }

    public String getVehicle_type_code() {
        return vehicle_type_code;
    }

    public void setVehicle_type_code(String vehicle_type_code) {
        this.vehicle_type_code = vehicle_type_code;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIsActivite() {
        return isActivite;
    }

    public void setIsActivite(int isActivite) {
        this.isActivite = isActivite;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getAuth_status() {
        return auth_status;
    }

    public void setAuth_status(int auth_status) {
        this.auth_status = auth_status;
    }
}
