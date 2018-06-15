package org.zzy.driver.mvp.presenter;

import com.zzy.quick.mvp.presenter.BasePresenter;

import org.zzy.driver.mvp.ui.activity.wallet.NoneCardActivity;
import org.zzy.driver.utils.UserInfoUtils;

/**
 * Created by zhou on 2018/6/15.
 */

public class NoneCardPresenter extends BasePresenter<NoneCardActivity> {

    /**
     * 添加银行卡前验证
     * */
    public void verify(){
        if(UserInfoUtils.getUserInfo().getHasPayPassword()==0){
            getView().promptSettingPassword();
        }else{
            getView().goInputPassword();
        }
    }
}
