package org.zzy.driver.mvp.model.bean.request;



import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 周正一 on 2016/7/18.
 */
public class RequestVehicleInfo {
    private String vehicleCode;//车牌号
    private String vehicleTypeCode;//车辆类型code
    private String vehicleType;//车辆类型
    private String linkTypeCode;//拖挂类型code
    private String linkType;//拖挂形式
    private String containerType;//箱型
    private String vehicleLength;//车长
    private String vehicleWeight; //车重
    private List<Integer> containerId;//箱型id
    private HashMap<String,File> imageFileMap;//保存照片文件


    public HashMap<String, File> getImageFileMap() {
        return imageFileMap;
    }

    public void setImageFileMap(HashMap<String, File> imageFileMap) {
        this.imageFileMap = imageFileMap;
    }

    public List<Integer> getContainerId() {
        return containerId;
    }

    public void setContainerId(List<Integer> containerId) {
        this.containerId = containerId;
    }

    public String getVehicleLength() {
        return vehicleLength;
    }

    public void setVehicleLength(String vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public String getVehicleWeight() {
        return vehicleWeight;
    }

    public void setVehicleWeight(String vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleTypeCode() {
        return vehicleTypeCode;
    }

    public void setVehicleTypeCode(String vehicleTypeCode) {
        this.vehicleTypeCode = vehicleTypeCode;
    }

    public String getLinkTypeCode() {
        return linkTypeCode;
    }

    public void setLinkTypeCode(String linkTypeCode) {
        this.linkTypeCode = linkTypeCode;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }
}
