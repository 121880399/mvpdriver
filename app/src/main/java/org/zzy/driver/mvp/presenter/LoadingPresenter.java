package org.zzy.driver.mvp.presenter;

import android.text.TextUtils;

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
import org.zzy.driver.mvp.model.net.api.UserApi;
import org.zzy.driver.mvp.ui.activity.LoadingActivity;

/**
 * Created by zhou on 2018/4/4.
 */

public class LoadingPresenter extends BasePresenter<LoadingActivity> implements HttpCallBack {


    /**
     * 判断用户是否登录,通过Token来判断，因为现在Loging接口返回的是token
     * */
    public boolean isLogin(){
        String token = SPUtils.getInstance().getString(CommonValue.TOKENCODE);
        if(!TextUtils.isEmpty(token)){
            return true;
        }
        return false;
    }

    /**
     * 是否是第一次使用
     * */
    public boolean isFirstAccess(){
        return SPUtils.getInstance().getBoolean(CommonValue.FIRSTACCESS,false);
    }

    /**
     * 设置已经访问过APP
     * */
    public void setAlreadyAccess(){
        SPUtils.getInstance().put(CommonValue.FIRSTACCESS,true);
    }


    /**
     * 获取用户信息
     * */
    public void getUserInfo(){
        UserApi.getInstance().getUserInfo(0,this);
    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if(requestUrl.equals(RequestCenter.USER_ACTION) && method.equals(RequestCenter.GET_USERINFO_METHOD)){
            JSONObject mainData = response.getMainData();
            try {
                ResponseUserInfo userInfo = JsonFactory.getJsonUtils().parseObject(mainData.getString("userInfo"), ResponseUserInfo.class);
                SPUtils.getInstance().put(CommonValue.USERINFO, userInfo);
                SPUtils.getInstance().put(CommonValue.USERID, String.valueOf(userInfo.getId()));
                //如果是签约承运商或者是认证承运商 就通过公司id查找车辆信息
                if (userInfo.getUserType() == CommonValue.SIGN_CARRIER || userInfo.getUserType() == CommonValue.AUTHENTICATION_CARRIER) {
                    UserApi.getInstance().getBindVehicle(userInfo.getCompany_id(), this);
                } else {
                    UserApi.getInstance().getBindVehicle(userInfo.getDriverId(), this);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (requestUrl.equals(RequestCenter.VEHICLE_ACTION) && method.equals(RequestCenter.GET_BINDVEHICLE_METHOD)) {
            JSONObject mainData = response.getMainData();
            try {
                ResponseVehicle vehicle = JsonFactory.getJsonUtils().parseObject(mainData.getString("vehicleInfo"), ResponseVehicle.class);
                SPUtils.getInstance().put(CommonValue.VEHICLEINFO, vehicle);
                getView().goMain();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.USER_ACTION) && method.equals(RequestCenter.GET_USERINFO_METHOD)) {
            getView().getUserInfoFaild();
        }
    }
}
