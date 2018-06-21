package org.zzy.driver.common;

/**
 * Created by 周正一 on 2016/7/8.
 */
public enum VehicleAuthStatusEnunm {
    /**
     * ' 1 : 注册完成2 : 车辆认证完成 3 : 车辆认证未通过
     */
    //AUTHED("待审核", 1), SIGNED("已认证", 2), REGISTED("审核失败", 3);
    NOINFO("资料未提交", 0),AUTHED("注册完成", 1), SIGNED("车辆认证完成", 2), REGISTED("车辆认证未通过", 3);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private VehicleAuthStatusEnunm(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (VehicleAuthStatusEnunm vehicleAuthStatusEnunm : VehicleAuthStatusEnunm.values()) {
            if (vehicleAuthStatusEnunm.getIndex() == index) {
                return vehicleAuthStatusEnunm.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
