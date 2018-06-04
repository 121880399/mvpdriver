package org.zzy.driver.mvp.model.bean.request;

/**
 * @function 出售运力实体类
 * Created by 周正一 on 2018/6/4.
 */
public class RequestSellCapacity {
    private int vehicleId;
    private String startName;
    private String startCode;
    private String vehiclecode;
    private String vehicleType;
    private String vehicleTypeCode;
    private String endCode;
    private String endName;
    private String startTime;
    private String price;
    private int vehicleAuthStatus;
    private int suport40;

    public int getVehicleAuthStatus() {
        return vehicleAuthStatus;
    }

    public void setVehicleAuthStatus(int vehicleAuthStatus) {
        this.vehicleAuthStatus = vehicleAuthStatus;
    }

    public int getSuport40() {
        return suport40;
    }

    public void setSuport40(int suport40) {
        this.suport40 = suport40;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }


    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;

    }

    public String getVehiclecode() {
        return vehiclecode;
    }

    public void setVehiclecode(String vehiclecode) {
        this.vehiclecode = vehiclecode;
    }
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getEndCode() {
        return endCode;
    }

    public void setEndCode(String endCode) {
        this.endCode = endCode;
    }
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
