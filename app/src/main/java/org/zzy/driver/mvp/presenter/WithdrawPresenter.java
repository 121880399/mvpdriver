package org.zzy.driver.mvp.presenter;

import android.text.TextUtils;

import com.zzy.quick.mvp.presenter.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;
import org.zzy.driver.R;
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
     * 提现
     * */
    public void withdraw(String withdrawMoney){
        if(verifyDate(withdrawMoney)){
            getView().inputPayPassword();
        }
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
            try {
                getView().setBankCardInfo(mainData.getString("bankCardNum"),mainData.getString("userName"),mainData.getString("bankCardNanme"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (requestUrl.equals(RequestCenter.WALLET_ACTION) && method.equals(RequestCenter.GET_WALLETINFO_METHOD)) {
            JSONObject mainData = response.getMainData();
            try {
                mMaxWithdrawMoney=mainData.getDouble("withdrawAccount");
                getView().setWalletInfo(mMaxWithdrawMoney);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }


}
