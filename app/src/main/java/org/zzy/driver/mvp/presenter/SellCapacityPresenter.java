package org.zzy.driver.mvp.presenter;

import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.mvp.presenter.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;
import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicle;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.fragment.SellCapacityFragment;
import org.zzy.driver.utils.UserInfoUtils;

import java.util.List;

/**
 * Created by zhou on 2018/6/4.
 */

public class SellCapacityPresenter extends BasePresenter<SellCapacityFragment> implements HttpCallBack {

    /**
     * @function 得到车辆列表
     * */
    public void getVehicleList(){
        BusinessApi api=new BusinessApi();
        ResponseUserInfo userInfo = UserInfoUtils.getUserInfo();
        int userType = userInfo.getUserType();
        if(userType== CommonValue.SIGN_CARRIER||userType==CommonValue.AUTHENTICATION_CARRIER){
            api.getVehicleList(userInfo.getCompany_id(),this);
        }else {
            api.getVehicleList(userInfo.getDriverId(),this);
        }
    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.VEHICLE_ACTION) && method.equals(RequestCenter.GET_VEHICLELIST_METHOD)) {
            JSONObject mainData = response.getMainData();
            try {
                List<ResponseVehicle> vehicleList=JsonFactory.getJsonUtils().parseArray(mainData.getString("vehicleList"), ResponseVehicle.class);
                getView().showVehicleList(vehicleList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }
}
