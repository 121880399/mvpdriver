package org.zzy.driver.mvp.presenter;

import com.google.gson.Gson;
import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.mvp.presenter.BasePresenter;
import com.zzy.quick.net.HttpManager;
import com.zzy.quick.net.HttpSubscriber;
import com.zzy.quick.net.NetError;
import com.zzy.quick.utils.EncryptUtils;
import com.zzy.quick.utils.SPUtils;
import com.zzy.quick.utils.log.LogFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.zzy.driver.common.AppConfig;
import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.model.net.HttpHeader;
import org.zzy.driver.mvp.model.net.HttpRequest;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.api.UserApi;
import org.zzy.driver.mvp.ui.LoginActivity;
import org.zzy.driver.utils.MD5Util;

import io.reactivex.functions.Consumer;

/**
 * Created by zhou on 2018/4/4.
 */

public class LoginPresenter extends BasePresenter<LoginActivity> {

    public void login(String userName,String password){
        final HttpRequest request=new HttpRequest();
        request.addHeader("action","user");
        request.addHeader("method","login");
        request.putParams("username",userName);
        //密码要两次加密，加密算法使用APP工程下uitils包中的类
        request.putParams("password", MD5Util.md5Encode(MD5Util.md5Encode(password)));
        HttpHeader.setDefaultHeader(request);
        String strEntity= JsonFactory.getJsonUtils().toJson(request);
        UserApi.getUserService().login(strEntity)
               .compose(HttpManager.<HttpResult>getErrorTransformer())
               .compose(HttpManager.<HttpResult>getFlowableScheduler())
                .subscribe(new HttpSubscriber<HttpResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        error.getMessage();
                    }

                    @Override
                    public void onNext(HttpResult httpResult) {
                        //登录成功后把用户信息存入SP中
                        JSONObject mainData = httpResult.getMainData();
                        try {
                            String token=mainData.getString("token");
                            ResponseUserInfo userInfo=JsonFactory.getJsonUtils().parseObject(mainData.getString("userInfo"),ResponseUserInfo.class);
                            SPUtils.getInstance().put(CommonValue.USERINFO,userInfo);
                            SPUtils.getInstance().put(CommonValue.TOKENCODE,token);
                            SPUtils.getInstance().put(CommonValue.USERID,userInfo.getId());
                            getView().goMain();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }
}
