package org.zzy.driver.mvp.model.bean.response;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * @function 推送订单实体类
 * Created by zhou on 2018/5/28.
 */

@Entity
public class ResponseOrder {

    @Id
    private long id;
    private String capacity_apply_order_id;
    private String containerType;
    private String endTime;
    private String end_address;
    private String end_contacts;
    private String end_contacts_phone;
    private String end_region;
    private String end_region_code;
    private String goods_name;
    private String price;
    private String startTime;
    private String start_address;
    private String start_contacts;
    private String start_contacts_phone;
    private String start_region;
    private String start_region_code;
    private String type;
    private String distance;
    private String waybillId;
    private String waybill_group_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCapacity_apply_order_id() {
        return capacity_apply_order_id;
    }

    public void setCapacity_apply_order_id(String capacity_apply_order_id) {
        this.capacity_apply_order_id = capacity_apply_order_id;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    public String getEnd_contacts() {
        return end_contacts;
    }

    public void setEnd_contacts(String end_contacts) {
        this.end_contacts = end_contacts;
    }

    public String getEnd_contacts_phone() {
        return end_contacts_phone;
    }

    public void setEnd_contacts_phone(String end_contacts_phone) {
        this.end_contacts_phone = end_contacts_phone;
    }

    public String getEnd_region() {
        return end_region;
    }

    public void setEnd_region(String end_region) {
        this.end_region = end_region;
    }

    public String getEnd_region_code() {
        return end_region_code;
    }

    public void setEnd_region_code(String end_region_code) {
        this.end_region_code = end_region_code;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public String getStart_contacts() {
        return start_contacts;
    }

    public void setStart_contacts(String start_contacts) {
        this.start_contacts = start_contacts;
    }

    public String getStart_contacts_phone() {
        return start_contacts_phone;
    }

    public void setStart_contacts_phone(String start_contacts_phone) {
        this.start_contacts_phone = start_contacts_phone;
    }

    public String getStart_region() {
        return start_region;
    }

    public void setStart_region(String start_region) {
        this.start_region = start_region;
    }

    public String getStart_region_code() {
        return start_region_code;
    }

    public void setStart_region_code(String start_region_code) {
        this.start_region_code = start_region_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(String waybillId) {
        this.waybillId = waybillId;
    }

    public String getWaybill_group_id() {
        return waybill_group_id;
    }

    public void setWaybill_group_id(String waybill_group_id) {
        this.waybill_group_id = waybill_group_id;
    }
}
