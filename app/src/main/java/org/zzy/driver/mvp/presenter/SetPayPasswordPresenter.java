package org.zzy.driver.mvp.presenter;

import android.text.TextUtils;

import com.zzy.quick.mvp.presenter.BasePresenter;

import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.activity.wallet.SetPayPasswordActivity;
import org.zzy.driver.utils.UserInfoUtils;
import org.zzy.driver.view.TimeCount;

/**
 * Created by zhou on 2018/6/19.
 */

public class SetPayPasswordPresenter extends BasePresenter<SetPayPasswordActivity> implements TimeCount.CountDownTimerListener, HttpCallBack {

    private TimeCount mTimeCount;

    public void getSmsCode(){
        mTimeCount=new TimeCount(60*1000L,1000);
        mTimeCount.setCountDownTimerListener(this);
        BusinessApi.getInstance().getPaySms(UserInfoUtils.getUserInfo().getPhone(),this);
    }


    /**
     * 提交数据
     * */
    public void submit(String idCardNum,String password,String verifyCode){
        if(verifyData(idCardNum,password,verifyCode)){
            setPassword(idCardNum,password,verifyCode);
        }
    }

    /**
     * 设置支付密码
     * */
    private void setPassword(String idCardNum,String password,String verifyCode){
        BusinessApi.getInstance().setPayPassword(idCardNum,password,verifyCode,this);
    }

    private boolean verifyData(String idCardNum,String password,String verifyCode){
        if(TextUtils.isEmpty(idCardNum)){
            getView().showError("请输入身份证号！");
            return false;
        }
        if(TextUtils.isEmpty(password)){
            getView().showError("请输入支付密码！");
            return false;
        }
        if(TextUtils.isEmpty(verifyCode)){
            getView().showError("请输入验证码！");
            return false;
        }
        return true;
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

    @Override
    public void doSuccess(HttpResult response, String requestUrl, String method) {
        if(requestUrl.equals(RequestCenter.WALLET_ACTION) && method.equals(RequestCenter.GET_PAYSMS_METHOD)){
            //验证码发送成功后才开始计时,并且不能点击发送验证码按钮
            mTimeCount.start();
            getView().setEnabled(false);
        }
    }

    @Override
    public void doFaild(HttpResult error, String requestUrl, String method) {

    }

    /**
     * Activity退出后，停止倒数
     * */
    public void stopTimeCount() {
        if(null!=mTimeCount){
            mTimeCount.cancel();
        }
    }
}
