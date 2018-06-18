package org.zzy.driver.mvp.presenter;

import com.zzy.quick.mvp.presenter.BasePresenter;

import org.zzy.driver.mvp.model.net.HttpCallBack;
import org.zzy.driver.mvp.model.net.HttpResult;
import org.zzy.driver.mvp.model.net.RequestCenter;
import org.zzy.driver.mvp.model.net.api.BusinessApi;
import org.zzy.driver.mvp.ui.activity.wallet.ResetPayPasswordActivity;
import org.zzy.driver.utils.UserInfoUtils;
import org.zzy.driver.view.TimeCount;

/**
 * Created by zhou on 2018/6/15.
 */

public class ResetPayPasswordPresenter extends BasePresenter<ResetPayPasswordActivity> implements HttpCallBack, TimeCount.CountDownTimerListener {

    private TimeCount mTimeCount;

    public void getSmsCode(){
        mTimeCount=new TimeCount(60*1000L,1000);
        mTimeCount.setCountDownTimerListener(this);
        BusinessApi.getInstance().getPaySms(UserInfoUtils.getUserInfo().getPhone(),this);
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
}
