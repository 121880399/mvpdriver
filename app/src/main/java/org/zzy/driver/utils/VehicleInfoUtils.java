package org.zzy.driver.utils;

import com.zzy.quick.utils.SPUtils;

import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicle;

/**
 * Created by zhou on 2018/5/11.
 */

public class VehicleInfoUtils {

    /**
     * 从sp中得到车辆信息
     * */
    public static ResponseVehicle getVehicleInfo(){
        return (ResponseVehicle)SPUtils.getInstance().getObject(CommonValue.VEHICLEINFO);
    }
}
