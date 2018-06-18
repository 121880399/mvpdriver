package org.zzy.driver.mvp.presenter;

import android.text.TextUtils;

import com.zzy.quick.json.JsonFactory;
import com.zzy.quick.mvp.presenter.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;
import org.zzy.driver.mvp.model.bean.request.RequestAddCard;
import org.zzy.driver.mvp.model.bean.response.ResponseCardInfo;
import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.activity.wallet.AddCardActivity;
import org.zzy.driver.utils.UserInfoUtils;
import org.zzy.driver.view.TimeCount;

/**
 * Created by zhou on 2018/6/15.
 */

public class AddCardPresenter extends BasePresenter<AddCardActivity> implements HttpCallBack, TimeCount.CountDownTimerListener {

    private TimeCount mTimeCount;

    /**
     * 通过银行卡查询银行名称
     * */
    public void getBankRelative(String cardNum){
        BusinessApi.getInstance().getBankRelative(cardNum,this);
    }

    /**
     * 发送验证码
     * */
    public void getBindSms(){
        mTimeCount=new TimeCount(60*1000L,1000);
        mTimeCount.setCountDownTimerListener(this);
        BusinessApi.getInstance().getBindSms(UserInfoUtils.getUserInfo().getPhone(),this);
    }

    @Override
    public void onFinish() {
        //完成倒数后恢复发送验证码，并且可以点击
        getView().renewSms();
        getView().setEnabled(true);
    }

    @Override
    public void onTick(int untilFinished) {
        getView().setTime(untilFinished+"s");
    }


    /**
     * Activity退出后，停止倒数
     * */
    public void stopTimeCount() {
        if(null!=mTimeCount){
            mTimeCount.cancel();
        }
    }

    /**
     * 绑定银行卡
     * **/
    public void bindingBankCard(RequestAddCard mAddCard) {
        if(verifyData(mAddCard)){
            BusinessApi.getInstance().bindingBankCard(UserInfoUtils.getUserInfo().getDriverId(),mAddCard,this);
        }
    }

    /**
     * 绑定前验证数据
     * */
    private boolean verifyData(RequestAddCard mAddCard){
        if(TextUtils.isEmpty(mAddCard.getUserName())){
            getView().showError("请填写持卡人姓名！");
            return false;
        }
        if(TextUtils.isEmpty(mAddCard.getBankCardNum())){
            getView().showError("请填写银行卡号！");
            return false;
        }
        if(TextUtils.isEmpty(mAddCard.getBankCardNum())){
            getView().showError("请填写验证码！");
            return false;
        }
        return true;
    }



    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if(requestUrl.equals(RequestCenter.WALLET_ACTION)&&method.equals(RequestCenter.GET_BANKRELATIVE_METHOD)){
            JSONObject mainData = response.getMainData();
            try {
                ResponseCardInfo cardInfo= JsonFactory.getJsonUtils().parseObject(mainData.getString("RelativeInfo"),ResponseCardInfo.class);
                getView().setCardInfo(cardInfo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(requestUrl.equals(RequestCenter.WALLET_ACTION) && method.equals(RequestCenter.GET_BINDSMS_METHOD)){
            //验证码发送成功后才开始计时,并且不能点击发送验证码按钮
            mTimeCount.start();
            getView().setEnabled(false);
        }
        if(requestUrl.equals(RequestCenter.WALLET_ACTION)&&method.equals(RequestCenter.BINDING_BANKCARD_METHOD)){
            getView().showTip();
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }
}
