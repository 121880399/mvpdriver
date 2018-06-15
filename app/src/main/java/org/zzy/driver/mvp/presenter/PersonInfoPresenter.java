package org.zzy.driver.mvp.presenter;

import com.zzy.quick.mvp.presenter.BasePresenter;
import com.zzy.quick.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.UserApi;
import org.zzy.driver.mvp.ui.activity.PersonInfoActivity;
import org.zzy.driver.utils.UserInfoUtils;

import java.io.File;

/**
 * Created by zhou on 2018/6/1.
 */

public class PersonInfoPresenter extends BasePresenter<PersonInfoActivity> implements HttpCallBack {

    public void setUserHead(File file){
        UserApi.newInstance().changeAvatar(file, UserInfoUtils.getUserInfo().getId(),this);
    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.USER_ACTION) && method.equals(RequestCenter.CHANGEAVATAR_METHOD)){
            JSONObject object = response.getMainData();
            ResponseUserInfo userInfo = UserInfoUtils.getUserInfo();
            try {
                userInfo.setIcon(object.getString("avator"));
                SPUtils.getInstance().put(CommonValue.USERINFO, userInfo);
                getView().showAvatar();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }
}
