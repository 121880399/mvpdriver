package org.zzy.driver.mvp.presenter;

import com.alibaba.fastjson.JSONObject;
import com.zzy.quick.mvp.presenter.BasePresenter;

import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.activity.wallet.MyBankCardActivity;
import org.zzy.driver.utils.UserInfoUtils;

/**
 * Created by zhou on 2018/6/15.
 */

public class MyBankCardPresenter extends BasePresenter<MyBankCardActivity> implements HttpCallBack {

    /**
     * 获取绑定银行卡信息
     * */
    public void getBindingBankCard(){
        BusinessApi.getInstance().getBindingBankCard(UserInfoUtils.getUserInfo().getDriverId(),this);
    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if(requestUrl.equals(RequestCenter.WALLET_ACTION)&&method.equals(RequestCenter.GET_BINDINGBANKCARD_METHOD)){
            JSONObject mainData = response.getMainData();
                getView().showCardInfo(mainData.getString("bankCardNum"),mainData.getString("userName"),mainData.getString("bankCardNanme"));
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }
}
