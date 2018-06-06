package org.zzy.driver.mvp.model.net.api;

import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpRequest;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.utils.MD5Util;

/**
 * @function 业务API
 * Created by zhou on 2018/5/30.
 */

public class BusinessApi  extends BaseApi{

    /**
     * 抢单接口
     * */
    public void acceptOrder(int driverId, int capacityApplyOrderId, int waybillGroupId,int support40GP, HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.WAYBILL_ACTION);
        request.addHeader("method", RequestCenter.ACCESSORDER_METHOD);
        request.putParams("driverId",driverId);
        request.putParams("capacityApplyOrderId",capacityApplyOrderId);
        request.putParams("waybillGroupId",waybillGroupId);
        request.putParams("support40GP", support40GP);
        doPost(request,callBack);
    }

    /**
     * 得到车辆列表接口
     * */
    public void getVehicleList(int driverId,HttpCallBack callBack){
        HttpRequest request=new HttpRequest();
        request.addHeader("action", RequestCenter.VEHICLE_ACTION);
        request.addHeader("method", RequestCenter.GET_VEHICLELIST_METHOD);
        request.putParams("driverId",driverId);
        doPost(request,callBack);
    }
}
