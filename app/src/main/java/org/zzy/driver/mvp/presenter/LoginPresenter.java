package org.zzy.driver.mvp.presenter;

import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.mvp.presenter.BasePresenter;
import com.zzy.quick.utils.SPUtils;
import com.zzy.quick.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.model.bean.response.ResponseVehicle;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.UserApi;
import org.zzy.driver.mvp.ui.activity.LoginActivity;

import okhttp3.Response;

/**
 * @function LoginPresenter用来写登录的业务逻辑
 * Created by zhou on 2018/4/4.
 */

public class LoginPresenter extends BasePresenter<LoginActivity> implements HttpCallBack {


    public void login(String userName, String password) {
        //发起登录请求
        UserApi.newInstance().login(userName, password, this);
    }


    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.USER_ACTION) && method.equals(RequestCenter.LOGIN_METHOD)) {
            //登录成功后将用户数据保存到SP中
            JSONObject mainData = response.getMainData();
            if (mainData != null) {
                try {
                    String token = mainData.getString("token");
                    SPUtils.getInstance().put(CommonValue.TOKENCODE, token);
                    //这里这样判断是因为微服务的调整，为了兼容以前的接口跟微服务以后的接口所做的判断
                    //以前的接口登录直接返回token和用户信息，微服务接口只返回token
                    ResponseUserInfo userInfo = JsonFactory.getJsonUtils().parseObject(mainData.getString("userInfo"), ResponseUserInfo.class);
                    if (userInfo != null) {
                        SPUtils.getInstance().put(CommonValue.USERINFO, userInfo);
                        SPUtils.getInstance().put(CommonValue.USERID, String.valueOf(userInfo.getId()));
                        UserApi.newInstance().getUserInfo(userInfo.getId(), this);
                    } else {
                        //这里为兼容微服务和老版本的接口
                        UserApi.newInstance().getUserInfo(0, this);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        if (requestUrl.equals(RequestCenter.USER_ACTION) && method.equals(RequestCenter.GET_USERINFO_METHOD)) {
            JSONObject mainData = response.getMainData();
            try {
                ResponseUserInfo userInfo = JsonFactory.getJsonUtils().parseObject(mainData.getString("userInfo"), ResponseUserInfo.class);
                SPUtils.getInstance().put(CommonValue.USERINFO, userInfo);
                SPUtils.getInstance().put(CommonValue.USERID, String.valueOf(userInfo.getId()));
                //如果是签约承运商或者是认证承运商 就通过公司id查找车辆信息
                if (userInfo.getUserType() == CommonValue.SIGN_CARRIER || userInfo.getUserType() == CommonValue.AUTHENTICATION_CARRIER) {
                    UserApi.newInstance().getBindVehicle(userInfo.getCompany_id(), this);
                } else {
                    UserApi.newInstance().getBindVehicle(userInfo.getDriverId(), this);
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
        if (requestUrl.equals(RequestCenter.USER_ACTION) && method.equals(RequestCenter.LOGIN_METHOD)) {
            ToastUtils.showShort(error.getErrorMsg());

        }
    }
}
