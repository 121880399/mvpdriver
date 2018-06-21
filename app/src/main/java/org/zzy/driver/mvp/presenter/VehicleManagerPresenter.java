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
import org.zzy.driver.mvp.model.net.api.UserApi;
import org.zzy.driver.mvp.ui.activity.VehicleManagerActivity;
import org.zzy.driver.utils.UserInfoUtils;

import java.util.ArrayList;
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
        BusinessApi.getInstance().getVehicleList(getDriverId(),this);
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
        if(requestUrl.equals(RequestCenter.VEHICLE_ACTION)&&method.equals(RequestCenter.GET_BINDVEHICLE_METHOD)){
            JSONObject mainData = response.getMainData();
            try {
                ResponseVehicle vehicle=JsonFactory.getJsonUtils().parseObject(mainData.getString("vehicleInfo"),ResponseVehicle.class);
                //修改绑定车辆以后，要更新SP
                SPUtils.getInstance().put(CommonValue.VEHICLEINFO,vehicle);
                //修改完绑定车辆以后，重新获取一些车辆列表刷新数据
                getVehicleList();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(requestUrl.equals(RequestCenter.VEHICLE_ACTION)&&method.equals(RequestCenter.BIND_VEHICLE_METHOD)){
            getView().tipBindSuccess();
            //绑定成功以后重新获取一下绑定的车辆信息
            getBindVehicle();
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }


    /**
     * 获取绑定车辆信息
     * */
    public void getBindVehicle(){
        UserApi.getInstance().getBindVehicle(getDriverId(),this);
    }

    /**
     * 得到司机id
     * */
    private int getDriverId() {
        ResponseUserInfo userInfo = UserInfoUtils.getUserInfo();
        int driverId;
        if(userInfo.getUserType()== CommonValue.SIGN_CARRIER || userInfo.getUserType()==CommonValue.AUTHENTICATION_CARRIER){
            driverId=userInfo.getCompany_id();
        }else{
            driverId=userInfo.getDriverId();
        }
        return driverId;
    }


    /**
     * 将车辆设为当前车辆
     * */
    public void bindVehicle(int currentVehicleId) {
        BusinessApi.getInstance().bindVehicle(getDriverId(),currentVehicleId,this);
    }


    /**
     * 删除车辆
     * 虽然接口支持批量删除，但是设为当前车辆一次只能设一辆，为了保持统一，不允许批量删除
     * */
    public void deleteVehicle(int vehicleId) {
        if(UserInfoUtils.getUserInfo().getUserType()!=3){
            getView().showError("承运商司机不能删除车辆！");
        }else{
            List<Integer> vehicleIds=new ArrayList<Integer>();
            vehicleIds.add(vehicleId);
            BusinessApi.getInstance().deleteVehicle(vehicleIds,this);
        }
    }
}
