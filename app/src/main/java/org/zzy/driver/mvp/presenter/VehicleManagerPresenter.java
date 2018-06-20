package org.zzy.driver.mvp.presenter;

import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.mvp.presenter.BasePresenter;
import com.zzy.quick.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicle;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.activity.VehicleManagerActivity;
import org.zzy.driver.utils.UserInfoUtils;

import java.util.List;

/**
 * Created by zhou on 2018/6/20.
 */

public class VehicleManagerPresenter extends BasePresenter<VehicleManagerActivity> implements HttpCallBack {

    /**
     * 判断是否为承运商
     * */
    public boolean isCarrier(int userType) {
        if(userType!= CommonValue.REGISTER_USER){
            return true;
        }
        return false;
    }

    /**
     * 得到车辆列表
     * */
    public void getVehicleList() {
        ResponseUserInfo userInfo = UserInfoUtils.getUserInfo();
        int driverId;
        //如果是注册用户，那么传司机ID，如果不是注册用户传用户ID
        if(userInfo.getUserType()==CommonValue.REGISTER_USER){
            driverId=userInfo.getDriverId();
        }else{
            driverId=userInfo.getCompany_id();
        }
        BusinessApi.getInstance().getVehicleList(driverId,this);
    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.VEHICLE_ACTION) && method.equals(RequestCenter.GET_VEHICLELIST_METHOD)) {
            JSONObject mainData = response.getMainData();
            if(mainData!=null){
                try {
                    List<ResponseVehicle> vehicleList=JsonFactory.getJsonUtils().parseArray(mainData.getString("vehicleList"), ResponseVehicle.class);
                    //如果将所有车辆全部删除，那么SP中也要进行相应的更新
                    if(vehicleList.size()==0){
                        SPUtils.getInstance().remove(CommonValue.VEHICLEINFO);
                    }
                    getView().showVehicleList(vehicleList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }
}
