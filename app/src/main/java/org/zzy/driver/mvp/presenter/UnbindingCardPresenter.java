package org.zzy.driver.mvp.presenter;

import com.zzy.quick.mvp.presenter.BasePresenter;

import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.activity.wallet.UnbindingCardActivity;
import org.zzy.driver.utils.UserInfoUtils;

/**
 * Created by zhou on 2018/6/15.
 */

public class UnbindingCardPresenter extends BasePresenter<UnbindingCardActivity> implements HttpCallBack {

    private String payPassword;

    /**
     * 验证支付密码
     * */
    public void checkPayPassword(String password){
        payPassword=password;
        BusinessApi.getInstance().checkPayPassword(password,this);
    }

    /**
     * 解绑银行卡
     * */
    public void unBindingBankCard(){
        BusinessApi.getInstance().unBindingBankCard(UserInfoUtils.getUserInfo().getDriverId(),payPassword,this);
    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if(requestUrl.equals(RequestCenter.WALLET_ACTION)&&method.equals(RequestCenter.CHECK_PAYPASSWORD_METHOD)){
            getView().showUnbindingTip();
        }
        if(requestUrl.equals(RequestCenter.WALLET_ACTION)&&method.equals(RequestCenter.UNBINDING_BANKCARD_METHOD)) {
            getView().goWallet();
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {
        if(requestUrl.equals(RequestCenter.WALLET_ACTION)&&method.equals(RequestCenter.CHECK_PAYPASSWORD_METHOD)){
            getView().showPasswordError();
        }
    }
}
