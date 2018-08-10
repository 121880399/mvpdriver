package org.zzy.driver.mvp.presenter;

import com.alibaba.fastjson.JSONObject;
import com.zzy.quick.mvp.presenter.BasePresenter;

import org.zzy.driver.common.CommonValue;
import org.zzy.driver.mvp.model.bean.response.ResponseUserInfo;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.activity.wallet.WalletActivity;
import org.zzy.driver.utils.UserInfoUtils;
import org.zzy.driver.utils.VehicleInfoUtils;

/**
 * Created by zhou on 2018/6/14.
 */

public class WalletPresenter extends BasePresenter<WalletActivity> implements HttpCallBack {
    public  static final int MYBANKCARD=1;//我的银行卡
    public  static final int WITHDRAW=2;//提现



    private boolean isBoundBank;

    /**
     * 获取钱包信息
     */
    public void getWalletInfo() {
        BusinessApi api = BusinessApi.getInstance();
        api.getWalletInfo(VehicleInfoUtils.getVehicleInfo().getDriver_id(), this);
    }

    /**
     * 支付设置验证
     * */
    public void paySettingVerify(){
        if(verifyUserStatus()){
            if(UserInfoUtils.getUserInfo().getHasPayPassword()==0){
                getView().promptSettingPassword();
            } else{
                getView().goPaySetting();
            }
        }
    }


    /**
     * 进入我的银行卡,提现，进行权限验证
     */
    public void verifyData(int type) {
        if (verifyUserStatus()) {
            //是否绑卡，如果没绑卡不能进入我的银行卡界面，也不能提现
            if (isBoundBank) {
                if (type == MYBANKCARD) {
                    getView().goMyBankCard();
                } else if (type == WITHDRAW) {
                    getView().goWithdraw();
                }
            } else {
                getView().promptBoundCard();
            }
        }
    }

    /**
     * 验证用户当前状态
     * */
    private boolean verifyUserStatus(){
        ResponseUserInfo userInfo = UserInfoUtils.getUserInfo();
        if (userInfo.getIdentStatus() != CommonValue.CHECKPASS && userInfo.getQuaStatus() != CommonValue.CHECKPASS) {
            getView().showError("用户资料未完善不能进行此操作！");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if (requestUrl.equals(RequestCenter.WALLET_ACTION) && method.equals(RequestCenter.GET_WALLETINFO_METHOD)) {
            JSONObject mainData = response.getMainData();
                double generalIncome = mainData.getDouble("generalIncome");
                double waitConfirmedIncome = mainData.getDouble("waitConfirmedIncome");
                double withdrawAccount = mainData.getDouble("withdrawAccount");
                isBoundBank = mainData.getBoolean("isBoundBank");
                getView().showData(generalIncome, waitConfirmedIncome, withdrawAccount);

        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }
}
