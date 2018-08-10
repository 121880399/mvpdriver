package org.zzy.driver.mvp.presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.zzy.quick.mvp.presenter.BasePresenter;

import org.zzy.driver.R;
import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.activity.wallet.WithdrawActivity;
import org.zzy.driver.utils.UserInfoUtils;

/**
 * Created by zhou on 2018/6/19.
 */

public class WithdrawPresenter extends BasePresenter<WithdrawActivity> implements HttpCallBack {

    //最大可提现金额
    private double mMaxWithdrawMoney;

    public void getBindingBankCard(){
        BusinessApi.getInstance().getBindingBankCard(UserInfoUtils.getUserInfo().getDriverId(),this);
    }

    public void getWalletInfo(){
        BusinessApi.getInstance().getWalletInfo(UserInfoUtils.getUserInfo().getDriverId(),this);
    }


    /**
     * 提现前的验证
     * */
    public void withdrawVerify(String withdrawMoney){
        if(verifyDate(withdrawMoney)){
            getView().inputPayPassword(withdrawMoney);
        }
    }


    /**
     * 提现
     * */
    public void withdraw(String withdrawMoney,String password){
        BusinessApi.getInstance().withdraw(UserInfoUtils.getUserInfo().getDriverId(),withdrawMoney,password,this);
    }

    /**
     * 验证数据
     * */
    private boolean verifyDate(String data){
        if(TextUtils.isEmpty(data)){
            getView().showError("请输入提现金额！");
            return false;
        }
        if(Double.valueOf(data)<=0){
            getView().showError("提现金额不能小于等于0");
            return false;
        }
        if(Double.valueOf(data)>mMaxWithdrawMoney){
            getView().showError("提现金额超出可提现额度！");
            return false;
        }
        return true;
    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.WALLET_ACTION) && method.equals(RequestCenter.GET_BINDINGBANKCARD_METHOD)) {
            JSONObject mainData = response.getMainData();
                getView().setBankCardInfo(mainData.getString("bankCardNum"),mainData.getString("userName"),mainData.getString("bankCardNanme"));
        }
        if (requestUrl.equals(RequestCenter.WALLET_ACTION) && method.equals(RequestCenter.GET_WALLETINFO_METHOD)) {
            JSONObject mainData = response.getMainData();
                mMaxWithdrawMoney=mainData.getDouble("withdrawAccount");
                getView().setWalletInfo(mMaxWithdrawMoney);
        }
        if (requestUrl.equals(RequestCenter.WALLET_ACTION) && method.equals(RequestCenter.WITHDRAW_METHOD)) {
            getView().withdrawSuccess();
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.WALLET_ACTION) && method.equals(RequestCenter.WITHDRAW_METHOD)) {
            if(error.getResponse().getStatus().equals(CommonValue.PAYPASSWORD_ERROR)){
                getView().showPasswordError();
            }
            getView().clearPassword();
        }
    }


}
