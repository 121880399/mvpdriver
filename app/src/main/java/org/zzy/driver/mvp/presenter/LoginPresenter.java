package org.zzy.driver.mvp.presenter;

import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.mvp.presenter.BasePresenter;
import com.zzy.quick.net.HttpManager;
import com.zzy.quick.net.HttpSubscriber;
import com.zzy.quick.net.NetError;
import com.zzy.quick.utils.SPUtils;
import com.zzy.quick.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpHeader;
import org.zzy.driver.mvp.model.net.HttpRequest;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BaseApi;
import org.zzy.driver.mvp.model.net.api.UserApi;
import org.zzy.driver.mvp.ui.LoginActivity;
import org.zzy.driver.utils.MD5Util;

/**
 * @function LoginPresenter用来写登录的业务逻辑
 * Created by zhou on 2018/4/4.
 */

public class LoginPresenter extends BasePresenter<LoginActivity> implements HttpCallBack {


    public void login(String userName,String password){
        UserApi api=new UserApi();
        //发起登录请求
        api.login(userName,password,this);
    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if(requestUrl.equals(RequestCenter.USER_ACTION)&&method.equals(RequestCenter.LOGIN_METHOD)) {
            //登录成功后将用户数据保存到SP中
            JSONObject mainData = response.getMainData();
            if(mainData!=null) {
                try {
                    String token = mainData.getString("token");
                    ResponseUserInfo userInfo = JsonFactory.getJsonUtils().parseObject(mainData.getString("userInfo"), ResponseUserInfo.class);
                    SPUtils.getInstance().put(CommonValue.USERINFO, userInfo);
                    SPUtils.getInstance().put(CommonValue.TOKENCODE, token);
                    SPUtils.getInstance().put(CommonValue.USERID, String.valueOf(userInfo.getId()));
                    getView().goMain();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {
        if(requestUrl.equals(RequestCenter.USER_ACTION)&&method.equals(RequestCenter.LOGIN_METHOD)) {
            ToastUtils.showShort(error.getErrorMsg());
        }
    }
}
