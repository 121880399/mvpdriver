package org.zzy.driver.mvp.presenter;

import com.zzy.quick.mvp.presenter.BasePresenter;

import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.UserApi;
import org.zzy.driver.mvp.ui.activity.wallet.InputPasswordActivity;

/**
 * Created by zhou on 2018/6/15.
 */

public class InputPasswordPresenter extends BasePresenter<InputPasswordActivity> implements HttpCallBack {

    /**
     * 验证支付密码
     * */
    public void checkPayPassword(String password){
        UserApi.newInstance().checkPayPassword(password,this);
    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if(requestUrl.equals(RequestCenter.WALLET_ACTION)&&method.equals(RequestCenter.CHECK_PAYPASSWORD_METHOD)) {
            getView().goAddCard();
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }
}
